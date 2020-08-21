package com.liany.clientmodel.model.subView.fragment;

import android.content.Context;

import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.contract.subView.fragment.SceneList_UploadContract;
import com.liany.clientmodel.utils.LogUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * @创建者 ly
 * @创建时间 2020/3/18
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class SceneList_UploadModel implements SceneList_UploadContract.Model {
    private Context mContext;

    public SceneList_UploadModel(Context mContext) {
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
    public void getSceneInfo(String crimeId, callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/scene/getInfo")
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
}
