package com.rocky.latte.ec.ui.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.rocky.latte.core.delegates.LatteDelegate;
import com.rocky.latte.core.ui.launcher.LauncherHolderCreater;
import com.rocky.latte.core.ui.launcher.ScrollLauncherTag;
import com.rocky.latte.core.util.storage.LattePreference;
import com.rocky.latte.ec.R;

import java.util.ArrayList;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/24
 */

public class LauncherSplishDelegate extends LatteDelegate implements OnItemClickListener {
    ConvenientBanner<Integer> mConvenientBanner = null;
    ArrayList<Integer> INTEGERS = new ArrayList<>();

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<Integer>(getContext());
        return mConvenientBanner;
    }

    private void initBanner() {
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);

        mConvenientBanner
                .setPages(new LauncherHolderCreater(), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        if (position == INTEGERS.size() - 1) {
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(),true);
        }
    }

}
