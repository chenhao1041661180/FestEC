package com.rocky.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/16
 */

public enum EcIconFonts implements Icon {
    icon_scan('\ue6b5'),
//    icon_ali_pay('\ue64b'),
    icon_home('\ue503'),
    icon_sort('\ue502'),
    icon_compass('\ue61a'),
    icon_shopping_cart('\ue506'),
    icon_notice('\ue620'),
    icon_user('\ue659');

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
