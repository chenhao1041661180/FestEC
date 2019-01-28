package com.rocky.latte.ec.main.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rocky.latte.core.ui.home.BaseHomeDelegate;
import com.rocky.latte.ec.R;
import com.rocky.latte.ec.R2;

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

    @Override
    public Object setLayout() {
        return R.layout.delegate_home;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {


    }
}
