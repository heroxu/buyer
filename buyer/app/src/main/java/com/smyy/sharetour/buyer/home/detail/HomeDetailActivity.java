package com.smyy.sharetour.buyer.home.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;

/**
 * create by xuxiarong on $DATA$
 */
public class HomeDetailActivity extends BaseMvpActivity {
    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_detail;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        toolbar.setTitle("商品详情");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {

    }
}
