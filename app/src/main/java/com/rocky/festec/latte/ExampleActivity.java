package com.rocky.festec.latte;


import com.rocky.festec.latte.delegate.ExampleDelegate;
import com.rocky.latte.core.activities.ProxyActivity;
import com.rocky.latte.core.delegates.LatteDelegate;


public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
