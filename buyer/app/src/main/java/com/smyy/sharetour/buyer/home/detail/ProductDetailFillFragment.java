package com.smyy.sharetour.buyer.home.detail;

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
public class ProductDetailFillFragment extends BaseMvpFragment {

    private ArrayList<SearchBuyer> searchBuyers = new ArrayList<>();
    private SearchBuyerAdapter mAdapter;

    public static ProductDetailFillFragment getInstance(String title) {
        ProductDetailFillFragment sf = new ProductDetailFillFragment();
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

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product_detail_fill;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

}
