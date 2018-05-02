package com.smyy.sharetour.buyer.require;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.bean.RequireBean;
import com.smyy.sharetour.buyer.module.order.OrderDetailActivity;
import com.smyy.sharetour.buyer.module.order.OrderHelper;
import com.smyy.sharetour.buyer.module.order.bean.OrderBean;
import com.smyy.sharetour.buyer.module.order.bean.OrderGoodsInfo;
import com.smyy.sharetour.buyer.tim.ChatActivity;
import com.smyy.sharetour.buyer.view.keyboard.KeyboardUtil;
import com.tencent.imsdk.TIMConversationType;
import com.xmyy.view.dialoglib.CommonDialog;
import com.xmyy.view.dialoglib.base.BindViewHolder;
import com.xmyy.view.dialoglib.listener.OnBindViewListener;
import com.xmyy.view.dialoglib.listener.OnViewClickListener;
import com.yongchun.library.utils.ScreenUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Liliping
 * @org www.smyy.com
 * @email lilp@stjf.com
 * @package com.smyy.sharetour.buyer.publish
 * @fileName RequireDetailsActivity
 * @date on 2018/4/19 0019 11:47
 * @describe 需求详情页
 */
public class RequireDetailsActivity extends BaseMvpActivity {

    @BindView(R.id.require_detail_budget)
    TextView requireDetailBudget;
    @BindView(R.id.require_detail_time)
    TextView requireDetailTime;
    @BindView(R.id.require_state)
    TextView requireState;
    @BindView(R.id.reward)
    TextView reward_text;
    @BindView(R.id.undo_require)
    TextView undoRequire;
    @BindView(R.id.require_state_img)
    ImageView requireStateImg;
    @BindView(R.id.require_reward)
    TextView requireReward;
    @BindView(R.id.wait_sell_text)
    TextView waitSellText;
    @BindView(R.id.wait_seller_1)
    ImageView waitSeller1;
    @BindView(R.id.wait_seller_2)
    ImageView waitSeller2;
    @BindView(R.id.wait_seller_3)
    ImageView waitSeller3;
    @BindView(R.id.wait_seller_4)
    ImageView waitSeller4;
    @BindView(R.id.wait_point_ll)
    LinearLayout waitPointLl;
    @BindView(R.id.point_seller_info)
    TextView pointSellerInfo;
    @BindView(R.id.ready_good_ll)
    LinearLayout readyGoodLl;
    @BindView(R.id.reward_frame)
    FrameLayout rewardFrame;
    @BindView(R.id.delete_frame)
    FrameLayout deleteFrame;
    @BindView(R.id.require_detail_country)
    TextView requireDetailCountry;

    public static final String REQUIRE_KEY = "require";
    public static final String REQUIRE_SUCCESS_KEY = "show_success";
    public static final int REQUIRE_REQUEST_CANCEL = 100;

    private RequireBean requireBean;
    private boolean isShowSuccessDialog = false;
    private int reward = 0;
    private CommonDialog dialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_require_details;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {

    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        Bundle bundle = getBundle();
        requireBean = (RequireBean) bundle.getSerializable(REQUIRE_KEY);
        isShowSuccessDialog = bundle.getBoolean(REQUIRE_SUCCESS_KEY);
        hideToolBarLayout(true);

        SpannableString spanText = new SpannableString(getString(R.string.pay_price) + getString(R.string.four_space));

        SpannableString spanText1 = new SpannableString(requireBean.getRequire_budget());

        spanText.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.txt_main)),
                0, spanText.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spanText1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.txt_price)),
                0, spanText1.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spanText1.setSpan(new AbsoluteSizeSpan(17, true),
                0, spanText1.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        requireDetailBudget.setText(spanText);
        requireDetailBudget.append(spanText1);

        spanText = new SpannableString(getString(R.string.expect_time) + getString(R.string.four_space));

        spanText1 = new SpannableString(requireBean.getRequire_time());

        spanText.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.txt_main)),
                0, spanText.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spanText1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.txt_price)),
                0, spanText1.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spanText1.setSpan(new AbsoluteSizeSpan(15, true),
                0, spanText1.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        requireDetailTime.setText(spanText);
        requireDetailTime.append(spanText1);

        spanText = new SpannableString("期望购买地" + getString(R.string.two_space));

        spanText1 = new SpannableString(requireBean.getRequire_buy_place());

        spanText.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.txt_main)),
                0, spanText.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spanText1.setSpan(new AbsoluteSizeSpan(15, true),
                0, spanText1.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spanText1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.txt_main)),
                0, spanText1.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        requireDetailCountry.setText(spanText);
        requireDetailCountry.append(spanText1);

        if(requireBean.getReward()!=null) {
            reward = Integer.parseInt(requireBean.getReward());
            if (reward != 0) {
                rewardPaySuccess();
            }
        }

        if(requireBean.getState()== Consts.REQUIRE_STATE_WAIT_POINT) {
            waitPointLl.setVisibility(View.VISIBLE);
            waitPointLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(RequireDetailsActivity.this, RequireSellerListActivity.class));
                }
            });
            spanText = new SpannableString("已有");
            spanText1 = new SpannableString("7");
            SpannableString spanText2 = new SpannableString("位买手抢单，请去指定");

            spanText.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.txt_gray_dark)),
                    0, spanText.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            spanText1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.txt_price)),
                    0, spanText1.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            spanText2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.txt_gray_dark)),
                    0, spanText2.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

            waitSellText.setText(spanText);
            waitSellText.append(spanText1);
            waitSellText.append(spanText2);
        } else if(requireBean.getState()== Consts.REQUIRE_STATE_WAIT_SEND_GOOD||requireBean.getState()== Consts.REQUIRE_STATE_WAIT_RECEIVE_GOOD) {
            readyGoodLl.setVisibility(View.VISIBLE);
            setRequireToOrderButton();
            switch (requireBean.getState()){
                case Consts.REQUIRE_STATE_WAIT_SEND_GOOD:
                    pointSellerInfo.setText("买手正全力为您备货中…");
                    break;
                case Consts.REQUIRE_STATE_WAIT_RECEIVE_GOOD:
                    pointSellerInfo.setText("买手已发货，请到订单详情确认收货");
                    break;
            }

        } else if(requireBean.getState()== Consts.REQUIRE_STATE_INVALID){
            setInvalidRequireButton();
        } else if(requireBean.getState()== Consts.REQUIRE_STATE_REQUIRE_DONE) {
            setRequireToOrderButton();
            deleteFrame.setVisibility(View.VISIBLE);
        }

        requireState.setText(Consts.REQUIRE_STATE_STRINGS[requireBean.getState()]);

        if (isShowSuccessDialog) {
            showPublishSuccessDialog();
            isShowSuccessDialog = false;
        }
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUIRE_REQUEST_CANCEL:
                    requireState.setText("已失效");
                    requireBean.setState(Consts.REQUIRE_STATE_INVALID);
                    startActivity(new Intent(RequireDetailsActivity.this, RequireCancelSuccessActivity.class));
                    setInvalidRequireButton();
                    break;
                default:
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setInvalidRequireButton() {
        Drawable d = getResources().getDrawable(R.mipmap.ic_trash_can);
        d.setBounds(0, 0, d.getMinimumWidth(), d.getMinimumHeight());
        reward_text.setCompoundDrawables(d, null, null, null);
        reward_text.setCompoundDrawablePadding(3);
        reward_text.setText(R.string.delete);
        rewardFrame.setVisibility(View.VISIBLE);
        d = getResources().getDrawable(R.mipmap.ic_edit_g);
        d.setBounds(0, 0, d.getMinimumWidth(), d.getMinimumHeight());
        undoRequire.setCompoundDrawables(d, null, null, null);
        undoRequire.setCompoundDrawablePadding(3);
        undoRequire.setText(R.string.edit_retry);
    }

    private void setRequireToOrderButton(){
        Drawable d = getResources().getDrawable(R.mipmap.ic_details_news);
        d.setBounds(0, 0, d.getMinimumWidth(), d.getMinimumHeight());
        reward_text.setCompoundDrawables(d, null, null, null);
        reward_text.setCompoundDrawablePadding(3);
        reward_text.setText(R.string.contact_seller);
        rewardFrame.setVisibility(View.VISIBLE);
        d = getResources().getDrawable(R.mipmap.ic_details_news);
        d.setBounds(0, 0, d.getMinimumWidth(), d.getMinimumHeight());
        undoRequire.setCompoundDrawables(d, null, null, null);
        undoRequire.setCompoundDrawablePadding(3);
        undoRequire.setText(R.string.order_detail);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void showPublishSuccessDialog() {
        CommonDialog.Builder builder = new CommonDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.layout_publish_success)
                .setGravity(Gravity.CENTER)
                .addOnClickListener(R.id.close_publish_success_dialog)
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                        switch (view.getId()) {
                            case R.id.close_publish_success_dialog:
                                commonDialog.dismiss();
                                break;
                        }
                    }
                });

        dialog = builder.create().show();
    }

    @OnClick({R.id.reward_frame, R.id.undo_frame, R.id.back_btn, R.id.delete_frame})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reward_frame:
                if(requireBean.getState()==Consts.REQUIRE_STATE_WAIT_SELLER||requireBean.getState()==Consts.REQUIRE_STATE_WAIT_POINT) {
                    showRewardDialog();
                } else if(requireBean.getState()==Consts.REQUIRE_STATE_INVALID) {
                    finish();
                } else {
                    ChatActivity.navToChat(RequireDetailsActivity.this, "我是小桂子", TIMConversationType.C2C);
                }
                break;
            case R.id.undo_frame:
                if(requireBean.getState()==Consts.REQUIRE_STATE_WAIT_SELLER||requireBean.getState()==Consts.REQUIRE_STATE_WAIT_POINT) {
                    startActivityForResult(new Intent(RequireDetailsActivity.this, RequireCancelActivity.class), REQUIRE_REQUEST_CANCEL);
                } else if(requireBean.getState()==Consts.REQUIRE_STATE_INVALID) {
                    startActivity(PublishRequireActivity.class);
                } else {
                    Intent intent = new Intent(RequireDetailsActivity.this, OrderDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(OrderHelper.ORDER_ID, getFakeData().getOrderId());
                    bundle.putInt(Consts.USER_TYPE, Consts.USER_TYPE_BACK_PACKER);
                    bundle.putSerializable(OrderDetailActivity.FAKE_DATA, getFakeData());
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                }
                break;

            case R.id.back_btn:
                finish();
                break;

            case R.id.delete_frame:
                finish();
                break;
        }
    }

    private OrderBean getFakeData() {
        OrderGoodsInfo goodsInfo1 = new OrderGoodsInfo("1",
                "NIKE HUARACHE DRIFT (PSE) LALALALALA",
                "黑白/36.5",
                "",
                "￥9,918.00",
                1, "");
        OrderGoodsInfo goodsInfo2 = new OrderGoodsInfo("2",
                "NIKE HUARACHE DRIFT (PSE) LALALALALA",
                "黑白/34",
                "",
                "￥8,918.00",
                1, "");
        ArrayList<OrderGoodsInfo> fakeGoodsList2 = new ArrayList<>();
        fakeGoodsList2.add(goodsInfo1);
        fakeGoodsList2.add(goodsInfo2);

        OrderBean orderBean = new OrderBean("1",
                OrderHelper.STATUS_BUYER_AWAIT_SHIPPING,
                "",
                "我是小桂子的桂子",
                "",
                "",
                2,
                "￥18,866.00",
                "￥30.00",
                "",
                OrderHelper.GOODS_TYPE_STOCK,
                fakeGoodsList2);

        return orderBean;
    }

    private void showRewardDialog() {
        CommonDialog.Builder builder = new CommonDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.layout_select_reward)
                .setGravity(Gravity.BOTTOM)
                .addOnClickListener(R.id.select_reward_close, R.id.pay_confirm, R.id.reward_50,
                        R.id.reward_100, R.id.reward_200, R.id.reward_500, R.id.reward_1000, R.id.reward_other_fl)
                .setOnBindViewListener(new OnBindViewListener() {
                    @Override
                    public void bindView(BindViewHolder viewHolder, CommonDialog dialog) {
                        TextView other = viewHolder.getView(R.id.reward_other);
                        if (reward != 0) {
                            other.setText(Integer.toString(reward));
                            Drawable d = getResources().getDrawable(R.mipmap.ic_edit_b);
                            d.setBounds(0, 0, d.getMinimumWidth(), d.getMinimumHeight());
                            other.setCompoundDrawables(null, null, d, null);
                            other.setCompoundDrawablePadding(3);
                            viewHolder.getView(R.id.reward_other_fl).setSelected(true);
                        }
                    }
                })
                .setOnViewClickListener(new OnViewClickListener() {
                    TextView focus_text = null;

                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                        if (focus_text != null) focus_text.setSelected(false);
                        switch (view.getId()) {
                            case R.id.select_reward_close:
                                commonDialog.dismiss();
                                break;

                            case R.id.reward_50:
                                focus_text = (TextView) view;
                                reward = 50;
                                break;
                            case R.id.reward_100:
                                focus_text = (TextView) view;
                                reward = 100;
                                break;
                            case R.id.reward_200:
                                focus_text = (TextView) view;
                                reward = 200;
                                break;
                            case R.id.reward_500:
                                focus_text = (TextView) view;
                                reward = 500;
                                break;
                            case R.id.reward_1000:
                                focus_text = (TextView) view;
                                reward = 1000;
                                break;
                            case R.id.reward_other_fl:
                                commonDialog.dismiss();
                                showRewardInputDialog();
                                break;
                            case R.id.pay_confirm:
                                commonDialog.dismiss();
                                showPaymentDialog();
                                break;
                        }
                        if (focus_text != null) focus_text.setSelected(true);
                    }
                });

        dialog = builder.create().show();
    }

    private void showRewardInputDialog() {
        CommonDialog.Builder builder = new CommonDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.layout_input_reward)
                .setWidth(ScreenUtils.getScreenWidth(RequireDetailsActivity.this))
                .setGravity(Gravity.BOTTOM)
                .addOnClickListener(R.id.reward_input_ok, R.id.input_reward_close)
                .setOnBindViewListener(new OnBindViewListener() {
                    @Override
                    public void bindView(final BindViewHolder viewHolder, final CommonDialog dialog) {
                        final KeyboardUtil keyboardUtil = new KeyboardUtil(viewHolder.getView(R.id.input_price_ll));
                        EditText input = viewHolder.getView(R.id.reward_input);
                        input.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                keyboardUtil.showKeyboard();
                                return false;
                            }
                        });
                        keyboardUtil.attachTo(input);
                        keyboardUtil.setOnOkClick(new KeyboardUtil.OnOkClick() {
                            @Override
                            public void onOkClick() {
                                EditText input = viewHolder.getView(R.id.reward_input);
                                if(!TextUtils.isEmpty(input.getText())) {
                                    int in = Integer.parseInt(input.getText().toString().trim());
                                    if (in > 0 && in <= 2000) {
                                        reward = in;
                                        dialog.dismiss();
                                        showRewardDialog();
                                    }
                                }
                            }
                        });
                    }
                })
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                        switch (view.getId()) {
                            case R.id.reward_input_ok:
                                EditText input = viewHolder.getView(R.id.reward_input);
                                if(!TextUtils.isEmpty(input.getText())) {
                                    int in = Integer.parseInt(input.getText().toString().trim());
                                    if (in > 0 && in <= 2000) {
                                        reward = in;
                                        commonDialog.dismiss();
                                        showRewardDialog();
                                    }
                                }
                                break;

                            case R.id.input_reward_close:
                                commonDialog.dismiss();
                                showRewardDialog();
                                break;
                        }
                    }
                });

        dialog = builder.create().show();
    }

    private void showPaymentDialog() {
        CommonDialog.Builder builder = new CommonDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.layout_payment)
                .setWidth(ScreenUtils.getScreenWidth(RequireDetailsActivity.this))
                .setGravity(Gravity.BOTTOM)
                .setOnBindViewListener(new OnBindViewListener() {
                    @Override
                    public void bindView(BindViewHolder viewHolder, CommonDialog dialog) {
                        TextView price = viewHolder.getView(R.id.pay_price);
                        SpannableString spanText = new SpannableString(getString(R.string.pay_price));

                        SpannableString spanText1 = new SpannableString(getString(R.string.money_unit)
                                + reward);

                        spanText.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.txt_gray_dark)),
                                0, spanText.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                        spanText1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.txt_price)),
                                0, spanText1.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

                        price.setText(spanText);
                        price.append(spanText1);
                    }
                })
                .addOnClickListener(R.id.pay_confirm, R.id.pay_close)
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                        switch (view.getId()) {
                            case R.id.pay_confirm:
                                rewardPaySuccess();
                            case R.id.pay_close:
                                commonDialog.dismiss();
                                break;
                        }
                    }
                });

        dialog = builder.create().show();
    }

    private void rewardPaySuccess() {
        if (dialog != null && dialog.isVisible()) {
            dialog.dismiss();
        }
        rewardFrame.setVisibility(View.GONE);
        requireReward.setText(getString(R.string.extra_reward) +
                getString(R.string.four_space) + getString(R.string.money_unit) + reward);
        requireReward.setVisibility(View.VISIBLE);
    }
}
