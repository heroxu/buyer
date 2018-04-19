package com.smyy.sharetour.buyer.module.my.presenter;

import android.os.SystemClock;

import com.smyy.sharetour.buyer.module.my.bean.ShippingAddressBean;
import com.smyy.sharetour.buyer.module.my.bean.UserInfoBean;
import com.smyy.sharetour.buyer.module.my.contract.IShippingAddressContract;
import com.smyy.sharetour.buyer.module.my.contract.IUserContract;

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
                            mView.showShippingAddress(datas);
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
