package com.smyy.sharetour.buyer.module.my;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.dialog.DialogUtils;
import com.smyy.sharetour.buyer.module.my.base.MyBaseMvpActivity;
import com.smyy.sharetour.buyer.module.my.base.MyBasePresenter;
import com.smyy.sharetour.buyer.util.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class LinkedPhoneActivity extends MyBaseMvpActivity {
    public static final String LINKED_PHONE_NUM = "linked_phone_num";

    @BindView(R.id.tv_my_linked_phone)
    TextView tvLinkedPhone;

    @BindView(R.id.tv_my_service_tel)
    TextView tvServiceTel;

    String mLinkedPhoneNum;
    String mServiceTel = Consts.SERVICE_TEL;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_linked_phone;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText(R.string.link_phone);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        Bundle bundle = getBundle();
        if (bundle != null) {
            mLinkedPhoneNum = bundle.getString(LINKED_PHONE_NUM);
            if (mLinkedPhoneNum != null) {
                tvLinkedPhone.setText("已绑定：" + StringUtil.getPhoneNum(mLinkedPhoneNum));
            }
        }
        if (!StringUtil.isEmpty(mServiceTel)) {
            tvServiceTel.setText("客服电话：" + mServiceTel);
            tvServiceTel.setVisibility(View.VISIBLE);
        } else {
            tvServiceTel.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.tv_my_service_tel})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_my_service_tel:
                DialogUtils.showCallDialog(this, Consts.SERVICE_TEL);
                break;

            default:
                break;
        }
    }

    @Override
    protected MyBasePresenter createPresenter() {
        return null;
    }
}
