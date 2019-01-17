package com.rocky.latte.core.net.callback;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/17
 */

public interface ResponseCallback {

    void onSuccess(String message);

    void onFailed();

    void onError(int code, String msg);

}
