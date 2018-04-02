package com.smyy.sharetour.buyer;

/**
 * 日志统计接口定义
 */
public interface IStatistic {

    //错误日志
    void logError(String error);
    /**
     * 自定义统计接口
     */
    /**
     * 1.提现 埋点
     */
    void addCustomEventWidthDraw(String ranAmount, String httpCode, String httpMsg);

    /**
     * 2.h5页面的点击统计
     */
    void addCustomEventH5Click(String h5url, String h5Title);

    /**
     * 3.动态h5包的下载成功 与否埋点
     */
    void addCustomEventH5Load(String mTitle, boolean isSuccessed);

    /**
     * 4.动态h5包的下载成功 与否埋点
     */
    void addCustomEventH5Download(String h5url, boolean isSuccessed);




    /**
     * 5.登陆 埋点
     */
    void addCustomEventUserLogin(String username, String httpCode, String httpMsg);

    /**
     * 6.消息页面点击次数 埋点
     */
    void addCustomEventMsgListClick();

    /**
     * 7.消息详情点击埋点
     */
    void addCustomEventMsgDetailClick(String title);

    /**
     * 8.token失效 埋点
     */
    void addCustomEventTokenInvalid(String httpCode, String httpMsg);

    /**
     * 9.更换手机 埋点
     */
    void addCustomEventPhoneChange(String oldPhone, String newPhone, String httpCode, String httpMsg);
}