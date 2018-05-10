package com.smyy.sharetour.buyer.module.my.contract;

import com.smyy.sharetour.buyer.module.my.base.MyBasePresenter;
import com.smyy.sharetour.buyer.module.my.base.MyIBaseView;
import com.smyy.sharetour.buyer.module.my.bean.ShippingAddressBean;
import com.smyy.sharetour.buyer.module.my.bean.UserInfoBean;

import java.util.List;

public interface IShippingAddressContract {
    interface View extends MyIBaseView {
        void showShippingAddressList(List<ShippingAddressBean> datas);

        void shippingAddressUpdated();

        void showShippingAddress(ShippingAddressBean data);

        void shippingAddressUpdateFail();
    }

    abstract class Presenter extends MyBasePresenter<View, Model> {
        public Presenter(View view, Model model) {
            super(view, model);
        }

        public abstract void getShippingAddressList();

        public abstract void getShippingAddress(int id);

        public abstract void deleteShippingAddress(int id);

        public abstract void setDefault(int id);

        public abstract void updateShippingAddress(int id, ShippingAddressBean shippingAddressBean);

        public abstract void addShippingAddress(ShippingAddressBean shippingAddressBean);
    }

    interface Model {
        List<ShippingAddressBean> getShippingAddressList();

        boolean deleteShippingAddress(int id);
        boolean setDefault(int id);

        ShippingAddressBean getShippingAddress(int id);

        boolean updateShippingAddress(int id, ShippingAddressBean shippingAddressBean);

        boolean addShippingAddress(ShippingAddressBean shippingAddressBean);
    }
}
