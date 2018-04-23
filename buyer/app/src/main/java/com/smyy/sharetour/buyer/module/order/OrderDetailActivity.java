package com.smyy.sharetour.buyer.module.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.RadioGroup;
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
import com.xmyy.view.dialoglib.CommonDialog;
import com.xmyy.view.dialoglib.base.BindViewHolder;
import com.xmyy.view.dialoglib.listener.OnBindViewListener;
import com.xmyy.view.dialoglib.listener.OnViewClickListener;

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
    View layStatus;
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
    @BindView(R.id.tv_order_verify_video)
    TextView tvVerifyVideo;
    @BindView(R.id.tv_order_contact_service)
    TextView tvContactService;
    @BindView(R.id.tv_order_remind_shipping)
    TextView tvRemindShipping;
    @BindView(R.id.tv_order_delete)
    TextView tvDelete;
    @BindView(R.id.tv_order_view_shipping)
    TextView tvViewShipping;
    @BindView(R.id.tv_order_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_order_pay)
    TextView tvPay;
    @BindView(R.id.tv_order_confirm)
    TextView tvConifrm;
    @BindView(R.id.tv_order_review)
    TextView tvReview;

    public static final String FAKE_DATA = "fake_data";
    private String mOrderNum;
    private int mCheckedId;

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
        initView();
        getFakeData();
    }

    private void initView() {
        linePaid.setEnabled(false);
        lineShipped.setEnabled(false);
        lineAwaitConfirm.setEnabled(false);
    }

    private void getFakeData() {
        Bundle bundle = getBundle();
        OrderBean orderBean = (OrderBean) bundle.getSerializable(FAKE_DATA);
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

            int goodsCountTotal = data.getGoodsCountTotal();
            if (goodsCountTotal > 0) {
                tvGoodsCount.setVisibility(View.GONE);
                tvGoodsCount.setText("共" + goodsCountTotal + "件商品");
            } else {
                tvGoodsCount.setVisibility(View.VISIBLE);
            }

            tvPriceTotal.setText(new Spanny().append("总额：")
                    .append(data.getPriceTotal(),
                            new ForegroundColorSpan(OrderDetailActivity.this.getResources().getColor(R.color.txt_price))));
            tvShippingFee.setText(StringUtil.connect("（含运费", data.getShippingFee(), "）"));

            int orderStatus = data.getOrderStatus();
            switch (orderStatus) {

                case Consts.ORDER_STATUS_AWAIT_PAY:
                    layProgress.setVisibility(View.VISIBLE);
                    dotAwaitPay.setEnabled(true);
                    tvAwaitPay.setEnabled(true);

                    tvVerifyVideo.setVisibility(View.GONE);
                    tvContactSeller.setVisibility(View.VISIBLE);
                    tvContactService.setVisibility(View.GONE);
                    tvRemindShipping.setVisibility(View.GONE);
                    tvDelete.setVisibility(View.GONE);
                    tvViewShipping.setVisibility(View.GONE);
                    tvCancel.setVisibility(View.VISIBLE);
                    tvPay.setVisibility(View.VISIBLE);
                    tvConifrm.setVisibility(View.GONE);
                    tvReview.setVisibility(View.GONE);
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

                    tvVerifyVideo.setVisibility(View.GONE);
                    tvContactSeller.setVisibility(View.VISIBLE);
                    tvContactService.setVisibility(View.VISIBLE);
                    tvRemindShipping.setVisibility(View.VISIBLE);
                    tvDelete.setVisibility(View.GONE);
                    tvViewShipping.setVisibility(View.GONE);
                    tvCancel.setVisibility(View.GONE);
                    tvPay.setVisibility(View.GONE);
                    tvConifrm.setVisibility(View.GONE);
                    tvReview.setVisibility(View.GONE);
                    break;

                case Consts.ORDER_STATUS_AWAIT_CONFIRM:
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

                    tvVerifyVideo.setVisibility(View.GONE);
                    tvContactSeller.setVisibility(View.VISIBLE);
                    tvContactService.setVisibility(View.GONE);
                    tvRemindShipping.setVisibility(View.GONE);
                    tvDelete.setVisibility(View.GONE);
                    tvViewShipping.setVisibility(View.VISIBLE);
                    tvCancel.setVisibility(View.GONE);
                    tvPay.setVisibility(View.GONE);
                    tvConifrm.setVisibility(View.VISIBLE);
                    tvReview.setVisibility(View.GONE);
                    break;

                case Consts.ORDER_STATUS_AWAIT_REVIEW:
                    layStatus.setVisibility(View.VISIBLE);
                    ivStatus.setImageResource(R.mipmap.ic_successfu_transaction);
                    tvStatus.setText("交易成功");

                    tvVerifyVideo.setVisibility(View.GONE);
                    tvContactSeller.setVisibility(View.GONE);
                    tvContactService.setVisibility(View.GONE);
                    tvRemindShipping.setVisibility(View.GONE);
                    tvDelete.setVisibility(View.VISIBLE);
                    tvViewShipping.setVisibility(View.VISIBLE);
                    tvCancel.setVisibility(View.GONE);
                    tvPay.setVisibility(View.GONE);
                    tvConifrm.setVisibility(View.GONE);
                    tvReview.setVisibility(View.VISIBLE);
                    break;

                case Consts.ORDER_STATUS_OTHER:
                    layStatus.setVisibility(View.VISIBLE);
                    ivStatus.setImageResource(R.mipmap.ic_successfu_failure);
                    tvStatus.setText("交易关闭");

                    tvVerifyVideo.setVisibility(View.GONE);
                    tvContactSeller.setVisibility(View.GONE);
                    tvContactService.setVisibility(View.GONE);
                    tvRemindShipping.setVisibility(View.GONE);
                    tvDelete.setVisibility(View.VISIBLE);
                    tvViewShipping.setVisibility(View.GONE);
                    tvCancel.setVisibility(View.GONE);
                    tvPay.setVisibility(View.GONE);
                    tvConifrm.setVisibility(View.GONE);
                    tvReview.setVisibility(View.GONE);
                    break;

                default:
                    break;
            }
        }
    }

    @OnClick({R.id.tv_order_copy_num, R.id.tv_order_verify_video, R.id.tv_order_contact_seller,
            R.id.tv_order_contact_service, R.id.tv_order_remind_shipping, R.id.tv_order_delete,
            R.id.tv_order_view_shipping, R.id.tv_order_cancel,
            R.id.tv_order_pay, R.id.tv_order_confirm, R.id.tv_order_review})
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.tv_order_copy_num:

                break;

            case R.id.tv_order_verify_video:

                break;

            case R.id.tv_order_contact_seller:

                break;

            case R.id.tv_order_contact_service:

                break;

            case R.id.tv_order_remind_shipping:

                break;

            case R.id.tv_order_delete:

                break;

            case R.id.tv_order_view_shipping:

                break;

            case R.id.tv_order_cancel:
                new CommonDialog.Builder(this.getSupportFragmentManager())
                        .setLayoutRes(R.layout.dialog_cancel_order)
                        .setGravity(Gravity.BOTTOM)
                        .setAnimRes(R.style.BottomDialogAnim)
                        .setDimAmount(0.5f)
                        .setScreenWidthAspect(OrderDetailActivity.this, 1)
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
                                        mCheckedId = checkedId;
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
                                        OrderDetailActivity.this.finish();
                                    }
                                });
                            }
                        })
                        .create().show();
                break;

            case R.id.tv_order_pay:

                break;

            case R.id.tv_order_confirm:

                break;

            case R.id.tv_order_review:
                startActivity(OrderCommentActivity.class);
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
