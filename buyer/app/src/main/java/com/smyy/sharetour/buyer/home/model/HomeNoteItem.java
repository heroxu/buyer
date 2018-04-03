package com.smyy.sharetour.buyer.home.model;

import java.io.Serializable;

/**
 * Created by 许夏荣 on 2018/3/29.
 * desc:
 */

public class HomeNoteItem implements Serializable {
    public String title;
    public int type;
    public String content;
    public String vedioUrl;
    public String avatar;
    public String avatarName;
    public String agree;
    public String comments;

    public HomeNoteItem(String title, int type, String content, String vedioUrl, String avatar, String avatarName, String agree, String comments) {
        this.title = title;
        this.type = type;
        this.content = content;
        this.vedioUrl = vedioUrl;
        this.avatar = avatar;
        this.avatarName = avatarName;
        this.agree = agree;
        this.comments = comments;
    }
}
