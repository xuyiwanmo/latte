package com.zhang.latte_core.app;

/**
 * 只会被初始化一次,多线程操作的惰性
 */

public enum ConfigKeys {
    API_HOST,
    APPLICATION_CONTEXT,
    CONFIG_READY,//配置是否完成
    INTERCEPTOR, HANDLER, ICON
}
