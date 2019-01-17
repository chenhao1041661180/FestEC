package com.rocky.latte.core.net.callback;

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

    public RequestCallback(ResponseCallback CALLBACK, IRequest IREQUEST) {
        this.CALLBACK = CALLBACK;
        this.IREQUEST = IREQUEST;
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
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (CALLBACK != null)
            CALLBACK.onFailed();

        if (IREQUEST != null)
            IREQUEST.onRequestFinish();
    }
}
