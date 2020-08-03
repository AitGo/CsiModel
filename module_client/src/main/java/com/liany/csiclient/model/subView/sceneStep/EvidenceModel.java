package com.liany.csiclient.model.subView.sceneStep;

import android.content.Context;

import com.liany.csiclient.base.Constants;
import com.liany.csiclient.callback.callBack;
import com.liany.csiclient.contract.subView.sceneStep.EvidenceContract;
import com.liany.csiclient.diagnose.CrimeItem;
import com.liany.csiclient.diagnose.EvidenceEntity;
import com.liany.csiclient.diagnose.Photo;
import com.liany.csiclient.utils.FileUtils;
import com.liany.csiclient.utils.GsonUtils;
import com.liany.csiclient.utils.LogUtils;
import com.liany.csiclient.utils.SPUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/3/26
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class EvidenceModel implements EvidenceContract.Model {
    private Context mContext;

    public EvidenceModel(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public void deleteEvidenceFingerPic(EvidenceEntity evidenceEntity) {
        FileUtils.deleteFile(evidenceEntity.getPhoto().getPath());
        //删除原图
//        FileUtils.deleteFile(evidenceEntity.getPhotoPath().replace("compress_",""));
        FileUtils.deleteFile(evidenceEntity.getPhoto().getPath().substring(0,evidenceEntity.getPhoto().getPath().lastIndexOf(".")) + ".bmp");
        FileUtils.deleteFile(evidenceEntity.getPhoto().getPath().substring(0,evidenceEntity.getPhoto().getPath().lastIndexOf(".")) + ".jpg");
    }

    @Override
    public void deleteEvidenceFootPic(EvidenceEntity evidenceEntity) {
        FileUtils.deleteFile(evidenceEntity.getPhoto().getPath());
        //删除原图
//        FileUtils.deleteFile(evidenceEntity.getPhotoPath().replace("compress_",""));
        FileUtils.deleteFile(evidenceEntity.getPhoto().getPath().substring(0,evidenceEntity.getPhoto().getPath().lastIndexOf(".")) + ".bmp");
        FileUtils.deleteFile(evidenceEntity.getPhoto().getPath().substring(0,evidenceEntity.getPhoto().getPath().lastIndexOf(".")) + ".jpg");
    }

    @Override
    public void deleteEvidenceFacePic(EvidenceEntity evidenceEntity) {
        FileUtils.deleteFile(evidenceEntity.getPhoto().getPath());
        //删除原图
        FileUtils.deleteFile(evidenceEntity.getPhoto().getPath().replace("compress_",""));
    }

    @Override
    public void deleteEvidenceOtherPic(EvidenceEntity evidenceEntity) {
        FileUtils.deleteFile(evidenceEntity.getPhoto().getPath());
        //删除原图
        FileUtils.deleteFile(evidenceEntity.getPhoto().getPath().replace("compress_",""));
    }


    @Override
    public void deleteMonitoringPic(Photo monitoringPhoto) {
        FileUtils.deleteFile(monitoringPhoto.getPath());
        //删除原图
        FileUtils.deleteFile(monitoringPhoto.getPath().replace("compress_",""));
//        //删除数据库
//        daoSession.getMonitoringPhotoDao().delete(monitoringPhoto);
    }

    @Override
    public void deleteCameraPic(Photo cameraPhoto) {
        FileUtils.deleteFile(cameraPhoto.getPath());
        //删除原图
        FileUtils.deleteFile(cameraPhoto.getPath().replace("compress_",""));
//        //删除数据库
//        daoSession.getCameraPhotoDao().delete(cameraPhoto);
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
    public void uploadPics(List<File> files, List<String> photoIds, String state, String parentId, String crimeId, callBack callBack) {
        String s = GsonUtils.gsonString(photoIds);
        OkGo.<String>post( Constants.ipAddress + "/scene/uploadPics")
                .tag(this)
                .addFileParams("pics",files)
                .params("photoIds", s)
                .params("state", state)
                .params("parentId", parentId)
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
    public void deletePic(Photo photo, callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/scene/deletePic")
                .tag(this)
                .params("photoId",photo.getId())
                .params("fileName",photo.getFileName())
                .params("crimeId",photo.getCrimeId())
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
    public void startCompareEvidence(CrimeItem crimeItem, callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/compare/startCompare")
                .tag(this)
                .params("crime", GsonUtils.gsonString(crimeItem))
                .params("type","1")
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

    @Override
    public void startCompareFootEvidence(CrimeItem crimeItem, callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/compare/startCompare")
                .tag(this)
                .params("crime", GsonUtils.gsonString(crimeItem))
                .params("type","3")
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

    @Override
    public void deleteEvidence(String evidenceId, String crimeId, callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/scene/deleteEvidence")
                .tag(this)
                .params("evidenceId", evidenceId)
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
    public void getCompareResult(String crimeId, String state, callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/compare/getCompareResult")
                .tag(this)
                .params("crimeId", crimeId)
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
    public void sendCompare(String evidenceId, String type, callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/compare/startCompareOne")
                .tag(this)
                .params("evidenceId", evidenceId)
                .params("type",type)
                .params("userId",(String) SPUtils.getParam(mContext, Constants.sp_userId,""))
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
