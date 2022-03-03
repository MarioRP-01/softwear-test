package com.softwear.webapp5.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.softwear.webapp5.model.Product;
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