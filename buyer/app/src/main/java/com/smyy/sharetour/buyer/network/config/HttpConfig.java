package com.smyy.sharetour.buyer.network.config;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.WebView;

import com.smyy.sharetour.buyer.MyApplication;
import com.smyy.sharetour.buyer.util.PackageUtils;

import java.util.Locale;

/**
 * Created by hasee on 2018/3/13.
 */

public class HttpConfig {
    //api     业务接口
    public static String BASE_URL_API = "https://www.baidu.com/";
    private static String mUserAgentAppname = "xmyy-android";
    private static String mUserAgent;
    private static String mUserAgentWhenError;//当add ua发生错误时候的ua

    /**
     * 正文页WebView 的 UserAgent
     */
    public static String getUserAgent(WebView webView) {
        if (mUserAgent == null) {
            Context context = MyApplication.getContext();
            String defaultUA = null;
            try {
                boolean isEmpty = false;
                if (webView == null) {
                    webView = new WebView(context);
                    isEmpty = true;
                }
                defaultUA = webView.getSettings().getUserAgentString();
                if (isEmpty) {
                    webView.destroy();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            mUserAgent = (TextUtils.isEmpty(defaultUA) ? "" : (defaultUA + " "))
                    + mUserAgentAppname + "/" + PackageUtils.getVersionName(context)
                    + " (Android " + Build.VERSION.RELEASE + "; "
                    + Locale.getDefault().toString() + "; "
                    + Build.MANUFACTURER + " " + Build.MODEL + "/" + Build.PRODUCT + ")";
        }
        return mUserAgent;
    }

    public static String getUserAgentWhenError() {
        if (mUserAgentWhenError == null) {
            mUserAgentWhenError = mUserAgentAppname + "/" + PackageUtils.getVersionName(MyApplication.getContext())
                    + " (Android " + Build.VERSION.RELEASE + "; "
                    + Locale.getDefault().toString() + ")";
        }
        return mUserAgentWhenError;
    }

    /**
     * 获取当前产品名称
     *
     * @return
     */
    public static final String getAppProduct() {
        String packageName = MyApplication.getApplication().getPackageName();
        return packageName;
    }

    //api     测试接口
    public static String TEST_API = "http://v.juhe.cn/joke/randJoke.php";

    public static class RequestUrl {
        /**
         * 测试接口
         **/
        public static String getTestUrl() {
            return TEST_API;
        }
    }

}
