package com.smyy.sharetour.buyer.bean;

import java.io.Serializable;
import java.util.List;

public class RequireBean implements Serializable {

    private int require_id;
    private String require_disc;
    private String require_budget;
    private String require_time;
    private String require_type;
    private String require_buy_place;
    private String require_receive_address;
    private boolean is_verify;
    private List<String> img_paths;
    private String reward;
    private int state;

    public int getRequireId() {
        return require_id;
    }

    public void setRequireId(int require_id) {
        this.require_id = require_id;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public RequireBean() {
    }

    public String getRequire_disc() {
        return require_disc;
    }

    public void setRequire_disc(String require_disc) {
        this.require_disc = require_disc;
    }

    public String getRequire_budget() {
        return require_budget;
    }

    public void setRequire_budget(String require_budget) {
        this.require_budget = require_budget;
    }

    public String getRequire_time() {
        return require_time;
    }

    public void setRequire_time(String require_time) {
        this.require_time = require_time;
    }

    public String getRequire_type() {
        return require_type;
    }

    public void setRequire_type(String require_type) {
        this.require_type = require_type;
    }

    public String getRequire_buy_place() {
        return require_buy_place;
    }

    public void setRequire_buy_place(String require_buy_place) {
        this.require_buy_place = require_buy_place;
    }

    public String getRequire_receive_address() {
        return require_receive_address;
    }

    public void setRequire_receive_address(String require_receive_address) {
        this.require_receive_address = require_receive_address;
    }

    public boolean isIs_verify() {
        return is_verify;
    }

    public void setIs_verify(boolean is_verify) {
        this.is_verify = is_verify;
    }

    public List<String> getImg_paths() {
        return img_paths;
    }

    public void setImg_paths(List<String> img_paths) {
        this.img_paths = img_paths;
    }

    @Override
    public String toString() {
        return "RequireBean{" +
                "require_disc='" + require_disc + '\'' +
                ", require_budget='" + require_budget + '\'' +
                ", require_time='" + require_time + '\'' +
                ", require_type='" + require_type + '\'' +
                ", require_buy_place='" + require_buy_place + '\'' +
                ", require_receive_address='" + require_receive_address + '\'' +
                ", is_verify=" + is_verify +
                ", reward='" + reward + '\'' +
                ", state=" + state +
                '}';
    }
}
