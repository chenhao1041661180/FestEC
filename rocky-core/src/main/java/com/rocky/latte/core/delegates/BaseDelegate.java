package com.rocky.latte.core.delegates;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rocky.latte.core.activities.ProxyActivity;
import com.rocky.latte.core.app.Latte;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/16
 */

public abstract class BaseDelegate extends SwipeBackFragment {
    private Unbinder mUnbinder = null;
    private View mRootView = null;

    public abstract Object setLayout();

    public abstract void onBindView(@Nullable Bundle savedInstanceState, View rootView);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public <T extends View> T $(@IdRes int viewId) {
        if (mRootView != null) {
            return mRootView.findViewById(viewId);
        }
        throw new NullPointerException("rootView is null");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = null;
        Object layout = setLayout();
        if (layout instanceof Integer)
            rootView = inflater.inflate((Integer) layout, container, false);
        else if (layout instanceof View)
            rootView = (View) layout;

        if (rootView != null) {
            mUnbinder = ButterKnife.bind(this, rootView);
            mRootView = rootView;
            onBindView(savedInstanceState, rootView);
        }

        return rootView;
    }

    //    @Override
//    public ProxyActivity getActivity() {
//        return (ProxyActivity) _mActivity;
//    }
    public void showToast(CharSequence s) {
        Toast.makeText(Latte.getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) mUnbinder.unbind();
    }
}
