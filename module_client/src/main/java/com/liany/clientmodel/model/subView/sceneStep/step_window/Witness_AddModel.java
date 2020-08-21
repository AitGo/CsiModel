package com.liany.clientmodel.model.subView.sceneStep.step_window;

import android.content.Context;

import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.contract.subView.sceneStep.step_window.Witness_AddContract;
import com.liany.clientmodel.diagnose.Photo;
import com.liany.clientmodel.utils.LogUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.io.File;

/**
 * @创建者 ly
 * @创建时间 2020/3/26
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class Witness_AddModel implements Witness_AddContract.Model {
    private Context mContext;

    public Witness_AddModel(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void uploadPic(File file, String photoId, String state, String parentId, String crimeId, callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/scene/uploadPic")
                .tag(this)
                .params("pic",file)
                .params("photoId",photoId)
                .params("state",state)
                .params("parentId",parentId)
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

    @Override
    public void deletePhoto(Photo pic, callBack callBack) {
        //删除服务器图片
        OkGo.<String>post( Constants.ipAddress + "/scene/deletePic")
                .tag(this)
                .params("photoId",pic.getId())
                .params("fileName",pic.getFileName())
                .params("crimeId",pic.getCrimeId())
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
