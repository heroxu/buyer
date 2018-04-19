package com.smyy.sharetour.buyer.ui.buyCommodity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.adapter.BuyCommodityAdapter;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.bean.BuyCommodityBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 伍振飞 on 2018/4/18 09:44
 * E-Mail Address：wuzf2012@sina.com
 */
public class BuyCommodityFragment extends BaseMvpFragment {
    private static final String ARGS_DATA = "data.args";
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private int mFragmentTag;
    BuyCommodityAdapter mAdapter;
    public static BuyCommodityFragment newInstance(String data) {
        Bundle args = new Bundle();
        args.putString(ARGS_DATA, data);
        BuyCommodityFragment fragment = new BuyCommodityFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_buy_commodity;
    }

    @Override
    protected void initData(Bundle bundle) {
        mFragmentTag = Integer.parseInt(getArguments().getString(ARGS_DATA));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        List<BuyCommodityBean> data = new ArrayList<>();
        data.add(new BuyCommodityBean());
        data.add(new BuyCommodityBean());
        data.add(new BuyCommodityBean());
        data.add(new BuyCommodityBean());
        data.add(new BuyCommodityBean());
        data.add(new BuyCommodityBean());
        data.add(new BuyCommodityBean());
        data.add(new BuyCommodityBean());
        data.add(new BuyCommodityBean());
        mAdapter =new BuyCommodityAdapter(data);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

}