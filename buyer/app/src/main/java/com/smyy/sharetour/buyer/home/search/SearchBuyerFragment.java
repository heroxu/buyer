package com.smyy.sharetour.buyer.home.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;

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

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search_buyer;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

}
