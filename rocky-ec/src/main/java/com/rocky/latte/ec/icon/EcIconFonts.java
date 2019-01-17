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
    icon_ali_pay('\ue64b');

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
