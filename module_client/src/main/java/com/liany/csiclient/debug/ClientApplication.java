package com.liany.csiclient.debug;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.widget.Toast;

//import com.kc.criminaiinvest.bean.ExecuteDraw;
import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.kc.criminaiinvest.bean.ExecuteDraw;
import com.liany.csiclient.base.Constants;
import com.liany.csiclient.utils.CrashHandler;
import com.liany.csiclient.utils.LogUtils;
import com.liany.model.common.base.BaseApplication;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import org.opencv.android.OpenCVLoader;

import java.util.Locale;
import java.util.logging.Level;

import androidx.multidex.MultiDex;
import okhttp3.OkHttpClient;

public class ClientApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //获取context
//        initBugly();
        initYunhen();
        initZxing();
        initOkgo();
        initAccessTokenWithAkSk();
        initExecuteDraw();
//        initCrash();
    }

    private void initExecuteDraw() {
//        ExecuteDraw.init(getApplication(),"Ha3Dius9dcNd8SVSA1nw");
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

    private void initBugly() {
        // 补丁回调接口
        Beta.betaPatchListener = new BetaPatchListener() {
            @Override
            public void onPatchReceived(String patchFile) {
                Toast.makeText(getContext(), "补丁下载地址" + patchFile, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadReceived(long savedLength, long totalLength) {
                Toast.makeText(getContext(),
                        String.format(Locale.getDefault(), "%s %d%%",
                                Beta.strNotificationDownloading,
                                (int) (totalLength == 0 ? 0 : savedLength * 100 / totalLength)),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadSuccess(String msg) {
                Toast.makeText(getContext(), "补丁下载成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadFailure(String msg) {
                Toast.makeText(getContext(), "补丁下载失败", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onApplySuccess(String msg) {
                Toast.makeText(getContext(), "补丁应用成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onApplyFailure(String msg) {
                Toast.makeText(getContext(), "补丁应用失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPatchRollback() {

            }
        };
        //测试设备
//        Bugly.setIsDevelopmentDevice(this, true);
//        CrashReport.initCrashReport(getApplicationContext(), "ebd5c1d883", Constants.Bugly_isDebug);
        Bugly.init(this, "ebd5c1d883", Constants.Bugly_isDebug);
    }

    /**
     * 用明文ak，sk初始化
     */
    private void initAccessTokenWithAkSk() {
        OCR.getInstance(getContext()).initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken result) {
                String token = result.getAccessToken();
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
                LogUtils.e("AK，SK方式获取token失败：" +  error.getMessage());
            }
        }, getContext(),  "PfnorjLM6o9yjAtgQfU4roIb", "g0TaOMC6xs7ErrUGh3qSWSydsVrX5Fnl");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Beta.unInit();
    }

    private void initCrash() {
        //捕获错误日志
        new Thread(){
            @Override
            public void run() {
                //把异常处理的handler设置到主线程里面
                CrashHandler ch = CrashHandler.getInstance();
                ch.init(getContext());
            }
        }.start();

    }
}
