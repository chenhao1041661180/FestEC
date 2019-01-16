package com.rocky.festec.latte.base;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.rocky.latte.core.app.Latte;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/16
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcons(new FontAwesomeModule())
                .configure();
    }
}
