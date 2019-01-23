package com.rocky.latte.core.net.interceptor;

import android.support.annotation.RawRes;

import com.rocky.latte.core.util.FileUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/23
 */

public class DebugInterceptor extends BaseInterceptor {
    private String DEBUG_URL;
    private int DEBUG_RAW_ID;

    public DebugInterceptor(String debugUrl, int rawId) {
        this.DEBUG_URL = debugUrl;
        this.DEBUG_RAW_ID = rawId;
    }
    private Response getResponse(Chain chain,String json){
        return  new Response.Builder()
                .addHeader("Content-Type","application/json")
                .code(200)
                .body(ResponseBody.create(MediaType.parse("application/jso"),json))
                .message("ok")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }
    private Response getDebugResponse(Chain chain, @RawRes int rawId){
        final String json = FileUtil.getRawFile(rawId);
        return getResponse(chain,json);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
       final String url = chain.request().url().toString();
       if (url.contains(DEBUG_URL)){
           return getDebugResponse(chain,DEBUG_RAW_ID);
       }
        return chain.proceed(chain.request());
    }
}
