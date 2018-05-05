package com.smyy.sharetour.buyer.home.search.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.db.HomeSearch;
import com.smyy.sharetour.buyer.db.operate.HomeSearchDaoOpe;
import com.smyy.sharetour.buyer.home.adapter.SearchHistoryAdapter;
import com.smyy.sharetour.buyer.home.adapter.SearchResultAdapter;
import com.smyy.sharetour.buyer.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearchActivity extends BaseMvpActivity implements View.OnClickListener{
    public static final String TYPE = "SearchActivity.TYPE";
    public static final String BUNDLE_HOME = "SearchActivity.BUNDLE_HOME";
    public static final String BUNDLE_FOUNT = "SearchActivity.BUNDLE_FOUNT";
    public static final String BUNDLE_BACK_PACKER = "SearchActivity.BUNDLE_BACK_PACKER";
    @BindView(R.id.iv_home_search_back)
    ImageView ivHomeSearchBack;
    @BindView(R.id.tv_home_search_cancel)
    TextView tvHomeSearchCancel;
    @BindView(R.id.sv_home_search)
    SearchView svHomeSearch;
    @BindView(R.id.rv_search_result)
    RecyclerView rvSearchResult;
    @BindView(R.id.iv_search_clear)
    ImageView ivSearchClear;
    @BindView(R.id.rv_search_history)
    RecyclerView rvSearchHistory;
    @BindView(R.id.rv_search_hot)
    RecyclerView rvSearchHot;
    @BindView(R.id.ll_search_history)
    LinearLayout llSearchHistory;
    private String searchType;//判断是搜索什么类型的东西
    private SearchHistoryAdapter mSearchHistoryAdapter;
    private SearchResultAdapter mSearchResultAdapter;

    private List<String> mSearchDatas = new ArrayList<>();
    private List<HomeSearch> mHistoryDatas = new ArrayList<>();
    private List<String> mHotDatas = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_search;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        if(getIntent()!=null){
            searchType = getIntent().getStringExtra(TYPE);
        }
        hideToolBarLayout(true);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        initListener();
        if(mSearchResultAdapter == null){
            mSearchResultAdapter = new SearchResultAdapter(this,mSearchDatas,searchType);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvSearchResult.setLayoutManager(linearLayoutManager);
        rvSearchResult.setAdapter(mSearchResultAdapter);

        rvSearchHot.setLayoutManager(new GridLayoutManager(this,3));
        mHotDatas.add("化妆品");
        mHotDatas.add("电动车");
        mHotDatas.add("NIKE 运动鞋");
        mHotDatas.add("香奈儿");
        mHotDatas.add("化妆品");
        mHotDatas.add("电动车");
        mHotDatas.add("NIKE 运动鞋");
        mHotDatas.add("香奈儿");
        rvSearchHot.setAdapter(new SearchResultAdapter(this,mHotDatas, searchType));

        rvSearchHistory.setLayoutManager(new GridLayoutManager(this,3));
        mHistoryDatas = HomeSearchDaoOpe.queryAll(this);
        if(mSearchHistoryAdapter == null){
            mSearchHistoryAdapter = new SearchHistoryAdapter(this, mHistoryDatas,searchType);
        }
        rvSearchHistory.setAdapter(mSearchHistoryAdapter);
    }

    private void initListener() {
        svHomeSearch.setIconified(false);
        svHomeSearch.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                return true;
            }
        });
        svHomeSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                ToastUtils.showToast(newText).show();
                if(newText.length()>0){
                    llSearchHistory.setVisibility(View.GONE);
                    rvSearchResult.setVisibility(View.VISIBLE);
                    mSearchDatas.clear();
                    for (int i = 0; i < 10; i++) {
                        mSearchDatas.add(newText+i);
                    }
                    mSearchResultAdapter.setData(mSearchDatas);
                }else {
                    llSearchHistory.setVisibility(View.VISIBLE);
                    rvSearchResult.setVisibility(View.GONE);
                }
                return false;
            }
        });
        ivHomeSearchBack.setOnClickListener(this);
        ivSearchClear.setOnClickListener(this);
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_home_search_back:
                finish();
                break;
            case R.id.iv_search_clear:
                HomeSearchDaoOpe.deleteAllData(SearchActivity.this);
                mHistoryDatas.clear();
                mSearchHistoryAdapter.notifyDataSetChanged();
                break;
        }
    }
}
