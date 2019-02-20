package com.rocky.latte.ec.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.rocky.latte.core.delegates.LatteDelegate;
import com.rocky.latte.ec.R;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/2/20
 */

public class SortContentDelegate extends LatteDelegate {
    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int mContentId = -1;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sort_content;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mContentId = args.getInt(ARG_CONTENT_ID);
        }
    }

    public static SortContentDelegate newInstance(int contentId) {
        final Bundle bundle = new Bundle();
        bundle.putInt(ARG_CONTENT_ID, contentId);
        SortContentDelegate sortContentDelegate = new SortContentDelegate();
        sortContentDelegate.setArguments(bundle);
        return sortContentDelegate;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
