package com.smyy.sharetour.buyer.home.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * create by xuxiarong on 2018/4/17
 */
public class SearchBuyer implements MultiItemEntity {
    public String name;
    public String location;

    public SearchBuyer(String name, String location) {
        this.name = name;
        this.location = location;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
