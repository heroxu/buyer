package com.smyy.sharetour.buyer.travel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.backpacker.travel.BackPackerTravelDetailItemAdapter;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.bean.TravelBean;
import com.smyy.sharetour.buyer.require.OnRecyclerViewOnClickListener;
import com.smyy.sharetour.buyer.util.ActivityLauncher;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Liliping
 * @org www.smyy.com
 * @email lilp@stjf.com
 * @package com.smyy.sharetour.buyer.BackPacker.Travel
 * @fileName SellerTravelDetailActivity
 * @date on 2018/4/24 0024 19:34
 * @describe 行程详情
 */
public class SellerTravelDetailActivity extends BaseMvpActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.good_list)
    RecyclerView goodList;

    private List<TravelBean.RouteBean> routes = new ArrayList<>();
    private TravelBean travelBean = new TravelBean();
    private BackPackerTravelDetailItemAdapter adapter;
    private SellerGoodItemAdapter goodAdapter;
    private List<String> goods = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_seller_travel_detail;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        hideToolBarLayout(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(SellerTravelDetailActivity.this));

        demo();
        adapter = new BackPackerTravelDetailItemAdapter(SellerTravelDetailActivity.this, travelBean);
        recyclerView.setAdapter(adapter);

        //商品列表
        goodList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        goodList.setLayoutManager(linearLayoutManager);

        goodAdapter = new SellerGoodItemAdapter(SellerTravelDetailActivity.this, goods);
        goodAdapter.setItemClickListener(new OnRecyclerViewOnClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                ActivityLauncher.viewHomeDetail(SellerTravelDetailActivity.this);
            }
        });
        goodList.setAdapter(goodAdapter);
    }


    private void demo() {
        String[] countrys = {"中国", "澳大利亚", "印度尼西亚", "美国", "中国"};
        String[] times = {"04/04", "04/05", "04/06", "04/08", "04/12"};
        int[] imgs = {R.mipmap.img_ill_china_fly, R.mipmap.img_ill_australia_fly, R.mipmap.img_ill_indonesia_fly, R.mipmap.img_ill_america_fly, R.mipmap.img_ill_china_fly};
        for (int i = 0; i < 5; i++) {
            TravelBean.RouteBean routeBean = new TravelBean.RouteBean();
            routeBean.setRouteCountry(countrys[i]);
            routeBean.setRouteTime(times[i]);
            routeBean.setImgId(imgs[i]);
            routes.add(routeBean);

            goods.add(""+i);
        }
        travelBean.setRouteBeans(routes);
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }


    @OnClick({R.id.back_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
        }
    }
}
