package com.liany.csiclient.presenter;

import android.content.Context;

import com.liany.csiclient.base.Constants;
import com.liany.csiclient.callback.callBack;
import com.liany.csiclient.contract.MainContract;
import com.liany.csiclient.diagnose.ContactsEntity;
import com.liany.csiclient.diagnose.CrimeItem;
import com.liany.csiclient.diagnose.MobileType;
import com.liany.csiclient.diagnose.Response;
import com.liany.csiclient.diagnose.WitnessEntity;
import com.liany.csiclient.model.MainModel;
import com.liany.csiclient.utils.FileUtils;
import com.liany.csiclient.utils.GsonUtils;
import com.liany.csiclient.utils.ProgressUtils;
import com.liany.csiclient.utils.StringUtils;
import com.liany.csiclient.utils.TimeUtils;
import com.liany.csiclient.utils.ToastUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/3/4
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class MainPresenter implements MainContract.Presenter {
    private Context mContext;
    private MainContract.View mainView;
    private MainContract.Model mainModel;

    private String mobileTypeFileName = "mobileTypeSetting.json";

    public MainPresenter(Context mContext, MainContract.View mainView) {
        this.mContext = mContext;
        this.mainView = mainView;
        mainModel = new MainModel(mContext);
    }

    @Override
    public void showBaseInfo() {
        CrimeItem crimeItem = initCrimeItemDate();
        mainView.showBaseInfo(crimeItem);
    }

    @Override
    public void initPresentScene() {
        ProgressUtils.showProgressDialog(mContext,"正在加载中");
        mainModel.getPresentScene(new callBack() {
            @Override
            public void onSuccess(String date) {
                ProgressUtils.dismissProgressDialog();
                Response<CrimeItem> response = GsonUtils.fromJsonObject(date, CrimeItem.class);
                if(response.getCode() == 200) {
                    CrimeItem crimeItem = response.getData();
                    if(crimeItem != null) {
                        String sceneName = StringUtils.long2String(crimeItem.getOccurred_start_time());

                        if (StringUtils.checkString(crimeItem.getArea())) {
                            sceneName += crimeItem.getArea();
                        }
                        if (StringUtils.checkString(crimeItem.getCasetype())) {
                            sceneName += crimeItem.getCasetype();
                        }
                        mainView.setSceneName(sceneName);
                    }
                }else {
                    ToastUtils.showLong("加载当前现场错误：" + response.getMsg());
                }
            }

            @Override
            public void onFail(String msg) {
                ProgressUtils.dismissProgressDialog();
                ToastUtils.showLong("加载当前现场错误：" + msg);
            }
        });
    }

    @Override
    public void showSelectScene() {
        mainModel.getSceneList("0", new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<List<CrimeItem>> response = GsonUtils.fromJsonArray(date, CrimeItem.class);
                if(response.getCode() == 200) {
                    mainView.showSelectScene(response.getData());
                }else {
                    ToastUtils.showLong("加载现场列表错误：" + response.getMsg());
                }
            }

            @Override
            public void onFail(String msg) {
                ToastUtils.showLong("加载现场列表错误：" + msg);
            }
        });
    }

    @Override
    public void updatePresentScene(CrimeItem item) {
        mainModel.setPresentScene(item.getId(), new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                if(response.getCode() == 200) {
                    ToastUtils.showLong("设置当前现场成功");
                    String sceneName = StringUtils.long2String(item.getOccurred_start_time());

                    if (StringUtils.checkString(item.getArea())) {
                        sceneName += item.getArea();
                    }
                    if (StringUtils.checkString(item.getCasetype())) {
                        sceneName += item.getCasetype();
                    }
                    mainView.setSceneName(sceneName);
                }else {
                    ToastUtils.showLong("设置当前现场错误：" + response.getMsg());
                }
            }

            @Override
            public void onFail(String msg) {
                ToastUtils.showLong("设置当前现场错误：" + msg);
            }
        });
    }

    @Override
    public void showProspection() {
        CrimeItem crimeItem = initCrimeItemDate();
        mainView.showProspection(crimeItem);
    }

    private CrimeItem initCrimeItemDate() {
        CrimeItem crimeItem = new CrimeItem();
        crimeItem.setId(StringUtils.getUUID());
        crimeItem.setSceneId(StringUtils.getUUID());
        crimeItem.setCaseId(StringUtils.getUUID());
        crimeItem.setReceptionId(StringUtils.getUUID());
        crimeItem.setOpinionId(StringUtils.getUUID());
        crimeItem.setOpinionResultId(StringUtils.getUUID());
        crimeItem.setOverviewId(StringUtils.getUUID());
        initBaseInfo(crimeItem);

        String userName = mainModel.getUserName();
        crimeItem.setCreateTime(System.currentTimeMillis());
        crimeItem.setUserName(userName);
        crimeItem.setLoginName(mainModel.getLoginName());
//        crimeItem.setTemperature("20");
//        crimeItem.setHumidity("35");
        crimeItem.setOccurred_start_time(StringUtils.String2long(TimeUtils.getTime(null,-1,0)));
//        String occurredEndTime = TimeUtils.getTime(null, Calendar.HOUR_OF_DAY,1);
//        crimeItem.setOccurred_end_time(StringUtils.String2long(occurredEndTime));
//        String accessTime = TimeUtils.getTime(occurredEndTime,Calendar.MINUTE,30);
//        crimeItem.setGet_access_time(StringUtils.String2long(accessTime));
        crimeItem.setGet_access_time(StringUtils.String2long(TimeUtils.getTime(null,-1,0)));
//        crimeItem.setWeatherCondition(mainModel.selectSysDictValue("2",Constants.DICT_WeatherCondition));
//        crimeItem.setWeatherConditionKey("2");
//        crimeItem.setWindDirection(mainModel.selectSysDictValue("09",Constants.DICT_WindDirection));
//        crimeItem.setWindDirectionKey("09");
//        crimeItem.setUnitsAssigned("110指挥中心");
//        crimeItem.setIlluminationCondition(mainModel.selectSysDictValue("1",Constants.DICT_IlluminationCondition));
//        crimeItem.setIlluminationConditionKey("1");
//        String accessStartTime = TimeUtils.getTime(accessTime,Calendar.HOUR_OF_DAY,1);
        crimeItem.setAccess_start_time(StringUtils.String2long(TimeUtils.getTime(null, Calendar.MINUTE,30)));
//        crimeItem.setAccess_end_time(StringUtils.String2long(TimeUtils.getTime(accessStartTime,Calendar.HOUR_OF_DAY,1)));
        /**
         *         crimeItem.setSceneCondition(mainModel.selectSysDictValue("1",Constants.DICT_SceneCondition));
         */
        crimeItem.setSceneConditionKey("1");
        crimeItem.setSceneCondition("原始现场");
//        crimeItem.setProductPeopleDuties("民警");
//        crimeItem.setSafeguard("专人看护现场，防止他人进入");
        crimeItem.setCrimePeopleFeature("不详");
        crimeItem.setCrimePeopleFeatureKey(StringUtils.getUUID());
        crimeItem.setSelectObject("其他");
        crimeItem.setSelectObjectKey("900");

        crimeItem.setReleatedPeopleItem(new ArrayList<ContactsEntity>());
        crimeItem.setLostItem(new ArrayList<>());
        crimeItem.setCrimeToolItem(new ArrayList<>());
        crimeItem.setPositionItem(new ArrayList<>());
        crimeItem.setFlatItem(new ArrayList<>());
        crimeItem.setDwgItem(new ArrayList<>());
        crimeItem.setPositionPhotoItem(new ArrayList<>());
        crimeItem.setOverviewPhotoItem(new ArrayList<>());
        crimeItem.setImportantPhotoItem(new ArrayList<>());
        crimeItem.setEvidenceItem(new ArrayList<>());
        crimeItem.setMonitoringPhotoItem(new ArrayList<>());
        crimeItem.setCameraPhotoItem(new ArrayList<>());
        crimeItem.setWitnessItem(new ArrayList<WitnessEntity>());
        crimeItem.setWifiInfos(new ArrayList<>());
        crimeItem.setGoodEntities(new ArrayList<>());
        crimeItem.setKctbasestationdataBeans(new ArrayList<>());

        //        crimeItem.setAccessInspectors(mainModel.getAccessInspectors());
//        crimeItem.setAccessInspectorsKey(mainModel.getAccessInspectorsKey());

//        String unitName = mainModel.getUnitName();
//        if(unitName != null && !unitName.equals("")) {
//            crimeItem.setArea(unitName);
//            crimeItem.setProductPeopleUnit(unitName);
//            crimeItem.setAreaKey(mainModel.selectOrganId(unitName));
//        }

//        if(userName != null && !userName.equals("")) {
//            String techId = mainModel.selectSysTechId(userName);
//            crimeItem.setSceneConductor(userName);
//            crimeItem.setSceneConductorKey(techId);
//            crimeItem.setProductPeopleName(userName);
//        }
        return crimeItem;
    }

    private void initBaseInfo(CrimeItem crimeItem) {
//        long count = mainModel.selectCrimeItemCount();
//        boolean isUseBaseInfo = (boolean) SPUtils.getParam(mContext,Constants.Is_Use_BaseInfo,false);
//        if(count > 0 && isUseBaseInfo) {
//            CrimeItem item = mainModel.selectLastCrimeItem();
//            crimeItem.setAccessInspectors(item.getAccessInspectors());
//            crimeItem.setAccessInspectorsKey(item.getAccessInspectorsKey());
//            crimeItem.setTemperature(item.getTemperature());
//            crimeItem.setHumidity(item.getHumidity());
//            crimeItem.setWeatherCondition(item.getWeatherCondition());
//            crimeItem.setWeatherConditionKey(item.getWeatherConditionKey());
//            crimeItem.setWindDirection(item.getWindDirection());
//            crimeItem.setWindDirectionKey(item.getWindDirectionKey());
//            crimeItem.setUnitsAssigned(item.getUnitsAssigned());
//            crimeItem.setAccessPolicemen(item.getAccessPolicemen());
//            crimeItem.setIlluminationCondition(item.getIlluminationCondition());
//            crimeItem.setIlluminationConditionKey(item.getIlluminationConditionKey());
//            crimeItem.setProductPeopleDuties(item.getProductPeopleDuties());
//            crimeItem.setSafeguard(item.getSafeguard());
//            crimeItem.setArea(item.getArea());
//            crimeItem.setAreaKey(item.getAreaKey());
//            crimeItem.setProductPeopleUnit(item.getProductPeopleUnit());
//            crimeItem.setProductPeopleName(item.getProductPeopleName());
//            crimeItem.setSceneConductor(item.getSceneConductor());
//            crimeItem.setSceneConductorKey(item.getSceneConductorKey());
//        }else {
            crimeItem.setAccessInspectors(mainModel.getAccessInspectors());
            crimeItem.setAccessInspectorsKey(mainModel.getAccessInspectorsKey());
            crimeItem.setTemperature("20");
            crimeItem.setHumidity("35");
        /**
         *         crimeItem.setWeatherCondition(mainModel.selectSysDictValue("2",Constants.DICT_WeatherCondition));
         */
            crimeItem.setWeatherConditionKey("2");
            crimeItem.setWeatherCondition("晴");
        /**
         *             crimeItem.setWindDirection(mainModel.selectSysDictValue("09",Constants.DICT_WindDirection));
         */
        crimeItem.setWindDirectionKey("09");
        crimeItem.setWindDirection("无风");
            crimeItem.setUnitsAssigned("110指挥中心");
        /**
         *         crimeItem.setIlluminationCondition(mainModel.selectSysDictValue("1",Constants.DICT_IlluminationCondition));
         */
            crimeItem.setIlluminationConditionKey("1");
            crimeItem.setIlluminationCondition("自然光");
            crimeItem.setProductPeopleDuties("民警");
            crimeItem.setSafeguard("专人看护现场，防止他人进入");
            String unitName = mainModel.getUnitName();
            if(unitName != null && !unitName.equals("")) {
                crimeItem.setArea(unitName);
                crimeItem.setProductPeopleUnit(unitName);
                crimeItem.setAreaKey(mainModel.selectOrganId());
                crimeItem.setUnitCode(mainModel.getUnitCode());
            }
            String userName = mainModel.getUserName();
            if(userName != null && !userName.equals("")) {
                String techId = mainModel.selectSysTechId();
                crimeItem.setSceneConductor(userName);
                crimeItem.setSceneConductorKey(techId);
                crimeItem.setProductPeopleName(userName);
            }
//        }
    }

    @Override
    public void initMobileTypeSetting(String manufacturer, String model) {
        //检查文件sd卡下是否存在
        if(FileUtils.checkFileExists(Constants.path_settingDir + File.separator + mobileTypeFileName)) {
            //判断code，确定是否需要复制
            String assetsFile = FileUtils.ReadAssetsFile(mContext, mobileTypeFileName);
            Response<List<MobileType>> assetsTypes = GsonUtils.fromJsonArray(assetsFile, MobileType.class);
            String sdCardFile = FileUtils.ReadSDCardFile(mContext, Constants.path_settingDir + File.separator + mobileTypeFileName);
            if(StringUtils.checkString(sdCardFile)) {
                Response<List<MobileType>> sdCardTypes = GsonUtils.fromJsonArray(sdCardFile, MobileType.class);
                if(sdCardTypes.getCode() >= assetsTypes.getCode()) {
                    //读取配置到SP
                    for(MobileType type : assetsTypes.getData()) {
                        if(type.getManufacturer().equals(manufacturer) && type.getModel().equals(model)) {
                            mainModel.saveMobileTypeSetting(type.getWidth(),type.getHeight());
                        }
                    }
                    return;
                }
            }
        }
        //复制assets文件下文件
        try {
            FileUtils.copyAssetsToDir(mContext, mobileTypeFileName, Constants.path_settingDir);
            String assetsFile = FileUtils.ReadAssetsFile(mContext, mobileTypeFileName);
            Response<List<MobileType>> assetsTypes = GsonUtils.fromJsonArray(assetsFile, MobileType.class);
            for(MobileType type : assetsTypes.getData()) {
                if(type.getManufacturer().equals(manufacturer) && type.getModel().equals(model)) {
                    mainModel.saveMobileTypeSetting(type.getWidth(),type.getHeight());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
