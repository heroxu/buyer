package com.smyy.sharetour.buyer.module.my.model;

import com.smyy.sharetour.buyer.MyApplication;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.SPConfig;
import com.smyy.sharetour.buyer.module.my.bean.BankcardBean;
import com.smyy.sharetour.buyer.module.my.bean.ShippingAddressBean;
import com.smyy.sharetour.buyer.module.my.contract.IBankcardContract;
import com.smyy.sharetour.buyer.module.my.contract.IShippingAddressContract;
import com.smyy.sharetour.buyer.util.SharePreferenceUtil;

import java.util.ArrayList;
import java.util.List;

public class BankcardModel implements IBankcardContract.Model {

    @Override
    public List<BankcardBean> getBankcardList() {
        MyApplication application = MyApplication.getApplication();
        if (application == null) return null;

        List<BankcardBean> datas = new SharePreferenceUtil(application, SPConfig.NAME_FAKE_DATA)
                .getListValue(SPConfig.KEY_FAKE_ADDRESS, BankcardBean.class);


        if (datas == null) {
            datas = new ArrayList<>();

            datas.add(new BankcardBean(1, R.drawable.fake_bg1, R.mipmap.fake_logo1, "招商银行", "储蓄卡", "**** **** **** 1234", "95555"));
            datas.add(new BankcardBean(2, R.drawable.fake_bg2, R.mipmap.fake_logo2, "华夏银行", "储蓄卡", "**** **** **** 1234", "95577"));
            datas.add(new BankcardBean(3, R.drawable.fake_bg3, R.mipmap.fake_logo3, "建设银行", "储蓄卡", "**** **** **** 1234", "95533"));
        }
        return datas;
    }

    @Override
    public boolean deleteBankcard(int id) {
        MyApplication application = MyApplication.getApplication();
        if (application == null) return false;

        List<BankcardBean> datas = getBankcardList();
        if (id >= 0 && id < datas.size()) {
            datas.remove(id);
        }
        return new SharePreferenceUtil(application, SPConfig.NAME_FAKE_DATA)
                .writeListValue(SPConfig.KEY_FAKE_ADDRESS, datas);
    }

    @Override
    public boolean deleteBankcard(BankcardBean bankcardBean) {
        MyApplication application = MyApplication.getApplication();
        if (application == null) return false;

        List<BankcardBean> datas = getBankcardList();
        datas.remove(bankcardBean);
        return new SharePreferenceUtil(application, SPConfig.NAME_FAKE_DATA)
                .writeListValue(SPConfig.KEY_FAKE_ADDRESS, datas);
    }
}
