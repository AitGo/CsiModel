package com.liany.clientmodel.model.subView.sceneStep.step_window;

import android.content.Context;

import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.contract.subView.sceneStep.step_window.EvidenceAddNewFootContract;
import com.liany.clientmodel.diagnose.EvidenceEntity;
import com.liany.clientmodel.diagnose.Photo;
import com.liany.clientmodel.utils.GsonUtils;
import com.liany.clientmodel.utils.LogUtils;
import com.liany.clientmodel.utils.SPUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.io.File;

/**
 * @创建者 ly
 * @创建时间 2020/3/30
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class EvidenceAddNewFootModel implements EvidenceAddNewFootContract.Model {
    private Context mContext;

    public EvidenceAddNewFootModel(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void selectHandEvidence(callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/sys/handEvidence")
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

    @Override
    public void selectFootEvidence(callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/sys/footEvidence")
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

    @Override
    public void selectToolEvidence(callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/sys/toolEvidence")
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

    @Override
    public void selectHandMethod(callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/sys/handMethod")
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

    @Override
    public void selectFootMethod(callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/sys/footMethod")
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

    @Override
    public void selectToolMethod(callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/sys/toolMethod")
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

    @Override
    public void selectInfer(callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/sys/infer")
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

    @Override
    public void selectGetPeople(String organId, callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/sys/people")
                .tag(this)
                .params("organId",organId)
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
    public String getOrganId() {
        return (String) SPUtils.getParam(mContext, Constants.sp_organId,"");
    }

    @Override
    public int getChooseDevice() {
        return (int) SPUtils.getParam(mContext, Constants.sp_UseDevice,0);
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
    public void deletePic(Photo pic, callBack callBack) {
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

    @Override
    public void uploadEvidence(EvidenceEntity evidenceEntity, callBack callBack) {
        String gsonString = GsonUtils.gsonString(evidenceEntity);
        OkGo.<String>post( Constants.ipAddress + "/scene/uploadEvidence")
                .tag(this)
                .params("evidence",gsonString)
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
