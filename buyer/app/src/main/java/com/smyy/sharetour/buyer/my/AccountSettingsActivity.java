package com.smyy.sharetour.buyer.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smyy.sharetour.buyer.MyApplication;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.my.base.MyBaseMvpActivity;
import com.smyy.sharetour.buyer.my.bean.UserInfoBean;
import com.smyy.sharetour.buyer.my.contract.IUserContract;
import com.smyy.sharetour.buyer.my.model.UserModel;
import com.smyy.sharetour.buyer.my.presenter.UserPresenter;
import com.smyy.sharetour.buyer.util.ActivityLauncher;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

import static com.smyy.sharetour.buyer.base.BaseApplication.getContext;

public class AccountSettingsActivity extends MyBaseMvpActivity<UserPresenter> implements IUserContract.View {
    @BindView(R.id.iv_my_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_my_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_my_user_intro)
    TextView tvUserIntro;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_account_settings;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText(getString(R.string.account_settings));
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {

    }


    @Override
    public void onResume() {
        super.onResume();
        initUserInfo();
    }

    private void initUserInfo() {
        mPresenter.getUserInfoCache();
    }

    @OnClick({R.id.lay_my_avatar_item, R.id.lay_my_nickname, R.id.lay_my_user_intro,
            R.id.tv_my_shipping_address, R.id.tv_my_security_center})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.lay_my_avatar_item:

                break;

            case R.id.lay_my_nickname:
                ActivityLauncher.viewActivity(this, EditNicknameActivity.class);
                break;

            case R.id.lay_my_user_intro:
                ActivityLauncher.viewActivity(this, EditUserIntroActivity.class);
                break;

            case R.id.tv_my_shipping_address:

                break;

            case R.id.tv_my_security_center:
                ActivityLauncher.viewActivity(this, SecurityCenterActivity.class);
                break;

            default:
                break;
        }
    }

    @Override
    protected UserPresenter createPresenter() {
        return new UserPresenter(this,new UserModel());
    }

    @Override
    public void showUserInfo(UserInfoBean userInfo) {
        String username = userInfo.getUsername();
        if (!TextUtils.isEmpty(username)) {
            tvNickname.setText(username);
        }
        String userIntro = userInfo.getUserIntro();
        if (!TextUtils.isEmpty(userIntro)) {
            tvUserIntro.setText(userIntro);
        } else {
            tvUserIntro.setText(R.string.please_introduce_yourself);
        }

        String filePath = userInfo.getAvatar();
        if (TextUtils.isEmpty(filePath)) {
            Glide.with(this).load(R.mipmap.user_avatar).into(ivAvatar);
        } else {
            File file = new File(filePath);
            Glide.with(getContext()).load(file).into(ivAvatar);
        }
    }

    @Override
    public void showDialog(String s) {

    }
}
