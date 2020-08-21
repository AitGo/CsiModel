package com.liany.clientmodel.view;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;

import com.liany.clientmodel.R;
import com.liany.clientmodel.base.BaseAcitivity;
import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.contract.SplashContract;
import com.liany.clientmodel.presenter.SplashPresenter;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

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
public class SplashActivity extends BaseAcitivity implements SplashContract.View, EasyPermissions.PermissionCallbacks {

    private final long SPLASH_LENGTH = 2000;
    Handler handler = new Handler();
    private SplashContract.Presenter presenter;

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
//        if(Constants.isUseLocalServer) {
//            Intent intent = new Intent(this, AndService.class);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                startForegroundService(intent);
//            } else {
//                startService(intent);
//            }
//        }
        presenter = new SplashPresenter(this,this);
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
        Intent intent = new Intent(SplashActivity.this, CaptureActivity.class);
        startActivityForResult(intent, Constants.code_scan);
    }

    @Override
    public void showLoginView(final boolean isShowUpdate) {
        handler.postDelayed(new Runnable() {

            public void run() {
                Intent intent = new Intent(SplashActivity.this, Model_LoginActivity.class);
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
