package com.smyy.sharetour.buyer.ui.buyCommodity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.adapter.FountAdapter;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.bean.FImage;
import com.smyy.sharetour.buyer.bean.FountBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 伍振飞 on 2018/4/18 09:44
 * E-Mail Address：wuzf2012@sina.com
 */
public class BuyDynamicFragment extends BaseMvpFragment {
    private static final String ARGS_DATA = "data.args";
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private int mFragmentTag;
    FountAdapter mAdapter;
    public static BuyDynamicFragment newInstance(String data) {
        Bundle args = new Bundle();
        args.putString(ARGS_DATA, data);
        BuyDynamicFragment fragment = new BuyDynamicFragment();
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
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<FountBean> data = new ArrayList<>();
        List<FImage> iData1 = new ArrayList<>();
        iData1.add(new FImage(R.mipmap.img_n_01));
        iData1.add(new FImage(R.mipmap.img_n_02));
        iData1.add(new FImage(R.mipmap.img_n_03));
        List<FImage> iData2 = new ArrayList<>();
        iData2.add(new FImage(R.mipmap.img_n_01));
        iData2.add(new FImage(R.mipmap.img_n_02));
        List<FImage> iData3 = new ArrayList<>();
        iData3.add(new FImage(R.mipmap.img_n_01));
        data.add(new FountBean(1, "http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png"));
        data.add(new FountBean(2, "http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png", iData1));
        data.add(new FountBean(1, "http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png"));
        data.add(new FountBean(2, "http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png", iData2));
        data.add(new FountBean(1, "http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png", null));
        data.add(new FountBean(2, "http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png", iData3));
        data.add(new FountBean(2, "http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png"));
        mAdapter = new FountAdapter(data);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }
}