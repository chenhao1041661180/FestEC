package com.rocky.latte.ec.main.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.rocky.latte.core.app.Latte;
import com.rocky.latte.core.net.RestClient;
import com.rocky.latte.core.net.callback.ResponseCallback;
import com.rocky.latte.core.ui.home.BaseHomeDelegate;
import com.rocky.latte.core.ui.recycler.DataConverter;
import com.rocky.latte.core.ui.recycler.MultipleFields;
import com.rocky.latte.core.ui.recycler.MultipleItemEntity;
import com.rocky.latte.core.ui.refresh.RefreshHolder;
import com.rocky.latte.ec.R;
import com.rocky.latte.ec.R2;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/28
 */

public class HomeDelegate extends BaseHomeDelegate {
    @BindView(R2.id.rv_index)
    public RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    public SwipeRefreshLayout mRefreshLayout = null;
    private RefreshHolder mRefreshHolder = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_home;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mRefreshHolder = new RefreshHolder(mRefreshLayout);
//        mRefreshHolder.firstPage();
        Log.i("xx","HOME-onLazyInitView:"+isSupportVisible());

        RestClient.builder()
                .url("mock/data/index_data.json")
                .callback(new ResponseCallback() {
                    @Override
                    public void onSuccess(String message) {
                        Log.i("xx",message);
                        HomeDataConverter dataConverter = new HomeDataConverter();
                        dataConverter.setJsonData(message);

                        ArrayList<MultipleItemEntity> list = dataConverter.convert();
                        Toast.makeText(Latte.getApplicationContext(), (String) list.get(1).getField(MultipleFields.IMAGE_URL),Toast.LENGTH_SHORT).show();
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

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        Log.i("xx","HOME-onBindView:"+isSupportVisible());

    }
}
