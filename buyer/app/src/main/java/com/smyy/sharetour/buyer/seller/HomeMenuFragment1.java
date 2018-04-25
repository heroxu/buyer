package com.smyy.sharetour.buyer.seller;

import android.os.Bundle;
import android.view.View;

import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.module.order.OrderListActivity;

import butterknife.OnClick;


public class HomeMenuFragment1 extends BaseMvpFragment {

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_seller_menu1;
    }

    @Override
    protected void initData(Bundle bundle) {
    }

    @OnClick({R.id.lay_home_my_goods, R.id.lay_home_my_presale, R.id.lay_home_my_travel, R.id.lay_home_my_order,
            R.id.lay_home_buyer_demand, R.id.lay_home_my_dynamic, R.id.lay_home_my_wallet, R.id.lay_home_my_live})
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt(Consts.USER_TYPE, Consts.USER_TYPE_SELLER);

        switch (view.getId()) {

            case R.id.lay_home_my_goods:

                break;

            case R.id.lay_home_my_presale:

                break;

            case R.id.lay_home_my_travel:

                break;

            case R.id.lay_home_my_order:
                startActivity(OrderListActivity.class, bundle);
                break;

            case R.id.lay_home_buyer_demand:

                break;

            case R.id.lay_home_my_dynamic:

                break;

            case R.id.lay_home_my_wallet:

                break;

            case R.id.lay_home_my_live:

                break;


            default:
                break;
        }
    }
}
