package com.softwear.webapp5.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.softwear.webapp5.model.User;
import com.softwear.webapp5.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository users;

	@PostConstruct
	public void init() {
		users.save(new User("ale.2019@alumnos.urjc.es", "Ale","Morueco", "1234", "Arroyo", 671642, "12/02/2000", null ,null));
		//users.save(new User());
	}

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
	
	
}
