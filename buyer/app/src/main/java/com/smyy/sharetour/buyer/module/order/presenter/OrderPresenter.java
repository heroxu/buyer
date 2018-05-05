package com.smyy.sharetour.buyer.module.order.presenter;

import com.smyy.sharetour.buyer.module.order.contract.IOrderContract;

public class OrderPresenter extends IOrderContract.Presenter {
    public OrderPresenter(IOrderContract.View view, IOrderContract.Model model) {
        super(view, model);
    }
}
