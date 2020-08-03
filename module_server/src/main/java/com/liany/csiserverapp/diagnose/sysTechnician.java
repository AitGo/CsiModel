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
@Entity(nameInDb = "TECHNICIAN")
public class sysTechnician {
    @Id()
    private String id;
    @Property(nameInDb = "organId")
    private String organId;//单位id
//    @Property(nameInDb = "trueName")
//    private String trueName;//真实姓名
    @Property(nameInDb = "idCardNo")
    private String idCardNo;//身份证号
    @Property(nameInDb = "contact")
    private String contact;
    @Property(nameInDb = "duty")
    private String duty;
    @Generated(hash = 750140610)
    public sysTechnician(String id, String organId, String idCardNo, String contact,
            String duty) {
        this.id = id;
        this.organId = organId;
        this.idCardNo = idCardNo;
        this.contact = contact;
        this.duty = duty;
    }
    @Generated(hash = 1675905632)
    public sysTechnician() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getOrganId() {
        return this.organId;
    }
    public void setOrganId(String organId) {
        this.organId = organId;
    }
    public String getIdCardNo() {
        return this.idCardNo;
    }
    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }
    public String getContact() {
        return this.contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getDuty() {
        return this.duty;
    }
    public void setDuty(String duty) {
        this.duty = duty;
    }

}
