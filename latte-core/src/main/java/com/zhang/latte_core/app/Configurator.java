package com.zhang.latte_core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 全局初始化  参数就创建
 */

public class Configurator {

    //键值对在不使用的时候  会自动回收
    private static final HashMap<String, Object> LATTE_CONFIGS = new HashMap<>();

    //存储字体的空间
    private static final ArrayList<IconFontDescriptor> ICONS=new ArrayList<>();

    public Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
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
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    public static HashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    /**
     * 配置主路径
     */
    public final Configurator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(),host);
        return this;
    }

    public void  checkConfiguration(){
        //让不可变性尽量最大
        final boolean isReady= (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());

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
    final <T> T getConfiguration(Enum<ConfigType> key){
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
}
