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
		productRepository.save(new Product("pantalón", "ufff", 15, (long) 156, "Amazon", "Murcia", "Softwear", "12/2/22", lista3));
	

		// Coupons

		List<Product> affectedProducts = new ArrayList<>();
		affectedProducts.add(camisa);

		Coupon coupon = new Coupon("ASTONISHOFFER", "total_percentage", "15/02/22", "26/06/22", 0f, 0.5f, null);
		Coupon coupon2x1 = new Coupon("2X1", "2x1", "13/03/22", "26/06/22", null, null, affectedProducts);
		Coupon coupon3x2 = new Coupon("TAKEALOOK3X2", "3x2", "12/02/22", "22/02/22", null, null, affectedProducts);
		Coupon couponLeatherPer = new Coupon("I<3LEATHER", "product_percentage", "15/02/22", "15/06/22", null, 0.4f, affectedProducts);
		Coupon couponLeatherTot = new Coupon("10LEATHER", "product_amount", "15/02/22", "15/06/22", null, 4.5f, affectedProducts);

		couponRepository.save(new Coupon("10PER", "total_percentage", "15/02/22", "26/06/22", 10.00f, 0.1f, null));
		couponRepository.save(new Coupon("GIVEME10", "total_amount", "15/02/22", "26/02/22", 10.00f, 2.5f, null));
		couponRepository.save(coupon2x1);
		couponRepository.save(coupon3x2);
		couponRepository.save(couponLeatherPer);
		couponRepository.save(couponLeatherTot);
		couponRepository.save(coupon);

		// Transactions

		List<Product> productList = new ArrayList<>();
		for(int i=0; i<3; i++) {
			productList.add(camisa);
		}

		Transaction transaction = new Transaction("CART", "24/02/22");
		transaction.setUsedCoupon(coupon);
		transaction.setProducts(productList);
		couponService.applyCoupon(transaction);
		transactionRepository.save(transaction);

		transaction = new Transaction("PROCESSED", "17/02/22");
		transaction.setUsedCoupon(coupon2x1);
		transaction.setProducts(productList);
		couponService.applyCoupon(transaction);
		transactionRepository.save(transaction);

		transaction = new Transaction("PROCESSED", "17/02/22");
		transaction.setUsedCoupon(coupon3x2);
		transaction.setProducts(productList);
		couponService.applyCoupon(transaction);
		transactionRepository.save(transaction);

		transaction = new Transaction("PROCESSED", "17/02/22");
		transaction.setUsedCoupon(couponLeatherPer);
		transaction.setProducts(productList);
		couponService.applyCoupon(transaction);
		transactionRepository.save(transaction);

		transaction = new Transaction("PROCESSED", "17/02/22");
		transaction.setUsedCoupon(couponLeatherTot);
		transaction.setProducts(productList);
		couponService.applyCoupon(transaction);
		transactionRepository.save(transaction);

		
		
		// Users

		userRepository.save(new ShopUser("admin", "admin@admin.com", "Administrator", "Softwear", passwordEncoder.encode("pass"), "Admin Street 1", 654321987, "01/01/2000", "ADMIN"));
		userRepository.save(new ShopUser("user", "user@user.com", "User", "Softwear", passwordEncoder.encode("pass"), "User Street 1", 654987321, "01/01/2000", "USER"));
		
	}
	
}