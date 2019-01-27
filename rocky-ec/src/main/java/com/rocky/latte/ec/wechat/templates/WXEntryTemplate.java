package com.rocky.latte.ec.wechat.templates;

import com.rocky.latte.core.activities.ProxyActivity;
import com.rocky.latte.core.delegates.LatteDelegate;
import com.rocky.latte.ec.wechat.BaseWXEntryActivity;
import com.rocky.latte.ec.wechat.LatteWeChat;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/26
 */

public class WXEntryTemplate extends BaseWXEntryActivity{

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0,0);

    }

    @Override
    protected void onSignSuccess(String userInfo) {
        LatteWeChat.getInstance().getIWeChatSignCallback().onSignInSuccess(userInfo);
    }
}
