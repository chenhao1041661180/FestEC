package com.rocky.latte.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.rocky.latte.core.ui.home.BaseHomeDelegate;
import com.rocky.latte.ec.R;
import com.rocky.latte.ec.main.sort.content.SortContentDelegate;
import com.rocky.latte.ec.main.sort.list.SortListDelegate;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/28
 */

public class SortDelegate extends BaseHomeDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        SortListDelegate sortListDelegate = new SortListDelegate();
        getSupportDelegate().loadRootFragment(R.id.fram_sort_list,sortListDelegate);
        getSupportDelegate().loadRootFragment(R.id.fram_sort_content, SortContentDelegate.newInstance(1));

    }
}
