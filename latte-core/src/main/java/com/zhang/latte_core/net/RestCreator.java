package com.zhang.latte_core.net;

import com.zhang.latte_core.app.ConfigKeys;
import com.zhang.latte_core.app.Latte;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by 德医互联 on 2017/8/29.
 */

public class RestCreator {

    private static final class ParamsHolder{
        public static final WeakHashMap<String,Object> PARAMS=new WeakHashMap<>();
    }

    public static WeakHashMap<String,Object> getParams(){
        return ParamsHolder.PARAMS;
    }

    private static final class RetrofitHolder{
        //注意  这个地方是 ConfigKeys.API_HOST.name()
        private static  final  String BASE_URL= (String) Latte.getConfigurations().get(ConfigKeys.API_HOST);
        private  static  final Retrofit RETROFIT_CLIENT=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())//引入转换器
                .build();
    }
    /**
     * 构建OkHttp
     */
    private static final class OKHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        private static final ArrayList<Interceptor> INTERCEPTORS = Latte.getConfiguration(ConfigKeys.INTERCEPTOR);

        private static OkHttpClient.Builder addInterceptor() {
            if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
                for (Interceptor interceptor : INTERCEPTORS) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }

        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    private static final class  RestServiceHolder{
        private static final RestService REST_SERVICE=
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    public static RestService getRestService(){
        return RestServiceHolder.REST_SERVICE;
    }
}
