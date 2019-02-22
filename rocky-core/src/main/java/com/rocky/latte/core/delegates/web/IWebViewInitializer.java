package com.rocky.latte.core.delegates.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/2/22
 */

public interface IWebViewInitializer {
    WebView initWebView(WebView webView);
    WebViewClient initWebViewClient();
    WebChromeClient initWebChromeClient();
}
