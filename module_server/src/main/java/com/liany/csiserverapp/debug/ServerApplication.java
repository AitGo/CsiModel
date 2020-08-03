package com.liany.csiserverapp.debug;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.widget.Toast;

import com.liany.csiserverapp.base.Constants;
import com.liany.csiserverapp.base.GreenDaoContext;
import com.liany.csiserverapp.dao.database.greenDao.db.DaoMaster;
import com.liany.csiserverapp.dao.database.greenDao.db.DaoSession;
import com.liany.csiserverapp.db.DBHelper;
import com.liany.csiserverapp.network.NetWorkManager;
import com.liany.csiserverapp.utils.SPUtils;
import com.liany.model.common.base.BaseApplication;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;

public class ServerApplication extends BaseApplication {

    private static Context mContext;
    private String spIsFirst = "isFirst";
    private String assetsDBFileName = "csi.db";

    @Override
    public void onCreate() {
        super.onCreate();
        //获取context
        mContext = getApplicationContext();
        initGreenDao();
        initRetrofit2();
        initToast();
        initBugly();
    }

    private void initBugly() {
        CrashReport.initCrashReport(getApplicationContext(), "4dd335bcd4", Constants.Bugly_isDebug);
        Bugly.init(getApplicationContext(), "4dd335bcd4", Constants.Bugly_isDebug);
    }

    private void initRetrofit2() {
        NetWorkManager.getInstance().init();
    }

    //创建一个静态的方法，以便获取context对象
    public static Context getContext(){
        return mContext;
    }

    public static void setContext(Context context) {
        mContext = context;
    }

    /**
     * 初始化GreenDao,直接在Application中进行初始化操作
     */
    private static void initGreenDao() {
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(new GreenDaoContext(mContext), "newcsi.db");
        DBHelper helper = new DBHelper(new GreenDaoContext(mContext),"csi.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public static DaoSession daoSession;
    public static DaoSession getDaoSession() {
        return daoSession;
    }
    public static void setDaoSession(DaoSession daoSession1) {
        daoSession = daoSession1;
    }

    public static void setIp(String ip) {
        Constants.ipAddress = ip;
    }

    private boolean checkFirstLogin() {
        return (boolean) SPUtils.getParam(this,spIsFirst,true);
    }

    private static final Handler sHandler = new Handler();
    private static Toast sToast; // 单例Toast,避免重复创建，显示时间过长

    public static void initToast() {
        sToast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
    }
    public static void toast(String txt, int duration) {
        sToast.setText(txt);
        sToast.setDuration(duration);
        sToast.show();
    }

    public static void runUi(Runnable runnable) {
        sHandler.post(runnable);
    }
}
