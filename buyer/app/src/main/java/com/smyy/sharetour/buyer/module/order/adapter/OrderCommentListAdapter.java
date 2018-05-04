package com.smyy.sharetour.buyer.module.order.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.module.order.bean.OrderGoodsInfo;
import com.smyy.sharetour.buyer.require.OnRecyclerViewOnClickListener;
import com.smyy.sharetour.buyer.view.InnerRecyclerView;
import com.smyy.sharetour.uiframelib.BaseActivity;
import com.yongchun.library.view.ImagePreviewActivity;
import com.yongchun.library.view.ImageSelectorActivity;

import java.util.ArrayList;
import java.util.List;


public class OrderCommentListAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final LayoutInflater inflater;
    private List<OrderGoodsInfo> list = new ArrayList<>();
    private List<ArrayList> picArrays = new ArrayList<>();
    private OnItemCommentClickListener mListener;
    private boolean is_append;

    public OrderCommentListAdapter(Context context, List<OrderGoodsInfo> list, boolean is_append){
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.is_append = is_append;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalViewHolder(inflater.inflate(R.layout.item_order_comment_list, parent, false), mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(!is_append&&picArrays.get(position)!=null&&picArrays.get(position).size()!= 0) {
            ((NormalViewHolder)holder).adapter.notifyItemRangeChanged(0, picArrays.get(position).size(), 0);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void setItemClickListener(OnItemCommentClickListener listener){
        this.mListener = listener;
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnItemCommentClickListener listener;
        private ImageView goodImage;
        private TextView goodName;
        private TextView goodSpec;
        private InnerRecyclerView picList;
        private TextView goodCount;
        private EditText discContext;
        private OrderCommentPicItemAdapter adapter;

        public NormalViewHolder(final View itemView, final OnItemCommentClickListener listener) {
            super(itemView);
            goodImage = (ImageView) itemView.findViewById(R.id.iv_order_goods_pic);
            goodName = (TextView) itemView.findViewById(R.id.iv_order_goods_name);
            goodSpec = (TextView) itemView.findViewById(R.id.iv_order_goods_spec);
            goodCount = (TextView) itemView.findViewById(R.id.tv_order_goods_count);
            discContext = (EditText) itemView.findViewById(R.id.disc_context);

            picList = (InnerRecyclerView) itemView.findViewById(R.id.comment_pic_list);
            if (!is_append) {
                picList.setLayoutManager(new GridLayoutManager(context, 4));
                final ArrayList<String> array = new ArrayList<>();
                picArrays.add(array);
                adapter = new OrderCommentPicItemAdapter(context, array);
                adapter.setItemClickListener(new OnRecyclerViewOnClickListener() {
                    @Override
                    public void OnItemClick(View v, int position) {
                        if(listener != null) {
                            listener.OnItemAddPicClick(itemView, getLayoutPosition());
                        }

                        if (array.size() != Consts.COMMENT_SELECT_PICTURE_NUM && position == array.size()) {
                            ImageSelectorActivity.start((BaseActivity) context, Consts.COMMENT_SELECT_PICTURE_NUM - array.size(),
                                    ImageSelectorActivity.MODE_MULTIPLE, true, true, false, 0);
                        } else {
                            ImagePreviewActivity.startPreview((BaseActivity) context, array, position);
                        }
                    }
                });
                adapter.setOnDeleteClickListener(new OrderCommentPicItemAdapter.OnDeleteClickListener() {
                    @Override
                    public void onDelClick(int position) {
                        array.remove(position);
                        adapter.notifyItemRemoved(0);
                    }
                });
                picList.setAdapter(adapter);
            } else {
                picList.setVisibility(View.GONE);
            }
        }

        @Override
        public void onClick(View v) {
            if (listener != null){
                listener.OnItemClick(v, getLayoutPosition());
            }
        }
    }

    public interface OnItemCommentClickListener extends OnRecyclerViewOnClickListener{
        void OnItemAddPicClick(View v, int position);
    }

    public void setGridViewData(int position, ArrayList<String> imgs) {
        picArrays.get(position).addAll(imgs);
    }
}
