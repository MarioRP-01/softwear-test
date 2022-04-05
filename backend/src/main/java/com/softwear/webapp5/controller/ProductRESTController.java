package com.softwear.webapp5.controller;

import com.softwear.webapp5.data.ProductNoImagesDTO;
import com.softwear.webapp5.data.ProductSize;
import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.service.ProductService;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/products")
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

    @GetMapping("")
    public ResponseEntity<List<Product>> getProduct(@RequestParam(required = false) String name, @RequestParam(required = false) String size, 
    @RequestParam(required = false) Integer page){
        
        if(name == null || size == null){
            if(page == null)
                return ResponseEntity.ok(productService.findAll());
            else{
                if(page < 1)
                    return ResponseEntity.badRequest().build();
                else
                    return ResponseEntity.ok(productService.findAll(PageRequest.of(page - 1, 1)).toList());
            }
        }

        ProductSize productSize = ProductSize.stringToProductSize(size);
        Optional<Product> product = productService.findByNameAndSize(name, productSize);

        if (product.isPresent()){
            List<Product> lp = new ArrayList<Product>();
            lp.add(product.get());
            return ResponseEntity.ok(lp);
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
            @RequestBody ProductNoImagesDTO newProduct) {

        Optional<Product> product = productService.findById(id);

        if (product.isPresent()) {
            Product updatedProduct = productService.updateProduct(product.get(), newProduct);
            productService.save(updatedProduct);

            return ResponseEntity.ok(updatedProduct);
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
                    .header(HttpHeaders.CONTENT_TYPE, "image/webp")
                    .contentLength(image.length())
                    .body(image.getBytes(1L, (int) image.length()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{productId}/image")
    public ResponseEntity<Object> saveProductImage(@PathVariable Long productId,
                @RequestParam MultipartFile imageFile) throws IOException, URISyntaxException {

        Optional<Product> productOptional = productService.findById(productId);

        if (productOptional.isPresent()){
            Product product = productOptional.get();

            URI currentLocation = fromCurrentRequest().build().toUri();
            String imageIndex = Integer.toString(product.getImages().size());
            URI location = productService.extendURI(currentLocation, imageIndex);

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

            List<Product> productsWithSameImages = productService.deleteImage(productOptional.get(), imageIndex);
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
