package com.smyy.sharetour.buyer.backpacker.my;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.backpacker.my.adapter.AccountStatementAdapter;
import com.smyy.sharetour.buyer.backpacker.my.bean.AccountStatementBean;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.module.my.QuestionActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyWalletActivity extends BaseMvpActivity {
    @BindView(R.id.lay_my_remind)
    View layRemind;

    @BindView(R.id.rv_my_account_statement)
    RecyclerView rvAccountStatement;
    @BindView(R.id.tv_my_money_num)
    TextView tvMoneyNum;
    @BindView(R.id.tv_my_withdraw)
    TextView tvWithdraw;
    private AccountStatementAdapter mStatementAdapter;

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_wallet;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        hideToolBarLayout(true);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        tvMoneyNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        rvAccountStatement.setLayoutManager(new LinearLayoutManager(this));
        mStatementAdapter = new AccountStatementAdapter(this);
        rvAccountStatement.setAdapter(mStatementAdapter);
        getFakeData();
    }

    private void getFakeData() {
        List<AccountStatementBean> list = new ArrayList<>();
        list.add(new AccountStatementBean("一只大榴莲", "2018-01-21 10:30:12", "100.00", Consts.STATEMENT_TYPE_IN));
        list.add(new AccountStatementBean("两梳大香蕉", "2018-01-21 10:30:12", "100.00", Consts.STATEMENT_TYPE_IN));
        list.add(new AccountStatementBean("TTCHEUNG", "2018-01-21 10:30:12", "100.00", Consts.STATEMENT_TYPE_OUT));
        list.add(new AccountStatementBean("一只大榴莲", "2018-01-21 10:30:12", "100.00", Consts.STATEMENT_TYPE_IN));
        list.add(new AccountStatementBean("两梳大香蕉", "2018-01-21 10:30:12", "100.00", Consts.STATEMENT_TYPE_IN));
        list.add(new AccountStatementBean("TTCHEUNG", "2018-01-21 10:30:12", "100.00", Consts.STATEMENT_TYPE_OUT));
        mStatementAdapter.setData(list);
    }

    @OnClick({R.id.iv_close, R.id.iv_my_ques, R.id.iv_my_close_remind, R.id.tv_my_withdraw})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.iv_close:
                finish();
                break;

            case R.id.iv_my_ques:
                startActivity(QuestionActivity.class);
                break;

            case R.id.iv_my_close_remind:
                layRemind.setVisibility(View.GONE);
                break;

            case R.id.tv_my_withdraw:
                startActivity(new Intent(MyWalletActivity.this, BackpackerWithdrawActivity.class));
                break;

            default:
                break;
        }
    }

    @Override
    protected void initStatusBar() {
        setStatusBar(Color.BLACK);
    }
}
