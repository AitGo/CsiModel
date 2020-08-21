package com.liany.clientmodel.presenter.subView.sceneStep.step_window;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.contract.subView.sceneStep.step_window.Visit_AddContactsContract;
import com.liany.clientmodel.diagnose.ContactsEntity;
import com.liany.clientmodel.diagnose.Photo;
import com.liany.clientmodel.diagnose.Response;
import com.liany.clientmodel.model.subView.sceneStep.step_window.Visit_AddContactsModel;
import com.liany.clientmodel.utils.BitmapUtils;
import com.liany.clientmodel.utils.FileUtils;
import com.liany.clientmodel.utils.GsonUtils;
import com.liany.clientmodel.utils.LogUtils;
import com.liany.clientmodel.utils.ProgressUtils;
import com.liany.clientmodel.utils.StringUtils;
import com.liany.clientmodel.utils.ToastUtils;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @创建者 ly
 * @创建时间 2020/3/27
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class Visit_AddContactsPresenter implements Visit_AddContactsContract.Presenter {
    private Context mContext;
    private Visit_AddContactsContract.View addContactsView;
    private Visit_AddContactsContract.Model addContactsModel;

    public Visit_AddContactsPresenter(Context mContext, Visit_AddContactsContract.View addContactsView) {
        this.mContext = mContext;
        this.addContactsView = addContactsView;
        this.addContactsModel = new Visit_AddContactsModel(mContext);
    }

    @Override
    public void initViewDate(ContactsEntity contactsEntity) {
        if(contactsEntity != null) {
            addContactsView.setType(contactsEntity.getType());
            addContactsView.setName(contactsEntity.getName());
            addContactsView.setSex(contactsEntity.getSexKey());
            addContactsView.setId(contactsEntity.getPeopleId());
            addContactsView.setTel(contactsEntity.getTel());
            addContactsView.setAddress(contactsEntity.getAddress());
        }
    }

    @Override
    public void saveContract(ContactsEntity contactsEntity, String crimeId, boolean isCheck, List<Photo> photos) {
        if(isCheck) {
            if(!StringUtils.checkString(addContactsView.getName())) {
                addContactsView.showMsgDialog("");
                return;
            }
            if(!StringUtils.checkString(addContactsView.getTel())) {
                addContactsView.showMsgDialog("");
                return;
            }
            if(!StringUtils.checkString(addContactsView.getAddress())) {
                addContactsView.showMsgDialog("");
                return;
            }
        }
        contactsEntity.setName(addContactsView.getName());
        contactsEntity.setType(addContactsView.getType());
        contactsEntity.setSex(addContactsView.getSex());
        contactsEntity.setSexKey(addContactsView.getSexKey());
        contactsEntity.setPeopleId(addContactsView.getId());
        contactsEntity.setTel(addContactsView.getTel());
        contactsEntity.setAddress(addContactsView.getAddress());
        contactsEntity.setPhotos(photos);
        addContactsView.close(contactsEntity);
    }

    @Override
    public void startFigerPrint(ContactsEntity contactsEntity) {
        Map<Integer,String> result = new HashMap<>();
//                for(Photo photo : photos) {
//                    result.put(Integer.valueOf(photo.getRev1()),"");
//                }
        addContactsView.startFigerPrintDevice(result, Constants.path_photoDir);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data, ContactsEntity contactsEntity) {
        if(resultCode == Activity.RESULT_OK) {
                if (requestCode == Constants.REQUEST_COLLECT_SCAN) {
                //指纹图片
                /**
                 * 指纹读取返回的是Map<Integer,String>,key是指纹代码,value是指纹图片的完整路径
                 * 指纹代码：
                 * 11:  "右手拇指";
                 * 12:  "右手食指";
                 * 13:  "右手中指";
                 * 14:  "右手环指";
                 * 15:  "右手小指";
                 * 16:  "左手拇指";
                 * 17:  "左手食指";
                 * 18:  "左手中指";
                 * 19:  "左手环指";
                 * 20:  "左手小指";
                 * 97:  "右手不确定指位";
                 * 98:  "左手不确定指位";
                 * 99:  "其他不确定指位";
                 */
                Map<Integer,String> result = (Map<Integer, String>) data.getSerializableExtra("com.liany.easycollect.resultMap");
                for(int i : result.keySet()) {
                    //png图片转bmp
                    String filePath = result.get(i);
                    if(!StringUtils.checkString(filePath)) {
                        return;
                    }
//                    File file = new File(filePath);
                    if(FileUtils.checkFileExists(filePath)) {
                        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
                        //裁剪图片
                        bitmap = BitmapUtils.imageCrop(bitmap,640,640);
                        Bitmap resultBitmap = BitmapUtils.convertGray(bitmap);
                        String fileName = "Finger_" + StringUtils.long2FileName(new Date());
                        BitmapUtils.save8BitBmp(resultBitmap, Constants.path_photoDir + File.separator + fileName + ".bmp");
                        File file = new File(Constants.path_photoDir + File.separator + fileName + ".bmp");
                        String photoId = StringUtils.getUUID();
//                        ProgressUtils.showProgressDialog(mContext,"正在上传图片");
                        addContactsView.showProgress("");
                        addContactsModel.uploadPic(file,
                                photoId,
                                Constants.state_visit_people,
                                contactsEntity.getId(),
                                contactsEntity.getCrimeId(),
                                new callBack() {
                                    @Override
                                    public void onSuccess(String date) {
//                                        ProgressUtils.dismissProgressDialog();
                                        addContactsView.dismissProgress();
                                        Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                                        if(response.getCode() == 200) {
                                            Photo photo = new Photo();
                                            photo.setId(photoId);
                                            photo.setPath(Constants.path_photoDir + File.separator + fileName + ".bmp");
                                            photo.setServerPath(Constants.ipAddress + File.separator + contactsEntity.getCrimeId() + File.separator + response.getData());
                                            photo.setParentId(contactsEntity.getId());
                                            photo.setCrimeId(contactsEntity.getCrimeId());
                                            photo.setType("bmp");
                                            photo.setUUID(StringUtils.md5HashCode32(Constants.path_photoDir + File.separator + fileName + ".bmp"));
                                            photo.setFileName(fileName + ".bmp");
                                            photo.setWidth(String.valueOf(resultBitmap.getWidth()));
                                            photo.setHeight(String.valueOf(resultBitmap.getHeight()));
                                            photo.setState(Constants.state_visit_people);
                                            //采集的指位
                                            photo.setRev1(String.valueOf(i));
                                            addContactsView.addFigerPhoto(photo);
                                        }else {
                                            ToastUtils.showLong("上传图片错误:" + response.getMsg());
                                        }
                                    }

                                    @Override
                                    public void onFail(String msg) {
//                                        ProgressUtils.dismissProgressDialog();
                                        addContactsView.dismissProgress();
                                        ToastUtils.showLong("上传图片错误:" + msg);
                                    }
                                });
                        //删除原图
                        FileUtils.deleteFile(filePath);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        ToastUtils.showShort("指纹采集错误：文件未找到");
                    }
                }

            }else if(requestCode == Constants.REQUEST_VISIT_ADD_CONTACTS_FINGER) {
                ContactsEntity entity = (ContactsEntity) data.getSerializableExtra("entity");
                LogUtils.e("fingers" + entity.getPhotos().size());
                if(entity.getPhotos().size() > 0) {
                    addContactsView.setPhotos(entity.getPhotos());
                }
            }
        }
    }

    @Override
    public void deleteFingerPhoto(List<Photo> photos, int i, String contactsId) {
        if(i == -1) {
            ProgressUtils.showProgressDialog(mContext,"正在删除图片");
            //删除没有保存的指纹照片
            addContactsModel.deletePhotoFileList(photos, new callBack() {
                @Override
                public void onSuccess(String date) {
                    ProgressUtils.dismissProgressDialog();
                    Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                    if(response.getCode() == 200) {
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
        }else {
            ProgressUtils.showProgressDialog(mContext,"正在删除图片");
            addContactsModel.deletePhotoFile(photos.get(i), new callBack() {
                @Override
                public void onSuccess(String date) {
                    ProgressUtils.dismissProgressDialog();
                    Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                    if(response.getCode() == 200) {
                        addContactsView.removeFigerPhoto(i);
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
    }
}
