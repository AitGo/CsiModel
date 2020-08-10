package com.liany.csiserverapp.andServer.service;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.provider.Settings;

import com.google.gson.Gson;
import com.liany.csiserverapp.andServer.model.SceneDB;
import com.liany.csiserverapp.andServer.model.UserDb;
import com.liany.csiserverapp.base.BaseEvent;
import com.liany.csiserverapp.base.Constants;
import com.liany.csiserverapp.debug.ServerApplication;
import com.liany.csiserverapp.diagnose.CrimeItem;
import com.liany.csiserverapp.diagnose.CrimeScene;
import com.liany.csiserverapp.diagnose.EvidenceEntity;
import com.liany.csiserverapp.diagnose.KCTBASESTATIONDATABean;
import com.liany.csiserverapp.diagnose.Photo;
import com.liany.csiserverapp.diagnose.sysOrgan;
import com.liany.csiserverapp.diagnose.sysUser;
import com.liany.csiserverapp.network.webservice.NetWorkUtils;
import com.liany.csiserverapp.network.webservice.WebServiceUtils;
import com.liany.csiserverapp.utils.BitmapUtils;
import com.liany.csiserverapp.utils.CollUtils;
import com.liany.csiserverapp.utils.Compare;
import com.liany.csiserverapp.utils.FileUtils;
import com.liany.csiserverapp.utils.GsonUtils;
import com.liany.csiserverapp.utils.Item2Scene;
import com.liany.csiserverapp.utils.LogUtils;
import com.liany.csiserverapp.utils.SPUtils;
import com.liany.csiserverapp.utils.StringUtils;
import com.liany.csiserverapp.utils.XmlUtils;
import com.yanzhenjie.andserver.http.HttpRequest;
import com.yanzhenjie.andserver.http.multipart.MultipartFile;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @创建者 ly
 * @创建时间 2020/3/6
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class SceneService {
    private static List<CrimeItem> items = new ArrayList<>();
    private static Item2Scene item2Scene;
    static ExecutorService featureES = Executors.newCachedThreadPool();

    public static String getPresentScene(Context context) {
        //得到当前现场
        String presentSceneId = (String) SPUtils.getParam(context, Constants.sp_presentScene,"");
        if(!StringUtils.checkString(presentSceneId)) {
            //默认现场为最新现场
            CrimeItem crime = SceneDB.getNewCrime();
            if(crime != null) {
                return GsonUtils.successJson(crime);
            }else {
                return GsonUtils.faildJson(500,"暂无现场，请先新建现场");
            }
        }
        CrimeItem item = SceneDB.selectCrimeById(presentSceneId);
        return GsonUtils.successJson(item);
    }

    public static String setPresentScene(Context mContext, String crimeId) {
        CrimeItem crimeItem = SceneDB.selectCrimeById(crimeId);
        if(crimeItem != null) {
            SPUtils.setParam(mContext, Constants.sp_presentScene,crimeId);
            return GsonUtils.successJson("设置成功");
        }
        return GsonUtils.faildJson(500,"暂无该现场");
    }

    /**
     * 获取现场列表
     * @param type
     * @return
     */
    public static String getSceneList(int type) {
        List<CrimeItem> sceneList = SceneDB.getSceneList(type);
        if(sceneList.size() > 0) {
            return GsonUtils.successJson(sceneList);
        }
        return GsonUtils.faildJson(500,"暂无数据");
    }

    public static String getSceneListByPage(HttpRequest request, String userName, int type, int offset) {
        List<CrimeItem> sceneList = SceneDB.getSceneListByPage(userName,type,offset);
//        if(sceneList.size() > 0 ) {
//            return GsonUtils.successJson(sceneList);
//        }else {
//            return GsonUtils.faildJson(500,"暂无数据");
//        }
        return GsonUtils.successJson(sceneList);
    }

    public static String getSceneInfo(Context mContext, String crimeId) {
        if(!StringUtils.checkString(crimeId)) {
            return GsonUtils.faildJson(500,"现场id为空");
        }
        CrimeItem crimeItem = SceneDB.getSceneInfoById(crimeId);
        return GsonUtils.successJson(crimeItem);
    }

    public static String saveScene(Context mContext, CrimeItem crimeItem) {
        if(!StringUtils.checkString(crimeItem.getId())) {
            return GsonUtils.faildJson(500,"现场id为空");
        }
        //判断该条数据是否上传
        if(SceneDB.checkCrimeUpload(crimeItem.getId())) {
            return GsonUtils.faildJson(500,"该条数据已经上传，无法修改");
        }
        //插入数据库
        try {
            CrimeItem item = SceneDB.checkCrime(crimeItem.getId());
            if(item == null) {
                BaseEvent.CommonEvent event = BaseEvent.CommonEvent.CREATE_CRIME;
                event.setObject(1);
                EventBus.getDefault().post(event);
            }
            SceneDB.insertCrimeItem(crimeItem);
            return GsonUtils.successJson("保存成功");
        }catch (Exception e) {
            return GsonUtils.faildJson(500,"保存错误：" + e.getMessage());
        }
    }

    public static String deleteScene(Context mContext, List<CrimeItem> crimeItems, String userName) {
        //判断案件是否上传
        for (CrimeItem item : crimeItems) {
            if (SceneDB.checkCrimeUpload(item.getId())) {
                return GsonUtils.faildJson(500,"该条数据已经上传，无法删除");
            }
            SceneDB.delete(item);
            FileUtils.deleteDirectory(Constants.photoPath + File.separator  + item.getId());

            //没有上传
            //服务器删除图片
            NetWorkUtils.deletePic(mContext, "deletePhoto", "", item.getId(), new NetWorkUtils.Callback() {
                @Override
                public void onNext(String result) {
                    LogUtils.e("delete " +result);
                    if(result.equals("success")) {
//                        SceneDB.delete(item);
//                        FileUtils.deleteDirectory(Constants.photoPath + File.separator  + item.getId());
//                        XfUtils.startSpeak("删除成功");
                    }else {
                        result = "删除文件错误,"+ result;
//                        XfUtils.startSpeak(result);
                    }
                }

                @Override
                public void onError(Throwable e) {
//                    XfUtils.startSpeak("删除文件错误");
                }
            });
        }
        BaseEvent.CommonEvent event = BaseEvent.CommonEvent.DELETE_CRIME;
        event.setObject(1);
        EventBus.getDefault().post(event);
        return GsonUtils.successJson("正在删除");
    }

    public static String createScene(HttpRequest request, CrimeItem crimeItem) {
        if(!StringUtils.checkString(crimeItem.getId())) {
            crimeItem.setId(StringUtils.getUUID());
        }
//        String userName = RequestUtils.getUser(request);
//        String password = RequestUtils.getPassword(request);
//        sysUser sysUser = UserDb.selectUser(userName, password);
//        XfUtils.startSpeak("创建现场成功");
        SceneDB.insertCrimeItem(crimeItem);
        return GsonUtils.successJson("创建现场成功");
    }

    private static String unitName;
    private static String unitCode;
    private static String userId;
    private static String userName;

    public static String uploadScene(Context mContext, List<CrimeItem> crimeItems, String id) {
//        sysUser sysUser = UserDb.selectUser(userName, password);
        sysUser sysUser = UserDb.selectUserById(id);
        String orgId;
        if(sysUser!= null) {
            userId = sysUser.getId();
            userName = sysUser.getUserName();
            String techId = sysUser.getTechId();
            orgId = UserDb.selectorganId(techId);
            sysOrgan organ = UserDb.selectUnitName(orgId);
            unitCode = organ.getUnitCode();
            unitName = organ.getUnitName();
            item2Scene = new Item2Scene(mContext,userId,unitCode,orgId,unitName);
            items.addAll(crimeItems);
            //上传之前未上传服务器成功的图片
            for(CrimeItem item : crimeItems) {
                uploadCrimeScenePic(mContext,item);
            }
        }else {
            return GsonUtils.faildJson(500,"人员信息错误");
        }
        return GsonUtils.successJson("正在上传数据");
    }

    public static void uploadCrimeScenePic(Context mContext, @org.jetbrains.annotations.NotNull CrimeItem item) {
        List<Photo> photos = SceneDB.selectPhotosByUploadFail(item.getId());
        if(photos.size() > 0) {
            Photo photo = photos.get(0);
            String state = photo.getState();
            if(!state.equals(Constants.photoState_visit_people)
                    && !state.equals(Constants.photoState_compare_people)
                    && !state.equals(Constants.photoState_compare_finger)
                    && !state.equals(Constants.photoState_compare_foot)
                    && !state.equals(Constants.photoState_compare_face)) {
                String path = photo.getPath();
//            String path = mContext.getExternalFilesDir(Constants.value_photoDir).getAbsolutePath() + File.separator + item.getId() + File.separator + photo.getFileName();
                String photoId = photo.getId();
                if (photo.getType().equals("bmp")) {
                    path = path.replace("bmp", "jpg");
                    File file = new File(path);
                    if (!FileUtils.checkFileExists(file)) {
                        BitmapUtils.saveBitmapAsPng(BitmapFactory.decodeFile(path), file);
                    }
                }
                if (photo.getType().equals("dwg")) {
                    //上传dwg文件，还是使用上传图片接口，为了区分dwg文件，在图片id中拼上"dwg
                    photoId += "dwg";
                }
                try {
                    String result = WebServiceUtils.uploadPic(((String) SPUtils.getParam(mContext,Constants.sp_url,Constants.defaultURL)).replace("?wsdl",""),
                            Constants.method_uploadPic,
                            path,
                            photoId,
                            photo.getCrimeId(),
                            StringUtils.md5HashCode32(path));
                    LogUtils.e("uploadPic " + result);
                    if (result.equals("success")) {
                        photo.setIsUpload(Constants.UPLOAD_SUCCESS);
                        SceneDB.updatePhoto(photo);
                        uploadCrimeScenePic(mContext, item);
                    } else {
//                                XfUtils.startSpeak("上传现场错误，上传图片失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                photo.setIsUpload(Constants.UPLOAD_SUCCESS);
                SceneDB.updatePhoto(photo);
                uploadCrimeScenePic(mContext, item);
            }
        }else {
            //上传案件信息
            uploadCrimeScene(mContext,item,item2Scene);
        }
    }

    public static void uploadCrimeScene(Context mContext, CrimeItem item, Item2Scene item2Scene) {
        List<CrimeScene> items = new ArrayList<>();
        //删除人脸信息
        CrimeItem crimeItem = SceneDB.selectCrimeByIdWithOutFace(item.getId());

        CrimeScene scene = item2Scene.item2Scene(crimeItem);
        items.add(scene);
        //完整数据，可以上传
        Gson gson = new Gson();
        String sceneDatas = gson.toJson(items);
        LogUtils.e(sceneDatas);
        NetWorkUtils.uploadScene(mContext, Constants.method_uploadSecne, items.get(0).getSceneInvestigation().getID(),
                unitCode,
                userName,
                userId,
                items, new NetWorkUtils.Callback() {
                    @Override
                    public void onNext(String result) {
                        LogUtils.e("uploadCrimeScene " +result);
                        if (result.equals("success")) {
                            //上传成功,修改数据库
                            SceneDB.uploadCrime(item);
                            LogUtils.i(userName + "数据上传成功");
//                            XfUtils.startSpeak("上传" + crimeItem.getCasetype() + "成功");
                            BaseEvent.CommonEvent event = BaseEvent.CommonEvent.UPLOAD_CRIME;
                            event.setObject(1);
                            EventBus.getDefault().post(event);
                        } else {
                            result = "数据上传失败，" + result;
                            LogUtils.i(userName + result);
//                            XfUtils.startSpeak("上传" + crimeItem.getCasetype() + "失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("uploadCrimeScene error:" +e.getMessage());
//                        XfUtils.startSpeak("上传" + crimeItem.getCasetype() + "错误");
                    }
                });
    }

    public static String uploadPic(Context mContext, MultipartFile pic, String photoId, String state, String parentId,
                                   String crimeId, String photoInfo, String rev2) {
        String fileName = "";
        try {
//            String photoPath = mContext.getExternalFilesDir(Constants.value_photoDir).getAbsolutePath();
            File localFile = new File(Constants.photoPath + File.separator + crimeId + File.separator,
                    pic.getFilename());
            if(!localFile.getParentFile().exists()) {
                localFile.getParentFile().mkdirs();
            }
            pic.transferTo(localFile);
            fileName = localFile.getName();
            String fingerNo = "";
            Photo photo = new Photo();
            if(state.equals(Constants.photoState_visit_people)) {
                String[] split = photoId.split(",");
                photoId = split[0];
                fingerNo = split[1];
                photo.setRev1(fingerNo);
            }
            if(state.equals(Constants.photoState_flat)) {
                photo.setPhotoInfo(photoInfo);
                photo.setRev2(rev2);
            }
            photo.setId(photoId);
            photo.setParentId(parentId);
            photo.setCrimeId(crimeId);
            photo.setPath(localFile.getAbsolutePath());
            LogUtils.e("Path local: " + localFile.getAbsolutePath());
            LogUtils.e("Path photo: " + photo.getPath());
            photo.setServerPath(Constants.ipAddress + crimeId + File.separator + fileName);
            LogUtils.e("Path serverpath: " + Constants.ipAddress + crimeId + File.separator + fileName);
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            photo.setType(fileType);
            String path = photo.getPath();
            if(fileType.equals("dwg")) {
                //上传dwg文件，还是使用上传图片接口，为了区分dwg文件，在图片id中拼上"dwg"
                photoId = photoId + "dwg";
            }else {
                photo.setWidth(BitmapFactory.decodeFile(localFile.getPath()).getWidth() + "");
                photo.setHeight(BitmapFactory.decodeFile(localFile.getPath()).getHeight() + "");
                photo.setUUID(StringUtils.md5HashCode32(localFile.getPath()));
                if(fileType.equals("bmp")) {
                    //如果是bmp文件，新建一个jpg图片上传到服务器
                    path = photo.getPath().replace("bmp","jpg");
                    File file = new File(path);
                    BitmapUtils.saveBitmapAsPng(BitmapFactory.decodeFile(photo.getPath()),file);
                }
            }
            photo.setFileName(fileName);
            photo.setState(state);

            if(!state.equals(Constants.photoState_visit_people)
                    && !state.equals(Constants.photoState_compare_people)
                    && !state.equals(Constants.photoState_compare_finger)
                    && !state.equals(Constants.photoState_compare_foot)
                    && !state.equals(Constants.photoState_compare_face)) {
                SceneDB.insertPhoto(photo);
                String finalPath = path;
                NetWorkUtils.uploadPic(mContext, Constants.method_uploadPic,
                        path,
                        photoId,
                        crimeId,
                        StringUtils.md5HashCode32(path), new NetWorkUtils.Callback() {
                            @Override
                            public void onNext(String result) {
                                LogUtils.e("uploadPic " +result);
                                if(result.equals("success")) {
                                    if(state.equals(Constants.photoState_evidence) && photo.getType().equals("bmp")) {
                                        LogUtils.e("uploadPic getFingerFeature");
                                        featureES.execute(new Runnable() {
                                            @Override
                                            public void run() {
                                                photo.setRev4(Compare.getFeature(finalPath));
                                                SceneDB.insertPhoto(photo);
                                            }
                                        });
                                    }
                                    photo.setIsUpload(Constants.UPLOAD_SUCCESS);
//                            Toast.makeText(mContext,"图片上传成功",Toast.LENGTH_SHORT).show();
                                }else {
                                    photo.setIsUpload(Constants.UPLOAD_FAIL);
//                            result = "上传图片错误:" + result;
                                }
                                SceneDB.insertPhoto(photo);
                            }

                            @Override
                            public void onError(Throwable e) {
                                LogUtils.e("uploadPic " +e.getMessage());
                                photo.setIsUpload(Constants.UPLOAD_FAIL);
                                if(state.equals(Constants.photoState_evidence) && photo.getType().equals("bmp")) {
                                    LogUtils.e("uploadPic getFingerFeature");
                                    featureES.execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            photo.setRev4(Compare.getFeature(finalPath));
                                            SceneDB.insertPhoto(photo);
                                        }
                                    });
                                }
                                SceneDB.insertPhoto(photo);
                            }
                        });
            }else if(state.equals(Constants.photoState_visit_people)){
//                String photoPath = photo.getPath();
//                if(photo.getType().equals("bmp")) {
//                    photoPath = photoPath.replace("bmp","jpg");
//                }
//                photo.setRev4(Compare.getFeature(photoPath));
//                photo.setRev4(Compare.getFeature(path));
                //判断当前指纹是否有图片
                List<Photo> photos = SceneDB.selectVisitFingerByFingerNo(photo, fingerNo);
                if(photos.size() > 0) {
                    SceneDB.deletePhotos(photos);
                    for(Photo photo1 : photos) {
                        FileUtils.deleteFile(photo1.getPath());
                    }
                }
                photo.setIsUpload(Constants.UPLOAD_SUCCESS);
                SceneDB.insertPhoto(photo);

                String finalPath1 = path;
                LogUtils.e("uploadPic getFingerFeature");
                featureES.execute(new Runnable() {
                    @Override
                    public void run() {
                        photo.setRev4(Compare.getFeature(finalPath1));
                        SceneDB.insertPhoto(photo);
                    }
                });
            }else if(state.equals(Constants.photoState_compare_people)
                    || state.equals(Constants.photoState_compare_finger)
                    || state.equals(Constants.photoState_compare_foot)
                    || state.equals(Constants.photoState_compare_face)) {
                photo.setIsUpload(Constants.UPLOAD_SUCCESS);
                SceneDB.insertPhoto(photo);
                LogUtils.e("uploadPic compare photo ");
            }
        }catch (Exception e) {
            e.printStackTrace();
            LogUtils.e(e.getMessage());
            return GsonUtils.faildJson(500,"保存图片错误：" + e.getMessage());
        }
        return GsonUtils.successJson(fileName);
    }

    public static String uploadPics(Context mContext, MultipartFile[] pics, List<String> photoIds, String state, String parentId,
                                    String crimeId, List<String> photoInfos, List<String> rev2s) {
        for(int i = 0; i < pics.length; i ++) {
            MultipartFile pic = pics[i];
            String photoId = photoIds.get(i);
            String fileName = "";
            try {
                File localFile = new File(Constants.photoPath + File.separator + crimeId + File.separator,
                        pic.getFilename());
                if(!localFile.getParentFile().exists()) {
                    localFile.getParentFile().mkdirs();
                }
                pic.transferTo(localFile);
                fileName = localFile.getName();
                String fingerNo = "";
                Photo photo = new Photo();
                if(state.equals(Constants.photoState_visit_people)) {
                    String[] split = photoId.split(",");
                    photoId = split[0];
                    fingerNo = split[1];
                    photo.setRev1(fingerNo);
                }
                if(state.equals(Constants.photoState_flat)) {
                    String photoInfo = photoInfos.get(i);
                    String rev2 = rev2s.get(i);
                    photo.setPhotoInfo(photoInfo);
                    photo.setRev2(rev2);
                }
                photo.setId(photoId);
                photo.setParentId(parentId);
                photo.setCrimeId(crimeId);
                photo.setPath(localFile.getAbsolutePath());
                LogUtils.e("Path local: " + localFile.getAbsolutePath());
                LogUtils.e("Path photo: " + photo.getPath());
                photo.setServerPath(Constants.ipAddress + crimeId + File.separator + fileName);
                LogUtils.e("Path serverpath: " + Constants.ipAddress + crimeId + File.separator + fileName);
                String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
                photo.setType(fileType);
                String path = photo.getPath();
                if(fileType.equals("dwg")) {
                    //上传dwg文件，还是使用上传图片接口，为了区分dwg文件，在图片id中拼上"dwg"
                    photoId = photoId + "dwg";
                }else {
                    photo.setWidth(BitmapFactory.decodeFile(localFile.getPath()).getWidth() + "");
                    photo.setHeight(BitmapFactory.decodeFile(localFile.getPath()).getHeight() + "");
                    photo.setUUID(StringUtils.md5HashCode32(localFile.getPath()));
                    if(fileType.equals("bmp")) {
                        //如果是bmp文件，新建一个jpg图片上传到服务器
                        path = photo.getPath().replace("bmp","jpg");
                        File file = new File(path);
                        BitmapUtils.saveBitmapAsPng(BitmapFactory.decodeFile(photo.getPath()),file);
                    }
                }
                photo.setFileName(fileName);
                photo.setState(state);

                if(!state.equals(Constants.photoState_visit_people)
                        && !state.equals(Constants.photoState_compare_people)
                        && !state.equals(Constants.photoState_compare_finger)
                        && !state.equals(Constants.photoState_compare_foot)
                        && !state.equals(Constants.photoState_compare_face)) {
                    SceneDB.insertPhoto(photo);
                    String finalPath = path;
                    NetWorkUtils.uploadPic(mContext, Constants.method_uploadPic,
                            path,
                            photoId,
                            crimeId,
                            StringUtils.md5HashCode32(path), new NetWorkUtils.Callback() {
                                @Override
                                public void onNext(String result) {
                                    LogUtils.e("uploadPic " +result);
                                    if(result.equals("success")) {
                                        if(state.equals(Constants.photoState_evidence) && photo.getType().equals("bmp")) {
                                            LogUtils.e("uploadPic getFingerFeature");
                                            featureES.execute(new Runnable() {
                                                @Override
                                                public void run() {
                                                    photo.setRev4(Compare.getFeature(finalPath));
                                                    SceneDB.insertPhoto(photo);
                                                }
                                            });
                                        }
                                        photo.setIsUpload(Constants.UPLOAD_SUCCESS);
//                            Toast.makeText(mContext,"图片上传成功",Toast.LENGTH_SHORT).show();
                                    }else {
                                        photo.setIsUpload(Constants.UPLOAD_FAIL);
//                            result = "上传图片错误:" + result;
                                    }
                                    SceneDB.insertPhoto(photo);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    LogUtils.e("uploadPic " +e.getMessage());
                                    photo.setIsUpload(Constants.UPLOAD_FAIL);
                                    if(state.equals(Constants.photoState_evidence) && photo.getType().equals("bmp")) {
                                        LogUtils.e("uploadPic getFingerFeature");
                                        featureES.execute(new Runnable() {
                                            @Override
                                            public void run() {
                                                photo.setRev4(Compare.getFeature(finalPath));
                                                SceneDB.insertPhoto(photo);
                                            }
                                        });
                                    }
                                    SceneDB.insertPhoto(photo);
                                }
                            });
                }else if(state.equals(Constants.photoState_visit_people)){
                    //判断当前指纹是否有图片
                    List<Photo> photos = SceneDB.selectVisitFingerByFingerNo(photo, fingerNo);
                    if(photos.size() > 0) {
                        SceneDB.deletePhotos(photos);
                        for(Photo photo1 : photos) {
                            FileUtils.deleteFile(photo1.getPath());
                        }
                    }
                    photo.setIsUpload(Constants.UPLOAD_SUCCESS);
                    SceneDB.insertPhoto(photo);

                    String finalPath1 = path;
                    LogUtils.e("uploadPic getFingerFeature");
                    featureES.execute(new Runnable() {
                        @Override
                        public void run() {
                            photo.setRev4(Compare.getFeature(finalPath1));
                            SceneDB.insertPhoto(photo);
                        }
                    });
                }else if(state.equals(Constants.photoState_compare_people)
                        || state.equals(Constants.photoState_compare_finger)
                        || state.equals(Constants.photoState_compare_foot)
                        || state.equals(Constants.photoState_compare_face)) {
                    photo.setIsUpload(Constants.UPLOAD_SUCCESS);
                    SceneDB.insertPhoto(photo);
                    LogUtils.e("uploadPic compare photo ");
                }
            }catch (Exception e) {
                return GsonUtils.faildJson(500,"保存图片错误");
            }
        }
        return GsonUtils.successJson("");
    }

    public static String deletePic(Context mContext, String photoId, String fileName, String crimeId) {
        Photo photo = SceneDB.selectPhotoById(photoId);
        if(photo != null) {
            if(FileUtils.deleteFile(photo.getPath())) {
                SceneDB.deletePhoto(photoId);
                return GsonUtils.successJson("删除成功");
            }else {
                return GsonUtils.faildJson(500,"删除失败,文件删除错误");
            }
        }
        return GsonUtils.faildJson(500,"删除失败,文件未找到");
    }

    public static String deletePics(Context mContext, List<Photo> photoList, String crimeId) {
        for(Photo photo : photoList) {
//            String photoPath = mContext.getExternalFilesDir(Constants.value_photoDir).getAbsolutePath();
            if(!FileUtils.deleteFile(Constants.photoPath + File.separator + crimeId + File.separator + photo.getFileName())) {
                return GsonUtils.faildJson(500,"删除失败");
            }
            SceneDB.deletePhoto(photo.getId());
        }
        return GsonUtils.successJson("删除成功");
    }

    private static long startTime,endTime;
    private static String deviceID = "";
    private static boolean isCollecting = false;
    private static String crimeId = "";
    private static String lat = "";
    private static String lon = "";

    public static String startCollectionKct(Context mContext, String id, String kctlat, String kctlon) {
        if(isCollecting) {
            return GsonUtils.faildJson(500,"正在采集");
        }
        crimeId = id;
        lat = kctlat;
        lon = kctlon;
        startCollection(mContext);
        deviceID = Settings.System.getString(mContext.getContentResolver(), Settings.System.ANDROID_ID);
        IntentFilter filter = new IntentFilter();
        filter.addAction(CollUtils.ACTION_RECEIVE_RESULT);
        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        mContext.registerReceiver(mReceiver, filter);
        isCollecting = true;
//        XfUtils.startSpeak("开始采集基站");
        return GsonUtils.successJson("开始采集");
    }

    private static BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (CollUtils.ACTION_RECEIVE_RESULT.equals(action)) {
                LogUtils.e("Received cell result");
                endTime = System.currentTimeMillis();
                ArrayList<String> result = (ArrayList<String>) intent.getStringArrayListExtra("result");

                /* << AnitaLin */
                //Bug [基站信息在采集过程中退出，再次新增或从列表进入采集时，程序崩溃退出]
                //Check if getActivity is null
//                if (activity == null) return;
                isCollecting = false;
                String file_path = (String) intent.getStringExtra("file_path");
                String uuid = (String) intent.getStringExtra("uuid");
                LogUtils.e( "uuid from another service = " + uuid);
//                LogUtils.e("file_path from another service = " + path);
                String path = CollUtils.copyToInternalPath(context, file_path);
                InputStream is = null;
                try {
                    is = new FileInputStream(new File(path));
                    List<KCTBASESTATIONDATABean> kctbasestationdataBeans = XmlUtils.xml2KCT(is,deviceID,startTime,endTime,crimeId,lat,lon);
                    //更新数据库
//                    XfUtils.startSpeak("基站采集完成");
                    SceneDB.updateKct(kctbasestationdataBeans,crimeId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public static void startCollection(Context context) {
        startTime = System.currentTimeMillis();
        LogUtils.e("startCollection");
        Intent it = new Intent();
        it.setAction("com.kuaikan.one_key");
        it.setComponent(new ComponentName("com.kuaikan.app.scenecollection",
                "com.kuaikan.app.scenecollection.OneKeyService"));
        it.putExtra("show", false);
        it.putExtra("is_quick_serch", false);
        it.putExtra("is_all_search", false);
        context.startService(it);
    }

    private static void stopCollection(Context context){
        LogUtils.e("stopCollection");

        Intent it=new Intent();
        it.setAction("com.kuaikan.one_key");
        it.setComponent(new ComponentName("com.kuaikan.app.scenecollection",
                "com.kuaikan.app.scenecollection.OneKeyService"));
        it.putExtra("request_type", 0);
        context.stopService(it);
    }

    public static String stopCollectionKct(Context mContext) {
        stopCollection(mContext);
        isCollecting = false;
        return GsonUtils.successJson("停止采集");
    }

    public static String getCollectionKctData(Context mContext,String crimeId) {
        List<KCTBASESTATIONDATABean> kctbasestationdataBeanList = SceneDB.selectKctList(crimeId);
        if(kctbasestationdataBeanList.size() == 0) {
            if(isCollecting) {
                return GsonUtils.faildJson(500,"采集中");
            }
        }else {
            return GsonUtils.successJson(kctbasestationdataBeanList);
        }
        return GsonUtils.successJson(kctbasestationdataBeanList);
    }

    public static String uploadEvidence(EvidenceEntity entity) {
        Photo photo = SceneDB.selectPhotoByParentId(entity.getId(), entity.getCrimeId());
        if(photo != null) {
            SceneDB.insertEvidence(entity);
        }else {
            return GsonUtils.faildJson(500,"没有痕迹照片");
        }
        return GsonUtils.successJson("上传成功");
    }

    public static String deleteEvidence(String evidenceId,String crimeId) {
        EvidenceEntity evidence = SceneDB.selectEvidenceById(evidenceId);
        if(evidence == null) {
            return GsonUtils.faildJson(500,"没有该痕迹，请检查后重试");
        }
        SceneDB.deleteEvidenceById(evidence);
        Photo photo = SceneDB.selectPhotoByParentId(evidenceId, crimeId);
        SceneDB.deletePhoto(photo.getId());
        return GsonUtils.successJson("删除成功");
    }

    private static String getPresentScene() {
        String presentSceneId = (String) SPUtils.getParam(ServerApplication.getContext(), Constants.sp_presentScene,"");
        if(!StringUtils.checkString(presentSceneId)) {
            //默认现场为最新现场
            CrimeItem crime = SceneDB.getNewCrime();
            if(crime != null) {
                return crime.getId();
            }else {
                return "";
            }
        }
        return presentSceneId;
    }

    public static String uploadFingerEvidencePic(Context mContext, MultipartFile pic) {
        //获取当前现场
        String crimeId = getPresentScene();
        CrimeItem item = SceneDB.selectCrimeById(crimeId);
        String accessInspectors = item.getAccessInspectors();
        String accessInspectorsKey = item.getAccessInspectorsKey();
        try {
            File localFile = new File(Constants.photoPath + File.separator + crimeId + File.separator,
                    pic.getFilename());
            if(!localFile.getParentFile().exists()) {
                localFile.getParentFile().mkdirs();
            }
            pic.transferTo(localFile);
            String photoId = StringUtils.getUUID();
            String evidenceId = StringUtils.getUUID();
            String state = Constants.photoState_evidence;
            String fileName = localFile.getName();
            Photo photo = new Photo();
            photo.setId(photoId);
            photo.setParentId(evidenceId);
            photo.setCrimeId(crimeId);
            photo.setPath(localFile.getAbsolutePath());
            LogUtils.e("Path local: " + localFile.getAbsolutePath());
            photo.setServerPath(Constants.ipAddress + crimeId + File.separator + fileName);
            LogUtils.e("Path serverpath: " + Constants.ipAddress + crimeId + File.separator + fileName);
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            photo.setType(fileType);
            String path = photo.getPath();
            photo.setWidth(BitmapFactory.decodeFile(localFile.getPath()).getWidth() + "");
            photo.setHeight(BitmapFactory.decodeFile(localFile.getPath()).getHeight() + "");
            photo.setUUID(StringUtils.md5HashCode32(localFile.getPath()));
            if(fileType.equals("bmp")) {
                //如果是bmp文件，新建一个jpg图片上传到服务器
                path = photo.getPath().replace("bmp","jpg");
                File file = new File(path);
                BitmapUtils.saveBitmapAsPng(BitmapFactory.decodeFile(photo.getPath()),file);
            }
            photo.setFileName(fileName);
            photo.setState(state);
            SceneDB.insertPhoto(photo);
            String finalPath = path;
            NetWorkUtils.uploadPic(mContext, Constants.method_uploadPic,
                    path,
                    photoId,
                    crimeId,
                    StringUtils.md5HashCode32(path), new NetWorkUtils.Callback() {
                        @Override
                        public void onNext(String result) {
                            LogUtils.e("uploadPic " +result);
                            if(result.equals("success")) {
                                LogUtils.e("uploadPic getFingerFeature");
                                featureES.execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        photo.setRev4(Compare.getFeature(finalPath));
                                        SceneDB.insertPhoto(photo);
                                    }
                                });
                                photo.setIsUpload(Constants.UPLOAD_SUCCESS);
                            }else {
                                photo.setIsUpload(Constants.UPLOAD_FAIL);
                            }
                            SceneDB.insertPhoto(photo);
                        }

                        @Override
                        public void onError(Throwable e) {
                            LogUtils.e("uploadPic " +e.getMessage());
                            photo.setIsUpload(Constants.UPLOAD_FAIL);
                            LogUtils.e("uploadPic getFingerFeature");
                            featureES.execute(new Runnable() {
                                @Override
                                public void run() {
                                    photo.setRev4(Compare.getFeature(finalPath));
                                    SceneDB.insertPhoto(photo);
                                }
                            });
                            SceneDB.insertPhoto(photo);
                        }
                    });

            //插入指纹数据
            EvidenceEntity finger = new EvidenceEntity();
            finger.setId(evidenceId);
            finger.setCrimeId(crimeId);
            finger.setEvidenceCategory("手印");
            finger.setEvidence("指纹");
            finger.setEvidenceKey("1101");
            finger.setEvidenceName("指纹");
            finger.setLegacySite("遗留部位");
            finger.setMethod("直接照相");
            finger.setMethodKey("A01");
            finger.setTime(System.currentTimeMillis());
            finger.setPeople(accessInspectors);
            finger.setPeopleKey(accessInspectorsKey);
            finger.setPhotoId(photoId);
            SceneDB.insertEvidence(finger);
        }catch (Exception e) {
            GsonUtils.faildJson(500,"文件写入失败");
        }
        return GsonUtils.successJson("上传成功");
    }

    public static String uploadFootEvidencePic(Context mContext, MultipartFile pic) {
        //获取当前现场
        String crimeId = getPresentScene();
        CrimeItem item = SceneDB.selectCrimeById(crimeId);
        String accessInspectors = item.getAccessInspectors();
        String accessInspectorsKey = item.getAccessInspectorsKey();
        try {
            File localFile = new File(Constants.photoPath + File.separator + crimeId + File.separator,
                    pic.getFilename());
            if(!localFile.getParentFile().exists()) {
                localFile.getParentFile().mkdirs();
            }
            pic.transferTo(localFile);
            String photoId = StringUtils.getUUID();
            String evidenceId = StringUtils.getUUID();
            String state = Constants.photoState_evidence;
            String fileName = localFile.getName();
            Photo photo = new Photo();
            photo.setId(photoId);
            photo.setParentId(evidenceId);
            photo.setCrimeId(crimeId);
            photo.setPath(localFile.getAbsolutePath());
            LogUtils.e("Path local: " + localFile.getAbsolutePath());
            photo.setServerPath(Constants.ipAddress + crimeId + File.separator + fileName);
            LogUtils.e("Path serverpath: " + Constants.ipAddress + crimeId + File.separator + fileName);
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            photo.setType(fileType);
            String path = photo.getPath();
            photo.setWidth(BitmapFactory.decodeFile(localFile.getPath()).getWidth() + "");
            photo.setHeight(BitmapFactory.decodeFile(localFile.getPath()).getHeight() + "");
            photo.setUUID(StringUtils.md5HashCode32(localFile.getPath()));
            if(fileType.equals("bmp")) {
                //如果是bmp文件，新建一个jpg图片上传到服务器
                path = photo.getPath().replace("bmp","jpg");
                File file = new File(path);
                BitmapUtils.saveBitmapAsPng(BitmapFactory.decodeFile(photo.getPath()),file);
            }
            photo.setFileName(fileName);
            photo.setState(state);
            SceneDB.insertPhoto(photo);
            String finalPath = path;
            NetWorkUtils.uploadPic(mContext, Constants.method_uploadPic,
                    path,
                    photoId,
                    crimeId,
                    StringUtils.md5HashCode32(path), new NetWorkUtils.Callback() {
                        @Override
                        public void onNext(String result) {
                            LogUtils.e("uploadPic " +result);
                            if(result.equals("success")) {
                                photo.setIsUpload(Constants.UPLOAD_SUCCESS);
                            }else {
                                photo.setIsUpload(Constants.UPLOAD_FAIL);
                            }
                            SceneDB.insertPhoto(photo);
                        }

                        @Override
                        public void onError(Throwable e) {
                            LogUtils.e("uploadPic " +e.getMessage());
                            photo.setIsUpload(Constants.UPLOAD_FAIL);
                            SceneDB.insertPhoto(photo);
                        }
                    });

            //插入足迹数据
            EvidenceEntity foot = new EvidenceEntity();
            foot.setId(evidenceId);
            foot.setCrimeId(crimeId);
            foot.setEvidenceCategory("足迹");
            foot.setEvidence("鞋印");
            foot.setEvidenceKey("1");
            foot.setEvidenceName("足迹");
            foot.setLegacySite("遗留部位");
            foot.setMethod("直接照相");
            foot.setMethodKey("B01");
            foot.setTime(System.currentTimeMillis());
            foot.setPeople(accessInspectors);
            foot.setPeopleKey(accessInspectorsKey);
            foot.setPhotoId(photoId);
            SceneDB.insertEvidence(foot);
        }catch (Exception e) {
            GsonUtils.faildJson(500,"文件写入失败");
        }
        return GsonUtils.successJson("上传成功");
    }
}
