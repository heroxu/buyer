package com.smyy.sharetour.buyer.module.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.module.my.base.MyBaseMvpActivity;
import com.smyy.sharetour.buyer.module.my.base.MyBasePresenter;
import com.smyy.sharetour.buyer.module.order.adapter.OrderGoodsListAdapter;
import com.smyy.sharetour.buyer.module.order.bean.OrderBean;
import com.smyy.sharetour.buyer.module.order.bean.OrderDetailBean;
import com.smyy.sharetour.buyer.util.Spanny;
import com.smyy.sharetour.buyer.util.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderDetailActivity extends MyBaseMvpActivity {
    @BindView(R.id.tv_order_remain_time)
    TextView tvRemainTime;
    @BindView(R.id.lay_order_progress)
    View layProgress;
    @BindView(R.id.line_order_paid)
    ImageView linePaid;
    @BindView(R.id.line_order_shipped)
    ImageView lineShipped;
    @BindView(R.id.line_order_await_confirm)
    ImageView lineAwaitConfirm;
    @BindView(R.id.iv_order_await_pay)
    CheckBox dotAwaitPay;
    @BindView(R.id.iv_order_paid)
    CheckBox dotPaid;
    @BindView(R.id.iv_order_shipped)
    CheckBox dotShipped;
    @BindView(R.id.iv_order_await_confirm)
    CheckBox dotAwaitConfirm;
    @BindView(R.id.tv_order_await_pay)
    CheckedTextView tvAwaitPay;
    @BindView(R.id.tv_order_paid)
    CheckedTextView tvPaid;
    @BindView(R.id.tv_order_shipped)
    CheckedTextView tvShipped;
    @BindView(R.id.tv_order_await_confirm)
    CheckedTextView tvAwaitConfirm;
    @BindView(R.id.lay_order_status)
    ViewGroup layStatus;
    @BindView(R.id.iv_order_status)
    ImageView ivStatus;
    @BindView(R.id.tv_order_status)
    TextView tvStatus;
    @BindView(R.id.tv_order_shipping_name)
    TextView tvShippingName;
    @BindView(R.id.tv_order_shipping_phone)
    TextView tvShippingPhone;
    @BindView(R.id.tv_order_shipping_address)
    TextView tvShippingAddress;
    @BindView(R.id.iv_order_seller_avatar)
    ImageView ivSellerAvatar;
    @BindView(R.id.tv_order_seller_name)
    TextView tvSellerName;
    @BindView(R.id.tv_order_contact_seller)
    TextView tvContactSeller;
    @BindView(R.id.rv_order_goods_list)
    RecyclerView rvGoodsList;
    @BindView(R.id.lay_order_sum)
    View laySum;
    @BindView(R.id.tv_order_goods_count)
    TextView tvGoodsCount;
    @BindView(R.id.tv_order_price_total)
    TextView tvPriceTotal;
    @BindView(R.id.tv_order_shipping_fee)
    TextView tvShippingFee;
    @BindView(R.id.tv_order_num)
    TextView tvOrderNum;
    @BindView(R.id.tv_order_copy_num)
    TextView tvCopyOrderNum;
    @BindView(R.id.tv_order_time)
    TextView tvOrderTime;
    @BindView(R.id.tv_order_bottom_btn1)
    TextView tvBottomBtn1;
    @BindView(R.id.tv_order_bottom_btn2)
    TextView tvBottomBtn2;
    @BindView(R.id.tv_order_bottom_btn3)
    TextView tvBottomBtn3;
    @BindView(R.id.tv_order_bottom_more)
    TextView tvBottomBtnMore;

    public static final String FAKE_DATA = "fake_data";
    private String mOrderNum;
    private int mUserType;
    private Bundle mBundle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("订单详情");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        mBundle = getBundle();
        if (mBundle != null) {
            mUserType = mBundle.getInt(Consts.USER_TYPE);
        }
        initView();
        getFakeData();
    }

    private void initView() {
        linePaid.setEnabled(false);
        lineShipped.setEnabled(false);
        lineAwaitConfirm.setEnabled(false);

        switch (mUserType) {
            case Consts.USER_TYPE_BACK_PACKER:
            case Consts.USER_TYPE_SELLER:
                layStatus.removeAllViews();
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                View view = LayoutInflater.from(this).inflate(R.layout.layout_order_status_buyer, null);
                view.setLayoutParams(lp);
                layStatus.addView(view);
                break;

            default:
                break;
        }
    }

    private void getFakeData() {
        if (mBundle != null) {
            OrderBean orderBean = (OrderBean) mBundle.getSerializable(FAKE_DATA);
            OrderDetailBean orderDetailBean = new OrderDetailBean();

            int orderStatus = orderBean.getOrderStatus();
            if (orderStatus == Consts.ORDER_STATUS_AWAIT_PAY) {
                orderDetailBean.setRemainTime("12:59:30");
            }
            orderDetailBean.setOrderStatus(orderStatus);

            orderDetailBean.setShippingName("阳鸿");
            orderDetailBean.setShippingPhone("13760685049");
            orderDetailBean.setShippingAddress("广东省广州市天河区冼村街道合景国际金融大厦32 楼3205室");

            orderDetailBean.setSellerName(orderBean.getSellerName());
            orderDetailBean.setGoodsList(orderBean.getGoodsList());
            orderDetailBean.setGoodsCountTotal(orderBean.getGoodsCountTotal());
            orderDetailBean.setPriceTotal(orderBean.getPriceTotal());
            orderDetailBean.setShippingFee(orderBean.getShippingFee());
            orderDetailBean.setOrderNum("201803071438023384");
            orderDetailBean.setOrderTime("2018-03-08 14:39:07");

            showOrderDetail(orderDetailBean);
        }
    }

    private void showOrderDetail(OrderDetailBean data) {
        if (data != null) {
            String remainTime = data.getRemainTime();
            if (!StringUtil.isEmpty(remainTime)) {
                tvRemainTime.setVisibility(View.VISIBLE);
                tvRemainTime.setText(new Spanny("订单剩 ")
                        .append(remainTime, new StyleSpan(android.graphics.Typeface.BOLD))
                        .append(" 自动关闭"));
            }

            tvShippingName.setText(data.getShippingName());
            tvShippingPhone.setText(data.getShippingPhone());
            tvShippingAddress.setText(data.getShippingAddress());
            mOrderNum = data.getOrderNum();
            tvOrderNum.setText(mOrderNum);
            tvOrderTime.setText(data.getOrderTime());


            ivSellerAvatar.setImageResource(R.mipmap.fake_seller_avatar);
//            Glide.with(mContext).load(data.getSellerAvatar()).into(ivSellerAvatar);//TODO RTRT
            tvSellerName.setText(data.getSellerName());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OrderDetailActivity.this);
            rvGoodsList.setLayoutManager(linearLayoutManager);
            rvGoodsList.setAdapter(new OrderGoodsListAdapter(OrderDetailActivity.this, data.getGoodsList()));

            int goodsType = data.getGoodsType();
            switch (goodsType) {
                case Consts.GOODS_TYPE_DEMAND:
                    laySum.setVisibility(View.GONE);
                    break;

                default:
                    laySum.setVisibility(View.VISIBLE);
                    int goodsCountTotal = data.getGoodsCountTotal();
                    if (goodsCountTotal > 0) {
                        tvGoodsCount.setVisibility(View.VISIBLE);
                        tvGoodsCount.setText("共" + goodsCountTotal + "件商品");
                    } else {
                        tvGoodsCount.setVisibility(View.GONE);
                    }

                    tvPriceTotal.setText(new Spanny().append("总额：")
                            .append(data.getPriceTotal(),
                                    new ForegroundColorSpan(getResources().getColor(R.color.txt_price))));
                    tvShippingFee.setText(StringUtil.connect("（含运费", data.getShippingFee(), "）"));
                    break;
            }


            int orderStatus = data.getOrderStatus();

            OrderHelper.switchBottomBtns(this, mUserType, orderStatus,
                    tvBottomBtn1, tvBottomBtn2, tvBottomBtn3, tvBottomBtnMore);

            switch (orderStatus) {

                case Consts.ORDER_STATUS_AWAIT_PAY:
                    layProgress.setVisibility(View.VISIBLE);
                    dotAwaitPay.setEnabled(true);
                    tvAwaitPay.setEnabled(true);
                    break;

                case Consts.ORDER_STATUS_AWAIT_SHIPPING:
                    layProgress.setVisibility(View.VISIBLE);

                    dotAwaitPay.setEnabled(true);
                    dotAwaitPay.setChecked(true);
                    tvAwaitPay.setEnabled(true);
                    tvAwaitPay.setChecked(true);

                    linePaid.setEnabled(true);
                    dotPaid.setEnabled(true);
                    tvPaid.setEnabled(true);
                    break;

                case Consts.ORDER_STATUS_AWAIT_CONFIRM:
                    switch (mUserType) {
                        case Consts.USER_TYPE_BACK_PACKER:
                        case Consts.USER_TYPE_SELLER:
                            layStatus.setVisibility(View.VISIBLE);
                            ivStatus.setImageResource(R.mipmap.ic_successfu_transaction);
                            tvStatus.setText("交易成功");
                            break;

                        default:
                            layProgress.setVisibility(View.VISIBLE);

                            dotAwaitPay.setEnabled(true);
                            tvAwaitPay.setEnabled(true);
                            dotAwaitPay.setChecked(true);
                            tvAwaitPay.setChecked(true);

                            linePaid.setEnabled(true);
                            dotPaid.setEnabled(true);
                            tvPaid.setEnabled(true);
                            dotPaid.setChecked(true);
                            tvPaid.setChecked(true);

                            lineShipped.setEnabled(true);
                            dotShipped.setEnabled(true);
                            tvShipped.setEnabled(true);
                            break;
                    }
                    break;

                case Consts.ORDER_STATUS_CONFIRMED:
                    layStatus.setVisibility(View.VISIBLE);
                    ivStatus.setImageResource(R.mipmap.ic_successfu_transaction);
                    tvStatus.setText("交易成功");
                    break;

                case Consts.ORDER_STATUS_OTHER:
                    layStatus.setVisibility(View.VISIBLE);
                    ivStatus.setImageResource(R.mipmap.ic_successfu_failure);
                    tvStatus.setText("交易关闭");
                    break;

                default:
                    break;
            }
        }
    }

    @OnClick({R.id.tv_order_copy_num, R.id.tv_order_bottom_more})
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.tv_order_copy_num:

                break;

            default:
                break;
        }
    }


    @Override
    protected MyBasePresenter createPresenter() {
        return null;
    }


}
