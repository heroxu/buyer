package com.smyy.sharetour.buyer.home.detail.commet;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.home.model.HomeRoute;
import com.smyy.sharetour.buyer.home.model.HomeTitleBean;
import com.smyy.sharetour.buyer.home.model.ProductBase;
import com.smyy.sharetour.buyer.home.model.ProductComment;
import com.smyy.sharetour.buyer.util.ActivityLauncher;
import com.smyy.sharetour.buyer.view.lisenter.PagingScrollHelper;

import java.util.List;

/**
 * create by xuxiarong on 2018/4/12
 */
public class ProductDetailCommentBaseAdapter extends RecyclerView.Adapter {

    public static final int ITEM_PRODUCT_COMMENT_HEADER = 0x10;
    public static final int ITEM_PRODUCT_LOOK_COMMENT = 0x20;
    public static final int ITEM_PRODUCT_NO_COMMENT = 0x30;
    public static final int ITEM_CHILD_RV_PRODUCT = 0x40;


    private Context mContext;
    private List<ProductBase> mDatas;
    private List<ProductComment> mProductComments;

    public ProductDetailCommentBaseAdapter(Context context, List<ProductBase> datas, List<ProductComment> comments) {
        this.mContext = context;
        this.mDatas = datas;
        this.mProductComments = comments;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_PRODUCT_COMMENT_HEADER:
                View comment_header = LayoutInflater.from(mContext).inflate(R.layout.item_product_detail_comment_header, parent, false);
                return new ProductComentHeaderViewHolder(comment_header);
            case ITEM_PRODUCT_LOOK_COMMENT:
                View look_comment = LayoutInflater.from(mContext).inflate(R.layout.item_product_detail_look_comment, parent, false);
                return new ProductLookCommentViewHolder(look_comment);
            case ITEM_CHILD_RV_PRODUCT:
                View comment_rv = LayoutInflater.from(mContext).inflate(R.layout.item_product_detail_comment_rv, parent, false);
                return new ProductCommentRvViewHolder(comment_rv);
            case ITEM_PRODUCT_NO_COMMENT:
                View no_comment = LayoutInflater.from(mContext).inflate(R.layout.item_product_detail_no_comment, parent, false);
                return new ProductNoCommentViewHolder(no_comment);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (mDatas.isEmpty()) {
            return;
        }
        if (ITEM_PRODUCT_COMMENT_HEADER == mDatas.get(position).viewType) {
            ProductComentHeaderViewHolder productComentHeaderViewHolder = (ProductComentHeaderViewHolder) holder;
            productComentHeaderViewHolder.tv_product_detail_comment_num.setText(mContext.getString(R.string.product_detail_comment_num, mProductComments.size()));
        } else if (ITEM_PRODUCT_LOOK_COMMENT == mDatas.get(position).viewType) {
            ProductLookCommentViewHolder lookCommentViewHolder = (ProductLookCommentViewHolder) holder;
            lookCommentViewHolder.tv_product_detail_look_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO start all comment activity
                }
            });

        } else if (ITEM_CHILD_RV_PRODUCT == mDatas.get(position).viewType) {
            ProductCommentRvViewHolder rvViewHolder = (ProductCommentRvViewHolder) holder;
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rvViewHolder.rv_product_detail_comment.setLayoutManager(linearLayoutManager);
            rvViewHolder.rv_product_detail_comment.setAdapter(new ProductDetailCommentAdapter(mContext, mProductComments));

        } else if (ITEM_PRODUCT_NO_COMMENT == mDatas.get(position).viewType) {

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas.isEmpty()) {
            return super.getItemViewType(position);
        }
        return mDatas.get(position).viewType;
    }

    @Override
    public int getItemCount() {
        return mDatas.isEmpty() ? 0 : mDatas.size();
    }

    public void setData(List<ProductBase> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    class ProductComentHeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_product_detail_comment_num;

        public ProductComentHeaderViewHolder(View itemView) {
            super(itemView);
            tv_product_detail_comment_num = (TextView) itemView.findViewById(R.id.tv_product_detail_comment_num);
        }
    }

    class ProductLookCommentViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_product_detail_look_comment;

        public ProductLookCommentViewHolder(View itemView) {
            super(itemView);
            tv_product_detail_look_comment = (TextView) itemView.findViewById(R.id.tv_product_detail_look_comment);
        }
    }

    class ProductCommentRvViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView rv_product_detail_comment;

        public ProductCommentRvViewHolder(View itemView) {
            super(itemView);
            rv_product_detail_comment = (RecyclerView) itemView.findViewById(R.id.rv_product_detail_comment);
        }
    }

    class ProductNoCommentViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_product_detail_no_comment;

        public ProductNoCommentViewHolder(View itemView) {
            super(itemView);
            tv_product_detail_no_comment = (TextView) itemView.findViewById(R.id.tv_product_detail_no_comment);
        }
    }
}
