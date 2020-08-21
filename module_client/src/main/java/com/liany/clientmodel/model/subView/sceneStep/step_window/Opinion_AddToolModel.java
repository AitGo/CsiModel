package com.liany.clientmodel.model.subView.sceneStep.step_window;

import android.content.Context;

import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.contract.subView.sceneStep.step_window.Opinion_AddToolContract;
import com.liany.clientmodel.utils.LogUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;


/**
 * @创建者 ly
 * @创建时间 2020/4/3
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class Opinion_AddToolModel implements Opinion_AddToolContract.Model {
    private Context mContext;

    public Opinion_AddToolModel(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void selectToolType(callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/sys/toolType")
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
    public void selectToolSource(callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/sys/toolSource")
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
}
