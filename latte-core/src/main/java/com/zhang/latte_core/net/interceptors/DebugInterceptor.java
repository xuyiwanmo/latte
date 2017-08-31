package com.zhang.latte_core.net.interceptors;

import android.support.annotation.RawRes;

import java.io.IOException;

import okhttp3.Protocol;
import okhttp3.Response;

/**
 * Created by 德医互联 on 2017/8/31.
 */

public class DebugInterceptor extends BaseInterceptor {

    private final String DEBUG_URL;
    private final int DEBUG_RAW_ID;

    public DebugInterceptor(String debug_url, int debug_raw_id) {
        this.DEBUG_URL = debug_url;
        this.DEBUG_RAW_ID = debug_raw_id;
    }

    private Response getResponse(Chain chain,String json){
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type","application/json")
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }
    private Response debugResponse(Chain chain, @RawRes int rawId){
        final  String json="";
        return  getResponse(chain,json);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final  String url=chain.request().url().toString();
        if (url.contains(DEBUG_URL)) {
            return debugResponse(chain,DEBUG_RAW_ID);
        }

        return chain.proceed(chain.request());
    }
}
