package com.softwear.webapp5.controller;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.softwear.webapp5.data.Size;
import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.model.ShopUser;
import com.softwear.webapp5.service.MailService;
import com.softwear.webapp5.service.ProductService;
import com.softwear.webapp5.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    PasswordEncoder passwordEncoder;


    @PostMapping("/manageUsers")
    public ShopUser users(@RequestParam String mode, @RequestParam(required = false) Long id, @RequestParam(required = false) String username, 
    @RequestParam(required = false) String password, @RequestParam(required = false) String email, 
    @RequestParam(required = false) String name, @RequestParam(required = false) String lastName, 
    @RequestParam(required = false) String address, @RequestParam(required = false) Integer mobileNumber,
    @RequestParam(required = false) String birthdate, 
    @RequestParam(required = false) String role){

        if(mode.equals("EDIT")){
            Logger log = LoggerFactory.getLogger(SampleLogController.class);
            Optional<ShopUser> oOldUser = userService.findById(id);
            log.info(oOldUser.toString());
            if(oOldUser.isPresent()){
                ShopUser oldUser = oOldUser.get();
                if(!password.equals(""))
                    password = passwordEncoder.encode(password);
                else
                    password = oldUser.getPassword();
                ShopUser newUser = new ShopUser(username, email, name, lastName, password, address, mobileNumber, birthdate, role);
                userService.updateInfo(oldUser, newUser);
                log.info(String.valueOf(oldUser.getId()));
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
    @RequestParam(required = false) String description, @RequestParam(required = false) float price,
    @RequestParam(required = false) int stock, @RequestParam(required = false) Size size,
    @RequestParam(required = false) ArrayList<File> imgs){

        if(mode.equals("EDIT")){
            // Logger log = LoggerFactory.getLogger(SampleLogController.class);
            // Optional<ShopUser> oOldUser = userService.findById(id);
            // log.info(oOldUser.toString());
            // if(oOldUser.isPresent()){
            //     ShopUser oldUser = oOldUser.get();
            //     if(!password.equals(""))
            //         password = passwordEncoder.encode(password);
            //     else
            //         password = oldUser.getPassword();
            //     ShopUser newUser = new ShopUser(username, email, name, lastName, password, address, mobileNumber, birthdate, role);
            //     userService.updateInfo(oldUser, newUser);
            //     log.info(String.valueOf(oldUser.getId()));
            //     return oldUser;
                return null;
            // }
            
        }else if(mode.equals("ADD")){
            // password = passwordEncoder.encode(password);
            // ShopUser newUser = new ShopUser(username, email, name, lastName, password, address, mobileNumber, birthdate, role);
            // userService.save(newUser);
            // return newUser;

        }else if(mode.equals("DELETE")){
            productService.deleteProduct(id);
            return null;
        }

        return null;
    }
}