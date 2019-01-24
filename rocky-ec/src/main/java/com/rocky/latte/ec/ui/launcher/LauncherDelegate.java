package com.rocky.latte.ec.ui.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.rocky.latte.core.delegates.LatteDelegate;
import com.rocky.latte.core.util.timer.BaseTimerTask;
import com.rocky.latte.core.util.timer.ITimerListener;
import com.rocky.latte.ec.R;
import com.rocky.latte.ec.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/24
 */

public class LauncherDelegate extends LatteDelegate implements ITimerListener {
    @BindView(R2.id.tv_delegate_timer)
    AppCompatTextView mTvTimer;
    private int mCount = 5;
    private Timer timer = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @OnClick(R2.id.tv_delegate_timer)
    void setOnClick() {
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    private void initTimer() {
        timer = new Timer();
        final BaseTimerTask timerTask = new BaseTimerTask(this);
        timer.schedule(timerTask, 0, 1000);
    }

    @Override
    public void onTimer() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                mCount--;
                if (mCount <= 0) {
                    if (timer != null)
                        timer.cancel();
                    timer = null;
                }

            }
        });
    }
}
