package com.softwear.webapp5.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/product/{id}/image/{imageIndex}")
    public ResponseEntity<Object> productImage(HttpServletResponse response, @PathVariable Long id, @PathVariable int imageIndex) throws IOException, SQLException {
        Optional<Product> optProd = productService.findById(id);
        if (optProd.isPresent()) {
            Product product = optProd.get();
            if (product.getImageFile(imageIndex) != null) {
                Resource file = new InputStreamResource(
                        product.getImageFile(imageIndex).getBinaryStream());
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                        .contentLength(product.getImageFile(imageIndex).length())
                        .body(file);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
