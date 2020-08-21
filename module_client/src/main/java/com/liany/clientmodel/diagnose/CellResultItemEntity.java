package com.liany.clientmodel.diagnose;

import java.io.Serializable;

/**
 * @创建者 ly
 * @创建时间 2019/4/2
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class CellResultItemEntity implements Serializable {
    private static final long serialVersionUID = -4455437944683440785L;
    private String id;
    private String crimeId;
    private String photoPath;
    //照片描述
    private String photoInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCrimeId() {
        return crimeId;
    }

    public void setCrimeId(String crimeId) {
        this.crimeId = crimeId;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getPhotoInfo() {
        return photoInfo;
    }

    public void setPhotoInfo(String photoInfo) {
        this.photoInfo = photoInfo;
    }
}
