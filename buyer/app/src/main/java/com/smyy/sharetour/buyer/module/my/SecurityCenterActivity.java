package com.smyy.sharetour.buyer.module.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.module.common.code.SmsCodeActivity;
import com.smyy.sharetour.buyer.module.my.base.MyBaseMvpActivity;
import com.smyy.sharetour.buyer.module.my.bean.UserInfoBean;
import com.smyy.sharetour.buyer.module.my.contract.IUserContract;
import com.smyy.sharetour.buyer.module.my.model.UserModel;
import com.smyy.sharetour.buyer.module.my.presenter.UserPresenter;
import com.smyy.sharetour.buyer.util.ActivityLauncher;
import com.smyy.sharetour.buyer.util.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class SecurityCenterActivity extends MyBaseMvpActivity<UserPresenter> implements IUserContract.View {

    private static final int REQ_LINK_PHONE = 1;

    @BindView(R.id.tv_my_linked_phone)
    TextView tvLinkedPhone;
    private String mLinkedPhoneNum;

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
        mPresenter.getUserInfo();
    }

    @OnClick({R.id.lay_my_linked_phone, R.id.tv_my_reset_login_password, R.id.tv_my_pay_password})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.lay_my_linked_phone:
                if (TextUtils.isEmpty(mLinkedPhoneNum)) {
                    startActivityForResult(LinkPhoneActivity.class, REQ_LINK_PHONE);
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString(LinkedPhoneActivity.LINKED_PHONE_NUM, mLinkedPhoneNum);
                    startActivityForResult(LinkedPhoneActivity.class, bundle, REQ_LINK_PHONE);
                }
                break;

            case R.id.tv_my_reset_login_password:
                break;

            case R.id.tv_my_pay_password:
                ActivityLauncher.viewBackpackSetPayPwdActivity(SecurityCenterActivity.this);
                break;

            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQ_LINK_PHONE:
                    mPresenter.getUserInfo();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected UserPresenter createPresenter() {
        return new UserPresenter(this, new UserModel());
    }

    @Override
    public void showUserInfo(UserInfoBean userInfo) {
        mLinkedPhoneNum = userInfo.getLinkedPhoneNum();
        if (TextUtils.isEmpty(mLinkedPhoneNum)) {
            tvLinkedPhone.setText(R.string.phone_not_linked_yet);
        } else {
            tvLinkedPhone.setText(StringUtil.getPhoneNum(mLinkedPhoneNum));
        }
    }

    @Override
    public void editUserInfoSuccess() {

    }

    @Override
    public void editUserInfoFail() {

    }
}
