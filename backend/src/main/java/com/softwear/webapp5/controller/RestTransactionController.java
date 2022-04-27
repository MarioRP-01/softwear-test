package com.softwear.webapp5.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.softwear.webapp5.data.IdDTO;
import com.softwear.webapp5.data.TransactionPageDTO;
import com.softwear.webapp5.data.TransactionType;
import com.softwear.webapp5.model.Coupon;
import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.model.ShopUser;
import com.softwear.webapp5.model.Transaction;
import com.softwear.webapp5.repository.CouponRepository;
import com.softwear.webapp5.repository.ProductRepository;
import com.softwear.webapp5.repository.UserRepository;
import com.softwear.webapp5.service.ProductService;
import com.softwear.webapp5.service.TransactionService;
import com.softwear.webapp5.service.UserService;

import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/transactions")
public class RestTransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CouponRepository couponRepository;

    @GetMapping("/{id}") //GET
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id){
        Optional<Transaction> oTrans = transactionService.findById(id);
        if(oTrans.isPresent())
            return ResponseEntity.ok(oTrans.get());
        return ResponseEntity.notFound().build();
    }

    /*
    @GetMapping("/transactions") //GET
    public ResponseEntity<List<Transaction>> getTransactions(@RequestParam(required = false) Integer page){
        if(page == null)
            return ResponseEntity.ok(transactionService.findAll());
        
        if(page < 1)
            return ResponseEntity.badRequest().build();
        List<Transaction> listTrans = transactionService.findAll(PageRequest.of(page - 1, 3)).toList();
        return ResponseEntity.ok(listTrans);
    }
    */

    @GetMapping(value = "")
    public ResponseEntity<TransactionPageDTO> getSpecialTransaction(@RequestParam(required = false) String type,
            @RequestParam(required = false) Integer page) {

        Pageable pageable;

        if (page == null) {
            pageable = Pageable.unpaged();

        } else {
            pageable = PageRequest.of(page - 1, 10);

        }
        
        Page<Transaction> transactions;

        if (type == null) {
            transactions = transactionService.findAll(pageable);           

        } else {
            boolean isValidType = EnumUtils.isValidEnum(TransactionType.class, type.toUpperCase());

            if (!isValidType) {
                return ResponseEntity.badRequest().build();
            }

            transactions = transactionService.findByType(type.toUpperCase(),pageable);
        }

        int totalPages = transactions.getTotalPages();

        TransactionPageDTO transactionPageDTO = new TransactionPageDTO(transactions.toList(), totalPages);  
        return ResponseEntity.ok(transactionPageDTO);
    }

    /*
    @GetMapping("/transactions/carts") //GET
    public ResponseEntity<List<Transaction>> getCart(@RequestParam(required = false) Integer page){
        List<Transaction> listTrans = getTransaction("CART", page);
        if(listTrans == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(listTrans);
    }

    @GetMapping("/transactions/wishlists") //GET
    public ResponseEntity<List<Transaction>> getWishlist(@RequestParam(required = false) Integer page){
        List<Transaction> listTrans = getTransaction("WISHLIST", page);
        if(listTrans == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(listTrans);
    }

    @GetMapping("/transactions/processed") //GET
    public ResponseEntity<List<Transaction>> getProcessed(@RequestParam(required = false) Integer page){
        List<Transaction> listTrans = getTransaction("PROCESSED", page);
        if(listTrans == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(listTrans);
    }
    */

    @GetMapping(value = "", params = {"user"})
    public ResponseEntity<TransactionPageDTO> getTransactionByUser(@RequestParam Long userId, 
            @RequestParam(required = false) String type, @RequestParam(required = false) Integer page) {

        if (type != null) {
            boolean isValidType = EnumUtils.isValidEnum(TransactionType.class, type.toUpperCase());

            if (!isValidType) {
                return ResponseEntity.badRequest().build();
            }
        }         
                
        TransactionPageDTO transactionPageDTO = transactionService.getUserTransaction(userId, type, page);
        return ResponseEntity.ok(transactionPageDTO);
    }

    @GetMapping("/my")
    public ResponseEntity<TransactionPageDTO> getMyTransactions(
        HttpServletRequest request,
        @RequestParam (required = false) String type,
        @RequestParam (required = false) Integer page) {

        if (type != null) {
            boolean isValidType = EnumUtils.isValidEnum(TransactionType.class, type.toUpperCase());

            if (!isValidType) {
                return ResponseEntity.badRequest().build();
            }
        }

        String username = request.getUserPrincipal().getName();
        ShopUser user = userService.findByUsername(username).get();

        TransactionPageDTO transactionPageDTO = transactionService.getUserTransaction(user.getId(), type, page);
        return ResponseEntity.ok(transactionPageDTO);
    }

    @PostMapping("") //ADD cart, wishlist or processed transaction
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
        Transaction newTransaction;
        ShopUser user;
        List<Product> products = new ArrayList<>();
        if(transaction != null){
            newTransaction = new Transaction();
            //We get the users by id
            user = getDbUserFromIdUserInTransaction(transaction);
            if(user == null)
                return ResponseEntity.notFound().build();
            else
                newTransaction.setUser(user);

            //We get the products by id
            products = getDbProductsFromIdProductsInTransaction(transaction);
            if(products == null)
                return ResponseEntity.notFound().build();
            else
                newTransaction.setProducts(products);

            //We get the coupon by id
            newTransaction.setUsedCoupon(getDbCouponFromIdCouponInTransaction(transaction));

            newTransaction.setType(transaction.getType());
            newTransaction.setDate(transaction.getDate());
        
            transactionService.save(newTransaction);
        }
        else //Cant save null transactions (many fields not nullables)
            return ResponseEntity.badRequest().build();
        transactionService.save(newTransaction);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newTransaction.getId()).toUri();
        return ResponseEntity.created(location).body(newTransaction);
    }


    @PostMapping(value = "/my/products", params = {"type", "amount"})
    public ResponseEntity<Product> addProductToOwnTransaction(
        @RequestParam String type,
        @RequestParam Integer amount,
        @RequestBody IdDTO productId,
        HttpServletRequest request) {
            
        String username = request.getUserPrincipal().getName();
        ShopUser user = userService.findByUsername(username).get();

        Optional<Product> productOptional = productService.findById(productId.getId());

        if (productOptional.isPresent()) {

            Transaction transaction;

            if (type.equals("wishlist")) {
                transaction = transactionService.findWishlist(user).get();
    
            } else if (type.equals("cart")) {
                transaction = transactionService.findCart(user).get();
    
            } else {
                return ResponseEntity.badRequest().build();

            }

            if (0 < amount && amount < productOptional.get().getStock()) {

                for (int i = 0; i < amount; i++) {

                    transaction.getProducts().add(productOptional.get());
                }

            } else return ResponseEntity.badRequest().build();

            transactionService.save(transaction);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

            return ResponseEntity.created(location).body(productOptional.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{transactionId}/products")
    public ResponseEntity<Transaction> addProductToTransaction(
        @PathVariable Long transactionId,
        @RequestBody IdDTO productId) {

        Optional<Transaction> transactionOptional = transactionService.findById(transactionId);
        Optional<Product> productOptional = productService.findById(productId.getId());

        if(transactionOptional.isPresent() && productOptional.isPresent()){

            Transaction transaction = transactionOptional.get();
            Product product = productOptional.get();

            transaction.getProducts().add(product);
            transactionService.save(transaction);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(transaction.getId()).toUri();

            return ResponseEntity.created(location).body(transaction);
        }

        return ResponseEntity.notFound().build();
    }



    /*
    @PostMapping("/transactions/{transactionId}/products/{productId}") //ADD product to transaction
    public ResponseEntity<Transaction> addProductToTransaction(@PathVariable(value = "transactionId") Long transactionId, @PathVariable(value = "productId") Long productId){

        Optional<Transaction> oTransaction = transactionService.findById(transactionId);
        Optional<Product> oProd = productService.findById(productId);

        if(oTransaction.isPresent() && oProd.isPresent()){

            Transaction transaction = oTransaction.get();
            Product product = oProd.get();

            transaction.getProducts().add(product);
            transactionService.save(transaction);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(transaction.getId()).toUri();

            return ResponseEntity.created(location).body(transaction);
        }

        return ResponseEntity.notFound().build();
    }
    */

 

    @PutMapping("/{id}") //EDIT (overwrite transaction)
    public ResponseEntity<Transaction> editTransaction(
        @PathVariable(value = "id") Long id, 
        @RequestBody Transaction transaction){ 
        //"Cannot construct instance of ShopUser" https://localhost:8443/api/transaction/12?type=WISHLIST&user=1&date=10/1/2034&totalPrice=&products=2

        Optional<Transaction> oNewTransaction = transactionService.findById(id);
        ShopUser user;
        List<Product> products = new ArrayList<>();

        if(oNewTransaction.isPresent()){
            Transaction newTransaction = oNewTransaction.get();

            //We get the users by id
            user = getDbUserFromIdUserInTransaction(transaction);
            if(user == null)
                return ResponseEntity.notFound().build();
            else
                newTransaction.setUser(user);

            //We get the products by id
            products = getDbProductsFromIdProductsInTransaction(transaction);
            if(products == null)
                return ResponseEntity.notFound().build();
            else
                newTransaction.setProducts(products);

            //We get the coupon by id
            newTransaction.setUsedCoupon(getDbCouponFromIdCouponInTransaction(transaction));

            newTransaction.setType(transaction.getType());
            newTransaction.setDate(transaction.getDate());
        
            transactionService.save(newTransaction);
            return ResponseEntity.ok(newTransaction);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}") //DELETE
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

    @DeleteMapping(value="/my/products", params={"type"})
    public ResponseEntity<Transaction> deleteAllProductsFromOwnTransaction(
        @RequestParam String type,
        HttpServletRequest request) {

        String username = request.getUserPrincipal().getName();
        ShopUser user = userService.findByUsername(username).get();

        Transaction transaction;

        if (type.equals("wishlist")) {
            transaction = transactionService.findWishlist(user).get();
            

        } else if (type.equals("cart")) {
            transaction = transactionService.findCart(user).get();

        } else {

            return ResponseEntity.badRequest().build();
        }
        
        transaction.getProducts().clear();
        transactionService.save(transaction);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        return ResponseEntity.created(location).body(transaction);
    }

        

    @DeleteMapping(value="/my/products/{productId}", params={"type"})
    public ResponseEntity<Product> deleteProductFromOwnTransaction(
        @PathVariable Long productId,
        @RequestParam String type,
        @RequestParam(required = false) Integer amount,
        HttpServletRequest request) {
        
        String username = request.getUserPrincipal().getName();
        ShopUser user = userService.findByUsername(username).get();

        Optional<Product> productOptional = productService.findById(productId);

        if (productOptional.isPresent()) {

            Transaction transaction;

            if (type.equals("wishlist")) {
                transaction = transactionService.findWishlist(user).get();
    
            } else if (type.equals("cart")) {
                transaction = transactionService.findCart(user).get();
    
            } else {
                return ResponseEntity.badRequest().build();

            }

            if (transaction.getProducts().contains(productOptional.get())) {

                if (amount == null) {
                    transaction.getProducts().removeAll(Collections.singletonList(productOptional.get()));

                } else if (amount > 0){

                    int count = 0;
                    do {
                        transaction.getProducts().remove(productOptional.get());                        
                        count++;

                    } while (transaction.getProducts().contains(productOptional.get()) && count < amount);

                } else {
                    return ResponseEntity.badRequest().build();
                    
                }

            } else ResponseEntity.notFound().build();

            transactionService.save(transaction);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

            return ResponseEntity.created(location).body(productOptional.get());

        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{transactionId}/products/{productId}") //DELETE product from transaction
    public ResponseEntity<Product> deleteProductFromTransaction(
        @PathVariable Long transactionId,
        @PathVariable Long productId){

        Optional<Transaction> oTransaction = transactionService.findById(transactionId);
        Optional<Product> oProd = productService.findById(productId);

        if(oTransaction.isPresent() && oProd.isPresent()){
            Transaction transaction = oTransaction.get();
            Product product = oProd.get();

            if(transaction.getProducts().contains(product))
                transaction.getProducts().remove(product);

            else
                ResponseEntity.notFound().build();

            transactionService.save(transaction);
            return ResponseEntity.ok(product);

        }
        return ResponseEntity.notFound().build();
    }

    private ShopUser getDbUserFromIdUserInTransaction(Transaction transaction) {
        try{ //If its null it throws an exception, if it's not and it exists we return it
            Optional<ShopUser> oUser = userRepository.findById(transaction.getUser().getId());
            if(oUser.isPresent()){
                return oUser.get();
            }else{
                return null;
            }
        }catch(Exception e){
            return null;
        }
    }

    private List<Product> getDbProductsFromIdProductsInTransaction(Transaction transaction) {
        List<Product> products = new ArrayList<>();
        List<Product> auxProducts = new ArrayList<>();
        auxProducts = transaction.getProducts();
        for(Product prod : auxProducts){
            Optional<Product> oProd = productRepository.findById(prod.getId());
            if(oProd.isPresent()){
                products.add(oProd.get());
            }
        }
        return products;
    }

    private Coupon getDbCouponFromIdCouponInTransaction(Transaction transaction) {
        Coupon coupon;
        try{ //If its null it throws an exception, if it's not and it exists we return it
                Optional<Coupon> oCoupon = couponRepository.findById(transaction.getUsedCoupon().getId());
                if(oCoupon.isPresent()){
                    coupon = oCoupon.get();
                    return coupon;
                }
            }catch(Exception e){
            }
        return null;
    }

/*
    private List<Transaction> getTransaction(String type, Integer page) {
        if(page != null){
            if(page < 1)
                return null;
            return transactionService.findByType(type.toUpperCase(), PageRequest.of(page - 1, 10)).toList();
        }
        return transactionService.findByType(type.toUpperCase());
    }
*/    
    
}