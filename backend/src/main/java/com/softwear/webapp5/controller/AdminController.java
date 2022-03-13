package com.softwear.webapp5.controller;

import java.util.List;

import com.softwear.webapp5.model.ShopUser;
import com.softwear.webapp5.service.MailService;
import com.softwear.webapp5.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

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

    @GetMapping("/manageProducts")
    public String products(){
        return "manageProducts";
    }

    
    @GetMapping("/manageUsers")
    public String users(Model model){
        List<ShopUser> users = userService.findAll();
        model.addAttribute("users", users);
        return "manageUsers";
    }

    @GetMapping("/home")
    public String adminHome(){
        return "adminHome";
    }
}
