package com.liany.csi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import pub.devrel.easypermissions.EasyPermissions;

import android.Manifest;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.liany.csi.base.AppApplication;
import com.liany.csiserverapp.andServer.manager.ServerManager;
import com.liany.csiserverapp.debug.ServerApplication;

import java.util.List;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

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
//        AndService andService = ((AndService) ARouter.getInstance().build("/service/andService").navigation());
//        Intent intent = new Intent(this, AndService.class);
//        startService(intent);
        ServerManager serverManager = new ServerManager(this);
        serverManager.startServer();
        ServerApplication.setDaoSession(AppApplication.getDaoSession());
        ARouter.getInstance().build("/csi/login").navigation();
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
