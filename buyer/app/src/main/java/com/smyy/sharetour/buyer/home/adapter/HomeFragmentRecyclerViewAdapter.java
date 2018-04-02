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


import com.smyy.sharetour.buyer.home.model.HomeNewSell;
import com.smyy.sharetour.buyer.home.model.HomeNewSellItem;
import com.smyy.sharetour.buyer.home.model.HomeRouteItem;
import com.smyy.sharetour.buyer.home.model.HomeRoute;
import com.smyy.sharetour.buyer.home.model.HomeRecyclerBaseBean;
import com.smyy.sharetour.buyer.home.model.HomeTitleBean;

import java.util.List;
import com.smyy.sharetour.buyer.R;

/**
 * Created by 许夏荣 on 2018/3/29.
 * desc:
 */

public class HomeFragmentRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static final int ITEM_TITLE = 0x10;
    public static final int ITEM_CHILD_ROUTE = 0x20;
    public static final int ITEM_CHILD_NEW_SELL = 0x30;
    public static final int ITEM_VEDIO = 0x40;
    public static final int ITEM_NOTES = 0x50;


    private Context mContext;
    private List<HomeRecyclerBaseBean> mDatas;

    public HomeFragmentRecyclerViewAdapter(Context context , List<HomeRecyclerBaseBean> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType){
            case ITEM_TITLE:
                View v = LayoutInflater.from(mContext).inflate(R.layout.item_home_child_title, parent, false);
                return new HomeChildTitleHolder(v);
            case ITEM_CHILD_ROUTE:
                View buyer_route = LayoutInflater.from(mContext).inflate(R.layout.item_home_child_route_rv, parent, false);
                return new HomeChildRouteHolder(buyer_route);
            case ITEM_CHILD_NEW_SELL:
                View grid_rv = LayoutInflater.from(mContext).inflate(R.layout.item_home_child_new_sell_rv, parent, false);
                return new HomeNewSellHolder(grid_rv);
            case ITEM_NOTES:
                View notes = LayoutInflater.from(mContext).inflate(R.layout.item_home_child_title, parent, false);
                return new HomeChildTitleHolder(notes);
            case ITEM_VEDIO:
                View vedio = LayoutInflater.from(mContext).inflate(R.layout.item_home_child_title, parent, false);
                return new HomeChildTitleHolder(vedio);
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
            HomeChildTitleHolder homeChildTitleHolder = (HomeChildTitleHolder) holder;
            HomeTitleBean homeTitleBean = (HomeTitleBean) mDatas.get(position);
            homeChildTitleHolder.tv_main_title.setText(homeTitleBean.mainTitle);
            homeChildTitleHolder.tv_sub_title.setText(homeTitleBean.subTitle);
            homeChildTitleHolder.ll_more.setVisibility(homeTitleBean.hasMore ? View.VISIBLE : View.GONE);
            homeChildTitleHolder.tv_change.setVisibility(homeTitleBean.hasChange ? View.VISIBLE : View.GONE);
        }else if(ITEM_CHILD_ROUTE == mDatas.get(position).viewType){
            HomeChildRouteHolder homeChildRouteHolder = (HomeChildRouteHolder) holder;
            HomeRoute homeRoute = (HomeRoute) mDatas.get(position);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            homeChildRouteHolder.rv_child_route.setLayoutManager(linearLayoutManager);
            homeChildRouteHolder.rv_child_route.setAdapter(new HomeChildRouteDetailAdapter(homeRoute.routes));
        }else if(ITEM_CHILD_NEW_SELL == mDatas.get(position).viewType){
            HomeNewSellHolder homeNewSellHolder = (HomeNewSellHolder) holder;
            HomeNewSell homeNewSell = (HomeNewSell) mDatas.get(position);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            homeNewSellHolder.rv_child_new_sell.setLayoutManager(linearLayoutManager);
            homeNewSellHolder.rv_child_new_sell.setAdapter(new HomeNewSellAdapter(homeNewSell.newSellItems));

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

    public static class HomeChildTitleHolder extends RecyclerView.ViewHolder{

        TextView tv_main_title;
        TextView tv_sub_title;
        LinearLayout ll_more;
        TextView tv_change;
        public HomeChildTitleHolder(View itemView) {
            super(itemView);
            tv_main_title = (TextView)itemView.findViewById(R.id.tv_main_title);
            tv_sub_title = (TextView)itemView.findViewById(R.id.tv_sub_title);
            ll_more = (LinearLayout)itemView.findViewById(R.id.ll_more);
            tv_change = (TextView)itemView.findViewById(R.id.tv_change);

        }
    }

    public static class HomeChildRouteHolder extends RecyclerView.ViewHolder{
        private RecyclerView rv_child_route;
        public HomeChildRouteHolder(View itemView) {
            super(itemView);
            rv_child_route = (RecyclerView) itemView.findViewById(R.id.rv_child_route);
        }
    }

    public class HomeChildRouteDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private List<HomeRouteItem> mRouteDatas;
        private int [] drawableArr = {R.mipmap.random_icon_one,R.mipmap.random_icon_two,R.mipmap.random_icon_three,R.mipmap.random_icon_four};
        public HomeChildRouteDetailAdapter(List<HomeRouteItem> datas) {
            this.mRouteDatas = datas;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new HomeRouteItemHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_child_route, parent, false));

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            HomeRouteItemHolder viewHolder = (HomeRouteItemHolder) holder;
            viewHolder.tv_buyer_go.setText(mRouteDatas.get(position).goTime);
            viewHolder.tv_buyer_back.setText(mRouteDatas.get(position).backTime);
            viewHolder.iv_buyer_route.setImageResource(drawableArr[position]);
        }

        @Override
        public int getItemCount() {
            return mRouteDatas.isEmpty()?0: mRouteDatas.size();
        }

        public  class HomeRouteItemHolder extends RecyclerView.ViewHolder{

            ImageView iv_buyer_route;
            TextView tv_buyer_go;
            TextView tv_buyer_back;
            TextView tv_route_classify_one;
            TextView tv_route_classify_two;
            TextView tv_route_classify_three;

            public HomeRouteItemHolder(View itemView) {
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

    public static class HomeNewSellHolder extends RecyclerView.ViewHolder{
        private RecyclerView rv_child_new_sell;
        public HomeNewSellHolder(View itemView) {
            super(itemView);
            rv_child_new_sell = (RecyclerView) itemView.findViewById(R.id.rv_child_new_sell);
        }
    }

    public class HomeNewSellAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private List<HomeNewSellItem> mNewSellDatas;
        private int [] drawableArr = {R.mipmap.random_icon_one,R.mipmap.random_icon_two,R.mipmap.random_icon_three,R.mipmap.random_icon_four};
        public HomeNewSellAdapter(List<HomeNewSellItem> datas) {
            this.mNewSellDatas = datas;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new HomeNewSellItemHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_child_new_sell, parent, false));

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            HomeNewSellItemHolder viewHolder = (HomeNewSellItemHolder) holder;
            HomeNewSellItem homeNewSellItem = mNewSellDatas.get(position);

            viewHolder.tv_new_sell_time.setText(homeNewSellItem.sellTime);
            viewHolder.tv_new_sell_reserve_number.setText(homeNewSellItem.reserveNumber);
            viewHolder.iv_new_sell_product_pic.setImageResource(R.mipmap.new_sell);
            viewHolder.tv_new_sell_product_name.setText(homeNewSellItem.productName);
            viewHolder.tv_new_sell_product_price.setText(homeNewSellItem.productPrice);
            viewHolder.tv_new_sell_product_address.setText(homeNewSellItem.productAddress);

        }

        @Override
        public int getItemCount() {
            return mNewSellDatas.isEmpty()?0: mNewSellDatas.size();
        }

        public  class HomeNewSellItemHolder extends RecyclerView.ViewHolder{

            TextView tv_new_sell_time;
            TextView tv_new_sell_reserve_number;
            ImageView iv_new_sell_product_pic;
            TextView tv_new_sell_product_name;
            TextView tv_new_sell_product_price;
            TextView tv_new_sell_product_address;


            public HomeNewSellItemHolder(View itemView) {
                super(itemView);
                tv_new_sell_time = (TextView) itemView.findViewById(R.id.tv_new_sell_time);
                tv_new_sell_reserve_number = (TextView) itemView.findViewById(R.id.tv_new_sell_reserve_number);

                iv_new_sell_product_pic = (ImageView) itemView.findViewById(R.id.iv_new_sell_product_pic);
                tv_new_sell_product_name = (TextView)itemView.findViewById(R.id.tv_new_sell_product_name);
                tv_new_sell_product_price = (TextView)itemView.findViewById(R.id.tv_new_sell_product_price);
                tv_new_sell_product_address = (TextView)itemView.findViewById(R.id.tv_new_sell_product_address);


            }
        }

    }


}
