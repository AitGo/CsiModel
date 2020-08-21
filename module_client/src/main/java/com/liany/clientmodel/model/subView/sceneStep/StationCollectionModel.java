package com.liany.clientmodel.model.subView.sceneStep;

import android.content.Context;

import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.contract.subView.sceneStep.StationCollectionContract;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.utils.LogUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * @创建者 ly
 * @创建时间 2020/4/7
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class StationCollectionModel implements StationCollectionContract.Model {
    private Context mContext;

    public StationCollectionModel(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void startCollect(CrimeItem item, callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/scene/startCollectionKct")
                .tag(this)
                .params("crimeId",item.getId())
                .params("lat", String.valueOf(item.getGpsLat()))
                .params("lon",String.valueOf(item.getGpsLon()))
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
    public void getKctData(CrimeItem item, callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/scene/getCollectionKct")
                .tag(this)
                .params("crimeId",item.getId())
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
    public void stopCollect(callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/scene/stopCollectionKct")
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
