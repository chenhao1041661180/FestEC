package com.rocky.latte.core.net.callback;

import android.content.Context;
import android.os.Handler;

import com.rocky.latte.core.ui.LatteLoader;
import com.rocky.latte.core.ui.LoaderCreator;
import com.rocky.latte.core.ui.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/17
 */

public class RequestCallback implements Callback<String> {
    private ResponseCallback CALLBACK;
    private IRequest IREQUEST;
    private Context context;
    private LoaderStyle LOADER_STYLE;
    private static final Handler mHandler = new Handler();

    public RequestCallback(Context CONTEXT, ResponseCallback CALLBACK, IRequest IREQUEST, LoaderStyle LOADER_STYLE) {
        this.CALLBACK = CALLBACK;
        this.IREQUEST = IREQUEST;
        this.context = CONTEXT;
        this.LOADER_STYLE = LOADER_STYLE;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (IREQUEST != null)
            IREQUEST.onRequestFinish();
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (CALLBACK != null)
                    CALLBACK.onSuccess(response.body());
            }
        } else {
            if (CALLBACK != null)
                CALLBACK.onError(response.code(), response.message());
        }
        stopDialog();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (CALLBACK != null)
            CALLBACK.onFailed();

        if (IREQUEST != null)
            IREQUEST.onRequestFinish();

        stopDialog();
    }
    private void stopDialog() {
        if (LOADER_STYLE != null) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            },1000);

        }
    }
}
