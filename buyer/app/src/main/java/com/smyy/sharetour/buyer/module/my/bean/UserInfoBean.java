package com.smyy.sharetour.buyer.module.my.bean;

import java.io.Serializable;
import java.util.List;


public class UserInfoBean implements Serializable {
    private String linkedPhoneNum;
    private String username;
    private String userIntro;
    private String avatar;
    private int awaitingPaymentOrderCount;
    private int awaitingShipmentOrderCount;
    private int awaitingConfirmationOrderCount;
    private int disputeOrderCount;
    private Residence residence;
    private List<String> usualDestList;

    public UserInfoBean(String linkedPhoneNum, String username, String userIntro, String avatar, int awaitingPaymentOrderCount, int awaitingShipmentOrderCount, int awaitingConfirmationOrderCount, int disputeOrderCount, Residence residence, List<String> usualDestList) {
        this.linkedPhoneNum = linkedPhoneNum;
        this.username = username;
        this.userIntro = userIntro;
        this.avatar = avatar;
        this.awaitingPaymentOrderCount = awaitingPaymentOrderCount;
        this.awaitingShipmentOrderCount = awaitingShipmentOrderCount;
        this.awaitingConfirmationOrderCount = awaitingConfirmationOrderCount;
        this.disputeOrderCount = disputeOrderCount;
        this.residence = residence;
        this.usualDestList = usualDestList;
    }

    public static class Residence {
        private String country;
        private String province;
        private String city;

        public Residence(String country, String province, String city) {
            this.country = country;
            this.province = province;
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }

    public String getLinkedPhoneNum() {
        return linkedPhoneNum;
    }

    public void setLinkedPhoneNum(String linkedPhoneNum) {
        this.linkedPhoneNum = linkedPhoneNum;
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

    public Residence getResidence() {
        return residence;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
    }

    public List<String> getUsualDestList() {
        return usualDestList;
    }

    public void setUsualDestList(List<String> usualDestList) {
        this.usualDestList = usualDestList;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "linkedPhoneNum='" + linkedPhoneNum + '\'' +
                ", username='" + username + '\'' +
                ", userIntro='" + userIntro + '\'' +
                ", avatar='" + avatar + '\'' +
                ", awaitingPaymentOrderCount=" + awaitingPaymentOrderCount +
                ", awaitingShipmentOrderCount=" + awaitingShipmentOrderCount +
                ", awaitingConfirmationOrderCount=" + awaitingConfirmationOrderCount +
                ", disputeOrderCount=" + disputeOrderCount +
                ", residence=" + residence +
                ", usualDestList=" + usualDestList +
                '}';
    }
}
