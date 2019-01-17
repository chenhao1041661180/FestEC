package com.rocky.latte.core.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.rocky.latte.core.R;
import com.rocky.latte.core.delegates.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/16
 */

public abstract class ProxyActivity extends SupportActivity {
    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        @SuppressLint("RestrictedApi")
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);

        if (savedInstanceState == null)
            loadRootFragment(R.id.delegate_container,setRootDelegate());
    }
}
