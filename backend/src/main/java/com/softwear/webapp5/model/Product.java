package com.softwear.webapp5.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private ArrayList <String> img_routes = new ArrayList<>();
    private double price;
    
    
    public Product() { }
    
	public Product(String name, String description, ArrayList<String> img_routes, double price) {
		this.name = name;
		this.description = description;
		this.img_routes = img_routes;
		this.price = price;
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
	public ArrayList<String> getImg_routes() {
		return img_routes;
	}
	public void setImg_routes(ArrayList<String> img_routes) {
		this.img_routes = img_routes;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
    
}