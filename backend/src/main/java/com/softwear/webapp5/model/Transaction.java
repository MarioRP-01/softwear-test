package com.softwear.webapp5.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(nullable = false)
    private String type;

	@ManyToOne(optional = false)
    private ShopUser user;

	@ManyToOne
    private Coupon usedCoupon;

	@Column(nullable = false)
    private String date;

	@Column(nullable = false)
	private Double totalPrice;

	@ManyToMany
	@Column(nullable = false)
    private List<Product> products;

	public Transaction(String type, String date) {
		this.type = type;
		this.date = date;
	}

    public Transaction(String type, ShopUser user, Coupon usedCoupon, String date, List<Product> products) {
        this.type = type;
        this.user = user;
        this.usedCoupon = usedCoupon;
        this.date = date;
        this.products = products;
		totalPrice = calculateTotalProductPrice();
    }

    public Transaction() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ShopUser getUser() {
		return user;
	}

	public void setUser(ShopUser user) {
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
		totalPrice = calculateTotalProductPrice();
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	// Returns sum of product prices, without discount
    public double calculateTotalProductPrice() {
		double sum = 0.0f;
		for(Product product: products) {
			sum += product.getPrice();
		}
		return sum;
	}

}