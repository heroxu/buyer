package com.smyy.sharetour.buyer.home.search.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.home.model.SearchProduct;

import java.util.List;

/**
 * create by xuxiarong on 2018/4/12
 */
public class SearchProductAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<SearchProduct> mDatas;

    public SearchProductAdapter(Context context, List<SearchProduct> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchProductViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_search_product, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        SearchProductViewHolder viewHolder = (SearchProductViewHolder) holder;
        SearchProduct searchProduct = mDatas.get(position);
        viewHolder.tv_search_product_name.setText(searchProduct.name);
        viewHolder.tv_search_product_price.setText(searchProduct.price);
        viewHolder.tv_search_product_rmb.setText(searchProduct.price_rmb);
        viewHolder.tv_search_product_nation_name.setText(searchProduct.nation_name);
    }

    @Override
    public int getItemCount() {
        return mDatas.isEmpty()?0:mDatas.size();
    }

    public void setData(List<SearchProduct> datas){
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    class SearchProductViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_search_product_pic;
        private TextView tv_search_product_name;
        private TextView tv_search_product_price;
        private TextView tv_search_product_rmb;
        private ImageView iv_search_product_nation_pic;
        private TextView tv_search_product_nation_name;

        public SearchProductViewHolder(View itemView) {
            super(itemView);
            iv_search_product_pic = (ImageView) itemView.findViewById(R.id.iv_search_product_pic);
            tv_search_product_name = (TextView) itemView.findViewById(R.id.tv_search_product_name);
            tv_search_product_price = (TextView) itemView.findViewById(R.id.tv_search_product_price);
            tv_search_product_rmb = (TextView) itemView.findViewById(R.id.tv_search_product_rmb);
            iv_search_product_nation_pic = (ImageView) itemView.findViewById(R.id.iv_search_product_nation_pic);
            tv_search_product_nation_name = (TextView) itemView.findViewById(R.id.tv_search_product_nation_name);

        }
    }
}
