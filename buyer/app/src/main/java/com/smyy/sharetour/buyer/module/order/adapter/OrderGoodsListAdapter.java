package com.smyy.sharetour.buyer.module.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.module.order.bean.OrderGoodsInfo;
import com.smyy.sharetour.buyer.util.StringUtil;

import java.util.List;


public class OrderGoodsListAdapter extends RecyclerView.Adapter<OrderGoodsListAdapter.ViewHolder> {

    private Context mContext;
    private List<OrderGoodsInfo> mDatas;

    public OrderGoodsListAdapter(Context context) {
        this.mContext = context;
    }

    public OrderGoodsListAdapter(Context context, List<OrderGoodsInfo> goodsList) {
        this.mContext = context;
        this.mDatas = goodsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_order_goods_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final OrderGoodsInfo data = mDatas.get(position);
        if (data != null) {
            holder.ivPic.setImageResource(R.mipmap.fake_goods_pic);
//            Glide.with(mContext).load(data.getGoodsPic()).into(holder.ivPic);//TODO RTRT
            holder.tvName.setText(data.getGoodsName());

            String goodsSpec = data.getGoodsSpec();
            if (StringUtil.isEmpty(goodsSpec)) {
                holder.tvSpec.setVisibility(View.GONE);
            } else {
                holder.tvSpec.setVisibility(View.VISIBLE);
                holder.tvSpec.setText("规格：" + goodsSpec);
            }

            String receiveDeadline = data.getReceiveDeadline();
            if (StringUtil.isEmpty(receiveDeadline)) {
                holder.tvDeadline.setVisibility(View.GONE);
            } else {
                holder.tvDeadline.setVisibility(View.VISIBLE);
                holder.tvDeadline.setText(receiveDeadline + " 前收到");
            }

            holder.tvPrice.setText(StringUtil.connect("价格：", data.getGoodsPrice()));
            holder.tvCount.setText("× ️" + data.getGoodsCount());


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(holder.itemView, position, data);
                    }
                }
            });
        }
    }

    public List<OrderGoodsInfo> getList() {
        return mDatas;
    }

    // 事件回调监听
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position, OrderGoodsInfo data);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public void setData(List<OrderGoodsInfo> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    public void remove(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount() - position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPic;
        private TextView tvName;
        private TextView tvSpec;
        private TextView tvDeadline;
        private TextView tvPrice;
        private TextView tvCount;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPic = (ImageView) itemView.findViewById(R.id.iv_order_goods_pic);
            tvName = (TextView) itemView.findViewById(R.id.iv_order_goods_name);
            tvSpec = (TextView) itemView.findViewById(R.id.iv_order_goods_spec);
            tvDeadline = (TextView) itemView.findViewById(R.id.iv_order_receive_deadline);
            tvPrice = (TextView) itemView.findViewById(R.id.iv_order_goods_price);
            tvCount = (TextView) itemView.findViewById(R.id.tv_order_goods_count);
        }
    }
}
