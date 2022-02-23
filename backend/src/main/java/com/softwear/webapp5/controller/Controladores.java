package com.softwear.webapp5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controladores {
	@GetMapping("/productView") //Falta id
	public String product(Model model) {
	    return "productView";
	}
	
	@GetMapping("/") //Falta id
	public String index(Model model) {
	    return "index";
	}
	
	@GetMapping("/about") //Falta id
	public String about(Model model) {
	    return "about";
	}
	
	@GetMapping("/userProfile") //Falta id
	public String user(Model model) {
	    return "userProfile";
	}
	
	@GetMapping("/login") //Falta id
	public String login(Model model) {
	    return "login";
	}
	
}
