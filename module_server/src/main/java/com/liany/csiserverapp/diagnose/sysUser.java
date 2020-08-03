package com.liany.csiserverapp.diagnose;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * @创建者 ly
 * @创建时间 2019/4/4
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
@Entity(nameInDb = "SYS_USER")
public class sysUser {
    @Id()
    private String id;
    @Property(nameInDb = "techId")
    private String techId;//技术人员id
    @Property(nameInDb = "userName")
    private String userName;//用户账号
    @Property(nameInDb = "password")
    private String password;//密码
    @Property(nameInDb = "trueName")
    private String trueName;//真是姓名
    @Generated(hash = 1777222181)
    public sysUser(String id, String techId, String userName, String password,
            String trueName) {
        this.id = id;
        this.techId = techId;
        this.userName = userName;
        this.password = password;
        this.trueName = trueName;
    }
    @Generated(hash = 620521178)
    public sysUser() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTechId() {
        return this.techId;
    }
    public void setTechId(String techId) {
        this.techId = techId;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getTrueName() {
        return this.trueName;
    }
    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

}
