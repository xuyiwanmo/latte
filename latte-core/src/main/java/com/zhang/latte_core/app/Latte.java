package com.zhang.latte_core.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by 德医互联 on 2017/8/25.
 */

public final class Latte {
    public static Configurator init(Context context) {
        getConfigurations().put(ConfigKeys.APPLICATION_CONTEXT, context);
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static HashMap<Object, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext(){
        return (Context) getConfigurations().get(ConfigKeys.APPLICATION_CONTEXT);
    }
}
