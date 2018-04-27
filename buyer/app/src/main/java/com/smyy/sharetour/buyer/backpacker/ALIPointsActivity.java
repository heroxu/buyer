package com.smyy.sharetour.buyer.backpacker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;

public class ALIPointsActivity extends BaseMvpActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_alipoints;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("芝麻信用");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {

    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }
}
