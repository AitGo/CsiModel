package com.liany.csiserverapp.diagnose;

import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2019/4/16
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class CrimeScene {


    /**
     * SCENE_INVESTIGATION : {"ID":"394be5a5fee343a49481dd1a64f5eceb","INVESTIGATION_NO":[],"RECEPTION_ID":"c575046d326c4feca61d2f33f6ac29ef","CASE_ID":"b66c45d471854af1a8a66870cf057c63","ITERATION_NO":"0","INVESTIGATION_DATE_FROM":"20190318173433","INVESTIGATION_DATE_TO":"20190318183433","INVESTIGATION_PLACE":"谢谢","ENV_TEMPERATURE":"20","ENV_MOISTNESS":"35","WIND":"09","WEATHER":"2","OTHER_WEATHER":[],"SCENE_CONDITION":"1","LIGHTING":"1","PROTECTION_DATE":"20190319113601","PROTECTION_MEASURE":"专人看护现场，防止他人进入","PROTECTOR":"重庆市刑科所&许洋&民警","INVEST_NOTE_ID":"e6d7cfe7790140fa8bd33a5eec964131","UPDATED_INVEST_NOTE_ID":[],"SCENE_LOSS":[],"OVERTURN_DEGREE":[],"WOUNDED_AMOUNT":"0","DEAD_AMOUNT":"0","LOST_TOTAL_VALUE":"888","REMNANT_DESC":[],"SCENE_DISPOSAL":[],"DIRECTOR":"许洋","INVESTIGATOR":"许洋,测试","DIRECTOR_IDS":"2f869bcd60b646ab0160b649f223000b","INVESTIGATOR_IDS":"2f869bcd60b646ab0160b649f223000b,2f869bcd60b646ab0160b64a63db000c","INVESTIGATOR_DUTIES":[],"WITNESS":"这种(男,0,,)","HANDPRINT_FLAG":[],"FOOTPRINT_FLAG":[],"TOOLMARK_FLAG":[],"BULLETPRINT_FLAG":[],"SPECIALPRINT_FLAG":[],"BIO_EVIDENCE_FLAG":[],"TOXIC_EVIDENCE_FLAG":[],"PHYSICAL_EVIDENCE_FLAG":[],"FILE_EVIDENCE_FLAG":[],"ELECTRO_EVIDENCE_FLAG":[],"VIDEO_EVIDENCE_FLAG":[],"OTHER_EVIDENCE_FLAG":[],"HANDPRINT_AMOUNT":"1","FOOTPRINT_AMOUNT":"0","TOOLMARK_AMOUNT":"0","BULLETPRINT_AMOUNT":"0","SPECIALPRINT_AMOUNT":"0","BIO_EVIDENCE_AMOUNT":"0","TOXIC_EVIDENCE_AMOUNT":"0","PHYSICAL_EVIDENCE_AMOUNT":"0","FILE_EVIDENCE_AMOUNT":"0","ELECTRO_EVIDENCE_AMOUNT":"0","VIDEO_EVIDENCE_AMOUNT":"0","OTHER_EVIDENCE_AMOUNT":"0","SCENE_PHOTO_AMOUNT":"5","UNUSED_SCENE_PHOTO_AMOUNT":"0","SCENE_PICTURE_AMOUNT":"2","SAVE_FLAG":"0","FINISH_FLAG":"0","UNFINISH_REASON":[],"QUALIFIED_FLAG":"0","UNQUALIFIED_REASON":[],"NOTE_MADE_FLAG":[],"NOTE_MADE_DATE":[],"WRITER":[],"DRAFTER":[],"CAMERIST":[],"VIDEOER":[],"RECORDIST":[],"VIDEO_TIME":[],"RECORD_TIME":[],"USER_ID":"2f869bcd610c539301610c6d8f4b0002","INIT_SERVER_NO":"500000000000","MODIFY_FLAG":"1","MODIFY_REASON":[],"DELETE_REASON":[],"MAIN_ORGAN_ID":"12345678901234567890123456789a01","MAIN_ORGAN_NAME":"重庆市刑科所","HELP_ORGAN_ID":[],"HELP_ORGAN_NAME":[],"TRANSFER_DATE":"20190319113601","TRANSFER_FLAG":[],"SECRECY":"1","DELETE_FLAG":"0","CREATE_USER":"许洋","CREATE_DATETIME":"20190319113601","UPDATE_USER":[],"UPDATE_DATETIME":"20190319113601","RESERVER1":[],"RESERVER2":[],"RESERVER3":"1","RESERVER4":[],"RESERVER5":[],"RESERVER6":[],"RESERVER7":[],"RESERVER8":[],"OTHER_PERSONS":[],"SUBMIT_DATETIME":[],"SBBID":[],"ENTRY_VERSION":"standard","BDYY":[],"BDYY_DESC":[]}
     * SCENE_LAW_CASE : {"ID":"b66c45d471854af1a8a66870cf057c63","RECEPTION_NO":[],"CASE_NO":[],"CASE_TYPE":"050227","CASE_CATEGORY":[],"CASE_NAME":[],"CASE_LEVEL":[],"SCENE_REGIONALISM":"500000000000","SCENE_DETAIL":"谢谢","OCCURRENCE_DATE_FROM":"20190318153433","OCCURRENCE_DATE_TO":"20190318163433","EXPOSURE_PROCESS":"据<被害人/报案人>报称:2019年03月18日15时34分，在谢谢，该处发现一起盗窃自行车，后拨打电话报警。","VICTIM_INFO":[],"INIT_SERVER_NO":[],"TRANSFER_DATE":"20190319113601","REMARK":[],"CRACKED_DATE":[],"SECRECY":"1","DELETE_FLAG":"0","CREATE_USER":"许洋","CREATE_DATETIME":"20190319113601","UPDATE_USER":[],"UPDATE_DATETIME":"20190319113601","RESERVER1":[],"RESERVER2":"394be5a5fee343a49481dd1a64f5eceb","RESERVER3":[],"RESERVER4":"0.0","RESERVER5":[],"RESERVER6":"0.0","RESERVER7":[],"RESERVER8":[],"IFFLAG":[]}
     * SCENE_RECEPTION_DISPATCH : {"ID":"c575046d326c4feca61d2f33f6ac29ef","RECEIVED_DATE":"20190318170433","RECEIVED_BY":"妹子","ASSIGNED_BY":"110指挥中心","ASSIGNED_WAY_T":[],"ASSIGNED_WAY_S":[],"ASSIGNED_CONTENT":"重庆市刑科所接到110指挥中心的指派: 在该所管界内谢谢发生一起盗窃自行车，请速派人员勘查现场。","DISPATCH_DATE":"20190318170433","INIT_SERVER_NO":[],"TRANSFER_DATE":"20190319113601","SECRECY":"1","DELETE_FLAG":"0","CREATE_USER":"许洋","CREATE_DATETIME":"20190319113601","UPDATE_USER":[],"UPDATE_DATETIME":"20190319113601","RESERVER1":[],"RESERVER2":[],"RESERVER3":[],"RESERVER4":[],"RESERVER5":[],"RESERVER6":[],"RESERVER7":[],"RESERVER8":[]}
     * SCENE_ANALYSIS_SUGGESTION : {"ID":"eab7cccaa2444792866738ae5c31f2b1","INVESTIGATION_ID":"394be5a5fee343a49481dd1a64f5eceb","METERIALS_RELIED_ON":[],"INTENTION":[],"INTENTION_CN":[],"INTENTION_DESC":[],"LOCATION":[],"LOCATION_CN":[],"LOCATION_DESC":[],"INVOLVED_ORG_TYPE":[],"INVOLVED_ORG_CN":[],"INVOLVED_ORG_DESC":[],"COMMISSION_PLACE":"谢谢","COMMISSION_PERIOD":[],"COMMISSION_PERIOD_CN":[],"COMMISSION_PERIOD_DESC":[],"ENTRANCE_EXIT":[],"ENTRANCE_EXIT_CN":[],"ENTRANCE_EXIT_DESC":[],"INTRUDING_WAY":[],"INTRUDING_WAY_CN":[],"INTRUDING_WAY_DESC":[],"COMMISSION_MEANS":[],"COMMISSION_MEANS_CN":[],"COMMISSION_MEANS_DESC":[],"COMMISSION_POINTS":[],"COMMISSION_POINTS_CN":[],"COMMISSION_POINTS_DESC":[],"MOTIVATION":[],"MOTIVATION_CN":[],"MOTIVATION_DESC":[],"CASE_PROPERTY":[],"CASE_PROPERTY_CN":[],"CASE_PROPERTY_DESC":[],"CRIMINAL_AMOUNT":"2","CRIMINAL_AMOUNT_DESC":[],"COMMISSION_DESC_ID":[],"CRIMINAL_POINTS_ID":"0577434dba8a43e4b29e5256dd666df4","BUNCH_REASON_ID":[],"SUGGESTION1":[],"SUGGESTION2":[],"SUGGESTION3":[],"SUGGESTION4":[],"SUGGESTION5":[],"SUGGESTION6":[],"ANALYSED_ORG":"许洋","ANALYSED_BY":"重庆市刑科所","ANALYSED_DATE":"20190319113601","INIT_SERVER_NO":[],"TRANSFER_DATE":[],"SECRECY":"1","DELETE_FLAG":"0","CREATE_USER":"重庆市刑科所","CREATE_DATETIME":"20190319113601","UPDATE_USER":[],"UPDATE_DATETIME":"20190319113601","RESERVER1":[],"RESERVER2":[],"RESERVER3":[],"RESERVER4":[],"RESERVER5":[],"RESERVER6":[],"RESERVER7":[],"RESERVER8":[],"EXIT_ENTRANCE":[],"EXIT_ENTRANCE_CN":[],"EXIT_ENTRANCE_DESC":[]}
     * SCENE_ANALYSIS_RESULT : {"ID":"8fc378419b0a4629840a85ae6219949b","INVESTIGATION_ID":"394be5a5fee343a49481dd1a64f5eceb","METERIALS_RELIED_ON":[],"INTENTION_DESC":[],"LOCATION_DESC":[],"INVOLVED_ORG_TYPE_DESC":[],"LOST_GOODS_DESC":"(测试，v，5，888，)","COMMISSION_PLACE":"谢谢","PERIOD_DESC":[],"ENTRANCE_EXIT_DESC":[],"INTRUDING_WAY_DESC":[],"COMMISSION_MEANS_DESP":[],"PERCULIARITIES_DESCRIPTION":[],"TOOLS_DESCRIPTION":"(刀，交通工具类，就地取材)","MOTIVE_DESC":[],"CASE_PROPERTY_DESC":[],"CRIMINAL_AMOUNT_DESC":"多人","COMMISSION_DESC_ID":[],"CRIMINAL_POINTS_ID":"0577434dba8a43e4b29e5256dd666df4","BUNCH_REASON_ID":[],"SUGGESTION_ID":[],"ANALYSED_ORG":"许洋","ANALYSED_BY":"重庆市刑科所","ANALYSED_DATE":"20190319113601","INIT_SERVER_NO":[],"TRANSFER_DATE":[],"SECRECY":"1","DELETE_FLAG":"0","CREATE_USER":"重庆市刑科所","CREATE_DATETIME":"20190319113601","UPDATE_USER":[],"UPDATE_DATETIME":"20190319113601","RESERVER1":[],"RESERVER2":[],"RESERVER3":[],"RESERVER4":[],"RESERVER5":[],"RESERVER6":[],"RESERVER7":[],"RESERVER8":[],"EXIT_ENTRANCE_DESC":[]}
     * COMMON_BIGTEXT_CLOB : {"ID":"e6d7cfe7790140fa8bd33a5eec964131","CONTENT":[],"REMARK":[],"SECRECY":"1","DELETE_FLAG":"0","CREATE_USER":"许洋","CREATE_DATETIME":"20190319113603","UPDATE_USER":[],"UPDATE_DATETIME":"20190319113603","RESERVER1":[],"RESERVER2":[],"RESERVER3":[],"RESERVER4":[],"RESERVER5":[],"RESERVER6":[],"RESERVER7":[],"RESERVER8":[]}
     * SCENE_COMMISSION_TOOLS : {"ID":"1a85016806364a8caafc1920bc7f0f52","ANALYSIS_ID":"eab7cccaa2444792866738ae5c31f2b1","SPECIES":"2","NAME":"刀","ORIGIN":"A","DESCRIPTION":[],"SECRECY":"1","DELETE_FLAG":"0","CREATE_USER":"许洋","CREATE_DATETIME":"20190319113601","UPDATE_USER":[],"UPDATE_DATETIME":"20190319113601","RESERVER1":[],"RESERVER2":[],"RESERVER3":[],"RESERVER4":[],"RESERVER5":[],"RESERVER6":[],"RESERVER7":[],"RESERVER8":[]}
     * SCENE_LOST_GOODS : {"ID":"97b09d17d61045df9e46497ba503ba47","SERIAL_NO":"1","INVESTIGATION_ID":"394be5a5fee343a49481dd1a64f5eceb","NAME":"测试","BRAND":"v","SPECIFICATION":[],"DESCRIPTION":[],"COLOR":[],"AMOUNT":"5","VALUE":"888","REMARK":[],"SECRECY":"1","DELETE_FLAG":"0","CREATE_USER":"许洋","CREATE_DATETIME":"20190319113601","UPDATE_USER":[],"UPDATE_DATETIME":"20190319113601","RESERVER1":[],"RESERVER2":[],"RESERVER3":[],"RESERVER4":[],"RESERVER5":[],"RESERVER6":[],"RESERVER7":[],"RESERVER8":[]}
     * SCENE_WITNESS : {"ID":"5f0ce97266d64b35a55be86faaa6bf4e","INVESTIGATION_ID":"394be5a5fee343a49481dd1a64f5eceb","SERIAL_NO":"1","NAME":"这种","SEX":"1","AGE":"0","PHONE":[],"ORGANIZATION":[],"ADDRESS":[],"REMARK":[],"SECRECY":"1","DELETE_FLAG":"0","CREATE_USER":"许洋","CREATE_DATETIME":"20190319113601","UPDATE_USER":[],"UPDATE_DATETIME":"20190319113601","RESERVER1":[],"RESERVER2":[],"RESERVER3":[],"RESERVER4":"2019-03-18","RESERVER5":[],"RESERVER6":[],"RESERVER7":[],"RESERVER8":[]}
     * SCENE_VICTIM : {"ID":"f7273149373e46839c85bbe07418260f","CASE_ID":"b66c45d471854af1a8a66870cf057c63","NAME":"测试","SEX":"1","AGE":[],"FIGURE":[],"HARM_FORM":[],"HARM_PART":[],"VIOLATION":[],"PHONE":"073188712585","ORGANIZATION":[],"ADDRESS_REGIONALISM":[],"ADDRESS":"长沙市岳麓区西","NATIVE_PLACE_REGIONALISM":[],"NATIVE_PLACE":[],"SECRECY":"1","DELETE_FLAG":"0","CREATE_USER":"许洋","CREATE_DATETIME":"20190319113601","UPDATE_USER":[],"UPDATE_DATETIME":"20190319113601","RESERVER1":"0","RESERVER2":[],"RESERVER3":[],"RESERVER4":[],"RESERVER5":[],"RESERVER6":[],"RESERVER7":[],"RESERVER8":[]}
     * SCENE_PICTURE : {"ID":"3cc3f43724574d8bbd6b4cccdb2e3aa3","SERIAL_NO":"2","INVESTIGATION_ID":"394be5a5fee343a49481dd1a64f5eceb","CREATE_USER_ID":"2f869bcd610c539301610c6d8f4b0002","PICTURE_ID":"43827c245b49455198cf7c1e48492705","PICTURE_NAME":"2019年03月18日重庆市刑科所盗窃自行车现场示意图","PICTURE_TYPE":"1010","DESCRIPTION":"2019年03月18日重庆市刑科所盗窃自行车现场示意图","SECRECY":"1","DELETE_FLAG":"0","CREATE_USER":"许洋","CREATE_DATETIME":"20190319113602","UPDATE_USER":[],"UPDATE_DATETIME":"20190319113602","RESERVER1":[],"RESERVER2":[],"RESERVER3":[],"RESERVER4":[],"RESERVER5":[],"RESERVER6":[],"RESERVER7":[],"RESERVER8":[]}
     * SCENE_PHOTO : [{"ID":"84e4a52c53254edf8e6f19e388eaf532","SERIAL_NO":"4","INVESTIGATION_ID":"394be5a5fee343a49481dd1a64f5eceb","CREATE_USER_ID":"2f869bcd610c539301610c6d8f4b0002","PHOTO_ID":"e70eab78483b4572b214f5b7b152d69f","PHOTO_NAME":"/MONITORING_PHOTO_20190318_17510p.jpg","PHOTO_TYPE":"A","DESCRIPTION":"监控","SECRECY":"1","DELETE_FLAG":"0","CREATE_USER":"许洋","CREATE_DATETIME":"20190319113602","UPDATE_USER":[],"UPDATE_DATETIME":"20190319113602","RESERVER1":[],"RESERVER2":[],"RESERVER3":[],"RESERVER4":[],"RESERVER5":[],"RESERVER6":[],"RESERVER7":[],"RESERVER8":[]},{"ID":"641cc97b94184e51bd6949928c4ecdaa","SERIAL_NO":"5","INVESTIGATION_ID":"394be5a5fee343a49481dd1a64f5eceb","CREATE_USER_ID":"2f869bcd610c539301610c6d8f4b0002","PHOTO_ID":"4c67abec235d4bdb9d1e9b0671150a4b","PHOTO_NAME":"/CAMERA_PHOTO_20190318_17511p.jpg","PHOTO_TYPE":"3","DESCRIPTION":"摄像头","SECRECY":"1","DELETE_FLAG":"0","CREATE_USER":"许洋","CREATE_DATETIME":"20190319113603","UPDATE_USER":[],"UPDATE_DATETIME":"20190319113603","RESERVER1":[],"RESERVER2":[],"RESERVER3":[],"RESERVER4":[],"RESERVER5":[],"RESERVER6":[],"RESERVER7":[],"RESERVER8":[]}]
     * COMMON_PICTURE : {"ID":"e70eab78483b4572b214f5b7b152d69f","INVESTIGATION_ID":"394be5a5fee343a49481dd1a64f5eceb","CONTENT":"v/Pncf9+j/hViwsLxdQtma0nAEqknym9R7UAf/9k=","WIDTH":"1200","HEIGHT":"1600","FILE_NAME":"/MONITORING_PHOTO_20190318_17510p.jpg","CATEGORY":"2","SUB_CATEGORY":[],"TYPE":"jpg","DESCRIPTION":[],"SECRECY":"1","DELETE_FLAG":"0","CREATE_USER":"许洋","CREATE_DATETIME":"20190319113603","UPDATE_USER":[],"UPDATE_DATETIME":"20190319113603","RESERVER1":[],"RESERVER2":[],"RESERVER3":[],"RESERVER4":[],"RESERVER5":[],"RESERVER6":[],"RESERVER7":[],"RESERVER8":[]}
     * SCENE_HANDPRINT : {"ID":"14319022b331493fbd4663b75a86bf91","SERIAL_NO":"1","CREATE_USER_ID":"2f869bcd610c539301610c6d8f4b0002","INVESTIGATION_ID":"394be5a5fee343a49481dd1a64f5eceb","HANDPRINT_PHOTO_ID":"4707487ecc6146e2846db969e2e9bd05","PRINT_TYPE":"1102","PRINT_CODE":[],"LEFT_POSITION":"出差","COLLECTION_MODE":"A01","COLLECTED_BY":"2f869bcd60b646ab0160b649f223000b,2f869bcd60b646ab0160b64a63db000c","COLLECTED_BY_NAME":"许洋,测试","COLLECTED_DATE":"20190318175057","CRIMINAL_FLAG":[],"STORAGE_FLAG":[],"UTILIZATION":[],"PRINT_FLAG":[],"SECRECY":"1","DELETE_FLAG":"0","CREATE_USER":"许洋","CREATE_DATETIME":"20190319113601","UPDATE_USER":[],"UPDATE_DATETIME":"20190319113601","RESERVER1":[],"RESERVER2":[],"RESERVER3":[],"RESERVER4":[],"RESERVER5":[],"RESERVER6":[],"RESERVER7":[],"RESERVER8":[]}
     * SCENE_FOOTPRINT : {"ID":"14319022b331493fbd4663b75a86bf91","SERIAL_NO":"1","CREATE_USER_ID":"2f869bcd610c539301610c6d8f4b0002","INVESTIGATION_ID":"394be5a5fee343a49481dd1a64f5eceb","FOOTPRINT_PHOTO_ID":"4707487ecc6146e2846db969e2e9bd05","PRINT_TYPE":"1102","PRINT_CODE_ID":[],"LEFT_POSITION":"出差","COLLECTION_MODE":"A01","COLLECTED_BY":"2f869bcd60b646ab0160b649f223000b,2f869bcd60b646ab0160b64a63db000c","COLLECTED_BY_NAME":"许洋,测试","COLLECTED_DATE":"20190318175057","CRIMINAL_FLAG":[],"STORAGE_FLAG":[],"UTILIZATION":[],"PRINT_FLAG":[],"SECRECY":"1","DELETE_FLAG":"0","CREATE_USER":"许洋","CREATE_DATETIME":"20190319113601","UPDATE_USER":[],"UPDATE_DATETIME":"20190319113601","RESERVER1":[],"RESERVER2":[],"RESERVER3":[],"RESERVER4":[],"RESERVER5":[],"RESERVER6":[],"RESERVER7":[],"RESERVER8":[]}
     * SCENE_TOOLMARK : {"ID":"14319022b331493fbd4663b75a86bf91","SERIAL_NO":"1","CREATE_USER_ID":"2f869bcd610c539301610c6d8f4b0002","INVESTIGATION_ID":"394be5a5fee343a49481dd1a64f5eceb","TOOLMARK_PHOTO_ID":"4707487ecc6146e2846db969e2e9bd05","PRINT_TYPE":"1102","TOOL_JUDGEMENT":[],"DESCRIPTION":[],"LEFT_POSITION":"出差","COLLECTION_MODE":"A01","COLLECTED_BY":"2f869bcd60b646ab0160b649f223000b,2f869bcd60b646ab0160b64a63db000c","COLLECTED_BY_NAME":"许洋,测试","COLLECTED_DATE":"20190318175057","CRIMINAL_FLAG":[],"STORAGE_FLAG":[],"UTILIZATION":[],"PRINT_FLAG":[],"SECRECY":"1","DELETE_FLAG":"0","CREATE_USER":"许洋","CREATE_DATETIME":"20190319113601","UPDATE_USER":[],"UPDATE_DATETIME":"20190319113601","RESERVER1":[],"RESERVER2":[],"RESERVER3":[],"RESERVER4":[],"RESERVER5":[],"RESERVER6":[],"RESERVER7":[],"RESERVER8":[]}
     * SCENE_OTHER_EVIDENCE : {"ID":"14319022b331493fbd4663b75a86bf91","SERIAL_NO":"1","CREATE_USER_ID":"2f869bcd610c539301610c6d8f4b0002","INVESTIGATION_ID":"394be5a5fee343a49481dd1a64f5eceb","EVIDENCE_PHOTO_ID":"4707487ecc6146e2846db969e2e9bd05","EVIDENCE_TYPE":"1102","DESCRIPTION":[],"LEFT_POSITION":"出差","COLLECTION_MODE":"A01","COLLECTED_BY":"2f869bcd60b646ab0160b649f223000b,2f869bcd60b646ab0160b64a63db000c","COLLECTED_BY_NAME":[],"COLLECTED_DATE":"20190318175057","CRIMINAL_FLAG":[],"STORAGE_FLAG":[],"UTILIZATION":[],"PRINT_FLAG":[],"SECRECY":"1","DELETE_FLAG":"0","CREATE_USER":"许洋","CREATE_DATETIME":"20190319113601","UPDATE_USER":[],"UPDATE_DATETIME":"20190319113601","RESERVER1":[],"RESERVER2":[],"RESERVER3":[],"RESERVER4":[],"RESERVER5":[],"RESERVER6":[],"RESERVER7":[],"RESERVER8":[]}
     * SCENE_VIDEO_EVIDENCE : {"ID":"14319022b331493fbd4663b75a86bf91","SERIAL_NO":"1","CREATE_USER_ID":"2f869bcd610c539301610c6d8f4b0002","INVESTIGATION_ID":"394be5a5fee343a49481dd1a64f5eceb","EVIDENCE_PHOTO_ID":"4707487ecc6146e2846db969e2e9bd05","EVIDENCE_TYPE":"1102","DESCRIPTION":[],"LEFT_POSITION":"出差","COLLECTION_MODE":"A01","COLLECTED_BY":"2f869bcd60b646ab0160b649f223000b,2f869bcd60b646ab0160b64a63db000c","COLLECTED_DATE":"20190318175057","COLLECTED_BY_NAME":[],"CRIMINAL_FLAG":[],"STORAGE_FLAG":[],"UTILIZATION":[],"PRINT_FLAG":[],"SECRECY":"1","DELETE_FLAG":"0","CREATE_USER":"许洋","CREATE_DATETIME":"20190319113601","UPDATE_USER":[],"UPDATE_DATETIME":"20190319113601","RESERVER1":[],"RESERVER2":[],"RESERVER3":[],"RESERVER4":[],"RESERVER5":[],"RESERVER6":[],"RESERVER7":[],"RESERVER8":[]}
     * KCT_CASE_INFO : {"ID":"862f3ca3bea644d294678a14429c8c58","CASE_ID":[],"KCT_UUID":"686315bdc1e04627b4e4448d3cdcf9b5","CASE_START_TIME":"20190318153433","CASE_END_TIME":"20190318163433","CASE_LON":"0.0E","CASE_LAT":"0.0N","WITNESS_INFO":[],"REMARK":[],"CREATE_USER":"许洋","CREATE_DATETIME":"20190319113603","UPDATE_USER":[],"UPDATE_DATETIME":[],"RESERVER1":[],"RESERVER2":[],"RESERVER3":[],"RESERVER4":[],"RESERVER5":[],"RESERVER6":[],"RESERVER7":[],"RESERVER8":[],"GPS_NAME":[],"CASE_NAME":[],"INVESTIGATION_ID":"394be5a5fee343a49481dd1a64f5eceb"}
     * KCT_LOCALE_DATA : {"ID":"2cc8c787dee24deeb6577eedde69ba9b","LOCALE_NAME":"谢谢","COL_STARTTIME":"20190318173433","COL_ENDTIME":"20190318183433","CREATE_USER":"许洋","CREATE_DATETIME":"20190319113603","UPDATE_USER":[],"UPDATE_DATETIME":"20190319113603","RESERVER1":[],"RESERVER2":[],"RESERVER3":[],"RESERVER4":[],"RESERVER5":[],"RESERVER6":[],"RESERVER7":[],"RESERVER8":[],"DATA_TYPE":"1","CASE_INFO_ID":"862f3ca3bea644d294678a14429c8c58"}
     * KCT_BASESTATION_DATA : {"ID":"d21e94312cde4bdc9a5369fca75c0ae7","BS_TYPE":"FDD_CT","IFACTIVE":"ACTIVE","REG_ZONE":[],"SID":[],"NID":[],"BASE_ID":[],"CDMA_CH":[],"PN":[],"STRENGTH":[],"MCC_MNC":"460-11","LAC":"34684","CELL_ID":"142376198","BCCH":[],"BSIC":[],"SYS_BAND":[],"RESERVER1":[],"RESERVER2":[],"RESERVER3":[],"RESERVER4":[],"RESERVER5":[],"RESERVER6":[],"RESERVER7":[],"RESERVER8":[],"RESERVER9":[],"RESERVER10":[],"LON":[],"LAT":[],"LOCALE_DATA_ID":"2cc8c787dee24deeb6577eedde69ba9b","COL_TIME":"20190318175051","ERFCN":[],"PCI":"368","BAND":[],"CELL":"142376198","EARFCN":[],"RSRP":"41","RSRQ":[],"RSSI":"41","RAC":[],"RNCID":[],"ENBID":[],"PHYCELLID":[],"CELLPARAMID":[],"TAC":"34684"}
     * COMMON_ATTACHMENT : {"ID":"b53dee31692346aa8367c5471425ce98","CONTENT":[],"FILE_NAME":"data.xml","CATEGORY":"01","TYPE":"xml","SECRECY":"1","DELETE_FLAG":"0","CREATE_USER":"许洋","CREATE_DATETIME":"20190319113603","UPDATE_USER":[],"UPDATE_DATETIME":"20190319113603","RESERVER1":"394be5a5fee343a49481dd1a64f5eceb","RESERVER2":"862f3ca3bea644d294678a14429c8c58","RESERVER3":"1","RESERVER4":[],"RESERVER5":[],"RESERVER6":[],"RESERVER7":[],"RESERVER8":[]}
     */

    private SCENEINVESTIGATIONBean 	SceneInvestigation;//现勘信息
    private SCENELAWCASEBean 	SceneLawCase;//案件基本信息
    private SCENERECEPTIONDISPATCHBean 	SceneReceptionDispatch;//现场接受指派表
    private SCENEANALYSISSUGGESTIONBean SceneAnalysisSuggestion;//现场分析意见表
    private SCENEANALYSISRESULTBean SceneAnalysisResult;//现场分析意见结果表
    private List<COMMONBIGTEXTCLOBBean> CommonBigtextClob;//通用大文本存储表
    private List<SCENECOMMISSIONTOOLSBean> 	SceneCommissionTools;//现场借助工具表
    private List<SCENELOSTGOODSBean> SceneLostGoods;//现场遗失物品表
    private List<SCENEWITNESSBean> SceneWitness;//现场见证人信息表
    private List<SCENEVICTIMBean> 	SceneVictim;//被害人事主表
    private List<SCENEPICTUREBean> 	ScenePicture;//现场图片表
    private List<COMMONPICTUREBean> CommonPicture;//通用图片表
    private List<SCENEHANDPRINTBean> 	SceneHandprint;//手纹痕迹信息表
    private List<SCENEFOOTPRINTBean> 	SceneFootprint;//足迹物证信息表
    private List<SCENETOOLMARKBean> 	SceneToolmark;//工具痕迹表
    private List<SCENEOTHEREVIDENCEBean> SceneOtherEvidence;//其他物证表
    private List<SCENEVIDEOEVIDENCEBean> SceneVideoEvidence;//视听物证表
    private KCTCASEINFOBean KctCaseInfo;//
    private List<KCTLOCALEDATABean> KctLocaleData;
    private List<KCTBASESTATIONDATABean> KctBasestationData;
    private List<COMMONATTACHMENTBean> CommonAttachment;//通用附件表
    private List<SCENEPHOTOBean> 	ScenePhoto;//现场照片表
    private List<sceneWifiInfo> sceneWifiInfo;//wifi信息
    private List<extracts> extracts;//提取物品
    private List<extractsPicture> extractsPicture;//提取物品图片

    public List<CrimeScene.extractsPicture> getExtractsPicture() {
        return extractsPicture;
    }

    public void setExtractsPicture(List<CrimeScene.extractsPicture> extractsPicture) {
        this.extractsPicture = extractsPicture;
    }

    public List<CrimeScene.extracts> getExtracts() {
        return extracts;
    }

    public void setExtracts(List<CrimeScene.extracts> extracts) {
        this.extracts = extracts;
    }

    public List<CrimeScene.sceneWifiInfo> getSceneWifiInfo() {
        return sceneWifiInfo;
    }

    public void setSceneWifiInfo(List<CrimeScene.sceneWifiInfo> sceneWifiInfo) {
        this.sceneWifiInfo = sceneWifiInfo;
    }

    public SCENEINVESTIGATIONBean getSceneInvestigation() {
        return SceneInvestigation;
    }

    public void setSceneInvestigation(SCENEINVESTIGATIONBean sceneInvestigation) {
        SceneInvestigation = sceneInvestigation;
    }

    public SCENELAWCASEBean getSceneLawCase() {
        return SceneLawCase;
    }

    public void setSceneLawCase(SCENELAWCASEBean sceneLawCase) {
        SceneLawCase = sceneLawCase;
    }

    public SCENERECEPTIONDISPATCHBean getSceneReceptionDispatch() {
        return SceneReceptionDispatch;
    }

    public void setSceneReceptionDispatch(SCENERECEPTIONDISPATCHBean sceneReceptionDispatch) {
        SceneReceptionDispatch = sceneReceptionDispatch;
    }

    public SCENEANALYSISSUGGESTIONBean getSceneAnalysisSuggestion() {
        return SceneAnalysisSuggestion;
    }

    public void setSceneAnalysisSuggestion(SCENEANALYSISSUGGESTIONBean sceneAnalysisSuggestion) {
        SceneAnalysisSuggestion = sceneAnalysisSuggestion;
    }

    public SCENEANALYSISRESULTBean getSceneAnalysisResult() {
        return SceneAnalysisResult;
    }

    public void setSceneAnalysisResult(SCENEANALYSISRESULTBean sceneAnalysisResult) {
        SceneAnalysisResult = sceneAnalysisResult;
    }

    public List<COMMONBIGTEXTCLOBBean> getCommonBigtextClob() {
        return CommonBigtextClob;
    }

    public void setCommonBigtextClob(List<COMMONBIGTEXTCLOBBean> commonBigtextClob) {
        CommonBigtextClob = commonBigtextClob;
    }

    public List<SCENECOMMISSIONTOOLSBean> getSceneCommissionTools() {
        return SceneCommissionTools;
    }

    public void setSceneCommissionTools(List<SCENECOMMISSIONTOOLSBean> sceneCommissionTools) {
        SceneCommissionTools = sceneCommissionTools;
    }

    public List<SCENELOSTGOODSBean> getSceneLostGoods() {
        return SceneLostGoods;
    }

    public void setSceneLostGoods(List<SCENELOSTGOODSBean> sceneLostGoods) {
        SceneLostGoods = sceneLostGoods;
    }

    public List<SCENEWITNESSBean> getSceneWitness() {
        return SceneWitness;
    }

    public void setSceneWitness(List<SCENEWITNESSBean> sceneWitness) {
        SceneWitness = sceneWitness;
    }

    public List<SCENEVICTIMBean> getSceneVictim() {
        return SceneVictim;
    }

    public void setSceneVictim(List<SCENEVICTIMBean> sceneVictim) {
        SceneVictim = sceneVictim;
    }

    public List<SCENEPICTUREBean> getScenePicture() {
        return ScenePicture;
    }

    public void setScenePicture(List<SCENEPICTUREBean> scenePicture) {
        ScenePicture = scenePicture;
    }

    public List<COMMONPICTUREBean> getCommonPicture() {
        return CommonPicture;
    }

    public void setCommonPicture(List<COMMONPICTUREBean> commonPicture) {
        CommonPicture = commonPicture;
    }

    public List<SCENEHANDPRINTBean> getSceneHandprint() {
        return SceneHandprint;
    }

    public void setSceneHandprint(List<SCENEHANDPRINTBean> sceneHandprint) {
        SceneHandprint = sceneHandprint;
    }

    public List<SCENEFOOTPRINTBean> getSceneFootprint() {
        return SceneFootprint;
    }

    public void setSceneFootprint(List<SCENEFOOTPRINTBean> sceneFootprint) {
        SceneFootprint = sceneFootprint;
    }

    public List<SCENETOOLMARKBean> getSceneToolmark() {
        return SceneToolmark;
    }

    public void setSceneToolmark(List<SCENETOOLMARKBean> sceneToolmark) {
        SceneToolmark = sceneToolmark;
    }

    public List<SCENEOTHEREVIDENCEBean> getSceneOtherEvidence() {
        return SceneOtherEvidence;
    }

    public void setSceneOtherEvidence(List<SCENEOTHEREVIDENCEBean> sceneOtherEvidence) {
        SceneOtherEvidence = sceneOtherEvidence;
    }

    public List<SCENEVIDEOEVIDENCEBean> getSceneVideoEvidence() {
        return SceneVideoEvidence;
    }

    public void setSceneVideoEvidence(List<SCENEVIDEOEVIDENCEBean> sceneVideoEvidence) {
        SceneVideoEvidence = sceneVideoEvidence;
    }

    public KCTCASEINFOBean getKctCaseInfo() {
        return KctCaseInfo;
    }

    public void setKctCaseInfo(KCTCASEINFOBean kctCaseInfo) {
        KctCaseInfo = kctCaseInfo;
    }

    public List<KCTLOCALEDATABean> getKctLocaleData() {
        return KctLocaleData;
    }

    public void setKctLocaleData(List<KCTLOCALEDATABean> kctLocaleData) {
        KctLocaleData = kctLocaleData;
    }

    public List<KCTBASESTATIONDATABean> getKctBasestationData() {
        return KctBasestationData;
    }

    public void setKctBasestationData(List<KCTBASESTATIONDATABean> kctBasestationData) {
        KctBasestationData = kctBasestationData;
    }

    public List<COMMONATTACHMENTBean> getCommonAttachment() {
        return CommonAttachment;
    }

    public void setCommonAttachment(List<COMMONATTACHMENTBean> commonAttachment) {
        CommonAttachment = commonAttachment;
    }

    public List<SCENEPHOTOBean> getScenePhoto() {
        return ScenePhoto;
    }

    public void setScenePhoto(List<SCENEPHOTOBean> scenePhoto) {
        ScenePhoto = scenePhoto;
    }

    public static class SCENEINVESTIGATIONBean {
        /**
         * ID : 394be5a5fee343a49481dd1a64f5eceb
         * INVESTIGATION_NO : []
         * RECEPTION_ID : c575046d326c4feca61d2f33f6ac29ef
         * CASE_ID : b66c45d471854af1a8a66870cf057c63
         * ITERATION_NO : 0
         * INVESTIGATION_DATE_FROM : 20190318173433
         * INVESTIGATION_DATE_TO : 20190318183433
         * INVESTIGATION_PLACE : 谢谢
         * ENV_TEMPERATURE : 20
         * ENV_MOISTNESS : 35
         * WIND : 09
         * WEATHER : 2
         * OTHER_WEATHER : []
         * SCENE_CONDITION : 1
         * LIGHTING : 1
         * PROTECTION_DATE : 20190319113601
         * PROTECTION_MEASURE : 专人看护现场，防止他人进入
         * PROTECTOR : 重庆市刑科所&许洋&民警
         * INVEST_NOTE_ID : e6d7cfe7790140fa8bd33a5eec964131
         * UPDATED_INVEST_NOTE_ID : []
         * SCENE_LOSS : []
         * OVERTURN_DEGREE : []
         * WOUNDED_AMOUNT : 0
         * DEAD_AMOUNT : 0
         * LOST_TOTAL_VALUE : 888
         * REMNANT_DESC : []
         * SCENE_DISPOSAL : []
         * DIRECTOR : 许洋
         * INVESTIGATOR : 许洋,测试
         * DIRECTOR_IDS : 2f869bcd60b646ab0160b649f223000b
         * INVESTIGATOR_IDS : 2f869bcd60b646ab0160b649f223000b,2f869bcd60b646ab0160b64a63db000c
         * INVESTIGATOR_DUTIES : []
         * WITNESS : 这种(男,0,,)
         * HANDPRINT_FLAG : []
         * FOOTPRINT_FLAG : []
         * TOOLMARK_FLAG : []
         * BULLETPRINT_FLAG : []
         * SPECIALPRINT_FLAG : []
         * BIO_EVIDENCE_FLAG : []
         * TOXIC_EVIDENCE_FLAG : []
         * PHYSICAL_EVIDENCE_FLAG : []
         * FILE_EVIDENCE_FLAG : []
         * ELECTRO_EVIDENCE_FLAG : []
         * VIDEO_EVIDENCE_FLAG : []
         * OTHER_EVIDENCE_FLAG : []
         * HANDPRINT_AMOUNT : 1
         * FOOTPRINT_AMOUNT : 0
         * TOOLMARK_AMOUNT : 0
         * BULLETPRINT_AMOUNT : 0
         * SPECIALPRINT_AMOUNT : 0
         * BIO_EVIDENCE_AMOUNT : 0
         * TOXIC_EVIDENCE_AMOUNT : 0
         * PHYSICAL_EVIDENCE_AMOUNT : 0
         * FILE_EVIDENCE_AMOUNT : 0
         * ELECTRO_EVIDENCE_AMOUNT : 0
         * VIDEO_EVIDENCE_AMOUNT : 0
         * OTHER_EVIDENCE_AMOUNT : 0
         * SCENE_PHOTO_AMOUNT : 5
         * UNUSED_SCENE_PHOTO_AMOUNT : 0
         * SCENE_PICTURE_AMOUNT : 2
         * SAVE_FLAG : 0
         * FINISH_FLAG : 0
         * UNFINISH_REASON : []
         * QUALIFIED_FLAG : 0
         * UNQUALIFIED_REASON : []
         * NOTE_MADE_FLAG : []
         * NOTE_MADE_DATE : []
         * WRITER : []
         * DRAFTER : []
         * CAMERIST : []
         * VIDEOER : []
         * RECORDIST : []
         * VIDEO_TIME : []
         * RECORD_TIME : []
         * USER_ID : 2f869bcd610c539301610c6d8f4b0002
         * INIT_SERVER_NO : 500000000000
         * MODIFY_FLAG : 1
         * MODIFY_REASON : []
         * DELETE_REASON : []
         * MAIN_ORGAN_ID : 12345678901234567890123456789a01
         * MAIN_ORGAN_NAME : 重庆市刑科所
         * HELP_ORGAN_ID : []
         * HELP_ORGAN_NAME : []
         * TRANSFER_DATE : 20190319113601
         * TRANSFER_FLAG : []
         * SECRECY : 1
         * DELETE_FLAG : 0
         * CREATE_USER : 许洋
         * CREATE_DATETIME : 20190319113601
         * UPDATE_USER : []
         * UPDATE_DATETIME : 20190319113601
         * RESERVER1 : []
         * RESERVER2 : []
         * RESERVER3 : 1
         * RESERVER4 : []
         * RESERVER5 : []
         * RESERVER6 : []
         * RESERVER7 : []
         * RESERVER8 : []
         * OTHER_PERSONS : []
         * SUBMIT_DATETIME : []
         * SBBID : []
         * ENTRY_VERSION : standard
         * BDYY : []
         * BDYY_DESC : []
         */

        private String ID;
        private String RECEPTION_ID;//接出警ID
        private String CASE_ID;//案件ID
        private int ITERATION_NO;//现场复勘号
        private String INVESTIGATION_DATE_FROM;//勘验开始时间
        private String INVESTIGATION_DATE_TO;//勘验结束时间
        private String INVESTIGATION_PLACE;//现场地点
        private String ENV_TEMPERATURE;//现场温度
        private String ENV_MOISTNESS;//现场相对湿度
        private String WIND;//现场风向
        private String WEATHER;//现场天气
        private String SCENE_CONDITION;//现场条件
        private String LIGHTING;//现场光照条件
        private String PROTECTION_DATE;//现场保护时间
        private String PROTECTION_MEASURE;//现场保护措施
        private String PROTECTOR;//现场保护人
        private String INVEST_NOTE_ID;//勘验检查情况
        private int WOUNDED_AMOUNT;//受伤数量
        private int DEAD_AMOUNT;//死亡数量
        private int LOST_TOTAL_VALUE;//损失物品总价值
        private String DIRECTOR;//现场指挥人员
        private String INVESTIGATOR;//勘验检查人员
        private String DIRECTOR_IDS;//现场指挥人员ID
        private String INVESTIGATOR_IDS;//勘验检查人员ID
        private String WITNESS;//见证人
        private int HANDPRINT_AMOUNT;//提取手印痕迹数量
        private int FOOTPRINT_AMOUNT;//提取足迹痕迹数量
        private int TOOLMARK_AMOUNT;//提取工具痕迹数量
        private int BULLETPRINT_AMOUNT;//提取枪弹痕迹数量
        private int SPECIALPRINT_AMOUNT;//提取特殊痕迹数量
        private int BIO_EVIDENCE_AMOUNT;//提取生物物证数量
        private int TOXIC_EVIDENCE_AMOUNT;//提取毒化物证数量
        private int PHYSICAL_EVIDENCE_AMOUNT;//提取理化物证数量
        private int FILE_EVIDENCE_AMOUNT;//提取文检物证数量
        private int ELECTRO_EVIDENCE_AMOUNT;//提取电子物证数量
        private int VIDEO_EVIDENCE_AMOUNT;//提取视听物证数量
        private int OTHER_EVIDENCE_AMOUNT;//提取其他物证数量
        private int SCENE_PHOTO_AMOUNT;//现场照片总数
        private int UNUSED_SCENE_PHOTO_AMOUNT;//现场照片未利用数
        private int SCENE_PICTURE_AMOUNT;//现场图总数
        private String SAVE_FLAG;//保存标记
        private String FINISH_FLAG;//完整标记
        private String QUALIFIED_FLAG;//合格标记
        private String USER_ID;//操作用户ID
        private String INIT_SERVER_NO;//原始服务器编号
        private String MODIFY_FLAG;//可修改标志
        private String MAIN_ORGAN_ID;//主勘单位ID
        private String MAIN_ORGAN_NAME;//主勘单位名称
        private String TRANSFER_DATE;//上报时间
        private String SECRECY;//密级
        private String DELETE_FLAG;//删除标志
        private String CREATE_USER;//记录创建人
        private String CREATE_DATETIME;//记录创建时间
        private String UPDATE_DATETIME;//记录更新时间
        private String RESERVER3;
        private String ENTRY_VERSION;
        private String INVESTIGATION_NO;//现场勘查号
        private String OTHER_WEATHER;//现场天气其他值
        private String UPDATED_INVEST_NOTE_ID;//笔录附件ID
        private String SCENE_LOSS;//现场损失情况
        private String OVERTURN_DEGREE;//现场物品翻动程度
        private String REMNANT_DESC;//现场遗留物
        private String SCENE_DISPOSAL;//现场处置意见
        private String INVESTIGATOR_DUTIES;//勘验人职责信息
        private String HANDPRINT_FLAG;//手印痕迹存在标志
        private String FOOTPRINT_FLAG;//足迹痕迹存在标志
        private String TOOLMARK_FLAG;//工具痕迹存在标志
        private String BULLETPRINT_FLAG;//枪弹痕迹存在标志
        private String SPECIALPRINT_FLAG;//特殊痕迹存在标志
        private String BIO_EVIDENCE_FLAG;//生物物证存在标志
        private String TOXIC_EVIDENCE_FLAG;//毒化物证存在标志
        private String PHYSICAL_EVIDENCE_FLAG;//理化物证存在标志
        private String FILE_EVIDENCE_FLAG;//文检物证存在标志
        private String ELECTRO_EVIDENCE_FLAG;//电子物证存在标志
        private String VIDEO_EVIDENCE_FLAG;//视听物证存在标志
        private String OTHER_EVIDENCE_FLAG;//其他物证存在标志
        private String UNFINISH_REASON;//不完整原因
        private String UNQUALIFIED_REASON;//不合格原因
        private String NOTE_MADE_FLAG;//是否已制作笔录
        private String NOTE_MADE_DATE;//制作笔录时间
        private String WRITER;//笔录人
        private String DRAFTER;//制图人
        private String CAMERIST;//照相人
        private String VIDEOER;//录像人
        private String RECORDIST;//录音人
        private String VIDEO_TIME;//录像时间
        private String RECORD_TIME;//录音时间
        private String MODIFY_REASON;//修改原因
        private String DELETE_REASON;//删除原因
        private String HELP_ORGAN_ID;//参与单位ID
        private String HELP_ORGAN_NAME;//参与单位名称
        private String TRANSFER_FLAG;//上报标志
        private String UPDATE_USER;//记录更新人
        private String RESERVER1;
        private String RESERVER2;
        private String RESERVER4;
        private String RESERVER5;
        private String RESERVER6;
        private String RESERVER7;
        private String RESERVER8;
        private String OTHER_PERSONS;//其他到达现场人员
        private String SUBMIT_DATETIME;//提交时间
        private String SBBID;//三版本标记
        private String BDYY;
        private String BDYY_DESC;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getRECEPTION_ID() {
            return RECEPTION_ID;
        }

        public void setRECEPTION_ID(String RECEPTION_ID) {
            this.RECEPTION_ID = RECEPTION_ID;
        }

        public String getCASE_ID() {
            return CASE_ID;
        }

        public void setCASE_ID(String CASE_ID) {
            this.CASE_ID = CASE_ID;
        }

        public int getITERATION_NO() {
            return ITERATION_NO;
        }

        public void setITERATION_NO(int ITERATION_NO) {
            this.ITERATION_NO = ITERATION_NO;
        }

        public String getINVESTIGATION_DATE_FROM() {
            return INVESTIGATION_DATE_FROM;
        }

        public void setINVESTIGATION_DATE_FROM(String INVESTIGATION_DATE_FROM) {
            this.INVESTIGATION_DATE_FROM = INVESTIGATION_DATE_FROM;
        }

        public String getINVESTIGATION_DATE_TO() {
            return INVESTIGATION_DATE_TO;
        }

        public void setINVESTIGATION_DATE_TO(String INVESTIGATION_DATE_TO) {
            this.INVESTIGATION_DATE_TO = INVESTIGATION_DATE_TO;
        }

        public String getINVESTIGATION_PLACE() {
            return INVESTIGATION_PLACE;
        }

        public void setINVESTIGATION_PLACE(String INVESTIGATION_PLACE) {
            this.INVESTIGATION_PLACE = INVESTIGATION_PLACE;
        }

        public String getENV_TEMPERATURE() {
            return ENV_TEMPERATURE;
        }

        public void setENV_TEMPERATURE(String ENV_TEMPERATURE) {
            this.ENV_TEMPERATURE = ENV_TEMPERATURE;
        }

        public String getENV_MOISTNESS() {
            return ENV_MOISTNESS;
        }

        public void setENV_MOISTNESS(String ENV_MOISTNESS) {
            this.ENV_MOISTNESS = ENV_MOISTNESS;
        }

        public String getWIND() {
            return WIND;
        }

        public void setWIND(String WIND) {
            this.WIND = WIND;
        }

        public String getWEATHER() {
            return WEATHER;
        }

        public void setWEATHER(String WEATHER) {
            this.WEATHER = WEATHER;
        }

        public String getSCENE_CONDITION() {
            return SCENE_CONDITION;
        }

        public void setSCENE_CONDITION(String SCENE_CONDITION) {
            this.SCENE_CONDITION = SCENE_CONDITION;
        }

        public String getLIGHTING() {
            return LIGHTING;
        }

        public void setLIGHTING(String LIGHTING) {
            this.LIGHTING = LIGHTING;
        }

        public String getPROTECTION_DATE() {
            return PROTECTION_DATE;
        }

        public void setPROTECTION_DATE(String PROTECTION_DATE) {
            this.PROTECTION_DATE = PROTECTION_DATE;
        }

        public String getPROTECTION_MEASURE() {
            return PROTECTION_MEASURE;
        }

        public void setPROTECTION_MEASURE(String PROTECTION_MEASURE) {
            this.PROTECTION_MEASURE = PROTECTION_MEASURE;
        }

        public String getPROTECTOR() {
            return PROTECTOR;
        }

        public void setPROTECTOR(String PROTECTOR) {
            this.PROTECTOR = PROTECTOR;
        }

        public String getINVEST_NOTE_ID() {
            return INVEST_NOTE_ID;
        }

        public void setINVEST_NOTE_ID(String INVEST_NOTE_ID) {
            this.INVEST_NOTE_ID = INVEST_NOTE_ID;
        }

        public int getWOUNDED_AMOUNT() {
            return WOUNDED_AMOUNT;
        }

        public void setWOUNDED_AMOUNT(int WOUNDED_AMOUNT) {
            this.WOUNDED_AMOUNT = WOUNDED_AMOUNT;
        }

        public int getDEAD_AMOUNT() {
            return DEAD_AMOUNT;
        }

        public void setDEAD_AMOUNT(int DEAD_AMOUNT) {
            this.DEAD_AMOUNT = DEAD_AMOUNT;
        }

        public int getLOST_TOTAL_VALUE() {
            return LOST_TOTAL_VALUE;
        }

        public void setLOST_TOTAL_VALUE(int LOST_TOTAL_VALUE) {
            this.LOST_TOTAL_VALUE = LOST_TOTAL_VALUE;
        }

        public String getDIRECTOR() {
            return DIRECTOR;
        }

        public void setDIRECTOR(String DIRECTOR) {
            this.DIRECTOR = DIRECTOR;
        }

        public String getINVESTIGATOR() {
            return INVESTIGATOR;
        }

        public void setINVESTIGATOR(String INVESTIGATOR) {
            this.INVESTIGATOR = INVESTIGATOR;
        }

        public String getDIRECTOR_IDS() {
            return DIRECTOR_IDS;
        }

        public void setDIRECTOR_IDS(String DIRECTOR_IDS) {
            this.DIRECTOR_IDS = DIRECTOR_IDS;
        }

        public String getINVESTIGATOR_IDS() {
            return INVESTIGATOR_IDS;
        }

        public void setINVESTIGATOR_IDS(String INVESTIGATOR_IDS) {
            this.INVESTIGATOR_IDS = INVESTIGATOR_IDS;
        }

        public String getWITNESS() {
            return WITNESS;
        }

        public void setWITNESS(String WITNESS) {
            this.WITNESS = WITNESS;
        }

        public int getHANDPRINT_AMOUNT() {
            return HANDPRINT_AMOUNT;
        }

        public void setHANDPRINT_AMOUNT(int HANDPRINT_AMOUNT) {
            this.HANDPRINT_AMOUNT = HANDPRINT_AMOUNT;
        }

        public int getFOOTPRINT_AMOUNT() {
            return FOOTPRINT_AMOUNT;
        }

        public void setFOOTPRINT_AMOUNT(int FOOTPRINT_AMOUNT) {
            this.FOOTPRINT_AMOUNT = FOOTPRINT_AMOUNT;
        }

        public int getTOOLMARK_AMOUNT() {
            return TOOLMARK_AMOUNT;
        }

        public void setTOOLMARK_AMOUNT(int TOOLMARK_AMOUNT) {
            this.TOOLMARK_AMOUNT = TOOLMARK_AMOUNT;
        }

        public int getBULLETPRINT_AMOUNT() {
            return BULLETPRINT_AMOUNT;
        }

        public void setBULLETPRINT_AMOUNT(int BULLETPRINT_AMOUNT) {
            this.BULLETPRINT_AMOUNT = BULLETPRINT_AMOUNT;
        }

        public int getSPECIALPRINT_AMOUNT() {
            return SPECIALPRINT_AMOUNT;
        }

        public void setSPECIALPRINT_AMOUNT(int SPECIALPRINT_AMOUNT) {
            this.SPECIALPRINT_AMOUNT = SPECIALPRINT_AMOUNT;
        }

        public int getBIO_EVIDENCE_AMOUNT() {
            return BIO_EVIDENCE_AMOUNT;
        }

        public void setBIO_EVIDENCE_AMOUNT(int BIO_EVIDENCE_AMOUNT) {
            this.BIO_EVIDENCE_AMOUNT = BIO_EVIDENCE_AMOUNT;
        }

        public int getTOXIC_EVIDENCE_AMOUNT() {
            return TOXIC_EVIDENCE_AMOUNT;
        }

        public void setTOXIC_EVIDENCE_AMOUNT(int TOXIC_EVIDENCE_AMOUNT) {
            this.TOXIC_EVIDENCE_AMOUNT = TOXIC_EVIDENCE_AMOUNT;
        }

        public int getPHYSICAL_EVIDENCE_AMOUNT() {
            return PHYSICAL_EVIDENCE_AMOUNT;
        }

        public void setPHYSICAL_EVIDENCE_AMOUNT(int PHYSICAL_EVIDENCE_AMOUNT) {
            this.PHYSICAL_EVIDENCE_AMOUNT = PHYSICAL_EVIDENCE_AMOUNT;
        }

        public int getFILE_EVIDENCE_AMOUNT() {
            return FILE_EVIDENCE_AMOUNT;
        }

        public void setFILE_EVIDENCE_AMOUNT(int FILE_EVIDENCE_AMOUNT) {
            this.FILE_EVIDENCE_AMOUNT = FILE_EVIDENCE_AMOUNT;
        }

        public int getELECTRO_EVIDENCE_AMOUNT() {
            return ELECTRO_EVIDENCE_AMOUNT;
        }

        public void setELECTRO_EVIDENCE_AMOUNT(int ELECTRO_EVIDENCE_AMOUNT) {
            this.ELECTRO_EVIDENCE_AMOUNT = ELECTRO_EVIDENCE_AMOUNT;
        }

        public int getVIDEO_EVIDENCE_AMOUNT() {
            return VIDEO_EVIDENCE_AMOUNT;
        }

        public void setVIDEO_EVIDENCE_AMOUNT(int VIDEO_EVIDENCE_AMOUNT) {
            this.VIDEO_EVIDENCE_AMOUNT = VIDEO_EVIDENCE_AMOUNT;
        }

        public int getOTHER_EVIDENCE_AMOUNT() {
            return OTHER_EVIDENCE_AMOUNT;
        }

        public void setOTHER_EVIDENCE_AMOUNT(int OTHER_EVIDENCE_AMOUNT) {
            this.OTHER_EVIDENCE_AMOUNT = OTHER_EVIDENCE_AMOUNT;
        }

        public int getSCENE_PHOTO_AMOUNT() {
            return SCENE_PHOTO_AMOUNT;
        }

        public void setSCENE_PHOTO_AMOUNT(int SCENE_PHOTO_AMOUNT) {
            this.SCENE_PHOTO_AMOUNT = SCENE_PHOTO_AMOUNT;
        }

        public int getUNUSED_SCENE_PHOTO_AMOUNT() {
            return UNUSED_SCENE_PHOTO_AMOUNT;
        }

        public void setUNUSED_SCENE_PHOTO_AMOUNT(int UNUSED_SCENE_PHOTO_AMOUNT) {
            this.UNUSED_SCENE_PHOTO_AMOUNT = UNUSED_SCENE_PHOTO_AMOUNT;
        }

        public int getSCENE_PICTURE_AMOUNT() {
            return SCENE_PICTURE_AMOUNT;
        }

        public void setSCENE_PICTURE_AMOUNT(int SCENE_PICTURE_AMOUNT) {
            this.SCENE_PICTURE_AMOUNT = SCENE_PICTURE_AMOUNT;
        }

        public String getSAVE_FLAG() {
            return SAVE_FLAG;
        }

        public void setSAVE_FLAG(String SAVE_FLAG) {
            this.SAVE_FLAG = SAVE_FLAG;
        }

        public String getFINISH_FLAG() {
            return FINISH_FLAG;
        }

        public void setFINISH_FLAG(String FINISH_FLAG) {
            this.FINISH_FLAG = FINISH_FLAG;
        }

        public String getQUALIFIED_FLAG() {
            return QUALIFIED_FLAG;
        }

        public void setQUALIFIED_FLAG(String QUALIFIED_FLAG) {
            this.QUALIFIED_FLAG = QUALIFIED_FLAG;
        }

        public String getUSER_ID() {
            return USER_ID;
        }

        public void setUSER_ID(String USER_ID) {
            this.USER_ID = USER_ID;
        }

        public String getINIT_SERVER_NO() {
            return INIT_SERVER_NO;
        }

        public void setINIT_SERVER_NO(String INIT_SERVER_NO) {
            this.INIT_SERVER_NO = INIT_SERVER_NO;
        }

        public String getMODIFY_FLAG() {
            return MODIFY_FLAG;
        }

        public void setMODIFY_FLAG(String MODIFY_FLAG) {
            this.MODIFY_FLAG = MODIFY_FLAG;
        }

        public String getMAIN_ORGAN_ID() {
            return MAIN_ORGAN_ID;
        }

        public void setMAIN_ORGAN_ID(String MAIN_ORGAN_ID) {
            this.MAIN_ORGAN_ID = MAIN_ORGAN_ID;
        }

        public String getMAIN_ORGAN_NAME() {
            return MAIN_ORGAN_NAME;
        }

        public void setMAIN_ORGAN_NAME(String MAIN_ORGAN_NAME) {
            this.MAIN_ORGAN_NAME = MAIN_ORGAN_NAME;
        }

        public String getTRANSFER_DATE() {
            return TRANSFER_DATE;
        }

        public void setTRANSFER_DATE(String TRANSFER_DATE) {
            this.TRANSFER_DATE = TRANSFER_DATE;
        }

        public String getSECRECY() {
            return SECRECY;
        }

        public void setSECRECY(String SECRECY) {
            this.SECRECY = SECRECY;
        }

        public String getDELETE_FLAG() {
            return DELETE_FLAG;
        }

        public void setDELETE_FLAG(String DELETE_FLAG) {
            this.DELETE_FLAG = DELETE_FLAG;
        }

        public String getCREATE_USER() {
            return CREATE_USER;
        }

        public void setCREATE_USER(String CREATE_USER) {
            this.CREATE_USER = CREATE_USER;
        }

        public String getCREATE_DATETIME() {
            return CREATE_DATETIME;
        }

        public void setCREATE_DATETIME(String CREATE_DATETIME) {
            this.CREATE_DATETIME = CREATE_DATETIME;
        }

        public String getUPDATE_DATETIME() {
            return UPDATE_DATETIME;
        }

        public void setUPDATE_DATETIME(String UPDATE_DATETIME) {
            this.UPDATE_DATETIME = UPDATE_DATETIME;
        }

        public String getRESERVER3() {
            return RESERVER3;
        }

        public void setRESERVER3(String RESERVER3) {
            this.RESERVER3 = RESERVER3;
        }

        public String getENTRY_VERSION() {
            return ENTRY_VERSION;
        }

        public void setENTRY_VERSION(String ENTRY_VERSION) {
            this.ENTRY_VERSION = ENTRY_VERSION;
        }

        public String getINVESTIGATION_NO() {
            return INVESTIGATION_NO;
        }

        public void setINVESTIGATION_NO(String INVESTIGATION_NO) {
            this.INVESTIGATION_NO = INVESTIGATION_NO;
        }

        public String getOTHER_WEATHER() {
            return OTHER_WEATHER;
        }

        public void setOTHER_WEATHER(String OTHER_WEATHER) {
            this.OTHER_WEATHER = OTHER_WEATHER;
        }

        public String getUPDATED_INVEST_NOTE_ID() {
            return UPDATED_INVEST_NOTE_ID;
        }

        public void setUPDATED_INVEST_NOTE_ID(String UPDATED_INVEST_NOTE_ID) {
            this.UPDATED_INVEST_NOTE_ID = UPDATED_INVEST_NOTE_ID;
        }

        public String getSCENE_LOSS() {
            return SCENE_LOSS;
        }

        public void setSCENE_LOSS(String SCENE_LOSS) {
            this.SCENE_LOSS = SCENE_LOSS;
        }

        public String getOVERTURN_DEGREE() {
            return OVERTURN_DEGREE;
        }

        public void setOVERTURN_DEGREE(String OVERTURN_DEGREE) {
            this.OVERTURN_DEGREE = OVERTURN_DEGREE;
        }

        public String getREMNANT_DESC() {
            return REMNANT_DESC;
        }

        public void setREMNANT_DESC(String REMNANT_DESC) {
            this.REMNANT_DESC = REMNANT_DESC;
        }

        public String getSCENE_DISPOSAL() {
            return SCENE_DISPOSAL;
        }

        public void setSCENE_DISPOSAL(String SCENE_DISPOSAL) {
            this.SCENE_DISPOSAL = SCENE_DISPOSAL;
        }

        public String getINVESTIGATOR_DUTIES() {
            return INVESTIGATOR_DUTIES;
        }

        public void setINVESTIGATOR_DUTIES(String INVESTIGATOR_DUTIES) {
            this.INVESTIGATOR_DUTIES = INVESTIGATOR_DUTIES;
        }

        public String getHANDPRINT_FLAG() {
            return HANDPRINT_FLAG;
        }

        public void setHANDPRINT_FLAG(String HANDPRINT_FLAG) {
            this.HANDPRINT_FLAG = HANDPRINT_FLAG;
        }

        public String getFOOTPRINT_FLAG() {
            return FOOTPRINT_FLAG;
        }

        public void setFOOTPRINT_FLAG(String FOOTPRINT_FLAG) {
            this.FOOTPRINT_FLAG = FOOTPRINT_FLAG;
        }

        public String getTOOLMARK_FLAG() {
            return TOOLMARK_FLAG;
        }

        public void setTOOLMARK_FLAG(String TOOLMARK_FLAG) {
            this.TOOLMARK_FLAG = TOOLMARK_FLAG;
        }

        public String getBULLETPRINT_FLAG() {
            return BULLETPRINT_FLAG;
        }

        public void setBULLETPRINT_FLAG(String BULLETPRINT_FLAG) {
            this.BULLETPRINT_FLAG = BULLETPRINT_FLAG;
        }

        public String getSPECIALPRINT_FLAG() {
            return SPECIALPRINT_FLAG;
        }

        public void setSPECIALPRINT_FLAG(String SPECIALPRINT_FLAG) {
            this.SPECIALPRINT_FLAG = SPECIALPRINT_FLAG;
        }

        public String getBIO_EVIDENCE_FLAG() {
            return BIO_EVIDENCE_FLAG;
        }

        public void setBIO_EVIDENCE_FLAG(String BIO_EVIDENCE_FLAG) {
            this.BIO_EVIDENCE_FLAG = BIO_EVIDENCE_FLAG;
        }

        public String getTOXIC_EVIDENCE_FLAG() {
            return TOXIC_EVIDENCE_FLAG;
        }

        public void setTOXIC_EVIDENCE_FLAG(String TOXIC_EVIDENCE_FLAG) {
            this.TOXIC_EVIDENCE_FLAG = TOXIC_EVIDENCE_FLAG;
        }

        public String getPHYSICAL_EVIDENCE_FLAG() {
            return PHYSICAL_EVIDENCE_FLAG;
        }

        public void setPHYSICAL_EVIDENCE_FLAG(String PHYSICAL_EVIDENCE_FLAG) {
            this.PHYSICAL_EVIDENCE_FLAG = PHYSICAL_EVIDENCE_FLAG;
        }

        public String getFILE_EVIDENCE_FLAG() {
            return FILE_EVIDENCE_FLAG;
        }

        public void setFILE_EVIDENCE_FLAG(String FILE_EVIDENCE_FLAG) {
            this.FILE_EVIDENCE_FLAG = FILE_EVIDENCE_FLAG;
        }

        public String getELECTRO_EVIDENCE_FLAG() {
            return ELECTRO_EVIDENCE_FLAG;
        }

        public void setELECTRO_EVIDENCE_FLAG(String ELECTRO_EVIDENCE_FLAG) {
            this.ELECTRO_EVIDENCE_FLAG = ELECTRO_EVIDENCE_FLAG;
        }

        public String getVIDEO_EVIDENCE_FLAG() {
            return VIDEO_EVIDENCE_FLAG;
        }

        public void setVIDEO_EVIDENCE_FLAG(String VIDEO_EVIDENCE_FLAG) {
            this.VIDEO_EVIDENCE_FLAG = VIDEO_EVIDENCE_FLAG;
        }

        public String getOTHER_EVIDENCE_FLAG() {
            return OTHER_EVIDENCE_FLAG;
        }

        public void setOTHER_EVIDENCE_FLAG(String OTHER_EVIDENCE_FLAG) {
            this.OTHER_EVIDENCE_FLAG = OTHER_EVIDENCE_FLAG;
        }

        public String getUNFINISH_REASON() {
            return UNFINISH_REASON;
        }

        public void setUNFINISH_REASON(String UNFINISH_REASON) {
            this.UNFINISH_REASON = UNFINISH_REASON;
        }

        public String getUNQUALIFIED_REASON() {
            return UNQUALIFIED_REASON;
        }

        public void setUNQUALIFIED_REASON(String UNQUALIFIED_REASON) {
            this.UNQUALIFIED_REASON = UNQUALIFIED_REASON;
        }

        public String getNOTE_MADE_FLAG() {
            return NOTE_MADE_FLAG;
        }

        public void setNOTE_MADE_FLAG(String NOTE_MADE_FLAG) {
            this.NOTE_MADE_FLAG = NOTE_MADE_FLAG;
        }

        public String getNOTE_MADE_DATE() {
            return NOTE_MADE_DATE;
        }

        public void setNOTE_MADE_DATE(String NOTE_MADE_DATE) {
            this.NOTE_MADE_DATE = NOTE_MADE_DATE;
        }

        public String getWRITER() {
            return WRITER;
        }

        public void setWRITER(String WRITER) {
            this.WRITER = WRITER;
        }

        public String getDRAFTER() {
            return DRAFTER;
        }

        public void setDRAFTER(String DRAFTER) {
            this.DRAFTER = DRAFTER;
        }

        public String getCAMERIST() {
            return CAMERIST;
        }

        public void setCAMERIST(String CAMERIST) {
            this.CAMERIST = CAMERIST;
        }

        public String getVIDEOER() {
            return VIDEOER;
        }

        public void setVIDEOER(String VIDEOER) {
            this.VIDEOER = VIDEOER;
        }

        public String getRECORDIST() {
            return RECORDIST;
        }

        public void setRECORDIST(String RECORDIST) {
            this.RECORDIST = RECORDIST;
        }

        public String getVIDEO_TIME() {
            return VIDEO_TIME;
        }

        public void setVIDEO_TIME(String VIDEO_TIME) {
            this.VIDEO_TIME = VIDEO_TIME;
        }

        public String getRECORD_TIME() {
            return RECORD_TIME;
        }

        public void setRECORD_TIME(String RECORD_TIME) {
            this.RECORD_TIME = RECORD_TIME;
        }

        public String getMODIFY_REASON() {
            return MODIFY_REASON;
        }

        public void setMODIFY_REASON(String MODIFY_REASON) {
            this.MODIFY_REASON = MODIFY_REASON;
        }

        public String getDELETE_REASON() {
            return DELETE_REASON;
        }

        public void setDELETE_REASON(String DELETE_REASON) {
            this.DELETE_REASON = DELETE_REASON;
        }

        public String getHELP_ORGAN_ID() {
            return HELP_ORGAN_ID;
        }

        public void setHELP_ORGAN_ID(String HELP_ORGAN_ID) {
            this.HELP_ORGAN_ID = HELP_ORGAN_ID;
        }

        public String getHELP_ORGAN_NAME() {
            return HELP_ORGAN_NAME;
        }

        public void setHELP_ORGAN_NAME(String HELP_ORGAN_NAME) {
            this.HELP_ORGAN_NAME = HELP_ORGAN_NAME;
        }

        public String getTRANSFER_FLAG() {
            return TRANSFER_FLAG;
        }

        public void setTRANSFER_FLAG(String TRANSFER_FLAG) {
            this.TRANSFER_FLAG = TRANSFER_FLAG;
        }

        public String getUPDATE_USER() {
            return UPDATE_USER;
        }

        public void setUPDATE_USER(String UPDATE_USER) {
            this.UPDATE_USER = UPDATE_USER;
        }

        public String getRESERVER1() {
            return RESERVER1;
        }

        public void setRESERVER1(String RESERVER1) {
            this.RESERVER1 = RESERVER1;
        }

        public String getRESERVER2() {
            return RESERVER2;
        }

        public void setRESERVER2(String RESERVER2) {
            this.RESERVER2 = RESERVER2;
        }

        public String getRESERVER4() {
            return RESERVER4;
        }

        public void setRESERVER4(String RESERVER4) {
            this.RESERVER4 = RESERVER4;
        }

        public String getRESERVER5() {
            return RESERVER5;
        }

        public void setRESERVER5(String RESERVER5) {
            this.RESERVER5 = RESERVER5;
        }

        public String getRESERVER6() {
            return RESERVER6;
        }

        public void setRESERVER6(String RESERVER6) {
            this.RESERVER6 = RESERVER6;
        }

        public String getRESERVER7() {
            return RESERVER7;
        }

        public void setRESERVER7(String RESERVER7) {
            this.RESERVER7 = RESERVER7;
        }

        public String getRESERVER8() {
            return RESERVER8;
        }

        public void setRESERVER8(String RESERVER8) {
            this.RESERVER8 = RESERVER8;
        }

        public String getOTHER_PERSONS() {
            return OTHER_PERSONS;
        }

        public void setOTHER_PERSONS(String OTHER_PERSONS) {
            this.OTHER_PERSONS = OTHER_PERSONS;
        }

        public String getSUBMIT_DATETIME() {
            return SUBMIT_DATETIME;
        }

        public void setSUBMIT_DATETIME(String SUBMIT_DATETIME) {
            this.SUBMIT_DATETIME = SUBMIT_DATETIME;
        }

        public String getSBBID() {
            return SBBID;
        }

        public void setSBBID(String SBBID) {
            this.SBBID = SBBID;
        }

        public String getBDYY() {
            return BDYY;
        }

        public void setBDYY(String BDYY) {
            this.BDYY = BDYY;
        }

        public String getBDYY_DESC() {
            return BDYY_DESC;
        }

        public void setBDYY_DESC(String BDYY_DESC) {
            this.BDYY_DESC = BDYY_DESC;
        }
    }

    public static class SCENELAWCASEBean {
        /**
         * ID : b66c45d471854af1a8a66870cf057c63
         * RECEPTION_NO : []
         * CASE_NO : []
         * CASE_TYPE : 050227
         * CASE_CATEGORY : []
         * CASE_NAME : []
         * CASE_LEVEL : []
         * SCENE_REGIONALISM : 500000000000
         * SCENE_DETAIL : 谢谢
         * OCCURRENCE_DATE_FROM : 20190318153433
         * OCCURRENCE_DATE_TO : 20190318163433
         * EXPOSURE_PROCESS : 据<被害人/报案人>报称:2019年03月18日15时34分，在谢谢，该处发现一起盗窃自行车，后拨打电话报警。
         * VICTIM_INFO : []
         * INIT_SERVER_NO : []
         * TRANSFER_DATE : 20190319113601
         * REMARK : []
         * CRACKED_DATE : []
         * SECRECY : 1
         * DELETE_FLAG : 0
         * CREATE_USER : 许洋
         * CREATE_DATETIME : 20190319113601
         * UPDATE_USER : []
         * UPDATE_DATETIME : 20190319113601
         * RESERVER1 : []
         * RESERVER2 : 394be5a5fee343a49481dd1a64f5eceb
         * RESERVER3 : []
         * RESERVER4 : 0.0
         * RESERVER5 : []
         * RESERVER6 : 0.0
         * RESERVER7 : []
         * RESERVER8 : []
         * IFFLAG : []
         */

        private String ID;
        private String CASE_TYPE;
        private String CASE_TYPE_VALUE;//案件类别中文描述
        private String SCENE_REGIONALISM;//发案行政区划
        private String SCENE_DETAIL;//发案地点
        private String OCCURRENCE_DATE_FROM;//发案时间上限
        private String OCCURRENCE_DATE_TO;//发案时间下限
        private String EXPOSURE_PROCESS;//案件发现过程
        private String TRANSFER_DATE;//上报时间
        private String SECRECY;//密级
        private String DELETE_FLAG;//删除标志
        private String CREATE_USER;//记录创建人
        private String CREATE_DATETIME;//记录创建时间
        private String UPDATE_DATETIME;//记录更新时间
        private String RESERVER2;
        private String RESERVER4;
        private String RESERVER6;
        private String RECEPTION_NO;//案（事）件受理号
        private String CASE_NO;//案件编号
        private String CASE_CATEGORY;//案件具体类型
        private String CASE_NAME;//案件名称
        private String CASE_LEVEL;//督办级别
        private String VICTIM_INFO;//被害人/事主信息
        private String INIT_SERVER_NO;//原始服务器编号
        private String REMARK;//备注
        private String CRACKED_DATE;//破案日期
        private String UPDATE_USER;//记录更新人
        private String RESERVER1;
        private String RESERVER3;//是否制作卷宗标识，0/没有 1/有
        private String RESERVER5;
        private String RESERVER7;
        private String RESERVER8;
        private String IFFLAG;//三版本复用标记

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getCASE_TYPE() {
            return CASE_TYPE;
        }

        public void setCASE_TYPE(String CASE_TYPE) {
            this.CASE_TYPE = CASE_TYPE;
        }

        public String getCASE_TYPE_VALUE() {
            return CASE_TYPE_VALUE;
        }

        public void setCASE_TYPE_VALUE(String CASE_TYPE_VALUE) {
            this.CASE_TYPE_VALUE = CASE_TYPE_VALUE;
        }

        public String getSCENE_REGIONALISM() {
            return SCENE_REGIONALISM;
        }

        public void setSCENE_REGIONALISM(String SCENE_REGIONALISM) {
            this.SCENE_REGIONALISM = SCENE_REGIONALISM;
        }

        public String getSCENE_DETAIL() {
            return SCENE_DETAIL;
        }

        public void setSCENE_DETAIL(String SCENE_DETAIL) {
            this.SCENE_DETAIL = SCENE_DETAIL;
        }

        public String getOCCURRENCE_DATE_FROM() {
            return OCCURRENCE_DATE_FROM;
        }

        public void setOCCURRENCE_DATE_FROM(String OCCURRENCE_DATE_FROM) {
            this.OCCURRENCE_DATE_FROM = OCCURRENCE_DATE_FROM;
        }

        public String getOCCURRENCE_DATE_TO() {
            return OCCURRENCE_DATE_TO;
        }

        public void setOCCURRENCE_DATE_TO(String OCCURRENCE_DATE_TO) {
            this.OCCURRENCE_DATE_TO = OCCURRENCE_DATE_TO;
        }

        public String getEXPOSURE_PROCESS() {
            return EXPOSURE_PROCESS;
        }

        public void setEXPOSURE_PROCESS(String EXPOSURE_PROCESS) {
            this.EXPOSURE_PROCESS = EXPOSURE_PROCESS;
        }

        public String getTRANSFER_DATE() {
            return TRANSFER_DATE;
        }

        public void setTRANSFER_DATE(String TRANSFER_DATE) {
            this.TRANSFER_DATE = TRANSFER_DATE;
        }

        public String getSECRECY() {
            return SECRECY;
        }

        public void setSECRECY(String SECRECY) {
            this.SECRECY = SECRECY;
        }

        public String getDELETE_FLAG() {
            return DELETE_FLAG;
        }

        public void setDELETE_FLAG(String DELETE_FLAG) {
            this.DELETE_FLAG = DELETE_FLAG;
        }

        public String getCREATE_USER() {
            return CREATE_USER;
        }

        public void setCREATE_USER(String CREATE_USER) {
            this.CREATE_USER = CREATE_USER;
        }

        public String getCREATE_DATETIME() {
            return CREATE_DATETIME;
        }

        public void setCREATE_DATETIME(String CREATE_DATETIME) {
            this.CREATE_DATETIME = CREATE_DATETIME;
        }

        public String getUPDATE_DATETIME() {
            return UPDATE_DATETIME;
        }

        public void setUPDATE_DATETIME(String UPDATE_DATETIME) {
            this.UPDATE_DATETIME = UPDATE_DATETIME;
        }

        public String getRESERVER2() {
            return RESERVER2;
        }

        public void setRESERVER2(String RESERVER2) {
            this.RESERVER2 = RESERVER2;
        }

        public String getRESERVER4() {
            return RESERVER4;
        }

        public void setRESERVER4(String RESERVER4) {
            this.RESERVER4 = RESERVER4;
        }

        public String getRESERVER6() {
            return RESERVER6;
        }

        public void setRESERVER6(String RESERVER6) {
            this.RESERVER6 = RESERVER6;
        }

        public String getRECEPTION_NO() {
            return RECEPTION_NO;
        }

        public void setRECEPTION_NO(String RECEPTION_NO) {
            this.RECEPTION_NO = RECEPTION_NO;
        }

        public String getCASE_NO() {
            return CASE_NO;
        }

        public void setCASE_NO(String CASE_NO) {
            this.CASE_NO = CASE_NO;
        }

        public String getCASE_CATEGORY() {
            return CASE_CATEGORY;
        }

        public void setCASE_CATEGORY(String CASE_CATEGORY) {
            this.CASE_CATEGORY = CASE_CATEGORY;
        }

        public String getCASE_NAME() {
            return CASE_NAME;
        }

        public void setCASE_NAME(String CASE_NAME) {
            this.CASE_NAME = CASE_NAME;
        }

        public String getCASE_LEVEL() {
            return CASE_LEVEL;
        }

        public void setCASE_LEVEL(String CASE_LEVEL) {
            this.CASE_LEVEL = CASE_LEVEL;
        }

        public String getVICTIM_INFO() {
            return VICTIM_INFO;
        }

        public void setVICTIM_INFO(String VICTIM_INFO) {
            this.VICTIM_INFO = VICTIM_INFO;
        }

        public String getINIT_SERVER_NO() {
            return INIT_SERVER_NO;
        }

        public void setINIT_SERVER_NO(String INIT_SERVER_NO) {
            this.INIT_SERVER_NO = INIT_SERVER_NO;
        }

        public String getREMARK() {
            return REMARK;
        }

        public void setREMARK(String REMARK) {
            this.REMARK = REMARK;
        }

        public String getCRACKED_DATE() {
            return CRACKED_DATE;
        }

        public void setCRACKED_DATE(String CRACKED_DATE) {
            this.CRACKED_DATE = CRACKED_DATE;
        }

        public String getUPDATE_USER() {
            return UPDATE_USER;
        }

        public void setUPDATE_USER(String UPDATE_USER) {
            this.UPDATE_USER = UPDATE_USER;
        }

        public String getRESERVER1() {
            return RESERVER1;
        }

        public void setRESERVER1(String RESERVER1) {
            this.RESERVER1 = RESERVER1;
        }

        public String getRESERVER3() {
            return RESERVER3;
        }

        public void setRESERVER3(String RESERVER3) {
            this.RESERVER3 = RESERVER3;
        }

        public String getRESERVER5() {
            return RESERVER5;
        }

        public void setRESERVER5(String RESERVER5) {
            this.RESERVER5 = RESERVER5;
        }

        public String getRESERVER7() {
            return RESERVER7;
        }

        public void setRESERVER7(String RESERVER7) {
            this.RESERVER7 = RESERVER7;
        }

        public String getRESERVER8() {
            return RESERVER8;
        }

        public void setRESERVER8(String RESERVER8) {
            this.RESERVER8 = RESERVER8;
        }

        public String getIFFLAG() {
            return IFFLAG;
        }

        public void setIFFLAG(String IFFLAG) {
            this.IFFLAG = IFFLAG;
        }
    }

    public static class SCENERECEPTIONDISPATCHBean {
        /**
         * ID : c575046d326c4feca61d2f33f6ac29ef
         * RECEIVED_DATE : 20190318170433
         * RECEIVED_BY : 妹子
         * ASSIGNED_BY : 110指挥中心
         * ASSIGNED_WAY_T : []
         * ASSIGNED_WAY_S : []
         * ASSIGNED_CONTENT : 重庆市刑科所接到110指挥中心的指派: 在该所管界内谢谢发生一起盗窃自行车，请速派人员勘查现场。
         * DISPATCH_DATE : 20190318170433
         * INIT_SERVER_NO : []
         * TRANSFER_DATE : 20190319113601
         * SECRECY : 1
         * DELETE_FLAG : 0
         * CREATE_USER : 许洋
         * CREATE_DATETIME : 20190319113601
         * UPDATE_USER : []
         * UPDATE_DATETIME : 20190319113601
         * RESERVER1 : []
         * RESERVER2 : []
         * RESERVER3 : []
         * RESERVER4 : []
         * RESERVER5 : []
         * RESERVER6 : []
         * RESERVER7 : []
         * RESERVER8 : []
         */

        private String ID;
        private String RECEIVED_DATE;//接警时间
        private String RECEIVED_BY;//接警人
        private String ASSIGNED_BY;//指派单位/人
        private String ASSIGNED_CONTENT;//指派内容
        private String DISPATCH_DATE;//出警时间
        private String TRANSFER_DATE;//上报时间
        private String SECRECY;//密级
        private String DELETE_FLAG;//删除标志
        private String CREATE_USER;//记录创建人
        private String CREATE_DATETIME;//记录创建时间
        private String UPDATE_DATETIME;//记录更新时间
        private String ASSIGNED_WAY_T;//指派方式(文字)
        private String ASSIGNED_WAY_S;//指派方式(选择)
        private String INIT_SERVER_NO;//原始服务器编号
        private String UPDATE_USER;//记录更新人
        private String RESERVER1;
        private String RESERVER2;
        private String RESERVER3;
        private String RESERVER4;
        private String RESERVER5;
        private String RESERVER6;
        private String RESERVER7;
        private String RESERVER8;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getRECEIVED_DATE() {
            return RECEIVED_DATE;
        }

        public void setRECEIVED_DATE(String RECEIVED_DATE) {
            this.RECEIVED_DATE = RECEIVED_DATE;
        }

        public String getRECEIVED_BY() {
            return RECEIVED_BY;
        }

        public void setRECEIVED_BY(String RECEIVED_BY) {
            this.RECEIVED_BY = RECEIVED_BY;
        }

        public String getASSIGNED_BY() {
            return ASSIGNED_BY;
        }

        public void setASSIGNED_BY(String ASSIGNED_BY) {
            this.ASSIGNED_BY = ASSIGNED_BY;
        }

        public String getASSIGNED_CONTENT() {
            return ASSIGNED_CONTENT;
        }

        public void setASSIGNED_CONTENT(String ASSIGNED_CONTENT) {
            this.ASSIGNED_CONTENT = ASSIGNED_CONTENT;
        }

        public String getDISPATCH_DATE() {
            return DISPATCH_DATE;
        }

        public void setDISPATCH_DATE(String DISPATCH_DATE) {
            this.DISPATCH_DATE = DISPATCH_DATE;
        }

        public String getTRANSFER_DATE() {
            return TRANSFER_DATE;
        }

        public void setTRANSFER_DATE(String TRANSFER_DATE) {
            this.TRANSFER_DATE = TRANSFER_DATE;
        }

        public String getSECRECY() {
            return SECRECY;
        }

        public void setSECRECY(String SECRECY) {
            this.SECRECY = SECRECY;
        }

        public String getDELETE_FLAG() {
            return DELETE_FLAG;
        }

        public void setDELETE_FLAG(String DELETE_FLAG) {
            this.DELETE_FLAG = DELETE_FLAG;
        }

        public String getCREATE_USER() {
            return CREATE_USER;
        }

        public void setCREATE_USER(String CREATE_USER) {
            this.CREATE_USER = CREATE_USER;
        }

        public String getCREATE_DATETIME() {
            return CREATE_DATETIME;
        }

        public void setCREATE_DATETIME(String CREATE_DATETIME) {
            this.CREATE_DATETIME = CREATE_DATETIME;
        }

        public String getUPDATE_DATETIME() {
            return UPDATE_DATETIME;
        }

        public void setUPDATE_DATETIME(String UPDATE_DATETIME) {
            this.UPDATE_DATETIME = UPDATE_DATETIME;
        }

        public String getASSIGNED_WAY_T() {
            return ASSIGNED_WAY_T;
        }

        public void setASSIGNED_WAY_T(String ASSIGNED_WAY_T) {
            this.ASSIGNED_WAY_T = ASSIGNED_WAY_T;
        }

        public String getASSIGNED_WAY_S() {
            return ASSIGNED_WAY_S;
        }

        public void setASSIGNED_WAY_S(String ASSIGNED_WAY_S) {
            this.ASSIGNED_WAY_S = ASSIGNED_WAY_S;
        }

        public String getINIT_SERVER_NO() {
            return INIT_SERVER_NO;
        }

        public void setINIT_SERVER_NO(String INIT_SERVER_NO) {
            this.INIT_SERVER_NO = INIT_SERVER_NO;
        }

        public String getUPDATE_USER() {
            return UPDATE_USER;
        }

        public void setUPDATE_USER(String UPDATE_USER) {
            this.UPDATE_USER = UPDATE_USER;
        }

        public String getRESERVER1() {
            return RESERVER1;
        }

        public void setRESERVER1(String RESERVER1) {
            this.RESERVER1 = RESERVER1;
        }

        public String getRESERVER2() {
            return RESERVER2;
        }

        public void setRESERVER2(String RESERVER2) {
            this.RESERVER2 = RESERVER2;
        }

        public String getRESERVER3() {
            return RESERVER3;
        }

        public void setRESERVER3(String RESERVER3) {
            this.RESERVER3 = RESERVER3;
        }

        public String getRESERVER4() {
            return RESERVER4;
        }

        public void setRESERVER4(String RESERVER4) {
            this.RESERVER4 = RESERVER4;
        }

        public String getRESERVER5() {
            return RESERVER5;
        }

        public void setRESERVER5(String RESERVER5) {
            this.RESERVER5 = RESERVER5;
        }

        public String getRESERVER6() {
            return RESERVER6;
        }

        public void setRESERVER6(String RESERVER6) {
            this.RESERVER6 = RESERVER6;
        }

        public String getRESERVER7() {
            return RESERVER7;
        }

        public void setRESERVER7(String RESERVER7) {
            this.RESERVER7 = RESERVER7;
        }

        public String getRESERVER8() {
            return RESERVER8;
        }

        public void setRESERVER8(String RESERVER8) {
            this.RESERVER8 = RESERVER8;
        }
    }

    public static class SCENEANALYSISSUGGESTIONBean {
        /**
         * ID : eab7cccaa2444792866738ae5c31f2b1
         * INVESTIGATION_ID : 394be5a5fee343a49481dd1a64f5eceb
         * METERIALS_RELIED_ON : []
         * INTENTION : []
         * INTENTION_CN : []
         * INTENTION_DESC : []
         * LOCATION : []
         * LOCATION_CN : []
         * LOCATION_DESC : []
         * INVOLVED_ORG_TYPE : []
         * INVOLVED_ORG_CN : []
         * INVOLVED_ORG_DESC : []
         * COMMISSION_PLACE : 谢谢
         * COMMISSION_PERIOD : []
         * COMMISSION_PERIOD_CN : []
         * COMMISSION_PERIOD_DESC : []
         * ENTRANCE_EXIT : []
         * ENTRANCE_EXIT_CN : []
         * ENTRANCE_EXIT_DESC : []
         * INTRUDING_WAY : []
         * INTRUDING_WAY_CN : []
         * INTRUDING_WAY_DESC : []
         * COMMISSION_MEANS : []
         * COMMISSION_MEANS_CN : []
         * COMMISSION_MEANS_DESC : []
         * COMMISSION_POINTS : []
         * COMMISSION_POINTS_CN : []
         * COMMISSION_POINTS_DESC : []
         * MOTIVATION : []
         * MOTIVATION_CN : []
         * MOTIVATION_DESC : []
         * CASE_PROPERTY : []
         * CASE_PROPERTY_CN : []
         * CASE_PROPERTY_DESC : []
         * CRIMINAL_AMOUNT : 2
         * CRIMINAL_AMOUNT_DESC : []
         * COMMISSION_DESC_ID : []
         * CRIMINAL_POINTS_ID : 0577434dba8a43e4b29e5256dd666df4
         * BUNCH_REASON_ID : []
         * SUGGESTION1 : []
         * SUGGESTION2 : []
         * SUGGESTION3 : []
         * SUGGESTION4 : []
         * SUGGESTION5 : []
         * SUGGESTION6 : []
         * ANALYSED_ORG : 许洋
         * ANALYSED_BY : 重庆市刑科所
         * ANALYSED_DATE : 20190319113601
         * INIT_SERVER_NO : []
         * TRANSFER_DATE : []
         * SECRECY : 1
         * DELETE_FLAG : 0
         * CREATE_USER : 重庆市刑科所
         * CREATE_DATETIME : 20190319113601
         * UPDATE_USER : []
         * UPDATE_DATETIME : 20190319113601
         * RESERVER1 : []
         * RESERVER2 : []
         * RESERVER3 : []
         * RESERVER4 : []
         * RESERVER5 : []
         * RESERVER6 : []
         * RESERVER7 : []
         * RESERVER8 : []
         * EXIT_ENTRANCE : []
         * EXIT_ENTRANCE_CN : []
         * EXIT_ENTRANCE_DESC : []
         */

        private String ID;
        private String INVESTIGATION_ID;//现场信息ID
        private String COMMISSION_PLACE;//作案地点
        private String CRIMINAL_AMOUNT;//作案人数
        private String CRIMINAL_POINTS_ID;//作案人特点
        private String ANALYSED_ORG;//现场分析单位
        private String ANALYSED_BY;//现场分析人
        private String ANALYSED_DATE;//现场分析时间
        private String SECRECY;//密级
        private String DELETE_FLAG;//删除标志
        private String CREATE_USER;//记录创建人
        private String CREATE_DATETIME;//记录创建时间
        private String UPDATE_DATETIME;//记录更新时间
        private String METERIALS_RELIED_ON;//现场分析依据的资料
        private String INTENTION;//选择对象标准代码
        private String INTENTION_CN;//选择对象标准中文
        private String INTENTION_DESC;//选择对象手工描述
        private String LOCATION;//选择处所标准代码
        private String LOCATION_CN;//选择处所标准中文
        private String LOCATION_DESC;//选择处所手工描述
        private String INVOLVED_ORG_TYPE;//涉案单位标准代码
        private String INVOLVED_ORG_CN;//涉案单位标准中文
        private String INVOLVED_ORG_DESC;//涉案单位类型手工描述
        private String COMMISSION_PERIOD;//作案时段标准代码
        private String COMMISSION_PERIOD_CN;//作案时段标准中文
        private String COMMISSION_PERIOD_DESC;//作案时段手工描述
        private String ENTRANCE_EXIT;//作案进出口标准代码
        private String ENTRANCE_EXIT_CN;//作案进出口标准中文
        private String ENTRANCE_EXIT_DESC;//作案进出口手工描述
        private String INTRUDING_WAY;//侵入方式标准代码
        private String INTRUDING_WAY_CN;//侵入方式标准中文
        private String INTRUDING_WAY_DESC;//侵入方式手工描述
        private String COMMISSION_MEANS;//作案手段标准代码
        private String COMMISSION_MEANS_CN;//作案手段标准中文
        private String COMMISSION_MEANS_DESC;//作案手段手工描述
        private String COMMISSION_POINTS;//作案特点标准代码
        private String COMMISSION_POINTS_CN;//作案特点标准中文
        private String COMMISSION_POINTS_DESC;//作案特点手工描述
        private String MOTIVATION;//作案动机目的
        private String MOTIVATION_CN;//作案动机目的标准中文
        private String MOTIVATION_DESC;//作案动机目的手工描述
        private String CASE_PROPERTY;//案件性质标准代码
        private String CASE_PROPERTY_CN;//案件性质标准中文
        private String CASE_PROPERTY_DESC;//案件性质手工描述
        private String CRIMINAL_AMOUNT_DESC;//作案人数手工描述
        private String COMMISSION_DESC_ID;//作案过程
        private String BUNCH_REASON_ID;//并案意见与根据
        private String SUGGESTION1;//工作建议之痕迹物证的保管
        private String SUGGESTION2;//工作建议之现场处置意见
        private String SUGGESTION3;//工作建议之侦查方向与范围
        private String SUGGESTION4;//工作建议之技术防范对策
        private String SUGGESTION5;//工作建议之现场尸体处理
        private String SUGGESTION6;//工作建议之其他
        private String INIT_SERVER_NO;//原始服务器编号
        private String TRANSFER_DATE;//上报时间
        private String UPDATE_USER;//记录更新人
        private String RESERVER1;
        private String RESERVER2;
        private String RESERVER3;
        private String RESERVER4;
        private String RESERVER5;
        private String RESERVER6;
        private String RESERVER7;
        private String RESERVER8;
        private String EXIT_ENTRANCE;
        private String EXIT_ENTRANCE_CN;
        private String EXIT_ENTRANCE_DESC;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getINVESTIGATION_ID() {
            return INVESTIGATION_ID;
        }

        public void setINVESTIGATION_ID(String INVESTIGATION_ID) {
            this.INVESTIGATION_ID = INVESTIGATION_ID;
        }

        public String getCOMMISSION_PLACE() {
            return COMMISSION_PLACE;
        }

        public void setCOMMISSION_PLACE(String COMMISSION_PLACE) {
            this.COMMISSION_PLACE = COMMISSION_PLACE;
        }

        public String getCRIMINAL_AMOUNT() {
            return CRIMINAL_AMOUNT;
        }

        public void setCRIMINAL_AMOUNT(String CRIMINAL_AMOUNT) {
            this.CRIMINAL_AMOUNT = CRIMINAL_AMOUNT;
        }

        public String getCRIMINAL_POINTS_ID() {
            return CRIMINAL_POINTS_ID;
        }

        public void setCRIMINAL_POINTS_ID(String CRIMINAL_POINTS_ID) {
            this.CRIMINAL_POINTS_ID = CRIMINAL_POINTS_ID;
        }

        public String getANALYSED_ORG() {
            return ANALYSED_ORG;
        }

        public void setANALYSED_ORG(String ANALYSED_ORG) {
            this.ANALYSED_ORG = ANALYSED_ORG;
        }

        public String getANALYSED_BY() {
            return ANALYSED_BY;
        }

        public void setANALYSED_BY(String ANALYSED_BY) {
            this.ANALYSED_BY = ANALYSED_BY;
        }

        public String getANALYSED_DATE() {
            return ANALYSED_DATE;
        }

        public void setANALYSED_DATE(String ANALYSED_DATE) {
            this.ANALYSED_DATE = ANALYSED_DATE;
        }

        public String getSECRECY() {
            return SECRECY;
        }

        public void setSECRECY(String SECRECY) {
            this.SECRECY = SECRECY;
        }

        public String getDELETE_FLAG() {
            return DELETE_FLAG;
        }

        public void setDELETE_FLAG(String DELETE_FLAG) {
            this.DELETE_FLAG = DELETE_FLAG;
        }

        public String getCREATE_USER() {
            return CREATE_USER;
        }

        public void setCREATE_USER(String CREATE_USER) {
            this.CREATE_USER = CREATE_USER;
        }

        public String getCREATE_DATETIME() {
            return CREATE_DATETIME;
        }

        public void setCREATE_DATETIME(String CREATE_DATETIME) {
            this.CREATE_DATETIME = CREATE_DATETIME;
        }

        public String getUPDATE_DATETIME() {
            return UPDATE_DATETIME;
        }

        public void setUPDATE_DATETIME(String UPDATE_DATETIME) {
            this.UPDATE_DATETIME = UPDATE_DATETIME;
        }

        public String getMETERIALS_RELIED_ON() {
            return METERIALS_RELIED_ON;
        }

        public void setMETERIALS_RELIED_ON(String METERIALS_RELIED_ON) {
            this.METERIALS_RELIED_ON = METERIALS_RELIED_ON;
        }

        public String getINTENTION() {
            return INTENTION;
        }

        public void setINTENTION(String INTENTION) {
            this.INTENTION = INTENTION;
        }

        public String getINTENTION_CN() {
            return INTENTION_CN;
        }

        public void setINTENTION_CN(String INTENTION_CN) {
            this.INTENTION_CN = INTENTION_CN;
        }

        public String getINTENTION_DESC() {
            return INTENTION_DESC;
        }

        public void setINTENTION_DESC(String INTENTION_DESC) {
            this.INTENTION_DESC = INTENTION_DESC;
        }

        public String getLOCATION() {
            return LOCATION;
        }

        public void setLOCATION(String LOCATION) {
            this.LOCATION = LOCATION;
        }

        public String getLOCATION_CN() {
            return LOCATION_CN;
        }

        public void setLOCATION_CN(String LOCATION_CN) {
            this.LOCATION_CN = LOCATION_CN;
        }

        public String getLOCATION_DESC() {
            return LOCATION_DESC;
        }

        public void setLOCATION_DESC(String LOCATION_DESC) {
            this.LOCATION_DESC = LOCATION_DESC;
        }

        public String getINVOLVED_ORG_TYPE() {
            return INVOLVED_ORG_TYPE;
        }

        public void setINVOLVED_ORG_TYPE(String INVOLVED_ORG_TYPE) {
            this.INVOLVED_ORG_TYPE = INVOLVED_ORG_TYPE;
        }

        public String getINVOLVED_ORG_CN() {
            return INVOLVED_ORG_CN;
        }

        public void setINVOLVED_ORG_CN(String INVOLVED_ORG_CN) {
            this.INVOLVED_ORG_CN = INVOLVED_ORG_CN;
        }

        public String getINVOLVED_ORG_DESC() {
            return INVOLVED_ORG_DESC;
        }

        public void setINVOLVED_ORG_DESC(String INVOLVED_ORG_DESC) {
            this.INVOLVED_ORG_DESC = INVOLVED_ORG_DESC;
        }

        public String getCOMMISSION_PERIOD() {
            return COMMISSION_PERIOD;
        }

        public void setCOMMISSION_PERIOD(String COMMISSION_PERIOD) {
            this.COMMISSION_PERIOD = COMMISSION_PERIOD;
        }

        public String getCOMMISSION_PERIOD_CN() {
            return COMMISSION_PERIOD_CN;
        }

        public void setCOMMISSION_PERIOD_CN(String COMMISSION_PERIOD_CN) {
            this.COMMISSION_PERIOD_CN = COMMISSION_PERIOD_CN;
        }

        public String getCOMMISSION_PERIOD_DESC() {
            return COMMISSION_PERIOD_DESC;
        }

        public void setCOMMISSION_PERIOD_DESC(String COMMISSION_PERIOD_DESC) {
            this.COMMISSION_PERIOD_DESC = COMMISSION_PERIOD_DESC;
        }

        public String getENTRANCE_EXIT() {
            return ENTRANCE_EXIT;
        }

        public void setENTRANCE_EXIT(String ENTRANCE_EXIT) {
            this.ENTRANCE_EXIT = ENTRANCE_EXIT;
        }

        public String getENTRANCE_EXIT_CN() {
            return ENTRANCE_EXIT_CN;
        }

        public void setENTRANCE_EXIT_CN(String ENTRANCE_EXIT_CN) {
            this.ENTRANCE_EXIT_CN = ENTRANCE_EXIT_CN;
        }

        public String getENTRANCE_EXIT_DESC() {
            return ENTRANCE_EXIT_DESC;
        }

        public void setENTRANCE_EXIT_DESC(String ENTRANCE_EXIT_DESC) {
            this.ENTRANCE_EXIT_DESC = ENTRANCE_EXIT_DESC;
        }

        public String getINTRUDING_WAY() {
            return INTRUDING_WAY;
        }

        public void setINTRUDING_WAY(String INTRUDING_WAY) {
            this.INTRUDING_WAY = INTRUDING_WAY;
        }

        public String getINTRUDING_WAY_CN() {
            return INTRUDING_WAY_CN;
        }

        public void setINTRUDING_WAY_CN(String INTRUDING_WAY_CN) {
            this.INTRUDING_WAY_CN = INTRUDING_WAY_CN;
        }

        public String getINTRUDING_WAY_DESC() {
            return INTRUDING_WAY_DESC;
        }

        public void setINTRUDING_WAY_DESC(String INTRUDING_WAY_DESC) {
            this.INTRUDING_WAY_DESC = INTRUDING_WAY_DESC;
        }

        public String getCOMMISSION_MEANS() {
            return COMMISSION_MEANS;
        }

        public void setCOMMISSION_MEANS(String COMMISSION_MEANS) {
            this.COMMISSION_MEANS = COMMISSION_MEANS;
        }

        public String getCOMMISSION_MEANS_CN() {
            return COMMISSION_MEANS_CN;
        }

        public void setCOMMISSION_MEANS_CN(String COMMISSION_MEANS_CN) {
            this.COMMISSION_MEANS_CN = COMMISSION_MEANS_CN;
        }

        public String getCOMMISSION_MEANS_DESC() {
            return COMMISSION_MEANS_DESC;
        }

        public void setCOMMISSION_MEANS_DESC(String COMMISSION_MEANS_DESC) {
            this.COMMISSION_MEANS_DESC = COMMISSION_MEANS_DESC;
        }

        public String getCOMMISSION_POINTS() {
            return COMMISSION_POINTS;
        }

        public void setCOMMISSION_POINTS(String COMMISSION_POINTS) {
            this.COMMISSION_POINTS = COMMISSION_POINTS;
        }

        public String getCOMMISSION_POINTS_CN() {
            return COMMISSION_POINTS_CN;
        }

        public void setCOMMISSION_POINTS_CN(String COMMISSION_POINTS_CN) {
            this.COMMISSION_POINTS_CN = COMMISSION_POINTS_CN;
        }

        public String getCOMMISSION_POINTS_DESC() {
            return COMMISSION_POINTS_DESC;
        }

        public void setCOMMISSION_POINTS_DESC(String COMMISSION_POINTS_DESC) {
            this.COMMISSION_POINTS_DESC = COMMISSION_POINTS_DESC;
        }

        public String getMOTIVATION() {
            return MOTIVATION;
        }

        public void setMOTIVATION(String MOTIVATION) {
            this.MOTIVATION = MOTIVATION;
        }

        public String getMOTIVATION_CN() {
            return MOTIVATION_CN;
        }

        public void setMOTIVATION_CN(String MOTIVATION_CN) {
            this.MOTIVATION_CN = MOTIVATION_CN;
        }

        public String getMOTIVATION_DESC() {
            return MOTIVATION_DESC;
        }

        public void setMOTIVATION_DESC(String MOTIVATION_DESC) {
            this.MOTIVATION_DESC = MOTIVATION_DESC;
        }

        public String getCASE_PROPERTY() {
            return CASE_PROPERTY;
        }

        public void setCASE_PROPERTY(String CASE_PROPERTY) {
            this.CASE_PROPERTY = CASE_PROPERTY;
        }

        public String getCASE_PROPERTY_CN() {
            return CASE_PROPERTY_CN;
        }

        public void setCASE_PROPERTY_CN(String CASE_PROPERTY_CN) {
            this.CASE_PROPERTY_CN = CASE_PROPERTY_CN;
        }

        public String getCASE_PROPERTY_DESC() {
            return CASE_PROPERTY_DESC;
        }

        public void setCASE_PROPERTY_DESC(String CASE_PROPERTY_DESC) {
            this.CASE_PROPERTY_DESC = CASE_PROPERTY_DESC;
        }

        public String getCRIMINAL_AMOUNT_DESC() {
            return CRIMINAL_AMOUNT_DESC;
        }

        public void setCRIMINAL_AMOUNT_DESC(String CRIMINAL_AMOUNT_DESC) {
            this.CRIMINAL_AMOUNT_DESC = CRIMINAL_AMOUNT_DESC;
        }

        public String getCOMMISSION_DESC_ID() {
            return COMMISSION_DESC_ID;
        }

        public void setCOMMISSION_DESC_ID(String COMMISSION_DESC_ID) {
            this.COMMISSION_DESC_ID = COMMISSION_DESC_ID;
        }

        public String getBUNCH_REASON_ID() {
            return BUNCH_REASON_ID;
        }

        public void setBUNCH_REASON_ID(String BUNCH_REASON_ID) {
            this.BUNCH_REASON_ID = BUNCH_REASON_ID;
        }

        public String getSUGGESTION1() {
            return SUGGESTION1;
        }

        public void setSUGGESTION1(String SUGGESTION1) {
            this.SUGGESTION1 = SUGGESTION1;
        }

        public String getSUGGESTION2() {
            return SUGGESTION2;
        }

        public void setSUGGESTION2(String SUGGESTION2) {
            this.SUGGESTION2 = SUGGESTION2;
        }

        public String getSUGGESTION3() {
            return SUGGESTION3;
        }

        public void setSUGGESTION3(String SUGGESTION3) {
            this.SUGGESTION3 = SUGGESTION3;
        }

        public String getSUGGESTION4() {
            return SUGGESTION4;
        }

        public void setSUGGESTION4(String SUGGESTION4) {
            this.SUGGESTION4 = SUGGESTION4;
        }

        public String getSUGGESTION5() {
            return SUGGESTION5;
        }

        public void setSUGGESTION5(String SUGGESTION5) {
            this.SUGGESTION5 = SUGGESTION5;
        }

        public String getSUGGESTION6() {
            return SUGGESTION6;
        }

        public void setSUGGESTION6(String SUGGESTION6) {
            this.SUGGESTION6 = SUGGESTION6;
        }

        public String getINIT_SERVER_NO() {
            return INIT_SERVER_NO;
        }

        public void setINIT_SERVER_NO(String INIT_SERVER_NO) {
            this.INIT_SERVER_NO = INIT_SERVER_NO;
        }

        public String getTRANSFER_DATE() {
            return TRANSFER_DATE;
        }

        public void setTRANSFER_DATE(String TRANSFER_DATE) {
            this.TRANSFER_DATE = TRANSFER_DATE;
        }

        public String getUPDATE_USER() {
            return UPDATE_USER;
        }

        public void setUPDATE_USER(String UPDATE_USER) {
            this.UPDATE_USER = UPDATE_USER;
        }

        public String getRESERVER1() {
            return RESERVER1;
        }

        public void setRESERVER1(String RESERVER1) {
            this.RESERVER1 = RESERVER1;
        }

        public String getRESERVER2() {
            return RESERVER2;
        }

        public void setRESERVER2(String RESERVER2) {
            this.RESERVER2 = RESERVER2;
        }

        public String getRESERVER3() {
            return RESERVER3;
        }

        public void setRESERVER3(String RESERVER3) {
            this.RESERVER3 = RESERVER3;
        }

        public String getRESERVER4() {
            return RESERVER4;
        }

        public void setRESERVER4(String RESERVER4) {
            this.RESERVER4 = RESERVER4;
        }

        public String getRESERVER5() {
            return RESERVER5;
        }

        public void setRESERVER5(String RESERVER5) {
            this.RESERVER5 = RESERVER5;
        }

        public String getRESERVER6() {
            return RESERVER6;
        }

        public void setRESERVER6(String RESERVER6) {
            this.RESERVER6 = RESERVER6;
        }

        public String getRESERVER7() {
            return RESERVER7;
        }

        public void setRESERVER7(String RESERVER7) {
            this.RESERVER7 = RESERVER7;
        }

        public String getRESERVER8() {
            return RESERVER8;
        }

        public void setRESERVER8(String RESERVER8) {
            this.RESERVER8 = RESERVER8;
        }

        public String getEXIT_ENTRANCE() {
            return EXIT_ENTRANCE;
        }

        public void setEXIT_ENTRANCE(String EXIT_ENTRANCE) {
            this.EXIT_ENTRANCE = EXIT_ENTRANCE;
        }

        public String getEXIT_ENTRANCE_CN() {
            return EXIT_ENTRANCE_CN;
        }

        public void setEXIT_ENTRANCE_CN(String EXIT_ENTRANCE_CN) {
            this.EXIT_ENTRANCE_CN = EXIT_ENTRANCE_CN;
        }

        public String getEXIT_ENTRANCE_DESC() {
            return EXIT_ENTRANCE_DESC;
        }

        public void setEXIT_ENTRANCE_DESC(String EXIT_ENTRANCE_DESC) {
            this.EXIT_ENTRANCE_DESC = EXIT_ENTRANCE_DESC;
        }
    }

    public static class SCENEANALYSISRESULTBean {
        /**
         * ID : 8fc378419b0a4629840a85ae6219949b
         * INVESTIGATION_ID : 394be5a5fee343a49481dd1a64f5eceb
         * METERIALS_RELIED_ON : []
         * INTENTION_DESC : []
         * LOCATION_DESC : []
         * INVOLVED_ORG_TYPE_DESC : []
         * LOST_GOODS_DESC : (测试，v，5，888，)
         * COMMISSION_PLACE : 谢谢
         * PERIOD_DESC : []
         * ENTRANCE_EXIT_DESC : []
         * INTRUDING_WAY_DESC : []
         * COMMISSION_MEANS_DESP : []
         * PERCULIARITIES_DESCRIPTION : []
         * TOOLS_DESCRIPTION : (刀，交通工具类，就地取材)
         * MOTIVE_DESC : []
         * CASE_PROPERTY_DESC : []
         * CRIMINAL_AMOUNT_DESC : 多人
         * COMMISSION_DESC_ID : []
         * CRIMINAL_POINTS_ID : 0577434dba8a43e4b29e5256dd666df4
         * BUNCH_REASON_ID : []
         * SUGGESTION_ID : []
         * ANALYSED_ORG : 许洋
         * ANALYSED_BY : 重庆市刑科所
         * ANALYSED_DATE : 20190319113601
         * INIT_SERVER_NO : []
         * TRANSFER_DATE : []
         * SECRECY : 1
         * DELETE_FLAG : 0
         * CREATE_USER : 重庆市刑科所
         * CREATE_DATETIME : 20190319113601
         * UPDATE_USER : []
         * UPDATE_DATETIME : 20190319113601
         * RESERVER1 : []
         * RESERVER2 : []
         * RESERVER3 : []
         * RESERVER4 : []
         * RESERVER5 : []
         * RESERVER6 : []
         * RESERVER7 : []
         * RESERVER8 : []
         * EXIT_ENTRANCE_DESC : []
         */

        private String ID;
        private String INVESTIGATION_ID;//现场信息ID
        private String LOST_GOODS_DESC;//损失物品描述
        private String COMMISSION_PLACE;//作案地点
        private String TOOLS_DESCRIPTION;//作案工具描述
        private String CRIMINAL_AMOUNT_DESC;//作案人数描述
        private String CRIMINAL_POINTS_ID;//作案人特点
        private String ANALYSED_ORG;//现场分析单位
        private String ANALYSED_BY;//现场分析人
        private String ANALYSED_DATE;//现场分析时间
        private String SECRECY;//密级
        private String DELETE_FLAG;//删除标志
        private String CREATE_USER;//记录创建人
        private String CREATE_DATETIME;//记录创建时间
        private String UPDATE_DATETIME;//记录更新时间
        private String METERIALS_RELIED_ON;//现场分析依据的资料
        private String INTENTION_DESC;//选择对象描述
        private String LOCATION_DESC;//选择处所描述
        private String INVOLVED_ORG_TYPE_DESC;//涉案单位类型描述
        private String PERIOD_DESC;//作案时段描述
        private String ENTRANCE_EXIT_DESC;//作案进口描述
        private String EXIT_ENTRANCE_DESC;//出口
        private String INTRUDING_WAY_DESC;//侵入方式描述
        private String COMMISSION_MEANS_DESP;//作案手段描述
        private String PERCULIARITIES_DESCRIPTION;//作案特点描述
        private String MOTIVE_DESC;//作案动机目的描述
        private String CASE_PROPERTY_DESC;//案件性质描述
        private String COMMISSION_DESC_ID;//作案过程
        private String BUNCH_REASON_ID;//并案意见及根据
        private String SUGGESTION_ID;//工作建议
        private String INIT_SERVER_NO;//原始服务器编号
        private String TRANSFER_DATE;//上报时间
        private String UPDATE_USER;//记录更新人
        private String RESERVER1;
        private String RESERVER2;
        private String RESERVER3;
        private String RESERVER4;
        private String RESERVER5;
        private String RESERVER6;
        private String RESERVER7;
        private String RESERVER8;


        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getINVESTIGATION_ID() {
            return INVESTIGATION_ID;
        }

        public void setINVESTIGATION_ID(String INVESTIGATION_ID) {
            this.INVESTIGATION_ID = INVESTIGATION_ID;
        }

        public String getLOST_GOODS_DESC() {
            return LOST_GOODS_DESC;
        }

        public void setLOST_GOODS_DESC(String LOST_GOODS_DESC) {
            this.LOST_GOODS_DESC = LOST_GOODS_DESC;
        }

        public String getCOMMISSION_PLACE() {
            return COMMISSION_PLACE;
        }

        public void setCOMMISSION_PLACE(String COMMISSION_PLACE) {
            this.COMMISSION_PLACE = COMMISSION_PLACE;
        }

        public String getTOOLS_DESCRIPTION() {
            return TOOLS_DESCRIPTION;
        }

        public void setTOOLS_DESCRIPTION(String TOOLS_DESCRIPTION) {
            this.TOOLS_DESCRIPTION = TOOLS_DESCRIPTION;
        }

        public String getCRIMINAL_AMOUNT_DESC() {
            return CRIMINAL_AMOUNT_DESC;
        }

        public void setCRIMINAL_AMOUNT_DESC(String CRIMINAL_AMOUNT_DESC) {
            this.CRIMINAL_AMOUNT_DESC = CRIMINAL_AMOUNT_DESC;
        }

        public String getCRIMINAL_POINTS_ID() {
            return CRIMINAL_POINTS_ID;
        }

        public void setCRIMINAL_POINTS_ID(String CRIMINAL_POINTS_ID) {
            this.CRIMINAL_POINTS_ID = CRIMINAL_POINTS_ID;
        }

        public String getANALYSED_ORG() {
            return ANALYSED_ORG;
        }

        public void setANALYSED_ORG(String ANALYSED_ORG) {
            this.ANALYSED_ORG = ANALYSED_ORG;
        }

        public String getANALYSED_BY() {
            return ANALYSED_BY;
        }

        public void setANALYSED_BY(String ANALYSED_BY) {
            this.ANALYSED_BY = ANALYSED_BY;
        }

        public String getANALYSED_DATE() {
            return ANALYSED_DATE;
        }

        public void setANALYSED_DATE(String ANALYSED_DATE) {
            this.ANALYSED_DATE = ANALYSED_DATE;
        }

        public String getSECRECY() {
            return SECRECY;
        }

        public void setSECRECY(String SECRECY) {
            this.SECRECY = SECRECY;
        }

        public String getDELETE_FLAG() {
            return DELETE_FLAG;
        }

        public void setDELETE_FLAG(String DELETE_FLAG) {
            this.DELETE_FLAG = DELETE_FLAG;
        }

        public String getCREATE_USER() {
            return CREATE_USER;
        }

        public void setCREATE_USER(String CREATE_USER) {
            this.CREATE_USER = CREATE_USER;
        }

        public String getCREATE_DATETIME() {
            return CREATE_DATETIME;
        }

        public void setCREATE_DATETIME(String CREATE_DATETIME) {
            this.CREATE_DATETIME = CREATE_DATETIME;
        }

        public String getUPDATE_DATETIME() {
            return UPDATE_DATETIME;
        }

        public void setUPDATE_DATETIME(String UPDATE_DATETIME) {
            this.UPDATE_DATETIME = UPDATE_DATETIME;
        }

        public String getMETERIALS_RELIED_ON() {
            return METERIALS_RELIED_ON;
        }

        public void setMETERIALS_RELIED_ON(String METERIALS_RELIED_ON) {
            this.METERIALS_RELIED_ON = METERIALS_RELIED_ON;
        }

        public String getINTENTION_DESC() {
            return INTENTION_DESC;
        }

        public void setINTENTION_DESC(String INTENTION_DESC) {
            this.INTENTION_DESC = INTENTION_DESC;
        }

        public String getLOCATION_DESC() {
            return LOCATION_DESC;
        }

        public void setLOCATION_DESC(String LOCATION_DESC) {
            this.LOCATION_DESC = LOCATION_DESC;
        }

        public String getINVOLVED_ORG_TYPE_DESC() {
            return INVOLVED_ORG_TYPE_DESC;
        }

        public void setINVOLVED_ORG_TYPE_DESC(String INVOLVED_ORG_TYPE_DESC) {
            this.INVOLVED_ORG_TYPE_DESC = INVOLVED_ORG_TYPE_DESC;
        }

        public String getPERIOD_DESC() {
            return PERIOD_DESC;
        }

        public void setPERIOD_DESC(String PERIOD_DESC) {
            this.PERIOD_DESC = PERIOD_DESC;
        }

        public String getENTRANCE_EXIT_DESC() {
            return ENTRANCE_EXIT_DESC;
        }

        public void setENTRANCE_EXIT_DESC(String ENTRANCE_EXIT_DESC) {
            this.ENTRANCE_EXIT_DESC = ENTRANCE_EXIT_DESC;
        }

        public String getINTRUDING_WAY_DESC() {
            return INTRUDING_WAY_DESC;
        }

        public void setINTRUDING_WAY_DESC(String INTRUDING_WAY_DESC) {
            this.INTRUDING_WAY_DESC = INTRUDING_WAY_DESC;
        }

        public String getCOMMISSION_MEANS_DESP() {
            return COMMISSION_MEANS_DESP;
        }

        public void setCOMMISSION_MEANS_DESP(String COMMISSION_MEANS_DESP) {
            this.COMMISSION_MEANS_DESP = COMMISSION_MEANS_DESP;
        }

        public String getPERCULIARITIES_DESCRIPTION() {
            return PERCULIARITIES_DESCRIPTION;
        }

        public void setPERCULIARITIES_DESCRIPTION(String PERCULIARITIES_DESCRIPTION) {
            this.PERCULIARITIES_DESCRIPTION = PERCULIARITIES_DESCRIPTION;
        }

        public String getMOTIVE_DESC() {
            return MOTIVE_DESC;
        }

        public void setMOTIVE_DESC(String MOTIVE_DESC) {
            this.MOTIVE_DESC = MOTIVE_DESC;
        }

        public String getCASE_PROPERTY_DESC() {
            return CASE_PROPERTY_DESC;
        }

        public void setCASE_PROPERTY_DESC(String CASE_PROPERTY_DESC) {
            this.CASE_PROPERTY_DESC = CASE_PROPERTY_DESC;
        }

        public String getCOMMISSION_DESC_ID() {
            return COMMISSION_DESC_ID;
        }

        public void setCOMMISSION_DESC_ID(String COMMISSION_DESC_ID) {
            this.COMMISSION_DESC_ID = COMMISSION_DESC_ID;
        }

        public String getBUNCH_REASON_ID() {
            return BUNCH_REASON_ID;
        }

        public void setBUNCH_REASON_ID(String BUNCH_REASON_ID) {
            this.BUNCH_REASON_ID = BUNCH_REASON_ID;
        }

        public String getSUGGESTION_ID() {
            return SUGGESTION_ID;
        }

        public void setSUGGESTION_ID(String SUGGESTION_ID) {
            this.SUGGESTION_ID = SUGGESTION_ID;
        }

        public String getINIT_SERVER_NO() {
            return INIT_SERVER_NO;
        }

        public void setINIT_SERVER_NO(String INIT_SERVER_NO) {
            this.INIT_SERVER_NO = INIT_SERVER_NO;
        }

        public String getTRANSFER_DATE() {
            return TRANSFER_DATE;
        }

        public void setTRANSFER_DATE(String TRANSFER_DATE) {
            this.TRANSFER_DATE = TRANSFER_DATE;
        }

        public String getUPDATE_USER() {
            return UPDATE_USER;
        }

        public void setUPDATE_USER(String UPDATE_USER) {
            this.UPDATE_USER = UPDATE_USER;
        }

        public String getRESERVER1() {
            return RESERVER1;
        }

        public void setRESERVER1(String RESERVER1) {
            this.RESERVER1 = RESERVER1;
        }

        public String getRESERVER2() {
            return RESERVER2;
        }

        public void setRESERVER2(String RESERVER2) {
            this.RESERVER2 = RESERVER2;
        }

        public String getRESERVER3() {
            return RESERVER3;
        }

        public void setRESERVER3(String RESERVER3) {
            this.RESERVER3 = RESERVER3;
        }

        public String getRESERVER4() {
            return RESERVER4;
        }

        public void setRESERVER4(String RESERVER4) {
            this.RESERVER4 = RESERVER4;
        }

        public String getRESERVER5() {
            return RESERVER5;
        }

        public void setRESERVER5(String RESERVER5) {
            this.RESERVER5 = RESERVER5;
        }

        public String getRESERVER6() {
            return RESERVER6;
        }

        public void setRESERVER6(String RESERVER6) {
            this.RESERVER6 = RESERVER6;
        }

        public String getRESERVER7() {
            return RESERVER7;
        }

        public void setRESERVER7(String RESERVER7) {
            this.RESERVER7 = RESERVER7;
        }

        public String getRESERVER8() {
            return RESERVER8;
        }

        public void setRESERVER8(String RESERVER8) {
            this.RESERVER8 = RESERVER8;
        }

        public String getEXIT_ENTRANCE_DESC() {
            return EXIT_ENTRANCE_DESC;
        }

        public void setEXIT_ENTRANCE_DESC(String EXIT_ENTRANCE_DESC) {
            this.EXIT_ENTRANCE_DESC = EXIT_ENTRANCE_DESC;
        }
    }

    public static class COMMONBIGTEXTCLOBBean {
        /**
         * ID : e6d7cfe7790140fa8bd33a5eec964131
         * CONTENT : []
         * REMARK : []
         * SECRECY : 1
         * DELETE_FLAG : 0
         * CREATE_USER : 许洋
         * CREATE_DATETIME : 20190319113603
         * UPDATE_USER : []
         * UPDATE_DATETIME : 20190319113603
         * RESERVER1 : []
         * RESERVER2 : []
         * RESERVER3 : []
         * RESERVER4 : []
         * RESERVER5 : []
         * RESERVER6 : []
         * RESERVER7 : []
         * RESERVER8 : []
         */

        private String ID;
        private String SECRECY;//密级
        private String DELETE_FLAG;//删除标志
        private String CREATE_USER;//记录创建人
        private String CREATE_DATETIME;//记录创建时间
        private String UPDATE_DATETIME;//记录更新时间
        private String CONTENT;//附件内容
        private String REMARK;//备注
        private String UPDATE_USER;//记录更新人
        private String RESERVER1;
        private String RESERVER2;
        private String RESERVER3;
        private String RESERVER4;
        private String RESERVER5;
        private String RESERVER6;
        private String RESERVER7;
        private String RESERVER8;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getSECRECY() {
            return SECRECY;
        }

        public void setSECRECY(String SECRECY) {
            this.SECRECY = SECRECY;
        }

        public String getDELETE_FLAG() {
            return DELETE_FLAG;
        }

        public void setDELETE_FLAG(String DELETE_FLAG) {
            this.DELETE_FLAG = DELETE_FLAG;
        }

        public String getCREATE_USER() {
            return CREATE_USER;
        }

        public void setCREATE_USER(String CREATE_USER) {
            this.CREATE_USER = CREATE_USER;
        }

        public String getCREATE_DATETIME() {
            return CREATE_DATETIME;
        }

        public void setCREATE_DATETIME(String CREATE_DATETIME) {
            this.CREATE_DATETIME = CREATE_DATETIME;
        }

        public String getUPDATE_DATETIME() {
            return UPDATE_DATETIME;
        }

        public void setUPDATE_DATETIME(String UPDATE_DATETIME) {
            this.UPDATE_DATETIME = UPDATE_DATETIME;
        }

        public String getCONTENT() {
            return CONTENT;
        }

        public void setCONTENT(String CONTENT) {
            this.CONTENT = CONTENT;
        }

        public String getREMARK() {
            return REMARK;
        }

        public void setREMARK(String REMARK) {
            this.REMARK = REMARK;
        }

        public String getUPDATE_USER() {
            return UPDATE_USER;
        }

        public void setUPDATE_USER(String UPDATE_USER) {
            this.UPDATE_USER = UPDATE_USER;
        }

        public String getRESERVER1() {
            return RESERVER1;
        }

        public void setRESERVER1(String RESERVER1) {
            this.RESERVER1 = RESERVER1;
        }

        public String getRESERVER2() {
            return RESERVER2;
        }

        public void setRESERVER2(String RESERVER2) {
            this.RESERVER2 = RESERVER2;
        }

        public String getRESERVER3() {
            return RESERVER3;
        }

        public void setRESERVER3(String RESERVER3) {
            this.RESERVER3 = RESERVER3;
        }

        public String getRESERVER4() {
            return RESERVER4;
        }

        public void setRESERVER4(String RESERVER4) {
            this.RESERVER4 = RESERVER4;
        }

        public String getRESERVER5() {
            return RESERVER5;
        }

        public void setRESERVER5(String RESERVER5) {
            this.RESERVER5 = RESERVER5;
        }

        public String getRESERVER6() {
            return RESERVER6;
        }

        public void setRESERVER6(String RESERVER6) {
            this.RESERVER6 = RESERVER6;
        }

        public String getRESERVER7() {
            return RESERVER7;
        }

        public void setRESERVER7(String RESERVER7) {
            this.RESERVER7 = RESERVER7;
        }

        public String getRESERVER8() {
            return RESERVER8;
        }

        public void setRESERVER8(String RESERVER8) {
            this.RESERVER8 = RESERVER8;
        }
    }

    public static class SCENECOMMISSIONTOOLSBean {
        /**
         * ID : 1a85016806364a8caafc1920bc7f0f52
         * ANALYSIS_ID : eab7cccaa2444792866738ae5c31f2b1
         * SPECIES : 2
         * NAME : 刀
         * ORIGIN : A
         * DESCRIPTION : []
         * SECRECY : 1
         * DELETE_FLAG : 0
         * CREATE_USER : 许洋
         * CREATE_DATETIME : 20190319113601
         * UPDATE_USER : []
         * UPDATE_DATETIME : 20190319113601
         * RESERVER1 : []
         * RESERVER2 : []
         * RESERVER3 : []
         * RESERVER4 : []
         * RESERVER5 : []
         * RESERVER6 : []
         * RESERVER7 : []
         * RESERVER8 : []
         */

        private String ID;
        private String ANALYSIS_ID;//分析意见表ID
        private String SPECIES;//工具类目
        private String NAME;//具体名称
        private String ORIGIN;//工具来源
        private String SECRECY;//密级
        private String DELETE_FLAG;//删除标志
        private String CREATE_USER;//记录创建人
        private String CREATE_DATETIME;//记录创建时间
        private String UPDATE_DATETIME;//记录更新时间
        private String DESCRIPTION;//描述
        private String UPDATE_USER;//记录更新人
        private String RESERVER1;
        private String RESERVER2;
        private String RESERVER3;
        private String RESERVER4;
        private String RESERVER5;
        private String RESERVER6;
        private String RESERVER7;
        private String RESERVER8;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getANALYSIS_ID() {
            return ANALYSIS_ID;
        }

        public void setANALYSIS_ID(String ANALYSIS_ID) {
            this.ANALYSIS_ID = ANALYSIS_ID;
        }

        public String getSPECIES() {
            return SPECIES;
        }

        public void setSPECIES(String SPECIES) {
            this.SPECIES = SPECIES;
        }

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public String getORIGIN() {
            return ORIGIN;
        }

        public void setORIGIN(String ORIGIN) {
            this.ORIGIN = ORIGIN;
        }

        public String getSECRECY() {
            return SECRECY;
        }

        public void setSECRECY(String SECRECY) {
            this.SECRECY = SECRECY;
        }

        public String getDELETE_FLAG() {
            return DELETE_FLAG;
        }

        public void setDELETE_FLAG(String DELETE_FLAG) {
            this.DELETE_FLAG = DELETE_FLAG;
        }

        public String getCREATE_USER() {
            return CREATE_USER;
        }

        public void setCREATE_USER(String CREATE_USER) {
            this.CREATE_USER = CREATE_USER;
        }

        public String getCREATE_DATETIME() {
            return CREATE_DATETIME;
        }

        public void setCREATE_DATETIME(String CREATE_DATETIME) {
            this.CREATE_DATETIME = CREATE_DATETIME;
        }

        public String getUPDATE_DATETIME() {
            return UPDATE_DATETIME;
        }

        public void setUPDATE_DATETIME(String UPDATE_DATETIME) {
            this.UPDATE_DATETIME = UPDATE_DATETIME;
        }

        public String getDESCRIPTION() {
            return DESCRIPTION;
        }

        public void setDESCRIPTION(String DESCRIPTION) {
            this.DESCRIPTION = DESCRIPTION;
        }

        public String getUPDATE_USER() {
            return UPDATE_USER;
        }

        public void setUPDATE_USER(String UPDATE_USER) {
            this.UPDATE_USER = UPDATE_USER;
        }

        public String getRESERVER1() {
            return RESERVER1;
        }

        public void setRESERVER1(String RESERVER1) {
            this.RESERVER1 = RESERVER1;
        }

        public String getRESERVER2() {
            return RESERVER2;
        }

        public void setRESERVER2(String RESERVER2) {
            this.RESERVER2 = RESERVER2;
        }

        public String getRESERVER3() {
            return RESERVER3;
        }

        public void setRESERVER3(String RESERVER3) {
            this.RESERVER3 = RESERVER3;
        }

        public String getRESERVER4() {
            return RESERVER4;
        }

        public void setRESERVER4(String RESERVER4) {
            this.RESERVER4 = RESERVER4;
        }

        public String getRESERVER5() {
            return RESERVER5;
        }

        public void setRESERVER5(String RESERVER5) {
            this.RESERVER5 = RESERVER5;
        }

        public String getRESERVER6() {
            return RESERVER6;
        }

        public void setRESERVER6(String RESERVER6) {
            this.RESERVER6 = RESERVER6;
        }

        public String getRESERVER7() {
            return RESERVER7;
        }

        public void setRESERVER7(String RESERVER7) {
            this.RESERVER7 = RESERVER7;
        }

        public String getRESERVER8() {
            return RESERVER8;
        }

        public void setRESERVER8(String RESERVER8) {
            this.RESERVER8 = RESERVER8;
        }
    }

    public static class SCENELOSTGOODSBean {
        /**
         * ID : 97b09d17d61045df9e46497ba503ba47
         * SERIAL_NO : 1
         * INVESTIGATION_ID : 394be5a5fee343a49481dd1a64f5eceb
         * NAME : 测试
         * BRAND : v
         * SPECIFICATION : []
         * DESCRIPTION : []
         * COLOR : []
         * AMOUNT : 5
         * VALUE : 888
         * REMARK : []
         * SECRECY : 1
         * DELETE_FLAG : 0
         * CREATE_USER : 许洋
         * CREATE_DATETIME : 20190319113601
         * UPDATE_USER : []
         * UPDATE_DATETIME : 20190319113601
         * RESERVER1 : []
         * RESERVER2 : []
         * RESERVER3 : []
         * RESERVER4 : []
         * RESERVER5 : []
         * RESERVER6 : []
         * RESERVER7 : []
         * RESERVER8 : []
         */

        private String ID;
        private String SERIAL_NO;//序号
        private String INVESTIGATION_ID;//现场信息ID
        private String NAME;//品名
        private String BRAND;//厂牌
        private String AMOUNT;//数量
        private String VALUE;//价值
        private String SECRECY;//密级
        private String DELETE_FLAG;//删除标志
        private String CREATE_USER;//记录创建人
        private String CREATE_DATETIME;//记录创建时间
        private String UPDATE_DATETIME;//记录更新时间
        private String SPECIFICATION;//型号
        private String DESCRIPTION;//特征描述
        private String COLOR;//颜色
        private String REMARK;//备注
        private String UPDATE_USER;//记录更新人
        private String RESERVER1;
        private String RESERVER2;
        private String RESERVER3;
        private String RESERVER4;
        private String RESERVER5;
        private String RESERVER6;
        private String RESERVER7;
        private String RESERVER8;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getSERIAL_NO() {
            return SERIAL_NO;
        }

        public void setSERIAL_NO(String SERIAL_NO) {
            this.SERIAL_NO = SERIAL_NO;
        }

        public String getINVESTIGATION_ID() {
            return INVESTIGATION_ID;
        }

        public void setINVESTIGATION_ID(String INVESTIGATION_ID) {
            this.INVESTIGATION_ID = INVESTIGATION_ID;
        }

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public String getBRAND() {
            return BRAND;
        }

        public void setBRAND(String BRAND) {
            this.BRAND = BRAND;
        }

        public String getAMOUNT() {
            return AMOUNT;
        }

        public void setAMOUNT(String AMOUNT) {
            this.AMOUNT = AMOUNT;
        }

        public String getVALUE() {
            return VALUE;
        }

        public void setVALUE(String VALUE) {
            this.VALUE = VALUE;
        }

        public String getSECRECY() {
            return SECRECY;
        }

        public void setSECRECY(String SECRECY) {
            this.SECRECY = SECRECY;
        }

        public String getDELETE_FLAG() {
            return DELETE_FLAG;
        }

        public void setDELETE_FLAG(String DELETE_FLAG) {
            this.DELETE_FLAG = DELETE_FLAG;
        }

        public String getCREATE_USER() {
            return CREATE_USER;
        }

        public void setCREATE_USER(String CREATE_USER) {
            this.CREATE_USER = CREATE_USER;
        }

        public String getCREATE_DATETIME() {
            return CREATE_DATETIME;
        }

        public void setCREATE_DATETIME(String CREATE_DATETIME) {
            this.CREATE_DATETIME = CREATE_DATETIME;
        }

        public String getUPDATE_DATETIME() {
            return UPDATE_DATETIME;
        }

        public void setUPDATE_DATETIME(String UPDATE_DATETIME) {
            this.UPDATE_DATETIME = UPDATE_DATETIME;
        }

        public String getSPECIFICATION() {
            return SPECIFICATION;
        }

        public void setSPECIFICATION(String SPECIFICATION) {
            this.SPECIFICATION = SPECIFICATION;
        }

        public String getDESCRIPTION() {
            return DESCRIPTION;
        }

        public void setDESCRIPTION(String DESCRIPTION) {
            this.DESCRIPTION = DESCRIPTION;
        }

        public String getCOLOR() {
            return COLOR;
        }

        public void setCOLOR(String COLOR) {
            this.COLOR = COLOR;
        }

        public String getREMARK() {
            return REMARK;
        }

        public void setREMARK(String REMARK) {
            this.REMARK = REMARK;
        }

        public String getUPDATE_USER() {
            return UPDATE_USER;
        }

        public void setUPDATE_USER(String UPDATE_USER) {
            this.UPDATE_USER = UPDATE_USER;
        }

        public String getRESERVER1() {
            return RESERVER1;
        }

        public void setRESERVER1(String RESERVER1) {
            this.RESERVER1 = RESERVER1;
        }

        public String getRESERVER2() {
            return RESERVER2;
        }

        public void setRESERVER2(String RESERVER2) {
            this.RESERVER2 = RESERVER2;
        }

        public String getRESERVER3() {
            return RESERVER3;
        }

        public void setRESERVER3(String RESERVER3) {
            this.RESERVER3 = RESERVER3;
        }

        public String getRESERVER4() {
            return RESERVER4;
        }

        public void setRESERVER4(String RESERVER4) {
            this.RESERVER4 = RESERVER4;
        }

        public String getRESERVER5() {
            return RESERVER5;
        }

        public void setRESERVER5(String RESERVER5) {
            this.RESERVER5 = RESERVER5;
        }

        public String getRESERVER6() {
            return RESERVER6;
        }

        public void setRESERVER6(String RESERVER6) {
            this.RESERVER6 = RESERVER6;
        }

        public String getRESERVER7() {
            return RESERVER7;
        }

        public void setRESERVER7(String RESERVER7) {
            this.RESERVER7 = RESERVER7;
        }

        public String getRESERVER8() {
            return RESERVER8;
        }

        public void setRESERVER8(String RESERVER8) {
            this.RESERVER8 = RESERVER8;
        }
    }

    public static class SCENEWITNESSBean {
        /**
         * ID : 5f0ce97266d64b35a55be86faaa6bf4e
         * INVESTIGATION_ID : 394be5a5fee343a49481dd1a64f5eceb
         * SERIAL_NO : 1
         * NAME : 这种
         * SEX : 1
         * AGE : 0
         * PHONE : []
         * ORGANIZATION : []
         * ADDRESS : []
         * REMARK : []
         * SECRECY : 1
         * DELETE_FLAG : 0
         * CREATE_USER : 许洋
         * CREATE_DATETIME : 20190319113601
         * UPDATE_USER : []
         * UPDATE_DATETIME : 20190319113601
         * RESERVER1 : []
         * RESERVER2 : []
         * RESERVER3 : []
         * RESERVER4 : 2019-03-18
         * RESERVER5 : []
         * RESERVER6 : []
         * RESERVER7 : []
         * RESERVER8 : []
         */

        private String ID;
        private String INVESTIGATION_ID;//现场勘验ID
        private String SERIAL_NO;//序号
        private String NAME;//姓名
        private String SEX;//性别
        private String AGE;//年龄
        private String SECRECY;//密级
        private String DELETE_FLAG;//删除标志
        private String CREATE_USER;//记录创建人
        private String CREATE_DATETIME;//记录创建时间
        private String UPDATE_DATETIME;//记录更新时间
        private String RESERVER4;
        private String PHONE;//电话
        private String ORGANIZATION;//单位
        private String ADDRESS;//住址
        private String REMARK;//备注
        private String UPDATE_USER;//记录更新人
        private String RESERVER1;//保留字段
        private String RESERVER2;
        private String RESERVER3;
        private String RESERVER5;
        private String RESERVER6;
        private String RESERVER7;
        private String RESERVER8;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getINVESTIGATION_ID() {
            return INVESTIGATION_ID;
        }

        public void setINVESTIGATION_ID(String INVESTIGATION_ID) {
            this.INVESTIGATION_ID = INVESTIGATION_ID;
        }

        public String getSERIAL_NO() {
            return SERIAL_NO;
        }

        public void setSERIAL_NO(String SERIAL_NO) {
            this.SERIAL_NO = SERIAL_NO;
        }

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public String getSEX() {
            return SEX;
        }

        public void setSEX(String SEX) {
            this.SEX = SEX;
        }

        public String getAGE() {
            return AGE;
        }

        public void setAGE(String AGE) {
            this.AGE = AGE;
        }

        public String getSECRECY() {
            return SECRECY;
        }

        public void setSECRECY(String SECRECY) {
            this.SECRECY = SECRECY;
        }

        public String getDELETE_FLAG() {
            return DELETE_FLAG;
        }

        public void setDELETE_FLAG(String DELETE_FLAG) {
            this.DELETE_FLAG = DELETE_FLAG;
        }

        public String getCREATE_USER() {
            return CREATE_USER;
        }

        public void setCREATE_USER(String CREATE_USER) {
            this.CREATE_USER = CREATE_USER;
        }

        public String getCREATE_DATETIME() {
            return CREATE_DATETIME;
        }

        public void setCREATE_DATETIME(String CREATE_DATETIME) {
            this.CREATE_DATETIME = CREATE_DATETIME;
        }

        public String getUPDATE_DATETIME() {
            return UPDATE_DATETIME;
        }

        public void setUPDATE_DATETIME(String UPDATE_DATETIME) {
            this.UPDATE_DATETIME = UPDATE_DATETIME;
        }

        public String getRESERVER4() {
            return RESERVER4;
        }

        public void setRESERVER4(String RESERVER4) {
            this.RESERVER4 = RESERVER4;
        }

        public String getPHONE() {
            return PHONE;
        }

        public void setPHONE(String PHONE) {
            this.PHONE = PHONE;
        }

        public String getORGANIZATION() {
            return ORGANIZATION;
        }

        public void setORGANIZATION(String ORGANIZATION) {
            this.ORGANIZATION = ORGANIZATION;
        }

        public String getADDRESS() {
            return ADDRESS;
        }

        public void setADDRESS(String ADDRESS) {
            this.ADDRESS = ADDRESS;
        }

        public String getREMARK() {
            return REMARK;
        }

        public void setREMARK(String REMARK) {
            this.REMARK = REMARK;
        }

        public String getUPDATE_USER() {
            return UPDATE_USER;
        }

        public void setUPDATE_USER(String UPDATE_USER) {
            this.UPDATE_USER = UPDATE_USER;
        }

        public String getRESERVER1() {
            return RESERVER1;
        }

        public void setRESERVER1(String RESERVER1) {
            this.RESERVER1 = RESERVER1;
        }

        public String getRESERVER2() {
            return RESERVER2;
        }

        public void setRESERVER2(String RESERVER2) {
            this.RESERVER2 = RESERVER2;
        }

        public String getRESERVER3() {
            return RESERVER3;
        }

        public void setRESERVER3(String RESERVER3) {
            this.RESERVER3 = RESERVER3;
        }

        public String getRESERVER5() {
            return RESERVER5;
        }

        public void setRESERVER5(String RESERVER5) {
            this.RESERVER5 = RESERVER5;
        }

        public String getRESERVER6() {
            return RESERVER6;
        }

        public void setRESERVER6(String RESERVER6) {
            this.RESERVER6 = RESERVER6;
        }

        public String getRESERVER7() {
            return RESERVER7;
        }

        public void setRESERVER7(String RESERVER7) {
            this.RESERVER7 = RESERVER7;
        }

        public String getRESERVER8() {
            return RESERVER8;
        }

        public void setRESERVER8(String RESERVER8) {
            this.RESERVER8 = RESERVER8;
        }
    }

    public static class SCENEVICTIMBean {
        /**
         * ID : f7273149373e46839c85bbe07418260f
         * CASE_ID : b66c45d471854af1a8a66870cf057c63
         * NAME : 测试
         * SEX : 1
         * AGE : []
         * FIGURE : []
         * HARM_FORM : []
         * HARM_PART : []
         * VIOLATION : []
         * PHONE : 073188712585
         * ORGANIZATION : []
         * ADDRESS_REGIONALISM : []
         * ADDRESS : 长沙市岳麓区西
         * NATIVE_PLACE_REGIONALISM : []
         * NATIVE_PLACE : []
         * SECRECY : 1
         * DELETE_FLAG : 0
         * CREATE_USER : 许洋
         * CREATE_DATETIME : 20190319113601
         * UPDATE_USER : []
         * UPDATE_DATETIME : 20190319113601
         * RESERVER1 : 0
         * RESERVER2 : []
         * RESERVER3 : []
         * RESERVER4 : []
         * RESERVER5 : []
         * RESERVER6 : []
         * RESERVER7 : []
         * RESERVER8 : []
         */

        private String ID;
        private String CASE_ID;//案件ID
        private String NAME;//姓名
        private String SEX;//性别
        private String PHONE;//电话
        private String ADDRESS;//现住址详址
        private String SECRECY;//密级
        private String DELETE_FLAG;//删除标志
        private String CREATE_USER;//记录创建人
        private String CREATE_DATETIME;//记录创建时间
        private String UPDATE_DATETIME;//记录更新时间
        private String RESERVER1;
        private String AGE;//年龄
        private String FIGURE;//身份
        private String HARM_FORM;//受害形式
        private String HARM_PART;//伤害部位
        private String VIOLATION;//侵害程度
        private String ORGANIZATION;//单位
        private String ADDRESS_REGIONALISM;//现住址区划
        private String NATIVE_PLACE_REGIONALISM;//户籍地区划
        private String NATIVE_PLACE;//户籍地详址
        private String UPDATE_USER;//记录更新人
        private String RESERVER2;
        private String RESERVER3;
        private String RESERVER4;
        private String RESERVER5;
        private String RESERVER6;
        private String RESERVER7;
        private String RESERVER8;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getCASE_ID() {
            return CASE_ID;
        }

        public void setCASE_ID(String CASE_ID) {
            this.CASE_ID = CASE_ID;
        }

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public String getSEX() {
            return SEX;
        }

        public void setSEX(String SEX) {
            this.SEX = SEX;
        }

        public String getPHONE() {
            return PHONE;
        }

        public void setPHONE(String PHONE) {
            this.PHONE = PHONE;
        }

        public String getADDRESS() {
            return ADDRESS;
        }

        public void setADDRESS(String ADDRESS) {
            this.ADDRESS = ADDRESS;
        }

        public String getSECRECY() {
            return SECRECY;
        }

        public void setSECRECY(String SECRECY) {
            this.SECRECY = SECRECY;
        }

        public String getDELETE_FLAG() {
            return DELETE_FLAG;
        }

        public void setDELETE_FLAG(String DELETE_FLAG) {
            this.DELETE_FLAG = DELETE_FLAG;
        }

        public String getCREATE_USER() {
            return CREATE_USER;
        }

        public void setCREATE_USER(String CREATE_USER) {
            this.CREATE_USER = CREATE_USER;
        }

        public String getCREATE_DATETIME() {
            return CREATE_DATETIME;
        }

        public void setCREATE_DATETIME(String CREATE_DATETIME) {
            this.CREATE_DATETIME = CREATE_DATETIME;
        }

        public String getUPDATE_DATETIME() {
            return UPDATE_DATETIME;
        }

        public void setUPDATE_DATETIME(String UPDATE_DATETIME) {
            this.UPDATE_DATETIME = UPDATE_DATETIME;
        }

        public String getRESERVER1() {
            return RESERVER1;
        }

        public void setRESERVER1(String RESERVER1) {
            this.RESERVER1 = RESERVER1;
        }

        public String getAGE() {
            return AGE;
        }

        public void setAGE(String AGE) {
            this.AGE = AGE;
        }

        public String getFIGURE() {
            return FIGURE;
        }

        public void setFIGURE(String FIGURE) {
            this.FIGURE = FIGURE;
        }

        public String getHARM_FORM() {
            return HARM_FORM;
        }

        public void setHARM_FORM(String HARM_FORM) {
            this.HARM_FORM = HARM_FORM;
        }

        public String getHARM_PART() {
            return HARM_PART;
        }

        public void setHARM_PART(String HARM_PART) {
            this.HARM_PART = HARM_PART;
        }

        public String getVIOLATION() {
            return VIOLATION;
        }

        public void setVIOLATION(String VIOLATION) {
            this.VIOLATION = VIOLATION;
        }

        public String getORGANIZATION() {
            return ORGANIZATION;
        }

        public void setORGANIZATION(String ORGANIZATION) {
            this.ORGANIZATION = ORGANIZATION;
        }

        public String getADDRESS_REGIONALISM() {
            return ADDRESS_REGIONALISM;
        }

        public void setADDRESS_REGIONALISM(String ADDRESS_REGIONALISM) {
            this.ADDRESS_REGIONALISM = ADDRESS_REGIONALISM;
        }

        public String getNATIVE_PLACE_REGIONALISM() {
            return NATIVE_PLACE_REGIONALISM;
        }

        public void setNATIVE_PLACE_REGIONALISM(String NATIVE_PLACE_REGIONALISM) {
            this.NATIVE_PLACE_REGIONALISM = NATIVE_PLACE_REGIONALISM;
        }

        public String getNATIVE_PLACE() {
            return NATIVE_PLACE;
        }

        public void setNATIVE_PLACE(String NATIVE_PLACE) {
            this.NATIVE_PLACE = NATIVE_PLACE;
        }

        public String getUPDATE_USER() {
            return UPDATE_USER;
        }

        public void setUPDATE_USER(String UPDATE_USER) {
            this.UPDATE_USER = UPDATE_USER;
        }

        public String getRESERVER2() {
            return RESERVER2;
        }

        public void setRESERVER2(String RESERVER2) {
            this.RESERVER2 = RESERVER2;
        }

        public String getRESERVER3() {
            return RESERVER3;
        }

        public void setRESERVER3(String RESERVER3) {
            this.RESERVER3 = RESERVER3;
        }

        public String getRESERVER4() {
            return RESERVER4;
        }

        public void setRESERVER4(String RESERVER4) {
            this.RESERVER4 = RESERVER4;
        }

        public String getRESERVER5() {
            return RESERVER5;
        }

        public void setRESERVER5(String RESERVER5) {
            this.RESERVER5 = RESERVER5;
        }

        public String getRESERVER6() {
            return RESERVER6;
        }

        public void setRESERVER6(String RESERVER6) {
            this.RESERVER6 = RESERVER6;
        }

        public String getRESERVER7() {
            return RESERVER7;
        }

        public void setRESERVER7(String RESERVER7) {
            this.RESERVER7 = RESERVER7;
        }

        public String getRESERVER8() {
            return RESERVER8;
        }

        public void setRESERVER8(String RESERVER8) {
            this.RESERVER8 = RESERVER8;
        }
    }

    public static class SCENEPICTUREBean {
        /**
         * ID : 3cc3f43724574d8bbd6b4cccdb2e3aa3
         * SERIAL_NO : 2
         * INVESTIGATION_ID : 394be5a5fee343a49481dd1a64f5eceb
         * CREATE_USER_ID : 2f869bcd610c539301610c6d8f4b0002
         * PICTURE_ID : 43827c245b49455198cf7c1e48492705
         * PICTURE_NAME : 2019年03月18日重庆市刑科所盗窃自行车现场示意图
         * PICTURE_TYPE : 1010
         * DESCRIPTION : 2019年03月18日重庆市刑科所盗窃自行车现场示意图
         * SECRECY : 1
         * DELETE_FLAG : 0
         * CREATE_USER : 许洋
         * CREATE_DATETIME : 20190319113602
         * UPDATE_USER : []
         * UPDATE_DATETIME : 20190319113602
         * RESERVER1 : []
         * RESERVER2 : []
         * RESERVER3 : []
         * RESERVER4 : []
         * RESERVER5 : []
         * RESERVER6 : []
         * RESERVER7 : []
         * RESERVER8 : []
         */

        private String ID;
        private String SERIAL_NO;//序号
        private String INVESTIGATION_ID;//现场信息ID
        private String CREATE_USER_ID;//创建用户id
        private String PICTURE_ID;//现场图附件ID
        private String PICTURE_NAME;//现场图名称
        private String PICTURE_TYPE;//现场图类型
        private String DESCRIPTION;//现场图说明
        private String SECRECY;//密级
        private String DELETE_FLAG;//删除标志
        private String CREATE_USER;//记录创建人
        private String CREATE_DATETIME;//记录创建时间
        private String UPDATE_DATETIME;//记录更新时间
        private String UPDATE_USER;//记录更新人
        private String RESERVER1;
        private String RESERVER2;
        private String RESERVER3;
        private String RESERVER4;
        private String RESERVER5;
        private String RESERVER6;
        private String RESERVER7;
        private String RESERVER8;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getSERIAL_NO() {
            return SERIAL_NO;
        }

        public void setSERIAL_NO(String SERIAL_NO) {
            this.SERIAL_NO = SERIAL_NO;
        }

        public String getINVESTIGATION_ID() {
            return INVESTIGATION_ID;
        }

        public void setINVESTIGATION_ID(String INVESTIGATION_ID) {
            this.INVESTIGATION_ID = INVESTIGATION_ID;
        }

        public String getCREATE_USER_ID() {
            return CREATE_USER_ID;
        }

        public void setCREATE_USER_ID(String CREATE_USER_ID) {
            this.CREATE_USER_ID = CREATE_USER_ID;
        }

        public String getPICTURE_ID() {
            return PICTURE_ID;
        }

        public void setPICTURE_ID(String PICTURE_ID) {
            this.PICTURE_ID = PICTURE_ID;
        }

        public String getPICTURE_NAME() {
            return PICTURE_NAME;
        }

        public void setPICTURE_NAME(String PICTURE_NAME) {
            this.PICTURE_NAME = PICTURE_NAME;
        }

        public String getPICTURE_TYPE() {
            return PICTURE_TYPE;
        }

        public void setPICTURE_TYPE(String PICTURE_TYPE) {
            this.PICTURE_TYPE = PICTURE_TYPE;
        }

        public String getDESCRIPTION() {
            return DESCRIPTION;
        }

        public void setDESCRIPTION(String DESCRIPTION) {
            this.DESCRIPTION = DESCRIPTION;
        }

        public String getSECRECY() {
            return SECRECY;
        }

        public void setSECRECY(String SECRECY) {
            this.SECRECY = SECRECY;
        }

        public String getDELETE_FLAG() {
            return DELETE_FLAG;
        }

        public void setDELETE_FLAG(String DELETE_FLAG) {
            this.DELETE_FLAG = DELETE_FLAG;
        }

        public String getCREATE_USER() {
            return CREATE_USER;
        }

        public void setCREATE_USER(String CREATE_USER) {
            this.CREATE_USER = CREATE_USER;
        }

        public String getCREATE_DATETIME() {
            return CREATE_DATETIME;
        }

        public void setCREATE_DATETIME(String CREATE_DATETIME) {
            this.CREATE_DATETIME = CREATE_DATETIME;
        }

        public String getUPDATE_DATETIME() {
            return UPDATE_DATETIME;
        }

        public void setUPDATE_DATETIME(String UPDATE_DATETIME) {
            this.UPDATE_DATETIME = UPDATE_DATETIME;
        }

        public String getUPDATE_USER() {
            return UPDATE_USER;
        }

        public void setUPDATE_USER(String UPDATE_USER) {
            this.UPDATE_USER = UPDATE_USER;
        }

        public String getRESERVER1() {
            return RESERVER1;
        }

        public void setRESERVER1(String RESERVER1) {
            this.RESERVER1 = RESERVER1;
        }

        public String getRESERVER2() {
            return RESERVER2;
        }

        public void setRESERVER2(String RESERVER2) {
            this.RESERVER2 = RESERVER2;
        }

        public String getRESERVER3() {
            return RESERVER3;
        }

        public void setRESERVER3(String RESERVER3) {
            this.RESERVER3 = RESERVER3;
        }

        public String getRESERVER4() {
            return RESERVER4;
        }

        public void setRESERVER4(String RESERVER4) {
            this.RESERVER4 = RESERVER4;
        }

        public String getRESERVER5() {
            return RESERVER5;
        }

        public void setRESERVER5(String RESERVER5) {
            this.RESERVER5 = RESERVER5;
        }

        public String getRESERVER6() {
            return RESERVER6;
        }

        public void setRESERVER6(String RESERVER6) {
            this.RESERVER6 = RESERVER6;
        }

        public String getRESERVER7() {
            return RESERVER7;
        }

        public void setRESERVER7(String RESERVER7) {
            this.RESERVER7 = RESERVER7;
        }

        public String getRESERVER8() {
            return RESERVER8;
        }

        public void setRESERVER8(String RESERVER8) {
            this.RESERVER8 = RESERVER8;
        }
    }

    public static class COMMONPICTUREBean {
        /**
         * ID : e70eab78483b4572b214f5b7b152d69f
         * INVESTIGATION_ID : 394be5a5fee343a49481dd1a64f5eceb
         * CONTENT : v/Pncf9+j/hViwsLxdQtma0nAEqknym9R7UAf/9k=
         * WIDTH : 1200
         * HEIGHT : 1600
         * FILE_NAME : /MONITORING_PHOTO_20190318_17510p.jpg
         * CATEGORY : 2
         * SUB_CATEGORY : []
         * TYPE : jpg
         * DESCRIPTION : []
         * SECRECY : 1
         * DELETE_FLAG : 0
         * CREATE_USER : 许洋
         * CREATE_DATETIME : 20190319113603
         * UPDATE_USER : []
         * UPDATE_DATETIME : 20190319113603
         * RESERVER1 : []
         * RESERVER2 : []
         * RESERVER3 : []
         * RESERVER4 : []
         * RESERVER5 : []
         * RESERVER6 : []
         * RESERVER7 : []
         * RESERVER8 : []
         */

        private String ID;
        private String INVESTIGATION_ID;//现场勘查ID
        private String CONTENT;//图片内容
        private String WIDTH;//图片宽度
        private String HEIGHT;//图片高度
        private String FILE_NAME;//图片文件名
        private String CATEGORY;//图片分类
        private String TYPE;//图片类型
        private String SECRECY;//密级
        private String DELETE_FLAG;//删除标志
        private String CREATE_USER;//记录创建人
        private String CREATE_DATETIME;//记录创建时间
        private String UPDATE_DATETIME;//记录更新时间
        private String SUB_CATEGORY;//图片子分类
        private String DESCRIPTION;//图片说明
        private String UPDATE_USER;//记录更新人
        private String RESERVER1;//图片类别，区分现场图，方位图等
        private String RESERVER2;
        private String RESERVER3;
        private String RESERVER4;
        private String RESERVER5;
        private String RESERVER6;
        private String RESERVER7;
        private String RESERVER8;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getINVESTIGATION_ID() {
            return INVESTIGATION_ID;
        }

        public void setINVESTIGATION_ID(String INVESTIGATION_ID) {
            this.INVESTIGATION_ID = INVESTIGATION_ID;
        }

        public String getCONTENT() {
            return CONTENT;
        }

        public void setCONTENT(String CONTENT) {
            this.CONTENT = CONTENT;
        }

        public String getWIDTH() {
            return WIDTH;
        }

        public void setWIDTH(String WIDTH) {
            this.WIDTH = WIDTH;
        }

        public String getHEIGHT() {
            return HEIGHT;
        }

        public void setHEIGHT(String HEIGHT) {
            this.HEIGHT = HEIGHT;
        }

        public String getFILE_NAME() {
            return FILE_NAME;
        }

        public void setFILE_NAME(String FILE_NAME) {
            this.FILE_NAME = FILE_NAME;
        }

        public String getCATEGORY() {
            return CATEGORY;
        }

        public void setCATEGORY(String CATEGORY) {
            this.CATEGORY = CATEGORY;
        }

        public String getTYPE() {
            return TYPE;
        }

        public void setTYPE(String TYPE) {
            this.TYPE = TYPE;
        }

        public String getSECRECY() {
            return SECRECY;
        }

        public void setSECRECY(String SECRECY) {
            this.SECRECY = SECRECY;
        }

        public String getDELETE_FLAG() {
            return DELETE_FLAG;
        }

        public void setDELETE_FLAG(String DELETE_FLAG) {
            this.DELETE_FLAG = DELETE_FLAG;
        }

        public String getCREATE_USER() {
            return CREATE_USER;
        }

        public void setCREATE_USER(String CREATE_USER) {
            this.CREATE_USER = CREATE_USER;
        }

        public String getCREATE_DATETIME() {
            return CREATE_DATETIME;
        }

        public void setCREATE_DATETIME(String CREATE_DATETIME) {
            this.CREATE_DATETIME = CREATE_DATETIME;
        }

        public String getUPDATE_DATETIME() {
            return UPDATE_DATETIME;
        }

        public void setUPDATE_DATETIME(String UPDATE_DATETIME) {
            this.UPDATE_DATETIME = UPDATE_DATETIME;
        }

        public String getSUB_CATEGORY() {
            return SUB_CATEGORY;
        }

        public void setSUB_CATEGORY(String SUB_CATEGORY) {
            this.SUB_CATEGORY = SUB_CATEGORY;
        }

        public String getDESCRIPTION() {
            return DESCRIPTION;
        }

        public void setDESCRIPTION(String DESCRIPTION) {
            this.DESCRIPTION = DESCRIPTION;
        }

        public String getUPDATE_USER() {
            return UPDATE_USER;
        }

        public void setUPDATE_USER(String UPDATE_USER) {
            this.UPDATE_USER = UPDATE_USER;
        }

        public String getRESERVER1() {
            return RESERVER1;
        }

        public void setRESERVER1(String RESERVER1) {
            this.RESERVER1 = RESERVER1;
        }

        public String getRESERVER2() {
            return RESERVER2;
        }

        public void setRESERVER2(String RESERVER2) {
            this.RESERVER2 = RESERVER2;
        }

        public String getRESERVER3() {
            return RESERVER3;
        }

        public void setRESERVER3(String RESERVER3) {
            this.RESERVER3 = RESERVER3;
        }

        public String getRESERVER4() {
            return RESERVER4;
        }

        public void setRESERVER4(String RESERVER4) {
            this.RESERVER4 = RESERVER4;
        }

        public String getRESERVER5() {
            return RESERVER5;
        }

        public void setRESERVER5(String RESERVER5) {
            this.RESERVER5 = RESERVER5;
        }

        public String getRESERVER6() {
            return RESERVER6;
        }

        public void setRESERVER6(String RESERVER6) {
            this.RESERVER6 = RESERVER6;
        }

        public String getRESERVER7() {
            return RESERVER7;
        }

        public void setRESERVER7(String RESERVER7) {
            this.RESERVER7 = RESERVER7;
        }

        public String getRESERVER8() {
            return RESERVER8;
        }

        public void setRESERVER8(String RESERVER8) {
            this.RESERVER8 = RESERVER8;
        }
    }

    public static class SCENEHANDPRINTBean {
        /**
         * ID : 14319022b331493fbd4663b75a86bf91
         * SERIAL_NO : 1
         * CREATE_USER_ID : 2f869bcd610c539301610c6d8f4b0002
         * INVESTIGATION_ID : 394be5a5fee343a49481dd1a64f5eceb
         * HANDPRINT_PHOTO_ID : 4707487ecc6146e2846db969e2e9bd05
         * PRINT_TYPE : 1102
         * PRINT_CODE : []
         * LEFT_POSITION : 出差
         * COLLECTION_MODE : A01
         * COLLECTED_BY : 2f869bcd60b646ab0160b649f223000b,2f869bcd60b646ab0160b64a63db000c
         * COLLECTED_BY_NAME : 许洋,测试
         * COLLECTED_DATE : 20190318175057
         * CRIMINAL_FLAG : []
         * STORAGE_FLAG : []
         * UTILIZATION : []
         * PRINT_FLAG : []
         * SECRECY : 1
         * DELETE_FLAG : 0
         * CREATE_USER : 许洋
         * CREATE_DATETIME : 20190319113601
         * UPDATE_USER : []
         * UPDATE_DATETIME : 20190319113601
         * RESERVER1 : []
         * RESERVER2 : []
         * RESERVER3 : []
         * RESERVER4 : []
         * RESERVER5 : []
         * RESERVER6 : []
         * RESERVER7 : []
         * RESERVER8 : []
         */

        private String ID;
        private String SERIAL_NO;//序号
        private String CREATE_USER_ID;//创建人id
        private String INVESTIGATION_ID;//现场信息ID
        private String HANDPRINT_PHOTO_ID;//手印照片ID
        private String PRINT_TYPE;//手印类型
        private String LEFT_POSITION;//遗留部位
        private String COLLECTION_MODE;//显现/提取方法
        private String COLLECTED_BY;//提取人
        private String COLLECTED_BY_NAME;//提取人姓名
        private String COLLECTED_DATE;//提取日期
        private String SECRECY;//密级
        private String DELETE_FLAG;//删除标志
        private String CREATE_USER;//记录创建人
        private String CREATE_DATETIME;//记录创建时间
        private String UPDATE_DATETIME;//记录更新时间
        private String PRINT_CODE;//指纹编码
        private String CRIMINAL_FLAG;//可靠程度
        private String STORAGE_FLAG;//是否存入指纹系统
        private String UTILIZATION;//利用情况
        private String PRINT_FLAG;//是否列入现场提取登记表
        private String UPDATE_USER;//记录更新人
        private String RESERVER1;
        private String RESERVER2;
        private String RESERVER3;
        private String RESERVER4;
        private String RESERVER5;
        private String RESERVER6;
        private String RESERVER7;
        private String RESERVER8;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getSERIAL_NO() {
            return SERIAL_NO;
        }

        public void setSERIAL_NO(String SERIAL_NO) {
            this.SERIAL_NO = SERIAL_NO;
        }

        public String getCREATE_USER_ID() {
            return CREATE_USER_ID;
        }

        public void setCREATE_USER_ID(String CREATE_USER_ID) {
            this.CREATE_USER_ID = CREATE_USER_ID;
        }

        public String getINVESTIGATION_ID() {
            return INVESTIGATION_ID;
        }

        public void setINVESTIGATION_ID(String INVESTIGATION_ID) {
            this.INVESTIGATION_ID = INVESTIGATION_ID;
        }

        public String getHANDPRINT_PHOTO_ID() {
            return HANDPRINT_PHOTO_ID;
        }

        public void setHANDPRINT_PHOTO_ID(String HANDPRINT_PHOTO_ID) {
            this.HANDPRINT_PHOTO_ID = HANDPRINT_PHOTO_ID;
        }

        public String getPRINT_TYPE() {
            return PRINT_TYPE;
        }

        public void setPRINT_TYPE(String PRINT_TYPE) {
            this.PRINT_TYPE = PRINT_TYPE;
        }

        public String getLEFT_POSITION() {
            return LEFT_POSITION;
        }

        public void setLEFT_POSITION(String LEFT_POSITION) {
            this.LEFT_POSITION = LEFT_POSITION;
        }

        public String getCOLLECTION_MODE() {
            return COLLECTION_MODE;
        }

        public void setCOLLECTION_MODE(String COLLECTION_MODE) {
            this.COLLECTION_MODE = COLLECTION_MODE;
        }

        public String getCOLLECTED_BY() {
            return COLLECTED_BY;
        }

        public void setCOLLECTED_BY(String COLLECTED_BY) {
            this.COLLECTED_BY = COLLECTED_BY;
        }

        public String getCOLLECTED_BY_NAME() {
            return COLLECTED_BY_NAME;
        }

        public void setCOLLECTED_BY_NAME(String COLLECTED_BY_NAME) {
            this.COLLECTED_BY_NAME = COLLECTED_BY_NAME;
        }

        public String getCOLLECTED_DATE() {
            return COLLECTED_DATE;
        }

        public void setCOLLECTED_DATE(String COLLECTED_DATE) {
            this.COLLECTED_DATE = COLLECTED_DATE;
        }

        public String getSECRECY() {
            return SECRECY;
        }

        public void setSECRECY(String SECRECY) {
            this.SECRECY = SECRECY;
        }

        public String getDELETE_FLAG() {
            return DELETE_FLAG;
        }

        public void setDELETE_FLAG(String DELETE_FLAG) {
            this.DELETE_FLAG = DELETE_FLAG;
        }

        public String getCREATE_USER() {
            return CREATE_USER;
        }

        public void setCREATE_USER(String CREATE_USER) {
            this.CREATE_USER = CREATE_USER;
        }

        public String getCREATE_DATETIME() {
            return CREATE_DATETIME;
        }

        public void setCREATE_DATETIME(String CREATE_DATETIME) {
            this.CREATE_DATETIME = CREATE_DATETIME;
        }

        public String getUPDATE_DATETIME() {
            return UPDATE_DATETIME;
        }

        public void setUPDATE_DATETIME(String UPDATE_DATETIME) {
            this.UPDATE_DATETIME = UPDATE_DATETIME;
        }

        public String getPRINT_CODE() {
            return PRINT_CODE;
        }

        public void setPRINT_CODE(String PRINT_CODE) {
            this.PRINT_CODE = PRINT_CODE;
        }

        public String getCRIMINAL_FLAG() {
            return CRIMINAL_FLAG;
        }

        public void setCRIMINAL_FLAG(String CRIMINAL_FLAG) {
            this.CRIMINAL_FLAG = CRIMINAL_FLAG;
        }

        public String getSTORAGE_FLAG() {
            return STORAGE_FLAG;
        }

        public void setSTORAGE_FLAG(String STORAGE_FLAG) {
            this.STORAGE_FLAG = STORAGE_FLAG;
        }

        public String getUTILIZATION() {
            return UTILIZATION;
        }

        public void setUTILIZATION(String UTILIZATION) {
            this.UTILIZATION = UTILIZATION;
        }

        public String getPRINT_FLAG() {
            return PRINT_FLAG;
        }

        public void setPRINT_FLAG(String PRINT_FLAG) {
            this.PRINT_FLAG = PRINT_FLAG;
        }

        public String getUPDATE_USER() {
            return UPDATE_USER;
        }

        public void setUPDATE_USER(String UPDATE_USER) {
            this.UPDATE_USER = UPDATE_USER;
        }

        public String getRESERVER1() {
            return RESERVER1;
        }

        public void setRESERVER1(String RESERVER1) {
            this.RESERVER1 = RESERVER1;
        }

        public String getRESERVER2() {
            return RESERVER2;
        }

        public void setRESERVER2(String RESERVER2) {
            this.RESERVER2 = RESERVER2;
        }

        public String getRESERVER3() {
            return RESERVER3;
        }

        public void setRESERVER3(String RESERVER3) {
            this.RESERVER3 = RESERVER3;
        }

        public String getRESERVER4() {
            return RESERVER4;
        }

        public void setRESERVER4(String RESERVER4) {
            this.RESERVER4 = RESERVER4;
        }

        public String getRESERVER5() {
            return RESERVER5;
        }

        public void setRESERVER5(String RESERVER5) {
            this.RESERVER5 = RESERVER5;
        }

        public String getRESERVER6() {
            return RESERVER6;
        }

        public void setRESERVER6(String RESERVER6) {
            this.RESERVER6 = RESERVER6;
        }

        public String getRESERVER7() {
            return RESERVER7;
        }

        public void setRESERVER7(String RESERVER7) {
            this.RESERVER7 = RESERVER7;
        }

        public String getRESERVER8() {
            return RESERVER8;
        }

        public void setRESERVER8(String RESERVER8) {
            this.RESERVER8 = RESERVER8;
        }
    }

    public static class SCENEFOOTPRINTBean {
        /**
         * ID : 14319022b331493fbd4663b75a86bf91
         * SERIAL_NO : 1
         * CREATE_USER_ID : 2f869bcd610c539301610c6d8f4b0002
         * INVESTIGATION_ID : 394be5a5fee343a49481dd1a64f5eceb
         * FOOTPRINT_PHOTO_ID : 4707487ecc6146e2846db969e2e9bd05
         * PRINT_TYPE : 1102
         * PRINT_CODE_ID : []
         * LEFT_POSITION : 出差
         * COLLECTION_MODE : A01
         * COLLECTED_BY : 2f869bcd60b646ab0160b649f223000b,2f869bcd60b646ab0160b64a63db000c
         * COLLECTED_BY_NAME : 许洋,测试
         * COLLECTED_DATE : 20190318175057
         * CRIMINAL_FLAG : []
         * STORAGE_FLAG : []
         * UTILIZATION : []
         * PRINT_FLAG : []
         * SECRECY : 1
         * DELETE_FLAG : 0
         * CREATE_USER : 许洋
         * CREATE_DATETIME : 20190319113601
         * UPDATE_USER : []
         * UPDATE_DATETIME : 20190319113601
         * RESERVER1 : []
         * RESERVER2 : []
         * RESERVER3 : []
         * RESERVER4 : []
         * RESERVER5 : []
         * RESERVER6 : []
         * RESERVER7 : []
         * RESERVER8 : []
         */

        private String ID;
        private String SERIAL_NO;//序号
        private String CREATE_USER_ID;//创建人id
        private String INVESTIGATION_ID;//现场信息ID
        private String FOOTPRINT_PHOTO_ID;//现场足迹照片ID
        private String PRINT_TYPE;//足迹类型
        private String LEFT_POSITION;//遗留部位
        private String COLLECTION_MODE;//提取方法
        private String COLLECTED_BY;//提取人
        private String COLLECTED_BY_NAME;//提取人姓名
        private String COLLECTED_DATE;//提取日期
        private String SECRECY;//密级
        private String DELETE_FLAG;//删除标志
        private String CREATE_USER;//记录创建人
        private String CREATE_DATETIME;//记录创建时间
        private String UPDATE_DATETIME;//记录更新时间
        private String PRINT_CODE_ID;//足迹编码ID
        private String CRIMINAL_FLAG;//可靠程度
        private String STORAGE_FLAG;//是否存入足迹系统
        private String UTILIZATION;//利用情况
        private String PRINT_FLAG;//是否列入现场提取登记表
        private String UPDATE_USER;///记录更新人
        private String RESERVER1;
        private String RESERVER2;
        private String RESERVER3;
        private String RESERVER4;
        private String RESERVER5;
        private String RESERVER6;
        private String RESERVER7;
        private String RESERVER8;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getSERIAL_NO() {
            return SERIAL_NO;
        }

        public void setSERIAL_NO(String SERIAL_NO) {
            this.SERIAL_NO = SERIAL_NO;
        }

        public String getCREATE_USER_ID() {
            return CREATE_USER_ID;
        }

        public void setCREATE_USER_ID(String CREATE_USER_ID) {
            this.CREATE_USER_ID = CREATE_USER_ID;
        }

        public String getINVESTIGATION_ID() {
            return INVESTIGATION_ID;
        }

        public void setINVESTIGATION_ID(String INVESTIGATION_ID) {
            this.INVESTIGATION_ID = INVESTIGATION_ID;
        }

        public String getFOOTPRINT_PHOTO_ID() {
            return FOOTPRINT_PHOTO_ID;
        }

        public void setFOOTPRINT_PHOTO_ID(String FOOTPRINT_PHOTO_ID) {
            this.FOOTPRINT_PHOTO_ID = FOOTPRINT_PHOTO_ID;
        }

        public String getPRINT_TYPE() {
            return PRINT_TYPE;
        }

        public void setPRINT_TYPE(String PRINT_TYPE) {
            this.PRINT_TYPE = PRINT_TYPE;
        }

        public String getLEFT_POSITION() {
            return LEFT_POSITION;
        }

        public void setLEFT_POSITION(String LEFT_POSITION) {
            this.LEFT_POSITION = LEFT_POSITION;
        }

        public String getCOLLECTION_MODE() {
            return COLLECTION_MODE;
        }

        public void setCOLLECTION_MODE(String COLLECTION_MODE) {
            this.COLLECTION_MODE = COLLECTION_MODE;
        }

        public String getCOLLECTED_BY() {
            return COLLECTED_BY;
        }

        public void setCOLLECTED_BY(String COLLECTED_BY) {
            this.COLLECTED_BY = COLLECTED_BY;
        }

        public String getCOLLECTED_BY_NAME() {
            return COLLECTED_BY_NAME;
        }

        public void setCOLLECTED_BY_NAME(String COLLECTED_BY_NAME) {
            this.COLLECTED_BY_NAME = COLLECTED_BY_NAME;
        }

        public String getCOLLECTED_DATE() {
            return COLLECTED_DATE;
        }

        public void setCOLLECTED_DATE(String COLLECTED_DATE) {
            this.COLLECTED_DATE = COLLECTED_DATE;
        }

        public String getSECRECY() {
            return SECRECY;
        }

        public void setSECRECY(String SECRECY) {
            this.SECRECY = SECRECY;
        }

        public String getDELETE_FLAG() {
            return DELETE_FLAG;
        }

        public void setDELETE_FLAG(String DELETE_FLAG) {
            this.DELETE_FLAG = DELETE_FLAG;
        }

        public String getCREATE_USER() {
            return CREATE_USER;
        }

        public void setCREATE_USER(String CREATE_USER) {
            this.CREATE_USER = CREATE_USER;
        }

        public String getCREATE_DATETIME() {
            return CREATE_DATETIME;
        }

        public void setCREATE_DATETIME(String CREATE_DATETIME) {
            this.CREATE_DATETIME = CREATE_DATETIME;
        }

        public String getUPDATE_DATETIME() {
            return UPDATE_DATETIME;
        }

        public void setUPDATE_DATETIME(String UPDATE_DATETIME) {
            this.UPDATE_DATETIME = UPDATE_DATETIME;
        }

        public String getPRINT_CODE_ID() {
            return PRINT_CODE_ID;
        }

        public void setPRINT_CODE_ID(String PRINT_CODE_ID) {
            this.PRINT_CODE_ID = PRINT_CODE_ID;
        }

        public String getCRIMINAL_FLAG() {
            return CRIMINAL_FLAG;
        }

        public void setCRIMINAL_FLAG(String CRIMINAL_FLAG) {
            this.CRIMINAL_FLAG = CRIMINAL_FLAG;
        }

        public String getSTORAGE_FLAG() {
            return STORAGE_FLAG;
        }

        public void setSTORAGE_FLAG(String STORAGE_FLAG) {
            this.STORAGE_FLAG = STORAGE_FLAG;
        }

        public String getUTILIZATION() {
            return UTILIZATION;
        }

        public void setUTILIZATION(String UTILIZATION) {
            this.UTILIZATION = UTILIZATION;
        }

        public String getPRINT_FLAG() {
            return PRINT_FLAG;
        }

        public void setPRINT_FLAG(String PRINT_FLAG) {
            this.PRINT_FLAG = PRINT_FLAG;
        }

        public String getUPDATE_USER() {
            return UPDATE_USER;
        }

        public void setUPDATE_USER(String UPDATE_USER) {
            this.UPDATE_USER = UPDATE_USER;
        }

        public String getRESERVER1() {
            return RESERVER1;
        }

        public void setRESERVER1(String RESERVER1) {
            this.RESERVER1 = RESERVER1;
        }

        public String getRESERVER2() {
            return RESERVER2;
        }

        public void setRESERVER2(String RESERVER2) {
            this.RESERVER2 = RESERVER2;
        }

        public String getRESERVER3() {
            return RESERVER3;
        }

        public void setRESERVER3(String RESERVER3) {
            this.RESERVER3 = RESERVER3;
        }

        public String getRESERVER4() {
            return RESERVER4;
        }

        public void setRESERVER4(String RESERVER4) {
            this.RESERVER4 = RESERVER4;
        }

        public String getRESERVER5() {
            return RESERVER5;
        }

        public void setRESERVER5(String RESERVER5) {
            this.RESERVER5 = RESERVER5;
        }

        public String getRESERVER6() {
            return RESERVER6;
        }

        public void setRESERVER6(String RESERVER6) {
            this.RESERVER6 = RESERVER6;
        }

        public String getRESERVER7() {
            return RESERVER7;
        }

        public void setRESERVER7(String RESERVER7) {
            this.RESERVER7 = RESERVER7;
        }

        public String getRESERVER8() {
            return RESERVER8;
        }

        public void setRESERVER8(String RESERVER8) {
            this.RESERVER8 = RESERVER8;
        }
    }

    public static class SCENETOOLMARKBean {
        /**
         * ID : 14319022b331493fbd4663b75a86bf91
         * SERIAL_NO : 1
         * CREATE_USER_ID : 2f869bcd610c539301610c6d8f4b0002
         * INVESTIGATION_ID : 394be5a5fee343a49481dd1a64f5eceb
         * TOOLMARK_PHOTO_ID : 4707487ecc6146e2846db969e2e9bd05
         * PRINT_TYPE : 1102
         * TOOL_JUDGEMENT : []
         * DESCRIPTION : []
         * LEFT_POSITION : 出差
         * COLLECTION_MODE : A01
         * COLLECTED_BY : 2f869bcd60b646ab0160b649f223000b,2f869bcd60b646ab0160b64a63db000c
         * COLLECTED_BY_NAME : 许洋,测试
         * COLLECTED_DATE : 20190318175057
         * CRIMINAL_FLAG : []
         * STORAGE_FLAG : []
         * UTILIZATION : []
         * PRINT_FLAG : []
         * SECRECY : 1
         * DELETE_FLAG : 0
         * CREATE_USER : 许洋
         * CREATE_DATETIME : 20190319113601
         * UPDATE_USER : []
         * UPDATE_DATETIME : 20190319113601
         * RESERVER1 : []
         * RESERVER2 : []
         * RESERVER3 : []
         * RESERVER4 : []
         * RESERVER5 : []
         * RESERVER6 : []
         * RESERVER7 : []
         * RESERVER8 : []
         */

        private String ID;
        private String SERIAL_NO;//序号
        private String CREATE_USER_ID;//创建人id
        private String INVESTIGATION_ID;//现场信息ID
        private String TOOLMARK_PHOTO_ID;//现场工具痕迹照片ID
        private String PRINT_TYPE;//工痕种类
        private String LEFT_POSITION;//提取方法
        private String COLLECTION_MODE;//提取人
        private String COLLECTED_BY;//提取人姓名
        private String COLLECTED_BY_NAME;//工具推断
        private String COLLECTED_DATE;//提取日期
        private String SECRECY;//密级
        private String DELETE_FLAG;//删除标志
        private String CREATE_USER;//记录创建人
        private String CREATE_DATETIME;//记录创建时间
        private String UPDATE_DATETIME;//记录更新时间
        private String TOOL_JUDGEMENT;//特征描述
        private String DESCRIPTION;//遗留部位
        private String CRIMINAL_FLAG;//可靠程度
        private String STORAGE_FLAG;//是否存入相关系统
        private String UTILIZATION;//利用情况
        private String PRINT_FLAG;//是否列入现场提取登记表
        private String UPDATE_USER;//记录更新人
        private String RESERVER1;
        private String RESERVER2;
        private String RESERVER3;
        private String RESERVER4;
        private String RESERVER5;
        private String RESERVER6;
        private String RESERVER7;
        private String RESERVER8;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getSERIAL_NO() {
            return SERIAL_NO;
        }

        public void setSERIAL_NO(String SERIAL_NO) {
            this.SERIAL_NO = SERIAL_NO;
        }

        public String getCREATE_USER_ID() {
            return CREATE_USER_ID;
        }

        public void setCREATE_USER_ID(String CREATE_USER_ID) {
            this.CREATE_USER_ID = CREATE_USER_ID;
        }

        public String getINVESTIGATION_ID() {
            return INVESTIGATION_ID;
        }

        public void setINVESTIGATION_ID(String INVESTIGATION_ID) {
            this.INVESTIGATION_ID = INVESTIGATION_ID;
        }

        public String getTOOLMARK_PHOTO_ID() {
            return TOOLMARK_PHOTO_ID;
        }

        public void setTOOLMARK_PHOTO_ID(String TOOLMARK_PHOTO_ID) {
            this.TOOLMARK_PHOTO_ID = TOOLMARK_PHOTO_ID;
        }

        public String getPRINT_TYPE() {
            return PRINT_TYPE;
        }

        public void setPRINT_TYPE(String PRINT_TYPE) {
            this.PRINT_TYPE = PRINT_TYPE;
        }

        public String getLEFT_POSITION() {
            return LEFT_POSITION;
        }

        public void setLEFT_POSITION(String LEFT_POSITION) {
            this.LEFT_POSITION = LEFT_POSITION;
        }

        public String getCOLLECTION_MODE() {
            return COLLECTION_MODE;
        }

        public void setCOLLECTION_MODE(String COLLECTION_MODE) {
            this.COLLECTION_MODE = COLLECTION_MODE;
        }

        public String getCOLLECTED_BY() {
            return COLLECTED_BY;
        }

        public void setCOLLECTED_BY(String COLLECTED_BY) {
            this.COLLECTED_BY = COLLECTED_BY;
        }

        public String getCOLLECTED_BY_NAME() {
            return COLLECTED_BY_NAME;
        }

        public void setCOLLECTED_BY_NAME(String COLLECTED_BY_NAME) {
            this.COLLECTED_BY_NAME = COLLECTED_BY_NAME;
        }

        public String getCOLLECTED_DATE() {
            return COLLECTED_DATE;
        }

        public void setCOLLECTED_DATE(String COLLECTED_DATE) {
            this.COLLECTED_DATE = COLLECTED_DATE;
        }

        public String getSECRECY() {
            return SECRECY;
        }

        public void setSECRECY(String SECRECY) {
            this.SECRECY = SECRECY;
        }

        public String getDELETE_FLAG() {
            return DELETE_FLAG;
        }

        public void setDELETE_FLAG(String DELETE_FLAG) {
            this.DELETE_FLAG = DELETE_FLAG;
        }

        public String getCREATE_USER() {
            return CREATE_USER;
        }

        public void setCREATE_USER(String CREATE_USER) {
            this.CREATE_USER = CREATE_USER;
        }

        public String getCREATE_DATETIME() {
            return CREATE_DATETIME;
        }

        public void setCREATE_DATETIME(String CREATE_DATETIME) {
            this.CREATE_DATETIME = CREATE_DATETIME;
        }

        public String getUPDATE_DATETIME() {
            return UPDATE_DATETIME;
        }

        public void setUPDATE_DATETIME(String UPDATE_DATETIME) {
            this.UPDATE_DATETIME = UPDATE_DATETIME;
        }

        public String getTOOL_JUDGEMENT() {
            return TOOL_JUDGEMENT;
        }

        public void setTOOL_JUDGEMENT(String TOOL_JUDGEMENT) {
            this.TOOL_JUDGEMENT = TOOL_JUDGEMENT;
        }

        public String getDESCRIPTION() {
            return DESCRIPTION;
        }

        public void setDESCRIPTION(String DESCRIPTION) {
            this.DESCRIPTION = DESCRIPTION;
        }

        public String getCRIMINAL_FLAG() {
            return CRIMINAL_FLAG;
        }

        public void setCRIMINAL_FLAG(String CRIMINAL_FLAG) {
            this.CRIMINAL_FLAG = CRIMINAL_FLAG;
        }

        public String getSTORAGE_FLAG() {
            return STORAGE_FLAG;
        }

        public void setSTORAGE_FLAG(String STORAGE_FLAG) {
            this.STORAGE_FLAG = STORAGE_FLAG;
        }

        public String getUTILIZATION() {
            return UTILIZATION;
        }

        public void setUTILIZATION(String UTILIZATION) {
            this.UTILIZATION = UTILIZATION;
        }

        public String getPRINT_FLAG() {
            return PRINT_FLAG;
        }

        public void setPRINT_FLAG(String PRINT_FLAG) {
            this.PRINT_FLAG = PRINT_FLAG;
        }

        public String getUPDATE_USER() {
            return UPDATE_USER;
        }

        public void setUPDATE_USER(String UPDATE_USER) {
            this.UPDATE_USER = UPDATE_USER;
        }

        public String getRESERVER1() {
            return RESERVER1;
        }

        public void setRESERVER1(String RESERVER1) {
            this.RESERVER1 = RESERVER1;
        }

        public String getRESERVER2() {
            return RESERVER2;
        }

        public void setRESERVER2(String RESERVER2) {
            this.RESERVER2 = RESERVER2;
        }

        public String getRESERVER3() {
            return RESERVER3;
        }

        public void setRESERVER3(String RESERVER3) {
            this.RESERVER3 = RESERVER3;
        }

        public String getRESERVER4() {
            return RESERVER4;
        }

        public void setRESERVER4(String RESERVER4) {
            this.RESERVER4 = RESERVER4;
        }

        public String getRESERVER5() {
            return RESERVER5;
        }

        public void setRESERVER5(String RESERVER5) {
            this.RESERVER5 = RESERVER5;
        }

        public String getRESERVER6() {
            return RESERVER6;
        }

        public void setRESERVER6(String RESERVER6) {
            this.RESERVER6 = RESERVER6;
        }

        public String getRESERVER7() {
            return RESERVER7;
        }

        public void setRESERVER7(String RESERVER7) {
            this.RESERVER7 = RESERVER7;
        }

        public String getRESERVER8() {
            return RESERVER8;
        }

        public void setRESERVER8(String RESERVER8) {
            this.RESERVER8 = RESERVER8;
        }
    }

    public static class SCENEOTHEREVIDENCEBean {
        /**
         * ID : 14319022b331493fbd4663b75a86bf91
         * SERIAL_NO : 1
         * CREATE_USER_ID : 2f869bcd610c539301610c6d8f4b0002
         * INVESTIGATION_ID : 394be5a5fee343a49481dd1a64f5eceb
         * EVIDENCE_PHOTO_ID : 4707487ecc6146e2846db969e2e9bd05
         * EVIDENCE_TYPE : 1102
         * DESCRIPTION : []
         * LEFT_POSITION : 出差
         * COLLECTION_MODE : A01
         * COLLECTED_BY : 2f869bcd60b646ab0160b649f223000b,2f869bcd60b646ab0160b64a63db000c
         * COLLECTED_BY_NAME : []
         * COLLECTED_DATE : 20190318175057
         * CRIMINAL_FLAG : []
         * STORAGE_FLAG : []
         * UTILIZATION : []
         * PRINT_FLAG : []
         * SECRECY : 1
         * DELETE_FLAG : 0
         * CREATE_USER : 许洋
         * CREATE_DATETIME : 20190319113601
         * UPDATE_USER : []
         * UPDATE_DATETIME : 20190319113601
         * RESERVER1 : []
         * RESERVER2 : []
         * RESERVER3 : []
         * RESERVER4 : []
         * RESERVER5 : []
         * RESERVER6 : []
         * RESERVER7 : []
         * RESERVER8 : []
         */

        private String ID;
        private String SERIAL_NO;//序号
        private String CREATE_USER_ID;//创建人id
        private String INVESTIGATION_ID;//现场信息ID
        private String EVIDENCE_PHOTO_ID;//类型
        private String EVIDENCE_TYPE;//特征描述
        private String LEFT_POSITION;//提取方法
        private String COLLECTION_MODE;//提取人
        private String COLLECTED_BY;//提取人姓名
        private String COLLECTED_DATE;//提取日期
        private String SECRECY;//密级
        private String DELETE_FLAG;//删除标志
        private String CREATE_USER;//记录创建人
        private String CREATE_DATETIME;//记录创建时间
        private String UPDATE_DATETIME;//记录更新时间
        private String DESCRIPTION;//遗留部位
        private String COLLECTED_BY_NAME;
        private String CRIMINAL_FLAG;//可靠程度
        private String STORAGE_FLAG;//是否存入相关系统
        private String UTILIZATION;//利用情况
        private String PRINT_FLAG;//是否列入现场提取登记表
        private String UPDATE_USER;//记录更新人
        private String RESERVER1;
        private String RESERVER2;
        private String RESERVER3;
        private String RESERVER4;
        private String RESERVER5;
        private String RESERVER6;
        private String RESERVER7;
        private String RESERVER8;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getSERIAL_NO() {
            return SERIAL_NO;
        }

        public void setSERIAL_NO(String SERIAL_NO) {
            this.SERIAL_NO = SERIAL_NO;
        }

        public String getCREATE_USER_ID() {
            return CREATE_USER_ID;
        }

        public void setCREATE_USER_ID(String CREATE_USER_ID) {
            this.CREATE_USER_ID = CREATE_USER_ID;
        }

        public String getINVESTIGATION_ID() {
            return INVESTIGATION_ID;
        }

        public void setINVESTIGATION_ID(String INVESTIGATION_ID) {
            this.INVESTIGATION_ID = INVESTIGATION_ID;
        }

        public String getEVIDENCE_PHOTO_ID() {
            return EVIDENCE_PHOTO_ID;
        }

        public void setEVIDENCE_PHOTO_ID(String EVIDENCE_PHOTO_ID) {
            this.EVIDENCE_PHOTO_ID = EVIDENCE_PHOTO_ID;
        }

        public String getEVIDENCE_TYPE() {
            return EVIDENCE_TYPE;
        }

        public void setEVIDENCE_TYPE(String EVIDENCE_TYPE) {
            this.EVIDENCE_TYPE = EVIDENCE_TYPE;
        }

        public String getLEFT_POSITION() {
            return LEFT_POSITION;
        }

        public void setLEFT_POSITION(String LEFT_POSITION) {
            this.LEFT_POSITION = LEFT_POSITION;
        }

        public String getCOLLECTION_MODE() {
            return COLLECTION_MODE;
        }

        public void setCOLLECTION_MODE(String COLLECTION_MODE) {
            this.COLLECTION_MODE = COLLECTION_MODE;
        }

        public String getCOLLECTED_BY() {
            return COLLECTED_BY;
        }

        public void setCOLLECTED_BY(String COLLECTED_BY) {
            this.COLLECTED_BY = COLLECTED_BY;
        }

        public String getCOLLECTED_DATE() {
            return COLLECTED_DATE;
        }

        public void setCOLLECTED_DATE(String COLLECTED_DATE) {
            this.COLLECTED_DATE = COLLECTED_DATE;
        }

        public String getSECRECY() {
            return SECRECY;
        }

        public void setSECRECY(String SECRECY) {
            this.SECRECY = SECRECY;
        }

        public String getDELETE_FLAG() {
            return DELETE_FLAG;
        }

        public void setDELETE_FLAG(String DELETE_FLAG) {
            this.DELETE_FLAG = DELETE_FLAG;
        }

        public String getCREATE_USER() {
            return CREATE_USER;
        }

        public void setCREATE_USER(String CREATE_USER) {
            this.CREATE_USER = CREATE_USER;
        }

        public String getCREATE_DATETIME() {
            return CREATE_DATETIME;
        }

        public void setCREATE_DATETIME(String CREATE_DATETIME) {
            this.CREATE_DATETIME = CREATE_DATETIME;
        }

        public String getUPDATE_DATETIME() {
            return UPDATE_DATETIME;
        }

        public void setUPDATE_DATETIME(String UPDATE_DATETIME) {
            this.UPDATE_DATETIME = UPDATE_DATETIME;
        }

        public String getDESCRIPTION() {
            return DESCRIPTION;
        }

        public void setDESCRIPTION(String DESCRIPTION) {
            this.DESCRIPTION = DESCRIPTION;
        }

        public String getCOLLECTED_BY_NAME() {
            return COLLECTED_BY_NAME;
        }

        public void setCOLLECTED_BY_NAME(String COLLECTED_BY_NAME) {
            this.COLLECTED_BY_NAME = COLLECTED_BY_NAME;
        }

        public String getCRIMINAL_FLAG() {
            return CRIMINAL_FLAG;
        }

        public void setCRIMINAL_FLAG(String CRIMINAL_FLAG) {
            this.CRIMINAL_FLAG = CRIMINAL_FLAG;
        }

        public String getSTORAGE_FLAG() {
            return STORAGE_FLAG;
        }

        public void setSTORAGE_FLAG(String STORAGE_FLAG) {
            this.STORAGE_FLAG = STORAGE_FLAG;
        }

        public String getUTILIZATION() {
            return UTILIZATION;
        }

        public void setUTILIZATION(String UTILIZATION) {
            this.UTILIZATION = UTILIZATION;
        }

        public String getPRINT_FLAG() {
            return PRINT_FLAG;
        }

        public void setPRINT_FLAG(String PRINT_FLAG) {
            this.PRINT_FLAG = PRINT_FLAG;
        }

        public String getUPDATE_USER() {
            return UPDATE_USER;
        }

        public void setUPDATE_USER(String UPDATE_USER) {
            this.UPDATE_USER = UPDATE_USER;
        }

        public String getRESERVER1() {
            return RESERVER1;
        }

        public void setRESERVER1(String RESERVER1) {
            this.RESERVER1 = RESERVER1;
        }

        public String getRESERVER2() {
            return RESERVER2;
        }

        public void setRESERVER2(String RESERVER2) {
            this.RESERVER2 = RESERVER2;
        }

        public String getRESERVER3() {
            return RESERVER3;
        }

        public void setRESERVER3(String RESERVER3) {
            this.RESERVER3 = RESERVER3;
        }

        public String getRESERVER4() {
            return RESERVER4;
        }

        public void setRESERVER4(String RESERVER4) {
            this.RESERVER4 = RESERVER4;
        }

        public String getRESERVER5() {
            return RESERVER5;
        }

        public void setRESERVER5(String RESERVER5) {
            this.RESERVER5 = RESERVER5;
        }

        public String getRESERVER6() {
            return RESERVER6;
        }

        public void setRESERVER6(String RESERVER6) {
            this.RESERVER6 = RESERVER6;
        }

        public String getRESERVER7() {
            return RESERVER7;
        }

        public void setRESERVER7(String RESERVER7) {
            this.RESERVER7 = RESERVER7;
        }

        public String getRESERVER8() {
            return RESERVER8;
        }

        public void setRESERVER8(String RESERVER8) {
            this.RESERVER8 = RESERVER8;
        }
    }

    public static class SCENEVIDEOEVIDENCEBean {
        /**
         * ID : 14319022b331493fbd4663b75a86bf91
         * SERIAL_NO : 1
         * CREATE_USER_ID : 2f869bcd610c539301610c6d8f4b0002
         * INVESTIGATION_ID : 394be5a5fee343a49481dd1a64f5eceb
         * EVIDENCE_PHOTO_ID : 4707487ecc6146e2846db969e2e9bd05
         * EVIDENCE_TYPE : 1102
         * DESCRIPTION : []
         * LEFT_POSITION : 出差
         * COLLECTION_MODE : A01
         * COLLECTED_BY : 2f869bcd60b646ab0160b649f223000b,2f869bcd60b646ab0160b64a63db000c
         * COLLECTED_DATE : 20190318175057
         * COLLECTED_BY_NAME : []
         * CRIMINAL_FLAG : []
         * STORAGE_FLAG : []
         * UTILIZATION : []
         * PRINT_FLAG : []
         * SECRECY : 1
         * DELETE_FLAG : 0
         * CREATE_USER : 许洋
         * CREATE_DATETIME : 20190319113601
         * UPDATE_USER : []
         * UPDATE_DATETIME : 20190319113601
         * RESERVER1 : []
         * RESERVER2 : []
         * RESERVER3 : []
         * RESERVER4 : []
         * RESERVER5 : []
         * RESERVER6 : []
         * RESERVER7 : []
         * RESERVER8 : []
         */

        private String ID;
        private String SERIAL_NO;//序号
        private String CREATE_USER_ID;//创建人id
        private String INVESTIGATION_ID;//现场信息ID
        private String EVIDENCE_PHOTO_ID;//视听物证照片ID
        private String EVIDENCE_TYPE;//类型
        private String LEFT_POSITION;//遗留部位
        private String COLLECTION_MODE;//提取方法
        private String COLLECTED_BY;//提取人
        private String COLLECTED_DATE;//提取日期
        private String SECRECY;//密级
        private String DELETE_FLAG;//删除标志
        private String CREATE_USER;//记录创建人
        private String CREATE_DATETIME;//记录创建时间
        private String UPDATE_DATETIME;//记录更新时间
        private String DESCRIPTION;//特征描述
        private String COLLECTED_BY_NAME;//提取人姓名
        private String CRIMINAL_FLAG;//可靠程度
        private String STORAGE_FLAG;//是否存入相关系统
        private String UTILIZATION;//利用情况
        private String PRINT_FLAG;//是否列入现场提取登记表
        private String UPDATE_USER;//记录更新人
        private String RESERVER1;
        private String RESERVER2;
        private String RESERVER3;
        private String RESERVER4;
        private String RESERVER5;
        private String RESERVER6;
        private String RESERVER7;
        private String RESERVER8;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getSERIAL_NO() {
            return SERIAL_NO;
        }

        public void setSERIAL_NO(String SERIAL_NO) {
            this.SERIAL_NO = SERIAL_NO;
        }

        public String getCREATE_USER_ID() {
            return CREATE_USER_ID;
        }

        public void setCREATE_USER_ID(String CREATE_USER_ID) {
            this.CREATE_USER_ID = CREATE_USER_ID;
        }

        public String getINVESTIGATION_ID() {
            return INVESTIGATION_ID;
        }

        public void setINVESTIGATION_ID(String INVESTIGATION_ID) {
            this.INVESTIGATION_ID = INVESTIGATION_ID;
        }

        public String getEVIDENCE_PHOTO_ID() {
            return EVIDENCE_PHOTO_ID;
        }

        public void setEVIDENCE_PHOTO_ID(String EVIDENCE_PHOTO_ID) {
            this.EVIDENCE_PHOTO_ID = EVIDENCE_PHOTO_ID;
        }

        public String getEVIDENCE_TYPE() {
            return EVIDENCE_TYPE;
        }

        public void setEVIDENCE_TYPE(String EVIDENCE_TYPE) {
            this.EVIDENCE_TYPE = EVIDENCE_TYPE;
        }

        public String getLEFT_POSITION() {
            return LEFT_POSITION;
        }

        public void setLEFT_POSITION(String LEFT_POSITION) {
            this.LEFT_POSITION = LEFT_POSITION;
        }

        public String getCOLLECTION_MODE() {
            return COLLECTION_MODE;
        }

        public void setCOLLECTION_MODE(String COLLECTION_MODE) {
            this.COLLECTION_MODE = COLLECTION_MODE;
        }

        public String getCOLLECTED_BY() {
            return COLLECTED_BY;
        }

        public void setCOLLECTED_BY(String COLLECTED_BY) {
            this.COLLECTED_BY = COLLECTED_BY;
        }

        public String getCOLLECTED_DATE() {
            return COLLECTED_DATE;
        }

        public void setCOLLECTED_DATE(String COLLECTED_DATE) {
            this.COLLECTED_DATE = COLLECTED_DATE;
        }

        public String getSECRECY() {
            return SECRECY;
        }

        public void setSECRECY(String SECRECY) {
            this.SECRECY = SECRECY;
        }

        public String getDELETE_FLAG() {
            return DELETE_FLAG;
        }

        public void setDELETE_FLAG(String DELETE_FLAG) {
            this.DELETE_FLAG = DELETE_FLAG;
        }

        public String getCREATE_USER() {
            return CREATE_USER;
        }

        public void setCREATE_USER(String CREATE_USER) {
            this.CREATE_USER = CREATE_USER;
        }

        public String getCREATE_DATETIME() {
            return CREATE_DATETIME;
        }

        public void setCREATE_DATETIME(String CREATE_DATETIME) {
            this.CREATE_DATETIME = CREATE_DATETIME;
        }

        public String getUPDATE_DATETIME() {
            return UPDATE_DATETIME;
        }

        public void setUPDATE_DATETIME(String UPDATE_DATETIME) {
            this.UPDATE_DATETIME = UPDATE_DATETIME;
        }

        public String getDESCRIPTION() {
            return DESCRIPTION;
        }

        public void setDESCRIPTION(String DESCRIPTION) {
            this.DESCRIPTION = DESCRIPTION;
        }

        public String getCOLLECTED_BY_NAME() {
            return COLLECTED_BY_NAME;
        }

        public void setCOLLECTED_BY_NAME(String COLLECTED_BY_NAME) {
            this.COLLECTED_BY_NAME = COLLECTED_BY_NAME;
        }

        public String getCRIMINAL_FLAG() {
            return CRIMINAL_FLAG;
        }

        public void setCRIMINAL_FLAG(String CRIMINAL_FLAG) {
            this.CRIMINAL_FLAG = CRIMINAL_FLAG;
        }

        public String getSTORAGE_FLAG() {
            return STORAGE_FLAG;
        }

        public void setSTORAGE_FLAG(String STORAGE_FLAG) {
            this.STORAGE_FLAG = STORAGE_FLAG;
        }

        public String getUTILIZATION() {
            return UTILIZATION;
        }

        public void setUTILIZATION(String UTILIZATION) {
            this.UTILIZATION = UTILIZATION;
        }

        public String getPRINT_FLAG() {
            return PRINT_FLAG;
        }

        public void setPRINT_FLAG(String PRINT_FLAG) {
            this.PRINT_FLAG = PRINT_FLAG;
        }

        public String getUPDATE_USER() {
            return UPDATE_USER;
        }

        public void setUPDATE_USER(String UPDATE_USER) {
            this.UPDATE_USER = UPDATE_USER;
        }

        public String getRESERVER1() {
            return RESERVER1;
        }

        public void setRESERVER1(String RESERVER1) {
            this.RESERVER1 = RESERVER1;
        }

        public String getRESERVER2() {
            return RESERVER2;
        }

        public void setRESERVER2(String RESERVER2) {
            this.RESERVER2 = RESERVER2;
        }

        public String getRESERVER3() {
            return RESERVER3;
        }

        public void setRESERVER3(String RESERVER3) {
            this.RESERVER3 = RESERVER3;
        }

        public String getRESERVER4() {
            return RESERVER4;
        }

        public void setRESERVER4(String RESERVER4) {
            this.RESERVER4 = RESERVER4;
        }

        public String getRESERVER5() {
            return RESERVER5;
        }

        public void setRESERVER5(String RESERVER5) {
            this.RESERVER5 = RESERVER5;
        }

        public String getRESERVER6() {
            return RESERVER6;
        }

        public void setRESERVER6(String RESERVER6) {
            this.RESERVER6 = RESERVER6;
        }

        public String getRESERVER7() {
            return RESERVER7;
        }

        public void setRESERVER7(String RESERVER7) {
            this.RESERVER7 = RESERVER7;
        }

        public String getRESERVER8() {
            return RESERVER8;
        }

        public void setRESERVER8(String RESERVER8) {
            this.RESERVER8 = RESERVER8;
        }
    }

    public static class KCTCASEINFOBean {
        /**
         * ID : 862f3ca3bea644d294678a14429c8c58
         * CASE_ID : []
         * KCT_UUID : 686315bdc1e04627b4e4448d3cdcf9b5
         * CASE_START_TIME : 20190318153433
         * CASE_END_TIME : 20190318163433
         * CASE_LON : 0.0E
         * CASE_LAT : 0.0N
         * WITNESS_INFO : []
         * REMARK : []
         * CREATE_USER : 许洋
         * CREATE_DATETIME : 20190319113603
         * UPDATE_USER : []
         * UPDATE_DATETIME : []
         * RESERVER1 : []
         * RESERVER2 : []
         * RESERVER3 : []
         * RESERVER4 : []
         * RESERVER5 : []
         * RESERVER6 : []
         * RESERVER7 : []
         * RESERVER8 : []
         * GPS_NAME : []
         * CASE_NAME : []
         * INVESTIGATION_ID : 394be5a5fee343a49481dd1a64f5eceb
         */

        private String ID;
        private String KCT_UUID;
        private String CASE_START_TIME;
        private String CASE_END_TIME;
        private String CASE_LON;
        private String CASE_LAT;
        private String CREATE_USER;
        private String CREATE_DATETIME;
        private String INVESTIGATION_ID;
        private String CASE_ID;
        private String WITNESS_INFO;
        private String REMARK;
        private String UPDATE_USER;
        private String UPDATE_DATETIME;
        private String RESERVER1;
        private String RESERVER2;
        private String RESERVER3;
        private String RESERVER4;
        private String RESERVER5;
        private String RESERVER6;
        private String RESERVER7;
        private String RESERVER8;
        private String GPS_NAME;
        private String CASE_NAME;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getKCT_UUID() {
            return KCT_UUID;
        }

        public void setKCT_UUID(String KCT_UUID) {
            this.KCT_UUID = KCT_UUID;
        }

        public String getCASE_START_TIME() {
            return CASE_START_TIME;
        }

        public void setCASE_START_TIME(String CASE_START_TIME) {
            this.CASE_START_TIME = CASE_START_TIME;
        }

        public String getCASE_END_TIME() {
            return CASE_END_TIME;
        }

        public void setCASE_END_TIME(String CASE_END_TIME) {
            this.CASE_END_TIME = CASE_END_TIME;
        }

        public String getCASE_LON() {
            return CASE_LON;
        }

        public void setCASE_LON(String CASE_LON) {
            this.CASE_LON = CASE_LON;
        }

        public String getCASE_LAT() {
            return CASE_LAT;
        }

        public void setCASE_LAT(String CASE_LAT) {
            this.CASE_LAT = CASE_LAT;
        }

        public String getCREATE_USER() {
            return CREATE_USER;
        }

        public void setCREATE_USER(String CREATE_USER) {
            this.CREATE_USER = CREATE_USER;
        }

        public String getCREATE_DATETIME() {
            return CREATE_DATETIME;
        }

        public void setCREATE_DATETIME(String CREATE_DATETIME) {
            this.CREATE_DATETIME = CREATE_DATETIME;
        }

        public String getINVESTIGATION_ID() {
            return INVESTIGATION_ID;
        }

        public void setINVESTIGATION_ID(String INVESTIGATION_ID) {
            this.INVESTIGATION_ID = INVESTIGATION_ID;
        }

        public String getCASE_ID() {
            return CASE_ID;
        }

        public void setCASE_ID(String CASE_ID) {
            this.CASE_ID = CASE_ID;
        }

        public String getWITNESS_INFO() {
            return WITNESS_INFO;
        }

        public void setWITNESS_INFO(String WITNESS_INFO) {
            this.WITNESS_INFO = WITNESS_INFO;
        }

        public String getREMARK() {
            return REMARK;
        }

        public void setREMARK(String REMARK) {
            this.REMARK = REMARK;
        }

        public String getUPDATE_USER() {
            return UPDATE_USER;
        }

        public void setUPDATE_USER(String UPDATE_USER) {
            this.UPDATE_USER = UPDATE_USER;
        }

        public String getUPDATE_DATETIME() {
            return UPDATE_DATETIME;
        }

        public void setUPDATE_DATETIME(String UPDATE_DATETIME) {
            this.UPDATE_DATETIME = UPDATE_DATETIME;
        }

        public String getRESERVER1() {
            return RESERVER1;
        }

        public void setRESERVER1(String RESERVER1) {
            this.RESERVER1 = RESERVER1;
        }

        public String getRESERVER2() {
            return RESERVER2;
        }

        public void setRESERVER2(String RESERVER2) {
            this.RESERVER2 = RESERVER2;
        }

        public String getRESERVER3() {
            return RESERVER3;
        }

        public void setRESERVER3(String RESERVER3) {
            this.RESERVER3 = RESERVER3;
        }

        public String getRESERVER4() {
            return RESERVER4;
        }

        public void setRESERVER4(String RESERVER4) {
            this.RESERVER4 = RESERVER4;
        }

        public String getRESERVER5() {
            return RESERVER5;
        }

        public void setRESERVER5(String RESERVER5) {
            this.RESERVER5 = RESERVER5;
        }

        public String getRESERVER6() {
            return RESERVER6;
        }

        public void setRESERVER6(String RESERVER6) {
            this.RESERVER6 = RESERVER6;
        }

        public String getRESERVER7() {
            return RESERVER7;
        }

        public void setRESERVER7(String RESERVER7) {
            this.RESERVER7 = RESERVER7;
        }

        public String getRESERVER8() {
            return RESERVER8;
        }

        public void setRESERVER8(String RESERVER8) {
            this.RESERVER8 = RESERVER8;
        }

        public String getGPS_NAME() {
            return GPS_NAME;
        }

        public void setGPS_NAME(String GPS_NAME) {
            this.GPS_NAME = GPS_NAME;
        }

        public String getCASE_NAME() {
            return CASE_NAME;
        }

        public void setCASE_NAME(String CASE_NAME) {
            this.CASE_NAME = CASE_NAME;
        }
    }

    public static class KCTLOCALEDATABean {
        /**
         * ID : 2cc8c787dee24deeb6577eedde69ba9b
         * LOCALE_NAME : 谢谢
         * COL_STARTTIME : 20190318173433
         * COL_ENDTIME : 20190318183433
         * CREATE_USER : 许洋
         * CREATE_DATETIME : 20190319113603
         * UPDATE_USER : []
         * UPDATE_DATETIME : 20190319113603
         * RESERVER1 : []
         * RESERVER2 : []
         * RESERVER3 : []
         * RESERVER4 : []
         * RESERVER5 : []
         * RESERVER6 : []
         * RESERVER7 : []
         * RESERVER8 : []
         * DATA_TYPE : 1
         * CASE_INFO_ID : 862f3ca3bea644d294678a14429c8c58
         */

        private String ID;
        private String LOCALE_NAME;
        private String COL_STARTTIME;
        private String COL_ENDTIME;
        private String CREATE_USER;
        private String CREATE_DATETIME;
        private String UPDATE_DATETIME;
        private String DATA_TYPE;
        private String CASE_INFO_ID;
        private String UPDATE_USER;
        private String RESERVER1;
        private String RESERVER2;
        private String RESERVER3;
        private String RESERVER4;
        private String RESERVER5;
        private String RESERVER6;
        private String RESERVER7;
        private String RESERVER8;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getLOCALE_NAME() {
            return LOCALE_NAME;
        }

        public void setLOCALE_NAME(String LOCALE_NAME) {
            this.LOCALE_NAME = LOCALE_NAME;
        }

        public String getCOL_STARTTIME() {
            return COL_STARTTIME;
        }

        public void setCOL_STARTTIME(String COL_STARTTIME) {
            this.COL_STARTTIME = COL_STARTTIME;
        }

        public String getCOL_ENDTIME() {
            return COL_ENDTIME;
        }

        public void setCOL_ENDTIME(String COL_ENDTIME) {
            this.COL_ENDTIME = COL_ENDTIME;
        }

        public String getCREATE_USER() {
            return CREATE_USER;
        }

        public void setCREATE_USER(String CREATE_USER) {
            this.CREATE_USER = CREATE_USER;
        }

        public String getCREATE_DATETIME() {
            return CREATE_DATETIME;
        }

        public void setCREATE_DATETIME(String CREATE_DATETIME) {
            this.CREATE_DATETIME = CREATE_DATETIME;
        }

        public String getUPDATE_DATETIME() {
            return UPDATE_DATETIME;
        }

        public void setUPDATE_DATETIME(String UPDATE_DATETIME) {
            this.UPDATE_DATETIME = UPDATE_DATETIME;
        }

        public String getDATA_TYPE() {
            return DATA_TYPE;
        }

        public void setDATA_TYPE(String DATA_TYPE) {
            this.DATA_TYPE = DATA_TYPE;
        }

        public String getCASE_INFO_ID() {
            return CASE_INFO_ID;
        }

        public void setCASE_INFO_ID(String CASE_INFO_ID) {
            this.CASE_INFO_ID = CASE_INFO_ID;
        }

        public String getUPDATE_USER() {
            return UPDATE_USER;
        }

        public void setUPDATE_USER(String UPDATE_USER) {
            this.UPDATE_USER = UPDATE_USER;
        }

        public String getRESERVER1() {
            return RESERVER1;
        }

        public void setRESERVER1(String RESERVER1) {
            this.RESERVER1 = RESERVER1;
        }

        public String getRESERVER2() {
            return RESERVER2;
        }

        public void setRESERVER2(String RESERVER2) {
            this.RESERVER2 = RESERVER2;
        }

        public String getRESERVER3() {
            return RESERVER3;
        }

        public void setRESERVER3(String RESERVER3) {
            this.RESERVER3 = RESERVER3;
        }

        public String getRESERVER4() {
            return RESERVER4;
        }

        public void setRESERVER4(String RESERVER4) {
            this.RESERVER4 = RESERVER4;
        }

        public String getRESERVER5() {
            return RESERVER5;
        }

        public void setRESERVER5(String RESERVER5) {
            this.RESERVER5 = RESERVER5;
        }

        public String getRESERVER6() {
            return RESERVER6;
        }

        public void setRESERVER6(String RESERVER6) {
            this.RESERVER6 = RESERVER6;
        }

        public String getRESERVER7() {
            return RESERVER7;
        }

        public void setRESERVER7(String RESERVER7) {
            this.RESERVER7 = RESERVER7;
        }

        public String getRESERVER8() {
            return RESERVER8;
        }

        public void setRESERVER8(String RESERVER8) {
            this.RESERVER8 = RESERVER8;
        }
    }

    public static class KCTBASESTATIONDATABean {
        /**
         * ID : d21e94312cde4bdc9a5369fca75c0ae7
         * BS_TYPE : FDD_CT
         * IFACTIVE : ACTIVE
         * REG_ZONE : []
         * SID : []
         * NID : []
         * BASE_ID : []
         * CDMA_CH : []
         * PN : []
         * STRENGTH : []
         * MCC_MNC : 460-11
         * LAC : 34684
         * CELL_ID : 142376198
         * BCCH : []
         * BSIC : []
         * SYS_BAND : []
         * RESERVER1 : []
         * RESERVER2 : []
         * RESERVER3 : []
         * RESERVER4 : []
         * RESERVER5 : []
         * RESERVER6 : []
         * RESERVER7 : []
         * RESERVER8 : []
         * RESERVER9 : []
         * RESERVER10 : []
         * LON : []
         * LAT : []
         * LOCALE_DATA_ID : 2cc8c787dee24deeb6577eedde69ba9b
         * COL_TIME : 20190318175051
         * ERFCN : []
         * PCI : 368
         * BAND : []
         * CELL : 142376198
         * EARFCN : []
         * RSRP : 41
         * RSRQ : []
         * RSSI : 41
         * RAC : []
         * RNCID : []
         * ENBID : []
         * PHYCELLID : []
         * CELLPARAMID : []
         * TAC : 34684
         */

        private String ID;
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
        private String RESERVER1;
        private String RESERVER2;
        private String RESERVER3;
        private String RESERVER4;
        private String RESERVER5;
        private String RESERVER6;
        private String RESERVER7;
        private String RESERVER8;
        private String RESERVER9;
        private String RESERVER10;
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

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
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

        public String getCDMA_CH() {
            return CDMA_CH;
        }

        public void setCDMA_CH(String CDMA_CH) {
            this.CDMA_CH = CDMA_CH;
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

        public String getRESERVER1() {
            return RESERVER1;
        }

        public void setRESERVER1(String RESERVER1) {
            this.RESERVER1 = RESERVER1;
        }

        public String getRESERVER2() {
            return RESERVER2;
        }

        public void setRESERVER2(String RESERVER2) {
            this.RESERVER2 = RESERVER2;
        }

        public String getRESERVER3() {
            return RESERVER3;
        }

        public void setRESERVER3(String RESERVER3) {
            this.RESERVER3 = RESERVER3;
        }

        public String getRESERVER4() {
            return RESERVER4;
        }

        public void setRESERVER4(String RESERVER4) {
            this.RESERVER4 = RESERVER4;
        }

        public String getRESERVER5() {
            return RESERVER5;
        }

        public void setRESERVER5(String RESERVER5) {
            this.RESERVER5 = RESERVER5;
        }

        public String getRESERVER6() {
            return RESERVER6;
        }

        public void setRESERVER6(String RESERVER6) {
            this.RESERVER6 = RESERVER6;
        }

        public String getRESERVER7() {
            return RESERVER7;
        }

        public void setRESERVER7(String RESERVER7) {
            this.RESERVER7 = RESERVER7;
        }

        public String getRESERVER8() {
            return RESERVER8;
        }

        public void setRESERVER8(String RESERVER8) {
            this.RESERVER8 = RESERVER8;
        }

        public String getRESERVER9() {
            return RESERVER9;
        }

        public void setRESERVER9(String RESERVER9) {
            this.RESERVER9 = RESERVER9;
        }

        public String getRESERVER10() {
            return RESERVER10;
        }

        public void setRESERVER10(String RESERVER10) {
            this.RESERVER10 = RESERVER10;
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
    }

    public static class COMMONATTACHMENTBean {
        /**
         * ID : b53dee31692346aa8367c5471425ce98
         * CONTENT : []
         * FILE_NAME : data.xml
         * CATEGORY : 01
         * TYPE : xml
         * SECRECY : 1
         * DELETE_FLAG : 0
         * CREATE_USER : 许洋
         * CREATE_DATETIME : 20190319113603
         * UPDATE_USER : []
         * UPDATE_DATETIME : 20190319113603
         * RESERVER1 : 394be5a5fee343a49481dd1a64f5eceb
         * RESERVER2 : 862f3ca3bea644d294678a14429c8c58
         * RESERVER3 : 1
         * RESERVER4 : []
         * RESERVER5 : []
         * RESERVER6 : []
         * RESERVER7 : []
         * RESERVER8 : []
         */

        private String ID;
        private String FILE_NAME;//附件文件名
        private String CATEGORY;//附件分类
        private String TYPE;//附件类型
        private String SECRECY;//密级
        private String DELETE_FLAG;//删除标志
        private String CREATE_USER;//记录创建人
        private String CREATE_DATETIME;//记录创建时间
        private String UPDATE_DATETIME;//记录更新时间
        private String RESERVER1;
        private String RESERVER2;
        private String RESERVER3;
        private String CONTENT;//附件内容
        private String UPDATE_USER;//记录更新人
        private String RESERVER4;
        private String RESERVER5;
        private String RESERVER6;
        private String RESERVER7;
        private String RESERVER8;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getFILE_NAME() {
            return FILE_NAME;
        }

        public void setFILE_NAME(String FILE_NAME) {
            this.FILE_NAME = FILE_NAME;
        }

        public String getCATEGORY() {
            return CATEGORY;
        }

        public void setCATEGORY(String CATEGORY) {
            this.CATEGORY = CATEGORY;
        }

        public String getTYPE() {
            return TYPE;
        }

        public void setTYPE(String TYPE) {
            this.TYPE = TYPE;
        }

        public String getSECRECY() {
            return SECRECY;
        }

        public void setSECRECY(String SECRECY) {
            this.SECRECY = SECRECY;
        }

        public String getDELETE_FLAG() {
            return DELETE_FLAG;
        }

        public void setDELETE_FLAG(String DELETE_FLAG) {
            this.DELETE_FLAG = DELETE_FLAG;
        }

        public String getCREATE_USER() {
            return CREATE_USER;
        }

        public void setCREATE_USER(String CREATE_USER) {
            this.CREATE_USER = CREATE_USER;
        }

        public String getCREATE_DATETIME() {
            return CREATE_DATETIME;
        }

        public void setCREATE_DATETIME(String CREATE_DATETIME) {
            this.CREATE_DATETIME = CREATE_DATETIME;
        }

        public String getUPDATE_DATETIME() {
            return UPDATE_DATETIME;
        }

        public void setUPDATE_DATETIME(String UPDATE_DATETIME) {
            this.UPDATE_DATETIME = UPDATE_DATETIME;
        }

        public String getRESERVER1() {
            return RESERVER1;
        }

        public void setRESERVER1(String RESERVER1) {
            this.RESERVER1 = RESERVER1;
        }

        public String getRESERVER2() {
            return RESERVER2;
        }

        public void setRESERVER2(String RESERVER2) {
            this.RESERVER2 = RESERVER2;
        }

        public String getRESERVER3() {
            return RESERVER3;
        }

        public void setRESERVER3(String RESERVER3) {
            this.RESERVER3 = RESERVER3;
        }

        public String getCONTENT() {
            return CONTENT;
        }

        public void setCONTENT(String CONTENT) {
            this.CONTENT = CONTENT;
        }

        public String getUPDATE_USER() {
            return UPDATE_USER;
        }

        public void setUPDATE_USER(String UPDATE_USER) {
            this.UPDATE_USER = UPDATE_USER;
        }

        public String getRESERVER4() {
            return RESERVER4;
        }

        public void setRESERVER4(String RESERVER4) {
            this.RESERVER4 = RESERVER4;
        }

        public String getRESERVER5() {
            return RESERVER5;
        }

        public void setRESERVER5(String RESERVER5) {
            this.RESERVER5 = RESERVER5;
        }

        public String getRESERVER6() {
            return RESERVER6;
        }

        public void setRESERVER6(String RESERVER6) {
            this.RESERVER6 = RESERVER6;
        }

        public String getRESERVER7() {
            return RESERVER7;
        }

        public void setRESERVER7(String RESERVER7) {
            this.RESERVER7 = RESERVER7;
        }

        public String getRESERVER8() {
            return RESERVER8;
        }

        public void setRESERVER8(String RESERVER8) {
            this.RESERVER8 = RESERVER8;
        }
    }

    public static class SCENEPHOTOBean {
        /**
         * ID : 84e4a52c53254edf8e6f19e388eaf532
         * SERIAL_NO : 4
         * INVESTIGATION_ID : 394be5a5fee343a49481dd1a64f5eceb
         * CREATE_USER_ID : 2f869bcd610c539301610c6d8f4b0002
         * PHOTO_ID : e70eab78483b4572b214f5b7b152d69f
         * PHOTO_NAME : /MONITORING_PHOTO_20190318_17510p.jpg
         * PHOTO_TYPE : A
         * DESCRIPTION : 监控
         * SECRECY : 1
         * DELETE_FLAG : 0
         * CREATE_USER : 许洋
         * CREATE_DATETIME : 20190319113602
         * UPDATE_USER : []
         * UPDATE_DATETIME : 20190319113602
         * RESERVER1 : []
         * RESERVER2 : []
         * RESERVER3 : []
         * RESERVER4 : []
         * RESERVER5 : []
         * RESERVER6 : []
         * RESERVER7 : []
         * RESERVER8 : []
         */

        private String ID;
        private String SERIAL_NO;//序号
        private String INVESTIGATION_ID;//现场信息ID
        private String CREATE_USER_ID;//创建用户id
        private String PHOTO_ID;//现场照片附件ID
        private String PHOTO_NAME;//现场照片名称
        private String PHOTO_TYPE;//现场照片类型
        private String DESCRIPTION;//现场照片说明
        private String SECRECY;//密级
        private String DELETE_FLAG;//删除标志
        private String CREATE_USER;//记录创建人
        private String CREATE_DATETIME;//记录创建时间
        private String UPDATE_DATETIME;//记录更新时间
        private String UPDATE_USER;//记录更新人
        private String RESERVER1;//保留字段
        private String RESERVER2;
        private String RESERVER3;
        private String RESERVER4;
        private String RESERVER5;
        private String RESERVER6;
        private String RESERVER7;
        private String RESERVER8;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getSERIAL_NO() {
            return SERIAL_NO;
        }

        public void setSERIAL_NO(String SERIAL_NO) {
            this.SERIAL_NO = SERIAL_NO;
        }

        public String getINVESTIGATION_ID() {
            return INVESTIGATION_ID;
        }

        public void setINVESTIGATION_ID(String INVESTIGATION_ID) {
            this.INVESTIGATION_ID = INVESTIGATION_ID;
        }

        public String getCREATE_USER_ID() {
            return CREATE_USER_ID;
        }

        public void setCREATE_USER_ID(String CREATE_USER_ID) {
            this.CREATE_USER_ID = CREATE_USER_ID;
        }

        public String getPHOTO_ID() {
            return PHOTO_ID;
        }

        public void setPHOTO_ID(String PHOTO_ID) {
            this.PHOTO_ID = PHOTO_ID;
        }

        public String getPHOTO_NAME() {
            return PHOTO_NAME;
        }

        public void setPHOTO_NAME(String PHOTO_NAME) {
            this.PHOTO_NAME = PHOTO_NAME;
        }

        public String getPHOTO_TYPE() {
            return PHOTO_TYPE;
        }

        public void setPHOTO_TYPE(String PHOTO_TYPE) {
            this.PHOTO_TYPE = PHOTO_TYPE;
        }

        public String getDESCRIPTION() {
            return DESCRIPTION;
        }

        public void setDESCRIPTION(String DESCRIPTION) {
            this.DESCRIPTION = DESCRIPTION;
        }

        public String getSECRECY() {
            return SECRECY;
        }

        public void setSECRECY(String SECRECY) {
            this.SECRECY = SECRECY;
        }

        public String getDELETE_FLAG() {
            return DELETE_FLAG;
        }

        public void setDELETE_FLAG(String DELETE_FLAG) {
            this.DELETE_FLAG = DELETE_FLAG;
        }

        public String getCREATE_USER() {
            return CREATE_USER;
        }

        public void setCREATE_USER(String CREATE_USER) {
            this.CREATE_USER = CREATE_USER;
        }

        public String getCREATE_DATETIME() {
            return CREATE_DATETIME;
        }

        public void setCREATE_DATETIME(String CREATE_DATETIME) {
            this.CREATE_DATETIME = CREATE_DATETIME;
        }

        public String getUPDATE_DATETIME() {
            return UPDATE_DATETIME;
        }

        public void setUPDATE_DATETIME(String UPDATE_DATETIME) {
            this.UPDATE_DATETIME = UPDATE_DATETIME;
        }

        public String getUPDATE_USER() {
            return UPDATE_USER;
        }

        public void setUPDATE_USER(String UPDATE_USER) {
            this.UPDATE_USER = UPDATE_USER;
        }

        public String getRESERVER1() {
            return RESERVER1;
        }

        public void setRESERVER1(String RESERVER1) {
            this.RESERVER1 = RESERVER1;
        }

        public String getRESERVER2() {
            return RESERVER2;
        }

        public void setRESERVER2(String RESERVER2) {
            this.RESERVER2 = RESERVER2;
        }

        public String getRESERVER3() {
            return RESERVER3;
        }

        public void setRESERVER3(String RESERVER3) {
            this.RESERVER3 = RESERVER3;
        }

        public String getRESERVER4() {
            return RESERVER4;
        }

        public void setRESERVER4(String RESERVER4) {
            this.RESERVER4 = RESERVER4;
        }

        public String getRESERVER5() {
            return RESERVER5;
        }

        public void setRESERVER5(String RESERVER5) {
            this.RESERVER5 = RESERVER5;
        }

        public String getRESERVER6() {
            return RESERVER6;
        }

        public void setRESERVER6(String RESERVER6) {
            this.RESERVER6 = RESERVER6;
        }

        public String getRESERVER7() {
            return RESERVER7;
        }

        public void setRESERVER7(String RESERVER7) {
            this.RESERVER7 = RESERVER7;
        }

        public String getRESERVER8() {
            return RESERVER8;
        }

        public void setRESERVER8(String RESERVER8) {
            this.RESERVER8 = RESERVER8;
        }
    }

    public static class sceneWifiInfo {
        private String ID;
        private String INVESTIGATION_ID;//现场id
        private String CREATE_USER;//创建人
        private String CREATE_DATETIME;//创建时间
        private String COLLECTION_DATETIME;//采集时间
        private String LINK_SPEED;//连接速率
        private String SSID;//wifi名称
        private String BSSID;//wifi识别id
        private String HIDDEN_SSID;//是否隐藏wifi名
        private String MACADDRESS;//mac地址
        private String NETWORKLD;//当前正在工作的网络连接id
        private String RSSI;//信号强度
        private String SUPPLICANTSTATE;//和当前wifi的协议连接状态（未连接，不可用，完成等enum对象）
        private String DETAILEDSTATEOF;//详细的状态信息

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getINVESTIGATION_ID() {
            return INVESTIGATION_ID;
        }

        public void setINVESTIGATION_ID(String INVESTIGATION_ID) {
            this.INVESTIGATION_ID = INVESTIGATION_ID;
        }

        public String getCREATE_USER() {
            return CREATE_USER;
        }

        public void setCREATE_USER(String CREATE_USER) {
            this.CREATE_USER = CREATE_USER;
        }

        public String getCREATE_DATETIME() {
            return CREATE_DATETIME;
        }

        public void setCREATE_DATETIME(String CREATE_DATETIME) {
            this.CREATE_DATETIME = CREATE_DATETIME;
        }

        public String getCOLLECTION_DATETIME() {
            return COLLECTION_DATETIME;
        }

        public void setCOLLECTION_DATETIME(String COLLECTION_DATETIME) {
            this.COLLECTION_DATETIME = COLLECTION_DATETIME;
        }

        public String getLINK_SPEED() {
            return LINK_SPEED;
        }

        public void setLINK_SPEED(String LINK_SPEED) {
            this.LINK_SPEED = LINK_SPEED;
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

        public String getHIDDEN_SSID() {
            return HIDDEN_SSID;
        }

        public void setHIDDEN_SSID(String HIDDEN_SSID) {
            this.HIDDEN_SSID = HIDDEN_SSID;
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

    /**
     * 提取物品
     */
    public static class extracts{
        private String ID;
        private String INVESTIGATION_ID;
        private String CODE;//二维码
        private String COLLECTED_NAME;//提取人
        private String COLLECTED_IDS;//提取人id
        private String MATERIAL_NAME;//物证名称
        private String COLLECTED_POSITION;//提取部位
        private String COLLECTED_METHOD;//提取方法
        private String AMOUNT;//提取数量
        private String COLLECTED_DATE;//提取时间
        private String REMARK;//备注

        private String CREATE_USER;//创建人
        private String CREATE_DATETIME;//创建时间

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getINVESTIGATION_ID() {
            return INVESTIGATION_ID;
        }

        public void setINVESTIGATION_ID(String INVESTIGATION_ID) {
            this.INVESTIGATION_ID = INVESTIGATION_ID;
        }

        public String getCODE() {
            return CODE;
        }

        public void setCODE(String CODE) {
            this.CODE = CODE;
        }

        public String getCOLLECTED_NAME() {
            return COLLECTED_NAME;
        }

        public void setCOLLECTED_NAME(String COLLECTED_NAME) {
            this.COLLECTED_NAME = COLLECTED_NAME;
        }

        public String getCOLLECTED_IDS() {
            return COLLECTED_IDS;
        }

        public void setCOLLECTED_IDS(String COLLECTED_IDS) {
            this.COLLECTED_IDS = COLLECTED_IDS;
        }

        public String getMATERIAL_NAME() {
            return MATERIAL_NAME;
        }

        public void setMATERIAL_NAME(String MATERIAL_NAME) {
            this.MATERIAL_NAME = MATERIAL_NAME;
        }

        public String getCOLLECTED_POSITION() {
            return COLLECTED_POSITION;
        }

        public void setCOLLECTED_POSITION(String COLLECTED_POSITION) {
            this.COLLECTED_POSITION = COLLECTED_POSITION;
        }

        public String getCOLLECTED_METHOD() {
            return COLLECTED_METHOD;
        }

        public void setCOLLECTED_METHOD(String COLLECTED_METHOD) {
            this.COLLECTED_METHOD = COLLECTED_METHOD;
        }

        public String getAMOUNT() {
            return AMOUNT;
        }

        public void setAMOUNT(String AMOUNT) {
            this.AMOUNT = AMOUNT;
        }

        public String getCOLLECTED_DATE() {
            return COLLECTED_DATE;
        }

        public void setCOLLECTED_DATE(String COLLECTED_DATE) {
            this.COLLECTED_DATE = COLLECTED_DATE;
        }

        public String getREMARK() {
            return REMARK;
        }

        public void setREMARK(String REMARK) {
            this.REMARK = REMARK;
        }

        public String getCREATE_USER() {
            return CREATE_USER;
        }

        public void setCREATE_USER(String CREATE_USER) {
            this.CREATE_USER = CREATE_USER;
        }

        public String getCREATE_DATETIME() {
            return CREATE_DATETIME;
        }

        public void setCREATE_DATETIME(String CREATE_DATETIME) {
            this.CREATE_DATETIME = CREATE_DATETIME;
        }
    }

    /**
     * 提取物品图片
     */
    public static class extractsPicture {
        private String ID;
        private String INVESTIGATION_ID;//现场勘查ID
        private String EXTRACTS_ID;//物品id
        private String CONTENT;//图片内容
        private String WIDTH;//图片宽度
        private String HEIGHT;//图片高度
        private String FILE_NAME;//图片文件名
        private String CATEGORY;//图片分类
        private String TYPE;//图片类型
        private String SECRECY;//密级
        private String DELETE_FLAG;//删除标志
        private String CREATE_USER;//记录创建人
        private String CREATE_DATETIME;//记录创建时间
        private String UPDATE_DATETIME;//记录更新时间
        private String SUB_CATEGORY;//图片子分类
        private String DESCRIPTION;//图片说明
        private String UPDATE_USER;//记录更新人
        private String RESERVER1;//图片类别，区分现场图，方位图等
        private String RESERVER2;
        private String RESERVER3;
        private String RESERVER4;
        private String RESERVER5;
        private String RESERVER6;
        private String RESERVER7;
        private String RESERVER8;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getINVESTIGATION_ID() {
            return INVESTIGATION_ID;
        }

        public void setINVESTIGATION_ID(String INVESTIGATION_ID) {
            this.INVESTIGATION_ID = INVESTIGATION_ID;
        }

        public String getEXTRACTS_ID() {
            return EXTRACTS_ID;
        }

        public void setEXTRACTS_ID(String EXTRACTS_ID) {
            this.EXTRACTS_ID = EXTRACTS_ID;
        }

        public String getCONTENT() {
            return CONTENT;
        }

        public void setCONTENT(String CONTENT) {
            this.CONTENT = CONTENT;
        }

        public String getWIDTH() {
            return WIDTH;
        }

        public void setWIDTH(String WIDTH) {
            this.WIDTH = WIDTH;
        }

        public String getHEIGHT() {
            return HEIGHT;
        }

        public void setHEIGHT(String HEIGHT) {
            this.HEIGHT = HEIGHT;
        }

        public String getFILE_NAME() {
            return FILE_NAME;
        }

        public void setFILE_NAME(String FILE_NAME) {
            this.FILE_NAME = FILE_NAME;
        }

        public String getCATEGORY() {
            return CATEGORY;
        }

        public void setCATEGORY(String CATEGORY) {
            this.CATEGORY = CATEGORY;
        }

        public String getTYPE() {
            return TYPE;
        }

        public void setTYPE(String TYPE) {
            this.TYPE = TYPE;
        }

        public String getSECRECY() {
            return SECRECY;
        }

        public void setSECRECY(String SECRECY) {
            this.SECRECY = SECRECY;
        }

        public String getDELETE_FLAG() {
            return DELETE_FLAG;
        }

        public void setDELETE_FLAG(String DELETE_FLAG) {
            this.DELETE_FLAG = DELETE_FLAG;
        }

        public String getCREATE_USER() {
            return CREATE_USER;
        }

        public void setCREATE_USER(String CREATE_USER) {
            this.CREATE_USER = CREATE_USER;
        }

        public String getCREATE_DATETIME() {
            return CREATE_DATETIME;
        }

        public void setCREATE_DATETIME(String CREATE_DATETIME) {
            this.CREATE_DATETIME = CREATE_DATETIME;
        }

        public String getUPDATE_DATETIME() {
            return UPDATE_DATETIME;
        }

        public void setUPDATE_DATETIME(String UPDATE_DATETIME) {
            this.UPDATE_DATETIME = UPDATE_DATETIME;
        }

        public String getSUB_CATEGORY() {
            return SUB_CATEGORY;
        }

        public void setSUB_CATEGORY(String SUB_CATEGORY) {
            this.SUB_CATEGORY = SUB_CATEGORY;
        }

        public String getDESCRIPTION() {
            return DESCRIPTION;
        }

        public void setDESCRIPTION(String DESCRIPTION) {
            this.DESCRIPTION = DESCRIPTION;
        }

        public String getUPDATE_USER() {
            return UPDATE_USER;
        }

        public void setUPDATE_USER(String UPDATE_USER) {
            this.UPDATE_USER = UPDATE_USER;
        }

        public String getRESERVER1() {
            return RESERVER1;
        }

        public void setRESERVER1(String RESERVER1) {
            this.RESERVER1 = RESERVER1;
        }

        public String getRESERVER2() {
            return RESERVER2;
        }

        public void setRESERVER2(String RESERVER2) {
            this.RESERVER2 = RESERVER2;
        }

        public String getRESERVER3() {
            return RESERVER3;
        }

        public void setRESERVER3(String RESERVER3) {
            this.RESERVER3 = RESERVER3;
        }

        public String getRESERVER4() {
            return RESERVER4;
        }

        public void setRESERVER4(String RESERVER4) {
            this.RESERVER4 = RESERVER4;
        }

        public String getRESERVER5() {
            return RESERVER5;
        }

        public void setRESERVER5(String RESERVER5) {
            this.RESERVER5 = RESERVER5;
        }

        public String getRESERVER6() {
            return RESERVER6;
        }

        public void setRESERVER6(String RESERVER6) {
            this.RESERVER6 = RESERVER6;
        }

        public String getRESERVER7() {
            return RESERVER7;
        }

        public void setRESERVER7(String RESERVER7) {
            this.RESERVER7 = RESERVER7;
        }

        public String getRESERVER8() {
            return RESERVER8;
        }

        public void setRESERVER8(String RESERVER8) {
            this.RESERVER8 = RESERVER8;
        }
    }
}
