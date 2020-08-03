package com.liany.csiserverapp.base;

import android.os.Environment;


import com.liany.csiserverapp.debug.ServerApplication;
import com.liany.csiserverapp.utils.IpUtils;
import com.liany.csiserverapp.utils.LogUtils;
import com.liany.model.common.base.BaseApplication;

import java.io.File;

/**
 * @创建者 ly
 * @创建时间 2019/3/15
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class Constants {

    public static boolean isLocal = false;
    public static final boolean Bugly_isDebug = false;
    public static int DEBUGLEVEL = LogUtils.LEVEL_ALL; //打开日志开关
    public static String LOG_NAME = "csiServer***"; //日志名字
    public static String SP_FILE_NAME = "csiClientSP";
    public static String defaultURL = "http://192.168.31.88:8080/lianyservice/service/appUploadDataService";
//    public static String defaultURL = "http://192.168.31.99:8888/lianyws/services/AppUploadDataService?wsdl";
//    public static String ipAddress = "http://192.168.43.1:8080/";
//    public static String ipAddress = "http://" + IpUtils.getIpAddress(ServerApplication.getContext()) + ":8080/";
//    public static String ip = "192.168.43.1";
//    public static String ip = IpUtils.getIpAddress(ServerApplication.getContext());
//    public static String ip = "127.0.0.1";
    public static String ip = "192.168.31.232";
    public static boolean isUseService = true;
    public static boolean isAlarm = true;

    public static String ipAddress = "http://" + ip + ":8080/";

    public static final int UPLOAD_FAIL = 1;
    public static final int UPLOAD_SUCCESS = 2;
    public static final int TYPE_FINGER_PEOPLE = 0;
    public static final int TYPE_FINGER_EVIDENCE = 1;
    public static final int TYPE_FOOT_EVIDENCE = 2;
    public static final int TYPE_FACE_EVIDENCE = 3;

    public static int value_pageSize = 6;

    public static final String photoState_positionPic = "0";
    public static final String photoState_flat = "1";
    public static final String photoState_positionPhoto = "2";
    public static final String photoState_overview = "3";
    public static final String photoState_important = "4";
    public static final String photoState_detail = "5";
    public static final String photoState_evidence = "6";
    public static final String state_evidence_finger = "601";
    public static final String state_evidence_foot = "602";
    public static final String state_evidence_tool = "603";
    public static final String state_evidence_other = "604";
    public static final String photoState_monitoring = "7";
    public static final String photoState_camera = "8";
    public static final String photoState_witness = "9";
    public static final String photoState_extract = "10";
    public static final String photoState_flat_dwg = "11";
    public static final String photoState_visit_people = "12";
    public static final String photoState_compare_people = "101";
    public static final String photoState_compare_finger = "102";
    public static final String photoState_compare_foot = "103";
    public static final String photoState_compare_face = "104";

    public static String photoPath = BaseApplication.getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
    public static String zipPath = BaseApplication.getContext().getExternalFilesDir("Zip").getAbsolutePath();
    public static String zipFilePath = BaseApplication.getContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
    public static String zipContactsPath = BaseApplication.getContext().getExternalFilesDir("Zip" + File.separator + "victim/").getAbsolutePath();
//    public static String photoPath = StringUtils.externalSDCardPath() + "/csiServer/Picture";
//    public static String zipPath = StringUtils.externalSDCardPath() + "/csiServer/Zip";
//    public static String zipFilePath = StringUtils.externalSDCardPath() + "/csiServer/Documents";
//    public static String zipContactsPath  = StringUtils.externalSDCardPath() + "/csiServer/Zip/victim";

    public static String sp_url = "url";
    public static String sp_isFirstLogin = "isFirstLogin";
    public static String sp_presentScene = "presentScene";
    public static String sp_accessInspectorsKey = "accessKey";
    public static String sp_accessInspectors = "access";
    public static String sp_version = "version";

    public static String method_loadUserInfo = "LoadUserInfo";
    public static String method_loadOrganInfo = "LoadOrganInfo";
    public static String method_loadTechnicianInfo = "LoadTechnicianInfo";
    public static String method_loadDictInfo = "LoadDictInfo";
    public static String method_deletePic = "deletePhoto";
    public static String method_uploadPic = "uploadPhoto";
    public static String method_uploadSecne = "uploadSceneInfo";
    public static String method_checkVersion = "checkAppVersion";

    public static final String DICT_WeatherCondition = "XCKYTQQKDM";
    public static final String DICT_WindDirection = "XCFXDM";
    public static final String DICT_SceneCondition = "XCTJDM";
    public static final String DICT_light = "XCKYGZTJDM";
    public static final String DICT_PeopleNumber = "ZARSLDM";
    public static final String DICT_CrimeMeans = "ZASDFLDM";
    public static final String DICT_CrimeCharacter = "AJXZDM";
    public static final String DICT_CrimeEntrance = "ZACRKDM";
    public static final String DICT_CrimeTiming = "ZASJDFLDM";
    public static final String DICT_Object = "XZDXFLDM";
    public static final String DICT_CrimeFeature = "ZATDFLDM";
    public static final String DICT_IntrusiveMethod = "QRFSFLDM";
    public static final String DICT_Location = "XZCSFLDM";
    public static final String DICT_CrimePurpose = "ZADJMDDM";
    public static final String DICT_Sex = "XBDM";
    public static final String DICT_ToolType = "ZAGJLMDM";
    public static final String DICT_ToolSource = "ZAGJLYDM";
    public static final String DICT_HandEvidence = "SYHJLXDM";
//    public static final String DICT_FootEvidence = "ZJHJLXDM";
    public static final String DICT_FootEvidence = "ZXYLXDM";
    public static final String DICT_ToolEvidence = "GJHJZLDM";
    public static final String DICT_HandMethod = "ZWTQFFDM";
    public static final String DICT_FootMethod = "ZJTQFFDM";
    public static final String DICT_ToolMethod = "GJHJTQFFDM";
    public static final String DICT_Infer = "GJTDZLDM";

    public static String sp_deviceId = "deviceId";

    public static final int REQUEST_ALARM = 9001;

}
