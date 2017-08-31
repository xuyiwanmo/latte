package com.zhang.latte_core.app;

import android.os.Handler;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * 全局初始化  参数就创建
 */

public class Configurator {

    //键值对在不使用的时候  会自动回收
    private static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();

    //存储字体的空间
    private static final ArrayList<IconFontDescriptor> ICONS=new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();
    private static final Handler HANDLER = new Handler();
    private Configurator() {
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, false);
        LATTE_CONFIGS.put(ConfigKeys.HANDLER, HANDLER);
    }

    /**
     * 单例模式  线程安全  静态内部类  然后get
     */
    private static class Holder {
        private static final Configurator INSTENCE = new Configurator();
    }

    public static Configurator getInstance(){
        return Holder.INSTENCE;
    }

    /**
     * 通知配置文件配置完成
     */
    public final void configure() {
        initIcons();
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, true);

    }

    public static HashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    /**
     * 配置主路径
     */
    public final Configurator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigKeys.API_HOST,host);
        return this;
    }

    public void  checkConfiguration(){
        //让不可变性尽量最大
        final boolean isReady= (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY);

        if (!isReady){
            throw new RuntimeException("Configuration is not ready ,call configution ");
        }
    }

    /**
     * 获取这些配置
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigKeys> key){
        checkConfiguration();
        return (T)LATTE_CONFIGS.get(key);
    }

    private void initIcons(){
        if (ICONS.size()>0) {
            final Iconify.IconifyInitializer initializer=Iconify.with(ICONS.get(0));//取出第一个
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    /**
     * 添加自己需要的
     * @param descriptor
     * @return
     */
    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = LATTE_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) LATTE_CONFIGS.get(key);
    }
}
