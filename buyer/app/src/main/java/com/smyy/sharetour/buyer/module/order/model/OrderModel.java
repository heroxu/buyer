package com.smyy.sharetour.buyer.module.order.model;

import com.smyy.sharetour.buyer.module.order.contract.IOrderContract;

public class OrderModel implements IOrderContract.Model {

    @Override
    public boolean deleteOrder(String id) {
        return true;
    }
}
