package com.smyy.sharetour.buyer.home.detail.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.home.model.ProductDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * create by xuxiarong on 2018/4/13
 */
public class ProductDetailFragment extends BaseMvpFragment {

    private List<ProductDetail> mDatas = new ArrayList<>();
    private RecyclerView rvProductBag;

    public static ProductDetailFragment getInstance(String title) {
        ProductDetailFragment sf = new ProductDetailFragment();
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvProductBag = (RecyclerView) view.findViewById(R.id.rv_product_detail_bag);
        rvProductBag.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mDatas.add(new ProductDetail("",""));
        rvProductBag.setAdapter(new ProductDetailAdapter(this.getContext(),mDatas));
    }


}
