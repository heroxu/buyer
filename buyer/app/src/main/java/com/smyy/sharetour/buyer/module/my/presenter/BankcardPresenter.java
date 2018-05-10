package com.smyy.sharetour.buyer.module.my.presenter;

import android.os.SystemClock;

import com.smyy.sharetour.buyer.module.my.bean.BankcardBean;
import com.smyy.sharetour.buyer.module.my.bean.ShippingAddressBean;
import com.smyy.sharetour.buyer.module.my.contract.IBankcardContract;
import com.smyy.sharetour.buyer.module.my.contract.IShippingAddressContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BankcardPresenter extends IBankcardContract.Presenter {
    public BankcardPresenter(IBankcardContract.View view, IBankcardContract.Model model) {
        super(view, model);
    }

    @Override
    public void getBankcardList() {

        Observable.create(new ObservableOnSubscribe<List<BankcardBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<BankcardBean>> e) throws Exception {
                if (mModel != null) {
                    List<BankcardBean> datas = mModel.getBankcardList();
                    e.onNext(datas);
                    e.onComplete();
                }

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<BankcardBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (mView != null) {
                            mView.showProgressDialog();
                        }
                    }

                    @Override
                    public void onNext(List<BankcardBean> datas) {
                        if (mView != null) {
                            mView.showBankcardList(datas);
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
    public void deleteBankcard(final int id) {

        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                if (mModel != null) {
                    SystemClock.sleep(1000);
                    boolean result = mModel.deleteBankcard(id);
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
                            mView.showProgressDialog("请稍后...");
                        }
                    }

                    @Override
                    public void onNext(Boolean result) {
                        if (mView != null) {
                            if (result) {
                                mView.deleteBankcardSuccess();
                            } else {
                                mView.deleteBankcardFail();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        if (mView != null) {
                        }
                    }
                });
    }

    public void deleteBankcard(final BankcardBean bankcardBean) {

        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                if (mModel != null) {
                    SystemClock.sleep(1000);
                    boolean result = mModel.deleteBankcard(bankcardBean);
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
                            mView.showProgressDialog("请稍后...");
                        }
                    }

                    @Override
                    public void onNext(Boolean result) {
                        if (mView != null) {
                            if (result) {
                                mView.deleteBankcardSuccess();
                            } else {
                                mView.deleteBankcardFail();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        if (mView != null) {
                        }
                    }
                });
    }
}
