package com.rocky.latte.core.ui.launcher;

import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import com.rocky.latte.core.R;


/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/24
 */

public class LauncherHolder extends Holder<Integer> {
    private AppCompatImageView imageView;
    public LauncherHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        imageView =itemView.findViewById(R.id.iv_post);
    }

    @Override
    public void updateUI(Integer data) {
        imageView.setBackgroundResource(data);
    }
}
