package com.softwear.webapp5.controller;

import java.util.List;
import java.util.Optional;

import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.model.Transaction;
import com.softwear.webapp5.repository.ProductRepository;
import com.softwear.webapp5.service.ProductService;
import com.softwear.webapp5.service.TransactionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    //ONLY RETURNING LAST ELEMENT MATCHING CRITERIA
    @GetMapping("/wishlist") //GET
    public ResponseEntity<List<Transaction>> getWishlist(){
        return ResponseEntity.ok(transactionService.findByType("WISHLIST"));
    }

    @PostMapping("/transaction") //ADD cart, wishlist or processed transaction
    public ResponseEntity<Transaction> addTransaction(Transaction transaction){
        Transaction newTransaction;
        if(transaction != null)
            newTransaction = new Transaction(transaction);
        else //Cant save null transactions (many fields not nullables)
            return ResponseEntity.badRequest().build();
        transactionService.save(newTransaction);
        return  ResponseEntity.ok(newTransaction);
    }

    @PostMapping("/transaction/{transactionId}/product/{productId}") //ADD product to transaction
    public ResponseEntity<Transaction> addProductToTransaction(@PathVariable(value = "transactionId") Long transactionId, @PathVariable(value = "productId") Long productId){
        Optional<Transaction> oTransaction = transactionService.findById(transactionId);
        Optional<Product> oProd = productService.findById(productId);
        if(oTransaction.isPresent() && oProd.isPresent()){
            Transaction transaction = oTransaction.get();
            Product product = oProd.get();
            transaction.getProducts().add(product);
            transactionService.save(transaction);
            return ResponseEntity.ok(transaction);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/transaction/{id}") //EDIT
    public ResponseEntity<Transaction> editTransaction(@PathVariable(value = "id") Long id, @RequestBody Transaction transaction){ //"Required request body is missing" https://localhost:8443/api/transaction/12?id=12&type=WISHLIST&user=1&date=10/1/2034&totalPrice=&products=2
        Optional<Transaction> oNewTransaction = transactionService.findById(id);
        if(oNewTransaction.isPresent()){
            Transaction newTransaction = oNewTransaction.get();

            newTransaction.setUser(transaction.getUser());
            newTransaction.setProducts(transaction.getProducts());
            newTransaction.setDate(transaction.getDate());
            newTransaction.setTotalPrice(transaction.getTotalPrice());
            newTransaction.setUsedCoupon(transaction.getUsedCoupon());
        
            transactionService.save(newTransaction);
            return ResponseEntity.ok(newTransaction);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/transaction/{id}") //DELETE
    public ResponseEntity<Transaction> removeTransaction(@PathVariable(value = "id") Long id){
        Optional<Transaction> oTransaction = transactionService.findById(id);
        if(oTransaction.isPresent()){
            Transaction transaction = oTransaction.get();
            ResponseEntity<Transaction> response = ResponseEntity.ok(transaction);
            Logger log = LoggerFactory.getLogger(SampleLogController.class);
            log.info(transaction.getId().toString());
            transactionService.delete(transaction);
            //Fails when trying to return transaction
            return response;
        }
        return ResponseEntity.notFound().build();
    }
}