package com.smyy.sharetour.buyer.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TravelBean implements Serializable {

    private int travel_id;
    private String create_date;
    List<RouteBean> routeBeans = new ArrayList<>();

    public TravelBean() {
    }

    public TravelBean(int travel_id, String create_date, List<RouteBean> routeBeans) {
        this.travel_id = travel_id;
        this.create_date = create_date;
        this.routeBeans = routeBeans;
    }

    public int getTravelId() {
        return travel_id;
    }

    public void setTravelId(int travel_id) {
        this.travel_id = travel_id;
    }

    public String getCreateDate() {
        return create_date;
    }

    public void setCreateDate(String create_date) {
        this.create_date = create_date;
    }

    public List<RouteBean> getRouteBeans() {
        return routeBeans;
    }

    public void setRouteBeans(List<RouteBean> routeBeans) {
        this.routeBeans = routeBeans;
    }

    @Override
    public String toString() {
        return "TravelBean{" +
                "travel_id=" + travel_id +
                ", create_date='" + create_date + '\'' +
                ", routeBeans=" + routeBeans.toString() +
                '}';
    }

    public static class RouteBean implements Serializable {
        private int route_id;
        private String route_country;
        private String route_time;
        private int img_id;
        private boolean is_return;

        public RouteBean() {
        }

        public RouteBean(int route_id, String route_country, String route_time, boolean is_return) {
            this.route_id = route_id;
            this.route_country = route_country;
            this.route_time = route_time;
            this.is_return = is_return;
        }

        public int getRouteId() {
            return route_id;
        }

        public void setRouteId(int route_id) {
            this.route_id = route_id;
        }

        public String getRouteCountry() {
            return route_country;
        }

        public void setRouteCountry(String route_country) {
            this.route_country = route_country;
        }

        public String getRouteTime() {
            return route_time;
        }

        public void setRouteTime(String route_time) {
            this.route_time = route_time;
        }

        public boolean isIsReturn() {
            return is_return;
        }

        public void setIsReturn(boolean is_return) {
            this.is_return = is_return;
        }

        public int getImgId() {
            return img_id;
        }

        public void setImgId(int img_id) {
            this.img_id = img_id;
        }
    }
}
