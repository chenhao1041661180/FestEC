package com.rocky.latte.core.delegates.web;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.rocky.latte.core.delegates.LatteDelegate;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/2/22
 */

public class Router {
    public Router() {

    }

    private static class Holder {
        private static final Router INSTANCE = new Router();
    }

    public static Router getInstance() {
        return Holder.INSTANCE;
    }

    public final boolean handleWebUrl(WebDelegate delegate, String url) {
        if (url.contains("tel:")) {
            callPhone(delegate.getContext(), url);
            return true;
        }
        final LatteDelegate topDelegate = delegate.getTopDelegate();

//        final LatteDelegate parentDelegate = delegate.getParentDelegate();
        final WebDelegateImpl webDelegate = WebDelegateImpl.create(url);
        topDelegate.start(webDelegate);
//        if (parentDelegate == null) delegate.start(webDelegate);
//        else parentDelegate.start(webDelegate);

        return true;
    }

    private void loadWebPage(WebView webView, String url) {
        if (webView != null)
            webView.loadUrl(url);
        else throw new NullPointerException("webview is null!");
    }

    private void loadLocalPage(WebView webView, String url) {
        loadWebPage(webView, "file:///android_asset/" + url);
    }

    private void loadPage(WebView webView, String url) {
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)) {
            loadWebPage(webView, url);
        } else {
            loadLocalPage(webView, url);
        }
    }

    public void loadPage(WebDelegate delegate, String url) {
        loadPage(delegate.getWebView(), url);
    }

    /**
     * 拨打电话
     *
     * @param context
     * @param uri
     */
    private void callPhone(Context context, String uri) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
        ContextCompat.startActivity(context, intent, null);
    }
}
