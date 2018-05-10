package com.smyy.sharetour.buyer.module.order.bean;

import java.io.Serializable;
import java.util.List;

public class OrderDetailBean implements Serializable {

    private String orderId;
    private String remainTime;
    private int orderStatus;
    private String shippingName;
    private String shippingPhone;
    private String shippingAddress;
    private String sellerId;
    private String sellerName;
    private String sellerAvatar;
    private String buyerId;
    private String buyerName;
    private String buyerAvatar;
    private int goodsCountTotal;
    private String priceTotal;
    private String verifyFee;//鉴定费
    private String reward;//辛苦费
    private String orderNum;
    private String orderTime;
    private String verifyVideo;//鉴定视频
    private int goodsType;//商品类型
    private String closeReason;
    private List<OrderGoodsInfo> goodsList;

    public OrderDetailBean() {
    }

    public OrderDetailBean(String orderId, String remainTime, int orderStatus, String shippingName, String shippingPhone, String shippingAddress, String sellerId, String sellerName, String sellerAvatar, String buyerId, String buyerName, String buyerAvatar, int goodsCountTotal, String priceTotal, String verifyFee, String reward, String orderNum, String orderTime, String verifyVideo, int goodsType, String closeReason, List<OrderGoodsInfo> goodsList) {
        this.orderId = orderId;
        this.remainTime = remainTime;
        this.orderStatus = orderStatus;
        this.shippingName = shippingName;
        this.shippingPhone = shippingPhone;
        this.shippingAddress = shippingAddress;
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.sellerAvatar = sellerAvatar;
        this.buyerId = buyerId;
        this.buyerName = buyerName;
        this.buyerAvatar = buyerAvatar;
        this.goodsCountTotal = goodsCountTotal;
        this.priceTotal = priceTotal;
        this.verifyFee = verifyFee;
        this.reward = reward;
        this.orderNum = orderNum;
        this.orderTime = orderTime;
        this.verifyVideo = verifyVideo;
        this.goodsType = goodsType;
        this.closeReason = closeReason;
        this.goodsList = goodsList;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(String remainTime) {
        this.remainTime = remainTime;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    public String getShippingPhone() {
        return shippingPhone;
    }

    public void setShippingPhone(String shippingPhone) {
        this.shippingPhone = shippingPhone;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerAvatar() {
        return sellerAvatar;
    }

    public void setSellerAvatar(String sellerAvatar) {
        this.sellerAvatar = sellerAvatar;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerAvatar() {
        return buyerAvatar;
    }

    public void setBuyerAvatar(String buyerAvatar) {
        this.buyerAvatar = buyerAvatar;
    }

    public int getGoodsCountTotal() {
        return goodsCountTotal;
    }

    public void setGoodsCountTotal(int goodsCountTotal) {
        this.goodsCountTotal = goodsCountTotal;
    }

    public String getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(String priceTotal) {
        this.priceTotal = priceTotal;
    }

    public String getVerifyFee() {
        return verifyFee;
    }

    public void setVerifyFee(String verifyFee) {
        this.verifyFee = verifyFee;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getVerifyVideo() {
        return verifyVideo;
    }

    public void setVerifyVideo(String verifyVideo) {
        this.verifyVideo = verifyVideo;
    }

    public int getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(int goodsType) {
        this.goodsType = goodsType;
    }

    public String getCloseReason() {
        return closeReason;
    }

    public void setCloseReason(String closeReason) {
        this.closeReason = closeReason;
    }

    public List<OrderGoodsInfo> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<OrderGoodsInfo> goodsList) {
        this.goodsList = goodsList;
    }
}
