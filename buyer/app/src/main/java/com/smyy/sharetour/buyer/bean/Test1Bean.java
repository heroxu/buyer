package com.smyy.sharetour.buyer.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by 伍振飞 on 2018/3/15 17:44
 * E-Mail Address：wuzf2012@sina.com
 */
public class Test1Bean extends MultiItemEntity {
    private String name;
    private String address;
    private String photo;

    public Test1Bean(String name, String address, String photo) {
        this.name = name;
        this.address = address;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
