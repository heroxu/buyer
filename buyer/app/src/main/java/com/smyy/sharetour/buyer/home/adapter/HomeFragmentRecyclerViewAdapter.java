package com.smyy.sharetour.buyer.home.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.home.model.HomeHotProduct;
import com.smyy.sharetour.buyer.home.model.HomeHotProductItem;
import com.smyy.sharetour.buyer.home.model.HomeNewSell;
import com.smyy.sharetour.buyer.home.model.HomeNewSellItem;
import com.smyy.sharetour.buyer.home.model.HomeNote;
import com.smyy.sharetour.buyer.home.model.HomeNoteItem;
import com.smyy.sharetour.buyer.home.model.HomeRecommend;
import com.smyy.sharetour.buyer.home.model.HomeRecommendItem;
import com.smyy.sharetour.buyer.home.model.HomeRecyclerBaseBean;
import com.smyy.sharetour.buyer.home.model.HomeRoute;
import com.smyy.sharetour.buyer.home.model.HomeRouteItem;
import com.smyy.sharetour.buyer.home.model.HomeTitleBean;
import com.smyy.sharetour.buyer.util.ActivityLauncher;
import com.smyy.sharetour.buyer.view.lisenter.PagingScrollHelper;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 许夏荣 on 2018/3/29.
 * desc:
 */

public class HomeFragmentRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static final int ITEM_TITLE = 0x10;
    public static final int ITEM_CHILD_ROUTE = 0x20;
    public static final int ITEM_CHILD_NEW_SELL = 0x30;
    public static final int ITEM_CHILD_HOT_PRODUCT = 0x40;
    public static final int ITEM_CHILD_RECOMMEND = 0x50;
    public static final int ITEM_NOTES = 0x60;

    public static final int NOTE_TYPE_VEDIO = 0x11;
    public static final int NOTE_TYPE_SINGLE = 0x21;
    public static final int NOTE_TYPE_MULTIPLE = 0x31;


    private Context mContext;
    private List<HomeRecyclerBaseBean> mDatas;

    public HomeFragmentRecyclerViewAdapter(Context context , List<HomeRecyclerBaseBean> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    public void setData(List<HomeRecyclerBaseBean> datas) {
        if(mDatas!=null){
            mDatas.clear();
            mDatas = datas;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType){
            case ITEM_TITLE:
                View v = LayoutInflater.from(mContext).inflate(R.layout.item_home_child_title, parent, false);
                return new HomeChildTitleHolder(v);
            case ITEM_CHILD_ROUTE:
                View buyer_route = LayoutInflater.from(mContext).inflate(R.layout.item_home_child_route_rv, parent, false);
                return new HomeChildRouteHolder(buyer_route);
            case ITEM_CHILD_NEW_SELL:
                View new_sell = LayoutInflater.from(mContext).inflate(R.layout.item_home_child_new_sell_rv, parent, false);
                return new HomeNewSellHolder(new_sell);
            case ITEM_CHILD_HOT_PRODUCT:
                View vedio = LayoutInflater.from(mContext).inflate(R.layout.item_home_child_hot_product_rv, parent, false);
                return new HomeHotProductHolder(vedio);
            case ITEM_CHILD_RECOMMEND:
                View recommend = LayoutInflater.from(mContext).inflate(R.layout.item_home_child_recommend_buyer_rv, parent, false);
                return new HomeRecommendHolder(recommend);
            case ITEM_NOTES:
                View notes = LayoutInflater.from(mContext).inflate(R.layout.item_home_child_notes_rv, parent, false);
                return new HomeNotesHolder(notes);
            default:
              return  null;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(mDatas.isEmpty()){
            return;
        }
        if(ITEM_TITLE == mDatas.get(position).viewType){
            HomeChildTitleHolder homeChildTitleHolder = (HomeChildTitleHolder) holder;
            HomeTitleBean homeTitleBean = (HomeTitleBean) mDatas.get(position);
            homeChildTitleHolder.tv_main_title.setText(homeTitleBean.mainTitle);
            homeChildTitleHolder.tv_sub_title.setText(homeTitleBean.subTitle);
            homeChildTitleHolder.ll_more.setVisibility(homeTitleBean.hasMore ? View.VISIBLE : View.GONE);
            homeChildTitleHolder.tv_change.setVisibility(homeTitleBean.hasChange ? View.VISIBLE : View.GONE);
        }else if(ITEM_CHILD_ROUTE == mDatas.get(position).viewType){
            HomeChildRouteHolder homeChildRouteHolder = (HomeChildRouteHolder) holder;
            HomeRoute homeRoute = (HomeRoute) mDatas.get(position);

            PagingScrollHelper scrollHelper = new PagingScrollHelper();
            LinearLayoutManager hLinearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            scrollHelper.setUpRecycleView(homeChildRouteHolder.rv_child_route);
            scrollHelper.setOnPageChangeListener(new PagingScrollHelper.onPageChangeListener() {
                @Override
                public void onPageChange(int index) {

                }
            });
            homeChildRouteHolder.rv_child_route.setHorizontalScrollBarEnabled(true);
            homeChildRouteHolder.rv_child_route.setLayoutManager(hLinearLayoutManager);

//            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
//            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//            homeChildRouteHolder.rv_child_route.setLayoutManager(linearLayoutManager);
            homeChildRouteHolder.rv_child_route.setAdapter(new HomeChildRouteDetailAdapter(homeRoute.routes));
        }else if(ITEM_CHILD_NEW_SELL == mDatas.get(position).viewType){
            HomeNewSellHolder homeNewSellHolder = (HomeNewSellHolder) holder;
            HomeNewSell homeNewSell = (HomeNewSell) mDatas.get(position);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            homeNewSellHolder.rv_child_new_sell.setLayoutManager(linearLayoutManager);
            homeNewSellHolder.rv_child_new_sell.setAdapter(new HomeNewSellAdapter(homeNewSell.newSellItems));
        }else if(ITEM_CHILD_HOT_PRODUCT == mDatas.get(position).viewType){
            HomeHotProductHolder hotProductHolder = (HomeHotProductHolder) holder;
            HomeHotProduct homeHotProduct = (HomeHotProduct) mDatas.get(position);
            LinearLayoutManager linearLayoutManager = new GridLayoutManager(mContext,2);
            hotProductHolder.rv_child_hot_product.setLayoutManager(linearLayoutManager);
            hotProductHolder.rv_child_hot_product.setAdapter(new HomeHotProductAdapter(homeHotProduct.hotproductItems));
        } else if (ITEM_CHILD_RECOMMEND == mDatas.get(position).viewType) {
            HomeRecommendHolder hotProductHolder = (HomeRecommendHolder) holder;
            HomeRecommend homeRecommend = (HomeRecommend) mDatas.get(position);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            hotProductHolder.rv_child_recommend_buyer.setLayoutManager(linearLayoutManager);
            hotProductHolder.rv_child_recommend_buyer.setAdapter(new HomeRecommendAdapter(homeRecommend.homeRecommendItems));
        }else if (ITEM_NOTES == mDatas.get(position).viewType) {
            HomeNotesHolder homeNotesHolder = (HomeNotesHolder) holder;
            HomeNote homeRecommend = (HomeNote) mDatas.get(position);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            homeNotesHolder.rv_child_notes.setLayoutManager(linearLayoutManager);
            homeNotesHolder.rv_child_notes.setAdapter(new HomeNotesAdapter(homeRecommend.homeNoteItems));
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.isEmpty()?0:mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {

        if(mDatas.isEmpty()){
            return super.getItemViewType(position);
        }
        return mDatas.get(position).viewType;
    }

    public static class HomeChildTitleHolder extends RecyclerView.ViewHolder{
        TextView tv_main_title;
        TextView tv_sub_title;
        LinearLayout ll_more;
        TextView tv_change;
        public HomeChildTitleHolder(View itemView) {
            super(itemView);
            tv_main_title = (TextView)itemView.findViewById(R.id.tv_main_title);
            tv_sub_title = (TextView)itemView.findViewById(R.id.tv_sub_title);
            ll_more = (LinearLayout)itemView.findViewById(R.id.ll_more);
            tv_change = (TextView) itemView.findViewById(R.id.tv_change);
        }
    }

    public static class HomeChildRouteHolder extends RecyclerView.ViewHolder{
        private RecyclerView rv_child_route;
        public HomeChildRouteHolder(View itemView) {
            super(itemView);
            rv_child_route = (RecyclerView) itemView.findViewById(R.id.rv_child_route);
        }
    }

    public class HomeChildRouteDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private List<HomeRouteItem> mRouteDatas;
        private int [] drawableArr = {R.mipmap.random_icon_one,R.mipmap.random_icon_two,R.mipmap.random_icon_three,R.mipmap.random_icon_four};
        public HomeChildRouteDetailAdapter(List<HomeRouteItem> datas) {
            this.mRouteDatas = datas;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new HomeRouteItemHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_child_route, parent, false));

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            HomeRouteItemHolder viewHolder = (HomeRouteItemHolder) holder;
            viewHolder.iv_buyer_route.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActivityLauncher.viewHomeDetail(mContext);
                }
            });
            viewHolder.tv_buyer_go.setText(mRouteDatas.get(position).goTime);
            viewHolder.tv_buyer_back.setText(mRouteDatas.get(position).backTime);
            viewHolder.iv_buyer_route.setImageResource(drawableArr[position]);
        }

        @Override
        public int getItemCount() {
            return mRouteDatas.isEmpty()?0: mRouteDatas.size();
        }

        public  class HomeRouteItemHolder extends RecyclerView.ViewHolder{
            ImageView iv_buyer_route;
            TextView tv_buyer_go;
            TextView tv_buyer_back;
            TextView tv_route_classify_one;
            TextView tv_route_classify_two;
            TextView tv_route_classify_three;
            public HomeRouteItemHolder(View itemView) {
                super(itemView);
                iv_buyer_route = (ImageView) itemView.findViewById(R.id.iv_buyer_route);
                tv_buyer_go = (TextView)itemView.findViewById(R.id.tv_buyer_go);
                tv_buyer_back = (TextView)itemView.findViewById(R.id.tv_buyer_back);
                tv_route_classify_one = (TextView)itemView.findViewById(R.id.tv_route_classify_one);
                tv_route_classify_two = (TextView)itemView.findViewById(R.id.tv_route_classify_two);
                tv_route_classify_three = (TextView)itemView.findViewById(R.id.tv_route_classify_three);
            }
        }
    }

    public static class HomeNewSellHolder extends RecyclerView.ViewHolder{
        private RecyclerView rv_child_new_sell;
        public HomeNewSellHolder(View itemView) {
            super(itemView);
            rv_child_new_sell = (RecyclerView) itemView.findViewById(R.id.rv_child_new_sell);
        }
    }

    public class HomeNewSellAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private List<HomeNewSellItem> mNewSellDatas;
        private int [] drawableArr = {R.mipmap.random_icon_one,R.mipmap.random_icon_two,R.mipmap.random_icon_three,R.mipmap.random_icon_four};
        public HomeNewSellAdapter(List<HomeNewSellItem> datas) {
            this.mNewSellDatas = datas;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new HomeNewSellItemHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_child_new_sell, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            HomeNewSellItemHolder viewHolder = (HomeNewSellItemHolder) holder;
            HomeNewSellItem homeNewSellItem = mNewSellDatas.get(position);
            viewHolder.tv_new_sell_time.setText(homeNewSellItem.sellTime);
            viewHolder.tv_new_sell_reserve_number.setText(homeNewSellItem.reserveNumber);
            viewHolder.iv_new_sell_product_pic.setImageResource(R.mipmap.home_new_sell);
            viewHolder.tv_new_sell_product_name.setText(homeNewSellItem.productName);
            viewHolder.tv_new_sell_product_price.setText(homeNewSellItem.productPrice);
            viewHolder.tv_new_sell_product_address.setText(homeNewSellItem.productAddress);
        }

        @Override
        public int getItemCount() {
            return mNewSellDatas.isEmpty()?0: mNewSellDatas.size();
        }

        public  class HomeNewSellItemHolder extends RecyclerView.ViewHolder{
            TextView tv_new_sell_time;
            TextView tv_new_sell_reserve_number;
            ImageView iv_new_sell_product_pic;
            TextView tv_new_sell_product_name;
            TextView tv_new_sell_product_price;
            TextView tv_new_sell_product_address;
            public HomeNewSellItemHolder(View itemView) {
                super(itemView);
                tv_new_sell_time = (TextView) itemView.findViewById(R.id.tv_new_sell_time);
                tv_new_sell_reserve_number = (TextView) itemView.findViewById(R.id.tv_new_sell_reserve_number);
                iv_new_sell_product_pic = (ImageView) itemView.findViewById(R.id.iv_new_sell_product_pic);
                tv_new_sell_product_name = (TextView)itemView.findViewById(R.id.tv_new_sell_product_name);
                tv_new_sell_product_price = (TextView)itemView.findViewById(R.id.tv_new_sell_product_price);
                tv_new_sell_product_address = (TextView)itemView.findViewById(R.id.tv_new_sell_product_address);
            }
        }
    }

    public static class HomeHotProductHolder extends RecyclerView.ViewHolder{
        private RecyclerView rv_child_hot_product;
        public HomeHotProductHolder(View itemView) {
            super(itemView);
            rv_child_hot_product = (RecyclerView) itemView.findViewById(R.id.rv_child_hot_product);
        }
    }

    public class HomeHotProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private List<HomeHotProductItem> mNewSellDatas;
        private int [] drawableArr = {R.mipmap.random_icon_one,R.mipmap.random_icon_two,R.mipmap.random_icon_three,R.mipmap.random_icon_four};
        public HomeHotProductAdapter(List<HomeHotProductItem> datas) {
            this.mNewSellDatas = datas;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new HomeHotProductItemHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_child_hot_product, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            HomeHotProductItemHolder hotProductItemHolder = (HomeHotProductItemHolder) holder;
            HomeHotProductItem hotProductItem = mNewSellDatas.get(position);
            hotProductItemHolder.tv_hot_product_name.setText(hotProductItem.hotProductName);
            hotProductItemHolder.tv_hot_product_price.setText(hotProductItem.hotProductPriceNow);
            hotProductItemHolder.tv_hot_product_price_before.setText(hotProductItem.hotProductPriceBefore);
//            hotProductItemHolder.iv_hot_product_seller_avatar.setImageResource();
            hotProductItemHolder.tv_hot_product_seller_name.setText(hotProductItem.hotProductSellerName);
        }

        @Override
        public int getItemCount() {
            return mNewSellDatas.isEmpty()?0: mNewSellDatas.size();
        }

        public  class HomeHotProductItemHolder extends RecyclerView.ViewHolder{
            TextView tv_hot_product_name;
            TextView tv_hot_product_price;
            ImageView iv_hot_product_pic;
            TextView tv_hot_product_price_before;
            CircleImageView iv_hot_product_seller_avatar;
            TextView tv_hot_product_seller_name;
            public HomeHotProductItemHolder(View itemView) {
                super(itemView);
                tv_hot_product_name = (TextView) itemView.findViewById(R.id.tv_hot_product_name);
                tv_hot_product_price = (TextView) itemView.findViewById(R.id.tv_hot_product_price);
                iv_hot_product_pic = (ImageView) itemView.findViewById(R.id.iv_hot_product_pic);
                tv_hot_product_price_before = (TextView)itemView.findViewById(R.id.tv_hot_product_price_before);
                iv_hot_product_seller_avatar = (CircleImageView) itemView.findViewById(R.id.iv_hot_product_seller_avatar);
                tv_hot_product_seller_name = (TextView)itemView.findViewById(R.id.tv_hot_product_seller_name);
            }
        }
    }

    public static class HomeRecommendHolder extends RecyclerView.ViewHolder{
        private RecyclerView rv_child_recommend_buyer;
        public HomeRecommendHolder(View itemView) {
            super(itemView);
            rv_child_recommend_buyer = (RecyclerView) itemView.findViewById(R.id.rv_child_recommend_buyer);
        }
    }

    public class HomeRecommendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private List<HomeRecommendItem> mRecommendItems;
        private int [] drawableArr = {R.mipmap.random_icon_one,R.mipmap.random_icon_two,R.mipmap.random_icon_three,R.mipmap.random_icon_four};
        public HomeRecommendAdapter(List<HomeRecommendItem> datas) {
            this.mRecommendItems = datas;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new HomeRecommendItemHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_child_recommend_buyer, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            HomeRecommendItemHolder hotProductItemHolder = (HomeRecommendItemHolder) holder;
            HomeRecommendItem homeRecommendItem = mRecommendItems.get(position);
            hotProductItemHolder.tv_home_recommend_location.setText(homeRecommendItem.location);
            hotProductItemHolder.tv_home_recommend_avatar_name.setText(homeRecommendItem.avatarName);
            hotProductItemHolder.tv_recommend_classify_one.setText(homeRecommendItem.classifyOne);
            hotProductItemHolder.tv_recommend_classify_two.setText(homeRecommendItem.classifyTwo);
            hotProductItemHolder.tv_recommend_classify_three.setText(homeRecommendItem.classifyThree);

//            hotProductItemHolder.iv_hot_product_seller_avatar.setImageResource();
//            hotProductItemHolder.tv_hot_product_seller_name.setText(homeRecommendItem.hotProductSellerName);
        }

        @Override
        public int getItemCount() {
            return mRecommendItems.isEmpty()?0: mRecommendItems.size();
        }

        public  class HomeRecommendItemHolder extends RecyclerView.ViewHolder{
            TextView tv_home_recommend_location;
            TextView tv_home_recommend_avatar_name;
            CircleImageView iv_home_recommend_avatar;
            TextView tv_recommend_classify_one;
            TextView tv_recommend_classify_two;
            TextView tv_recommend_classify_three;
            TextView tv_home_recommend_communicate;

            public HomeRecommendItemHolder(View itemView) {
                super(itemView);
                tv_home_recommend_location = (TextView) itemView.findViewById(R.id.tv_home_recommend_location);
                tv_home_recommend_avatar_name = (TextView) itemView.findViewById(R.id.tv_home_recommend_avatar_name);
                iv_home_recommend_avatar = (CircleImageView) itemView.findViewById(R.id.iv_home_recommend_avatar);
                tv_recommend_classify_one = (TextView)itemView.findViewById(R.id.tv_recommend_classify_one);
                tv_recommend_classify_two = (TextView) itemView.findViewById(R.id.tv_recommend_classify_two);
                tv_recommend_classify_three = (TextView)itemView.findViewById(R.id.tv_recommend_classify_three);
                tv_home_recommend_communicate = (TextView)itemView.findViewById(R.id.tv_home_recommend_communicate);
            }
        }
    }

    public static class HomeNotesHolder extends RecyclerView.ViewHolder{
        private RecyclerView rv_child_notes;
        public HomeNotesHolder(View itemView) {
            super(itemView);
            rv_child_notes = (RecyclerView) itemView.findViewById(R.id.rv_child_notes);
        }
    }

    public class HomeNotesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private List<HomeNoteItem> mNotesItems;
        private int [] drawableArr = {R.mipmap.random_icon_one,R.mipmap.random_icon_two,R.mipmap.random_icon_three,R.mipmap.random_icon_four};
        public HomeNotesAdapter(List<HomeNoteItem> datas) {
            this.mNotesItems = datas;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new HomeNotesItemHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_child_notes, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            HomeNotesItemHolder homeNotesItemHolder = (HomeNotesItemHolder) holder;
            HomeNoteItem homeNoteItem = mNotesItems.get(position);
            if(homeNoteItem.type == NOTE_TYPE_VEDIO){
                homeNotesItemHolder.ll_home_note_multiple_parent.setVisibility(View.GONE);
                homeNotesItemHolder.tv_home_notes_single_content.setVisibility(View.GONE);
                homeNotesItemHolder.vv_home_notes.setVisibility(View.VISIBLE);
                homeNotesItemHolder.tv_home_notes_single_title.setText(homeNoteItem.title);
                homeNotesItemHolder.tv_home_note_type.setText("视频");
            }else if(homeNoteItem.type == NOTE_TYPE_SINGLE){
                homeNotesItemHolder.ll_home_note_multiple_parent.setVisibility(View.GONE);
                homeNotesItemHolder.tv_home_notes_single_content.setVisibility(View.VISIBLE);
                homeNotesItemHolder.vv_home_notes.setVisibility(View.GONE);
                homeNotesItemHolder.tv_home_notes_single_title.setText(homeNoteItem.title);
                homeNotesItemHolder.tv_home_notes_single_content.setText(homeNoteItem.content);
                homeNotesItemHolder.tv_home_note_type.setText("笔记");

            }else if(homeNoteItem.type == NOTE_TYPE_MULTIPLE){
                homeNotesItemHolder.ll_home_note_multiple_parent.setVisibility(View.VISIBLE);
                homeNotesItemHolder.tv_home_notes_single_content.setVisibility(View.GONE);
                homeNotesItemHolder.tv_home_notes_single_title.setVisibility(View.GONE);
                homeNotesItemHolder.vv_home_notes.setVisibility(View.GONE);
                homeNotesItemHolder.tv_home_note_multiple_title.setText(homeNoteItem.content);
                homeNotesItemHolder.tv_home_note_multiple_content.setText(homeNoteItem.content);
                homeNotesItemHolder.tv_home_note_type.setText("笔记");

            }
            homeNotesItemHolder.tv_home_note_avatar_name.setText(homeNoteItem.avatarName);


//            hotProductItemHolder.iv_hot_product_seller_avatar.setImageResource();
//            hotProductItemHolder.tv_hot_product_seller_name.setText(homeRecommendItem.hotProductSellerName);
        }

        @Override
        public int getItemCount() {
            return mNotesItems.isEmpty()?0: mNotesItems.size();
        }

        public  class HomeNotesItemHolder extends RecyclerView.ViewHolder{
            TextView tv_home_notes_single_title;
            VideoView vv_home_notes;
            TextView tv_home_notes_single_content;
            LinearLayout ll_home_note_multiple_parent;
            TextView tv_home_note_multiple_title;
            TextView tv_home_note_multiple_content;
            ImageView iv_home_note_multiple_place;
            TextView tv_home_note_type;
            CircleImageView iv_home_note_avatar;
            TextView tv_home_note_avatar_name;
            TextView tv_notes_other_agree;
            TextView tv_notes_other_agree_number;
            TextView tv_notes_other_comment;
            TextView tv_notes_other_comment_number;
            public HomeNotesItemHolder(View itemView) {
                super(itemView);
                tv_home_notes_single_title = (TextView) itemView.findViewById(R.id.tv_home_notes_single_title);
                vv_home_notes = (VideoView) itemView.findViewById(R.id.vv_home_notes);
                tv_home_notes_single_content = (TextView)itemView.findViewById(R.id.tv_home_notes_single_content);
                ll_home_note_multiple_parent = (LinearLayout) itemView.findViewById(R.id.ll_home_note_multiple_parent);
                tv_home_note_multiple_title = (TextView) itemView.findViewById(R.id.tv_home_note_multiple_title);
                tv_home_note_multiple_content = (TextView)itemView.findViewById(R.id.tv_home_note_multiple_content);
                iv_home_note_multiple_place = (ImageView) itemView.findViewById(R.id.iv_home_note_multiple_place);
                tv_home_note_type = (TextView)itemView.findViewById(R.id.tv_home_note_type);
                iv_home_note_avatar = (CircleImageView) itemView.findViewById(R.id.iv_home_note_avatar);
                tv_home_note_avatar_name = (TextView)itemView.findViewById(R.id.tv_home_note_avatar_name);
                tv_notes_other_agree = (TextView)itemView.findViewById(R.id.tv_notes_other_agree);
                tv_notes_other_agree_number = (TextView)itemView.findViewById(R.id.tv_notes_other_agree_number);
                tv_notes_other_comment = (TextView)itemView.findViewById(R.id.tv_notes_other_comment);
                tv_notes_other_comment_number = (TextView)itemView.findViewById(R.id.tv_notes_other_comment_number);

            }
        }
    }


}
