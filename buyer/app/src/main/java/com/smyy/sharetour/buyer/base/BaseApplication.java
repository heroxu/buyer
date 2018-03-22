package com.smyy.sharetour.buyer.base;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.multidex.MultiDexApplication;

/**
 * Created by hasee on 2018/3/13.
 */

public class BaseApplication extends MultiDexApplication {
    private static final String TAG = BaseApplication.class.getSimpleName();

    private static BaseApplication mContext;

    public static Handler mMainThreadHandler;
    public static Handler mThreadHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mMainThreadHandler = new Handler(Looper.getMainLooper());
        HandlerThread thread = new HandlerThread(TAG, android.os.Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();
        mThreadHandler = new Handler(thread.getLooper());
    }

    public static BaseApplication getContext() {
        return mContext;
    }
}
