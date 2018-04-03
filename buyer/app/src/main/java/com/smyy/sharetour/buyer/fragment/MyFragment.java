package com.smyy.sharetour.buyer.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smyy.sharetour.buyer.Constants;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.my.model.UserInfo;
import com.smyy.sharetour.buyer.util.SharePreferenceUtil;
import com.smyy.sharetour.buyer.view.RedImageView;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hasee on 2018/3/15.
 */

public class MyFragment extends BaseMvpFragment {
    @BindView(R.id.tv_my_username)
    TextView tvUsername;
    @BindView(R.id.tv_my_user_intro)
    TextView tvUserIntro;
    @BindView(R.id.iv_my_avatar)
    ImageView ivAvatar;
    @BindView(R.id.riv_my_awaiting_payment)
    RedImageView rivAwaitingPayment;
    @BindView(R.id.riv_my_awaiting_shipment)
    RedImageView rivAwaitingShipment;
    @BindView(R.id.riv_my_awaiting_confirmation)
    RedImageView rivAwaitingConfirmation;
    @BindView(R.id.riv_my_dispute)
    RedImageView rivDispute;
    private UserInfo mUserInfo;


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
        changeTitleBarColor();
        initUserInfo();
    }

    private void initUserInfo() {
        SharePreferenceUtil sp = new SharePreferenceUtil(getContext(), Constants.MY_SP);
        mUserInfo = sp.getBeanValue(Constants.MY_SP_USER_INFO, UserInfo.class);
        if (mUserInfo == null) {
            mUserInfo = new UserInfo("悠闲的伪牧师", "一只大榴莲，两梳大香蕉。", "",
                    0, 0, 0, 0);
        }

        tvUsername.setText(mUserInfo.getUsername());
        tvUserIntro.setText(mUserInfo.getUserIntro());

        String filePath = mUserInfo.getAvatar();
        if (TextUtils.isEmpty(filePath)) {
            Glide.with(this).load(R.mipmap.user_avatar).into(ivAvatar);
        } else {
            File file = new File(filePath);
            Glide.with(getContext()).load(file).into(ivAvatar);
        }
        sp.writeBeanValue(Constants.MY_SP_USER_INFO, mUserInfo);
    }

    private void changeTitleBarColor() {
        StatusBarAdapter.changeStatusBarColor(getActivity(), getResources().getColor(R.color.transparent));
    }

    @OnClick({R.id.iv_my_setting, R.id.iv_my_msg, R.id.iv_my_edit_username, R.id.iv_my_avatar,
            R.id.tv_my_view_all_orders, R.id.lay_my_awaiting_payment, R.id.lay_my_awaiting_shipment, R.id.lay_my_awaiting_confirmation, R.id.lay_my_dispute,
            R.id.tv_my_cart, R.id.tv_my_demands, R.id.tv_my_reviews, R.id.tv_my_favorites, R.id.tv_my_interests, R.id.tv_help_and_feedback})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.iv_my_setting:

                break;

            case R.id.iv_my_msg:

                break;

            case R.id.iv_my_edit_username:

                break;

            case R.id.iv_my_avatar:

                break;

            case R.id.tv_my_view_all_orders:

                break;

            case R.id.lay_my_awaiting_payment:

                break;

            case R.id.lay_my_awaiting_shipment:

                break;

            case R.id.lay_my_awaiting_confirmation:

                break;

            case R.id.lay_my_dispute:

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
