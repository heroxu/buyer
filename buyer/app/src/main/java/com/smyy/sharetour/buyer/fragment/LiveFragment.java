package com.smyy.sharetour.buyer.fragment;

import android.os.Bundle;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;

/**
 * Created by hasee on 2018/3/15.
 */

public class LiveFragment extends BaseMvpFragment {
    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_live;
    }

    @Override
    protected void initData(Bundle bundle) {
        changeTitleBarColor();
    }

    private void changeTitleBarColor() {
        StatusBarAdapter.changeStatusBarColor(getActivity(), getResources().getColor(R.color.white));
    }
}
