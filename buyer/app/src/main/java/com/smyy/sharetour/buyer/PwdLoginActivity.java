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

import butterknife.BindView;

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
        initUI();
    }

    private void initUI() {
        hideToolBarLayout(true);
        llPwdEdit.setVisibility(View.VISIBLE);
        btnConfirm.setText("登录");
        btnConfirm.setClickable(false);
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
}
