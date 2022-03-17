package com.softwear.webapp5.controller;

import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String index(Model model, Pageable pageable) {
    	Page<Product> products = productService.findAll(PageRequest.of(0, 3));
        model.addAttribute("products", products);
        model.addAttribute("hasNext", products.hasNext());
        model.addAttribute("nextPage", products.getNumber()+1);
        model.addAttribute("prevPage", products.getNumber()-1);
        model.addAttribute("maxPages", products.getTotalPages());
        return "index";
    }

	@GetMapping("/productView")
	public String product(Model model) {
	    return "productView";
	}
	
	@GetMapping("/about")
	public String about(Model model) {
		model.addAttribute("activeMenu", "about");
	    return "about";
	}
}
