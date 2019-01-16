package com.rocky.latte.core.app;

import android.content.Context;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/16
 */

public final class Latte {

    public static Configurator init(Context context) {
        Configurator.getInstance().getLatteConfigs().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }


}
