package com.liany.csiclient.model;

import android.content.Context;

import com.liany.csiclient.base.Constants;
import com.liany.csiclient.callback.callBack;
import com.liany.csiclient.contract.MainContract;
import com.liany.csiclient.utils.LogUtils;
import com.liany.csiclient.utils.SPUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * @创建者 ly
 * @创建时间 2020/3/4
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class MainModel implements MainContract.Model {
    private Context mContext;

    public MainModel(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void getPresentScene(callBack callBack) {
        OkGo.<String>get( Constants.ipAddress + "/scene/getPresentScene")
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.e(response.body());
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        LogUtils.e(response.getException().getMessage());
                        callBack.onFail(response.getException().getMessage());
                    }
                });
    }

    @Override
    public void setPresentScene(String crimeId, callBack callBack) {
        OkGo.<String>get( Constants.ipAddress + "/scene/setPresentScene")
                .tag(this)
                .params("crimeId",crimeId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.e(response.body());
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        LogUtils.e(response.getException().getMessage());
                        callBack.onFail(response.getException().getMessage());
                    }
                });
    }

    @Override
    public void getSceneList(String type, callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/scene/getList")
                .tag(this)
                .params("type",type)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.e(response.body());
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        LogUtils.e(response.getException().getMessage());
                        callBack.onFail(response.getException().getMessage());
                    }
                });
    }

    @Override
    public String getUserName() {
        return (String) SPUtils.getParam(mContext, Constants.sp_userName,"");
    }

    @Override
    public String getLoginName() {
        return (String) SPUtils.getParam(mContext, Constants.sp_loginName,"");
    }

    @Override
    public String getAccessInspectors() {
        return (String) SPUtils.getParam(mContext, Constants.sp_accessInspectors,"");
    }

    @Override
    public String getAccessInspectorsKey() {
        return (String) SPUtils.getParam(mContext, Constants.sp_accessInspectorsKey,"");
    }

    @Override
    public String getUnitName() {
        return (String) SPUtils.getParam(mContext, Constants.sp_unitName,"");
    }

    @Override
    public String selectOrganId() {
        return (String) SPUtils.getParam(mContext, Constants.sp_organId,"");
    }

    @Override
    public String selectSysTechId() {
        return (String) SPUtils.getParam(mContext, Constants.sp_techId,"");
    }

    @Override
    public void saveMobileTypeSetting(float width, float height) {
        SPUtils.setParam(mContext, Constants.sp_mobileType_Width,width);
        SPUtils.setParam(mContext, Constants.sp_mobileType_Heigth,height);
    }

    @Override
    public String getUnitCode() {
        return (String) SPUtils.getParam(mContext, Constants.sp_unitCode,"");
    }
}
