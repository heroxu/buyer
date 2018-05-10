package com.smyy.sharetour.buyer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.MyApplication;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.module.my.bean.UserInfoBean;
import com.smyy.sharetour.buyer.module.my.contract.IUserContract;
import com.smyy.sharetour.buyer.module.my.model.UserModel;
import com.smyy.sharetour.buyer.module.my.presenter.UserPresenter;
import com.smyy.sharetour.buyer.util.ToastUtils;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.util.ActivityLauncher;
import com.smyy.sharetour.buyer.view.ClearWriteEditText;

import butterknife.BindView;
import butterknife.OnClick;

public class PwdLoginActivity extends BaseMvpActivity {
    @BindView(R.id.tv_module_name)
    TextView tvModuleName;
    @BindView(R.id.tv_region)
    TextView tvRegion;
    @BindView(R.id.tv_area_code)
    TextView tvAreaCode;
    @BindView(R.id.edit_phone)
    ClearWriteEditText editPhone;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.btv_password_login)
    TextView btvPasswordLogin;
    @BindView(R.id.ll_pwd_edit)
    LinearLayout llPwdEdit;
    @BindView(R.id.edit_password)
    ClearWriteEditText editPassword;
    //判断按钮是否可以被点击
    boolean isPhoneEdit;
    boolean isPwdEdit;

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        hideToolBarLayout(true);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initUI();
    }

    private void initUI() {
        hideToolBarLayout(true);
        llPwdEdit.setVisibility(View.VISIBLE);
        btnConfirm.setText("登录");
        btvPasswordLogin.setText("忘记密码");
        editPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > Consts.MIN_PHONE_LENGTH) {
                    isPhoneEdit = true;
                } else {
                    isPhoneEdit = false;
                }
                isConfirmClick();
            }
        });
        editPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > Consts.MIN_PWD_LENGTH) {
                    isPwdEdit = true;
                } else {
                    isPwdEdit = false;
                }
                isConfirmClick();
            }
        });
    }

    //判断登录按钮是否可以被点击
    private void isConfirmClick() {
        if (isPhoneEdit && isPwdEdit) {
            btnConfirm.setEnabled(true);
        } else {
            btnConfirm.setEnabled(false);
        }
    }

    @OnClick({R.id.iv_close, R.id.ll_region, R.id.ll_has_account, R.id.btn_confirm, R.id.btv_register_deal, R.id.btv_privacy_deal, R.id.btv_password_login, R.id.ll_login_wechat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                finish();
                break;
            case R.id.ll_region:
                break;
            case R.id.ll_has_account:
                break;
            case R.id.btn_confirm:
                String phoneNum = editPhone.getText().toString().trim();
                String pwd = editPassword.getText().toString().trim();
                if (Consts.DEFAULT_LOGIN_PWD.equals(pwd) && Consts.isPhoneNum(phoneNum)) {
                    MyApplication.getApplication().setLogin(true);
                    new UserPresenter(new IUserContract.View() {
                        @Override
                        public void showUserInfo(UserInfoBean userInfo) {

                        }

                        @Override
                        public void editUserInfoSuccess() {

                        }

                        @Override
                        public void editUserInfoFail() {

                        }

                        @Override
                        public void showProgressDialog() {

                        }

                        @Override
                        public void showProgressDialog(String msg) {

                        }

                        @Override
                        public void hideProgressDialog() {

                        }

                        @Override
                        public void finish() {

                        }

                        @Override
                        public void showToast(String s) {

                        }

                        @Override
                        public void showToast(int stringRes) {

                        }
                    }, new UserModel()).getUserInfo();
                    ToastUtils.showToast(PwdLoginActivity.this, "登录成功");
                    finish();
                } else {
                    ToastUtils.showToast(PwdLoginActivity.this, "登录失败，请检查手机和验证码是否正确");
                }
                finish();
                break;
            case R.id.btv_register_deal:
                break;
            case R.id.btv_privacy_deal:
                break;
            case R.id.btv_password_login:
                ActivityLauncher.viewVerifyPhoneActivity(this);
                finish();
                break;
            case R.id.ll_login_wechat:
                break;
        }
    }

}
