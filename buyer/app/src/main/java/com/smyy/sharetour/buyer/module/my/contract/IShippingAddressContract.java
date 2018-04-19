package com.smyy.sharetour.buyer.module.my.contract;

import com.smyy.sharetour.buyer.module.my.base.MyBasePresenter;
import com.smyy.sharetour.buyer.module.my.base.MyIBaseView;
import com.smyy.sharetour.buyer.module.my.bean.ShippingAddressBean;
import com.smyy.sharetour.buyer.module.my.bean.UserInfoBean;

import java.util.List;

public interface IShippingAddressContract {
    interface View extends MyIBaseView {
        void showShippingAddress(List<ShippingAddressBean> datas);

        void shippingAddressUndated();
    }

    abstract class Presenter extends MyBasePresenter<View, Model> {
        public Presenter(View view, Model model) {
            super(view, model);
        }

        public abstract void getShippingAddressList();
    }

    interface Model {
        List<ShippingAddressBean> getShippingAddressList();

        boolean deleteShippingAddress(int position);
        boolean setDefault(int position);
    }
}
