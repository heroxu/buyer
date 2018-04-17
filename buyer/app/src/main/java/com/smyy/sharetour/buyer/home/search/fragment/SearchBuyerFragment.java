package com.smyy.sharetour.buyer.home.search.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.home.model.SearchBuyer;
import com.smyy.sharetour.buyer.home.search.adapter.SearchBuyerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * create by xuxiarong on 2018/4/13
 */
public class SearchBuyerFragment extends BaseMvpFragment {


    @BindView(R.id.ll_search_buyer)
    LinearLayout llSearchBuyer;
    @BindView(R.id.ll_search_sort_smart)
    LinearLayout llSearchSortSmart;
    @BindView(R.id.rv_search_buyer)
    RecyclerView rvSearchBuyer;
    Unbinder unbinder;
    private ArrayList<SearchBuyer> searchBuyers = new ArrayList<>();
    private SearchBuyerAdapter mAdapter;

    public static SearchBuyerFragment getInstance(String title) {
        SearchBuyerFragment sf = new SearchBuyerFragment();
        return sf;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this.getActivity());

    }

    private void generateData() {
        searchBuyers.add(new SearchBuyer("小桂子的贵", "美国 纽约"));
        searchBuyers.add(new SearchBuyer("小桂子的贵", "加拿大 渥太华"));
        searchBuyers.add(new SearchBuyer("小桂子的贵", "日本 东京"));

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search_buyer;
    }

    @Override
    protected void initData(Bundle bundle) {
        generateData();
        rvSearchBuyer.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mAdapter = new SearchBuyerAdapter(getContext(), searchBuyers);
        rvSearchBuyer.setAdapter(mAdapter);
    }

}
