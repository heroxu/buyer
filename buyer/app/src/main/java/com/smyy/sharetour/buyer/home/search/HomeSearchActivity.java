package com.smyy.sharetour.buyer.home.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.smyy.sharetour.buyer.home.adapter.SearchHistoryAdapter;
import com.smyy.sharetour.buyer.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeSearchActivity extends BaseMvpActivity {


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

    private SearchHistoryAdapter mSearchHistoryAdapter;
    private List<String> mSearchDatas = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_search;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        hideToolBarLayout(true);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        initListener();
        if(mSearchHistoryAdapter == null){
            mSearchHistoryAdapter = new SearchHistoryAdapter(this,mSearchDatas);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        rvSearchHistory.setLayoutManager(linearLayoutManager);
//        rvSearchHot.setLayoutManager(linearLayoutManager);
        rvSearchResult.setLayoutManager(linearLayoutManager);
        rvSearchResult.setAdapter(mSearchHistoryAdapter);
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
                    mSearchHistoryAdapter.setData(mSearchDatas);
                }else {
                    llSearchHistory.setVisibility(View.VISIBLE);
                    rvSearchResult.setVisibility(View.GONE);
                }
                return false;
            }
        });
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }



}
