package com.rocky.latte.core.delegates.web.event;

import android.content.Context;

import com.rocky.latte.core.delegates.LatteDelegate;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/2/22
 */

public abstract class Event implements IEvent {
    private Context mContext = null;
    private String mAction = null;
    private LatteDelegate delegate = null;
    private String mUrl = null;

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String mAction) {
        this.mAction = mAction;
    }

    public LatteDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(LatteDelegate delegate) {
        this.delegate = delegate;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
