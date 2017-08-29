package com.zhang.latte_core.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by 德医互联 on 2017/8/25.
 */

public final class Latte {
    public static Configurator init(Context context) {
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context);
        return Configurator.getInstance();
    }

    public static HashMap<String, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Context getApplication(){
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT);
    }
}
