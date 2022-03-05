package com.softwear.webapp5.controller;

import com.softwear.webapp5.data.TransactionView;
import com.softwear.webapp5.model.Coupon;
import com.softwear.webapp5.model.Product;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Calendar;
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

    private String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        return String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH)) + "/" + String.format("%02d", calendar.get(Calendar.MONTH)) + "/" + Integer.toString(calendar.get(Calendar.YEAR));
    }

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

    @PostMapping("/cart/add")
    public String cartadd(@RequestParam Long id, Model model) {
        Optional<Product> optionalProduct = productService.findById(id);
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            ShopUser user = userService.findByUsername((String) model.getAttribute("username")).get();
            Optional<Transaction> optionalTransaction = transactionService.findCart(user);
            Transaction cart;
            if (optionalTransaction.isPresent()) {
                cart = optionalTransaction.get();
            } else {
                cart = new Transaction("CART", user, null, getCurrentDate(), new ArrayList<>());
            }
            cart.getProducts().add(product);
            cart.setTotalPrice(cart.calculateTotalProductPrice());
            productService.save(product);
            return "redirect:/cart";
        }
        return "error";
    }

    @PostMapping("/cart/delete")
    public String cartDelete(@RequestParam Long id, Model model) {
        Optional<Product> optionalProduct = productService.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            ShopUser user = userService.findByUsername((String) model.getAttribute("username")).get();
            Optional<Transaction> optCart = transactionService.findCart(user);
            if (optCart.isPresent()) {
                Transaction cart = optCart.get();
                if (cart.getProducts().contains(product)) {
                    cart.getProducts().remove(product);
                    cart.setTotalPrice(cart.calculateTotalProductPrice());
                    transactionService.save(cart);
                    return "redirect:/cart";
                }
            }
        }
        return "error";
    }

    @PostMapping("/cart/deleteAll")
    public String cartDeleteAll(@RequestParam Long id, Model model) {
        Optional<Product> optionalProduct = productService.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            ShopUser user = userService.findByUsername((String) model.getAttribute("username")).get();
            Optional<Transaction> optCart = transactionService.findCart(user);
            if (optCart.isPresent()) {
                Transaction cart = optCart.get();
                while (cart.getProducts().contains(product)) {
                    cart.getProducts().remove(product);
                    transactionService.save(cart);
                }
                return "redirect:/cart";
            }
        }
        return "error";
    }

    @PostMapping("/cart/empty")
    public String cartEmpty(Model model) {
        ShopUser user = userService.findByUsername((String) model.getAttribute("username")).get();
        Optional<Transaction> optCart = transactionService.findCart(user);
        if (optCart.isPresent()) {
            transactionService.delete(optCart.get());
        }
        return "redirect:/cart";
    }

    @PostMapping("/cart/applyCoupon")
    public String cartApplyCoupon(@RequestParam String code, Model model) {
        ShopUser user = userService.findByUsername((String) model.getAttribute("username")).get();
        Optional<Transaction> optCart = transactionService.findCart(user);
        if (optCart.isPresent()) {
            Transaction cart = optCart.get();
            Optional<Coupon> optionalCoupon = couponService.findByCode(code);
            if(optionalCoupon.isPresent()) {
                cart.setUsedCoupon(optionalCoupon.get());
                couponService.applyCoupon(cart);
            } else {
                model.addAttribute("wrongCoupon", true);
            }
        }
        return cart(model);
    }

    @PostMapping("/cart/pay")
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
                if(entry.getProduct().getStock() < entry.getQuantity()) {
                    model.addAttribute("product", entry.getProduct());
                    return "outOfStock";
                }
            }
            for(TransactionView.TransactionViewEntry entry: cartView.getTransactionEntries()) {
                Product product = entry.getProduct();
                product.setStock(product.getStock() - entry.getQuantity());
                productService.save(product);
            }
            cart.setType("PAID");
            cart.setDate(getCurrentDate());
            transactionService.save(cart);
            return "successfulPayment";
        }
        return "error";
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
        List<Transaction> transactions = transactionService.findPurchaseHistory(user);
        List<TransactionView> purchaseHistory = new ArrayList<>();
        for(Transaction transaction: transactions) {
            purchaseHistory.add(new TransactionView(transaction));
        }
        model.addAttribute("purchaseHistory", purchaseHistory);
        return "purchaseHistory";
    }

}
