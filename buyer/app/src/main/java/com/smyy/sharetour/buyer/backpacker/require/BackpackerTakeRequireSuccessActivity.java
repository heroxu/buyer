package com.smyy.sharetour.buyer.backpacker.require;

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

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Liliping
 * @org www.smyy.com
 * @email lilp@stjf.com
 * @package com.smyy.sharetour.buyer.BackPacker.Require
 * @fileName BackpackerTakeRequireSuccessActivity
 * @date on 2018/4/23 0023 10:14
 * @describe 背包客接需求成功
 */
public class BackpackerTakeRequireSuccessActivity extends BaseMvpActivity {

    @BindView(R.id.tv_buyer_name)
    TextView tvBuyerName;
    @BindView(R.id.tv_buyer_phone)
    TextView tvBuyerPhone;
    @BindView(R.id.tv_buyer_shipping_address)
    TextView tvBuyerShippingAddress;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_backpacke_submit_success_layout;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText(R.string.submit_application_success);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
    }


    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }


    @OnClick({R.id.view_require, R.id.return_backpacker_home})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.view_require:
                startActivity(new Intent(BackpackerTakeRequireSuccessActivity.this, BackPackerRequireListActivity.class));
                finish();
                break;
            case R.id.return_backpacker_home:
                ActivityLauncher.viewBackpackerHomeActivity(BackpackerTakeRequireSuccessActivity.this);
                finish();
                break;
        }
    }
}
