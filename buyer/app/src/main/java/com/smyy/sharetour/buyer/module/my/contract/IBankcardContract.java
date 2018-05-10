package com.smyy.sharetour.buyer.module.my.contract;

import com.smyy.sharetour.buyer.module.my.base.MyBasePresenter;
import com.smyy.sharetour.buyer.module.my.base.MyIBaseView;
import com.smyy.sharetour.buyer.module.my.bean.BankcardBean;
import com.smyy.sharetour.buyer.module.my.bean.ShippingAddressBean;

import java.util.List;

public interface IBankcardContract {
    interface View extends MyIBaseView {
        void showBankcardList(List<BankcardBean> datas);

        void deleteBankcardSuccess();

        void deleteBankcardFail();
    }

    abstract class Presenter extends MyBasePresenter<View, Model> {
        public Presenter(View view, Model model) {
            super(view, model);
        }

        public abstract void getBankcardList();

        public abstract void deleteBankcard(int id);
    }

    interface Model {
        List<BankcardBean> getBankcardList();

        boolean deleteBankcard(int id);

        boolean deleteBankcard(BankcardBean bankcardBean);
    }
}
