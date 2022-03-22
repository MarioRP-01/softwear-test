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
		ArrayList<File> imgsItem8 = new ArrayList<>();
		ArrayList<File> imgsItem9 = new ArrayList<>();
		ArrayList<File> imgsItem10 = new ArrayList<>();
		ArrayList<File> imgsItem11 = new ArrayList<>();
		ArrayList<File> imgsItem12 = new ArrayList<>();
		ArrayList<File> imgsItem13 = new ArrayList<>();
		ArrayList<File> imgsItem14 = new ArrayList<>();
		ArrayList<File> imgsItem15 = new ArrayList<>();
		ArrayList<File> imgsItem16 = new ArrayList<>();

		imgsItem1.add(new File("item1.webp"));
		imgsItem2.add(new File("item2.webp"));
		imgsItem3.add(new File("item3.webp"));
		imgsItem4.add(new File("item4.webp"));
		imgsItem5.add(new File("item5.webp"));
		imgsItem6.add(new File("item6.webp"));
		imgsItem7.add(new File("item7.webp"));
		imgsItem8.add(new File("item8.webp"));
		imgsItem9.add(new File("item9-1.webp"));
		imgsItem10.add(new File("item9-2.webp"));
		imgsItem11.add(new File("item9-3.webp"));
		imgsItem12.add(new File("item10.webp"));
		imgsItem13.add(new File("item11-1.webp"));
		imgsItem14.add(new File("item11-2.webp"));
		imgsItem15.add(new File("item12.webp"));
		imgsItem16.add(new File("item13-2.webp"));
		imgsItem16.add(new File("item13-3.webp"));
		imgsItem16.add(new File("item14.webp"));
		imgsItem16.add(new File("item15.webp"));
		imgsItem16.add(new File("item16.webp"));



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

		String description_longsleeves_shirt = "Oversize shirt in grey with round neck and long sleeves";

		Product longsleeves_XS = new Product("Long sleeves shirt", description_longsleeves_shirt, 35, (long) 19, ProductSize.XS, imgsItem5);
		Product longsleeves_S = new Product("Long sleeves shirt", description_happy_jacket, 35, (long) 32, ProductSize.S, imgsItem5);
		Product longsleeves_M = new Product("Long sleeves shirt", description_happy_jacket, 35, (long) 61, ProductSize.M, imgsItem5);
		Product longsleeves_L = new Product("Long sleeves shirt", description_happy_jacket, 35, (long) 3, ProductSize.L, imgsItem5);
		Product longsleeves_XL = new Product("Long sleeves shirt", description_happy_jacket, 35, (long) 40, ProductSize.XL, imgsItem5);

		productRepository.save(longsleeves_XS);
		productRepository.save(longsleeves_S);
		productRepository.save(longsleeves_M);
		productRepository.save(longsleeves_L);
		productRepository.save(longsleeves_XL);

		String description_paris_shirt = "Black shirt with Paris logo and simple style";

		Product paris_shirt_XS = new Product("Paris shirt", description_paris_shirt, 31, (long) 50, ProductSize.XS, imgsItem6);
		Product paris_shirt_S = new Product("Paris shirt", description_paris_shirt, 31, (long) 21, ProductSize.S, imgsItem6);
		Product paris_shirt_M = new Product("Paris shirt", description_paris_shirt, 31, (long) 29, ProductSize.M, imgsItem6);
		Product paris_shirt_L = new Product("Paris shirt", description_paris_shirt, 31, (long) 35, ProductSize.L, imgsItem6);
		Product paris_shirt_XL = new Product("Paris shirt", description_paris_shirt, 31, (long) 54, ProductSize.XL, imgsItem6);

		productRepository.save(paris_shirt_XS);
		productRepository.save(paris_shirt_S);
		productRepository.save(paris_shirt_M);
		productRepository.save(paris_shirt_L);
		productRepository.save(paris_shirt_XL);

		String description_black_body = "Black and adjusted body with asymetric sleeves";

		Product black_body_XS = new Product("Black body", description_black_body, 40, (long) 70, ProductSize.XS, imgsItem7);
		Product black_body_S = new Product("Black body", description_black_body, 40, (long) 31, ProductSize.S, imgsItem7);
		Product black_body_M = new Product("Black body", description_black_body, 40, (long) 46, ProductSize.M, imgsItem7);
		Product black_body_L = new Product("Black body", description_black_body, 40, (long) 30, ProductSize.L, imgsItem7);
		Product black_body_XL = new Product("Black body", description_black_body, 40, (long) 44, ProductSize.XL, imgsItem7);

		productRepository.save(black_body_XS);
		productRepository.save(black_body_S);
		productRepository.save(black_body_M);
		productRepository.save(black_body_L);
		productRepository.save(black_body_XL);

		String description_black_shirt = "Black simple cotton made shirt";

		Product black_shirt_XS = new Product("Black shirt", description_black_shirt, 40, (long) 70, ProductSize.XS, imgsItem8);
		Product black_shirt_S = new Product("Black shirt", description_black_shirt, 40, (long) 31, ProductSize.S, imgsItem8);
		Product black_shirt_M = new Product("Black shirt", description_black_shirt, 40, (long) 46, ProductSize.M, imgsItem8);
		Product black_shirt_L = new Product("Black shirt", description_black_shirt, 40, (long) 30, ProductSize.L, imgsItem8);
		Product black_shirt_XL = new Product("Black shirt", description_black_shirt, 40, (long) 44, ProductSize.XL, imgsItem8);

		productRepository.save(black_shirt_XS);
		productRepository.save(black_shirt_S);
		productRepository.save(black_shirt_M);
		productRepository.save(black_shirt_L);
		productRepository.save(black_shirt_XL);
		
		String description_arizona_jeans = "Straight high waisted jeans with zipper and rounded button";

		Product arizona_jeans_XS = new Product("Arizona jeans", description_arizona_jeans, 95, (long) 40, ProductSize.XS, imgsItem9);
		Product arizona_jeans_S = new Product("Arizona jeans", description_arizona_jeans, 95, (long) 31, ProductSize.S, imgsItem9);
		Product arizona_jeans_M = new Product("Arizona jeans", description_arizona_jeans, 95, (long) 10, ProductSize.M, imgsItem9);
		Product arizona_jeans_L = new Product("Arizona jeans", description_arizona_jeans, 95, (long) 35, ProductSize.L, imgsItem9);
		Product arizona_jeans_XL = new Product("Arizona jeans", description_arizona_jeans, 95, (long) 48, ProductSize.XL, imgsItem9);

		productRepository.save(arizona_jeans_XS);
		productRepository.save(arizona_jeans_S);
		productRepository.save(arizona_jeans_M);
		productRepository.save(arizona_jeans_L);
		productRepository.save(arizona_jeans_XL);

		
		String description_topos_dress = "Short and ruffled dress in blue with white topos";

		Product topos_dress_XS = new Product("Topos dress", description_topos_dress, 72, (long) 12, ProductSize.XS, imgsItem10);
		Product topos_dress_S = new Product("Topos dress", description_topos_dress, 72, (long) 24, ProductSize.S, imgsItem10);
		Product topos_dress_M = new Product("Topos dress", description_topos_dress, 72, (long) 31, ProductSize.M, imgsItem10);
		Product topos_dress_L = new Product("Topos dress", description_topos_dress, 72, (long) 30, ProductSize.L, imgsItem10);
		Product topos_dress_XL = new Product("Topos dress", description_topos_dress, 72, (long) 68, ProductSize.XL, imgsItem10);

		productRepository.save(topos_dress_XS);
		productRepository.save(topos_dress_S);
		productRepository.save(topos_dress_M);
		productRepository.save(topos_dress_L);
		productRepository.save(topos_dress_XL);

		String description_winter_jacket = "Black waterproof jacket with zipper. Made with goose feather";

		Product winter_jacket_XS = new Product("Winter jacket", description_winter_jacket, 99, (long) 15, ProductSize.XS, imgsItem11);
		Product winter_jacket_S = new Product("Winter jacket", description_winter_jacket, 99, (long) 24, ProductSize.S, imgsItem11);
		Product winter_jacket_M = new Product("Winter jacket", description_winter_jacket, 99, (long) 34, ProductSize.M, imgsItem11);
		Product winter_jacket_L = new Product("Winter jacket", description_winter_jacket, 99, (long) 3, ProductSize.L, imgsItem11);
		Product winter_jacket_XL = new Product("Winter jacket", description_winter_jacket, 99, (long) 25, ProductSize.XL, imgsItem11);

		productRepository.save(winter_jacket_XS);
		productRepository.save(winter_jacket_S);
		productRepository.save(winter_jacket_M);
		productRepository.save(winter_jacket_L);
		productRepository.save(winter_jacket_XL);

		String description_creamy_pants = "Creamy loose fit medium waisted pants";

		Product creamy_pants_XS = new Product("Winter jacket", description_creamy_pants, 86, (long) 45, ProductSize.XS, imgsItem12);
		Product creamy_pants_S = new Product("Winter jacket", description_creamy_pants, 86, (long) 40, ProductSize.S, imgsItem12);
		Product creamy_pants_M = new Product("Winter jacket", description_creamy_pants, 86, (long) 16, ProductSize.M, imgsItem12);
		Product creamy_pants_L = new Product("Winter jacket", description_creamy_pants, 86, (long) 11, ProductSize.L, imgsItem12);
		Product creamy_pants_XL = new Product("Winter jacket", description_creamy_pants, 86, (long) 5, ProductSize.XL, imgsItem12);

		productRepository.save(creamy_pants_XS);
		productRepository.save(creamy_pants_S);
		productRepository.save(creamy_pants_M);
		productRepository.save(creamy_pants_L);
		productRepository.save(creamy_pants_XL);

		String description_oversize_hoodie = "Basic hoodie in grey with hood and long sleeve";

		Product oversize_hoodie_XS = new Product("Oversize hoodie", description_oversize_hoodie, 74, (long) 12, ProductSize.XS, imgsItem13);
		Product oversize_hoodie_S = new Product("Oversize hoodie", description_oversize_hoodie, 74, (long) 4, ProductSize.S, imgsItem13);
		Product oversize_hoodie_M = new Product("Oversize hoodie", description_oversize_hoodie, 74, (long) 16, ProductSize.M, imgsItem13);
		Product oversize_hoodie_L = new Product("Oversize hoodie", description_oversize_hoodie, 74, (long) 0, ProductSize.L, imgsItem13);
		Product oversize_hoodie_XL = new Product("Oversize hoodie", description_oversize_hoodie, 74, (long) 9, ProductSize.XL, imgsItem13);

		productRepository.save(oversize_hoodie_XS);
		productRepository.save(oversize_hoodie_S);
		productRepository.save(oversize_hoodie_M);
		productRepository.save(oversize_hoodie_L);
		productRepository.save(oversize_hoodie_XL);

		String description_yellow_set = "Yellow set with short sleeves and pocket shirt and short pants";

		Product yellow_set_XS = new Product("Yellow set", description_yellow_set, 110, (long) 23, ProductSize.XS, imgsItem14);
		Product yellow_set_S = new Product("Yellow set", description_yellow_set, 110, (long) 0, ProductSize.S, imgsItem14);
		Product yellow_set_M = new Product("Yellow set", description_yellow_set, 110, (long) 15, ProductSize.M, imgsItem14);
		Product yellow_set_L = new Product("Yellow set", description_yellow_set, 110, (long) 3, ProductSize.L, imgsItem14);
		Product yellow_set_XL = new Product("Yellow set", description_yellow_set, 110, (long) 36, ProductSize.XL, imgsItem14);

		productRepository.save(yellow_set_XS);
		productRepository.save(yellow_set_S);
		productRepository.save(yellow_set_M);
		productRepository.save(yellow_set_L);
		productRepository.save(yellow_set_XL);

		String description_pocket_pants = "Long short waisted troussers with pockets in pistachio tone";

		Product pocket_pants_XS = new Product("Pocket pistachio pants", description_pocket_pants, 84, (long) 37, ProductSize.XS, imgsItem15);
		Product pocket_pants_S = new Product("Pocket pistachio pants", description_pocket_pants, 84, (long) 15, ProductSize.S, imgsItem15);
		Product pocket_pants_M = new Product("Pocket pistachio pants", description_pocket_pants, 84, (long) 1, ProductSize.M, imgsItem15);
		Product pocket_pants_L = new Product("Pocket pistachio pants", description_pocket_pants, 84, (long) 34, ProductSize.L, imgsItem15);
		Product pocket_pants_XL = new Product("Pocket pistachio pants", description_pocket_pants, 84, (long) 6, ProductSize.XL, imgsItem15);

		productRepository.save(pocket_pants_XS);
		productRepository.save(pocket_pants_S);
		productRepository.save(pocket_pants_M);
		productRepository.save(pocket_pants_L);
		productRepository.save(pocket_pants_XL);

		String description_goodvibes_set = "White & green shirt and Good vibes logo complementing green sporty shorts";

		Product goodvibes_set_XS = new Product("Good vibes set", description_goodvibes_set, 125, (long) 7, ProductSize.XS, imgsItem16);
		Product goodvibes_set_S = new Product("Good vibes set", description_goodvibes_set, 125, (long) 13, ProductSize.S, imgsItem16);
		Product goodvibes_set_M = new Product("Good vibes set", description_goodvibes_set, 125, (long) 4, ProductSize.M, imgsItem16);
		Product goodvibes_set_L = new Product("Good vibes set", description_goodvibes_set, 125, (long) 25, ProductSize.L, imgsItem16);
		Product goodvibes_set_XL = new Product("Good vibes set", description_goodvibes_set, 125, (long) 6, ProductSize.XL, imgsItem16);

		productRepository.save(goodvibes_set_XS);
		productRepository.save(goodvibes_set_S);
		productRepository.save(goodvibes_set_M);
		productRepository.save(goodvibes_set_L);
		productRepository.save(goodvibes_set_XL);

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
		for (int i=0; i<3; i++){
			productListTransaction2.add(cowboy_jacket_M);	
		}


		List<Product> productListTransaction3 = new ArrayList<>();
		for (int i=0; i<3; i++){
			productListTransaction2.add(puffer_jacket_XL);	
		}
		productListTransaction3.add(facha_jacket_XL);
		productListTransaction3.add(arizona_jeans_L);


		List<Product> productListTransaction4 = new ArrayList<>();
		productListTransaction4.add(topos_dress_XS);
		productListTransaction4.add(goodvibes_set_L);

		
		List<Product> productListTransaction5 = new ArrayList<>();
		productListTransaction5.add(black_body_S);
		productListTransaction5.add(paris_shirt_S);


		List<Product> productListTransaction6 = new ArrayList<>();
		productListTransaction6.add(pocket_pants_XL);
		productListTransaction6.add(winter_jacket_L);
		productListTransaction6.add(yellow_set_L);


		List<Product> productListTransaction7 = new ArrayList<>();
		productListTransaction7.add(winter_jacket_M);


		List<Product> productListTransaction8 = new ArrayList<>();
		productListTransaction8.add(longsleeves_M);
		productListTransaction8.add(happy_jaccket_M);


		List<Product> productListTransaction9 = new ArrayList<>();
		productListTransaction9.add(oversize_hoodie_L);


		List<Product> productListTransaction10 = new ArrayList<>();
		productListTransaction10.add(oversize_hoodie_M);
		productListTransaction10.add(oversize_hoodie_S);


		List<Product> productListTransaction11 = new ArrayList<>();
		productListTransaction11.add(pocket_pants_M);
		productListTransaction11.add(facha_jacket_M);


		List<Product> productListTransaction12 = new ArrayList<>();
		productListTransaction12.add(creamy_pants_L);


		List<Product> productListWishList = new ArrayList<>();
		productListWishList.add(black_body_L);

		List<Product> productListCart = new ArrayList<>();
		for(int i=0; i<2; i++) {
			productListCart.add(arizona_jeans_XL);
		}
		productListCart.add(cowboy_jacket_XL);

		Transaction transaction1 = new Transaction("PROCESSED", user, freeSelected, "17/02/2022", productListTransaction1);
        if(!couponService.applyCoupon(transaction1)) {
            transactionRepository.save(transaction1);
		}

		Transaction transaction2 = new Transaction("PROCESSED", user, threeXtwo, "17/02/2022", productListTransaction2);
        if(!couponService.applyCoupon(transaction2)) {
            transactionRepository.save(transaction2);
        }
		
		Transaction transaction3 = new Transaction("PROCESSED", user, threeXtwo, "17/02/2022", productListTransaction3);
		if(!couponService.applyCoupon(transaction3)) {
			transactionRepository.save(transaction3);
		}

		Transaction transaction4 = new Transaction("PROCESSED", user, globalDiscount10, "17/02/2022", productListTransaction4);
		if(!couponService.applyCoupon(transaction4)) {
			transactionRepository.save(transaction4);
		}

		Transaction transaction5 = new Transaction("PROCESSED", user, getTen, "17/02/2022", productListTransaction5);
		if(!couponService.applyCoupon(transaction5)) {
			transactionRepository.save(transaction5);
		}

		Transaction transaction6 = new Transaction("PROCESSED", user, null, "17/02/2022", productListTransaction6);
		transactionRepository.save(transaction6);
		
		Transaction transaction7 = new Transaction("PROCESSED", user, null, "01/13/2022",productListTransaction7);
		transactionRepository.save(transaction7);

		Transaction transaction8 = new Transaction("PROCESSED", user, null, "01/13/2022",productListTransaction8);
		transactionRepository.save(transaction8);

		Transaction transaction9 = new Transaction("PROCESSED", user, null, "01/13/2022",productListTransaction9);
		transactionRepository.save(transaction9);

		Transaction transaction10 = new Transaction("PROCESSED", user, null, "01/13/2022",productListTransaction10);
		transactionRepository.save(transaction10);

		Transaction transaction11 = new Transaction("PROCESSED", user, null, "01/13/2022",productListTransaction11);
		transactionRepository.save(transaction11);

		Transaction transaction12 = new Transaction("PROCESSED", user, null, "01/13/2022",productListTransaction12);
		transactionRepository.save(transaction12);

		Transaction wishList = new Transaction("WISHLIST", user, null, "24/02/2022", productListWishList);
		transactionRepository.save(wishList);

		Transaction cart = new Transaction("CART", user, null, "24/02/2022", productListCart);
		transactionRepository.save(cart);
	}
}