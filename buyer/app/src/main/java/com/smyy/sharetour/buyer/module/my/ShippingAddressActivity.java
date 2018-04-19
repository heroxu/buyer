package com.smyy.sharetour.buyer.module.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.module.my.adapter.ShippingAddressAdapter;
import com.smyy.sharetour.buyer.module.my.base.MyBaseMvpActivity;
import com.smyy.sharetour.buyer.module.my.bean.ShippingAddressBean;
import com.smyy.sharetour.buyer.module.my.contract.IShippingAddressContract;
import com.smyy.sharetour.buyer.module.my.model.ShippingAddressModel;
import com.smyy.sharetour.buyer.module.my.presenter.ShippingAddressPresenter;

import java.util.List;

import butterknife.BindView;

public class ShippingAddressActivity extends MyBaseMvpActivity<ShippingAddressPresenter> implements IShippingAddressContract.View {
    public static final String PURPOSE = "purpose";
    public static final String SELECT_ADDRESS = "select_address";
    public static final String SHIPPING_ADDRESS = "shipping_address";
    private String mPurpose;

    public static final int REQ_MANAGE = 1;

    @BindView(R.id.rv_my_shipping_address)
    RecyclerView mRecyclerView;

    private ShippingAddressAdapter mAdapter;
    private List<ShippingAddressBean> mDatas;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_shipping_address;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("收货地址");
        TextView toolbarRightTv = getToolbarRightTv();
        toolbarRightTv.setText("管理");
        toolbarRightTv.setTextColor(getResources().getColor(R.color.txt_gray_dark));
        toolbarRightTv.setVisibility(View.VISIBLE);
        toolbarRightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(ManageShippingAddressActivity.class, REQ_MANAGE);
            }
        });
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        Bundle bundle = getBundle();
        if (bundle != null) {
            mPurpose = bundle.getString(PURPOSE);
        }

        initView();

        mPresenter.getShippingAddressList();
    }

    private void initView() {
        if (mAdapter == null) {
            mAdapter = new ShippingAddressAdapter(this);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ShippingAddressAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, ShippingAddressBean data) {
                if (data != null && TextUtils.equals(mPurpose, SELECT_ADDRESS)) {
                    Intent intent = new Intent();
                    intent.putExtra(SHIPPING_ADDRESS, data);
                    setResult(RESULT_OK, intent);
                }
            }
        });
    }

    @Override
    protected ShippingAddressPresenter createPresenter() {
        return new ShippingAddressPresenter(this, new ShippingAddressModel());
    }

    @Override
    public void showShippingAddress(List<ShippingAddressBean> datas) {
        this.mDatas = datas;
        mAdapter.setData(datas);
    }

    @Override
    public void shippingAddressUndated() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQ_MANAGE:
                    mPresenter.getShippingAddressList();
                    break;
                default:
                    break;
            }
        }
    }
}
