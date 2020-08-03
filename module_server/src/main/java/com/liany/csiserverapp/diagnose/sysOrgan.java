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
@Entity(nameInDb = "ORGANIZATION")
public class sysOrgan {
    @Id()
    private String id;
    @Property(nameInDb = "parentId")
    private String parentId;//父级id
    @Property(nameInDb = "unitName")
    private String unitName;//单位名称
    @Property(nameInDb = "shortName")
    private String shortName;//单位简称
    @Property(nameInDb = "unitCode")
    private String unitCode;//单位代码
    @Generated(hash = 1267873039)
    public sysOrgan(String id, String parentId, String unitName, String shortName,
            String unitCode) {
        this.id = id;
        this.parentId = parentId;
        this.unitName = unitName;
        this.shortName = shortName;
        this.unitCode = unitCode;
    }
    @Generated(hash = 2036016085)
    public sysOrgan() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getParentId() {
        return this.parentId;
    }
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    public String getUnitName() {
        return this.unitName;
    }
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
    public String getShortName() {
        return this.shortName;
    }
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    public String getUnitCode() {
        return this.unitCode;
    }
    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

}
