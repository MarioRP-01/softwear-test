package com.softwear.webapp5.controller;

import com.softwear.webapp5.service.MailService;

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
		model.addAttribute("activeMenu", "about");
	    return "about";
	}
	
	/*@GetMapping("/userProfile") //Missing id
	public String user(Model model) {
	    return "userProfile";
	}*/
	

	
	@GetMapping("/mailTry")
	public String mailTest(Model model) {
		MailService ms = new MailService("softwearDAW@gmail.com", "9SEc6FMyIvPB");
		try {
			ms.send("p.pinillos.2019@alumnos.urjc.es", "Trying..", "Does this work?");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return "error";
	}
}
