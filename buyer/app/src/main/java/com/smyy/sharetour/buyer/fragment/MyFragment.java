package com.smyy.sharetour.buyer.fragment;

import android.os.Bundle;
import android.view.View;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.view.RedImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hasee on 2018/3/15.
 */

public class MyFragment extends BaseMvpFragment {
    @BindView(R.id.fake_status_bar)
    View fake_status_bar;

    @BindView(R.id.icon)
    RedImageView icon;

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_my;
    }

    @Override
    protected void initData(Bundle bundle) {
        //处理状态栏
        StatusBarAdapter.updateStatusHeight(getActivity(), fake_status_bar, null);
        changeTitleBarColor();
        icon.setText("5");
        icon.setRedPointVisible(View.VISIBLE);
    }

    private void changeTitleBarColor() {
        StatusBarAdapter.changeStatusBarColor(getActivity(), getResources().getColor(R.color.transparent));
    }

    @OnClick({ R.id.iv_setting, R.id.iv_msg, R.id.iv_edit_username, R.id.iv_user_avatar,
            R.id.tv_view_all_orders, R.id.lay_awaiting_payment, R.id.lay_awaiting_shipment, R.id.lay_awaiting_confirmation, R.id.lay_dispute,
            R.id.tv_my_cart ,R.id.tv_my_demands ,R.id.tv_my_reviews ,R.id.tv_my_favorites ,R.id.tv_my_interests ,R.id.tv_help_and_feedback})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.iv_setting:

                break;

            case R.id.iv_msg:

                break;

            case R.id.iv_edit_username:

                break;

            case R.id.iv_user_avatar:

                break;

            case R.id.tv_view_all_orders:

                break;

            case R.id.lay_awaiting_payment:

                break;

            case R.id.lay_awaiting_shipment:

                break;

            case R.id.lay_awaiting_confirmation:

                break;

            case R.id.lay_dispute:

                break;

            case R.id.tv_my_cart:

                break;

            case R.id.tv_my_demands:

                break;

            case R.id.tv_my_reviews:

                break;

            case R.id.tv_my_favorites:

                break;

            case R.id.tv_my_interests:

                break;

            case R.id.tv_help_and_feedback:

                break;

            default:
                break;
        }
    }

}
