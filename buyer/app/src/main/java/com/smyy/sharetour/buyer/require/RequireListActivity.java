package com.smyy.sharetour.buyer.require;

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
import com.smyy.sharetour.buyer.util.ActivityLauncher;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
/**
* @author Liliping
* @org www.smyy.com
* @email lilp@stjf.com
* @package com.smyy.sharetour.buyer.publish
* @fileName RequireListActivity
* @date on 2018/4/20 0020 15:21
* @describe 需求列表
*/
public class RequireListActivity extends BaseMvpActivity {

    @BindView(R.id.require_list)
    RecyclerView requireList;

    private List<RequireBean> requires = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_require_list;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText(R.string.my_require);
        TextView toolbarRightTv = getToolbarRightTv();
        toolbarRightTv.setText(getString(R.string.out_stock_require));
        toolbarRightTv.setTextColor(getResources().getColor(R.color.txt_gray_dark));
        toolbarRightTv.setVisibility(View.VISIBLE);
        toolbarRightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RequireListActivity.this, RequireOutListActivity.class));
            }
        });
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        requireList.setHasFixedSize(true);
        requireList.setLayoutManager(new LinearLayoutManager(RequireListActivity.this));
        requireList.addItemDecoration(new RecyclerViewDivider(RequireListActivity.this,
                LinearLayoutManager.VERTICAL, 30, R.color.window_background));
        demo();
        final RequireItemAdapter adapter = new RequireItemAdapter(RequireListActivity.this, requires);
        adapter.setItemClickListener(new RequireItemAdapter.OnItemRequireClickListener() {
            @Override
            public void OnItemDeleteClick(View v, int position) {
                requires.remove(position);
                adapter.notifyItemRemoved(position);
            }

            @Override
            public void OnItemClick(View v, int position) {
                Intent intent = new Intent(RequireListActivity.this, RequireDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(RequireDetailsActivity.REQUIRE_KEY, requires.get(position));
                bundle.putBoolean(RequireDetailsActivity.REQUIRE_SUCCESS_KEY, false);
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
            bean.setRequire_buy_place("日本");
            bean.setState(i);
            requires.add(bean);
        }
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }


    @OnClick(R.id.add_require)
    public void onViewClicked() {
        ActivityLauncher.viewPublishRequireActivity(RequireListActivity.this);
    }
}
