package com.rocky.latte.core.ui.home;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/28
 */

public class BottomTabBean {
    private CharSequence TITLE;
    private CharSequence ICON;

    public BottomTabBean(CharSequence ICON, CharSequence TITLE) {
        this.TITLE = TITLE;
        this.ICON = ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }

    public void setTitle(CharSequence TITLE) {
        this.TITLE = TITLE;
    }

    public CharSequence getIcon() {
        return ICON;
    }

    public void setIcon(CharSequence ICON) {
        this.ICON = ICON;
    }
}
