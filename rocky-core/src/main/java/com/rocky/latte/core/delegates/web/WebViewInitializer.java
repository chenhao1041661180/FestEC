package com.rocky.latte.core.delegates.web;

import android.os.Build;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/2/22
 */

public class WebViewInitializer {
    public WebView createWebView(WebView webView) {
        WebView.setWebContentsDebuggingEnabled(true);
        //不能横向滚动
        webView.setHorizontalScrollBarEnabled(false);
        //不能纵向滚动
        webView.setVerticalScrollBarEnabled(false);
        //允许截图
        webView.setDrawingCacheEnabled(true);
        //屏蔽长按事件
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        //初始化websetting
        final WebSettings webSettings = webView.getSettings();
        String userAgentString = webSettings.getUserAgentString();
        webSettings.setUserAgentString(userAgentString + "Latte ");

        //允许js代码
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        //缩放控件false
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDisplayZoomControls(false);
        //禁止缩放
        webSettings.setSupportZoom(false);
        //禁用文字缩放
        webSettings.setTextZoom(100);
        //文件权限 允许WebView使用File协议
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setAllowContentAccess(true);
        //缓存相关
        webSettings.setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setAppCachePath(webView.getContext().getDir("appcache", 0).getPath());

        //10M缓存，api 18后，系统自动管理
        webSettings.setAppCacheMaxSize(10 * 1024 * 1024);
        //自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
        /**
         * 加载RiseClub页面时，资源是http的，需要允许https与http同时加载，所以放开权限。
         * 但是要加上白名单功能：手动过滤
         */
        // 5.0以上开启混合模式加载
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        return webView;
    }
}
