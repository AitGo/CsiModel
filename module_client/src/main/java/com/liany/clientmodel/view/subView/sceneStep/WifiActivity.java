package com.liany.clientmodel.view.subView.sceneStep;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liany.clientmodel.R;
import com.liany.clientmodel.adapter.WifiAdapter;
import com.liany.clientmodel.base.BaseAcitivity;
import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.diagnose.CrimeItem;
import com.liany.clientmodel.diagnose.SceneWifiInfo;
import com.liany.clientmodel.utils.ClickUtils;
import com.liany.clientmodel.utils.SPUtils;
import com.liany.clientmodel.utils.StringUtils;
import com.liany.clientmodel.utils.ToastUtils;
import com.liany.clientmodel.widget.CsiProgressDialog;
import com.liany.clientmodel.widget.MyDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @创建者 ly
 * @创建时间 2020/3/24
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class WifiActivity extends BaseAcitivity implements View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    ImageView ivTitleConfirm;
    ImageView ivTitleSearchWifi;
    RecyclerView rvWifi;

    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_SETTINGS,
            Manifest.permission.CAMERA
    };

    private CrimeItem crime;
    private WifiManager mWifiManager;
    private List<SceneWifiInfo> scanResultsCopy = new ArrayList<>();
    private WifiAdapter adapter;
    private MyDialog dialog;
    private Context mContext;
    private MyDialog searchDialog;
    private CsiProgressDialog progressDialog;
    private List<SceneWifiInfo> wifiInfos = new ArrayList<>();
    private String collectTime = StringUtils.long2String(System.currentTimeMillis());
    private View emptyView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_wifi;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        ivTitleConfirm = findViewById(R.id.iv_title_confirm);
        ivTitleSearchWifi = findViewById(R.id.iv_title_search_wifi);
        rvWifi = findViewById(R.id.rv_wifi);

        ivTitleBack.setOnClickListener(this);
        ivTitleConfirm.setOnClickListener(this);
        ivTitleSearchWifi.setOnClickListener(this);

        ivTitleBack.setVisibility(View.VISIBLE);
        ivTitleConfirm.setVisibility(View.GONE);
        ivTitleSearchWifi.setVisibility(View.VISIBLE);
        tvTitle.setText("Wifi采集");

        rvWifi.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WifiAdapter(R.layout.item_adapter_wifi,scanResultsCopy);
        rvWifi.setAdapter(adapter);
        // 没有数据的时候默认显示该布局
        emptyView = getLayoutInflater().inflate(R.layout.item_adapter_empty, (ViewGroup) rvWifi.getParent(), false);
        adapter.setEmptyView(emptyView);
    }

    @Override
    protected void initData() {
        mContext = this;
        crime = (CrimeItem) getIntent().getSerializableExtra("crime");
        if(crime.getWifiInfos() != null) {
            scanResultsCopy.clear();
            scanResultsCopy.addAll(crime.getWifiInfos());
        }
        // 取得WifiManager对象
        mWifiManager = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            saveWifi();
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
            saveWifi();
            finish();
        } else if (id == R.id.iv_title_confirm) {
        } else if (id == R.id.iv_title_search_wifi) {
            if (!checkWifiState()) {
                ToastUtils.showShort("WIFI没有打开，请打开WIFI开关后重试");
            } else {
                scanResultsCopy.clear();
                scanResultsCopy.addAll(ScanResult2SceneWifiInfo(getWifiList(mContext)));
                adapter.notifyDataSetChanged();
                collectTime = StringUtils.long2String(System.currentTimeMillis());
            }
        }
    }

    private void saveWifi() {
//        String connWifiID = "-1";
//        WifiInfo info = mWifiManager.getConnectionInfo();
//        for(SceneWifiInfo wifiInfo : scanResultsCopy) {
//            if(wifiInfo.getSSID().equals(info.getSSID())) {
//                connWifiID = wifiInfo.getId();
//            }
//        }
//        for(SceneWifiInfo wifiInfo : scanResultsCopy) {
////            wifiInfo.setNETWORKLD(connWifiID);
//            wifiInfos.add(wifiInfo);
//        }
        Intent intent = getIntent();
        crime.setWifiInfos(scanResultsCopy);
        intent.putExtra("crime", crime);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    /**
     * 检查当前WIFI状态
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
            return  false;
        }
    }

    private List<SceneWifiInfo> ScanResult2SceneWifiInfo(List<ScanResult> results) {
        List<SceneWifiInfo> sceneWifiInfos = new ArrayList<>();
        for(ScanResult result : results) {
            if(StringUtils.checkString(result.SSID)) {
                SceneWifiInfo wifiInfo = new SceneWifiInfo();
                wifiInfo.setId(StringUtils.getUUID());
                wifiInfo.setINVERTIGATIONID(crime.getId());
                wifiInfo.setCREATEUSER((String) SPUtils.getParam(this, Constants.sp_userName,""));
                wifiInfo.setCREATEDATETIME(StringUtils.long2String(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss"));
                wifiInfo.setCOLLECTIONDATETIME(StringUtils.long2String(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss"));
//            wifiInfo.setLINKSPEED();
                wifiInfo.setSSID(result.SSID);
                wifiInfo.setBSSID(result.BSSID);
//            wifiInfo.setHIDDENSSID();
                wifiInfo.setMACADDRESS(result.BSSID);
//            wifiInfo.setNETWORKLD();
                wifiInfo.setRSSI(WifiManager.calculateSignalLevel(result.level, 100)+"");
//            wifiInfo.setSUPPLICANTSTATE();
//            wifiInfo.setDETAILEDSTATEOF();
                wifiInfo.setCrimeId(crime.getId());
                sceneWifiInfos.add(wifiInfo);
            }
        }
        return sceneWifiInfos;
    }

    /**
     *
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

    /**
     * 以 SSID 为关键字，过滤掉信号弱的选项
     * @param list
     * @return
     */
    public static List<ScanResult> filterScanResult(final List<ScanResult> list) {
        LinkedHashMap<String, ScanResult> linkedMap = new LinkedHashMap<>(list.size());
        for (ScanResult rst : list) {
            if (linkedMap.containsKey(rst.SSID)) {
                if (rst.level > linkedMap.get(rst.SSID).level) {
                    linkedMap.put(rst.SSID, rst);
                }
                continue;
            }
            linkedMap.put(rst.SSID, rst);
        }
        list.clear();
        list.addAll(linkedMap.values());
        return list;
    }

    public void showProgress(String msg) {
        if(progressDialog == null) {
            progressDialog = new CsiProgressDialog(this,msg);
        }else {
            progressDialog.setMessage(msg);
        }
        progressDialog.show();
    }

}
