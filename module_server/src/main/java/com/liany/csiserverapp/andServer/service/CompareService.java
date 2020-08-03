package com.liany.csiserverapp.andServer.service;

import android.content.Context;

import com.liany.csiserverapp.andServer.model.CompareDB;
import com.liany.csiserverapp.andServer.model.SceneDB;
import com.liany.csiserverapp.andServer.model.SysDB;
import com.liany.csiserverapp.andServer.model.UserDb;
import com.liany.csiserverapp.base.Constants;
import com.liany.csiserverapp.debug.ServerApplication;
import com.liany.csiserverapp.diagnose.CompareEntity;
import com.liany.csiserverapp.diagnose.ComparePhoto;
import com.liany.csiserverapp.diagnose.CompareTaskEntity;
import com.liany.csiserverapp.diagnose.ContactsEntity;
import com.liany.csiserverapp.diagnose.CrimeItem;
import com.liany.csiserverapp.diagnose.EvidenceEntity;
import com.liany.csiserverapp.diagnose.Photo;
import com.liany.csiserverapp.diagnose.sysOrgan;
import com.liany.csiserverapp.diagnose.sysUser;
import com.liany.csiserverapp.network.response.Response;
import com.liany.csiserverapp.network.webservice.NetWorkUtils;
import com.liany.csiserverapp.utils.Compare;
import com.liany.csiserverapp.utils.FileUtils;
import com.liany.csiserverapp.utils.GsonUtils;
import com.liany.csiserverapp.utils.LogUtils;
import com.liany.csiserverapp.utils.StringUtils;
import com.liany.csiserverapp.utils.ZipUtils;
import com.liany.csiserverapp.diagnose.CrimeItem;
import com.yanzhenjie.andserver.http.HttpRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @创建者 ly
 * @创建时间 2020/3/17
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class CompareService {

    static ExecutorService featureES = Executors.newCachedThreadPool();

    public static String getContactsCrimeList(String userName) {
        List<CrimeItem> crimeItems = CompareDB.getContactsCrimeList(userName);
//        if(crimeItems.size() > 0) {
//            return GsonUtils.successJson(crimeItems);
//        }
//        return GsonUtils.faildJson(500,"暂无对比记录，请提交比对后重试");
        return GsonUtils.successJson(crimeItems);
    }

    public static String getEvidenceFingerCrimeList(String userName) {
        List<CrimeItem> crimeItems = CompareDB.getEvidenceCrimeList(userName,Constants.TYPE_FINGER_EVIDENCE);
//        if(crimeItems.size() > 0) {
//            return GsonUtils.successJson(crimeItems);
//        }
//        return GsonUtils.faildJson(500,"暂无对比记录，请提交比对后重试");
        return GsonUtils.successJson(crimeItems);
    }

    public static String getEvidenceFootCrimeList(String userName) {
        List<CrimeItem> crimeItems = CompareDB.getEvidenceCrimeList(userName,Constants.TYPE_FOOT_EVIDENCE);
        return GsonUtils.successJson(crimeItems);
    }

    public static String startEvidence(HttpRequest request, Context mContext, CrimeItem item, String userId) {
//        String userName = RequestUtils.getUser(request);
//        String password = RequestUtils.getPassword(request);
//        sysUser sysUser = UserDb.selectUser(userName, password);
        sysUser sysUser = UserDb.selectUserById(userId);
        List<EvidenceEntity> evidenceEntityList = new ArrayList<>();
        List<EvidenceEntity> evidenceItem = item.getEvidenceItem();
        for(EvidenceEntity entity : evidenceItem) {
            if(entity.getEvidenceCategory().equals("手印")) {
                Photo photo = CompareDB.selectPhotoByParentId(entity.getId(), entity.getCrimeId(), Constants.photoState_evidence);
                entity.__setDaoSession(ServerApplication.getDaoSession());
                entity.setPhoto(photo);
                List<ComparePhoto> comparePhotos = CompareDB.selectComparePhotoByPhotoId(photo, Constants.TYPE_FINGER_EVIDENCE + "");
                if(comparePhotos.size() == 0) {
                    evidenceEntityList.add(entity);
                }
            }
        }
        if(evidenceEntityList.size() == 0) {
            return GsonUtils.faildJson(500,"暂无新指纹可以对比");
        }
        FileUtils.deleteDirectory(Constants.zipPath);
        String compareId = StringUtils.getUUID();
        String createName = sysUser.getTrueName();
        String crimeId = evidenceEntityList.get(0).getCrimeId();
        long createDate = System.currentTimeMillis();

        CompareEntity compareEntity = new CompareEntity();
        compareEntity.setId(compareId);
        compareEntity.setCrimeId(crimeId);
        compareEntity.setCreateDate(System.currentTimeMillis());
        compareEntity.setCreateName(createName);
        compareEntity.setRev2(Constants.TYPE_FINGER_EVIDENCE + "");
        //把bmp文件编号
        List<ComparePhoto> comparePhotos = new ArrayList<>();
        for(int i = 0; i < evidenceEntityList.size(); i++) {
            ComparePhoto photo = new ComparePhoto();
            photo.setId(StringUtils.getUUID());
            photo.setCompareId(compareId);
            photo.setEvidenceName(evidenceEntityList.get(i).getEvidenceName());
            photo.setSceneId(evidenceEntityList.get(i).getCrimeId());
            photo.setPhotoId(evidenceEntityList.get(i).getPhoto().getId());
            photo.setPhotoPath(evidenceEntityList.get(i).getPhoto().getPath());
            photo.setPhotoType(Constants.TYPE_FINGER_EVIDENCE + "");
            photo.setCreateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
            photo.setCreateName(createName);
            photo.setUpdateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
            photo.setFingerNo(i+1);
            photo.setServicePath(evidenceEntityList.get(i).getPhoto().getServerPath());
            photo.setRev2(item.getCasetype() + "@@" + item.getLocation());
            //编号后复制到指定文件夹下改名，准备打包zip
            String substring = evidenceEntityList.get(i).getPhoto().getPath().substring(0, evidenceEntityList.get(i).getPhoto().getPath().lastIndexOf("."));
            FileUtils.copyRename(substring + ".bmp",Constants.zipPath,i+1 + ".bmp");
            comparePhotos.add(photo);
        }
        //打包bmp压缩文件
        String zipFileName = StringUtils.getCompareZipFileName(createDate);
        try {
            ZipUtils.ZipFolder(Constants.zipPath,Constants.zipFilePath + File.separator + zipFileName);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.faildJson(500,e.getMessage());
        }finally {
            //不管是否压缩成功，压缩后删除zipPath
            FileUtils.deleteDirectory(Constants.zipPath);
        }
        //上传bmp文件压缩包
        CompareTaskEntity entity = new CompareTaskEntity();
        entity.setId(compareId);
        entity.setSceneId(crimeId);
        entity.setUserId(sysUser.getId());
        entity.setFileName(zipFileName);
        entity.setCreateUser(sysUser.getTrueName());
        entity.setCreateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
        entity.setTaskNo("RW" + StringUtils.long2String(createDate,"yyyyMMddHHmmss"));
        sysOrgan organ = SysDB.getOrgIdById(item.getAreaKey());
        String unitName = "";
        String unitCode = "";
        if(organ != null) {
            unitCode = organ.getUnitCode();
            unitName = organ.getUnitName();
        }
        //案件类别@@发案地点@@勘验地点@@勘验人@@勘验开始时间@@单位名称@@单位代码
        entity.setRev3(item.getCasetype()
                + "@@" + item.getLocation()
                + "@@" + item.getAccessLocation()
                + "@@" + item.getUserName()
                + "@@" + StringUtils.long2String(item.getAccess_start_time(),"yyyy-MM-dd HH:mm:ss")
                + "@@" + unitName
                + "@@" + unitCode);
        String sendJson = GsonUtils.gsonString(entity);
        NetWorkUtils.sendFingerTask(mContext, "sendFingerTask", sendJson, Constants.zipFilePath + File.separator + zipFileName, new NetWorkUtils.Callback() {
            @Override
            public void onNext(String result) {
                if(StringUtils.checkString(result)) {
                    Response<String> response = GsonUtils.fromJsonObject(result,String.class);
                    if(response.getCode() == 0) {
                        //上传服务器成功，写入本地数据库
                        CompareDB.updateCompareEntity(compareEntity);
                        CompareDB.updateComparePhotoList(comparePhotos);
//                        ToastUtils.showLong("提交对比成功");
//                        XfUtils.startSpeak("提交对比成功");
                    }else {
//                        XfUtils.startSpeak("提交对比失败，服务器返回错误" + response.getMsg());
//                        ToastUtils.showLong("提交对比失败：" + response.getMsg());
                    }
                }else {
//                    XfUtils.startSpeak("提交对比失败，服务器返回数据为空");
//                    ToastUtils.showLong("提交对比失败：服务器返回错误");
                }
                //删除压缩包
                FileUtils.deleteFile(Constants.zipFilePath + File.separator + zipFileName);
            }

            @Override
            public void onError(Throwable e) {
                //删除压缩包
                FileUtils.deleteFile(Constants.zipFilePath + File.separator + zipFileName);
//                ToastUtils.showLong("提交对比失败：" + e.getMessage());
//                XfUtils.startSpeak("提交对比失败，访问服务器错误");
            }
        });
        return GsonUtils.successJson("提交比对中");
    }

    public static String startEvidenceOne(HttpRequest request, Context mContext, String evidenceId, String userId) {
        sysUser sysUser = UserDb.selectUserById(userId);
        EvidenceEntity evidenceEntity = SceneDB.selectEvidenceById(evidenceId);
        if(evidenceEntity == null) {
            return GsonUtils.faildJson(500,"没有该痕迹数据");
        }
        CrimeItem item = SceneDB.selectCrimeById(evidenceEntity.getCrimeId());
        if(item == null) {
            return GsonUtils.faildJson(500,"没有该现场数据");
        }
        Photo photo = CompareDB.selectPhotoByParentId(evidenceEntity.getId(), evidenceEntity.getCrimeId(), Constants.photoState_evidence);
        evidenceEntity.__setDaoSession(ServerApplication.getDaoSession());
        evidenceEntity.setPhoto(photo);
        List<ComparePhoto> comparePhotos = CompareDB.selectComparePhotoByPhotoId(photo, Constants.TYPE_FINGER_EVIDENCE + "");
        if(comparePhotos.size() > 0) {
            return GsonUtils.faildJson(500,"该指纹已经提交比对");
        }
        FileUtils.deleteDirectory(Constants.zipPath);
        String compareId = StringUtils.getUUID();
        String createName = sysUser.getTrueName();
        String crimeId = evidenceEntity.getCrimeId();
        long createDate = System.currentTimeMillis();

        CompareEntity compareEntity = new CompareEntity();
        compareEntity.setId(compareId);
        compareEntity.setCrimeId(crimeId);
        compareEntity.setCreateDate(System.currentTimeMillis());
        compareEntity.setCreateName(createName);
        compareEntity.setRev2(Constants.TYPE_FINGER_EVIDENCE + "");
        //把bmp文件编号
        List<ComparePhoto> comparePhotos1 = new ArrayList<>();
        ComparePhoto comparePhoto = new ComparePhoto();
        comparePhoto.setId(StringUtils.getUUID());
        comparePhoto.setCompareId(compareId);
        comparePhoto.setEvidenceName(evidenceEntity.getEvidenceName());
        comparePhoto.setSceneId(evidenceEntity.getCrimeId());
        comparePhoto.setPhotoId(evidenceEntity.getPhoto().getId());
        comparePhoto.setPhotoPath(evidenceEntity.getPhoto().getPath());
        comparePhoto.setPhotoType(Constants.TYPE_FINGER_EVIDENCE + "");
        comparePhoto.setCreateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
        comparePhoto.setCreateName(createName);
        comparePhoto.setUpdateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
        comparePhoto.setFingerNo(1);
        comparePhoto.setRev2(item.getCasetype() + "@@" + item.getLocation());
        comparePhoto.setServicePath(evidenceEntity.getPhoto().getServerPath());
        //编号后复制到指定文件夹下改名，准备打包zip
        String substring = evidenceEntity.getPhoto().getPath().substring(0, evidenceEntity.getPhoto().getPath().lastIndexOf("."));
        FileUtils.copyRename(substring + ".bmp",Constants.zipPath,1 + ".bmp");
        comparePhotos1.add(comparePhoto);
        //打包bmp压缩文件
        String zipFileName = StringUtils.getCompareZipFileName(createDate);
        try {
            //目标目录
            File targetDir = new File(Constants.zipFilePath);
            //创建目录
            if (!targetDir.exists()) {
                targetDir.mkdirs();
            }
            ZipUtils.ZipFolder(Constants.zipPath,Constants.zipFilePath + File.separator + zipFileName);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.faildJson(500,e.getMessage());
        }finally {
            //不管是否压缩成功，压缩后删除zipPath
            FileUtils.deleteDirectory(Constants.zipPath);
        }
        //上传bmp文件压缩包
        CompareTaskEntity entity = new CompareTaskEntity();
        entity.setId(compareId);
        entity.setSceneId(crimeId);
        entity.setUserId(sysUser.getId());
        entity.setFileName(zipFileName);
        entity.setCreateUser(sysUser.getTrueName());
        entity.setCreateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
        entity.setTaskNo("RW" + StringUtils.long2String(createDate,"yyyyMMddHHmmss"));
        sysOrgan organ = SysDB.getOrgIdById(item.getAreaKey());
        String unitName = "";
        String unitCode = "";
        if(organ != null) {
            unitCode = organ.getUnitCode();
            unitName = organ.getUnitName();
        }
        //案件类别@@发案地点@@勘验地点@@勘验人@@勘验开始时间@@单位名称@@单位代码
        entity.setRev3(item.getCasetype()
                + "@@" + item.getLocation()
                + "@@" + item.getAccessLocation()
                + "@@" + item.getUserName()
                + "@@" + StringUtils.long2String(item.getAccess_start_time(),"yyyy-MM-dd HH:mm:ss")
                + "@@" + unitName
                + "@@" + unitCode);
        String sendJson = GsonUtils.gsonString(entity);
        LogUtils.e("sendFingerTaskOne:" + sendJson);
        NetWorkUtils.sendFingerTask(mContext, "sendFingerTask", sendJson, Constants.zipFilePath + File.separator + zipFileName, new NetWorkUtils.Callback() {
            @Override
            public void onNext(String result) {
                if(StringUtils.checkString(result)) {
                    Response<String> response = GsonUtils.fromJsonObject(result,String.class);
                    if(response.getCode() == 0) {
                        //上传服务器成功，写入本地数据库
                        CompareDB.updateCompareEntity(compareEntity);
                        CompareDB.updateComparePhotoList(comparePhotos1);
//                        ToastUtils.showLong("提交对比成功");
                    }else {
//                        ToastUtils.showLong("提交对比失败：" + response.getMsg());
                    }
                }else {
//                    ToastUtils.showLong("提交对比失败：服务器返回错误");
                }
                //删除压缩包
                FileUtils.deleteFile(Constants.zipFilePath + File.separator + zipFileName);
            }

            @Override
            public void onError(Throwable e) {
                //删除压缩包
                FileUtils.deleteFile(Constants.zipFilePath + File.separator + zipFileName);
//                ToastUtils.showLong("提交对比失败：" + e.getMessage());
            }
        });
        return GsonUtils.successJson("提交比对中");
    }

    public static String startEvidenceOut(HttpRequest request, Context mContext, String caseType, String location,
                                          String crimeId, String photoId, String unitCode, String unitName, String userId) {
        sysUser sysUser = UserDb.selectUserById(userId);

        Photo photo = SceneDB.selectPhotoById(photoId);
        List<ComparePhoto> comparePhotos = CompareDB.selectComparePhotoByPhotoId(photo, Constants.TYPE_FINGER_EVIDENCE + "");
        if(comparePhotos.size() > 0) {
            return GsonUtils.faildJson(500,"该指纹已经提交比对");
        }
        FileUtils.deleteDirectory(Constants.zipPath);
        String compareId = StringUtils.getUUID();
        String createName = sysUser.getTrueName();
        long createDate = System.currentTimeMillis();

        CompareEntity compareEntity = new CompareEntity();
        compareEntity.setId(compareId);
        compareEntity.setCrimeId(crimeId);
        compareEntity.setCreateDate(System.currentTimeMillis());
        compareEntity.setCreateName(createName);
        compareEntity.setRev2(Constants.TYPE_FINGER_EVIDENCE + "");
        //把bmp文件编号
        List<ComparePhoto> comparePhotos1 = new ArrayList<>();
        ComparePhoto comparePhoto = new ComparePhoto();
        comparePhoto.setId(StringUtils.getUUID());
        comparePhoto.setCompareId(compareId);
        comparePhoto.setEvidenceName("指纹");
        comparePhoto.setSceneId(crimeId);
        comparePhoto.setPhotoId(photoId);
        comparePhoto.setPhotoPath(photo.getPath());
        comparePhoto.setPhotoType(Constants.TYPE_FINGER_EVIDENCE + "");
        comparePhoto.setCreateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
        comparePhoto.setCreateName(createName);
        comparePhoto.setUpdateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
        comparePhoto.setFingerNo(1);
        comparePhoto.setRev2(caseType + "@@" + location);
        comparePhoto.setServicePath(photo.getServerPath());
        //编号后复制到指定文件夹下改名，准备打包zip
        String substring = photo.getPath().substring(0, photo.getPath().lastIndexOf("."));
        FileUtils.copyRename(substring + ".bmp",Constants.zipPath,1 + ".bmp");
        comparePhotos1.add(comparePhoto);
        //打包bmp压缩文件
        String zipFileName = StringUtils.getCompareZipFileName(createDate);
        try {
            //目标目录
            File targetDir = new File(Constants.zipFilePath);
            //创建目录
            if (!targetDir.exists()) {
                targetDir.mkdirs();
            }
            ZipUtils.ZipFolder(Constants.zipPath,Constants.zipFilePath + File.separator + zipFileName);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.faildJson(500,e.getMessage());
        }finally {
            //不管是否压缩成功，压缩后删除zipPath
            FileUtils.deleteDirectory(Constants.zipPath);
        }
        //上传bmp文件压缩包
        CompareTaskEntity entity = new CompareTaskEntity();
        entity.setId(compareId);
        entity.setSceneId(crimeId);
        entity.setUserId(sysUser.getId());
        entity.setFileName(zipFileName);
        entity.setCreateUser(sysUser.getTrueName());
        entity.setCreateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
        entity.setTaskNo("RW" + StringUtils.long2String(createDate,"yyyyMMddHHmmss"));
        //案件类别@@发案地点@@勘验地点@@勘验人@@勘验开始时间@@单位名称@@单位代码
        entity.setRev3(caseType
                + "@@" + location
                + "@@" + location
                + "@@" + createName
                + "@@" + StringUtils.long2String(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss")
                + "@@" + unitName
                + "@@" + unitCode);
        String sendJson = GsonUtils.gsonString(entity);
        LogUtils.e("sendFingerTaskOne:" + sendJson);
        NetWorkUtils.sendFingerTask(mContext, "sendFingerTask", sendJson, Constants.zipFilePath + File.separator + zipFileName, new NetWorkUtils.Callback() {
            @Override
            public void onNext(String result) {
                if(StringUtils.checkString(result)) {
                    Response<String> response = GsonUtils.fromJsonObject(result,String.class);
                    if(response.getCode() == 0) {
                        //上传服务器成功，写入本地数据库
                        CompareDB.updateCompareEntity(compareEntity);
                        CompareDB.updateComparePhotoList(comparePhotos1);
//                        ToastUtils.showLong("提交对比成功");
                    }else {
//                        ToastUtils.showLong("提交对比失败：" + response.getMsg());
                    }
                }else {
//                    ToastUtils.showLong("提交对比失败：服务器返回错误");
                }
                //删除压缩包
                FileUtils.deleteFile(Constants.zipFilePath + File.separator + zipFileName);
            }

            @Override
            public void onError(Throwable e) {
                //删除压缩包
                FileUtils.deleteFile(Constants.zipFilePath + File.separator + zipFileName);
//                ToastUtils.showLong("提交对比失败：" + e.getMessage());
            }
        });
        return GsonUtils.successJson("提交比对中");
    }

    public static String startPeople(HttpRequest request, Context mContext, CrimeItem item, String userId) {
//        String userName = RequestUtils.getUser(request);
//        String password = RequestUtils.getPassword(request);
//        sysUser sysUser = UserDb.selectUser(userName, password);
        sysUser sysUser = UserDb.selectUserById(userId);
        List<EvidenceEntity> evidenceItem = item.getEvidenceItem();
        if(evidenceItem.size() == 0) {
            return GsonUtils.faildJson(500,"暂无现场指纹，无法进行事主排查");
        }
        List<EvidenceEntity> compareEvidences = new ArrayList<>();
        for(EvidenceEntity entity : evidenceItem) {
            Photo photo = CompareDB.selectPhotoByParentId(entity.getId(), entity.getCrimeId(), Constants.photoState_evidence);
            entity.__setDaoSession(ServerApplication.getDaoSession());
            entity.setPhoto(photo);
            //判断是否重复比对
            List<ComparePhoto> comparePhotos = CompareDB.selectComparePhotoByPhotoId(photo, Constants.TYPE_FINGER_PEOPLE + "");
            if(comparePhotos.size() == 0) {
                compareEvidences.add(entity);
            }
        }
        List<ContactsEntity> contactsEntityList = item.getReleatedPeopleItem();
        List<ContactsEntity> compareConteacts = new ArrayList<>();
        for(ContactsEntity entity : contactsEntityList) {
            List<Photo> photos = CompareDB.selectPhotoListByParentId(entity.getId(), entity.getCrimeId(), Constants.photoState_visit_people);
            entity.__setDaoSession(ServerApplication.getDaoSession());
            entity.setPhotos(photos);
            //判断是否重复比对
            for(Photo photo : photos) {
                List<ComparePhoto> comparePhotos = CompareDB.selectComparePhotoByPhotoId(photo, Constants.TYPE_FINGER_PEOPLE + "");
                if(comparePhotos.size() == 0) {
                    compareConteacts.add(entity);
                }
            }
        }
        if(compareEvidences.size() <= 0 && compareConteacts.size() <= 0) {
            return GsonUtils.faildJson(500,"暂无新的事主指纹");
        }
//        XfUtils.startSpeak(sysUser.getTrueName() + "提交事主比对");
        FileUtils.deleteDirectory(Constants.zipPath);

        String compareId = StringUtils.getUUID();
        String createName = sysUser.getTrueName();
        String crimeId = item.getId();
        long createDate = System.currentTimeMillis();

        CompareEntity compareEntity = new CompareEntity();
        compareEntity.setId(compareId);
        compareEntity.setCrimeId(crimeId);
        compareEntity.setCreateDate(System.currentTimeMillis());
        compareEntity.setCreateName(createName);
        List<ComparePhoto> comparePhotos = new ArrayList<>();
        for(ContactsEntity contactsEntity : contactsEntityList) {
            //把bmp文件编号
            List<EvidenceEntity> fingers = new ArrayList<>();
            for(EvidenceEntity evidence : item.getEvidenceItem()) {
                if(evidence.getEvidenceCategory().equals("手印")) {
                    fingers.add(evidence);
                }
            }
            if(contactsEntity.getPhotos().size() > 0) {
                for (int i = 0; i < fingers.size(); i++) {
                    ComparePhoto photo = new ComparePhoto();
                    photo.setId(StringUtils.getUUID());
                    photo.setCompareId(compareId);
                    photo.setSceneId(fingers.get(i).getCrimeId());
                    photo.setPhotoId(fingers.get(i).getPhoto().getId());
                    String substring = fingers.get(i).getPhoto().getPath().substring(0, fingers.get(i).getPhoto().getPath().lastIndexOf("."));
                    photo.setPhotoPath(substring + ".bmp");
                    photo.setPhotoType(Constants.TYPE_FINGER_PEOPLE + "");
                    photo.setCreateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
                    photo.setCreateName(createName);
                    photo.setUpdateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
                    photo.setEvidenceName(fingers.get(i).getEvidenceName());
                    photo.setFingerNo(i + 1);
                    photo.setServicePath(fingers.get(i).getPhoto().getServerPath());
                    //编号后复制到指定文件夹下改名，准备打包zip
                    FileUtils.copyRename(fingers.get(i).getPhoto().getPath().substring(0,fingers.get(i).getPhoto().getPath().lastIndexOf(".")) + ".bmp", Constants.zipPath, (i + 1) + ".bmp");
                    comparePhotos.add(photo);
                }
            }
            List<Photo> photos = contactsEntity.getPhotos();
            for(int i = 0; i < photos.size(); i++) {
                ComparePhoto photo = new ComparePhoto();
                photo.setId(StringUtils.getUUID());
                photo.setCompareId(compareId);
                photo.setSceneId(crimeId);
                photo.setPhotoId(photos.get(i).getId());
                photo.setPhotoPath(photos.get(i).getPath());
                photo.setPhotoType(Constants.TYPE_FINGER_PEOPLE + "");
                photo.setCreateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
                photo.setCreateName(createName);
                photo.setUpdateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
                photo.setEvidenceName("");
                photo.setFingerNo(i + 1);
                photo.setServicePath(photos.get(i).getServerPath());
                //编号后复制到指定文件夹下改名，准备打包zip
                FileUtils.copyRename(photos.get(i).getPath().substring(0,photos.get(i).getPath().lastIndexOf(".")) + ".bmp", Constants.zipContactsPath, (i + 1) + ".bmp");
                comparePhotos.add(photo);
            }
        }
        //打包bmp压缩文件
        String zipFileName = StringUtils.getCompareZipFileName(createDate);
        try {
            ZipUtils.ZipFolder(Constants.zipPath, Constants.zipFilePath + File.separator + zipFileName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //不管是否压缩成功，压缩后删除zipPath
            FileUtils.deleteDirectory(Constants.zipPath);
        }
        //上传bmp文件压缩包
        CompareTaskEntity entity = new CompareTaskEntity();
        entity.setId(compareId);
        entity.setSceneId(crimeId);
        entity.setUserId(sysUser.getId());
        entity.setFileName(zipFileName);
        entity.setCreateUser(sysUser.getTrueName());
        entity.setCreateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
        entity.setTaskNo("RW" + StringUtils.long2String(createDate,"yyyyMMdd"));
        String sendJson = GsonUtils.gsonString(entity);
        NetWorkUtils.sendFingerTask(mContext, "sendFingerTask", sendJson, Constants.zipFilePath + File.separator + zipFileName, new NetWorkUtils.Callback() {
            @Override
            public void onNext(String result) {
                if (StringUtils.checkString(result)) {
                    Response<String> response = GsonUtils.fromJsonObject(result, String.class);
                    if (response.getCode() == 0) {
                        //上传服务器成功，写入本地数据库
                        CompareDB.updateCompareEntity(compareEntity);
                        CompareDB.updateComparePhotoList(comparePhotos);
//                        XfUtils.startSpeak("提交比对成功");
                    }else {
//                            ToastUtils.showLong("提交对比失败：" + response.getMsg());
//                        XfUtils.startSpeak("提交对比失败，" + response.getMsg());
                    }
                }else {
//                    XfUtils.startSpeak("提交对比失败，服务器返回数据为空");
                }
                //删除压缩包
                FileUtils.deleteFile(Constants.zipFilePath + File.separator + zipFileName);
            }

            @Override
            public void onError(Throwable e) {
                //删除压缩包
                FileUtils.deleteFile(Constants.zipFilePath + File.separator + zipFileName);
//                    ToastUtils.showLong("提交对比失败：" + e.getMessage());
//                XfUtils.startSpeak("提交对比失败，访问服务器错误");
            }
        });
        return GsonUtils.successJson("提交事主比对中");
    }

    public static String startPeopleLocal(HttpRequest request, Context mContext, CrimeItem item, String userId) {
        sysUser sysUser = UserDb.selectUserById(userId);
        List<EvidenceEntity> evidenceItem = item.getEvidenceItem();
        if(evidenceItem.size() == 0) {
            return GsonUtils.faildJson(500,"暂无现场指纹，无法进行事主排查");
        }
        List<EvidenceEntity> compareEvidences = new ArrayList<>();
        for(EvidenceEntity entity : evidenceItem) {
            Photo photo = CompareDB.selectPhotoByParentId(entity.getId(), entity.getCrimeId(), Constants.photoState_evidence);
            entity.__setDaoSession(ServerApplication.getDaoSession());
            entity.setPhoto(photo);
            if(photo != null) {
                //判断是否重复比对
                List<ComparePhoto> comparePhotos = CompareDB.selectComparePhotoByPhotoId(photo, Constants.TYPE_FINGER_PEOPLE + "");
                if(comparePhotos.size() == 0) {
                    compareEvidences.add(entity);
                }
            }
        }
        List<ContactsEntity> contactsEntityList = item.getReleatedPeopleItem();
        List<ContactsEntity> compareConteacts = new ArrayList<>();
        for(ContactsEntity entity : contactsEntityList) {
            List<Photo> photos = CompareDB.selectPhotoListByParentId(entity.getId(), entity.getCrimeId(), Constants.photoState_visit_people);
            entity.__setDaoSession(ServerApplication.getDaoSession());
            entity.setPhotos(photos);
            //判断是否重复比对
            for(Photo photo : photos) {
                List<ComparePhoto> comparePhotos = CompareDB.selectComparePeoplePhoto(photo);
                if(comparePhotos.size() == 0) {
                    compareConteacts.add(entity);
                }
            }
        }
        if(!(compareEvidences.size() > 0 || compareConteacts.size() > 0)) {
            return GsonUtils.faildJson(500,"暂无新的指纹");
        }
//        XfUtils.startSpeak(sysUser.getTrueName() + "提交事主比对");
        List<EvidenceEntity> fingers = new ArrayList<>();
        for(EvidenceEntity evidence : item.getEvidenceItem()) {
            if(evidence.getEvidenceCategory().equals("手印")) {
                fingers.add(evidence);
            }
        }
        String compareId = StringUtils.getUUID();
        String createName = sysUser.getTrueName();
        String crimeId = item.getId();
        long createDate = System.currentTimeMillis();

        CompareEntity compareEntity = new CompareEntity();
        compareEntity.setId(compareId);
        compareEntity.setCrimeId(crimeId);
        compareEntity.setCreateDate(createDate);
        compareEntity.setCreateName(createName);
        compareEntity.setRev2(Constants.TYPE_FINGER_PEOPLE + "");
        List<ComparePhoto> comparePhotos = new ArrayList<>();
        for(ContactsEntity contactsEntity : contactsEntityList) {
            if (contactsEntity.getPhotos().size() > 0) {
                for (Photo contactsPhoto : contactsEntity.getPhotos()) {
                    for(EvidenceEntity evidenceEntity : fingers) {
                        Photo fingerPhoto = SceneDB.selectPhotoByParentId(evidenceEntity.getId(), evidenceEntity.getCrimeId());
                        if(fingerPhoto != null) {
                            ComparePhoto comPhoto = new ComparePhoto();
                            comPhoto.setId(StringUtils.getUUID());
                            comPhoto.setCompareId(compareId);
                            comPhoto.setSceneId(crimeId);
                            comPhoto.setPhotoId(fingerPhoto.getId());
                            comPhoto.setPhotoPath(fingerPhoto.getPath());
                            comPhoto.setPhotoType(Constants.TYPE_FINGER_PEOPLE + "");
                            comPhoto.setCreateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
                            comPhoto.setCreateName(createName);
                            comPhoto.setUpdateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
                            comPhoto.setFingerNo(Integer.valueOf(contactsPhoto.getRev1()));
                            comPhoto.setServicePath(fingerPhoto.getServerPath());
                            comPhoto.setStatus("1");
                            comPhoto.setRev1(contactsPhoto.getId());
                            comPhoto.setEvidenceName(evidenceEntity.getEvidenceName());
                            comparePhotos.add(comPhoto);
                        }
                    }
                }
            }
        }
        CompareDB.updateCompareEntity(compareEntity);
        CompareDB.updateComparePhotoList(comparePhotos);
        featureES.execute(new Runnable() {
            @Override
            public void run() {
                for(ComparePhoto comparePhoto : comparePhotos) {
                    Photo fingerPhoto = SceneDB.selectPhotoById(comparePhoto.getPhotoId());
                    Photo contactsPhoto = SceneDB.selectPhotoById(comparePhoto.getRev1());
                    if(!StringUtils.checkString(contactsPhoto.getRev4())) {
                        String path = contactsPhoto.getPath();
                        if(contactsPhoto.getType().equals("bmp")) {
                            path = path.replace("bmp","jpg");
                        }
                        contactsPhoto.setRev4(Compare.getFeature(path));
                        SceneDB.insertPhoto(contactsPhoto);
                    }
                    if(!StringUtils.checkString(fingerPhoto.getRev4())) {
                        String path = fingerPhoto.getPath();
                        if(fingerPhoto.getType().equals("bmp")) {
                            path = path.replace("bmp","jpg");
                        }
                        fingerPhoto.setRev4(Compare.getFeature(path));
                        SceneDB.insertPhoto(fingerPhoto);
                    }
                    boolean b = Compare.CompareFinger(contactsPhoto.getRev4(), fingerPhoto.getRev4());
                    comparePhoto.setStatus(b ? "107" : "8");
                }
                CompareDB.updateComparePhotoList(comparePhotos);

//                for(ContactsEntity contactsEntity : contactsEntityList) {
//                    if(contactsEntity.getPhotos().size() > 0) {
//                        for(Photo photo : contactsEntity.getPhotos()) {
//                            for(EvidenceEntity entity : fingers) {
//                                Photo fingerPhoto = SceneDB.selectPhotoByParentId(entity.getId(), entity.getCrimeId());
//                                if(fingerPhoto != null) {
//                                    if(!StringUtils.checkString(photo.getRev4())) {
//                                        String path = photo.getPath();
//                                        if(photo.getType().equals("bmp")) {
//                                            path = path.replace("bmp","jpg");
//                                        }
//                                        photo.setRev4(Compare.getFeature(path));
//                                        SceneDB.insertPhoto(photo);
//                                    }
//                                    if(!StringUtils.checkString(fingerPhoto.getRev4())) {
//                                        String path = fingerPhoto.getPath();
//                                        if(fingerPhoto.getType().equals("bmp")) {
//                                            path = path.replace("bmp","jpg");
//                                        }
//                                        fingerPhoto.setRev4(Compare.getFeature(path));
//                                        SceneDB.insertPhoto(fingerPhoto);
//                                    }
//                                    boolean b = Compare.CompareFinger(photo.getRev4(), fingerPhoto.getRev4());
//                                    ComparePhoto comPhoto = new ComparePhoto();
//                                    comPhoto.setId(StringUtils.getUUID());
//                                    comPhoto.setCompareId(compareId);
//                                    comPhoto.setSceneId(crimeId);
//                                    comPhoto.setPhotoId(fingerPhoto.getId());
//                                    comPhoto.setPhotoPath(fingerPhoto.getPath());
//                                    comPhoto.setPhotoType(Constants.TYPE_FINGER_PEOPLE + "");
//                                    comPhoto.setCreateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
//                                    comPhoto.setCreateName(createName);
//                                    comPhoto.setUpdateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
////                                    comPhoto.setFingerNo(fingers.indexOf(fingerPhoto));
//                                    comPhoto.setFingerNo(Integer.valueOf(photo.getRev1()));
//                                    comPhoto.setServicePath(fingerPhoto.getServerPath());
//                                    comPhoto.setStatus(b ? "107" : "8");
//                                    comPhoto.setRev1(photo.getId());
//                                    comPhoto.setEvidenceName(entity.getEvidenceName());
//                                    comparePhotos.add(comPhoto);
//                                }
//                            }
//                        }
//                    }
//                }
////                CompareDB.updateCompareEntity(compareEntity);
//                CompareDB.updateComparePhotoList(comparePhotos);
            }
        });
//        CompareDB.updateCompareEntity(compareEntity);
        return GsonUtils.successJson("比对中");
    }

    public static String startPeopleLocalOne(HttpRequest request, Context mContext, String evidenceId, String userId) {
        sysUser sysUser = UserDb.selectUserById(userId);
        EvidenceEntity evidenceEntity = SceneDB.selectEvidenceById(evidenceId);
        if(evidenceEntity == null) {
            return GsonUtils.faildJson(500,"没有该痕迹数据");
        }
        CrimeItem item = SceneDB.selectCrimeById(evidenceEntity.getCrimeId());
        if(item == null) {
            return GsonUtils.faildJson(500,"没有该现场数据");
        }
        List<Photo> photos = SceneDB.selectPhotoByType(evidenceEntity.getCrimeId(),Constants.photoState_visit_people);
        if(photos.size() == 0) {
            return GsonUtils.faildJson(500,"暂无人员指纹，无法进行事主排查");
        }
        Photo photo = CompareDB.selectPhotoByParentId(evidenceEntity.getId(), evidenceEntity.getCrimeId(), Constants.photoState_evidence);
        evidenceEntity.__setDaoSession(ServerApplication.getDaoSession());
        evidenceEntity.setPhoto(photo);
        if(photo != null) {
            //判断是否重复比对
            List<ComparePhoto> comparePhotos = CompareDB.selectComparePhotoByPhotoId(photo, Constants.TYPE_FINGER_PEOPLE + "", "1");
            if(comparePhotos.size() > 0) {
                return GsonUtils.faildJson(500,"该指纹正在进行事主比对，请稍后重试");
            }
        }
        List<ContactsEntity> contactsEntityList = item.getReleatedPeopleItem();
        String compareId = StringUtils.getUUID();
        String createName = sysUser.getTrueName();
        String crimeId = item.getId();
        long createDate = System.currentTimeMillis();

        CompareEntity compareEntity = new CompareEntity();
        compareEntity.setId(compareId);
        compareEntity.setCrimeId(crimeId);
        compareEntity.setCreateDate(createDate);
        compareEntity.setCreateName(createName);
        compareEntity.setRev2(Constants.TYPE_FINGER_PEOPLE + "");
        List<ComparePhoto> comparePhotos = new ArrayList<>();
        for(ContactsEntity contactsEntity : contactsEntityList) {
            if (contactsEntity.getPhotos().size() > 0) {
                for (Photo contactsPhoto : contactsEntity.getPhotos()) {
                    Photo fingerPhoto = evidenceEntity.getPhoto();
                    if(fingerPhoto != null) {
                        ComparePhoto comPhoto = new ComparePhoto();
                        comPhoto.setId(StringUtils.getUUID());
                        comPhoto.setCompareId(compareId);
                        comPhoto.setSceneId(crimeId);
                        comPhoto.setPhotoId(fingerPhoto.getId());
                        comPhoto.setPhotoPath(fingerPhoto.getPath());
                        comPhoto.setPhotoType(Constants.TYPE_FINGER_PEOPLE + "");
                        comPhoto.setCreateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
                        comPhoto.setCreateName(createName);
                        comPhoto.setUpdateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
                        comPhoto.setFingerNo(Integer.valueOf(contactsPhoto.getRev1()));
                        comPhoto.setServicePath(fingerPhoto.getServerPath());
                        comPhoto.setStatus("1");
                        comPhoto.setRev1(contactsPhoto.getId());
                        comPhoto.setEvidenceName(evidenceEntity.getEvidenceName());
                        comparePhotos.add(comPhoto);
                    }
                }
            }
        }
        CompareDB.updateCompareEntity(compareEntity);
        CompareDB.updateComparePhotoList(comparePhotos);
        featureES.execute(new Runnable() {
            @Override
            public void run() {
                for(ComparePhoto comparePhoto : comparePhotos) {
                    Photo fingerPhoto = SceneDB.selectPhotoById(comparePhoto.getPhotoId());
                    Photo contactsPhoto = SceneDB.selectPhotoById(comparePhoto.getRev1());
                    if(!StringUtils.checkString(contactsPhoto.getRev4())) {
                        String path = contactsPhoto.getPath();
                        if(contactsPhoto.getType().equals("bmp")) {
                            path = path.replace("bmp","jpg");
                        }
                        contactsPhoto.setRev4(Compare.getFeature(path));
                        SceneDB.insertPhoto(contactsPhoto);
                    }
                    if(!StringUtils.checkString(fingerPhoto.getRev4())) {
                        String path = fingerPhoto.getPath();
                        if(fingerPhoto.getType().equals("bmp")) {
                            path = path.replace("bmp","jpg");
                        }
                        fingerPhoto.setRev4(Compare.getFeature(path));
                        SceneDB.insertPhoto(fingerPhoto);
                    }
                    boolean b = Compare.CompareFinger(contactsPhoto.getRev4(), fingerPhoto.getRev4());
                    comparePhoto.setStatus(b ? "107" : "8");
                }
                CompareDB.updateComparePhotoList(comparePhotos);
//                for(ContactsEntity contactsEntity : contactsEntityList) {
//                    if(contactsEntity.getPhotos().size() > 0) {
//                        for(Photo photo : contactsEntity.getPhotos()) {
//                            Photo fingerPhoto = evidenceEntity.getPhoto();
//                            if(fingerPhoto != null) {
//                                if(!StringUtils.checkString(photo.getRev4())) {
//                                    String path = photo.getPath();
//                                    if(photo.getType().equals("bmp")) {
//                                        path = path.replace("bmp","jpg");
//                                    }
//                                    photo.setRev4(Compare.getFeature(path));
//                                    SceneDB.insertPhoto(photo);
//                                }
//                                if(!StringUtils.checkString(fingerPhoto.getRev4())) {
//                                    String path = fingerPhoto.getPath();
//                                    if(fingerPhoto.getType().equals("bmp")) {
//                                        path = path.replace("bmp","jpg");
//                                    }
//                                    fingerPhoto.setRev4(Compare.getFeature(path));
//                                    SceneDB.insertPhoto(fingerPhoto);
//                                }
//                                boolean b = Compare.CompareFinger(photo.getRev4(), fingerPhoto.getRev4());
//                                ComparePhoto comPhoto = new ComparePhoto();
//                                comPhoto.setId(StringUtils.getUUID());
//                                comPhoto.setCompareId(compareId);
//                                comPhoto.setSceneId(crimeId);
//                                comPhoto.setPhotoId(fingerPhoto.getId());
//                                comPhoto.setPhotoPath(fingerPhoto.getPath());
//                                comPhoto.setPhotoType(Constants.TYPE_FINGER_PEOPLE + "");
//                                comPhoto.setCreateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
//                                comPhoto.setCreateName(createName);
//                                comPhoto.setUpdateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
//                                comPhoto.setFingerNo(Integer.valueOf(photo.getRev1()));
//                                comPhoto.setServicePath(fingerPhoto.getServerPath());
//                                comPhoto.setStatus(b ? "107" : "8");
//                                comPhoto.setRev1(photo.getId());
//                                comPhoto.setEvidenceName(evidenceEntity.getEvidenceName());
//                                comparePhotos.add(comPhoto);
//                            }
//                        }
//                    }
//                }
//                CompareDB.updateCompareEntity(compareEntity);
//                CompareDB.updateComparePhotoList(comparePhotos);
            }
        });
        return GsonUtils.successJson("比对中");
    }

    public static String startFoot(HttpRequest request, Context mContext, CrimeItem item, String userId) {
        sysUser sysUser = UserDb.selectUserById(userId);
        List<EvidenceEntity> evidenceEntityList = new ArrayList<>();
        List<EvidenceEntity> evidenceItem = item.getEvidenceItem();
        for(EvidenceEntity entity : evidenceItem) {
            if(entity.getEvidenceCategory().equals("足迹")) {
                Photo photo = CompareDB.selectPhotoByParentId(entity.getId(), entity.getCrimeId(), Constants.photoState_evidence);
                entity.__setDaoSession(ServerApplication.getDaoSession());
                entity.setPhoto(photo);
                if(photo != null) {
                    List<ComparePhoto> comparePhotos = CompareDB.selectComparePhotoByPhotoId(photo, Constants.TYPE_FOOT_EVIDENCE + "");
                    if(comparePhotos.size() == 0) {
                        evidenceEntityList.add(entity);
                    }
                }
            }
        }
        if(evidenceEntityList.size() == 0) {
            return GsonUtils.faildJson(500,"暂无新足迹可以对比");
        }
        FileUtils.deleteDirectory(Constants.zipPath);
        String compareId = StringUtils.getUUID();
        String createName = sysUser.getTrueName();
        String crimeId = evidenceEntityList.get(0).getCrimeId();
        long createDate = System.currentTimeMillis();

        CompareEntity compareEntity = new CompareEntity();
        compareEntity.setId(compareId);
        compareEntity.setCrimeId(crimeId);
        compareEntity.setCreateDate(System.currentTimeMillis());
        compareEntity.setCreateName(createName);
        compareEntity.setRev2(Constants.TYPE_FOOT_EVIDENCE + "");
        //把bmp文件编号
        List<ComparePhoto> comparePhotos = new ArrayList<>();
        String rev1 = "";
        for(int i = 0; i < evidenceEntityList.size(); i++) {
            ComparePhoto photo = new ComparePhoto();
            photo.setId(StringUtils.getUUID());
            photo.setCompareId(compareId);
            photo.setEvidenceName(evidenceEntityList.get(i).getEvidenceName());
            photo.setSceneId(evidenceEntityList.get(i).getCrimeId());
            photo.setPhotoId(evidenceEntityList.get(i).getPhoto().getId());
            photo.setPhotoPath(evidenceEntityList.get(i).getPhoto().getPath());
            photo.setPhotoType(Constants.TYPE_FOOT_EVIDENCE + "");
            photo.setCreateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
            photo.setCreateName(createName);
            photo.setUpdateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
            photo.setFingerNo(i+1);
            photo.setServicePath(evidenceEntityList.get(i).getPhoto().getServerPath());
            photo.setRev2(item.getCasetype() + "@@" + item.getLocation());
            //编号后复制到指定文件夹下改名，准备打包zip
            FileUtils.copyRename(evidenceEntityList.get(i).getPhoto().getPath(),Constants.zipPath,evidenceEntityList.get(i).getPhoto().getId() + ".jpg");
            comparePhotos.add(photo);
            rev1 += evidenceEntityList.get(i).getPhoto().getId() + ",";
        }
        //打包bmp压缩文件
        String zipFileName = StringUtils.getUUID() + ".zip";
        try {
            ZipUtils.ZipFolder(Constants.zipPath,Constants.zipFilePath + File.separator + zipFileName);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.faildJson(500,e.getMessage());
        }finally {
            //不管是否压缩成功，压缩后删除zipPath
            FileUtils.deleteDirectory(Constants.zipPath);
        }
        //上传bmp文件压缩包
        CompareTaskEntity entity = new CompareTaskEntity();
        entity.setId(compareId);
        entity.setSceneId(crimeId);
        entity.setUserId(sysUser.getId());
        entity.setFileName(zipFileName);
        entity.setCreateUser(sysUser.getTrueName());
        entity.setCreateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
        entity.setTaskNo("RW" + StringUtils.long2String(createDate,"yyyyMMddHHmmss"));
        //足迹rev1 字段  图片id用逗号拼接一下
        if(StringUtils.checkString(rev1)) {
            rev1 = rev1.substring(0,rev1.length()-1);
        }
        sysOrgan organ = SysDB.getOrgIdById(item.getAreaKey());
        String unitName = "";
        String unitCode = "";
        if(organ != null) {
            unitCode = organ.getUnitCode();
            unitName = organ.getUnitName();
        }
        //案件类别@@发案地点@@勘验地点@@勘验人@@勘验开始时间@@单位名称@@单位代码
        entity.setRev3(item.getCasetype()
                + "@@" + item.getLocation()
                + "@@" + item.getAccessLocation()
                + "@@" + item.getUserName()
                + "@@" + StringUtils.long2String(item.getAccess_start_time(),"yyyy-MM-dd HH:mm:ss")
                + "@@" + unitName
                + "@@" + unitCode);
        entity.setRev1(rev1);
        String sendJson = GsonUtils.gsonString(entity);
        LogUtils.e(sendJson);
        NetWorkUtils.sendFootTask(mContext, "sendFootTask", sendJson, Constants.zipFilePath + File.separator + zipFileName, new NetWorkUtils.Callback() {
            @Override
            public void onNext(String result) {
                if(StringUtils.checkString(result)) {
                    Response<String> response = GsonUtils.fromJsonObject(result,String.class);
                    if(response.getCode() == 0) {
                        //上传服务器成功，写入本地数据库
                        CompareDB.updateCompareEntity(compareEntity);
                        CompareDB.updateComparePhotoList(comparePhotos);
//                        ToastUtils.showLong("提交对比成功");
//                        XfUtils.startSpeak("提交对比成功");
                    }else {
//                        XfUtils.startSpeak("提交对比失败，服务器返回错误" + response.getMsg());
//                        ToastUtils.showLong("提交对比失败：" + response.getMsg());
                    }
                }else {
//                    XfUtils.startSpeak("提交对比失败，服务器返回数据为空");
//                    ToastUtils.showLong("提交对比失败：服务器返回错误");
                }
                //删除压缩包
                FileUtils.deleteFile(Constants.zipFilePath + File.separator + zipFileName);
            }

            @Override
            public void onError(Throwable e) {
                //删除压缩包
                FileUtils.deleteFile(Constants.zipFilePath + File.separator + zipFileName);
//                ToastUtils.showLong("提交对比失败：" + e.getMessage());
//                XfUtils.startSpeak("提交对比失败，访问服务器错误");
            }
        });
        return GsonUtils.successJson("提交比对中");
    }

    public static String startFootOne(HttpRequest request, Context mContext, String evidenceId, String userId) {
        sysUser sysUser = UserDb.selectUserById(userId);
        EvidenceEntity evidenceEntity = SceneDB.selectEvidenceById(evidenceId);
        if(evidenceEntity == null) {
            return GsonUtils.faildJson(500,"没有该痕迹数据");
        }
        CrimeItem item = SceneDB.selectCrimeById(evidenceEntity.getCrimeId());
        if(item == null) {
            return GsonUtils.faildJson(500,"没有该现场数据");
        }
        Photo footPhoto = CompareDB.selectPhotoByParentId(evidenceEntity.getId(), evidenceEntity.getCrimeId(), Constants.photoState_evidence);
        evidenceEntity.__setDaoSession(ServerApplication.getDaoSession());
        evidenceEntity.setPhoto(footPhoto);
        List<ComparePhoto> comparePhotos1 = CompareDB.selectComparePhotoByPhotoId(footPhoto, Constants.TYPE_FOOT_EVIDENCE + "");
        if(comparePhotos1.size() > 0) {
            return GsonUtils.faildJson(500,"该足迹已经提交比对");
        }
        FileUtils.deleteDirectory(Constants.zipPath);
        String compareId = StringUtils.getUUID();
        String createName = sysUser.getTrueName();
        String crimeId = evidenceEntity.getCrimeId();
        long createDate = System.currentTimeMillis();

        CompareEntity compareEntity = new CompareEntity();
        compareEntity.setId(compareId);
        compareEntity.setCrimeId(crimeId);
        compareEntity.setCreateDate(System.currentTimeMillis());
        compareEntity.setCreateName(createName);
        compareEntity.setRev2(Constants.TYPE_FOOT_EVIDENCE + "");
        //把bmp文件编号
        List<ComparePhoto> comparePhotos = new ArrayList<>();
        ComparePhoto photo = new ComparePhoto();
        photo.setId(StringUtils.getUUID());
        photo.setCompareId(compareId);
        photo.setEvidenceName(evidenceEntity.getEvidenceName());
        photo.setSceneId(evidenceEntity.getCrimeId());
        photo.setPhotoId(evidenceEntity.getPhoto().getId());
        photo.setPhotoPath(evidenceEntity.getPhoto().getPath());
        photo.setPhotoType(Constants.TYPE_FOOT_EVIDENCE + "");
        photo.setCreateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
        photo.setCreateName(createName);
        photo.setUpdateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
        photo.setFingerNo(1);
        photo.setServicePath(evidenceEntity.getPhoto().getServerPath());
        photo.setRev2(item.getCasetype() + "@@" + item.getLocation());
        //编号后复制到指定文件夹下改名，准备打包zip
        FileUtils.copyRename(evidenceEntity.getPhoto().getPath(),Constants.zipPath,evidenceEntity.getPhoto().getId() + ".jpg");
        comparePhotos.add(photo);
        String rev1 = evidenceEntity.getPhoto().getId() + ",";
        //打包bmp压缩文件
        String zipFileName = StringUtils.getUUID() + ".zip";
        try {
            //目标目录
            File targetDir = new File(Constants.zipFilePath);
            //创建目录
            if (!targetDir.exists()) {
                targetDir.mkdirs();
            }
            ZipUtils.ZipFolder(Constants.zipPath,Constants.zipFilePath + File.separator + zipFileName);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.faildJson(500,e.getMessage());
        }finally {
            //不管是否压缩成功，压缩后删除zipPath
            FileUtils.deleteDirectory(Constants.zipPath);
        }
        //上传bmp文件压缩包
        CompareTaskEntity entity = new CompareTaskEntity();
        entity.setId(compareId);
        entity.setSceneId(crimeId);
        entity.setUserId(sysUser.getId());
        entity.setFileName(zipFileName);
        entity.setCreateUser(sysUser.getTrueName());
        entity.setCreateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
        entity.setTaskNo("RW" + StringUtils.long2String(createDate,"yyyyMMddHHmmss"));
        //足迹rev1 字段  图片id用逗号拼接一下
        if(StringUtils.checkString(rev1)) {
            rev1 = rev1.substring(0,rev1.length()-1);
        }
        sysOrgan organ = SysDB.getOrgIdById(item.getAreaKey());
        String unitName = "";
        String unitCode = "";
        if(organ != null) {
            unitCode = organ.getUnitCode();
            unitName = organ.getUnitName();
        }
        //案件类别@@发案地点@@勘验地点@@勘验人@@勘验开始时间@@单位名称@@单位代码
        entity.setRev3(item.getCasetype()
                + "@@" + item.getLocation()
                + "@@" + item.getAccessLocation()
                + "@@" + item.getUserName()
                + "@@" + StringUtils.long2String(item.getAccess_start_time(),"yyyy-MM-dd HH:mm:ss")
                + "@@" + unitName
                + "@@" + unitCode);
        entity.setRev1(rev1);
        String sendJson = GsonUtils.gsonString(entity);
        LogUtils.e(sendJson);
        NetWorkUtils.sendFootTask(mContext, "sendFootTask", sendJson, Constants.zipFilePath + File.separator + zipFileName, new NetWorkUtils.Callback() {
            @Override
            public void onNext(String result) {
                if(StringUtils.checkString(result)) {
                    Response<String> response = GsonUtils.fromJsonObject(result,String.class);
                    if(response.getCode() == 0) {
                        //上传服务器成功，写入本地数据库
                        CompareDB.updateCompareEntity(compareEntity);
                        CompareDB.updateComparePhotoList(comparePhotos);
//                        ToastUtils.showLong("提交对比成功");
                    }else {
//                        ToastUtils.showLong("提交对比失败：" + response.getMsg());
                    }
                }else {
//                    ToastUtils.showLong("提交对比失败：服务器返回错误");
                }
                //删除压缩包
                FileUtils.deleteFile(Constants.zipFilePath + File.separator + zipFileName);
            }

            @Override
            public void onError(Throwable e) {
                //删除压缩包
                FileUtils.deleteFile(Constants.zipFilePath + File.separator + zipFileName);
//                ToastUtils.showLong("提交对比失败：" + e.getMessage());
            }
        });
        return GsonUtils.successJson("提交比对中");
    }

    public static String startFootOut(HttpRequest request, Context mContext, String caseType, String location,
                                      String crimeId, String photoId, String unitCode, String unitName, String userId) {
        sysUser sysUser = UserDb.selectUserById(userId);
        Photo footPhoto = SceneDB.selectPhotoById(photoId);
        List<ComparePhoto> comparePhotos1 = CompareDB.selectComparePhotoByPhotoId(footPhoto, Constants.TYPE_FOOT_EVIDENCE + "");
        if(comparePhotos1.size() > 0) {
            return GsonUtils.faildJson(500,"该足迹已经提交比对");
        }
        FileUtils.deleteDirectory(Constants.zipPath);
        String compareId = StringUtils.getUUID();
        String createName = sysUser.getTrueName();
        long createDate = System.currentTimeMillis();

        CompareEntity compareEntity = new CompareEntity();
        compareEntity.setId(compareId);
        compareEntity.setCrimeId(crimeId);
        compareEntity.setCreateDate(System.currentTimeMillis());
        compareEntity.setCreateName(createName);
        compareEntity.setRev2(Constants.TYPE_FOOT_EVIDENCE + "");
        //把bmp文件编号
        List<ComparePhoto> comparePhotos = new ArrayList<>();
        ComparePhoto photo = new ComparePhoto();
        photo.setId(StringUtils.getUUID());
        photo.setCompareId(compareId);
        photo.setEvidenceName("足迹");
        photo.setSceneId(crimeId);
        photo.setPhotoId(photoId);
        photo.setPhotoPath(footPhoto.getPath());
        photo.setPhotoType(Constants.TYPE_FOOT_EVIDENCE + "");
        photo.setCreateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
        photo.setCreateName(createName);
        photo.setUpdateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
        photo.setFingerNo(1);
        photo.setServicePath(footPhoto.getServerPath());
        photo.setRev2(caseType + "@@" + location);
        //编号后复制到指定文件夹下改名，准备打包zip
        FileUtils.copyRename(footPhoto.getPath(),Constants.zipPath,footPhoto.getId() + ".jpg");
        comparePhotos.add(photo);
        String rev1 = footPhoto.getId() + ",";
        //打包bmp压缩文件
        String zipFileName = StringUtils.getUUID() + ".zip";
        try {
            //目标目录
            File targetDir = new File(Constants.zipFilePath);
            //创建目录
            if (!targetDir.exists()) {
                targetDir.mkdirs();
            }
            ZipUtils.ZipFolder(Constants.zipPath,Constants.zipFilePath + File.separator + zipFileName);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.faildJson(500,e.getMessage());
        }finally {
            //不管是否压缩成功，压缩后删除zipPath
            FileUtils.deleteDirectory(Constants.zipPath);
        }
        //上传bmp文件压缩包
        CompareTaskEntity entity = new CompareTaskEntity();
        entity.setId(compareId);
        entity.setSceneId(crimeId);
        entity.setUserId(sysUser.getId());
        entity.setFileName(zipFileName);
        entity.setCreateUser(sysUser.getTrueName());
        entity.setCreateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
        entity.setTaskNo("RW" + StringUtils.long2String(createDate,"yyyyMMddHHmmss"));
        //足迹rev1 字段  图片id用逗号拼接一下
        if(StringUtils.checkString(rev1)) {
            rev1 = rev1.substring(0,rev1.length()-1);
        }
        //案件类别@@发案地点@@勘验地点@@勘验人@@勘验开始时间@@单位名称@@单位代码
        entity.setRev3(caseType
                + "@@" + location
                + "@@" + location
                + "@@" + createName
                + "@@" + StringUtils.long2String(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss")
                + "@@" + unitName
                + "@@" + unitCode);
        entity.setRev1(rev1);
        String sendJson = GsonUtils.gsonString(entity);
        LogUtils.e(sendJson);
        NetWorkUtils.sendFootTask(mContext, "sendFootTask", sendJson, Constants.zipFilePath + File.separator + zipFileName, new NetWorkUtils.Callback() {
            @Override
            public void onNext(String result) {
                if(StringUtils.checkString(result)) {
                    Response<String> response = GsonUtils.fromJsonObject(result,String.class);
                    if(response.getCode() == 0) {
                        //上传服务器成功，写入本地数据库
                        CompareDB.updateCompareEntity(compareEntity);
                        CompareDB.updateComparePhotoList(comparePhotos);
//                        ToastUtils.showLong("提交对比成功");
                    }else {
//                        ToastUtils.showLong("提交对比失败：" + response.getMsg());
                    }
                }else {
//                    ToastUtils.showLong("提交对比失败：服务器返回错误");
                }
                //删除压缩包
                FileUtils.deleteFile(Constants.zipFilePath + File.separator + zipFileName);
            }

            @Override
            public void onError(Throwable e) {
                //删除压缩包
                FileUtils.deleteFile(Constants.zipFilePath + File.separator + zipFileName);
//                ToastUtils.showLong("提交对比失败：" + e.getMessage());
            }
        });
        return GsonUtils.successJson("提交比对中");
    }

    public static String startFace(HttpRequest request, Context mContext, CrimeItem item, String userId) {
        sysUser sysUser = UserDb.selectUserById(userId);
        List<EvidenceEntity> evidenceEntityList = new ArrayList<>();
        List<EvidenceEntity> evidenceItem = item.getEvidenceItem();
        for(EvidenceEntity entity : evidenceItem) {
            if(entity.getEvidenceCategory().equals("人像")) {
                Photo photo = CompareDB.selectPhotoByParentId(entity.getId(), entity.getCrimeId(), Constants.photoState_evidence);
                entity.__setDaoSession(ServerApplication.getDaoSession());
                entity.setPhoto(photo);
                if(photo != null) {
                    List<ComparePhoto> comparePhotos = CompareDB.selectComparePhotoByPhotoId(photo, Constants.TYPE_FACE_EVIDENCE + "");
                    if(comparePhotos.size() == 0) {
                        evidenceEntityList.add(entity);
                    }
                }
            }
        }
        if(evidenceEntityList.size() == 0) {
            return GsonUtils.faildJson(500,"暂无新足迹人像可以对比");
        }
        FileUtils.deleteDirectory(Constants.zipPath);
        String compareId = StringUtils.getUUID();
        String createName = sysUser.getTrueName();
        String crimeId = evidenceEntityList.get(0).getCrimeId();
        long createDate = System.currentTimeMillis();

        CompareEntity compareEntity = new CompareEntity();
        compareEntity.setId(compareId);
        compareEntity.setCrimeId(crimeId);
        compareEntity.setCreateDate(System.currentTimeMillis());
        compareEntity.setCreateName(createName);
        compareEntity.setRev2(Constants.TYPE_FACE_EVIDENCE + "");
        //把bmp文件编号
        List<ComparePhoto> comparePhotos = new ArrayList<>();
        for(int i = 0; i < evidenceEntityList.size(); i++) {
            ComparePhoto photo = new ComparePhoto();
            photo.setId(StringUtils.getUUID());
            photo.setCompareId(compareId);
            photo.setEvidenceName(evidenceEntityList.get(i).getEvidenceName());
            photo.setSceneId(evidenceEntityList.get(i).getCrimeId());
            photo.setPhotoId(evidenceEntityList.get(i).getPhoto().getId());
            photo.setPhotoPath(evidenceEntityList.get(i).getPhoto().getPath());
            photo.setPhotoType(Constants.TYPE_FACE_EVIDENCE + "");
            photo.setCreateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
            photo.setCreateName(createName);
            photo.setUpdateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
            photo.setFingerNo(i+1);
            photo.setServicePath(evidenceEntityList.get(i).getPhoto().getServerPath());
            photo.setRev2(item.getCasetype() + "@@" + item.getLocation());
            //编号后复制到指定文件夹下改名，准备打包zip
            FileUtils.copyRename(evidenceEntityList.get(i).getPhoto().getPath(),Constants.zipPath,evidenceEntityList.get(i).getPhoto().getId() + ".jpg");
            comparePhotos.add(photo);
        }
        //打包bmp压缩文件
        String zipFileName = StringUtils.getUUID() + ".zip";
        try {
            ZipUtils.ZipFolder(Constants.zipPath,Constants.zipFilePath + File.separator + zipFileName);
        } catch (Exception e) {
            e.printStackTrace();
            return GsonUtils.faildJson(500,e.getMessage());
        }finally {
            //不管是否压缩成功，压缩后删除zipPath
            FileUtils.deleteDirectory(Constants.zipPath);
        }
        //上传bmp文件压缩包
        CompareTaskEntity entity = new CompareTaskEntity();
        entity.setId(compareId);
        entity.setSceneId(crimeId);
        entity.setUserId(sysUser.getId());
        entity.setFileName(zipFileName);
        entity.setCreateUser(sysUser.getTrueName());
        entity.setCreateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
        entity.setTaskNo("RW" + StringUtils.long2String(createDate,"yyyyMMddHHmmss"));
        sysOrgan organ = SysDB.getOrgIdById(item.getAreaKey());
        String unitName = "";
        String unitCode = "";
        if(organ != null) {
            unitCode = organ.getUnitCode();
            unitName = organ.getUnitName();
        }
        //案件类别@@发案地点@@勘验地点@@勘验人@@勘验开始时间@@单位名称@@单位代码
        entity.setRev3(item.getCasetype()
                + "@@" + item.getLocation()
                + "@@" + item.getAccessLocation()
                + "@@" + item.getUserName()
                + "@@" + StringUtils.long2String(item.getAccess_start_time(),"yyyy-MM-dd HH:mm:ss")
                + "@@" + unitName
                + "@@" + unitCode);
        String sendJson = GsonUtils.gsonString(entity);
        LogUtils.e(sendJson);
        NetWorkUtils.sendFaceTask(mContext, "sendFaceTask", sendJson, Constants.zipFilePath + File.separator + zipFileName, new NetWorkUtils.Callback() {
            @Override
            public void onNext(String result) {
                if(StringUtils.checkString(result)) {
                    Response<String> response = GsonUtils.fromJsonObject(result,String.class);
                    if(response.getCode() == 0) {
                        //上传服务器成功，写入本地数据库
                        CompareDB.updateCompareEntity(compareEntity);
                        CompareDB.updateComparePhotoList(comparePhotos);
//                        ToastUtils.showLong("提交对比成功");
                    }else {
//                        ToastUtils.showLong("提交对比失败：" + response.getMsg());
                    }
                }else {
//                    ToastUtils.showLong("提交对比失败：服务器返回错误");
                }
                //删除压缩包
                FileUtils.deleteFile(Constants.zipFilePath + File.separator + zipFileName);
            }

            @Override
            public void onError(Throwable e) {
                //删除压缩包
                FileUtils.deleteFile(Constants.zipFilePath + File.separator + zipFileName);
//                ToastUtils.showLong("提交对比失败：" + e.getMessage());
//                XfUtils.startSpeak("提交对比失败，访问服务器错误");
            }
        });
        return GsonUtils.successJson("提交比对中");
    }

    public static String startFaceOne(HttpRequest request, Context mContext, String evidenceId, String userId) {
        sysUser sysUser = UserDb.selectUserById(userId);
        EvidenceEntity evidenceEntity = SceneDB.selectEvidenceById(evidenceId);
        if(evidenceEntity == null) {
            return GsonUtils.faildJson(500,"没有该痕迹数据");
        }
        CrimeItem item = SceneDB.selectCrimeById(evidenceEntity.getCrimeId());
        if(item == null) {
            return GsonUtils.faildJson(500,"没有该现场数据");
        }
        Photo footPhoto = CompareDB.selectPhotoByParentId(evidenceEntity.getId(), evidenceEntity.getCrimeId(), Constants.photoState_evidence);
        evidenceEntity.__setDaoSession(ServerApplication.getDaoSession());
        evidenceEntity.setPhoto(footPhoto);
        List<ComparePhoto> comparePhotos1 = CompareDB.selectComparePhotoByPhotoId(footPhoto, Constants.TYPE_FACE_EVIDENCE + "");
        if(comparePhotos1.size() > 0) {
            return GsonUtils.faildJson(500,"该人像已经提交比对");
        }
        FileUtils.deleteDirectory(Constants.zipPath);
        String compareId = StringUtils.getUUID();
        String createName = sysUser.getTrueName();
        String crimeId = evidenceEntity.getCrimeId();
        long createDate = System.currentTimeMillis();

        CompareEntity compareEntity = new CompareEntity();
        compareEntity.setId(compareId);
        compareEntity.setCrimeId(crimeId);
        compareEntity.setCreateDate(System.currentTimeMillis());
        compareEntity.setCreateName(createName);
        compareEntity.setRev2(Constants.TYPE_FACE_EVIDENCE + "");
        //把bmp文件编号
        List<ComparePhoto> comparePhotos = new ArrayList<>();
        ComparePhoto photo = new ComparePhoto();
        photo.setId(StringUtils.getUUID());
        photo.setCompareId(compareId);
        photo.setEvidenceName(evidenceEntity.getEvidenceName());
        photo.setSceneId(evidenceEntity.getCrimeId());
        photo.setPhotoId(evidenceEntity.getPhoto().getId());
        photo.setPhotoPath(evidenceEntity.getPhoto().getPath());
        photo.setPhotoType(Constants.TYPE_FACE_EVIDENCE + "");
        photo.setCreateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
        photo.setCreateName(createName);
        photo.setUpdateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
        photo.setFingerNo(1);
        photo.setServicePath(evidenceEntity.getPhoto().getServerPath());
        photo.setRev2(item.getCasetype() + "@@" + item.getLocation());
        //编号后复制到指定文件夹下改名，准备打包zip
//        FileUtils.copyRename(evidenceEntity.getPhoto().getPath(),Constants.zipPath,evidenceEntity.getPhoto().getId() + ".jpg");
        File photoFile = new File(evidenceEntity.getPhoto().getPath());
        comparePhotos.add(photo);
        CompareTaskEntity entity = new CompareTaskEntity();
        entity.setId(compareId);
        entity.setSceneId(crimeId);
        entity.setUserId(sysUser.getId());
        entity.setFileName(photoFile.getName());
        entity.setCreateUser(sysUser.getTrueName());
        entity.setCreateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
        entity.setTaskNo("RW" + StringUtils.long2String(createDate,"yyyyMMddHHmmss"));
        sysOrgan organ = SysDB.getOrgIdById(item.getAreaKey());
        String unitName = "";
        String unitCode = "";
        if(organ != null) {
            unitCode = organ.getUnitCode();
            unitName = organ.getUnitName();
        }
        //案件类别@@发案地点@@勘验地点@@勘验人@@勘验开始时间@@单位名称@@单位代码
        entity.setRev3(item.getCasetype()
                + "@@" + item.getLocation()
                + "@@" + item.getAccessLocation()
                + "@@" + item.getUserName()
                + "@@" + StringUtils.long2String(item.getAccess_start_time(),"yyyy-MM-dd HH:mm:ss")
                + "@@" + unitName
                + "@@" + unitCode);
        String sendJson = GsonUtils.gsonString(entity);
        LogUtils.e(sendJson);
        NetWorkUtils.sendFaceTask(mContext, "sendFaceTask", sendJson, photoFile.getAbsolutePath(), new NetWorkUtils.Callback() {
            @Override
            public void onNext(String result) {
                if(StringUtils.checkString(result)) {
                    Response<String> response = GsonUtils.fromJsonObject(result,String.class);
                    if(response.getCode() == 0) {
                        //上传服务器成功，写入本地数据库
                        CompareDB.updateCompareEntity(compareEntity);
                        CompareDB.updateComparePhotoList(comparePhotos);
//                        ToastUtils.showLong("提交对比成功");
                    }else {
//                        ToastUtils.showLong("提交对比失败：" + response.getMsg());
                    }
                }else {
//                    ToastUtils.showLong("提交对比失败：服务器返回错误");
                }
            }

            @Override
            public void onError(Throwable e) {
                //删除压缩包
//                ToastUtils.showLong("提交对比失败：" + e.getMessage());
            }
        });
        return GsonUtils.successJson("提交比对中");
    }

    public static String startFaceOut(HttpRequest request, Context mContext, String caseType, String location,
                                      String crimeId, String photoId, String unitCode, String unitName, String userId) {
        sysUser sysUser = UserDb.selectUserById(userId);
        Photo footPhoto = SceneDB.selectPhotoById(photoId);
        List<ComparePhoto> comparePhotos1 = CompareDB.selectComparePhotoByPhotoId(footPhoto, Constants.TYPE_FACE_EVIDENCE + "");
        if(comparePhotos1.size() > 0) {
            return GsonUtils.faildJson(500,"该人脸已经提交比对");
        }
        FileUtils.deleteDirectory(Constants.zipPath);
        String compareId = StringUtils.getUUID();
        String createName = sysUser.getTrueName();
        long createDate = System.currentTimeMillis();

        CompareEntity compareEntity = new CompareEntity();
        compareEntity.setId(compareId);
        compareEntity.setCrimeId(crimeId);
        compareEntity.setCreateDate(System.currentTimeMillis());
        compareEntity.setCreateName(createName);
        compareEntity.setRev2(Constants.TYPE_FACE_EVIDENCE + "");
        //把bmp文件编号
        List<ComparePhoto> comparePhotos = new ArrayList<>();
        ComparePhoto photo = new ComparePhoto();
        photo.setId(StringUtils.getUUID());
        photo.setCompareId(compareId);
        photo.setEvidenceName("人像");
        photo.setSceneId(crimeId);
        photo.setPhotoId(photoId);
        photo.setPhotoPath(footPhoto.getPath());
        photo.setPhotoType(Constants.TYPE_FACE_EVIDENCE + "");
        photo.setCreateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
        photo.setCreateName(createName);
        photo.setUpdateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
        photo.setFingerNo(1);
        photo.setServicePath(footPhoto.getServerPath());
        photo.setRev2(caseType + "@@" + location);
        //编号后复制到指定文件夹下改名，准备打包zip
//        FileUtils.copyRename(footPhoto.getPath(),Constants.zipPath,footPhoto.getId() + ".jpg");
        File photoFile = new File(footPhoto.getPath());
        comparePhotos.add(photo);
        //上传bmp文件压缩包
        CompareTaskEntity entity = new CompareTaskEntity();
        entity.setId(compareId);
        entity.setSceneId(crimeId);
        entity.setUserId(sysUser.getId());
        entity.setFileName(photoFile.getName());
        entity.setCreateUser(sysUser.getTrueName());
        entity.setCreateDate(StringUtils.long2String(createDate,"yyyy-MM-dd HH:mm:ss"));
        entity.setTaskNo("RW" + StringUtils.long2String(createDate,"yyyyMMddHHmmss"));
        //案件类别@@发案地点@@勘验地点@@勘验人@@勘验开始时间@@单位名称@@单位代码
        entity.setRev3(caseType
                + "@@" + location
                + "@@" + location
                + "@@" + createName
                + "@@" + StringUtils.long2String(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss")
                + "@@" + unitName
                + "@@" + unitCode);
        String sendJson = GsonUtils.gsonString(entity);
        LogUtils.e(sendJson);
        NetWorkUtils.sendFaceTask(mContext, "sendFaceTask", sendJson, photoFile.getAbsolutePath(), new NetWorkUtils.Callback() {
            @Override
            public void onNext(String result) {
                if(StringUtils.checkString(result)) {
                    Response<String> response = GsonUtils.fromJsonObject(result,String.class);
                    if(response.getCode() == 0) {
                        //上传服务器成功，写入本地数据库
                        CompareDB.updateCompareEntity(compareEntity);
                        CompareDB.updateComparePhotoList(comparePhotos);
//                        ToastUtils.showLong("提交对比成功");
                    }else {
//                        ToastUtils.showLong("提交对比失败：" + response.getMsg());
                    }
                }else {
//                    ToastUtils.showLong("提交对比失败：服务器返回错误");
                }
            }

            @Override
            public void onError(Throwable e) {
//                ToastUtils.showLong("提交对比失败：" + e.getMessage());
            }
        });
        return GsonUtils.successJson("提交比对中");
    }

    public static String startFingerCompareResult(Context mContext,String crimeId,String state) {
        int type = 1;
        if(state.equals("1")) {
            type = Constants.TYPE_FINGER_EVIDENCE;
        }else if(state.equals("2")) {
            type = Constants.TYPE_FINGER_PEOPLE;
        }
        List<ComparePhoto> comparePhotos = CompareDB.selectNoResultComparePhotoByCrimeId(crimeId,type);
        //判断是否还有比对没有获取最终结果
        if (comparePhotos.size() > 0) {
            int finalType = type;
            NetWorkUtils.getFingerTaskResult(mContext, "getFingerTaskResult", crimeId, new NetWorkUtils.Callback() {
                @Override
                public void onNext(String result) {
                    if(StringUtils.checkString(result)) {
                        Response<List<ComparePhoto>> response = GsonUtils.fromJsonArray(result, ComparePhoto.class);
                        if(response.getCode() == 0) {
                            //返回数据成功，解析结果，插入comparePhoto
                            CompareDB.updateCompareFingerPhoto(response.getData(), finalType);
                        }
                    }
                }

                @Override
                public void onError(Throwable e) {

                }
            });
        }else {
            List<ComparePhoto> comparePhotoList = CompareDB.selectComparePhotoByCrimeId(crimeId, type);
            return GsonUtils.successJson(comparePhotoList);
        }
        return GsonUtils.faildJson(500,"正在加载");
    }

    public static String getCompareResult(String crimeId,String state) {
        int type = 1;
        if(state.equals("1")) {
            type = Constants.TYPE_FINGER_EVIDENCE;
        }else if(state.equals("2")) {
            type = Constants.TYPE_FINGER_PEOPLE;
        }else if(state.equals("3")) {
            type = Constants.TYPE_FOOT_EVIDENCE;
        }else if(state.equals("4")) {
            type = Constants.TYPE_FACE_EVIDENCE;
        }
        List<ComparePhoto> comparePhotoList = CompareDB.selectComparePhotoByCrimeId(crimeId,type);
        return GsonUtils.successJson(comparePhotoList);
    }

    public static String startFootCompareResult(Context mContext, String crimeId, String state) {
        List<ComparePhoto> comparePhotos = CompareDB.selectNoResultFootComparePhotoByCrimeId(crimeId,Constants.TYPE_FOOT_EVIDENCE);
        if(comparePhotos.size() > 0) {
            NetWorkUtils.getFootTaskResult(mContext, "getFootTaskResult", crimeId, new NetWorkUtils.Callback() {
                @Override
                public void onNext(String result) {
                    if(StringUtils.checkString(result)) {
                        Response<List<ComparePhoto>> response = GsonUtils.fromJsonArray(result, ComparePhoto.class);
                        if(response.getCode() == 0) {
                            //返回数据成功，解析结果，插入comparePhoto
                            CompareDB.updateCompareFootPhoto(response.getData());
                        }
                    }
                }

                @Override
                public void onError(Throwable e) {
                }
            });
        }else {
            List<ComparePhoto> comparePhotoList = CompareDB.selectFootComparePhotoByCrimeId(crimeId, Constants.TYPE_FOOT_EVIDENCE);
            return GsonUtils.successJson(comparePhotoList);
        }
        return GsonUtils.faildJson(500,"正在加载");
    }

    public static String startFaceCompareResult(Context mContext, String crimeId, String state) {
        List<ComparePhoto> comparePhotos = CompareDB.selectNoResultFaceCompareByCrimeId(crimeId,Constants.TYPE_FACE_EVIDENCE);
        if(comparePhotos.size() > 0) {
            NetWorkUtils.getFaceTaskResult(mContext, "getFaceTaskResult", crimeId, new NetWorkUtils.Callback() {
                @Override
                public void onNext(String result) {
                    if(StringUtils.checkString(result)) {
                        Response<List<ComparePhoto>> response = GsonUtils.fromJsonArray(result, ComparePhoto.class);
                        if(response.getCode() == 0) {
                            //返回数据成功，解析结果，插入comparePhoto
                            CompareDB.updateCompareFootPhoto(response.getData());
                        }
                    }
                }

                @Override
                public void onError(Throwable e) {
                }
            });
        }else {
            List<ComparePhoto> comparePhotoList = CompareDB.selectFootComparePhotoByCrimeId(crimeId, Constants.TYPE_FACE_EVIDENCE);
            return GsonUtils.successJson(comparePhotoList);
        }
        return GsonUtils.faildJson(500,"正在加载");
    }

    public static String getAllCompareResult(String userName, String state) {
        int type = 1;
        if(state.equals("1")) {
            type = Constants.TYPE_FINGER_EVIDENCE;
        }else if(state.equals("2")) {
            type = Constants.TYPE_FINGER_PEOPLE;
        }else if(state.equals("3")) {
            type = Constants.TYPE_FOOT_EVIDENCE;
        }else if(state.equals("4")) {
            type = Constants.TYPE_FACE_EVIDENCE;
        }
        List<ComparePhoto> comparePhotoList = CompareDB.selectComparePhotoByUserName(userName,type);
        return GsonUtils.successJson(comparePhotoList);
    }

    public static String startAllFootCompareResult(Context mContext, String userId, String state) {
        sysUser sysUser = UserDb.selectUserById(userId);
        List<ComparePhoto> comparePhotos = CompareDB.selectNoResultFootComparePhotoByUserName(sysUser.getTrueName(),Constants.TYPE_FOOT_EVIDENCE);
        if(comparePhotos.size() > 0) {
            NetWorkUtils.getFootTaskResult(mContext, "getFootTaskResult", userId, new NetWorkUtils.Callback() {
                @Override
                public void onNext(String result) {
                    if(StringUtils.checkString(result)) {
                        Response<List<ComparePhoto>> response = GsonUtils.fromJsonArray(result, ComparePhoto.class);
                        if(response.getCode() == 0) {
                            //返回数据成功，解析结果，插入comparePhoto
                            CompareDB.updateCompareFootPhoto(response.getData());
                        }
                    }
                }

                @Override
                public void onError(Throwable e) {
                }
            });
        }else {
            List<ComparePhoto> comparePhotoList = CompareDB.selectComparePhotoByUserName(sysUser.getTrueName(), Constants.TYPE_FOOT_EVIDENCE);
            return GsonUtils.successJson(comparePhotoList);
        }
        return GsonUtils.faildJson(500,"正在加载");
    }

    public static String startAllFaceCompareResult(Context mContext, String userId, String state) {
        sysUser sysUser = UserDb.selectUserById(userId);
        List<ComparePhoto> comparePhotos = CompareDB.selectNoResultFootComparePhotoByUserName(sysUser.getTrueName(),Constants.TYPE_FACE_EVIDENCE);
        if(comparePhotos.size() > 0) {
            NetWorkUtils.getFaceTaskResult(mContext, "getFaceTaskResult", userId, new NetWorkUtils.Callback() {
                @Override
                public void onNext(String result) {
                    if(StringUtils.checkString(result)) {
                        Response<List<ComparePhoto>> response = GsonUtils.fromJsonArray(result, ComparePhoto.class);
                        if(response.getCode() == 0) {
                            //返回数据成功，解析结果，插入comparePhoto
                            CompareDB.updateCompareFacePhoto(response.getData());
                        }
                    }
                }

                @Override
                public void onError(Throwable e) {
                }
            });
        }else {
            List<ComparePhoto> comparePhotoList = CompareDB.selectComparePhotoByUserName(sysUser.getTrueName(), Constants.TYPE_FACE_EVIDENCE);
            return GsonUtils.successJson(comparePhotoList);
        }
        return GsonUtils.faildJson(500,"正在加载");
    }

    public static String startAllCompareResult(Context mContext, String userId, String state) {
        int type = 1;
        if(state.equals("1")) {
            type = Constants.TYPE_FINGER_EVIDENCE;
        }else if(state.equals("2")) {
            type = Constants.TYPE_FINGER_PEOPLE;
        }
        sysUser sysUser = UserDb.selectUserById(userId);
        List<ComparePhoto> comparePhotos = CompareDB.selectNoResultComparePhotoByUserName(sysUser.getTrueName(),type);
        //判断是否还有比对没有获取最终结果
        if (comparePhotos.size() > 0) {
            int finalType = type;
            NetWorkUtils.getFingerTaskResult(mContext, "getFingerTaskResult", userId, new NetWorkUtils.Callback() {
                @Override
                public void onNext(String result) {
                    if(StringUtils.checkString(result)) {
                        Response<List<ComparePhoto>> response = GsonUtils.fromJsonArray(result, ComparePhoto.class);
                        if(response.getCode() == 0) {
                            //返回数据成功，解析结果，插入comparePhoto
                            CompareDB.updateCompareFingerPhoto(response.getData(), finalType);
                        }
                    }
                }

                @Override
                public void onError(Throwable e) {

                }
            });
        }else {
            List<ComparePhoto> comparePhotoList = CompareDB.selectComparePhotoByUserName(sysUser.getTrueName(), type);
            return GsonUtils.successJson(comparePhotoList);
        }
        return GsonUtils.faildJson(500,"正在加载");
    }

    public static String getAllCompareFromService(Context mContext,String userId) {
        sysUser sysUser = UserDb.selectUserById(userId);
        String userName = sysUser.getTrueName();
        //指纹
        List<ComparePhoto> compareFingers = CompareDB.selectNoResultComparePhotoByUserName(userName,Constants.TYPE_FINGER_EVIDENCE);
        if(compareFingers.size() > 0) {
            //获取指纹比对结果
            NetWorkUtils.getFingerTaskResult(mContext, "getFingerTaskResult", userId, new NetWorkUtils.Callback() {
                @Override
                public void onNext(String result) {
                    if(StringUtils.checkString(result)) {
                        Response<List<ComparePhoto>> response = GsonUtils.fromJsonArray(result, ComparePhoto.class);
                        if(response.getCode() == 0) {
                            //返回数据成功，解析结果，插入comparePhoto
                            CompareDB.updateCompareFingerPhoto(response.getData(), Constants.TYPE_FINGER_EVIDENCE);
                        }
                    }
                }

                @Override
                public void onError(Throwable e) {

                }
            });
        }
        //足迹
        List<ComparePhoto> compareFoots = CompareDB.selectNoResultFootComparePhotoByUserName(userName,Constants.TYPE_FOOT_EVIDENCE);
        if(compareFoots.size() > 0) {
            NetWorkUtils.getFootTaskResult(mContext, "getFootTaskResult", userId, new NetWorkUtils.Callback() {
                @Override
                public void onNext(String result) {
                    if(StringUtils.checkString(result)) {
                        Response<List<ComparePhoto>> response = GsonUtils.fromJsonArray(result, ComparePhoto.class);
                        if(response.getCode() == 0) {
                            //返回数据成功，解析结果，插入comparePhoto
                            CompareDB.updateCompareFootPhoto(response.getData());
                        }
                    }
                }

                @Override
                public void onError(Throwable e) {
                }
            });
        }
        //人脸
        List<ComparePhoto> compareFaces = CompareDB.selectNoResultFootComparePhotoByUserName(userName,Constants.TYPE_FACE_EVIDENCE);
        if(compareFaces.size() > 0) {
            NetWorkUtils.getFaceTaskResult(mContext, "getFaceTaskResult", userId, new NetWorkUtils.Callback() {
                @Override
                public void onNext(String result) {
                    if(StringUtils.checkString(result)) {
                        Response<List<ComparePhoto>> response = GsonUtils.fromJsonArray(result, ComparePhoto.class);
                        if(response.getCode() == 0) {
                            //返回数据成功，解析结果，插入comparePhoto
                            CompareDB.updateCompareFacePhoto(response.getData());
                        }
                    }
                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                }
            });
        }
        if(compareFingers.size() == 0 && compareFoots.size() == 0 && compareFaces.size() == 0) {
            return GsonUtils.successJson("获取数据成功");
        }
        return GsonUtils.faildJson(500,"正在比对中");
    }
}
