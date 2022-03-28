package com.softwear.webapp5.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public void update(ShopUser u) {
		
		Optional<ShopUser> oldUser = userService.findById(u.getId());
		userService.updateAdminInfo(oldUser.get(), u);
	}
	
	
}
