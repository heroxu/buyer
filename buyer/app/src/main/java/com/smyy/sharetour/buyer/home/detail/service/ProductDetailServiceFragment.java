package com.smyy.sharetour.buyer.home.detail.service;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.home.detail.commet.ProductDetailCommentAdapter;
import com.smyy.sharetour.buyer.home.detail.product.ProductDetailAdapter;
import com.smyy.sharetour.buyer.home.model.ProductComment;
import com.smyy.sharetour.buyer.home.model.ProductDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * create by xuxiarong on 2018/4/13
 */
public class ProductDetailServiceFragment extends BaseMvpFragment {

    private List<String> mDatas = new ArrayList<>();
    private RecyclerView rv_product_detail_service;

    public static ProductDetailServiceFragment getInstance(String title) {
        ProductDetailServiceFragment sf = new ProductDetailServiceFragment();
        return sf;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product_detail_service;
    }

    @Override
    protected void initData(Bundle bundle) {
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_product_detail_service = (RecyclerView) view.findViewById(R.id.rv_product_detail_service);
        rv_product_detail_service.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mDatas.add("");
        rv_product_detail_service.setAdapter(new ProductServiceAdapter(this.getContext(),mDatas));
    }

    private class ProductServiceAdapter extends RecyclerView.Adapter{

        private Context mContext;
        private List<String> mAdapterDatas;

        public ProductServiceAdapter(Context context, List<String> datas) {
            this.mContext = context;
            this.mAdapterDatas = datas;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ProductServiceViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_product_detail_service, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return mAdapterDatas.isEmpty()?0:mAdapterDatas.size();
        }
    }

    class ProductServiceViewHolder extends RecyclerView.ViewHolder {

        public ProductServiceViewHolder(View itemView) {
            super(itemView);
        }
    }

}
