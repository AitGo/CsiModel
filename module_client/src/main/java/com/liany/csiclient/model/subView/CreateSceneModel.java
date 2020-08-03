package com.liany.csiclient.model.subView;

import android.content.Context;

import com.liany.csiclient.base.Constants;
import com.liany.csiclient.callback.callBack;
import com.liany.csiclient.contract.subView.CreateSceneContract;
import com.liany.csiclient.diagnose.EvidenceEntity;
import com.liany.csiclient.diagnose.GoodEntity;
import com.liany.csiclient.diagnose.Photo;
import com.liany.csiclient.diagnose.WitnessEntity;
import com.liany.csiclient.utils.FileUtils;
import com.liany.csiclient.utils.LogUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/3/18
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class CreateSceneModel implements CreateSceneContract.Model {
    private Context mContext;

    public CreateSceneModel(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void saveCrime(String crimeItem, callBack callBack) {
        OkGo.<String>post( Constants.ipAddress + "/scene/save")
                .tag(this)
                .params("crimeItem",crimeItem)
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
    public void deleteExtract(GoodEntity item) {
        List<Photo> photos = item.getPhotos();
        for(Photo photo : photos) {
            FileUtils.deleteFile(photo.getPath());
            //删除原图
            FileUtils.deleteFile(photo.getPath().replace("compress_",""));
        }
    }

    @Override
    public void deleteWitness(WitnessEntity item) {
        FileUtils.deleteFile(item.getPhoto().getPath());
    }

    @Override
    public void deleteCamera(Photo item) {
        FileUtils.deleteFile(item.getPath());
        //删除原图
        FileUtils.deleteFile(item.getPath().replace("compress_",""));
    }

    @Override
    public void deleteMonitoring(Photo item) {
        FileUtils.deleteFile(item.getPath());
        //删除原图
        FileUtils.deleteFile(item.getPath().replace("compress_",""));
    }

    @Override
    public void deleteEvidence(EvidenceEntity item) {
        FileUtils.deleteFile(item.getPhoto().getPath());
        //删除原图
        FileUtils.deleteFile(item.getPhoto().getPath().replace("compress_",""));
    }

    @Override
    public void deleteImportant(Photo item) {
        FileUtils.deleteFile(item.getPath());
        //删除原图
        FileUtils.deleteFile(item.getPath().replace("compress_",""));
    }

    @Override
    public void deletelike(Photo item) {
        FileUtils.deleteFile(item.getPath());
        //删除原图
        FileUtils.deleteFile(item.getPath().replace("compress_",""));
    }

    @Override
    public void deletePositionPic(Photo item) {
        FileUtils.deleteFile(item.getPath());
        //删除原图
        FileUtils.deleteFile(item.getPath().replace("compress_",""));
    }

    @Override
    public void deleteFlat(Photo item) {
        FileUtils.deleteFile(item.getPath());
        //删除原图
        FileUtils.deleteFile(item.getPath().replace("Legend_",""));
        String flatPath = item.getPath().replace("Legend_", "");
        FileUtils.deleteFile(flatPath.replace(".png",".dwg"));
        FileUtils.deleteFile(flatPath.replace(".png",".pdf"));
        FileUtils.deleteFile(flatPath.replace(".png",".mwg"));
    }

    @Override
    public void deletePositionPhoto(Photo item) {
        //删除压缩图
        FileUtils.deleteFile(item.getPath());
        //删除原图
        FileUtils.deleteFile(item.getPath().replace("Legend_",""));
        FileUtils.deleteFile(item.getPath().replace("Legend_compress_",""));
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
