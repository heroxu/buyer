package com.smyy.sharetour.buyer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.home.model.SearchBuyer;
import com.smyy.sharetour.buyer.home.search.adapter.SearchBuyerAdapter;
import com.smyy.sharetour.buyer.util.ActivityLauncher;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecommendBuyActivity extends BaseMvpActivity {

    @BindView(R.id.ll_recommend_buy)
    LinearLayout llRecommendBuy;
    @BindView(R.id.ll_recommend_buy_sort_smart)
    LinearLayout llRecommendBuySortSmart;
    @BindView(R.id.rv_recommend_buy)
    RecyclerView rvRecommendBuy;
    private ArrayList<SearchBuyer> searchBuyers = new ArrayList<>();
    private RecommendBuyAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recommend_buy;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("推荐买手");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        generateData();
        rvRecommendBuy.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecommendBuyAdapter(searchBuyers);
        rvRecommendBuy.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ActivityLauncher.viewBuyHomePageActivity(RecommendBuyActivity.this);
            }
        });
    }

    private void generateData() {
        searchBuyers.add(new SearchBuyer("小桂子的贵", "美国 纽约"));
        searchBuyers.add(new SearchBuyer("小桂子的贵", "加拿大 渥太华"));
        searchBuyers.add(new SearchBuyer("小桂子的贵", "日本 东京"));
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

}
