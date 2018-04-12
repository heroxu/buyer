package com.smyy.sharetour.buyer.my.base;


import com.smyy.sharetour.uiframelib.BaseActivity;


public abstract class MyBaseMvpActivity<T extends MyBasePresenter> extends BaseActivity {

    protected T mPresenter;

    @Override
    protected void beforeInitData() {
        super.beforeInitData();
        mPresenter = createPresenter();
    }

    protected abstract T createPresenter();

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.unBind();
        }
        super.onDestroy();
    }
}
