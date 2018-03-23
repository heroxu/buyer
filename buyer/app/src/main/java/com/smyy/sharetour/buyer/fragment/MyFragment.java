package com.smyy.sharetour.buyer.fragment;

import android.os.Bundle;
import android.view.View;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.view.RedImageView;

import butterknife.BindView;

/**
 * Created by hasee on 2018/3/15.
 */

public class MyFragment extends BaseMvpFragment {
    @BindView(R.id.fake_status_bar)
    View fake_status_bar;

    @BindView(R.id.icon)
    RedImageView icon;

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_my;
    }

    @Override
    protected void initData(Bundle bundle) {
        //处理状态栏
        StatusBarAdapter.updateStatusHeight(getActivity(), fake_status_bar, null);
        changeTitleBarColor();
        icon.setText("5");
        icon.setRedPointVisible(View.VISIBLE);
    }

    private void changeTitleBarColor() {
        StatusBarAdapter.changeStatusBarColor(getActivity(), getResources().getColor(R.color.colorAccent));
    }
}
