package com.softwear.webapp5.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.data.ShopUserView;
import com.softwear.webapp5.data.TransactionView;
import com.softwear.webapp5.model.ShopUser;
import com.softwear.webapp5.model.Transaction;
import com.softwear.webapp5.service.MailService;
import com.softwear.webapp5.service.ProductService;
import com.softwear.webapp5.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
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
    ProductService productService;
    @Autowired
    PasswordEncoder passwordEncoder;

	/*@GetMapping("/mailTry")
	public String mailTest(Model model) {
		MailService ms = new MailService();
		try {
			ms.send("p.pinillos.2019@alumnos.urjc.es", "Trying..", "Does this work?");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return "index";
	}*/

    @GetMapping("/manageProducts")
    public String products(Model model){
        Page<Product> products = productService.findAll(PageRequest.of(0, 10));
        model.addAttribute("products", products);
        return "manageProducts"; 
    }

    
    @GetMapping("/manageUsers")
    public String users(Model model){
        Page<ShopUser> users = userService.findAll(PageRequest.of(0, 10));
        List<ShopUserView> listUsers = new ArrayList<>();
        for(ShopUser u: users) {
            listUsers.add(new ShopUserView(u));
        }
        model.addAttribute("users", users);
        model.addAttribute("hasPrev", users.hasPrevious());
        model.addAttribute("hasNext", users.hasNext());
        model.addAttribute("nextPage", users.getNumber()+1);
        model.addAttribute("prevPage", users.getNumber()-1);
        model.addAttribute("maxPages", users.getTotalPages());
        return "manageUsers";
    }

    @GetMapping("/home")
    public String adminHome(){
        return "adminHome";
    }
}
