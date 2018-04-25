package com.smyy.sharetour.buyer.module.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.module.my.adapter.BankcardAdapter;
import com.smyy.sharetour.buyer.module.my.adapter.InterestSellerAdapter;
import com.smyy.sharetour.buyer.module.my.base.MyBaseMvpActivity;
import com.smyy.sharetour.buyer.module.my.base.MyBasePresenter;
import com.smyy.sharetour.buyer.module.my.bean.BankcardBean;
import com.smyy.sharetour.buyer.module.my.bean.BankcardBean;
import com.smyy.sharetour.buyer.ui.buyCommodity.BuyHomePageActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BankcardListActivity extends MyBaseMvpActivity {

    @BindView(R.id.rv_my_interest_seller)
    RecyclerView mRecyclerView;

    private BankcardAdapter mAdapter;
    private List<BankcardBean> mDatas = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_bankcard;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("我的银行卡");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        initView();
        getFakeData();
    }

    private void getFakeData() {
        mDatas.add(new BankcardBean(R.drawable.fake_bg1, R.mipmap.fake_logo1, "招商银行", "储蓄卡", "**** **** **** 1234"));
        mDatas.add(new BankcardBean(R.drawable.fake_bg2, R.mipmap.fake_logo2, "华夏银行", "储蓄卡", "**** **** **** 1234"));
        mDatas.add(new BankcardBean(R.drawable.fake_bg3, R.mipmap.fake_logo3, "建设银行", "储蓄卡", "**** **** **** 1234"));

        mAdapter.setData(mDatas);
    }

    private void initView() {
        if (mAdapter == null) {
            mAdapter = new BankcardAdapter(this);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BankcardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, BankcardBean data) {

            }
        });
    }

    @Override
    protected MyBasePresenter createPresenter() {
        return null;
    }
}
