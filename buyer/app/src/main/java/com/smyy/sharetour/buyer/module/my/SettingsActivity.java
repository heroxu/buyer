package com.smyy.sharetour.buyer.module.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.smyy.sharetour.buyer.MyApplication;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.event.LoginEvent;
import com.smyy.sharetour.buyer.module.my.base.MyBaseMvpActivity;
import com.smyy.sharetour.buyer.module.my.contract.ISettingsContract;
import com.smyy.sharetour.buyer.module.my.model.SettingsModel;
import com.smyy.sharetour.buyer.module.my.presenter.SettingsPresenter;
import com.smyy.sharetour.buyer.util.ActivityLauncher;
import com.smyy.sharetour.buyer.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingsActivity extends MyBaseMvpActivity<SettingsPresenter> implements ISettingsContract.View {
    @BindView(R.id.tv_my_current_theme)
    TextView tvCurrentTheme;
    @BindView(R.id.tv_my_cache_size)
    TextView tvCacheSize;
    @BindView(R.id.tv_my_version_code)
    TextView tvVersionCode;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_settings;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText(getString(R.string.settings));
        TextView toolbarRightTv = getToolbarRightTv();
        toolbarRightTv.setText(getString(R.string.sign_out));
        toolbarRightTv.setVisibility(View.VISIBLE);
        toolbarRightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.getApplication().logout();
            }
        });
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        EventBus.getDefault().register(this);
        mPresenter.getCacheSize(MyApplication.getApplication());
        tvVersionCode.setText(MyApplication.getApplication().getAppVersionName());
    }

    @OnClick({R.id.tv_my_account_settings, R.id.tv_my_message_settings, R.id.lay_my_change_theme,
            R.id.lay_my_clear_cache, R.id.lay_my_about_us})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_my_account_settings:
                startActivity(AccountSettingsActivity.class);
                break;

            case R.id.tv_my_message_settings:
                startActivity(MessageSettingsActivity.class);
                break;

            case R.id.lay_my_change_theme:

                break;

            case R.id.lay_my_clear_cache:
                mPresenter.clearCache(MyApplication.getApplication());

                break;

            case R.id.lay_my_about_us:

                break;

            default:
                break;
        }
    }

    @Override
    protected SettingsPresenter createPresenter() {
        return new SettingsPresenter(this, new SettingsModel());
    }

    @Override
    public void showCacheSize(String cacheSize) {
        tvCacheSize.setText(cacheSize);
    }

    @Subscribe
    public void onEventMainThread(LoginEvent event) {
        if (!event.isLogin()) {
            ToastUtils.showToast(SettingsActivity.this, "退出登录成功");
            finish();
        }
    }
}
