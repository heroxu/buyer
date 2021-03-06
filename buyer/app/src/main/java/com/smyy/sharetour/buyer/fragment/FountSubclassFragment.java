package com.smyy.sharetour.buyer.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dtr.zxing.activity.CaptureActivity;
import com.smyy.sharetour.buyer.adapter.FountImageAdapter;
import com.smyy.sharetour.buyer.bean.FImage;
import com.smyy.sharetour.buyer.bean.FountBean;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.adapter.FountAdapter;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.util.ActivityLauncher;
import com.smyy.sharetour.buyer.util.LogUtil;
import com.smyy.sharetour.buyer.util.ToastUtils;
import com.yongchun.library.view.ImageSelectorActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 伍振飞 on 2018/3/23 10:13
 * E-Mail Address：wuzf2012@sina.com
 */
public class FountSubclassFragment extends BaseMvpFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private String mTitle;
    FountAdapter mFountAdapter;

    public static FountSubclassFragment getInstance(String title) {
        FountSubclassFragment sf = new FountSubclassFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fm_fount_subclass;
    }

    @Override
    protected void initData(Bundle bundle) {
        LogUtil.e("WZF", mTitle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final List<FountBean> data = new ArrayList<>();
        List<FImage> iData1 = new ArrayList<>();
        iData1.add(new FImage(R.mipmap.img_n_01));
        iData1.add(new FImage(R.mipmap.img_n_02));
        iData1.add(new FImage(R.mipmap.img_n_03));
        List<FImage> iData2 = new ArrayList<>();
        iData2.add(new FImage(R.mipmap.img_n_01));
        iData2.add(new FImage(R.mipmap.img_n_02));
        List<FImage> iData3 = new ArrayList<>();
        iData3.add(new FImage(R.mipmap.img_n_01));
        if ("精选".equals(mTitle)) {
            data.add(new FountBean(3, null));
        }
        data.add(new FountBean(1, "http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png"));
        data.add(new FountBean(2, "http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png", iData1));
        data.add(new FountBean(1, "http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png"));
        data.add(new FountBean(2, "http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png", iData2));
        data.add(new FountBean(1, "http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png", null));
        data.add(new FountBean(2, "http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png", iData3));
        data.add(new FountBean(2, "http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png"));
        mFountAdapter = new FountAdapter(data);
        recyclerView.setAdapter(mFountAdapter);
        //添加Android自带的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mFountAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (data.get(position).getType()) {
                    case 1:
                        ActivityLauncher.viewVideoDetailsActivity(getActivity());
                        break;
                    case 2:
                        ActivityLauncher.viewNoteDetailsActivity(getActivity());
                        break;
                }
            }
        });
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {

        }
    }

}