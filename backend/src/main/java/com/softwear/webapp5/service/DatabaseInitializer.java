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

		String description_facha_jacket = "";

		Product facha_jacket_XS = new Product("Facha jacket", description_facha_jacket, 40, (long) 10, ProductSize.XS, imgsItem3);
		Product facha_jacket_S = new Product("Facha jacket", description_facha_jacket, 40, (long) 45, ProductSize.S, imgsItem3);
		Product facha_jacket_M = new Product("Facha jacket", description_facha_jacket, 40, (long) 0, ProductSize.M, imgsItem3);
		Product facha_jacket_L = new Product("Facha jacket", description_facha_jacket, 40, (long) 2, ProductSize.L, imgsItem3);
		Product facha_jacket_XL = new Product("Facha jacket", description_facha_jacket, 40, (long) 7, ProductSize.XL, imgsItem3);

		productRepository.save(facha_jacket_XS);
		productRepository.save(facha_jacket_S);
		productRepository.save(facha_jacket_M);
		productRepository.save(facha_jacket_L);
		productRepository.save(facha_jacket_XL);

		String description_happy_jacket = "";

		Product happy_jaccket_XS = new Product("Happy jacket", description_happy_jacket, 55, (long) 2, ProductSize.XS, imgsItem4);
		Product happy_jaccket_S = new Product("Happy jacket", description_happy_jacket, 55, (long) 0, ProductSize.S, imgsItem4);
		Product happy_jaccket_M = new Product("Happy jacket", description_happy_jacket, 55, (long) 0, ProductSize.M, imgsItem4);
		Product happy_jaccket_L = new Product("Happy jacket", description_happy_jacket, 55, (long) 1, ProductSize.L, imgsItem4);
		Product happy_jaccket_XL = new Product("Happy jacket", description_happy_jacket, 55, (long) 4, ProductSize.XL, imgsItem4);

		productRepository.save(happy_jaccket_XS);
		productRepository.save(happy_jaccket_S);
		productRepository.save(happy_jaccket_M);
		productRepository.save(happy_jaccket_L);
		productRepository.save(happy_jaccket_XL);

		String description_longsleeves_shirt = "";

		Product longsleeves_XS = new Product("Long sleeves shirt", "Oversize shirt in grey with round neck and long sleeves", 35, (long) 19, ProductSize.XS, null);
		Product longsleeves_S = new Product("Long sleeves shirt", "Oversize shirt in grey with round neck and long sleeves", 35, (long) 32, ProductSize.S, null);
		Product longsleeves_M = new Product("Long sleeves shirt", "Oversize shirt in grey with round neck and long sleeves", 35, (long) 61, ProductSize.M, null);
		Product longsleeves_L = new Product("Long sleeves shirt", "Oversize shirt in grey with round neck and long sleeves", 35, (long) 3, ProductSize.L, null);
		Product longsleeves_XL = new Product("Long sleeves shirt", "Oversize shirt in grey with round neck and long sleeves", 35, (long) 40, ProductSize.XL, null);

		productRepository.save(longsleeves_XS);
		productRepository.save(longsleeves_S);
		productRepository.save(longsleeves_M);
		productRepository.save(longsleeves_L);
		productRepository.save(longsleeves_XL);

		String description_paris_shirt = "";

		Product paris_shirt_XS = new Product("Paris shirt", "Black shirt with Paris logo and simple style", 31, (long) 50, ProductSize.XS, null);
		Product paris_shirt_S = new Product("Paris shirt", "Black shirt with Paris logo and simple style", 31, (long) 21, ProductSize.S, null);
		Product paris_shirt_M = new Product("Paris shirt", "Black shirt with Paris logo and simple style", 31, (long) 29, ProductSize.M, null);
		Product paris_shirt_L = new Product("Paris shirt", "Black shirt with Paris logo and simple style", 31, (long) 35, ProductSize.L, null);
		Product paris_shirt_XL = new Product("Paris shirt", "Black shirt with Paris logo and simple style", 31, (long) 54, ProductSize.XL, null);

		productRepository.save(paris_shirt_XS);
		productRepository.save(paris_shirt_S);
		productRepository.save(paris_shirt_M);
		productRepository.save(paris_shirt_L);
		productRepository.save(paris_shirt_XL);

		String description_black_body = "";

		Product black_body_XS = new Product("Black body", "Black and adjusted body with asymetric sleeves", 40, (long) 70, ProductSize.XS, null);
		Product black_body_S = new Product("Black body", "Black and adjusted body with asymetric sleeves", 40, (long) 31, ProductSize.S, null);
		Product black_body_M = new Product("Black body", "Black and adjusted body with asymetric sleeves", 40, (long) 46, ProductSize.M, null);
		Product black_body_L = new Product("Black body", "Black and adjusted body with asymetric sleeves", 40, (long) 30, ProductSize.L, null);
		Product black_body_XL = new Product("Black body", "Black and adjusted body with asymetric sleeves", 40, (long) 44, ProductSize.XL, null);

		productRepository.save(black_body_XS);
		productRepository.save(black_body_S);
		productRepository.save(black_body_M);
		productRepository.save(black_body_L);
		productRepository.save(black_body_XL);

		String description_black_shirt = "";

		Product black_shirt_XS = new Product("Black shirt", "Black simple cotton made shirt", 40, (long) 70, ProductSize.XS, null);
		Product black_shirt_S = new Product("Black shirt", "Black simple cotton made shirt", 40, (long) 31, ProductSize.S, null);
		Product black_shirt_M = new Product("Black shirt", "Black simple cotton made shirt", 40, (long) 46, ProductSize.M, null);
		Product black_shirt_L = new Product("Black shirt", "Black simple cotton made shirt", 40, (long) 30, ProductSize.L, null);
		Product black_shirt_XL = new Product("Black shirt", "Black simple cotton made shirt", 40, (long) 44, ProductSize.XL, null);

		productRepository.save(black_body_XS);
		productRepository.save(black_body_S);
		productRepository.save(black_body_M);
		productRepository.save(black_body_L);
		productRepository.save(black_body_XL);
		
		String description_arizona_jeans = "";

		Product arizona_jeans_XS = new Product("Arizona jeans", "Straight high waisted jeans with zipper and rounded button", 95, (long) 40, ProductSize.XS, null);
		Product arizona_jeans_S = new Product("Arizona jeans", "Straight high waisted jeans with zipper and rounded button", 95, (long) 31, ProductSize.S, null);
		Product arizona_jeans_M = new Product("Arizona jeans", "Straight high waisted jeans with zipper and rounded button", 95, (long) 10, ProductSize.M, null);
		Product arizona_jeans_L = new Product("Arizona jeans", "Straight high waisted jeans with zipper and rounded button", 95, (long) 35, ProductSize.L, null);
		Product arizona_jeans_XL = new Product("Arizona jeans", "Straight high waisted jeans with zipper and rounded button", 95, (long) 48, ProductSize.XL, null);

		productRepository.save(arizona_jeans_XS);
		productRepository.save(arizona_jeans_S);
		productRepository.save(arizona_jeans_M);
		productRepository.save(arizona_jeans_L);
		productRepository.save(arizona_jeans_XL);

		
		String description_topos_dress = "";

		Product topos_dress_XS = new Product("Topos dress", "Short and ruffled dress in blue with white topos", 72, (long) 12, ProductSize.XS, null);
		Product topos_dress_S = new Product("Topos dress", "Short and ruffled dress in blue with white topos", 72, (long) 24, ProductSize.S, null);
		Product topos_dress_M = new Product("Topos dress", "Short and ruffled dress in blue with white topos", 72, (long) 31, ProductSize.M, null);
		Product topos_dress_L = new Product("Topos dress", "Short and ruffled dress in blue with white topos", 72, (long) 30, ProductSize.L, null);
		Product topos_dress_XL = new Product("Topos dress", "Short and ruffled dress in blue with white topos", 72, (long) 68, ProductSize.XL, null);

		productRepository.save(topos_dress_XS);
		productRepository.save(topos_dress_S);
		productRepository.save(topos_dress_M);
		productRepository.save(topos_dress_L);
		productRepository.save(topos_dress_XL);

		String description_winter_jacket = "";

		Product winter_jacket_XS = new Product("Winter jacket", "Black waterproof jacket with zipper. Made with goose feather", 99, (long) 15, ProductSize.XS, null);
		Product winter_jacket_S = new Product("Winter jacket", "Black waterproof jacket with zipper. Made with goose feather", 99, (long) 24, ProductSize.S, null);
		Product winter_jacket_M = new Product("Winter jacket", "Black waterproof jacket with zipper. Made with goose feather", 99, (long) 34, ProductSize.M, null);
		Product winter_jacket_L = new Product("Winter jacket", "Black waterproof jacket with zipper. Made with goose feather", 99, (long) 3, ProductSize.L, null);
		Product winter_jacket_XL = new Product("Winter jacket", "Black waterproof jacket with zipper. Made with goose feather", 99, (long) 25, ProductSize.XL, null);

		productRepository.save(winter_jacket_XS);
		productRepository.save(winter_jacket_S);
		productRepository.save(winter_jacket_M);
		productRepository.save(winter_jacket_L);
		productRepository.save(winter_jacket_XL);

		String description_creamy_pants = "";

		Product creamy_pants_XS = new Product("Winter jacket", "Creamy loose fit medium waisted pants", 86, (long) 45, ProductSize.XS, null);
		Product creamy_pants_S = new Product("Winter jacket", "Creamy loose fit medium waisted pants", 86, (long) 40, ProductSize.S, null);
		Product creamy_pants_M = new Product("Winter jacket", "Creamy loose fit medium waisted pants", 86, (long) 16, ProductSize.M, null);
		Product creamy_pants_L = new Product("Winter jacket", "Creamy loose fit medium waisted pants", 86, (long) 11, ProductSize.L, null);
		Product creamy_pants_XL = new Product("Winter jacket", "Creamy loose fit medium waisted pants", 86, (long) 5, ProductSize.XL, null);

		productRepository.save(creamy_pants_XS);
		productRepository.save(creamy_pants_S);
		productRepository.save(creamy_pants_M);
		productRepository.save(creamy_pants_L);
		productRepository.save(creamy_pants_XL);

		String description_oversize_hoodie = "";

		Product oversize_hoodie_XS = new Product("Oversize hoodie", "Basic hoodie in grey with hood and long sleeve", 74, (long) 12, ProductSize.XS, null);
		Product oversize_hoodie_S = new Product("Oversize hoodie", "Basic hoodie in grey with hood and long sleeve", 74, (long) 4, ProductSize.S, null);
		Product oversize_hoodie_M = new Product("Oversize hoodie", "Basic hoodie in grey with hood and long sleeve", 74, (long) 16, ProductSize.M, null);
		Product oversize_hoodie_L = new Product("Oversize hoodie", "Basic hoodie in grey with hood and long sleeve", 74, (long) 0, ProductSize.L, null);
		Product oversize_hoodie_XL = new Product("Oversize hoodie", "Basic hoodie in grey with hood and long sleeve", 74, (long) 9, ProductSize.XL, null);

		productRepository.save(oversize_hoodie_XS);
		productRepository.save(oversize_hoodie_S);
		productRepository.save(oversize_hoodie_M);
		productRepository.save(oversize_hoodie_L);
		productRepository.save(oversize_hoodie_XL);

		String description_yellow_set = "";

		Product yellow_set_XS = new Product("Yellow set", "Yellow set with short sleeves and pocket shirt and short pants", 110, (long) 23, ProductSize.XS, null);
		Product yellow_set_S = new Product("Yellow set", "Yellow set with short sleeves and pocket shirt and short pants", 110, (long) 0, ProductSize.S, null);
		Product yellow_set_M = new Product("Yellow set", "Yellow set with short sleeves and pocket shirt and short pants", 110, (long) 15, ProductSize.M, null);
		Product yellow_set_L = new Product("Yellow set", "Yellow set with short sleeves and pocket shirt and short pants", 110, (long) 3, ProductSize.L, null);
		Product yellow_set_XL = new Product("Yellow set", "Yellow set with short sleeves and pocket shirt and short pants", 110, (long) 36, ProductSize.XL, null);

		productRepository.save(yellow_set_XS);
		productRepository.save(yellow_set_S);
		productRepository.save(yellow_set_M);
		productRepository.save(yellow_set_L);
		productRepository.save(yellow_set_XL);

		String description_pocket_pants = "";

		Product pocket_pants_XS = new Product("Pocket pistachio pants", "Long short waisted troussers with pockets in pistachio tone", 84, (long) 37, ProductSize.XS, null);
		Product pocket_pants_S = new Product("Pocket pistachio pants", "Long short waisted troussers with pockets in pistachio tone", 84, (long) 15, ProductSize.S, null);
		Product pocket_pants_M = new Product("Pocket pistachio pants", "Long short waisted troussers with pockets in pistachio tone", 84, (long) 1, ProductSize.M, null);
		Product pocket_pants_L = new Product("Pocket pistachio pants", "Long short waisted troussers with pockets in pistachio tone", 84, (long) 34, ProductSize.L, null);
		Product pocket_pants_XL = new Product("Pocket pistachio pants", "Long short waisted troussers with pockets in pistachio tone", 84, (long) 6, ProductSize.XL, null);

		productRepository.save(pocket_pants_XS);
		productRepository.save(pocket_pants_S);
		productRepository.save(pocket_pants_M);
		productRepository.save(pocket_pants_L);
		productRepository.save(pocket_pants_XL);

		String description_goodvibes_set = "";

		Product goodvibes_set_XS = new Product("Good vibes set", "White & green shirt and Good vibes logo complementing green sporty shorts", 125, (long) 7, ProductSize.XS, null);
		Product goodvibes_set_S = new Product("Good vibes set", "White & green shirt and Good vibes logo complementing green sporty shorts", 125, (long) 13, ProductSize.S, null);
		Product goodvibes_set_M = new Product("Good vibes set", "White & green shirt and Good vibes logo complementing green sporty shorts", 125, (long) 4, ProductSize.M, null);
		Product goodvibes_set_L = new Product("Good vibes set", "White & green shirt and Good vibes logo complementing green sporty shorts", 125, (long) 25, ProductSize.L, null);
		Product goodvibes_set_XL = new Product("Good vibes set", "White & green shirt and Good vibes logo complementing green sporty shorts", 125, (long) 6, ProductSize.XL, null);

		productRepository.save(goodvibes_set_XS);
		productRepository.save(goodvibes_set_S);
		productRepository.save(goodvibes_set_M);
		productRepository.save(goodvibes_set_L);
		productRepository.save(goodvibes_set_XL);
		
		String description_palmspring_shirt = "";

		Product palmspring_shirt_XS = new Product("Palmspring set", "Beige tshirt palmspring logo", 43, (long) 26, ProductSize.XS, null);
		Product palmspring_shirt_S = new Product("Palmspring set", "Beige tshirt palmspring logo", 43, (long) 3, ProductSize.S, null);
		Product palmspring_shirt_M = new Product("Palmspring set", "Beige tshirt palmspring logo", 43, (long) 24, ProductSize.M, null);
		Product palmspring_shirt_L = new Product("Palmspring set", "Beige tshirt palmspring logo", 43, (long) 5, ProductSize.L, null);
		Product palmspring_shirt_XL = new Product("Palmspring set", "Beige tshirt palmspring logo", 43, (long) 16, ProductSize.XL, null);

		productRepository.save(palmspring_shirt_XS);
		productRepository.save(palmspring_shirt_S);
		productRepository.save(palmspring_shirt_M);
		productRepository.save(palmspring_shirt_L);
		productRepository.save(palmspring_shirt_XL);

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