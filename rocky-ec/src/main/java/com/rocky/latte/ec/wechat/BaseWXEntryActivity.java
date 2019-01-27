package com.rocky.latte.ec.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rocky.latte.core.net.RestClient;
import com.rocky.latte.core.net.callback.ResponseCallback;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;


/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/27
 */

public abstract class BaseWXEntryActivity extends BaseWXActivity {

    //用户登录成功后回调
    protected abstract void onSignSuccess(String userInfo);

    //微信发送请求到第三方应用后的回调
    @Override
    public void onReq(BaseReq baseReq) {

    }

    //第三方应用发送请求到微信后的回调
    @Override
    public void onResp(BaseResp baseResp) {
        final String code = ((SendAuth.Resp) baseResp).code;
        final StringBuilder authUrl = new StringBuilder();
        authUrl
                .append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
                .append(LatteWeChat.APP_ID)
                .append("&secret=")
                .append(LatteWeChat.APP_SECRET)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");
    }

    private void getAuth(String url){
        RestClient.builder()
                .url(url)
                .callback(new ResponseCallback() {
                    @Override
                    public void onSuccess(String message) {
                        final JSONObject authObj = JSON.parseObject(message);
                        final String accessToken = authObj.getString("access_token");
                        final String openId = authObj.getString("openid");

                    final  StringBuilder userInfoUrl = new StringBuilder();
                    userInfoUrl.append("https://api.weixin.qq.com/sns/userinfo?access_token=")
                            .append(accessToken)
                            .append("&openid=")
                            .append(openId)
                            .append("&lang=")
                            .append("zh_CN");
                    getUserInfo(userInfoUrl.toString());
                    }

                    @Override
                    public void onFailed() {

                    }

                    @Override
                    public void onError(int code, String msg) {

                    }
                }).build().get();
    }
    private void getUserInfo(String userInfoUrl){

        RestClient.builder()
                .url(userInfoUrl)
                .callback(new ResponseCallback() {
                    @Override
                    public void onSuccess(String message) {
                        final JSONObject authObj = JSON.parseObject(message);
                        final String accessToken = authObj.getString("access_token");
                        final String openId = authObj.getString("openid");

                        final  StringBuilder userInfoUrl = new StringBuilder();
                        userInfoUrl.append("https://api.weixin.qq.com/sns/userinfo?access_token=")
                                .append(accessToken)
                                .append("&openid=")
                                .append(openId)
                                .append("&lang=")
                                .append("zh_CN");
                        getUserInfo(userInfoUrl.toString());
                    }

                    @Override
                    public void onFailed() {

                    }

                    @Override
                    public void onError(int code, String msg) {

                    }
                }).build().get();
    }

}
