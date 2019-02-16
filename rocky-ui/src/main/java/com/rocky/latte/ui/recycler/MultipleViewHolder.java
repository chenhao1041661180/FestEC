package com.rocky.latte.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/2/15
 */

public class MultipleViewHolder extends BaseViewHolder {
    protected MultipleViewHolder(View view) {
        super(view);
    }

    public static MultipleViewHolder create(View view) {
        return new MultipleViewHolder(view);
    }
}
