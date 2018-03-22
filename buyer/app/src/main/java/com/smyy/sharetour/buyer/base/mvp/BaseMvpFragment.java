package com.smyy.sharetour.buyer.base.mvp;

import android.os.Bundle;

import com.smyy.sharetour.buyer.base.BaseFragment;


/**
 * Created by justin on 17/9/9.
 */

public abstract class BaseMvpFragment<T extends IBasePresenter> extends BaseFragment {

    protected T mPresenter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.bind();
        }
        super.onActivityCreated(savedInstanceState);
    }

    protected abstract T createPresenter();

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.unBind();
        }
        super.onDestroy();
    }

}
