package com.zhang.latte;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.zhang.latte_core.delegate.LatteDelagate;
import com.zhang.latte_core.net.RestClient;
import com.zhang.latte_core.net.callback.IError;
import com.zhang.latte_core.net.callback.IFailure;
import com.zhang.latte_core.net.callback.ISuccess;

/**
 * Created by 德医互联 on 2017/8/29.
 */

public class ExampleDelegate extends LatteDelagate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient(){
        RestClient.builder()
                .url("http://news.baidu.com")
                //.params("","")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(),response,Toast.LENGTH_SHORT).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .build()
                .get();
    }
}
