package com.smyy.sharetour.buyer.module.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.smyy.sharetour.buyer.MyApplication;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.event.LoginEvent;
import com.smyy.sharetour.buyer.module.my.base.MyBaseMvpActivity;
import com.smyy.sharetour.buyer.module.my.bean.ShippingAddressBean;
import com.smyy.sharetour.buyer.module.my.contract.ISettingsContract;
import com.smyy.sharetour.buyer.module.my.contract.IShippingAddressContract;
import com.smyy.sharetour.buyer.module.my.model.SettingsModel;
import com.smyy.sharetour.buyer.module.my.model.ShippingAddressModel;
import com.smyy.sharetour.buyer.module.my.presenter.SettingsPresenter;
import com.smyy.sharetour.buyer.module.my.presenter.ShippingAddressPresenter;
import com.smyy.sharetour.buyer.util.StringUtil;
import com.smyy.sharetour.buyer.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ShippingAddressEditActivity extends MyBaseMvpActivity<ShippingAddressPresenter> implements IShippingAddressContract.View {

    @BindView(R.id.et_my_shipping_name)
    EditText etName;
    @BindView(R.id.et_my_shipping_phone)
    EditText etPhone;
    @BindView(R.id.tv_my_shipping_district)
    TextView tvDistrict;
    @BindView(R.id.tv_my_shipping_street)
    TextView tvStreet;
    @BindView(R.id.et_my_shipping_address_detail)
    EditText etDetail;
    @BindView(R.id.cb_my_shipping_default)
    CheckBox cbDefault;


    public static final String PURPOSE = "purpose";
    public static final String EDIT_ADDRESS = "edit_address";
    public static final String ADD_ADDRESS = "add_address";
    private String mPurpose = ADD_ADDRESS;

    public static final String ADDRESS_ID = "address_id";
    private int mId;
    private String mShippingName;
    private String mShippingPhone;
    private String mShippingDetail;
    private String mShippingDistrict;
    private String mShippingStreet;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_shipping_address_edit;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText(getString(R.string.settings));
        TextView toolbarRightTv = getToolbarRightTv();
        toolbarRightTv.setText("保存并使用");
        toolbarRightTv.setVisibility(View.VISIBLE);
        toolbarRightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInput()) {
                    ShippingAddressBean shippingAddressBean =
                            new ShippingAddressBean(mId, cbDefault.isChecked(),
                                    mShippingName, mShippingPhone,
                                    mShippingDistrict, mShippingStreet,
                                    mShippingDetail);
                    switch (mPurpose) {
                        case EDIT_ADDRESS:
                            mPresenter.updateShippingAddress(mId, shippingAddressBean);
                            break;
                        case ADD_ADDRESS:
                            mPresenter.addShippingAddress(shippingAddressBean);
                            break;
                        default:
                            break;
                    }

                }
            }
        });
    }

    private boolean checkInput() {
        mShippingName = StringUtil.trim(etName);
        if (StringUtil.isEmpty(mShippingName)) {
            ToastUtils.showToast("姓名不能为空");
            return false;
        }
        mShippingPhone = StringUtil.trim(etPhone);
        if (StringUtil.isEmpty(mShippingPhone)) {
            ToastUtils.showToast("电话不能为空");
            return false;
        }
        mShippingDistrict = StringUtil.trim(tvDistrict);
        if (StringUtil.isEmpty(mShippingDistrict)) {
            ToastUtils.showToast("地区不能为空");
            return false;
        }
        mShippingStreet = StringUtil.trim(tvStreet);
        if (StringUtil.isEmpty(mShippingStreet)) {
            ToastUtils.showToast("街道不能为空");
            return false;
        }
        mShippingDetail = StringUtil.trim(etDetail);
        if (StringUtil.isEmpty(mShippingDetail) || mShippingDetail.length() < 5) {
            ToastUtils.showToast("详细地址不少于5个字");
            return false;
        }
        return true;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Bundle bundle = getBundle();
        if (bundle != null) {
            mPurpose = bundle.getString(PURPOSE, ADD_ADDRESS);
            mId = bundle.getInt(ADDRESS_ID);
        }

        switch (mPurpose) {
            case EDIT_ADDRESS:
                mPresenter.getShippingAddress(mId);
                break;
            default:
                break;
        }

    }

    @OnClick({R.id.lay_my_shipping_district, R.id.lay_my_shipping_street})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.lay_my_shipping_district:

                break;

            case R.id.lay_my_shipping_street:

                break;

            default:
                break;
        }
    }

    @Override
    protected ShippingAddressPresenter createPresenter() {
        return new ShippingAddressPresenter(this, new ShippingAddressModel());
    }

    @Override
    public void showShippingAddressList(List<ShippingAddressBean> datas) {

    }

    @Override
    public void shippingAddressUpdated() {
        hideProgressDialog();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void showShippingAddress(ShippingAddressBean data) {
        etName.setText(data.getName());
        etPhone.setText(data.getPhone());
        tvDistrict.setText(data.getDistrict());
        tvStreet.setText(data.getStreet());
        etDetail.setText(data.getDetailAddress());
        cbDefault.setChecked(data.isDefault());
    }

    @Override
    public void shippingAddressUpdateFail() {

    }
}
