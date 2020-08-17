package com.liany.csiserverapp.diagnose;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.io.Serializable;
import java.util.List;
import com.liany.csiserverapp.dao.database.greenDao.db.DaoSession;
import com.liany.csiserverapp.dao.database.greenDao.db.KCTBASESTATIONDATABeanDao;
import com.liany.csiserverapp.dao.database.greenDao.db.GoodEntityDao;
import com.liany.csiserverapp.dao.database.greenDao.db.SceneWifiInfoDao;
import com.liany.csiserverapp.dao.database.greenDao.db.WitnessEntityDao;
import com.liany.csiserverapp.dao.database.greenDao.db.PhotoDao;
import com.liany.csiserverapp.dao.database.greenDao.db.EvidenceEntityDao;
import com.liany.csiserverapp.dao.database.greenDao.db.ToolEntityDao;
import com.liany.csiserverapp.dao.database.greenDao.db.ItemEntityDao;
import com.liany.csiserverapp.dao.database.greenDao.db.ContactsEntityDao;
import com.liany.csiserverapp.dao.database.greenDao.db.CellResultItemEntityDao;
import com.liany.csiserverapp.dao.database.greenDao.db.CrimeItemDao;
import com.liany.csiserverapp.diagnose.converter.StringConverter;

/**
 * @创建者 ly
 * @创建时间 2019/3/21
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
@Entity(nameInDb = "crime")
public class CrimeItem implements Serializable {

    private static final long serialVersionUID = -8471593230949335622L;
    // 編號、日期時間、顏色、標題、內容、檔案名稱、經緯度、修改、已選擇
    //Page 1
    @Id
    private String id;
    private String sceneId;//
    private String sceneNo;//
    private String caseId;//
    private String receptionId;
    private String isUpload;//是否上传完成
    private String loginName;//登录账号
    private String userName;//真实姓名
    private String unitCode;//
    private long createTime;//
    private long updateTime;
    private String complete;//
    //    private String delete;//
    private Boolean isCollecting;//
    private Boolean isCollected;//
    @Convert(columnType = String.class, converter = StringConverter.class)
    private List<String> cellResult;//基站信息

    private double gpsLat;//纬度
    private double gpsLon;//经度
    private Boolean getLastData;//

    private String casetype;//案件类别
    private String casetypeKey;//案件类别
    private String location;//案发地点
    private String accessInspectors;//勘验人员
    private String accessInspectorsKey;//勘验人员
    private String accessReason;//勘验事由
    private String caseOccurProcess;//案发过程
    private String weatherCondition;//天气状况
    private String weatherConditionKey;
    private String windDirection;//风向
    private String windDirectionKey;//风向
    private String temperature;//温度
    private String humidity;//湿度
    private String area;//案发区域
    private String areaKey;//案发区域
    private long occurred_start_time;//案发开始时间
    private long occurred_end_time;//案发结束时间
    private long get_access_time;//接勘时间
    private String unitsAssigned;//指派单位
    private String accessPolicemen;//接警人
    private long access_start_time;//勘验开始时间
    private long access_end_time;//勘验结束时间
    private String accessLocation;//勘验地点
    private String sceneCondition;//现场条件
    private String sceneConditionKey;//现场条件
    private String changeOption;//
    private String changeOptionKey;
    private String changeReason;//
    private String illuminationCondition;//光照条件
    private String illuminationConditionKey;//光照条件
    private String productPeopleName;//保护人姓名
    private String productPeopleUnit;//保护人单位
    private String productPeopleDuties;//保护人职务
    private String safeguard;//保护措施
    private String sceneConductor;//现场指挥人员
    private String sceneConductorKey;//现场指挥人员


    //Page 1 (Cell result)
    @ToMany(referencedJoinProperty = "id")
    private List<CellResultItemEntity> cellResultItem;

    //Page 2 (New people)
    @ToMany(referencedJoinProperty = "id")
    private List<ContactsEntity> releatedPeopleItem;//联系人

    //Page 2 (New Item)
    @ToMany(referencedJoinProperty = "id")
    private List<ItemEntity> lostItem;//损失物品

    //Page 2 (New Tool)
    @ToMany(referencedJoinProperty = "id")
    private List<ToolEntity> crimeToolItem;//作案工具

    //Page 3 (Position)
    @ToMany(referencedJoinProperty = "id")
    private List<Photo> positionItem;//方位示意图

    //Page 3 (Flat)
    @ToMany(referencedJoinProperty = "id")
    private List<Photo> flatItem;//平面示意图

    @ToMany(referencedJoinProperty = "id")
    private List<Photo> dwgItem;//平面示意图

    //Page 4 (PositionPhoto)
    @ToMany(referencedJoinProperty = "id")
    private List<Photo> positionPhotoItem;//方位照片

    //Page 4 (Overview)
    @ToMany(referencedJoinProperty = "id")
    private List<Photo> overviewPhotoItem;//概貌照片

    //Page 4 (Important)
    @ToMany(referencedJoinProperty = "id")
    private List<Photo> importantPhotoItem;//重点部位

    @ToMany(referencedJoinProperty = "id")
    private List<Photo> detailPhotoItem;//细目照片

    //Page 5 (Evidence)
    @ToMany(referencedJoinProperty = "id")
    private List<EvidenceEntity> evidenceItem;//现场痕迹

    //Page 5 (Monitoring)
    @ToMany(referencedJoinProperty = "id")
    private List<Photo> monitoringPhotoItem;//监控画面

    //Page 5 (Camera)
    @ToMany(referencedJoinProperty = "id")
    private List<Photo> cameraPhotoItem;//摄像头位置

    //Page 6 (Overview)
    private String overview;//勘验情况
    private String overviewId;

    //Page 7 意见分析
    private String opinionId;//分析意见id
    private String opinionResultId;// 分析意见结果id
    private String crimePeopleNumber;//作案人数
    private String crimePeopleNumberKey;//作案人数
    private String crimePeopleNumberDesc;
    private String crimeMeans;//作案手段
    private String crimeMeansKey;//作案手段
    private String crimeMeansDesc;
    private String crimeCharacter;//案件性质
    private String crimeCharacterKey;//案件性质
    private String crimeCharacterDesc;
    private String crimeEntrance;//作案入口
    private String crimeEntranceKey;//作案入口
    private String crimeEntranceDesc;
    private String crimeTiming;//作案时机
    private String crimeTimingKey;//作案时机
    private String crimeTimingDesc;
    private String selectObject;//选择对象
    private String selectObjectKey;//选择对象
    private String selectObjectDesc;
    private String crimeExport;//作案出口
    private String crimeExportKey;//作案出口
    private String crimeExportDesc;
    private String crimePeopleFeature;//作案人特点
    private String crimePeopleFeatureKey;//作案人特点
    private String crimePeopleFeatureDesc;
    private String crimeFeature;//作案特点
    private String crimeFeatureKey;//作案特点
    private String crimeFeatureDesc;
    private String intrusiveMethod;//侵入方式
    private String intrusiveMethodKey;//侵入方式
    private String intrusiveMethodDesc;//侵入方式
    private String selectLocation;//选择处所
    private String selectLocationKey;//选择处所
    private String selectLocationDesc;//选择处所
    private String crimePurpose;//作案动机目的
    private String crimePurposeKey;//作案动机目的
    private String crimePurposeDesc;//作案动机目的

    //Page 8 (New Witness)
    @ToMany(referencedJoinProperty = "id")
    private List<WitnessEntity> witnessItem;//见证人

    @ToMany(referencedJoinProperty = "id")
    private List<SceneWifiInfo> wifiInfos;//wifi信息

    @ToMany(referencedJoinProperty = "id")
    private List<GoodEntity> goodEntities;//提取物品

//    private int comparisonState;//对比状态，0：未对比，1：对比中，2：事主对比完成，
//    private int comparisonFingerState;
//    private int comparisonFootState;

    @ToMany(referencedJoinProperty = "id")
    private List<KCTBASESTATIONDATABean> kctbasestationdataBeans;//基站数据

    private String rev1;//勘验人id
    private String rev2;
    private String rev3;
    private String rev4;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1178179243)
    private transient CrimeItemDao myDao;

    @Generated(hash = 392223013)
    public CrimeItem(String id, String sceneId, String sceneNo, String caseId, String receptionId,
            String isUpload, String loginName, String userName, String unitCode, long createTime,
            long updateTime, String complete, Boolean isCollecting, Boolean isCollected,
            List<String> cellResult, double gpsLat, double gpsLon, Boolean getLastData, String casetype,
            String casetypeKey, String location, String accessInspectors, String accessInspectorsKey,
            String accessReason, String caseOccurProcess, String weatherCondition,
            String weatherConditionKey, String windDirection, String windDirectionKey,
            String temperature, String humidity, String area, String areaKey, long occurred_start_time,
            long occurred_end_time, long get_access_time, String unitsAssigned, String accessPolicemen,
            long access_start_time, long access_end_time, String accessLocation, String sceneCondition,
            String sceneConditionKey, String changeOption, String changeOptionKey, String changeReason,
            String illuminationCondition, String illuminationConditionKey, String productPeopleName,
            String productPeopleUnit, String productPeopleDuties, String safeguard,
            String sceneConductor, String sceneConductorKey, String overview, String overviewId,
            String opinionId, String opinionResultId, String crimePeopleNumber,
            String crimePeopleNumberKey, String crimePeopleNumberDesc, String crimeMeans,
            String crimeMeansKey, String crimeMeansDesc, String crimeCharacter,
            String crimeCharacterKey, String crimeCharacterDesc, String crimeEntrance,
            String crimeEntranceKey, String crimeEntranceDesc, String crimeTiming,
            String crimeTimingKey, String crimeTimingDesc, String selectObject, String selectObjectKey,
            String selectObjectDesc, String crimeExport, String crimeExportKey, String crimeExportDesc,
            String crimePeopleFeature, String crimePeopleFeatureKey, String crimePeopleFeatureDesc,
            String crimeFeature, String crimeFeatureKey, String crimeFeatureDesc,
            String intrusiveMethod, String intrusiveMethodKey, String intrusiveMethodDesc,
            String selectLocation, String selectLocationKey, String selectLocationDesc,
            String crimePurpose, String crimePurposeKey, String crimePurposeDesc, String rev1,
            String rev2, String rev3, String rev4) {
        this.id = id;
        this.sceneId = sceneId;
        this.sceneNo = sceneNo;
        this.caseId = caseId;
        this.receptionId = receptionId;
        this.isUpload = isUpload;
        this.loginName = loginName;
        this.userName = userName;
        this.unitCode = unitCode;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.complete = complete;
        this.isCollecting = isCollecting;
        this.isCollected = isCollected;
        this.cellResult = cellResult;
        this.gpsLat = gpsLat;
        this.gpsLon = gpsLon;
        this.getLastData = getLastData;
        this.casetype = casetype;
        this.casetypeKey = casetypeKey;
        this.location = location;
        this.accessInspectors = accessInspectors;
        this.accessInspectorsKey = accessInspectorsKey;
        this.accessReason = accessReason;
        this.caseOccurProcess = caseOccurProcess;
        this.weatherCondition = weatherCondition;
        this.weatherConditionKey = weatherConditionKey;
        this.windDirection = windDirection;
        this.windDirectionKey = windDirectionKey;
        this.temperature = temperature;
        this.humidity = humidity;
        this.area = area;
        this.areaKey = areaKey;
        this.occurred_start_time = occurred_start_time;
        this.occurred_end_time = occurred_end_time;
        this.get_access_time = get_access_time;
        this.unitsAssigned = unitsAssigned;
        this.accessPolicemen = accessPolicemen;
        this.access_start_time = access_start_time;
        this.access_end_time = access_end_time;
        this.accessLocation = accessLocation;
        this.sceneCondition = sceneCondition;
        this.sceneConditionKey = sceneConditionKey;
        this.changeOption = changeOption;
        this.changeOptionKey = changeOptionKey;
        this.changeReason = changeReason;
        this.illuminationCondition = illuminationCondition;
        this.illuminationConditionKey = illuminationConditionKey;
        this.productPeopleName = productPeopleName;
        this.productPeopleUnit = productPeopleUnit;
        this.productPeopleDuties = productPeopleDuties;
        this.safeguard = safeguard;
        this.sceneConductor = sceneConductor;
        this.sceneConductorKey = sceneConductorKey;
        this.overview = overview;
        this.overviewId = overviewId;
        this.opinionId = opinionId;
        this.opinionResultId = opinionResultId;
        this.crimePeopleNumber = crimePeopleNumber;
        this.crimePeopleNumberKey = crimePeopleNumberKey;
        this.crimePeopleNumberDesc = crimePeopleNumberDesc;
        this.crimeMeans = crimeMeans;
        this.crimeMeansKey = crimeMeansKey;
        this.crimeMeansDesc = crimeMeansDesc;
        this.crimeCharacter = crimeCharacter;
        this.crimeCharacterKey = crimeCharacterKey;
        this.crimeCharacterDesc = crimeCharacterDesc;
        this.crimeEntrance = crimeEntrance;
        this.crimeEntranceKey = crimeEntranceKey;
        this.crimeEntranceDesc = crimeEntranceDesc;
        this.crimeTiming = crimeTiming;
        this.crimeTimingKey = crimeTimingKey;
        this.crimeTimingDesc = crimeTimingDesc;
        this.selectObject = selectObject;
        this.selectObjectKey = selectObjectKey;
        this.selectObjectDesc = selectObjectDesc;
        this.crimeExport = crimeExport;
        this.crimeExportKey = crimeExportKey;
        this.crimeExportDesc = crimeExportDesc;
        this.crimePeopleFeature = crimePeopleFeature;
        this.crimePeopleFeatureKey = crimePeopleFeatureKey;
        this.crimePeopleFeatureDesc = crimePeopleFeatureDesc;
        this.crimeFeature = crimeFeature;
        this.crimeFeatureKey = crimeFeatureKey;
        this.crimeFeatureDesc = crimeFeatureDesc;
        this.intrusiveMethod = intrusiveMethod;
        this.intrusiveMethodKey = intrusiveMethodKey;
        this.intrusiveMethodDesc = intrusiveMethodDesc;
        this.selectLocation = selectLocation;
        this.selectLocationKey = selectLocationKey;
        this.selectLocationDesc = selectLocationDesc;
        this.crimePurpose = crimePurpose;
        this.crimePurposeKey = crimePurposeKey;
        this.crimePurposeDesc = crimePurposeDesc;
        this.rev1 = rev1;
        this.rev2 = rev2;
        this.rev3 = rev3;
        this.rev4 = rev4;
    }

    @Generated(hash = 1038680592)
    public CrimeItem() {
    }



    public void setCellResultItem(List<CellResultItemEntity> cellResultItem) {
        this.cellResultItem = cellResultItem;
    }

    public void setReleatedPeopleItem(List<ContactsEntity> releatedPeopleItem) {
        this.releatedPeopleItem = releatedPeopleItem;
    }

    public void setLostItem(List<ItemEntity> lostItem) {
        this.lostItem = lostItem;
    }

    public void setCrimeToolItem(List<ToolEntity> crimeToolItem) {
        this.crimeToolItem = crimeToolItem;
    }

    public void setPositionItem(List<Photo> positionItem) {
        this.positionItem = positionItem;
    }

    public void setFlatItem(List<Photo> flatItem) {
        this.flatItem = flatItem;
    }

    public void setDwgItem(List<Photo> dwgItem) {
        this.dwgItem = dwgItem;
    }

    public void setPositionPhotoItem(List<Photo> positionPhotoItem) {
        this.positionPhotoItem = positionPhotoItem;
    }

    public void setOverviewPhotoItem(List<Photo> overviewPhotoItem) {
        this.overviewPhotoItem = overviewPhotoItem;
    }

    public void setImportantPhotoItem(List<Photo> importantPhotoItem) {
        this.importantPhotoItem = importantPhotoItem;
    }

    public void setDetailPhotoItem(List<Photo> detailPhotoItem) {
        this.detailPhotoItem = detailPhotoItem;
    }

    public void setEvidenceItem(List<EvidenceEntity> evidenceItem) {
        this.evidenceItem = evidenceItem;
    }

    public void setMonitoringPhotoItem(List<Photo> monitoringPhotoItem) {
        this.monitoringPhotoItem = monitoringPhotoItem;
    }

    public void setCameraPhotoItem(List<Photo> cameraPhotoItem) {
        this.cameraPhotoItem = cameraPhotoItem;
    }

    public void setWitnessItem(List<WitnessEntity> witnessItem) {
        this.witnessItem = witnessItem;
    }

    public void setWifiInfos(List<SceneWifiInfo> wifiInfos) {
        this.wifiInfos = wifiInfos;
    }

    public void setGoodEntities(List<GoodEntity> goodEntities) {
        this.goodEntities = goodEntities;
    }

    public void setKctbasestationdataBeans(List<KCTBASESTATIONDATABean> kctbasestationdataBeans) {
        this.kctbasestationdataBeans = kctbasestationdataBeans;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSceneId() {
        return this.sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public String getSceneNo() {
        return this.sceneNo;
    }

    public void setSceneNo(String sceneNo) {
        this.sceneNo = sceneNo;
    }

    public String getCaseId() {
        return this.caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getReceptionId() {
        return this.receptionId;
    }

    public void setReceptionId(String receptionId) {
        this.receptionId = receptionId;
    }

    public String getIsUpload() {
        return this.isUpload;
    }

    public void setIsUpload(String isUpload) {
        this.isUpload = isUpload;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUnitCode() {
        return this.unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getComplete() {
        return this.complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public Boolean getIsCollecting() {
        return this.isCollecting;
    }

    public void setIsCollecting(Boolean isCollecting) {
        this.isCollecting = isCollecting;
    }

    public Boolean getIsCollected() {
        return this.isCollected;
    }

    public void setIsCollected(Boolean isCollected) {
        this.isCollected = isCollected;
    }

    public List<String> getCellResult() {
        return this.cellResult;
    }

    public void setCellResult(List<String> cellResult) {
        this.cellResult = cellResult;
    }

    public double getGpsLat() {
        return this.gpsLat;
    }

    public void setGpsLat(double gpsLat) {
        this.gpsLat = gpsLat;
    }

    public double getGpsLon() {
        return this.gpsLon;
    }

    public void setGpsLon(double gpsLon) {
        this.gpsLon = gpsLon;
    }

    public Boolean getGetLastData() {
        return this.getLastData;
    }

    public void setGetLastData(Boolean getLastData) {
        this.getLastData = getLastData;
    }

    public String getCasetype() {
        return this.casetype;
    }

    public void setCasetype(String casetype) {
        this.casetype = casetype;
    }

    public String getCasetypeKey() {
        return this.casetypeKey;
    }

    public void setCasetypeKey(String casetypeKey) {
        this.casetypeKey = casetypeKey;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAccessInspectors() {
        return this.accessInspectors;
    }

    public void setAccessInspectors(String accessInspectors) {
        this.accessInspectors = accessInspectors;
    }

    public String getAccessInspectorsKey() {
        return this.accessInspectorsKey;
    }

    public void setAccessInspectorsKey(String accessInspectorsKey) {
        this.accessInspectorsKey = accessInspectorsKey;
    }

    public String getAccessReason() {
        return this.accessReason;
    }

    public void setAccessReason(String accessReason) {
        this.accessReason = accessReason;
    }

    public String getCaseOccurProcess() {
        return this.caseOccurProcess;
    }

    public void setCaseOccurProcess(String caseOccurProcess) {
        this.caseOccurProcess = caseOccurProcess;
    }

    public String getWeatherCondition() {
        return this.weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public String getWeatherConditionKey() {
        return this.weatherConditionKey;
    }

    public void setWeatherConditionKey(String weatherConditionKey) {
        this.weatherConditionKey = weatherConditionKey;
    }

    public String getWindDirection() {
        return this.windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getWindDirectionKey() {
        return this.windDirectionKey;
    }

    public void setWindDirectionKey(String windDirectionKey) {
        this.windDirectionKey = windDirectionKey;
    }

    public String getTemperature() {
        return this.temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return this.humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaKey() {
        return this.areaKey;
    }

    public void setAreaKey(String areaKey) {
        this.areaKey = areaKey;
    }

    public long getOccurred_start_time() {
        return this.occurred_start_time;
    }

    public void setOccurred_start_time(long occurred_start_time) {
        this.occurred_start_time = occurred_start_time;
    }

    public long getOccurred_end_time() {
        return this.occurred_end_time;
    }

    public void setOccurred_end_time(long occurred_end_time) {
        this.occurred_end_time = occurred_end_time;
    }

    public long getGet_access_time() {
        return this.get_access_time;
    }

    public void setGet_access_time(long get_access_time) {
        this.get_access_time = get_access_time;
    }

    public String getUnitsAssigned() {
        return this.unitsAssigned;
    }

    public void setUnitsAssigned(String unitsAssigned) {
        this.unitsAssigned = unitsAssigned;
    }

    public String getAccessPolicemen() {
        return this.accessPolicemen;
    }

    public void setAccessPolicemen(String accessPolicemen) {
        this.accessPolicemen = accessPolicemen;
    }

    public long getAccess_start_time() {
        return this.access_start_time;
    }

    public void setAccess_start_time(long access_start_time) {
        this.access_start_time = access_start_time;
    }

    public long getAccess_end_time() {
        return this.access_end_time;
    }

    public void setAccess_end_time(long access_end_time) {
        this.access_end_time = access_end_time;
    }

    public String getAccessLocation() {
        return this.accessLocation;
    }

    public void setAccessLocation(String accessLocation) {
        this.accessLocation = accessLocation;
    }

    public String getSceneCondition() {
        return this.sceneCondition;
    }

    public void setSceneCondition(String sceneCondition) {
        this.sceneCondition = sceneCondition;
    }

    public String getSceneConditionKey() {
        return this.sceneConditionKey;
    }

    public void setSceneConditionKey(String sceneConditionKey) {
        this.sceneConditionKey = sceneConditionKey;
    }

    public String getChangeOption() {
        return this.changeOption;
    }

    public void setChangeOption(String changeOption) {
        this.changeOption = changeOption;
    }

    public String getChangeOptionKey() {
        return this.changeOptionKey;
    }

    public void setChangeOptionKey(String changeOptionKey) {
        this.changeOptionKey = changeOptionKey;
    }

    public String getChangeReason() {
        return this.changeReason;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    public String getIlluminationCondition() {
        return this.illuminationCondition;
    }

    public void setIlluminationCondition(String illuminationCondition) {
        this.illuminationCondition = illuminationCondition;
    }

    public String getIlluminationConditionKey() {
        return this.illuminationConditionKey;
    }

    public void setIlluminationConditionKey(String illuminationConditionKey) {
        this.illuminationConditionKey = illuminationConditionKey;
    }

    public String getProductPeopleName() {
        return this.productPeopleName;
    }

    public void setProductPeopleName(String productPeopleName) {
        this.productPeopleName = productPeopleName;
    }

    public String getProductPeopleUnit() {
        return this.productPeopleUnit;
    }

    public void setProductPeopleUnit(String productPeopleUnit) {
        this.productPeopleUnit = productPeopleUnit;
    }

    public String getProductPeopleDuties() {
        return this.productPeopleDuties;
    }

    public void setProductPeopleDuties(String productPeopleDuties) {
        this.productPeopleDuties = productPeopleDuties;
    }

    public String getSafeguard() {
        return this.safeguard;
    }

    public void setSafeguard(String safeguard) {
        this.safeguard = safeguard;
    }

    public String getSceneConductor() {
        return this.sceneConductor;
    }

    public void setSceneConductor(String sceneConductor) {
        this.sceneConductor = sceneConductor;
    }

    public String getSceneConductorKey() {
        return this.sceneConductorKey;
    }

    public void setSceneConductorKey(String sceneConductorKey) {
        this.sceneConductorKey = sceneConductorKey;
    }

    public String getOverview() {
        return this.overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOverviewId() {
        return this.overviewId;
    }

    public void setOverviewId(String overviewId) {
        this.overviewId = overviewId;
    }

    public String getOpinionId() {
        return this.opinionId;
    }

    public void setOpinionId(String opinionId) {
        this.opinionId = opinionId;
    }

    public String getOpinionResultId() {
        return this.opinionResultId;
    }

    public void setOpinionResultId(String opinionResultId) {
        this.opinionResultId = opinionResultId;
    }

    public String getCrimePeopleNumber() {
        return this.crimePeopleNumber;
    }

    public void setCrimePeopleNumber(String crimePeopleNumber) {
        this.crimePeopleNumber = crimePeopleNumber;
    }

    public String getCrimePeopleNumberKey() {
        return this.crimePeopleNumberKey;
    }

    public void setCrimePeopleNumberKey(String crimePeopleNumberKey) {
        this.crimePeopleNumberKey = crimePeopleNumberKey;
    }

    public String getCrimePeopleNumberDesc() {
        return this.crimePeopleNumberDesc;
    }

    public void setCrimePeopleNumberDesc(String crimePeopleNumberDesc) {
        this.crimePeopleNumberDesc = crimePeopleNumberDesc;
    }

    public String getCrimeMeans() {
        return this.crimeMeans;
    }

    public void setCrimeMeans(String crimeMeans) {
        this.crimeMeans = crimeMeans;
    }

    public String getCrimeMeansKey() {
        return this.crimeMeansKey;
    }

    public void setCrimeMeansKey(String crimeMeansKey) {
        this.crimeMeansKey = crimeMeansKey;
    }

    public String getCrimeMeansDesc() {
        return this.crimeMeansDesc;
    }

    public void setCrimeMeansDesc(String crimeMeansDesc) {
        this.crimeMeansDesc = crimeMeansDesc;
    }

    public String getCrimeCharacter() {
        return this.crimeCharacter;
    }

    public void setCrimeCharacter(String crimeCharacter) {
        this.crimeCharacter = crimeCharacter;
    }

    public String getCrimeCharacterKey() {
        return this.crimeCharacterKey;
    }

    public void setCrimeCharacterKey(String crimeCharacterKey) {
        this.crimeCharacterKey = crimeCharacterKey;
    }

    public String getCrimeCharacterDesc() {
        return this.crimeCharacterDesc;
    }

    public void setCrimeCharacterDesc(String crimeCharacterDesc) {
        this.crimeCharacterDesc = crimeCharacterDesc;
    }

    public String getCrimeEntrance() {
        return this.crimeEntrance;
    }

    public void setCrimeEntrance(String crimeEntrance) {
        this.crimeEntrance = crimeEntrance;
    }

    public String getCrimeEntranceKey() {
        return this.crimeEntranceKey;
    }

    public void setCrimeEntranceKey(String crimeEntranceKey) {
        this.crimeEntranceKey = crimeEntranceKey;
    }

    public String getCrimeEntranceDesc() {
        return this.crimeEntranceDesc;
    }

    public void setCrimeEntranceDesc(String crimeEntranceDesc) {
        this.crimeEntranceDesc = crimeEntranceDesc;
    }

    public String getCrimeTiming() {
        return this.crimeTiming;
    }

    public void setCrimeTiming(String crimeTiming) {
        this.crimeTiming = crimeTiming;
    }

    public String getCrimeTimingKey() {
        return this.crimeTimingKey;
    }

    public void setCrimeTimingKey(String crimeTimingKey) {
        this.crimeTimingKey = crimeTimingKey;
    }

    public String getCrimeTimingDesc() {
        return this.crimeTimingDesc;
    }

    public void setCrimeTimingDesc(String crimeTimingDesc) {
        this.crimeTimingDesc = crimeTimingDesc;
    }

    public String getSelectObject() {
        return this.selectObject;
    }

    public void setSelectObject(String selectObject) {
        this.selectObject = selectObject;
    }

    public String getSelectObjectKey() {
        return this.selectObjectKey;
    }

    public void setSelectObjectKey(String selectObjectKey) {
        this.selectObjectKey = selectObjectKey;
    }

    public String getSelectObjectDesc() {
        return this.selectObjectDesc;
    }

    public void setSelectObjectDesc(String selectObjectDesc) {
        this.selectObjectDesc = selectObjectDesc;
    }

    public String getCrimeExport() {
        return this.crimeExport;
    }

    public void setCrimeExport(String crimeExport) {
        this.crimeExport = crimeExport;
    }

    public String getCrimeExportKey() {
        return this.crimeExportKey;
    }

    public void setCrimeExportKey(String crimeExportKey) {
        this.crimeExportKey = crimeExportKey;
    }

    public String getCrimeExportDesc() {
        return this.crimeExportDesc;
    }

    public void setCrimeExportDesc(String crimeExportDesc) {
        this.crimeExportDesc = crimeExportDesc;
    }

    public String getCrimePeopleFeature() {
        return this.crimePeopleFeature;
    }

    public void setCrimePeopleFeature(String crimePeopleFeature) {
        this.crimePeopleFeature = crimePeopleFeature;
    }

    public String getCrimePeopleFeatureKey() {
        return this.crimePeopleFeatureKey;
    }

    public void setCrimePeopleFeatureKey(String crimePeopleFeatureKey) {
        this.crimePeopleFeatureKey = crimePeopleFeatureKey;
    }

    public String getCrimePeopleFeatureDesc() {
        return this.crimePeopleFeatureDesc;
    }

    public void setCrimePeopleFeatureDesc(String crimePeopleFeatureDesc) {
        this.crimePeopleFeatureDesc = crimePeopleFeatureDesc;
    }

    public String getCrimeFeature() {
        return this.crimeFeature;
    }

    public void setCrimeFeature(String crimeFeature) {
        this.crimeFeature = crimeFeature;
    }

    public String getCrimeFeatureKey() {
        return this.crimeFeatureKey;
    }

    public void setCrimeFeatureKey(String crimeFeatureKey) {
        this.crimeFeatureKey = crimeFeatureKey;
    }

    public String getCrimeFeatureDesc() {
        return this.crimeFeatureDesc;
    }

    public void setCrimeFeatureDesc(String crimeFeatureDesc) {
        this.crimeFeatureDesc = crimeFeatureDesc;
    }

    public String getIntrusiveMethod() {
        return this.intrusiveMethod;
    }

    public void setIntrusiveMethod(String intrusiveMethod) {
        this.intrusiveMethod = intrusiveMethod;
    }

    public String getIntrusiveMethodKey() {
        return this.intrusiveMethodKey;
    }

    public void setIntrusiveMethodKey(String intrusiveMethodKey) {
        this.intrusiveMethodKey = intrusiveMethodKey;
    }

    public String getIntrusiveMethodDesc() {
        return this.intrusiveMethodDesc;
    }

    public void setIntrusiveMethodDesc(String intrusiveMethodDesc) {
        this.intrusiveMethodDesc = intrusiveMethodDesc;
    }

    public String getSelectLocation() {
        return this.selectLocation;
    }

    public void setSelectLocation(String selectLocation) {
        this.selectLocation = selectLocation;
    }

    public String getSelectLocationKey() {
        return this.selectLocationKey;
    }

    public void setSelectLocationKey(String selectLocationKey) {
        this.selectLocationKey = selectLocationKey;
    }

    public String getSelectLocationDesc() {
        return this.selectLocationDesc;
    }

    public void setSelectLocationDesc(String selectLocationDesc) {
        this.selectLocationDesc = selectLocationDesc;
    }

    public String getCrimePurpose() {
        return this.crimePurpose;
    }

    public void setCrimePurpose(String crimePurpose) {
        this.crimePurpose = crimePurpose;
    }

    public String getCrimePurposeKey() {
        return this.crimePurposeKey;
    }

    public void setCrimePurposeKey(String crimePurposeKey) {
        this.crimePurposeKey = crimePurposeKey;
    }

    public String getCrimePurposeDesc() {
        return this.crimePurposeDesc;
    }

    public void setCrimePurposeDesc(String crimePurposeDesc) {
        this.crimePurposeDesc = crimePurposeDesc;
    }

    public String getRev1() {
        return this.rev1;
    }

    public void setRev1(String rev1) {
        this.rev1 = rev1;
    }

    public String getRev2() {
        return this.rev2;
    }

    public void setRev2(String rev2) {
        this.rev2 = rev2;
    }

    public String getRev3() {
        return this.rev3;
    }

    public void setRev3(String rev3) {
        this.rev3 = rev3;
    }

    public String getRev4() {
        return this.rev4;
    }

    public void setRev4(String rev4) {
        this.rev4 = rev4;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1509014149)
    public List<CellResultItemEntity> getCellResultItem() {
        if (cellResultItem == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CellResultItemEntityDao targetDao = daoSession.getCellResultItemEntityDao();
            List<CellResultItemEntity> cellResultItemNew = targetDao._queryCrimeItem_CellResultItem(id);
            synchronized (this) {
                if (cellResultItem == null) {
                    cellResultItem = cellResultItemNew;
                }
            }
        }
        return cellResultItem;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1105314124)
    public synchronized void resetCellResultItem() {
        cellResultItem = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1574175380)
    public List<ContactsEntity> getReleatedPeopleItem() {
        if (releatedPeopleItem == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ContactsEntityDao targetDao = daoSession.getContactsEntityDao();
            List<ContactsEntity> releatedPeopleItemNew = targetDao
                    ._queryCrimeItem_ReleatedPeopleItem(id);
            synchronized (this) {
                if (releatedPeopleItem == null) {
                    releatedPeopleItem = releatedPeopleItemNew;
                }
            }
        }
        return releatedPeopleItem;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 35472814)
    public synchronized void resetReleatedPeopleItem() {
        releatedPeopleItem = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 697932528)
    public List<ItemEntity> getLostItem() {
        if (lostItem == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ItemEntityDao targetDao = daoSession.getItemEntityDao();
            List<ItemEntity> lostItemNew = targetDao._queryCrimeItem_LostItem(id);
            synchronized (this) {
                if (lostItem == null) {
                    lostItem = lostItemNew;
                }
            }
        }
        return lostItem;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 229545918)
    public synchronized void resetLostItem() {
        lostItem = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1506293263)
    public List<ToolEntity> getCrimeToolItem() {
        if (crimeToolItem == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ToolEntityDao targetDao = daoSession.getToolEntityDao();
            List<ToolEntity> crimeToolItemNew = targetDao._queryCrimeItem_CrimeToolItem(id);
            synchronized (this) {
                if (crimeToolItem == null) {
                    crimeToolItem = crimeToolItemNew;
                }
            }
        }
        return crimeToolItem;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 177126564)
    public synchronized void resetCrimeToolItem() {
        crimeToolItem = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 450052105)
    public List<Photo> getPositionItem() {
        if (positionItem == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PhotoDao targetDao = daoSession.getPhotoDao();
            List<Photo> positionItemNew = targetDao._queryCrimeItem_PositionItem(id);
            synchronized (this) {
                if (positionItem == null) {
                    positionItem = positionItemNew;
                }
            }
        }
        return positionItem;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 2109288846)
    public synchronized void resetPositionItem() {
        positionItem = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 155997471)
    public List<Photo> getFlatItem() {
        if (flatItem == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PhotoDao targetDao = daoSession.getPhotoDao();
            List<Photo> flatItemNew = targetDao._queryCrimeItem_FlatItem(id);
            synchronized (this) {
                if (flatItem == null) {
                    flatItem = flatItemNew;
                }
            }
        }
        return flatItem;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 915336549)
    public synchronized void resetFlatItem() {
        flatItem = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1236136978)
    public List<Photo> getDwgItem() {
        if (dwgItem == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PhotoDao targetDao = daoSession.getPhotoDao();
            List<Photo> dwgItemNew = targetDao._queryCrimeItem_DwgItem(id);
            synchronized (this) {
                if (dwgItem == null) {
                    dwgItem = dwgItemNew;
                }
            }
        }
        return dwgItem;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1151749273)
    public synchronized void resetDwgItem() {
        dwgItem = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 635645790)
    public List<Photo> getPositionPhotoItem() {
        if (positionPhotoItem == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PhotoDao targetDao = daoSession.getPhotoDao();
            List<Photo> positionPhotoItemNew = targetDao._queryCrimeItem_PositionPhotoItem(id);
            synchronized (this) {
                if (positionPhotoItem == null) {
                    positionPhotoItem = positionPhotoItemNew;
                }
            }
        }
        return positionPhotoItem;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 661805259)
    public synchronized void resetPositionPhotoItem() {
        positionPhotoItem = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1884988317)
    public List<Photo> getOverviewPhotoItem() {
        if (overviewPhotoItem == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PhotoDao targetDao = daoSession.getPhotoDao();
            List<Photo> overviewPhotoItemNew = targetDao._queryCrimeItem_OverviewPhotoItem(id);
            synchronized (this) {
                if (overviewPhotoItem == null) {
                    overviewPhotoItem = overviewPhotoItemNew;
                }
            }
        }
        return overviewPhotoItem;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 617425465)
    public synchronized void resetOverviewPhotoItem() {
        overviewPhotoItem = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1373988775)
    public List<Photo> getImportantPhotoItem() {
        if (importantPhotoItem == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PhotoDao targetDao = daoSession.getPhotoDao();
            List<Photo> importantPhotoItemNew = targetDao._queryCrimeItem_ImportantPhotoItem(id);
            synchronized (this) {
                if (importantPhotoItem == null) {
                    importantPhotoItem = importantPhotoItemNew;
                }
            }
        }
        return importantPhotoItem;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1295731436)
    public synchronized void resetImportantPhotoItem() {
        importantPhotoItem = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1638746532)
    public List<Photo> getDetailPhotoItem() {
        if (detailPhotoItem == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PhotoDao targetDao = daoSession.getPhotoDao();
            List<Photo> detailPhotoItemNew = targetDao._queryCrimeItem_DetailPhotoItem(id);
            synchronized (this) {
                if (detailPhotoItem == null) {
                    detailPhotoItem = detailPhotoItemNew;
                }
            }
        }
        return detailPhotoItem;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1818070714)
    public synchronized void resetDetailPhotoItem() {
        detailPhotoItem = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1359639471)
    public List<EvidenceEntity> getEvidenceItem() {
        if (evidenceItem == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            EvidenceEntityDao targetDao = daoSession.getEvidenceEntityDao();
            List<EvidenceEntity> evidenceItemNew = targetDao._queryCrimeItem_EvidenceItem(id);
            synchronized (this) {
                if (evidenceItem == null) {
                    evidenceItem = evidenceItemNew;
                }
            }
        }
        return evidenceItem;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 2013709818)
    public synchronized void resetEvidenceItem() {
        evidenceItem = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1560618605)
    public List<Photo> getMonitoringPhotoItem() {
        if (monitoringPhotoItem == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PhotoDao targetDao = daoSession.getPhotoDao();
            List<Photo> monitoringPhotoItemNew = targetDao._queryCrimeItem_MonitoringPhotoItem(id);
            synchronized (this) {
                if (monitoringPhotoItem == null) {
                    monitoringPhotoItem = monitoringPhotoItemNew;
                }
            }
        }
        return monitoringPhotoItem;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1067819398)
    public synchronized void resetMonitoringPhotoItem() {
        monitoringPhotoItem = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1585294975)
    public List<Photo> getCameraPhotoItem() {
        if (cameraPhotoItem == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PhotoDao targetDao = daoSession.getPhotoDao();
            List<Photo> cameraPhotoItemNew = targetDao._queryCrimeItem_CameraPhotoItem(id);
            synchronized (this) {
                if (cameraPhotoItem == null) {
                    cameraPhotoItem = cameraPhotoItemNew;
                }
            }
        }
        return cameraPhotoItem;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 254067597)
    public synchronized void resetCameraPhotoItem() {
        cameraPhotoItem = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1824340434)
    public List<WitnessEntity> getWitnessItem() {
        if (witnessItem == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            WitnessEntityDao targetDao = daoSession.getWitnessEntityDao();
            List<WitnessEntity> witnessItemNew = targetDao._queryCrimeItem_WitnessItem(id);
            synchronized (this) {
                if (witnessItem == null) {
                    witnessItem = witnessItemNew;
                }
            }
        }
        return witnessItem;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 725672874)
    public synchronized void resetWitnessItem() {
        witnessItem = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1559426941)
    public List<SceneWifiInfo> getWifiInfos() {
        if (wifiInfos == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SceneWifiInfoDao targetDao = daoSession.getSceneWifiInfoDao();
            List<SceneWifiInfo> wifiInfosNew = targetDao._queryCrimeItem_WifiInfos(id);
            synchronized (this) {
                if (wifiInfos == null) {
                    wifiInfos = wifiInfosNew;
                }
            }
        }
        return wifiInfos;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 833895091)
    public synchronized void resetWifiInfos() {
        wifiInfos = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1161505564)
    public List<GoodEntity> getGoodEntities() {
        if (goodEntities == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GoodEntityDao targetDao = daoSession.getGoodEntityDao();
            List<GoodEntity> goodEntitiesNew = targetDao._queryCrimeItem_GoodEntities(id);
            synchronized (this) {
                if (goodEntities == null) {
                    goodEntities = goodEntitiesNew;
                }
            }
        }
        return goodEntities;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1921564397)
    public synchronized void resetGoodEntities() {
        goodEntities = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 754436839)
    public List<KCTBASESTATIONDATABean> getKctbasestationdataBeans() {
        if (kctbasestationdataBeans == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            KCTBASESTATIONDATABeanDao targetDao = daoSession.getKCTBASESTATIONDATABeanDao();
            List<KCTBASESTATIONDATABean> kctbasestationdataBeansNew = targetDao
                    ._queryCrimeItem_KctbasestationdataBeans(id);
            synchronized (this) {
                if (kctbasestationdataBeans == null) {
                    kctbasestationdataBeans = kctbasestationdataBeansNew;
                }
            }
        }
        return kctbasestationdataBeans;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 288803515)
    public synchronized void resetKctbasestationdataBeans() {
        kctbasestationdataBeans = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1893891145)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCrimeItemDao() : null;
    }
}