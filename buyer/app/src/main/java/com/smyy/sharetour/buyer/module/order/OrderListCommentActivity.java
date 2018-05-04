package com.smyy.sharetour.buyer.module.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.module.order.adapter.OrderCommentListAdapter;
import com.smyy.sharetour.buyer.module.order.bean.OrderGoodsInfo;
import com.smyy.sharetour.buyer.require.RecyclerViewDivider;
import com.smyy.sharetour.buyer.util.ActivityLauncher;
import com.smyy.sharetour.buyer.view.InnerRecyclerView;
import com.smyy.sharetour.buyer.view.RatingBar;
import com.xmyy.view.dialoglib.CommonDialog;
import com.xmyy.view.dialoglib.base.BindViewHolder;
import com.xmyy.view.dialoglib.listener.OnViewClickListener;
import com.yongchun.library.utils.ScreenUtils;
import com.yongchun.library.view.ImageSelectorActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Liliping
 * @org www.smyy.com
 * @email lilp@stjf.com
 * @package com.smyy.sharetour.buyer.module.order
 * @fileName OrderListCommentActivity
 * @date on 2018/5/4 0004 15:27
 * @describe 订单多个商品评价页面
 */
public class OrderListCommentActivity extends BaseMvpActivity {

    private static final int REQUEST_CODE_CAMERA = 101;
    @BindView(R.id.good_rating)
    RatingBar goodRating;
    @BindView(R.id.good_quality)
    LinearLayout goodQuality;
    @BindView(R.id.seller_rating)
    RatingBar sellerRating;
    @BindView(R.id.seller_service)
    LinearLayout sellerService;
    @BindView(R.id.comment_list)
    InnerRecyclerView commentList;

    private List<OrderGoodsInfo> orderGoods = new ArrayList<>();
    private boolean is_append = false;
    private TextView toolbarRightTv;
    public static final String COMMENT_IS_APPEND = "append";
    public static final String COMMENT_GOOD_INFO = "good_info";
    private int gridView_position = -1;
    private OrderCommentListAdapter adapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_list_comment;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText(R.string.publish_comment);
        toolbarRightTv = getToolbarRightTv();
        toolbarRightTv.setText(getString(R.string.publish));
        toolbarRightTv.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        is_append = intent.getBooleanExtra(COMMENT_IS_APPEND, false);
        demo();
        initUI(intent);
    }


    private void initUI(Intent intent) {
        //判断是否为追加评价
        if (is_append) {
            goodQuality.setVisibility(View.GONE);
            sellerService.setVisibility(View.GONE);
        }

        commentList.setLayoutManager(new LinearLayoutManager(OrderListCommentActivity.this));
        commentList.addItemDecoration(new RecyclerViewDivider(OrderListCommentActivity.this,
                LinearLayoutManager.VERTICAL, ScreenUtils.dip2px(getApplicationContext(),30), R.color.window_background));

        adapter = new OrderCommentListAdapter(OrderListCommentActivity.this, orderGoods, is_append);
        adapter.setItemClickListener(new OrderCommentListAdapter.OnItemCommentClickListener() {
            @Override
            public void OnItemAddPicClick(View v, int position) {
                gridView_position = position;
            }

            @Override
            public void OnItemClick(View v, int position) {

            }
        });
        commentList.setAdapter(adapter);

        toolbarRightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!is_append) {
                    int good_score = goodRating.getStar();
                    int seller_score = sellerRating.getStar();
                }
                CommonDialog.Builder builder = new CommonDialog.Builder(getSupportFragmentManager())
                        .setLayoutRes(R.layout.layout_comment_success)
                        .setGravity(Gravity.CENTER)
                        .addOnClickListener(R.id.view_orders)
                        .setOnViewClickListener(new OnViewClickListener() {
                            @Override
                            public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                                commonDialog.dismiss();
                                finish();
                                ActivityLauncher.viewMyCommentActivity(OrderListCommentActivity.this);
                            }
                        });

                builder.create().show();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case ImageSelectorActivity.REQUEST_IMAGE:
                    ArrayList<String> images = (ArrayList<String>) data.getSerializableExtra(ImageSelectorActivity.REQUEST_OUTPUT);
                    adapter.setGridViewData(gridView_position, images);
                    adapter.notifyItemChanged(gridView_position,0);
                    break;
               /* // 预览
                case REQUEST_PREVIEW_CODE:
                    //ArrayList<String> ListExtra = data.getStringArrayListExtra(PublishRequireActivity.EXTRA_RESULT);
                    //loadAdapter(ListExtra);
                    break;*/
            }
        }
    }


    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    private void demo() {
        OrderGoodsInfo goodsInfo1 = new OrderGoodsInfo("1",
                "NIKE HUARACHE DRIFT (PSE) LALALALALA",
                "黑白/36.5",
                "",
                "￥9,918.00",
                1, "",OrderHelper.GOODS_TYPE_DEMAND,null);
        OrderGoodsInfo goodsInfo2 = new OrderGoodsInfo("2",
                "NIKE HUARACHE DRIFT (PSE) LALALALALA",
                "黑白/34",
                "",
                "￥8,918.00",
                1, "",OrderHelper.GOODS_TYPE_DEMAND,null);
        orderGoods.add(goodsInfo1);
        orderGoods.add(goodsInfo2);
    }
}
