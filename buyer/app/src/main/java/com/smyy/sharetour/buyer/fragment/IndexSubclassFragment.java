package com.smyy.sharetour.buyer.fragment;

import android.os.Bundle;
import android.widget.Gallery;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by 伍振飞 on 2018/3/23 10:13
 * E-Mail Address：wuzf2012@sina.com
 */
public class IndexSubclassFragment extends BaseMvpFragment {
    @BindView(R.id.gallery)
    Gallery gallery;
    Unbinder unbinder;

    public static IndexSubclassFragment getInstance(String title) {
        IndexSubclassFragment sf = new IndexSubclassFragment();
        return sf;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fm_index_subclass;
    }

    @Override
    protected void initData(Bundle bundle) {
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

}