package com.smyy.sharetour.buyer.home.detail.commet;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.home.model.ProductComment;
import com.smyy.sharetour.buyer.home.model.ProductDetail;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * create by xuxiarong on 2018/4/12
 */
public class ProductDetailCommentAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<ProductComment> mDatas;

    public ProductDetailCommentAdapter(Context context, List<ProductComment> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductDetailViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_product_detail_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ProductDetailViewHolder viewHolder = (ProductDetailViewHolder) holder;


            viewHolder.tv_product_detail_comment_content.setText(mDatas.get(position).content);
            viewHolder.tv_product_detail_avatar_name.setText(mDatas.get(position).avatarName);


    }

    @Override
    public int getItemCount() {
        return mDatas.isEmpty()?0:mDatas.size();
    }

    public void setData(List<ProductComment> datas){
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    class ProductDetailViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout ll_product_detail_comment;
        private CircleImageView iv_product_detail_avatar;
        private TextView tv_product_detail_avatar_name;
        private TextView tv_product_detail_comment_content;

        public ProductDetailViewHolder(View itemView) {
            super(itemView);
            iv_product_detail_avatar = (CircleImageView) itemView.findViewById(R.id.iv_product_detail_avatar);
            tv_product_detail_avatar_name = (TextView) itemView.findViewById(R.id.tv_product_detail_avatar_name);
            tv_product_detail_comment_content = (TextView) itemView.findViewById(R.id.tv_product_detail_comment_content);

        }
    }
}
