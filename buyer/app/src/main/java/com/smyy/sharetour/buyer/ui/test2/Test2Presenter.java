package com.smyy.sharetour.buyer.ui.test2;

import com.smyy.sharetour.buyer.base.mvp.BasePresenter;
import com.smyy.sharetour.buyer.network.rx.ResultExceptionUtils;
import com.smyy.sharetour.buyer.network.rx.RxCallBack;

/**
 * Created by 伍振飞 on 2018/3/16 10:20
 * E-Mail Address：wuzf2012@sina.com
 */
public class Test2Presenter extends BasePresenter<ITest2Contract.View> implements ITest2Contract.Presenter {
    private ITest2Contract.Model model;
    public Test2Presenter(ITest2Contract.View baseView) {
        super(baseView);
        this.model = new Test2Model();

    }

    @Override
    public void bind() {

    }

    @Override
    public void getData() {
        model.getData(new RxCallBack<Test2JsonData>() {
            @Override
            public void onSucessed(Test2JsonData data) {
                getView().getDataSuccess(data);
            }

            @Override
            public void onFailed(ResultExceptionUtils.ResponseThrowable e) {
                getView().getDataFail(e.getMessage());
            }
        });
    }
}
