package com.liany.csiserverapp.andServer.model;

import com.liany.csiserverapp.base.Constants;

import com.liany.csiserverapp.dao.database.greenDao.db.CompareEntityDao;
import com.liany.csiserverapp.dao.database.greenDao.db.ComparePhotoDao;
import com.liany.csiserverapp.dao.database.greenDao.db.ContactsEntityDao;
import com.liany.csiserverapp.dao.database.greenDao.db.CrimeItemDao;
import com.liany.csiserverapp.dao.database.greenDao.db.DaoSession;
import com.liany.csiserverapp.dao.database.greenDao.db.EvidenceEntityDao;
import com.liany.csiserverapp.dao.database.greenDao.db.GoodEntityDao;
import com.liany.csiserverapp.dao.database.greenDao.db.ItemEntityDao;
import com.liany.csiserverapp.dao.database.greenDao.db.KCTBASESTATIONDATABeanDao;
import com.liany.csiserverapp.dao.database.greenDao.db.PhotoDao;
import com.liany.csiserverapp.dao.database.greenDao.db.SceneWifiInfoDao;
import com.liany.csiserverapp.dao.database.greenDao.db.ToolEntityDao;
import com.liany.csiserverapp.dao.database.greenDao.db.WitnessEntityDao;
import com.liany.csiserverapp.dao.database.greenDao.db.sysDictDao;
import com.liany.csiserverapp.debug.ServerApplication;
import com.liany.csiserverapp.diagnose.CompareEntity;
import com.liany.csiserverapp.diagnose.ComparePhoto;
import com.liany.csiserverapp.diagnose.ContactsEntity;
import com.liany.csiserverapp.diagnose.CrimeItem;
import com.liany.csiserverapp.diagnose.EvidenceEntity;
import com.liany.csiserverapp.diagnose.GoodEntity;
import com.liany.csiserverapp.diagnose.ItemEntity;
import com.liany.csiserverapp.diagnose.KCTBASESTATIONDATABean;
import com.liany.csiserverapp.diagnose.Photo;
import com.liany.csiserverapp.diagnose.SceneWifiInfo;
import com.liany.csiserverapp.diagnose.ToolEntity;
import com.liany.csiserverapp.diagnose.WitnessEntity;
import com.liany.csiserverapp.utils.FileUtils;

import java.util.List;


/**
 * @创建者 ly
 * @创建时间 2020/3/9
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class SceneDB {

    public static CrimeItem getNewCrime() {
        DaoSession daoSession = ServerApplication.getDaoSession();
        List<CrimeItem> list = daoSession.getCrimeItemDao()
                .queryBuilder()
                .whereOr(CrimeItemDao.Properties.IsUpload.isNull(),CrimeItemDao.Properties.IsUpload.notEq("upload"))
                .orderDesc(CrimeItemDao.Properties.CreateTime)
                .list();
        if(list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 获取现场列表
     * @param type 0：未上传 1：已上传 2：全部
     */
    public static List<CrimeItem> getSceneList(int type) {

        if(type == 0) {
            DaoSession daoSession = ServerApplication.getDaoSession();
            return daoSession.getCrimeItemDao()
                    .queryBuilder()
                    .whereOr(CrimeItemDao.Properties.IsUpload.isNull(),CrimeItemDao.Properties.IsUpload.notEq("upload"))
                    .orderDesc(CrimeItemDao.Properties.CreateTime)
                    .list();
        }else if(type == 1) {
            DaoSession daoSession = ServerApplication.getDaoSession();
            return daoSession.getCrimeItemDao()
                    .queryBuilder()
                    .where(CrimeItemDao.Properties.IsUpload.eq("upload"))
                    .orderDesc(CrimeItemDao.Properties.CreateTime)
                    .list();
        }else {
            DaoSession daoSession = ServerApplication.getDaoSession();
            return daoSession.getCrimeItemDao().loadAll();
        }
    }

    /**
     * 分页获取现场列表
     * @param userName 用户名筛选，只显示登录人员录入的现场信息
     * @param offset 页数
     * @param type 0：未上传 1：已上传 2：全部
     * @return
     */
    public static List<CrimeItem> getSceneListByPage(String userName,int type,int offset) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        daoSession.clear();
        List<CrimeItem> crimeItemList;
        if(type == 0) {
            crimeItemList = daoSession.queryBuilder(CrimeItem.class)
                    //增加登录人员筛选，只显示登录人员录入的现场信息
                    .where(CrimeItemDao.Properties.UserName.eq(userName))
                    .whereOr(CrimeItemDao.Properties.IsUpload.isNull(), CrimeItemDao.Properties.IsUpload.notEq("upload"))
                    .orderDesc(CrimeItemDao.Properties.Occurred_start_time)
                    .offset(offset * Constants.value_pageSize).limit(Constants.value_pageSize).list();
        }else if(type == 1) {
            crimeItemList = daoSession.queryBuilder(CrimeItem.class)
                    //增加登录人员筛选，只显示登录人员录入的现场信息
                    .where(CrimeItemDao.Properties.UserName.eq(userName))
                    .where(CrimeItemDao.Properties.IsUpload.eq("upload"))
                    .orderDesc(CrimeItemDao.Properties.Occurred_start_time)
                    .offset(offset * Constants.value_pageSize).limit(Constants.value_pageSize).list();
        }else {
            crimeItemList = daoSession.queryBuilder(CrimeItem.class)
                    //增加登录人员筛选，只显示登录人员录入的现场信息
                    .where(CrimeItemDao.Properties.UserName.eq(userName))
                    .orderDesc(CrimeItemDao.Properties.Occurred_start_time)
                    .offset(offset * Constants.value_pageSize).limit(Constants.value_pageSize).list();
        }
        for(CrimeItem item : crimeItemList) {
//            List<ContactsEntity> contactsEntities = daoSession.queryBuilder(ContactsEntity.class).where(ContactsEntityDao.Properties.CrimeId.eq(crimeItem.getId())).list();
//            for(ContactsEntity entity : contactsEntities) {
//                List<Photo> list = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.ParentId.eq(entity.getId())).list();
//                entity.setPhotos(list);
//            }
//            crimeItem.setReleatedPeopleItem(contactsEntities);
//            crimeItem.setLostItem(daoSession.queryBuilder(ItemEntity.class).where(ItemEntityDao.Properties.CrimeId.eq(crimeItem.getId())).list());
//            crimeItem.setCrimeToolItem(daoSession.queryBuilder(ToolEntity.class).where(ToolEntityDao.Properties.CrimeId.eq(crimeItem.getId())).list());
//            crimeItem.setPositionItem(daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.ParentId.eq(crimeItem.getId()),PhotoDao.Properties.State.eq(Constants.photoState_positionPic)).list());
//            crimeItem.setFlatItem(daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.ParentId.eq(crimeItem.getId()),PhotoDao.Properties.State.eq(Constants.photoState_flat)).list());
//            crimeItem.setDwgItem(daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.ParentId.eq(crimeItem.getId()),PhotoDao.Properties.State.eq(Constants.photoState_flat_dwg)).list());
//            crimeItem.setPositionPhotoItem(daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.ParentId.eq(crimeItem.getId()),PhotoDao.Properties.State.eq(Constants.photoState_positionPhoto)).list());
//            crimeItem.setOverviewPhotoItem(daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.ParentId.eq(crimeItem.getId()),PhotoDao.Properties.State.eq(Constants.photoState_overview)).list());
//            crimeItem.setImportantPhotoItem(daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.ParentId.eq(crimeItem.getId()),PhotoDao.Properties.State.eq(Constants.photoState_important)).list());
//            crimeItem.setDetailPhotoItem(daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.ParentId.eq(crimeItem.getId()),PhotoDao.Properties.State.eq(Constants.photoState_detail)).list());
//            List<EvidenceEntity> evidenceEntities = daoSession.queryBuilder(EvidenceEntity.class).where(EvidenceEntityDao.Properties.CrimeId.eq(crimeItem.getId())).list();
//            for(EvidenceEntity entity : evidenceEntities) {
//                entity.setPhoto(daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(crimeItem.getId()),PhotoDao.Properties.ParentId.eq(entity.getId()),PhotoDao.Properties.State.eq(Constants.photoState_witness)).unique());
//            }
//            crimeItem.setEvidenceItem(evidenceEntities);
//            crimeItem.setMonitoringPhotoItem(daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.ParentId.eq(crimeItem.getId()),PhotoDao.Properties.State.eq(Constants.photoState_monitoring)).list());
//            crimeItem.setCameraPhotoItem(daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.ParentId.eq(crimeItem.getId()),PhotoDao.Properties.State.eq(Constants.photoState_camera)).list());
//            List<WitnessEntity> witnessEntities = daoSession.queryBuilder(WitnessEntity.class).where(WitnessEntityDao.Properties.CrimeId.eq(crimeItem.getId())).list();
//            for(WitnessEntity entity : witnessEntities) {
//                entity.setPhoto(daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(crimeItem.getId()),PhotoDao.Properties.ParentId.eq(entity.getId()),PhotoDao.Properties.State.eq(Constants.photoState_witness)).unique());
//            }
//            crimeItem.setWitnessItem(witnessEntities);
//            crimeItem.setWifiInfos(daoSession.queryBuilder(SceneWifiInfo.class).where(SceneWifiInfoDao.Properties.CrimeId.eq(crimeItem.getId())).list());
//            List<GoodEntity> goodEntities = daoSession.queryBuilder(GoodEntity.class).where(GoodEntityDao.Properties.CrimeId.eq(crimeItem.getId())).list();
//            for(GoodEntity entity : goodEntities) {
//                List<Photo> list = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.ParentId.eq(entity.getId())).list();
//                entity.setPhotos(list);
//            }
//            crimeItem.setGoodEntities(goodEntities);
//            crimeItem.setKctbasestationdataBeans(daoSession.queryBuilder(KCTBASESTATIONDATABean.class).where(KCTBASESTATIONDATABeanDao.Properties.CrimeId.eq(crimeItem.getId())).list());
            List<ContactsEntity> contactsEntities = daoSession.queryBuilder(ContactsEntity.class).where(ContactsEntityDao.Properties.CrimeId.eq(item.getId())).list();
            for(ContactsEntity entity : contactsEntities) {
                List<Photo> list = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.ParentId.eq(entity.getId())).list();
                for(Photo photo : list) {
                    photo.setRev4("");
                }
                entity.setPhotos(list);
            }
            item.setReleatedPeopleItem(contactsEntities);
            item.setLostItem(daoSession.queryBuilder(ItemEntity.class).where(ItemEntityDao.Properties.CrimeId.eq(item.getId())).list());
            item.setCrimeToolItem(daoSession.queryBuilder(ToolEntity.class).where(ToolEntityDao.Properties.CrimeId.eq(item.getId())).list());
            List<Photo> positionPics = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_positionPic)).list();
            item.setPositionItem(positionPics);
            List<Photo> flatPhotos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_flat)).list();
            item.setFlatItem(flatPhotos);
            List<Photo> dwgPhotos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_flat_dwg)).list();
            item.setDwgItem(dwgPhotos);
            List<Photo> positionPhotos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_positionPhoto)).list();
            item.setPositionPhotoItem(positionPhotos);
            List<Photo> overViewPhotos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_overview)).list();
            item.setOverviewPhotoItem(overViewPhotos);
            List<Photo> importantPhotos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_important)).list();
            item.setImportantPhotoItem(importantPhotos);
            List<Photo> detailPhotos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()), PhotoDao.Properties.State.eq(Constants.photoState_detail)).list();
            item.setDetailPhotoItem(detailPhotos);
            List<EvidenceEntity> evidenceEntities = daoSession.queryBuilder(EvidenceEntity.class).where(EvidenceEntityDao.Properties.CrimeId.eq(item.getId())).list();
            for(EvidenceEntity entity : evidenceEntities) {
                List<Photo> photos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()), PhotoDao.Properties.ParentId.eq(entity.getId()), PhotoDao.Properties.State.eq(Constants.photoState_evidence)).list();
                if(photos.size() > 0) {
                    for(Photo photo : photos) {
                        photo.setRev4("");
                    }
                    entity.setPhotoId(photos.get(photos.size() -1).getId());
                    entity.setPhoto(photos.get(photos.size() -1));
                }
            }
            item.setEvidenceItem(evidenceEntities);
            item.setMonitoringPhotoItem(daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_monitoring)).list());
            item.setCameraPhotoItem(daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_camera)).list());
            List<WitnessEntity> witnessEntities = daoSession.queryBuilder(WitnessEntity.class).where(WitnessEntityDao.Properties.CrimeId.eq(item.getId())).list();
            for(WitnessEntity entity : witnessEntities) {
                List<Photo> photos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()), PhotoDao.Properties.ParentId.eq(entity.getId()), PhotoDao.Properties.State.eq(Constants.photoState_witness)).list();
                if(photos.size() > 0) {
                    entity.setPhotoId(photos.get(photos.size() -1).getId());
                    entity.setPhoto(photos.get(photos.size() -1));
                }
            }
            item.setWitnessItem(witnessEntities);
            List<GoodEntity> goodEntities = daoSession.queryBuilder(GoodEntity.class).where(GoodEntityDao.Properties.CrimeId.eq(item.getId())).list();
            for(GoodEntity entity : goodEntities) {
                List<Photo> photos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.ParentId.eq(entity.getId()),PhotoDao.Properties.State.eq(Constants.photoState_extract)).list();
                entity.setPhotos(photos);
            }
            item.setGoodEntities(goodEntities);
            item.setKctbasestationdataBeans(daoSession.queryBuilder(KCTBASESTATIONDATABean.class).where(KCTBASESTATIONDATABeanDao.Properties.CrimeId.eq(item.getId())).list());
        }
        return crimeItemList;
    }

    public static int selectTotalCount() {
        return (int) ServerApplication.getDaoSession().queryBuilder(CrimeItem.class).count();
    }

    public static CrimeItem getSceneInfoById(String crimeId) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        daoSession.clear();
        CrimeItem item = daoSession.load(CrimeItem.class,crimeId);
        List<ContactsEntity> contactsEntities = daoSession.queryBuilder(ContactsEntity.class).where(ContactsEntityDao.Properties.CrimeId.eq(item.getId())).list();
        for(ContactsEntity entity : contactsEntities) {
            List<Photo> list = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.ParentId.eq(entity.getId())).list();
            for(Photo photo : list) {
                photo.setRev4("");
            }
            entity.setPhotos(list);
        }
        item.setReleatedPeopleItem(contactsEntities);
        item.setLostItem(daoSession.queryBuilder(ItemEntity.class).where(ItemEntityDao.Properties.CrimeId.eq(item.getId())).list());
        item.setCrimeToolItem(daoSession.queryBuilder(ToolEntity.class).where(ToolEntityDao.Properties.CrimeId.eq(item.getId())).list());
        List<Photo> positionPics = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_positionPic)).list();
        item.setPositionItem(positionPics);
        List<Photo> flatPhotos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_flat)).list();
        item.setFlatItem(flatPhotos);
        List<Photo> dwgPhotos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_flat_dwg)).list();
        item.setDwgItem(dwgPhotos);
        List<Photo> positionPhotos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_positionPhoto)).list();
        item.setPositionPhotoItem(positionPhotos);
        List<Photo> overViewPhotos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_overview)).list();
        item.setOverviewPhotoItem(overViewPhotos);
        List<Photo> importantPhotos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_important)).list();
        item.setImportantPhotoItem(importantPhotos);
        List<Photo> detailPhotos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()), PhotoDao.Properties.State.eq(Constants.photoState_detail)).list();
        item.setDetailPhotoItem(detailPhotos);
        List<EvidenceEntity> evidenceEntities = daoSession.queryBuilder(EvidenceEntity.class).where(EvidenceEntityDao.Properties.CrimeId.eq(item.getId())).list();
        for(EvidenceEntity entity : evidenceEntities) {
            List<Photo> photos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()), PhotoDao.Properties.ParentId.eq(entity.getId()), PhotoDao.Properties.State.eq(Constants.photoState_evidence)).list();
            if(photos.size() > 0) {
                for(Photo photo : photos) {
                    photo.setRev4("");
                }
                entity.setPhotoId(photos.get(photos.size() - 1).getId());
                entity.setPhoto(photos.get(photos.size() - 1));
            }
        }
        item.setEvidenceItem(evidenceEntities);
        item.setMonitoringPhotoItem(daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_monitoring)).list());
        item.setCameraPhotoItem(daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_camera)).list());
        List<WitnessEntity> witnessEntities = daoSession.queryBuilder(WitnessEntity.class).where(WitnessEntityDao.Properties.CrimeId.eq(item.getId())).list();
        for(WitnessEntity entity : witnessEntities) {
            List<Photo> photos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()), PhotoDao.Properties.ParentId.eq(entity.getId()), PhotoDao.Properties.State.eq(Constants.photoState_witness)).list();
            if(photos.size() > 0) {
                entity.setPhotoId(photos.get(photos.size() - 1).getId());
                entity.setPhoto(photos.get(photos.size() - 1));
            }
        }
        item.setWitnessItem(witnessEntities);
        List<GoodEntity> goodEntities = daoSession.queryBuilder(GoodEntity.class).where(GoodEntityDao.Properties.CrimeId.eq(item.getId())).list();
        for(GoodEntity entity : goodEntities) {
            List<Photo> photos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.ParentId.eq(entity.getId()),PhotoDao.Properties.State.eq(Constants.photoState_extract)).list();
            entity.setPhotos(photos);
        }
        item.setGoodEntities(goodEntities);
        item.setKctbasestationdataBeans(daoSession.queryBuilder(KCTBASESTATIONDATABean.class).where(KCTBASESTATIONDATABeanDao.Properties.CrimeId.eq(item.getId())).list());
        return item;
    }

    public static void insertCrimeItem(CrimeItem item) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        daoSession.clear();
        long i = daoSession.insertOrReplace(item);
        List<ContactsEntity> contactsEntityList = daoSession.queryBuilder(ContactsEntity.class).where(ContactsEntityDao.Properties.CrimeId.eq(item.getId())).list();
        daoSession.getContactsEntityDao().deleteInTx(contactsEntityList);
        daoSession.getContactsEntityDao().insertInTx(item.getReleatedPeopleItem());

        List<ItemEntity> itemList = item.getLostItem();
        List<ItemEntity> itemEntityList = daoSession.queryBuilder(ItemEntity.class).where(ItemEntityDao.Properties.CrimeId.eq(item.getId())).list();
        daoSession.getItemEntityDao().deleteInTx(itemEntityList);
        daoSession.getItemEntityDao().insertInTx(itemList);

        List<ToolEntity> toolList = item.getCrimeToolItem();
        List<ToolEntity> toolEntityList = daoSession.queryBuilder(ToolEntity.class).where(ToolEntityDao.Properties.CrimeId.eq(item.getId())).list();
        daoSession.getToolEntityDao().deleteInTx(toolEntityList);
        daoSession.getToolEntityDao().insertInTx(toolList);

//        List<EvidenceEntity> evidenceList = item.getEvidenceItem();
//        List<EvidenceEntity> evidenceEntityList = daoSession.queryBuilder(EvidenceEntity.class).where(EvidenceEntityDao.Properties.CrimeId.eq(item.getId())).list();
//        daoSession.getEvidenceEntityDao().deleteInTx(evidenceEntityList);
//        daoSession.getEvidenceEntityDao().insertInTx(evidenceList);

        List<WitnessEntity> witnessList = item.getWitnessItem();
        List<WitnessEntity> witnessEntityList = daoSession.queryBuilder(WitnessEntity.class).where(WitnessEntityDao.Properties.CrimeId.eq(item.getId())).list();
        daoSession.getWitnessEntityDao().deleteInTx(witnessEntityList);
        daoSession.getWitnessEntityDao().insertInTx(witnessList);

        List<SceneWifiInfo> sceneWifiInfos = item.getWifiInfos();
        List<SceneWifiInfo> sceneWifiInfoList = daoSession.queryBuilder(SceneWifiInfo.class).where(SceneWifiInfoDao.Properties.CrimeId.eq(item.getId())).list();
        daoSession.getSceneWifiInfoDao().deleteInTx(sceneWifiInfoList);
        daoSession.getSceneWifiInfoDao().insertInTx(sceneWifiInfos);

        List<GoodEntity> goodEntities = item.getGoodEntities();
        List<GoodEntity> goodEntityList = daoSession.queryBuilder(GoodEntity.class).where(GoodEntityDao.Properties.CrimeId.eq(item.getId())).list();
        daoSession.getGoodEntityDao().deleteInTx(goodEntityList);
        daoSession.getGoodEntityDao().insertInTx(goodEntities);

        List<KCTBASESTATIONDATABean> kctbasestationdataBeans = item.getKctbasestationdataBeans();
        List<KCTBASESTATIONDATABean> kctbasestationdataBeanList = daoSession.queryBuilder(KCTBASESTATIONDATABean.class).where(KCTBASESTATIONDATABeanDao.Properties.CrimeId.eq(item.getId())).list();
        daoSession.getKCTBASESTATIONDATABeanDao().deleteInTx(kctbasestationdataBeanList);
        daoSession.getKCTBASESTATIONDATABeanDao().insertInTx(kctbasestationdataBeans);

        //保存photoInfo
        List<Photo> positionPhotoItem = item.getPositionPhotoItem();
        for(Photo photo : positionPhotoItem) {
            Photo load = daoSession.getPhotoDao().load(photo.getId());
            load.setPhotoInfo(photo.getPhotoInfo());
            daoSession.getPhotoDao().update(load);
        }
        List<Photo> overviewPhotoItem = item.getOverviewPhotoItem();
        for(Photo photo : overviewPhotoItem) {
            Photo load = daoSession.getPhotoDao().load(photo.getId());
            load.setPhotoInfo(photo.getPhotoInfo());
            daoSession.getPhotoDao().update(load);
        }
        List<Photo> importantPhotoItem = item.getImportantPhotoItem();
        for(Photo photo : importantPhotoItem) {
            Photo load = daoSession.getPhotoDao().load(photo.getId());
            load.setPhotoInfo(photo.getPhotoInfo());
            daoSession.getPhotoDao().update(load);
        }
        List<Photo> detailPhotoItem = item.getDetailPhotoItem();
        for(Photo photo : detailPhotoItem) {
            Photo load = daoSession.getPhotoDao().load(photo.getId());
            load.setPhotoInfo(photo.getPhotoInfo());
            daoSession.getPhotoDao().update(load);
        }
        List<Photo> monitoringPhotoItem = item.getMonitoringPhotoItem();
        for(Photo photo : monitoringPhotoItem) {
            Photo load = daoSession.getPhotoDao().load(photo.getId());
            load.setPhotoInfo(photo.getPhotoInfo());
            daoSession.getPhotoDao().update(load);
        }
        List<Photo> cameraPhotoItem = item.getCameraPhotoItem();
        for(Photo photo : cameraPhotoItem) {
            Photo load = daoSession.getPhotoDao().load(photo.getId());
            load.setPhotoInfo(photo.getPhotoInfo());
            daoSession.getPhotoDao().update(load);
        }
    }


    public static CrimeItem selectCrimeById(String id) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        CrimeItem item = ServerApplication.getDaoSession().getCrimeItemDao().load(id);
        List<ContactsEntity> contactsEntities = daoSession.queryBuilder(ContactsEntity.class).where(ContactsEntityDao.Properties.CrimeId.eq(item.getId())).list();
        for(ContactsEntity entity : contactsEntities) {
            List<Photo> list = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.ParentId.eq(entity.getId())).list();
            entity.setPhotos(list);
        }
        item.setReleatedPeopleItem(contactsEntities);
        item.setLostItem(daoSession.queryBuilder(ItemEntity.class).where(ItemEntityDao.Properties.CrimeId.eq(item.getId())).list());
        item.setCrimeToolItem(daoSession.queryBuilder(ToolEntity.class).where(ToolEntityDao.Properties.CrimeId.eq(item.getId())).list());
        List<Photo> positionPics = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_positionPic)).list();
        item.setPositionItem(positionPics);
        List<Photo> flatPhotos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_flat)).list();
        item.setFlatItem(flatPhotos);
        List<Photo> dwgPhotos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_flat_dwg)).list();
        item.setDwgItem(dwgPhotos);
        List<Photo> positionPhotos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_positionPhoto)).list();
        item.setPositionPhotoItem(positionPhotos);
        List<Photo> overViewPhotos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_overview)).list();
        item.setOverviewPhotoItem(overViewPhotos);
        List<Photo> importantPhotos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_important)).list();
        item.setImportantPhotoItem(importantPhotos);
        List<Photo> detailPhotos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()), PhotoDao.Properties.State.eq(Constants.photoState_detail)).list();
        item.setDetailPhotoItem(detailPhotos);
        List<EvidenceEntity> evidenceEntities = daoSession.queryBuilder(EvidenceEntity.class).where(EvidenceEntityDao.Properties.CrimeId.eq(item.getId())).list();
        for(EvidenceEntity entity : evidenceEntities) {
            List<Photo> photos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()), PhotoDao.Properties.ParentId.eq(entity.getId()), PhotoDao.Properties.State.eq(Constants.photoState_evidence)).list();
            if(photos.size() > 0) {
                entity.setPhotoId(photos.get(photos.size() - 1).getId());
                entity.setPhoto(photos.get(photos.size() - 1));
            }
        }
        item.setEvidenceItem(evidenceEntities);
        item.setMonitoringPhotoItem(daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_monitoring)).list());
        item.setCameraPhotoItem(daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_camera)).list());
        List<WitnessEntity> witnessEntities = daoSession.queryBuilder(WitnessEntity.class).where(WitnessEntityDao.Properties.CrimeId.eq(item.getId())).list();
        for(WitnessEntity entity : witnessEntities) {
            List<Photo> photos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()), PhotoDao.Properties.ParentId.eq(entity.getId()), PhotoDao.Properties.State.eq(Constants.photoState_witness)).list();
            if(photos.size() > 0) {
                entity.setPhotoId(photos.get(photos.size() - 1).getId());
                entity.setPhoto(photos.get(photos.size() - 1));
            }
        }
        item.setWitnessItem(witnessEntities);
        List<GoodEntity> goodEntities = daoSession.queryBuilder(GoodEntity.class).where(GoodEntityDao.Properties.CrimeId.eq(item.getId())).list();
        for(GoodEntity entity : goodEntities) {
            List<Photo> photos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.ParentId.eq(entity.getId()),PhotoDao.Properties.State.eq(Constants.photoState_extract)).list();
            entity.setPhotos(photos);
        }
        item.setGoodEntities(goodEntities);
        item.setWifiInfos(daoSession.queryBuilder(SceneWifiInfo.class).where(SceneWifiInfoDao.Properties.CrimeId.eq(item.getId())).list());
        item.setKctbasestationdataBeans(daoSession.queryBuilder(KCTBASESTATIONDATABean.class).where(KCTBASESTATIONDATABeanDao.Properties.CrimeId.eq(item.getId())).list());
        return item;
    }

    public static CrimeItem selectCrimeByIdWithOutFace(String id) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        CrimeItem item = ServerApplication.getDaoSession().getCrimeItemDao().load(id);
        List<ContactsEntity> contactsEntities = daoSession.queryBuilder(ContactsEntity.class).where(ContactsEntityDao.Properties.CrimeId.eq(item.getId())).list();
        for(ContactsEntity entity : contactsEntities) {
            List<Photo> list = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.ParentId.eq(entity.getId())).list();
            entity.setPhotos(list);
        }
        item.setReleatedPeopleItem(contactsEntities);
        item.setLostItem(daoSession.queryBuilder(ItemEntity.class).where(ItemEntityDao.Properties.CrimeId.eq(item.getId())).list());
        item.setCrimeToolItem(daoSession.queryBuilder(ToolEntity.class).where(ToolEntityDao.Properties.CrimeId.eq(item.getId())).list());
        List<Photo> positionPics = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_positionPic)).list();
        item.setPositionItem(positionPics);
        List<Photo> flatPhotos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_flat)).list();
        item.setFlatItem(flatPhotos);
        List<Photo> dwgPhotos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_flat_dwg)).list();
        item.setDwgItem(dwgPhotos);
        List<Photo> positionPhotos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_positionPhoto)).list();
        item.setPositionPhotoItem(positionPhotos);
        List<Photo> overViewPhotos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_overview)).list();
        item.setOverviewPhotoItem(overViewPhotos);
        List<Photo> importantPhotos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_important)).list();
        item.setImportantPhotoItem(importantPhotos);
        List<Photo> detailPhotos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()), PhotoDao.Properties.State.eq(Constants.photoState_detail)).list();
        item.setDetailPhotoItem(detailPhotos);
        List<EvidenceEntity> evidenceEntities = daoSession.queryBuilder(EvidenceEntity.class)
                .where(EvidenceEntityDao.Properties.CrimeId.eq(item.getId()),
                        EvidenceEntityDao.Properties.EvidenceCategory.notEq("人像"))
                .list();
        for(EvidenceEntity entity : evidenceEntities) {
            List<Photo> photos = daoSession.queryBuilder(Photo.class)
                    .where(PhotoDao.Properties.CrimeId.eq(item.getId()),
                            PhotoDao.Properties.ParentId.eq(entity.getId()),
                            PhotoDao.Properties.State.eq(Constants.photoState_evidence)).list();
            if(photos.size() > 0) {
                entity.setPhotoId(photos.get(photos.size() - 1).getId());
                entity.setPhoto(photos.get(photos.size() - 1));
            }
        }
        item.setEvidenceItem(evidenceEntities);
        item.setMonitoringPhotoItem(daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_monitoring)).list());
        item.setCameraPhotoItem(daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.State.eq(Constants.photoState_camera)).list());
        List<WitnessEntity> witnessEntities = daoSession.queryBuilder(WitnessEntity.class).where(WitnessEntityDao.Properties.CrimeId.eq(item.getId())).list();
        for(WitnessEntity entity : witnessEntities) {
            List<Photo> photos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()), PhotoDao.Properties.ParentId.eq(entity.getId()), PhotoDao.Properties.State.eq(Constants.photoState_witness)).list();
            if(photos.size() > 0) {
                entity.setPhotoId(photos.get(photos.size() - 1).getId());
                entity.setPhoto(photos.get(photos.size() - 1));
            }
        }
        item.setWitnessItem(witnessEntities);
        List<GoodEntity> goodEntities = daoSession.queryBuilder(GoodEntity.class).where(GoodEntityDao.Properties.CrimeId.eq(item.getId())).list();
        for(GoodEntity entity : goodEntities) {
            List<Photo> photos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()),PhotoDao.Properties.ParentId.eq(entity.getId()),PhotoDao.Properties.State.eq(Constants.photoState_extract)).list();
            entity.setPhotos(photos);
        }
        item.setGoodEntities(goodEntities);
        item.setWifiInfos(daoSession.queryBuilder(SceneWifiInfo.class).where(SceneWifiInfoDao.Properties.CrimeId.eq(item.getId())).list());
        item.setKctbasestationdataBeans(daoSession.queryBuilder(KCTBASESTATIONDATABean.class).where(KCTBASESTATIONDATABeanDao.Properties.CrimeId.eq(item.getId())).list());
        return item;
    }

    /**
     * 判断现场是否上传
     * @param id
     * @return true:已上传 false:未上传
     */
    public static boolean checkCrimeUpload(String id) {
        CrimeItem item = ServerApplication.getDaoSession().getCrimeItemDao().load(id);
        if(item != null && item.getIsUpload() != null && item.getIsUpload().equals("upload")) {
            return true;
        }
        return false;
    }

    public static void delete(CrimeItem entity) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        daoSession.getContactsEntityDao().deleteInTx(daoSession.getContactsEntityDao().queryBuilder().where(ContactsEntityDao.Properties.CrimeId.eq(entity.getId())).list());
        daoSession.getItemEntityDao().deleteInTx(daoSession.getItemEntityDao().queryBuilder().where(ItemEntityDao.Properties.CrimeId.eq(entity.getId())).list());
        daoSession.getToolEntityDao().deleteInTx(daoSession.getToolEntityDao().queryBuilder().where(ToolEntityDao.Properties.CrimeId.eq(entity.getId())).list());
        daoSession.getEvidenceEntityDao().deleteInTx(daoSession.getEvidenceEntityDao().queryBuilder().where(EvidenceEntityDao.Properties.CrimeId.eq(entity.getId())).list());
        daoSession.getWitnessEntityDao().deleteInTx(daoSession.getWitnessEntityDao().queryBuilder().where(WitnessEntityDao.Properties.CrimeId.eq(entity.getId())).list());
        daoSession.getSceneWifiInfoDao().deleteInTx(daoSession.getSceneWifiInfoDao().queryBuilder().where(SceneWifiInfoDao.Properties.CrimeId.eq(entity.getId())).list());
        daoSession.getGoodEntityDao().deleteInTx(daoSession.getGoodEntityDao().queryBuilder().where(GoodEntityDao.Properties.CrimeId.eq(entity.getId())).list());
        daoSession.getKCTBASESTATIONDATABeanDao().deleteInTx(daoSession.getKCTBASESTATIONDATABeanDao().queryBuilder().where(KCTBASESTATIONDATABeanDao.Properties.CrimeId.eq(entity.getId())).list());
        List<Photo> list = daoSession.getPhotoDao().queryBuilder().where(PhotoDao.Properties.CrimeId.eq(entity.getId())).list();
        for(Photo item : list) {
            FileUtils.deleteFile(item.getPath());
        }
        daoSession.getPhotoDao().deleteInTx(list);
        daoSession.delete(entity);
        //删除比对数据
        List<CompareEntity> compareEntities = daoSession.getCompareEntityDao().queryBuilder().where(CompareEntityDao.Properties.CrimeId.eq(entity.getId())).list();
        daoSession.getCompareEntityDao().deleteInTx(compareEntities);
        List<ComparePhoto> comparePhotos = daoSession.getComparePhotoDao().queryBuilder().where(ComparePhotoDao.Properties.SceneId.eq(entity.getId())).list();
        daoSession.getComparePhotoDao().deleteInTx(comparePhotos);
    }

    public static String selectSysDictValue(String key,String parentKey) {
        return ServerApplication.getDaoSession().getSysDictDao().queryBuilder().where(sysDictDao.Properties.DictKey.eq(key),sysDictDao.Properties.ParentKey.eq(parentKey)).unique().getDictValue();
    }

    public static void insertPhoto(String photoId, String crimeId, String state, String path) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        Photo photo = new Photo();
        photo.setId(photoId);
        photo.setCrimeId(crimeId);
        photo.setState(state);
        photo.setPath(path);
        daoSession.getPhotoDao().insertOrReplace(photo);
    }

    public static void insertPhoto(Photo photo) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        daoSession.clear();
        daoSession.getPhotoDao().insertOrReplace(photo);
    }

    public static void deletePhoto(String photoId) {
        ServerApplication.getDaoSession().getPhotoDao().deleteByKey(photoId);
    }

    public static void uploadCrimes(List<CrimeItem> crimeItems) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        for(CrimeItem item : crimeItems) {
            CrimeItem load = daoSession.getCrimeItemDao().load(item.getId());
            if(load != null) {
                load.setIsUpload("upload");
                daoSession.getCrimeItemDao().update(load);
            }
        }
    }

    public static void uploadCrime(CrimeItem item) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        CrimeItem load = daoSession.getCrimeItemDao().load(item.getId());
        if(load != null) {
            load.setIsUpload("upload");
            daoSession.getCrimeItemDao().update(load);
        }
    }

    public static void updateKct(List<KCTBASESTATIONDATABean> kctbasestationdataBeans,String crimeId) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        List<KCTBASESTATIONDATABean> list = daoSession.getKCTBASESTATIONDATABeanDao().queryBuilder().where(KCTBASESTATIONDATABeanDao.Properties.CrimeId.eq(crimeId)).list();
        daoSession.getKCTBASESTATIONDATABeanDao().deleteInTx(list);
        daoSession.getKCTBASESTATIONDATABeanDao().insertInTx(kctbasestationdataBeans);
    }

    public static List<KCTBASESTATIONDATABean> selectKctList(String crimeId) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        return daoSession.getKCTBASESTATIONDATABeanDao().queryBuilder().where(KCTBASESTATIONDATABeanDao.Properties.CrimeId.eq(crimeId)).list();
    }

    public static List<Photo> selectPhotosByUploadFail(String crimeId) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        daoSession.clear();
        return daoSession.getPhotoDao().queryBuilder()
                .where(PhotoDao.Properties.CrimeId.eq(crimeId),PhotoDao.Properties.IsUpload.notEq(Constants.UPLOAD_SUCCESS))
                .list();
    }

    public static void updatePhoto(Photo photo) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        daoSession.getPhotoDao().update(photo);
    }

    public static Photo selectPhotoById(String photoId) {
        return ServerApplication.getDaoSession().getPhotoDao().load(photoId);
    }

    public static void insertEvidence(EvidenceEntity entity) {
        ServerApplication.getDaoSession().getEvidenceEntityDao().insertOrReplace(entity);
    }

    public static Photo selectPhotoByParentId(String id, String crimeId) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        List<Photo> photos = daoSession.getPhotoDao().queryBuilder().where(PhotoDao.Properties.ParentId.eq(id), PhotoDao.Properties.CrimeId.eq(crimeId)).list();
        if(photos.size() > 0) {
            return photos.get(photos.size() -1);
        }
        return null;
    }

    public static CrimeItem checkCrime(String crimeId) {
        return ServerApplication.getDaoSession().getCrimeItemDao().load(crimeId);
    }

    public static List<Photo> selectVisitFingerByFingerNo(Photo photo ,String fingerNo) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        return daoSession.getPhotoDao().queryBuilder().where(PhotoDao.Properties.CrimeId.eq(photo.getCrimeId()), PhotoDao.Properties.State.eq(photo.getState()), PhotoDao.Properties.Rev1.eq(fingerNo)).list();
    }

    public static void deletePhotos(List<Photo> photos) {
        ServerApplication.getDaoSession().getPhotoDao().deleteInTx(photos);
    }

    public static EvidenceEntity selectEvidenceById(String evidenceId) {
        return ServerApplication.getDaoSession().getEvidenceEntityDao().load(evidenceId);
    }

    public static void deleteEvidenceById(EvidenceEntity entity) {
        ServerApplication.getDaoSession().getEvidenceEntityDao().delete(entity);
    }

    public static List<Photo> selectPhotoByType(String crimeId, String state) {
        return ServerApplication.getDaoSession().getPhotoDao().queryBuilder()
                .where(PhotoDao.Properties.CrimeId.eq(crimeId), PhotoDao.Properties.State.eq(state)).list();
    }
}
