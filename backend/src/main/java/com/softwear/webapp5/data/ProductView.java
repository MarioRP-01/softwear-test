package com.softwear.webapp5.data;

import java.util.ArrayList;
import java.util.List;

import com.softwear.webapp5.model.Product;

public class ProductView {
	
	private Long id;
	private String name;
	private String description;
	private double price;
	private long stock;
	private ProductSize size;
	private List<String> images;
	
	
	public ProductView(Product p) {
		this.id = p.getId();
		this.name = p.getName();
		this.description = p.getDescription();
		this.price = p.getPrice();
		this.stock = p.getStock();
		this.size = p.getSize();
		this.images = p.getImages();
	}
	
	public ProductView(Long id, String name, String description, double price, long stock, ProductSize size,
			ArrayList<String> images) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.size = size;
		this.images = images;
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
	public List<String> getImages() {
		return images;
	}
	public void setImages(ArrayList<String> images) {
		this.images = images;
	}
	
	

}