package com.softwear.webapp5.controller;

import com.softwear.webapp5.data.ProductSize;
import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ProductRESTController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public Product product(@RequestParam(required = false) Long id, @RequestParam(required = false) String name, @RequestParam(required = false) String size) {
        Optional<Product> product;
        if(id != null) {
            product = productService.findById(id);
            if(product.isPresent()) {
                return product.get();
            }
        } else if(name != null && size != null) {
            ProductSize pSize = ProductSize.stringToProductSize(size);
            product = productService.findByNameAndSize(name, pSize);
            if(product.isPresent()) {
                return product.get();
            }
        }
        return null;
    }

}
