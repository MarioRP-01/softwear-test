package com.softwear.webapp5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("/productView") //Missing id
	public String product(Model model) {
	    return "productView";
	}
	
	@GetMapping("/") //Missing id
	public String index(Model model) {
	    return "index";
	}
	
	@GetMapping("/about") //Missing id
	public String about(Model model) {
	    return "about";
	}
	
	/*@GetMapping("/userProfile") //Missing id
	public String user(Model model) {
	    return "userProfile";
	}*/
	
	@GetMapping("/login") //Missing id
	public String login(Model model) {
	    return "login";
	}
	
}
