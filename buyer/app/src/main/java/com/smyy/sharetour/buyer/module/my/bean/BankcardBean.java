package com.smyy.sharetour.buyer.module.my.bean;

import java.io.Serializable;


public class BankcardBean implements Serializable {
    private int bgRes;
    private int logoRes;
    private String name;
    private String type;
    private String num;

    public int getBgRes() {
        return bgRes;
    }

    public void setBgRes(int bgRes) {
        this.bgRes = bgRes;
    }

    public int getLogoRes() {
        return logoRes;
    }

    public void setLogoRes(int logoRes) {
        this.logoRes = logoRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public BankcardBean(int bgRes, int logoRes, String name, String type, String num) {
        this.bgRes = bgRes;
        this.logoRes = logoRes;
        this.name = name;
        this.type = type;
        this.num = num;
    }
}
