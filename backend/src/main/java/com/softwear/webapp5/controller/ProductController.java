package com.softwear.webapp5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.service.ProductService;

@Controller
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @GetMapping("/productView/{id}")
    public String getProduct(@PathVariable long id, Model model) {
        Product product = productService.findById(id).orElseThrow();
		String firstImg_route = productService.getFirstImg_rout(product);
		ArrayList<String> nonFirstImg_routes = productService.getNonFirstImg_routes(product);
        model.addAttribute("product", product);
		model.addAttribute("firstImg_route", firstImg_route);
		model.addAttribute("nonFirstImg_routes", nonFirstImg_routes);
        return "productView";
    }

/*
	@PostMapping("/userProfile")
	public ResponseEntity<Product> createItem(@RequestBody Product product) {

		products.save(product);

		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();

		return ResponseEntity.created(location).body(product);
	}
	
	@PutMapping("/{id}")
	public Product replaceItem(@PathVariable long id, @RequestBody Product newItem) {

		products.findById(id).orElseThrow();

		newItem.setId(id);
		products.save(newItem);
			
		return newItem;
	}

	@DeleteMapping("/{id}")
	public Product deleteItem(@PathVariable long id) {

		Product product = products.findById(id).orElseThrow();

		products.deleteById(id);
		
		return product;
	}
*/	
}

