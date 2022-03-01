package com.softwear.webapp5.service;

import com.softwear.webapp5.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import com.softwear.webapp5.model.User;

public class UserService {
	
	private UserRepository users;
	
	public Optional<User> findById(long id){
		return users.findById(id);
	}
	
	public List<User> findAll(){
		return users.findAll();
	}
	
	public List<User> findByUsername(String username){
		return users.findByUsername(username);
	}
	
	public List<User> findByEmail (String email){
		return users.findByEmail(email);
	}
	
	public List<User> findByName (String name){
		return users.findByName(name);
	}
	
	public List<User> findByLastname (String lastname){
		return users.findByLastname(lastname);
	}
	
	public List<User> findByAddress (String address){
		return users.findByAddress(address);
	}
	
	public List<User> findByBirthdate (String birthdate){
		return users.findByBirthdate(birthdate);
	}

}
