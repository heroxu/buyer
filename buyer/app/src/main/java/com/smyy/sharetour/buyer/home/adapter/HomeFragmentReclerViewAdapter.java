package com.smyy.sharetour.buyer.home.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.smyy.sharetour.buyer.home.model.HomeBuyerItemBean;
import com.smyy.sharetour.buyer.home.model.HomeBuyerRouteBean;
import com.smyy.sharetour.buyer.home.model.HomeRecyclerBaseBean;
import com.smyy.sharetour.buyer.home.model.HomeTitleBean;

import java.util.List;
import com.smyy.sharetour.buyer.R;

/**
 * Created by 许夏荣 on 2018/3/29.
 * desc:
 */

public class HomeFragmentReclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static final int ITEM_TITLE = 0x10;
    public static final int CHILD_BUYER_ROUTE = 0x20;
    public static final int HOR_GRID_RV  = 0x30;
    public static final int ITEM_VEDIO = 0x40;
    public static final int ITEM_NOTES = 0x50;


    private Context mContext;
    private List<HomeRecyclerBaseBean> mDatas;

    public HomeFragmentReclerViewAdapter(Context context , List<HomeRecyclerBaseBean> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType){
            case ITEM_TITLE:
                View v = LayoutInflater.from(mContext).inflate(R.layout.item_home_all_title, parent, false);
                return new HomeViewHolder(v);
            case CHILD_BUYER_ROUTE:
                View buyer_route = LayoutInflater.from(mContext).inflate(R.layout.recycler_child_route_layout, parent, false);
                return new ChildRouteViewHold(buyer_route);
            case HOR_GRID_RV:
                View grid_rv = LayoutInflater.from(mContext).inflate(R.layout.item_home_all_title, parent, false);
                return new HomeViewHolder(grid_rv);
            case ITEM_NOTES:
                View notes = LayoutInflater.from(mContext).inflate(R.layout.item_home_all_title, parent, false);
                return new HomeViewHolder(notes);
            case ITEM_VEDIO:
                View vedio = LayoutInflater.from(mContext).inflate(R.layout.item_home_all_title, parent, false);
                return new HomeViewHolder(vedio);
            default:
              return  null;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(mDatas.isEmpty()){
            return;
        }
        if(ITEM_TITLE == mDatas.get(position).viewType){
            HomeViewHolder homeViewHolder = (HomeViewHolder) holder;
            HomeTitleBean homeTitleBean = (HomeTitleBean) mDatas.get(position);
            homeViewHolder.tv_main_title.setText(homeTitleBean.mainTitle);
            homeViewHolder.tv_sub_title.setText(homeTitleBean.subTitle);
            homeViewHolder.ll_more.setVisibility(homeTitleBean.hasMore ? View.VISIBLE : View.GONE);
            homeViewHolder.tv_change.setVisibility(homeTitleBean.hasChange ? View.VISIBLE : View.GONE);
        }else if(CHILD_BUYER_ROUTE == mDatas.get(position).viewType){
            ChildRouteViewHold childRouteViewHold = (ChildRouteViewHold) holder;
            HomeBuyerRouteBean homeBuyerRouteBean = (HomeBuyerRouteBean) mDatas.get(position);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            childRouteViewHold.rv_child_route.setLayoutManager(linearLayoutManager);
            childRouteViewHold.rv_child_route.setAdapter(new HomeBuyerRouteAdapter(homeBuyerRouteBean.routes));
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.isEmpty()?0:mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {

        if(mDatas.isEmpty()){
            return super.getItemViewType(position);
        }

        return mDatas.get(position).viewType;
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder{

        TextView tv_main_title;
        TextView tv_sub_title;
        LinearLayout ll_more;
        TextView tv_change;
        public HomeViewHolder(View itemView) {
            super(itemView);
            tv_main_title = (TextView)itemView.findViewById(R.id.tv_main_title);
            tv_sub_title = (TextView)itemView.findViewById(R.id.tv_sub_title);
            ll_more = (LinearLayout)itemView.findViewById(R.id.ll_more);
            tv_change = (TextView)itemView.findViewById(R.id.tv_change);

        }
    }

    public static class ChildRouteViewHold extends RecyclerView.ViewHolder{
        private RecyclerView rv_child_route;
        public ChildRouteViewHold(View itemView) {
            super(itemView);
            rv_child_route = (RecyclerView) itemView.findViewById(R.id.rv_child_route);
        }
    }



    public class HomeBuyerRouteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private List<HomeBuyerItemBean> mRouteDatas;
        private int [] drawableArr = {R.mipmap.random_icon_one,R.mipmap.random_icon_two,R.mipmap.random_icon_three,R.mipmap.random_icon_four};
        public HomeBuyerRouteAdapter( List<HomeBuyerItemBean> datas) {
            this.mRouteDatas = datas;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new  BuyerRouteViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_child_buyer_route, parent, false));

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            BuyerRouteViewHolder viewHolder = (BuyerRouteViewHolder) holder;
            viewHolder.tv_buyer_go.setText(mRouteDatas.get(position).goTime);
            viewHolder.tv_buyer_back.setText(mRouteDatas.get(position).backTime);
            viewHolder.iv_buyer_route.setImageResource(drawableArr[position]);
        }

        @Override
        public int getItemCount() {
            return mRouteDatas.isEmpty()?0: mRouteDatas.size();
        }

        public  class BuyerRouteViewHolder extends RecyclerView.ViewHolder{

            ImageView iv_buyer_route;
            TextView tv_buyer_go;
            TextView tv_buyer_back;
            TextView tv_route_classify_one;
            TextView tv_route_classify_two;
            TextView tv_route_classify_three;

            public BuyerRouteViewHolder(View itemView) {
                super(itemView);
                iv_buyer_route = (ImageView) itemView.findViewById(R.id.iv_buyer_route);
                tv_buyer_go = (TextView)itemView.findViewById(R.id.tv_buyer_go);
                tv_buyer_back = (TextView)itemView.findViewById(R.id.tv_buyer_back);
                tv_route_classify_one = (TextView)itemView.findViewById(R.id.tv_route_classify_one);
                tv_route_classify_two = (TextView)itemView.findViewById(R.id.tv_route_classify_two);
                tv_route_classify_three = (TextView)itemView.findViewById(R.id.tv_route_classify_three);


            }
        }

    }
}
