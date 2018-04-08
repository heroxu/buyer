package com.smyy.sharetour.buyer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.util.ActivityLauncher;

import butterknife.BindView;
import butterknife.OnClick;

public class VerifyPhoneActivity extends BaseMvpActivity {
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
    @BindView(R.id.ll_login_wechat)
    LinearLayout llLoginWechat;
    SmsCodeDialog mSmsCodeDialog;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {

    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        initUI();
    }

    private void initUI() {
        hideToolBarLayout(true);
        tvModuleName.setText("验证手机号");
        btnConfirm.setText("发送验证码");
        btnConfirm.setClickable(false);
        btvPasswordLogin.setVisibility(View.GONE);
        llLoginWechat.setVisibility(View.GONE);
        editPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 5) {
                    btnConfirm.setClickable(true);
                    btnConfirm.setBackgroundDrawable(getResources().getDrawable(R.drawable.rs_select_btn_yellow));
                } else {
                    btnConfirm.setClickable(false);
                    btnConfirm.setBackgroundDrawable(getResources().getDrawable(R.drawable.rs_select_btn_gray));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.iv_close, R.id.ll_region, R.id.ll_has_account, R.id.btn_confirm, R.id.btv_register_deal, R.id.btv_privacy_deal, R.id.ll_login_wechat})
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
                showmCodeDialog();
                break;
            case R.id.btv_register_deal:
                break;
            case R.id.btv_privacy_deal:
                break;
            case R.id.ll_login_wechat:
                break;
        }
    }

    /**
     * 弹出验证码对话框
     */
    private void showmCodeDialog() {
        if (mSmsCodeDialog == null) {
            mSmsCodeDialog = new SmsCodeDialog(this);
        }
        if (mSmsCodeDialog.isShowing()) {
            return;
        }
        mSmsCodeDialog.setClickCallbackListener(new SmsCodeDialog.SmsCodeCallback() {
            @Override
            public void SmsCodeResult(String smsCode) {
                if (Consts.DEFAULT_SMS_CODE.equals(smsCode)) {
                    ActivityLauncher.viewSetPwdActivity(VerifyPhoneActivity.this);
                    finish();
                } else {
                    ToastUtils.showToast(VerifyPhoneActivity.this, "验证码错误");
                }
            }

            @Override
            public void SmsCodeCancel() {

            }
        });
        mSmsCodeDialog.show();
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }
}
