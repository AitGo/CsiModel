package com.liany.clientmodel.diagnose;

import java.io.Serializable;

/**
 * @创建者 ly
 * @创建时间 2019/3/29
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class WitnessEntity implements Serializable {

    private static final long serialVersionUID = -8804248307988047122L;
    private String id;
    private String crimeId;
    private String witnessName;//见证人姓名
    private String witnessSex;//性别
    private String witnessSexKey;//性别
    private String witnessBirthday;//出生日期
    private String witnessNumber;//联系电话
    private String witnessAddress;//现居地址
    private Photo photo;
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

    public String getWitnessName() {
        return witnessName;
    }

    public void setWitnessName(String witnessName) {
        this.witnessName = witnessName;
    }

    public String getWitnessSex() {
        return witnessSex;
    }

    public void setWitnessSex(String witnessSex) {
        this.witnessSex = witnessSex;
    }

    public String getWitnessSexKey() {
        return witnessSexKey;
    }

    public void setWitnessSexKey(String witnessSexKey) {
        this.witnessSexKey = witnessSexKey;
    }

    public String getWitnessBirthday() {
        return witnessBirthday;
    }

    public void setWitnessBirthday(String witnessBirthday) {
        this.witnessBirthday = witnessBirthday;
    }

    public String getWitnessNumber() {
        return witnessNumber;
    }

    public void setWitnessNumber(String witnessNumber) {
        this.witnessNumber = witnessNumber;
    }

    public String getWitnessAddress() {
        return witnessAddress;
    }

    public void setWitnessAddress(String witnessAddress) {
        this.witnessAddress = witnessAddress;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
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
