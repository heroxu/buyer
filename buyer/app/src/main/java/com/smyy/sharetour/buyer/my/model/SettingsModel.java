package com.smyy.sharetour.buyer.my.model;

import android.app.Application;

import com.smyy.sharetour.buyer.my.contract.ISettingsContract;
import com.smyy.sharetour.buyer.util.CacheUtils;

public class SettingsModel implements ISettingsContract.Model {
    @Override
    public String getCacheSize(Application application) throws Exception {
        return CacheUtils.getInternalCache(application);
    }

    @Override
    public boolean clearCache(Application application) {
        return CacheUtils.clearInternalCache(application);
    }
}
