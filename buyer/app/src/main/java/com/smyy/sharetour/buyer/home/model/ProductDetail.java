package com.smyy.sharetour.buyer.home.model;

import java.io.Serializable;

/**
 * create by xuxiarong on 2018/4/25
 */
public class ProductDetail implements Serializable{
    public String imgUrl;
    public String content;

    public ProductDetail(String imgUrl, String content) {
        this.imgUrl = imgUrl;
        this.content = content;
    }
}
