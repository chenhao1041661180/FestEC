package com.rocky.latte.core.net;

import com.rocky.latte.core.net.callback.IRequest;
import com.rocky.latte.core.net.callback.ResponseCallback;

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
    private String mUrl;
    private WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private ResponseCallback mCallback;
    private IRequest mIRequest;
    private RequestBody mBody;

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

    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mIRequest, mCallback, mBody);
    }
}
