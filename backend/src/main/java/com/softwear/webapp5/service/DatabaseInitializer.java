package com.softwear.webapp5.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Blob;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.repository.ProductRepository;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.softwear.webapp5.data.ProductSize;
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
	public void init() throws FileNotFoundException {

		// Users
		ShopUser user = new ShopUser("user", "user@user.com", "User", "Softwear", passwordEncoder.encode("pass"), "User Street 1", 654987321, "01/01/2000", "USER");
		userRepository.save(new ShopUser("admin", "admin@admin.com", "Administrator", "Softwear", passwordEncoder.encode("pass"), "Admin Street 1", 654321987, "01/01/2000", "ADMIN"));
		userRepository.save(user);

		List<String> productImages1 = new ArrayList<>();
		List<String> productImages2 = new ArrayList<>();
		List<String> productImages3 = new ArrayList<>();
		ArrayList<Blob> list1 = new ArrayList<>();
		ArrayList<Blob> list2 = new ArrayList<>();
		ArrayList<Blob> list3 = new ArrayList<>();

		productImages1.add("src/main/resources/static/assets/productos/item1.webp");
		productImages1.add("src/main/resources/static/assets/productos/item2.webp");
		productImages1.add("src/main/resources/static/assets/productos/item3.webp");
		productImages2.add("src/main/resources/static/assets/productos/item4.webp");
		productImages2.add("src/main/resources/static/assets/productos/item5.webp");
		productImages2.add("src/main/resources/static/assets/productos/item6.webp");
		productImages3.add("src/main/resources/static/assets/productos/item7.webp");
		productImages3.add("src/main/resources/static/assets/productos/item8.webp");

		for(int i = 0; i < productImages1.size(); i++) {
			String image = productImages1.get(i);
			File imageFile = new File(image);
			list1.add(BlobProxy.generateProxy(
					new FileInputStream(imageFile), imageFile.length()));
			productImages1.set(i, "/product/1/image/" + i);
		}

		for(int i = 0; i < productImages2.size(); i++) {
			String image = productImages2.get(i);
			File imageFile = new File(image);
			list2.add(BlobProxy.generateProxy(
					new FileInputStream(imageFile), imageFile.length()));
			productImages2.set(i, "/product/2/image/" + i);
		}

		for(int i = 0; i < productImages3.size(); i++) {
			String image = productImages3.get(i);
			File imageFile = new File(image);
			list3.add(BlobProxy.generateProxy(
					new FileInputStream(imageFile), imageFile.length()));
			productImages3.set(i, "/product/3/image/" + i);
		}

		Product shirt = new Product("Shirt", "So comfortable", 10, (long) 156, ProductSize.XL, productImages1, list1);

		productRepository.save(shirt);
		//productRepository.save(new Product("Jacket", "So comfortable", 20, (long) 156, ProductSize.XS, productImages2, list2));
		productRepository.save(new Product("Trousers", "Best quality trousers", 15, (long) 156, ProductSize.S, productImages3, list3));
	

		// Coupons

		Product leather_coat = new Product("Leather Coat (Softwear)", "Test Product", 10.00, 2L, ProductSize.M, productImages2, list2);
		List<Product> affectedProducts = new ArrayList<>();
		affectedProducts.add(leather_coat);
		productRepository.save(leather_coat);
		productRepository.save(new Product("Leather Coat (Softwear)", "Test Product", 10.00, 21L, ProductSize.L, productImages2, null));

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

		transaction = new Transaction("WISHLIST", user, null, "24/02/2022", affectedProducts);
		transactionRepository.save(transaction);

		transaction = new Transaction("CART", user, null, "24/02/2022", productList);
		transactionRepository.save(transaction);
		
	}
	
}