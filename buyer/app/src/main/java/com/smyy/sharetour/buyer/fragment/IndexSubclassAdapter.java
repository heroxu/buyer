package com.smyy.sharetour.buyer.fragment;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smyy.sharetour.buyer.bean.Test1Bean;

import java.util.List;

/**
 * Created by 伍振飞 on 2018/3/23 15:22
 * E-Mail Address：wuzf2012@sina.com
 */
public class IndexSubclassAdapter extends BaseMultiItemQuickAdapter<Test1Bean> {
    public IndexSubclassAdapter(List<Test1Bean> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Test1Bean test1Bean) {

    }
}