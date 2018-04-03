package com.smyy.sharetour.uiframelib;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;


/**
 * Created by justin on 17/9/9.
 */

public abstract class BaseActivity extends UmengActivity {
    static final String TAG = BaseActivity.class.getSimpleName();

    protected ImmersionBar mImmersionBar;
    Toolbar mToolbar;
    TextView mToolbarTitle;

    protected FrameLayout mContentLayout;
    private LoadingDailog mLoadingDailog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_base);
        mContentLayout = (FrameLayout) findViewById(R.id.layout_content);
        int layoutId = getLayoutId();
        if (layoutId != 0) {
            LayoutInflater.from(this).inflate(layoutId, mContentLayout, true);
        }
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbarTitle = (TextView) findViewById(R.id.tv_center_title);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            // 默认居左title不可用
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        configToolBar(mToolbar, mToolbarTitle);

        //初始化沉浸式
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
        beforeInitData();
        initData(savedInstanceState, getIntent());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        hideProgressDialog();
        super.onDestroy();
        if (mImmersionBar != null) {
            mImmersionBar.destroy();  //在BaseActivity里销毁}
        }
    }

    public void showProgressDialog() {
        if (mLoadingDailog == null) {
            LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(this)
                    .setMessage("加载中...")
                    .setCancelable(false)
                    .setCancelOutside(false);
            mLoadingDailog = loadBuilder.create();
        }
        try {
            mLoadingDailog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideProgressDialog() {
        if (mLoadingDailog != null && mLoadingDailog.isShowing()) {
            mLoadingDailog.dismiss();
        }
    }

    protected abstract int getLayoutId();

    protected abstract void configToolBar(Toolbar toolbar, TextView title);

    protected abstract void initData(@Nullable Bundle savedInstanceState, Intent intent);

    protected void hideToolBarLayout(boolean hide) {
        int visibility = hide ? View.GONE : View.VISIBLE;
        if (mToolbar != null) {
            mToolbar.setVisibility(visibility);
        }
        // toolbar下面的线条
//        View divider_line = findViewById(R.id.divider_line);
//        if (divider_line != null) {
//            divider_line.setVisibility(visibility);
//        }
    }

    protected void beforeInitData() {

    }

//    public void showProgressDialog(String msg) {
//        if (mProgressDialog == null) {
//            mProgressDialog = XqcProgressDialog.create(this, msg, false, null);
//        }
//        try {
//            mProgressDialog.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void hideProgressDialog() {
//        if (mProgressDialog != null && mProgressDialog.isShowing()) {
//            mProgressDialog.dismiss();
//        }
//    }

    protected TextView getToolbarTitle() {
        return mToolbarTitle;
    }

    protected Toolbar getToolbar() {
        return mToolbar;
    }

    /**
     * 是否可以使用沉浸式
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    private void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        initStatusBar();
    }


    /**
     * 设置状态栏颜色
     * 默认白底黑字
     */
    protected void initStatusBar() {
        setStatusBarWhite();
    }

    /**
     * 设置状态栏颜色
     * 关联到指定Toolbar
     */
    protected void setStatusBar(Toolbar toolbar) {
        mImmersionBar.titleBar(toolbar).init();
    }

    /**
     * 设置状态栏颜色为白底黑字
     */
    protected void setStatusBarWhite() {
        mImmersionBar.fitsSystemWindows(true).statusBarColorInt(Color.WHITE).statusBarDarkFont(true, 0.2f).init();
    }

    /**
     * 设置状态栏颜色
     */
    protected void setStatusBar(@ColorInt int statusBarColor) {
        mImmersionBar.fitsSystemWindows(true).statusBarColorInt(statusBarColor).init();
    }
}
