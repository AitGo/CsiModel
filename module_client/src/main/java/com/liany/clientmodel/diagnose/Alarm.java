package com.liany.clientmodel.diagnose;

import java.io.Serializable;

/**
 * @创建者 ly
 * @创建时间 2019/8/23
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class Alarm implements Serializable {
    private static final long serialVersionUID = -7895802292949052719L;
    //id
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlarmNo() {
        return alarmNo;
    }

    public void setAlarmNo(String alarmNo) {
        this.alarmNo = alarmNo;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public String getAlarmDate() {
        return alarmDate;
    }

    public void setAlarmDate(String alarmDate) {
        this.alarmDate = alarmDate;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getOccurPlace() {
        return occurPlace;
    }

    public void setOccurPlace(String occurPlace) {
        this.occurPlace = occurPlace;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportPhone() {
        return reportPhone;
    }

    public void setReportPhone(String reportPhone) {
        this.reportPhone = reportPhone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAlarmUnit() {
        return alarmUnit;
    }

    public void setAlarmUnit(String alarmUnit) {
        this.alarmUnit = alarmUnit;
    }

    public String getAlarmUnitName() {
        return alarmUnitName;
    }

    public void setAlarmUnitName(String alarmUnitName) {
        this.alarmUnitName = alarmUnitName;
    }

    public String getAlarmFromDate() {
        return alarmFromDate;
    }

    public void setAlarmFromDate(String alarmFromDate) {
        this.alarmFromDate = alarmFromDate;
    }

    public String getAlarmToDate() {
        return alarmToDate;
    }

    public void setAlarmToDate(String alarmToDate) {
        this.alarmToDate = alarmToDate;
    }
}
