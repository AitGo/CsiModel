package com.liany.csiclient.view.subView.sceneStep.step_window;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.TranslateAnimation;
import com.amap.api.maps.offlinemap.OfflineMapManager;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.liany.csiclient.R;
import com.liany.csiclient.base.BaseAcitivity;
import com.liany.csiclient.base.Constants;
import com.liany.csiclient.utils.ClickUtils;
import com.liany.csiclient.utils.GPSUtils;
import com.liany.csiclient.utils.LogUtils;
import com.liany.csiclient.utils.ProgressUtils;
import com.liany.csiclient.utils.SPUtils;
import com.liany.csiclient.widget.MyDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @创建者 ly
 * @创建时间 2019/3/26
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class Figure_PositionActivity extends BaseAcitivity implements OfflineMapManager.OfflineMapDownloadListener, View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    ImageView ivTitleCamera;
    MapView mMapView;
    ImageButton btnPosition;

    private AMap aMap;
    private UiSettings mUiSettings;//map UiSettings对象
    private GeocodeSearch geocoderSearch;
    private Marker screenMarker = null;
    private MyDialog missingPermissionDialog;
    private LocationSource.OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private MyDialog openGPSDialog;
    private double lon;
    private double lat;
    private OfflineMapManager amapManager = null;
    private Context mContext;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_figure_position;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        ivTitleCamera = findViewById(R.id.iv_title_camera);
        mMapView = findViewById(R.id.map);
        btnPosition = findViewById(R.id.btn_position);

        ivTitleBack.setOnClickListener(this);
        ivTitleCamera.setOnClickListener(this);
        btnPosition.setOnClickListener(this);

        ivTitleCamera.setVisibility(View.VISIBLE);
        tvTitle.setText(getString(R.string.position));
        initMap();
    }


    @Override
    protected void initData() {
        mContext = this;
        MapsInitializer.sdcardDir = getSdCacheDir(this);
//        positionPresenter.getPermission();
        checkGPSOpen();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        if(null != mMapView) {
            mMapView.onDestroy();
        }
        if(null != mlocationClient){
            mlocationClient.onDestroy();
        }
//        Intent i = new Intent();
//        i.putExtra(Constants.positionFilePathKey, "");
//        setResult(Constants.RESULT_CODE_POSITION, i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
        amapManager = new OfflineMapManager(this, this);

        try {
//            Log.d("ddd","download");
//            amapManager.downloadByCityName("长沙市");
//            amapManager.downloadByCityName("北京市");
//            amapManager.downloadByProvinceName("云南省");
//            amapManager.downloadByCityName("昌邑市");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
//        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View view) {
        if(ClickUtils.isFastClick()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.iv_title_back) {
            finish();
        } else if (id == R.id.iv_title_camera) {
            getMapScreenShot();
        } else if (id == R.id.btn_position) {
            if (checkGPSOpen()) {
                mlocationClient.startLocation();//启动定位
            }
        }
    }

    private boolean checkGPSOpen() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // 判断GPS模块是否开启，如果没有则开启
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            openGPSDialog = new MyDialog.Builder(this)
                    .setTitle(getString(R.string.prompt))
                    .setMsg("要使用定位功能，请打开GPS连接")
                    .setPositiveButton(getString(R.string.setting), new MyDialog.ConfirmListener() {
                        @Override
                        public void onClick() {
                            // 转到手机设置界面，用户设置GPS
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivityForResult(intent, 0); // 设置完成后返回到原来的界面
                            openGPSDialog.dismiss();
                        }
                    })
                    .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            openGPSDialog.dismiss();
                        }
                    })
                    .create();
            openGPSDialog.show();
            return false;
        }else {
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

    private void initMap() {

        aMap = mMapView.getMap();

        mUiSettings = aMap.getUiSettings();//实例化UiSettings类对象
        mUiSettings.setZoomControlsEnabled(true);

        mUiSettings.setMyLocationButtonEnabled(false); //显示默认的定位按钮
        mUiSettings.setCompassEnabled(true);//显示指南针

        aMap.setMyLocationEnabled(true);// 可触发定位并显示当前位置

        geocoderSearch = new GeocodeSearch(getApplicationContext());

        // 设置定位监听
        aMap.setLocationSource(new LocationSource() {
            @Override
            public void activate(OnLocationChangedListener onLocationChangedListener) {
                mListener = onLocationChangedListener;
                if (mlocationClient == null) {
                    //初始化定位
                    mlocationClient = new AMapLocationClient(getApplicationContext());
                    //初始化定位参数
                    mLocationOption = new AMapLocationClientOption();
                    //设置定位回调监听
                    mlocationClient.setLocationListener(new AMapLocationListener() {
                        @Override
                        public void onLocationChanged(AMapLocation amapLocation) {
                            if (mListener != null&&amapLocation != null) {
                                if (amapLocation != null
                                        &&amapLocation.getErrorCode() == 0) {
//                                    mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
                                    //设置地图显示中心点
                                    LatLng latLng = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());
                                    aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,17));
                                    StringBuffer buffer = new StringBuffer();
                                    buffer.append(amapLocation.getCountry() + ""
                                            + amapLocation.getProvince() + ""
                                            + amapLocation.getCity() + ""
                                            + amapLocation.getProvince() + ""
                                            + amapLocation.getDistrict() + ""
                                            + amapLocation.getStreet() + ""
                                            + amapLocation.getStreetNum());

                                    GPSUtils.Gps gps = GPSUtils.amap2Gps84(amapLocation.getLatitude(), amapLocation.getLongitude());
                                    lat = gps.getWgLat();
                                    lon = gps.getWgLon();
                                    SPUtils.setParam(getApplicationContext(),"gpsLat",String.valueOf(lat));
                                    SPUtils.setParam(getApplicationContext(),"gpsLon",String.valueOf(lon));
                                    Log.e("marker position", buffer.toString());
                                    Log.e("marker position",lat + "\n" + lon);
                                } else {
                                    String errText = "定位失败," + amapLocation.getErrorCode()+ ": " + amapLocation.getErrorInfo();
                                    Log.e("marker position AmapErr",errText);
                                }
                            }
                        }
                    });

                    aMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
                        @Override
                        public void onCameraChange(CameraPosition cameraPosition) {

                        }

                        @Override
                        public void onCameraChangeFinish(CameraPosition cameraPosition) {
                            //屏幕中心的Marker跳动
                            startJumpAnimation();
                            LatLonPoint point = new LatLonPoint(cameraPosition.target.latitude,cameraPosition.target.longitude);
                            GPSUtils.Gps gps = GPSUtils.amap2Gps84(cameraPosition.target.latitude, cameraPosition.target.longitude);
                            lat = gps.getWgLat();
                            lon = gps.getWgLon();
                            RegeocodeQuery query = new RegeocodeQuery(point, 50, GeocodeSearch.AMAP);

                            geocoderSearch.getFromLocationAsyn(query);
                            geocoderSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
                                @Override
                                public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
                                    RegeocodeAddress address = regeocodeResult.getRegeocodeAddress();
                                    LatLonPoint latLonPoint = regeocodeResult.getRegeocodeQuery().getPoint();

                                    Log.e("marker position", address.getFormatAddress());
                                }

                                @Override
                                public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

                                }
                            });

                        }
                    });
                    //设置为高精度定位模式
//                    mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
                    mLocationOption.setLocationMode(Constants.AMAP_MODE);

                    //获取一次定位结果：
                    //该方法默认为false。
                    mLocationOption.setOnceLocation(true);
                    //关闭缓存机制
                    mLocationOption.setLocationCacheEnable(false);

//                    //获取最近3s内精度最高的一次定位结果：
//                    //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
//                    mLocationOption.setOnceLocationLatest(true);
                    //设置定位参数
                    mlocationClient.setLocationOption(mLocationOption);
                    // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
                    // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
                    // 在定位结束后，在合适的生命周期调用onDestroy()方法
                    // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
                    mlocationClient.startLocation();//启动定位
                }
            }

            @Override
            public void deactivate() {
                mListener = null;
                if (mlocationClient != null) {
                    mlocationClient.stopLocation();
                    mlocationClient.onDestroy();
                }
                mlocationClient = null;
            }
        });
        aMap.setOnMapLoadedListener(new AMap.OnMapLoadedListener() {
            @Override
            public void onMapLoaded() {
                addMarkerInScreenCenter();
            }
        });
        // 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setMyLocationEnabled(true);
        // 设置定位的类型为定位模式，有定位、跟随或地图根据面向方向旋转几种
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
    }

    /**
     * 在屏幕中心添加一个Marker
     */
    public void addMarkerInScreenCenter() {
        LatLng latLng = aMap.getCameraPosition().target;
        Point screenPosition = aMap.getProjection().toScreenLocation(latLng);
        screenMarker = aMap.addMarker(new MarkerOptions()
                .anchor(0.5f,0.5f)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.purple_pin1)));
        //设置Marker在屏幕上,不跟随地图移动
        screenMarker.setPositionByPixels(screenPosition.x,screenPosition.y);
    }

    /**
     * 屏幕中心marker 跳动
     */
    public void startJumpAnimation() {

        if (screenMarker != null ) {
            //根据屏幕距离计算需要移动的目标点
            final LatLng latLng = screenMarker.getPosition();
            Point point =  aMap.getProjection().toScreenLocation(latLng);
            point.y -= dip2px(this,125);
            LatLng target = aMap.getProjection()
                    .fromScreenLocation(point);
            //使用TranslateAnimation,填写一个需要移动的目标点
            Animation animation = new TranslateAnimation(target);
            animation.setInterpolator(new Interpolator() {
                @Override
                public float getInterpolation(float input) {
                    // 模拟重加速度的interpolator
                    if(input <= 0.5) {
                        return (float) (0.5f - 2 * (0.5 - input) * (0.5 - input));
                    } else {
                        return (float) (0.5f - Math.sqrt((input - 0.5f)*(1.5f - input)));
                    }
                }
            });
            //整个移动所需要的时间
            animation.setDuration(600);
            //设置动画
            screenMarker.setAnimation(animation);
            //开始动画
            screenMarker.startAnimation();

        } else {
            Log.e("amap","screenMarker is null");
        }
    }

    //dip和px转换
    private static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public void getMapScreenShot() {
        ProgressUtils.showProgressDialog(this,"正在保存图片");
        aMap.getMapScreenShot(new AMap.OnMapScreenShotListener() {
            @Override
            public void onMapScreenShot(Bitmap bitmap) {
                Log.d("aMap","onMapScreenShot");
            }

            @Override
            public void onMapScreenShot(Bitmap bitmap, int status) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                if(null == bitmap){
                    ProgressUtils.dismissProgressDialog();
                    return;
                }
                try {
                    final String fileName = "Position_" + sdf.format(new Date()) + ".png";
                    FileOutputStream fos = new FileOutputStream(
                            Constants.path_photoDir + File.separator + fileName);
                    boolean b = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    try {
                        fos.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                        ProgressUtils.dismissProgressDialog();
                        return;
                    }
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        ProgressUtils.dismissProgressDialog();
                        return;
                    }
                    StringBuffer buffer = new StringBuffer();
                    if (b) {
                        buffer.append("截屏成功 ");
                        File file = new File(Constants.path_photoDir + File.separator + fileName);
                        Intent i = new Intent();
                        i.putExtra(Constants.positionFilePathKey, file.getName());
                        i.putExtra("lat",lat);
                        i.putExtra("lon",lon);
                        setResult(Constants.RESULT_CODE_POSITION, i);
                        finish();
//                        CompressUtils.compressPic(mContext, new File(Constants.path_photoDir + File.separator + fileName), "compress_",  new CompressUtils.Callback() {
//                            @Override
//                            public void onStart() {
//                            }
//
//                            @Override
//                            public void onSuccess(File file) {
//                                if(FileUtils.checkFileExists(file)) {
//                                    Intent i = new Intent();
//                                    i.putExtra(Constants.positionFilePathKey, file.getName());
//                                    i.putExtra("lat",lat);
//                                    i.putExtra("lon",lon);
//                                    setResult(Constants.RESULT_CODE_POSITION, i);
//                                    finish();
//                                }else {
//                                    ToastUtils.showShort("图片压缩错误：文件未找到");
//                                }
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                            }
//                        });
                    } else {
                        buffer.append("截屏失败 ");
                        ProgressUtils.dismissProgressDialog();
                        return;
                    }
                    if (status != 0)
                        buffer.append("地图渲染完成，截屏无网格");
                    else {
                        buffer.append( "地图未渲染完成，截屏有网格");
                    }
                    ProgressUtils.dismissProgressDialog();
                    LogUtils.e(buffer.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    ProgressUtils.dismissProgressDialog();
                }

            }
        });
    }


    @Override
    public void onDownload(int i, int i1, String s) {

    }

    @Override
    public void onCheckUpdate(boolean b, String s) {

    }

    @Override
    public void onRemove(boolean b, String s, String s1) {

    }

    private String getSdCacheDir(Context context) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            File fExternalStorageDirectory = Environment
                    .getExternalStorageDirectory();
            File autonaviDir = new File(
                    fExternalStorageDirectory, "amapsdk");
            boolean result = false;
            if (!autonaviDir.exists()) {
                result = autonaviDir.mkdir();
            }
            File minimapDir = new File(autonaviDir,
                    "offlineMap");
            if (!minimapDir.exists()) {
                result = minimapDir.mkdir();
            }
            return minimapDir.toString() + "/";
        } else {
            return "";
        }
    }

}
