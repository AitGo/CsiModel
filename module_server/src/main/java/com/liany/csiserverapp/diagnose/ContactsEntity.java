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
import com.liany.csiserverapp.dao.database.greenDao.db.ContactsEntityDao;

/**
 * @创建者 ly
 * @创建时间 2019/3/22
 * @描述 ${联系人bean}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
@Entity(nameInDb = "contacts")
public class ContactsEntity implements Serializable {

    private static final long serialVersionUID = 7773579016386276921L;
    @Id
    private String id;
    private String crimeId;

    private String type; //联系人类型 0：报案人，1：受害人
    private String name;
    private String sex;//性别 0：男，1：女
    private String sexKey;//性别 0：男，1：女
    private String peopleId;//身份证号
    private String tel;//联系电话
    private String address;//现居地址

    @ToMany(referencedJoinProperty = "parentId")
    private List<Photo> photos;//采集指纹照片
    //    private int isUpload;//图片在服务器的状态:1:上传失败,2:上传成功
//    private int isDelete;//1:删除失败,2:删除成功
    private String rev1;
    private String rev2;
    private String rev3;
    private String rev4;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 786518748)
    private transient ContactsEntityDao myDao;
    @Generated(hash = 1145306498)
    public ContactsEntity(String id, String crimeId, String type, String name,
            String sex, String sexKey, String peopleId, String tel, String address,
            String rev1, String rev2, String rev3, String rev4) {
        this.id = id;
        this.crimeId = crimeId;
        this.type = type;
        this.name = name;
        this.sex = sex;
        this.sexKey = sexKey;
        this.peopleId = peopleId;
        this.tel = tel;
        this.address = address;
        this.rev1 = rev1;
        this.rev2 = rev2;
        this.rev3 = rev3;
        this.rev4 = rev4;
    }
    @Generated(hash = 900460154)
    public ContactsEntity() {
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
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getSexKey() {
        return this.sexKey;
    }
    public void setSexKey(String sexKey) {
        this.sexKey = sexKey;
    }
    public String getPeopleId() {
        return this.peopleId;
    }
    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }
    public String getTel() {
        return this.tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
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
    @Generated(hash = 2062933603)
    public List<Photo> getPhotos() {
        if (photos == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PhotoDao targetDao = daoSession.getPhotoDao();
            List<Photo> photosNew = targetDao._queryContactsEntity_Photos(id);
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
    @Generated(hash = 1649281976)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getContactsEntityDao() : null;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
