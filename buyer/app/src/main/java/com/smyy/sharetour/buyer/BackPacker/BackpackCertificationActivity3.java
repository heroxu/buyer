package com.smyy.sharetour.buyer.BackPacker;

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
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.util.ToastUtils;

import butterknife.BindView;


public class BackpackCertificationActivity3 extends BaseMvpActivity {

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
        SpannableString spannableString = new SpannableString("同意《芝麻信用协议及授权条款》以及《背包客认证协议及条款》");
        ALiClickableSpan colorSpan1 = new ALiClickableSpan();
        BackpackClickableSpan colorSpan2 = new BackpackClickableSpan();
        spannableString.setSpan(colorSpan1, 2, 15, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(colorSpan2, 17, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvAgreement.setText(spannableString);
        tvAgreement.setMovementMethod(LinkMovementMethod.getInstance());//不设置 没有点击事件
        tvAgreement.setHighlightColor(Color.TRANSPARENT); //设置点击后的颜色为透明
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
}
