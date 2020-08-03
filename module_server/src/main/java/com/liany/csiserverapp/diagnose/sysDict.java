package com.liany.csiserverapp.diagnose;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;

/**
 * @创建者 ly
 * @创建时间 2019/4/4
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
@Entity(nameInDb = "SYS_DICT")
public class sysDict implements Serializable {
    private static final long serialVersionUID = 8023926597195423032L;
    @Id()
    private String id;//
    @Property(nameInDb = "dictLevel")
    private  String dictLevel;//字典级别
    @Property(nameInDb = "dictKey")
    private String dictKey;//字典代码值
    @Property(nameInDb = "parentKey")
    private String parentKey;//父级代码值
    @Property(nameInDb = "rootKey")
    private String rootKey;//根级代码值
    @Property(nameInDb = "dictValue")
    private String dictValue;//字典中文值
    @Property(nameInDb = "dictPy")
    private String dictPy;
    @Property(nameInDb = "remark")
    private String remark;
    @Generated(hash = 1154322492)
    public sysDict(String id, String dictLevel, String dictKey, String parentKey,
            String rootKey, String dictValue, String dictPy, String remark) {
        this.id = id;
        this.dictLevel = dictLevel;
        this.dictKey = dictKey;
        this.parentKey = parentKey;
        this.rootKey = rootKey;
        this.dictValue = dictValue;
        this.dictPy = dictPy;
        this.remark = remark;
    }
    @Generated(hash = 2132873383)
    public sysDict() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDictLevel() {
        return this.dictLevel;
    }
    public void setDictLevel(String dictLevel) {
        this.dictLevel = dictLevel;
    }
    public String getDictKey() {
        return this.dictKey;
    }
    public void setDictKey(String dictKey) {
        this.dictKey = dictKey;
    }
    public String getParentKey() {
        return this.parentKey;
    }
    public void setParentKey(String parentKey) {
        this.parentKey = parentKey;
    }
    public String getRootKey() {
        return this.rootKey;
    }
    public void setRootKey(String rootKey) {
        this.rootKey = rootKey;
    }
    public String getDictValue() {
        return this.dictValue;
    }
    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }
    public String getDictPy() {
        return this.dictPy;
    }
    public void setDictPy(String dictPy) {
        this.dictPy = dictPy;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

}
