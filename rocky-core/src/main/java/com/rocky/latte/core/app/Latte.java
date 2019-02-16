package com.rocky.latte.core.app;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/16
 */

public final class Latte {

    public static Configurator init(Context context) {
        getConfigurator()
                .getLatteConfigs()
                .put(ConfigType.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfigurations(Enum<ConfigType> typeEnum) {
        return getConfigurator().getConfiguration(typeEnum);
    }

    public static Context getApplicationContext() {
        return getConfigurations(ConfigType.APPLICATION_CONTEXT);
    }
    public static Handler getHandler() {
        return getConfigurations(ConfigType.HANDLER);
    }
}
