package com.liany.csiclient.base;


import android.os.Environment;

import com.amap.api.location.AMapLocationClientOption;
import com.liany.csiclient.debug.ClientApplication;
import com.liany.csiclient.utils.IpUtils;
import com.liany.csiclient.utils.LogUtils;
import com.liany.model.common.base.BaseApplication;

/**
 * @创建者 ly
 * @创建时间 2019/3/15
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class Constants {

    public static AMapLocationClientOption.AMapLocationMode AMAP_MODE = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy;
    public static String ipAddress = "http://" + IpUtils.getIpAddress(BaseApplication.getContext()) +  ":8080";
//    public static String ipAddress = "http://192.168.31.145:8080";
//    public static String ipAddress = "http://192.168.43.1:8080";
//    public static String ipAddress = "http://localhost:8080";
    public static boolean isUseDraw = true;
    public static boolean isAlarm = true;
    public static int mTaskId = 0;

    public static final boolean Bugly_isDebug = true;
    public static final boolean isDebug = false;
    public static int DEBUGLEVEL = LogUtils.LEVEL_ALL; //打开日志开关
    public static String LOG_NAME = "csiClient***"; //日志名字
    public static String SP_FILE_NAME = "csiClientSP";
//    public static String SP_FILE_NAME = "CoreInformation";

    public static String sp_isFirstLogin = "isFirstLogin";
    public static String sp_url = "url";
    public static String sp_version = "version";

    public static String[] devices = new String[]{"本机摄像头", "云痕采集", "单反相机"};
    public static String[] devicesPeople = new String[]{"指纹采集设备", "云痕采集"};

    public static int code_scan = 0;
    public static int code_scan_ip = 1001;
    public static int code_permissions_camera = 1;


    //是否在分析意见显示查询按钮
    public static boolean state_isShowOpinionQueryBtn = false;
    //是否显示WiFi采集和基站采集
    public static boolean state_isShowwifi = true;
    //是否显示提取物品
    public static boolean state_isShowExtract = true;
    //是否上传现场物品(上传数据，不是界面)
    public static boolean state_isUploadExtract = true;

    public static String path_photoDir = BaseApplication.getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
    public static final String path_settingDir = BaseApplication.getContext().getExternalFilesDir("Setting").getAbsolutePath();
    public static final String path_flatDir = BaseApplication.getContext().getExternalFilesDir("Flat").getAbsolutePath();

    public static final String FingerPhoto = "photoPath";

    public static String sp_mobileType_Width = "mobileTypeWidth";
    public static String sp_mobileType_Heigth = "mobileTypeHeight";
    public static String sp_loginName = "loginName";
    public static String sp_userName = "userName";
    public static String sp_userId = "userId";
    public static String sp_personId = "personId";
    public static String sp_organId = "organId";
    public static String sp_techId = "techId";
    public static String sp_unitName = "unitName";
    public static String sp_unitCode = "unitCode";
    public static String sp_accessInspectorsKey = "accessKey";
    public static String sp_accessInspectors = "access";
    public static String sp_accessInspectorsId = "accessId";
    public static String sp_UseDevice = "useDevice";
    public static String sp_UseDevicePeople = "useDevicePeople";
    public static final String positionFilePathKey = "positionFileName";

    public static final String state_positionPic = "0";
    public static final String state_flat = "1";
    public static final String state_positionPhoto = "2";
    public static final String state_overview = "3";
    public static final String state_important = "4";
    public static final String state_detail = "5";
    public static final String state_evidence = "6";
    public static final String state_evidence_finger = "601";
    public static final String state_evidence_foot = "602";
    public static final String state_evidence_tool = "603";
    public static final String state_evidence_other = "604";
    public static final String state_monitoring = "7";
    public static final String state_camera = "8";
    public static final String state_witness = "9";
    public static final String state_extract = "10";
    public static final String state_flat_dwg = "11";
    public static final String state_visit_people = "12";
    public static final String photoState_compare_people = "101";
    public static final String photoState_compare_finger = "102";
    public static final String photoState_compare_foot = "103";
    public static final String photoState_compare_face = "104";

    public static int value_pageSize = 6;

    //figure
    public static final int RESULT_CODE_POSITION = 1;
    //main
    public static final int RESULT_CODE_STARTCAMERA = 10;
    public static final int RESULT_CODE_STARTWRITE = 11;
    public static final int SCAN_REQUEST_CODE = 0;
    //flat
    public static final int REQUEST_FLAT = 107;
    public static final int EVENT_EDIT_FLAT = 1051;

    //photo
    public static final int REQUEST_TAKE_PHOTO_POSITION = 2;
    public static final int REQUEST_TAKE_PHOTO_POSITION_img = 2001;
    public static final int REQUEST_TAKE_PHOTO_POSITION_img_more = 2002;
    public static final int REQUEST_TAKE_PHOTO_LIKE = 3;
    public static final int REQUEST_TAKE_PHOTO_LIKE_img = 3001;
    public static final int REQUEST_TAKE_PHOTO_LIKE_img_more = 3002;
    public static final int REQUEST_TAKE_PHOTO_IMPORTANT = 4;
    public static final int REQUEST_TAKE_PHOTO_IMPORTANT_img = 4001;
    public static final int REQUEST_TAKE_PHOTO_IMPORTANT_img_more = 4002;
    public static final int REQUEST_TAKE_PHOTO_DETAIL = 101;
    public static final int REQUEST_TAKE_PHOTO_DETAIL_img = 102;
    public static final int REQUEST_TAKE_PHOTO_DETAIL_img_more = 103;

    //evidence
    public static final int REQUEST_EVIDENCE_EVIDENCE = 5;
    public static final int REQUEST_EVIDENCE_EVIDENCE_GOOGLE = 42;
    public static final int REQUEST_EVIDENCE_EVIDENCE_ALBUM = 43;
    public static final int REQUEST_EVIDENCE_MONITORING = 6;
    public static final int REQUEST_EVIDENCE_MONITORING_img = 6001;
    public static final int REQUEST_EVIDENCE_MONITORING_img_more = 6002;
    public static final int REQUEST_EVIDENCE_CAMERA = 7;
    public static final int REQUEST_EVIDENCE_CAMERA_img = 7001;
    public static final int REQUEST_EVIDENCE_CAMERA_img_more = 7002;
    public static final int REQUEST_EVIDENCE_ADD = 8;
    public static final int REQUEST_EVIDENCE_ADD_FINGER = 801;
    public static final int REQUEST_EVIDENCE_ADD_FOOT = 802;
    public static final int REQUEST_EVIDENCE_ADD_OTHER = 803;
    public static final int REQUEST_EVIDENCE_ADD_FACE = 804;

    public static final int EVENT_PHOTO_TYPE_SIGN= 9;

    public static final int REQUEST_WITNESS_ADD = 10;

    public static final int REQUEST_VISIT_ADD_CONTACTS = 13;
    public static final int REQUEST_VISIT_ADD_ITEM = 14;
    public static final int REQUEST_VISIT_ADD_TOOL = 15;

    public static final String SELECT_TITLE = "seletTitle";
    public static final int REQUEST_SELECT_RADIO = 11;

    public static final int REQUEST_CREATE_SCENE_PROSPECTING = 16;
    public static final int REQUEST_CREATE_SCENE_BASEINFO = 38;
    public static final int REQUEST_CREATE_SCENE_VISIT = 17;
    public static final int REQUEST_CREATE_SCENE_FIGURE = 18;
    public static final int REQUEST_CREATE_SCENE_PHOTO = 19;
    public static final int REQUEST_CREATE_SCENE_EVIDENCE = 20;
    public static final int REQUEST_CREATE_SCENE_SITUATION = 21;
    public static final int REQUEST_CREATE_SCENE_OPINION = 22;
    public static final int REQUEST_CREATE_SCENE_WITNESS = 23;
    public static final int REQUEST_CREATE_SCENE_WIFI = 33;
    public static final int REQUEST_CREATE_SCENE_EXTRACT = 34;
    public static final int REQUEST_CREATE_STATION = 37;

    public static final int RESULT_CODE_LOCATION_HARDWARE = 24;

    public static final String SELECT_POSITION = "select_position";

    public static final String SELECT_CHECK_DICT = "select_Check_DictListData";
    public static final String RESULT_CHECK_DICT = "result_Check_DictListData";
    public static final int REQUEST_CHECK_DICT = 25;

    public static final String SELECT_RADIO_DICT = "select_Radio_DictListData";
    public static final String RESULT_RADIO_DICT = "result_Radio_DictListData";
    public static final int REQUEST_RADIO_DICT = 26;

    public static final String SELECT_EXPAND_RADIO_DICT = "select_expand_Radio_DictListData";
    public static final String RESULT_EXPAND_RADIO_DICT = "result_expand_Radio_DictListData";
    public static final int REQUEST_EXPAND_RADIO_DICT = 27;

    public static final String SELECT_EXPAND_POSITION = "select_expand_position";
    public static final String SELECT_EXPAND_CHECK_DICT = "select_expand_Check_DictListData";
    public static final String RESULT_EXPAND_CHECK_DICT = "result_expand_Check_DictListData";
    public static final int REQUEST_EXPAND_CHECK_DICT = 28;

    public static final String SELECT_RADIO_USER = "select_Radio_UserListData";
    public static final String RESULT_RADIO_USER = "result_Radio_UserListData";
    public static final int REQUEST_RADIO_USER = 29;

    public static final String SELECT_CHECK_USER = "select_Check_UserListData";
    public static final String RESULT_CHECK_USER = "result_Check_UserListData";
    public static final int REQUEST_CHECK_USER = 30;

    public static final int REQUEST_EXTRACT = 35;
    public static final int REQUEST_LEGEND = 31;
    public static final int REQUEST_CODE_CAPTURE = 37;
    public static final int REQUEST_COLLECT_SCAN = 40;

    public static final int REQUEST_VISIT_ROTATE = 41;

    public static boolean TYPE_YHCAMERA_PEOPLE = false;
    public static boolean TYPE_YHCAMERA_EVIDENCE = true;
    public static String sp_deviceId = "deviceId";

    public static final int REQUEST_VISIT_ADD_CONTACTS_FINGER = 44;

    public static final int REQUEST_WITNESS_ADD_IDCARD = 45;
    public static final int REQUEST_LOCAL_CROP = 46;
    public static final int REQUEST_COMPARE_FINGER_ADD = 47;
    public static final int REQUEST_COMPARE_FOOT_ADD = 48;
    public static final int REQUEST_COMPARE_FACE_ADD = 51;
    public static final int REQUEST_UCROP = 49;
    public static final int REQUEST_ALARM = 50;

}
