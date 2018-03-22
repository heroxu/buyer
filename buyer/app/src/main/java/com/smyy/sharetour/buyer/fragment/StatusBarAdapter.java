package com.smyy.sharetour.buyer.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.smyy.sharetour.buyer.R;

/**
 * Created by hasee on 2018/3/15.
 */

public class StatusBarAdapter {

    private StatusBarAdapter() {

    }

    //更新伪透明状态栏的颜色
    public static void changeStatusBarColor(Activity activity, int color) {
        if (activity != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            if (window == null) {
                return;
            }
            window.setStatusBarColor(color);
        }
    }

    //更新伪透明状态栏的高度or topPadding ， 可以再Application里面先获取这个值，就不需要获取那么多篇了
    public static int updateStatusHeight(Context context, View tagSystemBarHeightView, View tagSystemBarTopView) {
        Resources resources = context.getResources();
        int statusbar_view_height = resources.getDimensionPixelSize(R.dimen.statusbar_view_height);
        System.out.println(" updateStatusHeight 1 statusbar_view_height = " + statusbar_view_height);
        if (statusbar_view_height > 0) {
            try {
                /**获取状态栏高度**/
                int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
                statusbar_view_height = resources.getDimensionPixelSize(resourceId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (tagSystemBarHeightView != null) {
                tagSystemBarHeightView.getLayoutParams().height = statusbar_view_height;
            }
            if (tagSystemBarTopView != null) {
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) tagSystemBarTopView.getLayoutParams();
                params.topMargin = statusbar_view_height;
                tagSystemBarTopView.setLayoutParams(params);
            }
        }
        System.out.println(" updateStatusHeight 2 statusbar_view_height = " + statusbar_view_height);
        return statusbar_view_height;
    }

}
