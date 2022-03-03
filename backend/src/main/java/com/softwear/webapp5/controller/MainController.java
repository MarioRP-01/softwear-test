package com.softwear.webapp5.controller;

import com.softwear.webapp5.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private ProductService productService;

    @GetMapping("/productView") //Missing id
    public String product(Model model) {
        return "productView";
    }

    @GetMapping("/") //Missing id
    public String index(Model model) {
        model.addAttribute("products", productService.findAll());
        return "index";
    }

    @GetMapping("/about") //Missing id
    public String about(Model model) {
        return "about";
    }

    @GetMapping("/userProfile") //Missing id
    public String user(Model model) {
        return "userProfile";
    }

    @GetMapping("/login") //Missing id
    public String login(Model model) {
        return "login";
    }

}