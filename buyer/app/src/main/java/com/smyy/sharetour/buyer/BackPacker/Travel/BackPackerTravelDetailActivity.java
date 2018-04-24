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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Liliping
 * @org www.smyy.com
 * @email lilp@stjf.com
 * @package com.smyy.sharetour.buyer.BackPacker.Travel
 * @fileName BackPackerTravelDetailActivity
 * @date on 2018/4/24 0024 19:34
 * @describe 行程详情
 */
public class BackPackerTravelDetailActivity extends BaseMvpActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<TravelBean.RouteBean> routes = new ArrayList<>();
    private TravelBean travelBean = new TravelBean();
    private BackPackerTravelDetailItemAdapter adapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_backpacker_travel_detail;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        hideToolBarLayout(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(BackPackerTravelDetailActivity.this));
        demo();
        adapter = new BackPackerTravelDetailItemAdapter(BackPackerTravelDetailActivity.this, travelBean);
        recyclerView.setAdapter(adapter);
    }


    private void demo() {
        String[] countrys = {"中国","澳大利亚","印度尼西亚","美国","中国"};
        String[] times = {"04/04","04/05","04/06","04/08","04/12"};
        int[] imgs = {R.mipmap.img_ill_china,R.mipmap.img_ill_australia,R.mipmap.img_ill_indonesia,R.mipmap.img_ill_america,R.mipmap.img_ill_china};
        for (int i = 0; i < 5; i++) {
            TravelBean.RouteBean routeBean = new TravelBean.RouteBean();
            routeBean.setRouteCountry(countrys[i]);
            routeBean.setRouteTime(times[i]);
            routeBean.setImgId(imgs[i]);
            routes.add(routeBean);
        }
        travelBean.setRouteBeans(routes);
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }



    @OnClick({R.id.delete_frame, R.id.edit_frame})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.delete_frame:
                break;
            case R.id.edit_frame:
                break;
        }
    }
}
