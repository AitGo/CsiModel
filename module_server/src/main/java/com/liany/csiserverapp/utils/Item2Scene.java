package com.liany.csiserverapp.utils;

import android.content.Context;

import com.liany.csiserverapp.andServer.model.UserDb;
import com.liany.csiserverapp.base.Constants;
import com.liany.csiserverapp.dao.database.greenDao.db.DaoSession;
import com.liany.csiserverapp.dao.database.greenDao.db.PhotoDao;
import com.liany.csiserverapp.debug.ServerApplication;
import com.liany.csiserverapp.diagnose.ContactsEntity;
import com.liany.csiserverapp.diagnose.CrimeItem;
import com.liany.csiserverapp.diagnose.CrimeScene;
import com.liany.csiserverapp.diagnose.EvidenceEntity;
import com.liany.csiserverapp.diagnose.GoodEntity;
import com.liany.csiserverapp.diagnose.ItemEntity;
import com.liany.csiserverapp.diagnose.KCTBASESTATIONDATABean;
import com.liany.csiserverapp.diagnose.Photo;
import com.liany.csiserverapp.diagnose.SceneWifiInfo;
import com.liany.csiserverapp.diagnose.ToolEntity;
import com.liany.csiserverapp.diagnose.WitnessEntity;
import com.liany.csiserverapp.dao.database.greenDao.db.DaoSession;
import com.liany.csiserverapp.diagnose.sysOrgan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2019/4/16
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class Item2Scene {
    private Context mContext;
    private DaoSession daoSession;

    private final String SECRECY = "1";
    private final String DELETE_FLAG = "0";
    private final String VICTIM = "受害人";
    private final String REPORT = "报案人";

    private final String HANDENVICE = "手印";
    private final String FOOTENVICE = "足迹";
    private final String TOOLENVICE = "工痕";
    private final String OTHERENVICE = "其他";

    private String userId = "";
    private String unitCode = "";
    private String orgId = "";
    private String unitName = "";

    public Item2Scene(Context context,String userId,String unitCode,String orgId,String unitName) {
        this.mContext = context;
        daoSession = ServerApplication.getDaoSession();
        this.userId = userId;
        this.unitCode = unitCode;
        this.orgId = orgId;
        this.unitName = unitName;
    }

    public CrimeScene item2Scene(CrimeItem item) {
        CrimeScene scene = new CrimeScene();
        scene.setSceneInvestigation(getscene(item));
        scene.setSceneLawCase(getLawCase(item));
        scene.setSceneReceptionDispatch(getReceptionDispatch(item));
        scene.setSceneAnalysisSuggestion(getAnalysisSuggestion(item));
        scene.setSceneAnalysisResult(getAnalysisResult(item));
        scene.setCommonBigtextClob(getCommonBigtextClob(item));
        scene.setSceneCommissionTools(getCommissionTools(item));
        scene.setSceneLostGoods(getLostGoods(item));
        scene.setSceneWitness(getWitness(item));
        scene.setSceneVictim(getVictim(item));
        scene.setScenePicture(getPicture(item));
        scene.setScenePhoto(getPhoto(item));
        scene.setCommonPicture(getCommonPicture(item));
        scene.setSceneHandprint(getHandprint(item));
        scene.setSceneFootprint(getFootprint(item));
        scene.setSceneToolmark(getToolmark(item));
        scene.setSceneOtherEvidence(getOtherEvidence(item));
//        scene.setSCENE_VIDEO_EVIDENCE(getVideoEvidence(item));
        scene.setSceneWifiInfo(getWifiInfo(item));
        if(item.getKctbasestationdataBeans().size() > 0) {
            String kctCaseInfoId = StringUtils.getUUID();
            String kctLocaleDataId = StringUtils.getUUID();
            scene.setKctCaseInfo(getKctCaseInfo(item,kctCaseInfoId));
            scene.setKctLocaleData(getKctLocaleData(item,kctCaseInfoId,kctLocaleDataId));
            scene.setKctBasestationData(getKctBasestationData(item,kctLocaleDataId));
        }
        scene.setExtracts(getExtracts(item));
        scene.setExtractsPicture(getExtractsPicture(item));
        return scene;
    }

    private CrimeScene.SCENEINVESTIGATIONBean getscene(CrimeItem item) {
        CrimeScene.SCENEINVESTIGATIONBean sceneinvestigationBean = new CrimeScene.SCENEINVESTIGATIONBean();

        sceneinvestigationBean.setID(item.getId());
        sceneinvestigationBean.setRECEPTION_ID(item.getReceptionId());
        sceneinvestigationBean.setCASE_ID(item.getCaseId());
        sceneinvestigationBean.setITERATION_NO(0);////现场复勘号
        sceneinvestigationBean.setINVESTIGATION_DATE_FROM(StringUtils.long2String(item.getAccess_start_time(),"yyyy-MM-dd HH:mm:ss"));
        sceneinvestigationBean.setINVESTIGATION_DATE_TO(StringUtils.long2String(item.getAccess_end_time(),"yyyy-MM-dd HH:mm:ss"));
        sceneinvestigationBean.setINVESTIGATION_PLACE(item.getAccessLocation());
        sceneinvestigationBean.setENV_TEMPERATURE(item.getTemperature());
        sceneinvestigationBean.setENV_MOISTNESS(item.getHumidity());
        sceneinvestigationBean.setWIND(item.getWindDirectionKey());
        sceneinvestigationBean.setWEATHER(item.getWeatherConditionKey());
        sceneinvestigationBean.setSCENE_CONDITION(item.getSceneConditionKey());
        sceneinvestigationBean.setLIGHTING(item.getIlluminationConditionKey());
        sceneinvestigationBean.setPROTECTION_DATE(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));//现场保护时间
        sceneinvestigationBean.setPROTECTION_MEASURE(item.getSafeguard());
        sceneinvestigationBean.setPROTECTOR(item.getProductPeopleUnit() + "&amp" + item.getProductPeopleName() + "&amp" + item.getProductPeopleDuties());
        sceneinvestigationBean.setINVEST_NOTE_ID(item.getOverviewId());
//        sceneinvestigationBean.setWOUNDED_AMOUNT();//受伤数量
//        sceneinvestigationBean.setDEAD_AMOUNT();//死亡数量
        int totalValue = 0;
        for(ItemEntity entity : item.getLostItem()) {
            totalValue = totalValue + Integer.valueOf(entity.getValue());
        }
        sceneinvestigationBean.setLOST_TOTAL_VALUE(totalValue);//损失物品总价值
        sceneinvestigationBean.setDIRECTOR(item.getSceneConductor());
        sceneinvestigationBean.setINVESTIGATOR(item.getAccessInspectors());
        sceneinvestigationBean.setINVESTIGATOR_IDS(item.getAccessInspectorsKey());
        String[] split = item.getAccessInspectorsKey().split(",");
        String key = "";
        StringBuilder keyBuilder = new StringBuilder();
        for(String id : split) {
            keyBuilder.append(key);
            keyBuilder.append(id);
            keyBuilder.append(",4,5,6");
            keyBuilder.append(";");
        }
        key = keyBuilder.toString();
        int index = 0;
        if(keyBuilder.length() != 0) {
            index = keyBuilder.length()-1;
        }
        String substring = key.substring(0, index);
        sceneinvestigationBean.setINVESTIGATOR_DUTIES(substring);
        sceneinvestigationBean.setDIRECTOR_IDS(item.getSceneConductorKey());
//        sceneinvestigationBean.setWITNESS();//见证人
        sceneinvestigationBean.setHANDPRINT_AMOUNT(EvidenceGetTypeValue(item, HANDENVICE).size());
        sceneinvestigationBean.setFOOTPRINT_AMOUNT(EvidenceGetTypeValue(item, FOOTENVICE).size());
        sceneinvestigationBean.setTOOLMARK_AMOUNT(EvidenceGetTypeValue(item, TOOLENVICE).size());
        sceneinvestigationBean.setOTHER_EVIDENCE_AMOUNT(EvidenceGetTypeValue(item, OTHERENVICE).size());
        int scenePhotoAmount = item.getPositionPhotoItem().size() + item.getOverviewPhotoItem().size() + item.getImportantPhotoItem().size()
                + item.getMonitoringPhotoItem().size() + item.getCameraPhotoItem().size() + item.getDetailPhotoItem().size();
        sceneinvestigationBean.setSCENE_PHOTO_AMOUNT(scenePhotoAmount);
        sceneinvestigationBean.setSCENE_PICTURE_AMOUNT(item.getFlatItem().size() + item.getPositionItem().size()+ item.getWitnessItem().size());
        sceneinvestigationBean.setSAVE_FLAG("0");
//        private String FINISH_FLAG;//完整标记
//        private String QUALIFIED_FLAG;//合格标记
        sceneinvestigationBean.setFINISH_FLAG("0");
        sceneinvestigationBean.setQUALIFIED_FLAG("0");
        sceneinvestigationBean.setMODIFY_FLAG("1");
        sceneinvestigationBean.setUSER_ID(userId);
        sceneinvestigationBean.setINIT_SERVER_NO(unitCode);
//        private String MODIFY_FLAG;//可修改标志
        sceneinvestigationBean.setMAIN_ORGAN_ID(orgId);
        sceneinvestigationBean.setMAIN_ORGAN_NAME(unitName);
        sceneinvestigationBean.setTRANSFER_DATE(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
        sceneinvestigationBean.setSECRECY(SECRECY);
        sceneinvestigationBean.setDELETE_FLAG(DELETE_FLAG);
        sceneinvestigationBean.setCREATE_USER(item.getUserName());
        sceneinvestigationBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
        if(item.getUpdateTime() == 0) {
            item.setUpdateTime(System.currentTimeMillis());
        }
        sceneinvestigationBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));

        sceneinvestigationBean.setRESERVER3("1");
        sceneinvestigationBean.setRESERVER6("2");
        sceneinvestigationBean.setENTRY_VERSION("standard");
        sceneinvestigationBean.setMODIFY_FLAG("1");
        return sceneinvestigationBean;
    }

    /**
     * 转换案件基本信息
     * @param item
     * @return
     */
    private CrimeScene.SCENELAWCASEBean getLawCase(CrimeItem item) {
        CrimeScene.SCENELAWCASEBean lawcase = new CrimeScene.SCENELAWCASEBean();
        lawcase.setID(item.getCaseId());
        lawcase.setRESERVER2(item.getId());
        lawcase.setCASE_TYPE(item.getCasetypeKey());
        lawcase.setCASE_TYPE_VALUE(item.getCasetype());
        sysOrgan sysOrgan = UserDb.selectUnitName(item.getAreaKey());
        lawcase.setSCENE_REGIONALISM(sysOrgan.getUnitCode());
        lawcase.setINIT_SERVER_NO(unitCode);
        lawcase.setSCENE_DETAIL(item.getLocation());
        lawcase.setOCCURRENCE_DATE_FROM(StringUtils.long2String(item.getOccurred_start_time(),"yyyy-MM-dd HH:mm:ss"));
        lawcase.setOCCURRENCE_DATE_TO(StringUtils.long2String(item.getOccurred_end_time(),"yyyy-MM-dd HH:mm:ss"));
        lawcase.setEXPOSURE_PROCESS(item.getCaseOccurProcess());
        lawcase.setTRANSFER_DATE(StringUtils.long2String(new Date().getTime(),"yyyy-MM-dd HH:mm:ss"));//上报时间
        lawcase.setSECRECY(SECRECY);
        lawcase.setDELETE_FLAG(DELETE_FLAG);
        lawcase.setCREATE_USER(item.getUserName());
        lawcase.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));//创建时间
        if(item.getUpdateTime() == 0) {
            item.setUpdateTime(System.currentTimeMillis());
        }
        lawcase.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));//修改时间
        lawcase.setRESERVER6(String.valueOf(item.getGpsLon()));//经度
        lawcase.setRESERVER4(String.valueOf(item.getGpsLat()));//纬度
        return lawcase;
    }

    /**
     * 现场接受指派
     * @return
     */
    private CrimeScene.SCENERECEPTIONDISPATCHBean getReceptionDispatch(CrimeItem item) {
        CrimeScene.SCENERECEPTIONDISPATCHBean scenereceptiondispatchBean = new CrimeScene.SCENERECEPTIONDISPATCHBean();

        scenereceptiondispatchBean.setID(item.getReceptionId());
        scenereceptiondispatchBean.setRECEIVED_DATE(StringUtils.long2String(item.getGet_access_time(),"yyyy-MM-dd HH:mm:ss"));
        scenereceptiondispatchBean.setRECEIVED_BY(item.getAccessPolicemen());
        scenereceptiondispatchBean.setASSIGNED_BY(item.getUnitsAssigned());
        scenereceptiondispatchBean.setASSIGNED_CONTENT(item.getAccessReason());
        scenereceptiondispatchBean.setTRANSFER_DATE(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
        scenereceptiondispatchBean.setSECRECY(SECRECY);
        scenereceptiondispatchBean.setDELETE_FLAG(DELETE_FLAG);
        scenereceptiondispatchBean.setCREATE_USER(item.getUserName());
        scenereceptiondispatchBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));//创建时间
        if(item.getUpdateTime() == 0) {
            item.setUpdateTime(System.currentTimeMillis());
        }
        scenereceptiondispatchBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));//修改时间

        return scenereceptiondispatchBean;
    }

    /**
     * 现场分析意见
     * @param item
     * @return
     */
    public CrimeScene.SCENEANALYSISSUGGESTIONBean getAnalysisSuggestion(CrimeItem item) {
        CrimeScene.SCENEANALYSISSUGGESTIONBean sceneanalysissuggestionBean = new CrimeScene.SCENEANALYSISSUGGESTIONBean();
        sceneanalysissuggestionBean.setID(item.getOpinionId());
        sceneanalysissuggestionBean.setINVESTIGATION_ID(item.getId());
        sceneanalysissuggestionBean.setCOMMISSION_PLACE(item.getLocation());
        sceneanalysissuggestionBean.setCRIMINAL_AMOUNT(item.getCrimePeopleNumberKey());
        sceneanalysissuggestionBean.setCRIMINAL_POINTS_ID(item.getCrimePeopleFeatureKey());
        sceneanalysissuggestionBean.setANALYSED_ORG(unitName);
        sceneanalysissuggestionBean.setANALYSED_BY(item.getUserName());
        sceneanalysissuggestionBean.setANALYSED_DATE(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
        sceneanalysissuggestionBean.setSECRECY(SECRECY);
        sceneanalysissuggestionBean.setDELETE_FLAG(DELETE_FLAG);
        sceneanalysissuggestionBean.setCREATE_USER(item.getUserName());
        sceneanalysissuggestionBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
        if(item.getUpdateTime() == 0) {
            item.setUpdateTime(System.currentTimeMillis());
        }
        sceneanalysissuggestionBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));

        sceneanalysissuggestionBean.setINTENTION(item.getSelectObjectKey());
        sceneanalysissuggestionBean.setINTENTION_CN(item.getSelectObject());
        sceneanalysissuggestionBean.setLOCATION(item.getSelectLocationKey());
        sceneanalysissuggestionBean.setLOCATION_CN(item.getSelectLocation());
        sceneanalysissuggestionBean.setCOMMISSION_PERIOD(item.getCrimeTimingKey());
        sceneanalysissuggestionBean.setCOMMISSION_PERIOD_CN(item.getCrimeTiming());

        sceneanalysissuggestionBean.setINTRUDING_WAY(item.getIntrusiveMethodKey());
        sceneanalysissuggestionBean.setINTRUDING_WAY_CN(item.getIntrusiveMethod());
        sceneanalysissuggestionBean.setCOMMISSION_MEANS(item.getCrimeMeansKey());
        sceneanalysissuggestionBean.setCOMMISSION_MEANS_CN(item.getCrimeMeans());
        sceneanalysissuggestionBean.setCOMMISSION_POINTS(item.getCrimeFeatureKey());
        sceneanalysissuggestionBean.setCOMMISSION_POINTS_CN(item.getCrimeFeature());
        sceneanalysissuggestionBean.setMOTIVATION(item.getCrimePurposeKey());
        sceneanalysissuggestionBean.setMOTIVATION_CN(item.getCrimePurpose());
        sceneanalysissuggestionBean.setCASE_PROPERTY(item.getCrimeCharacterKey());
        sceneanalysissuggestionBean.setCASE_PROPERTY_CN(item.getCrimeCharacter());
        sceneanalysissuggestionBean.setENTRANCE_EXIT(item.getCrimeEntranceKey());
        sceneanalysissuggestionBean.setENTRANCE_EXIT_CN(item.getCrimeEntrance());
        sceneanalysissuggestionBean.setEXIT_ENTRANCE(item.getCrimeExportKey());
        sceneanalysissuggestionBean.setEXIT_ENTRANCE_CN(item.getCrimeExport());
        sceneanalysissuggestionBean.setTRANSFER_DATE(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));

        return sceneanalysissuggestionBean;

    }

    /**
     * 现场分析意见结果
     * @param item
     * @return
     */
    private CrimeScene.SCENEANALYSISRESULTBean getAnalysisResult(CrimeItem item) {
        CrimeScene.SCENEANALYSISRESULTBean sceneanalysisresultBean = new CrimeScene.SCENEANALYSISRESULTBean();
        sceneanalysisresultBean.setID(item.getOpinionResultId());
        sceneanalysisresultBean.setINVESTIGATION_ID(item.getId());
//        sceneanalysisresultBean.setLOST_GOODS_DESC();//损失物品描述
        sceneanalysisresultBean.setCOMMISSION_PLACE(item.getLocation());
//        sceneanalysisresultBean.setTOOLS_DESCRIPTION();//作案工具描述
        sceneanalysisresultBean.setCRIMINAL_AMOUNT_DESC(item.getCrimePeopleNumber());
        sceneanalysisresultBean.setCRIMINAL_POINTS_ID(item.getCrimePeopleFeatureKey());
        sceneanalysisresultBean.setANALYSED_ORG(unitName);
        sceneanalysisresultBean.setANALYSED_BY(item.getUserName());
        sceneanalysisresultBean.setANALYSED_DATE(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
        sceneanalysisresultBean.setSECRECY(SECRECY);
        sceneanalysisresultBean.setDELETE_FLAG(DELETE_FLAG);
        sceneanalysisresultBean.setCREATE_USER(item.getUserName());
        sceneanalysisresultBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
        if(item.getUpdateTime() == 0) {
            item.setUpdateTime(System.currentTimeMillis());
        }
        sceneanalysisresultBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
//        sceneanalysisresultBean.setMETERIALS_RELIED_ON();//现场分析依据的资料
        sceneanalysisresultBean.setINTENTION_DESC(item.getSelectObject());
        sceneanalysisresultBean.setLOCATION_DESC(item.getSelectLocation());
//        sceneanalysisresultBean.setINVOLVED_ORG_TYPE_DESC();//涉案单位类型描述
        sceneanalysisresultBean.setPERIOD_DESC(item.getCrimeTiming());
        sceneanalysisresultBean.setENTRANCE_EXIT_DESC(item.getCrimeEntrance());
        sceneanalysisresultBean.setEXIT_ENTRANCE_DESC(item.getCrimeExport());
        sceneanalysisresultBean.setINTRUDING_WAY_DESC(item.getIntrusiveMethod());
        sceneanalysisresultBean.setCOMMISSION_MEANS_DESP(item.getCrimeMeans());
        sceneanalysisresultBean.setPERCULIARITIES_DESCRIPTION(item.getCrimeFeature());
        sceneanalysisresultBean.setMOTIVE_DESC(item.getCrimePurpose());
        sceneanalysisresultBean.setCASE_PROPERTY_DESC(item.getCrimeCharacter());
        //        private String COMMISSION_DESC_ID;//作案过程
        sceneanalysisresultBean.setTRANSFER_DATE(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
        String TOOLS_DESCRIPTION = "";
        for(int i = 0; i < item.getCrimeToolItem().size(); i++) {
            TOOLS_DESCRIPTION += i+1 + "、" + item.getCrimeToolItem().get(i).getName() + "，" + item.getCrimeToolItem().get(i).getCategory() + "，" + item.getCrimeToolItem().get(i).getSource() + "，";
        }
        sceneanalysisresultBean.setTOOLS_DESCRIPTION(TOOLS_DESCRIPTION);
        return sceneanalysisresultBean;
    }



    /**
     * 通用大文本存储表
     * @param item
     * @return
     */
    private List<CrimeScene.COMMONBIGTEXTCLOBBean> getCommonBigtextClob(CrimeItem item) {
        List<CrimeScene.COMMONBIGTEXTCLOBBean> commonbigtextclobBeans = new ArrayList<>();

        CrimeScene.COMMONBIGTEXTCLOBBean commonbigtextclobBean = new CrimeScene.COMMONBIGTEXTCLOBBean();

        commonbigtextclobBean.setID(item.getOverviewId());
        commonbigtextclobBean.setSECRECY(SECRECY);
        commonbigtextclobBean.setDELETE_FLAG(DELETE_FLAG);
        commonbigtextclobBean.setCREATE_USER(item.getUserName());
        commonbigtextclobBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
        if(item.getUpdateTime() == 0) {
            item.setUpdateTime(System.currentTimeMillis());
        }
        commonbigtextclobBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
        commonbigtextclobBean.setCONTENT(item.getOverview());

        commonbigtextclobBeans.add(commonbigtextclobBean);

        CrimeScene.COMMONBIGTEXTCLOBBean commonbigtextclobBean1 = new CrimeScene.COMMONBIGTEXTCLOBBean();
        commonbigtextclobBean1.setID(item.getCrimePeopleFeatureKey());
        commonbigtextclobBean1.setSECRECY(SECRECY);
        commonbigtextclobBean1.setDELETE_FLAG(DELETE_FLAG);
        commonbigtextclobBean1.setCREATE_USER(item.getUserName());
        commonbigtextclobBean1.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
        if(item.getUpdateTime() == 0) {
            item.setUpdateTime(System.currentTimeMillis());
        }
        commonbigtextclobBean1.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
        commonbigtextclobBean1.setCONTENT(item.getCrimePeopleFeature());

        commonbigtextclobBeans.add(commonbigtextclobBean1);

        return commonbigtextclobBeans;
    }

    /**
     * 现场借助工具表
     * @param item
     * @return
     */
    private List<CrimeScene.SCENECOMMISSIONTOOLSBean> getCommissionTools(CrimeItem item) {
        List<CrimeScene.SCENECOMMISSIONTOOLSBean> scenecommissiontoolsBeanList = new ArrayList<>();
        for(ToolEntity entity : item.getCrimeToolItem()) {
            CrimeScene.SCENECOMMISSIONTOOLSBean scenecommissiontoolsBean = new CrimeScene.SCENECOMMISSIONTOOLSBean();
            scenecommissiontoolsBean.setID(entity.getId());
            scenecommissiontoolsBean.setANALYSIS_ID(item.getOpinionId());//分析意见表ID
            scenecommissiontoolsBean.setSPECIES(entity.getCategoryKey());
            scenecommissiontoolsBean.setNAME(entity.getName());
            scenecommissiontoolsBean.setORIGIN(entity.getSourceKey());
            scenecommissiontoolsBean.setSECRECY(SECRECY);
            scenecommissiontoolsBean.setDELETE_FLAG(DELETE_FLAG);
            scenecommissiontoolsBean.setCREATE_USER(item.getUserName());
            scenecommissiontoolsBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            if(item.getUpdateTime() == 0) {
                item.setUpdateTime(System.currentTimeMillis());
            }
            scenecommissiontoolsBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));

            scenecommissiontoolsBeanList.add(scenecommissiontoolsBean);
        }
        return scenecommissiontoolsBeanList;
    }

    /**
     * 现场遗失物品表
     * @param item
     * @return
     */
    private List<CrimeScene.SCENELOSTGOODSBean> getLostGoods(CrimeItem item) {
        List<CrimeScene.SCENELOSTGOODSBean> scenelostgoodsBeanList = new ArrayList<>();
        for(int i = 0; i < item.getLostItem().size(); i++) {
            CrimeScene.SCENELOSTGOODSBean scenelostgoodsBean = new CrimeScene.SCENELOSTGOODSBean();
            scenelostgoodsBean.setID(item.getLostItem().get(i).getId());
            scenelostgoodsBean.setSERIAL_NO(String.valueOf(i+1));
            scenelostgoodsBean.setINVESTIGATION_ID(item.getId());
            scenelostgoodsBean.setNAME(item.getLostItem().get(i).getItemName());
            scenelostgoodsBean.setBRAND(item.getLostItem().get(i).getBrandModel());
            scenelostgoodsBean.setAMOUNT(item.getLostItem().get(i).getAmount());
            scenelostgoodsBean.setVALUE(item.getLostItem().get(i).getValue());
            scenelostgoodsBean.setSECRECY(SECRECY);
            scenelostgoodsBean.setDELETE_FLAG(DELETE_FLAG);
            scenelostgoodsBean.setDESCRIPTION(item.getLostItem().get(i).getFeatureDescription());
            scenelostgoodsBean.setCREATE_USER(item.getUserName());
            scenelostgoodsBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            if(item.getUpdateTime() == 0) {
                item.setUpdateTime(System.currentTimeMillis());
            }
            scenelostgoodsBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));

            scenelostgoodsBeanList.add(scenelostgoodsBean);
        }

        return scenelostgoodsBeanList;
    }

    /**
     * 见证人
     * @param item
     * @return
     */
    public List<CrimeScene.SCENEWITNESSBean> getWitness(CrimeItem item) {

        List<CrimeScene.SCENEWITNESSBean> scenewitnessBeanList = new ArrayList<>();
        for(int i = 0; i < item.getWitnessItem().size(); i++) {
            CrimeScene.SCENEWITNESSBean scenewitnessBean = new CrimeScene.SCENEWITNESSBean();
            scenewitnessBean.setID(item.getWitnessItem().get(i).getId());
            scenewitnessBean.setINVESTIGATION_ID(item.getId());
            scenewitnessBean.setSERIAL_NO(String.valueOf(i+1));
            scenewitnessBean.setNAME(item.getWitnessItem().get(i).getWitnessName());
            scenewitnessBean.setSEX(item.getWitnessItem().get(i).getWitnessSexKey());
            scenewitnessBean.setSECRECY(SECRECY);
            scenewitnessBean.setDELETE_FLAG(DELETE_FLAG);
            scenewitnessBean.setCREATE_USER(item.getUserName());
            scenewitnessBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            if(item.getUpdateTime() == 0) {
                item.setUpdateTime(System.currentTimeMillis());
            }
            scenewitnessBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
            scenewitnessBean.setRESERVER4(item.getWitnessItem().get(i).getWitnessBirthday());
            scenewitnessBean.setPHONE(item.getWitnessItem().get(i).getWitnessNumber());
            scenewitnessBean.setADDRESS(item.getWitnessItem().get(i).getWitnessAddress());

            scenewitnessBeanList.add(scenewitnessBean);
        }
        return scenewitnessBeanList;
    }

    /**
     * 受害人/报案人
     * @param item
     * @return
     */
    private List<CrimeScene.SCENEVICTIMBean> getVictim(CrimeItem item) {
        List<ContactsEntity> reportEntityList = ContactsGetTypeValue(item,REPORT);
        List<CrimeScene.SCENEVICTIMBean> sceneReportBeanList = new ArrayList<>();
        for(int i= 0; i < reportEntityList.size(); i++) {
            CrimeScene.SCENEVICTIMBean scenevictimBean = new CrimeScene.SCENEVICTIMBean();
            scenevictimBean.setID(reportEntityList.get(i).getId());
            scenevictimBean.setCASE_ID(item.getCaseId());
            scenevictimBean.setNAME(reportEntityList.get(i).getName());
            scenevictimBean.setSEX(reportEntityList.get(i).getSexKey());
            scenevictimBean.setPHONE(reportEntityList.get(i).getTel());
            scenevictimBean.setADDRESS(reportEntityList.get(i).getAddress());
            scenevictimBean.setSECRECY(SECRECY);
            scenevictimBean.setDELETE_FLAG(DELETE_FLAG);
            scenevictimBean.setCREATE_USER(item.getUserName());
            scenevictimBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            if(item.getUpdateTime() == 0) {
                item.setUpdateTime(System.currentTimeMillis());
            }
            scenevictimBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
            scenevictimBean.setRESERVER1("0");
            sceneReportBeanList.add(scenevictimBean);
        }

        List<ContactsEntity> contactsEntityList = ContactsGetTypeValue(item,VICTIM);
//        List<CrimeScene.SCENEVICTIMBean> scenevictimBeanList = new ArrayList<>();
        for(int i= 0; i < contactsEntityList.size(); i++) {
            CrimeScene.SCENEVICTIMBean scenevictimBean = new CrimeScene.SCENEVICTIMBean();
            scenevictimBean.setID(contactsEntityList.get(i).getId());
            scenevictimBean.setCASE_ID(item.getCaseId());
            scenevictimBean.setNAME(contactsEntityList.get(i).getName());
            scenevictimBean.setSEX(contactsEntityList.get(i).getSexKey());
            scenevictimBean.setPHONE(contactsEntityList.get(i).getTel());
            scenevictimBean.setADDRESS(contactsEntityList.get(i).getAddress());
            scenevictimBean.setSECRECY(SECRECY);
            scenevictimBean.setDELETE_FLAG(DELETE_FLAG);
            scenevictimBean.setCREATE_USER(item.getUserName());
            scenevictimBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            if(item.getUpdateTime() == 0) {
                item.setUpdateTime(System.currentTimeMillis());
            }
            scenevictimBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
            scenevictimBean.setRESERVER1("1");
            sceneReportBeanList.add(scenevictimBean);
        }
        return sceneReportBeanList;
    }



    /**
     * 现场图片表
     * @param item
     * @return
     */
    private List<CrimeScene.SCENEPICTUREBean> getPicture(CrimeItem item) {
        List<CrimeScene.SCENEPICTUREBean> scenepictureBeanList = new ArrayList<>();
        int no = 1;
        for(Photo photo : item.getPositionItem()) {
            CrimeScene.SCENEPICTUREBean scenepictureBean = new CrimeScene.SCENEPICTUREBean();
            scenepictureBean.setID(photo.getId());
            scenepictureBean.setSERIAL_NO( no + "");
            scenepictureBean.setINVESTIGATION_ID(item.getId());
            scenepictureBean.setCREATE_USER_ID(userId);
            scenepictureBean.setPICTURE_ID(photo.getId());
            scenepictureBean.setPICTURE_NAME(photo.getPhotoInfo());
            scenepictureBean.setPICTURE_TYPE("1082");
            scenepictureBean.setDESCRIPTION(photo.getPhotoInfo());
            scenepictureBean.setSECRECY(SECRECY);
            scenepictureBean.setDELETE_FLAG(DELETE_FLAG);
            scenepictureBean.setCREATE_USER(item.getUserName());
            scenepictureBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            if(item.getUpdateTime() == 0) {
                item.setUpdateTime(System.currentTimeMillis());
            }
            scenepictureBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
            no ++;
            scenepictureBeanList.add(scenepictureBean);
        }
        for(Photo photo : item.getFlatItem()) {
            CrimeScene.SCENEPICTUREBean scenepictureBean = new CrimeScene.SCENEPICTUREBean();
            scenepictureBean.setID(photo.getId());
            scenepictureBean.setSERIAL_NO( no + "");
            scenepictureBean.setINVESTIGATION_ID(item.getId());
            scenepictureBean.setCREATE_USER_ID(userId);
            scenepictureBean.setPICTURE_ID(photo.getId());
            scenepictureBean.setPICTURE_NAME(photo.getPhotoInfo());
            scenepictureBean.setPICTURE_TYPE("1010");
            scenepictureBean.setDESCRIPTION(photo.getPhotoInfo());
            scenepictureBean.setSECRECY(SECRECY);
            scenepictureBean.setDELETE_FLAG(DELETE_FLAG);
            scenepictureBean.setCREATE_USER(item.getUserName());
            scenepictureBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            if(item.getUpdateTime() == 0) {
                item.setUpdateTime(System.currentTimeMillis());
            }
            scenepictureBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
            no ++;
            scenepictureBeanList.add(scenepictureBean);
        }
        for(WitnessEntity photo : item.getWitnessItem()) {
            photo.__setDaoSession(ServerApplication.getDaoSession());
            List<Photo> photos = ServerApplication.getDaoSession().getPhotoDao().queryBuilder().where(PhotoDao.Properties.CrimeId.eq(item.getId()), PhotoDao.Properties.ParentId.eq(photo.getId()), PhotoDao.Properties.State.eq(Constants.photoState_witness)).list();
            if(photos.size() > 0) {
                Photo photo1 = photos.get(photos.size() - 1);
                photo.setPhoto(photo1);
                CrimeScene.SCENEPICTUREBean scenepictureBean = new CrimeScene.SCENEPICTUREBean();
                scenepictureBean.setID(photo.getId());
                scenepictureBean.setSERIAL_NO( no + "");
                scenepictureBean.setINVESTIGATION_ID(item.getId());
                scenepictureBean.setCREATE_USER_ID(userId);
                scenepictureBean.setPICTURE_ID(photo.getPhoto().getId());
                scenepictureBean.setPICTURE_NAME(photo.getPhoto().getFileName());
                scenepictureBean.setPICTURE_TYPE("99");
                scenepictureBean.setDESCRIPTION("");
                scenepictureBean.setSECRECY(SECRECY);
                scenepictureBean.setDELETE_FLAG(DELETE_FLAG);
                scenepictureBean.setCREATE_USER(item.getUserName());
                scenepictureBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
                if(item.getUpdateTime() == 0) {
                    item.setUpdateTime(System.currentTimeMillis());
                }
                scenepictureBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
                no ++;
                scenepictureBeanList.add(scenepictureBean);
            }
        }
        return scenepictureBeanList;
    }

    /**
     * 通用图片表
     * @param item
     * @return
     */
    private List<CrimeScene.COMMONPICTUREBean> getCommonPicture(CrimeItem item) {
        List<CrimeScene.COMMONPICTUREBean> commonpictureBeanArrayList = new ArrayList<>();
        for(Photo photo : item.getPositionItem()) {
            CrimeScene.COMMONPICTUREBean commonpictureBean = new CrimeScene.COMMONPICTUREBean();
            commonpictureBean.setID(photo.getId());
            commonpictureBean.setINVESTIGATION_ID(item.getId());
            commonpictureBean.setWIDTH(photo.getWidth());
            commonpictureBean.setHEIGHT(photo.getHeight());
            commonpictureBean.setFILE_NAME(photo.getFileName());
            commonpictureBean.setCATEGORY("1");
            commonpictureBean.setTYPE(photo.getType());
            commonpictureBean.setSECRECY(SECRECY);
            commonpictureBean.setDELETE_FLAG(DELETE_FLAG);
            commonpictureBean.setCREATE_USER(item.getUserName());
            commonpictureBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            if(item.getUpdateTime() == 0) {
                item.setUpdateTime(System.currentTimeMillis());
            }
            commonpictureBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
            commonpictureBean.setDESCRIPTION(photo.getPhotoInfo());
            commonpictureBeanArrayList.add(commonpictureBean);
            commonpictureBean.setRESERVER1("1000");
        }
        for(Photo photo : item.getFlatItem()) {
            CrimeScene.COMMONPICTUREBean commonpictureBean = new CrimeScene.COMMONPICTUREBean();
            commonpictureBean.setID(photo.getId());
            commonpictureBean.setINVESTIGATION_ID(item.getId());
            commonpictureBean.setWIDTH(photo.getWidth());
            commonpictureBean.setHEIGHT(photo.getHeight());
            commonpictureBean.setFILE_NAME(photo.getFileName());
            commonpictureBean.setCATEGORY("1");
            commonpictureBean.setTYPE(photo.getType());
            commonpictureBean.setSECRECY(SECRECY);
            commonpictureBean.setDELETE_FLAG(DELETE_FLAG);
            commonpictureBean.setCREATE_USER(item.getUserName());
            commonpictureBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            if(item.getUpdateTime() == 0) {
                item.setUpdateTime(System.currentTimeMillis());
            }
            commonpictureBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
            commonpictureBean.setDESCRIPTION(photo.getPhotoInfo());
            commonpictureBeanArrayList.add(commonpictureBean);
            commonpictureBean.setRESERVER1("1010");
        }

        for(Photo photo : item.getPositionPhotoItem()) {
            CrimeScene.COMMONPICTUREBean commonpictureBean = new CrimeScene.COMMONPICTUREBean();
            commonpictureBean.setID(photo.getId());
            commonpictureBean.setINVESTIGATION_ID(item.getId());
            commonpictureBean.setWIDTH(photo.getWidth());
            commonpictureBean.setHEIGHT(photo.getHeight());
            commonpictureBean.setFILE_NAME(photo.getFileName());
            commonpictureBean.setCATEGORY("2");
            commonpictureBean.setTYPE(photo.getType());
            commonpictureBean.setSECRECY(SECRECY);
            commonpictureBean.setDELETE_FLAG(DELETE_FLAG);
            commonpictureBean.setCREATE_USER(item.getUserName());
            commonpictureBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            if(item.getUpdateTime() == 0) {
                item.setUpdateTime(System.currentTimeMillis());
            }
            commonpictureBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
            commonpictureBean.setDESCRIPTION(photo.getPhotoInfo());
            commonpictureBeanArrayList.add(commonpictureBean);
        }
        for(Photo photo : item.getOverviewPhotoItem()) {
            CrimeScene.COMMONPICTUREBean commonpictureBean = new CrimeScene.COMMONPICTUREBean();
            commonpictureBean.setID(photo.getId());
            commonpictureBean.setINVESTIGATION_ID(item.getId());
            commonpictureBean.setWIDTH(photo.getWidth());
            commonpictureBean.setHEIGHT(photo.getHeight());
            commonpictureBean.setFILE_NAME(photo.getFileName());
            commonpictureBean.setCATEGORY("2");
            commonpictureBean.setTYPE(photo.getType());
            commonpictureBean.setSECRECY(SECRECY);
            commonpictureBean.setDELETE_FLAG(DELETE_FLAG);
            commonpictureBean.setCREATE_USER(item.getUserName());
            commonpictureBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            if(item.getUpdateTime() == 0) {
                item.setUpdateTime(System.currentTimeMillis());
            }
            commonpictureBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
            commonpictureBean.setDESCRIPTION(photo.getPhotoInfo());
            commonpictureBeanArrayList.add(commonpictureBean);
        }
        for(Photo photo : item.getImportantPhotoItem()) {
            CrimeScene.COMMONPICTUREBean commonpictureBean = new CrimeScene.COMMONPICTUREBean();
            commonpictureBean.setID(photo.getId());
            commonpictureBean.setINVESTIGATION_ID(item.getId());
            commonpictureBean.setWIDTH(photo.getWidth());
            commonpictureBean.setHEIGHT(photo.getHeight());
            commonpictureBean.setFILE_NAME(photo.getFileName());
            commonpictureBean.setCATEGORY("2");
            commonpictureBean.setTYPE(photo.getType());
            commonpictureBean.setSECRECY(SECRECY);
            commonpictureBean.setDELETE_FLAG(DELETE_FLAG);
            commonpictureBean.setCREATE_USER(item.getUserName());
            commonpictureBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            if(item.getUpdateTime() == 0) {
                item.setUpdateTime(System.currentTimeMillis());
            }
            commonpictureBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
            commonpictureBean.setDESCRIPTION(photo.getPhotoInfo());
            commonpictureBeanArrayList.add(commonpictureBean);
        }
        for(Photo photo : item.getMonitoringPhotoItem()) {
            CrimeScene.COMMONPICTUREBean commonpictureBean = new CrimeScene.COMMONPICTUREBean();
            commonpictureBean.setID(photo.getId());
            commonpictureBean.setINVESTIGATION_ID(item.getId());
            commonpictureBean.setWIDTH(photo.getWidth());
            commonpictureBean.setHEIGHT(photo.getHeight());
            commonpictureBean.setFILE_NAME(photo.getFileName());
            commonpictureBean.setCATEGORY("2");
            commonpictureBean.setTYPE(photo.getType());
            commonpictureBean.setSECRECY(SECRECY);
            commonpictureBean.setDELETE_FLAG(DELETE_FLAG);
            commonpictureBean.setCREATE_USER(item.getUserName());
            commonpictureBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            if(item.getUpdateTime() == 0) {
                item.setUpdateTime(System.currentTimeMillis());
            }
            commonpictureBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
            commonpictureBean.setDESCRIPTION(photo.getPhotoInfo());
            commonpictureBeanArrayList.add(commonpictureBean);
        }
        for(Photo photo : item.getCameraPhotoItem()) {
            CrimeScene.COMMONPICTUREBean commonpictureBean = new CrimeScene.COMMONPICTUREBean();
            commonpictureBean.setID(photo.getId());
            commonpictureBean.setINVESTIGATION_ID(item.getId());
            commonpictureBean.setWIDTH(photo.getWidth());
            commonpictureBean.setHEIGHT(photo.getHeight());
            commonpictureBean.setFILE_NAME(photo.getFileName());
            commonpictureBean.setCATEGORY("2");
            commonpictureBean.setTYPE(photo.getType());
            commonpictureBean.setSECRECY(SECRECY);
            commonpictureBean.setDELETE_FLAG(DELETE_FLAG);
            commonpictureBean.setCREATE_USER(item.getUserName());
            commonpictureBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            if(item.getUpdateTime() == 0) {
                item.setUpdateTime(System.currentTimeMillis());
            }
            commonpictureBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
            commonpictureBean.setDESCRIPTION(photo.getPhotoInfo());
            commonpictureBeanArrayList.add(commonpictureBean);
        }
        List<EvidenceEntity> handEvidences = EvidenceGetTypeValue(item, HANDENVICE);
        for(EvidenceEntity entity : handEvidences) {
            entity.__setDaoSession(ServerApplication.getDaoSession());
            List<Photo> photos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()), PhotoDao.Properties.ParentId.eq(entity.getId()), PhotoDao.Properties.State.eq(Constants.photoState_evidence)).list();
            if(photos.size() > 0) {
                Photo photo = photos.get(photos.size() - 1);
                entity.setPhoto(photo);
                CrimeScene.COMMONPICTUREBean commonpictureBean = new CrimeScene.COMMONPICTUREBean();
                commonpictureBean.setID(photo.getId());
                commonpictureBean.setINVESTIGATION_ID(item.getId());
                commonpictureBean.setWIDTH(photo.getWidth());
                commonpictureBean.setHEIGHT(photo.getHeight());
                String photoName = photo.getFileName();
                String photoType = photo.getType();
                if(photoType.equals("bmp")) {
                    photoType = "jpg";
                    photoName = photoName.replace("bmp","jpg");
                }
                commonpictureBean.setFILE_NAME(photoName);
                commonpictureBean.setTYPE(photoType);
                commonpictureBean.setCATEGORY("3");
                commonpictureBean.setSECRECY(SECRECY);
                commonpictureBean.setDELETE_FLAG(DELETE_FLAG);
                commonpictureBean.setCREATE_USER(item.getUserName());
                commonpictureBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
                if(item.getUpdateTime() == 0) {
                    item.setUpdateTime(System.currentTimeMillis());
                }
                commonpictureBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
//            commonpictureBean.setDESCRIPTION(photo.getPhotoInfo());
                commonpictureBeanArrayList.add(commonpictureBean);
            }
        }
        List<EvidenceEntity> footEvidences = EvidenceGetTypeValue(item, FOOTENVICE);
        for(EvidenceEntity entity : footEvidences) {
            entity.__setDaoSession(ServerApplication.getDaoSession());
            List<Photo> photos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()), PhotoDao.Properties.ParentId.eq(entity.getId()), PhotoDao.Properties.State.eq(Constants.photoState_evidence)).list();
            if(photos.size() > 0) {
                Photo photo = photos.get(photos.size() - 1);
                entity.setPhoto(photo);
                CrimeScene.COMMONPICTUREBean commonpictureBean = new CrimeScene.COMMONPICTUREBean();
                commonpictureBean.setID(photo.getId());
                commonpictureBean.setINVESTIGATION_ID(item.getId());
                commonpictureBean.setWIDTH(photo.getWidth());
                commonpictureBean.setHEIGHT(photo.getHeight());
                commonpictureBean.setFILE_NAME(photo.getFileName());
                commonpictureBean.setCATEGORY("4");
                commonpictureBean.setTYPE(photo.getType());
                commonpictureBean.setSECRECY(SECRECY);
                commonpictureBean.setDELETE_FLAG(DELETE_FLAG);
                commonpictureBean.setCREATE_USER(item.getUserName());
                commonpictureBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
                if(item.getUpdateTime() == 0) {
                    item.setUpdateTime(System.currentTimeMillis());
                }
                commonpictureBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
//            commonpictureBean.setDESCRIPTION(photo.getPhotoInfo());
                commonpictureBeanArrayList.add(commonpictureBean);
            }
        }
        List<EvidenceEntity> toolEvidences = EvidenceGetTypeValue(item, TOOLENVICE);
        for(EvidenceEntity entity : toolEvidences) {
            entity.__setDaoSession(ServerApplication.getDaoSession());
            List<Photo> photos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()), PhotoDao.Properties.ParentId.eq(entity.getId()), PhotoDao.Properties.State.eq(Constants.photoState_evidence)).list();
            if(photos.size() > 0) {
                Photo photo = photos.get(photos.size() - 1);
                entity.setPhoto(photo);
                CrimeScene.COMMONPICTUREBean commonpictureBean = new CrimeScene.COMMONPICTUREBean();
                commonpictureBean.setID(photo.getId());
                commonpictureBean.setINVESTIGATION_ID(item.getId());
                commonpictureBean.setWIDTH(photo.getWidth());
                commonpictureBean.setHEIGHT(photo.getHeight());
                commonpictureBean.setFILE_NAME(photo.getFileName());
                commonpictureBean.setCATEGORY("5");
                commonpictureBean.setTYPE(photo.getType());
                commonpictureBean.setSECRECY(SECRECY);
                commonpictureBean.setDELETE_FLAG(DELETE_FLAG);
                commonpictureBean.setCREATE_USER(item.getUserName());
                commonpictureBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
                if(item.getUpdateTime() == 0) {
                    item.setUpdateTime(System.currentTimeMillis());
                }
                commonpictureBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
//            commonpictureBean.setDESCRIPTION(photo.getPhotoInfo());
                commonpictureBeanArrayList.add(commonpictureBean);
            }
        }
        List<EvidenceEntity> otherEvidences = EvidenceGetTypeValue(item, OTHERENVICE);
        for(EvidenceEntity entity : otherEvidences) {
            entity.__setDaoSession(ServerApplication.getDaoSession());
            List<Photo> photos = daoSession.queryBuilder(Photo.class).where(PhotoDao.Properties.CrimeId.eq(item.getId()), PhotoDao.Properties.ParentId.eq(entity.getId()), PhotoDao.Properties.State.eq(Constants.photoState_evidence)).list();
            if(photos.size() > 0) {
                Photo photo = photos.get(photos.size() - 1);
                entity.setPhoto(photo);
                CrimeScene.COMMONPICTUREBean commonpictureBean = new CrimeScene.COMMONPICTUREBean();
                commonpictureBean.setID(photo.getId());
                commonpictureBean.setINVESTIGATION_ID(item.getId());
                commonpictureBean.setWIDTH(photo.getWidth());
                commonpictureBean.setHEIGHT(photo.getHeight());
                commonpictureBean.setFILE_NAME(photo.getFileName());
                commonpictureBean.setCATEGORY("14");
                commonpictureBean.setTYPE(photo.getType());
                commonpictureBean.setSECRECY(SECRECY);
                commonpictureBean.setDELETE_FLAG(DELETE_FLAG);
                commonpictureBean.setCREATE_USER(item.getUserName());
                commonpictureBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
                if(item.getUpdateTime() == 0) {
                    item.setUpdateTime(System.currentTimeMillis());
                }
                commonpictureBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
//            commonpictureBean.setDESCRIPTION(photo.getPhotoInfo());
                commonpictureBeanArrayList.add(commonpictureBean);
            }
        }
        for(WitnessEntity entity : item.getWitnessItem()) {
            Photo photo = entity.getPhoto();
            if(photo != null) {
                CrimeScene.COMMONPICTUREBean commonpictureBean = new CrimeScene.COMMONPICTUREBean();
                commonpictureBean.setID(photo.getId());
                commonpictureBean.setINVESTIGATION_ID(item.getId());
                commonpictureBean.setWIDTH(photo.getWidth());
                commonpictureBean.setHEIGHT(photo.getHeight());
                commonpictureBean.setFILE_NAME(photo.getFileName());
                commonpictureBean.setCATEGORY("99");
                commonpictureBean.setTYPE(photo.getType());
                commonpictureBean.setSECRECY(SECRECY);
                commonpictureBean.setDELETE_FLAG(DELETE_FLAG);
                commonpictureBean.setCREATE_USER(item.getUserName());
                commonpictureBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
                if(item.getUpdateTime() == 0) {
                    item.setUpdateTime(System.currentTimeMillis());
                }
                commonpictureBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
//            commonpictureBean.setDESCRIPTION(photo.getPhotoInfo());
                commonpictureBeanArrayList.add(commonpictureBean);
            }
        }
        for(Photo photo : item.getDetailPhotoItem()) {
            CrimeScene.COMMONPICTUREBean commonpictureBean = new CrimeScene.COMMONPICTUREBean();
            commonpictureBean.setID(photo.getId());
            commonpictureBean.setINVESTIGATION_ID(item.getId());
            commonpictureBean.setWIDTH(photo.getWidth());
            commonpictureBean.setHEIGHT(photo.getHeight());
            String photoName = photo.getFileName();
            String photoType = photo.getType();
            if(photoType.equals("bmp")) {
                photoType = "jpg";
                photoName = photoName.replace("bmp","jpg");
            }
            commonpictureBean.setFILE_NAME(photoName);
            commonpictureBean.setTYPE(photoType);
            commonpictureBean.setCATEGORY("2");
            commonpictureBean.setSECRECY(SECRECY);
            commonpictureBean.setDELETE_FLAG(DELETE_FLAG);
            commonpictureBean.setCREATE_USER(item.getUserName());
            commonpictureBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            if(item.getUpdateTime() == 0) {
                item.setUpdateTime(System.currentTimeMillis());
            }
            commonpictureBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
            commonpictureBean.setDESCRIPTION(photo.getPhotoInfo());
            commonpictureBeanArrayList.add(commonpictureBean);
        }
        return commonpictureBeanArrayList;
    }

    /**
     * 手印
     * 物证名称rev5 特征rev7
     * @param item
     * @return
     */
    private List<CrimeScene.SCENEHANDPRINTBean> getHandprint(CrimeItem item) {
        List<CrimeScene.SCENEHANDPRINTBean> scenehandprintBeanList = new ArrayList<>();
        List<EvidenceEntity> handEvidence = EvidenceGetTypeValue(item,HANDENVICE);
        for(int i = 0; i < handEvidence.size(); i++) {
            CrimeScene.SCENEHANDPRINTBean scenehandprintBean = new CrimeScene.SCENEHANDPRINTBean();
            scenehandprintBean.setID(handEvidence.get(i).getId());
            scenehandprintBean.setSERIAL_NO(String.valueOf(i+1));
            scenehandprintBean.setCREATE_USER_ID(userId);
            scenehandprintBean.setINVESTIGATION_ID(item.getId());
            scenehandprintBean.setHANDPRINT_PHOTO_ID(handEvidence.get(i).getPhoto().getId());//照片id
            scenehandprintBean.setPRINT_TYPE(handEvidence.get(i).getEvidenceKey());//手印类型
            scenehandprintBean.setLEFT_POSITION(handEvidence.get(i).getLegacySite());
            scenehandprintBean.setCOLLECTION_MODE(handEvidence.get(i).getMethodKey());
            scenehandprintBean.setCOLLECTED_BY(handEvidence.get(i).getPeopleKey());
            scenehandprintBean.setCOLLECTED_BY_NAME(handEvidence.get(i).getPeople());
            scenehandprintBean.setCOLLECTED_DATE(StringUtils.long2String(handEvidence.get(i).getTime(),"yyyy-MM-dd HH:mm:ss"));
            scenehandprintBean.setSECRECY(SECRECY);
            scenehandprintBean.setDELETE_FLAG(DELETE_FLAG);
            scenehandprintBean.setCREATE_USER(item.getUserName());
            scenehandprintBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            if(item.getUpdateTime() == 0) {
                item.setUpdateTime(System.currentTimeMillis());
            }
            scenehandprintBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
            scenehandprintBean.setRESERVER5(handEvidence.get(i).getEvidenceName());
            scenehandprintBean.setRESERVER7(handEvidence.get(i).getBasiceFeature());

            scenehandprintBeanList.add(scenehandprintBean);
        }
        return scenehandprintBeanList;
    }

    /**
     * 足迹
     * 物证名称rev2 特征rev3
     * @param item
     * @return
     */
    private List<CrimeScene.SCENEFOOTPRINTBean> getFootprint(CrimeItem item) {
        List<CrimeScene.SCENEFOOTPRINTBean> scenefootprintBeanList = new ArrayList<>();
        List<EvidenceEntity> footEvidence = EvidenceGetTypeValue(item,FOOTENVICE);
        for(int i = 0; i < footEvidence.size(); i++) {
            CrimeScene.SCENEFOOTPRINTBean scenefootprintBean = new CrimeScene.SCENEFOOTPRINTBean();
            scenefootprintBean.setID(footEvidence.get(i).getId());
            scenefootprintBean.setSERIAL_NO(String.valueOf(i+1));
            scenefootprintBean.setCREATE_USER_ID(userId);
            scenefootprintBean.setINVESTIGATION_ID(item.getId());
            scenefootprintBean.setFOOTPRINT_PHOTO_ID(footEvidence.get(i).getPhoto().getId());
            scenefootprintBean.setPRINT_TYPE(footEvidence.get(i).getEvidenceKey());
            scenefootprintBean.setLEFT_POSITION(footEvidence.get(i).getLegacySite());
            scenefootprintBean.setCOLLECTION_MODE(footEvidence.get(i).getMethodKey());
            scenefootprintBean.setCOLLECTED_BY(footEvidence.get(i).getPeopleKey());
            scenefootprintBean.setCOLLECTED_BY_NAME(footEvidence.get(i).getPeople());
            scenefootprintBean.setCOLLECTED_DATE(StringUtils.long2String(footEvidence.get(i).getTime(),"yyyy-MM-dd HH:mm:ss"));
            scenefootprintBean.setSECRECY(SECRECY);
            scenefootprintBean.setDELETE_FLAG(DELETE_FLAG);
            scenefootprintBean.setCREATE_USER(item.getUserName());
            scenefootprintBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            if(item.getUpdateTime() == 0) {
                item.setUpdateTime(System.currentTimeMillis());
            }
            scenefootprintBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
            scenefootprintBean.setRESERVER2(footEvidence.get(i).getEvidenceName());
            scenefootprintBean.setRESERVER3(footEvidence.get(i).getBasiceFeature());
            scenefootprintBeanList.add(scenefootprintBean);
        }
        return scenefootprintBeanList;
    }

    /**
     * 工痕
     * 物证名称rev1 特征DESCRIPTION
     * @param item
     * @return
     */
    private List<CrimeScene.SCENETOOLMARKBean> getToolmark(CrimeItem item) {
        List<CrimeScene.SCENETOOLMARKBean> scenetoolmarkBeanList = new ArrayList<>();
        List<EvidenceEntity> toolEvidence = EvidenceGetTypeValue(item,TOOLENVICE);
        for(int i = 0; i < toolEvidence.size(); i++) {
            CrimeScene.SCENETOOLMARKBean scenetoolmarkBean = new CrimeScene.SCENETOOLMARKBean();
            scenetoolmarkBean.setID(toolEvidence.get(i).getId());
            scenetoolmarkBean.setSERIAL_NO(String.valueOf(i+1));
            scenetoolmarkBean.setINVESTIGATION_ID(item.getId());
            scenetoolmarkBean.setTOOLMARK_PHOTO_ID(toolEvidence.get(i).getPhoto().getId());
            scenetoolmarkBean.setPRINT_TYPE(toolEvidence.get(i).getEvidenceKey());
            scenetoolmarkBean.setLEFT_POSITION(toolEvidence.get(i).getLegacySite());
            scenetoolmarkBean.setCOLLECTION_MODE(toolEvidence.get(i).getMethodKey());
            scenetoolmarkBean.setCOLLECTED_BY(toolEvidence.get(i).getPeopleKey());
            scenetoolmarkBean.setCOLLECTED_BY_NAME(toolEvidence.get(i).getPeople());
            scenetoolmarkBean.setCOLLECTED_DATE(StringUtils.long2String(toolEvidence.get(i).getTime(),"yyyy-MM-dd HH:mm:ss"));
            scenetoolmarkBean.setSECRECY(SECRECY);
            scenetoolmarkBean.setDELETE_FLAG(DELETE_FLAG);
            scenetoolmarkBean.setCREATE_USER(item.getUserName());
            scenetoolmarkBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            if(item.getUpdateTime() == 0) {
                item.setUpdateTime(System.currentTimeMillis());
            }
            scenetoolmarkBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
            scenetoolmarkBean.setTOOL_JUDGEMENT(toolEvidence.get(i).getInferKey());
            scenetoolmarkBean.setRESERVER1(toolEvidence.get(i).getEvidenceName());
            scenetoolmarkBean.setDESCRIPTION(toolEvidence.get(i).getBasiceFeature());

            scenetoolmarkBeanList.add(scenetoolmarkBean);
        }
        return scenetoolmarkBeanList;
    }

    /**
     * 其他痕迹
     * 物证名称rev1 特征DESCRIPTION
     * @param item
     * @return
     */
    private List<CrimeScene.SCENEOTHEREVIDENCEBean> getOtherEvidence(CrimeItem item) {
        List<CrimeScene.SCENEOTHEREVIDENCEBean> sceneotherevidenceBeanList = new ArrayList<>();
        List<EvidenceEntity> otherEvidence = EvidenceGetTypeValue(item,OTHERENVICE);
        for(int i = 0; i < otherEvidence.size(); i++) {
            CrimeScene.SCENEOTHEREVIDENCEBean sceneotherevidenceBean = new CrimeScene.SCENEOTHEREVIDENCEBean();
            sceneotherevidenceBean.setID(otherEvidence.get(i).getId());
            sceneotherevidenceBean.setSERIAL_NO(String.valueOf(i+1));
            sceneotherevidenceBean.setCREATE_USER_ID(userId);
            sceneotherevidenceBean.setINVESTIGATION_ID(item.getId());
            sceneotherevidenceBean.setEVIDENCE_PHOTO_ID(otherEvidence.get(i).getPhoto().getId());
            sceneotherevidenceBean.setEVIDENCE_TYPE(otherEvidence.get(i).getEvidenceCategory());
            sceneotherevidenceBean.setLEFT_POSITION(otherEvidence.get(i).getLegacySite());
            sceneotherevidenceBean.setCOLLECTION_MODE(otherEvidence.get(i).getMethod());
            sceneotherevidenceBean.setCOLLECTED_BY(otherEvidence.get(i).getPeopleKey());
            sceneotherevidenceBean.setCOLLECTED_BY_NAME(otherEvidence.get(i).getPeople());
            sceneotherevidenceBean.setCOLLECTED_DATE(StringUtils.long2String(otherEvidence.get(i).getTime(),"yyyy-MM-dd HH:mm:ss"));
            sceneotherevidenceBean.setSECRECY(SECRECY);
            sceneotherevidenceBean.setDELETE_FLAG(DELETE_FLAG);
            sceneotherevidenceBean.setCREATE_USER(item.getUserName());
            sceneotherevidenceBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            if(item.getUpdateTime() == 0) {
                item.setUpdateTime(System.currentTimeMillis());
            }
            sceneotherevidenceBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
            sceneotherevidenceBean.setRESERVER1(otherEvidence.get(i).getEvidenceName());
            sceneotherevidenceBean.setDESCRIPTION(otherEvidence.get(i).getBasiceFeature());

            sceneotherevidenceBeanList.add(sceneotherevidenceBean);
        }
        return sceneotherevidenceBeanList;
    }



    /**
     * 视听物证表
     * @param item
     * @return
     */
    private List<CrimeScene.SCENEVIDEOEVIDENCEBean> getVideoEvidence(CrimeItem item) {
        List<CrimeScene.SCENEVIDEOEVIDENCEBean> scenevideoevidenceBeanList = new ArrayList<>();
//        int i = 1;
//        for(MonitoringPhoto photo : item.getMonitoringPhotoItem()) {
//            CrimeScene.SCENEVIDEOEVIDENCEBean scenevideoevidenceBean = new CrimeScene.SCENEVIDEOEVIDENCEBean();
//            scenevideoevidenceBean.setID(photo.getId());
//            scenevideoevidenceBean.setSERIAL_NO(i + "");
//            scenevideoevidenceBean.setCREATE_USER_ID(selectUserByLoginName(SPUtils.getParam(mContext,Constants.loginName,"")).getId());
//            scenevideoevidenceBean.setINVESTIGATION_ID(item.getId());
//            scenevideoevidenceBean.setEVIDENCE_PHOTO_ID(photo.getPhotoId());
//            scenevideoevidenceBean.setEVIDENCE_TYPE();
//            scenevideoevidenceBean.setLEFT_POSITION();
//            scenevideoevidenceBean.setCOLLECTION_MODE();
//            scenevideoevidenceBean.setCOLLECTED_BY();
//            scenevideoevidenceBean.setCOLLECTED_DATE();
//            scenevideoevidenceBean.setSECRECY(SECRECY);
//
//        }
//        private String ID;
//        private String SERIAL_NO;//序号
//        private String CREATE_USER_ID;//创建人id
//        private String INVESTIGATION_ID;//现场信息ID
//        private String EVIDENCE_PHOTO_ID;//视听物证照片ID
//        private String EVIDENCE_TYPE;//类型
//        private String LEFT_POSITION;//遗留部位
//        private String COLLECTION_MODE;//提取方法
//        private String COLLECTED_BY;//提取人
//        private String COLLECTED_DATE;//提取日期
//        private String SECRECY;//密级
//        private String DELETE_FLAG;//删除标志
//        private String CREATE_USER;//记录创建人
//        private String CREATE_DATETIME;//记录创建时间
//        private String UPDATE_DATETIME;//记录更新时间
//        private String DESCRIPTION;//特征描述
//        private String COLLECTED_BY_NAME;//提取人姓名
        return scenevideoevidenceBeanList;
    }

//    /**
//     * 附件表
//     * @return
//     */
//    private List<CrimeScene.COMMONATTACHMENTBean> getCommonAttachement() {
//
//        return null;
//    }

    /**
     * 现场照片表
     * @param item
     * @return
     */
    private List<CrimeScene.SCENEPHOTOBean> getPhoto(CrimeItem item) {
        List<CrimeScene.SCENEPHOTOBean> scenephotoBeanList = new ArrayList<>();
        int i = 1;

        for(Photo photo : item.getPositionPhotoItem()) {
            CrimeScene.SCENEPHOTOBean scenephotoBean = new CrimeScene.SCENEPHOTOBean();
            scenephotoBean.setID(photo.getId());
            scenephotoBean.setSERIAL_NO(i + "");
            scenephotoBean.setINVESTIGATION_ID(item.getId());
            scenephotoBean.setCREATE_USER_ID(userId);
            scenephotoBean.setPHOTO_ID(photo.getId());
            scenephotoBean.setPHOTO_NAME(photo.getFileName());
            scenephotoBean.setPHOTO_TYPE("1");
            scenephotoBean.setDESCRIPTION(photo.getPhotoInfo());
            scenephotoBean.setSECRECY(SECRECY);
            scenephotoBean.setDELETE_FLAG(DELETE_FLAG);
            scenephotoBean.setCREATE_USER(item.getUserName());
            scenephotoBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            if(item.getUpdateTime() == 0) {
                item.setUpdateTime(System.currentTimeMillis());
            }
            scenephotoBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
            scenephotoBeanList.add(scenephotoBean);
            i++;
        }
        for(Photo photo : item.getOverviewPhotoItem()) {
            CrimeScene.SCENEPHOTOBean scenephotoBean = new CrimeScene.SCENEPHOTOBean();
            scenephotoBean.setID(photo.getId());
            scenephotoBean.setSERIAL_NO(i + "");
            scenephotoBean.setINVESTIGATION_ID(item.getId());
            scenephotoBean.setCREATE_USER_ID(userId);
            scenephotoBean.setPHOTO_ID(photo.getId());
            scenephotoBean.setPHOTO_NAME(photo.getFileName());
            scenephotoBean.setPHOTO_TYPE("2");
            scenephotoBean.setDESCRIPTION(photo.getPhotoInfo());
            scenephotoBean.setSECRECY(SECRECY);
            scenephotoBean.setDELETE_FLAG(DELETE_FLAG);
            scenephotoBean.setCREATE_USER(item.getUserName());
            scenephotoBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            if(item.getUpdateTime() == 0) {
                item.setUpdateTime(System.currentTimeMillis());
            }
            scenephotoBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
            scenephotoBeanList.add(scenephotoBean);
            i++;
        }
        for(Photo photo : item.getImportantPhotoItem()) {
            CrimeScene.SCENEPHOTOBean scenephotoBean = new CrimeScene.SCENEPHOTOBean();
            scenephotoBean.setID(photo.getId());
            scenephotoBean.setSERIAL_NO(i + "");
            scenephotoBean.setINVESTIGATION_ID(item.getId());
            scenephotoBean.setCREATE_USER_ID(userId);
            scenephotoBean.setPHOTO_ID(photo.getId());
            scenephotoBean.setPHOTO_NAME(photo.getFileName());
            scenephotoBean.setPHOTO_TYPE("3");
            scenephotoBean.setDESCRIPTION(photo.getPhotoInfo());
            scenephotoBean.setSECRECY(SECRECY);
            scenephotoBean.setDELETE_FLAG(DELETE_FLAG);
            scenephotoBean.setCREATE_USER(item.getUserName());
            scenephotoBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            if(item.getUpdateTime() == 0) {
                item.setUpdateTime(System.currentTimeMillis());
            }
            scenephotoBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
            scenephotoBeanList.add(scenephotoBean);
            i++;
        }
        for(Photo photo : item.getMonitoringPhotoItem()) {
            CrimeScene.SCENEPHOTOBean scenephotoBean = new CrimeScene.SCENEPHOTOBean();
            scenephotoBean.setID(photo.getId());
            scenephotoBean.setSERIAL_NO(i + "");
            scenephotoBean.setINVESTIGATION_ID(item.getId());
            scenephotoBean.setCREATE_USER_ID(userId);
            scenephotoBean.setPHOTO_ID(photo.getId());
            scenephotoBean.setPHOTO_NAME(photo.getFileName());
            scenephotoBean.setPHOTO_TYPE("3");
            scenephotoBean.setDESCRIPTION(photo.getPhotoInfo());
            scenephotoBean.setSECRECY(SECRECY);
            scenephotoBean.setDELETE_FLAG(DELETE_FLAG);
            scenephotoBean.setCREATE_USER(item.getUserName());
            scenephotoBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            if(item.getUpdateTime() == 0) {
                item.setUpdateTime(System.currentTimeMillis());
            }
            scenephotoBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
            scenephotoBeanList.add(scenephotoBean);
            i++;
        }
        for(Photo photo : item.getCameraPhotoItem()) {
            CrimeScene.SCENEPHOTOBean scenephotoBean = new CrimeScene.SCENEPHOTOBean();
            scenephotoBean.setID(photo.getId());
            scenephotoBean.setSERIAL_NO(i + "");
            scenephotoBean.setINVESTIGATION_ID(item.getId());
            scenephotoBean.setCREATE_USER_ID(userId);
            scenephotoBean.setPHOTO_ID(photo.getId());
            scenephotoBean.setPHOTO_NAME(photo.getFileName());
            scenephotoBean.setPHOTO_TYPE("3");
            scenephotoBean.setDESCRIPTION(photo.getPhotoInfo());
            scenephotoBean.setSECRECY(SECRECY);
            scenephotoBean.setDELETE_FLAG(DELETE_FLAG);
            scenephotoBean.setCREATE_USER(item.getUserName());
            scenephotoBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            if(item.getUpdateTime() == 0) {
                item.setUpdateTime(System.currentTimeMillis());
            }
            scenephotoBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
            scenephotoBeanList.add(scenephotoBean);
            i++;
        }
        for(Photo photo : item.getDetailPhotoItem()) {
            CrimeScene.SCENEPHOTOBean scenephotoBean = new CrimeScene.SCENEPHOTOBean();
            scenephotoBean.setID(photo.getId());
            scenephotoBean.setSERIAL_NO(i + "");
            scenephotoBean.setINVESTIGATION_ID(item.getId());
            scenephotoBean.setCREATE_USER_ID(userId);
            scenephotoBean.setPHOTO_ID(photo.getId());
            String photoName = photo.getFileName();
            if(photo.getType().equals("bmp")) {
                photoName = photoName.replace("bmp","jpg");
            }
            scenephotoBean.setPHOTO_NAME(photoName);
            scenephotoBean.setPHOTO_TYPE("4");
            scenephotoBean.setDESCRIPTION(photo.getPhotoInfo());
            scenephotoBean.setSECRECY(SECRECY);
            scenephotoBean.setDELETE_FLAG(DELETE_FLAG);
            scenephotoBean.setCREATE_USER(item.getUserName());
            scenephotoBean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            if(item.getUpdateTime() == 0) {
                item.setUpdateTime(System.currentTimeMillis());
            }
            scenephotoBean.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
            scenephotoBeanList.add(scenephotoBean);
            i++;
        }

        return scenephotoBeanList;
    }

    /**
     * wifi信息
     * @param item
     * @return
     */
    private List<CrimeScene.sceneWifiInfo> getWifiInfo(CrimeItem item) {
        List<SceneWifiInfo> wifiInfos = item.getWifiInfos();
        List<CrimeScene.sceneWifiInfo> wifiInfoList = new ArrayList<>();
        for(SceneWifiInfo info : wifiInfos) {
            CrimeScene.sceneWifiInfo entity = new CrimeScene.sceneWifiInfo();
            entity.setID(info.getId());
            entity.setINVESTIGATION_ID(info.getINVERTIGATIONID());
            entity.setCREATE_USER(info.getCREATEUSER());
            entity.setCREATE_DATETIME(info.getCREATEDATETIME());
            entity.setCOLLECTION_DATETIME(info.getCOLLECTIONDATETIME());
//            entity.setLINKSPEED(info.getLINKSPEED());
            entity.setSSID(info.getSSID());
            entity.setBSSID(info.getBSSID());
//            entity.setHIDDENSSID(info.getHIDDENSSID());
            entity.setMACADDRESS(info.getMACADDRESS());
//            entity.setNETWORKLD(info.getNETWORKLD());
            entity.setRSSI(info.getRSSI());
//            entity.setSUPPLICANTSTATE(info.getSUPPLICANTSTATE());
//            entity.setDETAILEDSTATEOF(info.getDETAILEDSTATEOF());
            wifiInfoList.add(entity);
        }
        return wifiInfoList;
    }

    /**
     * 提取物品
     * @param item
     * @return
     */
    private List<CrimeScene.extracts> getExtracts(CrimeItem item) {
        List<GoodEntity> goodEntities = item.getGoodEntities();
        List<CrimeScene.extracts> extractsList = new ArrayList<>();
        for(GoodEntity entity : goodEntities) {
            CrimeScene.extracts extract = new CrimeScene.extracts();
            extract.setID(entity.getId());
            extract.setINVESTIGATION_ID(entity.getCrimeId());
            extract.setCODE(entity.getCode());
            extract.setCOLLECTED_NAME(entity.getCollectedName());
            extract.setMATERIAL_NAME(entity.getMaterialName());
            extract.setCOLLECTED_POSITION(entity.getCollectedPosition());
            extract.setCOLLECTED_METHOD(entity.getCollectedMethod());
            extract.setCOLLECTED_IDS(entity.getCollectedIds());
            extract.setCOLLECTED_DATE(entity.getCollectedDate());
            extract.setREMARK(entity.getRemark());
            extract.setCREATE_USER(item.getUserName());
            extract.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            extractsList.add(extract);
        }
        return extractsList;
    }

    /**
     * 提取物品图片
     * @param item
     * @return
     */
    private List<CrimeScene.extractsPicture> getExtractsPicture(CrimeItem item) {
        List<CrimeScene.extractsPicture> extractsPictures = new ArrayList<>();
        if(item.getGoodEntities().size() > 0) {
            for(GoodEntity entity : item.getGoodEntities()) {
                if(entity.getPhotos() != null && entity.getPhotos().size() > 0) {
                    for(Photo photo : entity.getPhotos()) {
                        CrimeScene.extractsPicture extractsPicture = new CrimeScene.extractsPicture();
                        extractsPicture.setID(photo.getId());
                        extractsPicture.setINVESTIGATION_ID(item.getId());
                        extractsPicture.setEXTRACTS_ID(photo.getParentId());
                        extractsPicture.setWIDTH(photo.getWidth());
                        extractsPicture.setHEIGHT(photo.getHeight());
                        extractsPicture.setFILE_NAME(photo.getFileName());
                        extractsPicture.setCATEGORY("88");
                        extractsPicture.setTYPE(photo.getType());
                        extractsPicture.setSECRECY(SECRECY);
                        extractsPicture.setDELETE_FLAG(DELETE_FLAG);
                        extractsPicture.setCREATE_USER(item.getUserName());
                        extractsPicture.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
                        if(item.getUpdateTime() == 0) {
                            item.setUpdateTime(System.currentTimeMillis());
                        }
                        extractsPicture.setUPDATE_DATETIME(StringUtils.long2String(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
                        extractsPictures.add(extractsPicture);
                    }
                }
            }
        }
        return extractsPictures;
    }

    /**
     * 基站信息
     * @return
     */
    private CrimeScene.KCTCASEINFOBean getKctCaseInfo(CrimeItem item,String kctCaseInfoId) {
        CrimeScene.KCTCASEINFOBean bean = new CrimeScene.KCTCASEINFOBean();
        bean.setID(kctCaseInfoId);
        if(item.getKctbasestationdataBeans().size() > 0) {
            bean.setKCT_UUID(item.getKctbasestationdataBeans().get(0).getUUID());
        }
        bean.setCASE_ID(item.getCaseId());
        bean.setCASE_START_TIME(StringUtils.long2String(item.getOccurred_start_time(),"yyyyMMddHHmmss"));
        bean.setCASE_START_TIME(StringUtils.long2String(item.getOccurred_end_time(),"yyyyMMddHHmmss"));
        bean.setCASE_LON(item.getGpsLon() + "E");
        bean.setCASE_LAT(item.getGpsLat() + "N");
        bean.setINVESTIGATION_ID(item.getId());
        bean.setCREATE_USER(item.getUserName());
        bean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
        return bean;
    }

    /**
     *
     * @param kctCaseInfoId
     * @param kctLocaleDataId
     * @return
     */
    private List<CrimeScene.KCTLOCALEDATABean> getKctLocaleData(CrimeItem item,String kctCaseInfoId, String kctLocaleDataId) {
        List<CrimeScene.KCTLOCALEDATABean> kctlocaledataBeans = new ArrayList<>();
        CrimeScene.KCTLOCALEDATABean bean = new CrimeScene.KCTLOCALEDATABean();
        bean.setID(kctLocaleDataId);
        bean.setLOCALE_NAME(item.getLocation());
        bean.setCOL_STARTTIME(StringUtils.long2String(item.getKctbasestationdataBeans().get(0).getStartTime(),"yyyyMMddHHmmss"));
        bean.setCOL_ENDTIME(StringUtils.long2String(item.getKctbasestationdataBeans().get(0).getEndTime(),"yyyyMMddHHmmss"));
        bean.setCREATE_USER(item.getUserName());
        bean.setCREATE_DATETIME(StringUtils.long2String(item.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
        bean.setCASE_INFO_ID(kctCaseInfoId);
        bean.setDATA_TYPE("1");
        kctlocaledataBeans.add(bean);
        return kctlocaledataBeans;
    }

    /**
     *
     * @param kctLocaleDataId
     * @return
     */
    private List<CrimeScene.KCTBASESTATIONDATABean> getKctBasestationData(CrimeItem item,String kctLocaleDataId) {
        List<CrimeScene.KCTBASESTATIONDATABean> kctbasestationdataBeans = new ArrayList<>();
        for(KCTBASESTATIONDATABean bean : item.getKctbasestationdataBeans()) {
            CrimeScene.KCTBASESTATIONDATABean sceneBean = new CrimeScene.KCTBASESTATIONDATABean();
            sceneBean.setID(bean.getId());
            sceneBean.setBASE_ID(bean.getBASE_ID());
            sceneBean.setIFACTIVE(bean.getIFACTIVE());
            sceneBean.setREG_ZONE(bean.getREG_ZONE());
            sceneBean.setSID(bean.getSID());
            sceneBean.setNID(bean.getNID());
            sceneBean.setBASE_ID(bean.getBASE_ID());
            sceneBean.setCDMA_CH(bean.getCDMA_CH());
            sceneBean.setPN(bean.getPN());
            sceneBean.setSTRENGTH(bean.getSTRENGTH());
            sceneBean.setMCC_MNC(bean.getMCC_MNC());
            sceneBean.setLAC(bean.getLAC());
            sceneBean.setCELL_ID(bean.getCELL_ID());
            sceneBean.setBCCH(bean.getBCCH());
            sceneBean.setBSIC(bean.getBSIC());
            sceneBean.setSYS_BAND(bean.getSYS_BAND());
            sceneBean.setLON(bean.getLON());
            sceneBean.setLAT(bean.getLAT());
            sceneBean.setLOCALE_DATA_ID(kctLocaleDataId);
            sceneBean.setCOL_TIME(bean.getCOL_TIME());
            sceneBean.setERFCN(bean.getERFCN());
            sceneBean.setPCI(bean.getPCI());
            sceneBean.setBAND(bean.getBAND());
            sceneBean.setCELL(bean.getCELL());
            sceneBean.setEARFCN(bean.getEARFCN());
            sceneBean.setRSRP(bean.getRSRP());
            sceneBean.setRSRQ(bean.getRSRQ());
            sceneBean.setRSSI(bean.getRSSI());
            sceneBean.setRAC(bean.getRAC());
            sceneBean.setRNCID(bean.getRNCID());
            sceneBean.setENBID(bean.getENBID());
            sceneBean.setPHYCELLID(bean.getPHYCELLID());
            sceneBean.setCELLPARAMID(bean.getCELLPARAMID());
            sceneBean.setTAC(bean.getTAC());
            sceneBean.setBS_TYPE(bean.getBS_TYPE());
            kctbasestationdataBeans.add(sceneBean);
        }
        return kctbasestationdataBeans;
    }


    /**
     * 得到受害人或报案人信息
     * @param item
     * @param type
     * @return
     */
    private List<ContactsEntity> ContactsGetTypeValue(CrimeItem item,String type) {
        List<ContactsEntity> contactsEntityList = new ArrayList<>();
        for(ContactsEntity entity : item.getReleatedPeopleItem()) {
            if(StringUtils.checkString(entity.getType()) && entity.getType().equals(type)) {
                contactsEntityList.add(entity);
            }
        }
        return contactsEntityList;
    }

    /**
     * 得到不同痕迹类型的痕迹信息
     * @param item
     * @param type
     * @return
     */
    private List<EvidenceEntity> EvidenceGetTypeValue(CrimeItem item,String type) {
        List<EvidenceEntity> evidenceEntityList = new ArrayList<>();
        for(EvidenceEntity entity : item.getEvidenceItem()) {
            if(entity.getEvidenceCategory().equals(type)) {
                evidenceEntityList.add(entity);
            }
        }
        return evidenceEntityList;
    }

    public String getString(List<String> values) {
        String value = "";
        if(values.size() > 0) {
            for (int i = 0; i < values.size(); i++) {
                if(i != values.size() -1) {
                    value = value +values.get(i) + ",";
                }else {
                    value = value +values.get(i);
                }
            }
        }
        return value;
    }
}
