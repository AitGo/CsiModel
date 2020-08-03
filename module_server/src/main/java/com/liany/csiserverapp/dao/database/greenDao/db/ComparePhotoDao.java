package com.liany.csiserverapp.dao.database.greenDao.db;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import com.liany.csiserverapp.diagnose.ComparePhoto;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "comparePhoto".
*/
public class ComparePhotoDao extends AbstractDao<ComparePhoto, String> {

    public static final String TABLENAME = "comparePhoto";

    /**
     * Properties of entity ComparePhoto.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, String.class, "id", true, "ID");
        public final static Property CompareId = new Property(1, String.class, "compareId", false, "COMPARE_ID");
        public final static Property SceneId = new Property(2, String.class, "sceneId", false, "SCENE_ID");
        public final static Property PhotoId = new Property(3, String.class, "photoId", false, "PHOTO_ID");
        public final static Property TaskId = new Property(4, String.class, "taskId", false, "TASK_ID");
        public final static Property PhotoPath = new Property(5, String.class, "photoPath", false, "PHOTO_PATH");
        public final static Property ServicePath = new Property(6, String.class, "servicePath", false, "SERVICE_PATH");
        public final static Property PhotoType = new Property(7, String.class, "photoType", false, "PHOTO_TYPE");
        public final static Property Status = new Property(8, String.class, "status", false, "STATUS");
        public final static Property CompareInfo = new Property(9, String.class, "compareInfo", false, "COMPARE_INFO");
        public final static Property FingerNo = new Property(10, int.class, "fingerNo", false, "FINGER_NO");
        public final static Property FingerTaskId = new Property(11, String.class, "fingerTaskId", false, "FINGER_TASK_ID");
        public final static Property CreateDate = new Property(12, String.class, "createDate", false, "CREATE_DATE");
        public final static Property UpdateDate = new Property(13, String.class, "updateDate", false, "UPDATE_DATE");
        public final static Property CreateName = new Property(14, String.class, "createName", false, "CREATE_NAME");
        public final static Property UpdateName = new Property(15, String.class, "updateName", false, "UPDATE_NAME");
        public final static Property EvidenceName = new Property(16, String.class, "evidenceName", false, "EVIDENCE_NAME");
        public final static Property Rev1 = new Property(17, String.class, "rev1", false, "REV1");
        public final static Property Rev2 = new Property(18, String.class, "rev2", false, "REV2");
        public final static Property Rev3 = new Property(19, String.class, "rev3", false, "REV3");
    }

    private Query<ComparePhoto> compareEntity_ComparePhotosQuery;

    public ComparePhotoDao(DaoConfig config) {
        super(config);
    }
    
    public ComparePhotoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"comparePhoto\" (" + //
                "\"ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: id
                "\"COMPARE_ID\" TEXT," + // 1: compareId
                "\"SCENE_ID\" TEXT," + // 2: sceneId
                "\"PHOTO_ID\" TEXT," + // 3: photoId
                "\"TASK_ID\" TEXT," + // 4: taskId
                "\"PHOTO_PATH\" TEXT," + // 5: photoPath
                "\"SERVICE_PATH\" TEXT," + // 6: servicePath
                "\"PHOTO_TYPE\" TEXT," + // 7: photoType
                "\"STATUS\" TEXT," + // 8: status
                "\"COMPARE_INFO\" TEXT," + // 9: compareInfo
                "\"FINGER_NO\" INTEGER NOT NULL ," + // 10: fingerNo
                "\"FINGER_TASK_ID\" TEXT," + // 11: fingerTaskId
                "\"CREATE_DATE\" TEXT," + // 12: createDate
                "\"UPDATE_DATE\" TEXT," + // 13: updateDate
                "\"CREATE_NAME\" TEXT," + // 14: createName
                "\"UPDATE_NAME\" TEXT," + // 15: updateName
                "\"EVIDENCE_NAME\" TEXT," + // 16: evidenceName
                "\"REV1\" TEXT," + // 17: rev1
                "\"REV2\" TEXT," + // 18: rev2
                "\"REV3\" TEXT);"); // 19: rev3
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"comparePhoto\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ComparePhoto entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String compareId = entity.getCompareId();
        if (compareId != null) {
            stmt.bindString(2, compareId);
        }
 
        String sceneId = entity.getSceneId();
        if (sceneId != null) {
            stmt.bindString(3, sceneId);
        }
 
        String photoId = entity.getPhotoId();
        if (photoId != null) {
            stmt.bindString(4, photoId);
        }
 
        String taskId = entity.getTaskId();
        if (taskId != null) {
            stmt.bindString(5, taskId);
        }
 
        String photoPath = entity.getPhotoPath();
        if (photoPath != null) {
            stmt.bindString(6, photoPath);
        }
 
        String servicePath = entity.getServicePath();
        if (servicePath != null) {
            stmt.bindString(7, servicePath);
        }
 
        String photoType = entity.getPhotoType();
        if (photoType != null) {
            stmt.bindString(8, photoType);
        }
 
        String status = entity.getStatus();
        if (status != null) {
            stmt.bindString(9, status);
        }
 
        String compareInfo = entity.getCompareInfo();
        if (compareInfo != null) {
            stmt.bindString(10, compareInfo);
        }
        stmt.bindLong(11, entity.getFingerNo());
 
        String fingerTaskId = entity.getFingerTaskId();
        if (fingerTaskId != null) {
            stmt.bindString(12, fingerTaskId);
        }
 
        String createDate = entity.getCreateDate();
        if (createDate != null) {
            stmt.bindString(13, createDate);
        }
 
        String updateDate = entity.getUpdateDate();
        if (updateDate != null) {
            stmt.bindString(14, updateDate);
        }
 
        String createName = entity.getCreateName();
        if (createName != null) {
            stmt.bindString(15, createName);
        }
 
        String updateName = entity.getUpdateName();
        if (updateName != null) {
            stmt.bindString(16, updateName);
        }
 
        String evidenceName = entity.getEvidenceName();
        if (evidenceName != null) {
            stmt.bindString(17, evidenceName);
        }
 
        String rev1 = entity.getRev1();
        if (rev1 != null) {
            stmt.bindString(18, rev1);
        }
 
        String rev2 = entity.getRev2();
        if (rev2 != null) {
            stmt.bindString(19, rev2);
        }
 
        String rev3 = entity.getRev3();
        if (rev3 != null) {
            stmt.bindString(20, rev3);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ComparePhoto entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String compareId = entity.getCompareId();
        if (compareId != null) {
            stmt.bindString(2, compareId);
        }
 
        String sceneId = entity.getSceneId();
        if (sceneId != null) {
            stmt.bindString(3, sceneId);
        }
 
        String photoId = entity.getPhotoId();
        if (photoId != null) {
            stmt.bindString(4, photoId);
        }
 
        String taskId = entity.getTaskId();
        if (taskId != null) {
            stmt.bindString(5, taskId);
        }
 
        String photoPath = entity.getPhotoPath();
        if (photoPath != null) {
            stmt.bindString(6, photoPath);
        }
 
        String servicePath = entity.getServicePath();
        if (servicePath != null) {
            stmt.bindString(7, servicePath);
        }
 
        String photoType = entity.getPhotoType();
        if (photoType != null) {
            stmt.bindString(8, photoType);
        }
 
        String status = entity.getStatus();
        if (status != null) {
            stmt.bindString(9, status);
        }
 
        String compareInfo = entity.getCompareInfo();
        if (compareInfo != null) {
            stmt.bindString(10, compareInfo);
        }
        stmt.bindLong(11, entity.getFingerNo());
 
        String fingerTaskId = entity.getFingerTaskId();
        if (fingerTaskId != null) {
            stmt.bindString(12, fingerTaskId);
        }
 
        String createDate = entity.getCreateDate();
        if (createDate != null) {
            stmt.bindString(13, createDate);
        }
 
        String updateDate = entity.getUpdateDate();
        if (updateDate != null) {
            stmt.bindString(14, updateDate);
        }
 
        String createName = entity.getCreateName();
        if (createName != null) {
            stmt.bindString(15, createName);
        }
 
        String updateName = entity.getUpdateName();
        if (updateName != null) {
            stmt.bindString(16, updateName);
        }
 
        String evidenceName = entity.getEvidenceName();
        if (evidenceName != null) {
            stmt.bindString(17, evidenceName);
        }
 
        String rev1 = entity.getRev1();
        if (rev1 != null) {
            stmt.bindString(18, rev1);
        }
 
        String rev2 = entity.getRev2();
        if (rev2 != null) {
            stmt.bindString(19, rev2);
        }
 
        String rev3 = entity.getRev3();
        if (rev3 != null) {
            stmt.bindString(20, rev3);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public ComparePhoto readEntity(Cursor cursor, int offset) {
        ComparePhoto entity = new ComparePhoto( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // compareId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // sceneId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // photoId
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // taskId
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // photoPath
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // servicePath
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // photoType
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // status
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // compareInfo
            cursor.getInt(offset + 10), // fingerNo
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // fingerTaskId
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // createDate
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // updateDate
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // createName
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // updateName
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // evidenceName
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // rev1
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // rev2
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19) // rev3
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ComparePhoto entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setCompareId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setSceneId(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPhotoId(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTaskId(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setPhotoPath(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setServicePath(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setPhotoType(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setStatus(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setCompareInfo(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setFingerNo(cursor.getInt(offset + 10));
        entity.setFingerTaskId(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setCreateDate(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setUpdateDate(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setCreateName(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setUpdateName(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setEvidenceName(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setRev1(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setRev2(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setRev3(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
     }
    
    @Override
    protected final String updateKeyAfterInsert(ComparePhoto entity, long rowId) {
        return entity.getId();
    }
    
    @Override
    public String getKey(ComparePhoto entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ComparePhoto entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "comparePhotos" to-many relationship of CompareEntity. */
    public List<ComparePhoto> _queryCompareEntity_ComparePhotos(String id) {
        synchronized (this) {
            if (compareEntity_ComparePhotosQuery == null) {
                QueryBuilder<ComparePhoto> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.Id.eq(null));
                compareEntity_ComparePhotosQuery = queryBuilder.build();
            }
        }
        Query<ComparePhoto> query = compareEntity_ComparePhotosQuery.forCurrentThread();
        query.setParameter(0, id);
        return query.list();
    }

}
