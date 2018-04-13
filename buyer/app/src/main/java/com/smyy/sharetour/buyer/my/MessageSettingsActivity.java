package com.smyy.sharetour.buyer.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.smyy.sharetour.buyer.MyApplication;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.SPConfig;
import com.smyy.sharetour.buyer.my.bean.UserInfoBean;
import com.smyy.sharetour.buyer.util.SharePreferenceUtil;
import com.smyy.sharetour.uiframelib.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MessageSettingsActivity extends BaseActivity {
    @BindView(R.id.iv_my_toggle_im)
    CheckBox toggleIm;
    @BindView(R.id.iv_my_toggle_sysm)
    CheckBox toggleSysm;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_message_settings;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText(getString(R.string.message_settings));
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {

        toggleIm.setChecked(new SharePreferenceUtil(MyApplication.getContext(), SPConfig.APP_SETTINGS)
                .getBooleanValue(SPConfig.TOGGLE_IM, true));

        toggleSysm.setChecked(new SharePreferenceUtil(MyApplication.getContext(), SPConfig.APP_SETTINGS)
                .getBooleanValue(SPConfig.TOGGLE_SYSM, true));
    }


    @OnClick({R.id.iv_my_toggle_im, R.id.iv_my_toggle_sysm})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.iv_my_toggle_im:
                new SharePreferenceUtil(MyApplication.getContext(), SPConfig.APP_SETTINGS)
                        .writeBooleanValue(SPConfig.TOGGLE_IM, toggleIm.isChecked());
                break;

            case R.id.iv_my_toggle_sysm:
                new SharePreferenceUtil(MyApplication.getContext(), SPConfig.APP_SETTINGS)
                        .writeBooleanValue(SPConfig.TOGGLE_SYSM, toggleSysm.isChecked());
                break;

            default:
                break;
        }
    }
}
