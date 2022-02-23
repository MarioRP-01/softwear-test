package com.softwear.webapp5.model;


import java.util.ArrayList;
import java.util.Date;

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
	private Date birthday;
	private ArrayList<Product> cart = new ArrayList();
	//private ArrayList<Transaction> purchaseHistory = new ArrayList();
	private ArrayList<Product> wishlist= new ArrayList();
	
	
	public User() {
	
	}

	public User(String email, String name, String lastName, String password, String address, int mobileNumber, Date birthday,
			ArrayList<Product> cart, ArrayList<Product> wishlist) {
		this.email = email;
		this.name = name;
		this.lastName= lastName;
		this.password = password;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.birthday = birthday;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
