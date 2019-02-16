package com.rocky.latte.core.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.Toast;

import com.rocky.latte.core.app.Latte;
import com.rocky.latte.core.net.RestClient;
import com.rocky.latte.core.net.callback.ResponseCallback;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/28
 */

public class RefreshHolder implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout REFRESH_LAYOUT;

    public RefreshHolder(SwipeRefreshLayout REFRESH_LAYOUT) {
        REFRESH_LAYOUT.setProgressViewOffset(true, 100, 300);
        REFRESH_LAYOUT.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        REFRESH_LAYOUT.setOnRefreshListener(this);
        this.REFRESH_LAYOUT = REFRESH_LAYOUT;
    }

    private void refresh() {
        firstPage();
        REFRESH_LAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    public void firstPage(){
        RestClient.builder()
                .url("mock/data/index_data.json")
                .callback(new ResponseCallback() {
                    @Override
                    public void onSuccess(String message) {
                        Log.i("xx",message);

//                        Toast.makeText(Latte.getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed() {
                        Toast.makeText(Latte.getApplicationContext(),"onFailed",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(Latte.getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                    }
                })
                .build().get();
    }
}
