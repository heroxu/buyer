package com.smyy.sharetour.buyer.backpacker.certification;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.util.ActivityLauncher;
import com.smyy.sharetour.buyer.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;


public class BackpackCertificationActivity3 extends BaseMvpActivity {
    @BindView(R.id.line_1)
    ImageView line1;
    @BindView(R.id.line_2)
    ImageView line2;
    @BindView(R.id.c_btn_1)
    CheckBox cBtn1;
    @BindView(R.id.c_btn_2)
    CheckBox cBtn2;
    @BindView(R.id.c_btn_3)
    CheckBox cBtn3;
    @BindView(R.id.tv_certification_1)
    CheckedTextView tvCertification1;
    @BindView(R.id.tv_certification_2)
    CheckedTextView tvCertification2;
    @BindView(R.id.tv_certification_3)
    CheckedTextView tvCertification3;
    @BindView(R.id.tv_agreement)
    TextView tvAgreement;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_backpack_certification_2;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("认证");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        hideToolBarDividerLine(true);
        initView();
        SpannableString spannableString = new SpannableString("同意《芝麻信用协议及授权条款》以及《背包客认证协议及条款》");
        ALiClickableSpan colorSpan1 = new ALiClickableSpan();
        BackpackClickableSpan colorSpan2 = new BackpackClickableSpan();
        spannableString.setSpan(colorSpan1, 2, 15, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(colorSpan2, 17, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvAgreement.setText(spannableString);
        tvAgreement.setMovementMethod(LinkMovementMethod.getInstance());//不设置 没有点击事件
        tvAgreement.setHighlightColor(Color.TRANSPARENT); //设置点击后的颜色为透明
    }
    private void initView() {
        cBtn1.setEnabled(true);
        cBtn2.setEnabled(true);
        cBtn3.setEnabled(true);
        tvCertification3.setEnabled(true);
    }
    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    class ALiClickableSpan extends ClickableSpan {
        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);         //设置文本的颜色
            ds.setColor(getResources().getColor(R.color.txt_hint));
            ds.setUnderlineText(false);
        }

        @Override
        public void onClick(View widget) {
            ToastUtils.showToast("芝麻被点击了");
        }
    }
    class BackpackClickableSpan extends ClickableSpan {

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);         //设置文本的颜色
            ds.setColor(getResources().getColor(R.color.txt_hint));
            ds.setUnderlineText(false);
        }

        @Override
        public void onClick(View widget) {
            ToastUtils.showToast("背包被点击了");
        }
    }
    @OnClick(R.id.btn_next)
    public void onViewClicked() {
        ActivityLauncher.viewALiCertificationActivity(this);
    }
}
