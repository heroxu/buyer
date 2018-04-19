package com.smyy.sharetour.buyer;


public class SPConfig {
    /**
     * 用户信息缓存，退出应用是清除
     */
    public static final String NAME_USER_CACHE = "user_cache";
    public static final String KEY_USER_INFO = "user_info";

    public static final String NAME_APP_SETTINGS = "app_settings";
    /**
     * 是否打开即时消息通知
     */
    public static final String KEY_TOGGLE_IM = "toggle_im";
    /**
     * 是否打开系统消息通知
     */
    public static final String KEY_TOGGLE_SYSM = "toggle_sysm";

    /**
     * 虚拟数据，后台上线后删除
     */
    public static final String NAME_FAKE_DATA = "fake_data";
    public static final String KEY_FAKE_ADDRESS = "fake_address";
}
