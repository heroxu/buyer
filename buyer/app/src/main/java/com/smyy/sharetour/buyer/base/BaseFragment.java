package com.smyy.sharetour.buyer.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.android.tu.loadingdialog.LoadingDailog;
import com.gyf.barlibrary.ImmersionBar;

import java.lang.reflect.Method;

import butterknife.ButterKnife;


/**
 * Created by justin on 17/9/9.
 */

public abstract class BaseFragment extends UmengFragment {

    protected Activity mActivity;
    private LoadingDailog mLoadingDailog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    protected ImmersionBar mImmersionBar;

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getActivity() != null && getActivity().isFinishing()) {
            return null;
        }
        ViewGroup rootView = (ViewGroup) inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && mImmersionBar != null)
            mImmersionBar.init();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isImmersionBarEnabled())
            initImmersionBar();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null && getActivity().isFinishing()) {
            return;
        }
        if (savedInstanceState != null) {
            initData(savedInstanceState);
        } else {
            if (getActivity() != null && getActivity().getIntent() != null) {
                initData(getActivity().getIntent().getExtras());
            } else {
                initData(null);
            }
        }
    }

    protected abstract int getLayoutId();
    protected abstract void initData(Bundle bundle);

    /**
     * 利用反射让隐藏在Overflow中的MenuItem显示Icon图标
     * @param menu
     * onMenuOpened方法中调用
     */
    public static void setOverflowIconVisible(Menu menu) {
        if (menu != null && menu.getClass().getSimpleName().equals("MenuBuilder")) {
            try {
                Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                m.setAccessible(true);
                m.invoke(menu, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /** fragment 页面被回收后，恢复页面工具类 **/
    public static class FragmentRestoredUtil {
        private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

        public static void onRestoreState(Bundle bundle, FragmentManager fragmentManager, BaseFragment fragment) {
            if (bundle == null) {
                return;
            }
            boolean isSupportHidden = bundle.getBoolean(STATE_SAVE_IS_HIDDEN);
            try {
                FragmentTransaction ft = fragmentManager.beginTransaction();
                if (isSupportHidden) {
                    ft.hide(fragment);
                } else {
                    ft.show(fragment);
                }
//                ft.commit();
                ft.commitAllowingStateLoss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static void onSaveInstanceState(Bundle outState, BaseFragment fragment) {
            outState.putBoolean(STATE_SAVE_IS_HIDDEN, fragment.isHidden());
        }
    }

    /**
     * 是否在Fragment使用沉浸式
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    /**
     * 初始化沉浸式
     */
    private void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
        initStatusBar();
    }

    /**
     * 设置状态栏颜色
     * 默认白底黑字
     */
    protected void initStatusBar() {
        mImmersionBar.fitsSystemWindows(true).statusBarColorInt(Color.WHITE).statusBarDarkFont(true, 0.2f).init();
    }

    /**
     * 设置状态栏颜色
     * 关联到指定Toolbar
     */
    protected void setStatusBar(Toolbar toolbar) {
        mImmersionBar.fitsSystemWindows(true).titleBar(toolbar).init();
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

    public void showProgressDialog() {
        if (mLoadingDailog == null) {
            LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(mActivity)
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
}
