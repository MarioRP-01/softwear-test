package com.softwear.webapp5.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(nullable = false)
    private String name;

	@Column(nullable = false)
    private String description;

	@Column(nullable = false)
	private double price;

	@Column(nullable = false)
	private int stock;
	
	private String supplier;
	private String madeIn;
	private String brand;
	private String manufactDate;

    private ArrayList<String> img_routes = new ArrayList<>();
    
	public Product(String name, String description, double price, int stock, String supplier, 
	String madeIn, String brand, String manufactDate, ArrayList<String> img_routes) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.supplier = supplier;
		this.madeIn = madeIn;
		this.brand = brand;
		this.manufactDate = manufactDate;
		this.img_routes = img_routes;
	}

	public Product() {}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getManufactDate() {
		return manufactDate;
	}

	public void setManufactDate(String manufactDate) {
		this.manufactDate = manufactDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getMadeIn() {
		return madeIn;
	}

	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}

	public ArrayList<String> getImg_routes() {
		return img_routes;
	}

	public void setImg_routes(ArrayList<String> img_routes) {
		this.img_routes = img_routes;
	}
}