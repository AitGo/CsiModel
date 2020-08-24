package com.liany.csiclient;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;

import com.liany.clientmodel.base.BaseAcitivity;
import com.liany.clientmodel.base.Constants;
import com.liany.csiclient.base.Zz_Application;
import com.liany.csiclient.contract.Zz_SplashContract;
import com.liany.csiclient.presenter.Zz_SplashPresenter;
import com.liany.csiserverapp.andServer.manager.ServerManager;
import com.liany.csiserverapp.debug.ServerApplication;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

import java.util.List;

import androidx.annotation.NonNull;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @创建者 ly
 * @创建时间 2020/8/24
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class Zz_SplashActivity extends BaseAcitivity  implements Zz_SplashContract.View, EasyPermissions.PermissionCallbacks {

    private final long SPLASH_LENGTH = 2000;
    Handler handler = new Handler();
    private Zz_SplashContract.Presenter presenter;

    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.CAMERA
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        showLoginView(false);
    }

    @Override
    protected void initData() {
        getPermission();
        ServerManager serverManager = new ServerManager(this);
        serverManager.startServer();
        ServerApplication.setDaoSession(Zz_Application.getDaoSession());
        presenter = new Zz_SplashPresenter(this,this);
//        presenter.checkIsFirst();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenter.onRequestPermissionsResult(requestCode,  permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void showScanView() {
        Intent intent = new Intent(Zz_SplashActivity.this, CaptureActivity.class);
        startActivityForResult(intent, Constants.code_scan);
    }

    @Override
    public void showLoginView(final boolean isShowUpdate) {
        handler.postDelayed(new Runnable() {

            public void run() {
                Intent intent = new Intent(Zz_SplashActivity.this, Zz_LoginActivity.class);
                intent.putExtra("show",isShowUpdate);
                startActivity(intent);
                finish();
            }
        }, SPLASH_LENGTH);
    }

    @Override
    public void closeActivity() {
        finish();
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
}
