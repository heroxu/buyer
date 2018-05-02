package com.smyy.sharetour.buyer.backpacker;

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

public class BackPackerCertificationListActivity extends BaseMvpActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_back_packer_certification_list;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("认证信息");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {

    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }


    @OnClick({R.id.r_btn_name, R.id.r_btn_ali, R.id.r_btn_passport})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.r_btn_name:
                ActivityLauncher.viewBackPackerCertificationActivity(BackPackerCertificationListActivity.this,BackPackerCertificationActivity.BUNDLE_NAME_TYPE);
                break;
            case R.id.r_btn_ali:
                ActivityLauncher.viewALIPointsActivity(BackPackerCertificationListActivity.this);
                break;
            case R.id.r_btn_passport:
                ActivityLauncher.viewBackPackerCertificationActivity(BackPackerCertificationListActivity.this,BackPackerCertificationActivity.BUNDLE_PASSPORT_TYPE);
                break;
        }
    }
}
