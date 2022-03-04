package com.softwear.webapp5.controller;

import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.softwear.webapp5.service.MailService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private ProductService productService;

    @GetMapping("/") //Missing id
    public String index(Model model, Pageable pageable) {
        Page<Product> products = productService.findAll(pageable);
        model.addAttribute("products", products);
        return "index";
    }

	@GetMapping("/productView") //Missing id
	public String product(Model model) {
	    return "productView";
	}
	
	@GetMapping("/about") //Missing id
	public String about(Model model) {
		model.addAttribute("activeMenu", "about");
	    return "about";
	}
}
