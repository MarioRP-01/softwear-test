package com.softwear.webapp5.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.softwear.webapp5.model.ShopUser;
import com.softwear.webapp5.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
	
	@Autowired
	private UserService users;
	
	private Long id;
	
	@GetMapping("/userProfile")
	public String getUser(Model model) {

		ShopUser user = users.findByUsername((String) model.getAttribute("username")).get();
		this.id = user.getId();

		model.addAttribute("id", this.id);
		model.addAttribute("username", user.getUsername());
		model.addAttribute("name", user.getName());
		model.addAttribute("lastName", user.getLastName());
		model.addAttribute("address", user.getAddress());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("mobileNumber", user.getMobileNumber());
		model.addAttribute("birthdate", user.getBirthdate());
		
		return "userProfile";
	}
	
	@PostMapping("/userProfile/{id}")
	public String updateUser(Model model, HttpServletRequest request, ShopUser u) throws IOException{
		
		Optional<ShopUser> oldUser= users.findById(id);
		
		users.updateInfo(oldUser,u);
		
		return getUser(model);
	}
	
	@PostMapping("/userProfile/changePassword/{id}")
	public String updatePass(Model model,  HttpServletRequest request, @RequestParam(name = "oldPass") String oldPass,  @RequestParam(name = "newPass") String newPass,  @RequestParam(name = "newConfPass") String newConfPass) {
		
		Optional<ShopUser> oldUser= users.findById(id);
		
		if(oldUser.get().getPassword().equals(oldPass) && newPass.equals(newConfPass)) {
			users.updatePass(oldUser, newPass);
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
	
}
