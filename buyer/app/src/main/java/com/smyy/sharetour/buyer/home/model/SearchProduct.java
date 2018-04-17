package com.smyy.sharetour.buyer.home.model;

import java.io.Serializable;

/**
 * create by xuxiarong on 2018/4/17
 */
public class SearchProduct implements Serializable{
    public String name;
    public String url;
    public String price;
    public String price_rmb;
    public String nation_pic;
    public String nation_name;

    public SearchProduct(String name, String url, String price, String price_rmb, String nation_pic, String nation_name) {
        this.name = name;
        this.url = url;
        this.price = price;
        this.price_rmb = price_rmb;
        this.nation_pic = nation_pic;
        this.nation_name = nation_name;
    }
}
