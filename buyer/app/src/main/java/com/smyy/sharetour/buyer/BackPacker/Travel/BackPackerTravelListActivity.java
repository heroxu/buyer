package com.smyy.sharetour.buyer.BackPacker.Travel;

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
import com.smyy.sharetour.buyer.bean.TravelBean;
import com.smyy.sharetour.buyer.require.OnRecyclerViewOnClickListener;
import com.smyy.sharetour.buyer.require.RecyclerViewDivider;
import com.yongchun.library.utils.ScreenUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
* @author Liliping
* @org www.smyy.com
* @email lilp@stjf.com
* @package com.smyy.sharetour.buyer.BackPacker.Travel
* @fileName BackPackerTravelListActivity
* @date on 2018/4/24 0024 9:48
* @describe 行程列表
*/
public class BackPackerTravelListActivity extends BaseMvpActivity {

    @BindView(R.id.travel_list)
    RecyclerView travelList;

    private List<TravelBean> travels = new ArrayList<>();
    private BackPackerTravelItemAdapter adapter;
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected int getLayoutId() {
        return R.layout.activity_backpacker_travel_list;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("行程");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        travelList.setHasFixedSize(true);
        travelList.setLayoutManager(new LinearLayoutManager(BackPackerTravelListActivity.this));
        travelList.addItemDecoration(new RecyclerViewDivider(BackPackerTravelListActivity.this,
                LinearLayoutManager.VERTICAL, ScreenUtils.dip2px(getApplicationContext(),30), R.color.window_background));
        demo();
        adapter = new BackPackerTravelItemAdapter(BackPackerTravelListActivity.this, travels);
        adapter.setItemClickListener(new OnRecyclerViewOnClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                startActivity(new Intent(BackPackerTravelListActivity.this, BackPackerTravelDetailActivity.class));
            }
        });
        travelList.setAdapter(adapter);

    }


    private void demo() {
        for (int i = 0; i < 3; i++) {
            TravelBean bean = new TravelBean();
            bean.setCreateDate("02月12日创建");

            List<TravelBean.RouteBean> routes = new ArrayList<>();

            TravelBean.RouteBean routeBean = new TravelBean.RouteBean();
            routeBean.setRouteTime("03月07日");
            routeBean.setRouteCountry("新加坡");
            routes.add(routeBean);

            if(i==2) {
                bean.setRouteBeans(routes);
                travels.add(bean);
                continue;
            }

            TravelBean.RouteBean routeBean1 = new TravelBean.RouteBean();
            routeBean1.setRouteTime("03月09日");
            routeBean1.setRouteCountry("马来西亚");
            routes.add(routeBean1);

            if(i==1) {
                bean.setRouteBeans(routes);
                travels.add(bean);
                continue;
            }
            TravelBean.RouteBean routeBean2 = new TravelBean.RouteBean();
            routeBean2.setRouteTime("03月12日");
            routeBean2.setRouteCountry("韩国");
            routes.add(routeBean2);
            bean.setRouteBeans(routes);
            travels.add(bean);
        }
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }


}
