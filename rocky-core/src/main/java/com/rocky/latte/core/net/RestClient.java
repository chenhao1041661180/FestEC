package com.rocky.latte.core.net;

import android.content.Context;

import com.rocky.latte.core.net.callback.IRequest;
import com.rocky.latte.core.net.callback.RequestCallback;
import com.rocky.latte.core.net.callback.ResponseCallback;
import com.rocky.latte.core.net.download.DownloadHandler;
import com.rocky.latte.core.ui.LatteLoader;
import com.rocky.latte.core.ui.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
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

    private final Context CONTEXT;
    private final LoaderStyle LOADER_STYLE;
    private final RequestBody BODY;
    private final File FILE;
    //下载
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    public RestClient(Context context, String mUrl, WeakHashMap<String, Object> params, IRequest IRequest, ResponseCallback mCallback, RequestBody mBody, LoaderStyle loaderStyle, File file, String downloadDir,
                      String extension,
                      String name) {
        this.CONTEXT = context;
        this.URL = mUrl;
        PARAMS.putAll(params);
        this.CALLBACK = mCallback;
        this.IREQUEST = IRequest;
        this.BODY = mBody;
        this.FILE = file;
        this.LOADER_STYLE = loaderStyle;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    public final void request(HttpMethod method) {
        RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (IREQUEST != null)
            IREQUEST.onRequestStart();

        if (LOADER_STYLE != null)
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = service.upload(URL, body);
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
        if (BODY == null)
            request(HttpMethod.POST);
        else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);

        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void download() {
        new DownloadHandler(URL, PARAMS, CALLBACK, IREQUEST, DOWNLOAD_DIR, EXTENSION, NAME).handleDownload();
    }

    public final void put() {
        if (BODY == null)
            request(HttpMethod.PUT);
        else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);

        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallback(CONTEXT, CALLBACK, IREQUEST, LOADER_STYLE);
    }
}
