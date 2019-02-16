package com.rocky.latte.ui.recycler;

import android.support.annotation.ColorRes;

import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/2/16
 */

public class BaseDecoration extends DividerItemDecoration {


    private BaseDecoration(@ColorRes int color, int size) {
        setDividerLookup(new DividerLookupImpl(color, size));
    }

    public static BaseDecoration create(@ColorRes int color, int size) {
        return new BaseDecoration(color, size);
    }

    class DividerLookupImpl implements DividerLookup {
        private final int SIZE;
        private final int COLOR;

        public DividerLookupImpl(int color, int size) {
            this.COLOR = color;
            this.SIZE = size;

        }

        @Override
        public Divider getVerticalDivider(int position) {
            return new Divider.Builder().size(SIZE).color(COLOR).build();
        }

        @Override
        public Divider getHorizontalDivider(int position) {
            return new Divider.Builder().size(SIZE).color(COLOR).build();
        }
    }
}
