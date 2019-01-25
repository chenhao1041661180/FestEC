package com.rocky.festec.latte;


import com.rocky.latte.core.activities.ProxyActivity;
import com.rocky.latte.core.delegates.LatteDelegate;
import com.rocky.latte.ec.ui.launcher.LauncherDelegate;
import com.rocky.latte.ec.ui.sign.SignUpDelegate;


public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }
}
