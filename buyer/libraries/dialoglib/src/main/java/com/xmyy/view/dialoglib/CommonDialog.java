package com.xmyy.view.dialoglib;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;

import com.xmyy.view.dialoglib.base.BaseDialogFragment;
import com.xmyy.view.dialoglib.base.BindViewHolder;
import com.xmyy.view.dialoglib.base.CommonController;
import com.xmyy.view.dialoglib.listener.OnBindViewListener;
import com.xmyy.view.dialoglib.listener.OnViewClickListener;

/**
 * 参考： https://github.com/Timmy-zzh/TDialog
 **/
public class CommonDialog extends BaseDialogFragment {

    private static final String KEY_TCONTROLLER = "CommonController";
    protected CommonController commonController;

    public CommonDialog() {
        commonController = new CommonController();
    }

    /**
     * 当设备旋转时,会重新调用onCreate,进行数据恢复
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            commonController = (CommonController) savedInstanceState.getSerializable(KEY_TCONTROLLER);
        }
    }

    /**
     * 进行数据保存
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(KEY_TCONTROLLER, commonController);
        super.onSaveInstanceState(outState);
    }

    /**
     * 弹窗消失时回调方法
     */
    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        DialogInterface.OnDismissListener onDismissListener = commonController.getOnDismissListener();
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
        }
    }

    /**
     * 获取弹窗xml布局界面
     */
    @Override
    protected int getLayoutRes() {
        return commonController.getLayoutRes();
    }

    @Override
    protected View getDialogView() {
        return commonController.getDialogView();
    }

    @Override
    protected void bindView(View view) {
        //控件点击事件处理
        BindViewHolder viewHolder = new BindViewHolder(view, this);
        if (commonController.isCancelable() && commonController.getIds() != null && commonController.getIds().length > 0) {
            for (int id : commonController.getIds()) {
                viewHolder.addOnClickListener(id);
            }
        }
        //回调方法获取到布局,进行处理
        if (commonController.getOnBindViewListener() != null) {
            commonController.getOnBindViewListener().bindView(viewHolder);
        }
    }


    @Override
    public int getGravity() {
        return commonController.getGravity();
    }

    @Override
    public float getDimAmount() {
        return commonController.getDimAmount();
    }

    @Override
    public int getDialogHeight() {
        return commonController.getHeight();
    }

    @Override
    public int getDialogWidth() {
        return commonController.getWidth();
    }

    @Override
    public String getFragmentTag() {
        return commonController.getTag();
    }

    public OnViewClickListener getOnViewClickListener() {
        return commonController.getOnViewClickListener();
    }

    @Override
    public boolean isCancelable() {
        return commonController.isCancelable();
    }

    @Override
    protected boolean isCancelableOutside() {
        return commonController.isCancelableOutside();
    }

    public CommonDialog show() {
        //如果宽高都没有设置,则默认给弹窗提供宽度为800
        if (commonController.getWidth() <= 0 && commonController.getHeight() <= 0) {
            commonController.setWidth(600);
        }
        Log.d(TAG, "show");
        show(commonController.getFragmentManager());
        return this;
    }

    /*********************************************************************
     * 使用Builder模式实现
     *
     */
    public static class Builder {

        CommonController.TParams params;

        public Builder(FragmentManager fragmentManager) {
            params = new CommonController.TParams();
            params.mFragmentManager = fragmentManager;
        }

        //各种setXXX()方法设置数据
        public Builder setLayoutRes(@LayoutRes int layoutRes) {
            params.mLayoutRes = layoutRes;
            return this;
        }

        public Builder setDialogView(View view) {
            params.mDialogView = view;
            return this;
        }

        /**
         * 设置弹窗宽度是屏幕宽度的比例 0 -1
         */
        public Builder setScreenWidthAspect(Activity activity, float widthAspect) {
            params.mWidth = (int) (getWindowWidth(activity) * widthAspect);
            return this;
        }

        public Builder setWidth(int widthPx) {
            params.mWidth = widthPx;
            return this;
        }

        /**
         * 设置屏幕高度比例 0 -1
         */
        public Builder setScreenHeightAspect(Activity activity, float heightAspect) {
            params.mHeight = (int) (getWindowHeight(activity) * heightAspect);
            return this;
        }

        public Builder setHeight(int heightPx) {
            params.mHeight = heightPx;
            return this;
        }

        public Builder setGravity(int gravity) {
            params.mGravity = gravity;
            return this;
        }

        public Builder setCancelableOutside(boolean cancel) {
            params.mIsCancelableOutside = cancel;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            params.mCancelable = cancelable;
            return this;
        }

        public Builder setOnDismissListener(DialogInterface.OnDismissListener dismissListener) {
            params.mOnDismissListener = dismissListener;
            return this;
        }


        public Builder setDimAmount(float dim) {
            params.mDimAmount = dim;
            return this;
        }

        public Builder setTag(String tag) {
            params.mTag = tag;
            return this;
        }

        public Builder setOnBindViewListener(OnBindViewListener listener) {
            params.bindViewListener = listener;
            return this;
        }

        public Builder addOnClickListener(int... ids) {
            params.ids = ids;
            return this;
        }

        public Builder setOnViewClickListener(OnViewClickListener listener) {
            params.mOnViewClickListener = listener;
            return this;
        }

        public CommonDialog create() {
            CommonDialog dialog = new CommonDialog();
            Log.d(TAG, "create");
            //将数据从Buidler的DjParams中传递到DjDialog中
            params.apply(dialog.commonController);
            return dialog;
        }
    }
}
