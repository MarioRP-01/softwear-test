package com.softwear.webapp5.controller;

import com.softwear.webapp5.data.TransactionView;
import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.model.ShopUser;
import com.softwear.webapp5.model.Transaction;
import com.softwear.webapp5.service.ProductService;
import com.softwear.webapp5.service.TransactionService;
import com.softwear.webapp5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    private String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        return Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)) + "/" + Integer.toString(calendar.get(Calendar.MONTH)) + "/" + Integer.toString(calendar.get(Calendar.YEAR));
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        ShopUser user = userService.findByUsername((String) model.getAttribute("username")).get();
        Transaction cart = transactionService.findCart(user).get();
        return "cart";
    }

    @GetMapping("/wishlist")
    public String wishlist(Model model) {
        ShopUser user = userService.findByUsername((String) model.getAttribute("username")).get();
        Optional<Transaction> optWishlist = transactionService.findWishlist(user);
        TransactionView wishlist;
        if(optWishlist.isPresent()) {
            wishlist = new TransactionView(optWishlist.get());
        } else {
            wishlist = new TransactionView();
        }
        model.addAttribute("wishlist", wishlist);
        return "wishlist";
    }

    @PostMapping("/wishlist/add")
    public String wishlistAdd(@RequestParam Long id, Model model) {
        Optional<Product> optionalProduct = productService.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            ShopUser user = userService.findByUsername((String) model.getAttribute("username")).get();
            Optional<Transaction> optWishlist = transactionService.findWishlist(user);
            Transaction wishlist;
            if (optWishlist.isPresent()) {
                wishlist = optWishlist.get();
            } else {
                wishlist = new Transaction("WISHLIST", user, null, getCurrentDate(), new ArrayList<>());
            }
            if (!wishlist.getProducts().contains(product)) {
                wishlist.getProducts().add(product);
                transactionService.save(wishlist);
            }
            return "redirect:/wishlist";
        }
        return "error";
    }

    @PostMapping("/wishlist/delete")
    public String wishlistDelete(@RequestParam Long id, Model model) {
        Optional<Product> optionalProduct = productService.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            ShopUser user = userService.findByUsername((String) model.getAttribute("username")).get();
            Optional<Transaction> optWishlist = transactionService.findWishlist(user);
            if (optWishlist.isPresent()) {
                Transaction wishlist = optWishlist.get();
                while (wishlist.getProducts().contains(product)) {
                    wishlist.getProducts().remove(product);
                    transactionService.save(wishlist);
                }
                return "redirect:/wishlist";
            }
        }
        return "error";
    }

    @PostMapping("/wishlist/empty")
    public String wishlistEmpty(@RequestParam Long id, Model model) {
        ShopUser user = userService.findByUsername((String) model.getAttribute("username")).get();
        Optional<Transaction> optWishlist = transactionService.findWishlist(user);
        if (optWishlist.isPresent()) {
            transactionService.delete(optWishlist.get());
        }
        return "redirect:/wishlist";
    }

    @GetMapping("/purchaseHistory")
    public String purchaseHistory(Model model) {
        ShopUser user = userService.findByUsername((String) model.getAttribute("username")).get();
        List<Transaction> purchaseHistory = transactionService.findPurchaseHistory(user);
        return "cart";
    }

}
