package com.smyy.sharetour.buyer.module.order.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.module.my.bean.ShippingAddressBean;
import com.smyy.sharetour.buyer.module.order.bean.OrderBean;
import com.smyy.sharetour.buyer.module.order.bean.OrderGoodsInfo;
import com.smyy.sharetour.buyer.util.Spanny;
import com.smyy.sharetour.buyer.util.StringUtil;

import java.util.List;


public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder> {

    private Context mContext;
    private List<OrderBean> mDatas;
    private OrderGoodsListAdapter mGoodsListAdapter;

    public OrderListAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_order_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final OrderBean data = mDatas.get(position);
        if (data != null) {
            holder.ivSellerAvatar.setImageResource(R.mipmap.fake_seller_avatar);
//            Glide.with(mContext).load(data.getSellerAvatar()).into(holder.ivSellerAvatar);//TODO RTRT
            holder.tvSellerName.setText(data.getSellerName());

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            holder.rvGoodsList.setLayoutManager(linearLayoutManager);
            OrderGoodsListAdapter adapter = new OrderGoodsListAdapter(mContext, data.getGoodsList());
            holder.rvGoodsList.setAdapter(adapter);
            adapter.setOnItemClickListener(new OrderGoodsListAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position, OrderGoodsInfo goodsInfo) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(holder.itemView, position, data);
                    }
                }
            });

            int goodsCountTotal = data.getGoodsCountTotal();
            if (goodsCountTotal > 0) {
                holder.tvGoodsCount.setVisibility(View.VISIBLE);
                holder.tvGoodsCount.setText("共" + goodsCountTotal + "件商品");
            } else {
                holder.tvGoodsCount.setVisibility(View.GONE);
            }

            holder.tvPriceTotal.setText(new Spanny().append("总额：")
                    .append(data.getPriceTotal(),
                            new ForegroundColorSpan(mContext.getResources().getColor(R.color.txt_price))));
            holder.tvShippingFee.setText(StringUtil.connect("（含运费", data.getShippingFee(), "）"));

            int orderStatus = data.getOrderStatus();
            if (orderStatus >= 0 && orderStatus < Consts.ORDER_STATUS_STRINGS.length) {
                holder.tvStatus.setText(Consts.ORDER_STATUS_STRINGS[orderStatus]);
            } else {
                holder.tvStatus.setText("");
            }

            switch (orderStatus) {
                case Consts.ORDER_STATUS_AWAIT_PAY:
                    holder.tvStatus.setTextColor(mContext.getResources().getColor(R.color.txt_price));
                    holder.tvVerifyVideo.setVisibility(View.GONE);
                    holder.tvContactSeller.setVisibility(View.VISIBLE);
                    holder.tvContactService.setVisibility(View.GONE);
                    holder.tvRemindShipping.setVisibility(View.GONE);
                    holder.tvDelete.setVisibility(View.GONE);
                    holder.tvViewShipping.setVisibility(View.GONE);
                    holder.tvCancel.setVisibility(View.VISIBLE);
                    holder.tvPay.setVisibility(View.VISIBLE);
                    holder.tvConifrm.setVisibility(View.GONE);
                    holder.tvReview.setVisibility(View.GONE);
                    break;

                case Consts.ORDER_STATUS_AWAIT_SHIPPING:
                    holder.tvStatus.setTextColor(mContext.getResources().getColor(R.color.txt_gray));
                    holder.tvVerifyVideo.setVisibility(View.GONE);
                    holder.tvContactSeller.setVisibility(View.VISIBLE);
                    holder.tvContactService.setVisibility(View.VISIBLE);
                    holder.tvRemindShipping.setVisibility(View.VISIBLE);
                    holder.tvDelete.setVisibility(View.GONE);
                    holder.tvViewShipping.setVisibility(View.GONE);
                    holder.tvCancel.setVisibility(View.GONE);
                    holder.tvPay.setVisibility(View.GONE);
                    holder.tvConifrm.setVisibility(View.GONE);
                    holder.tvReview.setVisibility(View.GONE);
                    break;

                case Consts.ORDER_STATUS_AWAIT_CONFIRM:
                    holder.tvStatus.setTextColor(mContext.getResources().getColor(R.color.txt_gray));
                    holder.tvVerifyVideo.setVisibility(View.GONE);
                    holder.tvContactSeller.setVisibility(View.VISIBLE);
                    holder.tvContactService.setVisibility(View.GONE);
                    holder.tvRemindShipping.setVisibility(View.GONE);
                    holder.tvDelete.setVisibility(View.GONE);
                    holder.tvViewShipping.setVisibility(View.VISIBLE);
                    holder.tvCancel.setVisibility(View.GONE);
                    holder.tvPay.setVisibility(View.GONE);
                    holder.tvConifrm.setVisibility(View.VISIBLE);
                    holder.tvReview.setVisibility(View.GONE);
                    break;

                case Consts.ORDER_STATUS_AWAIT_REVIEW:
                    holder.tvStatus.setTextColor(mContext.getResources().getColor(R.color.txt_gray));
                    holder.tvVerifyVideo.setVisibility(View.GONE);
                    holder.tvContactSeller.setVisibility(View.GONE);
                    holder.tvContactService.setVisibility(View.GONE);
                    holder.tvRemindShipping.setVisibility(View.GONE);
                    holder.tvDelete.setVisibility(View.VISIBLE);
                    holder.tvViewShipping.setVisibility(View.VISIBLE);
                    holder.tvCancel.setVisibility(View.GONE);
                    holder.tvPay.setVisibility(View.GONE);
                    holder.tvConifrm.setVisibility(View.GONE);
                    holder.tvReview.setVisibility(View.VISIBLE);
                    break;

                case Consts.ORDER_STATUS_OTHER:
                    holder.tvStatus.setTextColor(mContext.getResources().getColor(R.color.txt_gray));
                    holder.tvVerifyVideo.setVisibility(View.GONE);
                    holder.tvContactSeller.setVisibility(View.GONE);
                    holder.tvContactService.setVisibility(View.GONE);
                    holder.tvRemindShipping.setVisibility(View.GONE);
                    holder.tvDelete.setVisibility(View.VISIBLE);
                    holder.tvViewShipping.setVisibility(View.GONE);
                    holder.tvCancel.setVisibility(View.GONE);
                    holder.tvPay.setVisibility(View.GONE);
                    holder.tvConifrm.setVisibility(View.GONE);
                    holder.tvReview.setVisibility(View.GONE);
                    break;

                default:
                    break;
            }


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(holder.itemView, position, data);
                    }
                }
            });

            holder.tvVerifyVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemViewClickListener != null) {
                        onItemViewClickListener.onItemViewClick(holder.tvVerifyVideo, position, data);
                    }
                }
            });
            holder.tvContactSeller.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemViewClickListener != null) {
                        onItemViewClickListener.onItemViewClick(holder.tvContactSeller, position, data);
                    }
                }
            });
            holder.tvContactService.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemViewClickListener != null) {
                        onItemViewClickListener.onItemViewClick(holder.tvContactService, position, data);
                    }
                }
            });
            holder.tvRemindShipping.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemViewClickListener != null) {
                        onItemViewClickListener.onItemViewClick(holder.tvRemindShipping, position, data);
                    }
                }
            });
            holder.tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemViewClickListener != null) {
                        onItemViewClickListener.onItemViewClick(holder.tvDelete, position, data);
                    }
                }
            });
            holder.tvViewShipping.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemViewClickListener != null) {
                        onItemViewClickListener.onItemViewClick(holder.tvViewShipping, position, data);
                    }
                }
            });
            holder.tvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemViewClickListener != null) {
                        onItemViewClickListener.onItemViewClick(holder.tvCancel, position, data);
                    }
                }
            });
            holder.tvPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemViewClickListener != null) {
                        onItemViewClickListener.onItemViewClick(holder.tvPay, position, data);
                    }
                }
            });
            holder.tvConifrm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemViewClickListener != null) {
                        onItemViewClickListener.onItemViewClick(holder.tvConifrm, position, data);
                    }
                }
            });
            holder.tvReview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemViewClickListener != null) {
                        onItemViewClickListener.onItemViewClick(holder.tvReview, position, data);
                    }
                }
            });
        }
    }

    public List<OrderBean> getList() {
        return mDatas;
    }

    // 事件回调监听
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position, OrderBean data);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    // 事件回调监听
    private OnItemViewClickListener onItemViewClickListener;

    public interface OnItemViewClickListener {
        void onItemViewClick(View view, int position, OrderBean data);
    }

    public void setOnItemViewClickListener(OnItemViewClickListener listener) {
        this.onItemViewClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public void setData(List<OrderBean> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    public void remove(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount() - position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivSellerAvatar;
        private TextView tvSellerName;
        private TextView tvStatus;
        private RecyclerView rvGoodsList;
        private TextView tvGoodsCount;
        private TextView tvPriceTotal;
        private TextView tvShippingFee;
        private TextView tvVerifyVideo;
        private TextView tvContactSeller;
        private TextView tvContactService;
        private TextView tvRemindShipping;
        private TextView tvDelete;
        private TextView tvViewShipping;
        private TextView tvCancel;
        private TextView tvPay;
        private TextView tvConifrm;
        private TextView tvReview;

        public ViewHolder(View itemView) {
            super(itemView);
            ivSellerAvatar = (ImageView) itemView.findViewById(R.id.iv_order_seller_avatar);
            tvSellerName = (TextView) itemView.findViewById(R.id.tv_order_seller_name);
            tvStatus = (TextView) itemView.findViewById(R.id.tv_order_status);
            rvGoodsList = (RecyclerView) itemView.findViewById(R.id.rv_order_goods_list);
            tvGoodsCount = (TextView) itemView.findViewById(R.id.tv_order_goods_count);
            tvPriceTotal = (TextView) itemView.findViewById(R.id.tv_order_price_total);
            tvShippingFee = (TextView) itemView.findViewById(R.id.tv_order_shipping_fee);
            tvVerifyVideo = (TextView) itemView.findViewById(R.id.tv_order_verify_video);
            tvContactSeller = (TextView) itemView.findViewById(R.id.tv_order_contact_seller);
            tvContactService = (TextView) itemView.findViewById(R.id.tv_order_contact_service);
            tvRemindShipping = (TextView) itemView.findViewById(R.id.tv_order_remind_shipping);
            tvDelete = (TextView) itemView.findViewById(R.id.tv_order_delete);
            tvViewShipping = (TextView) itemView.findViewById(R.id.tv_order_view_shipping);
            tvCancel = (TextView) itemView.findViewById(R.id.tv_order_cancel);
            tvPay = (TextView) itemView.findViewById(R.id.tv_order_pay);
            tvConifrm = (TextView) itemView.findViewById(R.id.tv_order_confirm);
            tvReview = (TextView) itemView.findViewById(R.id.tv_order_review);
        }
    }
}
