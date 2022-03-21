package com.softwear.webapp5.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Coupon {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String startDate;

    @Column(nullable = false)
    private String dateOfExpiry;
    
    private Float minimum;
    
    private Float discount;

	@ManyToMany
	@OnDelete(action = OnDeleteAction.CASCADE)
    private List<Product> affectedProducts;
    
    

    public Coupon(String code, String type, String startDate, String dateOfExpiry, Float minimum, Float discount, List<Product> affectedProducts) {
		this.code = code;
		this.type = type;
		this.startDate = startDate;
		this.dateOfExpiry = dateOfExpiry;
		this.minimum = minimum;
		this.discount = discount;
		this.affectedProducts = affectedProducts;
	}



	public Coupon() {}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
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

	

	public Float getMinimum() {
		return minimum;
	}


	
	public void setMinimum(Float minimum) {
		this.minimum = minimum;
	}



	public Float getDiscount() {
		return discount;
	}



	public void setDiscount(Float discount) {
		this.discount = discount;
	}



	public List<Product> getAffectedProducts() {
		return affectedProducts;
	}



	public void setAffectedProducts(List<Product> affectedProducts) {
		this.affectedProducts = affectedProducts;
	}



	@Override
	public String toString() {
		return "Coupon [code=" + code + ", type=" + type + ", startDate=" + startDate + ", dateOfExpiry=" + dateOfExpiry
				+ ", discount=" + discount + "]";
	}
    

}