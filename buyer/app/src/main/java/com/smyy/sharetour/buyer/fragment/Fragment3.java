package com.smyy.sharetour.buyer.fragment;

import android.os.Bundle;
import android.view.View;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;

import butterknife.BindView;

/**
 * Created by hasee on 2018/3/15.
 */

public class Fragment3 extends BaseMvpFragment {
    @BindView(R.id.fake_status_bar)
    View fake_status_bar;
    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment3;
    }

    @Override
    protected void initData(Bundle bundle) {
        //处理状态栏
        StatusBarAdapter.updateStatusHeight(getActivity(), fake_status_bar, null);
        changeTitleBarColor();
    }

    private void changeTitleBarColor() {
        StatusBarAdapter.changeStatusBarColor(getActivity(), getResources().getColor(R.color.colorAccent));
    }
}
