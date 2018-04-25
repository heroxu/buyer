package com.smyy.sharetour.buyer.backpacker.certification;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.util.ActivityLauncher;

import butterknife.OnClick;

public class ALiCertificationActivity extends BaseMvpActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ali_certification;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("认证");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {

    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @OnClick(R.id.btn_next)
    public void onViewClicked() {
        ActivityLauncher.viewALiCertificationFinishActivity(this);
    }
}
