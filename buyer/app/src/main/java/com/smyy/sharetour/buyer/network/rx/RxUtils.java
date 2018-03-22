package com.smyy.sharetour.buyer.network.rx;

import android.webkit.MimeTypeMap;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * rx工具类
 */

public class RxUtils {

    /**
     * 获取默认的消费者，监听者，设置好的回调
     * 也可以自己
     * new BaseSubscriber<T>() {
     *
     * @param rxCallBack
     * @param <T>
     * @return
     * @Override public void onSucessed(T model) {
     * rxCallBack.onSucessed(model);
     * }
     * @Override public void onError(ResultExceptionUtils.ResponseThrowable e) {
     * rxCallBack.onFailed(e);
     * }
     * }
     */
    public static <T> BaseSubscriber<EntityResponse<T>> getDefaultSubscriber(final RxCallBack<T> rxCallBack) {

        return new BaseSubscriber<EntityResponse<T>>() {
            @Override
            public void onSucessed(EntityResponse<T> result) {
                rxCallBack.onSucessed(result.getEntity());
                rxCallBack.handResult(result.getEntity(), result.getBuilder(), true);
            }

            @Override
            public void onError(ResultExceptionUtils.ResponseThrowable e) {
                rxCallBack.onFailed(e);
                rxCallBack.handResult(null, e.getBuilder(), false);
            }
        };
    }


    /**
     * 转换成html5需要的json成功对象
     *
     * @param data
     * @return
     */
    public static JSONObject toResponseSucessed(String httpcode, String data) {

        JSONObject response = new JSONObject();
        try {
            response.put("status", httpcode);
            response.put("data", data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 转换成html5需要的json错误对象
     *
     * @param data
     * @return
     */
    public static JSONObject toResponseFailed(String httpcode, String data) {
        JSONObject response = new JSONObject();
        try {
            response.put("status", httpcode);
            response.put("error", data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return response;
    }


    /**
     * 构建上传的requestBody内容
     *
     * @param filePath
     * @param name
     * @return
     */
    public static RequestBody buildRequestBody(String filePath, String name) {

        try {
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
            URI uri = null;
            uri = new URI(filePath);
            int index = filePath.lastIndexOf('/');
            String filename = filePath.substring(index + 1);
            index = filePath.lastIndexOf('.');
            String ext = filePath.substring(index + 1);

            MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
            String mimeType = mimeTypeMap.getMimeTypeFromExtension(ext);
            builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + name + "\";filename=\"" + filename + "\"")
                    , RequestBody.create(MediaType.parse(mimeType), new File(uri)));
            RequestBody requestBody = builder.build();
            return requestBody;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 线程调度，消费者在主线程，生产者在io线程（新起线程优于Schedulers.newThread()）
     *
     * @return
     */
    public static ObservableTransformer schedulersTransformer() {
        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());

            }
        };
    }

    /**
     * 执行异步
     *
     * @param runnableAsync 线程执行耗时
     * @param runnableSync  耗时成功运行之后主线程执行
     */
    public static void asyncRun(final Runnable runnableAsync, final Runnable runnableSync) {
        Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {
                if (runnableAsync != null)
                    runnableAsync.run();
                e.onNext("sucessed");
            }
        }).compose(schedulersTransformer())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (runnableSync != null)
                            runnableSync.run();
                    }
                });
    }
    /**
     * 执行异步
     *
     * @param runnableAsync 线程执行耗时
     */
    public static void asyncRun(final Runnable runnableAsync) {
        asyncRun(runnableAsync,null);
    }
}
