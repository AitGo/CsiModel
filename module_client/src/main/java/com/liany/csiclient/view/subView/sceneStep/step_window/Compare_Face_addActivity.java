package com.liany.csiclient.view.subView.sceneStep.step_window;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.liany.csiclient.R;
import com.liany.csiclient.base.BaseAcitivity;
import com.liany.csiclient.base.Constants;
import com.liany.csiclient.diagnose.ComparePhoto;
import com.liany.csiclient.diagnose.Photo;
import com.liany.csiclient.diagnose.Response;
import com.liany.csiclient.diagnose.sysDict;
import com.liany.csiclient.utils.ClickUtils;
import com.liany.csiclient.utils.CompressUtils;
import com.liany.csiclient.utils.GsonUtils;
import com.liany.csiclient.utils.LogUtils;
import com.liany.csiclient.utils.NetworkUtils;
import com.liany.csiclient.utils.ProgressUtils;
import com.liany.csiclient.utils.SPUtils;
import com.liany.csiclient.utils.StringUtils;
import com.liany.csiclient.utils.SysCameraUtils;
import com.liany.csiclient.utils.ToastUtils;
import com.liany.csiclient.utils.getPhotoFromPhotoAlbum;
import com.liany.csiclient.view.subView.sceneStep.Select_RadioList_DictActivity;
import com.liany.csiclient.widget.ClearableEditText;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @创建者 ly
 * @创建时间 2020/7/10
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class Compare_Face_addActivity extends BaseAcitivity implements View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    ImageView ivTitleImgRight;
    ImageView ivTitleCameraRight;
    TextView casetype;
    ClearableEditText location;
    ImageView ivCompare;
    ImageView ivTitleConfirm;
    ImageView ivPosition;

    private String caseType = "";
    private String locationS = "";
    private String crimeId = "";
    private String photoId = "";
    private boolean isUploadPhoto = false;
    private String photoName = "";
    private String cropPath = "";
    private File outputImagepath;
    private Context mContext;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_compare_face_add;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        ivTitleImgRight = findViewById(R.id.iv_title_img_right);
        ivTitleCameraRight = findViewById(R.id.iv_title_camera_right);
        casetype = findViewById(R.id.casetype);
        location = findViewById(R.id.location);
        ivCompare = findViewById(R.id.iv_compare_Finger);
        ivTitleConfirm = findViewById(R.id.iv_title_confirm);
        ivPosition = findViewById(R.id.iv_position);

        ivTitleBack.setOnClickListener(this);
        ivPosition.setOnClickListener(this);
        ivTitleImgRight.setOnClickListener(this);
        ivTitleCameraRight.setOnClickListener(this);
        casetype.setOnClickListener(this);
        ivTitleConfirm.setOnClickListener(this);

        ivTitleImgRight.setVisibility(View.VISIBLE);
        ivTitleCameraRight.setVisibility(View.VISIBLE);
        ivTitleConfirm.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mContext = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

    @Override
    public void onClick(View view) {
        if(ClickUtils.isFastClick()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.iv_title_back) {
            finish();
        } else if (id == R.id.iv_position) {
            initLocation(true);
        } else if (id == R.id.iv_title_img_right) {
            goPhotoAlbum();
        } else if (id == R.id.iv_title_camera_right) {//                addPresenter.checkPermission();
            takePhoto();
        } else if (id == R.id.casetype) {
            String type = casetype.getText().toString();
            OkGo.<String>post(Constants.ipAddress + "/sys/caseType")
                    .tag(this)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(com.lzy.okgo.model.Response<String> response1) {
                            LogUtils.e(response1.body());
                            String date = response1.body();
                            Response<List<sysDict>> response = GsonUtils.fromJsonArray(date, sysDict.class);
                            if (response.getCode() == 200) {
                                List<Integer> selectList = new ArrayList<>();
                                if (type != null && !type.equals("")) {
                                    for (sysDict dict : response.getData()) {
                                        if (dict.getDictValue().equals(type)) {
                                            selectList.add(response.getData().indexOf(dict));
                                        }
                                    }
                                }
                                Intent intent = new Intent();
                                intent.setClass(Compare_Face_addActivity.this, Select_RadioList_DictActivity.class);
                                intent.putExtra(Constants.SELECT_TITLE, "案件类别");
                                intent.putExtra(Constants.SELECT_RADIO_DICT, (Serializable) response.getData());
                                intent.putExtra(Constants.SELECT_POSITION, (Serializable) selectList);
                                startActivityForResult(intent, Constants.REQUEST_RADIO_DICT);
                            }
                        }

                        @Override
                        public void onError(com.lzy.okgo.model.Response<String> response) {
                            super.onError(response);
                            LogUtils.e(response.getException().getMessage());
                        }
                    });
        } else if (id == R.id.iv_title_confirm) {
            NetworkUtils.isOnLineNet(mContext, new NetworkUtils.NetCallback() {
                @Override
                public void onNext() {
                    uploadCompare();
                }

                @Override
                public void onError() {
                    ToastUtils.showShort("请检查网络是否可用");
                    return;
                }
            });
        }
    }

    public void uploadCompare() {
        locationS = location.getText().toString().trim();
        if(!StringUtils.checkString(caseType)) {
            ToastUtils.showShort("案件类别不能为空");
            return;
        }
        if(!StringUtils.checkString(locationS)) {
            ToastUtils.showShort("勘验地点不能为空");
            return;
        }
        if(!isUploadPhoto) {
            ToastUtils.showShort("人像图片不能为空");
            return;
        }
        OkGo.<String>post( Constants.ipAddress + "/compare/startCompareOut")
                .tag(this)
                .params("caseType",caseType)
                .params("location",locationS)
                .params("crimeId",crimeId)
                .params("photoId",photoId)
                .params("unitCode", (String) SPUtils.getParam(mContext, Constants.sp_unitCode,""))
                .params("unitName", (String) SPUtils.getParam(mContext, Constants.sp_unitName,""))
                .params("type", "4")
                .params("userId", (String) SPUtils.getParam(mContext, Constants.sp_userId,""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response1) {
                        LogUtils.e(response1.body());
                        ProgressUtils.dismissProgressDialog();
                        Response<String> response = GsonUtils.fromJsonObject(response1.body(), String.class);
                        if(response.getCode() == 200) {
                            ToastUtils.showShort(response.getData());
                            ComparePhoto photo = new ComparePhoto();
                            photo.setId(StringUtils.getUUID());
                            photo.setCreateDate(StringUtils.long2String(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss"));
                            photo.setCreateName((String) SPUtils.getParam(mContext, Constants.sp_userName,""));
                            photo.setPhotoId(photoId);
                            photo.setStatus("-2");
                            photo.setRev2(caseType + "@@" + locationS);
                            photo.setServicePath(Constants.ipAddress + "/" + crimeId + "/" + photoName);
                            Intent intent = getIntent();
                            intent.putExtra("comparePhoto",photo);
                            setResult(Activity.RESULT_OK, intent);
                            finish();
                        }else {
                            ToastUtils.showLong("发送比对失败:" + response.getMsg());
                        }
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                        LogUtils.e(response.getException().getMessage());
                        ProgressUtils.dismissProgressDialog();
                        ToastUtils.showLong("发送比对错误:" + response.getException().getMessage());
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == Constants.REQUEST_RADIO_DICT) {
                if (data.getStringExtra(Constants.SELECT_TITLE).equals("案件类别")) {
                    sysDict dict = (sysDict) data.getSerializableExtra(Constants.RESULT_RADIO_DICT);
                    casetype.setText(dict.getDictValue());
                    caseType = dict.getDictValue();
                }
            }else if((requestCode == Constants.REQUEST_EVIDENCE_EVIDENCE)) {
                CompressUtils.compressPic(mContext, outputImagepath, "compress_", new CompressUtils.Callback() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(File file) {
                        photoName = file.getName();
                        ProgressUtils.showProgressDialog(mContext,"正在上传图片");
                        photoId = StringUtils.getUUID();
                        crimeId = StringUtils.getUUID();
                        OkGo.<String>post( Constants.ipAddress + "/scene/uploadPic")
                                .tag(this)
                                .params("pic",file)
                                .params("photoId",photoId)
                                .params("state", Constants.photoState_compare_face)
                                .params("parentId",crimeId)
                                .params("crimeId",crimeId)
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(com.lzy.okgo.model.Response<String> response1) {
                                        LogUtils.e(response1.body());
                                        ProgressUtils.dismissProgressDialog();
                                        Response<String> response = GsonUtils.fromJsonObject(response1.body(), String.class);
                                        if(response.getCode() == 200) {
                                            Photo photo = new Photo();
                                            photo.setId(photoId);
                                            photo.setParentId(crimeId);
                                            photo.setCrimeId(crimeId);
                                            photo.setPath(file.getPath());
                                            photo.setServerPath(Constants.ipAddress + File.separator + crimeId + File.separator + response.getData());
                                            photo.setWidth(BitmapFactory.decodeFile(file.getPath()).getWidth() + "");
                                            photo.setHeight(BitmapFactory.decodeFile(file.getPath()).getHeight() + "");
                                            photo.setFileName(file.getName());
                                            photo.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
                                            photo.setUUID(StringUtils.md5HashCode32(file.getPath()));
                                            photo.setState(Constants.state_evidence);
                                            Glide.with(mContext).load(photo.getServerPath())
                                                    .dontAnimate()
                                                    .into(ivCompare);
                                            isUploadPhoto = true;
                                        }else {
                                            ToastUtils.showLong("上传图片错误:" + response.getMsg());
                                        }
                                    }

                                    @Override
                                    public void onError(com.lzy.okgo.model.Response<String> response) {
                                        super.onError(response);
                                        LogUtils.e(response.getException().getMessage());
                                        ProgressUtils.dismissProgressDialog();
                                        ToastUtils.showLong("上传图片错误:" + response.getException().getMessage());
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
                        photoName = file.getName();
                        photoId = StringUtils.getUUID();
                        crimeId = StringUtils.getUUID();
                        ProgressUtils.showProgressDialog(mContext,"正在上传图片");
                        OkGo.<String>post( Constants.ipAddress + "/scene/uploadPic")
                                .tag(this)
                                .params("pic",file)
                                .params("photoId",photoId)
                                .params("state", Constants.photoState_compare_face)
                                .params("parentId",crimeId)
                                .params("crimeId",crimeId)
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(com.lzy.okgo.model.Response<String> response1) {
                                        LogUtils.e(response1.body());
                                        ProgressUtils.dismissProgressDialog();
                                        Response<String> response = GsonUtils.fromJsonObject(response1.body(), String.class);
                                        if(response.getCode() == 200) {
                                            Photo photo = new Photo();
                                            photo.setId(photoId);
                                            photo.setParentId(crimeId);
                                            photo.setCrimeId(crimeId);
                                            photo.setPath(file.getPath());
                                            photo.setServerPath(Constants.ipAddress + File.separator + crimeId + File.separator + response.getData());
                                            photo.setWidth(BitmapFactory.decodeFile(file.getPath()).getWidth() + "");
                                            photo.setHeight(BitmapFactory.decodeFile(file.getPath()).getHeight() + "");
                                            photo.setFileName(file.getName());
                                            photo.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
                                            photo.setUUID(StringUtils.md5HashCode32(file.getPath()));
                                            photo.setState(Constants.state_evidence);
                                            Glide.with(mContext).load(photo.getServerPath())
                                                    .dontAnimate()
                                                    .into(ivCompare);
                                            isUploadPhoto = true;
                                        }else {
                                            ToastUtils.showLong("上传图片错误:" + response.getMsg());
                                        }
                                    }

                                    @Override
                                    public void onError(com.lzy.okgo.model.Response<String> response) {
                                        super.onError(response);
                                        LogUtils.e(response.getException().getMessage());
                                        ProgressUtils.dismissProgressDialog();
                                        ToastUtils.showLong("上传图片错误:" + response.getException().getMessage());
                                    }
                                });
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("onError" + e.getMessage());
                    }
                });
            }
        }
    }

    /**
     * 打开系统相机
     */
    public void takePhoto() {
        outputImagepath = SysCameraUtils.takePhoto(this, Constants.path_photoDir + File.separator,"finger", Constants.REQUEST_EVIDENCE_EVIDENCE_GOOGLE);
    }

    public void goPhotoAlbum() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, Constants.REQUEST_EVIDENCE_EVIDENCE_ALBUM);
    }

    private void initLocation(boolean isSetAddress) {
        if (isSetAddress) {
            ProgressUtils.showProgressDialog(this, "正在定位");
        }
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
                        //可在其中解析amapLocation获取相应内容。
                        String city = amapLocation.getCity();
                        String district = amapLocation.getDistrict();//城区信息
                        String street = amapLocation.getStreet();
                        String streetNum = amapLocation.getStreetNum();//街道门牌号信息
//                        String cityCode = amapLocation.getCityCode();//城市编码
//                        String adCode = amapLocation.getAdCode();//地区编码
                        String aoiName = amapLocation.getAoiName();//获取当前定位点的AOI信息
//                        String buildingId = amapLocation.getBuildingId();//获取当前室内定位的建筑物Id
                        String floor = amapLocation.getFloor();//获取当前室内定位的楼层
                        LogUtils.e(street + "\n" + streetNum + "\n" + aoiName + "\n" + floor);
                        String address = city + district + street + streetNum + aoiName ;
                        location.setText(address);
                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        LogUtils.e("location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                    }
                }
                ProgressUtils.dismissProgressDialog();
            }
        });
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();

        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(Constants.AMAP_MODE);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        //设置是否允许模拟位置,默认为true，允许模拟位置
        mLocationOption.setMockEnable(false);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

}
