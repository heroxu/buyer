package com.smyy.sharetour.buyer.backpacker.require;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.xmyy.view.dialoglib.CommonDialog;
import com.xmyy.view.dialoglib.base.BindViewHolder;
import com.xmyy.view.dialoglib.listener.OnBindViewListener;
import com.xmyy.view.dialoglib.listener.OnViewClickListener;
import com.yongchun.library.utils.ScreenUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Liliping
 * @org www.smyy.com
 * @email lilp@stjf.com
 * @package com.smyy.sharetour.buyer.BackPacker.Require
 * @fileName BackpackerTakeRequireActivity
 * @date on 2018/4/22 0022 17:38
 * @describe 背包客接需求
 */
public class BackpackerTakeRequireActivity extends BaseMvpActivity {


    @BindView(R.id.backpacker_item_image)
    ImageView backpackerItemImage;
    @BindView(R.id.backpacker_item_disc)
    TextView backpackerItemDisc;
    @BindView(R.id.backpacker_item_price)
    TextView backpackerItemPrice;
    @BindView(R.id.backpacker_item_time)
    TextView backpackerItemTime;
    @BindView(R.id.check_assurance)
    CheckBox checkAssurance;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_backpacke_take_require_layout;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText(R.string.take_require);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
    }


    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }


    @OnClick({R.id.confirm_take_require, R.id.assurance_frame})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.confirm_take_require:
                if(checkAssurance.isChecked()){
                    showPaymentDialog(5);
                }
                break;
            case R.id.assurance_frame:
                checkAssurance.setChecked(true);
                break;
        }
    }

    private void showPaymentDialog(final int assurance) {
        CommonDialog.Builder builder = new CommonDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.layout_payment)
                .setWidth(ScreenUtils.getScreenWidth(BackpackerTakeRequireActivity.this))
                .setGravity(Gravity.BOTTOM)
                .setOnBindViewListener(new OnBindViewListener() {
                    @Override
                    public void bindView(BindViewHolder viewHolder, CommonDialog dialog) {
                        TextView price = viewHolder.getView(R.id.pay_price);
                        SpannableString spanText = new SpannableString(getString(R.string.pay_price));

                        SpannableString spanText1 = new SpannableString(getString(R.string.money_unit)
                                + assurance);

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

        builder.create().show();
    }

    private void rewardPaySuccess() {
        startActivity(new Intent(BackpackerTakeRequireActivity.this, BackpackerTakeRequireSuccessActivity.class));
        finish();
    }
}
