package com.smyy.sharetour.buyer.module.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.util.FragmentUtil;

public class DisputeOrderListActivity extends BaseMvpActivity {
    private int mUserType;

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_dispute_list;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("退货退款");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        Bundle bundle = getBundle();
        if (bundle != null) {
            mUserType = bundle.getInt(Consts.USER_TYPE);
        }
        DisputeOrderListFragment disputeOrderFragment = DisputeOrderListFragment.getInstance(mUserType);
        FragmentUtil.addFragment(getSupportFragmentManager(), disputeOrderFragment, R.id.main_content);
    }
}
