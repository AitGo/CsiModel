package com.liany.clientmodel.presenter.subView.sceneStep.step_window;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.contract.subView.sceneStep.step_window.EvidenceAddNewFaceContract;
import com.liany.clientmodel.diagnose.EvidenceEntity;
import com.liany.clientmodel.diagnose.Photo;
import com.liany.clientmodel.diagnose.Response;
import com.liany.clientmodel.diagnose.selectUser;
import com.liany.clientmodel.model.subView.sceneStep.step_window.EvidenceAddNewFaceModel;
import com.liany.clientmodel.utils.CompressUtils;
import com.liany.clientmodel.utils.FileUtils;
import com.liany.clientmodel.utils.GsonUtils;
import com.liany.clientmodel.utils.LogUtils;
import com.liany.clientmodel.utils.ProgressUtils;
import com.liany.clientmodel.utils.StringUtils;
import com.liany.clientmodel.utils.ToastUtils;
import com.liany.clientmodel.utils.getPhotoFromPhotoAlbum;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * @创建者 ly
 * @创建时间 2020/7/23
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class EvidenceAddNewFacePresenter implements EvidenceAddNewFaceContract.Presenter {
    private Context mContext;
    private EvidenceAddNewFaceContract.View addView;
    private EvidenceAddNewFaceContract.Model addModel;
    private TimePickerView pv_time;

    private String evidence = "痕迹";
    private String method = "提取方法";
    private String infer = "工具推断";
    private String getPeople = "提取人";
    private String photoPath = "";
    private String cropPath = "";

    public EvidenceAddNewFacePresenter(Context mContext, EvidenceAddNewFaceContract.View addView) {
        this.mContext = mContext;
        this.addView = addView;
        this.addModel = new EvidenceAddNewFaceModel(mContext);
    }

    @Override
    public void initValue(EvidenceEntity entity, int position) {
        if(position != -1) {
            if(entity != null && entity.getId() != null) {
                if(entity.getPhoto() != null) {
                    addView.setPhoto(entity.getPhoto());
                }
                addView.setEvidenceName(entity.getEvidenceName());
                addView.setTime(StringUtils.long2String(entity.getTime()));
                addView.setPeople(entity.getPeople());
            }
        }
    }

    @Override
    public void checkPermission() {
        addView.takePhoto();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data, final File outputImagepath, String crimeId, EvidenceEntity entity) {
        if(resultCode == RESULT_OK) {
            if (requestCode == Constants.REQUEST_EVIDENCE_EVIDENCE) {
                CompressUtils.compressPic(mContext, outputImagepath, "compress_", new CompressUtils.Callback() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(File file) {
                        ProgressUtils.showProgressDialog(mContext,"正在上传图片");
                        String photoId = StringUtils.getUUID();
                        addModel.uploadPic(file,
                                photoId,
                                Constants.state_evidence,
                                entity.getId(),
                                crimeId,
                                new callBack() {
                                    @Override
                                    public void onSuccess(String date) {
                                        ProgressUtils.dismissProgressDialog();
                                        Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                                        if(response.getCode() == 200) {
                                            Photo photo = new Photo();
                                            photo.setId(photoId);
                                            photo.setParentId(entity.getId());
                                            photo.setCrimeId(crimeId);
                                            photo.setPath(file.getPath());
                                            photo.setServerPath(Constants.ipAddress + File.separator + crimeId + File.separator + response.getData());
                                            photo.setWidth(BitmapFactory.decodeFile(file.getPath()).getWidth() + "");
                                            photo.setHeight(BitmapFactory.decodeFile(file.getPath()).getHeight() + "");
                                            photo.setFileName(file.getName());
                                            photo.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
                                            photo.setUUID(StringUtils.md5HashCode32(file.getPath()));
                                            photo.setState(Constants.state_evidence);
                                            addView.setPhoto(photo);
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
            }else if(requestCode == Constants.REQUEST_EVIDENCE_EVIDENCE_ALBUM) {
                String path = getPhotoFromPhotoAlbum.getRealPathFromUri(mContext, data.getData());
                File file1 = new File(path);
                CompressUtils.compressPic(mContext, file1, "compress_", new CompressUtils.Callback() {
                    @Override
                    public void onStart() {
                        LogUtils.e("onStart");
                    }

                    @Override
                    public void onSuccess(File file) {
                        String photoId = StringUtils.getUUID();
                        ProgressUtils.showProgressDialog(mContext,"正在上传图片");
                        addModel.uploadPic(file,
                                photoId,
                                Constants.state_evidence,
                                entity.getId(),
                                crimeId,
                                new callBack() {
                                    @Override
                                    public void onSuccess(String date) {
                                        ProgressUtils.dismissProgressDialog();
                                        Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                                        if(response.getCode() == 200) {
                                            Photo photo = new Photo();
                                            photo.setId(photoId);
                                            photo.setParentId(entity.getId());
                                            photo.setCrimeId(crimeId);
                                            photo.setPath(file.getPath());
                                            photo.setServerPath(Constants.ipAddress + File.separator + crimeId + File.separator + response.getData());
                                            photo.setWidth(BitmapFactory.decodeFile(file.getPath()).getWidth() + "");
                                            photo.setHeight(BitmapFactory.decodeFile(file.getPath()).getHeight() + "");
                                            photo.setFileName(file.getName());
                                            photo.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
                                            photo.setUUID(StringUtils.md5HashCode32(file.getPath()));
                                            photo.setState(Constants.state_evidence);
                                            addView.setPhoto(photo);
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
                        LogUtils.e("onError" + e.getMessage());
                    }
                });
            } else if(requestCode == Constants.REQUEST_CHECK_USER) {
                if(data.getStringExtra(Constants.SELECT_TITLE).equals(getPeople)) {
                    List<selectUser> users = (List<selectUser>) data.getSerializableExtra(Constants.RESULT_CHECK_USER);
                    addView.setPeople(users);
                }
            }
        }
    }

    @Override
    public void getEvidenceTime() {
        Calendar selectedDate;
        String sDate = addView.getEvidenceTime();
        if(sDate != null && !sDate.equals("") ) {
            selectedDate = StringUtils.String2Calendar(sDate);
        }else {
            selectedDate = Calendar.getInstance();
        }
        pv_time = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                addView.setEvidenceTime(StringUtils.Date2String(date,"yyyy年MM月dd日HH时mm分"));
            }
        }).setType(new boolean[]{true, true, true, true, true, false})// 默认全部显示
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .build();
        pv_time.show();
    }

    @Override
    public void deleteEvidencePhoto(EvidenceEntity entity, String photoPath) {
        if(photoPath != null) {
            addModel.deletePic(entity.getPhoto(), new callBack() {
                @Override
                public void onSuccess(String date) {
                    Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                    if(response.getCode() == 200) {

                    }else {
                        ToastUtils.showLong("删除图片错误:" + response.getMsg());
                    }
                }

                @Override
                public void onFail(String msg) {
                    ToastUtils.showLong("删除图片错误:" + msg);
                }
            });
        }
    }

    @Override
    public void saveEvidence(EvidenceEntity entity, String crimeId, boolean isCheck) {
        if(addView.getPhotoFile() == null || addView.getPhotoFile().equals("")) {
            Toast.makeText(mContext,"没有痕迹照片！",Toast.LENGTH_LONG).show();
        }else {
            if(entity == null) {
                entity = new EvidenceEntity();
            }
            if(isCheck) {
                if(!StringUtils.checkString(addView.getEvidenceName())) {
                    addView.showMsgDialog("");
                    return;
                }
                if(!StringUtils.checkString(addView.getPeople())) {
                    addView.showMsgDialog("");
                    return;
                }
            }
            if(entity.getId() == null) {
                entity.setId(StringUtils.getUUID());
            }
            entity.setCrimeId(crimeId);
            entity.setEvidenceCategory(addView.getEvidenceType());
            entity.setEvidenceName(addView.getEvidenceName());
            entity.setTime(StringUtils.String2long(addView.getTime()));
            entity.setPeople(addView.getPeople());
            //上传数据
            ProgressUtils.showProgressDialog(mContext,"正在提交");
            EvidenceEntity finalEntity = entity;
            addModel.uploadEvidence(entity, new callBack() {
                @Override
                public void onSuccess(String date) {
                    ProgressUtils.dismissProgressDialog();
                    Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                    if(response.getCode() == 200) {
                        ToastUtils.showLong("上传成功");
                        addView.close(finalEntity);
                    }
                }

                @Override
                public void onFail(String msg) {
                    ProgressUtils.dismissProgressDialog();
                    ToastUtils.showLong("上传错误");
                }
            });
        }
    }

    @Override
    public void getPeople() {
        String value = addView.getPeopleId();
        addModel.selectGetPeople(addModel.getOrganId(), new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<List<selectUser>> response = GsonUtils.fromJsonArray(date, selectUser.class);
                if(response.getCode() == 200) {
                    addView.startSelectUserView(Constants.REQUEST_CHECK_USER,getPeople,response.getData(),value);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void copyPhoto(String path, String crimeId, EvidenceEntity evidenceEntity) {
        int chooseDevice = addModel.getChooseDevice();
        if(chooseDevice == 2) {
            //判断是否有文件，如果没有提示用户需要拍照后下载到本地
            String flashAirFolderName = FileUtils.getFlashAirFolderName(path);
            if(StringUtils.checkString(flashAirFolderName)) {
                //得到最后一个文件
                String lastPhotoPath = FileUtils.getLastPhoto(path + File.separator + flashAirFolderName);
                if(StringUtils.checkString(lastPhotoPath)) {
                    ProgressUtils.showProgressDialog(mContext,"正在上传文件");
                    String photoId = StringUtils.getUUID();
                    //上传文件
                    addModel.uploadPic(new File(lastPhotoPath),
                            photoId,
                            Constants.state_evidence,
                            crimeId,
                            crimeId,
                            new callBack() {
                                @Override
                                public void onSuccess(String date) {
                                    ProgressUtils.dismissProgressDialog();
                                    Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                                    if(response.getCode() == 200) {
                                        //复制到photo目录，修改文件名
                                        String photoName = "Finger_" + StringUtils.long2FileName(new Date()) + ".jpg";
                                        FileUtils.copyRename(lastPhotoPath,photoPath + File.separator,photoName);
                                        String UUID = StringUtils.md5HashCode32(photoPath + File.separator + photoName);
                                        File file = new File(photoPath + File.separator + photoName);
                                        Photo photo = new Photo();
                                        photo.setId(photoId);
                                        photo.setParentId(evidenceEntity.getId());
                                        photo.setCrimeId(crimeId);
                                        photo.setPath(file.getPath());
                                        photo.setServerPath(Constants.ipAddress + File.separator + crimeId + File.separator + response.getData());
                                        photo.setWidth(BitmapFactory.decodeFile(file.getPath()).getWidth() + "");
                                        photo.setHeight(BitmapFactory.decodeFile(file.getPath()).getHeight() + "");
                                        photo.setFileName(file.getName());
                                        photo.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
                                        photo.setUUID(StringUtils.md5HashCode32(file.getPath()));
                                        photo.setState(Constants.state_evidence);
                                        addView.setPhoto(photo);
                                        //删除原文件夹
                                        FileUtils.deleteDirectory(path + File.separator + flashAirFolderName);
                                    }else {
                                        ToastUtils.showLong("上传文件错误:" + response.getMsg());
                                    }
                                }

                                @Override
                                public void onFail(String msg) {
                                    ProgressUtils.dismissProgressDialog();
                                    ToastUtils.showLong("上传文件错误:" + msg);
                                }
                            });
                }else {
                    ToastUtils.showLong("暂无新照片,请拍照并下载后重试");
                }
            }else {
                ToastUtils.showLong("暂无新照片,请拍照并下载后重试");
            }
        }
    }
}
