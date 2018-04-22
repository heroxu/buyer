package com.smyy.sharetour.buyer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SmallBackpackActivity extends BaseMvpActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.btn_settlement)
    Button btnSettlement;
    private boolean isCheckOll;
    private SmallBackpackAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_small_backpack;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("小背包");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<SmallBackpackBean> data = new ArrayList<>();
        data.add(new SmallBackpackBean(SmallBackpackBean.GOODS_TYPE));
        data.add(new SmallBackpackBean(SmallBackpackBean.GOODS_TYPE));
        data.add(new SmallBackpackBean(SmallBackpackBean.GOODS_FAILURE_TYPE));
        mAdapter = new SmallBackpackAdapter(data);
        recyclerView.setAdapter(mAdapter);
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_custom_divider_10));
        recyclerView.addItemDecoration(divider);
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }


    @OnClick({R.id.iv_check_all, R.id.btn_settlement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_check_all:
                break;
            case R.id.btn_settlement:
                break;
        }
    }
}
