package com.liany.csiclient.model;

import android.content.Context;

import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.utils.LogUtils;
import com.liany.clientmodel.utils.SPUtils;
import com.liany.csiclient.contract.Zz_SplashContract;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * @创建者 ly
 * @创建时间 2020/8/24
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class Zz_SplashModel implements Zz_SplashContract.Model {
    private Context mContext;

    public Zz_SplashModel(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void checkIsFirst(final callBack callBack) {
        OkGo.<String>get( Constants.ipAddress + "/user/checkFirst")
                .tag(this)
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

    @Override
    public void saveUrl(String result) {
        SPUtils.setParam(mContext,Constants.sp_url,result);
    }
}
