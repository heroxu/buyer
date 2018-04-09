package com.smyy.sharetour.buyer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.util.ToastUtils;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.view.ClearWriteEditText;

import butterknife.BindView;
import butterknife.OnClick;

public class SetPwdActivity extends BaseMvpActivity {

    @BindView(R.id.tv_module_name)
    TextView tvModuleName;
    @BindView(R.id.cwet_pwd)
    ClearWriteEditText cwetPwd;
    @BindView(R.id.cwet_again_pwd)
    ClearWriteEditText cwetAgainPwd;
    boolean isPwdFinish;
    boolean isAgainPwdFinish;
    @BindView(R.id.reg_button)
    Button regButton;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set_pwd;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        tvModuleName.setText("重置密码");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        initUI();
    }

    private void initUI() {
        hideToolBarLayout(true);
        cwetPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 5) {
                    isPwdFinish = true;
                } else {
                    isPwdFinish = false;
                }
                isConfirmClick();

            }
        });
        cwetAgainPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 5) {
                    isAgainPwdFinish = true;
                } else {
                    isAgainPwdFinish = false;
                }
                isConfirmClick();
            }
        });
    }

    private void isConfirmClick() {
        if (isPwdFinish && isAgainPwdFinish) {
            regButton.setEnabled(true);
        } else {
            regButton.setEnabled(false);
        }
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @OnClick({R.id.iv_close, R.id.reg_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                finish();
                break;
            case R.id.reg_button:
                ToastUtils.showToast(SetPwdActivity.this, tvModuleName.getText().toString().trim() + "成功");
                finish();
                break;
        }
    }
}
