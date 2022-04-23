package com.softwear.webapp5.service;

import com.softwear.webapp5.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.softwear.webapp5.data.UserEditProfileDTO;
import com.softwear.webapp5.model.ShopUser;

@Service
public class UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository shopUsers;
	
	public Page<ShopUser> findAll(Pageable page){
		return shopUsers.findAll(page);
	}
	
	public Optional<ShopUser> findById(Long id){
		return shopUsers.findById(id);
	}
	
	public List<ShopUser> findAll(){
		return shopUsers.findAll();
	}

	public Optional<ShopUser> findByUsername(String username){
		return shopUsers.findByUsername(username);
	}
	
	public List<ShopUser> findByEmail (String email){
		return shopUsers.findByEmail(email);
	}
	
	public List<ShopUser> findByName (String name){
		return shopUsers.findByName(name);
	}
	
	public List<ShopUser> findByLastName (String lastName){
		return shopUsers.findByLastName(lastName);
	}
	
	public List<ShopUser> findByAddress (String address){
		return shopUsers.findByAddress(address);
	}
	
	public List<ShopUser> findByBirthdate (String birthdate){
		return shopUsers.findByBirthdate(birthdate);
	}
	
	///////////////////////////////////////////
	
	public void updateInfo(Optional<ShopUser> oldShopUser, ShopUser u) {
		
		
		oldShopUser.get().setAddress(u.getAddress());
		oldShopUser.get().setBirthdate(u.getBirthdate());
		oldShopUser.get().setEmail(u.getEmail());
		oldShopUser.get().setLastName(u.getLastName());
		oldShopUser.get().setMobileNumber(u.getMobileNumber());
		oldShopUser.get().setName(u.getName());
		
		shopUsers.save(oldShopUser.get());
		
	}

	public void updateProfile(ShopUser user, UserEditProfileDTO editData) {
		
		user.setEmail(editData.getEmail());
		user.setName(editData.getName());
		user.setLastName(editData.getLastName());
		user.setAddress(editData.getAddress());
		user.setMobileNumber(editData.getMobileNumber());
		user.setBirthdate(editData.getBirthDate());
	}

	public void updateUser(ShopUser oldUser, ShopUser newUser) {

		oldUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

		oldUser.setUsername(newUser.getUsername());
		oldUser.setAddress(newUser.getAddress());
		oldUser.setPassword(newUser.getPassword());
		oldUser.setBirthdate(newUser.getBirthdate());
		oldUser.setEmail(newUser.getEmail());
		oldUser.setLastName(newUser.getLastName());
		oldUser.setMobileNumber(newUser.getMobileNumber());
		oldUser.setName(newUser.getName());
		oldUser.setRole(newUser.getRole());
	}

	public void updateAdminInfo(ShopUser oldShopUser, ShopUser u) {

		oldShopUser.setUsername(u.getUsername());
		oldShopUser.setAddress(u.getAddress());
		oldShopUser.setPassword(u.getPassword());
		oldShopUser.setBirthdate(u.getBirthdate());
		oldShopUser.setEmail(u.getEmail());
		oldShopUser.setLastName(u.getLastName());
		oldShopUser.setMobileNumber(u.getMobileNumber());
		oldShopUser.setName(u.getName());
		oldShopUser.setRole(u.getRole());
		
		shopUsers.save(oldShopUser);
		
	}
	
	public void updatePass(Optional<ShopUser> oldShopUser, String newPass) {

		oldShopUser.get().setPassword(newPass);
		shopUsers.save(oldShopUser.get());
		
	}

	public void updatePass(ShopUser user, String newPass) {

		user.setPassword(newPass);
		shopUsers.save(user);
	}
	
	public void saveUser(ShopUser u) {
		shopUsers.save(u);
	}

	public void delete(Long id){
		shopUsers.deleteById(id);
	}

	public boolean checkShippingData(ShopUser user) {
		return !user.getName().equals("") && !user.getLastName().equals("") && !user.getAddress().equals("");
	}

}
