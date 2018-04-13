package com.smyy.sharetour.buyer.my.presenter;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.my.contract.ISettingsContract;
import com.smyy.sharetour.buyer.my.contract.IUserContract;
import com.smyy.sharetour.buyer.util.CacheUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by simt180321 on 2018/4/12.
 */

public class SettingsPresenter extends ISettingsContract.Presenter {
    public SettingsPresenter(ISettingsContract.View view, ISettingsContract.Model model) {
        super(view, model);
    }

    @Override
    public void getCacheSize(final Application application) {
//TODO RTRT 后期进行封装
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                if (mModel != null) {
                    String cacheSize = mModel.getCacheSize(application);
                    e.onNext(cacheSize);
                    e.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String s) {
                        if (mView != null && !TextUtils.isEmpty(s)) {
                            mView.showCacheSize(s);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void clearCache(final Application application) {
        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                if (mModel != null) {
                    boolean result = mModel.clearCache(application);
                    e.onNext(result);
                    e.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (mView != null) {
                            mView.showProgressDialog();
                        }
                    }

                    @Override
                    public void onNext(Boolean result) {
                        if (result) {
                            mView.showToast(R.string.have_been_cleared);
                            getCacheSize(application);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        if (mView != null) {
                            mView.hideProgressDialog();
                        }
                    }
                });
    }
}
