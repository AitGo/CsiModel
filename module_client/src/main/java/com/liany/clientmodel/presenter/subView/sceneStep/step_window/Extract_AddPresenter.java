package com.liany.clientmodel.presenter.subView.sceneStep.step_window;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.contract.subView.sceneStep.step_window.Extract_AddContract;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.diagnose.GoodEntity;
import com.liany.clientmodel.diagnose.Photo;
import com.liany.clientmodel.diagnose.Response;
import com.liany.clientmodel.diagnose.selectUser;
import com.liany.clientmodel.model.subView.sceneStep.step_window.Extract_AddModel;
import com.liany.clientmodel.utils.CompressUtils;
import com.liany.clientmodel.utils.GsonUtils;
import com.liany.clientmodel.utils.LogUtils;
import com.liany.clientmodel.utils.ProgressUtils;
import com.liany.clientmodel.utils.StringUtils;
import com.liany.clientmodel.utils.ToastUtils;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.File;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * @创建者 ly
 * @创建时间 2020/3/27
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class Extract_AddPresenter implements Extract_AddContract.Presenter {
    private Context mContext;
    private Extract_AddContract.Model model;
    private Extract_AddContract.View view;

    private String getPeople = "提取人";

    public Extract_AddPresenter(Context mContext, Extract_AddContract.View view) {
        this.mContext = mContext;
        this.view = view;
        this.model = new Extract_AddModel(mContext);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data, File file, String crimeId, String goodId) {
        if(resultCode == RESULT_OK) {
            if (requestCode == Constants.REQUEST_CHECK_USER) {
                if (data.getStringExtra(Constants.SELECT_TITLE).equals(getPeople)) {
                    List<selectUser> users = (List<selectUser>) data.getSerializableExtra(Constants.RESULT_CHECK_USER);
                    view.setCollectedName(users);
                }
            }else if(requestCode == Constants.SCAN_REQUEST_CODE) {
                if (null != data) {
                    Bundle bundle = data.getExtras();
                    if (bundle == null) {
                        return;
                    }
                    if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                        //处理登录
                        String result = bundle.getString(CodeUtils.RESULT_STRING);
                        view.setScan(result);
                        LogUtils.e(result);
                    } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                        LogUtils.e("scan fail");
                    }
                }
            } else if (requestCode == Constants.REQUEST_EVIDENCE_EVIDENCE) {
                CompressUtils.compressPic(mContext, file, "compress_", new CompressUtils.Callback() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(File file) {
                        ProgressUtils.showProgressDialog(mContext,"正在上传图片");
                        String photoId = StringUtils.getUUID();
                        model.uploadPic(file,
                                photoId,
                                Constants.state_extract,
                                goodId,
                                crimeId,
                                new callBack() {
                                    @Override
                                    public void onSuccess(String date) {
                                        ProgressUtils.dismissProgressDialog();
                                        Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                                        if(response.getCode() == 200) {
                                            String uuid = StringUtils.md5HashCode32(file.getPath());
                                            Photo photo = new Photo();
                                            photo.setId(photoId);
                                            photo.setParentId(goodId);
                                            photo.setCrimeId(crimeId);
                                            photo.setPath(file.getPath());
                                            photo.setServerPath(Constants.ipAddress + File.separator + crimeId +File.separator + response.getData());
                                            photo.setFileName(file.getName());
                                            Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
                                            photo.setHeight(String.valueOf(bitmap.getHeight()));
                                            photo.setWidth(String.valueOf(bitmap.getWidth()));
                                            photo.setUUID(uuid);
                                            photo.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
                                            view.setPhoto(photo);
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

                    @Override
                    public void onError(Throwable e) {

                    }
                });
            }
        }
    }

    @Override
    public void saveExtract(CrimeItem crimeItem, GoodEntity entity, boolean isCheck) {
        if(isCheck) {
            if(entity.getPhotos() == null) {
                ToastUtils.showLong("物品照片不能为空");
                return;
            }
            if(entity.getPhotos().size() == 0) {
                ToastUtils.showLong("物品照片不能为空");
                return;
            }
            if(!StringUtils.checkString(view.getScan())) {
                view.showMsgDialog("");
                return;
            }
            if(!StringUtils.checkString(view.getMaterialName())) {
                view.showMsgDialog("");
                return;
            }
            if(!StringUtils.checkString(view.getCollectedPosition())) {
                view.showMsgDialog("");
                return;
            }
            if(!StringUtils.checkString(view.getCollectedMethod())) {
                view.showMsgDialog("");
                return;
            }
        }
        entity.setCrimeId(crimeItem.getId());
//        entity.setCollectedName(view.getCollectedName());
//        entity.setCollectedIds();
        entity.setCollectedDate(view.getCollectedDate());
        entity.setCollectedMethod(view.getCollectedMethod());
        entity.setCollectedPosition(view.getCollectedPosition());
        entity.setMaterialName(view.getMaterialName());
        entity.setCollectedNum(view.getCollectedNum());
        entity.setRemark(view.getRemark());
        view.saveExtract(entity);
    }

    @Override
    public void getPeople() {
        String value = view.getCollectedId();
        model.selectGetPeople(model.getOrganId(), new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<List<selectUser>> response = GsonUtils.fromJsonArray(date, selectUser.class);
                if(response.getCode() == 200) {
                    view.startSelectUserView(Constants.REQUEST_CHECK_USER,getPeople,response.getData(),value);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void deleteExtractPic(Photo photo, String crimeId) {
        ProgressUtils.showProgressDialog(mContext,"正在删除图片");
        model.deletePhoto(photo, new callBack() {
            @Override
            public void onSuccess(String date) {
                ProgressUtils.dismissProgressDialog();
                Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                if(response.getCode() == 200) {
                    view.updateExtractList(photo);
                }else {
                    ToastUtils.showLong("删除图片错误:" + response.getMsg());
                }
            }

            @Override
            public void onFail(String msg) {
                ProgressUtils.dismissProgressDialog();
                ToastUtils.showLong("删除图片错误:" + msg);
            }
        });
    }

    @Override
    public void deleteExtractPicList(List<Photo> photos, String crimeId) {
//        ProgressUtils.showProgressDialog(mContext,"正在删除图片");
//        model.deletePhotoList(photos,crimeId, new callBack() {
//            @Override
//            public void onSuccess(String date) {
//                ProgressUtils.dismissProgressDialog();
//                Response<String> response = GsonUtils.fromJsonObject(date, String.class);
//                if(response.getCode() == 200) {
//                }else {
//                    ToastUtils.showLong("删除图片错误:" + response.getMsg());
//                }
//            }
//
//            @Override
//            public void onFail(String msg) {
//                ProgressUtils.dismissProgressDialog();
//                ToastUtils.showLong("删除图片错误:" + msg);
//            }
//        });
    }
}
