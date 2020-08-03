package com.liany.csiserverapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import pub.devrel.easypermissions.EasyPermissions;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.liany.csiserverapp.andServer.manager.ServerManager;
import com.liany.csiserverapp.base.BaseEvent;
import com.liany.csiserverapp.base.Constants;
import com.liany.csiserverapp.bt.BtBase;
import com.liany.csiserverapp.bt.BtServer;
import com.liany.csiserverapp.dao.database.greenDao.db.CompareEntityDao;
import com.liany.csiserverapp.dao.database.greenDao.db.ComparePhotoDao;
import com.liany.csiserverapp.dao.database.greenDao.db.ContactsEntityDao;
import com.liany.csiserverapp.dao.database.greenDao.db.CrimeItemDao;
import com.liany.csiserverapp.dao.database.greenDao.db.DaoSession;
import com.liany.csiserverapp.dao.database.greenDao.db.EvidenceEntityDao;
import com.liany.csiserverapp.dao.database.greenDao.db.GoodEntityDao;
import com.liany.csiserverapp.dao.database.greenDao.db.ItemEntityDao;
import com.liany.csiserverapp.dao.database.greenDao.db.PhotoDao;
import com.liany.csiserverapp.dao.database.greenDao.db.SceneWifiInfoDao;
import com.liany.csiserverapp.dao.database.greenDao.db.ToolEntityDao;
import com.liany.csiserverapp.dao.database.greenDao.db.WitnessEntityDao;
import com.liany.csiserverapp.debug.ServerApplication;
import com.liany.csiserverapp.diagnose.CrimeItem;
import com.liany.csiserverapp.diagnose.Photo;
import com.liany.csiserverapp.utils.FileUtils;
import com.liany.csiserverapp.utils.LogUtils;
import com.liany.csiserverapp.utils.SPUtils;
import com.liany.csiserverapp.utils.StringUtils;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnConfirmListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.List;


public class Server_MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks, View.OnClickListener, BtBase.Listener {

    private Button btnRestart,btnSetting,btnTel,btnDeleteUpload;
    private TextView tv,tvAllCount,tvUploadCount;

    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_SETTINGS
    };

    private Context mContext;
    private WifiManager mWifiManager;
    private DaoSession daoSession = ServerApplication.getDaoSession();

    private BtServer mServer;
    private ServerManager serverManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getPermission();
        setContentView(R.layout.activity_server_main);

        btnRestart = findViewById(R.id.btn_restart);
        btnSetting = findViewById(R.id.btn_setting);
        btnTel = findViewById(R.id.btn_tel);
        btnDeleteUpload = findViewById(R.id.btn_deleteUpload);
        tv = findViewById(R.id.tv);
        tvAllCount = findViewById(R.id.tv_all_count);
        tvUploadCount = findViewById(R.id.tv_upload_count);
        btnRestart.setOnClickListener(this);
        btnSetting.setOnClickListener(this);
        btnTel.setOnClickListener(this);
        btnDeleteUpload.setOnClickListener(this);

        tv = findViewById(R.id.tv);
        mContext = this;
        String serialNumber = StringUtils.getSerialNumber();
        SPUtils.setParam(this, Constants.sp_deviceId, serialNumber);

        if(Constants.isUseService) {
            Intent intent = new Intent(this, AndService.class);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            //8以上使用前台服务
//            startForegroundService(intent);
//        } else {
            startService(intent);
//        }
        }else {
            if(serverManager == null) {
                serverManager = new ServerManager(getApplicationContext());
            }
            serverManager.startServer();
        }

        BatteryManager batteryManager = (BatteryManager) getSystemService(BATTERY_SERVICE);
        int battery = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        LogUtils.e("battery:" + battery);
        tv.setText("当前电量:  " + battery);
        long count = daoSession.getCrimeItemDao().queryBuilder().count();
        tvAllCount.setText("" + count);
        long upload = daoSession.getCrimeItemDao().queryBuilder().where(CrimeItemDao.Properties.IsUpload.eq("upload")).count();
        tvUploadCount.setText("" + upload);
//        initDir();
        mServer = new BtServer(this);

    }

    private void initDir() {
//        File pic = new File(StringUtils.externalSDCardPath() + "/csiServer/Picture");
//        if (!pic.exists()) {
//            pic.mkdirs();
//        }
//        File zip = new File(StringUtils.externalSDCardPath() + "/csiServer/Zip/victim");
//        if(!zip.exists()) {
//            zip.mkdirs();
//        }
//        File doc = new File(StringUtils.externalSDCardPath() + "/csiServer/Documents");
//        if(!doc.exists()) {
//            doc.mkdirs();
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(BaseEvent.CommonEvent event) {
        if (event == BaseEvent.CommonEvent.CREATE_CRIME) {
            long count = daoSession.getCrimeItemDao().queryBuilder().count();
            tvAllCount.setText("" + count);
        } else if (event == BaseEvent.CommonEvent.DELETE_CRIME) {
            long count = daoSession.getCrimeItemDao().queryBuilder().count();
            tvAllCount.setText("" + count);
        } else if (event == BaseEvent.CommonEvent.UPLOAD_CRIME) {
            long upload = daoSession.getCrimeItemDao().queryBuilder().where(CrimeItemDao.Properties.IsUpload.eq("upload")).count();
            tvUploadCount.setText("" + upload);
        }
    }


    /*
     * 适配于Android_O上创建HotSpot的方法
     */
    public void setWifiApEnabledForAndroidO() {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            wifiManager.startLocalOnlyHotspot(new WifiManager.LocalOnlyHotspotCallback() {
                /*
                *这里是成功开启wifi后回调的方法，此处的reservation中可以通过.getWifiConfiguration().获取
                *到WifiConfiguration，同样的，在WifiConfiguration中可以获取到开启热点的SSID和preSharedKey，
                *此方法开启的热点的名称和密码是系统随机生产的，无法自定义名称和密码，至少我到目前为止
                没有找到可用于设置8.0名称和密码的方法哈哈哈。
                */
                @Override
                public void onStarted(WifiManager.LocalOnlyHotspotReservation reservation) {
                    super.onStarted(reservation);
                    String SSID = reservation.getWifiConfiguration().SSID;
                    String preSharedKey = reservation.getWifiConfiguration().preSharedKey;
                }

                @Override
                public void onStopped() {
                    super.onStopped();
                }

                @Override
                public void onFailed(int reason) {
                    super.onFailed(reason);
                }
            }, null);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_restart) {
            if(Constants.isUseService) {
                Intent intent = new Intent(this, AndService.class);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    startForegroundService(intent);
//                } else {
                startService(intent);
//                }
            }else {
                if(serverManager == null) {
                    serverManager = new ServerManager(getApplicationContext());
                }
                serverManager.stopServer();
                serverManager.startServer();
            }

        }else if(v.getId() == R.id.btn_setting){
            Intent setting = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
            startActivity(setting);
        }else if(v.getId() == R.id.btn_deleteUpload) {
            long upload = daoSession.getCrimeItemDao().queryBuilder().where(CrimeItemDao.Properties.IsUpload.eq("upload")).count();
            if(upload > 0) {
                new XPopup.Builder(this).asConfirm(getString(R.string.prompt), "是否删除已经上传的现场数据",
                        new OnConfirmListener() {
                            @Override
                            public void onConfirm() {
                                deleteUpload();
                            }
                        })
                        .show();
            }else {
                Toast.makeText(this,"暂无数据可以清理",Toast.LENGTH_SHORT).show();
            }
        }else if(v.getId() == R.id.btn_tel) {
            String content = "设备编号：" + StringUtils.getSerialNumber() +
                    "\n" + "\n" + "联系电话：" + getString(R.string.tel);
            new XPopup.Builder(this).asConfirm("售后服务", content,
                    new OnConfirmListener() {
                        @Override
                        public void onConfirm() {

                        }
                    })
                    .show();
        }
    }

    private void deleteUpload() {
        List<CrimeItem> uploads = daoSession.getCrimeItemDao().queryBuilder().where(CrimeItemDao.Properties.IsUpload.eq("upload")).list();
        for(CrimeItem item : uploads) {
            daoSession.getContactsEntityDao().deleteInTx(daoSession.getContactsEntityDao().queryBuilder().where(ContactsEntityDao.Properties.CrimeId.eq(item.getId())).list());
            daoSession.getItemEntityDao().deleteInTx(daoSession.getItemEntityDao().queryBuilder().where(ItemEntityDao.Properties.CrimeId.eq(item.getId())).list());
            daoSession.getToolEntityDao().deleteInTx(daoSession.getToolEntityDao().queryBuilder().where(ToolEntityDao.Properties.CrimeId.eq(item.getId())).list());
            List<Photo> photos = daoSession.getPhotoDao().queryBuilder().where(PhotoDao.Properties.CrimeId.eq(item.getId())).list();
            daoSession.getPhotoDao().deleteInTx(photos);
            daoSession.getEvidenceEntityDao().deleteInTx(daoSession.getEvidenceEntityDao().queryBuilder().where(EvidenceEntityDao.Properties.CrimeId.eq(item.getId())).list());
            daoSession.getWitnessEntityDao().deleteInTx(daoSession.getWitnessEntityDao().queryBuilder().where(WitnessEntityDao.Properties.CrimeId.eq(item.getId())).list());
            daoSession.getSceneWifiInfoDao().deleteInTx(daoSession.getSceneWifiInfoDao().queryBuilder().where(SceneWifiInfoDao.Properties.CrimeId.eq(item.getId())).list());
            daoSession.getGoodEntityDao().deleteInTx(daoSession.getGoodEntityDao().queryBuilder().where(GoodEntityDao.Properties.CrimeId.eq(item.getId())).list());
            daoSession.getCrimeItemDao().delete(item);
            FileUtils.deleteDirectory(Constants.photoPath + File.separator + item.getId());
            daoSession.getCompareEntityDao().deleteInTx(daoSession.getCompareEntityDao().queryBuilder().where(CompareEntityDao.Properties.CrimeId.eq(item.getId())).list());
            daoSession.getComparePhotoDao().deleteInTx(daoSession.getComparePhotoDao().queryBuilder().where(ComparePhotoDao.Properties.SceneId.eq(item.getId())).list());
        }
        long count = daoSession.getCrimeItemDao().queryBuilder().count();
        tvAllCount.setText("" + count);
        long upload = daoSession.getCrimeItemDao().queryBuilder().where(CrimeItemDao.Properties.IsUpload.eq("upload")).count();
        tvUploadCount.setText("" + upload);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    /**
     * 检查权限
     *
     * @param
     * @since 2.5.0
     */
    private void getPermission() {
        if (EasyPermissions.hasPermissions(this, needPermissions)) {
            //已经打开权限
//            Toast.makeText(this, "已经申请相关权限", Toast.LENGTH_SHORT).show();
        } else {
            //没有打开相关权限、申请权限
            EasyPermissions.requestPermissions(this, "需要获取您的存储、定位权限", 1, needPermissions);
        }

    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void socketNotify(int state, final Object obj) {
        if (isDestroyed())
            return;
        String msg = null;
        switch (state) {
            case BtBase.Listener.CONNECTED:
                BluetoothDevice dev = (BluetoothDevice) obj;
                msg = String.format("与%s(%s)连接成功", dev.getName(), dev.getAddress());
//                mTips.setText(msg);
                break;
            case BtBase.Listener.DISCONNECTED:
                mServer.listen();
                msg = "连接断开,正在重新监听...";
//                mTips.setText(msg);
                break;
            case BtBase.Listener.MSG:
                msg = String.format("\n%s", obj);
//                mLogs.append(msg);
                break;
        }
        ServerApplication.toast(msg, 0);
    }
}
