package com.rocky.latte.ec.wechat;

import android.app.Activity;

import com.rocky.latte.core.app.ConfigType;
import com.rocky.latte.core.app.Latte;
import com.rocky.latte.ec.wechat.callback.IWeChatSignCallback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/27
 */

public class LatteWeChat {
    public static final String APP_ID = Latte.getConfigurations(ConfigType.WE_CHAT_APP_ID);
    public static final String APP_SECRET = Latte.getConfigurations(ConfigType.WE_CHAT_APP_SECRET);
    private IWeChatSignCallback mIWeChatSignCallback;
    private final IWXAPI WXAPI;

    private static final class Holder {
        private static final LatteWeChat INSTANCE = new LatteWeChat();

    }

    public static LatteWeChat getInstance() {
        return Holder.INSTANCE;
    }

    public LatteWeChat() {
        final Activity activity = Latte.getConfigurations(ConfigType.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_ID);
    }

    public final IWXAPI getWXAPI() {
        return WXAPI;
    }

    public LatteWeChat onSignSuccess(IWeChatSignCallback iWeChatSignCallback) {
        this.mIWeChatSignCallback = iWeChatSignCallback;
        return this;
    }

    public IWeChatSignCallback getIWeChatSignCallback() {
        return mIWeChatSignCallback;
    }

    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }

}
