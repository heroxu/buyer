package com.smyy.sharetour.buyer.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.my.base.MyBaseMvpActivity;
import com.smyy.sharetour.buyer.my.bean.UserInfoBean;
import com.smyy.sharetour.buyer.my.contract.IUserContract;
import com.smyy.sharetour.buyer.my.model.UserModel;
import com.smyy.sharetour.buyer.my.presenter.UserPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class SecurityCenterActivity extends MyBaseMvpActivity<UserPresenter> implements IUserContract.View {
    @BindView(R.id.tv_my_linked_phone)
    TextView tvLinkedPhone;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_security_center;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText(getString(R.string.security_center));
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

    @OnClick({R.id.lay_my_linked_phone, R.id.tv_my_reset_login_password, R.id.tv_my_pay_password})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.lay_my_linked_phone:

                break;

            case R.id.tv_my_reset_login_password:
                break;

            case R.id.tv_my_pay_password:
                break;

            default:
                break;
        }
    }

    @Override
    protected UserPresenter createPresenter() {
        return new UserPresenter(this, new UserModel());
    }

    @Override
    public void showUserInfo(UserInfoBean userInfo) {
        String phone = userInfo.getPhone();
        if (TextUtils.isEmpty(phone)) {
            tvLinkedPhone.setText(R.string.phone_not_linked_yet);
        } else {
            tvLinkedPhone.setText(phone);
        }
    }

    @Override
    public void showDialog(String s) {

    }
}
