package com.smyy.sharetour.buyer.module.order;

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

public class ShippingInfoActivity extends BaseMvpActivity {
    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shipping_info;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("物流信息");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {

    }

    @OnClick({R.id.tv_order_contact_service})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tv_order_contact_service:
                OrderHelper.switchOperate(this, OrderHelper.OPERATE_CONTACT_SERVICE);
                break;

            default:
                break;
        }
    }
}
