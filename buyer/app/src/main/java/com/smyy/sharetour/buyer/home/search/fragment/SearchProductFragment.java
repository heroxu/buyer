package com.smyy.sharetour.buyer.home.search.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.home.model.SearchProduct;
import com.smyy.sharetour.buyer.home.search.adapter.SearchProductAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * create by xuxiarong on 2018/4/13
 */
public class SearchProductFragment extends BaseMvpFragment implements View.OnClickListener{

    @BindView(R.id.tv_search_sort_sell)
    TextView tvSearchSortSell;
    @BindView(R.id.tv_search_sort_new)
    TextView tvSearchSortNew;
    @BindView(R.id.tv_search_sort_price)
    TextView tvSearchSortPrice;
    @BindView(R.id.ll_search_sort_price)
    LinearLayout llSearchSortPrice;
    @BindView(R.id.rv_search_product)
    RecyclerView rvSearchProduct;
    private List<SearchProduct> mSearchProduct = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;

    public static SearchProductFragment getInstance(String title) {
        SearchProductFragment sf = new SearchProductFragment();
        return sf;
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search_product;
    }

    @Override
    protected void initData(Bundle bundle) {
        tvSearchSortSell.setOnClickListener(this);
        tvSearchSortNew.setOnClickListener(this);
        llSearchSortPrice.setOnClickListener(this);
        rvSearchProduct.setLayoutManager(new LinearLayoutManager(getContext()));
        generateData();
        mAdapter = new SearchProductAdapter(getContext(), mSearchProduct);
        rvSearchProduct.setAdapter(mAdapter);
    }

    private void generateData() {
        mSearchProduct.add(new SearchProduct("NIKE 超酷跑鞋", "sdaaa", "W 1232.00", "约2334元", "sss", "韩国"));
        mSearchProduct.add(new SearchProduct("NIKE 超酷跑鞋", "sdaaa", "W 6762.00", "2334", "sss", "韩国"));
        mSearchProduct.add(new SearchProduct("NIKE 超酷跑鞋", "sdaaa", "W 6543.00", "2334", "sss", "韩国"));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search_sort_sell:
                tvSearchSortSell.setSelected(true);
                tvSearchSortNew.setSelected(false);
                tvSearchSortPrice.setSelected(false);
                break;
            case R.id.tv_search_sort_new:
                tvSearchSortSell.setSelected(false);
                tvSearchSortNew.setSelected(true);
                tvSearchSortPrice.setSelected(false);
                break;
            case R.id.ll_search_sort_price:
                tvSearchSortSell.setSelected(false);
                tvSearchSortNew.setSelected(false);
                tvSearchSortPrice.setSelected(true);
                break;
            default:
                break;
        }
    }
}
