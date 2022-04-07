package com.softwear.webapp5.controller;

import java.util.ArrayList;
import java.util.List;

import com.softwear.webapp5.model.Coupon;
import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.data.ShopUserView;
import com.softwear.webapp5.model.ShopUser;
import com.softwear.webapp5.service.CouponService;
import com.softwear.webapp5.service.ProductService;
import com.softwear.webapp5.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    CouponService couponService;
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
        model.addAttribute("hasPrev", products.hasPrevious());
        model.addAttribute("hasNext", products.hasNext());
        model.addAttribute("nextPage", products.getNumber()+1);
        model.addAttribute("prevPage", products.getNumber()-1);
        model.addAttribute("maxPages", products.getTotalPages());
        return "manageProducts";
    }

    @GetMapping("/manageCoupons")
    public String coupons(Model model){
        Page<Coupon> coupons = couponService.findAll(PageRequest.of(0, 10));
        model.addAttribute("coupons", coupons);
        model.addAttribute("hasPrev", coupons.hasPrevious());
        model.addAttribute("hasNext", coupons.hasNext());
        model.addAttribute("nextPage", coupons.getNumber()+1);
        model.addAttribute("prevPage", coupons.getNumber()-1);
        model.addAttribute("maxPages", coupons.getTotalPages());


        return "manageCoupons"; 
    }
    
    @GetMapping("/manageUsers")
    public String users(Model model){
        Page<ShopUser> users = userService.findAll(PageRequest.of(0, 1));
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
