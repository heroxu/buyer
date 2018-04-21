package com.smyy.sharetour.buyer.publish;

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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
* @author Liliping
* @org www.smyy.com
* @email lilp@stjf.com
* @package com.smyy.sharetour.buyer.publish
* @fileName RequireOutListActivity
* @date on 2018/4/21 0021 16:12
* @describe 下架需求列表
*/
public class RequireOutListActivity extends BaseMvpActivity {

    @BindView(R.id.require_list)
    RecyclerView requireList;
    @BindView(R.id.add_require)
    TextView addRequire;

    private static final int REQUIRE_OUT_REQUEST_CANCEL = 54;

    private List<RequireBean> requires = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_require_list;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText(R.string.out_stock_require);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        requireList.setHasFixedSize(true);
        requireList.setLayoutManager(new LinearLayoutManager(RequireOutListActivity.this));
        requireList.addItemDecoration(new RecyclerViewDivider(RequireOutListActivity.this,
                LinearLayoutManager.VERTICAL, 30, R.color.window_background));
        addRequire.setVisibility(View.GONE);

        demo();
        final RequireItemAdapter adapter = new RequireItemAdapter(RequireOutListActivity.this, requires);
        adapter.setItemClickListener(new OnRequireOutOnClickListener() {
            @Override
            public void OnItemDeleteClick(View v, int position) {
                requires.remove(position);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void OnItemRetryClick(View v, int position) {

            }

            @Override
            public void OnItemClick(View v, int position) {
                Intent intent = new Intent(RequireOutListActivity.this, RequireDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(RequireDetailsActivity.REQUIRE_KEY, requires.get(position));
                bundle.putBoolean(RequireDetailsActivity.REQUIRE_SUCCESS_KEY, false);
                intent.putExtra("bundle", bundle);
                startActivityForResult(intent, REQUIRE_OUT_REQUEST_CANCEL);
            }
        });
        requireList.setAdapter(adapter);
    }

    private void demo() {
        for (int i = 0; i < 2; i++) {
            RequireBean bean = new RequireBean();
            bean.setRequire_disc("NIKE HUARACHE DRIFT (PSE)…");
            bean.setRequire_time("2018-03-08");
            bean.setRequire_budget("9918.00");
            bean.setState(i+5);
            requires.add(bean);
        }
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }


}
