package com.smyy.sharetour.buyer.BackPacker.my.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;

import butterknife.OnClick;

/**
 * @author Liliping
 * @org www.smyy.com
 * @email lilp@stjf.com
 * @package com.smyy.sharetour.buyer.BackPacker.my.adapter
 * @fileName BackpackerWithdrawProgressActivity
 * @date on 2018/4/25 0025 17:33
 * @describe 提现进度
 */
public class BackpackerWithdrawProgressActivity extends BaseMvpActivity {


    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_backpacker_withdraw_progress;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("提现");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
    }



    @OnClick(R.id.confirm)
    public void onViewClicked() {
        finish();
    }
}
