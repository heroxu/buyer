package com.smyy.sharetour.buyer.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.adapter.CommentsSecondAdapter;
import com.smyy.sharetour.buyer.adapter.FountImageAdapter;
import com.smyy.sharetour.buyer.bean.CommentsBean;
import com.smyy.sharetour.buyer.bean.FountBean;
import com.smyy.sharetour.buyer.bean.Test1Bean;
import com.smyy.sharetour.buyer.util.ActivityLauncher;
import com.smyy.sharetour.buyer.util.ToastUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 伍振飞 on 2018/4/11 11:11
 * E-Mail Address：wuzf2012@sina.com
 */
public class CommentsAdapter extends BaseQuickAdapter<CommentsBean.MainList, BaseViewHolder> {

    public CommentsAdapter(List<CommentsBean.MainList> data) {
        super(R.layout.item_comments_first_layer, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, CommentsBean.MainList mainList) {
        //创建一个临时数据下一个界面用
        baseViewHolder.addOnClickListener(R.id.tv_reply_comments);
        final CommentsBean.MainList nextData = mainList;
        baseViewHolder.setText(R.id.tv_comments_first_name, mainList.getName())
                .setText(R.id.tv_comments_first_content, mainList.getContent());
        CircleImageView imageView = baseViewHolder.getView(R.id.iv_comments_first_photo);
        Glide.with(mContext).load(mainList.getImageUrl()).into(imageView);
        ((RecyclerView) baseViewHolder.getView(R.id.rv_comments_second)).setLayoutManager(new LinearLayoutManager(mContext));
        mainList.setCsCount();
        CommentsSecondAdapter mCommentsSecondAdapter = new CommentsSecondAdapter(mainList.getCsList());
        ((RecyclerView) baseViewHolder.getView(R.id.rv_comments_second)).setAdapter(mCommentsSecondAdapter);
        mCommentsSecondAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_more_reply:
                        ActivityLauncher.viewMoreReplyActivityty(mContext, nextData);
                        break;
                }
            }
        });
    }
}
