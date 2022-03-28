package com.softwear.webapp5.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softwear.webapp5.model.ShopUser;
import com.softwear.webapp5.service.UserService;

@RestController
@RequestMapping("/api/users")
public class RestUserController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/")
	public Collection<ShopUser> get() {
		return userService.findAll();
	}
	
	@PutMapping("/updateInfo")
	public void update(@RequestBody ShopUser u) {
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		Optional<ShopUser> oldUser = userService.findById(u.getId());
	
		userService.updateAdminInfo(oldUser.get(), u);
	}
	
	@PostMapping("/createUser")
	public void create(@RequestBody ShopUser u) {
		userService.saveUser(u);
	}
	
	@DeleteMapping("/deleteUser")
	public void delete(@RequestParam Long id) {
		userService.delete(id);
	}
}
