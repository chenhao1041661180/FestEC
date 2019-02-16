package com.rocky.latte.ec.main.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.rocky.latte.core.ui.home.BaseHomeDelegate;
import com.rocky.latte.ec.R;
import com.rocky.latte.ec.R2;
import com.rocky.latte.ui.recycler.BaseDecoration;
import com.rocky.latte.ui.refresh.RefreshHolder;

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
        mRefreshHolder = RefreshHolder.create(mRefreshLayout, mRecyclerView, new HomeDataConverter());
        mRefreshHolder.firstPage();
        Log.i("xx", "HOME-onLazyInitView:" + isSupportVisible());

    }

    @SuppressLint("ResourceType")
    private void initRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(getContext(),R.color.app_background),2));
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        Log.i("xx", "HOME-onBindView:" + isSupportVisible());
        initRecyclerView();

    }
}
