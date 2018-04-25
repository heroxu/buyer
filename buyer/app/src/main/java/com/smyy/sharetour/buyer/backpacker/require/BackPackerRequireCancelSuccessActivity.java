package com.smyy.sharetour.buyer.backpacker.require;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
* @author Liliping
* @org www.smyy.com
* @email lilp@stjf.com
* @package com.smyy.sharetour.buyer.BackPacker.Require
* @fileName BackPackerRequireCancelSuccessActivity
* @date on 2018/4/23 0023 14:23
* @describe 背包客撤销接需求成功
*/
public class BackPackerRequireCancelSuccessActivity extends BaseMvpActivity {

    @BindView(R.id.pay_group)
    RadioGroup payGroup;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_backpacker_require_cancel_success;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText(R.string.cancel_success);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {

    }


    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }


    @OnClick(R.id.pay_confirm)
    public void onViewClicked() {
        int i = payGroup.getCheckedRadioButtonId();
        finish();
    }
}
