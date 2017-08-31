package com.zhang.latte;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.zhang.latte_core.app.Latte;
import com.zhang.latte_core.net.interceptors.DebugInterceptor;
import com.zhang.latte_ec.icon.FontEcModule;

/**
 * Created by 德医互联 on 2017/8/25.
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://127.0.0.1")
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .configure();
    }
}
