package com.softwear.webapp5.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type;
    private User user;
    private Coupon usedCoupon;
    private String date;
    private List<Product> products;

    public Transaction(String type, User user, Coupon usedCoupon, String date, List<Product> products) {
        this.type = type;
        this.user = user;
        this.usedCoupon = usedCoupon;
        this.date = date;
        this.products = products;
    }

    public Transaction() {}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Coupon getUsedCoupon() {
		return usedCoupon;
	}

	public void setUsedCoupon(Coupon usedCoupon) {
		this.usedCoupon = usedCoupon;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

    

}