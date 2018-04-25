package com.smyy.sharetour.buyer.BackPacker.payPwd;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.util.ToastUtils;
import com.smyy.sharetour.buyer.view.PasswordEditText;

import butterknife.BindView;
import butterknife.OnClick;

public class BackpackSetPayPwdActivity extends BaseMvpActivity {
    @BindView(R.id.pe_password)
    PasswordEditText pePassword;
    @BindView(R.id.tv_pay_pwd_title)
    TextView tvPayPwdTitle;
    private String pwd1;
    private String pwd2;
    private boolean isFirstOk;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_backpack_set_pay_pwd;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("设置支付密码");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        pePassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!isFirstOk) {
                    pwd1 = pePassword.getText().toString().trim();
                } else {
                    pwd2 = pePassword.getText().toString().trim();
                }
            }
        });
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @OnClick(R.id.btn_finish)
    public void onViewClicked() {
        if (!isFirstOk) {
            if (pwd1.length() == 6) {
                changeUI();
            } else {
                ToastUtils.showToast(BackpackSetPayPwdActivity.this, "请输入最少6位数支付密码");
            }
        } else {
            if (pwd2.length() == 6) {
                if (pwd1.equals(pwd2)) {
                    ToastUtils.showToast(BackpackSetPayPwdActivity.this, "支付密码设置成功");
                    finish();
                } else {
                    ToastUtils.showToast(BackpackSetPayPwdActivity.this, "两次密码不一致");
                }
            } else {
                ToastUtils.showToast(BackpackSetPayPwdActivity.this, "请输入最少6位数支付密码");
            }
        }
    }

    private void changeUI() {
        isFirstOk = true;
        tvPayPwdTitle.setText("请再次输入，以确认密码");
        pePassword.setText("");
    }

}
