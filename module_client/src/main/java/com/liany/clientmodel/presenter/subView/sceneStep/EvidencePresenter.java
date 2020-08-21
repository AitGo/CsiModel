package com.liany.clientmodel.presenter.subView.sceneStep;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;

import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.contract.subView.sceneStep.EvidenceContract;
import com.liany.clientmodel.diagnose.ComparePhoto;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.diagnose.EvidenceEntity;
import com.liany.clientmodel.diagnose.Photo;
import com.liany.clientmodel.diagnose.Response;
import com.liany.clientmodel.model.subView.sceneStep.EvidenceModel;
import com.liany.clientmodel.utils.CompressUtils;
import com.liany.clientmodel.utils.FileUtils;
import com.liany.clientmodel.utils.GsonUtils;
import com.liany.clientmodel.utils.LogUtils;
import com.liany.clientmodel.utils.ProgressUtils;
import com.liany.clientmodel.utils.StringUtils;
import com.liany.clientmodel.utils.ToastUtils;
import com.liany.clientmodel.utils.getPhotoFromPhotoAlbum;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * @创建者 ly
 * @创建时间 2020/3/26
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class EvidencePresenter implements EvidenceContract.Presenter {
    private Context mContext;
    private EvidenceContract.View evidenceView;
    private EvidenceContract.Model evidenceModel;

    public EvidencePresenter(Context mContext, EvidenceContract.View evidenceView) {
        this.mContext = mContext;
        this.evidenceView = evidenceView;
        this.evidenceModel = new EvidenceModel(mContext);
    }

    @Override
    public void addPosition(int code) {
        evidenceView.takePhoto(code);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(requestCode == Constants.REQUEST_EVIDENCE_MONITORING || requestCode == Constants.REQUEST_EVIDENCE_CAMERA) {
            boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
            if (cameraAccepted) {
                evidenceView.takePhoto(requestCode);
            } else {
                //用户授权拒绝
                LogUtils.e("get premissions fail");
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data, final File file, CrimeItem crimeItem) {
        if (resultCode == RESULT_OK) {
            if (requestCode == Constants.REQUEST_EVIDENCE_ADD_FINGER) {
                EvidenceEntity entity = (EvidenceEntity) data.getSerializableExtra("evidenceEntity");
                int position = data.getIntExtra("position", -1);
                evidenceView.addFingerEvidencePic(entity, position);
//                addDetail(new File(entity.getPhoto().getPath()),crimeItem);
            } else if(requestCode == Constants.REQUEST_EVIDENCE_ADD_FOOT) {
                EvidenceEntity entity = (EvidenceEntity) data.getSerializableExtra("evidenceEntity");
                int position = data.getIntExtra("position", -1);
                evidenceView.addFootEvidencePic(entity, position);
//                addDetail(new File(entity.getPhoto().getPath()),crimeItem);
            } else if(requestCode == Constants.REQUEST_EVIDENCE_ADD_FACE) {
                EvidenceEntity entity = (EvidenceEntity) data.getSerializableExtra("evidenceEntity");
                int position = data.getIntExtra("position", -1);
                evidenceView.addFaceEvidencePic(entity, position);
            } else if(requestCode == Constants.REQUEST_EVIDENCE_ADD_OTHER) {
                EvidenceEntity entity = (EvidenceEntity) data.getSerializableExtra("evidenceEntity");
                int position = data.getIntExtra("position", -1);
                evidenceView.addOtherEvidencePic(entity, position);
//                addDetail(new File(entity.getPhoto().getPath()),crimeItem);
            } else if (requestCode == Constants.REQUEST_EVIDENCE_MONITORING) {
                CompressUtils.compressPic(mContext, file, "compress_", new CompressUtils.Callback() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(File file) {
                        ProgressUtils.showProgressDialog(mContext,"正在上传图片");
                        String photoId = StringUtils.getUUID();
                        evidenceModel.uploadPic(file,
                                photoId,
                                Constants.state_monitoring,
                                crimeItem.getId(),
                                crimeItem.getId(),
                                new callBack() {
                                    @Override
                                    public void onSuccess(String date) {
                                        ProgressUtils.dismissProgressDialog();
                                        Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                                        if(response.getCode() == 200) {
                                            Photo entity = new Photo();
                                            entity.setId(photoId);
                                            entity.setParentId(crimeItem.getId());
                                            entity.setCrimeId(crimeItem.getId());
                                            entity.setFileName(file.getName());
                                            entity.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
                                            entity.setWidth(BitmapFactory.decodeFile(file.getPath()).getWidth() + "");
                                            entity.setHeight(BitmapFactory.decodeFile(file.getPath()).getHeight() + "");
                                            entity.setPath(file.getPath());
                                            entity.setServerPath(Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + response.getData());
                                            entity.setUUID(StringUtils.md5HashCode32(entity.getPath()));
                                            entity.setState(Constants.state_monitoring);
                                            evidenceView.addMonitoringPic(entity);
//                                            addDetail(file,crimeItem);
                                        } else {
                                            ToastUtils.showLong("上传图片错误:" + response.getMsg());
                                        }
                                    }

                                    @Override
                                    public void onFail(String msg) {
                                        ToastUtils.showLong("上传图片错误:" + msg);
                                        ProgressUtils.dismissProgressDialog();
                                    }
                                });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
            } else if (requestCode == Constants.REQUEST_EVIDENCE_MONITORING_img) {
                String path = getPhotoFromPhotoAlbum.getRealPathFromUri(mContext, data.getData());
                File file1 = new File(path);
                CompressUtils.compressPic(mContext, file1, "compress_", new CompressUtils.Callback() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(File file) {
                        ProgressUtils.showProgressDialog(mContext,"正在上传图片");
                        String photoId = StringUtils.getUUID();
                        evidenceModel.uploadPic(file,
                                photoId,
                                Constants.state_monitoring,
                                crimeItem.getId(),
                                crimeItem.getId(),
                                new callBack() {
                                    @Override
                                    public void onSuccess(String date) {
                                        ProgressUtils.dismissProgressDialog();
                                        Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                                        if(response.getCode() == 200) {
                                            Photo entity = new Photo();
                                            entity.setId(photoId);
                                            entity.setParentId(crimeItem.getId());
                                            entity.setCrimeId(crimeItem.getId());
                                            entity.setFileName(file.getName());
                                            entity.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
                                            entity.setWidth(BitmapFactory.decodeFile(file.getPath()).getWidth() + "");
                                            entity.setHeight(BitmapFactory.decodeFile(file.getPath()).getHeight() + "");
                                            entity.setPath(file.getPath());
                                            entity.setServerPath(Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + response.getData());
                                            entity.setUUID(StringUtils.md5HashCode32(entity.getPath()));
                                            entity.setState(Constants.state_monitoring);
                                            evidenceView.addMonitoringPic(entity);
//                                            addDetail(file,crimeItem);
                                        } else {
                                            ToastUtils.showLong("上传图片错误:" + response.getMsg());
                                        }
                                    }

                                    @Override
                                    public void onFail(String msg) {
                                        ToastUtils.showLong("上传图片错误:" + msg);
                                        ProgressUtils.dismissProgressDialog();
                                    }
                                });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
            } else if (requestCode == Constants.REQUEST_EVIDENCE_MONITORING_img_more) {
                ProgressUtils.showProgressDialog(mContext,"正在上传图片");
                // 结果回调
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                List<File> files = new ArrayList<>();
                List<String> photoIds = new ArrayList<>();
                for(LocalMedia media : selectList) {
                    String compressPath = media.getCompressPath();
                    if(!StringUtils.checkString(compressPath)) {
                        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            compressPath = media.getAndroidQToPath();
                        }else {
                            compressPath = media.getRealPath();
                        }
                    }
                    File file1 = new File(compressPath);
                    files.add(file1);
                    photoIds.add(StringUtils.getUUID());
                }
                evidenceModel.uploadPics(files,
                        photoIds,
                        Constants.state_monitoring,
                        crimeItem.getId(),
                        crimeItem.getId(), new callBack() {
                            @Override
                            public void onSuccess(String date) {
                                ProgressUtils.dismissProgressDialog();
                                Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                                if(response.getCode() == 200) {
                                    List<Photo> photos = new ArrayList<>();
                                    for(int i = 0; i < files.size(); i++) {
                                        File file = files.get(i);
                                        String photoId = photoIds.get(i);
                                        Photo entity = new Photo();
                                        entity.setId(photoId);
                                        entity.setParentId(crimeItem.getId());
                                        entity.setCrimeId(crimeItem.getId());
                                        entity.setFileName(file.getName());
                                        entity.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
                                        entity.setWidth(BitmapFactory.decodeFile(file.getPath()).getWidth() + "");
                                        entity.setHeight(BitmapFactory.decodeFile(file.getPath()).getHeight() + "");
                                        entity.setPath(file.getPath());
                                        entity.setServerPath(Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + file.getName());
                                        entity.setUUID(StringUtils.md5HashCode32(entity.getPath()));
                                        entity.setState(Constants.state_monitoring);
                                        photos.add(entity);
                                    }
                                    evidenceView.addMonitoringPics(photos);
                                }else {
                                    ToastUtils.showLong("上传图片错误:" + response.getMsg());
                                }
                                //删除图片压缩缓存
                                evidenceView.clearCache();
                            }

                            @Override
                            public void onFail(String msg) {
                                ToastUtils.showLong("上传图片错误:" + msg);
                                ProgressUtils.dismissProgressDialog();
                                //删除图片压缩缓存
                                evidenceView.clearCache();
                            }
                        });
            } else if (requestCode == Constants.REQUEST_EVIDENCE_CAMERA) {
                CompressUtils.compressPic(mContext, file, "compress_", new CompressUtils.Callback() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(File file) {
                        ProgressUtils.showProgressDialog(mContext,"正在上传图片");
                        String photoId = StringUtils.getUUID();
                        evidenceModel.uploadPic(file,
                                photoId,
                                Constants.state_camera,
                                crimeItem.getId(),
                                crimeItem.getId(),
                                new callBack() {
                                    @Override
                                    public void onSuccess(String date) {
                                        ProgressUtils.dismissProgressDialog();
                                        Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                                        if(response.getCode() == 200) {
                                            Photo entity = new Photo();
                                            entity.setId(photoId);
                                            entity.setCrimeId(crimeItem.getId());
                                            entity.setParentId(crimeItem.getId());
                                            entity.setFileName(file.getName());
                                            entity.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
                                            entity.setWidth(BitmapFactory.decodeFile(file.getPath()).getWidth() + "");
                                            entity.setHeight(BitmapFactory.decodeFile(file.getPath()).getHeight() + "");
                                            entity.setPath(file.getPath());
                                            entity.setServerPath(Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + response.getData());
                                            entity.setUUID(StringUtils.md5HashCode32(entity.getPath()));
                                            entity.setState(Constants.state_camera);
                                            evidenceView.addCameraPic(entity);
//                                            addDetail(file,crimeItem);
                                        } else {
                                            ToastUtils.showLong("上传图片错误:" + response.getMsg());
                                        }
                                    }

                                    @Override
                                    public void onFail(String msg) {
                                        ToastUtils.showLong("上传图片错误:" + msg);
                                        ProgressUtils.dismissProgressDialog();
                                    }
                                });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
            }else if (requestCode == Constants.REQUEST_EVIDENCE_CAMERA_img) {
                String path = getPhotoFromPhotoAlbum.getRealPathFromUri(mContext, data.getData());
                File file1 = new File(path);
                CompressUtils.compressPic(mContext, file1, "compress_", new CompressUtils.Callback() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(File file) {
                        ProgressUtils.showProgressDialog(mContext, "正在上传图片");
                        String photoId = StringUtils.getUUID();
                        evidenceModel.uploadPic(file,
                                photoId,
                                Constants.state_camera,
                                crimeItem.getId(),
                                crimeItem.getId(),
                                new callBack() {
                                    @Override
                                    public void onSuccess(String date) {
                                        ProgressUtils.dismissProgressDialog();
                                        Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                                        if (response.getCode() == 200) {
                                            Photo entity = new Photo();
                                            entity.setId(photoId);
                                            entity.setCrimeId(crimeItem.getId());
                                            entity.setParentId(crimeItem.getId());
                                            entity.setFileName(file.getName());
                                            entity.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
                                            entity.setWidth(BitmapFactory.decodeFile(file.getPath()).getWidth() + "");
                                            entity.setHeight(BitmapFactory.decodeFile(file.getPath()).getHeight() + "");
                                            entity.setPath(file.getPath());
                                            entity.setServerPath(Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + response.getData());
                                            entity.setUUID(StringUtils.md5HashCode32(entity.getPath()));
                                            entity.setState(Constants.state_camera);
                                            evidenceView.addCameraPic(entity);
                                        } else {
                                            ToastUtils.showLong("上传图片错误:" + response.getMsg());
                                        }
                                    }

                                    @Override
                                    public void onFail(String msg) {
                                        ToastUtils.showLong("上传图片错误:" + msg);
                                        ProgressUtils.dismissProgressDialog();
                                    }
                                });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
            } else if (requestCode == Constants.REQUEST_EVIDENCE_CAMERA_img_more) {
                ProgressUtils.showProgressDialog(mContext,"正在上传图片");
                // 结果回调
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                List<File> files = new ArrayList<>();
                List<String> photoIds = new ArrayList<>();
                for(LocalMedia media : selectList) {
                    String compressPath = media.getCompressPath();
                    if(!StringUtils.checkString(compressPath)) {
                        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            compressPath = media.getAndroidQToPath();
                        }else {
                            compressPath = media.getRealPath();
                        }
                    }
                    File file1 = new File(compressPath);
                    files.add(file1);
                    photoIds.add(StringUtils.getUUID());
                }
                evidenceModel.uploadPics(files,
                        photoIds,
                        Constants.state_camera,
                        crimeItem.getId(),
                        crimeItem.getId(), new callBack() {
                            @Override
                            public void onSuccess(String date) {
                                ProgressUtils.dismissProgressDialog();
                                Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                                if(response.getCode() == 200) {
                                    List<Photo> photos = new ArrayList<>();
                                    for(int i = 0; i < files.size(); i++) {
                                        File file = files.get(i);
                                        String photoId = photoIds.get(i);
                                        Photo entity = new Photo();
                                        entity.setId(photoId);
                                        entity.setCrimeId(crimeItem.getId());
                                        entity.setParentId(crimeItem.getId());
                                        entity.setFileName(file.getName());
                                        entity.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
                                        entity.setWidth(BitmapFactory.decodeFile(file.getPath()).getWidth() + "");
                                        entity.setHeight(BitmapFactory.decodeFile(file.getPath()).getHeight() + "");
                                        entity.setPath(file.getPath());
                                        entity.setServerPath(Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + file.getName());
                                        entity.setUUID(StringUtils.md5HashCode32(entity.getPath()));
                                        entity.setState(Constants.state_camera);
                                        photos.add(entity);
                                    }
                                    evidenceView.addCameraPics(photos);
                                }else {
                                    ToastUtils.showLong("上传图片错误:" + response.getMsg());
                                }
                                //删除图片压缩缓存
                                evidenceView.clearCache();
                            }

                            @Override
                            public void onFail(String msg) {
                                ToastUtils.showLong("上传图片错误:" + msg);
                                ProgressUtils.dismissProgressDialog();
                                //删除图片压缩缓存
                                evidenceView.clearCache();
                            }
                        });
            }
        }
    }

    private void addDetail(File file, CrimeItem crimeItem) {
        FileUtils.copyRename(file.getAbsolutePath(),
                Constants.path_photoDir + File.separator,
                "detail_" + file.getName());
        File file1 = new File(Constants.path_photoDir + File.separator + "detail_" + file.getName());
        String photoId = StringUtils.getUUID();
        evidenceModel.uploadPic(new File(Constants.path_photoDir + File.separator + "detail_" + file.getName())
                ,photoId,
                Constants.state_detail,
                crimeItem.getId(),
                crimeItem.getId(),
                new callBack() {
                    @Override
                    public void onSuccess(String date) {
                        ProgressUtils.dismissProgressDialog();
                        Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                        if(response.getCode() == 200) {
                            Photo photo = new Photo();
                            photo.setId(photoId);
                            photo.setParentId(crimeItem.getId());
                            photo.setCrimeId(crimeItem.getId());
                            photo.setPath(file1.getPath());
                            photo.setServerPath(Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + response.getData());
                            photo.setWidth(BitmapFactory.decodeFile(file1.getPath()).getWidth() + "");
                            photo.setHeight(BitmapFactory.decodeFile(file1.getPath()).getHeight() + "");
                            photo.setFileName(file1.getName());
                            photo.setType(file1.getName().substring(file1.getName().lastIndexOf(".") + 1));
                            photo.setUUID(StringUtils.md5HashCode32(file1.getPath()));
//                    photo.setRev1("0");
                            photo.setState(Constants.state_detail);
                            evidenceView.addDetail(photo);
                        }else {
                            ToastUtils.showLong("上传图片错误:" + response.getMsg());
                        }
                    }

                    @Override
                    public void onFail(String msg) {
                        ToastUtils.showLong("上传图片错误:" + msg);
                        ProgressUtils.dismissProgressDialog();
                    }
                });
    }

    @Override
    public void deleteEvidenceFingerPic(EvidenceEntity evidenceEntity) {
        evidenceModel.deletePic(evidenceEntity.getPhoto(), new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                if(response.getCode() == 200) {
                    evidenceView.updateEvidenceFingerList(evidenceEntity);
                    evidenceModel.deleteEvidenceFingerPic(evidenceEntity);
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

    @Override
    public void deleteEvidenceFootPic(EvidenceEntity evidenceEntity) {
        evidenceModel.deletePic(evidenceEntity.getPhoto(), new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                if(response.getCode() == 200) {
                    evidenceView.updateEvidenceFootList(evidenceEntity);
                    evidenceModel.deleteEvidenceFootPic(evidenceEntity);
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

    @Override
    public void deleteEvidenceOtherPic(EvidenceEntity evidenceEntity) {
        evidenceModel.deletePic(evidenceEntity.getPhoto(), new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                if(response.getCode() == 200) {
                    evidenceView.updateEvidenceOtherList(evidenceEntity);
                    evidenceModel.deleteEvidenceOtherPic(evidenceEntity);
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

    @Override
    public void deleteMonitoringPic(Photo monitoringPhoto) {
        evidenceModel.deletePic(monitoringPhoto, new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                if(response.getCode() == 200) {
                    evidenceView.updateMonitoringList(monitoringPhoto);
                    evidenceModel.deleteMonitoringPic(monitoringPhoto);
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

    @Override
    public void deleteCameraPic(Photo cameraPhoto) {
        evidenceModel.deletePic(cameraPhoto, new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                if(response.getCode() == 200) {
                    evidenceView.updateCameraList(cameraPhoto);
                    evidenceModel.deleteCameraPic(cameraPhoto);
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

    @Override
    public void compareFinger(CrimeItem crimeItem) {
        List<EvidenceEntity> fingers = new ArrayList<>();
        for(EvidenceEntity evidenceEntity : crimeItem.getEvidenceItem()) {
            if(evidenceEntity.getEvidenceCategory().equals("手印")) {
                fingers.add(evidenceEntity);
            }
        }
        if(fingers.size() > 0) {
            ProgressUtils.showProgressDialog(mContext,"正在提交数据");
            evidenceModel.startCompareEvidence(crimeItem, new callBack() {
                @Override
                public void onSuccess(String date) {
                    ProgressUtils.dismissProgressDialog();
                    Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                    if(response.getCode() == 200) {
                        ToastUtils.showLong(response.getData());
                    }else {
                        ToastUtils.showLong(response.getMsg());
                    }
                }

                @Override
                public void onFail(String msg) {
                    ProgressUtils.dismissProgressDialog();
                    ToastUtils.showLong("提交现场比对错误:" + msg);
                }
            });
        }else {
            ToastUtils.showLong("暂无现场指纹，请提交后重试");
        }
    }

    @Override
    public void compareFoot(CrimeItem crimeItem) {
        List<EvidenceEntity> entities = new ArrayList<>();
        for(EvidenceEntity evidenceEntity : crimeItem.getEvidenceItem()) {
            if(evidenceEntity.getEvidenceCategory().equals("足迹")) {
                entities.add(evidenceEntity);
            }
        }
        if(entities.size() > 0) {
            ProgressUtils.showProgressDialog(mContext,"正在提交数据");
            evidenceModel.startCompareFootEvidence(crimeItem, new callBack() {
                @Override
                public void onSuccess(String date) {
                    ProgressUtils.dismissProgressDialog();
                    Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                    if(response.getCode() == 200) {
                        ToastUtils.showLong(response.getData());
                    }else {
                        ToastUtils.showLong(response.getMsg());
                    }
                }

                @Override
                public void onFail(String msg) {
                    ProgressUtils.dismissProgressDialog();
                    ToastUtils.showLong("提交现场比对错误:" + msg);
                }
            });
        }else {
            ToastUtils.showLong("暂无现场足迹，请提交后重试");
        }
    }

    @Override
    public void deleteEvidence(EvidenceEntity evidenceEntity, int type) {
        ProgressUtils.showProgressDialog(mContext,"正在删除数据");
        evidenceModel.deleteEvidence(evidenceEntity.getId(), evidenceEntity.getCrimeId(), new callBack() {
            @Override
            public void onSuccess(String date) {
                ProgressUtils.dismissProgressDialog();
                Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                if(response.getCode() == 200) {
                    ToastUtils.showLong(response.getData());
                    if(type == 1) {
                        evidenceView.updateEvidenceFingerList(evidenceEntity);
                        evidenceModel.deleteEvidenceFingerPic(evidenceEntity);
                    }else if(type == 2) {
                        evidenceView.updateEvidenceFootList(evidenceEntity);
                        evidenceModel.deleteEvidenceFootPic(evidenceEntity);
                    }else if(type == 3) {
                        evidenceView.updateEvidenceOtherList(evidenceEntity);
                        evidenceModel.deleteEvidenceOtherPic(evidenceEntity);
                    }else if(type == 4) {
                        evidenceView.updateEvidenceFaceList(evidenceEntity);
                        evidenceModel.deleteEvidenceFacePic(evidenceEntity);
                    }
                 }else {
                    ToastUtils.showLong(response.getMsg());
                }
            }

            @Override
            public void onFail(String msg) {
                ProgressUtils.dismissProgressDialog();
                ToastUtils.showLong("删除数据错误:" + msg);
            }
        });
    }

    @Override
    public void getCompareResult(CrimeItem item) {
        if(item == null) {
            ToastUtils.showShort("案件信息为空");
            return;
        }
        if(!StringUtils.checkString(item.getId())) {
            ToastUtils.showShort("案件ID为空");
            return;
        }
        ProgressUtils.showProgressDialog(mContext,"正在加载中");
        evidenceModel.getCompareResult(item.getId(), "2", new callBack() {
            @Override
            public void onSuccess(String date) {
                ProgressUtils.dismissProgressDialog();
                Response<List<ComparePhoto>> response = GsonUtils.fromJsonArray(date, ComparePhoto.class);
                if(response.getCode() == 200) {
                    evidenceView.updateFinger(response.getData());
                }
            }

            @Override
            public void onFail(String msg) {
                ProgressUtils.dismissProgressDialog();
                ToastUtils.showLong("获取比对结果错误:" + msg);
            }
        });
    }

    @Override
    public void sendCompare(EvidenceEntity evidenceEntity, int type) {
        if(evidenceEntity == null) {
            ToastUtils.showShort("痕迹信息为空");
            return;
        }
        if(!StringUtils.checkString(evidenceEntity.getId())) {
            ToastUtils.showShort("痕迹ID为空");
            return;
        }
        ProgressUtils.showProgressDialog(mContext,"正在加载中");
        evidenceModel.sendCompare(evidenceEntity.getId(), type + "", new callBack() {
            @Override
            public void onSuccess(String date) {
                ProgressUtils.dismissProgressDialog();
                Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                if(response.getCode() == 200) {
                    if(type != 2) {
                        evidenceView.startAlarm();
                    }
                    ToastUtils.showShort(response.getData());
                }else {
                    ToastUtils.showShort("提交比对错误：" + response.getMsg());
                }
            }

            @Override
            public void onFail(String msg) {
                ToastUtils.showShort("提交比对错误：" + msg);
            }
        });
    }

}
