package com.smyy.sharetour.buyer.home.detail.commet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.home.model.ProductBase;
import com.smyy.sharetour.buyer.home.model.ProductComment;

import java.security.cert.PKIXCertPathBuilderResult;
import java.util.ArrayList;
import java.util.List;

/**
 * create by xuxiarong on 2018/4/13
 */
public class ProductDetailCommentFragment extends BaseMvpFragment {

    private List<ProductComment> mProductComments = new ArrayList<>();
    private List<ProductBase> mDatas = new ArrayList<>();
    private RecyclerView rv_product_detail_base;


    public static ProductDetailCommentFragment getInstance(String title) {
        ProductDetailCommentFragment sf = new ProductDetailCommentFragment();
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
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product_detail_comment;
    }

    @Override
    protected void initData(Bundle bundle) {
        mProductComments.add(new ProductComment("","sdasd","算啦解放啦时间疯狂拉升减肥是你父母你的家客户服务和设计开发拿手机看恒大健康是你们放那可就是大大说撒大发大发"));
        mProductComments.add(new ProductComment("","sdasd","算啦解放啦时间疯狂拉升减肥是你父母你的家客户服务和设计开发拿手机看恒大健康是你们放那可就是大大说撒大发大发"));
        mProductComments.add(new ProductComment("","sdasd","算啦解放啦时间疯狂拉升减肥是你父母你的家客户服务和设计开发拿手机看恒大健康是你们放那可就是大大说撒大发大发"));
        mProductComments.add(new ProductComment("","sdasd","算啦解放啦时间疯狂拉升减肥是你父母你的家客户服务和设计开发拿手机看恒大健康是你们放那可就是大大说撒大发大发"));

        if(mProductComments.isEmpty()){
            mDatas.add(new ProductBase(ProductDetailCommentBaseAdapter.ITEM_PRODUCT_COMMENT_HEADER));
            mDatas.add(new ProductBase(ProductDetailCommentBaseAdapter.ITEM_PRODUCT_NO_COMMENT));

        }else {
            mDatas.add(new ProductBase(ProductDetailCommentBaseAdapter.ITEM_PRODUCT_COMMENT_HEADER));
            mDatas.add(new ProductBase(ProductDetailCommentBaseAdapter.ITEM_CHILD_RV_PRODUCT));
            mDatas.add(new ProductBase(ProductDetailCommentBaseAdapter.ITEM_PRODUCT_LOOK_COMMENT));

        }
        rv_product_detail_base.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv_product_detail_base.setAdapter(new ProductDetailCommentBaseAdapter(this.getContext(), mDatas,mProductComments));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_product_detail_base = (RecyclerView) view.findViewById(R.id.rv_product_detail_base);


    }


}
