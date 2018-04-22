package com.smyy.sharetour.buyer.module.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.PluralsRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.module.order.adapter.OrderListAdapter;
import com.smyy.sharetour.buyer.module.order.bean.OrderBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OrderListFragment extends BaseMvpFragment {
    @BindView(R.id.rv_order_list)
    RecyclerView mRecyclerView;
    private OrderListAdapter mAdapter;
    private List<OrderBean> mDatas;
    private int mOrderType;
    private static final String ORDER_TYPE = "order_type";
    private ArrayList<OrderBean.GoodsInfo> fakdeGoodsList1;
    private ArrayList<OrderBean.GoodsInfo> fakeGoodsList2;


    public static OrderListFragment getInstance(int orderType) {
        OrderListFragment fragment = new OrderListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ORDER_TYPE, orderType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mOrderType = arguments.getInt(ORDER_TYPE);
        }
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order_list;
    }

    @Override
    protected void initData(Bundle bundle) {
        if (mAdapter == null) {
            mAdapter = new OrderListAdapter(getContext());
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemViewClickListener(new OrderListAdapter.OnItemViewClickListener() {
            @Override
            public void onItemViewClick(View view, int position, OrderBean data) {
                switch (view.getId()) {
                    case R.id.iv_order_verify_video:

                        break;

                    case R.id.iv_order_contact_seller:

                        break;

                    case R.id.iv_order_contact_service:

                        break;

                    case R.id.iv_order_remind_shipping:

                        break;

                    case R.id.iv_order_delete:

                        break;

                    case R.id.iv_order_view_shipping:

                        break;

                    case R.id.iv_order_cancel:

                        break;

                    case R.id.iv_order_pay:

                        break;

                    case R.id.iv_order_confirm:

                        break;

                    case R.id.iv_order_review:
                        startActivity(OrderCommentActivity.class);
                        break;

                    default:
                        break;
                }
            }
        });
        mDatas = getFakeData();
        mAdapter.setData(mDatas);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public List<OrderBean> getFakeData() {
        OrderBean.GoodsInfo goodsInfo1 = new OrderBean.GoodsInfo("1",
                "NIKE HUARACHE DRIFT (PSE) LALALALALA",
                "黑白/36.5",
                "￥9,918.00",
                1, "");
        OrderBean.GoodsInfo goodsInfo2 = new OrderBean.GoodsInfo("2",
                "NIKE HUARACHE DRIFT (PSE) LALALALALA",
                "黑白/34",
                "￥8,918.00",
                1, "");
        fakdeGoodsList1 = new ArrayList<>();
        fakdeGoodsList1.add(goodsInfo1);
        fakeGoodsList2 = new ArrayList<>();
        fakeGoodsList2.add(goodsInfo1);
        fakeGoodsList2.add(goodsInfo2);


        List<OrderBean> fakeData = new ArrayList<>();
        switch (mOrderType) {
            case Consts.ORDER_STATE_ALL:
                fakeData.add(getFakeOrderBean1(Consts.ORDER_STATE_AWAIT_PAY));
                fakeData.add(getFakeOrderBean1(Consts.ORDER_STATE_AWAIT_CONFIRM));
                fakeData.add(getFakeOrderBean1(Consts.ORDER_STATE_AWAIT_REVIEW));
                fakeData.add(getFakeOrderBean2(Consts.ORDER_STATE_OTHER));
                fakeData.add(getFakeOrderBean1(Consts.ORDER_STATE_AWAIT_SHIPPING));
                fakeData.add(getFakeOrderBean1(Consts.ORDER_STATE_AWAIT_PAY));
                fakeData.add(getFakeOrderBean2(Consts.ORDER_STATE_AWAIT_SHIPPING));
                fakeData.add(getFakeOrderBean1(Consts.ORDER_STATE_OTHER));
                fakeData.add(getFakeOrderBean1(Consts.ORDER_STATE_AWAIT_CONFIRM));
                break;

            case Consts.ORDER_STATE_AWAIT_PAY:
            case Consts.ORDER_STATE_AWAIT_SHIPPING:
            case Consts.ORDER_STATE_AWAIT_CONFIRM:
            case Consts.ORDER_STATE_AWAIT_REVIEW:
            case Consts.ORDER_STATE_OTHER:
                fakeData.add(getFakeOrderBean1(mOrderType));
                fakeData.add(getFakeOrderBean1(mOrderType));
                fakeData.add(getFakeOrderBean2(mOrderType));
                fakeData.add(getFakeOrderBean1(mOrderType));
                fakeData.add(getFakeOrderBean2(mOrderType));
                fakeData.add(getFakeOrderBean1(mOrderType));
                break;

            default:
                break;
        }

        return fakeData;
    }

    private OrderBean getFakeOrderBean1(int orderStatus) {
        OrderBean orderBean1 = new OrderBean("1",
                orderStatus,
                "",
                "我是小桂子的桂子",
                "",
                "",
                1,
                "￥9,948.00",
                "￥30.00",
                fakdeGoodsList1);
        return orderBean1;
    }

    private OrderBean getFakeOrderBean2(int orderStatus) {
        OrderBean orderBean2 = new OrderBean("1",
                orderStatus,
                "",
                "我是小桂子的桂子",
                "",
                "",
                2,
                "￥18,866.00",
                "￥30.00",
                fakeGoodsList2);
        return orderBean2;
    }
}
