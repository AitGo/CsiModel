package com.liany.csiclient;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.liany.csiclient.base.AppApplication;
import com.liany.clientmodel.base.BaseAcitivity;
import com.liany.clientmodel.base.Constants;
import com.liany.csiclient.contract.LoginContract;
import com.liany.csiclient.presenter.LoginPresenter;
import com.liany.clientmodel.utils.ClickUtils;
import com.liany.clientmodel.widget.MyDialog;
import com.liany.csiserverapp.andServer.manager.ServerManager;
import com.liany.csiserverapp.debug.ServerApplication;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.xinghuo.mpaas.librarysso.LoginUserInfo;
import com.xinghuo.mpaas.librarysso.SSOContext;
import com.xinghuo.mpaas.librarysso.VerifyResponseCallback;

import java.util.List;

import androidx.annotation.NonNull;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @创建者 ly
 * @创建时间 2020/3/4
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class LoginActivity extends BaseAcitivity implements LoginContract.View, EasyPermissions.PermissionCallbacks, View.OnClickListener {

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
            Manifest.permission.CAMERA,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.RECORD_AUDIO
    };
    EditText etLoginUser;
    EditText etLoginPassword;
    Button btnLoginSignin;
    ImageView ivLoginScan;
    TextView tvLoginVersion;
    Button btnLoginIp;

    private LoginContract.Presenter presenter;
    private MyDialog updateDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_liany_model;
    }

    @Override
    protected void initView() {
        etLoginUser = findViewById(R.id.et_login_user);
        etLoginPassword = findViewById(R.id.et_login_password);
        btnLoginSignin = findViewById(R.id.btn_login_signin);
        ivLoginScan = findViewById(R.id.iv_login_scan);
        tvLoginVersion = findViewById(R.id.tv_login_version);
        btnLoginIp = findViewById(R.id.btn_login_ip);

        btnLoginSignin.setOnClickListener(this);
        ivLoginScan.setOnClickListener(this);
        btnLoginIp.setOnClickListener(this);

        setVersion(getAppVersionName(this));
        btnLoginIp.setVisibility(Constants.isDebug ? View.VISIBLE : View.GONE);
        presenter.initData();
    }

    @Override
    protected void initData() {
        getPermission();
        ServerManager serverManager = new ServerManager(this);
        serverManager.startServer();
        ServerApplication.setDaoSession(AppApplication.getDaoSession());
        presenter = new LoginPresenter(this, this);
        presenter.checkIsFirst();
        if (getIntent().getBooleanExtra("show", false)) {
            showUpdateDialog();
        }
    }


    @Override
    public void onClick(View view) {
        if (ClickUtils.isFastClick()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.btn_login_signin) {
            presenter.login();
//                presenter.checkVersion(getVersionCode(this),getAppVersionName(this));
        } else if (id == R.id.iv_login_scan) {
            showScanView(Constants.code_scan);
        } else if (id == R.id.btn_login_ip) {
            showScanView(Constants.code_scan_ip);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    public void showUpdateDialog() {
        MyDialog.Builder builder = new MyDialog.Builder(this);
        updateDialog = builder.setTitle(getString(R.string.prompt))
                .setMsg("是否要更新数据库信息")
                .setPositiveButton(getString(R.string.confirm), () -> {
                    presenter.updateDB();
                    updateDialog.dismiss();
                })
                .setNegativeButton(getString(R.string.cancel), (dialog, which) -> updateDialog.dismiss())
                .create();
        updateDialog.show();
    }

    @Override
    public void showScanView(int code) {
        Intent intent = new Intent(LoginActivity.this, CaptureActivity.class);
        startActivityForResult(intent, code);
    }

    @Override
    public String getAccount() {
        return etLoginUser.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return etLoginPassword.getText().toString().trim();
    }

    @Override
    public void showMainActivity() {
        ARouter.getInstance().build("/csi/main").navigation();
        finish();
    }

    @Override
    public void setAccount(String account) {
        etLoginUser.setText(account);
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

    public String getAppVersionName(Context context) {
        String appVersionName = "";
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            appVersionName = info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appVersionName;
    }

    public int getVersionCode(Context context)//获取版本号(内部识别号)
    {
        try {
            PackageInfo pi=context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
    }

    public void setVersion(String version) {
        tvLoginVersion.setText(getString(R.string.version_text) + version);
    }

    @Override
    public void showScanView() {
        Intent intent = new Intent(LoginActivity.this, CaptureActivity.class);
        startActivityForResult(intent, Constants.code_scan);
    }

    @Override
    public void checkVersion() {
        presenter.checkVersion(getVersionCode(this),getAppVersionName(this));
    }

    @Override
    public void initXHData() {
        String token = "";
        Intent intent = getIntent();
        if (intent != null) {
            token = intent.getStringExtra("token");
        }
        if (TextUtils.isEmpty(token)) {
            return;
        } else {
            verifyToken(token);
        }
    }

    /**
     * 校验token获取用户信息
     *
     * @param token 从平台APP获取的token
     */
    private void verifyToken(final String token) {
        SSOContext.verifyToken(token, new VerifyResponseCallback() {
            @Override
            public void responseSuccess(String s) {
                LoginUserInfo userInfo = new Gson().fromJson(s, LoginUserInfo.class);
                if (userInfo != null && userInfo.getCode() == 200) {
                    Message msg = Message.obtain();
                    msg.obj = userInfo.getData();
                    msg.what = 0;
                    mHandler.sendMessage(msg);
                } else {
                    mHandler.sendEmptyMessage(1);
                }
            }

            @Override
            public void responseFail(String s) {
                Message msg = Message.obtain();
                msg.obj = s;
                msg.what = 2;
                mHandler.sendMessage(msg);
            }
        });
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    LoginUserInfo.DataBean userInfo = (LoginUserInfo.DataBean) msg.obj;
//                    Toast.makeText(LoginActivity.this, "token校验成功："
//                                    + "登录id：" + userInfo.getLoginId()
//                                    + "身份证号：" + userInfo.getIdcard()
//                                    + "警号：" + userInfo.getUserCode()
//                            , Toast.LENGTH_LONG).show();
                    //登录
                    presenter.autoLogin(userInfo.getUserCode());
                    break;
                case 1:
                    Toast.makeText(LoginActivity.this, "验证失败", Toast.LENGTH_LONG).show();
                    finish();
                    break;
                case 2:
                    Toast.makeText(LoginActivity.this, (String) msg.obj, Toast.LENGTH_LONG).show();
                    finish();
                    break;
            }
        }
    };

}
