package com.softwear.webapp5.controller;

import com.softwear.webapp5.data.CouponView;
import com.softwear.webapp5.data.TransactionView;
import com.softwear.webapp5.model.ShopUser;
import com.softwear.webapp5.model.Transaction;
import com.softwear.webapp5.service.CouponService;
import com.softwear.webapp5.service.ProductService;
import com.softwear.webapp5.service.TransactionService;
import com.softwear.webapp5.service.UserService;
import com.softwear.webapp5.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @Autowired
    private MailService mailService;

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

    private String createCartSummary(Transaction cart) {
        StringBuilder products = new StringBuilder();
        TransactionView cartView = new TransactionView(cart);
        for(int i = 0; i < cartView.getTransactionEntries().size(); i++) {
            TransactionView.TransactionViewEntry entry = cartView.getTransactionEntries().get(i);
            products.append(String.format("\t#%d\t%s - %s\t$%01.02f x %d\t->\t$%01.02f\n",
                    i + 1, entry.getProduct().getName(), entry.getProduct().getSize(), entry.getProduct().getPrice(), entry.getQuantity(), entry.getTotalPrice()));
        }
        if(cartView.getCoupon() != null) {
            CouponView coupon = cartView.getCoupon();
            products.append(String.format("\tCoupon: %s\t\t\t- $%01.02f\n", coupon.getCode(), coupon.getDiscount()));
        }
        products.append(String.format("Total: $%01.02f\n", cartView.getTotalPrice()));
        return String.format("Transaction ID: %d\nDate: %s\nStatus: %s\nShipping Address: %s\nSummary:\n%s", cart.getId(), cart.getDate(), cart.getType(), cart.getUser().getAddress(), products.toString());
    }

    private void sendMail(Transaction cart) {
        ShopUser user = cart.getUser();
        StringBuilder msg = new StringBuilder();
        msg.append(String.format("Hi Mr./Ms. %s,\n", user.getLastName()));
        msg.append("We have recived the payment of your new oder. It will be processed soon\n");
        msg.append("Order details:\n");
        msg.append(createCartSummary(cart));
        try {
            mailService.send(user.getEmail(), "Your order has been paid", msg.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            sendMail(cart);
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
