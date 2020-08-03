package com.liany.csiserverapp.diagnose;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * @创建者 ly
 * @创建时间 2019/8/23
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
@Entity(nameInDb = "alarm")
public class Alarm implements Serializable {
    private static final long serialVersionUID = -7895802292949052719L;
    //id
    @Id
    private String id;
    //接警号
    private String alarmNo;
    //接警人
    private String alarmName;
    //接警时间
    private String alarmDate;
    //案件类别
    private String caseType;
    //发案地点
    private String occurPlace;
    //报警方式
    private String alarmType;
    //报警人
    private String reportName;
    //报警人电话
    private String reportPhone;
    //备注
    private String remark;
    //接警单位
    private String alarmUnit;
    private String alarmUnitName;//接警单位名称
    private String alarmFromDate;//接警开始时间
    private String alarmToDate;//接警结束时间
    @Generated(hash = 454359356)
    public Alarm(String id, String alarmNo, String alarmName, String alarmDate,
            String caseType, String occurPlace, String alarmType, String reportName,
            String reportPhone, String remark, String alarmUnit,
            String alarmUnitName, String alarmFromDate, String alarmToDate) {
        this.id = id;
        this.alarmNo = alarmNo;
        this.alarmName = alarmName;
        this.alarmDate = alarmDate;
        this.caseType = caseType;
        this.occurPlace = occurPlace;
        this.alarmType = alarmType;
        this.reportName = reportName;
        this.reportPhone = reportPhone;
        this.remark = remark;
        this.alarmUnit = alarmUnit;
        this.alarmUnitName = alarmUnitName;
        this.alarmFromDate = alarmFromDate;
        this.alarmToDate = alarmToDate;
    }
    @Generated(hash = 1972324134)
    public Alarm() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAlarmNo() {
        return this.alarmNo;
    }
    public void setAlarmNo(String alarmNo) {
        this.alarmNo = alarmNo;
    }
    public String getAlarmName() {
        return this.alarmName;
    }
    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }
    public String getAlarmDate() {
        return this.alarmDate;
    }
    public void setAlarmDate(String alarmDate) {
        this.alarmDate = alarmDate;
    }
    public String getCaseType() {
        return this.caseType;
    }
    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }
    public String getOccurPlace() {
        return this.occurPlace;
    }
    public void setOccurPlace(String occurPlace) {
        this.occurPlace = occurPlace;
    }
    public String getAlarmType() {
        return this.alarmType;
    }
    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }
    public String getReportName() {
        return this.reportName;
    }
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }
    public String getReportPhone() {
        return this.reportPhone;
    }
    public void setReportPhone(String reportPhone) {
        this.reportPhone = reportPhone;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getAlarmUnit() {
        return this.alarmUnit;
    }
    public void setAlarmUnit(String alarmUnit) {
        this.alarmUnit = alarmUnit;
    }
    public String getAlarmUnitName() {
        return this.alarmUnitName;
    }
    public void setAlarmUnitName(String alarmUnitName) {
        this.alarmUnitName = alarmUnitName;
    }
    public String getAlarmFromDate() {
        return this.alarmFromDate;
    }
    public void setAlarmFromDate(String alarmFromDate) {
        this.alarmFromDate = alarmFromDate;
    }
    public String getAlarmToDate() {
        return this.alarmToDate;
    }
    public void setAlarmToDate(String alarmToDate) {
        this.alarmToDate = alarmToDate;
    }

}
