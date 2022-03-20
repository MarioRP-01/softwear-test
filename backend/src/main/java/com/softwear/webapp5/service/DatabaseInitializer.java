package com.softwear.webapp5.service;

import java.io.File;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.repository.ProductRepository;
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
	public void init() {

		// Users
		ShopUser user = new ShopUser("user", "user@user.com", "User", "Softwear", passwordEncoder.encode("pass"), "User Street 1", 654987321, "01/01/2000", "USER");
		ShopUser admin = new ShopUser("admin", "admin@admin.com", "Administrator", "Softwear", passwordEncoder.encode("pass"), "Admin Street 1", 654321987, "01/01/2000", "ADMIN");
		userRepository.save(admin);
		userRepository.save(user);

		ArrayList<File> imgsItem1 = new ArrayList<>();
		ArrayList<File> imgsItem2 = new ArrayList<>();
		ArrayList<File> imgsItem3 = new ArrayList<>();
		ArrayList<File> imgsItem4 = new ArrayList<>();
		ArrayList<File> imgsItem5 = new ArrayList<>();
		ArrayList<File> imgsItem6 = new ArrayList<>();
		ArrayList<File> imgsItem7 = new ArrayList<>();
		ArrayList<File> imgsItem9 = new ArrayList<>();
		ArrayList<File> imgsItem10 = new ArrayList<>();
		ArrayList<File> imgsItem11 = new ArrayList<>();
		ArrayList<File> imgsItem12 = new ArrayList<>();
		ArrayList<File> imgsItem13 = new ArrayList<>();
		ArrayList<File> imgsItem14 = new ArrayList<>();

		imgsItem1.add(new File("item1.webp"));
		imgsItem2.add(new File("item2.webp"));
		imgsItem3.add(new File("item3.webp"));
		imgsItem4.add(new File("item4.webp"));
		imgsItem5.add(new File("item5.webp"));

		String description_puffer_jacket = "";

		Product puffer_jacket_XS = new Product("Puffer jacket", description_puffer_jacket, 45, (long) 13, ProductSize.XS, imgsItem1);
		Product puffer_jacket_S = new Product("Puffer jacket", description_puffer_jacket, 45, (long) 99, ProductSize.S, imgsItem1);
		Product puffer_jacket_M = new Product("Puffer jacket", description_puffer_jacket, 45, (long) 23, ProductSize.M, imgsItem1);
		Product puffer_jacket_L = new Product("Puffer jacket", description_puffer_jacket, 45, (long) 109, ProductSize.L, imgsItem1);
		Product puffer_jacket_XL = new Product("Puffer jacket", description_puffer_jacket, 45, (long) 0, ProductSize.XL, imgsItem1);

		productRepository.save(puffer_jacket_XS);
		productRepository.save(puffer_jacket_S);
		productRepository.save(puffer_jacket_M);
		productRepository.save(puffer_jacket_L);
		productRepository.save(puffer_jacket_XL);

		String description_cowboy_jacket = "";

		Product cowboy_jacket_XS = new Product("Cowboy jacket", description_cowboy_jacket, 60, (long) 6, ProductSize.XS, imgsItem2);
		Product cowboy_jacket_S = new Product("Cowboy jacket", description_cowboy_jacket, 60, (long) 8, ProductSize.S, imgsItem2);
		Product cowboy_jacket_M = new Product("Cowboy jacket", description_cowboy_jacket, 60, (long) 44, ProductSize.M, imgsItem2);
		Product cowboy_jacket_L = new Product("Cowboy jacket", description_cowboy_jacket, 60, (long) 0, ProductSize.L, imgsItem2);
		Product cowboy_jacket_XL = new Product("Cowboy jacket", description_cowboy_jacket, 60, (long) 2, ProductSize.XL, imgsItem2);

		productRepository.save(cowboy_jacket_XS);
		productRepository.save(cowboy_jacket_S);
		productRepository.save(cowboy_jacket_M);
		productRepository.save(cowboy_jacket_L);
		productRepository.save(cowboy_jacket_XL);

		String description_Facha_jacket = "";

		Product facha_jacket_XS = new Product("Facha jacket", description_Facha_jacket, 40, (long) 10, ProductSize.XS, imgsItem3);
		Product facha_jacket_S = new Product("Facha jacket", description_Facha_jacket, 40, (long) 45, ProductSize.S, imgsItem3);
		Product facha_jacket_M = new Product("Facha jacket", description_Facha_jacket, 40, (long) 0, ProductSize.M, imgsItem3);
		Product facha_jacket_L = new Product("Facha jacket", description_Facha_jacket, 40, (long) 2, ProductSize.L, imgsItem3);
		Product facha_jacket_XL = new Product("Facha jacket", description_Facha_jacket, 40, (long) 7, ProductSize.XL, imgsItem3);

		productRepository.save(facha_jacket_XS);
		productRepository.save(facha_jacket_S);
		productRepository.save(facha_jacket_M);
		productRepository.save(facha_jacket_L);
		productRepository.save(facha_jacket_XL);

		String description_Happy_jacket = "";

		Product happy_jaccket_XS = new Product("Happy jacket", description_Happy_jacket, 55, (long) 2, ProductSize.XS, imgsItem4);
		Product happy_jaccket_S = new Product("Happy jacket", description_Happy_jacket, 55, (long) 0, ProductSize.S, imgsItem4);
		Product happy_jaccket_M = new Product("Happy jacket", description_Happy_jacket, 55, (long) 0, ProductSize.M, imgsItem4);
		Product happy_jaccket_L = new Product("Happy jacket", description_Happy_jacket, 55, (long) 1, ProductSize.L, imgsItem4);
		Product happy_jaccket_XL = new Product("Happy jacket", description_Happy_jacket, 55, (long) 4, ProductSize.XL, imgsItem4);

		productRepository.save(happy_jaccket_XS);
		productRepository.save(happy_jaccket_S);
		productRepository.save(happy_jaccket_M);
		productRepository.save(happy_jaccket_L);
		productRepository.save(happy_jaccket_XL);

		
	

		// Coupons

		Product leather_coat = new Product("Leather Coat (Softwear)", "Test Product", 10.00, 2L, ProductSize.M, lista1);
		List<Product> affectedProducts = new ArrayList<>();
		affectedProducts.add(leather_coat);
		productRepository.save(leather_coat);
		productRepository.save(new Product("Leather Coat (Softwear)", "Test Product", 10.00, 21L, ProductSize.L, lista1));

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

		List<Product> addCamisa = new ArrayList<>();
        for(int i=0; i<3; i++) {
            addCamisa.add(camisa);
        }

		Transaction transaction = new Transaction("PROCESSED", user, coupon3x2, "17/02/2022", addCamisa);
        if(!couponService.applyCoupon(transaction)) {
            transactionRepository.save(transaction);
        }

        transaction = new Transaction("PROCESSED", user, couponLeatherPer, "17/02/2022", addCamisa);
        if(!couponService.applyCoupon(transaction)) {
            transactionRepository.save(transaction);
		}
		
		transaction = new Transaction("PROCESSED", user, coupon3x2, "17/02/2022", productList);
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