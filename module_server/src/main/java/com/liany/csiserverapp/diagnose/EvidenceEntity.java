package com.liany.csiserverapp.diagnose;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

import java.io.Serializable;
import com.liany.csiserverapp.dao.database.greenDao.db.DaoSession;
import com.liany.csiserverapp.dao.database.greenDao.db.PhotoDao;
import com.liany.csiserverapp.dao.database.greenDao.db.EvidenceEntityDao;

/**
 * @创建者 ly
 * @创建时间 2019/3/29
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
@Entity(nameInDb = "evidence")
public class EvidenceEntity implements Serializable {

    private static final long serialVersionUID = -909248588573511738L;
    @Id
    private String id;
    private String crimeId;
    private String evidenceCategory;//痕迹类型 手印,足迹,人像
    private String evidenceCategoryKey;//痕迹类型
    private String evidence;//痕迹
    private String evidenceKey;//痕迹
    private String evidenceName;//物证名称
    private String legacySite;//遗留部位
    private String basiceFeature;//基本特征
    private String infer;//工具推断
    private String inferKey;//工具推断
    private String method;//提取方法
    private String methodKey;//提取方法
    private long time;//提取时间
    private String people;//提取人
    private String peopleKey;//提取人id
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
    @Generated(hash = 1190357275)
    private transient EvidenceEntityDao myDao;
    @Generated(hash = 567248507)
    public EvidenceEntity(String id, String crimeId, String evidenceCategory,
            String evidenceCategoryKey, String evidence, String evidenceKey,
            String evidenceName, String legacySite, String basiceFeature,
            String infer, String inferKey, String method, String methodKey,
            long time, String people, String peopleKey, String photoId, String rev1,
            String rev2, String rev3, String rev4) {
        this.id = id;
        this.crimeId = crimeId;
        this.evidenceCategory = evidenceCategory;
        this.evidenceCategoryKey = evidenceCategoryKey;
        this.evidence = evidence;
        this.evidenceKey = evidenceKey;
        this.evidenceName = evidenceName;
        this.legacySite = legacySite;
        this.basiceFeature = basiceFeature;
        this.infer = infer;
        this.inferKey = inferKey;
        this.method = method;
        this.methodKey = methodKey;
        this.time = time;
        this.people = people;
        this.peopleKey = peopleKey;
        this.photoId = photoId;
        this.rev1 = rev1;
        this.rev2 = rev2;
        this.rev3 = rev3;
        this.rev4 = rev4;
    }
    @Generated(hash = 1996860960)
    public EvidenceEntity() {
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
    public String getEvidenceCategory() {
        return this.evidenceCategory;
    }
    public void setEvidenceCategory(String evidenceCategory) {
        this.evidenceCategory = evidenceCategory;
    }
    public String getEvidenceCategoryKey() {
        return this.evidenceCategoryKey;
    }
    public void setEvidenceCategoryKey(String evidenceCategoryKey) {
        this.evidenceCategoryKey = evidenceCategoryKey;
    }
    public String getEvidence() {
        return this.evidence;
    }
    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }
    public String getEvidenceKey() {
        return this.evidenceKey;
    }
    public void setEvidenceKey(String evidenceKey) {
        this.evidenceKey = evidenceKey;
    }
    public String getEvidenceName() {
        return this.evidenceName;
    }
    public void setEvidenceName(String evidenceName) {
        this.evidenceName = evidenceName;
    }
    public String getLegacySite() {
        return this.legacySite;
    }
    public void setLegacySite(String legacySite) {
        this.legacySite = legacySite;
    }
    public String getBasiceFeature() {
        return this.basiceFeature;
    }
    public void setBasiceFeature(String basiceFeature) {
        this.basiceFeature = basiceFeature;
    }
    public String getInfer() {
        return this.infer;
    }
    public void setInfer(String infer) {
        this.infer = infer;
    }
    public String getInferKey() {
        return this.inferKey;
    }
    public void setInferKey(String inferKey) {
        this.inferKey = inferKey;
    }
    public String getMethod() {
        return this.method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public String getMethodKey() {
        return this.methodKey;
    }
    public void setMethodKey(String methodKey) {
        this.methodKey = methodKey;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public String getPeople() {
        return this.people;
    }
    public void setPeople(String people) {
        this.people = people;
    }
    public String getPeopleKey() {
        return this.peopleKey;
    }
    public void setPeopleKey(String peopleKey) {
        this.peopleKey = peopleKey;
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
    @Generated(hash = 2038664254)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getEvidenceEntityDao() : null;
    }


}
