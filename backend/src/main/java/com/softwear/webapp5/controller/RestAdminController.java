package com.softwear.webapp5.controller;

import java.util.Optional;

import com.softwear.webapp5.model.ShopUser;
import com.softwear.webapp5.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apiadmin")
public class RestAdminController {

    @Autowired
    UserService userService;
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
}
