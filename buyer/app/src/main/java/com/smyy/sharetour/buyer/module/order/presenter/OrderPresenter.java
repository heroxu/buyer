package com.smyy.sharetour.buyer.module.order.presenter;

import com.smyy.sharetour.buyer.module.my.bean.UserInfoBean;
import com.smyy.sharetour.buyer.module.my.contract.IOrderContract;
import com.smyy.sharetour.buyer.module.my.contract.IUserContract;

import java.util.List;

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
}
