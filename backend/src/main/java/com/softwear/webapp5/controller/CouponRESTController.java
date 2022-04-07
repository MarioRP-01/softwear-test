package com.softwear.webapp5.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.softwear.webapp5.model.Coupon;
import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.repository.CouponRepository;
import com.softwear.webapp5.repository.ProductRepository;
import com.softwear.webapp5.service.CouponService;
import com.softwear.webapp5.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
@RequestMapping("/api")
public class CouponRESTController {

    @Autowired
    CouponService couponService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CouponRepository couponRepository;

    @GetMapping("/coupons/{id}") //GET
    public ResponseEntity<Coupon> getTransactionById(@PathVariable Long id){
        Optional<Coupon> oCoupon = couponService.findById(id);
        if(oCoupon.isPresent())
            return ResponseEntity.ok(oCoupon.get());
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/coupons") //GET
    public ResponseEntity<List<Coupon>> getCoupon(@RequestParam(required = false) Integer page){
        if(page != null){
            if(page < 1)
                return ResponseEntity.badRequest().build();
            return ResponseEntity.ok(couponService.findAll(PageRequest.of(page - 1, 1)).toList());
        }
        return ResponseEntity.ok(couponService.findAll());
    }

    @PostMapping("/coupons") //ADD cart, wishlist or processed coupon
    public ResponseEntity<Coupon> addCoupon(@RequestBody Coupon coupon){
        Coupon newCoupon;
        List<Product> products = new ArrayList<>();

        if(coupon != null){
            newCoupon = new Coupon();

            //We get the products by id
            products = getDbProductsFromIdProductsInCoupon(coupon);
            if(products.size() == 0 || coupon.getType().equals("") || coupon.getCode().equals("") || coupon.getDateOfExpiry().equals("") || coupon.getStartDate().equals("")
            || !couponService.areDatesInRange(coupon) )
                return ResponseEntity.notFound().build();
            
            newCoupon.setAffectedProducts(products);
            newCoupon.setType(coupon.getType());
            newCoupon.setCode(coupon.getCode());
            newCoupon.setStartDate(coupon.getStartDate());
            newCoupon.setDateOfExpiry(coupon.getDateOfExpiry());
            newCoupon.setDiscount(coupon.getDiscount());
            newCoupon.setMinimum(coupon.getMinimum());
        
            couponService.save(newCoupon);
        }
        else //Cant save null coupons (many fields not nullables)
            return ResponseEntity.badRequest().build();
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCoupon.getId()).toUri();
        return ResponseEntity.created(location).body(newCoupon);
    }

    @PostMapping("/coupons/{couponId}/products/{productId}") //ADD product to coupon
    public ResponseEntity<Coupon> addProductToCoupon(@PathVariable(value = "couponId") Long couponId, @PathVariable(value = "productId") Long productId){
        Optional<Coupon> oCoupon = couponService.findById(couponId);
        Optional<Product> oProd = productService.findById(productId);
        if(oCoupon.isPresent() && oProd.isPresent()){
            Coupon coupon = oCoupon.get();
            Product product = oProd.get();
            coupon.getAffectedProducts().add(product);
            couponService.save(coupon);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(coupon.getId()).toUri();
            return ResponseEntity.created(location).body(coupon);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/coupons/{couponId}/products/{productId}") //DELETE product from coupon
    public ResponseEntity<Product> deleteProductFromCoupon(@PathVariable(value = "couponId") Long couponId, @PathVariable(value = "productId") Long productId){
        Optional<Coupon> oCoupon = couponService.findById(couponId);
        Optional<Product> oProd = productService.findById(productId);
        if(oCoupon.isPresent() && oProd.isPresent()){
            Coupon coupon = oCoupon.get();
            Product product = oProd.get();
            if(coupon.getAffectedProducts().contains(product))
                coupon.getAffectedProducts().remove(product);
            else
                return ResponseEntity.notFound().build();
            couponService.save(coupon);
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/coupons/{id}") //EDIT (overwrite coupon)
    public ResponseEntity<Coupon> editCoupon(@PathVariable(value = "id") Long id, @RequestBody Coupon coupon){ //"Cannot construct instance of ShopUser" https://localhost:8443/api/coupon/12?type=WISHLIST&user=1&date=10/1/2034&totalPrice=&products=2
        Optional<Coupon> oNewCoupon = couponService.findById(id);
        List<Product> products = new ArrayList<>();

        if(oNewCoupon.isPresent()){
            Coupon newCoupon = oNewCoupon.get();

            products = getDbProductsFromIdProductsInCoupon(coupon);
            if(products == null || coupon.getType().equals("") || coupon.getCode().equals("") || coupon.getDateOfExpiry().equals("") || coupon.getStartDate().equals(""))
                return ResponseEntity.notFound().build();
            
            newCoupon.setAffectedProducts(products);
            newCoupon.setType(coupon.getType());
            newCoupon.setCode(coupon.getCode());
            newCoupon.setStartDate(coupon.getStartDate());
            newCoupon.setDateOfExpiry(coupon.getDateOfExpiry());
            newCoupon.setDiscount(coupon.getDiscount());
            newCoupon.setMinimum(coupon.getMinimum());
        
            couponService.save(newCoupon);
            return ResponseEntity.ok(newCoupon);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/coupons/{id}") //DELETE
    public ResponseEntity<Coupon> removeCoupon(@PathVariable(value = "id") Long id){
        Optional<Coupon> oCoupon = couponService.findById(id);
        if(oCoupon.isPresent()){
            Coupon coupon = oCoupon.get();
            ResponseEntity<Coupon> response = ResponseEntity.ok(coupon);
            couponService.delete(coupon);
            return response;
        }
        return ResponseEntity.notFound().build();
    }

    private List<Product> getDbProductsFromIdProductsInCoupon(Coupon coupon) {
        List<Product> products = new ArrayList<>();
        List<Product> auxProducts = new ArrayList<>();
        auxProducts = coupon.getAffectedProducts();
        for(Product prod : auxProducts){
            Optional<Product> oProd = productRepository.findById(prod.getId());
            if(oProd.isPresent()){
                products.add(oProd.get());
            }
        }
        return products;
    }
}