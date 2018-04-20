package com.smyy.sharetour.buyer.publish;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.bean.RequireBean;
import com.xmyy.view.dialoglib.CommonDialog;
import com.xmyy.view.dialoglib.base.BindViewHolder;
import com.xmyy.view.dialoglib.listener.OnBindViewListener;
import com.xmyy.view.dialoglib.listener.OnViewClickListener;

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
        if(resultCode==RESULT_OK) {
            requireState.setText(R.string.cancel_over);
            startActivity(new Intent(RequireDetailsActivity.this, RequireCancelSuccessActivity.class));
            setInvalidRequireButton();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setInvalidRequireButton() {
        reward_text.setText(R.string.delete_require);
        reward_text.setVisibility(View.VISIBLE);
        undoRequire.setText(R.string.edit_retry);
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

    @OnClick({R.id.reward, R.id.undo_require})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reward:
                showRewardDialog();
                break;
            case R.id.undo_require:
                startActivityForResult(new Intent(RequireDetailsActivity.this, RequireCancelActivity.class), REQUIRE_REQUEST_CANCEL);
                break;
        }
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
                            Drawable d = getResources().getDrawable(R.mipmap.arrow_right_normal);
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
                .setGravity(Gravity.CENTER)
                .addOnClickListener(R.id.reward_input_ok, R.id.input_reward_close)
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                        switch (view.getId()) {
                            case R.id.reward_input_ok:
                                EditText input = viewHolder.getView(R.id.reward_input);
                                int in = Integer.parseInt(input.getText().toString().trim());
                                if (in > 0 && in <= 2000) {
                                    reward = in;
                                    commonDialog.dismiss();
                                    showRewardDialog();
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
                .addOnClickListener(R.id.pay_confirm)
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                        switch (view.getId()) {
                            case R.id.pay_confirm:
                                commonDialog.dismiss();
                                rewardPaySuccess();
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
        reward_text.setVisibility(View.GONE);
        requireReward.setText(getString(R.string.extra_reward) +
                getString(R.string.space) + getString(R.string.money_unit) + reward);
        requireReward.setVisibility(View.VISIBLE);
    }

}
