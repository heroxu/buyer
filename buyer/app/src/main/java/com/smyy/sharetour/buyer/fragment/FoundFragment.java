package com.smyy.sharetour.buyer.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.smyy.sharetour.buyer.FImage;
import com.smyy.sharetour.buyer.FountBean;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.adapter.FountAdapter;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hasee on 2018/3/15.
 */

public class FoundFragment extends BaseMvpFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_fount;
    }

    @Override
    protected void initData(Bundle bundle) {
        changeTitleBarColor();
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

        data.add(new FountBean(3, null));
        data.add(new FountBean(1, "http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png"));
        data.add(new FountBean(2, "http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png", iData1));
        data.add(new FountBean(1, "http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png"));
        data.add(new FountBean(2, "http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png", iData2));
        data.add(new FountBean(1, "http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png", null));
        data.add(new FountBean(2, "http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png", iData3));
        data.add(new FountBean(2, "http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png"));
        FountAdapter mAdapter = new FountAdapter(getActivity(), data);
        recyclerView.setAdapter(new FountAdapter(getActivity(), data));
        //添加Android自带的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    private void changeTitleBarColor() {
        StatusBarAdapter.changeStatusBarColor(getActivity(), getResources().getColor(R.color.white));
    }

}
