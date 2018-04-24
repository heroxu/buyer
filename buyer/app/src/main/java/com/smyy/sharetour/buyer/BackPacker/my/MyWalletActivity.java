package com.smyy.sharetour.buyer.BackPacker.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;

public class MyWalletActivity extends BaseMvpActivity {
    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_wallet;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {

    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {

    }
}
