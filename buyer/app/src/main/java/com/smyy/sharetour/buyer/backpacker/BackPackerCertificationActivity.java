package com.smyy.sharetour.buyer.backpacker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;

import butterknife.BindView;

public class BackPackerCertificationActivity extends BaseMvpActivity {
    public static final String TYPE = "BackPackerCertificationActivity.TYPE";
    public static final String BUNDLE_NAME_TYPE = "BackPackerCertificationActivity.BUNDLE_NAME_TYPE";
    public static final String BUNDLE_PASSPORT_TYPE = "BackPackerCertificationActivity.BUNDLE_PASSPORT_TYPE";
    @BindView(R.id.tv_certification)
    TextView tvCertification;
    @BindView(R.id.tv_certification_name)
    TextView tvCertificationName;
    @BindView(R.id.tv_certification_num)
    TextView tvCertificationNum;
    private String mTitle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_back_packer_certification;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        if (getIntent() != null) {
            String type = getIntent().getStringExtra(TYPE);
            if (BUNDLE_NAME_TYPE.equals(type)) {
                mTitle = "实名认证";
            } else {
                mTitle = "护照认证";
            }
        }
        title.setText(mTitle);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        tvCertification.setText("您已完成" + mTitle);
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

}
