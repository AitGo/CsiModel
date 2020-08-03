package com.liany.csiserverapp.andServer.model;

import android.database.Cursor;

import com.liany.csiserverapp.dao.database.greenDao.db.ComparePhotoDao;
import com.liany.csiserverapp.dao.database.greenDao.db.ContactsEntityDao;
import com.liany.csiserverapp.dao.database.greenDao.db.DaoSession;
import com.liany.csiserverapp.dao.database.greenDao.db.EvidenceEntityDao;
import com.liany.csiserverapp.dao.database.greenDao.db.PhotoDao;
import com.liany.csiserverapp.debug.ServerApplication;
import com.liany.csiserverapp.diagnose.CompareEntity;
import com.liany.csiserverapp.diagnose.ComparePhoto;
import com.liany.csiserverapp.diagnose.ContactsEntity;
import com.liany.csiserverapp.diagnose.CrimeItem;
import com.liany.csiserverapp.diagnose.EvidenceEntity;
import com.liany.csiserverapp.diagnose.Photo;
import com.liany.csiserverapp.utils.StringUtils;
import com.liany.csiserverapp.base.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/3/17
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class CompareDB {

    public static List<CrimeItem> getContactsCrimeList(String userName) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        List<CrimeItem> crimeItems = new ArrayList<>();
        String SQL_DISTINCT_ENAME = "SELECT DISTINCT "+ ComparePhotoDao.Properties.SceneId.columnName
                + " FROM " + ComparePhotoDao.TABLENAME
                + " WHERE " + ComparePhotoDao.Properties.PhotoType.columnName + " = '" + Constants.TYPE_FINGER_PEOPLE + "'"
                + " AND " + ComparePhotoDao.Properties.CreateName.columnName + " = '" + userName + "'"
                + " order by " + ComparePhotoDao.Properties.CreateDate.columnName + " desc";
        //查询comparePhoto
        ArrayList<String> result = new ArrayList<String>();
        Cursor c = daoSession.getDatabase().rawQuery(SQL_DISTINCT_ENAME, null);
        try{
            if (c.moveToFirst()) {
                do {
                    result.add(c.getString(0));
                } while (c.moveToNext());
            }
        } finally {
            c.close();
        }
        for(String crimeId : result) {
            CrimeItem load = daoSession.getCrimeItemDao().load(crimeId);
            if(load != null) {
                List<ContactsEntity> contactsEntities = daoSession.getContactsEntityDao().queryBuilder().where(ContactsEntityDao.Properties.CrimeId.eq(load.getId())).list();
                for(ContactsEntity entity : contactsEntities) {
                    List<Photo> list = daoSession.getPhotoDao().queryBuilder().where(PhotoDao.Properties.CrimeId.eq(crimeId), PhotoDao.Properties.ParentId.eq(entity.getId()), PhotoDao.Properties.State.eq(Constants.photoState_visit_people)).list();
                    for(Photo photo : list) {
                        photo.setRev4("");
                    }
                    entity.setPhotos(list);
                }
                load.setReleatedPeopleItem(contactsEntities);
                List<EvidenceEntity> evidenceEntities = daoSession.getEvidenceEntityDao().queryBuilder().where(EvidenceEntityDao.Properties.CrimeId.eq(crimeId)).list();
                for(EvidenceEntity entity : evidenceEntities) {
                    List<Photo> photos = daoSession.getPhotoDao().queryBuilder().where(PhotoDao.Properties.CrimeId.eq(crimeId), PhotoDao.Properties.ParentId.eq(entity.getId()), PhotoDao.Properties.State.eq(Constants.photoState_evidence)).list();
                    if(photos.size() > 0) {
                        Photo photo = photos.get(photos.size() - 1);
                        photo.setRev4("");
                        entity.setPhoto(photo);
                    }
                }
                load.setEvidenceItem(evidenceEntities);
                crimeItems.add(load);
            }
        }
        return crimeItems;
    }

    public static List<CrimeItem> getEvidenceCrimeList(String userName,int type) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        String SQL_DISTINCT_ENAME = "SELECT DISTINCT "+ ComparePhotoDao.Properties.SceneId.columnName
                + " FROM " + ComparePhotoDao.TABLENAME
                + " WHERE " + ComparePhotoDao.Properties.PhotoType.columnName + " = '" + type + "'"
                + " AND " + ComparePhotoDao.Properties.CreateName.columnName + " = '" + userName + "'"
                + " order by " + ComparePhotoDao.Properties.CreateDate.columnName + " desc";
        //查询comparePhoto
        ArrayList<String> result = new ArrayList<String>();
        Cursor c = daoSession.getDatabase().rawQuery(SQL_DISTINCT_ENAME, null);
        try{
            if (c.moveToFirst()) {
                do {
                    result.add(c.getString(0));
                } while (c.moveToNext());
            }
        } finally {
            c.close();
        }
        List<CrimeItem> crimeItems = new ArrayList<>();
        for(String crimeId : result) {
            CrimeItem item = daoSession.getCrimeItemDao().load(crimeId);
            if(item != null) {
                List<EvidenceEntity> evidenceEntities = daoSession.getEvidenceEntityDao().queryBuilder().where(EvidenceEntityDao.Properties.CrimeId.eq(crimeId)).list();
                for(EvidenceEntity entity : evidenceEntities) {
                    List<Photo> photos = daoSession.getPhotoDao().queryBuilder().where(PhotoDao.Properties.CrimeId.eq(crimeId), PhotoDao.Properties.ParentId.eq(entity.getId()), PhotoDao.Properties.State.eq(Constants.photoState_evidence)).list();
                    if(photos.size() > 0) {
                        Photo photo = photos.get(photos.size() - 1);
                        photo.setRev4("");
                        entity.setPhoto(photo);
                    }
                }
                item.setEvidenceItem(evidenceEntities);
                crimeItems.add(item);
            }
        }
        return crimeItems;
    }

    public static List<Photo> selectPhotoByCrimeId(String crimeId,String type) {
        return ServerApplication.getDaoSession().getPhotoDao().queryBuilder().where(PhotoDao.Properties.CrimeId.eq(crimeId),PhotoDao.Properties.State.eq(type)).list();
    }

    public static Photo selectPhotoByParentId(String id, String crimeId, String type) {
        return ServerApplication.getDaoSession().getPhotoDao().queryBuilder().where(PhotoDao.Properties.CrimeId.eq(crimeId), PhotoDao.Properties.ParentId.eq(id),PhotoDao.Properties.State.eq(type)).unique();
    }

    public static List<Photo> selectPhotoListByParentId(String id, String crimeId, String type) {
        return ServerApplication.getDaoSession().getPhotoDao().queryBuilder().where(PhotoDao.Properties.CrimeId.eq(crimeId), PhotoDao.Properties.ParentId.eq(id),PhotoDao.Properties.State.eq(type)).list();
    }

    public static List<ComparePhoto> selectComparePhotoByPhotoId(Photo photo) {
        return ServerApplication.getDaoSession().getComparePhotoDao().queryBuilder().where(ComparePhotoDao.Properties.PhotoId.eq(photo.getId())).list();
    }

    public static List<ComparePhoto> selectComparePhotoByPhotoId(Photo photo,String type) {
        return ServerApplication.getDaoSession().getComparePhotoDao().queryBuilder().where(ComparePhotoDao.Properties.PhotoId.eq(photo.getId()),ComparePhotoDao.Properties.PhotoType.eq(type)).list();
    }

    public static List<ComparePhoto> selectComparePhotoByPhotoId(Photo photo,String type, String status) {
        return ServerApplication.getDaoSession().getComparePhotoDao().queryBuilder()
                .where(ComparePhotoDao.Properties.PhotoId.eq(photo.getId()),ComparePhotoDao.Properties.PhotoType.eq(type),ComparePhotoDao.Properties.Status.eq(status))
                .list();
    }

    public static void updateCompareEntity(CompareEntity compareEntity) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        if(daoSession.getCompareEntityDao().load(compareEntity.getId()) == null) {
            daoSession.getCompareEntityDao().insert(compareEntity);
        }else {
            daoSession.getCompareEntityDao().update(compareEntity);
        }
    }

    public static void updateComparePhotoList(List<ComparePhoto> comparePhotos) {
        ServerApplication.getDaoSession().getComparePhotoDao().insertOrReplaceInTx(comparePhotos);
    }

    public static List<ComparePhoto> selectNoResultComparePhotoByCrimeId(String crimeId,int type) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        List<ComparePhoto> comparePhotos = daoSession.getComparePhotoDao().queryBuilder()
                .where(ComparePhotoDao.Properties.SceneId.eq(crimeId),
                        ComparePhotoDao.Properties.PhotoType.eq(type),
                        ComparePhotoDao.Properties.Status.notEq("7"),
                        ComparePhotoDao.Properties.Status.notEq("8"),
                        ComparePhotoDao.Properties.Status.notEq("107"),
                        ComparePhotoDao.Properties.Status.notEq("177"),
                        ComparePhotoDao.Properties.Status.notEq("187")
                        )
                .list();
        List<ComparePhoto> list = daoSession.getComparePhotoDao().queryBuilder()
                .where(ComparePhotoDao.Properties.SceneId.eq(crimeId),
                        ComparePhotoDao.Properties.PhotoType.eq(type),
                        ComparePhotoDao.Properties.Status.isNull()).list();
        comparePhotos.addAll(list);
        return comparePhotos;
    }

    public static List<ComparePhoto> selectNoResultFaceCompareByCrimeId(String crimeId,int type) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        List<ComparePhoto> comparePhotos = daoSession.getComparePhotoDao().queryBuilder()
                .where(ComparePhotoDao.Properties.SceneId.eq(crimeId),
                        ComparePhotoDao.Properties.PhotoType.eq(type),
                        ComparePhotoDao.Properties.Status.notEq("1"),
                        ComparePhotoDao.Properties.Status.notEq("2")
                )
                .list();
        List<ComparePhoto> list = daoSession.getComparePhotoDao().queryBuilder()
                .where(ComparePhotoDao.Properties.SceneId.eq(crimeId),
                        ComparePhotoDao.Properties.PhotoType.eq(type),
                        ComparePhotoDao.Properties.Status.isNull()).list();
        comparePhotos.addAll(list);
        return comparePhotos;
    }

    public static List<ComparePhoto> selectComparePhotoByCrimeId(String crimeId,int type) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        List<ComparePhoto> comparePhotos = daoSession.getComparePhotoDao().queryBuilder()
                .where(ComparePhotoDao.Properties.SceneId.eq(crimeId),
                        ComparePhotoDao.Properties.PhotoType.eq(type))
                .orderAsc(ComparePhotoDao.Properties.CreateDate)
                .list();
        List<ComparePhoto> result = new ArrayList<>();
        if(type == Constants.TYPE_FINGER_PEOPLE) {
//            Iterator<ComparePhoto> iterator = comparePhotos.iterator();
//            while (iterator.hasNext()) {
//                ComparePhoto photo = iterator.next();
//                if (photo.getEvidenceName().equals("")) {
//                    iterator.remove();//使用迭代器的删除方法删除
//                }
//            }
//            for(ComparePhoto photo : comparePhotos) {
//                if(result.size() == 0) {
//                    result.add(photo);
//                }else {
//                    for(int i = 0; i < result.size(); i++) {
//                        if(result.get(i).getPhotoId().equals(photo.getPhotoId())) {
//                            break;
//                        }else {
//                            if(i == result.size() - 1) {
//                                result.add(photo);
//                            }
//                        }
//                    }
//                }
//            }
            result.addAll(comparePhotos);
//            for(ComparePhoto comparePhoto : result) {
//                List<ComparePhoto> list = daoSession.getComparePhotoDao().queryBuilder()
//                        .where(ComparePhotoDao.Properties.PhotoId.eq(comparePhoto.getPhotoId()), ComparePhotoDao.Properties.Status.eq("107")).list();
//                if(list.size() > 0) {
//                    comparePhoto.setStatus("107");
//                }
//            }
        }else {
            result.addAll(comparePhotos);
        }
        return result;
    }

    public static void updateCompareFingerPhoto(List<ComparePhoto> data,int type) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        for(ComparePhoto photo : data) {
            List<ComparePhoto> list = daoSession.getComparePhotoDao().queryBuilder().where(ComparePhotoDao.Properties.CompareId.eq(photo.getTaskId()),
                    ComparePhotoDao.Properties.SceneId.eq(photo.getSceneId()),
                    ComparePhotoDao.Properties.FingerNo.eq(photo.getFingerNo()),
                    ComparePhotoDao.Properties.PhotoType.eq(type)).list();
            for(ComparePhoto entity : list) {
                entity.setStatus(photo.getStatus());
                entity.setCreateDate(photo.getCreateDate());
                if(type == Constants.TYPE_FINGER_EVIDENCE) {
                    //rev1为比中信息
                    entity.setRev1(photo.getRev1());
                }
                daoSession.getComparePhotoDao().update(entity);
            }
        }
    }

    public static List<ComparePhoto> selectComparePeoplePhoto(Photo photo) {
        return ServerApplication.getDaoSession().getComparePhotoDao().queryBuilder().where(ComparePhotoDao.Properties.Rev1.eq(photo.getId())).list();
    }

    public static void updateCompareFootPhoto(List<ComparePhoto> data) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        for(ComparePhoto photo : data) {
            List<ComparePhoto> entitys = daoSession.getComparePhotoDao().queryBuilder()
                    .where(ComparePhotoDao.Properties.PhotoId.eq(photo.getFingerTaskId()),
                            ComparePhotoDao.Properties.PhotoType.eq(Constants.TYPE_FOOT_EVIDENCE),
                            ComparePhotoDao.Properties.SceneId.eq(photo.getSceneId()))
                    .list();
            for(ComparePhoto entity : entitys) {
                entity.setStatus(photo.getStatus());
                entity.setCreateDate(photo.getCreateDate());
                if(StringUtils.checkString(photo.getRev1())) {
                    //rev1为比中信息
                    entity.setRev1(photo.getRev1());
                }
                daoSession.getComparePhotoDao().update(entity);
            }
        }
    }

    public static void updateCompareFacePhoto(List<ComparePhoto> data) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        for(ComparePhoto photo : data) {
            List<ComparePhoto> entitys = daoSession.getComparePhotoDao().queryBuilder()
                    .where(ComparePhotoDao.Properties.CompareId.eq(photo.getFingerTaskId()),
                            ComparePhotoDao.Properties.PhotoType.eq(Constants.TYPE_FACE_EVIDENCE),
                            ComparePhotoDao.Properties.SceneId.eq(photo.getSceneId()))
                    .list();
            for(ComparePhoto entity : entitys) {
                entity.setStatus(photo.getStatus());
                entity.setCreateDate(photo.getCreateDate());
                if(StringUtils.checkString(photo.getRev1())) {
                    //rev1为比中信息
                    entity.setRev1(photo.getRev1());
                }
                daoSession.getComparePhotoDao().update(entity);
            }
        }
    }

    public static List<ComparePhoto> selectNoResultFootComparePhotoByCrimeId(String crimeId,int type) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        List<ComparePhoto> comparePhotos = daoSession.getComparePhotoDao().queryBuilder()
                .where(ComparePhotoDao.Properties.SceneId.eq(crimeId),
                        ComparePhotoDao.Properties.PhotoType.eq(type),
                        ComparePhotoDao.Properties.Status.notEq("3"),
                        ComparePhotoDao.Properties.Status.notEq("9")
                )
                .list();
        List<ComparePhoto> list = daoSession.getComparePhotoDao().queryBuilder()
                .where(ComparePhotoDao.Properties.SceneId.eq(crimeId),
                        ComparePhotoDao.Properties.PhotoType.eq(type),
                        ComparePhotoDao.Properties.Status.isNull()).list();
        comparePhotos.addAll(list);
        return comparePhotos;
    }

    public static List<ComparePhoto> selectFootComparePhotoByCrimeId(String crimeId, int type) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        return daoSession.getComparePhotoDao().queryBuilder()
                .where(ComparePhotoDao.Properties.SceneId.eq(crimeId),
                        ComparePhotoDao.Properties.PhotoType.eq(type))
                .list();
    }

    public static List<ComparePhoto> selectComparePhotoByUserName(String userName, int type) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        List<ComparePhoto> comparePhotos = daoSession.getComparePhotoDao().queryBuilder()
                .where(ComparePhotoDao.Properties.CreateName.eq(userName),
                        ComparePhotoDao.Properties.PhotoType.eq(type))
                .orderDesc(ComparePhotoDao.Properties.CreateDate)
                .list();
        List<ComparePhoto> result = new ArrayList<>();
        if(type == Constants.TYPE_FINGER_PEOPLE) {
            for(ComparePhoto photo : comparePhotos) {
                if(result.size() == 0) {
                    result.add(photo);
                }else {
                    for(int i = 0; i < result.size(); i++) {
                        if(result.get(i).getPhotoId().equals(photo.getPhotoId())) {
                            break;
                        }else {
                            if(i == result.size() - 1) {
                                result.add(photo);
                            }
                        }
                    }
                }
            }
            for(ComparePhoto comparePhoto : result) {
                List<ComparePhoto> list = daoSession.getComparePhotoDao().queryBuilder()
                        .where(ComparePhotoDao.Properties.PhotoId.eq(comparePhoto.getPhotoId()), ComparePhotoDao.Properties.Status.eq("107")).list();
                if(list.size() > 0) {
                    comparePhoto.setStatus("107");
                }
            }
        }else {
            result.addAll(comparePhotos);
        }
        return result;
    }

    public static List<ComparePhoto> selectNoResultFootComparePhotoByUserName(String userName, int type) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        List<ComparePhoto> comparePhotos = daoSession.getComparePhotoDao().queryBuilder()
                .where(ComparePhotoDao.Properties.CreateName.eq(userName),
                        ComparePhotoDao.Properties.PhotoType.eq(type),
                        ComparePhotoDao.Properties.Status.notEq("3"),
                        ComparePhotoDao.Properties.Status.notEq("9")
                )
                .list();
        List<ComparePhoto> list = daoSession.getComparePhotoDao().queryBuilder()
                .where(ComparePhotoDao.Properties.CreateName.eq(userName),
                        ComparePhotoDao.Properties.PhotoType.eq(type),
                        ComparePhotoDao.Properties.Status.isNull()).list();
        comparePhotos.addAll(list);
        return comparePhotos;
    }

    public static List<ComparePhoto> selectNoResultComparePhotoByUserName(String trueName, int type) {
        DaoSession daoSession = ServerApplication.getDaoSession();
        List<ComparePhoto> comparePhotos = daoSession.getComparePhotoDao().queryBuilder()
                .where(ComparePhotoDao.Properties.CreateName.eq(trueName),
                        ComparePhotoDao.Properties.PhotoType.eq(type),
                        ComparePhotoDao.Properties.Status.notEq("7"),
                        ComparePhotoDao.Properties.Status.notEq("8"),
                        ComparePhotoDao.Properties.Status.notEq("107"),
                        ComparePhotoDao.Properties.Status.notEq("177"),
                        ComparePhotoDao.Properties.Status.notEq("187")
                )
                .list();
        List<ComparePhoto> list = daoSession.getComparePhotoDao().queryBuilder()
                .where(ComparePhotoDao.Properties.CreateName.eq(trueName),
                        ComparePhotoDao.Properties.PhotoType.eq(type),
                        ComparePhotoDao.Properties.Status.isNull()).list();
        comparePhotos.addAll(list);
        return comparePhotos;
    }
}
