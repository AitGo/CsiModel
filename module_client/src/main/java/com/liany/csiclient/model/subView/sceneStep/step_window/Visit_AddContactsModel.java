package com.liany.csiclient.model.subView.sceneStep.step_window;

import android.content.Context;

import com.liany.csiclient.base.Constants;
import com.liany.csiclient.callback.callBack;
import com.liany.csiclient.contract.subView.sceneStep.step_window.Visit_AddContactsContract;
import com.liany.csiclient.diagnose.Photo;
import com.liany.csiclient.utils.GsonUtils;
import com.liany.csiclient.utils.LogUtils;
import com.liany.csiclient.utils.SPUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/3/27
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class Visit_AddContactsModel implements Visit_AddContactsContract.Model {
    private Context mContext;

    public Visit_AddContactsModel(Context mContext) {
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
    public void deletePhotoFile(Photo delPhoto, callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/scene/deletePic")
                .tag(this)
                .params("photoId",delPhoto.getId())
                .params("fileName", delPhoto.getFileName())
                .params("crimeId",delPhoto.getRev1())
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
//        for(Photo photo : delPhotos) {
//            FileUtils.deleteFile(photo.getPath());
//            FileUtils.deleteFile(photo.getPath().replace(".bmp",".png"));
//        }
    }

    @Override
    public void deletePhotoFileList(List<Photo> delPhotos, callBack callBack) {
        String gString = GsonUtils.gsonString(delPhotos);
        String crimeId = "";
        List<String> fileNameList = new ArrayList<>();
        for(Photo photo : delPhotos) {
            fileNameList.add(photo.getFileName());
            crimeId = photo.getParentId();
        }
        String gsonString = GsonUtils.gsonString(fileNameList);
        OkGo.<String>post( Constants.ipAddress + "/scene/deletePicList")
                .tag(this)
                .params("fileNameList", gsonString)
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
    public int getChooseContactsDevice() {
        return (int) SPUtils.getParam(mContext, Constants.sp_UseDevicePeople,0);
    }
}
