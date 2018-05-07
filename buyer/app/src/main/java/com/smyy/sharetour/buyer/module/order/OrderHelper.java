package com.smyy.sharetour.buyer.module.order;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.backpacker.order.UploadShippingInfoActivity;
import com.smyy.sharetour.buyer.dialog.DialogUtils;
import com.smyy.sharetour.buyer.module.order.adapter.OrderReviewsAdapter;
import com.smyy.sharetour.buyer.module.order.bean.OrderBean;
import com.smyy.sharetour.buyer.module.order.bean.OrderDetailBean;
import com.smyy.sharetour.buyer.module.order.bean.OrderReviewsBean;
import com.smyy.sharetour.buyer.module.order.presenter.OrderPresenter;
import com.smyy.sharetour.buyer.tim.ChatActivity;
import com.smyy.sharetour.buyer.util.StringUtil;
import com.smyy.sharetour.uiframelib.BaseActivity;
import com.tencent.imsdk.TIMConversationType;
import com.xmyy.view.dialoglib.CommonDialog;
import com.xmyy.view.dialoglib.base.BindViewHolder;
import com.xmyy.view.dialoglib.listener.OnBindViewListener;
import com.xmyy.view.dialoglib.listener.OnViewClickListener;

import java.util.ArrayList;
import java.util.List;

public class OrderHelper {

    //商品类型
    public static final int GOODS_TYPE_DEMAND = 1;//需求型商品
    public static final int GOODS_TYPE_PRESELL = 2;//预售型商品
    public static final int GOODS_TYPE_STOCK = 3;//现货型商品

    public static final String ORDER_ID = "order_id";
    //订单类型
    public static final String ORDER_TYPE = "order_type";
    public static final int TYPE_ALL = 0;
    public static final int TYPE_AWAIT_PAY = 1;//待付款
    public static final int TYPE_AWAIT_SHIPPING = 2;//待发货
    public static final int TYPE_AWAIT_CONFIRM = 3;//待收货 已发货
    public static final int TYPE_AWAIT_REVIEW = 4;//待评价
    public static final int TYPE_DISPUTE = 5;//售后

    //买家订单状态
    public static final int STATUS_BUYER_AWAIT_PAY = 1;
    public static final int STATUS_BUYER_AWAIT_SHIPPING = 2;//待发货
    public static final int STATUS_BUYER_AWAIT_CONFIRM = 3;//待收货
    public static final int STATUS_BUYER_AWAIT_REVIEW = 4;//交易成功(待评价)
    public static final int STATUS_BUYER_ADD_REVIEW = 5;//交易成功(待追评)
    public static final int STATUS_BUYER_DONE = 6;//交易成功(无其他操作)
    public static final int STATUS_BUYER_DUR_DISPUTE = 7;//售后处理中
    public static final int STATUS_BUYER_DISPUTE_SUCCESS = 8;//退款成功
    public static final int STATUS_BUYER_CLOSED = 9;
    //订单状态对应文本
    public static final String[] STATUS_STRINGS_BUYER = {"", "待付款", "待发货",
            "待收货", "交易成功", "交易成功", "交易成功", "售后处理中", "退款成功", "交易关闭"};


    //卖家订单状态
    public static final int STATUS_SELLER_AWAIT_PAY = 1;
    public static final int STATUS_SELLER_AWAIT_SHIPPING = 2;//待发货
    public static final int STATUS_SELLER_SHIPPED = 3;//已发货
    public static final int STATUS_SELLER_DONE = 4;//交易成功
    public static final int STATUS_SELLER_DUR_DISPUTE = 5;//售后处理中
    public static final int STATUS_SELLER_DISPUTE_SUCCESS = 6;//退款成功
    public static final int STATUS_SELLER_CLOSED = 7;
    //订单状态对应文本
    public static final String[] STATUS_STRINGS_SELLER = {"", "等待买家付款", "待发货",
            "已发货", "交易成功", "售后处理中", "退款成功", "交易关闭"};

    //订单操作
    public static final int OPERATE_VERIFY_VIDEO = 1;//鉴定视频
    public static final int OPERATE_CONTACT_SELLER = 2;//联系买手
    public static final int OPERATE_CONTACT_BUYER = 3;//联系卖家
    public static final int OPERATE_REMIND_SHIPPING = 4;//提醒发货
    public static final int OPERATE_VIEW_SHIPPING = 5;//查看物流
    public static final int OPERATE_DELETE = 6;//删除订单
    public static final int OPERATE_CANCEL = 7;//取消订单
    public static final int OPERATE_PAY = 8;//支付
    public static final int OPERATE_CONFIRM = 9;//确认收货
    public static final int OPERATE_REVIEW = 10;//评价
    public static final int OPERATE_ADD_REVIEW = 11;//追加评价
    public static final int OPERATE_VIEW_REVIEWS = 12;//查看评价
    public static final int OPERATE_CONTACT_SERVICE = 13;//联系客服
    public static final int OPERATE_TO_SHIPPING = 14;//发货
    public static final int OPERATE_DISPUTE_DETAIL = 15;//售后详情
    public static final int OPERATE_DISPUTE_UNSHIPPED = 16;//申请售后（待发货）
    public static final int OPERATE_DISPUTE_SHIPPED = 17;//申请售后（待收货）


    /**
     * 根据传入的参数进行相应操作
     *
     * @param id 可以是orderId、disputeOrderId、鉴定视频地址、用户聊天id
     */
    public static void switchOperate(final BaseActivity activity, int userType,
                                     OrderPresenter presenter, int position, String id,
                                     int orderOperateType) {
        Bundle bundle = new Bundle();
        bundle.putInt(Consts.USER_TYPE, userType);
        switch (orderOperateType) {
            case OPERATE_DISPUTE_SHIPPED:
                showBtmOptionDialog(activity, R.layout.dialog_dispute_shipped, new OnOptionConfirmListener() {
                    @Override
                    public void onConfirm(RadioGroup radioGroup) {

                    }
                });

                break;

            case OPERATE_DISPUTE_UNSHIPPED:
                showBtmOptionDialog(activity, R.layout.dialog_dispute_unshipped, new OnOptionConfirmListener() {
                    @Override
                    public void onConfirm(RadioGroup radioGroup) {

                    }
                });

                break;

            case OPERATE_DISPUTE_DETAIL:
                activity.startActivity(DisputeOrderDetailActivity.class, bundle);
                break;

            case OPERATE_VERIFY_VIDEO:

                break;

            case OPERATE_CONTACT_BUYER:
                ChatActivity.navToChat(activity, id, TIMConversationType.C2C);
                break;

            case OPERATE_CONTACT_SELLER:
                ChatActivity.navToChat(activity, id, TIMConversationType.C2C);
                break;

            case OPERATE_REVIEW:
                activity.startActivity(OrderListCommentActivity.class);
                break;

            case OPERATE_ADD_REVIEW:
                Intent intent = new Intent(activity, OrderListCommentActivity.class);
                intent.putExtra(OrderListCommentActivity.COMMENT_IS_APPEND, true);
                activity.startActivity(intent);
                break;


            case OPERATE_VIEW_REVIEWS:
                new CommonDialog.Builder(activity.getSupportFragmentManager())
                        .setLayoutRes(R.layout.dialog_order_reviews)
                        .setGravity(Gravity.BOTTOM)
                        .setAnimRes(R.style.BottomDialogAnim)
                        .setDimAmount(0.5f)
                        .setScreenWidthAspect(activity, 1)
                        .setOnBindViewListener(new OnBindViewListener() {
                            @Override
                            public void bindView(BindViewHolder viewHolder, CommonDialog dialog) {
                                viewHolder.setOnViewClickListener(R.id.icon_close, new OnViewClickListener() {
                                    @Override
                                    public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                                        commonDialog.dismiss();
                                    }
                                });
                                RecyclerView rvReviews = viewHolder.getView(R.id.rv_order_reviews);

                                OrderReviewsAdapter adapter = new OrderReviewsAdapter(activity);
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
                                rvReviews.setLayoutManager(linearLayoutManager);
                                rvReviews.setAdapter(adapter);

                                List<OrderReviewsBean> datas = new ArrayList<>();
                                datas.add(new OrderReviewsBean());
                                datas.add(new OrderReviewsBean());
                                datas.add(new OrderReviewsBean());
                                datas.add(new OrderReviewsBean());
                                adapter.setData(datas);
                            }
                        })
                        .create().show();
                break;

            case OPERATE_REMIND_SHIPPING:

                break;

            case OPERATE_TO_SHIPPING:
                activity.startActivity(UploadShippingInfoActivity.class);
                break;

            case OPERATE_VIEW_SHIPPING:
                activity.startActivity(ShippingInfoActivity.class);
                break;

            case OPERATE_DELETE:
                if (position >= 0 && !StringUtil.isEmpty(id)) {
                    presenter.deleteOrder(position, id);
                }
                break;

            case OPERATE_PAY:

                break;

            case OPERATE_CONFIRM:

                break;

            case OPERATE_CONTACT_SERVICE:
                DialogUtils.showTwoBtnMsgBox(activity, null, Consts.SERVICE_TEL, "呼叫",
                        new OnViewClickListener() {
                            @Override
                            public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                                commonDialog.dismiss();
                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri
                                        .parse("tel:" + Consts.SERVICE_TEL));
                                activity.startActivity(intent);
                            }
                        });
                break;

            case OPERATE_CANCEL:
                showBtmOptionDialog(activity, R.layout.dialog_cancel_order, new OnOptionConfirmListener() {
                    @Override
                    public void onConfirm(RadioGroup radioGroup) {

                    }
                });
                break;
            default:
                break;
        }
    }

    public static void switchOperate(final BaseActivity activity, int userType,
                                     OrderPresenter presenter, String id, int orderOperateType) {
        switchOperate(activity, userType, null, -1, id, orderOperateType);
    }

    public static void switchOperate(final BaseActivity activity, int userType, String id, int orderOperateType) {
        switchOperate(activity, userType, null, id, orderOperateType);
    }

    public static void switchOperate(final BaseActivity activity, int userType, int orderOperateType) {
        switchOperate(activity, userType, null, orderOperateType);
    }

    public static void switchOperate(final BaseActivity activity, int orderOperateType) {
        switchOperate(activity, -1, orderOperateType);
    }

    /**
     * 设置底部按钮文本及相应操作（按钮从右往左排列）
     */
    private static void switchBottomBtns(final BaseActivity activity,
                                         final int userType, final OrderPresenter presenter,
                                         final int position, boolean isSolid,
                                         TextView tvBottomBtn1, TextView tvBottomBtn2,
                                         TextView tvBottomBtn3, final TextView tvBottomBtnMore,
                                         String btnTxt1, final int orderOperateType1, final String id1,
                                         String btnTxt2, final int orderOperateType2, final String id2,
                                         String btnTxt3, final int orderOperateType3, final String id3,
                                         String btnTxt4, final int orderOperateType4, final String id4,
                                         String btnTxt5, final int orderOperateType5, final String id5,
                                         String btnTxt6, final int orderOperateType6, final String id6) {
        if (isSolid) {
            tvBottomBtn1.setBackgroundResource(R.drawable.bg_rounded_rectangle_ffcd1f);
            tvBottomBtn1.setTextColor(activity.getResources().getColor(R.color.txt_main));
        } else {
            tvBottomBtn1.setBackgroundResource(R.drawable.bg_rounded_rectangle_stroke_gray);
            tvBottomBtn1.setTextColor(activity.getResources().getColor(R.color.txt_gray_dark));
        }

        tvBottomBtn1.setText(btnTxt1);
        if (orderOperateType1 == -1) {
            tvBottomBtn1.setClickable(false);
        } else {
            tvBottomBtn1.setClickable(true);
            tvBottomBtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switchOperate(activity, userType, presenter, position, id1, orderOperateType1);
                }
            });
        }

        if (btnTxt2 == null) {
            tvBottomBtn2.setVisibility(View.GONE);
        } else {
            tvBottomBtn2.setVisibility(View.VISIBLE);
            tvBottomBtn2.setText(btnTxt2);
            if (orderOperateType2 == -1) {
                tvBottomBtn2.setClickable(false);
            } else {
                tvBottomBtn2.setClickable(true);
                tvBottomBtn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switchOperate(activity, userType, presenter, position, id2, orderOperateType2);
                    }
                });
            }
        }

        if (btnTxt3 == null) {
            tvBottomBtn3.setVisibility(View.GONE);
        } else {
            tvBottomBtn3.setVisibility(View.VISIBLE);
            tvBottomBtn3.setText(btnTxt3);
            if (orderOperateType3 == -1) {
                tvBottomBtn3.setClickable(false);
            } else {
                tvBottomBtn3.setClickable(true);
                tvBottomBtn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switchOperate(activity, userType, presenter, position, id3, orderOperateType3);
                    }
                });
            }
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
            if (orderOperateType4 == -1) {
                tvBottomBtn4.setClickable(false);
            } else {
                tvBottomBtn4.setClickable(true);
                tvBottomBtn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismiss();
                        switchOperate(activity, userType, presenter, position, id4, orderOperateType4);
                    }
                });
            }

            if (btnTxt5 == null) {
                layBottomBtn5.setVisibility(View.GONE);
            } else {
                layBottomBtn5.setVisibility(View.VISIBLE);
                tvBottomBtn5.setText(btnTxt5);
                if (orderOperateType5 == -1) {
                    tvBottomBtn5.setClickable(false);
                } else {
                    tvBottomBtn5.setClickable(true);
                    tvBottomBtn5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mPopupWindow.dismiss();
                            switchOperate(activity, userType, presenter, position, id5, orderOperateType5);
                        }
                    });
                }
            }

            if (btnTxt6 == null) {
                layBottomBtn6.setVisibility(View.GONE);
            } else {
                layBottomBtn6.setVisibility(View.VISIBLE);
                tvBottomBtn6.setText(btnTxt6);
                if (orderOperateType6 == -1) {
                    tvBottomBtn6.setClickable(false);
                } else {
                    tvBottomBtn6.setClickable(true);
                    tvBottomBtn6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mPopupWindow.dismiss();
                            switchOperate(activity, userType, presenter, position, id6, orderOperateType6);
                        }
                    });
                }
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

    private static void switchBottomBtns(final BaseActivity activity,
                                         final int userType, final OrderPresenter presenter,
                                         boolean isSolid,
                                         TextView tvBottomBtn1, TextView tvBottomBtn2,
                                         TextView tvBottomBtn3, final TextView tvBottomBtnMore,
                                         String btnTxt1, final int orderOperateType1, final String id1,
                                         String btnTxt2, final int orderOperateType2, final String id2,
                                         String btnTxt3, final int orderOperateType3, final String id3,
                                         String btnTxt4, final int orderOperateType4, final String id4,
                                         String btnTxt5, final int orderOperateType5, final String id5,
                                         String btnTxt6, final int orderOperateType6, final String id6) {
        switchBottomBtns(activity,
                userType, presenter,
                -1, isSolid,
                tvBottomBtn1, tvBottomBtn2,
                tvBottomBtn3, tvBottomBtnMore,
                btnTxt1, orderOperateType1, id1,
                btnTxt2, orderOperateType2, id2,
                btnTxt3, orderOperateType3, id3,
                btnTxt4, orderOperateType4, id4,
                btnTxt5, orderOperateType5, id5,
                btnTxt6, orderOperateType6, id6);
    }

    public static void switchListBottomBtns(BaseActivity activity, int userType,
                                            OrderPresenter presenter,
                                            int position, OrderBean orderBean,
                                            View layBottomBtns, TextView tvBottomBtn1, TextView tvBottomBtn2,
                                            TextView tvBottomBtn3, TextView tvBottomBtnMore) {
        int orderStatus = orderBean.getOrderStatus();
        String orderId = orderBean.getOrderId();
        if (userType == Consts.USER_TYPE_BUYER) {
            switch (orderStatus) {
                case STATUS_BUYER_AWAIT_PAY:
                    layBottomBtns.setVisibility(View.VISIBLE);
                    switchBottomBtns(activity, userType, presenter, true,
                            tvBottomBtn1, tvBottomBtn2,
                            tvBottomBtn3, tvBottomBtnMore,
                            "支付", OPERATE_PAY, orderId,
                            "取消订单", OPERATE_CANCEL, orderId,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null);
                    break;

                case STATUS_BUYER_AWAIT_SHIPPING:
                    if (orderBean.getGoodsType() == GOODS_TYPE_DEMAND) {
                        layBottomBtns.setVisibility(View.GONE);
                    } else {
                        layBottomBtns.setVisibility(View.VISIBLE);
                        switchBottomBtns(activity, userType, presenter, false,
                                tvBottomBtn1, tvBottomBtn2,
                                tvBottomBtn3, tvBottomBtnMore,
                                "取消订单", OPERATE_CANCEL, orderId,
                                null, -1, null,
                                null, -1, null,
                                null, -1, null,
                                null, -1, null,
                                null, -1, null);
                    }

                    break;

                case STATUS_BUYER_AWAIT_CONFIRM:
                    layBottomBtns.setVisibility(View.VISIBLE);
                    switchBottomBtns(activity, userType, presenter, true,
                            tvBottomBtn1, tvBottomBtn2,
                            tvBottomBtn3, tvBottomBtnMore,
                            "确认收货", OPERATE_CONFIRM, orderId,
                            "查看物流", OPERATE_VIEW_SHIPPING, orderId,
                            TextUtils.isEmpty(orderBean.getVerifyVideo()) ? null : "鉴定视频",
                            OPERATE_VERIFY_VIDEO, orderBean.getVerifyVideo(),
                            null, -1, null,
                            null, -1, null,
                            null, -1, null);

                    break;

                case STATUS_BUYER_AWAIT_REVIEW:
                    layBottomBtns.setVisibility(View.VISIBLE);
                    switchBottomBtns(activity, userType, presenter, true,
                            tvBottomBtn1, tvBottomBtn2,
                            tvBottomBtn3, tvBottomBtnMore,
                            "评价", OPERATE_REVIEW, orderId,
                            "查看物流", OPERATE_VIEW_SHIPPING, orderId,
                            TextUtils.isEmpty(orderBean.getVerifyVideo()) ? null : "鉴定视频",
                            OPERATE_VERIFY_VIDEO, orderBean.getVerifyVideo(),
                            null, -1, null,
                            null, -1, null,
                            null, -1, null);

                    break;

                case STATUS_BUYER_ADD_REVIEW:
                    layBottomBtns.setVisibility(View.VISIBLE);
                    switchBottomBtns(activity, userType, presenter, true,
                            tvBottomBtn1, tvBottomBtn2,
                            tvBottomBtn3, tvBottomBtnMore,
                            "追加评价", OPERATE_ADD_REVIEW, orderId,
                            TextUtils.isEmpty(orderBean.getVerifyVideo()) ? null : "鉴定视频",
                            OPERATE_VERIFY_VIDEO, orderBean.getVerifyVideo(),
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null);

                    break;

                case STATUS_BUYER_DONE:
                    String verifyVideo = orderBean.getVerifyVideo();
                    if (StringUtil.isEmpty(verifyVideo)) {
                        layBottomBtns.setVisibility(View.GONE);
                    } else {
                        layBottomBtns.setVisibility(View.VISIBLE);
                        switchBottomBtns(activity, userType, presenter, false,
                                tvBottomBtn1, tvBottomBtn2,
                                tvBottomBtn3, tvBottomBtnMore,
                                "鉴定视频", OPERATE_VERIFY_VIDEO, verifyVideo,
                                null, -1, null,
                                null, -1, null,
                                null, -1, null,
                                null, -1, null,
                                null, -1, null);
                    }

                    break;

                case STATUS_BUYER_DUR_DISPUTE:
                    layBottomBtns.setVisibility(View.VISIBLE);
                    switchBottomBtns(activity, userType, presenter, false,
                            tvBottomBtn1, tvBottomBtn2,
                            tvBottomBtn3, tvBottomBtnMore,
                            "查看详情", -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null);

                    break;

                case STATUS_BUYER_DISPUTE_SUCCESS:
                    layBottomBtns.setVisibility(View.VISIBLE);
                    switchBottomBtns(activity, userType, presenter, false,
                            tvBottomBtn1, tvBottomBtn2,
                            tvBottomBtn3, tvBottomBtnMore,
                            "查看详情", -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null);

                    break;

                case STATUS_BUYER_CLOSED:
                    layBottomBtns.setVisibility(View.VISIBLE);
                    switchBottomBtns(activity, userType, presenter,
                            position, false,
                            tvBottomBtn1, tvBottomBtn2,
                            tvBottomBtn3, tvBottomBtnMore,
                            "删除订单", OPERATE_DELETE, orderId,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null);

                    break;

                default:
                    layBottomBtns.setVisibility(View.GONE);
                    break;
            }

        } else if (userType == Consts.USER_TYPE_BACK_PACKER
                || userType == Consts.USER_TYPE_SELLER) {
            switch (orderStatus) {

                case STATUS_SELLER_AWAIT_PAY:
                    layBottomBtns.setVisibility(View.VISIBLE);
                    switchBottomBtns(activity, userType, presenter, false,
                            tvBottomBtn1, tvBottomBtn2,
                            tvBottomBtn3, tvBottomBtnMore,
                            "联系买家", OPERATE_CONTACT_BUYER, orderBean.getBuyerName(),
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null);
                    break;

                case STATUS_SELLER_AWAIT_SHIPPING:
                    layBottomBtns.setVisibility(View.VISIBLE);
                    switchBottomBtns(activity, userType, presenter, true,
                            tvBottomBtn1, tvBottomBtn2,
                            tvBottomBtn3, tvBottomBtnMore,
                            "发货", OPERATE_TO_SHIPPING, orderId,
                            "联系买家", OPERATE_CONTACT_BUYER, orderBean.getBuyerName(),
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null);
                    break;

                case STATUS_SELLER_SHIPPED:
                    layBottomBtns.setVisibility(View.VISIBLE);
                    switchBottomBtns(activity, userType, presenter, true,
                            tvBottomBtn1, tvBottomBtn2,
                            tvBottomBtn3, tvBottomBtnMore,
                            "查看物流", OPERATE_VIEW_SHIPPING, orderId,
                            "联系买家", OPERATE_CONTACT_BUYER, orderBean.getBuyerName(),
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null);
                    break;

                case STATUS_SELLER_DONE:
                    layBottomBtns.setVisibility(View.VISIBLE);
                    switchBottomBtns(activity, userType, presenter, false,
                            tvBottomBtn1, tvBottomBtn2,
                            tvBottomBtn3, tvBottomBtnMore,
                            "查看物流", OPERATE_VIEW_SHIPPING, orderId,
                            "查看评价", OPERATE_VIEW_REVIEWS, orderId,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null);
                    break;

                case STATUS_SELLER_DUR_DISPUTE:
                case STATUS_SELLER_DISPUTE_SUCCESS:
                    layBottomBtns.setVisibility(View.VISIBLE);
                    switchBottomBtns(activity, userType, presenter, false,
                            tvBottomBtn1, tvBottomBtn2,
                            tvBottomBtn3, tvBottomBtnMore,
                            "查看详情", -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null);
                    break;

                case STATUS_SELLER_CLOSED:
                    layBottomBtns.setVisibility(View.VISIBLE);
                    switchBottomBtns(activity, userType, presenter,
                            position, false,
                            tvBottomBtn1, tvBottomBtn2,
                            tvBottomBtn3, tvBottomBtnMore,
                            "删除订单", OPERATE_DELETE, orderId,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null);
                    break;

                default:
                    layBottomBtns.setVisibility(View.GONE);
                    break;
            }
        } else {
            layBottomBtns.setVisibility(View.GONE);
        }
    }

    public static void switchDetailStatusNBtns(BaseActivity activity, int userType,
                                               OrderPresenter presenter, OrderDetailBean orderDetailBean,
                                               LinearLayout layStatus,
                                               View layBottomBtns, TextView tvBottomBtn1, TextView tvBottomBtn2,
                                               TextView tvBottomBtn3, TextView tvBottomBtnMore) {
        int orderStatus = orderDetailBean.getOrderStatus();
        String orderId = orderDetailBean.getOrderId();
        if (userType == Consts.USER_TYPE_BUYER) {
            switch (orderStatus) {
                case STATUS_BUYER_AWAIT_PAY:
                    switchStatus(activity, userType, layStatus, 1);
                    layBottomBtns.setVisibility(View.VISIBLE);
                    switchBottomBtns(activity, userType, presenter, true,
                            tvBottomBtn1, tvBottomBtn2,
                            tvBottomBtn3, tvBottomBtnMore,
                            "支付", OPERATE_PAY, orderId,
                            "取消订单", OPERATE_CANCEL, orderId,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null);
                    break;

                case STATUS_BUYER_AWAIT_SHIPPING:
                    switchStatus(activity, userType, layStatus, 2);
                    layBottomBtns.setVisibility(View.GONE);

                    break;

                case STATUS_BUYER_AWAIT_CONFIRM:
                    switchStatus(activity, userType, layStatus, 3);
                    layBottomBtns.setVisibility(View.VISIBLE);
                    switchBottomBtns(activity, userType, presenter, true,
                            tvBottomBtn1, tvBottomBtn2,
                            tvBottomBtn3, tvBottomBtnMore,
                            "确认收货", OPERATE_CONFIRM, orderId,
                            "查看物流", OPERATE_VIEW_SHIPPING, orderId,
                            TextUtils.isEmpty(orderDetailBean.getVerifyVideo()) ? null : "鉴定视频",
                            OPERATE_VERIFY_VIDEO, orderDetailBean.getVerifyVideo(),
                            null, -1, null,
                            null, -1, null,
                            null, -1, null);

                    break;

                case STATUS_BUYER_AWAIT_REVIEW:
                    switchStatus(activity, layStatus, R.mipmap.ic_successfu_transaction, "交易成功");
                    layBottomBtns.setVisibility(View.VISIBLE);
                    switchBottomBtns(activity, userType, presenter, true,
                            tvBottomBtn1, tvBottomBtn2,
                            tvBottomBtn3, tvBottomBtnMore,
                            "评价", OPERATE_REVIEW, orderId,
                            "查看物流", OPERATE_VIEW_SHIPPING, orderId,
                            TextUtils.isEmpty(orderDetailBean.getVerifyVideo()) ? null : "鉴定视频",
                            OPERATE_VERIFY_VIDEO, orderDetailBean.getVerifyVideo(),
                            null, -1, null,
                            null, -1, null,
                            null, -1, null);

                    break;

                case STATUS_BUYER_ADD_REVIEW:
                    switchStatus(activity, layStatus, R.mipmap.ic_successfu_transaction, "交易成功");
                    layBottomBtns.setVisibility(View.VISIBLE);
                    switchBottomBtns(activity, userType, presenter, true,
                            tvBottomBtn1, tvBottomBtn2,
                            tvBottomBtn3, tvBottomBtnMore,
                            "追加评价", OPERATE_ADD_REVIEW, orderId,
                            TextUtils.isEmpty(orderDetailBean.getVerifyVideo()) ? null : "鉴定视频",
                            OPERATE_VERIFY_VIDEO, orderDetailBean.getVerifyVideo(),
                            "删除订单", OPERATE_DELETE, orderId,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null);

                    break;

                case STATUS_BUYER_DONE:
                    switchStatus(activity, layStatus, R.mipmap.ic_successfu_transaction, "交易成功");
                    layBottomBtns.setVisibility(View.VISIBLE);
                    switchBottomBtns(activity, userType, presenter, false,
                            tvBottomBtn1, tvBottomBtn2,
                            tvBottomBtn3, tvBottomBtnMore,
                            TextUtils.isEmpty(orderDetailBean.getVerifyVideo()) ? null : "鉴定视频",
                            OPERATE_VERIFY_VIDEO, orderDetailBean.getVerifyVideo(),
                            "删除订单", OPERATE_DELETE, orderId,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null);

                    break;

                case STATUS_BUYER_DUR_DISPUTE:
                    switchStatus(activity, layStatus, R.mipmap.ic_in_stock, "售后处理中");
                    layBottomBtns.setVisibility(View.VISIBLE);
                    switchBottomBtns(activity, userType, presenter, false,
                            tvBottomBtn1, tvBottomBtn2,
                            tvBottomBtn3, tvBottomBtnMore,
                            "联系客服", OPERATE_CONTACT_SERVICE, null,
                            "删除订单", OPERATE_DELETE, orderId,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null);

                    break;

                case STATUS_BUYER_DISPUTE_SUCCESS:
                    switchStatus(activity, layStatus, R.mipmap.ic_shipped, "退款成功");
                    layBottomBtns.setVisibility(View.VISIBLE);
                    switchBottomBtns(activity, userType, presenter, false,
                            tvBottomBtn1, tvBottomBtn2,
                            tvBottomBtn3, tvBottomBtnMore,
                            "联系客服", OPERATE_CONTACT_SERVICE, null,
                            "删除订单", OPERATE_DELETE, orderId,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null);

                    break;

                case STATUS_BUYER_CLOSED:
                    switchStatus(activity, layStatus, R.mipmap.ic_successfu_failure, "交易关闭", orderDetailBean.getCloseReason());
                    layBottomBtns.setVisibility(View.VISIBLE);
                    switchBottomBtns(activity, userType, presenter, false,
                            tvBottomBtn1, tvBottomBtn2,
                            tvBottomBtn3, tvBottomBtnMore,
                            "删除订单", OPERATE_DELETE, orderId,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null);

                    break;

                default:
                    layBottomBtns.setVisibility(View.GONE);
                    break;
            }

        } else if (userType == Consts.USER_TYPE_BACK_PACKER
                || userType == Consts.USER_TYPE_SELLER) {
            switch (orderStatus) {

                case STATUS_SELLER_AWAIT_PAY:
                    if (userType == Consts.USER_TYPE_BACK_PACKER) {
                        switchStatus(activity, layStatus, R.mipmap.ic_in_stock, "待付款");
                    } else {
                        switchStatus(activity, userType, layStatus, 1);
                    }
                    layBottomBtns.setVisibility(View.GONE);
                    break;

                case STATUS_SELLER_AWAIT_SHIPPING:
                    if (userType == Consts.USER_TYPE_BACK_PACKER) {
                        switchStatus(activity, layStatus, R.mipmap.ic_in_stock, "待发货");
                    } else {
                        switchStatus(activity, userType, layStatus, 2);
                    }
                    layBottomBtns.setVisibility(View.VISIBLE);
                    switchBottomBtns(activity, userType, presenter, true,
                            tvBottomBtn1, tvBottomBtn2,
                            tvBottomBtn3, tvBottomBtnMore,
                            "发货", OPERATE_TO_SHIPPING, orderId,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null);
                    break;

                case STATUS_SELLER_SHIPPED:
                    if (userType == Consts.USER_TYPE_BACK_PACKER) {
                        switchStatus(activity, layStatus, R.mipmap.ic_shipped, "已发货");
                    } else {
                        switchStatus(activity, userType, layStatus, 3);
                    }
                    layBottomBtns.setVisibility(View.VISIBLE);
                    switchBottomBtns(activity, userType, presenter, true,
                            tvBottomBtn1, tvBottomBtn2,
                            tvBottomBtn3, tvBottomBtnMore,
                            "查看物流", OPERATE_VIEW_SHIPPING, orderId,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null);
                    break;

                case STATUS_SELLER_DONE:
                    switchStatus(activity, layStatus, R.mipmap.ic_successfu_transaction, "交易成功");
                    layBottomBtns.setVisibility(View.VISIBLE);
                    switchBottomBtns(activity, userType, presenter, false,
                            tvBottomBtn1, tvBottomBtn2,
                            tvBottomBtn3, tvBottomBtnMore,
                            "查看物流", OPERATE_VIEW_SHIPPING, orderId,
                            "查看评价", OPERATE_VIEW_REVIEWS, orderId,
                            "删除订单", OPERATE_DELETE, orderId,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null);
                    break;

                case STATUS_SELLER_DUR_DISPUTE:
                    switchStatus(activity, layStatus, R.mipmap.ic_in_stock, "售后处理中");
                    layBottomBtns.setVisibility(View.VISIBLE);
                    switchBottomBtns(activity, userType, presenter, false,
                            tvBottomBtn1, tvBottomBtn2,
                            tvBottomBtn3, tvBottomBtnMore,
                            "联系客服", OPERATE_CONTACT_SERVICE, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null);
                    break;

                case STATUS_SELLER_DISPUTE_SUCCESS:
                    switchStatus(activity, layStatus, R.mipmap.ic_shipped, "退款成功");
                    layBottomBtns.setVisibility(View.VISIBLE);
                    switchBottomBtns(activity, userType, presenter, false,
                            tvBottomBtn1, tvBottomBtn2,
                            tvBottomBtn3, tvBottomBtnMore,
                            "联系客服", OPERATE_CONTACT_SERVICE, null,
                            "删除订单", OPERATE_DELETE, orderId,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null);
                    break;

                case STATUS_SELLER_CLOSED:
                    switchStatus(activity, layStatus, R.mipmap.ic_successfu_failure, "交易关闭", orderDetailBean.getCloseReason());
                    layBottomBtns.setVisibility(View.VISIBLE);
                    switchBottomBtns(activity, userType, presenter, false,
                            tvBottomBtn1, tvBottomBtn2,
                            tvBottomBtn3, tvBottomBtnMore,
                            "删除订单", OPERATE_DELETE, orderId,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null,
                            null, -1, null);
                    break;

                default:
                    layBottomBtns.setVisibility(View.GONE);
                    break;
            }
        } else {
            layBottomBtns.setVisibility(View.GONE);
        }
    }

    public static void switchStatus(BaseActivity activity, LinearLayout layStatus,
                                    int iconRes, String text) {
        switchStatus(activity, layStatus, iconRes, text, null);
    }

    public static void switchStatus(BaseActivity activity, LinearLayout layStatus,
                                    int iconRes, String text, String reason) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        View view = LayoutInflater.from(activity).inflate(R.layout.layout_order_status_packer, null);
        view.setLayoutParams(lp);
        layStatus.addView(view);

        ImageView ivIcon = (ImageView) view.findViewById(R.id.iv_status_icon);
        TextView tvStatus = (TextView) view.findViewById(R.id.tv_status_txt);
        TextView tvReason = (TextView) view.findViewById(R.id.tv_status_reason);

        ivIcon.setImageResource(iconRes);
        tvStatus.setText(text);
        if (TextUtils.isEmpty(reason)) {
            tvReason.setVisibility(View.GONE);
        } else {
            tvReason.setVisibility(View.VISIBLE);
            tvReason.setText(reason);
        }
    }

    public static void switchStatus(BaseActivity activity, int userType,
                                    LinearLayout layStatus, int step) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        View view;
        if (userType == Consts.USER_TYPE_SELLER ||
                userType == Consts.USER_TYPE_BACK_PACKER) {
            view = LayoutInflater.from(activity).inflate(R.layout.layout_order_status_seller, null);
        } else {
            view = LayoutInflater.from(activity).inflate(R.layout.layout_order_status_buyer, null);
        }
        view.setLayoutParams(lp);
        layStatus.addView(view);

        ImageView line2 = (ImageView) view.findViewById(R.id.iv_status_line2);
        ImageView line3 = (ImageView) view.findViewById(R.id.iv_status_line3);
        ImageView line4 = (ImageView) view.findViewById(R.id.iv_status_line4);
        CheckBox dot1 = (CheckBox) view.findViewById(R.id.iv_status_dot1);
        CheckBox dot2 = (CheckBox) view.findViewById(R.id.iv_status_dot2);
        CheckBox dot3 = (CheckBox) view.findViewById(R.id.iv_status_dot3);
        CheckBox dot4 = (CheckBox) view.findViewById(R.id.iv_status_dot4);
        CheckedTextView txt1 = (CheckedTextView) view.findViewById(R.id.tv_status_txt1);
        CheckedTextView txt2 = (CheckedTextView) view.findViewById(R.id.tv_status_txt2);
        CheckedTextView txt3 = (CheckedTextView) view.findViewById(R.id.tv_status_txt3);
        CheckedTextView txt4 = (CheckedTextView) view.findViewById(R.id.tv_status_txt4);


        line2.setEnabled(false);
        line3.setEnabled(false);
        line4.setEnabled(false);
        if (step >= 1) {
            dot1.setEnabled(true);
            txt1.setEnabled(true);
        }
        if (step >= 2) {
            dot1.setChecked(true);
            txt1.setChecked(true);

            line2.setEnabled(true);
            dot2.setEnabled(true);
            txt2.setEnabled(true);
        }
        if (step >= 3) {
            dot2.setChecked(true);
            txt2.setChecked(true);

            line3.setEnabled(true);
            dot3.setEnabled(true);
            txt3.setEnabled(true);
        }
    }


    /**
     * @param activity
     * @param dialogRes               参考dialog_cancel_order，必须包含R.id.icon_close、R.id.btn_dialog_confirm、R.id.rg_dialog
     * @param onOptionConfirmListener
     */
    public static void showBtmOptionDialog(FragmentActivity activity, int dialogRes, final OnOptionConfirmListener onOptionConfirmListener) {
        new CommonDialog.Builder(activity.getSupportFragmentManager())
                .setLayoutRes(dialogRes)
                .setGravity(Gravity.BOTTOM)
                .setAnimRes(R.style.BottomDialogAnim)
                .setDimAmount(0.5f)
                .setScreenWidthAspect(activity, 1)
                .setOnBindViewListener(new OnBindViewListener() {
                    @Override
                    public void bindView(BindViewHolder viewHolder, CommonDialog dialog) {
                        viewHolder.setOnViewClickListener(R.id.icon_close, new OnViewClickListener() {
                            @Override
                            public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                                commonDialog.dismiss();
                            }
                        });

                        final Button btnCancel = viewHolder.getView(R.id.btn_dialog_confirm);
                        final RadioGroup radioGroup = viewHolder.getView(R.id.rg_dialog);
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


                        viewHolder.setOnViewClickListener(R.id.btn_dialog_confirm, new OnViewClickListener() {
                            @Override
                            public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                                commonDialog.dismiss();
                                if (onOptionConfirmListener != null) {
                                    onOptionConfirmListener.onConfirm(radioGroup);
                                }
                            }
                        });
                    }
                })
                .create().show();
    }

    public interface OnOptionConfirmListener {
        void onConfirm(RadioGroup radioGroup);
    }
}
