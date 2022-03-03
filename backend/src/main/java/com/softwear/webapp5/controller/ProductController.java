package com.softwear.webapp5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.softwear.webapp5.repository.ProductRepository;

@Controller
public class ProductController {
    
    @Autowired
    private ProductRepository products;
    

    @GetMapping("/productView/{id}")
    public String getProduct(@PathVariable long id, Model model) {
        model.addAttribute("product", products.findById(id).orElseThrow());
        return "productView";
    }

}