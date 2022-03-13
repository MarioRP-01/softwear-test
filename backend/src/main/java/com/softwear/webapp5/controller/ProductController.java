package com.softwear.webapp5.controller;

import com.softwear.webapp5.model.ShopUser;
import com.softwear.webapp5.service.TransactionService;
import com.softwear.webapp5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.softwear.webapp5.data.ProductAvailabilityBySize;
import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.service.ProductService;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;
    
    @GetMapping("/productView/{id}")
    public String getProduct(@PathVariable long id, Model model) {
        Product product = productService.findById(id).orElseThrow();
		File firstImg = productService.getFirstImg(product);
		ArrayList<File> nonFirstImgs = productService.getNonFirstImgs(product);
		
		List<ProductAvailabilityBySize> availableSizesStatus; 
		availableSizesStatus = productService.getAvailableSizesStatus(product);

		//TODO rutas imagenes
		model.addAttribute("availableSizesStatus", availableSizesStatus);		
        model.addAttribute("product", product);
        model.addAttribute("inStock", product.getStock() > 0);
        model.addAttribute("lowStock", product.getStock() <= 30);
		model.addAttribute("firstImg", firstImg);
		model.addAttribute("nonFirstImgs", nonFirstImgs);

        if((boolean) model.getAttribute("logged")) {
            model.addAttribute("inWishlist", transactionService.findProductInWishlist(userService.findByUsername((String) model.getAttribute("username")).get(), product.getName()).isPresent());
        }

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

