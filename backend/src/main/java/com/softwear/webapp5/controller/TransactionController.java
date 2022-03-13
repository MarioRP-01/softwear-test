package com.softwear.webapp5.controller;

import com.softwear.webapp5.data.TransactionView;
import com.softwear.webapp5.model.ShopUser;
import com.softwear.webapp5.model.Transaction;
import com.softwear.webapp5.service.CouponService;
import com.softwear.webapp5.service.ProductService;
import com.softwear.webapp5.service.TransactionService;
import com.softwear.webapp5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class TransactionController {

    @Autowired
    private CouponService couponService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    // CART
    @GetMapping("/cart")
    public String cart(Model model) {
        ShopUser user = userService.findByUsername((String) model.getAttribute("username")).get();
        Optional<Transaction> optCart = transactionService.findCart(user);
        TransactionView cartView;
        if(optCart.isPresent()) {
            Transaction cart = optCart.get();
            boolean check = true;
            if(cart.getUsedCoupon() != null) {
                check = couponService.applyCoupon(cart);
            }
            if(!model.containsAttribute("wrongCoupon")){
                model.addAttribute("wrongCoupon", false);
            }
            if(!check) {
                model.addAttribute("wrongCoupon", true);
            }
            cartView = new TransactionView(cart);
        } else {
            cartView = new TransactionView();
        }
        model.addAttribute("cart", cartView);
        model.addAttribute("totalPrice", cartView.getTotalPrice());
        return "cart";
    }

    @GetMapping("/cart/pay")
    public String cartPay(Model model) {
        ShopUser user = userService.findByUsername((String) model.getAttribute("username")).get();
        Optional<Transaction> optCart = transactionService.findCart(user);
        if(optCart.isPresent()) {
            Transaction cart = optCart.get();
            TransactionView cartView = new TransactionView(cart);
            if(cartView.getTransactionEntries().isEmpty()) {
                return "redirect:/cart";
            }
            for(TransactionView.TransactionViewEntry entry: cartView.getTransactionEntries()) {
                if(!productService.checkStock(entry.getProduct(), entry.getQuantity())) {
                    model.addAttribute("product", entry.getProduct());
                    return "outOfStock";
                }
            }
            for(TransactionView.TransactionViewEntry entry: cartView.getTransactionEntries()) {
                productService.deleteStock(entry.getProduct(), entry.getQuantity());
            }
            cart.setType("PAID");
            cart.setDate(TransactionService.getCurrentDate());
            transactionService.save(cart);
            return "successfulPayment";
        }
        return "error";
    }

    // WISHLIST
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


    //ERRORPAYMENT
    @GetMapping("/errorPayment")
    public String getErrorPayment() {

        return "errorPayment";
    }


    /*@PostMapping("/wishlist/add")
    public String wishlistAdd(@RequestParam Long id, Model model) {
        if (transactionService.addToWishlist(id, userService.findByUsername((String) model.getAttribute("username")).get())) {
            return "redirect:/wishlist";
        }
        return "error";
    }

    @PostMapping("/wishlist/delete")
    public String wishlistDelete(@RequestParam Long id, Model model) {
        Optional<Product> optionalProduct = productService.findById(id);
        if(transactionService.removeFromWishlist(id, userService.findByUsername((String) model.getAttribute("username")).get())) {
                return "redirect:/wishlist";
        }
        return "error";
    }

    @PostMapping("/wishlist/empty")
    public String wishlistEmpty(@RequestParam Long id, Model model) {
        transactionService.emptyWishlist(userService.findByUsername((String) model.getAttribute("username")).get());
        return "redirect:/wishlist";
    }*/

    // PURCHASE HISTORY
    @GetMapping("/purchaseHistory")
    public String purchaseHistory(Model model) {
        ShopUser user = userService.findByUsername((String) model.getAttribute("username")).get();
        List<Transaction> transactions = transactionService.findPurchaseHistory(user);
        List<TransactionView> purchaseHistory = new ArrayList<>();
        for(Transaction transaction: transactions) {
            purchaseHistory.add(new TransactionView(transaction));
        }
        model.addAttribute("purchaseHistory", purchaseHistory);
        return "purchaseHistory";
    }

}
