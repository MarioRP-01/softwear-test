package com.softwear.webapp5.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softwear.webapp5.model.User;

public interface  UserRepository extends JpaRepository<User, Long> {
	
	public Optional<User> findById(Long id);
	public List<User> findByUsername(String username);
	public List<User> findByEmail (String email);
	public List<User> findByName (String name);
	public List<User> findByLastName (String lastName);
	public List<User> findByAddress (String Address);
	public List<User> findByBirthdate (String Birthdate);
	
}
