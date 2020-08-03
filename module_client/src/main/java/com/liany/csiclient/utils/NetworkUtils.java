package com.liany.csiclient.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static com.lzy.okgo.utils.HttpUtils.runOnUiThread;

/**
 * @创建者 ly
 * @创建时间 2020/7/23
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class NetworkUtils {

    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                //这种方法也可以
                //return mNetworkInfo .getState()== NetworkInfo.State.CONNECTED
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 在子线程里开启该方法，可检测当前网络是否能打开网页
     * true是可以上网，false是不能上网
     */
    public static boolean isOnline() {
        URL url;
        try {
            url = new URL("https://www.baidu.com");
            InputStream stream = url.openStream();
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void isOnLineNet(Context mContext, NetCallback callback) {
        if(isNetworkConnected(mContext)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(isOnline()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                callback.onNext();
                            }
                        });
                    }else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                callback.onError();
                            }
                        });
                    }
                }
            }).start();
        }else {
            callback.onError();
        }
    }

    public interface NetCallback{
        void onNext();
        void onError();
    }
}
