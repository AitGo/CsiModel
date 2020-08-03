package com.liany.csiserverapp.diagnose;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import com.liany.csiserverapp.dao.database.greenDao.db.DaoSession;
import com.liany.csiserverapp.dao.database.greenDao.db.ComparePhotoDao;
import com.liany.csiserverapp.dao.database.greenDao.db.CompareEntityDao;

/**
 * @创建者 ly
 * @创建时间 2019/11/12
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
@Entity(nameInDb = "compare")
public class CompareEntity {

    @Id
    private String id;
    private String crimeId;
    @ToMany(referencedJoinProperty = "id")
    private List<ComparePhoto> comparePhotos;

    private long createDate;
    private long updateDate;
    private String createName;
    private String updateName;
    private String taskNo;
    private String rev1;
    private String rev2; //比对类型  0-事主，1-现场指纹，2-足迹
    private String rev3;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1691717206)
    private transient CompareEntityDao myDao;
    @Generated(hash = 902004724)
    public CompareEntity(String id, String crimeId, long createDate,
            long updateDate, String createName, String updateName, String taskNo,
            String rev1, String rev2, String rev3) {
        this.id = id;
        this.crimeId = crimeId;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createName = createName;
        this.updateName = updateName;
        this.taskNo = taskNo;
        this.rev1 = rev1;
        this.rev2 = rev2;
        this.rev3 = rev3;
    }
    @Generated(hash = 1161188677)
    public CompareEntity() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCrimeId() {
        return this.crimeId;
    }
    public void setCrimeId(String crimeId) {
        this.crimeId = crimeId;
    }
    public long getCreateDate() {
        return this.createDate;
    }
    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }
    public long getUpdateDate() {
        return this.updateDate;
    }
    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }
    public String getCreateName() {
        return this.createName;
    }
    public void setCreateName(String createName) {
        this.createName = createName;
    }
    public String getUpdateName() {
        return this.updateName;
    }
    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }
    public String getTaskNo() {
        return this.taskNo;
    }
    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }
    public String getRev1() {
        return this.rev1;
    }
    public void setRev1(String rev1) {
        this.rev1 = rev1;
    }
    public String getRev2() {
        return this.rev2;
    }
    public void setRev2(String rev2) {
        this.rev2 = rev2;
    }
    public String getRev3() {
        return this.rev3;
    }
    public void setRev3(String rev3) {
        this.rev3 = rev3;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 754569772)
    public List<ComparePhoto> getComparePhotos() {
        if (comparePhotos == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ComparePhotoDao targetDao = daoSession.getComparePhotoDao();
            List<ComparePhoto> comparePhotosNew = targetDao
                    ._queryCompareEntity_ComparePhotos(id);
            synchronized (this) {
                if (comparePhotos == null) {
                    comparePhotos = comparePhotosNew;
                }
            }
        }
        return comparePhotos;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1676240608)
    public synchronized void resetComparePhotos() {
        comparePhotos = null;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1034548588)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCompareEntityDao() : null;
    }

}
