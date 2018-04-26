package com.smyy.sharetour.buyer.backpacker.payPwd;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.util.ActivityLauncher;

import butterknife.OnClick;

public class BackpackSettingActivity extends BaseMvpActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_backpack_setting;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("管理");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {

    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @OnClick({R.id.pay_pwd, R.id.user_management})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pay_pwd:
                ActivityLauncher.viewBackpackSetPayPwdActivity(BackpackSettingActivity.this);
                break;
            case R.id.user_management:

                break;
        }
    }
}
