package com.softwear.webapp5.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.softwear.webapp5.model.ShopUser;
import com.softwear.webapp5.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
	
	@Autowired
	private UserService users;
	
	private Long id;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/userProfile")
	public String getUser(Model model) {

		ShopUser user = users.findByUsername((String) model.getAttribute("username")).get();
		this.id = user.getId();

		//model.addAttribute("id", this.id);
		model.addAttribute("name", user.getName());
		model.addAttribute("lastName", user.getLastName());
		model.addAttribute("address", user.getAddress());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("mobileNumber", user.getMobileNumber());
		model.addAttribute("birthdate", user.getBirthdate());
		
		return "userProfile";
	}
	
	@PostMapping("/userProfile")
	public String updateUser(Model model, HttpServletRequest request, ShopUser u) throws IOException{
		
		Optional<ShopUser> oldUser= users.findById(id);
		
		users.updateInfo(oldUser,u);
		
		return getUser(model);
	}
	
	@PostMapping("/userProfile/changePassword")
	public String updatePass(Model model,  HttpServletRequest request, @RequestParam(name = "oldPass") String oldPass,  @RequestParam(name = "newPass") String newPass,  @RequestParam(name = "newConfPass") String newConfPass) {
		
		Optional<ShopUser> oldUser= users.findById(id);
		
		if(passwordEncoder.matches(oldPass, oldUser.get().getPassword()) && newPass.equals(newConfPass)) {
			users.updatePass(oldUser, passwordEncoder.encode(newPass));
			return getUser(model);
		}
		
		
		return "BadPass";
	}
	
    @GetMapping("/userProfile/purchaseHistory")
    public String purchaseHistory(Model model) {
        return "purchaseHistory";
    }
    
    
	@GetMapping("/login") //Missing id
	public String loginPage(Model model) {
	    return "login";
	}
	
	@PostMapping("/login")
	public String login (Model model) {
		
		
		return "index";
	}

	@GetMapping("/loginSuccess")
	public String loginSuccess(Model model) {
		String role = users.findByUsername((String) model.getAttribute("username")).get().getRole();
		if(role.equals("ADMIN")) {
			return "redirect:/admin/home";
		}
		if(role.equals("USER")) {
			return "redirect:/";
		}
		return "error";
	}
	
	@PostMapping("/login/register")
	public String registerUser(Model model, HttpServletRequest request, ShopUser u, @RequestParam(name="confPassword") String confPass) {
		
		if(u.getPassword().equals(confPass)) {
			u.setPassword(passwordEncoder.encode(confPass));
			u.setName("");
			u.setAddress("");
			u.setBirthdate("");
			u.setEmail("");
			u.setLastName("");
			u.setName("");
			u.setRole("USER");
			users.saveUser(u);
			try {
				request.login(u.getUsername(), confPass);
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}
		
		return "redirect:/userProfile";
	}
	
}
