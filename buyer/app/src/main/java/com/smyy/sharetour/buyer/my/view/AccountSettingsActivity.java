package com.smyy.sharetour.buyer.my.view;

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
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.my.model.UserInfo;
import com.smyy.sharetour.buyer.util.ActivityLauncher;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

import static com.smyy.sharetour.buyer.base.BaseApplication.getContext;

public class AccountSettingsActivity extends BaseMvpActivity {
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
        UserInfo mUserInfo = MyApplication.getApplication().getUserInfo();

        tvNickname.setText(mUserInfo.getUsername());
        tvUserIntro.setText(mUserInfo.getUserIntro());

        String filePath = mUserInfo.getAvatar();
        if (TextUtils.isEmpty(filePath)) {
            Glide.with(this).load(R.mipmap.user_avatar).into(ivAvatar);
        } else {
            File file = new File(filePath);
            Glide.with(getContext()).load(file).into(ivAvatar);
        }
    }

    @OnClick({R.id.tv_my_avatar_item, R.id.tv_my_nickname_item, R.id.tv_my_user_intro_item,
            R.id.tv_my_shipping_address, R.id.tv_my_security_center})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_my_avatar_item:

                break;

            case R.id.tv_my_nickname_item:
                ActivityLauncher.viewActivity(this, EditNicknameActivity.class);
                break;

            case R.id.tv_my_user_intro_item:
                ActivityLauncher.viewActivity(this, EditUserIntroActivity.class);
                break;

            case R.id.tv_my_shipping_address:

                break;

            case R.id.tv_my_security_center:

                break;

            default:
                break;
        }
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }
}
