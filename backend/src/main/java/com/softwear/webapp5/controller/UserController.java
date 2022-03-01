package com.softwear.webapp5.controller;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.model.User;
import com.softwear.webapp5.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService users;

	@GetMapping("/userProfile")
	public String user(Model model) {
		model.addAttribute("name", users.findAll().get(0).getName());
		model.addAttribute("lastName", users.findAll().get(0).getLastName());
		model.addAttribute("address", users.findAll().get(0).getAddress());
		model.addAttribute("email", users.findAll().get(0).getEmail());
		model.addAttribute("number", users.findAll().get(0).getMobileNumber());
		model.addAttribute("birthdate", users.findAll().get(0).getBirthdate());
		
		return "userProfile";
	}
	
	
    @GetMapping("/userProfile/purchaseHistory")
    public String purchaseHistory(Model model) {
        return "purchaseHistory";
    }
	
}
