package com.smyy.sharetour.buyer.home.search.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.adapter.BuyVideoAdapter;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.bean.BuyVideoBean;
import com.smyy.sharetour.buyer.home.search.SearchBuyVideoBean;
import com.smyy.sharetour.buyer.home.search.adapter.SearchNotesAdapter;
import com.smyy.sharetour.buyer.home.search.adapter.SearchVideoAdapter;
import com.smyy.sharetour.buyer.util.ActivityLauncher;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 伍振飞 on 2018/4/18 09:44
 * E-Mail Address：wuzf2012@sina.com
 */
public class SearchVideoFragment extends BaseMvpFragment {
    private static final String ARGS_DATA = "data.args";
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    SearchVideoAdapter mAdapter;

    public static SearchVideoFragment getInstance(String data) {
        Bundle args = new Bundle();
        args.putString(ARGS_DATA, data);
        SearchVideoFragment fragment = new SearchVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_buy_video;
    }

    @Override
    protected void initData(Bundle bundle) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<SearchBuyVideoBean> data = new ArrayList<>();
        data.add(new SearchBuyVideoBean());
        data.add(new SearchBuyVideoBean());
        data.add(new SearchBuyVideoBean());
        data.add(new SearchBuyVideoBean());
        data.add(new SearchBuyVideoBean());
        data.add(new SearchBuyVideoBean());
        data.add(new SearchBuyVideoBean());
        data.add(new SearchBuyVideoBean());
        data.add(new SearchBuyVideoBean());
        data.add(new SearchBuyVideoBean());
        mAdapter = new SearchVideoAdapter(data);
        recyclerView.setAdapter(mAdapter);
        //添加Android自带的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ActivityLauncher.viewVideoDetailsActivity(getActivity());
            }
        });
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }
}