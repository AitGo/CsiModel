package com.liany.csiclient.diagnose;

import java.io.Serializable;
import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2019/3/21
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class CrimeItem implements Serializable {

    private static final long serialVersionUID = -8471593230949335622L;
    // 編號、日期時間、顏色、標題、內容、檔案名稱、經緯度、修改、已選擇
    //Page 1
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
    private List<CellResultItemEntity> cellResultItem;

    //Page 2 (New people)
    private List<ContactsEntity> releatedPeopleItem;//联系人

    //Page 2 (New Item)
    private List<ItemEntity> lostItem;//损失物品

    //Page 2 (New Tool)
    private List<ToolEntity> crimeToolItem;//作案工具

    //Page 3 (Position)
    private List<Photo> positionItem;//方位示意图

    //Page 3 (Flat)
    private List<Photo> flatItem;//平面示意图

    private List<Photo> dwgItem;//平面示意图

    //Page 4 (PositionPhoto)
    private List<Photo> positionPhotoItem;//方位照片

    //Page 4 (Overview)
    private List<Photo> overviewPhotoItem;//概貌照片

    //Page 4 (Important)
    private List<Photo> importantPhotoItem;//重点部位

    private List<Photo> detailPhotoItem;//细目照片

    //Page 5 (Evidence)
    private List<EvidenceEntity> evidenceItem;//现场痕迹

    //Page 5 (Monitoring)
    private List<Photo> monitoringPhotoItem;//监控画面

    //Page 5 (Camera)
    private List<Photo> cameraPhotoItem;//摄像头位置

    //Page 6 (Overview)
    private String overview;//勘验情况
    private String overviewId;

    //Page 7 意见分析
    private String opinionId;//分析意见id
    private String opinionResultId;// 分析意见结果id
    private String crimePeopleNumber;//作案人数
    private String crimePeopleNumberKey;//作案人数
    private String crimeMeans;//作案手段
    private String crimeMeansKey;//作案手段
    private String crimeCharacter;//案件性质
    private String crimeCharacterKey;//案件性质
    private String crimeEntrance;//作案入口
    private String crimeEntranceKey;//作案入口
    private String crimeTiming;//作案时机
    private String crimeTimingKey;//作案时机
    private String selectObject;//选择对象
    private String selectObjectKey;//选择对象
    private String crimeExport;//作案出口
    private String crimeExportKey;//作案出口
    private String crimePeopleFeature;//作案人特点
    private String crimePeopleFeatureKey;//作案人特点
    private String crimeFeature;//作案特点
    private String crimeFeatureKey;//作案特点
    private String intrusiveMethod;//侵入方式
    private String intrusiveMethodKey;//侵入方式
    private String selectLocation;//选择处所
    private String selectLocationKey;//选择处所
    private String crimePurpose;//作案动机目的
    private String crimePurposeKey;//作案动机目的

    //Page 8 (New Witness)
    private List<WitnessEntity> witnessItem;//见证人

    private List<SceneWifiInfo> wifiInfos;//wifi信息

    private List<GoodEntity> goodEntities;//提取物品

//    private int comparisonState;//对比状态，0：未对比，1：对比中，2：事主对比完成，
//    private int comparisonFingerState;
//    private int comparisonFootState;

    private List<KCTBASESTATIONDATABean> kctbasestationdataBeans;//基站数据

    private String rev1;//勘验人id
    private String rev2;
    private String rev3;
    private String rev4;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public String getSceneNo() {
        return sceneNo;
    }

    public void setSceneNo(String sceneNo) {
        this.sceneNo = sceneNo;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getReceptionId() {
        return receptionId;
    }

    public void setReceptionId(String receptionId) {
        this.receptionId = receptionId;
    }

    public String getIsUpload() {
        return isUpload;
    }

    public void setIsUpload(String isUpload) {
        this.isUpload = isUpload;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public Boolean getCollecting() {
        return isCollecting;
    }

    public void setCollecting(Boolean collecting) {
        isCollecting = collecting;
    }

    public Boolean getCollected() {
        return isCollected;
    }

    public void setCollected(Boolean collected) {
        isCollected = collected;
    }

    public List<String> getCellResult() {
        return cellResult;
    }

    public void setCellResult(List<String> cellResult) {
        this.cellResult = cellResult;
    }

    public double getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(double gpsLat) {
        this.gpsLat = gpsLat;
    }

    public double getGpsLon() {
        return gpsLon;
    }

    public void setGpsLon(double gpsLon) {
        this.gpsLon = gpsLon;
    }

    public Boolean getGetLastData() {
        return getLastData;
    }

    public void setGetLastData(Boolean getLastData) {
        this.getLastData = getLastData;
    }

    public String getCasetype() {
        return casetype;
    }

    public void setCasetype(String casetype) {
        this.casetype = casetype;
    }

    public String getCasetypeKey() {
        return casetypeKey;
    }

    public void setCasetypeKey(String casetypeKey) {
        this.casetypeKey = casetypeKey;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAccessInspectors() {
        return accessInspectors;
    }

    public void setAccessInspectors(String accessInspectors) {
        this.accessInspectors = accessInspectors;
    }

    public String getAccessInspectorsKey() {
        return accessInspectorsKey;
    }

    public void setAccessInspectorsKey(String accessInspectorsKey) {
        this.accessInspectorsKey = accessInspectorsKey;
    }

    public String getAccessReason() {
        return accessReason;
    }

    public void setAccessReason(String accessReason) {
        this.accessReason = accessReason;
    }

    public String getCaseOccurProcess() {
        return caseOccurProcess;
    }

    public void setCaseOccurProcess(String caseOccurProcess) {
        this.caseOccurProcess = caseOccurProcess;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public String getWeatherConditionKey() {
        return weatherConditionKey;
    }

    public void setWeatherConditionKey(String weatherConditionKey) {
        this.weatherConditionKey = weatherConditionKey;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getWindDirectionKey() {
        return windDirectionKey;
    }

    public void setWindDirectionKey(String windDirectionKey) {
        this.windDirectionKey = windDirectionKey;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaKey() {
        return areaKey;
    }

    public void setAreaKey(String areaKey) {
        this.areaKey = areaKey;
    }

    public long getOccurred_start_time() {
        return occurred_start_time;
    }

    public void setOccurred_start_time(long occurred_start_time) {
        this.occurred_start_time = occurred_start_time;
    }

    public long getOccurred_end_time() {
        return occurred_end_time;
    }

    public void setOccurred_end_time(long occurred_end_time) {
        this.occurred_end_time = occurred_end_time;
    }

    public long getGet_access_time() {
        return get_access_time;
    }

    public void setGet_access_time(long get_access_time) {
        this.get_access_time = get_access_time;
    }

    public String getUnitsAssigned() {
        return unitsAssigned;
    }

    public void setUnitsAssigned(String unitsAssigned) {
        this.unitsAssigned = unitsAssigned;
    }

    public String getAccessPolicemen() {
        return accessPolicemen;
    }

    public void setAccessPolicemen(String accessPolicemen) {
        this.accessPolicemen = accessPolicemen;
    }

    public long getAccess_start_time() {
        return access_start_time;
    }

    public void setAccess_start_time(long access_start_time) {
        this.access_start_time = access_start_time;
    }

    public long getAccess_end_time() {
        return access_end_time;
    }

    public void setAccess_end_time(long access_end_time) {
        this.access_end_time = access_end_time;
    }

    public String getAccessLocation() {
        return accessLocation;
    }

    public void setAccessLocation(String accessLocation) {
        this.accessLocation = accessLocation;
    }

    public String getSceneCondition() {
        return sceneCondition;
    }

    public void setSceneCondition(String sceneCondition) {
        this.sceneCondition = sceneCondition;
    }

    public String getSceneConditionKey() {
        return sceneConditionKey;
    }

    public void setSceneConditionKey(String sceneConditionKey) {
        this.sceneConditionKey = sceneConditionKey;
    }

    public String getChangeOption() {
        return changeOption;
    }

    public void setChangeOption(String changeOption) {
        this.changeOption = changeOption;
    }

    public String getChangeOptionKey() {
        return changeOptionKey;
    }

    public void setChangeOptionKey(String changeOptionKey) {
        this.changeOptionKey = changeOptionKey;
    }

    public String getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    public String getIlluminationCondition() {
        return illuminationCondition;
    }

    public void setIlluminationCondition(String illuminationCondition) {
        this.illuminationCondition = illuminationCondition;
    }

    public String getIlluminationConditionKey() {
        return illuminationConditionKey;
    }

    public void setIlluminationConditionKey(String illuminationConditionKey) {
        this.illuminationConditionKey = illuminationConditionKey;
    }

    public String getProductPeopleName() {
        return productPeopleName;
    }

    public void setProductPeopleName(String productPeopleName) {
        this.productPeopleName = productPeopleName;
    }

    public String getProductPeopleUnit() {
        return productPeopleUnit;
    }

    public void setProductPeopleUnit(String productPeopleUnit) {
        this.productPeopleUnit = productPeopleUnit;
    }

    public String getProductPeopleDuties() {
        return productPeopleDuties;
    }

    public void setProductPeopleDuties(String productPeopleDuties) {
        this.productPeopleDuties = productPeopleDuties;
    }

    public String getSafeguard() {
        return safeguard;
    }

    public void setSafeguard(String safeguard) {
        this.safeguard = safeguard;
    }

    public String getSceneConductor() {
        return sceneConductor;
    }

    public void setSceneConductor(String sceneConductor) {
        this.sceneConductor = sceneConductor;
    }

    public String getSceneConductorKey() {
        return sceneConductorKey;
    }

    public void setSceneConductorKey(String sceneConductorKey) {
        this.sceneConductorKey = sceneConductorKey;
    }

    public List<CellResultItemEntity> getCellResultItem() {
        return cellResultItem;
    }

    public void setCellResultItem(List<CellResultItemEntity> cellResultItem) {
        this.cellResultItem = cellResultItem;
    }

    public List<ContactsEntity> getReleatedPeopleItem() {
        return releatedPeopleItem;
    }

    public void setReleatedPeopleItem(List<ContactsEntity> releatedPeopleItem) {
        this.releatedPeopleItem = releatedPeopleItem;
    }

    public List<ItemEntity> getLostItem() {
        return lostItem;
    }

    public void setLostItem(List<ItemEntity> lostItem) {
        this.lostItem = lostItem;
    }

    public List<ToolEntity> getCrimeToolItem() {
        return crimeToolItem;
    }

    public void setCrimeToolItem(List<ToolEntity> crimeToolItem) {
        this.crimeToolItem = crimeToolItem;
    }

    public List<Photo> getPositionItem() {
        return positionItem;
    }

    public void setPositionItem(List<Photo> positionItem) {
        this.positionItem = positionItem;
    }

    public List<Photo> getFlatItem() {
        return flatItem;
    }

    public void setFlatItem(List<Photo> flatItem) {
        this.flatItem = flatItem;
    }

    public List<Photo> getPositionPhotoItem() {
        return positionPhotoItem;
    }

    public void setPositionPhotoItem(List<Photo> positionPhotoItem) {
        this.positionPhotoItem = positionPhotoItem;
    }

    public List<Photo> getOverviewPhotoItem() {
        return overviewPhotoItem;
    }

    public void setOverviewPhotoItem(List<Photo> overviewPhotoItem) {
        this.overviewPhotoItem = overviewPhotoItem;
    }

    public List<Photo> getImportantPhotoItem() {
        return importantPhotoItem;
    }

    public void setImportantPhotoItem(List<Photo> importantPhotoItem) {
        this.importantPhotoItem = importantPhotoItem;
    }

    public List<Photo> getDetailPhotoItem() {
        return detailPhotoItem;
    }

    public void setDetailPhotoItem(List<Photo> detailPhotoItem) {
        this.detailPhotoItem = detailPhotoItem;
    }

    public List<EvidenceEntity> getEvidenceItem() {
        return evidenceItem;
    }

    public void setEvidenceItem(List<EvidenceEntity> evidenceItem) {
        this.evidenceItem = evidenceItem;
    }

    public List<Photo> getMonitoringPhotoItem() {
        return monitoringPhotoItem;
    }

    public void setMonitoringPhotoItem(List<Photo> monitoringPhotoItem) {
        this.monitoringPhotoItem = monitoringPhotoItem;
    }

    public List<Photo> getCameraPhotoItem() {
        return cameraPhotoItem;
    }

    public void setCameraPhotoItem(List<Photo> cameraPhotoItem) {
        this.cameraPhotoItem = cameraPhotoItem;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOverviewId() {
        return overviewId;
    }

    public void setOverviewId(String overviewId) {
        this.overviewId = overviewId;
    }

    public String getOpinionId() {
        return opinionId;
    }

    public void setOpinionId(String opinionId) {
        this.opinionId = opinionId;
    }

    public String getOpinionResultId() {
        return opinionResultId;
    }

    public void setOpinionResultId(String opinionResultId) {
        this.opinionResultId = opinionResultId;
    }

    public String getCrimePeopleNumber() {
        return crimePeopleNumber;
    }

    public void setCrimePeopleNumber(String crimePeopleNumber) {
        this.crimePeopleNumber = crimePeopleNumber;
    }

    public String getCrimePeopleNumberKey() {
        return crimePeopleNumberKey;
    }

    public void setCrimePeopleNumberKey(String crimePeopleNumberKey) {
        this.crimePeopleNumberKey = crimePeopleNumberKey;
    }

    public String getCrimeMeans() {
        return crimeMeans;
    }

    public void setCrimeMeans(String crimeMeans) {
        this.crimeMeans = crimeMeans;
    }

    public String getCrimeMeansKey() {
        return crimeMeansKey;
    }

    public void setCrimeMeansKey(String crimeMeansKey) {
        this.crimeMeansKey = crimeMeansKey;
    }

    public String getCrimeCharacter() {
        return crimeCharacter;
    }

    public void setCrimeCharacter(String crimeCharacter) {
        this.crimeCharacter = crimeCharacter;
    }

    public String getCrimeCharacterKey() {
        return crimeCharacterKey;
    }

    public void setCrimeCharacterKey(String crimeCharacterKey) {
        this.crimeCharacterKey = crimeCharacterKey;
    }

    public String getCrimeEntrance() {
        return crimeEntrance;
    }

    public void setCrimeEntrance(String crimeEntrance) {
        this.crimeEntrance = crimeEntrance;
    }

    public String getCrimeEntranceKey() {
        return crimeEntranceKey;
    }

    public void setCrimeEntranceKey(String crimeEntranceKey) {
        this.crimeEntranceKey = crimeEntranceKey;
    }

    public String getCrimeTiming() {
        return crimeTiming;
    }

    public void setCrimeTiming(String crimeTiming) {
        this.crimeTiming = crimeTiming;
    }

    public String getCrimeTimingKey() {
        return crimeTimingKey;
    }

    public void setCrimeTimingKey(String crimeTimingKey) {
        this.crimeTimingKey = crimeTimingKey;
    }

    public String getSelectObject() {
        return selectObject;
    }

    public void setSelectObject(String selectObject) {
        this.selectObject = selectObject;
    }

    public String getSelectObjectKey() {
        return selectObjectKey;
    }

    public void setSelectObjectKey(String selectObjectKey) {
        this.selectObjectKey = selectObjectKey;
    }

    public String getCrimeExport() {
        return crimeExport;
    }

    public void setCrimeExport(String crimeExport) {
        this.crimeExport = crimeExport;
    }

    public String getCrimeExportKey() {
        return crimeExportKey;
    }

    public void setCrimeExportKey(String crimeExportKey) {
        this.crimeExportKey = crimeExportKey;
    }

    public String getCrimePeopleFeature() {
        return crimePeopleFeature;
    }

    public void setCrimePeopleFeature(String crimePeopleFeature) {
        this.crimePeopleFeature = crimePeopleFeature;
    }

    public String getCrimePeopleFeatureKey() {
        return crimePeopleFeatureKey;
    }

    public void setCrimePeopleFeatureKey(String crimePeopleFeatureKey) {
        this.crimePeopleFeatureKey = crimePeopleFeatureKey;
    }

    public String getCrimeFeature() {
        return crimeFeature;
    }

    public void setCrimeFeature(String crimeFeature) {
        this.crimeFeature = crimeFeature;
    }

    public String getCrimeFeatureKey() {
        return crimeFeatureKey;
    }

    public void setCrimeFeatureKey(String crimeFeatureKey) {
        this.crimeFeatureKey = crimeFeatureKey;
    }

    public String getIntrusiveMethod() {
        return intrusiveMethod;
    }

    public void setIntrusiveMethod(String intrusiveMethod) {
        this.intrusiveMethod = intrusiveMethod;
    }

    public String getIntrusiveMethodKey() {
        return intrusiveMethodKey;
    }

    public void setIntrusiveMethodKey(String intrusiveMethodKey) {
        this.intrusiveMethodKey = intrusiveMethodKey;
    }

    public String getSelectLocation() {
        return selectLocation;
    }

    public void setSelectLocation(String selectLocation) {
        this.selectLocation = selectLocation;
    }

    public String getSelectLocationKey() {
        return selectLocationKey;
    }

    public void setSelectLocationKey(String selectLocationKey) {
        this.selectLocationKey = selectLocationKey;
    }

    public String getCrimePurpose() {
        return crimePurpose;
    }

    public void setCrimePurpose(String crimePurpose) {
        this.crimePurpose = crimePurpose;
    }

    public String getCrimePurposeKey() {
        return crimePurposeKey;
    }

    public void setCrimePurposeKey(String crimePurposeKey) {
        this.crimePurposeKey = crimePurposeKey;
    }

    public List<WitnessEntity> getWitnessItem() {
        return witnessItem;
    }

    public void setWitnessItem(List<WitnessEntity> witnessItem) {
        this.witnessItem = witnessItem;
    }

    public List<SceneWifiInfo> getWifiInfos() {
        return wifiInfos;
    }

    public void setWifiInfos(List<SceneWifiInfo> wifiInfos) {
        this.wifiInfos = wifiInfos;
    }

    public List<GoodEntity> getGoodEntities() {
        return goodEntities;
    }

    public void setGoodEntities(List<GoodEntity> goodEntities) {
        this.goodEntities = goodEntities;
    }

    public List<KCTBASESTATIONDATABean> getKctbasestationdataBeans() {
        return kctbasestationdataBeans;
    }

    public void setKctbasestationdataBeans(List<KCTBASESTATIONDATABean> kctbasestationdataBeans) {
        this.kctbasestationdataBeans = kctbasestationdataBeans;
    }

    public List<Photo> getDwgItem() {
        return dwgItem;
    }

    public void setDwgItem(List<Photo> dwgItem) {
        this.dwgItem = dwgItem;
    }

    public String getRev1() {
        return rev1;
    }

    public void setRev1(String rev1) {
        this.rev1 = rev1;
    }

    public String getRev2() {
        return rev2;
    }

    public void setRev2(String rev2) {
        this.rev2 = rev2;
    }

    public String getRev3() {
        return rev3;
    }

    public void setRev3(String rev3) {
        this.rev3 = rev3;
    }

    public String getRev4() {
        return rev4;
    }

    public void setRev4(String rev4) {
        this.rev4 = rev4;
    }
}