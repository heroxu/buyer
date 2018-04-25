package com.smyy.sharetour.buyer.home.model;

import java.io.Serializable;

/**
 * create by xuxiarong on 2018/4/25
 */
public class ProductComment implements Serializable{
    public String avatarUrl;
    public String avatarName;

    public String content;

    public ProductComment(String avatarUrl, String avatarName, String content) {
        this.avatarUrl = avatarUrl;
        this.avatarName = avatarName;
        this.content = content;
    }
}
