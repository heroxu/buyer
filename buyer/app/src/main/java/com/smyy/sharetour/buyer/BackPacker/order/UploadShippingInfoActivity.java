package com.smyy.sharetour.buyer.BackPacker.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;

import butterknife.OnClick;

public class UploadShippingInfoActivity extends BaseMvpActivity {
    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_upload_shipping_info;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("填写物流信息");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {

    }

    @OnClick({R.id.btn_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_confirm:
                finish();
                break;

            default:
                break;
        }
    }
}

