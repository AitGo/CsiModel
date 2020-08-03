package com.liany.csiclient.contract.subView.sceneStep;

import android.content.Intent;

import com.liany.csiclient.callback.callBack;
import com.liany.csiclient.diagnose.CrimeItem;
import com.liany.csiclient.diagnose.selectUser;
import com.liany.csiclient.diagnose.sysDict;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @创建者 ly
 * @创建时间 2020/3/23
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public interface BaseInfoContract {
    interface Model {
        String getOrganId();
        void selectAccessInspectors(String organId, callBack callBack);
        void selectWeatherCondition(callBack callBack);
        void selectWindDirection(callBack callBack);
        void selectSceneCondition(callBack callBack);
        void selectIlluminationCondition(callBack callBack);
        void selectArea(String organId, callBack callBack);

        void setAccessInspectors(String s);

        void setAccessInspectorsKey(String s);

        void createCrimeItem(String baseInfo, callBack callBack);

    }

    interface View {
        void setAccessInspectors(String accessInspectors);
        void setAccessInspectors(List<selectUser> users);
        String getAccessInspectors();
        void setWeatherCondition(String weatherCondition);
        void setWeatherCondition(sysDict dict);
        String getWeatherCondition();
        void setWindDirection(String windDirection);
        void setWindDirection(sysDict dict);
        String getWindDirection();
        void setTemperature(String temperature);
        String getTemperature();
        void setHumidity(String humidity);
        String getHumidity();
        void setArea(String area);
        void setArea(selectUser area);
        String getArea();
        void setUnitsAssigned(String unitsAssigned);
        String getUnitsAssigned();
        void setAccessPolicemen(String accessPolicemen);
        String getAccessPolicemen();
        void setIlluminationCondition(String illuminationCondition);
        void setIlluminationCondition(sysDict dict);
        String getIlluminationCondition();
        void setProductPeopleName(String productPeopleName);
        String getProductPeopleName();
        void setProductPeopleUnit(String productPeopleUnit);
        String getProductPeopleUnit();
        void setProductPeopleDuties(String productPeopleDuties);
        String getProductPeopleDuties();
        void setSafeguard(String safeguard);
        String getSafeguard();
        void setSceneConductor(String sceneConductor);
        void setSceneConductor(selectUser user);
        String getSceneConductor();

        void setSceneCondition(String sceneConditionKey);
        void setChangeOption(String changeOptionKey);
        String getChangeOption();
        String getChangeOptionKey();
        void setChangeReason(String changeReason);
        String getChagenReason();
        void setRadioSceneCondition1(String text);
        String getRadioSceneCondition1();
        void setRadioSceneCondition2(String text);
        String getRadioSceneCondition2();

        String getSceneCondition();

        String getSceneConditionKey();

        void startCreateSceneView(CrimeItem crimeItem);

        void startSelectDictView(int selectType, String title, List<sysDict> dicts, List<Integer> selectList);

        void startSelectUserView(int selectType, String title, List<selectUser> users, String value);

        void startSelectUserRadioView(int selectType, String title, List<selectUser> users, String value);

        void startSelectUnitRadioView(int selectType, String title, List<selectUser> users, String value);
    }

    interface Presenter {
        CrimeItem saveBaseInfo(CrimeItem crimeItem);

        void initView(CrimeItem crimeItem);

        void onActivityResult(int requestCode, int resultCode, @Nullable Intent data);

        void accessInspectors();
        void weatherCondition();
        void windDirection();
        void illuminationCondition();
        void sceneConductor();
        void area();

        void saveCrimeItem(CrimeItem crimeItem);
    }
}
