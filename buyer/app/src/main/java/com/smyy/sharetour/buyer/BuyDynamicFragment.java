package com.smyy.sharetour.buyer;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.smyy.sharetour.buyer.adapter.CollectionAdapter;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.bean.CollectionBean;
import com.smyy.sharetour.buyer.view.SwipeItemLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 伍振飞 on 2018/4/18 09:44
 * E-Mail Address：wuzf2012@sina.com
 */
public class BuyDynamicFragment extends BaseMvpFragment {
    private static final String ARGS_DATA = "data.args";
    @BindView(R.id.rv_collection)
    RecyclerView rvCollection;
    CollectionAdapter mCollectionAdapter;
    private int mFragmentTag;

    public static BuyDynamicFragment newInstance(String data) {
        Bundle args = new Bundle();
        args.putString(ARGS_DATA, data);
        BuyDynamicFragment fragment = new BuyDynamicFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collection;
    }

    @Override
    protected void initData(Bundle bundle) {
        mFragmentTag = Integer.parseInt(getArguments().getString(ARGS_DATA));
        rvCollection.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<CollectionBean> data = new ArrayList<>();
        data.add(new CollectionBean(mFragmentTag));
        data.add(new CollectionBean(mFragmentTag));
        data.add(new CollectionBean(mFragmentTag));
        data.add(new CollectionBean(mFragmentTag));
        data.add(new CollectionBean(mFragmentTag));
        data.add(new CollectionBean(mFragmentTag));
        mCollectionAdapter = new CollectionAdapter(data);
        rvCollection.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(getActivity()));
        rvCollection.setAdapter(mCollectionAdapter);
        //添加Android自带的分割线
        rvCollection.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }
}