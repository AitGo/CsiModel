package com.liany.clientmodel.diagnose;

import java.io.Serializable;

/**
 * @创建者 ly
 * @创建时间 2019/8/1
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class SceneWifiInfo implements Serializable {
    private static final long serialVersionUID = -8970412014798153640L;
    private String id;
    private String crimeId;
    private String INVERTIGATIONID;//现场id
    private String CREATEUSER;//创建人
    private String CREATEDATETIME;//创建时间
    private String COLLECTIONDATETIME;//采集时间
    private String LINKSPEED;//连接速率
    private String SSID;//wifi名称
    private String BSSID;//wifi识别id
    private String HIDDENSSID;//是否隐藏wifi名
    private String MACADDRESS;//mac地址
    private String NETWORKLD;//当前正在工作的网络连接id
    private String RSSI;//信号强度
    private String SUPPLICANTSTATE;//和当前wifi的协议连接状态（未连接，不可用，完成等enum对象）
    private String DETAILEDSTATEOF;//详细的状态信息

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

    public String getINVERTIGATIONID() {
        return INVERTIGATIONID;
    }

    public void setINVERTIGATIONID(String INVERTIGATIONID) {
        this.INVERTIGATIONID = INVERTIGATIONID;
    }

    public String getCREATEUSER() {
        return CREATEUSER;
    }

    public void setCREATEUSER(String CREATEUSER) {
        this.CREATEUSER = CREATEUSER;
    }

    public String getCREATEDATETIME() {
        return CREATEDATETIME;
    }

    public void setCREATEDATETIME(String CREATEDATETIME) {
        this.CREATEDATETIME = CREATEDATETIME;
    }

    public String getCOLLECTIONDATETIME() {
        return COLLECTIONDATETIME;
    }

    public void setCOLLECTIONDATETIME(String COLLECTIONDATETIME) {
        this.COLLECTIONDATETIME = COLLECTIONDATETIME;
    }

    public String getLINKSPEED() {
        return LINKSPEED;
    }

    public void setLINKSPEED(String LINKSPEED) {
        this.LINKSPEED = LINKSPEED;
    }

    public String getSSID() {
        return SSID;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
    }

    public String getBSSID() {
        return BSSID;
    }

    public void setBSSID(String BSSID) {
        this.BSSID = BSSID;
    }

    public String getHIDDENSSID() {
        return HIDDENSSID;
    }

    public void setHIDDENSSID(String HIDDENSSID) {
        this.HIDDENSSID = HIDDENSSID;
    }

    public String getMACADDRESS() {
        return MACADDRESS;
    }

    public void setMACADDRESS(String MACADDRESS) {
        this.MACADDRESS = MACADDRESS;
    }

    public String getNETWORKLD() {
        return NETWORKLD;
    }

    public void setNETWORKLD(String NETWORKLD) {
        this.NETWORKLD = NETWORKLD;
    }

    public String getRSSI() {
        return RSSI;
    }

    public void setRSSI(String RSSI) {
        this.RSSI = RSSI;
    }

    public String getSUPPLICANTSTATE() {
        return SUPPLICANTSTATE;
    }

    public void setSUPPLICANTSTATE(String SUPPLICANTSTATE) {
        this.SUPPLICANTSTATE = SUPPLICANTSTATE;
    }

    public String getDETAILEDSTATEOF() {
        return DETAILEDSTATEOF;
    }

    public void setDETAILEDSTATEOF(String DETAILEDSTATEOF) {
        this.DETAILEDSTATEOF = DETAILEDSTATEOF;
    }
}
