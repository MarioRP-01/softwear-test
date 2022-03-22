package com.softwear.webapp5.model;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

	@ElementCollection
	private List<String> images;

	@ElementCollection
	@Lob
	@JsonIgnore
    private List<Blob> imageFiles = new ArrayList<>();
    
	public Product(String name, String description, double price, Long stock, ProductSize size, 
			List<String> images, ArrayList<Blob> imageFiles) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.size = size;
		this.images = images;
		this.imageFiles = imageFiles;
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

	public ProductSize getSize() {
		return size;
	}

	public void setSize(ProductSize size) {
		this.size = size;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getImage(int imageIndex) {
		if(imageIndex < images.size()) {
			return images.get(imageIndex);
		}
		return "";
	}

	public void addImage(String image) {
		images.add(image);
	}

	public void setImage(int imageIndex, String image) {
		if(imageIndex < images.size()) {
			images.set(imageIndex, image);
		}
	}

	public void removeImage(int imageIndex) {
		if(imageIndex < images.size()) {
			images.remove(imageIndex);
		}
	}

	public List<Blob> getImageFiles() {
		return imageFiles;
	}

	public void setImageFiles(List<Blob> imageFiles) {
		this.imageFiles = imageFiles;
	}

	public Blob getImageFile(int imageIndex) {
		return (Blob) imageFiles.get(imageIndex);
	}

	public void setImageFile(int imageIndex, Blob image) {
		if(imageFiles.size() > imageIndex) {
			imageFiles.set(imageIndex, image);
		}
	}

	public void addImageFile(Blob image) {
		imageFiles.add(image);
	}

	public void removeImageFile(int imageIndex) {
		if(imageFiles.size() > imageIndex) {
			imageFiles.remove(imageIndex);
		}
	}

}