package com.rocky.festec.latte.delegate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.rocky.festec.latte.R;
import com.rocky.latte.core.delegates.LatteDelegate;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/16
 */

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
