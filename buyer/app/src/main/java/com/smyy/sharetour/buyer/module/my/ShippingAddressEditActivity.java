package com.smyy.sharetour.buyer.module.my;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.module.my.base.MyBaseMvpActivity;
import com.smyy.sharetour.buyer.module.my.bean.DistrictJsonBean;
import com.smyy.sharetour.buyer.module.my.bean.ShippingAddressBean;
import com.smyy.sharetour.buyer.module.my.contract.IShippingAddressContract;
import com.smyy.sharetour.buyer.module.my.model.ShippingAddressModel;
import com.smyy.sharetour.buyer.module.my.presenter.ShippingAddressPresenter;
import com.smyy.sharetour.buyer.util.GetJsonDataUtil;
import com.smyy.sharetour.buyer.util.StringUtil;
import com.smyy.sharetour.buyer.util.ToastUtils;

import org.json.JSONArray;

import java.util.ArrayList;
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

    private boolean isLoaded = false;


    private ArrayList<DistrictJsonBean> provinceItems = new ArrayList<>();
    private ArrayList<ArrayList<String>> cityItems = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> districtItems = new ArrayList<>();
    private ArrayList<String> streetItems = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;

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


        mHandler.sendEmptyMessage(MSG_LOAD_DATA);

    }

    @OnClick({R.id.lay_my_shipping_district, R.id.lay_my_shipping_street})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.lay_my_shipping_district:

                if (isLoaded) {
                    showDistrictPickerView();
                } else {
                    Toast.makeText(ShippingAddressEditActivity.this, "Please waiting until the data is parsed", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.lay_my_shipping_street:

                if (isLoaded) {
                    showStreetPickerView();
                } else {
                    Toast.makeText(ShippingAddressEditActivity.this, "Please waiting until the data is parsed", Toast.LENGTH_SHORT).show();
                }
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


    private void showDistrictPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = provinceItems.get(options1).getPickerViewText() +
                        cityItems.get(options1).get(options2) +
                        districtItems.get(options1).get(options2).get(options3);
                tvDistrict.setText(tx);
            }
        })
                .setOutSideCancelable(false)
                .setCancelColor(getResources().getColor(R.color.txt_gray_dark))
                .setSubmitColor(getResources().getColor(R.color.txt_price))
                .setSubCalSize(15)
                .setTitleText("")
                .setTitleSize(15)
                .setDividerColor(Color.parseColor("#676767"))
                .setTextColorCenter(getResources().getColor(R.color.txt_main)) //设置选中项文字颜色
                .setContentTextSize(15)
                .setTitleBgColor(Color.WHITE)
                .setBgColor(getResources().getColor(R.color.window_background))
                .build();
        pvOptions.setPicker(provinceItems, cityItems, districtItems);//三级选择器
        pvOptions.show();
    }


    private void showStreetPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = streetItems.get(options1);
                tvStreet.setText(tx);
            }
        })
                .setOutSideCancelable(false)
                .setCancelColor(getResources().getColor(R.color.txt_gray_dark))
                .setSubmitColor(getResources().getColor(R.color.txt_price))
                .setSubCalSize(15)
                .setTitleText("")
                .setTitleSize(15)
                .setDividerColor(Color.parseColor("#676767"))
                .setTextColorCenter(getResources().getColor(R.color.txt_main)) //设置选中项文字颜色
                .setContentTextSize(15)
                .setTitleBgColor(Color.WHITE)
                .setBgColor(getResources().getColor(R.color.window_background))
                .build();
        pvOptions.setPicker(streetItems);
        pvOptions.show();
    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String DistrictJsonData = new GetJsonDataUtil().getJson(this, "districts.json");//获取assets目录下的json文件数据

        ArrayList<DistrictJsonBean> districtJsonBean = parseDistrictData(DistrictJsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        provinceItems = districtJsonBean;

        for (int i = 0; i < districtJsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < districtJsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = districtJsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (districtJsonBean.get(i).getCityList().get(c).getArea() == null
                        || districtJsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(districtJsonBean.get(i).getCityList().get(c).getArea());
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            cityItems.add(CityList);

            /**
             * 添加地区数据
             */
            districtItems.add(Province_AreaList);
        }


        //RTRT将来从后台获取
        String StreetJsonData = new GetJsonDataUtil().getJson(this, "streets.json");//获取assets目录下的json文件数据
        streetItems = parseStreetData(StreetJsonData);//用Gson 转成实体
        //RTRT将来从后台获取

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }


    public ArrayList<DistrictJsonBean> parseDistrictData(String result) {//Gson 解析
        ArrayList<DistrictJsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                DistrictJsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), DistrictJsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }


    public ArrayList<String> parseStreetData(String result) {//Gson 解析
        ArrayList<String> detail = new ArrayList<>();
        try {
            Gson gson = new Gson();
            JsonArray arry = new JsonParser().parse(result).getAsJsonArray();
            for (JsonElement jsonElement : arry) {
                detail.add(gson.fromJson(jsonElement, String.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 子线程中解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS:
                    isLoaded = true;
                    break;

                case MSG_LOAD_FAILED:
                    break;
            }
        }
    };
}
