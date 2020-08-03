package com.liany.csiserverapp.andServer.service;

import android.content.Context;

import com.liany.csiserverapp.andServer.model.SysDB;
import com.liany.csiserverapp.diagnose.CrimeItem;
import com.liany.csiserverapp.diagnose.SelectLocationBean;
import com.liany.csiserverapp.diagnose.sysDict;
import com.liany.csiserverapp.network.response.Response;
import com.liany.csiserverapp.utils.FileUtils;
import com.liany.csiserverapp.utils.GsonUtils;

import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/3/16
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class SysService {

    public static String getPeople(String organId) {
//        String techid = SysDB.getTechidByUserName(loginName);
//        String orgId = SysDB.getOrgIdByTechId(techid);
        return GsonUtils.successJson(SysDB.selectPeople(organId));
    }

    public static String getWeatherCondition() {
        return GsonUtils.successJson(SysDB.selectWeatherCondition());
    }

    public static String getWindDirection() {
        return GsonUtils.successJson(SysDB.selectWindDirection());
    }

    public static String getArea(String organId) {
//        String techid = SysDB.getTechidByUserName(loginName);
//        String orgId = SysDB.getOrgIdByTechId(techid);
        return GsonUtils.successJson(SysDB.selectArea(organId));
    }

    public static String getLight() {
        return GsonUtils.successJson(SysDB.selectLight());
    }

    public static String getCaseType(Context mContext) {
        return GsonUtils.successJson(SysDB.selectCasetype(mContext));
    }

    public static String getToolType() {
        return GsonUtils.successJson(SysDB.selectToolType());
    }

    public static String getToolSource() {
        return GsonUtils.successJson(SysDB.selectToolSource());
    }

    public static String getHandEvidence() {
        return GsonUtils.successJson(SysDB.selectHandEvidence());
    }

    public static String getFootEvidence() {
        return GsonUtils.successJson(SysDB.selectFootEvidence());
    }

    public static String getToolEvidence() {
        return GsonUtils.successJson(SysDB.selectToolEvidence());
    }

    public static String getHandMethod() {
        return GsonUtils.successJson(SysDB.selectHandMethod());
    }

    public static String getFootMethod() {
        return GsonUtils.successJson(SysDB.selectFootMethod());
    }

    public static String getToolMethod() {
        return GsonUtils.successJson(SysDB.selectToolMethod());
    }

    public static String getInfer() {
        return GsonUtils.successJson(SysDB.selectInfer());
    }

    public static String getPeopleNumber(Context mContext) {
        String json = FileUtils.ReadAssetsFile(mContext, "peopleNumber.json");
        Response<List<sysDict>> response = GsonUtils.fromJsonArray(json, sysDict.class);
        return GsonUtils.successJson(response.getData());
//        return GsonUtils.successJson(SysDB.selectPeopleNumber());
    }

    public static String getCrimeMeans() {
        return GsonUtils.successJson(SysDB.selectCrimeMeans());
    }

    public static String getCrimeCharacter(Context mContext, String caseTypeKey) {
        String json = FileUtils.ReadAssetsFile(mContext, "crimeCharacter.json");
        Response<List<CrimeItem>> response = GsonUtils.fromJsonArray(json, CrimeItem.class);
        for(CrimeItem item : response.getData()) {
            if(caseTypeKey.equals(item.getCasetypeKey())) {
                if(item.getCrimeCharacterKey().equals("100")) {
                    String jsonValue = FileUtils.ReadAssetsFile(mContext, "crimeCharacterValue.json");
                    Response<List<sysDict>> listResponse = GsonUtils.fromJsonArray(jsonValue, sysDict.class);
                    return GsonUtils.successJson(listResponse.getData());
                }else {
                    return GsonUtils.successJson(SysDB.selectCrimeCharacterByCrime(item));
                }
            }
        }
        return GsonUtils.successJson(SysDB.selectCrimeCharacter());
    }

    public static String getCrimeEntrance(Context mContext) {
        String json = FileUtils.ReadAssetsFile(mContext, "crimeExport.json");
        Response<List<sysDict>> response = GsonUtils.fromJsonArray(json, sysDict.class);
//        return GsonUtils.successJson(SysDB.selectCrimeEntrance());
        return GsonUtils.successJson(response.getData());
    }

    public static String getCrimeTiming(Context mContext) {
        String json = FileUtils.ReadAssetsFile(mContext, "crimeTimingValue.json");
        Response<List<sysDict>> response = GsonUtils.fromJsonArray(json, sysDict.class);
        return GsonUtils.successJson(response.getData());
    }

    public static String getSelectObject() {
        return GsonUtils.successJson(SysDB.selectObject());
    }

    public static String getCrimeExport(Context mContext) {
        String json = FileUtils.ReadAssetsFile(mContext, "crimeExport.json");
        Response<List<sysDict>> response = GsonUtils.fromJsonArray(json, sysDict.class);
//        return GsonUtils.successJson(SysDB.selectCrimeEntrance());
        return GsonUtils.successJson(response.getData());
    }

    public static String getCrimeFeature() {
        return GsonUtils.successJson(SysDB.selectCrimeFeature());
    }

    public static String getIntrusiveMethod(Context mContext,String exportKey,String entranceKey) {
        String json = FileUtils.ReadAssetsFile(mContext, "intrusiveMethodValue.json");
        Response<List<sysDict>> response = GsonUtils.fromJsonArray(json, sysDict.class);
        return GsonUtils.successJson(SysDB.selectIntrusiveMethod(response.getData()));
    }

    public static String getSelectLocation(Context mContext, String caseTypeKey) {
        String json = FileUtils.ReadAssetsFile(mContext, "selectLocation.json");
        Response<List<SelectLocationBean>> response = GsonUtils.fromJsonArray(json, SelectLocationBean.class);
        for(SelectLocationBean item : response.getData()) {
            if(item.getCasetypeKey().equals(caseTypeKey)) {
                return GsonUtils.successJson(SysDB.selectLocationBySelectLocationBean(item));
            }
        }
        return GsonUtils.successJson(SysDB.selectLocation());
    }

    public static String getCrimePurpose() {
        return GsonUtils.successJson(SysDB.selectCrimePurpose());
    }
}
