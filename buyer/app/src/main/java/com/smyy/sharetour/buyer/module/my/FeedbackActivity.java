package com.smyy.sharetour.buyer.module.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.module.my.base.MyBaseMvpActivity;
import com.smyy.sharetour.buyer.module.my.base.MyBasePresenter;
import com.smyy.sharetour.buyer.util.ToastUtils;

import butterknife.BindView;

public class FeedbackActivity extends MyBaseMvpActivity {
    @BindView(R.id.et_my_feedback)
    EditText etFeedback;
    @BindView(R.id.tv_my_count)
    TextView tvCount;
    private int mFeedbackLength;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_feedback;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("帮助与反馈");
        TextView toolbarRightTv = getToolbarRightTv();
        toolbarRightTv.setText("发送");
        toolbarRightTv.setVisibility(View.VISIBLE);
        toolbarRightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFeedbackLength > 140) {
                    ToastUtils.showToast("不得超过140个字");
                } else {
                    finish();
                }
            }
        });
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        etFeedback.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mFeedbackLength = etFeedback.getText().toString().length();
                tvCount.setText(mFeedbackLength + "/140");
            }
        });
    }

    @Override
    protected MyBasePresenter createPresenter() {
        return null;
    }
}
