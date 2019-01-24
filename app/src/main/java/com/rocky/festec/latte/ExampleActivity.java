package com.rocky.festec.latte;


import com.rocky.latte.core.activities.ProxyActivity;
import com.rocky.latte.core.delegates.LatteDelegate;
import com.rocky.latte.ec.ui.launcher.LauncherSplishDelegate;


public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherSplishDelegate();
    }
}
