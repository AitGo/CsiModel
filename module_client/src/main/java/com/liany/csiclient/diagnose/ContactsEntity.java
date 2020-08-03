package com.liany.csiclient.diagnose;

import java.io.Serializable;
import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2019/3/22
 * @描述 ${联系人bean}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ContactsEntity implements Serializable {

    private static final long serialVersionUID = 7773579016386276921L;
    private String id;
    private String crimeId;

    private String type; //联系人类型 0：报案人，1：受害人
    private String name;
    private String sex;//性别 0：男，1：女
    private String sexKey;//性别 0：男，1：女
    private String peopleId;//身份证号
    private String tel;//联系电话
    private String address;//现居地址

    private List<Photo> photos;//采集指纹照片
    //    private int isUpload;//图片在服务器的状态:1:上传失败,2:上传成功
//    private int isDelete;//1:删除失败,2:删除成功
    private String rev1;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSexKey() {
        return sexKey;
    }

    public void setSexKey(String sexKey) {
        this.sexKey = sexKey;
    }

    public String getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
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
