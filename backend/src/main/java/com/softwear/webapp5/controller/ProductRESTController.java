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
