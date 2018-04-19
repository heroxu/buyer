package com.smyy.sharetour.buyer;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;

public class BuyHomePageActivity extends BaseMvpActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_buy_home_page;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {

    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        hideToolBarLayout(true);
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }
}
