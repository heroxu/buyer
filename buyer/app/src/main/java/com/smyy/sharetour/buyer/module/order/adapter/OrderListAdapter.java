package com.smyy.sharetour.buyer.module.order.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.module.order.OrderHelper;
import com.smyy.sharetour.buyer.module.order.bean.OrderBean;
import com.smyy.sharetour.buyer.module.order.bean.OrderGoodsInfo;
import com.smyy.sharetour.buyer.util.Spanny;
import com.smyy.sharetour.buyer.util.StringUtil;
import com.smyy.sharetour.uiframelib.BaseActivity;

import java.util.List;


public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder> {

    private BaseActivity mActivity;
    private List<OrderBean> mDatas;

    public OrderListAdapter(BaseActivity context) {
        this.mActivity = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mActivity).inflate(R.layout.item_order_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final OrderBean data = mDatas.get(position);
        if (data != null) {
            holder.ivSellerAvatar.setImageResource(R.mipmap.fake_seller_avatar);
//            Glide.with(mActivity).load(data.getSellerAvatar()).into(holder.ivSellerAvatar);//TODO RTRT
            holder.tvSellerName.setText(data.getSellerName());

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
            holder.rvGoodsList.setLayoutManager(linearLayoutManager);
            OrderGoodsListAdapter adapter = new OrderGoodsListAdapter(mActivity, data.getGoodsList());
            holder.rvGoodsList.setAdapter(adapter);
            adapter.setOnItemClickListener(new OrderGoodsListAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position, OrderGoodsInfo goodsInfo) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(holder.itemView, position, data);
                    }
                }
            });

            int goodsType = data.getGoodsType();
            switch (goodsType) {
                case Consts.GOODS_TYPE_DEMAND:
                    holder.laySum.setVisibility(View.GONE);
                    break;

                default:
                    holder.laySum.setVisibility(View.VISIBLE);
                    int goodsCountTotal = data.getGoodsCountTotal();
                    if (goodsCountTotal > 0) {
                        holder.tvGoodsCount.setVisibility(View.VISIBLE);
                        holder.tvGoodsCount.setText("共" + goodsCountTotal + "件商品");
                    } else {
                        holder.tvGoodsCount.setVisibility(View.GONE);
                    }

                    holder.tvPriceTotal.setText(new Spanny().append("总额：")
                            .append(data.getPriceTotal(),
                                    new ForegroundColorSpan(mActivity.getResources().getColor(R.color.txt_price))));
                    holder.tvShippingFee.setText(StringUtil.connect("（含运费", data.getShippingFee(), "）"));
                    break;
            }

            int orderStatus = data.getOrderStatus();
            if (orderStatus >= 0 && orderStatus < Consts.ORDER_STATUS_STRINGS.length) {
                holder.tvStatus.setText(Consts.ORDER_STATUS_STRINGS[orderStatus]);
            } else {
                holder.tvStatus.setText("");
            }

            switch (orderStatus) {

                case Consts.ORDER_STATUS_AWAIT_PAY:
                    holder.tvStatus.setTextColor(mActivity.getResources().getColor(R.color.txt_price));

                    OrderHelper.switchBottomBtns(mActivity, true,
                            holder.tvBottomBtn1, holder.tvBottomBtn2,
                            holder.tvBottomBtn3, holder.tvBottomBtnMore,
                            "付款", Consts.ORDER_OPERATE_PAY,
                            "取消订单", Consts.ORDER_OPERATE_CANCEL,
                            "联系买手", Consts.ORDER_OPERATE_CONTACT_BUYER,
                            null, -1,
                            null, -1,
                            null, -1);
                    break;

                case Consts.ORDER_STATUS_AWAIT_SHIPPING:
                    holder.tvStatus.setTextColor(mActivity.getResources().getColor(R.color.txt_gray));

                    OrderHelper.switchBottomBtns(mActivity, false,
                            holder.tvBottomBtn1, holder.tvBottomBtn2,
                            holder.tvBottomBtn3, holder.tvBottomBtnMore,
                            "提醒发货", Consts.ORDER_OPERATE_REMIND_SHIPPING,
                            "联系客服", Consts.ORDER_OPERATE_CONTACT_SERVICE,
                            "联系买手", Consts.ORDER_OPERATE_CONTACT_BUYER,
                            null, -1,
                            null, -1,
                            null, -1);

                    break;

                case Consts.ORDER_STATUS_AWAIT_CONFIRM:
                    holder.tvStatus.setTextColor(mActivity.getResources().getColor(R.color.txt_gray));

                    OrderHelper.switchBottomBtns(mActivity, true,
                            holder.tvBottomBtn1, holder.tvBottomBtn2,
                            holder.tvBottomBtn3, holder.tvBottomBtnMore,
                            "确认收货", Consts.ORDER_OPERATE_CONFIRM,
                            "查看物流", Consts.ORDER_OPERATE_VIEW_SHIPPING,
                            "联系买手", Consts.ORDER_OPERATE_CONTACT_BUYER,
                            null, -1,
                            null, -1,
                            null, -1);

                    break;

                case Consts.ORDER_STATUS_AWAIT_REVIEW:
                    holder.tvStatus.setTextColor(mActivity.getResources().getColor(R.color.txt_gray));

                    OrderHelper.switchBottomBtns(mActivity, true,
                            holder.tvBottomBtn1, holder.tvBottomBtn2,
                            holder.tvBottomBtn3, holder.tvBottomBtnMore,
                            "评价", Consts.ORDER_OPERATE_REVIEW,
                            "查看物流", Consts.ORDER_OPERATE_VIEW_SHIPPING,
                            "删除订单", Consts.ORDER_OPERATE_DELETE,
                            null, -1,
                            null, -1,
                            null, -1);

                    break;

                case Consts.ORDER_STATUS_OTHER:
                    holder.tvStatus.setTextColor(mActivity.getResources().getColor(R.color.txt_gray));

                    OrderHelper.switchBottomBtns(mActivity, false,
                            holder.tvBottomBtn1, holder.tvBottomBtn2,
                            holder.tvBottomBtn3, holder.tvBottomBtnMore,
                            "删除订单", Consts.ORDER_OPERATE_DELETE,
                            null, -1,
                            null, -1,
                            null, -1,
                            null, -1,
                            null, -1);

                    break;

                default:
                    break;
            }
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
        private View laySum;
        private TextView tvGoodsCount;
        private TextView tvPriceTotal;
        private TextView tvShippingFee;
        private TextView tvBottomBtn1;
        private TextView tvBottomBtn2;
        private TextView tvBottomBtn3;
        private TextView tvBottomBtnMore;

        public ViewHolder(View itemView) {
            super(itemView);
            ivSellerAvatar = (ImageView) itemView.findViewById(R.id.iv_order_seller_avatar);
            tvSellerName = (TextView) itemView.findViewById(R.id.tv_order_seller_name);
            tvStatus = (TextView) itemView.findViewById(R.id.tv_order_status);
            rvGoodsList = (RecyclerView) itemView.findViewById(R.id.rv_order_goods_list);
            laySum = itemView.findViewById(R.id.lay_order_sum);
            tvGoodsCount = (TextView) itemView.findViewById(R.id.tv_order_goods_count);
            tvPriceTotal = (TextView) itemView.findViewById(R.id.tv_order_price_total);
            tvShippingFee = (TextView) itemView.findViewById(R.id.tv_order_shipping_fee);
            tvBottomBtn1 = (TextView) itemView.findViewById(R.id.tv_order_bottom_btn1);
            tvBottomBtn2 = (TextView) itemView.findViewById(R.id.tv_order_bottom_btn2);
            tvBottomBtn3 = (TextView) itemView.findViewById(R.id.tv_order_bottom_btn3);
            tvBottomBtnMore = (TextView) itemView.findViewById(R.id.tv_order_bottom_more);
        }
    }
}
