package com.smyy.sharetour.buyer.bean;

import java.io.Serializable;

public class TravelBean implements Serializable {

    private int travel_id;
    private String travel_country;
    private String travel_time;
    private boolean is_return;

    public TravelBean() {
    }

    public TravelBean(int travel_id, String travel_country, String travel_time, boolean is_return) {
        this.travel_id = travel_id;
        this.travel_country = travel_country;
        this.travel_time = travel_time;
        this.is_return = is_return;
    }

    public int getTravelId() {
        return travel_id;
    }

    public void setTravelId(int travel_id) {
        this.travel_id = travel_id;
    }

    public String getTravelCountry() {
        return travel_country;
    }

    public void setTravelCountry(String travel_country) {
        this.travel_country = travel_country;
    }

    public String getTravelTime() {
        return travel_time;
    }

    public void setTravelTime(String travel_time) {
        this.travel_time = travel_time;
    }

    public boolean getIsReturn() {
        return is_return;
    }

    public void setIsReturn(boolean is_return) {
        this.is_return = is_return;
    }

    @Override
    public String toString() {
        return "TravelBean{" +
                "travel_id=" + travel_id +
                ", travel_country='" + travel_country + '\'' +
                ", travel_time='" + travel_time + '\'' +
                ", is_return=" + is_return +
                '}';
    }
}
