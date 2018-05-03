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
import com.smyy.sharetour.buyer.module.order.adapter.DisputeOrderListAdapter;
import com.smyy.sharetour.buyer.module.order.bean.DisputeOrderBean;
import com.smyy.sharetour.uiframelib.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DisputeOrderListFragment extends BaseMvpFragment {
    @BindView(R.id.rv_order_list)
    RecyclerView mRecyclerView;
    private DisputeOrderListAdapter mAdapter;
    private List<DisputeOrderBean> mDatas;
    private int mUserType;
    private Bundle mBundle;


    public static DisputeOrderListFragment getInstance(int userType) {
        DisputeOrderListFragment fragment = new DisputeOrderListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Consts.USER_TYPE, userType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBundle = getArguments();
        if (mBundle != null) {
            mUserType = mBundle.getInt(Consts.USER_TYPE);
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
            mAdapter = new DisputeOrderListAdapter((BaseActivity) getActivity(), mUserType);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new DisputeOrderListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, DisputeOrderBean data) {
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

    public List<DisputeOrderBean> getFakeData() {
        DisputeOrderBean goodsInfo1 = new DisputeOrderBean("1",
                OrderHelper.STATUS_BUYER_DUR_DISPUTE,
                "",
                "我是小桂子的桂子",
                "",
                "",
                "我是小桂子",
                "",
                "",
                "NIKE HUARACHE DRIFT (PSE) LALALALALA",
                "黑白/36.5",
                "",
                "￥9,918.00",
                1, "", OrderHelper.GOODS_TYPE_STOCK);

        DisputeOrderBean goodsInfo2 = new DisputeOrderBean("2",
                OrderHelper.STATUS_BUYER_DISPUTE_SUCCESS,
                "",
                "我是小桂子的桂子",
                "",
                "",
                "我是小桂子",
                "",
                "",
                "NIKE HUARACHE DRIFT (PSE) LALALALALA",
                "黑白/36.5",
                "",
                "￥9,918.00",
                1, "", OrderHelper.GOODS_TYPE_STOCK);

        DisputeOrderBean goodsInfoDL = new DisputeOrderBean("3",
                OrderHelper.STATUS_BUYER_DISPUTE_SUCCESS,
                "",
                "我是小桂子的桂子",
                "",
                "",
                "我是小桂子",
                "",
                "",
                "NIKE HUARACHE DRIFT (PSE) LALALALALA",
                "",
                "2018-05-01",
                "￥9,918.00",
                1, "", OrderHelper.GOODS_TYPE_DEMAND);

        DisputeOrderBean goodsInfoDL1 = new DisputeOrderBean("3",
                OrderHelper.STATUS_BUYER_DUR_DISPUTE,
                "",
                "我是小桂子的桂子",
                "",
                "",
                "我是小桂子",
                "",
                "",
                "NIKE HUARACHE DRIFT (PSE) LALALALALA",
                "",
                "2018-05-01",
                "￥9,918.00",
                1, "", OrderHelper.GOODS_TYPE_DEMAND);


        List<DisputeOrderBean> fakeData = new ArrayList<>();
        if (mUserType == Consts.USER_TYPE_BACK_PACKER) {
            fakeData.add(goodsInfoDL);
            fakeData.add(goodsInfoDL1);
        } else {
            fakeData.add(goodsInfo1);
            fakeData.add(goodsInfo2);
            fakeData.add(goodsInfoDL);
            fakeData.add(goodsInfoDL1);
        }

        return fakeData;
    }


}
