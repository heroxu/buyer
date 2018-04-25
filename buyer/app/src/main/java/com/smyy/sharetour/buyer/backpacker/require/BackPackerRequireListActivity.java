package com.smyy.sharetour.buyer.BackPacker.Require;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.bean.RequireBean;
import com.smyy.sharetour.buyer.require.OnRecyclerViewOnClickListener;
import com.smyy.sharetour.buyer.require.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
/**
* @author Liliping
* @org www.smyy.com
* @email lilp@stjf.com
* @package com.smyy.sharetour.buyer.BackPacker.Require
* @fileName BackPackerRequireListActivity
* @date on 2018/4/23 0023 11:07
* @describe 背包客需求列表
*/
public class BackPackerRequireListActivity extends BaseMvpActivity {

    @BindView(R.id.require_list)
    RecyclerView requireList;

    private List<RequireBean> requires = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_backpacker_require_list;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText(R.string.require);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        requireList.setHasFixedSize(true);
        requireList.setLayoutManager(new LinearLayoutManager(BackPackerRequireListActivity.this));
        requireList.addItemDecoration(new RecyclerViewDivider(BackPackerRequireListActivity.this,
                LinearLayoutManager.VERTICAL, 30, R.color.window_background));
        demo();
        BackPackerRequireItemAdapter adapter = new BackPackerRequireItemAdapter(BackPackerRequireListActivity.this, requires);
        adapter.setItemClickListener(new OnRecyclerViewOnClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                Intent intent = new Intent(BackPackerRequireListActivity.this, BackPackerRequireDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(BackPackerRequireDetailsActivity.REQUIRE_KEY, requires.get(position));
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
        requireList.setAdapter(adapter);
    }

    private void demo() {
        for (int i = 0; i < 5; i++) {
            RequireBean bean = new RequireBean();
            bean.setRequire_disc("NIKE HUARACHE DRIFT (PSE)…");
            bean.setRequire_time("2018-03-08");
            bean.setRequire_budget("9918.00");
            bean.setState(i+1);
            requires.add(bean);
        }
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

}
