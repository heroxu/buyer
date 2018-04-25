package com.smyy.sharetour.buyer.seller;

import android.graphics.Color;
import android.os.Bundle;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;


public class HomeMenuFragment2 extends BaseMvpFragment {

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
    }

    @Override
    protected void initStatusBar() {
        setStatusBar(Color.BLACK);
    }


}
