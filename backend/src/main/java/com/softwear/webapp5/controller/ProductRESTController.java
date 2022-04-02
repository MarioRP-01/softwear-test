package com.softwear.webapp5.controller;

import com.softwear.webapp5.data.ProductSize;
import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.service.ProductService;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api")
public class ProductRESTController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/product", params = "id")
    public ResponseEntity<Product> getProduct(@RequestParam Long id) {

        Optional<Product> product = productService.findById(id);

        if (product.isPresent()){
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
        } 
    }

    @GetMapping(value = "/product", params = {"name", "size"})
    public ResponseEntity<Product> getProduct(@RequestParam String name, @RequestParam String size){
        
        ProductSize productSize = ProductSize.stringToProductSize(size);
        Optional<Product> product = productService.findByNameAndSize(name, productSize);

        if (product.isPresent()){
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    @GetMapping("/product/{productId}/image/{imageIndex}")
    public ResponseEntity<Object> getProductImage(@PathVariable Long productId, @PathVariable int imageIndex) throws SQLException {
        Product product = productService.findById(productId).orElseThrow();
        List<Blob> images = product.getImageFiles();
        if (product.getImageFiles() != null && images.size() > imageIndex) {
            Blob image = product.getImageFile(imageIndex);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "image/*")
                    .contentLength(image.length())
                    .body(image.getBytes(1L, (int) image.length()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/product/{productId}/image/{imageIndex}")
    public ResponseEntity<Object> saveProductImage(@PathVariable Long productId, @PathVariable int imageIndex,
                                                   @RequestParam MultipartFile imageFile) throws IOException {
        Product product = productService.findById(productId).orElseThrow();
        URI location = fromCurrentRequest().build().toUri();
        product.addImage(location.toString());
        product.addImageFile(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
        productService.save(product);
        return ResponseEntity.created(location).build();
    }

}
