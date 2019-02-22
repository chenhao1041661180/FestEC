package com.rocky.latte.core.delegates.web.client;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.rocky.latte.core.delegates.web.Router;
import com.rocky.latte.core.delegates.web.WebDelegate;
import com.rocky.latte.core.util.LatteLogger;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/2/22
 */

public class WebViewClientImpl extends WebViewClient {
    private final WebDelegate delegate;

    public WebViewClientImpl(WebDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return super.shouldOverrideUrlLoading(view, request);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LatteLogger.d("shouldOverrideUrlLoading",url);
        return Router.getInstance().handleWebUrl(delegate,url);
    }
}
