package com.liany.clientmodel.presenter.subView.sceneStep;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;

import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.callback.callBack;
import com.liany.clientmodel.contract.subView.sceneStep.PhotoContract;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.diagnose.Photo;
import com.liany.clientmodel.diagnose.Response;
import com.liany.clientmodel.model.subView.sceneStep.PhotoModel;
import com.liany.clientmodel.utils.CompressUtils;
import com.liany.clientmodel.utils.FileUtils;
import com.liany.clientmodel.utils.GsonUtils;
import com.liany.clientmodel.utils.LogUtils;
import com.liany.clientmodel.utils.ProgressUtils;
import com.liany.clientmodel.utils.StringUtils;
import com.liany.clientmodel.utils.ToastUtils;
import com.liany.clientmodel.utils.getPhotoFromPhotoAlbum;
import com.liany.clientmodel.view.subView.sceneStep.PhotoActivity;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static android.app.Activity.RESULT_OK;

/**
 * @创建者 ly
 * @创建时间 2020/3/26
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class PhotoPresenter implements PhotoContract.Presenter {
    private PhotoContract.View photoView;
    private PhotoContract.Model photoModel;
    private Context mContext;
    private Photo uploadPositionPic;
    private Photo uploadOverViewPhoto;


    public PhotoPresenter(Context mContext, PhotoContract.View loginView) {
        this.mContext = mContext;
        this.photoView = loginView;
        photoModel = new PhotoModel(mContext);
    }

    @Override
    public void addPosition(int code) {
        if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA)) {
            photoView.takePhoto(code);
//            photoView.startCamera(code);
        } else {
            //提示用户开户拍照权限
            String[] perms = {Manifest.permission.CAMERA,Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions((PhotoActivity)mContext, perms, code);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data, final File file, CrimeItem crimeItem) {
        if(resultCode == RESULT_OK) {
            if (requestCode == Constants.REQUEST_TAKE_PHOTO_POSITION) {//获取系统照片上传
                CompressUtils.compressPic(mContext, file, "compress_", new CompressUtils.Callback() {
                    @Override
                    public void onStart() {
                        LogUtils.e("onStart");
                    }

                    @Override
                    public void onSuccess(File file) {
                        String photoId = StringUtils.getUUID();
                        ProgressUtils.showProgressDialog(mContext,"正在上传图片");
                        photoModel.uploadPic(file,
                                photoId,
                                Constants.state_positionPhoto,
                                crimeItem.getId(),
                                crimeItem.getId(),
                                new callBack() {
                            @Override
                            public void onSuccess(String date) {
                                ProgressUtils.dismissProgressDialog();
                                Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                                if(response.getCode() == 200) {
                                    Photo position = new Photo();
                                    position.setFileName(file.getName());
                                    position.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
                                    position.setWidth(BitmapFactory.decodeFile(file.getPath()).getWidth() + "");
                                    position.setHeight(BitmapFactory.decodeFile(file.getPath()).getHeight() + "");
                                    position.setPath(file.getPath());
                                    position.setServerPath(Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + response.getData());
                                    position.setId(photoId);
                                    position.setUUID(StringUtils.md5HashCode32(position.getPath()));
                                    position.setCrimeId(crimeItem.getId());
                                    position.setParentId(crimeItem.getId());
                                    position.setState(Constants.state_positionPhoto);
                                    uploadPositionPic = position;
                                    photoView.showOrientationDialog(Constants.REQUEST_TAKE_PHOTO_POSITION);
                                    photoView.addPositionPic(position);
//                                    addDetail(file,Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + response.getData(),crimeItem);
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
                    public void onError(Throwable e) {
                        LogUtils.e("onError" + e.getMessage());
                    }
                });
            }else if (requestCode == Constants.REQUEST_TAKE_PHOTO_POSITION_img) {//获取系统照片上传
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
                        photoModel.uploadPic(file,
                                photoId,
                                Constants.state_positionPhoto,
                                crimeItem.getId(),
                                crimeItem.getId(),
                                new callBack() {
                                    @Override
                                    public void onSuccess(String date) {
                                        ProgressUtils.dismissProgressDialog();
                                        Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                                        if(response.getCode() == 200) {
                                            Photo position = new Photo();
                                            position.setFileName(file.getName());
                                            position.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
                                            position.setWidth(BitmapFactory.decodeFile(file.getPath()).getWidth() + "");
                                            position.setHeight(BitmapFactory.decodeFile(file.getPath()).getHeight() + "");
                                            position.setPath(file.getPath());
                                            position.setServerPath(Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + response.getData());
                                            position.setId(photoId);
                                            position.setUUID(StringUtils.md5HashCode32(position.getPath()));
                                            position.setCrimeId(crimeItem.getId());
                                            position.setParentId(crimeItem.getId());
                                            position.setState(Constants.state_positionPhoto);
                                            uploadPositionPic = position;
                                            photoView.showOrientationDialog(Constants.REQUEST_TAKE_PHOTO_POSITION);
                                            photoView.addPositionPic(position);
//                                            addDetail(file,Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + response.getData(),crimeItem);
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
                    public void onError(Throwable e) {
                        LogUtils.e("onError" + e.getMessage());
                    }
                });
            } else if (requestCode == Constants.REQUEST_TAKE_PHOTO_POSITION_img_more) {
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
                photoModel.uploadPics(files,
                        photoIds,
                        Constants.state_positionPhoto,
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
                                Photo position = new Photo();
                                position.setFileName(file.getName());
                                position.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
                                position.setWidth(BitmapFactory.decodeFile(file.getPath()).getWidth() + "");
                                position.setHeight(BitmapFactory.decodeFile(file.getPath()).getHeight() + "");
                                position.setPath(file.getPath());
                                position.setServerPath(Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + file.getName());
                                position.setId(photoId);
                                position.setUUID(StringUtils.md5HashCode32(position.getPath()));
                                position.setCrimeId(crimeItem.getId());
                                position.setParentId(crimeItem.getId());
                                position.setState(Constants.state_positionPhoto);
//                                uploadPositionPic = position;
//                                photoView.showOrientationDialog(Constants.REQUEST_TAKE_PHOTO_POSITION);
                                photos.add(position);
                            }
                            photoView.addPositionPics(photos);
                        }else {
                            ToastUtils.showLong("上传图片错误:" + response.getMsg());
                        }
                        //删除图片压缩缓存
                        photoView.clearCache();
                    }

                    @Override
                    public void onFail(String msg) {
                        ToastUtils.showLong("上传图片错误:" + msg);
                        ProgressUtils.dismissProgressDialog();
                        //删除图片压缩缓存
                        photoView.clearCache();
                    }
                });
            } else if(requestCode == Constants.REQUEST_TAKE_PHOTO_LIKE) {//获取系统照片上传
                CompressUtils.compressPic(mContext, file, "compress_", new CompressUtils.Callback() {
                    @Override
                    public void onStart() {
                        LogUtils.e("onStart");
                    }

                    @Override
                    public void onSuccess(File file) {
                        ProgressUtils.showProgressDialog(mContext,"正在上传图片");
                        String photoId = StringUtils.getUUID();
                        photoModel.uploadPic(file,
                                photoId,
                                Constants.state_overview,
                                crimeItem.getId(),
                                crimeItem.getId(),
                                new callBack() {
                            @Override
                            public void onSuccess(String date) {
                                ProgressUtils.dismissProgressDialog();
                                Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                                if(response.getCode() == 200) {
                                    Photo position = new Photo();
                                    position.setFileName(file.getName());
                                    position.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
                                    position.setWidth(BitmapFactory.decodeFile(file.getPath()).getWidth() + "");
                                    position.setHeight(BitmapFactory.decodeFile(file.getPath()).getHeight() + "");
                                    position.setPath(file.getPath());
                                    position.setServerPath(Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + response.getData());
                                    position.setId(photoId);
                                    position.setUUID(StringUtils.md5HashCode32(position.getPath()));
                                    position.setCrimeId(crimeItem.getId());
                                    position.setParentId(crimeItem.getId());
                                    position.setState(Constants.state_overview);
                                    uploadOverViewPhoto = position;
                                    photoView.showOrientationDialog(Constants.REQUEST_TAKE_PHOTO_LIKE);
                                    photoView.addLikePic(position);
//                                    addDetail(file,Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + response.getData(),crimeItem);
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
                    public void onError(Throwable e) {
                        LogUtils.e("onError" + e.getMessage());
                    }
                });
            } else if(requestCode == Constants.REQUEST_TAKE_PHOTO_LIKE_img) {//获取系统照片上传
                String path = getPhotoFromPhotoAlbum.getRealPathFromUri(mContext, data.getData());
                File file1 = new File(path);
                CompressUtils.compressPic(mContext, file1, "compress_", new CompressUtils.Callback() {
                    @Override
                    public void onStart() {
                        LogUtils.e("onStart");
                    }

                    @Override
                    public void onSuccess(File file) {
                        ProgressUtils.showProgressDialog(mContext,"正在上传图片");
                        String photoId = StringUtils.getUUID();
                        photoModel.uploadPic(file,
                                photoId,
                                Constants.state_overview,
                                crimeItem.getId(),
                                crimeItem.getId(),
                                new callBack() {
                                    @Override
                                    public void onSuccess(String date) {
                                        ProgressUtils.dismissProgressDialog();
                                        Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                                        if(response.getCode() == 200) {
                                            Photo position = new Photo();
                                            position.setFileName(file.getName());
                                            position.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
                                            position.setWidth(BitmapFactory.decodeFile(file.getPath()).getWidth() + "");
                                            position.setHeight(BitmapFactory.decodeFile(file.getPath()).getHeight() + "");
                                            position.setPath(file.getPath());
                                            position.setServerPath(Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + response.getData());
                                            position.setId(photoId);
                                            position.setUUID(StringUtils.md5HashCode32(position.getPath()));
                                            position.setCrimeId(crimeItem.getId());
                                            position.setParentId(crimeItem.getId());
                                            position.setState(Constants.state_overview);
                                            uploadOverViewPhoto = position;
                                            photoView.showOrientationDialog(Constants.REQUEST_TAKE_PHOTO_LIKE);
                                            photoView.addLikePic(position);
//                                            addDetail(file,Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + response.getData(),crimeItem);
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
                    public void onError(Throwable e) {
                        LogUtils.e("onError" + e.getMessage());
                    }
                });
            } else if (requestCode == Constants.REQUEST_TAKE_PHOTO_LIKE_img_more) {
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
                photoModel.uploadPics(files,
                        photoIds,
                        Constants.state_overview,
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
                                        Photo position = new Photo();
                                        position.setFileName(file.getName());
                                        position.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
                                        position.setWidth(BitmapFactory.decodeFile(file.getPath()).getWidth() + "");
                                        position.setHeight(BitmapFactory.decodeFile(file.getPath()).getHeight() + "");
                                        position.setPath(file.getPath());
                                        position.setServerPath(Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + file.getName());
                                        position.setId(photoId);
                                        position.setUUID(StringUtils.md5HashCode32(position.getPath()));
                                        position.setCrimeId(crimeItem.getId());
                                        position.setParentId(crimeItem.getId());
                                        position.setState(Constants.state_overview);
//                                        uploadOverViewPhoto = position;
//                                        photoView.showOrientationDialog(Constants.REQUEST_TAKE_PHOTO_LIKE);
                                        photos.add(position);
                                    }
                                    photoView.addLikePics(photos);
                                }else {
                                    ToastUtils.showLong("上传图片错误:" + response.getMsg());
                                }
                                //删除图片压缩缓存
                                photoView.clearCache();
                            }

                            @Override
                            public void onFail(String msg) {
                                ToastUtils.showLong("上传图片错误:" + msg);
                                ProgressUtils.dismissProgressDialog();
                                //删除图片压缩缓存
                                photoView.clearCache();
                            }
                        });
            } else if(requestCode == Constants.REQUEST_TAKE_PHOTO_IMPORTANT) {//获取系统照片上传
                CompressUtils.compressPic(mContext, file, "compress_", new CompressUtils.Callback() {
                    @Override
                    public void onStart() {
                        LogUtils.e("onStart");
                    }

                    @Override
                    public void onSuccess(File file) {
                        ProgressUtils.showProgressDialog(mContext,"正在上传图片");
                        String photoId = StringUtils.getUUID();
                        photoModel.uploadPic(file,
                                photoId,
                                Constants.state_important,
                                crimeItem.getId(),
                                crimeItem.getId(),
                                new callBack() {
                            @Override
                            public void onSuccess(String date) {
                                ProgressUtils.dismissProgressDialog();
                                Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                                if(response.getCode() == 200) {
                                    Photo position = new Photo();
                                    position.setFileName(file.getName());
                                    position.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
                                    position.setWidth(BitmapFactory.decodeFile(file.getPath()).getWidth() + "");
                                    position.setHeight(BitmapFactory.decodeFile(file.getPath()).getHeight() + "");
                                    position.setPath(file.getPath());
                                    position.setServerPath(Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + response.getData());
                                    position.setId(photoId);
                                    position.setUUID(StringUtils.md5HashCode32(position.getPath()));
                                    position.setCrimeId(crimeItem.getId());
                                    position.setParentId(crimeItem.getId());
                                    position.setState(Constants.state_important);
                                    photoView.addImportantPic(position);
//                                    addDetail(file,Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + response.getData(),crimeItem);
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
                    public void onError(Throwable e) {
                        LogUtils.e("onError" + e.getMessage());
                    }
                });
            } else if(requestCode == Constants.REQUEST_TAKE_PHOTO_IMPORTANT_img) {//获取系统照片上传
                String path = getPhotoFromPhotoAlbum.getRealPathFromUri(mContext, data.getData());
                File file1 = new File(path);
                CompressUtils.compressPic(mContext, file1, "compress_", new CompressUtils.Callback() {
                    @Override
                    public void onStart() {
                        LogUtils.e("onStart");
                    }

                    @Override
                    public void onSuccess(File file) {
                        ProgressUtils.showProgressDialog(mContext,"正在上传图片");
                        String photoId = StringUtils.getUUID();
                        photoModel.uploadPic(file,
                                photoId,
                                Constants.state_important,
                                crimeItem.getId(),
                                crimeItem.getId(),
                                new callBack() {
                                    @Override
                                    public void onSuccess(String date) {
                                        ProgressUtils.dismissProgressDialog();
                                        Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                                        if(response.getCode() == 200) {
                                            Photo position = new Photo();
                                            position.setFileName(file.getName());
                                            position.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
                                            position.setWidth(BitmapFactory.decodeFile(file.getPath()).getWidth() + "");
                                            position.setHeight(BitmapFactory.decodeFile(file.getPath()).getHeight() + "");
                                            position.setPath(file.getPath());
                                            position.setServerPath(Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + response.getData());
                                            position.setId(photoId);
                                            position.setUUID(StringUtils.md5HashCode32(position.getPath()));
                                            position.setCrimeId(crimeItem.getId());
                                            position.setParentId(crimeItem.getId());
                                            position.setState(Constants.state_important);
                                            photoView.addImportantPic(position);
//                                            addDetail(file,Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + response.getData(),crimeItem);
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
                    public void onError(Throwable e) {
                        LogUtils.e("onError" + e.getMessage());
                    }
                });
            } else if (requestCode == Constants.REQUEST_TAKE_PHOTO_IMPORTANT_img_more) {
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
                photoModel.uploadPics(files,
                        photoIds,
                        Constants.state_important,
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
                                        Photo position = new Photo();
                                        position.setFileName(file.getName());
                                        position.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
                                        position.setWidth(BitmapFactory.decodeFile(file.getPath()).getWidth() + "");
                                        position.setHeight(BitmapFactory.decodeFile(file.getPath()).getHeight() + "");
                                        position.setPath(file.getPath());
                                        position.setServerPath(Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + file.getName());
                                        position.setId(photoId);
                                        position.setUUID(StringUtils.md5HashCode32(position.getPath()));
                                        position.setCrimeId(crimeItem.getId());
                                        position.setParentId(crimeItem.getId());
                                        position.setState(Constants.state_important);
                                        photos.add(position);
                                    }
                                    photoView.addImportantPics(photos);
                                }else {
                                    ToastUtils.showLong("上传图片错误:" + response.getMsg());
                                }
                                //删除图片压缩缓存
                                photoView.clearCache();
                            }

                            @Override
                            public void onFail(String msg) {
                                ToastUtils.showLong("上传图片错误:" + msg);
                                ProgressUtils.dismissProgressDialog();
                                //删除图片压缩缓存
                                photoView.clearCache();
                            }
                        });
            } else if(requestCode == Constants.REQUEST_TAKE_PHOTO_DETAIL) {
                CompressUtils.compressPic(mContext, file, "compress_", new CompressUtils.Callback() {
                    @Override
                    public void onStart() {
                        LogUtils.e("onStart");
                    }

                    @Override
                    public void onSuccess(File file) {
                        ProgressUtils.showProgressDialog(mContext,"正在上传图片");
                        String photoId = StringUtils.getUUID();
                        photoModel.uploadPic(file,
                                photoId,
                                Constants.state_detail,
                                crimeItem.getId(),
                                crimeItem.getId(),
                                new callBack() {
                                    @Override
                                    public void onSuccess(String date) {
                                        ProgressUtils.dismissProgressDialog();
                                        Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                                        if(response.getCode() == 200) {
                                            Photo position = new Photo();
                                            position.setFileName(file.getName());
                                            position.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
                                            position.setWidth(BitmapFactory.decodeFile(file.getPath()).getWidth() + "");
                                            position.setHeight(BitmapFactory.decodeFile(file.getPath()).getHeight() + "");
                                            position.setPath(file.getPath());
                                            position.setServerPath(Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + response.getData());
                                            position.setId(photoId);
                                            position.setUUID(StringUtils.md5HashCode32(position.getPath()));
                                            position.setCrimeId(crimeItem.getId());
                                            position.setParentId(crimeItem.getId());
                                            position.setState(Constants.state_detail);
                                            photoView.addDetail(position);
//                                    addDetail(file,Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + response.getData(),crimeItem);
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
                    public void onError(Throwable e) {
                        LogUtils.e("onError" + e.getMessage());
                    }
                });
            } else if(requestCode == Constants.REQUEST_TAKE_PHOTO_DETAIL_img) {//获取系统照片上传
                String path = getPhotoFromPhotoAlbum.getRealPathFromUri(mContext, data.getData());
                File file1 = new File(path);
                CompressUtils.compressPic(mContext, file1, "compress_", new CompressUtils.Callback() {
                    @Override
                    public void onStart() {
                        LogUtils.e("onStart");
                    }

                    @Override
                    public void onSuccess(File file) {
                        ProgressUtils.showProgressDialog(mContext,"正在上传图片");
                        String photoId = StringUtils.getUUID();
                        photoModel.uploadPic(file,
                                photoId,
                                Constants.state_detail,
                                crimeItem.getId(),
                                crimeItem.getId(),
                                new callBack() {
                                    @Override
                                    public void onSuccess(String date) {
                                        ProgressUtils.dismissProgressDialog();
                                        Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                                        if(response.getCode() == 200) {
                                            Photo position = new Photo();
                                            position.setFileName(file.getName());
                                            position.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
                                            position.setWidth(BitmapFactory.decodeFile(file.getPath()).getWidth() + "");
                                            position.setHeight(BitmapFactory.decodeFile(file.getPath()).getHeight() + "");
                                            position.setPath(file.getPath());
                                            position.setServerPath(Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + response.getData());
                                            position.setId(photoId);
                                            position.setUUID(StringUtils.md5HashCode32(position.getPath()));
                                            position.setCrimeId(crimeItem.getId());
                                            position.setParentId(crimeItem.getId());
                                            position.setState(Constants.state_detail);
                                            photoView.addDetail(position);
//                                            addDetail(file,Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + response.getData(),crimeItem);
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
                    public void onError(Throwable e) {
                        LogUtils.e("onError" + e.getMessage());
                    }
                });
            } else if (requestCode == Constants.REQUEST_TAKE_PHOTO_DETAIL_img_more) {
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
                photoModel.uploadPics(files,
                        photoIds,
                        Constants.state_detail,
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
                                        Photo position = new Photo();
                                        position.setFileName(file.getName());
                                        position.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
                                        position.setWidth(BitmapFactory.decodeFile(file.getPath()).getWidth() + "");
                                        position.setHeight(BitmapFactory.decodeFile(file.getPath()).getHeight() + "");
                                        position.setPath(file.getPath());
                                        position.setServerPath(Constants.ipAddress + File.separator + crimeItem.getId() + File.separator + file.getName());
                                        position.setId(photoId);
                                        position.setUUID(StringUtils.md5HashCode32(position.getPath()));
                                        position.setCrimeId(crimeItem.getId());
                                        position.setParentId(crimeItem.getId());
                                        position.setState(Constants.state_detail);
                                        photos.add(position);
                                    }
                                    photoView.addDetails(photos);
                                }else {
                                    ToastUtils.showLong("上传图片错误:" + response.getMsg());
                                }
                                //删除图片压缩缓存
                                photoView.clearCache();
                            }

                            @Override
                            public void onFail(String msg) {
                                ToastUtils.showLong("上传图片错误:" + msg);
                                ProgressUtils.dismissProgressDialog();
                                //删除图片压缩缓存
                                photoView.clearCache();
                            }
                        });
            }
        }
    }

    private void addDetail(File file,String servicePath,CrimeItem crimeItem) {
        FileUtils.copyRename(file.getAbsolutePath(),
                Constants.path_photoDir + File.separator,
                "detail_" + file.getName());
        File file1 = new File(Constants.path_photoDir + File.separator + "detail_" + file.getName());
        String photoId = StringUtils.getUUID();
        photoModel.uploadPic(new File(Constants.path_photoDir + File.separator + "detail_" + file.getName())
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
                    photoView.addDetail(photo);
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
    public void deletePositionPic(Photo positionPic) {
        photoModel.deletePic(positionPic, new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                if(response.getCode() == 200) {
                    photoView.deleteDetail(positionPic.getServerPath());
                    photoView.updatePositionList(positionPic);
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
    public void deleteLikePic(Photo overViewPhoto) {
        photoModel.deletePic(overViewPhoto, new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                if(response.getCode() == 200) {
                    photoView.deleteDetail(overViewPhoto.getServerPath());
                    photoView.updateLikeList(overViewPhoto);
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
    public void deleteImportantPic(Photo importantPhoto) {
        photoModel.deletePic(importantPhoto, new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                if(response.getCode() == 200) {
                    photoView.deleteDetail(importantPhoto.getServerPath());
                    photoView.updateImportantList(importantPhoto);
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
    public void deleteDetailPic(Photo photo) {
        photoModel.deletePic(photo, new callBack() {
            @Override
            public void onSuccess(String date) {
                Response<String> response = GsonUtils.fromJsonObject(date, String.class);
                if(response.getCode() == 200) {
                    photoView.updateDetailList(photo);
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
    public void setPositionDes(String message) {
        if(message != null) {
            uploadPositionPic.setPhotoInfo(message);
            photoView.updatePositionListOnDes(uploadPositionPic);
        }
    }

    @Override
    public void setLikeDes(String message) {
        if(message != null) {
            uploadOverViewPhoto.setPhotoInfo(message);
            photoView.updateOverviewListOnDes(uploadOverViewPhoto);
        }
    }
}
