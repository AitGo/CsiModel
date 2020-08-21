package ynga.liany.csiclient.model;

import android.content.Context;

import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.utils.LogUtils;
import com.liany.clientmodel.utils.SPUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import ynga.liany.csiclient.contract.LoginContract;

/**
 * @创建者 ly
 * @创建时间 2020/3/4
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class LoginModel implements LoginContract.Model {
    private Context mContext;

    public LoginModel(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void updateDB(String url, callBack callBack) {
        OkGo.<String>get( Constants.ipAddress + "/user/updateDB")
                .tag(this)
                .params("url",url)
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

    @Override
    public void login(String account, String password, callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/user/login")
                .tag(this)
                .params("account",account)
                .params("password",password)
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
    public void checkIsFirst(callBack callBack) {
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
    public void autoLogin(String account, callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/user/autoLogin")
                .tag(this)
                .params("account",account)
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
    public void checkVersion(String versionCode, String versionName, callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/user/checkVersion")
                .tag(this)
                .params("versionCode",versionCode)
                .params("versionName",versionName)
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
