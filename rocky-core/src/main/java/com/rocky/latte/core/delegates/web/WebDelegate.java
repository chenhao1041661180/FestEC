package com.rocky.latte.core.delegates.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.rocky.latte.core.app.Latte;
import com.rocky.latte.core.delegates.LatteDelegate;
import com.rocky.latte.core.delegates.web.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/2/22
 */

public abstract class WebDelegate extends LatteDelegate {
    private String mUrl = null;
    private WebView mWebView = null;
    private final ReferenceQueue<WebView> WEB_VIEWE_QUEUE = new ReferenceQueue<>();
    private boolean mIsWebViewAvailable = false;
    private LatteDelegate mTopDelegate = null;

    public WebDelegate() {

    }

    public abstract IWebViewInitializer setInitializer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle bundle = getArguments();
        mUrl = bundle.getString(RouteKeys.URL.name());
        initWebView();
    }

    private void initWebView() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            final IWebViewInitializer initializer = setInitializer();
            if (initializer != null) {
                final WeakReference<WebView> webViewWeakReference = new WeakReference<>(new WebView(getContext()), WEB_VIEWE_QUEUE);
                mWebView = webViewWeakReference.get();
                mWebView = initializer.initWebView(mWebView);
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());
                mWebView.addJavascriptInterface(WebJsInterface.create(this), "latte");
                mIsWebViewAvailable = true;
            } else {
                throw new NullPointerException("IWebViewInitializer is  null!");
            }
        }
    }

    public void setTopDelegate(LatteDelegate delegate) {
        mTopDelegate = delegate;
    }

    public LatteDelegate getTopDelegate() {
        if (mTopDelegate == null)
            mTopDelegate = this;
        return mTopDelegate;
    }

    public WebView getWebView() {
        if (mWebView == null)
            throw new NullPointerException("webview is null!");
        else
            return mIsWebViewAvailable ? mWebView : null;
    }

    public String getUrl() {
        if (mUrl == null)
            throw new NullPointerException("url is null!");
        else
            return mUrl;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) mWebView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) mWebView.onResume();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebViewAvailable = false;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }
}
