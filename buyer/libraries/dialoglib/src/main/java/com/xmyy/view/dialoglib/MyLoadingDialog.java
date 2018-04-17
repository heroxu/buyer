package com.xmyy.view.dialoglib;

import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xmyy.view.dialoglib.base.BindViewHolder;
import com.xmyy.view.dialoglib.listener.OnBindViewListener;


public class MyLoadingDialog extends TDialog {
    public static final int SUCCESS = 1;
    public static final int FAIL = -1;
    public static final int LOADING = 0;

    ImageView mIvReult;
    View mProgressbar;
    TextView mTvMsg;

    public void setStatus(int status, String msg) {
        if (mIvReult == null || mProgressbar == null || mTvMsg == null) {
            return;
        }

        mTvMsg.setText(msg);
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



    @Override
    protected void bindView(View view) {
        super.bindView(view);
        mIvReult = (ImageView) view.findViewById(R.id.iv_dialog_result);
        mProgressbar = view.findViewById(R.id.progressbar_dialog);
        mTvMsg = (TextView) view.findViewById(R.id.tv_dialog_msg);
    }

    public static class Builder extends TDialog.Builder {

        public Builder(FragmentManager fragmentActivity) {
            super(fragmentActivity);
            params.mLayoutRes = R.layout.my_dialog_loading;
        }

        public Builder setMessage(final String msg) {
            setOnBindViewListener(new OnBindViewListener() {
                @Override
                public void bindView(BindViewHolder viewHolder) {
                    viewHolder.setText(R.id.tv_dialog_msg, msg);
                }
            });
            return this;
        }

        @Override
        public MyLoadingDialog create() {
            MyLoadingDialog dialog = new MyLoadingDialog();
            Log.d(TAG, "create");
            //将数据从Buidler的TParams中传递到TDialog中
            params.apply(dialog.tController);
            return dialog;
        }
    }
}
