package com.liany.model.common.base;

import android.app.Application;
import android.content.Context;

/**
 * @创建者 ly
 * @创建时间 2020/7/2
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class BaseApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }

}
