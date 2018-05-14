package com.smyy.sharetour.buyer.dialog;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.MyApplication;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.view.RxCountDown;
import com.tuo.customview.VerificationCodeView;

import io.reactivex.functions.Consumer;

/**
 * Created by 伍振飞 on 2018/4/3 17:47
 * E-Mail Address：wuzf2012@sina.com
 */
public class SmsCodeDialog extends AnimationDialog implements View.OnClickListener {
    private VerificationCodeView mSmsCode;
    private SmsCodeCallback mSmsCodeCallback;
    private View iv_close;
    RxCountDown mRxCountDown;
    TextView mTvCodeTime;
    TextView mTvSmsPhone;
    private String mPhone;

    public SmsCodeDialog(Activity context) {
        super(context);
    }

    @Override
    public void setLocationBottom() {
        setLocationMiddle();
    }

    @Override
    protected View obtainView(final Context context) {
        View parent = LayoutInflater.from(context).inflate(R.layout.dialog_sms_code, null);
        iv_close = parent.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(this);
        mSmsCode = (VerificationCodeView) parent.findViewById(R.id.icv_sms);
        mTvCodeTime = (TextView) parent.findViewById(R.id.tv_sms_count_down);
        mTvSmsPhone = (TextView) parent.findViewById(R.id.tv_sms_phone);
        mTvCodeTime.setOnClickListener(this);
        mSmsCode.setInputCompleteListener(new VerificationCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                if (mSmsCode.getInputContent().length() == 6) {
                    dismiss();
                    MyApplication.getApplication().mThreadHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mSmsCodeCallback.SmsCodeResult(mSmsCode.getInputContent());
                        }
                    }, 100);
                }
            }

            @Override
            public void deleteContent() {

            }
        });
        return parent;
    }

    private void showRxCountDown() {
        if (mRxCountDown != null) {
            mRxCountDown.stop();
            mRxCountDown = null;
        }
        /**
         * 倒计时
         */
        mRxCountDown = new RxCountDown.Builder().setMaxTime(Consts.DEFAULT_COUNTDOWN_TIME).setSubscriber(new Consumer<Long>() {
            @Override
            public void accept(Long countDown) throws Exception {
                if (countDown > 0) {
                    mTvCodeTime.setTextColor(MyApplication.getApplication().getResources().getColor(R.color.txt_gray));
                    mTvCodeTime.setText(countDown + "秒后可重新发送");
                    mTvCodeTime.setEnabled(false);
                } else {
                    mTvCodeTime.setTextColor(MyApplication.getApplication().getResources().getColor(R.color.txt_hint));
                    mTvCodeTime.setEnabled(true);
                    mTvCodeTime.setText("重新发送");
                }
            }
        }).build();
        mRxCountDown.start();
    }

    @Override
    public void destroy() {
        if (mRxCountDown != null) {
            mRxCountDown.stop();
            mRxCountDown = null;
        }
        if (mSmsCode != null) {
            mSmsCode = null;
        }
        super.destroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
                cancel();
                if (mSmsCodeCallback != null) {
                    mSmsCodeCallback.SmsCodeCancel();
                }
                break;
            case R.id.tv_sms_count_down:
                mTvCodeTime.setEnabled(false);
                mRxCountDown.start();
                break;
        }
    }

    @Override
    public void dismiss() {
//        mRxCountDown.stop();
        super.dismiss();
    }


    public void showDialog(String phone) {
        mTvSmsPhone.setText("短信验证码已发送至" + phone);
        mSmsCode.clearInputContent();
        if (!TextUtils.isEmpty(phone)) {
            if (!phone.equals(mPhone)) {
                mPhone = phone;
                showRxCountDown();
            }
        }
        this.show();
    }


    public void setClickCallbackListener(SmsCodeCallback listener) {
        mSmsCodeCallback = listener;
    }

    /**
     * 输入支付密码之后的结果回调
     */
    public interface SmsCodeCallback {
        void SmsCodeResult(String smsCode);

        void SmsCodeCancel();
    }
}
