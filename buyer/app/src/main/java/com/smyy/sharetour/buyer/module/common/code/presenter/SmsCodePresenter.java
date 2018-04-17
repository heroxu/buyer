package com.smyy.sharetour.buyer.module.common.code.presenter;

import android.os.SystemClock;

import com.smyy.sharetour.buyer.module.common.code.contract.ISmsCodeContract;
import com.smyy.sharetour.buyer.module.my.bean.UserInfoBean;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SmsCodePresenter extends ISmsCodeContract.Presenter {
    public SmsCodePresenter(ISmsCodeContract.View view, ISmsCodeContract.Model model) {
        super(view, model);
    }

    @Override
    public void getSmsCode(final String internationalCode, final String phoneNum) {

        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                if (mModel != null) {
                    SystemClock.sleep(1000);
                    e.onNext(mModel.getSmsCode(internationalCode, phoneNum));
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
                            mView.startCountDown();
                        }
                    }

                    @Override
                    public void onNext(Boolean result) {
                        if (mView != null) {
                            if (!result) {
                                mView.showToast("验证码获取失败");
                                mView.stopCountDown();
                            }
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

    @Override
    public void verifySmsCode(final String smsCode) {

        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                if (mModel != null) {
                    SystemClock.sleep(1000);
                    e.onNext(mModel.verifySmsCode(smsCode));
                    e.onComplete();
                }

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (mView != null) {
                            mView.showProgressDialog("绑定中");
                        }
                    }

                    @Override
                    public void onNext(Boolean result) {
                        if (mView != null) {
                            if (result) {
                                mView.verifySmsCodeSuccess();
                            } else {
                                mView.verifySmsCodeFail();
                            }
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
}
