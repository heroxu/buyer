package com.smyy.sharetour.buyer.backpacker.my.bean;

import java.io.Serializable;


public class AccountStatementBean implements Serializable {
    private String name;
    private String time;
    private String num;
    private int type;

    public AccountStatementBean(String name, String time, String num, int type) {
        this.name = name;
        this.time = time;
        this.num = num;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
