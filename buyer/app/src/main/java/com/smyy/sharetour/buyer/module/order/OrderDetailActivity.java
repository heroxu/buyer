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
import com.smyy.sharetour.buyer.module.my.base.MyBasePresenter;
import com.smyy.sharetour.buyer.module.order.adapter.OrderGoodsListAdapter;
import com.smyy.sharetour.buyer.module.order.bean.OrderBean;
import com.smyy.sharetour.buyer.module.order.bean.OrderDetailBean;
import com.smyy.sharetour.buyer.ui.buyCommodity.BuyHomePageActivity;
import com.smyy.sharetour.buyer.util.Spanny;
import com.smyy.sharetour.buyer.util.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderDetailActivity extends MyBaseMvpActivity {
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
        if (mBundle != null) {
            OrderBean orderBean = (OrderBean) mBundle.getSerializable(FAKE_DATA);
            OrderDetailBean orderDetailBean = new OrderDetailBean();

            int orderStatus = orderBean.getOrderStatus();
            if (orderStatus == OrderHelper.STATUS_BUYER_AWAIT_PAY ||
                    orderStatus == OrderHelper.STATUS_SELLER_AWAIT_PAY) {
                orderDetailBean.setRemainTime("12:59:30");
            }
            orderDetailBean.setOrderStatus(orderStatus);

            orderDetailBean.setShippingName("阳鸿");
            orderDetailBean.setShippingPhone("13760685049");
            orderDetailBean.setShippingAddress("广东省广州市天河区冼村街道合景国际金融大厦32 楼3205室");

            orderDetailBean.setSellerName(orderBean.getSellerName());
            orderDetailBean.setBuyerName(orderBean.getBuyerName());
            orderDetailBean.setGoodsList(orderBean.getGoodsList());
            orderDetailBean.setGoodsCountTotal(orderBean.getGoodsCountTotal());
            orderDetailBean.setPriceTotal(orderBean.getPriceTotal());
            orderDetailBean.setShippingFee(orderBean.getShippingFee());
            orderDetailBean.setGoodsType(orderBean.getGoodsType());
            if (orderBean.getGoodsType() == OrderHelper.GOODS_TYPE_DEMAND) {
                orderDetailBean.setReward("¥ 300.00");
            }
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
                        OrderHelper.switchOperate(OrderDetailActivity.this, OrderHelper.OPERATE_CONTACT_SELLER);
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
                        OrderHelper.switchOperate(OrderDetailActivity.this, OrderHelper.OPERATE_CONTACT_BUYER);
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
            rvGoodsList.setAdapter(new OrderGoodsListAdapter(OrderDetailActivity.this, true,
                    mUserType, data.getOrderStatus(), data.getGoodsList()));

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
    protected MyBasePresenter createPresenter() {
        return null;
    }


}
