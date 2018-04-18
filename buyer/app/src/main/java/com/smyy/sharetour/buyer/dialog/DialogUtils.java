package com.smyy.sharetour.buyer.dialog;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;

import com.smyy.sharetour.buyer.R;
import com.xmyy.view.dialoglib.CommonDialog;
import com.xmyy.view.dialoglib.base.BindViewHolder;
import com.xmyy.view.dialoglib.listener.OnBindViewListener;
import com.xmyy.view.dialoglib.listener.OnViewClickListener;


public class DialogUtils {
    public static CommonDialog.Builder createBottomMenuDialogBuilder(
            @NonNull FragmentActivity activity
            , String messageText, OnViewClickListener messageClickListener
            , String button1Text, OnViewClickListener button1ClickListener
            , String button2Text, OnViewClickListener button2ClickListener
            , String button3Text, OnViewClickListener button3ClickListener
            , String cancelText, OnViewClickListener cancelClickListener) {
        return new BottomMenuBuilder(activity)
                .setMessageText(messageText)
                .setMessageClickListener(messageClickListener)
                .setButtonText(button1Text, button2Text, button3Text)
                .setButtonClickListener(button1ClickListener, button2ClickListener, button3ClickListener)
                .setCancelText(cancelText)
                .setCancelClickListener(cancelClickListener)
                .build()
                .setCancelableOutside(false)
                .setCancelable(true);
    }

    /**
     * 底部弹出菜单
     *
     * @param activity
     * @param button1Text
     * @param button1ClickListener
     * @param button2Text
     * @param button2ClickListener
     * @param button3Text
     * @param button3ClickListener
     */
    public static void showBottomMenu(
            @NonNull FragmentActivity activity
            , String button1Text, OnViewClickListener button1ClickListener
            , String button2Text, OnViewClickListener button2ClickListener
            , String button3Text, OnViewClickListener button3ClickListener) {
        createBottomMenuDialogBuilder(activity
                , null, null
                , button1Text, button1ClickListener
                , button2Text, button2ClickListener
                , button3Text, button3ClickListener
                , null, null).create().show();
    }

    public static void showBottomMenu(
            @NonNull FragmentActivity activity
            , String button1Text, OnViewClickListener button1ClickListener
            , String button2Text, OnViewClickListener button2ClickListener) {
        showBottomMenu(activity
                , button1Text, button1ClickListener
                , button2Text, button2ClickListener
                , null, null);
    }

    public static void showBottomMenu(
            @NonNull FragmentActivity activity
            , String button1Text, OnViewClickListener button1ClickListener) {
        showBottomMenu(activity
                , button1Text, button1ClickListener
                , null, null);
    }

    /**
     * 头部有Message文本框的底部弹出菜单
     *
     * @param activity
     * @param messageText
     * @param messageClickListener
     * @param button1Text
     * @param button1ClickListener
     * @param button2Text
     * @param button2ClickListener
     * @param button3Text
     * @param button3ClickListener
     */
    public static void showBottomMsgMenu(
            @NonNull FragmentActivity activity
            , String messageText, OnViewClickListener messageClickListener
            , String button1Text, OnViewClickListener button1ClickListener
            , String button2Text, OnViewClickListener button2ClickListener
            , String button3Text, OnViewClickListener button3ClickListener) {
        createBottomMenuDialogBuilder(activity
                , messageText, messageClickListener
                , button1Text, button1ClickListener
                , button2Text, button2ClickListener
                , button3Text, button3ClickListener
                , null, null).create().show();
    }

    public static void showBottomMsgMenu(
            @NonNull FragmentActivity activity
            , String messageText
            , String button1Text, OnViewClickListener button1ClickListener
            , String button2Text, OnViewClickListener button2ClickListener
            , String button3Text, OnViewClickListener button3ClickListener) {
        showBottomMsgMenu(activity
                , messageText, null
                , button1Text, button1ClickListener
                , button2Text, button2ClickListener
                , button3Text, button3ClickListener);
    }

    public static void showBottomMsgMenu(
            @NonNull FragmentActivity activity
            , String messageText, OnViewClickListener messageClickListener
            , String button1Text, OnViewClickListener button1ClickListener
            , String button2Text, OnViewClickListener button2ClickListener) {
        showBottomMsgMenu(activity
                , messageText, messageClickListener
                , button1Text, button1ClickListener
                , button2Text, button2ClickListener
                , null, null);
    }

    public static void showBottomMsgMenu(
            @NonNull FragmentActivity activity
            , String messageText
            , String button1Text, OnViewClickListener button1ClickListener
            , String button2Text, OnViewClickListener button2ClickListener) {
        showBottomMsgMenu(activity
                , messageText, null
                , button1Text, button1ClickListener
                , button2Text, button2ClickListener);
    }

    public static void showBottomMsgMenu(
            @NonNull FragmentActivity activity
            , String messageText, OnViewClickListener messageClickListener
            , String button1Text, OnViewClickListener button1ClickListener) {
        showBottomMsgMenu(activity
                , messageText, messageClickListener
                , button1Text, button1ClickListener
                , null, null);
    }

    public static void showBottomMsgMenu(
            @NonNull FragmentActivity activity
            , String messageText
            , String button1Text, OnViewClickListener button1ClickListener) {
        showBottomMsgMenu(activity
                , messageText, null
                , button1Text, button1ClickListener);
    }

    public static void showBottomMsgMenu(
            @NonNull FragmentActivity activity
            , String messageText, OnViewClickListener messageClickListener) {
        showBottomMsgMenu(activity
                , messageText, messageClickListener
                , null, null);
    }

    public static void showBottomMsgMenu(
            @NonNull FragmentActivity activity
            , String messageText) {
        showBottomMsgMenu(activity
                , messageText, null);
    }

    private static class BottomMenuBuilder {
        private String mMsgTx;
        private String mBtn1Tx;
        private String mBtn2Tx;
        private String mBtn3Tx;
        private String mCancelTx = "取消";
        private OnViewClickListener mMsgClickListener;
        private OnViewClickListener mBtn1ClickListener;
        private OnViewClickListener mBtn2ClickListener;
        private OnViewClickListener mBtn3ClickListener;
        private OnViewClickListener mCancelClickListener;


        private FragmentActivity mActivity;


        public BottomMenuBuilder(@NonNull FragmentActivity activity) {
            this.mActivity = activity;
        }

        public BottomMenuBuilder setButtonText(String btn1Text, String btn2Text, String btn3Text) {
            this.mBtn1Tx = btn1Text;
            this.mBtn2Tx = btn2Text;
            this.mBtn3Tx = btn3Text;
            return this;
        }

        public BottomMenuBuilder setCancelText(String cancelText) {
            this.mCancelTx = cancelText;
            return this;
        }

        public BottomMenuBuilder setMessageText(String messageText) {
            this.mMsgTx = messageText;
            return this;
        }

        public BottomMenuBuilder setButtonClickListener(OnViewClickListener btn1ClickListener,
                                                        OnViewClickListener btn2ClickListener,
                                                        OnViewClickListener btn3ClickListener) {
            this.mBtn1ClickListener = btn1ClickListener;
            this.mBtn2ClickListener = btn2ClickListener;
            this.mBtn3ClickListener = btn3ClickListener;
            return this;
        }

        public BottomMenuBuilder setCancelClickListener(OnViewClickListener cancelClickListener) {
            this.mCancelClickListener = cancelClickListener;
            return this;
        }

        public BottomMenuBuilder setMessageClickListener(OnViewClickListener messageClickListener) {
            this.mMsgClickListener = messageClickListener;
            return this;
        }

        public CommonDialog.Builder build() {
            CommonDialog.Builder builder = new CommonDialog.Builder(mActivity
                    .getSupportFragmentManager())
                    .setLayoutRes(R.layout.dialog_bottom_menu)
                    .setScreenWidthAspect(mActivity, 1)
                    .setGravity(Gravity.BOTTOM)
                    .setOnBindViewListener(new OnBindViewListener() {
                        @Override
                        public void bindView(BindViewHolder viewHolder, CommonDialog dialog) {
                            if (TextUtils.isEmpty(mMsgTx)) {
                                viewHolder.setVisibility(R.id.lay_dialog_btm_msg, View.GONE);
                            } else {
                                viewHolder.setVisibility(R.id.lay_dialog_btm_msg, View.VISIBLE);
                                viewHolder.setText(R.id.tv_dialog_btm_msg, mMsgTx);
                                viewHolder.setOnViewClickListener(R.id.tv_dialog_btm_msg, mMsgClickListener);
                            }

                            if (TextUtils.isEmpty(mBtn1Tx)) {
                                viewHolder.setVisibility(R.id.lay_dialog_btm1, View.GONE);
                            } else {
                                viewHolder.setVisibility(R.id.lay_dialog_btm1, View.VISIBLE);
                                viewHolder.setText(R.id.tv_dialog_btm1, mBtn1Tx);
                                viewHolder.setOnViewClickListener(R.id.tv_dialog_btm1, mBtn1ClickListener);
                            }

                            if (TextUtils.isEmpty(mBtn2Tx)) {
                                viewHolder.setVisibility(R.id.lay_dialog_btm2, View.GONE);
                            } else {
                                viewHolder.setVisibility(R.id.lay_dialog_btm2, View.VISIBLE);
                                viewHolder.setText(R.id.tv_dialog_btm2, mBtn2Tx);
                                viewHolder.setOnViewClickListener(R.id.tv_dialog_btm2, mBtn2ClickListener);
                            }

                            if (TextUtils.isEmpty(mBtn3Tx)) {
                                viewHolder.setVisibility(R.id.lay_dialog_btm3, View.GONE);
                            } else {
                                viewHolder.setVisibility(R.id.lay_dialog_btm3, View.VISIBLE);
                                viewHolder.setText(R.id.tv_dialog_btm3, mBtn3Tx);
                                viewHolder.setOnViewClickListener(R.id.tv_dialog_btm3, mBtn3ClickListener);
                            }

                            viewHolder.setText(R.id.tv_dialog_btm_cancel,
                                    TextUtils.isEmpty(mCancelTx) ? "取消" : mCancelTx);
                            if (mCancelClickListener == null) {
                                mCancelClickListener = new OnViewClickListener() {
                                    @Override
                                    public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                                        commonDialog.dismiss();
                                    }
                                };
                            }
                            viewHolder.setOnViewClickListener(R.id.tv_dialog_btm_cancel, mCancelClickListener);
                        }
                    });
            return builder;
        }
    }

}
