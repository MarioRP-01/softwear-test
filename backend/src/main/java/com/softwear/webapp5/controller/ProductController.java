package com.softwear.webapp5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.service.ProductService;

@Controller
public class ProductController {
    
    @Autowired
    private ProductService productService;
    

    @GetMapping("/productView/{id}")
    public String getProduct(@PathVariable long id, Model model) {
        Product product = productService.findById(id).orElseThrow();
        model.addAttribute("product", product);
        return "productView";
    }

}