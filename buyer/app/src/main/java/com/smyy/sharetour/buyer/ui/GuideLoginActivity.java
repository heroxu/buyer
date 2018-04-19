package com.smyy.sharetour.buyer.ui;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.smyy.sharetour.buyer.event.LoginEvent;
import com.smyy.sharetour.buyer.view.CustomVideoView;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.util.ActivityLauncher;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

public class GuideLoginActivity extends BaseMvpActivity {

    @BindView(R.id.glogin_videoview)
    CustomVideoView gloginVideoview;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide_login;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        EventBus.getDefault().register(this);
        hideToolBarLayout(true);
        initVideoView();
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }


    @OnClick({R.id.ll_glogin_close, R.id.btn_glogin_register, R.id.btn_glogin_login, R.id.ll_glogin_wechat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_glogin_close:
                finish();
                break;
            case R.id.btn_glogin_register:
                ActivityLauncher.viewRegisterActivity(this);
                break;
            case R.id.btn_glogin_login:
                ActivityLauncher.viewLoginActivity(this);
                break;
            case R.id.ll_glogin_wechat:
                break;
        }
    }

    //返回重启加载
    @Override
    protected void onRestart() {
        initVideoView();
        super.onRestart();
    }

    private void initVideoView() {
        //设置播放加载路径
        gloginVideoview.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.login));
        //播放
        gloginVideoview.start();
        //循环播放
        gloginVideoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                gloginVideoview.start();
            }
        });
    }

    //防止锁屏或者切出的时候，音乐在播放
    @Override
    protected void onStop() {
        gloginVideoview.stopPlayback();
        super.onStop();
    }

    @Override
    protected void initStatusBar() {
        setStatusBar(Color.BLACK);
    }

    @Subscribe
    public void onEventMainThread(LoginEvent event) {
        if (event.isLogin()) {
            finish();
        }
    }
}
