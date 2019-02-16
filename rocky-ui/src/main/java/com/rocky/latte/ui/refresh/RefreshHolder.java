package com.rocky.latte.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rocky.latte.core.app.Latte;
import com.rocky.latte.core.net.RestClient;
import com.rocky.latte.core.net.callback.ResponseCallback;
import com.rocky.latte.core.refresh.PageBean;
import com.rocky.latte.ui.recycler.DataConverter;
import com.rocky.latte.ui.recycler.MultipleRecyclerviewAdapter;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/28
 */

public class RefreshHolder implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener {
    private SwipeRefreshLayout REFRESH_LAYOUT;
    private PageBean BEAN;
    private RecyclerView RECYCLERVIEW;
    private DataConverter CONVERTER;
    private MultipleRecyclerviewAdapter mAdapter = null;


    private RefreshHolder(SwipeRefreshLayout swipeRefreshLayout, RecyclerView recyclerView, DataConverter converter, PageBean bean) {
        this.REFRESH_LAYOUT = swipeRefreshLayout;
        this.RECYCLERVIEW = recyclerView;
        this.CONVERTER = converter;
        this.BEAN = bean;
        REFRESH_LAYOUT.setProgressViewOffset(true, 100, 300);
        REFRESH_LAYOUT.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        REFRESH_LAYOUT.setOnRefreshListener(this);

    }

    public static RefreshHolder create(SwipeRefreshLayout swipeRefreshLayout, RecyclerView recyclerView, DataConverter converter) {
        return new RefreshHolder(swipeRefreshLayout, recyclerView, converter, new PageBean());
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

    public void firstPage() {
        RestClient.builder()
                .url("mock/data/index_data.json")
                .callback(new ResponseCallback() {
                    @Override
                    public void onSuccess(String message) {
                        Log.i("xx", message);
                        JSONObject jsonObject = JSON.parseObject(message);
                        BEAN.setTotal(jsonObject.getInteger("total"))
                                .setPageSize(jsonObject.getInteger("page_size"));
                        //设置Adapter
                        mAdapter = MultipleRecyclerviewAdapter.create(CONVERTER.setJsonData(message));
                        mAdapter.setOnLoadMoreListener(RefreshHolder.this, RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(mAdapter);
                        BEAN.addIndex();
                    }

                    @Override
                    public void onFailed() {
                        Toast.makeText(Latte.getApplicationContext(), "onFailed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(Latte.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                })
                .build().get();
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
