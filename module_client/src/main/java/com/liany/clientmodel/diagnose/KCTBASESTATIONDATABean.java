package com.liany.clientmodel.diagnose;

import java.io.Serializable;

/**
 * @创建者 ly
 * @创建时间 2019/9/6
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class KCTBASESTATIONDATABean implements Serializable {

    private static final long serialVersionUID = -3100657843750297574L;
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

    public String getBS_TYPE() {
        return BS_TYPE;
    }

    public void setBS_TYPE(String BS_TYPE) {
        this.BS_TYPE = BS_TYPE;
    }

    public String getIFACTIVE() {
        return IFACTIVE;
    }

    public void setIFACTIVE(String IFACTIVE) {
        this.IFACTIVE = IFACTIVE;
    }

    public String getMCC_MNC() {
        return MCC_MNC;
    }

    public void setMCC_MNC(String MCC_MNC) {
        this.MCC_MNC = MCC_MNC;
    }

    public String getLAC() {
        return LAC;
    }

    public void setLAC(String LAC) {
        this.LAC = LAC;
    }

    public String getCELL_ID() {
        return CELL_ID;
    }

    public void setCELL_ID(String CELL_ID) {
        this.CELL_ID = CELL_ID;
    }

    public String getLOCALE_DATA_ID() {
        return LOCALE_DATA_ID;
    }

    public void setLOCALE_DATA_ID(String LOCALE_DATA_ID) {
        this.LOCALE_DATA_ID = LOCALE_DATA_ID;
    }

    public String getCOL_TIME() {
        return COL_TIME;
    }

    public void setCOL_TIME(String COL_TIME) {
        this.COL_TIME = COL_TIME;
    }

    public String getPCI() {
        return PCI;
    }

    public void setPCI(String PCI) {
        this.PCI = PCI;
    }

    public String getCELL() {
        return CELL;
    }

    public void setCELL(String CELL) {
        this.CELL = CELL;
    }

    public String getRSRP() {
        return RSRP;
    }

    public void setRSRP(String RSRP) {
        this.RSRP = RSRP;
    }

    public String getRSSI() {
        return RSSI;
    }

    public void setRSSI(String RSSI) {
        this.RSSI = RSSI;
    }

    public String getTAC() {
        return TAC;
    }

    public void setTAC(String TAC) {
        this.TAC = TAC;
    }

    public String getREG_ZONE() {
        return REG_ZONE;
    }

    public void setREG_ZONE(String REG_ZONE) {
        this.REG_ZONE = REG_ZONE;
    }

    public String getSID() {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public String getNID() {
        return NID;
    }

    public void setNID(String NID) {
        this.NID = NID;
    }

    public String getBASE_ID() {
        return BASE_ID;
    }

    public void setBASE_ID(String BASE_ID) {
        this.BASE_ID = BASE_ID;
    }

    public String getCDMA_CH() {
        return CDMA_CH;
    }

    public void setCDMA_CH(String CDMA_CH) {
        this.CDMA_CH = CDMA_CH;
    }

    public String getPN() {
        return PN;
    }

    public void setPN(String PN) {
        this.PN = PN;
    }

    public String getSTRENGTH() {
        return STRENGTH;
    }

    public void setSTRENGTH(String STRENGTH) {
        this.STRENGTH = STRENGTH;
    }

    public String getBCCH() {
        return BCCH;
    }

    public void setBCCH(String BCCH) {
        this.BCCH = BCCH;
    }

    public String getBSIC() {
        return BSIC;
    }

    public void setBSIC(String BSIC) {
        this.BSIC = BSIC;
    }

    public String getSYS_BAND() {
        return SYS_BAND;
    }

    public void setSYS_BAND(String SYS_BAND) {
        this.SYS_BAND = SYS_BAND;
    }

    public String getLON() {
        return LON;
    }

    public void setLON(String LON) {
        this.LON = LON;
    }

    public String getLAT() {
        return LAT;
    }

    public void setLAT(String LAT) {
        this.LAT = LAT;
    }

    public String getERFCN() {
        return ERFCN;
    }

    public void setERFCN(String ERFCN) {
        this.ERFCN = ERFCN;
    }

    public String getBAND() {
        return BAND;
    }

    public void setBAND(String BAND) {
        this.BAND = BAND;
    }

    public String getEARFCN() {
        return EARFCN;
    }

    public void setEARFCN(String EARFCN) {
        this.EARFCN = EARFCN;
    }

    public String getRSRQ() {
        return RSRQ;
    }

    public void setRSRQ(String RSRQ) {
        this.RSRQ = RSRQ;
    }

    public String getRAC() {
        return RAC;
    }

    public void setRAC(String RAC) {
        this.RAC = RAC;
    }

    public String getRNCID() {
        return RNCID;
    }

    public void setRNCID(String RNCID) {
        this.RNCID = RNCID;
    }

    public String getENBID() {
        return ENBID;
    }

    public void setENBID(String ENBID) {
        this.ENBID = ENBID;
    }

    public String getPHYCELLID() {
        return PHYCELLID;
    }

    public void setPHYCELLID(String PHYCELLID) {
        this.PHYCELLID = PHYCELLID;
    }

    public String getCELLPARAMID() {
        return CELLPARAMID;
    }

    public void setCELLPARAMID(String CELLPARAMID) {
        this.CELLPARAMID = CELLPARAMID;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
