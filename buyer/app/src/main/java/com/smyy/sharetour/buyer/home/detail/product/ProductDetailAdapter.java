package com.smyy.sharetour.buyer.home.detail.product;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.home.model.ProductDetail;

import java.util.List;

/**
 * create by xuxiarong on 2018/4/12
 */
public class ProductDetailAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<ProductDetail> mDatas;

    public ProductDetailAdapter(Context context, List<ProductDetail> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductDetailViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_product_detail_comment_rv_content, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ProductDetailViewHolder viewHolder = (ProductDetailViewHolder) holder;
//        viewHolder.tv_product_detail.setText(mDatas.get(position).content);
    }

    @Override
    public int getItemCount() {
        return mDatas.isEmpty()?0:mDatas.size();
    }

    public void setData(List<ProductDetail> datas){
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    class ProductDetailViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_product_detail;
        private TextView tv_product_detail;
        public ProductDetailViewHolder(View itemView) {
            super(itemView);
            iv_product_detail = (ImageView) itemView.findViewById(R.id.iv_product_detail);
            tv_product_detail = (TextView) itemView.findViewById(R.id.tv_product_detail);
        }
    }
}
