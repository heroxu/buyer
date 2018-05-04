package com.smyy.sharetour.buyer.module.my.contract;

import com.smyy.sharetour.buyer.module.my.base.MyBasePresenter;
import com.smyy.sharetour.buyer.module.my.base.MyIBaseView;
import com.smyy.sharetour.buyer.module.my.bean.UserInfoBean;

import java.util.List;

public interface IOrderContract {
    interface View extends MyIBaseView {
    }

    abstract class Presenter extends MyBasePresenter<View, Model> {
        public Presenter(View view, Model model) {
            super(view, model);
        }
    }

    interface Model {
    }
}
