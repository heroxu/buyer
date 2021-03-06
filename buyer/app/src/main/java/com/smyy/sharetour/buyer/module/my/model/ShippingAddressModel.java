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
                .getListValue(SPConfig.KEY_FAKE_ADDRESS, ShippingAddressBean.class);


        if (datas == null) {
            datas = new ArrayList<>();
            datas.add(new ShippingAddressBean(0, true, "张晓安0", "18612345678",
                    "广东省广州市天河区", "冼村街道", " 珠江新城华夏路8号合景国际金融广场 32层"));
            for (int i = 1; i <= 10; i++) {
                datas.add(new ShippingAddressBean(i, false, "张晓安" + i, "18612345678",
                        "广东省广州市天河区", "冼村街道", " 合景国际金融大厦32楼"));
            }
//            datas.add(new ShippingAddressBean(0, true, "张晓安0", "18612345678", "广东省广州市天河区冼村街道 珠江新城华夏路8号合景国际金融广场 32层"));
//            datas.add(new ShippingAddressBean(1, false, "张晓安1", "18612345678", "广东省广州市天河区冼村街道 合景国际金融大厦32楼"));
//            datas.add(new ShippingAddressBean(2, false, "张晓安2", "18612345678", "广东省广州市天河区冼村街道 合景国际金融大厦32楼"));
        }
        return datas;
    }

    @Override
    public boolean deleteShippingAddress(int id) {
        MyApplication application = MyApplication.getApplication();
        if (application == null) return false;

        List<ShippingAddressBean> datas = getShippingAddressList();
        if (id >= 0 && id < datas.size()) {
            datas.remove(id);
        }
        return new SharePreferenceUtil(application, SPConfig.NAME_FAKE_DATA)
                .writeListValue(SPConfig.KEY_FAKE_ADDRESS, datas);
    }

    @Override
    public boolean setDefault(int id) {
        MyApplication application = MyApplication.getApplication();
        if (application == null) return false;

        List<ShippingAddressBean> datas = getShippingAddressList();
        if (id >= 0 && id < datas.size()) {
            for (ShippingAddressBean bean :
                    datas) {
                bean.setDefault(false);
            }
            datas.get(id).setDefault(true);
        }
        return new SharePreferenceUtil(application, SPConfig.NAME_FAKE_DATA)
                .writeListValue(SPConfig.KEY_FAKE_ADDRESS, datas);
    }

    @Override
    public ShippingAddressBean getShippingAddress(int id) {
        List<ShippingAddressBean> shippingAddressList = getShippingAddressList();
        if (shippingAddressList == null) {
            return null;
        }
        return shippingAddressList.get(id);
    }

    @Override
    public boolean updateShippingAddress(int id, ShippingAddressBean shippingAddressBean) {
        MyApplication application = MyApplication.getApplication();
        if (application == null) return false;

        List<ShippingAddressBean> datas = getShippingAddressList();
        if (id >= 0 && id < datas.size()) {
            datas.set(id, shippingAddressBean);
        }
        boolean result = new SharePreferenceUtil(application, SPConfig.NAME_FAKE_DATA)
                .writeListValue(SPConfig.KEY_FAKE_ADDRESS, datas);

        if (result && shippingAddressBean.isDefault()) {
            return setDefault(id);
        }
        return result;
    }

    @Override
    public boolean addShippingAddress(ShippingAddressBean shippingAddressBean) {
        MyApplication application = MyApplication.getApplication();
        if (application == null) return false;

        List<ShippingAddressBean> datas = getShippingAddressList();
        int id = datas.size();
        shippingAddressBean.setId(id);
        datas.add(shippingAddressBean);
        boolean result = new SharePreferenceUtil(application, SPConfig.NAME_FAKE_DATA)
                .writeListValue(SPConfig.KEY_FAKE_ADDRESS, datas);

        if (result && shippingAddressBean.isDefault()) {
            return setDefault(id);
        }
        return result;
    }
}
