package com.smyy.sharetour.buyer.module.my.bean;

import java.io.Serializable;


public class ShippingAddressBean implements Serializable {
    private int id;
    private boolean isDefault;
    private String name;
    private String phone;
    private String district;
    private String street;
    private String detailAddress;

    public ShippingAddressBean(int id, boolean isDefault, String name, String phone, String district, String street, String detailAddress) {
        this.id = id;
        this.isDefault = isDefault;
        this.name = name;
        this.phone = phone;
        this.district = district;
        this.street = street;
        this.detailAddress = detailAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    @Override
    public String toString() {
        return "ShippingAddressBean{" +
                "id=" + id +
                ", isDefault=" + isDefault +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                '}';
    }
}
