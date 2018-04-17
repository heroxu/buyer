package com.smyy.sharetour.buyer.module.common.code;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.module.common.code.contract.ISmsCodeContract;
import com.smyy.sharetour.buyer.module.common.code.model.SmsCodeModel;
import com.smyy.sharetour.buyer.module.common.code.presenter.SmsCodePresenter;
import com.smyy.sharetour.buyer.module.my.base.MyBaseMvpActivity;
import com.smyy.sharetour.buyer.module.my.base.MyBasePresenter;
import com.smyy.sharetour.buyer.module.my.bean.UserInfoBean;
import com.smyy.sharetour.buyer.module.my.contract.IUserContract;
import com.smyy.sharetour.buyer.module.my.model.UserModel;
import com.smyy.sharetour.buyer.module.my.presenter.UserPresenter;
import com.smyy.sharetour.buyer.util.StringUtil;
import com.smyy.sharetour.buyer.util.ToastUtils;
import com.smyy.sharetour.buyer.view.RxCountDown;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class SmsCodeActivity extends MyBaseMvpActivity<SmsCodePresenter> implements ISmsCodeContract.View {

    public static final String TITLE = "title";
    public static final String PHONE_NUM = "phone_num";
    /**
     * 国际区号
     */
    public static final String INTERNATIONAL_CODE = "international_code";

    @BindView(R.id.et_code_input_code)
    EditText etInputCode;
    @BindView(R.id.tv_code_get_code)
    TextView tvGetCode;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;

    Bundle mBundle;

    String mInternationalCode;
    String mPhoneNum;
    private String mSmsCode;
    private RxCountDown mRxCountDown;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_code_sms_code;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        mBundle = getBundle();
        if (mBundle != null) {
            String titleStr = mBundle.getString(TITLE);
            if (!TextUtils.isEmpty(titleStr)) {
                title.setText(titleStr);
            }
        }

    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        if (mBundle != null) {
            mInternationalCode = mBundle.getString(INTERNATIONAL_CODE);
            mPhoneNum = mBundle.getString(PHONE_NUM);
        }

        mRxCountDown = new RxCountDown.Builder().setMaxTime(Consts.DEFAULT_COUNTDOWN_TIME).setSubscriber(new Consumer<Long>() {
            @Override
            public void accept(Long countDown) throws Exception {
                if (countDown > 0) {
                    tvGetCode.setEnabled(false);
                    tvGetCode.setText(countDown + "s");
                } else {
                    tvGetCode.setEnabled(true);
                    tvGetCode.setText("发送验证码");
                }
            }
        }).build();

        setListener();
    }

    private void setListener() {
        etInputCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mSmsCode = StringUtil.trim(etInputCode);
                if (StringUtil.isSmsCode(mSmsCode)) {
                    btnConfirm.setEnabled(true);
                } else {
                    btnConfirm.setEnabled(false);
                }
            }
        });
    }

    @OnClick({R.id.tv_code_get_code, R.id.btn_confirm})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_code_get_code:
                mPresenter.getSmsCode(mInternationalCode, mPhoneNum);
                break;

            case R.id.btn_confirm:
                mPresenter.verifySmsCode(mSmsCode);
                break;

            default:
                break;
        }
    }

    @Override
    protected SmsCodePresenter createPresenter() {
        return new SmsCodePresenter(this, new SmsCodeModel());
    }

    @Override
    protected void onDestroy() {
        mRxCountDown.stop();
        super.onDestroy();
    }

    @Override
    public void startCountDown() {
        mRxCountDown.start();
    }

    @Override
    public void stopCountDown() {
        mRxCountDown.stop();
        tvGetCode.setEnabled(true);
        tvGetCode.setText("发送验证码");
    }

    @Override
    public void verifySmsCodeSuccess() {
        hideProgressDialog();
        ToastUtils.showToast("绑定成功");
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void verifySmsCodeFail() {
        hideProgressDialog();
        ToastUtils.showToast("绑定失败");
    }
}
