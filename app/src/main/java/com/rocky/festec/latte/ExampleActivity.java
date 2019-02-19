package com.rocky.festec.latte;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.rocky.festec.latte.delegate.ExampleDelegate;
import com.rocky.latte.core.activities.ProxyActivity;
import com.rocky.latte.core.app.Latte;
import com.rocky.latte.core.delegates.LatteDelegate;
import com.rocky.latte.ec.main.EcBottomDelegate;
import com.rocky.latte.ec.ui.launcher.OnLauncherFinishTag;
import com.rocky.latte.ec.ui.launcher.ILauncherListener;
import com.rocky.latte.ec.ui.launcher.LauncherDelegate;
import com.rocky.latte.ec.ui.sign.ISignListener;
import com.rocky.latte.ec.ui.sign.SignInDelegate;

import qiu.niorgai.StatusBarCompat;


public class ExampleActivity extends ProxyActivity implements ILauncherListener, ISignListener {

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Latte.getConfigurator().withActivity(this);
        StatusBarCompat.translucentStatusBar(this, true);
    }

    @Override
    public void onSignInSuccess() {
//        Toast.makeText(Latte.getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
        startWithPop(new EcBottomDelegate());
    }

    @Override
    public void onSignUpSuccess() {
//        Toast.makeText(Latte.getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
        startWithPop(new EcBottomDelegate());
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:

//                Toast.makeText(Latte.getApplicationContext(), "启动结束，用户登录了", Toast.LENGTH_SHORT).show();
                startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
//                Toast.makeText(Latte.getApplicationContext(), "启动结束，用户没登录", Toast.LENGTH_SHORT).show();
                startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
