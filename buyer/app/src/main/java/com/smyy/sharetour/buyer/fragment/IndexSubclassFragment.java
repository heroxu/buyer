package com.smyy.sharetour.buyer.fragment;

import android.os.Bundle;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.home.adapter.HomeFragmentRecyclerViewAdapter;
import com.smyy.sharetour.buyer.home.model.HomeNewSell;
import com.smyy.sharetour.buyer.home.model.HomeNewSellItem;
import com.smyy.sharetour.buyer.home.model.HomeRouteItem;
import com.smyy.sharetour.buyer.home.model.HomeRoute;
import com.smyy.sharetour.buyer.home.model.HomeRecyclerBaseBean;
import com.smyy.sharetour.buyer.home.model.HomeTitleBean;

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

    private HomeFragmentRecyclerViewAdapter mAdapter;
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
        List<HomeRecyclerBaseBean> homeRecyclerBaseBeans = new ArrayList<>();
        homeRecyclerBaseBeans.add(new HomeTitleBean("买手行程", "真实行程", HomeFragmentRecyclerViewAdapter.ITEM_TITLE,true, false));

        HomeRoute homeRoute = new HomeRoute();
        List<HomeRouteItem> homeRouteItems = new ArrayList<>();
        homeRoute.viewType = HomeFragmentRecyclerViewAdapter.ITEM_CHILD_ROUTE;
        homeRouteItems.add(new HomeRouteItem("1yue29","1yue30","222"));
        homeRouteItems.add(new HomeRouteItem("1yue22","1yue33","2332"));
        homeRouteItems.add(new HomeRouteItem("1yue29","1yue30","222"));
        homeRouteItems.add(new HomeRouteItem("1yue22","1yue33","2332"));
        homeRoute.routes = homeRouteItems;
        homeRecyclerBaseBeans.add(homeRoute);



        homeRecyclerBaseBeans.add(new HomeTitleBean("最新预售", "全球抢购", HomeFragmentRecyclerViewAdapter.ITEM_TITLE,false, false));
        HomeNewSell homeNewSell = new HomeNewSell();
        homeNewSell.viewType = HomeFragmentRecyclerViewAdapter.ITEM_CHILD_NEW_SELL;
        List<HomeNewSellItem> homeNewSellItems =new ArrayList<>();
        homeNewSellItems.add(new HomeNewSellItem("24小时30分", "预定200", "----", "NIKE HUARACHE DRIFT 运动鞋", "¥456.90", "韩国/乐天免税店"));
        homeNewSellItems.add(new HomeNewSellItem("24小时30分", "预定200", "----", "NIKE HUARACHE DRIFT 运动鞋", "¥456.90", "韩国/乐天免税店"));
        homeNewSellItems.add(new HomeNewSellItem("24小时30分", "预定200", "----", "NIKE HUARACHE DRIFT 运动鞋", "¥456.90", "韩国/乐天免税店"));
        homeNewSellItems.add(new HomeNewSellItem("24小时30分", "预定200", "----", "NIKE HUARACHE DRIFT 运动鞋", "¥456.90", "韩国/乐天免税店"));
        homeNewSell.newSellItems = homeNewSellItems;
        homeRecyclerBaseBeans.add(homeNewSell);


        homeRecyclerBaseBeans.add(new HomeTitleBean("火爆单品", "猜你喜欢", HomeFragmentRecyclerViewAdapter.ITEM_TITLE,false, true));
        homeRecyclerBaseBeans.add(new HomeTitleBean("推荐买手", "千挑万选", HomeFragmentRecyclerViewAdapter.ITEM_TITLE,true, false));
        homeRecyclerBaseBeans.add(new HomeTitleBean("精选内容", "读万卷书行万里路", HomeFragmentRecyclerViewAdapter.ITEM_TITLE,false, false));



        mAdapter = new HomeFragmentRecyclerViewAdapter(getActivity(), homeRecyclerBaseBeans);
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