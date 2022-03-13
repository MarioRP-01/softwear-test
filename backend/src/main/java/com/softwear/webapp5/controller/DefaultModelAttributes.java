package com.softwear.webapp5.controller;

import com.softwear.webapp5.data.TransactionView;
import com.softwear.webapp5.model.ShopUser;
import com.softwear.webapp5.model.Transaction;
import com.softwear.webapp5.service.TransactionService;
import com.softwear.webapp5.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@ControllerAdvice
public class DefaultModelAttributes {

    @Autowired
    private UserService users;

    @Autowired
    private TransactionService transactions;

    @ModelAttribute("logged")
    public boolean logged(HttpServletRequest request) {
        return request.getUserPrincipal() != null;
    }

    @ModelAttribute("username")
    public String username(HttpServletRequest request) {
        if(logged(request)) {
            return request.getUserPrincipal().getName();
        }
        return "";
    }

    @ModelAttribute("role")
    public String role(HttpServletRequest request) {
        if(logged(request)) {
            Optional<ShopUser> oUser = users.findByUsername(request.getUserPrincipal().getName());
            if(oUser.isPresent()) {
                return oUser.get().getRole();
            }
        }
        return "NO_AUTH";
    }
    
    @ModelAttribute("isAdmin")
    public boolean isAdmin(HttpServletRequest request) {
        
        if(logged(request)) {
            Optional<ShopUser> oUser = users.findByUsername(request.getUserPrincipal().getName());
            if(oUser.isPresent()) {
                return oUser.get().getRole().equals("ADMIN");
            }
        }
        return false;
    }

    @ModelAttribute("isUser")
    public boolean isUser(HttpServletRequest request) {
        if(logged(request)) {
            Optional<ShopUser> oUser = users.findByUsername(request.getUserPrincipal().getName());
            if(oUser.isPresent()) {
                return oUser.get().getRole().equals("USER");
            }
        }
        return false;
    }

    @ModelAttribute("nCartItems")
    public int nCartItems(HttpServletRequest request) {
        if(logged(request)) {
            Optional<ShopUser> oUser = users.findByUsername(request.getUserPrincipal().getName());
            if(oUser.isPresent()) {
                Optional<Transaction> oCart = transactions.findCart(oUser.get());
                if(oCart.isPresent()) {
                    return oCart.get().getProducts().size();
                }
            }
        }
        return 0;
    }

}
