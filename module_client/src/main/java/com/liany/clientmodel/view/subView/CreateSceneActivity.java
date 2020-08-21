package com.liany.clientmodel.view.subView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.liany.clientmodel.R;
import com.liany.clientmodel.base.BaseAcitivity;
import com.liany.clientmodel.base.BaseEvent;
import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.contract.subView.CreateSceneContract;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.diagnose.KCTBASESTATIONDATABean;
import com.liany.clientmodel.diagnose.SceneWifiInfo;
import com.liany.clientmodel.presenter.subView.CreateScenePresenter;
import com.liany.clientmodel.utils.ClickUtils;
import com.liany.clientmodel.utils.GPSUtils;
import com.liany.clientmodel.utils.LogUtils;
import com.liany.clientmodel.utils.SPUtils;
import com.liany.clientmodel.utils.StringUtils;
import com.liany.clientmodel.utils.ToastUtils;
import com.liany.clientmodel.view.subView.sceneStep.VideoViewActivity;
import com.liany.clientmodel.widget.MyDialog;
import com.liany.clientmodel.widget.badge.BadgeRelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.annotation.Nullable;

/**
 * @创建者 ly
 * @创建时间 2020/3/18
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class CreateSceneActivity extends BaseAcitivity implements CreateSceneContract.View, View.OnClickListener {
    RelativeLayout iv_titleBack;
    TextView tv_title;
    ImageView iv_titleSetting;
    FrameLayout fl_create;
    BadgeRelativeLayout llSceneStepProspecting;
    BadgeRelativeLayout llSceneStepVisit;
    BadgeRelativeLayout llSceneStepFigure;
    BadgeRelativeLayout llSceneStepPhoto;
    BadgeRelativeLayout llSceneStepEvidence;
    BadgeRelativeLayout llSceneStepSituation;
    BadgeRelativeLayout llSceneStepOpinion;
    BadgeRelativeLayout llSceneStepWitness;
    ImageView ivTitleConfirm;
    LinearLayout llCollection;
    LinearLayout llLoading;
    BadgeRelativeLayout llSceneStepCollection;
    BadgeRelativeLayout llSceneExtract;
    BadgeRelativeLayout llSceneStationCollection;
    LinearLayout llStartcollection;
    BadgeRelativeLayout llSceneStepBaseInfo;
    BadgeRelativeLayout llSceneStepVideo;

    private WifiManager mWifiManager;
    private MyDialog dialog, searchDialog;
    private List<SceneWifiInfo> scanResultsCopy = new ArrayList<>();
    //    private String collectTime = StringUtils.long2String(System.currentTimeMillis());
    private CrimeItem crimeItem;
    private MyDialog backDialog, comparisonDialog;
    private boolean isLoading = false;
    private Context mContext;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private String lat = "0";
    private String lon = "0";
    private String isCreate = "0";

    private CreateSceneContract.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sub_createscene;
    }

    @Override
    protected void initView() {
        iv_titleBack = findViewById(R.id.iv_title_back);
        tv_title = findViewById(R.id.tv_title);
        iv_titleSetting = findViewById(R.id.iv_title_setting);
        fl_create = findViewById(R.id.fl_create);
        llSceneStepProspecting = findViewById(R.id.ll_scene_step_prospecting);
        llSceneStepVisit = findViewById(R.id.ll_scene_step_visit);
        llSceneStepFigure = findViewById(R.id.ll_scene_step_figure);
        llSceneStepPhoto = findViewById(R.id.ll_scene_step_photo);
        llSceneStepEvidence = findViewById(R.id.ll_scene_step_evidence);
        llSceneStepSituation = findViewById(R.id.ll_scene_step_situation);
        llSceneStepOpinion = findViewById(R.id.ll_scene_step_opinion);
        llSceneStepWitness = findViewById(R.id.ll_scene_step_witness);
        ivTitleConfirm = findViewById(R.id.iv_title_confirm);
        llCollection = findViewById(R.id.ll_collection);
        llLoading = findViewById(R.id.ll_loading);
        llSceneStepCollection = findViewById(R.id.ll_scene_step_collection);
        llSceneExtract = findViewById(R.id.ll_scene_extract);
        llSceneStationCollection = findViewById(R.id.ll_scene_station_collection);
        llStartcollection = findViewById(R.id.ll_startcollection);
        llSceneStepBaseInfo = findViewById(R.id.ll_scene_step_baseInfo);
        llSceneStepVideo = findViewById(R.id.ll_scene_step_video);

        iv_titleBack.setOnClickListener(this);
        llSceneStepBaseInfo.setOnClickListener(this);
        llSceneStepProspecting.setOnClickListener(this);
        llSceneStepVisit.setOnClickListener(this);
        llSceneStepFigure.setOnClickListener(this);
        llSceneStepPhoto.setOnClickListener(this);
        llSceneStepEvidence.setOnClickListener(this);
        llSceneStepSituation.setOnClickListener(this);
        llSceneStepOpinion.setOnClickListener(this);
        llSceneStepWitness.setOnClickListener(this);
        llSceneStepCollection.setOnClickListener(this);
        llSceneExtract.setOnClickListener(this);
        llSceneStationCollection.setOnClickListener(this);
        llSceneStepVideo.setOnClickListener(this);
        ivTitleConfirm.setOnClickListener(this);

        llSceneStepCollection.setVisibility(Constants.state_isShowwifi ? View.VISIBLE : View.INVISIBLE);
        llSceneExtract.setVisibility(Constants.state_isShowExtract ? View.VISIBLE : View.INVISIBLE);
//        presenter.setTitle(crimeItem);
        tv_title.setText("现场详情");
        iv_titleSetting.setVisibility(View.GONE);
        ivTitleConfirm.setVisibility(View.VISIBLE);
//        presenter.initBadge();
    }

    @Override
    protected void initData() {
        mContext = this;
        crimeItem = (CrimeItem) getIntent().getSerializableExtra("crime");
        isCreate = getIntent().getStringExtra("isCreate");
        presenter = new CreateScenePresenter(this, this, crimeItem);
        // 取得WifiManager对象
        mWifiManager = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (isCreate.equals("0")) {
            presenter.initCrimeItem(crimeItem);
        }
        initLocation();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(BaseEvent.CommonEvent event) {
        if (event == BaseEvent.CommonEvent.UPLOAD_BLUETOOTH) {
            String data = (String) event.getObject();
            if (data.equals("com.liany.collectioning")) {
                showLoading();
            } else if (data.equals("com.liany.collectionFail")) {
                closeLoading();
            }
        } else if (event == BaseEvent.CommonEvent.UPLOAD_BLUETOOTH_DATA) {
            closeLoading();
            ToastUtils.showLong("采集完成");
            showCollectionBadge("1");
            List<KCTBASESTATIONDATABean> beans = (List<KCTBASESTATIONDATABean>) event.getObject();
            presenter.setKCTBaseStationList(beans);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showBackDialog(crimeItem);
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        if (ClickUtils.isFastClick()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.iv_title_back) {
            showBackDialog(crimeItem);
        } else if (id == R.id.iv_title_confirm) {
            showSaveDialog();
        } else if (id == R.id.ll_scene_step_baseInfo) {
            presenter.sceneStep_baseInfo(crimeItem);
        } else if (id == R.id.ll_scene_step_prospecting) {
            presenter.sceneStep_prospecting(crimeItem);
        } else if (id == R.id.ll_scene_step_visit) {
            presenter.sceneStep_visit(crimeItem);
        } else if (id == R.id.ll_scene_step_figure) {
            presenter.sceneStep_figure(crimeItem);
        } else if (id == R.id.ll_scene_step_photo) {
            presenter.sceneStep_photo(crimeItem);
        } else if (id == R.id.ll_scene_step_evidence) {
            presenter.sceneStep_evidence(crimeItem);
        } else if (id == R.id.ll_scene_step_situation) {
            presenter.sceneStep_situation(crimeItem);
        } else if (id == R.id.ll_scene_step_opinion) {
            presenter.sceneStep_opinion(crimeItem);
        } else if (id == R.id.ll_scene_step_witness) {
            presenter.sceneStep_witness(crimeItem);
        } else if (id == R.id.ll_scene_step_collection) {//wifi采集界面
            presenter.sceneStep_wifi(crimeItem);
        } else if (id == R.id.ll_scene_station_collection) {
            presenter.sceneStep_stationCollection(crimeItem);
        } else if (id == R.id.ll_scene_extract) {//提取物品
            presenter.sceneStep_extract(crimeItem);
        } else if (id == R.id.ll_scene_step_video) {//视频攻略
            presenter.sceneStep_video();
        }
    }

    @Override
    public void setTitle(String title) {
        tv_title.setText(title);
    }

    @Override
    public void startActivity(Class<?> activity, CrimeItem crimeItem, int requestCode) {
        Intent intent = new Intent(CreateSceneActivity.this, activity);
        if (crimeItem == null) {
            //新建现场
            crimeItem = new CrimeItem();
        }
        //修改现场
        Bundle bundle = new Bundle();
        bundle.putSerializable("crime", crimeItem);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void startProspectingActivity(Class<?> activity, CrimeItem crimeItem, int requestCode) {
        Intent intent = new Intent(CreateSceneActivity.this, activity);
        if (crimeItem == null) {
            //新建现场
            crimeItem = new CrimeItem();
        }
        //修改现场
        Bundle bundle = new Bundle();
        bundle.putSerializable("crime", crimeItem);
        bundle.putString("isCreate", "0");
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void updateCrime(CrimeItem crimeItem) {
        this.crimeItem = crimeItem;
    }

    @Override
    public CrimeItem getCrime() {
        return crimeItem;
    }

    @Override
    public void showBackDialog(CrimeItem crimeItem) {
        String title = "提示";
        MyDialog.Builder builder = new MyDialog.Builder(this);
        backDialog = builder.setTitle(title)
                .setMsg("是否先保存数据后再退出？")
                .setPositiveButton("保存", new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
                        presenter.saveCrime(crimeItem);
                        backDialog.dismiss();
                    }
                })
                .setNegativeButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        backDialog.dismiss();
                        finish();
                    }
                })
                .create();
        backDialog.show();
    }

    @Override
    public void showSaveDialog() {
        String msg = "";
        String title = "";
        msg = presenter.getBackMsg(msg);
        if (msg.equals("")) {
            msg = "是否保存数据";
            title = getString(R.string.prompt);
        } else {
            title = "警告 : 请填写必填项信息";
        }
//        msg = CheckCrime.needToCheckInformation(crimeItem,msg);
        MyDialog.Builder builder = new MyDialog.Builder(this);
        backDialog = builder.setTitle(title)
                .setMsg(msg)
                .setPositiveButton("保存", new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
                        presenter.saveCrime(crimeItem);
                        backDialog.dismiss();
//                        finish();
                    }
                })
                .setNegativeButton("补全信息", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        backDialog.dismiss();
                    }
                })
                .create();
        backDialog.show();
    }

    @Override
    public void showBaseInfoBadge(String value) {
        if (value.equals("0")) {
            llSceneStepBaseInfo.hiddenBadge();
        } else {
            llSceneStepBaseInfo.showBadge(value);
        }
    }

    @Override
    public void showProspectingBadge(String value) {
        if (value.equals("0")) {
            llSceneStepProspecting.hiddenBadge();
        } else {
            llSceneStepProspecting.showBadge(value);
        }
    }

    @Override
    public void showVisitBadge(String value) {
        if (value.equals("0")) {
            llSceneStepVisit.hiddenBadge();
        } else {
            llSceneStepVisit.showBadge(value);
        }
    }

    @Override
    public void showFigureBadge(String value) {
        if (value.equals("0")) {
            llSceneStepFigure.hiddenBadge();
        } else {
            llSceneStepFigure.showBadge(value);
        }
    }

    @Override
    public void showPhotoBadge(String value) {
        if (value.equals("0")) {
            llSceneStepPhoto.hiddenBadge();
        } else {
            llSceneStepPhoto.showBadge(value);
        }
    }

    @Override
    public void showEvidenceBadge(String value) {
        if (value.equals("0")) {
            llSceneStepEvidence.hiddenBadge();
        } else {
            llSceneStepEvidence.showBadge(value);
        }
    }

    @Override
    public void showSituationBadge(String value) {
        if (value.equals("0")) {
            llSceneStepSituation.hiddenBadge();
        } else {
            llSceneStepSituation.showBadge(value);
        }
    }

    @Override
    public void showOpinionBadge(String value) {
        if (value.equals("0")) {
            llSceneStepOpinion.hiddenBadge();
        } else {
            llSceneStepOpinion.showBadge(value);
        }
    }

    @Override
    public void showWitnessBadge(String value) {
        if (value.equals("0")) {
            llSceneStepWitness.hiddenBadge();
        } else {
            llSceneStepWitness.showBadge(value);
        }
    }

    @Override
    public void showWifiBadge(String value) {
        if (value.equals("0")) {
            llSceneStepCollection.hiddenBadge();
        } else {
            llSceneStepCollection.showBadge(value);
        }
    }

    @Override
    public void showExtractBadge(String value) {
        if (value.equals("0")) {
            llSceneExtract.hiddenBadge();
        } else {
            llSceneExtract.showBadge(value);
        }
    }

    @Override
    public void showCollectionBadge(String value) {
        if (value.equals("0")) {
            llSceneStationCollection.hiddenBadge();
        } else {
            llSceneStationCollection.showBadge(value);
        }
    }

    @Override
    public void closeProspectingBadge() {
        llSceneStepProspecting.hiddenBadge();
    }

    @Override
    public void closeVisitBadge() {
        llSceneStepVisit.hiddenBadge();
    }

    @Override
    public void closeFigureBadge() {
        llSceneStepFigure.hiddenBadge();
    }

    @Override
    public void closePhotoBadge() {
        llSceneStepPhoto.hiddenBadge();
    }

    @Override
    public void closeEvidenceBadge() {
        llSceneStepEvidence.hiddenBadge();
    }

    @Override
    public void closeSituationBadge() {
        llSceneStepSituation.hiddenBadge();
    }

    @Override
    public void closeOpinionBadge() {
        llSceneStepOpinion.hiddenBadge();
    }

    @Override
    public void closeWitnessBadge() {
        llSceneStepWitness.hiddenBadge();
    }

    @Override
    public void showLoading() {
        llLoading.setVisibility(View.VISIBLE);
        llStartcollection.setVisibility(View.GONE);
        llSceneStationCollection.setEnabled(false);
    }

    @Override
    public void closeLoading() {
        llLoading.setVisibility(View.GONE);
        llStartcollection.setVisibility(View.VISIBLE);
        llSceneStationCollection.setEnabled(true);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void setCrimeItem(CrimeItem item) {
        this.crimeItem = item;
    }

    @Override
    public void startCollectWifi() {
        if (!checkWifiState()) {
            ToastUtils.showShort("请打开WIFI开关，确保WIFI正常采集");
        } else {
            scanResultsCopy.clear();
            scanResultsCopy.addAll(ScanResult2SceneWifiInfo(getWifiList(mContext)));
            crimeItem.setWifiInfos(scanResultsCopy);
//            crimeItem.getWifiInfos().clear();
//            crimeItem.getWifiInfos().addAll(scanResultsCopy);
            presenter.setWIfiList(scanResultsCopy);
//            collectTime = StringUtils.long2String(System.currentTimeMillis());
        }
    }

    @Override
    public void startVideo() {
        Intent intent = new Intent(CreateSceneActivity.this, VideoViewActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 检查当前WIFI状态
     *
     * @return 关闭 false 打开true
     */
    @SuppressLint("WrongConstant")
    public boolean checkWifiState() {
        if (mWifiManager.getWifiState() == 0) {
            return false;
        } else if (mWifiManager.getWifiState() == 1) {
            return false;
        } else if (mWifiManager.getWifiState() == 2) {
            return true;
        } else if (mWifiManager.getWifiState() == 3) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 打开或关闭wifi
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.CHANGE_WIFI_STATE"/>}</p>
     *
     * @param enabled {@code true}: 打开<br>{@code false}: 关闭
     */
    public void setWifiEnabled(final boolean enabled) {
        if (enabled) {
            if (!mWifiManager.isWifiEnabled()) {
                mWifiManager.setWifiEnabled(true);
            }
        } else {
            if (mWifiManager.isWifiEnabled()) {
                mWifiManager.setWifiEnabled(false);
            }
        }
    }

    private List<SceneWifiInfo> ScanResult2SceneWifiInfo(List<ScanResult> results) {
        List<SceneWifiInfo> sceneWifiInfos = new ArrayList<>();
        for (ScanResult result : results) {
            if (StringUtils.checkString(result.SSID)) {
                SceneWifiInfo wifiInfo = new SceneWifiInfo();
                wifiInfo.setId(StringUtils.getUUID());
                wifiInfo.setINVERTIGATIONID(crimeItem.getId());
                wifiInfo.setCREATEUSER((String) SPUtils.getParam(this, Constants.sp_userName, ""));
                wifiInfo.setCREATEDATETIME(StringUtils.long2String(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
                wifiInfo.setCOLLECTIONDATETIME(StringUtils.long2String(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
//            wifiInfo.setLINKSPEED();
                wifiInfo.setSSID(result.SSID);
                wifiInfo.setBSSID(result.BSSID);
//            wifiInfo.setHIDDENSSID();
                wifiInfo.setMACADDRESS(result.BSSID);
//            wifiInfo.setNETWORKLD();
                wifiInfo.setRSSI(WifiManager.calculateSignalLevel(result.level, 100) + "");
//            wifiInfo.setSUPPLICANTSTATE();
//            wifiInfo.setDETAILEDSTATEOF();
                wifiInfo.setCrimeId(crimeItem.getId());
                sceneWifiInfos.add(wifiInfo);
            }
        }
        return sceneWifiInfos;
    }

    /**
     * 获取WIFI列表
     * <p>需要权限{@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/> <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>}</p>
     * <p>注意Android6.0上需要主动申请定位权限，并且打开定位开关</p>
     *
     * @param context 上下文
     * @return wifi列表
     */
    public List<ScanResult> getWifiList(Context context) {
        WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> scanResults = wm.getScanResults();

        Collections.sort(scanResults, new Comparator<ScanResult>() {
            @Override
            public int compare(ScanResult scanResult1, ScanResult scanResult2) {
                return scanResult2.level - scanResult1.level;
            }
        });
//        filterScanResult(scanResults);
        return scanResults;
    }

    private void initLocation() {
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
                        GPSUtils.Gps gps = GPSUtils.amap2Gps84(amapLocation.getLatitude(), amapLocation.getLongitude());
                        lat = String.valueOf(gps.getWgLat());
                        lon = String.valueOf(gps.getWgLon());
                        if (crimeItem.getGpsLat() == 0) {
                            crimeItem.setGpsLat(gps.getWgLat());
                        }
                        if (crimeItem.getGpsLon() == 0) {
                            crimeItem.setGpsLon(gps.getWgLon());
                        }
                        String location = city + district + street + streetNum + aoiName ;
                        if(crimeItem.getGpsLon() != 0 && crimeItem.getGpsLat() != 0) {
                            location = location + "(" + crimeItem.getGpsLon() + "," + crimeItem.getGpsLat() + ")";
                        }
                        if (!StringUtils.checkString(crimeItem.getLocation())) {
                            crimeItem.setLocation(location);
                        }
                        if (!StringUtils.checkString(crimeItem.getAccessLocation())) {
                            crimeItem.setAccessLocation(location);
                        }

                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        LogUtils.e("location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                    }
                }
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
