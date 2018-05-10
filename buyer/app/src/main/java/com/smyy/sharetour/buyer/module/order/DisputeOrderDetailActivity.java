package com.smyy.sharetour.buyer.module.order;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.dialog.DialogUtils;
import com.smyy.sharetour.buyer.module.my.base.MyBaseMvpActivity;
import com.smyy.sharetour.buyer.module.my.base.MyBasePresenter;
import com.smyy.sharetour.buyer.module.order.bean.DisputeOrderBean;
import com.smyy.sharetour.buyer.module.order.bean.DisputeOrderDetailBean;
import com.smyy.sharetour.buyer.module.order.contract.IOrderContract;
import com.smyy.sharetour.buyer.module.order.model.OrderModel;
import com.smyy.sharetour.buyer.module.order.presenter.OrderPresenter;
import com.smyy.sharetour.buyer.util.Spanny;
import com.smyy.sharetour.buyer.util.StringUtil;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.OnClick;

public class DisputeOrderDetailActivity extends MyBaseMvpActivity implements IOrderContract.View {

    @BindView(R.id.lay_order_status)
    LinearLayout layStatus;
    @BindView(R.id.tv_order_dispute_reason)
    TextView tvDisputeReason;
    @BindView(R.id.tv_order_dispute_refund_num)
    TextView tvRefundNum;
    @BindView(R.id.tv_order_dispute_time)
    TextView tvOrderTime;
    @BindView(R.id.tv_order_dispute_num)
    TextView tvOrderNum;

    @BindView(R.id.tv_order_contact_opposite)
    TextView tvContactOpposite;
    @BindView(R.id.tv_order_confirm_refund)
    TextView tvConfirmRefund;

    @BindView(R.id.iv_order_goods_pic)
    ImageView ivGoodsPic;
    @BindView(R.id.iv_order_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.iv_order_goods_spec)
    TextView tvGoodsSpec;
    @BindView(R.id.iv_order_receive_deadline)
    TextView tvGoodsDeadline;
    @BindView(R.id.iv_order_goods_price)
    TextView tvGoodsPrice;
    @BindView(R.id.tv_order_goods_count)
    TextView tvGoodsCount;

    public static final String FAKE_DATA = "fake_data";
    private String mOrderNum;
    private int mUserType;
    private Bundle mBundle;
    private DisputeOrderDetailBean mOrderDetailBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_dispute_detail;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("售后详情");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mBundle = getBundle();
        if (mBundle != null) {
            mUserType = mBundle.getInt(Consts.USER_TYPE);
        }
        getFakeData();
    }

    private void getFakeData() {
        DisputeOrderDetailBean orderDetailBean = new DisputeOrderDetailBean();
        setFakedata(orderDetailBean);

        orderDetailBean.setRefundReason("一直未收到货");
        orderDetailBean.setRefundNum("¥ 9,918.00");
        orderDetailBean.setOrderNum("201803071438023384");
        orderDetailBean.setOrderTime("2018-03-08 14:39:07");

        showOrderDetail(orderDetailBean);
    }

    private void setFakedata(DisputeOrderDetailBean orderDetailBean) {
        if (mBundle != null) {
            Serializable mBundleSerializable = mBundle.getSerializable(FAKE_DATA);
            if (mBundleSerializable != null && mBundleSerializable instanceof DisputeOrderBean) {
                DisputeOrderBean orderBean = (DisputeOrderBean) mBundleSerializable;

                orderDetailBean.setOrderId(orderBean.getOrderId());
                orderDetailBean.setOrderStatus(orderBean.getOrderStatus());
                orderDetailBean.setSellerName(orderBean.getSellerName());
                orderDetailBean.setSellerAvatar(orderBean.getSellerAvatar());
                orderDetailBean.setBuyerName(orderBean.getBuyerName());
                orderDetailBean.setBuyerAvatar(orderBean.getBuyerAvatar());
                orderDetailBean.setGoodsType(orderBean.getGoodsType());

                orderDetailBean.setGoodsId(orderBean.getGoodsId());
                orderDetailBean.setGoodsName(orderBean.getGoodsName());
                orderDetailBean.setGoodsSpec(orderBean.getGoodsSpec());
                orderDetailBean.setReceiveDeadline(orderBean.getReceiveDeadline());
                orderDetailBean.setGoodsPrice(orderBean.getGoodsPrice());
                orderDetailBean.setGoodsCount(orderBean.getGoodsCount());
                return;
            }

            orderDetailBean.setOrderId(mBundle.getString(OrderHelper.ORDER_ID));
        }

        if (StringUtil.isEmpty(orderDetailBean.getOrderId())) {
            orderDetailBean.setOrderId("1");
        }
        orderDetailBean.setOrderStatus(OrderHelper.STATUS_BUYER_AWAIT_SHIPPING);
        orderDetailBean.setSellerName("我是小桂子呀");
        orderDetailBean.setSellerAvatar("");
        orderDetailBean.setBuyerName("我是小桂子");
        orderDetailBean.setBuyerAvatar("");
        orderDetailBean.setGoodsType(OrderHelper.GOODS_TYPE_DEMAND);

        orderDetailBean.setGoodsId("");
        orderDetailBean.setGoodsName("NIKE HUARACHE DRIFT (PSE) LALALALALA");
        orderDetailBean.setGoodsSpec("");
        orderDetailBean.setReceiveDeadline("2018-05-01");
        orderDetailBean.setGoodsPrice("￥9,918.00");
        orderDetailBean.setGoodsCount(1);
    }

    private void showOrderDetail(DisputeOrderDetailBean data) {
        if (data != null) {
            mOrderDetailBean = data;
            if (mUserType == Consts.USER_TYPE_BUYER) {
                tvContactOpposite.setText("联系买手");
            } else {
                tvContactOpposite.setText("联系买家");
            }

            switch (data.getOrderStatus()) {
                case OrderHelper.STATUS_BUYER_DISPUTE_SUCCESS:
                case OrderHelper.STATUS_SELLER_DISPUTE_SUCCESS:
                    OrderHelper.switchStatus(this, layStatus, R.mipmap.ic_shipped, "退款成功");
                    tvConfirmRefund.setVisibility(View.GONE);
                    break;

                default:
                    OrderHelper.switchStatus(this, layStatus, R.mipmap.ic_in_stock, "售后处理中");
                    if (mUserType == Consts.USER_TYPE_BUYER) {
                        tvConfirmRefund.setVisibility(View.GONE);
                    } else {
                        tvConfirmRefund.setVisibility(View.VISIBLE);
                    }
                    break;
            }

            tvDisputeReason.setText(StringUtil.connect("退款理由：", data.getRefundReason()));
            String refundNum = data.getRefundNum();
            if (!StringUtil.isEmpty(refundNum)) {
                tvRefundNum.setText(new Spanny("退款金额：")
                        .append(refundNum, new StyleSpan(Typeface.BOLD)));
            }
            tvOrderTime.setText(StringUtil.connect("申请时间：", data.getOrderTime()));
            tvOrderNum.setText(StringUtil.connect("退款编号：", data.getOrderNum()));

            ivGoodsPic.setImageResource(R.mipmap.fake_goods_pic);
//            Glide.with(mActivity).load(data.getGoodsPic()).into(ivGoodsPic);//TODO RTRT
            tvGoodsName.setText(data.getGoodsName());

            String goodsSpec = data.getGoodsSpec();
            if (StringUtil.isEmpty(goodsSpec)) {
                tvGoodsSpec.setVisibility(View.GONE);
            } else {
                tvGoodsSpec.setVisibility(View.VISIBLE);
                tvGoodsSpec.setText("规格：" + goodsSpec);
            }

            String receiveDeadline = data.getReceiveDeadline();
            if (StringUtil.isEmpty(receiveDeadline)) {
                tvGoodsDeadline.setVisibility(View.GONE);
            } else {
                tvGoodsDeadline.setVisibility(View.VISIBLE);
                tvGoodsDeadline.setText(receiveDeadline + " 前收到");
            }

            tvGoodsPrice.setText(StringUtil.connect("价格：", data.getGoodsPrice()));


            int goodsType = data.getGoodsType();
            if (goodsType == OrderHelper.GOODS_TYPE_DEMAND) {
                tvGoodsCount.setVisibility(View.GONE);
            } else {
                tvGoodsCount.setVisibility(View.VISIBLE);
                tvGoodsCount.setText("× ️" + data.getGoodsCount());
            }
        }
    }

    @OnClick({R.id.tv_order_contact_service, R.id.tv_order_contact_opposite, R.id.tv_order_confirm_refund})
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.tv_order_contact_service:
                OrderHelper.switchOperate(this, OrderHelper.OPERATE_CONTACT_SERVICE);
                break;

            case R.id.tv_order_contact_opposite:
                if (mUserType == Consts.USER_TYPE_BUYER) {
                    OrderHelper.switchOperate(this, mUserType,
                            mOrderDetailBean.getSellerName(), OrderHelper.OPERATE_CONTACT_SELLER);
                } else {
                    OrderHelper.switchOperate(this, mUserType,
                            mOrderDetailBean.getBuyerName(), OrderHelper.OPERATE_CONTACT_BUYER);
                }
                break;

            case R.id.tv_order_confirm_refund:
                DialogUtils.showPayPwdDialog(this, new DialogUtils.OnPwdInputListener() {
                    @Override
                    public void onFinish() {

                    }
                });
                break;

            default:
                break;
        }
    }


    @Override
    protected MyBasePresenter createPresenter() {
        return new OrderPresenter(this, new OrderModel());
    }


    @Override
    public void deleteOrder(int position) {

    }

    @Override
    public void updateOrderDetail(String id) {
        getFakeData();
    }
}
