package com.smyy.sharetour.buyer.fragment;

import android.os.Bundle;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.home.adapter.HomeFragmentReclerViewAdapter;
import com.smyy.sharetour.buyer.home.model.HomeResultBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by 伍振飞 on 2018/3/23 10:13
 * E-Mail Address：wuzf2012@sina.com
 */
public class IndexSubclassFragment extends BaseMvpFragment {

    @BindView(R.id.home_all_srl)
    SwipeRefreshLayout home_all_srl;
    @BindView(R.id.home_all_rv)
    RecyclerView home_all_rv;

    private HomeFragmentReclerViewAdapter mAdapter;
    Unbinder unbinder;

    public static IndexSubclassFragment getInstance(String title) {
        IndexSubclassFragment sf = new IndexSubclassFragment();
        return sf;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fm_index_subclass;
    }

    private void initListener(){
        home_all_srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                loadData();
            }
        });
        home_all_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void loadData() {
        List<HomeResultBean> homeResultBeans = new ArrayList<>();
        homeResultBeans.add(new HomeResultBean("买手行程", "真实行程", HomeFragmentReclerViewAdapter.TITLE,true, false));
        homeResultBeans.add(new HomeResultBean("最新预售", "全球抢购", HomeFragmentReclerViewAdapter.TITLE,false, false));
        homeResultBeans.add(new HomeResultBean("火爆单品", "猜你喜欢", HomeFragmentReclerViewAdapter.TITLE,false, true));
        homeResultBeans.add(new HomeResultBean("推荐买手", "千挑万选", HomeFragmentReclerViewAdapter.TITLE,true, false));
        homeResultBeans.add(new HomeResultBean("精选内容", "读万卷书行万里路", HomeFragmentReclerViewAdapter.TITLE,false, false));
        mAdapter = new HomeFragmentReclerViewAdapter(getActivity(), homeResultBeans);
        home_all_rv.setAdapter(mAdapter);
    }

    @Override
    protected void initData(Bundle bundle) {
        initListener();
        loadData();
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

}