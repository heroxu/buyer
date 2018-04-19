package com.smyy.sharetour.buyer.module.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.module.my.adapter.ManageShippingAddressAdapter;
import com.smyy.sharetour.buyer.module.my.base.MyBaseMvpActivity;
import com.smyy.sharetour.buyer.module.my.base.MyBasePresenter;
import com.smyy.sharetour.buyer.module.my.bean.ShippingAddressBean;
import com.smyy.sharetour.buyer.module.my.contract.IShippingAddressContract;
import com.smyy.sharetour.buyer.module.my.model.ShippingAddressModel;
import com.smyy.sharetour.buyer.module.my.presenter.ShippingAddressPresenter;
import com.smyy.sharetour.buyer.util.ToastUtils;

import java.util.List;

import butterknife.BindView;

public class ManageShippingAddressActivity extends MyBaseMvpActivity<ShippingAddressPresenter> implements IShippingAddressContract.View {
    @BindView(R.id.rv_my_shipping_address)
    RecyclerView mRecyclerView;
    @BindView(R.id.btn_my_add_address)
    Button btnAddAddress;

    private ManageShippingAddressAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_manage_shipping_address;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("管理收货地址");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        initView();

        mPresenter.getShippingAddressList();
    }

    private void initView() {
        if (mAdapter == null) {
            mAdapter = new ManageShippingAddressAdapter(this);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemViewClickListener(new ManageShippingAddressAdapter.OnItemViewClickListener() {
            @Override
            public void onItemViewClick(View view, int position, ShippingAddressBean data) {
                switch (view.getId()) {
                    case R.id.rb_my_shipping_default:
                        if (!data.isDefault()) {
                            List<ShippingAddressBean> list = mAdapter.getList();
                            for (int i = 0; i < list.size(); i++) {
                                list.get(i).setDefault(false);
                            }
                            data.setDefault(true);
                            mAdapter.notifyDataSetChanged();
                        }

                        break;
                    case R.id.tv_my_shipping_edit:
                        ToastUtils.showToast("edit");
                        break;
                    case R.id.tv_my_shipping_delete:
                        ToastUtils.showToast("delete");
                        break;
                    default:
                        break;
                }
            }
        });

        mAdapter.setOnDefaultChangedListener(new ManageShippingAddressAdapter.OnDefaultChangedListener() {
            @Override
            public void onDefaultChanged(View view, int position, ShippingAddressBean data) {
                List datas = mAdapter.getList();
            }
        });
    }

    @Override
    protected ShippingAddressPresenter createPresenter() {
        return new ShippingAddressPresenter(this, new ShippingAddressModel());
    }

    @Override
    public void showShippingAddress(List<ShippingAddressBean> datas) {
        mAdapter.setData(datas);
    }
}
