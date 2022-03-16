package com.softwear.webapp5.controller;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.softwear.webapp5.data.CouponView;
import com.softwear.webapp5.data.ProductSize;
import com.softwear.webapp5.data.ProductView;
import com.softwear.webapp5.model.Coupon;
import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.model.ShopUser;
import com.softwear.webapp5.service.CouponService;
import com.softwear.webapp5.service.MailService;
import com.softwear.webapp5.service.ProductService;
import com.softwear.webapp5.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apiadmin")
public class RestAdminController {

    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    CouponService couponService;
    @Autowired
    PasswordEncoder passwordEncoder;


    @PostMapping("/manageUsers")
    public ShopUser users(@RequestParam String mode, @RequestParam(required = false) Long id, @RequestParam(required = false) String username, 
    @RequestParam(required = false) String password, @RequestParam(required = false) String email, 
    @RequestParam(required = false) String name, @RequestParam(required = false) String lastName, 
    @RequestParam(required = false) String address, @RequestParam(required = false) Integer mobileNumber,
    @RequestParam(required = false) String birthdate, 
    @RequestParam(required = false) String role){

        if(mode.equals("EDIT")){
            Optional<ShopUser> oOldUser = userService.findById(id);
            if(oOldUser.isPresent()){
                ShopUser oldUser = oOldUser.get();
                if(!password.equals(""))
                    password = passwordEncoder.encode(password);
                else
                    password = oldUser.getPassword();
                ShopUser newUser = new ShopUser(username, email, name, lastName, password, address, mobileNumber, birthdate, role);
                userService.updateInfo(oldUser, newUser);
                return oldUser;
            }
            
        }else if(mode.equals("ADD")){
            password = passwordEncoder.encode(password);
            ShopUser newUser = new ShopUser(username, email, name, lastName, password, address, mobileNumber, birthdate, role);
            userService.save(newUser);
            return newUser;

        }else if(mode.equals("DELETE")){
            userService.delete(id);
            return null;
        }

        return null;
    }

    @PostMapping("/manageProducts")
    public Product products(@RequestParam String mode, @RequestParam(required = false) Long id, @RequestParam(required = false) String name, 
    @RequestParam(required = false) String description, @RequestParam(required = false) String price,
    @RequestParam(required = false) String stock, @RequestParam(required = false) String size,
    @RequestParam(required = false) ArrayList<File> imgs){
        Logger log = LoggerFactory.getLogger(SampleLogController.class);
        log.info("llega");
        if(mode.equals("EDIT")){
            Optional<Product> oOldProduct = productService.findById(id);
            if(oOldProduct.isPresent()){
                Product oldProduct = oOldProduct.get();
                Product newProduct = new Product(name, description, Double.valueOf(price), Long.valueOf(stock), ProductSize.valueOf(size), imgs);
                productService.updateInfo(oldProduct, newProduct);
                log.info(String.valueOf(oldProduct.getId()));
                return oldProduct;
            }
        }else if(mode.equals("ADD")){
            Product newProduct = new Product(name, description, Double.valueOf(price), Long.valueOf(stock), ProductSize.valueOf(size), imgs);
            productService.save(newProduct);
            return newProduct;
        }else if(mode.equals("DELETE")){
            productService.deleteProduct(id);
            return null;
        }
        return null;
    }


    @PostMapping("/manageCoupons")
    public Coupon coupons(@RequestParam String mode, @RequestParam(required = false) Long id, @RequestParam(required = false) String code,
    @RequestParam(required = false) String type, @RequestParam(required = false) String startDate,
    @RequestParam(required = false) String dateOfExpiry, @RequestParam(required = false) String minimum,
    @RequestParam(required = false) String discount, @RequestParam(required = false) String affectedProductsIDs){

        ArrayList<Product> affectedProducts = new ArrayList<>();
        if(mode.equals("EDIT") || mode.equals("ADD")){ //If we need data besides the ID and mode, we make some checking beforehand
            if(affectedProductsIDs.equals("All") || affectedProductsIDs.equals("")){
                affectedProducts = null;
            }else{
                String[] ids = affectedProductsIDs.split(",");
                for(String i : ids){
                    Optional<Product> oProductAux = productService.findById(Long.valueOf(i.trim()));
                    if(oProductAux.isPresent())
                        affectedProducts.add(oProductAux.get());
                }
            }
            if(minimum.equals(""))
                minimum = "0";
            if(discount.equals(""))
                discount = "0";
        }
        if(mode.equals("EDIT")){
            Optional<Coupon> oOldCoupon = couponService.findById(id);
            if(oOldCoupon.isPresent()){
                Coupon oldCoupon = oOldCoupon.get();
                Coupon newCoupon = new Coupon(code, type, startDate, dateOfExpiry, Float.valueOf(minimum), Float.valueOf(discount), affectedProducts);
                couponService.updateInfo(oldCoupon, newCoupon);
                return oldCoupon;
            }
        }else if(mode.equals("ADD")){
            Coupon newCoupon = new Coupon(code, type, startDate, dateOfExpiry, Float.valueOf(minimum), Float.valueOf(discount), affectedProducts);
            couponService.addCoupon(newCoupon);
            return newCoupon;
        
        }else if(mode.equals("DELETE")){
            couponService.deleteCoupon(id);
            return null;
        }
        return null;
    }
    
    @GetMapping("/manageProducts/{pageNumber}")
    public List<ProductView> users(Model model, @PathVariable int pageNumber){
        Page<Product> productPage = productService.findAll(PageRequest.of(pageNumber, 10));
        List<ProductView> listProduct= new ArrayList<>();
        for(Product p: productPage) {
        	listProduct.add(new ProductView(p));
        }
        return listProduct;
    }

    
    @GetMapping("/manageCoupons/{pageNumber}")
    public List<CouponView> coupons(Model model, @PathVariable int pageNumber){
    	Page<Coupon> coupons = couponService.findAll(PageRequest.of(pageNumber, 10));
    	List<CouponView> listCoupon= new ArrayList<>();
    	for(Coupon c: coupons) {
    		listCoupon.add(new CouponView(c));
    	}
    	return listCoupon;
    }
}