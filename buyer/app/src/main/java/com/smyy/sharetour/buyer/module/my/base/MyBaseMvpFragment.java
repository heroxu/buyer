package com.smyy.sharetour.buyer.module.my.base;

import android.os.Bundle;

import com.smyy.sharetour.buyer.base.BaseFragment;
import com.smyy.sharetour.buyer.util.ToastUtils;


/**
 * Created by justin on 17/9/9.
 */

public abstract class MyBaseMvpFragment<T extends MyBasePresenter> extends BaseFragment {

    protected T mPresenter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        mPresenter = createPresenter();
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

    public void showToast(String s) {
        ToastUtils.showToast(s);
    }

    public void showToast(int strRes) {
        ToastUtils.showToast(strRes);
    }

}
