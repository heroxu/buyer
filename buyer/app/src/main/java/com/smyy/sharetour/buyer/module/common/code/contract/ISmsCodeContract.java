package com.smyy.sharetour.buyer.module.common.code.contract;

import android.app.Application;

import com.smyy.sharetour.buyer.module.my.base.MyBasePresenter;
import com.smyy.sharetour.buyer.module.my.base.MyIBaseView;

public interface ISmsCodeContract {
    interface View extends MyIBaseView {
        void startCountDown();

        void stopCountDown();

        void verifySmsCodeSuccess();
    }

    abstract class Presenter extends MyBasePresenter<ISmsCodeContract.View, ISmsCodeContract.Model> {
        public Presenter(ISmsCodeContract.View view, ISmsCodeContract.Model model) {
            super(view, model);
        }

        public abstract void getSmsCode(String internationalCode, String phoneNum);

        public abstract void verifySmsCode(String smsCode);
    }

    interface Model {
        boolean getSmsCode(String internationalCode, String phoneNum);

        boolean verifySmsCode(String smsCode);
    }
}
