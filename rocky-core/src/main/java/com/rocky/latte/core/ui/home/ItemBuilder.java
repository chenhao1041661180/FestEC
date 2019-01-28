package com.rocky.latte.core.ui.home;

import java.util.LinkedHashMap;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/28
 */

public class ItemBuilder {

    LinkedHashMap<BottomTabBean, BaseHomeDelegate> ITEMS = new LinkedHashMap<>();

    public static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public ItemBuilder addItem(BottomTabBean tab, BaseHomeDelegate delegate) {
        this.ITEMS.put(tab, delegate);
        return this;
    }

    public ItemBuilder addItems(LinkedHashMap<BottomTabBean, BaseHomeDelegate> items) {
        this.ITEMS.putAll(items);
        return this;
    }

    public LinkedHashMap<BottomTabBean, BaseHomeDelegate> build() {
        return ITEMS;
    }
}
