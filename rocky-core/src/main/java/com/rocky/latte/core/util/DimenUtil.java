package com.rocky.latte.core.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.rocky.latte.core.app.Latte;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/17
 */

public class DimenUtil {

    public static final int getScreenWidth() {
        final Resources resources = Latte.getApplicationContext().getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static final int getScreenHeight() {
        final Resources resources = Latte.getApplicationContext().getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return metrics.heightPixels;
    }
}
