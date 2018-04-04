package com.smyy.sharetour.buyer.my.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.my.contract.ISettingsContract;
import com.smyy.sharetour.buyer.my.presenter.SettingsPresenter;

public class SettingsActivity extends BaseMvpActivity<SettingsPresenter> implements ISettingsContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_settings;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText(getString(R.string.settings));
        TextView toolbarRightTv = getToolbarRightTv();
        toolbarRightTv.setText(getString(R.string.sign_out));
        toolbarRightTv.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {

    }


    @Override
    protected SettingsPresenter createPresenter() {
        return new SettingsPresenter(this);
    }
}
