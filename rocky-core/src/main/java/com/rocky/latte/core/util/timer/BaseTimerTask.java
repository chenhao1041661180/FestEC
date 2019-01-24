package com.rocky.latte.core.util.timer;

import java.util.TimerTask;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/24
 */

public class BaseTimerTask extends TimerTask {
    private ITimerListener mITimerListener;

    public BaseTimerTask(ITimerListener timerListener) {
        this.mITimerListener = timerListener;
    }

    @Override
    public void run() {
        if (mITimerListener != null)
            mITimerListener.onTimer();
    }
}
