package com.softwear.webapp5.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.softwear.webapp5.model.User;
import com.softwear.webapp5.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService users;
	
	private Long id;

	@GetMapping("/userProfile")
	public String user(Model model) {
		
		
		return "userProfile";
	}
	
	@GetMapping("/userProfile/{id}")
	public String getUser(Model model, @PathVariable Long id) {

		this.id=id;
		model.addAttribute("username", users.findById(id).get().getUsername());
		model.addAttribute("name", users.findById(id).get().getName());
		model.addAttribute("lastName", users.findById(id).get().getLastName());
		model.addAttribute("address", users.findById(id).get().getAddress());
		model.addAttribute("email", users.findById(id).get().getEmail());
		model.addAttribute("mobileNumber", users.findById(id).get().getMobileNumber());
		model.addAttribute("birthdate", users.findById(id).get().getBirthdate());
		
		return "userProfile";
	}
	
	@PostMapping("/")
	public String updateUser(Model model, User u) throws IOException{
		
		Optional<User> oldUser= users.findById(id);
		
		users.updateInfo(oldUser,u);
		
		return "index";
	}
	
	@PostMapping("/userProfile/{id}")
	public String updatePass(Model model, @RequestParam(name = "oldPass") String oldPass,  @RequestParam(name = "newPass") String newPass,  @RequestParam(name = "newConfPass") String newConfPass) {
		
		Optional<User> oldUser= users.findById(id);
		
		if(oldUser.get().getPassword().equals(oldPass) && newPass.equals(newConfPass)) {
			users.updatePass(oldUser, newPass);
			return "index";
		}
		
		return "BadPass";
	}
	
	
	
    @GetMapping("/userProfile/purchaseHistory")
    public String purchaseHistory(Model model) {
        return "purchaseHistory";
    }
	
}
