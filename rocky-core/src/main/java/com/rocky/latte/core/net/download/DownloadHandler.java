package com.rocky.latte.core.net.download;

import android.os.AsyncTask;

import com.rocky.latte.core.net.RestCreator;
import com.rocky.latte.core.net.callback.IRequest;
import com.rocky.latte.core.net.callback.ResponseCallback;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 模块说明：下载文件
 *
 * @author 陈浩
 * @date 2019/1/18
 */

public class DownloadHandler {

    private final String URL;
    private final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final ResponseCallback CALLBACK;
    private final IRequest IREQUEST;
    //下载
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    public DownloadHandler(String URL, WeakHashMap<String, Object> PARAMS, ResponseCallback CALLBACK, IRequest IREQUEST, String DOWNLOAD_DIR, String EXTENSION, String NAME) {
        this.URL = URL;
        this.PARAMS.putAll(PARAMS);
        this.CALLBACK = CALLBACK;
        this.IREQUEST = IREQUEST;
        this.DOWNLOAD_DIR = DOWNLOAD_DIR;
        this.EXTENSION = EXTENSION;
        this.NAME = NAME;
    }

    public void handleDownload() {
        if (IREQUEST != null)
            IREQUEST.onRequestStart();
        RestCreator.getRestService().download(URL, PARAMS).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    final ResponseBody responseBody = response.body();
                    SaveFileTask saveFileTask = new SaveFileTask(CALLBACK, IREQUEST);
                    saveFileTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, responseBody, DOWNLOAD_DIR, EXTENSION, NAME);

                    //这里需要判断,否则文件下载不全
                    if (saveFileTask.isCancelled()){
                        if (IREQUEST!=null){
                            IREQUEST.onRequestFinish();
                        }
                    }
                }else {
                    if (CALLBACK!=null)
                        CALLBACK.onError(response.code(),response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (CALLBACK!=null)
                    CALLBACK.onFailed();
            }
        });

    }
}
