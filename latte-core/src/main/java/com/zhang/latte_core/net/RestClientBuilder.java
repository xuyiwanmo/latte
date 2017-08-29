package com.zhang.latte_core.net;

import com.zhang.latte_core.net.callback.IError;
import com.zhang.latte_core.net.callback.IFailure;
import com.zhang.latte_core.net.callback.IRequest;
import com.zhang.latte_core.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by 德医互联 on 2017/8/29.
 */

public class RestClientBuilder {
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private String mUrl;
    private IRequest mIRequest;
    private ISuccess mISuccess;
    private IFailure mIFailure;
    private IError mIError;
    private RequestBody mBody;

    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        this.PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        this.PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mIRequest, mISuccess, mIFailure, mIError, mBody);
    }
}
