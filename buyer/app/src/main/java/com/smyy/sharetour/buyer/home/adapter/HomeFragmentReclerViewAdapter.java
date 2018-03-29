package com.smyy.sharetour.buyer.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.smyy.sharetour.buyer.home.model.HomeResultBean;

import java.util.List;
import com.smyy.sharetour.buyer.R;

/**
 * Created by 许夏荣 on 2018/3/29.
 * desc:
 */

public class HomeFragmentReclerViewAdapter extends RecyclerView.Adapter<HomeFragmentReclerViewAdapter.HomeViewHolder>{

    public static final int TITLE  = 0x10;
    public static final int HOR_RV  = 0x20;
    public static final int HOR_GRID_RV  = 0x30;
    public static final int ITEM_VEDIO = 0x40;
    public static final int ITEM_NOTES = 0x50;


    private Context mContext;
    private List<HomeResultBean> mDatas;

    public HomeFragmentReclerViewAdapter(Context context , List<HomeResultBean> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType){
            case TITLE:
                View v = LayoutInflater.from(mContext).inflate(R.layout.item_home_all_title, parent, false);
                return new HomeViewHolder(v);
            case HOR_RV:
                View hor_rv = LayoutInflater.from(mContext).inflate(R.layout.item_home_all_title, parent, false);
                return new HomeViewHolder(hor_rv);
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
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        if(mDatas.isEmpty()){
            return;
        }

        HomeResultBean homeResultBean = mDatas.get(position);
        holder.tv_main_title.setText(homeResultBean.mainTitle);
        holder.tv_sub_title.setText(homeResultBean.subTitle);
        holder.ll_more.setVisibility(homeResultBean.hasMore ? View.VISIBLE : View.GONE);
        holder.tv_change.setVisibility(homeResultBean.hasChange ? View.VISIBLE : View.GONE);
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
}
