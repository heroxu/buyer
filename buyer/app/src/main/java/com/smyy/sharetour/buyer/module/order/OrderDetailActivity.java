package com.smyy.sharetour.buyer.module.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.module.my.base.MyBaseMvpActivity;
import com.smyy.sharetour.buyer.module.order.adapter.OrderGoodsListAdapter;
import com.smyy.sharetour.buyer.module.order.bean.OrderBean;
import com.smyy.sharetour.buyer.module.order.bean.OrderDetailBean;
import com.smyy.sharetour.buyer.module.order.bean.OrderGoodsInfo;
import com.smyy.sharetour.buyer.module.order.contract.IOrderContract;
import com.smyy.sharetour.buyer.module.order.model.OrderModel;
import com.smyy.sharetour.buyer.module.order.presenter.OrderPresenter;
import com.smyy.sharetour.buyer.ui.buyCommodity.BuyHomePageActivity;
import com.smyy.sharetour.buyer.util.Spanny;
import com.smyy.sharetour.buyer.util.StringUtil;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderDetailActivity extends MyBaseMvpActivity<OrderPresenter> implements IOrderContract.View {
    @BindView(R.id.tv_order_remain_time)
    TextView tvRemainTime;
    @BindView(R.id.lay_order_status)
    LinearLayout layStatus;
    @BindView(R.id.tv_order_shipping_name)
    TextView tvShippingName;
    @BindView(R.id.tv_order_shipping_phone)
    TextView tvShippingPhone;
    @BindView(R.id.tv_order_shipping_address)
    TextView tvShippingAddress;
    @BindView(R.id.lay_order_opposite_info)
    View layOppositeInfo;
    @BindView(R.id.iv_order_opposite_avatar)
    ImageView ivOppositeAvatar;
    @BindView(R.id.tv_order_opposite_name)
    TextView tvOppositeName;
    @BindView(R.id.tv_order_contact_opposite)
    TextView tvContactOpposite;
    @BindView(R.id.rv_order_goods_list)
    RecyclerView rvGoodsList;
    @BindView(R.id.lay_order_sum)
    View laySum;
    @BindView(R.id.tv_order_goods_count_total)
    TextView tvGoodsCountTotal;
    @BindView(R.id.tv_order_price_total)
    TextView tvPriceTotal;
    @BindView(R.id.tv_order_shipping_fee)
    TextView tvShippingFee;
    @BindView(R.id.lay_order_reward)
    View layReward;
    @BindView(R.id.tv_order_reward)
    TextView tvReward;
    @BindView(R.id.tv_order_num)
    TextView tvOrderNum;
    @BindView(R.id.tv_order_copy_num)
    TextView tvCopyOrderNum;
    @BindView(R.id.tv_order_time)
    TextView tvOrderTime;
    @BindView(R.id.lay_order_detail_bottom_btns)
    View layBottomBtns;
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
        getFakeData();
    }

    private void getFakeData() {
        OrderDetailBean orderDetailBean = new OrderDetailBean();
        setFakedata(orderDetailBean);

        int orderStatus = orderDetailBean.getOrderStatus();
        switch (orderStatus) {
            case OrderHelper.STATUS_BUYER_AWAIT_PAY:
//            case OrderHelper.STATUS_SELLER_AWAIT_PAY:
                orderDetailBean.setRemainTime("12:59:30");
                break;

            case OrderHelper.STATUS_BUYER_CLOSED:
            case OrderHelper.STATUS_SELLER_CLOSED:
                orderDetailBean.setCloseReason("我不想买了");
                break;

            default:
                break;

        }

        if (orderDetailBean.getGoodsType() == OrderHelper.GOODS_TYPE_DEMAND) {
            orderDetailBean.setReward("¥ 300.00");
        }

        orderDetailBean.setShippingName("阳鸿");
        orderDetailBean.setShippingPhone("13760685049");
        orderDetailBean.setShippingAddress("广东省广州市天河区冼村街道合景国际金融大厦32 楼3205室");
        orderDetailBean.setOrderNum("201803071438023384");
        orderDetailBean.setOrderTime("2018-03-08 14:39:07");

        showOrderDetail(orderDetailBean);
    }

    private void setFakedata(OrderDetailBean orderDetailBean) {
        if (mBundle != null) {
            Serializable mBundleSerializable = mBundle.getSerializable(FAKE_DATA);
            if (mBundleSerializable != null && mBundleSerializable instanceof OrderBean) {
                OrderBean orderBean = (OrderBean) mBundleSerializable;

                orderDetailBean.setOrderId(orderBean.getOrderId());
                orderDetailBean.setOrderStatus(orderBean.getOrderStatus());
                orderDetailBean.setSellerName(orderBean.getSellerName());
                orderDetailBean.setSellerAvatar(orderBean.getSellerAvatar());
                orderDetailBean.setBuyerName(orderBean.getBuyerName());
                orderDetailBean.setBuyerAvatar(orderBean.getBuyerAvatar());
                orderDetailBean.setGoodsCountTotal(orderBean.getGoodsCountTotal());
                orderDetailBean.setPriceTotal(orderBean.getPriceTotal());
                orderDetailBean.setShippingFee(orderBean.getShippingFee());
                orderDetailBean.setVerifyVideo(orderBean.getVerifyVideo());
                orderDetailBean.setGoodsType(orderBean.getGoodsType());
                orderDetailBean.setGoodsList(orderBean.getGoodsList());
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
        orderDetailBean.setGoodsCountTotal(1);
        orderDetailBean.setPriceTotal("￥9,948.00");
        orderDetailBean.setShippingFee("￥30.00");
        orderDetailBean.setVerifyVideo("");
        orderDetailBean.setGoodsType(OrderHelper.GOODS_TYPE_DEMAND);
        ArrayList<OrderGoodsInfo> fakdeGoodsList1 = new ArrayList<>();
        OrderGoodsInfo goodsInfo1 = new OrderGoodsInfo("1",
                "NIKE HUARACHE DRIFT (PSE) LALALALALA",
                "黑白/36.5",
                "",
                "￥9,918.00",
                1, "", OrderHelper.GOODS_TYPE_DEMAND, null);
        fakdeGoodsList1.add(goodsInfo1);
        orderDetailBean.setGoodsList(fakdeGoodsList1);
    }

    private void showOrderDetail(final OrderDetailBean data) {
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

            if (mUserType == Consts.USER_TYPE_BUYER) {
                ivOppositeAvatar.setImageResource(R.mipmap.fake_seller_avatar);
//            Glide.with(mContext).load(data.getSellerAvatar()).into(ivOppositeAvatar);//TODO RTRT
                tvOppositeName.setText(data.getSellerName());
                tvOppositeName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(BuyHomePageActivity.class);
                    }
                });

                tvContactOpposite.setText("联系买手");
                tvContactOpposite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OrderHelper.switchOperate(OrderDetailActivity.this,
                                mUserType, data.getSellerName(), OrderHelper.OPERATE_CONTACT_SELLER);
                    }
                });

            } else {
                ivOppositeAvatar.setImageResource(R.mipmap.fake_seller_avatar);
//            Glide.with(mContext).load(data.getBuyerAvatar()).into(ivOppositeAvatar);//TODO RTRT
                tvOppositeName.setText(data.getBuyerName());
                tvOppositeName.setCompoundDrawables(null, null, null, null);

                tvContactOpposite.setText("联系买家");
                tvContactOpposite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OrderHelper.switchOperate(OrderDetailActivity.this,
                                mUserType, data.getBuyerName(), OrderHelper.OPERATE_CONTACT_BUYER);
                    }
                });
            }
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OrderDetailActivity.this, LinearLayoutManager.VERTICAL, false) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            rvGoodsList.setLayoutManager(linearLayoutManager);
            rvGoodsList.setAdapter(new OrderGoodsListAdapter(OrderDetailActivity.this, mUserType,
                    mPresenter, true,
                    data.getOrderStatus(), data.getGoodsList()));

            int goodsType = data.getGoodsType();
            if (goodsType == OrderHelper.GOODS_TYPE_DEMAND) {
                tvGoodsCountTotal.setVisibility(View.GONE);
            } else {
                int goodsCountTotal = data.getGoodsCountTotal();
                if (goodsCountTotal > 0) {
                    tvGoodsCountTotal.setVisibility(View.VISIBLE);
                    tvGoodsCountTotal.setText("共" + goodsCountTotal + "件商品");
                } else {
                    tvGoodsCountTotal.setVisibility(View.GONE);
                }
            }

            tvPriceTotal.setText(StringUtil.connect("总额：", data.getPriceTotal()));
            tvShippingFee.setText(StringUtil.connect("（含运费", data.getShippingFee(), "）"));

            String reward = data.getReward();
            if (StringUtil.isEmpty(reward)) {
                layReward.setVisibility(View.GONE);
            } else {
                layReward.setVisibility(View.VISIBLE);
                tvReward.setText(reward);
            }

            OrderHelper.switchDetailStatusNBtns(this, mUserType, null, data, layStatus,
                    layBottomBtns, tvBottomBtn1, tvBottomBtn2, tvBottomBtn3, tvBottomBtnMore);
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
    protected OrderPresenter createPresenter() {
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
