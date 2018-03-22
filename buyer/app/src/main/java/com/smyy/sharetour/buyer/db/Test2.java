package com.smyy.sharetour.buyer.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 伍振飞 on 2018/3/19 15:53
 * E-Mail Address：wuzf2012@sina.com
 */
@Entity
public class Test2 {
    @Id(autoincrement = true)
    private Long bId;
    @NotNull
    private String photo;
    @NotNull
    private String phoneNum;
    @Generated(hash = 251823037)
    public Test2(Long bId, @NotNull String photo, @NotNull String phoneNum) {
        this.bId = bId;
        this.photo = photo;
        this.phoneNum = phoneNum;
    }
    @Generated(hash = 1885849886)
    public Test2() {
    }
    public Long getBId() {
        return this.bId;
    }
    public void setBId(Long bId) {
        this.bId = bId;
    }
    public String getPhoto() {
        return this.photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getPhoneNum() {
        return this.phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
