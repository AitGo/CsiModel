package com.liany.csiclient.model.subView.fragment;

import android.content.Context;

import com.liany.csiclient.base.Constants;
import com.liany.csiclient.callback.callBack;
import com.liany.csiclient.contract.subView.fragment.SceneList_InuploadContract;
import com.liany.csiclient.diagnose.CrimeItem;
import com.liany.csiclient.utils.GsonUtils;
import com.liany.csiclient.utils.LogUtils;
import com.liany.csiclient.utils.SPUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/3/17
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class SceneList_InuploadModel implements SceneList_InuploadContract.Model {
    private Context mContext;

    public SceneList_InuploadModel(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void getPageList(String userName, String type, int offset, callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/scene/getPageList")
                .tag(this)
                .params("userName",userName)
                .params("type",type)
                .params("offset",offset)
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
    public void deleteScene(List<CrimeItem> crimeItems, callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/scene/delete")
                .tag(this)
                .params("crimeItems", GsonUtils.gsonString(crimeItems))
                .params("userName", (String) SPUtils.getParam(mContext, Constants.sp_userName,""))
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
    public void uploadScene(List<CrimeItem> crimeItems, callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/scene/upload")
                .tag(this)
                .params("crimeItems", GsonUtils.gsonString(crimeItems))
                .params("userId", (String) SPUtils.getParam(mContext, Constants.sp_userId,""))
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
