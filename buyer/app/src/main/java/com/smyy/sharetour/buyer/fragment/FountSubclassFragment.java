package com.smyy.sharetour.buyer.fragment;

import android.os.Bundle;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;

/**
 * Created by 伍振飞 on 2018/3/23 10:13
 * E-Mail Address：wuzf2012@sina.com
 */
public class FountSubclassFragment extends BaseMvpFragment {

    public static FountSubclassFragment getInstance(String title) {
        FountSubclassFragment sf = new FountSubclassFragment();
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