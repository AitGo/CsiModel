package com.liany.csiclient.presenter.subView.sceneStep;

import android.content.Context;
import android.content.Intent;

import com.bigkoo.pickerview.view.TimePickerView;
import com.liany.csiclient.base.Constants;
import com.liany.csiclient.callback.callBack;
import com.liany.csiclient.contract.subView.sceneStep.BaseInfoContract;
import com.liany.csiclient.diagnose.CrimeItem;
import com.liany.csiclient.diagnose.Response;
import com.liany.csiclient.diagnose.selectUser;
import com.liany.csiclient.diagnose.sysDict;
import com.liany.csiclient.model.subView.sceneStep.BaseInfoModel;
import com.liany.csiclient.utils.GsonUtils;
import com.liany.csiclient.utils.StringUtils;
import com.liany.csiclient.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

import static android.app.Activity.RESULT_OK;

/**
 * @创建者 ly
 * @创建时间 2020/3/23
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class BaseInfoPresenter implements BaseInfoContract.Presenter {
    private BaseInfoContract.View view;
    private BaseInfoContract.Model model;
    private Context mContext;
    private TimePickerView pv_time;

    private String weatherCondition = "天气状况";
    private String windDirection = "风向";
    private String illuminationCondition = "光照条件";
    private String accessInspectors = "勘验检查人员";
    private String sceneConductor = "现场指挥人员";
    private String area = "发案区划";

    public BaseInfoPresenter(Context mContext, BaseInfoContract.View loginView) {
        this.mContext = mContext;
        this.view = loginView;
        model = new BaseInfoModel(mContext);
    }

    @Override
    public void initView(CrimeItem crimeItem) {
        view.setAccessInspectors(crimeItem.getAccessInspectors());
        view.setWeatherCondition(crimeItem.getWeatherCondition());
        view.setWindDirection(crimeItem.getWindDirection());
        view.setTemperature(crimeItem.getTemperature());
        view.setHumidity(crimeItem.getHumidity());
        view.setArea(crimeItem.getArea());
        view.setUnitsAssigned(crimeItem.getUnitsAssigned());
        view.setAccessPolicemen(crimeItem.getAccessPolicemen());
        view.setIlluminationCondition(crimeItem.getIlluminationCondition());
        view.setProductPeopleName(crimeItem.getProductPeopleName());
        view.setProductPeopleUnit(crimeItem.getProductPeopleUnit());
        view.setProductPeopleDuties(crimeItem.getProductPeopleDuties());
        view.setSafeguard(crimeItem.getSafeguard());
        view.setSceneConductor(crimeItem.getSceneConductor());
        view.setSceneCondition(crimeItem.getSceneConditionKey());
        view.setChangeOption(crimeItem.getChangeOptionKey());
        view.setChangeReason(crimeItem.getChangeReason());
    }

    @Override
    public CrimeItem saveBaseInfo(CrimeItem item) {
        if(item == null) {
            item = new CrimeItem();
        }
        item.setAccessInspectors(view.getAccessInspectors());
        item.setWeatherCondition(view.getWeatherCondition());
        item.setWindDirection(view.getWindDirection());
        item.setTemperature(view.getTemperature());
        item.setHumidity(view.getHumidity());
        item.setArea(view.getArea());
        item.setUnitsAssigned(view.getUnitsAssigned());
        item.setAccessPolicemen(view.getAccessPolicemen());
        item.setIlluminationCondition(view.getIlluminationCondition());
        item.setProductPeopleName(view.getProductPeopleName());
        item.setProductPeopleUnit(view.getProductPeopleUnit());
        item.setProductPeopleDuties(view.getProductPeopleDuties());
        item.setSafeguard(view.getSafeguard());
        item.setSceneConductor(view.getSceneConductor());
        item.setSceneCondition(view.getSceneCondition());
        item.setSceneConditionKey(view.getSceneConditionKey());
        item.setChangeOption(view.getChangeOption());
        item.setChangeOptionKey(view.getChangeOptionKey());
        item.setChangeReason(view.getChagenReason());
        return item;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK) {
            if (requestCode == Constants.REQUEST_RADIO_DICT) {
                if(data.getStringExtra(Constants.SELECT_TITLE).equals(weatherCondition)) {
                    sysDict dict = (sysDict) data.getSerializableExtra(Constants.RESULT_RADIO_DICT);
                    view.setWeatherCondition(dict);
                } else if(data.getStringExtra(Constants.SELECT_TITLE).equals(windDirection)) {
                    sysDict dict = (sysDict) data.getSerializableExtra(Constants.RESULT_RADIO_DICT);
                    view.setWindDirection(dict);
                } else if(data.getStringExtra(Constants.SELECT_TITLE).equals(illuminationCondition)) {
                    sysDict dict = (sysDict) data.getSerializableExtra(Constants.RESULT_RADIO_DICT);
                    view.setIlluminationCondition(dict);
                }
            } else if(requestCode == Constants.REQUEST_CHECK_DICT) {

            } else if(requestCode == Constants.REQUEST_CHECK_USER) {
                if(data.getStringExtra(Constants.SELECT_TITLE).equals(accessInspectors)) {
                    List<selectUser> users = (List<selectUser>) data.getSerializableExtra(Constants.RESULT_CHECK_USER);
                    view.setAccessInspectors(users);
                    model.setAccessInspectors(StringUtils.selectUserValue2String(users));
                    model.setAccessInspectorsKey(StringUtils.selectUserKey2String(users));
                }
            } else if(requestCode == Constants.REQUEST_RADIO_USER) {
                if(data.getStringExtra(Constants.SELECT_TITLE).equals(sceneConductor)) {
                    selectUser user = (selectUser) data.getSerializableExtra(Constants.RESULT_RADIO_USER);
                    view.setSceneConductor(user);
                } else if(data.getStringExtra(Constants.SELECT_TITLE).equals(area)) {
                    selectUser area = (selectUser) data.getSerializableExtra(Constants.RESULT_RADIO_USER);
                    view.setArea(area);
                }
            }
        }
    }

    @Override
    public void accessInspectors() {
        String value = view.getAccessInspectors();
        model.selectAccessInspectors(model.getOrganId(), new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<List<selectUser>> response = GsonUtils.fromJsonArray(date, selectUser.class);
                if(response.getCode() == 200) {
                    view.startSelectUserView(Constants.REQUEST_CHECK_USER,accessInspectors,response.getData(),value);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void weatherCondition() {
        String weather = view.getWeatherCondition();
        model.selectWeatherCondition(new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<List<sysDict>> response = GsonUtils.fromJsonArray(date, sysDict.class);
                if(response.getCode() == 200) {
                    List<Integer> selectList = new ArrayList<>();
                    if(weather != null && !weather.equals("")) {
                        for(sysDict dict : response.getData()) {
                            if(dict.getDictValue().equals(weather)) {
                                selectList.add(response.getData().indexOf(dict));
                            }
                        }
                    }
                    view.startSelectDictView(Constants.REQUEST_RADIO_DICT,weatherCondition,response.getData(),selectList);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void windDirection() {
        String wind = view.getWindDirection();
        model.selectWindDirection(new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<List<sysDict>> response = GsonUtils.fromJsonArray(date, sysDict.class);
                if(response.getCode() == 200) {
                    List<Integer> selectList = new ArrayList<>();
                    if(wind != null && !wind.equals("")) {
                        for(sysDict dict : response.getData()) {
                            if(dict.getDictValue().equals(wind)) {
                                selectList.add(response.getData().indexOf(dict));
                            }
                        }
                    }
                    view.startSelectDictView(Constants.REQUEST_RADIO_DICT,windDirection,response.getData(),selectList);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });

    }

    @Override
    public void illuminationCondition() {
        String value = view.getIlluminationCondition();
        model.selectIlluminationCondition(new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<List<sysDict>> response = GsonUtils.fromJsonArray(date, sysDict.class);
                if(response.getCode() == 200) {
                    List<Integer> selectList = new ArrayList<>();
                    if(value != null && !value.equals("")) {
                        for(sysDict dict : response.getData()) {
                            if(dict.getDictValue().equals(value)) {
                                selectList.add(response.getData().indexOf(dict));
                            }
                        }
                    }
                    view.startSelectDictView(Constants.REQUEST_RADIO_DICT,illuminationCondition,response.getData(),selectList);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });

    }

    @Override
    public void sceneConductor() {
        String value = view.getSceneConductor();
        model.selectAccessInspectors(model.getOrganId(), new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<List<selectUser>> response = GsonUtils.fromJsonArray(date, selectUser.class);
                if(response.getCode() == 200) {
                    view.startSelectUserRadioView(Constants.REQUEST_RADIO_USER,sceneConductor,response.getData(),value);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void area() {
        String value = view.getArea();
        model.selectArea(model.getOrganId(), new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<List<selectUser>> response = GsonUtils.fromJsonArray(date, selectUser.class);
                if(response.getCode() == 200) {
                    view.startSelectUnitRadioView(Constants.REQUEST_RADIO_USER,area,response.getData(),value);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });

    }

    @Override
    public void saveCrimeItem(CrimeItem crimeItem) {
        if(StringUtils.checkString(crimeItem.getId())) {
            crimeItem.setId(StringUtils.getUUID());
        }
        String s = GsonUtils.gsonString(crimeItem);
        model.createCrimeItem(s, new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                if(response.getCode() == 200) {
                    ToastUtils.showLong(response.getData());
                    view.startCreateSceneView(crimeItem);
                }
            }

            @Override
            public void onFail(String msg) {
                ToastUtils.showLong("创建现场错误:" + msg);
            }
        });
    }
}
