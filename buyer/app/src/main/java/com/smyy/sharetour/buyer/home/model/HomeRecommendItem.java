package com.smyy.sharetour.buyer.home.model;

import java.io.Serializable;

/**
 * Created by 许夏荣 on 2018/3/29.
 * desc:
 */

public class HomeRecommendItem implements Serializable {

    public String location;
    public String avatarUrl;
    public String avatarName;
    public String classifyOne;
    public String classifyTwo;
    public String classifyThree;

    public HomeRecommendItem(String location, String avatarUrl, String avatarName, String classifyOne, String classifyTwo, String classifyThree) {
        this.location = location;
        this.avatarUrl = avatarUrl;
        this.avatarName = avatarName;
        this.classifyOne = classifyOne;
        this.classifyTwo = classifyTwo;
        this.classifyThree = classifyThree;
    }
}
