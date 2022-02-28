package com.softwear.webapp5.model;


import java.util.ArrayList;

import javax.persistence.*;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String email;
	private String name;
	private String lastName;
	private String password;
	private String address;
	private int mobileNumber;
	private String birthdate;
	private ArrayList<Product> cart = new ArrayList<>();
	//private ArrayList<Transaction> purchaseHistory = new ArrayList();
	private ArrayList<Product> wishlist= new ArrayList<>();
	
	
	public User() {
	
	}

	public User(String email, String name, String lastName, String password, String address, int mobileNumber, String birthdate,
			ArrayList<Product> cart, ArrayList<Product> wishlist) {
		this.email = email;
		this.name = name;
		this.lastName= lastName;
		this.password = password;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.birthdate = birthdate;
		this.cart = cart;
		this.wishlist = wishlist;
	}

	public void setId(Long id) {
		this.id=id;
	}
	
	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public ArrayList<Product> getCart() {
		return cart;
	}

	public void setCart(ArrayList<Product> cart) {
		this.cart = cart;
	}

	public ArrayList<Product> getWishlist() {
		return wishlist;
	}

	public void setWishlist(ArrayList<Product> wishlist) {
		this.wishlist = wishlist;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
	
}
