package com.liany.csiserverapp.diagnose;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * @创建者 ly
 * @创建时间 2019/4/2
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
@Entity(nameInDb = "cellResultItem")
public class CellResultItemEntity implements Serializable {
    private static final long serialVersionUID = -4455437944683440785L;
    @Id
    private String id;
    private String crimeId;
    private String photoPath;
    //照片描述
    private String photoInfo;
    @Generated(hash = 1513179196)
    public CellResultItemEntity(String id, String crimeId, String photoPath,
            String photoInfo) {
        this.id = id;
        this.crimeId = crimeId;
        this.photoPath = photoPath;
        this.photoInfo = photoInfo;
    }
    @Generated(hash = 121211612)
    public CellResultItemEntity() {
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
    public String getPhotoPath() {
        return this.photoPath;
    }
    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
    public String getPhotoInfo() {
        return this.photoInfo;
    }
    public void setPhotoInfo(String photoInfo) {
        this.photoInfo = photoInfo;
    }


}
