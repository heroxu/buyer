package com.smyy.sharetour.buyer.module.order.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class OrderBean implements Serializable {

    private String orderId;
    private int orderStatus;
    private String sellerId;
    private String sellerName;
    private String sellerAvatar;
    private String buyerId;
    private int goodsCountTotal;
    private String priceTotal;
    private String shippingFee;//运费
    private List<GoodsInfo> goodsList;


    public static class GoodsInfo implements Serializable {
        private String goodsId;
        private String goodsName;
        private String goodsSpec;
        private String goodsPrice;
        private int goodsCount;
        private String goodsPic;

        public GoodsInfo(String goodsId, String goodsName, String goodsSpec, String goodsPrice, int goodsCount, String goodsPic) {
            this.goodsId = goodsId;
            this.goodsName = goodsName;
            this.goodsSpec = goodsSpec;
            this.goodsPrice = goodsPrice;
            this.goodsCount = goodsCount;
            this.goodsPic = goodsPic;
        }

        @Override
        public String toString() {
            return "GoodsInfo{" +
                    "goodsId='" + goodsId + '\'' +
                    ", goodsName='" + goodsName + '\'' +
                    ", goodsSpec='" + goodsSpec + '\'' +
                    ", goodsPrice='" + goodsPrice + '\'' +
                    ", goodsCount='" + goodsCount + '\'' +
                    ", goodsPic='" + goodsPic + '\'' +
                    '}';
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsSpec() {
            return goodsSpec;
        }

        public void setGoodsSpec(String goodsSpec) {
            this.goodsSpec = goodsSpec;
        }

        public String getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(String goodsPrice) {
            this.goodsPrice = goodsPrice;
        }

        public int getGoodsCount() {
            return goodsCount;
        }

        public void setGoodsCount(int goodsCount) {
            this.goodsCount = goodsCount;
        }

        public String getGoodsPic() {
            return goodsPic;
        }

        public void setGoodsPic(String goodsPic) {
            this.goodsPic = goodsPic;
        }
    }


    public OrderBean(String orderId, int orderStatus, String sellerId, String sellerName, String sellerAvatar, String buyerId, int goodsCountTotal, String priceTotal, String shippingFee, List<GoodsInfo> goodsList) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.sellerAvatar = sellerAvatar;
        this.buyerId = buyerId;
        this.goodsCountTotal = goodsCountTotal;
        this.priceTotal = priceTotal;
        this.shippingFee = shippingFee;
        this.goodsList = goodsList;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
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

    public String getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(String shippingFee) {
        this.shippingFee = shippingFee;
    }

    public List<GoodsInfo> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsInfo> goodsList) {
        this.goodsList = goodsList;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "orderId='" + orderId + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", sellerName='" + sellerName + '\'' +
                ", sellerAvatar='" + sellerAvatar + '\'' +
                ", buyerId='" + buyerId + '\'' +
                ", goodsCountTotal=" + goodsCountTotal +
                ", priceTotal=" + priceTotal +
                ", shippingFee=" + shippingFee +
                ", goodsList=" + goodsList +
                '}';
    }
}
