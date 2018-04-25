package com.smyy.sharetour.buyer.backpacker.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.dialog.DialogUtils;
import com.xmyy.view.dialoglib.CommonDialog;
import com.xmyy.view.dialoglib.base.BindViewHolder;
import com.xmyy.view.dialoglib.listener.OnViewClickListener;

import butterknife.OnClick;

public class UploadShippingInfoActivity extends BaseMvpActivity {
    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_upload_shipping_info;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("填写物流信息");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {

    }

    @OnClick({R.id.btn_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_confirm:
                DialogUtils.showTwoBtnMsgBox(this,null,  "确认发货物流信息吗？", new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                        commonDialog.dismiss();
                        finish();
                    }
                });
                break;

            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            DialogUtils.showTwoBtnMsgBox(this, null,"您发货的物流信息尚未提交，确定退出吗？",  new OnViewClickListener() {
                @Override
                public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                    commonDialog.dismiss();
                    finish();
                }
            });
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DialogUtils.showTwoBtnMsgBox(this, null,"您发货的物流信息尚未提交，确定退出吗？",  new OnViewClickListener() {
            @Override
            public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                commonDialog.dismiss();
                finish();
            }
        });
    }
}

