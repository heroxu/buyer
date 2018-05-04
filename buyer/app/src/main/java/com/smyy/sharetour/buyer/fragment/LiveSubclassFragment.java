package com.smyy.sharetour.buyer.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.adapter.BuyVideoAdapter;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.bean.BuyVideoBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * create by lifg on 2018/4/10
 */
public class LiveSubclassFragment  extends BaseMvpFragment {
    @BindView(R.id.live_list_recycler_view)
    RecyclerView recyclerView;
    private String mTitle;
    BuyVideoAdapter mAdapter;
    public  static  LiveSubclassFragment getInstance(String title){
        LiveSubclassFragment liveSubclassFragment=new LiveSubclassFragment();
        liveSubclassFragment.mTitle=title;
        return  liveSubclassFragment;
    }
    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_live_child_listpage;
    }

    @Override
    protected void initData(Bundle bundle) {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        List<BuyVideoBean> data = new ArrayList<>();
        data.add(new BuyVideoBean());
        data.add(new BuyVideoBean());
        data.add(new BuyVideoBean());
        data.add(new BuyVideoBean());
        data.add(new BuyVideoBean());
        data.add(new BuyVideoBean());
        data.add(new BuyVideoBean());
        data.add(new BuyVideoBean());
        data.add(new BuyVideoBean());
        data.add(new BuyVideoBean());
        mAdapter =new BuyVideoAdapter(data);
        recyclerView.setAdapter(mAdapter);


    }
}
