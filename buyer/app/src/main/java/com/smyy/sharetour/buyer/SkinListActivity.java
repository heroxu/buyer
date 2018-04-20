package com.smyy.sharetour.buyer;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.smyy.sharetour.buyer.adapter.BuyVideoAdapter;
import com.smyy.sharetour.buyer.adapter.SkinListAdapter;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.util.ActivityLauncher;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SkinListActivity extends BaseMvpActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    SkinListAdapter mAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_skin_list;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("主题换肤");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        List<SkinListBean> data = new ArrayList<>();
        data.add(new SkinListBean());
        data.add(new SkinListBean());
        data.add(new SkinListBean());
        data.add(new SkinListBean());
        data.add(new SkinListBean());
        mAdapter =new SkinListAdapter(data);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ActivityLauncher.viewSkinPeelerActivity(SkinListActivity.this);
            }
        });
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }
}
