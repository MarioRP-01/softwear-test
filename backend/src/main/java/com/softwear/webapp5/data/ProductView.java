package com.softwear.webapp5.data;

import java.io.File;
import java.util.ArrayList;

import com.softwear.webapp5.model.Product;

public class ProductView {
	
	private Long id;
	private String name;
	private String description;
	private double price;
	private long stock;
	private ProductSize size;
	private ArrayList<File> imgs;
	
	
	public ProductView(Product p) {
		this.id = p.getId();
		this.name = p.getName();
		this.description = p.getDescription();
		this.price = p.getPrice();
		this.stock = p.getStock();
		this.size = p.getSize();
		this.imgs = p.getImgs();
	}
	
	public ProductView(Long id, String name, String description, double price, long stock, ProductSize size,
			ArrayList<File> imgs) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.size = size;
		this.imgs = imgs;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public long getStock() {
		return stock;
	}
	public void setStock(long stock) {
		this.stock = stock;
	}
	public ProductSize getSize() {
		return size;
	}
	public void setSize(ProductSize size) {
		this.size = size;
	}
	public ArrayList<File> getImgs() {
		return imgs;
	}
	public void setImgs(ArrayList<File> imgs) {
		this.imgs = imgs;
	}
	
	

}