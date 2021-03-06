package com.rocky.latte.core.ui.home;

import android.view.KeyEvent;
import android.view.View;

import com.rocky.latte.core.delegates.LatteDelegate;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/28
 */

public abstract class BaseHomeDelegate extends LatteDelegate implements View.OnKeyListener {
    private long mExitTime = 0;
    private static final int EXIT_TIME = 2000;

    @Override
    public void onResume() {
        super.onResume();
        View rootView = getView();
        if (rootView != null) {
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - mExitTime > EXIT_TIME) {
                showToast("再按一次退出程序");
                mExitTime = System.currentTimeMillis();
            } else {
                _mActivity.finish();
                if (mExitTime != 0)
                    mExitTime = 0;
            }
            return true;
        }
        return false;
    }
}
