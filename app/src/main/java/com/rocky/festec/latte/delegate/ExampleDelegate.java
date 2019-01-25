package com.rocky.festec.latte.delegate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.rocky.festec.latte.R;
import com.rocky.latte.core.app.Latte;
import com.rocky.latte.core.delegates.LatteDelegate;
import com.rocky.latte.core.net.RestClient;
import com.rocky.latte.core.net.callback.ResponseCallback;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/16
 */

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        test();
    }

    private void test() {
        RestClient.builder()
                .url("/api/index")
                .loader(getContext())
                .callback(new ResponseCallback() {
                    @Override
                    public void onSuccess(String message) {
                        Log.i("xx", "onSuccess:"+message);
//                        Toast.makeText(Latte.getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed() {
                        Log.i("xx", "onFailed");
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.i("xx", "onError" + msg);
                    }
                }).build().get();
    }
}
