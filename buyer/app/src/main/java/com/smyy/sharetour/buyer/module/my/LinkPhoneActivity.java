package com.smyy.sharetour.buyer.module.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.module.common.code.SmsCodeActivity;
import com.smyy.sharetour.buyer.module.my.base.MyBaseMvpActivity;
import com.smyy.sharetour.buyer.module.my.base.MyBasePresenter;
import com.smyy.sharetour.buyer.ui.SelectAreaCodeActivity;
import com.smyy.sharetour.buyer.util.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class LinkPhoneActivity extends MyBaseMvpActivity {

    private static final int REQ_SMS_CODE = 1;
    private static final int REQ_INTERNATIONAL_CODE = 2;

    @BindView(R.id.tv_my_international_code)
    TextView tvInternationalCode;
    @BindView(R.id.et_my_phone_number)
    EditText etPhoneNum;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;

    String mInternationalCode = "86";
    String mPhoneNum;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_link_phone;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText(R.string.link_phone);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        tvInternationalCode.setText("+" + mInternationalCode);
        setListener();
    }

    private void setListener() {
        etPhoneNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPhoneNum = StringUtil.trim(etPhoneNum);
                if (StringUtil.isPhoneNum(mPhoneNum)) {
                    btnConfirm.setEnabled(true);
                } else {
                    btnConfirm.setEnabled(false);
                }
            }
        });
    }

    @OnClick({R.id.tv_my_international_code, R.id.btn_confirm})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_my_international_code:
                startActivityForResult(SelectAreaCodeActivity.class, REQ_INTERNATIONAL_CODE);

                break;

            case R.id.btn_confirm:
                Bundle bundle = new Bundle();
                bundle.putString(SmsCodeActivity.TITLE, getResources().getString(R.string.link_phone));
                bundle.putString(SmsCodeActivity.PHONE_NUM, mPhoneNum);
                bundle.putString(SmsCodeActivity.INTERNATIONAL_CODE, mInternationalCode);
                startActivityForResult(SmsCodeActivity.class, bundle, REQ_SMS_CODE);
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
                case REQ_SMS_CODE:
                    setResult(RESULT_OK);
                    finish();
                    break;

                case REQ_INTERNATIONAL_CODE:
                    //TODO mInternationalCode=
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected MyBasePresenter createPresenter() {
        return null;
    }
}
