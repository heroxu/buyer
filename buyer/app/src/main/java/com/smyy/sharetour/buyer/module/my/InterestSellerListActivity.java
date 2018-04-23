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
import com.smyy.sharetour.buyer.module.my.adapter.InterestSellerAdapter;
import com.smyy.sharetour.buyer.module.my.base.MyBaseMvpActivity;
import com.smyy.sharetour.buyer.module.my.base.MyBasePresenter;
import com.smyy.sharetour.buyer.module.my.bean.InterestSellerBean;
import com.smyy.sharetour.buyer.ui.buyCommodity.BuyHomePageActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class InterestSellerListActivity extends MyBaseMvpActivity {

    @BindView(R.id.rv_my_shipping_address)
    RecyclerView mRecyclerView;

    private InterestSellerAdapter mAdapter;
    private List<InterestSellerBean> mDatas = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_interest_seller;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("我的关注");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        initView();
        getFakeData();
    }

    private void getFakeData() {
        mDatas.add(new InterestSellerBean());
        mDatas.add(new InterestSellerBean());
        mDatas.add(new InterestSellerBean());
        mAdapter.setData(mDatas);
    }

    private void initView() {
        if (mAdapter == null) {
            mAdapter = new InterestSellerAdapter(this);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new InterestSellerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, InterestSellerBean data) {
                startActivity(BuyHomePageActivity.class);
            }
        });
    }

    @Override
    protected MyBasePresenter createPresenter() {
        return null;
    }
}
