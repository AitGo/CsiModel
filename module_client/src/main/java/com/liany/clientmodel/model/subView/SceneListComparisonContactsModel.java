package com.liany.clientmodel.model.subView;

import android.content.Context;

import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.contract.subView.SceneListComparisonContactsContract;
import com.liany.clientmodel.utils.LogUtils;
import com.liany.clientmodel.utils.SPUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * @创建者 ly
 * @创建时间 2020/3/19
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class SceneListComparisonContactsModel implements SceneListComparisonContactsContract.Model {
    private Context mContext;

    public SceneListComparisonContactsModel(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void getCrimeList(callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/compare/contactsCrimeList")
                .tag(this)
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
}
