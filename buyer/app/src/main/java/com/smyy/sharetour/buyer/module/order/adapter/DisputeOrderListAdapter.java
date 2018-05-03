package com.smyy.sharetour.buyer.module.order.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.module.order.OrderHelper;
import com.smyy.sharetour.buyer.module.order.bean.DisputeOrderBean;
import com.smyy.sharetour.buyer.module.order.bean.OrderGoodsInfo;
import com.smyy.sharetour.buyer.ui.buyCommodity.BuyHomePageActivity;
import com.smyy.sharetour.buyer.util.StringUtil;
import com.smyy.sharetour.uiframelib.BaseActivity;

import java.util.List;


public class DisputeOrderListAdapter extends RecyclerView.Adapter<DisputeOrderListAdapter.ViewHolder> {

    private BaseActivity mActivity;
    private int mUserType;
    private List<DisputeOrderBean> mDatas;

    public DisputeOrderListAdapter(BaseActivity context, int userType) {
        this.mActivity = context;
        this.mUserType = userType;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mActivity).inflate(R.layout.item_order_dispute_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final DisputeOrderBean data = mDatas.get(position);
        if (data != null) {
            if (mUserType == Consts.USER_TYPE_BUYER) {
                holder.ivOppositeAvatar.setImageResource(R.mipmap.fake_seller_avatar);
//            Glide.with(mContext).load(data.getSellerAvatar()).into(ivOppositeAvatar);//TODO RTRT
                holder.tvOppositeName.setText(data.getSellerName());
                holder.tvOppositeName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mActivity.startActivity(BuyHomePageActivity.class);
                    }
                });

            } else {
                holder.ivOppositeAvatar.setImageResource(R.mipmap.fake_seller_avatar);
//            Glide.with(mContext).load(data.getBuyerAvatar()).into(ivOppositeAvatar);//TODO RTRT
                holder.tvOppositeName.setText(data.getBuyerName());
                holder.tvOppositeName.setCompoundDrawables(null, null, null, null);
            }

            holder.ivPic.setImageResource(R.mipmap.fake_goods_pic);
//            Glide.with(mActivity).load(data.getGoodsPic()).into(holder.ivPic);//TODO RTRT
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

            if (data.getGoodsType() == OrderHelper.GOODS_TYPE_DEMAND) {
                holder.tvCount.setVisibility(View.GONE);
            } else {
                holder.tvCount.setVisibility(View.VISIBLE);
                holder.tvCount.setText("× ️" + data.getGoodsCount());
            }

            int orderStatus = data.getOrderStatus();
            switch (orderStatus) {
                case OrderHelper.STATUS_BUYER_DUR_DISPUTE:
                case OrderHelper.STATUS_SELLER_DUR_DISPUTE:
                    holder.tvStatus.setText("售后处理中");
                    break;

                case OrderHelper.STATUS_BUYER_DISPUTE_SUCCESS:
                case OrderHelper.STATUS_SELLER_DISPUTE_SUCCESS:
                    holder.tvStatus.setText("退款成功");
                    break;

                default:
                    holder.tvStatus.setText("");
                    break;
            }

            holder.tvBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OrderHelper.switchOperate(mActivity, OrderHelper.OPERATE_DISPUTE_DETAIL);
                }
            });


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

    public List<DisputeOrderBean> getList() {
        return mDatas;
    }

    // 事件回调监听
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position, DisputeOrderBean data);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public void setData(List<DisputeOrderBean> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    public void remove(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount() - position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivOppositeAvatar;
        private TextView tvOppositeName;
        private TextView tvStatus;
        private ImageView ivPic;
        private TextView tvName;
        private TextView tvSpec;
        private TextView tvDeadline;
        private TextView tvPrice;
        private TextView tvCount;
        private TextView tvBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            ivOppositeAvatar = (ImageView) itemView.findViewById(R.id.iv_order_opposite_avatar);
            tvOppositeName = (TextView) itemView.findViewById(R.id.tv_order_opposite_name);
            tvStatus = (TextView) itemView.findViewById(R.id.tv_order_status);
            ivPic = (ImageView) itemView.findViewById(R.id.iv_order_goods_pic);
            tvName = (TextView) itemView.findViewById(R.id.iv_order_goods_name);
            tvSpec = (TextView) itemView.findViewById(R.id.iv_order_goods_spec);
            tvDeadline = (TextView) itemView.findViewById(R.id.iv_order_receive_deadline);
            tvPrice = (TextView) itemView.findViewById(R.id.iv_order_goods_price);
            tvCount = (TextView) itemView.findViewById(R.id.tv_order_goods_count);
            tvBtn = (TextView) itemView.findViewById(R.id.tv_order_btn);
        }
    }
}
