package com.smyy.sharetour.buyer.dialog;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.xmyy.view.dialoglib.CommonDialog;
import com.xmyy.view.dialoglib.base.BindViewHolder;
import com.xmyy.view.dialoglib.listener.OnBindViewListener;
import com.xmyy.view.dialoglib.listener.OnViewClickListener;


public class DialogUtils {
    public static CommonDialog.Builder createMessageBoxDialogBuilder(
            @NonNull FragmentActivity activity, int type,
            String titleText,
            String messageText,
            int confirmColor,
            String confirmText, OnViewClickListener confirmClickListener,
            String cancelText, OnViewClickListener cancelClickListener) {
        return new MessageBoxBuilder(activity, type)
                .setText(titleText, messageText, confirmText, cancelText)
                .setConfirmButtonColor(confirmColor)
                .setClickListener(confirmClickListener, cancelClickListener)
                .build()
                .setCancelableOutside(false)
                .setCancelable(true);
    }

    public static CommonDialog.Builder createMessageBoxDialogBuilder(
            @NonNull FragmentActivity activity, int type,
            String titleText,
            String messageText,
            String confirmText, OnViewClickListener confirmClickListener,
            String cancelText, OnViewClickListener cancelClickListener) {
        return new MessageBoxBuilder(activity, type)
                .setText(titleText, messageText, confirmText, cancelText)
                .setClickListener(confirmClickListener, cancelClickListener)
                .build()
                .setCancelableOutside(false)
                .setCancelable(true);
    }

    public static CommonDialog.Builder createBottomMenuDialogBuilder(
            @NonNull FragmentActivity activity,
            String messageText, OnViewClickListener messageClickListener,
            String button1Text, OnViewClickListener button1ClickListener,
            String button2Text, OnViewClickListener button2ClickListener,
            String button3Text, OnViewClickListener button3ClickListener,
            String cancelText, OnViewClickListener cancelClickListener) {
        return new BottomMenuBuilder(activity)
                .setMessageText(messageText)
                .setMessageClickListener(messageClickListener)
                .setButtonText(button1Text, button2Text, button3Text)
                .setButtonClickListener(button1ClickListener, button2ClickListener, button3ClickListener)
                .setCancelText(cancelText)
                .setCancelClickListener(cancelClickListener)
                .build()
                .setAnimRes(R.style.BottomDialogAnim)
                .setCancelableOutside(false)
                .setCancelable(true);
    }

    /**
     * 两个按钮的消息对话框
     *
     * @param activity
     * @param messageText
     * @param confirmText          传入null则默认显示"确定"
     * @param confirmClickListener 传入null则默认dialog.dismiss
     * @param cancelText           传入null则默认显示"取消"
     * @param cancelClickListener  传入null则默认dialog.dismiss
     */
    public static void showTwoBtnMsgBox(
            @NonNull FragmentActivity activity, String titleText, String messageText, int confirmColor,
            String confirmText, OnViewClickListener confirmClickListener,
            String cancelText, OnViewClickListener cancelClickListener) {
        createMessageBoxDialogBuilder(
                activity, MessageBoxBuilder.TYPE_TWO_BTN,
                titleText,
                messageText,
                confirmColor,
                confirmText, confirmClickListener,
                cancelText, cancelClickListener).create().show();
    }

    public static void showTwoBtnMsgBox(
            @NonNull FragmentActivity activity, String titleText, String messageText,
            String confirmText, OnViewClickListener confirmClickListener,
            String cancelText, OnViewClickListener cancelClickListener) {
        createMessageBoxDialogBuilder(
                activity, MessageBoxBuilder.TYPE_TWO_BTN,
                titleText,
                messageText,
                confirmText, confirmClickListener,
                cancelText, cancelClickListener).create().show();
    }

    public static void showTwoBtnMsgBox(
            @NonNull FragmentActivity activity, String titleText, String messageText,
            String confirmText, OnViewClickListener confirmClickListener) {
        showTwoBtnMsgBox(
                activity, titleText, messageText,
                confirmText, confirmClickListener,
                null, null);
    }

    public static void showTwoBtnMsgBox(
            @NonNull FragmentActivity activity
            , String titleText, String messageText, OnViewClickListener confirmClickListener) {
        showTwoBtnMsgBox(
                activity, titleText, messageText,
                null, confirmClickListener,
                null, null);
    }

    /**
     * 一个按钮的消息对话框
     *
     * @param activity
     * @param messageText
     * @param confirmText          传入null则默认显示"确定"
     * @param confirmClickListener 传入null则默认dialog.dismiss
     */
    public static void showOneBtnMsgBox(
            @NonNull FragmentActivity activity, String titleText, String messageText,
            String confirmText, OnViewClickListener confirmClickListener) {
        createMessageBoxDialogBuilder(
                activity, MessageBoxBuilder.TYPE_ONE_BTN,
                titleText, messageText,
                confirmText, confirmClickListener,
                null, null).create().show();
    }

    public static void showOneBtnMsgBox(
            @NonNull FragmentActivity activity, String titleText, String messageText,
            OnViewClickListener confirmClickListener) {
        showOneBtnMsgBox(
                activity, titleText, messageText,
                null, confirmClickListener);
    }

    /**
     * 底部弹出菜单
     *
     * @param activity
     * @param button1Text          传入null则隐藏
     * @param button1ClickListener 传入null则无点击相应
     * @param button2Text
     * @param button2ClickListener
     * @param button3Text
     * @param button3ClickListener
     * @param cancelText           传入null则默认显示"取消"
     * @param cancelClickListener  传入null则默认dialog.dismiss
     */
    public static void showBottomMenu(
            @NonNull FragmentActivity activity,
            String button1Text, OnViewClickListener button1ClickListener,
            String button2Text, OnViewClickListener button2ClickListener,
            String button3Text, OnViewClickListener button3ClickListener,
            String cancelText, OnViewClickListener cancelClickListener) {
        createBottomMenuDialogBuilder(activity,
                null, null,
                button1Text, button1ClickListener,
                button2Text, button2ClickListener,
                button3Text, button3ClickListener,
                cancelText, cancelClickListener).create().show();
    }

    public static void showBottomMenu(
            @NonNull FragmentActivity activity,
            String button1Text, OnViewClickListener button1ClickListener,
            String button2Text, OnViewClickListener button2ClickListener,
            String button3Text, OnViewClickListener button3ClickListener) {
        showBottomMenu(activity,
                button1Text, button1ClickListener,
                button2Text, button2ClickListener,
                button3Text, button3ClickListener,
                null, null);
    }

    public static void showBottomMenu(
            @NonNull FragmentActivity activity,
            String button1Text, OnViewClickListener button1ClickListener,
            String button2Text, OnViewClickListener button2ClickListener) {
        showBottomMenu(activity,
                button1Text, button1ClickListener,
                button2Text, button2ClickListener,
                null, null);
    }

    public static void showBottomMenu(
            @NonNull FragmentActivity activity,
            String button1Text, OnViewClickListener button1ClickListener) {
        showBottomMenu(activity,
                button1Text, button1ClickListener,
                null, null);
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
            @NonNull FragmentActivity activity,
            String messageText, OnViewClickListener messageClickListener,
            String button1Text, OnViewClickListener button1ClickListener,
            String button2Text, OnViewClickListener button2ClickListener,
            String button3Text, OnViewClickListener button3ClickListener) {
        createBottomMenuDialogBuilder(activity,
                messageText, messageClickListener,
                button1Text, button1ClickListener,
                button2Text, button2ClickListener,
                button3Text, button3ClickListener,
                null, null).create().show();
    }

    public static void showBottomMsgMenu(
            @NonNull FragmentActivity activity,
            String messageText,
            String button1Text, OnViewClickListener button1ClickListener,
            String button2Text, OnViewClickListener button2ClickListener,
            String button3Text, OnViewClickListener button3ClickListener) {
        showBottomMsgMenu(activity,
                messageText, null,
                button1Text, button1ClickListener,
                button2Text, button2ClickListener,
                button3Text, button3ClickListener);
    }

    public static void showBottomMsgMenu(
            @NonNull FragmentActivity activity,
            String messageText, OnViewClickListener messageClickListener,
            String button1Text, OnViewClickListener button1ClickListener,
            String button2Text, OnViewClickListener button2ClickListener) {
        showBottomMsgMenu(activity,
                messageText, messageClickListener,
                button1Text, button1ClickListener,
                button2Text, button2ClickListener,
                null, null);
    }

    public static void showBottomMsgMenu(
            @NonNull FragmentActivity activity,
            String messageText,
            String button1Text, OnViewClickListener button1ClickListener,
            String button2Text, OnViewClickListener button2ClickListener) {
        showBottomMsgMenu(activity,
                messageText, null,
                button1Text, button1ClickListener,
                button2Text, button2ClickListener);
    }

    public static void showBottomMsgMenu(
            @NonNull FragmentActivity activity,
            String messageText, OnViewClickListener messageClickListener,
            String button1Text, OnViewClickListener button1ClickListener) {
        showBottomMsgMenu(activity,
                messageText, messageClickListener,
                button1Text, button1ClickListener,
                null, null);
    }

    public static void showBottomMsgMenu(
            @NonNull FragmentActivity activity,
            String messageText,
            String button1Text, OnViewClickListener button1ClickListener) {
        showBottomMsgMenu(activity,
                messageText, null,
                button1Text, button1ClickListener);
    }

    public static void showBottomMsgMenu(
            @NonNull FragmentActivity activity,
            String messageText, OnViewClickListener messageClickListener) {
        showBottomMsgMenu(activity,
                messageText, messageClickListener,
                null, null);
    }

    public static void showBottomMsgMenu(
            @NonNull FragmentActivity activity,
            String messageText) {
        showBottomMsgMenu(activity,
                messageText, null);
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
            CommonDialog.Builder builder = new CommonDialog.Builder(mActivity.getSupportFragmentManager())
                    .setLayoutRes(R.layout.dialog_bottom_menu)
                    .setScreenWidthAspect(mActivity, 1)
                    .setGravity(Gravity.BOTTOM)
                    .setOnBindViewListener(new OnBindViewListener() {
                        @Override
                        public void bindView(BindViewHolder viewHolder, CommonDialog dialog) {
                            if (mMsgTx == null) {
                                viewHolder.setVisibility(R.id.lay_dialog_btm_msg, View.GONE);
                            } else {
                                viewHolder.setVisibility(R.id.lay_dialog_btm_msg, View.VISIBLE);
                                viewHolder.setText(R.id.tv_dialog_btm_msg, mMsgTx);
                                viewHolder.setOnViewClickListener(R.id.tv_dialog_btm_msg, mMsgClickListener);
                            }

                            if (mBtn1Tx == null) {
                                viewHolder.setVisibility(R.id.lay_dialog_btm1, View.GONE);
                            } else {
                                viewHolder.setVisibility(R.id.lay_dialog_btm1, View.VISIBLE);
                                viewHolder.setText(R.id.tv_dialog_btm1, mBtn1Tx);
                                viewHolder.setOnViewClickListener(R.id.tv_dialog_btm1, mBtn1ClickListener);
                            }

                            if (mBtn2Tx == null) {
                                viewHolder.setVisibility(R.id.lay_dialog_btm2, View.GONE);
                            } else {
                                viewHolder.setVisibility(R.id.lay_dialog_btm2, View.VISIBLE);
                                viewHolder.setText(R.id.tv_dialog_btm2, mBtn2Tx);
                                viewHolder.setOnViewClickListener(R.id.tv_dialog_btm2, mBtn2ClickListener);
                            }

                            if (mBtn3Tx == null) {
                                viewHolder.setVisibility(R.id.lay_dialog_btm3, View.GONE);
                            } else {
                                viewHolder.setVisibility(R.id.lay_dialog_btm3, View.VISIBLE);
                                viewHolder.setText(R.id.tv_dialog_btm3, mBtn3Tx);
                                viewHolder.setOnViewClickListener(R.id.tv_dialog_btm3, mBtn3ClickListener);
                            }

                            viewHolder.setText(R.id.tv_dialog_btm_cancel,
                                    mCancelTx == null ? "取消" : mCancelTx);
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


    private static class MessageBoxBuilder {
        private String mMsgTx;
        private String mTitleTx;
        private String mConfirmTx = "确定";
        private String mCancelTx = "取消";
        private int mConfirmColor = R.color.txt_red;
        private OnViewClickListener mConfirmClickListener;
        private OnViewClickListener mCancelClickListener;


        private FragmentActivity mActivity;

        private int mType = 2;
        public static final int TYPE_ONE_BTN = 1;
        public static final int TYPE_TWO_BTN = 2;


        public MessageBoxBuilder(@NonNull FragmentActivity activity, int type) {
            this.mActivity = activity;
            this.mType = type;
        }

        public MessageBoxBuilder setText(String titleText, String msgText, String confirmText, String cancelText) {
            this.mMsgTx = msgText;
            this.mTitleTx = titleText;
            this.mConfirmTx = confirmText;
            this.mCancelTx = cancelText;
            return this;
        }

        public MessageBoxBuilder setConfirmButtonColor(int resId){
            this.mConfirmColor = resId;
            return this;
        }

        public MessageBoxBuilder setClickListener(OnViewClickListener confirmClickListener,
                                                  OnViewClickListener cancelClickListener) {
            this.mConfirmClickListener = confirmClickListener;
            this.mCancelClickListener = cancelClickListener;
            return this;
        }

        public CommonDialog.Builder build() {
            CommonDialog.Builder builder = new CommonDialog.Builder(mActivity.getSupportFragmentManager())
                    .setLayoutRes(R.layout.dialog_message_box)
                    .setGravity(Gravity.CENTER)
                    .setDimAmount(0.5f)
                    .setOnBindViewListener(new OnBindViewListener() {
                        @Override
                        public void bindView(BindViewHolder viewHolder, CommonDialog dialog) {
                            viewHolder.setText(R.id.tv_dialog_msg, mMsgTx);
                            if(mTitleTx!=null) {
                                viewHolder.setText(R.id.tv_dialog_title, mTitleTx);
                            } else {
                                viewHolder.getView(R.id.tv_dialog_title).setVisibility(View.GONE);
                            }

                            ((TextView)viewHolder.getView(R.id.tv_dialog_confirm)).setTextColor(
                                    mActivity.getResources().getColor(mConfirmColor));

                            if (mConfirmTx == null) {
                                mConfirmTx = "确定";
                            }
                            if (mConfirmClickListener == null) {
                                mConfirmClickListener = new OnViewClickListener() {
                                    @Override
                                    public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                                        commonDialog.dismiss();
                                    }
                                };
                            }

                            switch (mType) {
                                case TYPE_ONE_BTN:
                                    viewHolder.setVisibility(R.id.tv_dialog_one_btn, View.VISIBLE);
                                    viewHolder.setVisibility(R.id.lay_dialog_two_btn, View.GONE);

                                    viewHolder.setText(R.id.tv_dialog_one_btn, mConfirmTx);
                                    viewHolder.setOnViewClickListener(R.id.tv_dialog_one_btn, mConfirmClickListener);
                                    break;

                                case TYPE_TWO_BTN:
                                    viewHolder.setVisibility(R.id.tv_dialog_one_btn, View.GONE);
                                    viewHolder.setVisibility(R.id.lay_dialog_two_btn, View.VISIBLE);

                                    viewHolder.setText(R.id.tv_dialog_confirm, mConfirmTx);
                                    viewHolder.setOnViewClickListener(R.id.tv_dialog_confirm, mConfirmClickListener);


                                    if (mCancelTx == null) {
                                        mCancelTx = "取消";
                                    }
                                    if (mCancelClickListener == null) {
                                        mCancelClickListener = new OnViewClickListener() {
                                            @Override
                                            public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                                                commonDialog.dismiss();
                                            }
                                        };
                                    }
                                    viewHolder.setText(R.id.tv_dialog_cancel, mCancelTx);
                                    viewHolder.setOnViewClickListener(R.id.tv_dialog_cancel, mCancelClickListener);
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
            return builder;
        }
    }

}
