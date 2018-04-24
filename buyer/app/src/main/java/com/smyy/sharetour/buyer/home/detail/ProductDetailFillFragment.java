package com.smyy.sharetour.buyer.home.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.home.adapter.SearchResultAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * create by xuxiarong on 2018/4/13
 */
public class ProductDetailFillFragment extends BaseMvpFragment {

    Unbinder unbinder;
    private List<String> mHotDatas = new ArrayList<>();
    private RecyclerView rvProductBag;

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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvProductBag = (RecyclerView) view.findViewById(R.id.rv_product_detail_bag);
        rvProductBag.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mHotDatas.add("化妆品");
        mHotDatas.add("电动车");
        mHotDatas.add("NIKE 运动鞋");
        mHotDatas.add("香奈儿");
        mHotDatas.add("化妆品");
        mHotDatas.add("电动车");
        mHotDatas.add("NIKE 运动鞋");
        mHotDatas.add("香奈儿");
        mHotDatas.add("化妆品");
        mHotDatas.add("电动车");
        mHotDatas.add("NIKE 运动鞋");        mHotDatas.add("香奈儿");
        mHotDatas.add("化妆品");
        mHotDatas.add("电动车");
        mHotDatas.add("NIKE 运动鞋");        mHotDatas.add("香奈儿");
        mHotDatas.add("化妆品");
        mHotDatas.add("电动车");
        mHotDatas.add("NIKE 运动鞋");
        mHotDatas.add("香奈儿");
        mHotDatas.add("化妆品");
        mHotDatas.add("电动车");
        mHotDatas.add("NIKE 运动鞋");
        mHotDatas.add("香奈儿");
        rvProductBag.setAdapter(new SearchResultAdapter(this.getContext(), mHotDatas));
    }
}
