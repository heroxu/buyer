package com.smyy.sharetour.buyer.home.detail.dialog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.db.HomeSearch;
import com.smyy.sharetour.buyer.db.operate.HomeSearchDaoOpe;
import com.smyy.sharetour.buyer.util.ActivityLauncher;

import java.util.List;

/**
 * create by xuxiarong on 2018/4/26
 */
public class ProductConfirmOrderAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<String> mDatas;
    private int selected = -1;
    public ProductConfirmOrderAdapter(Context context, List<String> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderConfirmHolder(LayoutInflater.from(mContext).inflate(R.layout.item_product_detail_confirm_order, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        OrderConfirmHolder viewHolder = (OrderConfirmHolder) holder;
        viewHolder.tv_item_confirm_order_color.setText(mDatas.get(position));
        if(position == selected){
            viewHolder.tv_item_confirm_order_color.setBackground(mContext.getDrawable(R.drawable.bg_confirm_order_select));
        }else {
            viewHolder.tv_item_confirm_order_color.setBackground(mContext.getDrawable(R.drawable.bg_confirm_order_unselect));
        }
        viewHolder.tv_item_confirm_order_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.isEmpty()?0:mDatas.size();
    }

    public void setData(List<String> datas){
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    class OrderConfirmHolder extends RecyclerView.ViewHolder{
        private TextView tv_item_confirm_order_color;
        public OrderConfirmHolder(View itemView) {
            super(itemView);
            tv_item_confirm_order_color = (TextView) itemView.findViewById(R.id.tv_item_confirm_order_color);
        }
    }
}
