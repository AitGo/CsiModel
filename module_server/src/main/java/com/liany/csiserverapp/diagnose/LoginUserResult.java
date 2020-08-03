package com.liany.csiserverapp.diagnose;

/**
 * @创建者 ly
 * @创建时间 2020/3/23
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class LoginUserResult {
    private String userName;//用户真实姓名
    private String userId;//用户id
    private String personId;//用户身份证号
    private String organId;//单位id
    private String techId;//检验id
    private String unitName;//单位名称
    private String unitCode;//单位代码
    private String deviceId;//设备id

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    public String getTechId() {
        return techId;
    }

    public void setTechId(String techId) {
        this.techId = techId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
}
