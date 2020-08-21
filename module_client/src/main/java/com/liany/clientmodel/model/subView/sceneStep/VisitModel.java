package com.liany.clientmodel.model.subView.sceneStep;

import android.content.Context;

import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.contract.subView.sceneStep.VisitContract;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.utils.GsonUtils;
import com.liany.clientmodel.utils.LogUtils;
import com.liany.clientmodel.utils.SPUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * @创建者 ly
 * @创建时间 2020/3/26
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class VisitModel implements VisitContract.Model {
    private Context mContext;

    public VisitModel(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void startComparePeople(CrimeItem crimeItem, callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/compare/startCompare")
                .tag(this)
                .params("crime", GsonUtils.gsonString(crimeItem))
                .params("type", "2")//1：指纹比对，2：事主比对
                .params("userId", (String) SPUtils.getParam(mContext, Constants.sp_userId,""))
                .cacheKey("cacheGetKey")
                .cacheMode(CacheMode.NO_CACHE)
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
