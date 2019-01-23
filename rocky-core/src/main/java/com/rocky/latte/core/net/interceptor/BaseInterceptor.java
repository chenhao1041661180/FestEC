package com.rocky.latte.core.net.interceptor;

import java.io.IOException;
import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/23
 */

public abstract class BaseInterceptor implements Interceptor {

    protected LinkedHashMap<String, String> getUrlParamters(Chain chain) {
        final HttpUrl httpUrl = chain.request().url();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        int size = httpUrl.querySize();
        for (int i = 0; i < size; i++) {
            params.put(httpUrl.queryParameterName(i), httpUrl.queryParameterValue(i));
        }
        return params;
    }

    protected String getUrlParamter(Chain chain, String key) {
        final HttpUrl httpUrl = chain.request().url();
        return httpUrl.queryParameter(key);
    }

    protected LinkedHashMap<String, String> getBodyParamters(Chain chain) {
        FormBody body = (FormBody) chain.request().body();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        int size = body.size();
        for (int i = 0; i < size; i++) {
            params.put(body.name(i), body.value(i));
        }
        return params;
    }

    protected String getBodyParamter(Chain chain, String key) {
        return getBodyParamters(chain).get(key);
    }

}
