package com.rocky.latte.ec.main.discover;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.rocky.latte.core.ui.home.BaseHomeDelegate;
import com.rocky.latte.ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by 傅令杰
 */

public class DiscoverDelegate extends BaseHomeDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {


    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        Log.i("xx","发现:"+isSupportVisible());
//        final WebDelegateImpl delegate = WebDelegateImpl.create("index.html");
//        delegate.setTopDelegate(this.getParentDelegate());
//        getSupportDelegate().loadRootFragment(R.id.web_discovery_container, delegate);
    }

//    @Override
//    public FragmentAnimator onCreateFragmentAnimator() {
//        return new DefaultHorizontalAnimator();
//    }
}
