package com.liany.csiserverapp.dao.database.greenDao.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.liany.csiserverapp.diagnose.CompareEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "compare".
*/
public class CompareEntityDao extends AbstractDao<CompareEntity, String> {

    public static final String TABLENAME = "compare";

    /**
     * Properties of entity CompareEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, String.class, "id", true, "ID");
        public final static Property CrimeId = new Property(1, String.class, "crimeId", false, "CRIME_ID");
        public final static Property CreateDate = new Property(2, long.class, "createDate", false, "CREATE_DATE");
        public final static Property UpdateDate = new Property(3, long.class, "updateDate", false, "UPDATE_DATE");
        public final static Property CreateName = new Property(4, String.class, "createName", false, "CREATE_NAME");
        public final static Property UpdateName = new Property(5, String.class, "updateName", false, "UPDATE_NAME");
        public final static Property TaskNo = new Property(6, String.class, "taskNo", false, "TASK_NO");
        public final static Property Rev1 = new Property(7, String.class, "rev1", false, "REV1");
        public final static Property Rev2 = new Property(8, String.class, "rev2", false, "REV2");
        public final static Property Rev3 = new Property(9, String.class, "rev3", false, "REV3");
    }

    private DaoSession daoSession;


    public CompareEntityDao(DaoConfig config) {
        super(config);
    }
    
    public CompareEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"compare\" (" + //
                "\"ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: id
                "\"CRIME_ID\" TEXT," + // 1: crimeId
                "\"CREATE_DATE\" INTEGER NOT NULL ," + // 2: createDate
                "\"UPDATE_DATE\" INTEGER NOT NULL ," + // 3: updateDate
                "\"CREATE_NAME\" TEXT," + // 4: createName
                "\"UPDATE_NAME\" TEXT," + // 5: updateName
                "\"TASK_NO\" TEXT," + // 6: taskNo
                "\"REV1\" TEXT," + // 7: rev1
                "\"REV2\" TEXT," + // 8: rev2
                "\"REV3\" TEXT);"); // 9: rev3
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"compare\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CompareEntity entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String crimeId = entity.getCrimeId();
        if (crimeId != null) {
            stmt.bindString(2, crimeId);
        }
        stmt.bindLong(3, entity.getCreateDate());
        stmt.bindLong(4, entity.getUpdateDate());
 
        String createName = entity.getCreateName();
        if (createName != null) {
            stmt.bindString(5, createName);
        }
 
        String updateName = entity.getUpdateName();
        if (updateName != null) {
            stmt.bindString(6, updateName);
        }
 
        String taskNo = entity.getTaskNo();
        if (taskNo != null) {
            stmt.bindString(7, taskNo);
        }
 
        String rev1 = entity.getRev1();
        if (rev1 != null) {
            stmt.bindString(8, rev1);
        }
 
        String rev2 = entity.getRev2();
        if (rev2 != null) {
            stmt.bindString(9, rev2);
        }
 
        String rev3 = entity.getRev3();
        if (rev3 != null) {
            stmt.bindString(10, rev3);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CompareEntity entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String crimeId = entity.getCrimeId();
        if (crimeId != null) {
            stmt.bindString(2, crimeId);
        }
        stmt.bindLong(3, entity.getCreateDate());
        stmt.bindLong(4, entity.getUpdateDate());
 
        String createName = entity.getCreateName();
        if (createName != null) {
            stmt.bindString(5, createName);
        }
 
        String updateName = entity.getUpdateName();
        if (updateName != null) {
            stmt.bindString(6, updateName);
        }
 
        String taskNo = entity.getTaskNo();
        if (taskNo != null) {
            stmt.bindString(7, taskNo);
        }
 
        String rev1 = entity.getRev1();
        if (rev1 != null) {
            stmt.bindString(8, rev1);
        }
 
        String rev2 = entity.getRev2();
        if (rev2 != null) {
            stmt.bindString(9, rev2);
        }
 
        String rev3 = entity.getRev3();
        if (rev3 != null) {
            stmt.bindString(10, rev3);
        }
    }

    @Override
    protected final void attachEntity(CompareEntity entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public CompareEntity readEntity(Cursor cursor, int offset) {
        CompareEntity entity = new CompareEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // crimeId
            cursor.getLong(offset + 2), // createDate
            cursor.getLong(offset + 3), // updateDate
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // createName
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // updateName
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // taskNo
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // rev1
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // rev2
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // rev3
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CompareEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setCrimeId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCreateDate(cursor.getLong(offset + 2));
        entity.setUpdateDate(cursor.getLong(offset + 3));
        entity.setCreateName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setUpdateName(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setTaskNo(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setRev1(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setRev2(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setRev3(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    @Override
    protected final String updateKeyAfterInsert(CompareEntity entity, long rowId) {
        return entity.getId();
    }
    
    @Override
    public String getKey(CompareEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CompareEntity entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}