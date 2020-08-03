package com.liany.csiserverapp.diagnose;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * @创建者 ly
 * @创建时间 2019/8/1
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
@Entity(nameInDb = "wifi")
public class SceneWifiInfo implements Serializable {
    private static final long serialVersionUID = -8970412014798153640L;
    @Id
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
    @Generated(hash = 1205108198)
    public SceneWifiInfo(String id, String crimeId, String INVERTIGATIONID,
            String CREATEUSER, String CREATEDATETIME, String COLLECTIONDATETIME,
            String LINKSPEED, String SSID, String BSSID, String HIDDENSSID,
            String MACADDRESS, String NETWORKLD, String RSSI,
            String SUPPLICANTSTATE, String DETAILEDSTATEOF) {
        this.id = id;
        this.crimeId = crimeId;
        this.INVERTIGATIONID = INVERTIGATIONID;
        this.CREATEUSER = CREATEUSER;
        this.CREATEDATETIME = CREATEDATETIME;
        this.COLLECTIONDATETIME = COLLECTIONDATETIME;
        this.LINKSPEED = LINKSPEED;
        this.SSID = SSID;
        this.BSSID = BSSID;
        this.HIDDENSSID = HIDDENSSID;
        this.MACADDRESS = MACADDRESS;
        this.NETWORKLD = NETWORKLD;
        this.RSSI = RSSI;
        this.SUPPLICANTSTATE = SUPPLICANTSTATE;
        this.DETAILEDSTATEOF = DETAILEDSTATEOF;
    }
    @Generated(hash = 792831279)
    public SceneWifiInfo() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCrimeId() {
        return this.crimeId;
    }
    public void setCrimeId(String crimeId) {
        this.crimeId = crimeId;
    }
    public String getINVERTIGATIONID() {
        return this.INVERTIGATIONID;
    }
    public void setINVERTIGATIONID(String INVERTIGATIONID) {
        this.INVERTIGATIONID = INVERTIGATIONID;
    }
    public String getCREATEUSER() {
        return this.CREATEUSER;
    }
    public void setCREATEUSER(String CREATEUSER) {
        this.CREATEUSER = CREATEUSER;
    }
    public String getCREATEDATETIME() {
        return this.CREATEDATETIME;
    }
    public void setCREATEDATETIME(String CREATEDATETIME) {
        this.CREATEDATETIME = CREATEDATETIME;
    }
    public String getCOLLECTIONDATETIME() {
        return this.COLLECTIONDATETIME;
    }
    public void setCOLLECTIONDATETIME(String COLLECTIONDATETIME) {
        this.COLLECTIONDATETIME = COLLECTIONDATETIME;
    }
    public String getLINKSPEED() {
        return this.LINKSPEED;
    }
    public void setLINKSPEED(String LINKSPEED) {
        this.LINKSPEED = LINKSPEED;
    }
    public String getSSID() {
        return this.SSID;
    }
    public void setSSID(String SSID) {
        this.SSID = SSID;
    }
    public String getBSSID() {
        return this.BSSID;
    }
    public void setBSSID(String BSSID) {
        this.BSSID = BSSID;
    }
    public String getHIDDENSSID() {
        return this.HIDDENSSID;
    }
    public void setHIDDENSSID(String HIDDENSSID) {
        this.HIDDENSSID = HIDDENSSID;
    }
    public String getMACADDRESS() {
        return this.MACADDRESS;
    }
    public void setMACADDRESS(String MACADDRESS) {
        this.MACADDRESS = MACADDRESS;
    }
    public String getNETWORKLD() {
        return this.NETWORKLD;
    }
    public void setNETWORKLD(String NETWORKLD) {
        this.NETWORKLD = NETWORKLD;
    }
    public String getRSSI() {
        return this.RSSI;
    }
    public void setRSSI(String RSSI) {
        this.RSSI = RSSI;
    }
    public String getSUPPLICANTSTATE() {
        return this.SUPPLICANTSTATE;
    }
    public void setSUPPLICANTSTATE(String SUPPLICANTSTATE) {
        this.SUPPLICANTSTATE = SUPPLICANTSTATE;
    }
    public String getDETAILEDSTATEOF() {
        return this.DETAILEDSTATEOF;
    }
    public void setDETAILEDSTATEOF(String DETAILEDSTATEOF) {
        this.DETAILEDSTATEOF = DETAILEDSTATEOF;
    }

}
