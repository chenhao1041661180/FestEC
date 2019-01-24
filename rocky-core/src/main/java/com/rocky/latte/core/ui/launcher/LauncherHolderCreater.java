package com.rocky.latte.core.ui.launcher;

import android.view.View;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.rocky.latte.core.R;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/24
 */

public class LauncherHolderCreater implements CBViewHolderCreator {

    @Override
    public Holder createHolder(View itemView) {
        return new LauncherHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_localimage;
    }
}
