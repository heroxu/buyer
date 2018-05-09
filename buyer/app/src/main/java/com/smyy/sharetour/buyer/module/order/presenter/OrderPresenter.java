package com.smyy.sharetour.buyer.module.order.presenter;

import com.smyy.sharetour.buyer.module.order.contract.IOrderContract;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OrderPresenter extends IOrderContract.Presenter {
    public OrderPresenter(IOrderContract.View view, IOrderContract.Model model) {
        super(view, model);
    }

    @Override
    public void deleteOrder(final int position, final String id) {

        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                if (mModel != null) {
//                    SystemClock.sleep(1000);
                    boolean result = mModel.deleteOrder(id);
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
                            mView.showProgressDialog("删除中");
                        }
                    }

                    @Override
                    public void onNext(Boolean result) {
                        if (mView != null) {
                            mView.deleteOrder(position);
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
    public void pay(final String id) {

        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                if (mModel != null) {
//                    SystemClock.sleep(1000);
                    boolean result = mModel.pay(id);
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
                            mView.showProgressDialog("支付中");
                        }
                    }

                    @Override
                    public void onNext(Boolean result) {
                        if (mView != null) {
                            if (result) {
                                mView.showToast("支付成功");
                                mView.updateOrderDetail(id);
                            } else {
                                mView.showToast("支付失败");
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
    public void confirm(final String id) {

        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                if (mModel != null) {
//                    SystemClock.sleep(1000);
                    boolean result = mModel.confirm(id);
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
                            mView.showProgressDialog("确认收货中");
                        }
                    }

                    @Override
                    public void onNext(Boolean result) {
                        if (mView != null) {
                            if (result) {
                                mView.showToast("交易成功");
                                mView.updateOrderDetail(id);
                            } else {
                                mView.showToast("确认收货失败");
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
}
