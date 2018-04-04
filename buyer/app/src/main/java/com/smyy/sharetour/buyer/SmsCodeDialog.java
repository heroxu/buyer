package com.smyy.sharetour.buyer;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.tuo.customview.VerificationCodeView;

import io.reactivex.functions.Consumer;

/**
 * Created by 伍振飞 on 2018/4/3 17:47
 * E-Mail Address：wuzf2012@sina.com
 */
public class SmsCodeDialog extends AnimationDialog implements View.OnClickListener {
    private VerificationCodeView mSmsCode;
    private SmsCodeCallback mPayCallback;
    private View iv_close;
    RxCountDown mRxCountDown;
    TextView mTvCodeTime;

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
        mTvCodeTime.setOnClickListener(this);
        mSmsCode.setInputCompleteListener(new VerificationCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                if (mSmsCode.getInputContent().length() == 6) {
                    MyApplication.getApplication().mThreadHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dismiss();
                        }
                    }, 100);
                }
            }

            @Override
            public void deleteContent() {

            }
        });
        /**
         * 倒计时
         */
        mRxCountDown = new RxCountDown.Builder().setMaxTime(10).setSubscriber(new Consumer<Long>() {
            @Override
            public void accept(Long countDown) throws Exception {
                if (countDown > 0) {
                    mTvCodeTime.setText(countDown + "秒后可重新发送");
                    mTvCodeTime.setEnabled(false);
                } else {
                    mTvCodeTime.setEnabled(true);
                    mTvCodeTime.setText("重新发送");
                }
            }
        }).build();
        mRxCountDown.start();
        return parent;
    }

    @Override
    public void destroy() {
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
                if (mPayCallback != null) {
                    mPayCallback.SmsCodeCancel();
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
        mRxCountDown.stop();
        super.dismiss();
    }

    public void setClickCallbackListener(SmsCodeCallback listener) {
        mPayCallback = listener;
    }

    /**
     * 输入支付密码之后的结果回调
     */
    public interface SmsCodeCallback {
        //支付结果
        void SmsCodeResult(String smsCode);

        //取消支付
        void SmsCodeCancel();
    }
}
