package com.smyy.sharetour.buyer.dialog;


import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hasee on 2018/5/2.
 */

public class AreaBean implements MultiItemEntity {
    private boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    private String countries;
    private List<Region> mRegions;

    public AreaBean(String countries) {
        this.countries = countries;
        this.mRegions = new ArrayList<>();
        this.mRegions.add(new Region("全部"));
        this.mRegions.add(new Region("日本"));
        this.mRegions.add(new Region("韩国"));
        this.mRegions.add(new Region("泰国"));
        this.mRegions.add(new Region("马来西亚"));
        this.mRegions.add(new Region("新加坡"));
    }

    public String getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public List<Region> getmRegions() {
        return mRegions;
    }

    public void setmRegions(List<Region> mRegions) {
        this.mRegions = mRegions;
    }

    public static class Region {
        public Region(boolean isCheck, String mRegion) {
            this.isCheck = isCheck;
            this.mRegion = mRegion;
        }

        private boolean isCheck;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        private String mRegion;

        public String getmRegion() {
            return mRegion;
        }

        public void setmRegion(String mRegion) {
            this.mRegion = mRegion;
        }

        public Region(String mRegion) {
            this.mRegion = mRegion;
        }
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
