package com.liany.csi.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.kc.criminaiinvest.bean.ExecuteDraw;
import com.liany.csiclient.utils.CrashHandler;
import com.liany.csiserverapp.base.GreenDaoContext;
import com.liany.csiserverapp.dao.database.greenDao.db.DaoMaster;
import com.liany.csiserverapp.dao.database.greenDao.db.DaoSession;
import com.liany.csiserverapp.db.DBHelper;
import com.liany.csiserverapp.network.NetWorkManager;
import com.liany.model.common.base.BaseApplication;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import org.opencv.android.OpenCVLoader;

import java.util.logging.Level;

import okhttp3.OkHttpClient;


public class AppApplication extends BaseApplication {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        //获取context
        mContext = getApplicationContext();
        ARouter.init(this);
        initYunhen();
        initZxing();
        initOkgo();
        initGreenDao();
        initRetrofit2();
        initToast();
        initCrash();
        ExecuteDraw.init(this);
    }

    private void initYunhen() {
        if (OpenCVLoader.initDebug()) {
            System.loadLibrary("opencv_java3");
        }
    }

    private void initOkgo() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);
//        builder.cookieJar(new CookieJarImpl(new DBCookieStore(this)));
        builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));

        OkGo.getInstance().init(this)                           //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setRetryCount(0);                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
    }

    private void initZxing() {
        ZXingLibrary.initDisplayOpinion(this);
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

    private static DaoSession daoSession;
    public static DaoSession getDaoSession() {
        return daoSession;
    }

    private void initRetrofit2() {
        NetWorkManager.getInstance().init();
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

    private void initCrash() {
        //捕获错误日志
        new Thread(){
            @Override
            public void run() {
                //把异常处理的handler设置到主线程里面
                CrashHandler ch = CrashHandler.getInstance();
                ch.init(mContext);
            }
        }.start();

    }
}
