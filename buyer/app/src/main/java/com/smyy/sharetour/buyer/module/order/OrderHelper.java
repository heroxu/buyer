package com.smyy.sharetour.buyer.module.order;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.uiframelib.BaseActivity;
import com.xmyy.view.dialoglib.CommonDialog;
import com.xmyy.view.dialoglib.base.BindViewHolder;
import com.xmyy.view.dialoglib.listener.OnBindViewListener;
import com.xmyy.view.dialoglib.listener.OnViewClickListener;

public class OrderHelper {

    /**
     * 设置底部按钮文本及相应操作（按钮从右往左排列）
     */
    public static void switchBottomBtns(final BaseActivity activity, boolean isSolid,
                                        TextView tvBottomBtn1, TextView tvBottomBtn2,
                                        TextView tvBottomBtn3, final TextView tvBottomBtnMore,
                                        String btnTxt1, final int orderOperateType1,
                                        String btnTxt2, final int orderOperateType2,
                                        String btnTxt3, final int orderOperateType3,
                                        String btnTxt4, final int orderOperateType4,
                                        String btnTxt5, final int orderOperateType5,
                                        String btnTxt6, final int orderOperateType6) {
        if (isSolid) {
            tvBottomBtn1.setBackgroundResource(R.drawable.bg_rounded_rectangle_ffcd1f);
            tvBottomBtn1.setTextColor(activity.getResources().getColor(R.color.txt_main));
        } else {
            tvBottomBtn1.setBackgroundResource(R.drawable.bg_rounded_rectangle_stroke_gray);
            tvBottomBtn1.setTextColor(activity.getResources().getColor(R.color.txt_gray_dark));
        }

        tvBottomBtn1.setText(btnTxt1);
        tvBottomBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchOrderOperate(activity, orderOperateType1);
            }
        });

        if (btnTxt2 == null) {
            tvBottomBtn2.setVisibility(View.GONE);
        } else {
            tvBottomBtn2.setVisibility(View.VISIBLE);
            tvBottomBtn2.setText(btnTxt2);
            tvBottomBtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switchOrderOperate(activity, orderOperateType2);
                }
            });
        }

        if (btnTxt3 == null) {
            tvBottomBtn3.setVisibility(View.GONE);
        } else {
            tvBottomBtn3.setVisibility(View.VISIBLE);
            tvBottomBtn3.setText(btnTxt3);
            tvBottomBtn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switchOrderOperate(activity, orderOperateType3);
                }
            });
        }

        if (btnTxt4 == null) {
            tvBottomBtnMore.setVisibility(View.GONE);
        } else {
            tvBottomBtnMore.setVisibility(View.VISIBLE);

            View popupView = View.inflate(activity, R.layout.layout_order_bottom_btns_pop, null);
            final PopupWindow mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            mPopupWindow.setTouchable(true);
            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.setBackgroundDrawable(new BitmapDrawable(activity.getResources(), (Bitmap) null));
            mPopupWindow.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

            TextView tvBottomBtn4 = (TextView) popupView.findViewById(R.id.tv_order_bottom_btn4);
            TextView tvBottomBtn5 = (TextView) popupView.findViewById(R.id.tv_order_bottom_btn5);
            View layBottomBtn5 = popupView.findViewById(R.id.lay_order_bottom_btn5);
            TextView tvBottomBtn6 = (TextView) popupView.findViewById(R.id.tv_order_bottom_btn6);
            View layBottomBtn6 = popupView.findViewById(R.id.lay_order_bottom_btn6);

            tvBottomBtn4.setText(btnTxt4);
            tvBottomBtn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switchOrderOperate(activity, orderOperateType4);
                }
            });

            if (btnTxt5 == null) {
                layBottomBtn5.setVisibility(View.GONE);
            } else {
                layBottomBtn5.setVisibility(View.VISIBLE);
                tvBottomBtn5.setText(btnTxt2);
                tvBottomBtn5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismiss();
                        switchOrderOperate(activity, orderOperateType5);
                    }
                });
            }

            if (btnTxt6 == null) {
                layBottomBtn6.setVisibility(View.GONE);
            } else {
                layBottomBtn6.setVisibility(View.VISIBLE);
                tvBottomBtn6.setText(btnTxt6);
                tvBottomBtn6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismiss();
                        switchOrderOperate(activity, orderOperateType6);
                    }
                });
            }

            tvBottomBtnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPopupWindow.showAsDropDown(tvBottomBtnMore,
                            0,
                            -tvBottomBtnMore.getHeight() - mPopupWindow.getContentView().getHeight() + tvBottomBtnMore.getPaddingTop());
                }
            });
        }
    }

    public static void switchOrderOperate(final BaseActivity activity, int orderOperateType) {
        switch (orderOperateType) {
            case Consts.ORDER_OPERATE_VERIFY_VIDEO:

                break;

            case Consts.ORDER_OPERATE_CONTACT_BUYER:

                break;

            case Consts.ORDER_OPERATE_CONTACT_SELLER:

                break;

            case Consts.ORDER_OPERATE_REVIEW:
                activity.startActivity(OrderCommentActivity.class);
                break;

            case Consts.ORDER_OPERATE_VIEW_REVIEWS:

                break;

            case Consts.ORDER_OPERATE_REMIND_SHIPPING:

                break;

            case Consts.ORDER_OPERATE_VIEW_SHIPPING:

                break;

            case Consts.ORDER_OPERATE_DELETE:

                break;

            case Consts.ORDER_OPERATE_PAY:

                break;

            case Consts.ORDER_OPERATE_CONFIRM:

                break;

            case Consts.ORDER_OPERATE_CONTACT_SERVICE:

                break;

            case Consts.ORDER_OPERATE_CANCEL:
                new CommonDialog.Builder(activity.getSupportFragmentManager())
                        .setLayoutRes(R.layout.dialog_cancel_order)
                        .setGravity(Gravity.BOTTOM)
                        .setAnimRes(R.style.BottomDialogAnim)
                        .setDimAmount(0.5f)
                        .setScreenWidthAspect(activity, 1)
                        .setOnBindViewListener(new OnBindViewListener() {
                            @Override
                            public void bindView(BindViewHolder viewHolder, CommonDialog dialog) {
                                viewHolder.setOnViewClickListener(R.id.select_reward_close, new OnViewClickListener() {
                                    @Override
                                    public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                                        commonDialog.dismiss();
                                    }
                                });

                                final Button btnCancel = viewHolder.getView(R.id.btn_order_cancel);
                                final RadioGroup radioGroup = viewHolder.getView(R.id.rg_order_cancel_reason);
                                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                                    @Override
                                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                                        radioGroup.getCheckedRadioButtonId();
                                        if (checkedId != -1) {
                                            btnCancel.setEnabled(true);
                                        } else {
                                            btnCancel.setEnabled(false);
                                        }
                                    }
                                });

                                viewHolder.setOnViewClickListener(R.id.btn_order_cancel, new OnViewClickListener() {
                                    @Override
                                    public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                                        commonDialog.dismiss();
                                        activity.finish();
                                    }
                                });
                            }
                        })
                        .create().show();
                break;
            default:
                break;
        }
    }
}
