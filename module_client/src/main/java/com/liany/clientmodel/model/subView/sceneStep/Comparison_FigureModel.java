package com.liany.clientmodel.model.subView.sceneStep;

import android.content.Context;

import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.contract.subView.sceneStep.Comparison_FigureContract;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.utils.LogUtils;
import com.liany.clientmodel.utils.SPUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * @创建者 ly
 * @创建时间 2020/4/8
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class Comparison_FigureModel implements Comparison_FigureContract.Model {
    private Context mContext;

    public Comparison_FigureModel(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void getComparisonData(CrimeItem crimeItem, String state, callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/compare/getCompareResult")
                .tag(this)
                .params("crimeId", crimeItem.getId())
                .params("state",state)
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
    public void getAllComparisonData(String state, callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/compare/getAllCompareResult")
                .tag(this)
                .params("userId", (String) SPUtils.getParam(mContext, Constants.sp_userId,""))
                .params("state", state)
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
