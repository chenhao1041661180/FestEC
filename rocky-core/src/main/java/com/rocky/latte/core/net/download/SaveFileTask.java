package com.rocky.latte.core.net.download;

import android.os.AsyncTask;

import com.rocky.latte.core.net.callback.IRequest;
import com.rocky.latte.core.net.callback.ResponseCallback;

import java.io.File;

/**
 * 模块说明：
 * @author 陈浩
 * @date 2019/1/18
 */

public class SaveFileTask extends AsyncTask<Object, Void, File> {
    private ResponseCallback CALLBACK;
    private IRequest IREQUEST;

    public SaveFileTask(ResponseCallback CALLBACK, IRequest IREQUEST) {
        this.CALLBACK = CALLBACK;
        this.IREQUEST = IREQUEST;
    }

    public SaveFileTask() {
    }

    @Override
    protected File doInBackground(Object... objects) {

        return null;
    }
}
