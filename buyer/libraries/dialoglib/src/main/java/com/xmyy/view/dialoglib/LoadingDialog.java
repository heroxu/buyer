package com.xmyy.view.dialoglib;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xmyy.view.dialoglib.base.BaseDialogFragment;


public class LoadingDialog extends BaseDialogFragment {
    public static final int SUCCESS = 1;
    public static final int FAIL = -1;
    public static final int LOADING = 0;

    ImageView mIvReult;
    View mProgressbar;
    TextView mTvMsg;
    private int mStatus;
    private String mMessage;

    public void switchStatus(int status, String msg) {
        if (mIvReult == null || mProgressbar == null || mTvMsg == null) {
            return;
        }

        if (!TextUtils.isEmpty(msg)) {
            mTvMsg.setText(msg);
        }
        switch (status) {
            case SUCCESS:
            case FAIL:
                mIvReult.setVisibility(View.VISIBLE);
                mProgressbar.setVisibility(View.GONE);
                break;
            default:
                mIvReult.setVisibility(View.GONE);
                mProgressbar.setVisibility(View.VISIBLE);
                break;
        }
    }

    public LoadingDialog setStatus(int status, String msg) {
        this.mStatus = status;
        this.mMessage = msg;
        return this;
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.dialog_loading;
    }

    @Override
    protected void bindView(View view) {
        mIvReult = (ImageView) view.findViewById(R.id.iv_dialog_result);
        mProgressbar = view.findViewById(R.id.progressbar_dialog);
        mTvMsg = (TextView) view.findViewById(R.id.tv_dialog_msg);
        switchStatus(mStatus, mMessage);
    }

    @Override
    protected int getAnimRes() {
        return 0;
    }

    @Override
    protected View getDialogView() {
        return null;
    }
}
