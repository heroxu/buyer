package com.smyy.sharetour.buyer.seller;

import android.os.Bundle;
import android.view.View;

import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;

import butterknife.OnClick;


public class HomeMenuFragment2 extends BaseMvpFragment {

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_seller_menu2;
    }

    @Override
    protected void initData(Bundle bundle) {
    }

    @OnClick({R.id.lay_home_my_favorite, R.id.lay_home_my_comment})
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt(Consts.USER_TYPE, Consts.USER_TYPE_SELLER);

        switch (view.getId()) {

            case R.id.lay_home_my_favorite:

                break;

            case R.id.lay_home_my_comment:

                break;


            default:
                break;
        }
    }
}
