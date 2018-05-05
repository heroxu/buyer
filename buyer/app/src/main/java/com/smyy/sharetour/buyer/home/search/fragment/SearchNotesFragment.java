package com.smyy.sharetour.buyer.home.search.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.home.search.SearchBuyNotesBean;
import com.smyy.sharetour.buyer.home.search.adapter.SearchNotesAdapter;
import com.smyy.sharetour.buyer.util.ActivityLauncher;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 伍振飞 on 2018/4/18 09:44
 * E-Mail Address：wuzf2012@sina.com
 */
public class SearchNotesFragment extends BaseMvpFragment {
    private static final String ARGS_DATA = "data.args";
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    SearchNotesAdapter mAdapter;
    public static SearchNotesFragment getInstance(String data) {
        Bundle args = new Bundle();
        args.putString(ARGS_DATA, data);
        SearchNotesFragment fragment = new SearchNotesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dynamic_rv;
    }

    @Override
    protected void initData(Bundle bundle) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<SearchBuyNotesBean> data = new ArrayList<>();
        data.add(new SearchBuyNotesBean());
        data.add(new SearchBuyNotesBean());
        data.add(new SearchBuyNotesBean());
        data.add(new SearchBuyNotesBean());
        data.add(new SearchBuyNotesBean());
        data.add(new SearchBuyNotesBean());
        mAdapter = new SearchNotesAdapter(data);
        recyclerView.setAdapter(mAdapter);
        //添加Android自带的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ActivityLauncher.viewNoteDetailsActivity(getActivity());
            }
        });
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }
}