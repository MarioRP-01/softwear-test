package com.softwear.webapp5.data;

import com.softwear.webapp5.model.Coupon;

public class CouponView {

    private long id;
    private String code;
    private double discount;

    public CouponView(Coupon coupon) {
        id = coupon.getId();
        code = coupon.getCode();
    }

    public CouponView(Coupon coupon, double discount) {
        id = coupon.getId();
        code = coupon.getCode();
        this.discount = discount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
