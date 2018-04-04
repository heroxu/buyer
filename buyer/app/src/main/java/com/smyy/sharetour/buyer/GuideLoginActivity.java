package com.smyy.sharetour.buyer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.util.ActivityLauncher;

import butterknife.OnClick;

public class GuideLoginActivity extends BaseMvpActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide_login;
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


    @OnClick({R.id.ll_glogin_close, R.id.btn_glogin_register, R.id.btn_glogin_login, R.id.ll_glogin_wechat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_glogin_close:
                finish();
                break;
            case R.id.btn_glogin_register:
                ActivityLauncher.viewRegisterActivity(this);
                break;
            case R.id.btn_glogin_login:
                ActivityLauncher.viewLoginActivity(this);
                break;
            case R.id.ll_glogin_wechat:
                break;
        }
    }
}
