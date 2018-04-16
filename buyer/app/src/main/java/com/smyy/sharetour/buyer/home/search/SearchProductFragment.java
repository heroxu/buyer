package com.smyy.sharetour.buyer.home.search;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;

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
