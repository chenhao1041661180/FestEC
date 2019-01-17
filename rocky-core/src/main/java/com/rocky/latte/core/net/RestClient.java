package com.rocky.latte.core.net;

import com.rocky.latte.core.net.callback.IRequest;
import com.rocky.latte.core.net.callback.RequestCallback;
import com.rocky.latte.core.net.callback.ResponseCallback;

import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/16
 */

public class RestClient {
    private final String URL;
    private final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final ResponseCallback CALLBACK;
    private final IRequest IREQUEST;
    private final RequestBody BODY;

    public RestClient(String mUrl, WeakHashMap<String, Object> params, IRequest IRequest, ResponseCallback mCallback, RequestBody mBody) {
        this.URL = mUrl;
        PARAMS.putAll(params);
        this.CALLBACK = mCallback;
        this.IREQUEST = IRequest;
        this.BODY = mBody;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    public final void request(HttpMethod method) {
        RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (IREQUEST != null)
            IREQUEST.onRequestStart();
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            default:
                break;
        }

        if (call != null)
            call.enqueue(getRequestCallback());

    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallback(CALLBACK, IREQUEST);
    }
}
