package com.liany.clientmodel.model.subView.sceneStep;

import android.content.Context;

import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.contract.subView.sceneStep.PhotoContract;
import com.liany.clientmodel.diagnose.Photo;
import com.liany.clientmodel.utils.FileUtils;
import com.liany.clientmodel.utils.GsonUtils;
import com.liany.clientmodel.utils.LogUtils;
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
public class PhotoModel implements PhotoContract.Model {
    private Context mContext;

    public PhotoModel(Context mContext) {
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
    public void deleteLikePic(Photo overViewPhoto) {

        FileUtils.deleteFile(overViewPhoto.getPath());
        //删除原图
        FileUtils.deleteFile(overViewPhoto.getPath().replace("compress_",""));
//        //删除数据库
//        daoSession.getOverViewPhotoDao().delete(overViewPhoto);
    }

    @Override
    public void deleteImportantPic(Photo importantPhoto) {

        FileUtils.deleteFile(importantPhoto.getPath());
        //删除原图
        FileUtils.deleteFile(importantPhoto.getPath().replace("compress_",""));

//        //删除服务器文件
//        WebServiceUtils.deleteServicePic(importantPhoto.getPhotoId(),importantPhoto.getCrimeId());
//        //删除数据库
//        daoSession.getImportantPhotoDao().delete(importantPhoto);
    }

    @Override
    public void deleteDetailPic(Photo photo) {

    }

}
