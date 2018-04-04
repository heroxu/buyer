package com.smyy.sharetour.buyer;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by 伍振飞 on 2018/4/3 16:45
 * E-Mail Address：wuzf2012@sina.com
 */
public abstract class AnimationDialog extends Dialog {

    public AnimationDialog(Activity context) {
        this(context, R.style.Animation_Dialog);
    }

    public AnimationDialog(Activity context, int theme) {
        super(context, theme);
        init(context);
    }

    /**
     * 自行设置dialog位置。
     *
     */
    public void setDialogLocation(int gravity, int x, int y) {
        Window window = this.getWindow();
        window.setGravity(gravity);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        lp.x = x; // 新位置X坐标
        lp.y = y; // 新位置Y坐标
        window.setAttributes(lp);
    }

    public void setLocationBottom() {
        setDialogLocation(Gravity.BOTTOM, 0, 0);
    }

    public void setLocationMiddle() {
        setDialogLocation(Gravity.CENTER, 0, 0);
    }

    private void init(Context context) {
        View view = obtainView(context);
        setContentView(view);
        setLocationBottom();
        setCanceledOnTouchOutside(true);
//        this.setOnDismissListener(new OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialogInterface) {
//				destroy();
//            }
//        });
    }

    public void destroy() {
    }

    /** 初始化布局 **/
    protected abstract View obtainView(Context context);
}

