package com.liany.clientmodel.model.subView.sceneStep;

import android.content.Context;

import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.contract.subView.sceneStep.Comparison_PeopleContract;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.utils.LogUtils;
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
public class Comparison_PeopleModel implements Comparison_PeopleContract.Model {
    private Context mContext;

    public Comparison_PeopleModel(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void startComparisonData(CrimeItem crimeItem, String state, callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/compare/startCompareResult")
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
}
