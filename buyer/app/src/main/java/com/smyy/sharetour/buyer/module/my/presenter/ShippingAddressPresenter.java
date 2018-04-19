package com.smyy.sharetour.buyer.module.my.presenter;

import com.smyy.sharetour.buyer.module.my.bean.ShippingAddressBean;
import com.smyy.sharetour.buyer.module.my.contract.IShippingAddressContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShippingAddressPresenter extends IShippingAddressContract.Presenter {
    public ShippingAddressPresenter(IShippingAddressContract.View view, IShippingAddressContract.Model model) {
        super(view, model);
    }

    @Override
    public void getShippingAddressList() {

        Observable.create(new ObservableOnSubscribe<List<ShippingAddressBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ShippingAddressBean>> e) throws Exception {
                if (mModel != null) {
//                    SystemClock.sleep(1000);
                    List<ShippingAddressBean> datas = mModel.getShippingAddressList();
                    e.onNext(datas);
                    e.onComplete();
                }

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ShippingAddressBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (mView != null) {
                            mView.showProgressDialog();
                        }
                    }

                    @Override
                    public void onNext(List<ShippingAddressBean> datas) {
                        if (mView != null) {
                            mView.showShippingAddressList(datas);
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
    public void getShippingAddress(final int id) {

        Observable.create(new ObservableOnSubscribe<ShippingAddressBean>() {
            @Override
            public void subscribe(ObservableEmitter<ShippingAddressBean> e) throws Exception {
                if (mModel != null) {
//                    SystemClock.sleep(1000);
                    ShippingAddressBean data = mModel.getShippingAddress(id);
                    e.onNext(data);
                    e.onComplete();
                }

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShippingAddressBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (mView != null) {
                            mView.showProgressDialog();
                        }
                    }

                    @Override
                    public void onNext(ShippingAddressBean data) {
                        if (mView != null) {
                            mView.showShippingAddress(data);
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
    public void deleteShippingAddress(final int id) {

        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                if (mModel != null) {
                    boolean result = mModel.deleteShippingAddress(id);
                    e.onNext(result);
                    e.onComplete();
                }

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Boolean result) {
                        if (mView != null) {
                            //
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
    public void setDefault(final int id) {

        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                if (mModel != null) {
                    boolean result = mModel.setDefault(id);
                    e.onNext(result);
                    e.onComplete();
                }

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Boolean result) {
                        if (mView != null && result) {
                            mView.shippingAddressUpdated();
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
    public void updateShippingAddress(final int id, final ShippingAddressBean shippingAddressBean) {

        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                if (mModel != null) {
                    boolean result = mModel.updateShippingAddress(id, shippingAddressBean);
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
                        if (mView != null) {
                            if (result) {
                                mView.shippingAddressUpdated();
                            } else {
                                mView.shippingAddressUpdateFail();
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

    @Override
    public void addShippingAddress(final ShippingAddressBean shippingAddressBean) {

        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                if (mModel != null) {
                    boolean result = mModel.addShippingAddress(shippingAddressBean);
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
                        if (mView != null) {
                            if (result) {
                                mView.shippingAddressUpdated();
                            } else {
                                mView.shippingAddressUpdateFail();
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
