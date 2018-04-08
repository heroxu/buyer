package com.smyy.sharetour.buyer.my.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.util.ActivityLauncher;
import com.smyy.sharetour.uiframelib.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AccountSettingsActivity extends BaseActivity {
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


    @OnClick({R.id.tv_my_avatar_item, R.id.tv_my_nickname_item, R.id.tv_my_user_intro_item,
            R.id.tv_my_user_intro, R.id.tv_my_shipping_address})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_my_avatar_item:

                break;

            case R.id.tv_my_nickname_item:
                ActivityLauncher.viewActivity(this,EditNicknameActivity.class);
                break;

            case R.id.tv_my_user_intro_item:
                ActivityLauncher.viewActivity(this,EditUserIntroActivity.class);
                break;

            case R.id.tv_my_shipping_address:

                break;

            case R.id.tv_my_security_center:

                break;

            default:
                break;
        }
    }
}
