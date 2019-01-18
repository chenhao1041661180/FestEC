package com.rocky.latte.core.net;

import android.content.Context;

import com.rocky.latte.core.net.callback.IRequest;
import com.rocky.latte.core.net.callback.ResponseCallback;
import com.rocky.latte.core.ui.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/17
 */

public class RestClientBuilder {
    private String mUrl = null;
    private WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private ResponseCallback mCallback = null;
    private IRequest mIRequest = null;
    private RequestBody mBody = null;
    private Context context = null;
    private LoaderStyle loaderStyle = null;
    private File mFile;

    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;


    RestClientBuilder() {
    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        this.PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        this.PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder callback(ResponseCallback callback) {
        this.mCallback = callback;
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder loader(Context context, LoaderStyle loaderStyle) {
        this.context = context;
        this.loaderStyle = loaderStyle;
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String filePath) {
        this.mFile = new File(filePath);
        return this;
    }

    public final RestClientBuilder loader(Context context) {
        this.context = context;
        this.loaderStyle = LoaderStyle.BallGridPulseIndicator;
        return this;
    }

    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    public final RestClientBuilder dir(String dir) {
        this.mDownloadDir = dir;
        return this;
    }

    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    public final RestClient build() {
        return new RestClient(context, mUrl, PARAMS, mIRequest, mCallback, mBody, loaderStyle, mFile,mDownloadDir,mExtension,mName);
    }
}
