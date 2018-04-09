package com.smyy.sharetour.buyer.network.rx;

import com.smyy.sharetour.buyer.BuildConfig;
import com.smyy.sharetour.buyer.util.LogUtil;
import com.smyy.sharetour.buyer.MyApplication;
import com.smyy.sharetour.buyer.util.NetworkUtils;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.util.ToastUtils;

import io.reactivex.subscribers.DisposableSubscriber;


/**
 * 观察者的实现，自定义基类，相当于回调或消费者等
 */
public abstract class BaseSubscriber<T> extends DisposableSubscriber<T> {
    public static final String TAG = BaseSubscriber.class.getSimpleName();
    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d(">>>", "统一在每个请求前检测网络");
        if (!NetworkUtils.isNetworkAvailable(MyApplication.getApplication().getApplicationContext())) {
            /**
             * doSomething
             * 比如从缓存中取数据
             * 下面只是提示无网络
             */
            //有可能在非ui线程中, 解决https://bugly.qq.com/v2/crash-reporting/crashes/21720d2a2f/422?pid=1
            MyApplication.getApplication().mMainThreadHandler.post(new Runnable() {
                @Override
                public void run() {
                    ToastUtils.showToast(MyApplication.getContext(), R.string.no_network_message);
                }
            });
            onComplete();
        }
    }

    /**
     * 因错误请求结束
     *
     * @param t
     */
    @Override
    public void onError(Throwable t) {
        super.onStart();
        LogUtil.i(TAG, "BaseSubscriber onError:" + t.getMessage());
        if (BuildConfig.DEBUG) {
            t.printStackTrace();
        }
        if (t instanceof ResultExceptionUtils.ResponseThrowable) {
            onError((ResultExceptionUtils.ResponseThrowable) t);
        } else {
            onError(new ResultExceptionUtils.ResponseThrowable(t));
        }
    }

    /**
     * 请求完成
     */
    @Override
    public void onComplete() {
        super.onStart();
        LogUtil.i(TAG, "BaseSubscriber onComplete:");

    }


    /**
     * 请求结果
     *
     * @param t
     */
    @Override
    public void onNext(T t) {
        super.onStart();
        //LogUtil.i(TAG,"BaseSubscriber onNext data:"+new Gson().toJson(t));
        onSucessed(t);

    }


    /**
     * 成功后返回的数据
     * 一定有数据，且不会空
     *
     * @param model
     */
    public abstract void onSucessed(T model);

    /**
     * 统一处理后返回的错误
     * e.message 是处理过的消息提示
     *
     * @param e
     */
    public abstract void onError(ResultExceptionUtils.ResponseThrowable e);


}
