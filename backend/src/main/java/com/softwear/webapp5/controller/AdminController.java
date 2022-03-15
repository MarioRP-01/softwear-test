package com.softwear.webapp5.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.softwear.webapp5.data.ProductView;
import com.softwear.webapp5.model.Coupon;
import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.model.ShopUser;
import com.softwear.webapp5.service.CouponService;
import com.softwear.webapp5.service.MailService;
import com.softwear.webapp5.service.ProductService;
import com.softwear.webapp5.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@GetMapping("/mailTry")
	public String mailTest(Model model) {
		MailService ms = new MailService();
		try {
			ms.send("p.pinillos.2019@alumnos.urjc.es", "Trying..", "Does this work?");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return "index";
	}

	@GetMapping("/manageProducts")
    public String products(Model model){
		Page<Product> products = productService.findAll(PageRequest.of(0, 10));
        List<ProductView> listProduct= new ArrayList<>();
        for(Product p: products) {
        	listProduct.add(new ProductView(p));
        }
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
        // Page<Coupon> coupons = CouponService.findAll(PageRequest.of(0, 10));
        List<Coupon> coupons = couponService.findAll();
        model.addAttribute("coupons", coupons);
        // model.addAttribute("id", coupons.get(0).getId());
        // model.addAttribute("code", coupons.get(0).getCode());
        // model.addAttribute("type", coupons.get(0).getType());
        // model.addAttribute("startDate", coupons.get(0).getStartDate());
        // model.addAttribute("minumum", coupons.get(0).getMinimum());
        // model.addAttribute("discount", coupons.get(0).getDiscount());
        // model.addAttribute("dateOfExpiry", coupons.get(0).getDateOfExpiry());


        return "manageCoupons"; 
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
