package com.smyy.sharetour.buyer.network.rx;

import android.os.Build;

import com.smyy.sharetour.buyer.MyApplication;
import com.smyy.sharetour.buyer.network.config.HttpConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OKHTTP 自定义拦截
 */
public class BaseInterceptor implements Interceptor {
    private String okHttpUserAgent;
    private JSONArray mRequestRoute;//和服务端配合的请求head头route

//    private Map<String, String> headers;
//
//    public BaseInterceptor(Map<String, String> headers) {
//        this.headers = headers;
//    }

    public BaseInterceptor() {
        mRequestRoute = obtainReqHead();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request chainRequest = chain.request();
        Request.Builder requestBuilder = chainRequest.newBuilder();
        requestBuilder.header("channel", "MOBILE");

        addReqHeader(chainRequest, requestBuilder);

//        if (headers != null && headers.size() > 0) {
//            Set<String> keys = headers.keySet();
//            for (String headerKey : keys) {
//                requestBuilder.header(headerKey, headers.get(headerKey)).build();
//            }
//        }

        try {
            if (okHttpUserAgent == null)
                okHttpUserAgent = HttpConfig.getUserAgent(null);
            requestBuilder.header("User-Agent", okHttpUserAgent);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            MyApplication.mXqcStatistic.logError("OkHttpUtils intercept addHeader UA error msg1 = " + e.getMessage());
            try {
                okHttpUserAgent = HttpConfig.getUserAgentWhenError();
                requestBuilder.header("User-Agent", okHttpUserAgent);
            } catch (Exception e2) {
                e2.printStackTrace();
                MyApplication.mXqcStatistic.logError("OkHttpUtils intercept addHeader getUserAgentWhenError error msg3 = " + e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            MyApplication.mXqcStatistic.logError("OkHttpUtils intercept addHeader UA error msg2 = " + e.getMessage());
        }
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }

    /**
     * 获取reqHead头RequestRoute的基本参数
     * @return
     */
    private JSONArray obtainReqHead() {
        try {
            JSONArray requestRoute = new JSONArray();
            MyApplication application = MyApplication.getApplication();
            //android sdk
            JSONObject route = new JSONObject();
            route.put("system", "android");
            route.put("version", Build.VERSION.SDK);//17
            requestRoute.put(route);
            //手机型号
            route = new JSONObject();
            route.put("system", Build.MODEL);//AMOI N828
            route.put("version", Build.VERSION.RELEASE);//4.2.1
            requestRoute.put(route);
            //手机分辨率 480x800
            route = new JSONObject();
            route.put("system", "Dpi");
            route.put("version", application.getDpi());
            requestRoute.put(route);
            //app版本
            route = new JSONObject();
            route.put("system", HttpConfig.getAppProduct());
            route.put("version", application.getAppVersionName());
            requestRoute.put(route);
            return requestRoute;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加入reqHead头
     * @param chainRequest
     * @param builder
     */
    private void addReqHeader(Request chainRequest, Request.Builder builder) {
        if (mRequestRoute == null) {
            mRequestRoute = obtainReqHead();
        }

        JSONObject reqHead = new JSONObject();
        try {
            MyApplication application = MyApplication.getApplication();
//            String account = application.getAccount();
//            String deviceToken = application.getUmDeviceToken();
//            if (account == null) {
//                account = deviceToken;
//            }
//            reqHead.put("user", account);
//            reqHead.put("requestDevice", deviceToken);
            reqHead.put("reqTime", String.valueOf(System.currentTimeMillis()));
            if (chainRequest != null) {
                reqHead.put("reqUrl", chainRequest.url().url());
            }
            reqHead.put("RequestRoute", mRequestRoute);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        builder.header("reqHead", reqHead.toString());
    }
}