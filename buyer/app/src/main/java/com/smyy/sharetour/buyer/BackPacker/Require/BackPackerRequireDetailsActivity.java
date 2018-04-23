package com.smyy.sharetour.buyer.BackPacker.Require;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.bean.RequireBean;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Liliping
 * @org www.smyy.com
 * @email lilp@stjf.com
 * @package com.smyy.sharetour.buyer.BackPacker.Require
 * @fileName BackPackerRequireDetailsActivity
 * @date on 2018/4/23 0023 11:08
 * @describe 背包客需求详情页
 */
public class BackPackerRequireDetailsActivity extends BaseMvpActivity {


    public static final String REQUIRE_KEY = "require";
    public static final String REQUIRE_SUCCESS_KEY = "show_success";
    public static final String REQUIRE_TAKE_KEY = "take_require";
    public static final int REQUIRE_REQUEST_CANCEL = 100;
    @BindView(R.id.require_detail_time)
    TextView requireDetailTime;
    @BindView(R.id.require_detail_budget)
    TextView requireDetailBudget;
    @BindView(R.id.require_state_img)
    ImageView requireStateImg;
    @BindView(R.id.require_state)
    TextView requireState;
    @BindView(R.id.require_reward)
    TextView requireReward;
    @BindView(R.id.point_seller_info)
    TextView pointSellerInfo;
    @BindView(R.id.ready_good_ll)
    LinearLayout readyGoodLl;
    @BindView(R.id.cancel_take_require)
    TextView cancelTakeRequire;
    @BindView(R.id.cancel_frame)
    FrameLayout cancelFrame;
    @BindView(R.id.order_detail_frame)
    FrameLayout orderDetailFrame;
    @BindView(R.id.order_detail)
    TextView orderDetail;

    private RequireBean requireBean;
    private boolean isShowSuccessDialog = false;
    private boolean isTakeRequire = false;
    private int reward = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_backpacker_require_details;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {

    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        Bundle bundle = getBundle();
        requireBean = (RequireBean) bundle.getSerializable(REQUIRE_KEY);
        isTakeRequire = bundle.getBoolean(REQUIRE_TAKE_KEY);
        hideToolBarLayout(true);

        SpannableString spanText = new SpannableString(getString(R.string.pay_price) + getString(R.string.space));

        SpannableString spanText1 = new SpannableString(requireBean.getRequire_budget());

        spanText.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.txt_gray_dark)),
                0, spanText.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spanText1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.txt_price)),
                0, spanText1.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        requireDetailBudget.setText(spanText);
        requireDetailBudget.append(spanText1);

        spanText = new SpannableString(getString(R.string.expect_time) + getString(R.string.space));

        spanText1 = new SpannableString(requireBean.getRequire_time());

        spanText.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.txt_gray_dark)),
                0, spanText.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spanText1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.txt_price)),
                0, spanText1.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        requireDetailTime.setText(spanText);
        requireDetailTime.append(spanText1);
        if (requireBean.getReward() != null) {
            reward = Integer.parseInt(requireBean.getReward());
            if (reward != 0) {
                requireReward.setText(getString(R.string.extra_reward) +
                        getString(R.string.space) + getString(R.string.money_unit) + reward);
                requireReward.setVisibility(View.VISIBLE);
            }
        }

        if (requireBean.getState() == Consts.REQUIRE_STATE_WAIT_SEND_GOOD || requireBean.getState() == Consts.REQUIRE_STATE_WAIT_RECEIVE_GOOD) {
            readyGoodLl.setVisibility(View.VISIBLE);
            cancelFrame.setVisibility(View.GONE);
            switch (requireBean.getState()) {
                case Consts.REQUIRE_STATE_WAIT_SEND_GOOD:
                    pointSellerInfo.setText("请尽快为买家发货，并到订单详情填写物流信息");
                    pointSellerInfo.setTextColor(getResources().getColor(R.color.txt_price));
                    readyGoodLl.setVisibility(View.VISIBLE);
                    break;
            }

        } else if (requireBean.getState() == Consts.REQUIRE_STATE_CANCEL ) {
            orderDetailFrame.setVisibility(View.GONE);
        } else if(requireBean.getState() == Consts.REQUIRE_STATE_WAIT_POINT) {
            readyGoodLl.setVisibility(View.VISIBLE);
            setWaitPointButton();
        }

        requireState.setText(Consts.REQUIRE_STATE_STRINGS[requireBean.getState()]);

        if (isTakeRequire) {
            setViewRequireButton();
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
                    requireState.setText(R.string.cancel_over);
                    requireBean.setState(Consts.REQUIRE_STATE_CANCEL);
                    setCancelButton();
                    startActivity(new Intent(BackPackerRequireDetailsActivity.this, BackPackerRequireCancelSuccessActivity.class));
                    break;
                default:
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void setWaitPointButton() {
        orderDetailFrame.setVisibility(View.GONE);
        Drawable d = getResources().getDrawable(R.mipmap.ic_undo_require);
        d.setBounds(0, 0, d.getMinimumWidth(), d.getMinimumHeight());
        cancelTakeRequire.setCompoundDrawables(d, null, null, null);
        cancelTakeRequire.setCompoundDrawablePadding(3);
        cancelTakeRequire.setText(R.string.cancel);
    }

    private void setCancelButton() {
        orderDetailFrame.setVisibility(View.GONE);
        Drawable d = getResources().getDrawable(R.mipmap.ic_undo_require);
        d.setBounds(0, 0, d.getMinimumWidth(), d.getMinimumHeight());
        cancelTakeRequire.setCompoundDrawables(d, null, null, null);
        cancelTakeRequire.setCompoundDrawablePadding(3);
        cancelTakeRequire.setText(R.string.delete);
    }


    private void setViewRequireButton() {
        cancelFrame.setVisibility(View.GONE);
        Drawable d = getResources().getDrawable(R.mipmap.ic_details_news);
        d.setBounds(0, 0, d.getMinimumWidth(), d.getMinimumHeight());
        orderDetail.setCompoundDrawables(d, null, null, null);
        orderDetail.setCompoundDrawablePadding(3);
        orderDetail.setText(R.string.take_require);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    @OnClick({R.id.contact_frame, R.id.cancel_frame, R.id.order_detail_frame})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.contact_frame:
                break;
            case R.id.cancel_frame:
                if(requireBean.getState()==Consts.REQUIRE_STATE_WAIT_POINT){
                    Intent intent = new Intent(BackPackerRequireDetailsActivity.this, BackPackerRequireCancelActivity.class);
                    startActivityForResult(intent, REQUIRE_REQUEST_CANCEL);
                }
                break;
            case R.id.order_detail_frame:
                if(isTakeRequire){
                    startActivity(new Intent(BackPackerRequireDetailsActivity.this, BackpackerTakeRequireActivity.class));
                    finish();
                }
                break;
        }
    }

}
