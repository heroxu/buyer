package com.smyy.sharetour.buyer.dialog;


import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by hasee on 2018/5/2.
 */

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
