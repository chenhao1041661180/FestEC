package com.rocky.latte.ec.main.personal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.rocky.latte.core.ui.home.BaseHomeDelegate;
import com.rocky.latte.ec.R;

/**
 * Created by 傅令杰
 */

public class PersonalDelegate extends BaseHomeDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
//        final WebDelegateImpl delegate = WebDelegateImpl.create("index.html");
//        delegate.setTopDelegate(this.getParentDelegate());
//        getSupportDelegate().loadRootFragment(R.id.web_discovery_container, delegate);
    }

//    @Override
//    public FragmentAnimator onCreateFragmentAnimator() {
//        return new DefaultHorizontalAnimator();
//    }
}
