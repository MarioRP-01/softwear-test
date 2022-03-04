package com.softwear.webapp5.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softwear.webapp5.model.ShopUser;

public interface  UserRepository extends JpaRepository<ShopUser, Long> {
	
	public Optional<ShopUser> findById(Long id);
	public Optional<ShopUser> findByUsername(String username);
	public List<ShopUser> findByEmail (String email);
	public List<ShopUser> findByName (String name);
	public List<ShopUser> findByLastName (String lastName);
	public List<ShopUser> findByAddress (String Address);
	public List<ShopUser> findByBirthdate (String Birthdate);
	
}
