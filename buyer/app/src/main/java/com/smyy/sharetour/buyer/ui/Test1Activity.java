package com.smyy.sharetour.buyer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.smyy.sharetour.buyer.adapter.Test1Adapter;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.bean.Test1Bean;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;

import java.util.ArrayList;

import butterknife.BindView;

public class Test1Activity extends BaseMvpActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test1;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("测试");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Test1Bean> data = new ArrayList<>();
        data.add(new Test1Bean("夏木悠悠","广州市天河区","http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png"));
        data.add(new Test1Bean("夏木悠悠","广州市天河区","http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png"));
        data.add(new Test1Bean("夏木悠悠","广州市天河区","http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png"));
        data.add(new Test1Bean("夏木悠悠","广州市天河区","http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png"));
        data.add(new Test1Bean("夏木悠悠","广州市天河区","http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png"));
        data.add(new Test1Bean("夏木悠悠","广州市天河区","http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png"));
        data.add(new Test1Bean("夏木悠悠","广州市天河区","http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png"));
        recyclerView.setAdapter(new Test1Adapter(data));

    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

}
