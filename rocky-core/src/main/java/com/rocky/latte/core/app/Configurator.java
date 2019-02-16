package com.rocky.latte.core.app;

import android.app.Activity;
import android.os.Handler;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

import okhttp3.Interceptor;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/16
 */

public final class Configurator {
    public static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();
    public static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    public static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();
    private static final Handler HANDLER = new Handler();
    public Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY, false);
        LATTE_CONFIGS.put(ConfigType.HANDLER, HANDLER);
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    final HashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    public final void configure() {
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY, true);
    }

    /**
     * 初始化图标
     */
    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    /**
     * 请求api地址
     * @param apiHost
     * @return
     */
    public final Configurator withApiHost(String apiHost) {
        LATTE_CONFIGS.put(ConfigType.API_HOST, apiHost);
        return this;
    }

    /**
     * 添加字体图标
     * @param iconFontDescriptor
     * @return
     */
    public final Configurator withIcons(IconFontDescriptor iconFontDescriptor) {
        ICONS.add(iconFontDescriptor);
        return this;
    }

    /**
     *  添加请求拦截器
     * @param interceptor
     * @return
     */
    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    /**
     * 添加请求拦截器
     * @param interceptors
     * @return
     */
    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    /**
     * 添加WECHAT_APP_ID
     * @param appId
     * @return
     */
    public final Configurator withWeChatAppId(String appId) {
        LATTE_CONFIGS.put(ConfigType.WE_CHAT_APP_ID, appId);
        return this;
    }
    /**
     * 添加微信AppSecret
     * @param appSecret
     * @return
     */
    public final Configurator withWeChatAppSecret(String appSecret) {
        LATTE_CONFIGS.put(ConfigType.WE_CHAT_APP_SECRET, appSecret);
        return this;
    }
    /**
     * 添加activity
     * @param activity
     * @return
     */
    public final Configurator withActivity(Activity activity) {
        LATTE_CONFIGS.put(ConfigType.ACTIVITY, activity);
        return this;
    }
    /**
     * 检查配置
     */
    @SuppressWarnings("unchecked")
    private void checkConfigurations() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY);
        if (!isReady)
            throw new RuntimeException("Configuration is not ready,call configure");
    }

    /**
     * getConfiguration
     *
     * @param typeEnum
     * @param <T>
     * @return
     */
    final <T> T getConfiguration(Enum<ConfigType> typeEnum) {
        checkConfigurations();
        return (T) LATTE_CONFIGS.get(typeEnum);
    }
}
