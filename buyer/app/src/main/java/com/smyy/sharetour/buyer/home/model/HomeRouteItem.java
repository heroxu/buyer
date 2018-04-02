package com.smyy.sharetour.buyer.home.model;

import java.io.Serializable;

/**
 * Created by 许夏荣 on 2018/3/29.
 * desc:
 */

public class HomeRouteItem implements Serializable{
    public String goTime;
    public String backTime;
    public String routeWays;

    public HomeRouteItem(String goTime, String backTime, String routeWays) {
        this.goTime = goTime;
        this.backTime = backTime;
        this.routeWays = routeWays;
    }
}
