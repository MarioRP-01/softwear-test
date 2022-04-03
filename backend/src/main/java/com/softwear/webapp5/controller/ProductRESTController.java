package com.softwear.webapp5.controller;

import com.softwear.webapp5.data.ProductNoImagesDTO;
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
@RequestMapping("/api/product")
public class ProductRESTController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {

        Optional<Product> product = productService.findById(id);

        if (product.isPresent()){
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
        } 
    }

    @GetMapping("/{name}/{size}")
    public ResponseEntity<Product> getProduct(@PathVariable String name, @PathVariable String size){
        
        ProductSize productSize = ProductSize.stringToProductSize(size);
        Optional<Product> product = productService.findByNameAndSize(name, productSize);

        if (product.isPresent()){
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductNoImagesDTO productNoImages) {

        Product product = new Product(productNoImages);
        productService.save(product);

        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();

        return ResponseEntity.created(location).body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable long id){
        
        Optional<Product> product = productService.findById(id);

        if (product.isPresent()) {
            productService.deleteProduct(id);

            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }   

    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable long id,
            @RequestBody Product newProduct) {

        Optional<Product> product = productService.findById(id);

        if (product.isPresent()) {
            newProduct.setId(id);
            productService.save(newProduct);

            return ResponseEntity.ok(newProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    @GetMapping("/{productId}/image/{imageIndex}")
    public ResponseEntity<Object> getProductImage(@PathVariable Long productId,
            @PathVariable int imageIndex) throws SQLException {

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

    @PostMapping("/{productId}/image/{imageIndex}")
    public ResponseEntity<Object> saveProductImage(@PathVariable Long productId, @PathVariable int imageIndex,
                @RequestParam MultipartFile imageFile) throws IOException {

        Optional<Product> productOptional = productService.findById(productId);
        URI location = fromCurrentRequest().build().toUri();

        if (productOptional.isPresent()){
            Product product = productOptional.get();

            product.addImage(location.toString());
            product.addImageFile(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));

            productService.save(product);

            return ResponseEntity.created(location).build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{productId}/image/{imageIndex}")
    public ResponseEntity<Object> deleteImage(@PathVariable Long productId,
            @PathVariable int imageIndex) {
        
        Optional<Product> productOptional = productService.findById(productId);

        if (productOptional.isPresent()) {

            List<Product> productsWithSameImages= productService.deleteImage(productOptional.get(), imageIndex);
            for (Product product : productsWithSameImages)
                productService.save(product);

            return ResponseEntity.noContent().build();
        }
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{productId}/image")
    public ResponseEntity<Object> deleteAllImage(@PathVariable Long productId) {
        
        Optional<Product> productOptional = productService.findById(productId);

        if (productOptional.isPresent()) {

            List<Product> productsWithSameImages= productService.deleteAllImages(productOptional.get());
            for (Product product : productsWithSameImages)
                productService.save(product);

            return ResponseEntity.noContent().build();
        }
            return ResponseEntity.notFound().build();
    }
}
