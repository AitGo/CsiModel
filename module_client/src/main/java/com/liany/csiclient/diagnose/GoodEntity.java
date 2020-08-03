package com.liany.csiclient.diagnose;

import java.io.Serializable;
import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2019/8/16
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class GoodEntity implements Serializable {

    private static final long serialVersionUID = -305401489447341178L;
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

    private List<Photo> photos;//物证照片
    private String code;//二维码

    private String rev1;//0:普通检材 1:生物检材
    private String rev2;
    private String rev3;
    private String rev4;

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

    public String getCollectedName() {
        return collectedName;
    }

    public void setCollectedName(String collectedName) {
        this.collectedName = collectedName;
    }

    public String getCollectedIds() {
        return collectedIds;
    }

    public void setCollectedIds(String collectedIds) {
        this.collectedIds = collectedIds;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getCollectedPosition() {
        return collectedPosition;
    }

    public void setCollectedPosition(String collectedPosition) {
        this.collectedPosition = collectedPosition;
    }

    public String getCollectedMethod() {
        return collectedMethod;
    }

    public void setCollectedMethod(String collectedMethod) {
        this.collectedMethod = collectedMethod;
    }

    public String getCollectedNum() {
        return collectedNum;
    }

    public void setCollectedNum(String collectedNum) {
        this.collectedNum = collectedNum;
    }

    public String getCollectedDate() {
        return collectedDate;
    }

    public void setCollectedDate(String collectedDate) {
        this.collectedDate = collectedDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRev1() {
        return rev1;
    }

    public void setRev1(String rev1) {
        this.rev1 = rev1;
    }

    public String getRev2() {
        return rev2;
    }

    public void setRev2(String rev2) {
        this.rev2 = rev2;
    }

    public String getRev3() {
        return rev3;
    }

    public void setRev3(String rev3) {
        this.rev3 = rev3;
    }

    public String getRev4() {
        return rev4;
    }

    public void setRev4(String rev4) {
        this.rev4 = rev4;
    }
}
