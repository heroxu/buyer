package com.smyy.sharetour.buyer.my.bean;

import java.io.Serializable;


public class UserInfoBean implements Serializable {
    private String phone;
    private String username;
    private String userIntro;
    private String avatar;
    private int awaitingPaymentOrderCount;
    private int awaitingShipmentOrderCount;
    private int awaitingConfirmationOrderCount;
    private int disputeOrderCount;

    public UserInfoBean(String phone, String username, String userIntro, String avatar, int awaitingPaymentOrderCount, int awaitingShipmentOrderCount, int awaitingConfirmationOrderCount, int disputeOrderCount) {
        this.phone = phone;
        this.username = username;
        this.userIntro = userIntro;
        this.avatar = avatar;
        this.awaitingPaymentOrderCount = awaitingPaymentOrderCount;
        this.awaitingShipmentOrderCount = awaitingShipmentOrderCount;
        this.awaitingConfirmationOrderCount = awaitingConfirmationOrderCount;
        this.disputeOrderCount = disputeOrderCount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserIntro() {
        return userIntro;
    }

    public void setUserIntro(String userIntro) {
        this.userIntro = userIntro;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAwaitingPaymentOrderCount() {
        return awaitingPaymentOrderCount;
    }

    public void setAwaitingPaymentOrderCount(int awaitingPaymentOrderCount) {
        this.awaitingPaymentOrderCount = awaitingPaymentOrderCount;
    }

    public int getAwaitingShipmentOrderCount() {
        return awaitingShipmentOrderCount;
    }

    public void setAwaitingShipmentOrderCount(int awaitingShipmentOrderCount) {
        this.awaitingShipmentOrderCount = awaitingShipmentOrderCount;
    }

    public int getAwaitingConfirmationOrderCount() {
        return awaitingConfirmationOrderCount;
    }

    public void setAwaitingConfirmationOrderCount(int awaitingConfirmationOrderCount) {
        this.awaitingConfirmationOrderCount = awaitingConfirmationOrderCount;
    }

    public int getDisputeOrderCount() {
        return disputeOrderCount;
    }

    public void setDisputeOrderCount(int disputeOrderCount) {
        this.disputeOrderCount = disputeOrderCount;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "username='" + username + '\'' +
                ", userIntro='" + userIntro + '\'' +
                ", avatar='" + avatar + '\'' +
                ", phone='" + phone + '\'' +
                ", awaitingPaymentOrderCount=" + awaitingPaymentOrderCount +
                ", awaitingShipmentOrderCount=" + awaitingShipmentOrderCount +
                ", awaitingConfirmationOrderCount=" + awaitingConfirmationOrderCount +
                ", disputeOrderCount=" + disputeOrderCount +
                '}';
    }
}
