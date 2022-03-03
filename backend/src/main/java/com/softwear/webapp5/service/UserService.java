package com.softwear.webapp5.service;

import com.softwear.webapp5.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softwear.webapp5.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository users;
	
	public Optional<User> findById(Long id){
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
	
	public List<User> findByLastName (String lastName){
		return users.findByLastName(lastName);
	}
	
	public List<User> findByAddress (String address){
		return users.findByAddress(address);
	}
	
	public List<User> findByBirthdate (String birthdate){
		return users.findByBirthdate(birthdate);
	}
	
	///////////////////////////////////////////
	
	public void updateInfo(Optional<User> oldUser, User u) {

		oldUser.get().setUsername(u.getUsername());
		oldUser.get().setAddress(u.getAddress());
		oldUser.get().setBirthdate(u.getBirthdate());
		oldUser.get().setEmail(u.getEmail());
		oldUser.get().setLastName(u.getLastName());
		oldUser.get().setMobileNumber(u.getMobileNumber());
		oldUser.get().setName(u.getName());
		
		users.save(oldUser.get());
		
	}
	
	public void updatePass(Optional<User> oldUser, String newPass) {

		oldUser.get().setPassword(newPass);
		users.save(oldUser.get());
		
	}

}
