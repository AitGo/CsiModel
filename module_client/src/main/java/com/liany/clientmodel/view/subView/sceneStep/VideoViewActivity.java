package com.liany.clientmodel.view.subView.sceneStep;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liany.clientmodel.R;
import com.liany.clientmodel.base.BaseAcitivity;
import com.liany.clientmodel.base.Constants;
import com.liany.clientmodel.utils.ClickUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @创建者 ly
 * @创建时间 2020/4/24
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class VideoViewActivity extends BaseAcitivity implements View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    WebView mWebView;

    private String url = "/extractionMethod.html";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step_video;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        mWebView = findViewById(R.id.webView);

        ivTitleBack.setOnClickListener(this);

        tvTitle.setText("视频攻略");
        initWebViewSetting();
    }

    private void initWebViewSetting() {
        WebSettings webSettings = mWebView.getSettings();
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //去掉滚动条
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);
        //不显示webview缩放按钮
        webSettings.setDisplayZoomControls(false);

        // 通过addJavascriptInterface()将Java对象映射到JS对象
        //参数1：Javascript对象名
        //参数2：Java对象名
        try {
            if (Build.VERSION.SDK_INT >= 16) {
                Class<?> clazz = mWebView.getSettings().getClass();
                Method method = clazz.getMethod(
                        "setAllowUniversalAccessFromFileURLs", boolean.class);//利用反射机制去修改设置对象
                if (method != null) {
                    method.invoke(mWebView.getSettings(), true);//修改设置
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 先载入JS代码
        mWebView.loadUrl(Constants.ipAddress + url);

        // 由于设置了弹窗检验调用结果,所以需要支持js对话框
        // webview只是载体，内容的渲染需要使用webviewChromClient类去实现
        // 通过设置WebChromeClient对象处理JavaScript的对话框
        //设置响应js 的Alert()函数
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {

//                dialog = builder.setTitle(getString(R.string.prompt))
//                        .setText(message)
//                        .setPositiveButton(getString(R.string.confirm), new MyDialog.ConfirmListener() {
//                            @Override
//                            public void onClick() {
//                                result.confirm();
//                                dialog.dismiss();
//                            }
//                        })
//                        .create();
//                dialog.show();
//                return true;
                return super.onJsAlert(view,url,message,result);
            }
        });

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                try{
                    if(url.startsWith("baiduboxapp://")){
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    }
                }catch (Exception e){
                    return false;
                }

                view.loadUrl(url);
                return true;
            }
        });

        // 点击后退按钮,让WebView后退一页
        mWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) { // 表示按返回键
                        // 时的操作
                        mWebView.goBack(); // 后退
                        // webview.goForward();//前进
                        return true; // 已处理
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

    @Override
    public void onClick(View view) {
        if(ClickUtils.isFastClick()) {
            return;
        }
        if (view.getId() == R.id.iv_title_back) {
            finish();
        }
    }
}
