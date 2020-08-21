package com.liany.clientmodel.view.subView.sceneStep;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.weather.LocalWeatherForecastResult;
import com.amap.api.services.weather.LocalWeatherLive;
import com.amap.api.services.weather.LocalWeatherLiveResult;
import com.amap.api.services.weather.WeatherSearch;
import com.amap.api.services.weather.WeatherSearchQuery;
import com.liany.clientmodel.R;
import com.liany.clientmodel.base.BaseAcitivity;
import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.contract.subView.sceneStep.ProspectingContract;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.diagnose.WeatherEntity;
import com.liany.clientmodel.diagnose.selectUser;
import com.liany.clientmodel.diagnose.sysDict;
import com.liany.clientmodel.presenter.subView.sceneStep.ProspectingPresenter;
import com.liany.clientmodel.utils.ClickUtils;
import com.liany.clientmodel.utils.GPSUtils;
import com.liany.clientmodel.utils.LogUtils;
import com.liany.clientmodel.utils.ProgressUtils;
import com.liany.clientmodel.utils.StringUtils;
import com.liany.clientmodel.view.subView.CreateSceneActivity;
import com.liany.clientmodel.widget.ClearableEditText;
import com.liany.clientmodel.widget.MyDialog;

import java.io.Serializable;
import java.util.List;

import androidx.annotation.Nullable;

/**
 * @创建者 ly
 * @创建时间 2020/3/24
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class ProspectingActivity extends BaseAcitivity implements ProspectingContract.View, WeatherSearch.OnWeatherSearchListener, View.OnClickListener {

    RelativeLayout ivTitleBack;
    TextView tvTitle;
    TextView casetype;
    ImageView typeQuestion;
    TextView tvQhdx;
    ClearableEditText location;
    ImageView ivPosition;
    ClearableEditText accessLocation;
    ImageView ivTitleConfirm;

    private String isCreate = "0";
    private ProspectingContract.Presenter presenter;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private String lat = "0";
    private String lon = "0";
    private CrimeItem crimeItem;
    private MyDialog dialog,myDialog;
    private WeatherSearchQuery mquery;
    private WeatherSearch mweathersearch;
    private Context mContext;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_prospecting;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        casetype = findViewById(R.id.casetype);
        typeQuestion = findViewById(R.id.type_question);
        tvQhdx = findViewById(R.id.tv_qhdx);
        location = findViewById(R.id.location);
        ivPosition = findViewById(R.id.iv_position);
        accessLocation = findViewById(R.id.accessLocation);
        ivTitleConfirm = findViewById(R.id.iv_title_confirm);

        ivTitleBack.setOnClickListener(this);
        typeQuestion.setOnClickListener(this);
        ivPosition.setOnClickListener(this);
        casetype.setOnClickListener(this);
        tvQhdx.setOnClickListener(this);
        ivTitleConfirm.setOnClickListener(this);

        tvTitle.setText("接勘");
        if (isCreate.equals("1")) {
            ivTitleConfirm.setVisibility(View.VISIBLE);
        }else {
            ivTitleConfirm.setVisibility(View.GONE);
        }
        presenter.initValue(crimeItem);
    }

    @Override
    protected void initData() {
        mContext = this;
        crimeItem = (CrimeItem) getIntent().getSerializableExtra("crime");
        isCreate = getIntent().getStringExtra("isCreate");
        presenter = new ProspectingPresenter(this, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(isCreate.equals("1")) {
//                crimeItem.setCasetype(getCaseType());
                crimeItem.setLocation(getLocation());
                crimeItem.setAccessLocation(getAccessLocation());
                finish();
            }else {
                closeProspecting();
            }
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        if(ClickUtils.isFastClick()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.iv_title_back) {
            if (isCreate.equals("1")) {
                crimeItem.setLocation(getLocation());
                crimeItem.setAccessLocation(getAccessLocation());
                finish();
            } else {
                closeProspecting();
            }
        } else if (id == R.id.casetype) {
            presenter.casetype();
        } else if (id == R.id.tv_qhdx) {
            presenter.qhdx();
        } else if (id == R.id.type_question) {
        } else if (id == R.id.iv_position) {
            initLocation(true);
        } else if (id == R.id.iv_title_confirm) {//创建现场
            presenter.checkDate(crimeItem);
        }
    }

    public void showCreateDialog() {
        //显示弹窗
        MyDialog.Builder builder = new MyDialog.Builder(this);
        dialog = builder.setTitle(getString(R.string.prompt))
                .setMsg("是否要创建现场？")
                .setPositiveButton(getString(R.string.confirm), new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
                        presenter.createCrime(crimeItem);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
    }

    @Override
    public void showMsgDialog() {
        MyDialog.Builder builder = new MyDialog.Builder(this);
        myDialog = builder.setTitle(getString(R.string.prompt))
                .setMsg("请填写必填项信息")
                .setPositiveButton("退出", new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
                        finish();
                        myDialog.dismiss();
                    }
                })
                .setNegativeButton("补全信息", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myDialog.dismiss();
                    }
                })
                .create();
        myDialog.show();
    }

    @Override
    public void showCreateView() {
        Intent intent = new Intent(ProspectingActivity.this, CreateSceneActivity.class);
        if (crimeItem != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("crime", crimeItem);
            bundle.putString("isCreate","1");
            intent.putExtras(bundle);
        }
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setCaseType(sysDict dict) {
        if(dict != null) {
            this.casetype.setText(dict.getDictValue());
            crimeItem.setCasetype(dict.getDictValue());
            crimeItem.setCasetypeKey(dict.getDictKey());
        }
    }

    @Override
    public void setCaseType(String caseType) {
        this.casetype.setText(caseType);
    }

    @Override
    public String getCaseType() {
        return this.casetype.getText().toString();
    }

    @Override
    public void setLocation(String location) {
        this.location.setText(location);
        crimeItem.setLocation(location);
    }

    @Override
    public String getLocation() {
        return location.getText().toString();
    }

    @Override
    public void setAccessLocation(String accessLocation) {
        this.accessLocation.setText(accessLocation);
        crimeItem.setAccessLocation(accessLocation);
    }

    @Override
    public String getAccessLocation() {
        return accessLocation.getText().toString();
    }

    @Override
    public void closeEdit() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(location.getWindowToken(), 0);
    }

    @Override
    public void startRadioSelectDictView(int selectType, String title, List<sysDict> dicts, List<Integer> selectList) {
        Intent intent = new Intent();
        intent.setClass(this, Select_RadioList_DictActivity.class);
        intent.putExtra(Constants.SELECT_TITLE, title);
        intent.putExtra(Constants.SELECT_RADIO_DICT, (Serializable) dicts);
        intent.putExtra(Constants.SELECT_POSITION, (Serializable) selectList);
        startActivityForResult(intent, selectType);
    }

    @Override
    public void startCheckSelectDictView(int selectType, String title, List<sysDict> dicts, List<Integer> selectList) {
        Intent intent = new Intent();
        intent.setClass(this, Select_CheckList_DictActivity.class);
        intent.putExtra(Constants.SELECT_TITLE, title);
        intent.putExtra(Constants.SELECT_CHECK_DICT, (Serializable) dicts);
        intent.putExtra(Constants.SELECT_POSITION, (Serializable) selectList);
        startActivityForResult(intent, selectType);
    }

    @Override
    public void startExpandCheckSelectDictView(int selectType, String title, List<sysDict> dicts, List<Integer> selectList) {
        Intent intent = new Intent();
        intent.setClass(this, Select_Expand_CheckActivity.class);
        intent.putExtra(Constants.SELECT_TITLE, title);
        intent.putExtra(Constants.SELECT_EXPAND_CHECK_DICT, (Serializable) dicts);
        intent.putExtra(Constants.SELECT_POSITION, (Serializable) selectList);
        startActivityForResult(intent, selectType);
    }

    @Override
    public void startExpandRadioSelectDictView(int selectType, String title, List<sysDict> dicts, List<Integer> selectList) {
        Intent intent = new Intent();
        intent.setClass(this, Select_Expand_RadioActivity.class);
        intent.putExtra(Constants.SELECT_TITLE, title);
        intent.putExtra(Constants.SELECT_EXPAND_RADIO_DICT, (Serializable) dicts);
        intent.putExtra(Constants.SELECT_POSITION, (Serializable) selectList);
        startActivityForResult(intent, selectType);
    }

    @Override
    public void startSelectUserView(int selectType, String title, List<selectUser> users, String value) {
        Intent intent = new Intent();
        intent.setClass(ProspectingActivity.this, Select_Check_UserActivity.class);
        intent.putExtra(Constants.SELECT_TITLE, title);
        intent.putExtra(Constants.SELECT_CHECK_USER, (Serializable) users);
        intent.putExtra(Constants.SELECT_POSITION, value);
        startActivityForResult(intent, selectType);
    }

    @Override
    public void startSelectUserRadioView(int selectType, String title, List<selectUser> users, String value) {
        Intent intent = new Intent();
        intent.setClass(ProspectingActivity.this, Select_Radio_UserActivity.class);
        intent.putExtra(Constants.SELECT_TITLE, title);
        intent.putExtra(Constants.SELECT_RADIO_USER, (Serializable) users);
        intent.putExtra(Constants.SELECT_POSITION, value);
        startActivityForResult(intent, selectType);
    }

    @Override
    public void startSelectUnitRadioView(int selectType, String title, List<selectUser> users, String value) {
        Intent intent = new Intent();
        intent.setClass(ProspectingActivity.this, Select_Radio_UnitActivity.class);
        intent.putExtra(Constants.SELECT_TITLE, title);
        intent.putExtra(Constants.SELECT_RADIO_USER, (Serializable) users);
        intent.putExtra(Constants.SELECT_POSITION, value);
        startActivityForResult(intent, selectType);
    }

    @Override
    public void setQhdx(String selectObject) {
        tvQhdx.setText(selectObject);
    }

    @Override
    public void setQhdx(List<sysDict> dicts) {
        tvQhdx.setText(StringUtils.sysDictValue2String(dicts));
        crimeItem.setSelectObject(StringUtils.sysDictValue2String(dicts));
        crimeItem.setSelectObjectKey(StringUtils.sysDictKey2String(dicts));
    }

    @Override
    public String getQhdx() {
        return tvQhdx.getText().toString().trim();
    }

    public void closeProspecting() {
        crimeItem.setLocation(getLocation());
        crimeItem.setAccessLocation(getAccessLocation());
        Intent intent = getIntent();
        intent.putExtra("crime", crimeItem);
        setResult(Activity.RESULT_OK, intent);
        finish();
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
                        //检索参数为城市和天气类型，实况天气为WEATHER_TYPE_LIVE、天气预报为WEATHER_TYPE_FORECAST
                        mquery = new WeatherSearchQuery("北京", WeatherSearchQuery.WEATHER_TYPE_LIVE);
                        mweathersearch=new WeatherSearch(mContext);
                        mweathersearch.setOnWeatherSearchListener((ProspectingActivity)mContext);
                        mweathersearch.setQuery(mquery);
                        mweathersearch.searchWeatherAsyn(); //异步搜索
                        GPSUtils.Gps gps = GPSUtils.amap2Gps84(amapLocation.getLatitude(), amapLocation.getLongitude());
                        lat = String.valueOf(gps.getWgLat());
                        lon = String.valueOf(gps.getWgLon());
                        if(crimeItem.getGpsLat() == 0) {
                            crimeItem.setGpsLat(gps.getWgLat());
                        }
                        if(crimeItem.getGpsLon() == 0) {
                            crimeItem.setGpsLon(gps.getWgLon());
                        }
//                        if (isSetAddress) {
                        String address = city + district + street + streetNum + aoiName ;
                        if(crimeItem.getGpsLon() != 0 && crimeItem.getGpsLat() != 0) {
                            address = address + "(" + crimeItem.getGpsLon() + "," + crimeItem.getGpsLat() + ")";
                        }
                        location.setText(address);
                        accessLocation.setText(address);
                        crimeItem.setAccessLocation(address);
                        crimeItem.setLocation(address);

//                        }
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


    @Override
    public void onWeatherLiveSearched(LocalWeatherLiveResult weatherLiveResult, int rCode) {
        if (rCode == 1000) {
            if (weatherLiveResult != null&&weatherLiveResult.getLiveResult() != null) {
                LocalWeatherLive weatherlive = weatherLiveResult.getLiveResult();
                LogUtils.e(weatherlive.getReportTime()+"发布");
                LogUtils.e(weatherlive.getWeather());
                LogUtils.e(weatherlive.getTemperature()+"°");
                LogUtils.e(weatherlive.getWindDirection()+"风     "+weatherlive.getWindPower()+"级");
                LogUtils.e("湿度         "+weatherlive.getHumidity()+"%");

                crimeItem.setTemperature(weatherlive.getTemperature());//温度
                crimeItem.setHumidity(weatherlive.getHumidity());//湿度
                WeatherEntity weatherEntity = StringUtils.selectAmapWeatherCondition(weatherlive.getWeather(),"weatherCondition.json");
                if(weatherEntity != null) {
                    crimeItem.setWeatherCondition(weatherEntity.getDictValue());
                    crimeItem.setWeatherConditionKey(weatherEntity.getDictKey());
                }
                WeatherEntity windDirection = StringUtils.selectAmapWeatherCondition(weatherlive.getWindDirection(),"windDirection.json");
                if(windDirection != null) {
                    crimeItem.setWindDirection(windDirection.getDictValue());
                    crimeItem.setWindDirectionKey(windDirection.getDictKey());
                }
            }else {
                LogUtils.e("暂无数据");
            }
        }else {
            LogUtils.e("获取天气状况失败：" + rCode);
        }
    }

    @Override
    public void onWeatherForecastSearched(LocalWeatherForecastResult localWeatherForecastResult, int i) {

    }

}
