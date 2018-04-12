package com.smyy.sharetour.buyer.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.uiframelib.BaseActivity;

import butterknife.OnClick;

public class MessageSettingsActivity extends BaseActivity {

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
    }


    @OnClick({R.id.iv_my_toggle_im, R.id.iv_my_toggle_sysm})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.iv_my_toggle_im:

                break;

            case R.id.iv_my_toggle_sysm:

                break;

            default:
                break;
        }
    }
}
