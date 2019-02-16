package com.rocky.festec.latte.base;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.rocky.festec.latte.R;
import com.rocky.latte.core.app.Latte;
import com.rocky.latte.core.net.interceptor.DebugInterceptor;
import com.rocky.latte.ec.db.DaoManager;
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
        Latte.init(this)
                .withApiHost("http://mock.fulingjie.com/")
                .withIcons(new FontEcModule())
                .withIcons(new FontAwesomeModule())
//                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .configure();

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
        DaoManager.getInstance().init(this);

    }
}
