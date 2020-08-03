package com.liany.csiserverapp.diagnose;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * @创建者 ly
 * @创建时间 2019/9/6
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
@Entity(nameInDb = "kctBaseStation")
public class KCTBASESTATIONDATABean implements Serializable {

    private static final long serialVersionUID = -3100657843750297574L;
    @Id
    private String id;
    private String crimeId;
    private String BS_TYPE;
    private String IFACTIVE;
    private String MCC_MNC;
    private String LAC;
    private String CELL_ID;
    private String LOCALE_DATA_ID;
    private String COL_TIME;
    private String PCI;
    private String CELL;
    private String RSRP;
    private String RSSI;
    private String TAC;
    private String REG_ZONE;
    private String SID;
    private String NID;
    private String BASE_ID;
    private String CDMA_CH;
    private String PN;
    private String STRENGTH;// 信号强度
    private String BCCH;
    private String BSIC;
    private String SYS_BAND;
    private String LON;
    private String LAT;
    private String ERFCN;
    private String BAND;
    private String EARFCN;
    private String RSRQ;
    private String RAC;
    private String RNCID;
    private String ENBID;
    private String PHYCELLID;
    private String CELLPARAMID;
    private String UUID;
    private long startTime;
    private long endTime;
    @Generated(hash = 1316167886)
    public KCTBASESTATIONDATABean(String id, String crimeId, String BS_TYPE,
            String IFACTIVE, String MCC_MNC, String LAC, String CELL_ID,
            String LOCALE_DATA_ID, String COL_TIME, String PCI, String CELL,
            String RSRP, String RSSI, String TAC, String REG_ZONE, String SID,
            String NID, String BASE_ID, String CDMA_CH, String PN, String STRENGTH,
            String BCCH, String BSIC, String SYS_BAND, String LON, String LAT,
            String ERFCN, String BAND, String EARFCN, String RSRQ, String RAC,
            String RNCID, String ENBID, String PHYCELLID, String CELLPARAMID,
            String UUID, long startTime, long endTime) {
        this.id = id;
        this.crimeId = crimeId;
        this.BS_TYPE = BS_TYPE;
        this.IFACTIVE = IFACTIVE;
        this.MCC_MNC = MCC_MNC;
        this.LAC = LAC;
        this.CELL_ID = CELL_ID;
        this.LOCALE_DATA_ID = LOCALE_DATA_ID;
        this.COL_TIME = COL_TIME;
        this.PCI = PCI;
        this.CELL = CELL;
        this.RSRP = RSRP;
        this.RSSI = RSSI;
        this.TAC = TAC;
        this.REG_ZONE = REG_ZONE;
        this.SID = SID;
        this.NID = NID;
        this.BASE_ID = BASE_ID;
        this.CDMA_CH = CDMA_CH;
        this.PN = PN;
        this.STRENGTH = STRENGTH;
        this.BCCH = BCCH;
        this.BSIC = BSIC;
        this.SYS_BAND = SYS_BAND;
        this.LON = LON;
        this.LAT = LAT;
        this.ERFCN = ERFCN;
        this.BAND = BAND;
        this.EARFCN = EARFCN;
        this.RSRQ = RSRQ;
        this.RAC = RAC;
        this.RNCID = RNCID;
        this.ENBID = ENBID;
        this.PHYCELLID = PHYCELLID;
        this.CELLPARAMID = CELLPARAMID;
        this.UUID = UUID;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    @Generated(hash = 2143447972)
    public KCTBASESTATIONDATABean() {
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
    public String getBS_TYPE() {
        return this.BS_TYPE;
    }
    public void setBS_TYPE(String BS_TYPE) {
        this.BS_TYPE = BS_TYPE;
    }
    public String getIFACTIVE() {
        return this.IFACTIVE;
    }
    public void setIFACTIVE(String IFACTIVE) {
        this.IFACTIVE = IFACTIVE;
    }
    public String getMCC_MNC() {
        return this.MCC_MNC;
    }
    public void setMCC_MNC(String MCC_MNC) {
        this.MCC_MNC = MCC_MNC;
    }
    public String getLAC() {
        return this.LAC;
    }
    public void setLAC(String LAC) {
        this.LAC = LAC;
    }
    public String getCELL_ID() {
        return this.CELL_ID;
    }
    public void setCELL_ID(String CELL_ID) {
        this.CELL_ID = CELL_ID;
    }
    public String getLOCALE_DATA_ID() {
        return this.LOCALE_DATA_ID;
    }
    public void setLOCALE_DATA_ID(String LOCALE_DATA_ID) {
        this.LOCALE_DATA_ID = LOCALE_DATA_ID;
    }
    public String getCOL_TIME() {
        return this.COL_TIME;
    }
    public void setCOL_TIME(String COL_TIME) {
        this.COL_TIME = COL_TIME;
    }
    public String getPCI() {
        return this.PCI;
    }
    public void setPCI(String PCI) {
        this.PCI = PCI;
    }
    public String getCELL() {
        return this.CELL;
    }
    public void setCELL(String CELL) {
        this.CELL = CELL;
    }
    public String getRSRP() {
        return this.RSRP;
    }
    public void setRSRP(String RSRP) {
        this.RSRP = RSRP;
    }
    public String getRSSI() {
        return this.RSSI;
    }
    public void setRSSI(String RSSI) {
        this.RSSI = RSSI;
    }
    public String getTAC() {
        return this.TAC;
    }
    public void setTAC(String TAC) {
        this.TAC = TAC;
    }
    public String getREG_ZONE() {
        return this.REG_ZONE;
    }
    public void setREG_ZONE(String REG_ZONE) {
        this.REG_ZONE = REG_ZONE;
    }
    public String getSID() {
        return this.SID;
    }
    public void setSID(String SID) {
        this.SID = SID;
    }
    public String getNID() {
        return this.NID;
    }
    public void setNID(String NID) {
        this.NID = NID;
    }
    public String getBASE_ID() {
        return this.BASE_ID;
    }
    public void setBASE_ID(String BASE_ID) {
        this.BASE_ID = BASE_ID;
    }
    public String getCDMA_CH() {
        return this.CDMA_CH;
    }
    public void setCDMA_CH(String CDMA_CH) {
        this.CDMA_CH = CDMA_CH;
    }
    public String getPN() {
        return this.PN;
    }
    public void setPN(String PN) {
        this.PN = PN;
    }
    public String getSTRENGTH() {
        return this.STRENGTH;
    }
    public void setSTRENGTH(String STRENGTH) {
        this.STRENGTH = STRENGTH;
    }
    public String getBCCH() {
        return this.BCCH;
    }
    public void setBCCH(String BCCH) {
        this.BCCH = BCCH;
    }
    public String getBSIC() {
        return this.BSIC;
    }
    public void setBSIC(String BSIC) {
        this.BSIC = BSIC;
    }
    public String getSYS_BAND() {
        return this.SYS_BAND;
    }
    public void setSYS_BAND(String SYS_BAND) {
        this.SYS_BAND = SYS_BAND;
    }
    public String getLON() {
        return this.LON;
    }
    public void setLON(String LON) {
        this.LON = LON;
    }
    public String getLAT() {
        return this.LAT;
    }
    public void setLAT(String LAT) {
        this.LAT = LAT;
    }
    public String getERFCN() {
        return this.ERFCN;
    }
    public void setERFCN(String ERFCN) {
        this.ERFCN = ERFCN;
    }
    public String getBAND() {
        return this.BAND;
    }
    public void setBAND(String BAND) {
        this.BAND = BAND;
    }
    public String getEARFCN() {
        return this.EARFCN;
    }
    public void setEARFCN(String EARFCN) {
        this.EARFCN = EARFCN;
    }
    public String getRSRQ() {
        return this.RSRQ;
    }
    public void setRSRQ(String RSRQ) {
        this.RSRQ = RSRQ;
    }
    public String getRAC() {
        return this.RAC;
    }
    public void setRAC(String RAC) {
        this.RAC = RAC;
    }
    public String getRNCID() {
        return this.RNCID;
    }
    public void setRNCID(String RNCID) {
        this.RNCID = RNCID;
    }
    public String getENBID() {
        return this.ENBID;
    }
    public void setENBID(String ENBID) {
        this.ENBID = ENBID;
    }
    public String getPHYCELLID() {
        return this.PHYCELLID;
    }
    public void setPHYCELLID(String PHYCELLID) {
        this.PHYCELLID = PHYCELLID;
    }
    public String getCELLPARAMID() {
        return this.CELLPARAMID;
    }
    public void setCELLPARAMID(String CELLPARAMID) {
        this.CELLPARAMID = CELLPARAMID;
    }
    public String getUUID() {
        return this.UUID;
    }
    public void setUUID(String UUID) {
        this.UUID = UUID;
    }
    public long getStartTime() {
        return this.startTime;
    }
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    public long getEndTime() {
        return this.endTime;
    }
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

}
