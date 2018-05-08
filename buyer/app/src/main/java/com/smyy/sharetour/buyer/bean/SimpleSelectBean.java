package com.smyy.sharetour.buyer.bean;


import com.chad.library.adapter.base.entity.MultiItemEntity;

public class SimpleSelectBean implements MultiItemEntity {
    private boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    private String option;

    public SimpleSelectBean(String option, boolean isCheck) {
        this.isCheck = isCheck;
        this.option = option;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
