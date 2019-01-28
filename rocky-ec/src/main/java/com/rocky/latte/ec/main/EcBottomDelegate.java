package com.rocky.latte.ec.main;

import android.graphics.Color;

import com.rocky.latte.core.ui.home.BaseBottomDelegate;
import com.rocky.latte.core.ui.home.BaseHomeDelegate;
import com.rocky.latte.core.ui.home.BottomTabBean;
import com.rocky.latte.core.ui.home.ItemBuilder;
import com.rocky.latte.ec.main.cart.ShopCartDelegate;
import com.rocky.latte.ec.main.discover.DiscoverDelegate;
import com.rocky.latte.ec.main.home.HomeDelegate;
import com.rocky.latte.ec.main.personal.PersonalDelegate;
import com.rocky.latte.ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/28
 */

public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BaseHomeDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BaseHomeDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{icon-home}", "首页"), new HomeDelegate());
        items.put(new BottomTabBean("{icon-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{icon-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{icon-shopping-cart}", "购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{icon-user}", "我的"), new PersonalDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 3;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
