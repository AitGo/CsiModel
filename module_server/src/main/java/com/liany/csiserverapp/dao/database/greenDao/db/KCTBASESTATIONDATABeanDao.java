package com.liany.csiserverapp.dao.database.greenDao.db;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import com.liany.csiserverapp.diagnose.KCTBASESTATIONDATABean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "kctBaseStation".
*/
public class KCTBASESTATIONDATABeanDao extends AbstractDao<KCTBASESTATIONDATABean, String> {

    public static final String TABLENAME = "kctBaseStation";

    /**
     * Properties of entity KCTBASESTATIONDATABean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, String.class, "id", true, "ID");
        public final static Property CrimeId = new Property(1, String.class, "crimeId", false, "CRIME_ID");
        public final static Property BS_TYPE = new Property(2, String.class, "BS_TYPE", false, "BS__TYPE");
        public final static Property IFACTIVE = new Property(3, String.class, "IFACTIVE", false, "IFACTIVE");
        public final static Property MCC_MNC = new Property(4, String.class, "MCC_MNC", false, "MCC__MNC");
        public final static Property LAC = new Property(5, String.class, "LAC", false, "LAC");
        public final static Property CELL_ID = new Property(6, String.class, "CELL_ID", false, "CELL__ID");
        public final static Property LOCALE_DATA_ID = new Property(7, String.class, "LOCALE_DATA_ID", false, "LOCALE__DATA__ID");
        public final static Property COL_TIME = new Property(8, String.class, "COL_TIME", false, "COL__TIME");
        public final static Property PCI = new Property(9, String.class, "PCI", false, "PCI");
        public final static Property CELL = new Property(10, String.class, "CELL", false, "CELL");
        public final static Property RSRP = new Property(11, String.class, "RSRP", false, "RSRP");
        public final static Property RSSI = new Property(12, String.class, "RSSI", false, "RSSI");
        public final static Property TAC = new Property(13, String.class, "TAC", false, "TAC");
        public final static Property REG_ZONE = new Property(14, String.class, "REG_ZONE", false, "REG__ZONE");
        public final static Property SID = new Property(15, String.class, "SID", false, "SID");
        public final static Property NID = new Property(16, String.class, "NID", false, "NID");
        public final static Property BASE_ID = new Property(17, String.class, "BASE_ID", false, "BASE__ID");
        public final static Property CDMA_CH = new Property(18, String.class, "CDMA_CH", false, "CDMA__CH");
        public final static Property PN = new Property(19, String.class, "PN", false, "PN");
        public final static Property STRENGTH = new Property(20, String.class, "STRENGTH", false, "STRENGTH");
        public final static Property BCCH = new Property(21, String.class, "BCCH", false, "BCCH");
        public final static Property BSIC = new Property(22, String.class, "BSIC", false, "BSIC");
        public final static Property SYS_BAND = new Property(23, String.class, "SYS_BAND", false, "SYS__BAND");
        public final static Property LON = new Property(24, String.class, "LON", false, "LON");
        public final static Property LAT = new Property(25, String.class, "LAT", false, "LAT");
        public final static Property ERFCN = new Property(26, String.class, "ERFCN", false, "ERFCN");
        public final static Property BAND = new Property(27, String.class, "BAND", false, "BAND");
        public final static Property EARFCN = new Property(28, String.class, "EARFCN", false, "EARFCN");
        public final static Property RSRQ = new Property(29, String.class, "RSRQ", false, "RSRQ");
        public final static Property RAC = new Property(30, String.class, "RAC", false, "RAC");
        public final static Property RNCID = new Property(31, String.class, "RNCID", false, "RNCID");
        public final static Property ENBID = new Property(32, String.class, "ENBID", false, "ENBID");
        public final static Property PHYCELLID = new Property(33, String.class, "PHYCELLID", false, "PHYCELLID");
        public final static Property CELLPARAMID = new Property(34, String.class, "CELLPARAMID", false, "CELLPARAMID");
        public final static Property UUID = new Property(35, String.class, "UUID", false, "UUID");
        public final static Property StartTime = new Property(36, long.class, "startTime", false, "START_TIME");
        public final static Property EndTime = new Property(37, long.class, "endTime", false, "END_TIME");
    }

    private Query<KCTBASESTATIONDATABean> crimeItem_KctbasestationdataBeansQuery;

    public KCTBASESTATIONDATABeanDao(DaoConfig config) {
        super(config);
    }
    
    public KCTBASESTATIONDATABeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"kctBaseStation\" (" + //
                "\"ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: id
                "\"CRIME_ID\" TEXT," + // 1: crimeId
                "\"BS__TYPE\" TEXT," + // 2: BS_TYPE
                "\"IFACTIVE\" TEXT," + // 3: IFACTIVE
                "\"MCC__MNC\" TEXT," + // 4: MCC_MNC
                "\"LAC\" TEXT," + // 5: LAC
                "\"CELL__ID\" TEXT," + // 6: CELL_ID
                "\"LOCALE__DATA__ID\" TEXT," + // 7: LOCALE_DATA_ID
                "\"COL__TIME\" TEXT," + // 8: COL_TIME
                "\"PCI\" TEXT," + // 9: PCI
                "\"CELL\" TEXT," + // 10: CELL
                "\"RSRP\" TEXT," + // 11: RSRP
                "\"RSSI\" TEXT," + // 12: RSSI
                "\"TAC\" TEXT," + // 13: TAC
                "\"REG__ZONE\" TEXT," + // 14: REG_ZONE
                "\"SID\" TEXT," + // 15: SID
                "\"NID\" TEXT," + // 16: NID
                "\"BASE__ID\" TEXT," + // 17: BASE_ID
                "\"CDMA__CH\" TEXT," + // 18: CDMA_CH
                "\"PN\" TEXT," + // 19: PN
                "\"STRENGTH\" TEXT," + // 20: STRENGTH
                "\"BCCH\" TEXT," + // 21: BCCH
                "\"BSIC\" TEXT," + // 22: BSIC
                "\"SYS__BAND\" TEXT," + // 23: SYS_BAND
                "\"LON\" TEXT," + // 24: LON
                "\"LAT\" TEXT," + // 25: LAT
                "\"ERFCN\" TEXT," + // 26: ERFCN
                "\"BAND\" TEXT," + // 27: BAND
                "\"EARFCN\" TEXT," + // 28: EARFCN
                "\"RSRQ\" TEXT," + // 29: RSRQ
                "\"RAC\" TEXT," + // 30: RAC
                "\"RNCID\" TEXT," + // 31: RNCID
                "\"ENBID\" TEXT," + // 32: ENBID
                "\"PHYCELLID\" TEXT," + // 33: PHYCELLID
                "\"CELLPARAMID\" TEXT," + // 34: CELLPARAMID
                "\"UUID\" TEXT," + // 35: UUID
                "\"START_TIME\" INTEGER NOT NULL ," + // 36: startTime
                "\"END_TIME\" INTEGER NOT NULL );"); // 37: endTime
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"kctBaseStation\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, KCTBASESTATIONDATABean entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String crimeId = entity.getCrimeId();
        if (crimeId != null) {
            stmt.bindString(2, crimeId);
        }
 
        String BS_TYPE = entity.getBS_TYPE();
        if (BS_TYPE != null) {
            stmt.bindString(3, BS_TYPE);
        }
 
        String IFACTIVE = entity.getIFACTIVE();
        if (IFACTIVE != null) {
            stmt.bindString(4, IFACTIVE);
        }
 
        String MCC_MNC = entity.getMCC_MNC();
        if (MCC_MNC != null) {
            stmt.bindString(5, MCC_MNC);
        }
 
        String LAC = entity.getLAC();
        if (LAC != null) {
            stmt.bindString(6, LAC);
        }
 
        String CELL_ID = entity.getCELL_ID();
        if (CELL_ID != null) {
            stmt.bindString(7, CELL_ID);
        }
 
        String LOCALE_DATA_ID = entity.getLOCALE_DATA_ID();
        if (LOCALE_DATA_ID != null) {
            stmt.bindString(8, LOCALE_DATA_ID);
        }
 
        String COL_TIME = entity.getCOL_TIME();
        if (COL_TIME != null) {
            stmt.bindString(9, COL_TIME);
        }
 
        String PCI = entity.getPCI();
        if (PCI != null) {
            stmt.bindString(10, PCI);
        }
 
        String CELL = entity.getCELL();
        if (CELL != null) {
            stmt.bindString(11, CELL);
        }
 
        String RSRP = entity.getRSRP();
        if (RSRP != null) {
            stmt.bindString(12, RSRP);
        }
 
        String RSSI = entity.getRSSI();
        if (RSSI != null) {
            stmt.bindString(13, RSSI);
        }
 
        String TAC = entity.getTAC();
        if (TAC != null) {
            stmt.bindString(14, TAC);
        }
 
        String REG_ZONE = entity.getREG_ZONE();
        if (REG_ZONE != null) {
            stmt.bindString(15, REG_ZONE);
        }
 
        String SID = entity.getSID();
        if (SID != null) {
            stmt.bindString(16, SID);
        }
 
        String NID = entity.getNID();
        if (NID != null) {
            stmt.bindString(17, NID);
        }
 
        String BASE_ID = entity.getBASE_ID();
        if (BASE_ID != null) {
            stmt.bindString(18, BASE_ID);
        }
 
        String CDMA_CH = entity.getCDMA_CH();
        if (CDMA_CH != null) {
            stmt.bindString(19, CDMA_CH);
        }
 
        String PN = entity.getPN();
        if (PN != null) {
            stmt.bindString(20, PN);
        }
 
        String STRENGTH = entity.getSTRENGTH();
        if (STRENGTH != null) {
            stmt.bindString(21, STRENGTH);
        }
 
        String BCCH = entity.getBCCH();
        if (BCCH != null) {
            stmt.bindString(22, BCCH);
        }
 
        String BSIC = entity.getBSIC();
        if (BSIC != null) {
            stmt.bindString(23, BSIC);
        }
 
        String SYS_BAND = entity.getSYS_BAND();
        if (SYS_BAND != null) {
            stmt.bindString(24, SYS_BAND);
        }
 
        String LON = entity.getLON();
        if (LON != null) {
            stmt.bindString(25, LON);
        }
 
        String LAT = entity.getLAT();
        if (LAT != null) {
            stmt.bindString(26, LAT);
        }
 
        String ERFCN = entity.getERFCN();
        if (ERFCN != null) {
            stmt.bindString(27, ERFCN);
        }
 
        String BAND = entity.getBAND();
        if (BAND != null) {
            stmt.bindString(28, BAND);
        }
 
        String EARFCN = entity.getEARFCN();
        if (EARFCN != null) {
            stmt.bindString(29, EARFCN);
        }
 
        String RSRQ = entity.getRSRQ();
        if (RSRQ != null) {
            stmt.bindString(30, RSRQ);
        }
 
        String RAC = entity.getRAC();
        if (RAC != null) {
            stmt.bindString(31, RAC);
        }
 
        String RNCID = entity.getRNCID();
        if (RNCID != null) {
            stmt.bindString(32, RNCID);
        }
 
        String ENBID = entity.getENBID();
        if (ENBID != null) {
            stmt.bindString(33, ENBID);
        }
 
        String PHYCELLID = entity.getPHYCELLID();
        if (PHYCELLID != null) {
            stmt.bindString(34, PHYCELLID);
        }
 
        String CELLPARAMID = entity.getCELLPARAMID();
        if (CELLPARAMID != null) {
            stmt.bindString(35, CELLPARAMID);
        }
 
        String UUID = entity.getUUID();
        if (UUID != null) {
            stmt.bindString(36, UUID);
        }
        stmt.bindLong(37, entity.getStartTime());
        stmt.bindLong(38, entity.getEndTime());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, KCTBASESTATIONDATABean entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String crimeId = entity.getCrimeId();
        if (crimeId != null) {
            stmt.bindString(2, crimeId);
        }
 
        String BS_TYPE = entity.getBS_TYPE();
        if (BS_TYPE != null) {
            stmt.bindString(3, BS_TYPE);
        }
 
        String IFACTIVE = entity.getIFACTIVE();
        if (IFACTIVE != null) {
            stmt.bindString(4, IFACTIVE);
        }
 
        String MCC_MNC = entity.getMCC_MNC();
        if (MCC_MNC != null) {
            stmt.bindString(5, MCC_MNC);
        }
 
        String LAC = entity.getLAC();
        if (LAC != null) {
            stmt.bindString(6, LAC);
        }
 
        String CELL_ID = entity.getCELL_ID();
        if (CELL_ID != null) {
            stmt.bindString(7, CELL_ID);
        }
 
        String LOCALE_DATA_ID = entity.getLOCALE_DATA_ID();
        if (LOCALE_DATA_ID != null) {
            stmt.bindString(8, LOCALE_DATA_ID);
        }
 
        String COL_TIME = entity.getCOL_TIME();
        if (COL_TIME != null) {
            stmt.bindString(9, COL_TIME);
        }
 
        String PCI = entity.getPCI();
        if (PCI != null) {
            stmt.bindString(10, PCI);
        }
 
        String CELL = entity.getCELL();
        if (CELL != null) {
            stmt.bindString(11, CELL);
        }
 
        String RSRP = entity.getRSRP();
        if (RSRP != null) {
            stmt.bindString(12, RSRP);
        }
 
        String RSSI = entity.getRSSI();
        if (RSSI != null) {
            stmt.bindString(13, RSSI);
        }
 
        String TAC = entity.getTAC();
        if (TAC != null) {
            stmt.bindString(14, TAC);
        }
 
        String REG_ZONE = entity.getREG_ZONE();
        if (REG_ZONE != null) {
            stmt.bindString(15, REG_ZONE);
        }
 
        String SID = entity.getSID();
        if (SID != null) {
            stmt.bindString(16, SID);
        }
 
        String NID = entity.getNID();
        if (NID != null) {
            stmt.bindString(17, NID);
        }
 
        String BASE_ID = entity.getBASE_ID();
        if (BASE_ID != null) {
            stmt.bindString(18, BASE_ID);
        }
 
        String CDMA_CH = entity.getCDMA_CH();
        if (CDMA_CH != null) {
            stmt.bindString(19, CDMA_CH);
        }
 
        String PN = entity.getPN();
        if (PN != null) {
            stmt.bindString(20, PN);
        }
 
        String STRENGTH = entity.getSTRENGTH();
        if (STRENGTH != null) {
            stmt.bindString(21, STRENGTH);
        }
 
        String BCCH = entity.getBCCH();
        if (BCCH != null) {
            stmt.bindString(22, BCCH);
        }
 
        String BSIC = entity.getBSIC();
        if (BSIC != null) {
            stmt.bindString(23, BSIC);
        }
 
        String SYS_BAND = entity.getSYS_BAND();
        if (SYS_BAND != null) {
            stmt.bindString(24, SYS_BAND);
        }
 
        String LON = entity.getLON();
        if (LON != null) {
            stmt.bindString(25, LON);
        }
 
        String LAT = entity.getLAT();
        if (LAT != null) {
            stmt.bindString(26, LAT);
        }
 
        String ERFCN = entity.getERFCN();
        if (ERFCN != null) {
            stmt.bindString(27, ERFCN);
        }
 
        String BAND = entity.getBAND();
        if (BAND != null) {
            stmt.bindString(28, BAND);
        }
 
        String EARFCN = entity.getEARFCN();
        if (EARFCN != null) {
            stmt.bindString(29, EARFCN);
        }
 
        String RSRQ = entity.getRSRQ();
        if (RSRQ != null) {
            stmt.bindString(30, RSRQ);
        }
 
        String RAC = entity.getRAC();
        if (RAC != null) {
            stmt.bindString(31, RAC);
        }
 
        String RNCID = entity.getRNCID();
        if (RNCID != null) {
            stmt.bindString(32, RNCID);
        }
 
        String ENBID = entity.getENBID();
        if (ENBID != null) {
            stmt.bindString(33, ENBID);
        }
 
        String PHYCELLID = entity.getPHYCELLID();
        if (PHYCELLID != null) {
            stmt.bindString(34, PHYCELLID);
        }
 
        String CELLPARAMID = entity.getCELLPARAMID();
        if (CELLPARAMID != null) {
            stmt.bindString(35, CELLPARAMID);
        }
 
        String UUID = entity.getUUID();
        if (UUID != null) {
            stmt.bindString(36, UUID);
        }
        stmt.bindLong(37, entity.getStartTime());
        stmt.bindLong(38, entity.getEndTime());
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public KCTBASESTATIONDATABean readEntity(Cursor cursor, int offset) {
        KCTBASESTATIONDATABean entity = new KCTBASESTATIONDATABean( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // crimeId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // BS_TYPE
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // IFACTIVE
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // MCC_MNC
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // LAC
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // CELL_ID
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // LOCALE_DATA_ID
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // COL_TIME
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // PCI
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // CELL
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // RSRP
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // RSSI
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // TAC
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // REG_ZONE
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // SID
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // NID
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // BASE_ID
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // CDMA_CH
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // PN
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // STRENGTH
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // BCCH
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // BSIC
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // SYS_BAND
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24), // LON
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25), // LAT
            cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26), // ERFCN
            cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27), // BAND
            cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28), // EARFCN
            cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29), // RSRQ
            cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30), // RAC
            cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31), // RNCID
            cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32), // ENBID
            cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33), // PHYCELLID
            cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34), // CELLPARAMID
            cursor.isNull(offset + 35) ? null : cursor.getString(offset + 35), // UUID
            cursor.getLong(offset + 36), // startTime
            cursor.getLong(offset + 37) // endTime
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, KCTBASESTATIONDATABean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setCrimeId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setBS_TYPE(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setIFACTIVE(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setMCC_MNC(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setLAC(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setCELL_ID(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setLOCALE_DATA_ID(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setCOL_TIME(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setPCI(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setCELL(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setRSRP(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setRSSI(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setTAC(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setREG_ZONE(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setSID(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setNID(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setBASE_ID(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setCDMA_CH(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setPN(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setSTRENGTH(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setBCCH(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setBSIC(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setSYS_BAND(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setLON(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
        entity.setLAT(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
        entity.setERFCN(cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26));
        entity.setBAND(cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27));
        entity.setEARFCN(cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28));
        entity.setRSRQ(cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29));
        entity.setRAC(cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30));
        entity.setRNCID(cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31));
        entity.setENBID(cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32));
        entity.setPHYCELLID(cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33));
        entity.setCELLPARAMID(cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34));
        entity.setUUID(cursor.isNull(offset + 35) ? null : cursor.getString(offset + 35));
        entity.setStartTime(cursor.getLong(offset + 36));
        entity.setEndTime(cursor.getLong(offset + 37));
     }
    
    @Override
    protected final String updateKeyAfterInsert(KCTBASESTATIONDATABean entity, long rowId) {
        return entity.getId();
    }
    
    @Override
    public String getKey(KCTBASESTATIONDATABean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(KCTBASESTATIONDATABean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "kctbasestationdataBeans" to-many relationship of CrimeItem. */
    public List<KCTBASESTATIONDATABean> _queryCrimeItem_KctbasestationdataBeans(String id) {
        synchronized (this) {
            if (crimeItem_KctbasestationdataBeansQuery == null) {
                QueryBuilder<KCTBASESTATIONDATABean> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.Id.eq(null));
                crimeItem_KctbasestationdataBeansQuery = queryBuilder.build();
            }
        }
        Query<KCTBASESTATIONDATABean> query = crimeItem_KctbasestationdataBeansQuery.forCurrentThread();
        query.setParameter(0, id);
        return query.list();
    }

}