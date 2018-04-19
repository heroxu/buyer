package com.smyy.sharetour.buyer.module.my.model;

import com.smyy.sharetour.buyer.MyApplication;
import com.smyy.sharetour.buyer.SPConfig;
import com.smyy.sharetour.buyer.module.my.bean.ShippingAddressBean;
import com.smyy.sharetour.buyer.module.my.contract.IShippingAddressContract;
import com.smyy.sharetour.buyer.util.SharePreferenceUtil;

import java.util.ArrayList;
import java.util.List;

public class ShippingAddressModel implements IShippingAddressContract.Model {

    @Override
    public List<ShippingAddressBean> getShippingAddressList() {
        MyApplication application = MyApplication.getApplication();
        if (application == null) return null;

        List<ShippingAddressBean> datas = new SharePreferenceUtil(application, SPConfig.NAME_FAKE_DATA)
                .getListValue(SPConfig.KEY_FAKE_ADDRESS);

        if (datas == null) {
            datas = new ArrayList<>();
            datas.add(new ShippingAddressBean(0, true, "张晓安", "18612345678", "广东省广州市天河区冼村街道 珠江新城华夏路8号合景国际金融广场 32层"));
            datas.add(new ShippingAddressBean(1, false, "张晓安", "18612345678", "广东省广州市天河区冼村街道 合景国际金融大厦32楼"));
            datas.add(new ShippingAddressBean(2, false, "张晓安", "18612345678", "广东省广州市天河区冼村街道 合景国际金融大厦32楼"));
        }
        return datas;
    }
}
