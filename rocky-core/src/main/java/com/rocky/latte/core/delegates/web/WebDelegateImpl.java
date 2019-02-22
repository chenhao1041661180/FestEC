package com.rocky.latte.core.delegates.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.rocky.latte.core.delegates.web.client.WebChromeClientImpl;
import com.rocky.latte.core.delegates.web.client.WebViewClientImpl;
import com.rocky.latte.core.delegates.web.route.RouteKeys;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/2/22
 */

public class WebDelegateImpl extends WebDelegate implements IWebViewInitializer {

    public static WebDelegateImpl create(String url) {
        Bundle bundle = new Bundle();
        bundle.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(bundle);
        return delegate;
    }


    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        if (getUrl() != null) {
            //用原生的方式模拟跳转并加载页面
            Router.getInstance().loadPage(this, getUrl());
        }
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }
}
