package com.rocky.latte.core.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/2/22
 */

public class WebJsInterface {
    private WebDelegate delegate = null;

    private WebJsInterface(WebDelegate delegate) {
        this.delegate = delegate;
    }

    public static WebJsInterface create(WebDelegate delegate) {
        return new WebJsInterface(delegate);
    }
    @JavascriptInterface
    public  String event(String params){
        JSON.parseObject(params).getString("action");
        return null;
    }
}
