package com.liany.csiclient.presenter.subView.sceneStep.step_window;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import com.liany.csiclient.base.Constants;
import com.liany.csiclient.callback.callBack;
import com.liany.csiclient.contract.subView.sceneStep.step_window.Witness_AddContract;
import com.liany.csiclient.diagnose.Photo;
import com.liany.csiclient.diagnose.Response;
import com.liany.csiclient.diagnose.WitnessEntity;
import com.liany.csiclient.model.subView.sceneStep.step_window.Witness_AddModel;
import com.liany.csiclient.utils.FileUtils;
import com.liany.csiclient.utils.GsonUtils;
import com.liany.csiclient.utils.LogUtils;
import com.liany.csiclient.utils.ProgressUtils;
import com.liany.csiclient.utils.StringUtils;
import com.liany.csiclient.utils.ToastUtils;

import java.io.File;

import static android.app.Activity.RESULT_OK;

/**
 * @创建者 ly
 * @创建时间 2020/3/26
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class Witness_AddPresenter implements Witness_AddContract.Presenter {
    private Witness_AddContract.View view;
    private Witness_AddContract.Model model;
    private Context mContext;
    private String filePath;

    public Witness_AddPresenter(Context mContext, Witness_AddContract.View view) {
        this.mContext = mContext;
        this.view = view;
        model = new Witness_AddModel(mContext);
    }

    @Override
    public void getBirthday() {
        view.showTimerView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data, WitnessEntity entity) {
        if(resultCode == RESULT_OK) {
            if(requestCode == Constants.EVENT_PHOTO_TYPE_SIGN) {
                filePath = data.getStringExtra("SIGN");
                ProgressUtils.showProgressDialog(mContext,"正在上传图片");
                String photoId = StringUtils.getUUID();
                File file = new File(filePath);
                model.uploadPic(file,
                        photoId,
                        Constants.state_witness,
                        entity.getId(),
                        view.getCrimeId(),
                        new callBack() {
                    @Override
                    public void onSuccess(String date) {
                        ProgressUtils.dismissProgressDialog();
                        Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                        if(response.getCode() == 200) {
                            String uuid = StringUtils.md5HashCode32(file.getPath());
                            Photo photo = new Photo();
                            photo.setId(photoId);
                            photo.setParentId(entity.getId());
                            photo.setCrimeId(view.getCrimeId());
                            photo.setPath(filePath);
                            photo.setServerPath(Constants.ipAddress + File.separator + view.getCrimeId() +File.separator + response.getData());
                            photo.setFileName(file.getName());
                            Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
                            photo.setHeight(String.valueOf(bitmap.getHeight()));
                            photo.setWidth(String.valueOf(bitmap.getWidth()));
                            photo.setUUID(uuid);
                            photo.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
                            view.setSignImg(photo);
                        }else {
                            ToastUtils.showLong("上传图片错误:" + response.getMsg());
                        }
                    }

                    @Override
                    public void onFail(String msg) {
                        ProgressUtils.dismissProgressDialog();
                        ToastUtils.showLong("上传图片错误:" + msg);
                    }
                });
            }
//            else if(requestCode == Constants.REQUEST_WITNESS_ADD_IDCARD) {
//                if (data != null) {
//                    String contentType = data.getStringExtra(CameraActivity.KEY_CONTENT_TYPE);
//                    String filePath = FileUtils.getSaveFile(mContext).getAbsolutePath();
//                    if (!TextUtils.isEmpty(contentType)) {
//                        if (CameraActivity.CONTENT_TYPE_ID_CARD_FRONT.equals(contentType)) {
//                            recIDCard(IDCardParams.ID_CARD_SIDE_FRONT, filePath);
//                        }
//                        //身份证背面
////                        else if (CameraActivity.CONTENT_TYPE_ID_CARD_BACK.equals(contentType)) {
////                            recIDCard(IDCardParams.ID_CARD_SIDE_BACK, filePath);
////                        }
//                    }
//                }
//            }
        }
    }

    private void recIDCard(String idCardSide, String filePath) {
//        IDCardParams param = new IDCardParams();
//        param.setImageFile(new File(filePath));
//        // 设置身份证正反面
//        param.setIdCardSide(idCardSide);
//        // 设置方向检测
//        param.setDetectDirection(true);
//        // 设置图像参数压缩质量0-100, 越大图像质量越好但是请求时间越长。 不设置则默认值为20
//        param.setImageQuality(20);
//
//        OCR.getInstance(mContext).recognizeIDCard(param, new OnResultListener<IDCardResult>() {
//            @Override
//            public void onResult(IDCardResult result) {
//                if (result != null) {
//                    LogUtils.e(result.toString());
//                    view.readIDCardSuccess(result);
//                }
//            }
//
//            @Override
//            public void onError(OCRError error) {
//                LogUtils.e(error.getMessage());
//                view.readIDCardError(error);
//            }
//        });
    }

    @Override
    public void saveWitness(WitnessEntity entity, String crimeId, boolean isCheck) {
        if(isCheck) {
            if(!StringUtils.checkString(view.getWitnessName())) {
                view.showMsgDialog("");
                return;
            }
        }
        if(entity == null) {
            entity = new WitnessEntity();
        }
        if(!StringUtils.checkString(entity.getId())) {
            entity.setId(StringUtils.getUUID());
        }
        if(!StringUtils.checkString(filePath)) {
            ToastUtils.showLong("签名为空");
            return;
        }
        entity.setCrimeId(crimeId);
        entity.setWitnessAddress(view.getWitnessAddress());
        entity.setWitnessBirthday(view.getBirthdayDate());
        entity.setWitnessNumber(view.getWitnessNumber());
        entity.setWitnessSex(view.getWitnessSex());
        entity.setWitnessSexKey(view.getWitnessSexKey());
        entity.setWitnessName(view.getWitnessName());
//        addModel.updateWitness(entity);
        view.close(entity);
    }

    @Override
    public void setValueToView(WitnessEntity entity) {
        if(StringUtils.checkString(entity.getWitnessName())) {
            view.setWitnessName(entity.getWitnessName());
        }
        if(StringUtils.checkString(entity.getWitnessSex())) {
            view.setWitnessSex(entity.getWitnessSex());
            view.setWitnessSexKey(entity.getWitnessSexKey());
        }
        view.setBirthdayDate(entity.getWitnessBirthday());
        view.setWitnessNumber(entity.getWitnessNumber());
        view.setWitnessAddress(entity.getWitnessAddress());
        if(entity.getPhoto() != null) {
            if(StringUtils.checkString(entity.getPhoto().getServerPath())) {
                view.setSignImg(entity.getPhoto());
            }
            filePath = entity.getPhoto().getServerPath();
        }
    }
}
