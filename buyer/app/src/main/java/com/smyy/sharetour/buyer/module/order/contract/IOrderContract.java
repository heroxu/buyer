package com.smyy.sharetour.buyer.module.order.contract;

import com.smyy.sharetour.buyer.module.my.base.MyBasePresenter;
import com.smyy.sharetour.buyer.module.my.base.MyIBaseView;

public interface IOrderContract {
    interface View extends MyIBaseView {
        void deleteOrder(int position);
    }

    abstract class Presenter extends MyBasePresenter<View, Model> {
        public Presenter(View view, Model model) {
            super(view, model);
        }

        public abstract void deleteOrder(int position, String id);
    }

    interface Model {
        boolean deleteOrder(String id);
    }
}
