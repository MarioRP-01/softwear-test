package com.softwear.webapp5.data;

import java.util.List;

import com.softwear.webapp5.model.Coupon;
import com.softwear.webapp5.model.Product;

public class CouponView {

	 private long id;
	    private String code;
	    private String type;
	    private String startDate;
	    private String dateOfExpiry;
	    private double minimum;
	    private double discount;
	    private List<Product> affectedProducts;
	    
	    public CouponView(Coupon coupon, double discount) {
	        id = coupon.getId();
	        code = coupon.getCode();
	        this.discount = discount;
	    }
	    
	    public CouponView(Coupon c) {
	    	this.id = c.getId();
			this.code = c.getCode();
			this.type = c.getType();
			this.startDate = c.getStartDate();
			this.dateOfExpiry = c.getDateOfExpiry();
			if(c.getMinimum()==null) {
				this.minimum = 0;
			}else{
				this.minimum = c.getMinimum();
			}
			
			if(c.getDiscount()==null) {
				this.discount=0;
			}else {
				this.discount = c.getDiscount();
			}
			
			this.affectedProducts = c.getAffectedProducts();
	    }
	    
		public CouponView(long id, String code, String type, String startDate, String dateOfExpiry, double minimum,
				double discount, List<Product> affectedProducts) {
			this.id = id;
			this.code = code;
			this.type = type;
			this.startDate = startDate;
			this.dateOfExpiry = dateOfExpiry;
			this.minimum = minimum;
			this.discount = discount;
			this.affectedProducts = affectedProducts;
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

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getStartDate() {
			return startDate;
		}

		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}

		public String getDateOfExpiry() {
			return dateOfExpiry;
		}

		public void setDateOfExpiry(String dateOfExpiry) {
			this.dateOfExpiry = dateOfExpiry;
		}

		public double getMinimum() {
			return minimum;
		}

		public void setMinimum(double minimum) {
			this.minimum = minimum;
		}

		public double getDiscount() {
			return discount;
		}

		public void setDiscount(double discount) {
			this.discount = discount;
		}

		public List<Product> getAffectedProducts() {
			return affectedProducts;
		}

		public void setAffectedProducts(List<Product> affectedProducts) {
			this.affectedProducts = affectedProducts;
		}

	    
    

    
}
