package com.liany.csiserverapp.diagnose;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.io.Serializable;
import java.util.List;
import com.liany.csiserverapp.dao.database.greenDao.db.DaoSession;
import com.liany.csiserverapp.dao.database.greenDao.db.PhotoDao;
import com.liany.csiserverapp.dao.database.greenDao.db.GoodEntityDao;

/**
 * @创建者 ly
 * @创建时间 2019/8/16
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
@Entity(nameInDb = "goodEntity")
public class GoodEntity implements Serializable {

    private static final long serialVersionUID = -305401489447341178L;
    @Id
    private String id;
    private String crimeId;
    private String collectedName;//提取人
    private String collectedIds;//提取人id
    private String materialName;//物证名称
    private String collectedPosition;//提取部位
    private String collectedMethod;//提取方法
    private String collectedNum;//提取数量
    private String collectedDate;//提取时间
    private String remark;//备注

    @ToMany(referencedJoinProperty = "parentId")
    private List<Photo> photos;//物证照片
    private String code;//二维码

    private String rev1;
    private String rev2;
    private String rev3;
    private String rev4;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 553792161)
    private transient GoodEntityDao myDao;
    @Generated(hash = 568311313)
    public GoodEntity(String id, String crimeId, String collectedName,
            String collectedIds, String materialName, String collectedPosition,
            String collectedMethod, String collectedNum, String collectedDate,
            String remark, String code, String rev1, String rev2, String rev3,
            String rev4) {
        this.id = id;
        this.crimeId = crimeId;
        this.collectedName = collectedName;
        this.collectedIds = collectedIds;
        this.materialName = materialName;
        this.collectedPosition = collectedPosition;
        this.collectedMethod = collectedMethod;
        this.collectedNum = collectedNum;
        this.collectedDate = collectedDate;
        this.remark = remark;
        this.code = code;
        this.rev1 = rev1;
        this.rev2 = rev2;
        this.rev3 = rev3;
        this.rev4 = rev4;
    }
    @Generated(hash = 1126203598)
    public GoodEntity() {
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
    public String getCollectedName() {
        return this.collectedName;
    }
    public void setCollectedName(String collectedName) {
        this.collectedName = collectedName;
    }
    public String getCollectedIds() {
        return this.collectedIds;
    }
    public void setCollectedIds(String collectedIds) {
        this.collectedIds = collectedIds;
    }
    public String getMaterialName() {
        return this.materialName;
    }
    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }
    public String getCollectedPosition() {
        return this.collectedPosition;
    }
    public void setCollectedPosition(String collectedPosition) {
        this.collectedPosition = collectedPosition;
    }
    public String getCollectedMethod() {
        return this.collectedMethod;
    }
    public void setCollectedMethod(String collectedMethod) {
        this.collectedMethod = collectedMethod;
    }
    public String getCollectedNum() {
        return this.collectedNum;
    }
    public void setCollectedNum(String collectedNum) {
        this.collectedNum = collectedNum;
    }
    public String getCollectedDate() {
        return this.collectedDate;
    }
    public void setCollectedDate(String collectedDate) {
        this.collectedDate = collectedDate;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getCode() {
        return this.code;
    }
    public void setCode(String code) {
        this.code = code;
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
    public String getRev4() {
        return this.rev4;
    }
    public void setRev4(String rev4) {
        this.rev4 = rev4;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 953923353)
    public List<Photo> getPhotos() {
        if (photos == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PhotoDao targetDao = daoSession.getPhotoDao();
            List<Photo> photosNew = targetDao._queryGoodEntity_Photos(id);
            synchronized (this) {
                if (photos == null) {
                    photos = photosNew;
                }
            }
        }
        return photos;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 781103891)
    public synchronized void resetPhotos() {
        photos = null;
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
    @Generated(hash = 1539300179)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getGoodEntityDao() : null;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
