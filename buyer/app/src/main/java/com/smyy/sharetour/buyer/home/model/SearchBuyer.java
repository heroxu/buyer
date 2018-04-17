package com.smyy.sharetour.buyer.home.model;

import java.io.Serializable;

/**
 * create by xuxiarong on 2018/4/17
 */
public class SearchBuyer implements Serializable{
    public String name;
    public String location;

    public SearchBuyer(String name, String location) {
        this.name = name;
        this.location = location;
    }
}
