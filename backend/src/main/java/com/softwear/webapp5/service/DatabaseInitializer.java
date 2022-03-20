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

		List<Product> productListA = new ArrayList<>();

		productListA.add(puffer_jacket_XS);
		productListA.add(puffer_jacket_S);
		productListA.add(puffer_jacket_M);
		productListA.add(puffer_jacket_L);
		productListA.add(puffer_jacket_XL);

		List<Product> productListB = new ArrayList<>();

		productListB.add(puffer_jacket_XS);
		productListB.add(puffer_jacket_S);
		productListB.add(puffer_jacket_M);
		productListB.add(puffer_jacket_L);
		productListB.add(puffer_jacket_XL);
		productListB.add(happy_jaccket_XS);
		productListB.add(happy_jaccket_S);
		productListB.add(happy_jaccket_M);
		productListB.add(happy_jaccket_L);
		productListB.add(happy_jaccket_XL);

		Coupon globalDiscount50 = new Coupon("ASTONISHOFFER", "total_percentage", "15/02/2022", "26/06/2022", 0f, 0.5f, null);
		Coupon twoXone = new Coupon("2X1", "2x1", "13/02/2022", "26/06/2022", null, null, productListA);
		Coupon threeXtwo = new Coupon("TAKEALOOK3X2", "3x2", "12/02/2022", "22/02/2022", null, null, productListA);
		Coupon productListADiscount = new Coupon("I<3LEATHER", "product_percentage", "15/02/2022", "15/06/2022", null, 0.4f, productListA);
		Coupon freeSelected = new Coupon("FREESELECTEDA", "product_amount", "15/02/2022", "15/06/2022", null, 4.5f, productListA);
		Coupon globalDiscount10 = new Coupon("10PER", "total_percentage", "15/02/2022", "26/06/2022", 10.00f, 0.1f, null);
		Coupon getTen = new Coupon("GIVEME10", "total_amount", "15/02/2022", "26/02/2022", 10.00f, 2.5f, null);
		
		couponRepository.save(globalDiscount50);
		couponRepository.save(twoXone);
		couponRepository.save(threeXtwo);
		couponRepository.save(productListADiscount);
		couponRepository.save(freeSelected);
		couponRepository.save(globalDiscount10);
		couponRepository.save(getTen);


		// Transactions

		List<Product> productListTransaction1 = new ArrayList<>();
		productListTransaction1.add(puffer_jacket_XS);
		productListTransaction1.add(puffer_jacket_S);
		productListTransaction1.add(puffer_jacket_L);


		List<Product> productListTransaction2 = new ArrayList<>();
		productListTransaction2.add(cowboy_jacket_M);

		List<Product> productListTransaction3 = new ArrayList<>();

		List<Product> productListTransaction4 = new ArrayList<>();
		List<Product> productListTransaction5 = new ArrayList<>();
		List<Product> productListTransaction6 = new ArrayList<>();
		List<Product> productListTransaction7 = new ArrayList<>();
		List<Product> productListTransaction8 = new ArrayList<>();
		List<Product> productListTransaction9 = new ArrayList<>();
		List<Product> productListTransaction10 = new ArrayList<>();
		List<Product> productListTransaction11 = new ArrayList<>();
		List<Product> productListTransaction12 = new ArrayList<>();

		List<Product> productListwishList = new ArrayList<>();
		List<Product> productListCart = new ArrayList<>();

		for(int i=0; i<2; i++) {
			puffer_jacket_S
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