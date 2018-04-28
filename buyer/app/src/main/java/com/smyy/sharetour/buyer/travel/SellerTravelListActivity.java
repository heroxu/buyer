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
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.bean.TravelBean;
import com.smyy.sharetour.buyer.require.OnRecyclerViewOnClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
* @author Liliping
* @org www.smyy.com
* @email lilp@stjf.com
* @package com.smyy.sharetour.buyer.travel
* @fileName SellerTravelListActivity
* @date on 2018/4/28 0028 9:59
* @describe 行程列表
*/
public class SellerTravelListActivity extends BaseMvpActivity {

    @BindView(R.id.travel_list)
    RecyclerView travelList;

    private List<TravelBean> travels = new ArrayList<>();
    private SellerTravelItemAdapter adapter;
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected int getLayoutId() {
        return R.layout.activity_seller_travel_list;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("买手行程");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        travelList.setHasFixedSize(true);
        travelList.setLayoutManager(new LinearLayoutManager(SellerTravelListActivity.this));
        demo();
        adapter = new SellerTravelItemAdapter(SellerTravelListActivity.this, travels);
        adapter.setItemClickListener(new OnRecyclerViewOnClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                startActivity(new Intent(SellerTravelListActivity.this, SellerTravelDetailActivity.class));
            }
        });
        travelList.setAdapter(adapter);

    }

    String[] starts = {"尼泊尔", "印度尼西亚", "香港", "香港"};
    String[] ends = {"澳大利亚", "列支敦士登", "马来西亚", "马来西亚"};
    String[] middles = {"柬埔寨", "列支敦士登", "俄罗斯", "台湾"};
    int[] imgs = {R.mipmap.img_ill_china, R.mipmap.img_ill_australia, R.mipmap.img_ill_indonesia, R.mipmap.img_ill_america};

    private void demo() {
        for (int i = 0; i < 4; i++) {
            TravelBean bean = new TravelBean();
            bean.setCreateDate("02月12日创建");
            if(i==1){
                bean.setCurrentPlace(0);
            } else {
                bean.setCurrentPlace(1);
            }

            List<TravelBean.RouteBean> routes = new ArrayList<>();

            TravelBean.RouteBean routeBean = new TravelBean.RouteBean();
            routeBean.setRouteTime("03月07日");
            routeBean.setRouteCountry(starts[i]);
            routeBean.setImgId(imgs[i]);
            routes.add(routeBean);

            TravelBean.RouteBean routeBean1 = new TravelBean.RouteBean();
            routeBean1.setRouteTime("03月09日");
            routeBean1.setRouteCountry(middles[i]);
            routeBean1.setImgId(imgs[i]);
            routes.add(routeBean1);

            TravelBean.RouteBean routeBean2 = new TravelBean.RouteBean();
            routeBean2.setRouteTime("03月12日");
            routeBean2.setRouteCountry(ends[i]);
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
