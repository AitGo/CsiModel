package com.liany.clientmodel.model.subView.sceneStep;

import android.content.Context;

import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.contract.subView.sceneStep.BaseInfoContract;
import com.liany.clientmodel.utils.LogUtils;
import com.liany.clientmodel.utils.SPUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * @创建者 ly
 * @创建时间 2020/3/23
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class BaseInfoModel implements BaseInfoContract.Model {
    private Context mContext;

    public BaseInfoModel(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public String getOrganId() {
        return (String) SPUtils.getParam(mContext, Constants.sp_organId,"");
    }

    @Override
    public void selectAccessInspectors(String organId, callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/sys/people")
                .tag(this)
                .params("organId",organId)
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
    public void selectWeatherCondition(callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/sys/weatherCondition")
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
    public void selectWindDirection(callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/sys/windDirection")
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
    public void selectSceneCondition(callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/sys/windDirection")
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
    public void selectIlluminationCondition(callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/sys/light")
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
    public void selectArea(String organId, callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/sys/area")
                .tag(this)
                .params("organId",organId)
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
    public void setAccessInspectors(String s) {
        SPUtils.setParam(mContext, Constants.sp_accessInspectors,s);
    }

    @Override
    public void setAccessInspectorsKey(String s) {
        SPUtils.setParam(mContext, Constants.sp_accessInspectorsKey,s);
    }

    @Override
    public void setAccessInspectorsId(String s) {
        SPUtils.setParam(mContext, Constants.sp_accessInspectorsId,s);
    }

    @Override
    public void createCrimeItem(String baseInfo, callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/scene/createScene")
                .tag(this)
                .params("baseInfo",baseInfo)
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
}
