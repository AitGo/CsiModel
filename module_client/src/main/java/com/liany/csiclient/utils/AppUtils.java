package com.liany.csiclient.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.liany.csiclient.debug.ClientApplication;

/**
 * @创建者 ly
 * @创建时间 2020/3/30
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class AppUtils {
    /**
     * 检查是否安装了第三方应用
     * @param packageName 包名
     * @return
     */
    public static boolean checkLibInstall(String packageName) {
        try {
            PackageInfo packageInfo = ClientApplication.getContext().getPackageManager().getPackageInfo(packageName, 0);
            if(packageInfo == null) {
                return false;
            }else {
                return true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
