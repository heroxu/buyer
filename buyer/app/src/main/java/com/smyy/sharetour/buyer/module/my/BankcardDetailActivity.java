package com.smyy.sharetour.buyer.module.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.dialog.DialogUtils;
import com.smyy.sharetour.buyer.module.my.base.MyBaseMvpActivity;
import com.smyy.sharetour.buyer.module.my.base.MyBasePresenter;
import com.smyy.sharetour.buyer.module.my.bean.BankcardBean;
import com.smyy.sharetour.buyer.util.Spanny;
import com.smyy.sharetour.buyer.util.StringUtil;
import com.xmyy.view.dialoglib.CommonDialog;
import com.xmyy.view.dialoglib.base.BindViewHolder;
import com.xmyy.view.dialoglib.listener.OnViewClickListener;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.OnClick;

public class BankcardDetailActivity extends MyBaseMvpActivity {
    public static final String BANKCARD_BEAN = "bankcard_bean";

    @BindView(R.id.lay_my_bankcard_bg)
    View layBg;

    @BindView(R.id.iv_my_bankcard_logo)
    ImageView ivLogo;

    @BindView(R.id.tv_my_bankcard_name)
    TextView tvName;

    @BindView(R.id.tv_my_bankcard_type)
    TextView tvType;

    @BindView(R.id.tv_my_bankcard_num)
    TextView tvNum;

    @BindView(R.id.tv_my_contact_bank_service)
    TextView tvContactService;

    private String mBankServiceTel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_bankcard_detail;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("我的银行卡");
        TextView toolbarRightTv = getToolbarRightTv();
        toolbarRightTv.setVisibility(View.VISIBLE);
        toolbarRightTv.setTextColor(getResources().getColor(R.color.txt_main));
        toolbarRightTv.setText("设置");
        toolbarRightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.showBottomMsgMenu(BankcardDetailActivity.this,
                        "解除绑定后银行将不可使用，包括支付",
                        "确定解除绑定",
                        new OnViewClickListener() {
                            @Override
                            public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                                commonDialog.dismiss();
                                DialogUtils.showPayPwdDialog(BankcardDetailActivity.this, new DialogUtils.OnPwdInputListener() {
                                    @Override
                                    public void onFinish() {

                                    }
                                });
                            }
                        });
            }
        });
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Bundle bundle = getBundle();
        if (bundle != null) {
            Serializable serializable = bundle.getSerializable(BANKCARD_BEAN);
            if (serializable != null && serializable instanceof BankcardBean) {
                BankcardBean bankcardBean = (BankcardBean) serializable;
                layBg.setBackground(getResources().getDrawable(bankcardBean.getBgRes()));
                ivLogo.setBackground(getResources().getDrawable(bankcardBean.getLogoRes()));
                tvName.setText(bankcardBean.getName());
                tvType.setText(bankcardBean.getType());
                tvNum.setText(StringUtil.getBankcardNum(bankcardBean.getNum()));

                mBankServiceTel = bankcardBean.getServiceTel();
                if (!StringUtil.isEmpty(mBankServiceTel)) {
                    tvContactService.setText(new Spanny("客服热线 ")
                            .append(mBankServiceTel.trim(), new ForegroundColorSpan(getResources().getColor(R.color.txt_hint))));
                }
            }
        }
    }

    @OnClick({R.id.tv_my_contact_bank_service})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tv_my_contact_bank_service:
                if (!StringUtil.isEmpty(mBankServiceTel)) {
                    DialogUtils.showCallDialog(this, mBankServiceTel.trim());
                }
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
