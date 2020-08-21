package ynga.liany.csiclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import pub.devrel.easypermissions.EasyPermissions;
import ynga.liany.csiclient.base.AppApplication;
import ynga.liany.csiclient.contract.LoginContract;
import ynga.liany.csiclient.presenter.LoginPresenter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.liany.clientmodel.base.BaseAcitivity;
import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.utils.ClickUtils;
import com.liany.clientmodel.utils.LogUtils;
import com.liany.clientmodel.utils.ToastUtils;
import com.liany.clientmodel.widget.MyDialog;
import com.liany.csiserverapp.AndService;
import com.liany.csiserverapp.andServer.manager.ServerManager;
import com.liany.csiserverapp.debug.ServerApplication;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.xinghuo.mpaas.librarysso.SSOContext;
import com.xinghuo.mpaas.librarysso.VerifyResponseCallback;
import com.xinghuo.mpaas.librarysso.VerifyToken;

import java.util.List;

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
        return R.layout.activity_login_ks;
    }

    @Override
    protected void initView() {
        etLoginUser = findViewById(R.id.et_login_user);
        etLoginPassword = findViewById(R.id.et_login_password);
        btnLoginSignin = findViewById(R.id.btn_login_signin);
        ivLoginScan = findViewById(R.id.iv_login_scan);
        tvLoginVersion = findViewById(R.id.tv_login_version);

        btnLoginSignin.setOnClickListener(this);
        ivLoginScan.setOnClickListener(this);

        setVersion(getAppVersionName(this));
        presenter.initData();
    }

    @Override
    protected void initData() {

        presenter = new LoginPresenter(this, this);
        getPermission();
        ServerManager serverManager = new ServerManager(this);
        serverManager.startServer();
        ServerApplication.setDaoSession(AppApplication.getDaoSession());
        if (getIntent().getBooleanExtra("show", false)) {
            showUpdateDialog();
        }
    }

    @Override
    public void onClick(View v) {
        if (ClickUtils.isFastClick()) {
            return;
        }
        switch (v.getId()) {
            case R.id.btn_login_signin:
                presenter.login();
                break;
            case R.id.iv_login_scan:
                showScanView(Constants.code_scan);
                break;
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
                .setPositiveButton(getString(R.string.confirm), new MyDialog.ConfirmListener() {
                    @Override
                    public void onClick() {
                        presenter.updateDB();
                        updateDialog.dismiss();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updateDialog.dismiss();
                    }
                })
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
//            presenter.checkIsFirst();
            //已经打开权限
//            Toast.makeText(this, "已经申请相关权限", Toast.LENGTH_SHORT).show();
        } else {
            //没有打开相关权限、申请权限
            EasyPermissions.requestPermissions(this, "需要获取您的存储、定位权限", 1, needPermissions);
        }

    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        LogUtils.e("onPermissionsGranted");
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        LogUtils.e("onPermissionsDenied");
        presenter.checkIsFirst();
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

    public void setVersion(String version) {
        tvLoginVersion.setText(getString(R.string.version_text) + version);
    }

    @Override
    public void showScanView() {
        Intent intent = new Intent(LoginActivity.this, CaptureActivity.class);
        startActivityForResult(intent, Constants.code_scan);
    }

    @Override
    public void initToken() {
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

    @Override
    public void checkVersion() {
        presenter.checkVersion(getVersionCode(this),getAppVersionName(this));
    }

    //校验token和获取用户信息
    private void verifyToken(final String token) {
        SSOContext.verifyToken(token, new VerifyResponseCallback() {
            @Override
            public void responseSuccess(String s) {
                VerifyToken vt = new Gson().fromJson(s, VerifyToken.class);
                if (vt != null && vt.getCode() == 200) {
                    Message msg = Message.obtain();
                    msg.obj = vt.getData();
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
                    VerifyToken.DataBean mLoginUserInfo = (VerifyToken.DataBean) msg.obj;
                    ToastUtils.showLong("token校验成功：" + mLoginUserInfo.getLoginUserName());
                    //用户姓名 mLoginUserInfo.getLoginUserName()
                    //用户登录名 mLoginUserInfo.getLoginId()
                    //查询数据库
                    presenter.autoLogin(mLoginUserInfo.getLoginId());
                    break;

                case 1:
                    ToastUtils.showLong("验证失败");
                    finish();
                    break;

                case 2:
                    ToastUtils.showLong((String) msg.obj);
                    finish();
                    break;
            }
        }
    };

}
