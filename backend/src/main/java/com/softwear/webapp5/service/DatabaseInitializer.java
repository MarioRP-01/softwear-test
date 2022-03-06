package com.softwear.webapp5.service;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.softwear.webapp5.model.Coupon;
import com.softwear.webapp5.repository.CouponRepository;
import com.softwear.webapp5.model.Transaction;
import com.softwear.webapp5.model.ShopUser;
import com.softwear.webapp5.repository.TransactionRepository;
import com.softwear.webapp5.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseInitializer {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CouponService couponService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostConstruct
	public void init() {



		// Users
		ShopUser user = new ShopUser("user", "user@user.com", "User", "Softwear", passwordEncoder.encode("pass"), "User Street 1", 654987321, "01/01/2000", "USER");
		userRepository.save(new ShopUser("admin", "admin@admin.com", "Administrator", "Softwear", passwordEncoder.encode("pass"), "Admin Street 1", 654321987, "01/01/2000", "ADMIN"));
		userRepository.save(user);

		ArrayList<String> lista1 = new ArrayList<>();
		ArrayList<String> lista2 = new ArrayList<>();
		ArrayList<String> lista3 = new ArrayList<>();

		lista1.add("item1.webp");
		lista1.add("item2.webp");
		lista1.add("item3.webp");

		lista2.add("item4.webp");
		lista2.add("item5.webp");
		lista2.add("item6.webp");

		lista3.add("item7.webp");
		lista3.add("item8.webp");

		Product camisa = new Product("camisa", "es cómoda", 10, (long) 156, "Correos", "China", "Softwear", "15/3/21", lista1);

		productRepository.save(camisa);
		productRepository.save(new Product("chaqueta", "está cómoda", 20, (long) 156, "DMW", "Albacete", "Softwear", "26/4/21", lista2));
		productRepository.save(new Product("pantalón", "ufff", 15, (long) 156, "Amazon", "Murcia", "Softwear", "12/2/2022", lista3));
	

		// Coupons

		Product leather_coat = new Product("Leather Coat (Softwear)", "Test Product", 10.00, 2L, "DMW", "Spain", "Softwear", "20/11/21", lista1);
		List<Product> affectedProducts = new ArrayList<>();
		affectedProducts.add(leather_coat);
		productRepository.save(leather_coat);

		Coupon coupon = new Coupon("ASTONISHOFFER", "total_percentage", "15/02/2022", "26/06/2022", 0f, 0.5f, null);
		Coupon coupon2x1 = new Coupon("2X1", "2x1", "13/02/2022", "26/06/2022", null, null, affectedProducts);
		Coupon coupon3x2 = new Coupon("TAKEALOOK3X2", "3x2", "12/02/2022", "22/02/2022", null, null, affectedProducts);
		Coupon couponLeatherPer = new Coupon("I<3LEATHER", "product_percentage", "15/02/2022", "15/06/2022", null, 0.4f, affectedProducts);
		Coupon couponLeatherTot = new Coupon("10LEATHER", "product_amount", "15/02/2022", "15/06/2022", null, 4.5f, affectedProducts);

		couponRepository.save(new Coupon("10PER", "total_percentage", "15/02/2022", "26/06/2022", 10.00f, 0.1f, null));
		couponRepository.save(new Coupon("GIVEME10", "total_amount", "15/02/2022", "26/02/2022", 10.00f, 2.5f, null));
		couponRepository.save(coupon2x1);
		couponRepository.save(coupon3x2);
		couponRepository.save(couponLeatherPer);
		couponRepository.save(couponLeatherTot);
		couponRepository.save(coupon);

		// Transactions

		List<Product> productList = new ArrayList<>();
		for(int i=0; i<3; i++) {
			productList.add(leather_coat);
		}

		Transaction transaction = new Transaction("PROCESSED", user, coupon3x2, "17/02/2022", productList);
		if(!couponService.applyCoupon(transaction)) {
			transactionRepository.save(transaction);
		}

		transaction = new Transaction("PROCESSED", user, couponLeatherPer, "17/02/2022", productList);
		if(!couponService.applyCoupon(transaction)) {
			transactionRepository.save(transaction);
		}

		transaction = new Transaction("PROCESSED", user, couponLeatherTot, "17/02/2022", productList);
		if(!couponService.applyCoupon(transaction)) {
			transactionRepository.save(transaction);
		}

		transaction = new Transaction("WISHLIST", user, null, "24/02/2022", new ArrayList<>());
		transactionRepository.save(transaction);

		transaction = new Transaction("CART", user, null, "24/02/2022", productList);
		transactionRepository.save(transaction);
		
	}
	
}