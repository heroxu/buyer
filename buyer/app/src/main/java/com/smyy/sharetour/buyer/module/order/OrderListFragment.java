package com.smyy.sharetour.buyer.module.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.module.my.base.MyBaseMvpFragment;
import com.smyy.sharetour.buyer.module.order.adapter.OrderListAdapter;
import com.smyy.sharetour.buyer.module.order.bean.OrderBean;
import com.smyy.sharetour.buyer.module.order.bean.OrderGoodsInfo;
import com.smyy.sharetour.buyer.module.order.contract.IOrderContract;
import com.smyy.sharetour.buyer.module.order.model.OrderModel;
import com.smyy.sharetour.buyer.module.order.presenter.OrderPresenter;
import com.smyy.sharetour.uiframelib.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OrderListFragment extends MyBaseMvpFragment<OrderPresenter> implements IOrderContract.View {
    @BindView(R.id.rv_order_list)
    RecyclerView mRecyclerView;
    private OrderListAdapter mAdapter;
    private List<OrderBean> mDatas;
    private int mOrderType;
    private ArrayList<OrderGoodsInfo> fakdeGoodsList1;
    private ArrayList<OrderGoodsInfo> fakeGoodsList2;
    private ArrayList<OrderGoodsInfo> fakdeGoodsListDL;
    private int mUserType;
    private Bundle mBundle;


    public static OrderListFragment getInstance(int userType, int orderType) {
        OrderListFragment fragment = new OrderListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Consts.USER_TYPE, userType);
        bundle.putInt(OrderHelper.ORDER_TYPE, orderType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBundle = getArguments();
        if (mBundle != null) {
            mUserType = mBundle.getInt(Consts.USER_TYPE);
            mOrderType = mBundle.getInt(OrderHelper.ORDER_TYPE);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order_list;
    }

    @Override
    protected void initData(Bundle bundle) {
        if (mAdapter == null) {
            mAdapter = new OrderListAdapter((BaseActivity) getActivity(), mUserType, mPresenter);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OrderListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, OrderBean data) {
                if (mBundle == null) {
                    mBundle = new Bundle();
                }
                mBundle.putString(OrderHelper.ORDER_ID, data.getOrderId());
                mBundle.putSerializable(OrderDetailActivity.FAKE_DATA, data);
                startActivity(OrderDetailActivity.class, mBundle);
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
                1, "", OrderHelper.GOODS_TYPE_STOCK, null);
        OrderGoodsInfo goodsInfo2 = new OrderGoodsInfo("2",
                "NIKE HUARACHE DRIFT (PSE) LALALALALA",
                "黑白/34",
                "",
                "￥8,918.00",
                1, "", OrderHelper.GOODS_TYPE_PRESELL, null);
        OrderGoodsInfo goodsInfoDL = new OrderGoodsInfo("2",
                "NIKE HUARACHE DRIFT (PSE) LALALALALA",
                "",
                "2018-05-01",
                "￥9,918.00",
                1, "", OrderHelper.GOODS_TYPE_DEMAND, null);
        fakdeGoodsList1 = new ArrayList<>();
        fakdeGoodsList1.add(goodsInfo1);
        fakeGoodsList2 = new ArrayList<>();
        fakeGoodsList2.add(goodsInfo1);
        fakeGoodsList2.add(goodsInfo2);
        fakdeGoodsListDL = new ArrayList<>();
        fakdeGoodsListDL.add(goodsInfoDL);


        List<OrderBean> fakeData = new ArrayList<>();
        switch (mUserType) {
            case Consts.USER_TYPE_BUYER:
                switch (mOrderType) {
                    case OrderHelper.TYPE_ALL:
                        fakeData.add(getFakeOrderBean1(OrderHelper.STATUS_BUYER_AWAIT_PAY));
                        fakeData.add(getFakeOrderBean1(OrderHelper.STATUS_BUYER_AWAIT_CONFIRM));
                        fakeData.add(getFakeOrderBean1(OrderHelper.STATUS_BUYER_AWAIT_REVIEW));
                        fakeData.add(getFakeOrderBean2(OrderHelper.STATUS_BUYER_ADD_REVIEW));
                        fakeData.add(getFakeOrderBean2(OrderHelper.STATUS_BUYER_AWAIT_SHIPPING));
                        fakeData.add(getFakeOrderBean1(OrderHelper.STATUS_BUYER_AWAIT_PAY));
                        fakeData.add(getFakeOrderBean2(OrderHelper.STATUS_BUYER_DISPUTE_SUCCESS));
                        fakeData.add(getFakeOrderBean1(OrderHelper.STATUS_BUYER_DUR_DISPUTE));
                        fakeData.add(getFakeOrderBean1(OrderHelper.STATUS_BUYER_AWAIT_CONFIRM));
                        fakeData.add(getFakeOrderBean1(OrderHelper.STATUS_BUYER_CLOSED));
                        break;

                    case OrderHelper.TYPE_AWAIT_PAY:
                        fakeData.add(getFakeOrderBean1(OrderHelper.STATUS_BUYER_AWAIT_PAY));
                        break;
                    case OrderHelper.TYPE_AWAIT_SHIPPING:
                        fakeData.add(getFakeOrderBean2(OrderHelper.STATUS_BUYER_AWAIT_SHIPPING));
                        break;
                    case OrderHelper.TYPE_AWAIT_CONFIRM:
                        fakeData.add(getFakeOrderBean1(OrderHelper.STATUS_BUYER_AWAIT_CONFIRM));
                        fakeData.add(getFakeOrderBean2(OrderHelper.STATUS_BUYER_AWAIT_CONFIRM));
                        break;
                    case OrderHelper.TYPE_AWAIT_REVIEW:
                        fakeData.add(getFakeOrderBean1(OrderHelper.STATUS_BUYER_AWAIT_REVIEW));
                        fakeData.add(getFakeOrderBean1(OrderHelper.STATUS_BUYER_AWAIT_REVIEW));
                        fakeData.add(new OrderBean("1",
                                OrderHelper.STATUS_BUYER_AWAIT_REVIEW,
                                "",
                                "我是小桂子的桂子",
                                "",
                                "",
                                "我是小桂子",
                                "",
                                1,
                                "￥9,948.00",
                                "￥30.00",
                                "",
                                OrderHelper.GOODS_TYPE_DEMAND,
                                fakdeGoodsListDL));
                        break;

                    default:
                        break;
                }
                break;
            case Consts.USER_TYPE_BACK_PACKER:
                switch (mOrderType) {
                    case OrderHelper.TYPE_ALL:
                        fakeData.add(getFakeOrderBeanDL(OrderHelper.STATUS_SELLER_SHIPPED));
                        fakeData.add(getFakeOrderBeanDL(OrderHelper.STATUS_SELLER_AWAIT_PAY));
                        fakeData.add(getFakeOrderBeanDL(OrderHelper.STATUS_SELLER_DONE));
                        fakeData.add(getFakeOrderBeanDL(OrderHelper.STATUS_SELLER_DUR_DISPUTE));
                        fakeData.add(getFakeOrderBeanDL(OrderHelper.STATUS_SELLER_DISPUTE_SUCCESS));
                        fakeData.add(getFakeOrderBeanDL(OrderHelper.STATUS_SELLER_AWAIT_SHIPPING));
                        fakeData.add(getFakeOrderBeanDL(OrderHelper.STATUS_SELLER_AWAIT_PAY));
                        fakeData.add(getFakeOrderBeanDL(OrderHelper.STATUS_SELLER_AWAIT_SHIPPING));
                        fakeData.add(getFakeOrderBeanDL(OrderHelper.STATUS_SELLER_CLOSED));
                        fakeData.add(getFakeOrderBeanDL(OrderHelper.STATUS_SELLER_DUR_DISPUTE));
                        break;

                    case OrderHelper.TYPE_AWAIT_SHIPPING:
                        fakeData.add(getFakeOrderBeanDL(OrderHelper.STATUS_SELLER_AWAIT_SHIPPING));
                        fakeData.add(getFakeOrderBeanDL(OrderHelper.STATUS_SELLER_AWAIT_SHIPPING));
                        fakeData.add(getFakeOrderBeanDL(OrderHelper.STATUS_SELLER_AWAIT_SHIPPING));
                        break;

                    case OrderHelper.TYPE_AWAIT_CONFIRM:
                        fakeData.add(getFakeOrderBeanDL(OrderHelper.STATUS_SELLER_SHIPPED));
                        fakeData.add(getFakeOrderBeanDL(OrderHelper.STATUS_SELLER_SHIPPED));
                        break;

                    default:
                        break;
                }
                break;
            case Consts.USER_TYPE_SELLER:
                switch (mOrderType) {
                    case OrderHelper.TYPE_ALL:
                        fakeData.add(getFakeOrderBean1(OrderHelper.STATUS_SELLER_SHIPPED));
                        fakeData.add(getFakeOrderBean1(OrderHelper.STATUS_SELLER_AWAIT_PAY));
                        fakeData.add(getFakeOrderBean1(OrderHelper.STATUS_SELLER_DUR_DISPUTE));
                        fakeData.add(getFakeOrderBean1(OrderHelper.STATUS_SELLER_DONE));
                        fakeData.add(getFakeOrderBean2(OrderHelper.STATUS_SELLER_DISPUTE_SUCCESS));
                        fakeData.add(getFakeOrderBean1(OrderHelper.STATUS_SELLER_AWAIT_SHIPPING));
                        fakeData.add(getFakeOrderBean1(OrderHelper.STATUS_SELLER_AWAIT_PAY));
                        fakeData.add(getFakeOrderBean2(OrderHelper.STATUS_SELLER_AWAIT_SHIPPING));
                        fakeData.add(getFakeOrderBean1(OrderHelper.STATUS_SELLER_CLOSED));
                        fakeData.add(getFakeOrderBean1(OrderHelper.STATUS_SELLER_DUR_DISPUTE));
                        break;

                    case OrderHelper.TYPE_AWAIT_PAY:
                        fakeData.add(getFakeOrderBean1(OrderHelper.STATUS_SELLER_AWAIT_PAY));
                        break;

                    case OrderHelper.TYPE_AWAIT_SHIPPING:
                        fakeData.add(getFakeOrderBean2(OrderHelper.STATUS_SELLER_AWAIT_SHIPPING));
                        break;

                    case OrderHelper.TYPE_AWAIT_CONFIRM:
                        fakeData.add(getFakeOrderBean1(OrderHelper.STATUS_SELLER_SHIPPED));
                        fakeData.add(getFakeOrderBean2(OrderHelper.STATUS_SELLER_SHIPPED));
                        break;

                    default:
                        break;
                }
                break;
            default:
                break;
        }

        return fakeData;
    }

    private OrderBean getFakeOrderBean1(int orderStatus) {
        switch (orderStatus) {
            case OrderHelper.STATUS_BUYER_DUR_DISPUTE:
            case OrderHelper.STATUS_BUYER_DISPUTE_SUCCESS:
            case OrderHelper.STATUS_SELLER_DUR_DISPUTE:
            case OrderHelper.STATUS_SELLER_DISPUTE_SUCCESS:
                for (OrderGoodsInfo goodsInfo :
                        fakdeGoodsList1) {
                    goodsInfo.setDisputeOrderId("201803071438023384");
                }
                break;
            default:
                for (OrderGoodsInfo goodsInfo :
                        fakdeGoodsList1) {
                    goodsInfo.setDisputeOrderId("");
                }
                break;
        }
        OrderBean orderBean1 = new OrderBean("1",
                orderStatus,
                "",
                "我是小桂子的桂子",
                "",
                "",
                "我是小桂子",
                "",
                1,
                "￥9,948.00",
                "￥30.00",
                "",
                OrderHelper.GOODS_TYPE_STOCK,
                fakdeGoodsList1);
        return orderBean1;
    }

    private OrderBean getFakeOrderBean2(int orderStatus) {
        switch (orderStatus) {
            case OrderHelper.STATUS_BUYER_DUR_DISPUTE:
            case OrderHelper.STATUS_BUYER_DISPUTE_SUCCESS:
            case OrderHelper.STATUS_SELLER_DUR_DISPUTE:
            case OrderHelper.STATUS_SELLER_DISPUTE_SUCCESS:
                for (OrderGoodsInfo goodsInfo :
                        fakeGoodsList2) {
                    goodsInfo.setDisputeOrderId("201803071438023384");
                }
                break;
            default:
                for (OrderGoodsInfo goodsInfo :
                        fakeGoodsList2) {
                    goodsInfo.setDisputeOrderId("");
                }
                break;
        }
        OrderBean orderBean2 = new OrderBean("1",
                orderStatus,
                "",
                "我是小桂子的桂子",
                "",
                "",
                "我是小桂子",
                "",
                2,
                "￥18,866.00",
                "￥30.00",
                "",
                OrderHelper.GOODS_TYPE_STOCK,
                fakeGoodsList2);
        return orderBean2;
    }

    private OrderBean getFakeOrderBeanDL(int orderStatus) {
        switch (orderStatus) {
            case OrderHelper.STATUS_BUYER_DUR_DISPUTE:
            case OrderHelper.STATUS_BUYER_DISPUTE_SUCCESS:
            case OrderHelper.STATUS_SELLER_DUR_DISPUTE:
            case OrderHelper.STATUS_SELLER_DISPUTE_SUCCESS:
                for (OrderGoodsInfo goodsInfo :
                        fakdeGoodsListDL) {
                    goodsInfo.setDisputeOrderId("201803071438023384");
                }
                break;
            default:
                for (OrderGoodsInfo goodsInfo :
                        fakdeGoodsListDL) {
                    goodsInfo.setDisputeOrderId("");
                }
                break;
        }
        OrderBean orderBeanDL = new OrderBean("1",
                orderStatus,
                "",
                "我是小桂子的桂子",
                "",
                "",
                "我是小桂子",
                "",
                1,
                "￥9,948.00",
                "￥30.00",
                "",
                OrderHelper.GOODS_TYPE_DEMAND,
                fakdeGoodsListDL);
        return orderBeanDL;
    }

    @Override
    protected OrderPresenter createPresenter() {
        return new OrderPresenter(this, new OrderModel());
    }

    @Override
    public void finish() {
        mActivity.finish();
    }
}
