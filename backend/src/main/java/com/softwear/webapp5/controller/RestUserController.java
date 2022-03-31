package com.softwear.webapp5.controller;

import java.util.Collection;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import com.softwear.webapp5.data.PassChange;
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
	
	@PutMapping("/updateAdminInfo")
	public void updateAdmin(@RequestBody ShopUser u) {
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		Optional<ShopUser> oldUser = userService.findById(u.getId());
	
		userService.updateAdminInfo(oldUser.get(), u);
	}
	
	@PutMapping("/updateUserInfo")
	public void updateUser(HttpServletRequest request, @RequestBody ShopUser u) {
		Optional<ShopUser> oldUser = userService.findByUsername(request.getUserPrincipal().getName());
	
		userService.updateInfo(oldUser, u);
	}
	
	@PutMapping("/updatePassword")
	public ResponseEntity updateUserPass(HttpServletRequest request, @RequestBody PassChange pc) {
		Optional<ShopUser> oldUser= userService.findByUsername(request.getUserPrincipal().getName());
		
		if(passwordEncoder.matches(pc.getOldPass(), oldUser.get().getPassword()) && pc.getNewPass().equals(pc.getNewConfPass())) {
			userService.updatePass(oldUser, passwordEncoder.encode(pc.getNewPass())	);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.badRequest().build();
		
	}
	
	@PostMapping("/createUser")
	public void create(@RequestBody ShopUser u) {
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		userService.saveUser(u);
	}
	
	@DeleteMapping("/deleteUser")
	public void delete(@RequestParam Long id) {
		userService.delete(id);
	}
}
