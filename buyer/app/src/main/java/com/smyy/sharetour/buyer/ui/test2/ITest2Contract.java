package com.smyy.sharetour.buyer.ui.test2;

import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.base.mvp.IBaseView;
import com.smyy.sharetour.buyer.network.rx.RxCallBack;

import io.reactivex.annotations.NonNull;

/**
 * Created by 伍振飞 on 2018/3/16 09:30
 * E-Mail Address：wuzf2012@sina.com
 */
public interface ITest2Contract {
    interface View extends IBaseView {
        void getDataSuccess(Test2JsonData data);
        void getDataFail(String message);
    }

    interface Presenter extends IBasePresenter {
        void getData();
    }
    interface Model{
        void getData(@NonNull final RxCallBack<Test2JsonData> rxCallBack);
    }
}
