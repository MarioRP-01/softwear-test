package com.softwear.webapp5;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class adminHomeController {
	@GetMapping("/adminHome.html")
	public String greeting(Model model) {
	    return "adminHome";
	}
}
