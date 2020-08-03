package com.liany.csiclient.diagnose;

import java.io.Serializable;
import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2019/4/24
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class selectUser implements Serializable {

    private static final long serialVersionUID = -8434288833526806385L;
    private String userId;
    private String userName;
    private String trueName;
    private String techId;
    private String organId;
    private String parentId;
    private String unitName;
    private String shortName;
    private String unitCode;

    private boolean isCheck = false;

    private List<selectUser> childUser;

    public List<selectUser> getChildUser() {
        return childUser;
    }

    public void setChildUser(List<selectUser> childUser) {
        this.childUser = childUser;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getTechId() {
        return techId;
    }

    public void setTechId(String techId) {
        this.techId = techId;
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }
}
