package com.liany.csiserverapp.diagnose;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

import java.io.Serializable;
import com.liany.csiserverapp.dao.database.greenDao.db.DaoSession;
import com.liany.csiserverapp.dao.database.greenDao.db.PhotoDao;
import com.liany.csiserverapp.dao.database.greenDao.db.WitnessEntityDao;

/**
 * @创建者 ly
 * @创建时间 2019/3/29
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
@Entity(nameInDb = "witness")
public class WitnessEntity implements Serializable {

    private static final long serialVersionUID = -8804248307988047122L;
    @Id
    private String id;
    private String crimeId;
    private String witnessName;//见证人姓名
    private String witnessSex;//性别
    private String witnessSexKey;//性别
    private String witnessBirthday;//出生日期
    private String witnessNumber;//联系电话
    private String witnessAddress;//现居地址
    private String photoId;
    @ToOne(joinProperty  = "photoId")
    private Photo photo;
    private String rev1;
    private String rev2;
    private String rev3;
    private String rev4;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 723835314)
    private transient WitnessEntityDao myDao;
    @Generated(hash = 1243475741)
    public WitnessEntity(String id, String crimeId, String witnessName,
            String witnessSex, String witnessSexKey, String witnessBirthday,
            String witnessNumber, String witnessAddress, String photoId,
            String rev1, String rev2, String rev3, String rev4) {
        this.id = id;
        this.crimeId = crimeId;
        this.witnessName = witnessName;
        this.witnessSex = witnessSex;
        this.witnessSexKey = witnessSexKey;
        this.witnessBirthday = witnessBirthday;
        this.witnessNumber = witnessNumber;
        this.witnessAddress = witnessAddress;
        this.photoId = photoId;
        this.rev1 = rev1;
        this.rev2 = rev2;
        this.rev3 = rev3;
        this.rev4 = rev4;
    }
    @Generated(hash = 488704020)
    public WitnessEntity() {
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
    public String getWitnessName() {
        return this.witnessName;
    }
    public void setWitnessName(String witnessName) {
        this.witnessName = witnessName;
    }
    public String getWitnessSex() {
        return this.witnessSex;
    }
    public void setWitnessSex(String witnessSex) {
        this.witnessSex = witnessSex;
    }
    public String getWitnessSexKey() {
        return this.witnessSexKey;
    }
    public void setWitnessSexKey(String witnessSexKey) {
        this.witnessSexKey = witnessSexKey;
    }
    public String getWitnessBirthday() {
        return this.witnessBirthday;
    }
    public void setWitnessBirthday(String witnessBirthday) {
        this.witnessBirthday = witnessBirthday;
    }
    public String getWitnessNumber() {
        return this.witnessNumber;
    }
    public void setWitnessNumber(String witnessNumber) {
        this.witnessNumber = witnessNumber;
    }
    public String getWitnessAddress() {
        return this.witnessAddress;
    }
    public void setWitnessAddress(String witnessAddress) {
        this.witnessAddress = witnessAddress;
    }
    public String getPhotoId() {
        return this.photoId;
    }
    public void setPhotoId(String photoId) {
        this.photoId = photoId;
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
    @Generated(hash = 2024353292)
    private transient String photo__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 512368403)
    public Photo getPhoto() {
        String __key = this.photoId;
        if (photo__resolvedKey == null || photo__resolvedKey != __key) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PhotoDao targetDao = daoSession.getPhotoDao();
            Photo photoNew = targetDao.load(__key);
            synchronized (this) {
                photo = photoNew;
                photo__resolvedKey = __key;
            }
        }
        return photo;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2088160251)
    public void setPhoto(Photo photo) {
        synchronized (this) {
            this.photo = photo;
            photoId = photo == null ? null : photo.getId();
            photo__resolvedKey = photoId;
        }
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
    @Generated(hash = 840092342)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getWitnessEntityDao() : null;
    }

}
