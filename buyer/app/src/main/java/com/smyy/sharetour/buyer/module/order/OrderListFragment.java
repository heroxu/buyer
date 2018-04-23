package com.smyy.sharetour.buyer.module.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.module.my.bean.ShippingAddressBean;
import com.smyy.sharetour.buyer.module.order.adapter.OrderListAdapter;
import com.smyy.sharetour.buyer.module.order.bean.OrderBean;
import com.smyy.sharetour.buyer.module.order.bean.OrderGoodsInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OrderListFragment extends BaseMvpFragment {
    @BindView(R.id.rv_order_list)
    RecyclerView mRecyclerView;
    private OrderListAdapter mAdapter;
    private List<OrderBean> mDatas;
    private int mOrderType;
    private ArrayList<OrderGoodsInfo> fakdeGoodsList1;
    private ArrayList<OrderGoodsInfo> fakeGoodsList2;


    public static OrderListFragment getInstance(int orderType) {
        OrderListFragment fragment = new OrderListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Consts.ORDER_TYPE, orderType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mOrderType = arguments.getInt(Consts.ORDER_TYPE);
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

        mAdapter.setOnItemClickListener(new OrderListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, OrderBean data) {
                Bundle bundle = new Bundle();
                bundle.putString(Consts.ORDER_ID, data.getOrderId());
                bundle.putSerializable(OrderDetailActivity.FAKE_DATA, data);
                startActivity(OrderDetailActivity.class, bundle);
            }
        });

        mAdapter.setOnItemViewClickListener(new OrderListAdapter.OnItemViewClickListener() {
            @Override
            public void onItemViewClick(View view, int position, OrderBean data) {
                Bundle bundle = new Bundle();
                switch (view.getId()) {

                    case R.id.tv_order_verify_video:

                        break;

                    case R.id.tv_order_contact_seller:

                        break;

                    case R.id.tv_order_contact_service:

                        break;

                    case R.id.tv_order_remind_shipping:

                        break;

                    case R.id.tv_order_delete:

                        break;

                    case R.id.tv_order_view_shipping:

                        break;

                    case R.id.tv_order_cancel:

                        break;

                    case R.id.tv_order_pay:

                        break;

                    case R.id.tv_order_confirm:

                        break;

                    case R.id.tv_order_review:
                        bundle.putString(Consts.ORDER_ID, data.getOrderId());
                        startActivity(OrderCommentActivity.class, bundle);
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
        OrderGoodsInfo goodsInfo1 = new OrderGoodsInfo("1",
                "NIKE HUARACHE DRIFT (PSE) LALALALALA",
                "黑白/36.5",
                "",
                "￥9,918.00",
                1, "");
        OrderGoodsInfo goodsInfo2 = new OrderGoodsInfo("2",
                "NIKE HUARACHE DRIFT (PSE) LALALALALA",
                "黑白/34",
                "",
                "￥8,918.00",
                1, "");
        OrderGoodsInfo goodsInfoDL = new OrderGoodsInfo("2",
                "NIKE HUARACHE DRIFT (PSE) LALALALALA",
                "",
                "2018-05-01",
                "￥9,918.00",
                1, "");
        fakdeGoodsList1 = new ArrayList<>();
        fakdeGoodsList1.add(goodsInfo1);
        fakeGoodsList2 = new ArrayList<>();
        fakeGoodsList2.add(goodsInfo1);
        fakeGoodsList2.add(goodsInfo2);
        List<OrderGoodsInfo> fakdeGoodsListDL = new ArrayList<>();
        fakdeGoodsListDL.add(goodsInfoDL);


        List<OrderBean> fakeData = new ArrayList<>();
        switch (mOrderType) {
            case Consts.ORDER_TYPE_ALL:
                fakeData.add(getFakeOrderBean1(Consts.ORDER_STATUS_AWAIT_PAY));
                fakeData.add(getFakeOrderBean1(Consts.ORDER_STATUS_AWAIT_CONFIRM));
                fakeData.add(getFakeOrderBean1(Consts.ORDER_STATUS_AWAIT_REVIEW));
                fakeData.add(getFakeOrderBean2(Consts.ORDER_STATUS_OTHER));
                fakeData.add(getFakeOrderBean1(Consts.ORDER_STATUS_AWAIT_SHIPPING));
                fakeData.add(getFakeOrderBean1(Consts.ORDER_STATUS_AWAIT_PAY));
                fakeData.add(getFakeOrderBean2(Consts.ORDER_STATUS_AWAIT_SHIPPING));
                fakeData.add(getFakeOrderBean1(Consts.ORDER_STATUS_OTHER));
                fakeData.add(getFakeOrderBean1(Consts.ORDER_STATUS_AWAIT_CONFIRM));
                break;

            case Consts.ORDER_TYPE_AWAIT_PAY:
                fakeData.add(getFakeOrderBean1(Consts.ORDER_STATUS_AWAIT_PAY));
                break;
            case Consts.ORDER_TYPE_AWAIT_SHIPPING:
                fakeData.add(getFakeOrderBean2(Consts.ORDER_STATUS_AWAIT_SHIPPING));
                break;
            case Consts.ORDER_TYPE_AWAIT_CONFIRM:
                fakeData.add(getFakeOrderBean1(Consts.ORDER_STATUS_AWAIT_CONFIRM));
                fakeData.add(getFakeOrderBean2(Consts.ORDER_STATUS_AWAIT_CONFIRM));
                break;
            case Consts.ORDER_TYPE_AWAIT_REVIEW:
                fakeData.add(getFakeOrderBean1(Consts.ORDER_STATUS_AWAIT_REVIEW));
                fakeData.add(getFakeOrderBean1(Consts.ORDER_STATUS_AWAIT_REVIEW));
                fakeData.add(new OrderBean("1",
                        Consts.ORDER_STATUS_AWAIT_REVIEW,
                        "",
                        "我是小桂子的桂子",
                        "",
                        "",
                        1,
                        "￥9,948.00",
                        "￥30.00",
                        fakdeGoodsListDL));
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
