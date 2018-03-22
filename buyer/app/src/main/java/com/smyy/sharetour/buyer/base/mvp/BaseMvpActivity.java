package com.smyy.sharetour.buyer.base.mvp;


import com.smyy.sharetour.uiframelib.BaseActivity;

/**
 * Created by justin on 17/9/9.
 */

public abstract class BaseMvpActivity<T extends IBasePresenter> extends BaseActivity {

    protected T mPresenter;

    @Override
    protected void beforeInitData() {
        super.beforeInitData();
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.bind();
        }
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
