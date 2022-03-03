package com.softwear.webapp5.service;

import javax.annotation.PostConstruct;

import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softwear.webapp5.model.Coupon;
import com.softwear.webapp5.model.Transaction;
import com.softwear.webapp5.model.ShopUser;
import com.softwear.webapp5.repository.CouponRepository;
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
	private CouponService couponService;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostConstruct
	public void init() {

		// Products
		Product leather_coat = new Product("leather coat", "Test Product", new ArrayList<>(), 10);
		productRepository.save(leather_coat);

		// Coupons

		List<Product> affectedProducts = new ArrayList<>();
		affectedProducts.add(leather_coat);

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
			productList.add(leather_coat);
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
		
		userRepository.save(new ShopUser("JOLU","joseluis@gmail.com","Jose Luis","Ekisde","1234","Calle falsa 123", 678934837,"31/01/1990","Admin"));
		userRepository.save(new ShopUser("PEPELU","pepeluis@gmail.com","Pepe Luis","Ekisde","5678","Calle falsa 123", 671234129,"30/03/1993","User"));
		
	}
	
}
