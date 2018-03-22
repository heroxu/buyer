package com.smyy.sharetour.buyer.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.Method;

import butterknife.ButterKnife;


/**
 * Created by justin on 17/9/9.
 */

public abstract class BaseFragment extends UmengFragment {

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

}
