package com.softwear.webapp5.model;

import java.io.File;
import java.io.File;
import java.sql.Blob;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.softwear.webapp5.data.ProductSize;

@Entity
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
    private String name;

	@Column(nullable = false)
    private String description;

	@Column(nullable = false)
	private double price;

	@Column(nullable = false)
	private Long stock;
	
	@Column(nullable = false)
	private ProductSize size;

    private ArrayList<File> imgs = new ArrayList<>();
    
	public Product(String name, String description, double price, Long stock, ProductSize size, 
			ArrayList<File> imgs) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.size = size;
		this.imgs = imgs;
	}

	public Product() {}

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

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public ArrayList<File> getImgs() {
		return imgs;
	}

	public void setImgs(ArrayList<File> img_routes) {
		this.imgs = imgs;
	}

	public ProductSize getSize() {
		return size;
	}

	public void setSize(ProductSize size) {
		this.size = size;
	}

	public Blob getImageFile(int imageIndex) {
		return (Blob) imgs.get(imageIndex);
	}
}