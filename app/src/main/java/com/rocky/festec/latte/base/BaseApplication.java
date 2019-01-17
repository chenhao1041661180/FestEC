package com.rocky.festec.latte.base;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.rocky.latte.core.app.Latte;
import com.rocky.latte.ec.icon.FontEcModule;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/16
 */

public class BaseApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this).withApiHost("http://127.0.0.1/")

                .withIcons(new FontEcModule())
                .withIcons(new FontAwesomeModule())
                .configure();
    }
}
