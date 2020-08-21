package com.liany.csi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import pub.devrel.easypermissions.EasyPermissions;

import android.Manifest;
import android.os.Bundle;
import android.os.Handler;

import com.alibaba.android.arouter.launcher.ARouter;
import com.liany.csi.base.AppApplication;
import com.liany.csiserverapp.andServer.manager.ServerManager;
import com.liany.csiserverapp.debug.ServerApplication;

import java.util.List;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    private final long SPLASH_LENGTH = 2000;
    Handler handler = new Handler();

    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.CAMERA
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_main);
        getPermission();
        ServerManager serverManager = new ServerManager(this);
        serverManager.startServer();
        ServerApplication.setDaoSession(AppApplication.getDaoSession());
        handler.postDelayed(new Runnable() {
            public void run() {
                ARouter.getInstance().build("/csi/login").navigation();
                finish();
            }
        }, SPLASH_LENGTH);
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
}
