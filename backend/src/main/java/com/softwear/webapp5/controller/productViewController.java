package com.softwear.webapp5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class productViewController {
	@GetMapping("/productView.html")
    public String greeting(Model model) {
        return "productView";
    }
}
