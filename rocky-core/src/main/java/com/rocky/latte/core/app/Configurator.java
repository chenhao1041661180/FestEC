package com.rocky.latte.core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.WeakHashMap;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/16
 */

public final class Configurator {
    public static final WeakHashMap<String, Object> LATTE_CONFIGS = new WeakHashMap<>();
    public static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    public Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    final WeakHashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    public final void configure() {
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withApiHost(String apiHost) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), apiHost);
        return this;
    }

    public final Configurator withIcons(IconFontDescriptor iconFontDescriptor) {
        ICONS.add(iconFontDescriptor);
        return this;
    }

    /**
     * 检查配置
     */
    @SuppressWarnings("unchecked")
    private void checkConfigurations() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
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
        return (T) LATTE_CONFIGS.get(typeEnum.name());
    }
}
