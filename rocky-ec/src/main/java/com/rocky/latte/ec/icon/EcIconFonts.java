package com.rocky.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/16
 */

public enum EcIconFonts implements Icon {
    icon_scan('\ue60b'),
    icon_ali_pay('\ue64b'),
    icon_home('\ue505'),
    icon_sort('\ue60a'),
    icon_compass('\ue50c'),
    icon_shopping_cart('\ue503'),
    icon_user('\ue699');

    private char character;

    EcIconFonts(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
