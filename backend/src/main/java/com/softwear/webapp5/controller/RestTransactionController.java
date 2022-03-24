package com.softwear.webapp5.controller;

import java.util.List;
import java.util.Optional;

import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.model.Transaction;
import com.softwear.webapp5.repository.ProductRepository;
import com.softwear.webapp5.service.ProductService;
import com.softwear.webapp5.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpOr;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestTransactionController {

    @Autowired
    TransactionService transactionService;
    @Autowired
    ProductService productService;

    @GetMapping("/cart") //GET
    public ResponseEntity<List<Transaction>> getCart(){
        return ResponseEntity.ok(transactionService.findByType("CART"));
    }

    @PostMapping("/cart") //ADD cart
    public ResponseEntity<Transaction> addCart(@RequestBody(required=false) Transaction cart){
        Transaction newCart;
        if(cart != null)
            newCart = new Transaction(cart);
        else
            newCart = new Transaction();
        transactionService.save(newCart);
        return  ResponseEntity.ok(newCart);
    }

    @PostMapping("/cart/{cartId}/product/{productId}") //ADD product to cart
    public ResponseEntity<Transaction> addCart(@PathVariable(value = "cartId") Long cartId, @PathVariable(value = "productId") Long productId){
        Optional<Transaction> oCart = transactionService.findById(cartId);
        Optional<Product> oProd = productService.findById(productId);
        if(oCart.isPresent() && oProd.isPresent()){
            Transaction cart = oCart.get();
            Product product = oProd.get();
            cart.getProducts().add(product);
            transactionService.save(cart);
            return ResponseEntity.ok(cart);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/cart/{id}") //EDIT
    public ResponseEntity<Transaction> editCart(@PathVariable(value = "id") Long id, @RequestBody Transaction cart){
        Optional<Transaction> oNewCart = transactionService.findById(id);
        if(oNewCart.isPresent()){
            Transaction newCart = oNewCart.get();

            newCart.setUser(cart.getUser());
            newCart.setProducts(cart.getProducts());
            newCart.setDate(cart.getDate());
            newCart.setTotalPrice(cart.getTotalPrice());
            newCart.setUsedCoupon(cart.getUsedCoupon());
        
            transactionService.save(newCart);
            return ResponseEntity.ok(newCart);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/cart/{id}") //DELETE
    public ResponseEntity<Transaction> removeCart(@PathVariable(value = "id") Long id){
        Optional<Transaction> oCart = transactionService.findById(id);
        if(oCart.isPresent()){
            Transaction cart = oCart.get();
            transactionService.delete(cart);
            //Fail when trying to return cart
            return ResponseEntity.ok(cart);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/wishlist") //GET
    public ResponseEntity<List<Transaction>> getWishlist(){
        return ResponseEntity.ok(transactionService.findByType("WISHLIST"));
    }

    @PostMapping("/wishlist") //ADD wishlist
    public ResponseEntity<Transaction> addWishlist(@RequestBody(required=false) Transaction wishlist){
        Transaction newWishlist;
        if(wishlist != null)
            newWishlist = new Transaction(wishlist);
        else
            newWishlist = new Transaction();
        transactionService.save(newWishlist);
        return ResponseEntity.ok(newWishlist);
    }

    @PostMapping("/wishlist/{wishlistId}/product/{productId}") //ADD product to wishlist
    public ResponseEntity<Transaction> addWishlist(@PathVariable(value = "wishlistId") Long wishlistId, @PathVariable(value = "productId") Long productId){
        Optional<Transaction> oWishlist = transactionService.findById(wishlistId);
        Optional<Product> oProd = productService.findById(productId);
        if(oWishlist.isPresent() && oProd.isPresent()){
            Transaction wishlist = oWishlist.get();
            Product product = oProd.get();
            wishlist.getProducts().add(product);
            transactionService.save(wishlist);
            return ResponseEntity.ok(wishlist);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/wishlist/{id}") //EDIT
    public ResponseEntity<Transaction> editWishlist(@PathVariable(value = "id") Long id, @RequestBody Transaction wishlist){
        Optional<Transaction> oNewWishlist = transactionService.findById(id);
        if(oNewWishlist.isPresent()){
            Transaction newWishlist = oNewWishlist.get();

            newWishlist.setUser(wishlist.getUser());
            newWishlist.setProducts(wishlist.getProducts());
            newWishlist.setDate(wishlist.getDate());
            newWishlist.setTotalPrice(wishlist.getTotalPrice());
            newWishlist.setUsedCoupon(wishlist.getUsedCoupon());
        
            transactionService.save(newWishlist);
            return ResponseEntity.ok(newWishlist);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/wishlist/{id}") //DELETE
    public ResponseEntity<Transaction> removeWishlist(@PathVariable(value = "id") Long id){
        Optional<Transaction> oWishlist = transactionService.findById(id);
        if(oWishlist.isPresent()){
            Transaction wishlist = oWishlist.get();
            transactionService.delete(wishlist);
            return ResponseEntity.ok(wishlist);
        }
        return ResponseEntity.notFound().build();
    }
}