package com.softwear.webapp5.service;

import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.softwear.webapp5.model.Coupon;
import com.softwear.webapp5.model.User;
import com.softwear.webapp5.repository.CouponRepository;
import com.softwear.webapp5.repository.TransactionRepository;
import com.softwear.webapp5.repository.UserRepository;

@Service
public class CouponService {

	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private TransactionRepository transactionRepository;
	
	//Uncomment when Product is finished
	/*@Autowired
	private ProductRepository productRepository;*/

	private int[] transformStringDateToIntArray(String date) {
		String[] strArray = date.split("/");
		int[] dateArray = new int[3];
		for(int index = 0; index < strArray.length; index++) {
			dateArray[index] = Integer.valueOf(strArray[index]);
		}
		return dateArray;
	}

	private boolean checkDates(int[] startDate, int[] endDate) {
		return !(startDate[2] > endDate[2] ||
		(
			startDate[2] == endDate[2] 
			&& 
			(
				startDate[1] > endDate[1]
				||
				(
					startDate[1] == endDate[1] 
					&&
					startDate[0] > endDate[0]
				)
			)
		));
	}

	public boolean checkCoupon(User user, Coupon coupon) {
		// Uncomment block bellow when User is done
		/*if(couponRepository.findCouponsByUser(user).contains(coupon)) {
			return false;
		}*/
		Calendar currentDate = Calendar.getInstance();
		int[] intCurrentDate = {currentDate.get(Calendar.DAY_OF_MONTH), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.YEAR)};
		return checkDates(transformStringDateToIntArray(coupon.getStartDate()), intCurrentDate) && checkDates(intCurrentDate, transformStringDateToIntArray(coupon.getDateOfExpiry()));
	}

	public boolean addCoupon(Coupon coupon) {
		
		int[] stDate = transformStringDateToIntArray(coupon.getStartDate());
		int[] endDate = transformStringDateToIntArray(coupon.getDateOfExpiry());
		if(checkDates(stDate, endDate)) {
			couponRepository.save(coupon);
			return true;
		}
		return false;
	}

	public void deleteCoupon(Long id) {
		Optional<Coupon> coupon = couponRepository.findById(id);
		if(coupon.isPresent()) {
			couponRepository.delete(coupon.get());
		}
	}

	public List<Coupon> findAll() {
		return couponRepository.findAll();
	}
	
	public Optional<Coupon> findById(Long id) {
		return couponRepository.findById(id);
	}

	public List<Coupon> findByCode(String code) {
		return couponRepository.findByCode(code);
	}

	public List<Coupon> findByType(String type) {
		return couponRepository.findByType(type);
	}

	public List<Coupon> findByStartDate(String startDate) {
		return couponRepository.findByStartDate(startDate);
	}

	public List<Coupon> findByDateOfExpiry(String dateOfExpiry) {
		return couponRepository.findByDateOfExpiry(dateOfExpiry);
	}
	
	public List<Coupon> findByMinimum(Float minimum) {
		return couponRepository.findByMinimum(minimum);
	}

	public List<Coupon> findByDiscount(Float discount) {
		return couponRepository.findByDiscount(discount);
	}

	public List<Coupon> findCouponsByUser(User user) {
		return couponRepository.findCouponsByUser(user);
	}

	private boolean applyTotalPercentCoupon(Transaction transaction) {
		Coupon coupon = transaction.getUsedCoupon();
		if(coupon.getDiscount() == null) {
			return false;
		}
		transaction.setTotalPrice(transaction.getTotalPrice() * (1 - coupon.getDiscount()));
		return true;
	}

	private boolean applyTotalAmountCoupon(Transaction transaction) {
		Coupon coupon = transaction.getUsedCoupon();
		if(coupon.getDiscount() == null) {
			return false;
		}
		transaction.setTotalPrice(transaction.getTotalPrice() - coupon.getDiscount());
		return true;
	}

	private boolean applyMxNCoupon(Transaction transaction, int M, int N) {
		List<Product> products = transaction.getUsedCoupon().getAffectedProducts();
		if(products == null || products.isEmpty()) {
			return false;
		}
		boolean changed = false;
		for(Product product: products) {
			int numberOfDiscounts = Collections.frequency(transaction.getProducts(), product) / M;
			if(numberOfDiscounts > 0) {
				transaction.setTotalPrice(transaction.getTotalPrice() - (M - N) * numberOfDiscounts * product.getPrice());
				changed = true;
			}
		}
		return changed;
	}

	private boolean applyProductPercentCoupon(Transaction transaction) {
		Coupon coupon = transaction.getUsedCoupon();
		if(coupon.getDiscount() == null) {
			return false;
		}
		List<Product> products = coupon.getAffectedProducts();
		if(products == null || products.isEmpty()) {
			return false;
		}
		boolean changed = false;
		for(Product product: products) {
			int numberOfProducts = Collections.frequency(transaction.getProducts(), product);
			if(numberOfProducts > 0) {
				transaction.setTotalPrice(transaction.getTotalPrice() - numberOfProducts * coupon.getDiscount() * product.getPrice());
				changed = true;
			}
		}
		return changed;
	}

	private boolean applyProductAmountCoupon(Transaction transaction) {
		Coupon coupon = transaction.getUsedCoupon();
		if(coupon.getDiscount() == null) {
			return false;
		}
		List<Product> products = coupon.getAffectedProducts();
		if(products == null || products.isEmpty()) {
			return false;
		}
		boolean changed = false;
		for(Product product: products) {
			int numberOfProducts = Collections.frequency(transaction.getProducts(), product);
			if(numberOfProducts > 0) {
				transaction.setTotalPrice(transaction.getTotalPrice() - numberOfProducts * coupon.getDiscount());
				changed = true;
			}
		}
		return changed;
	}

	private boolean applyCouponByType(Transaction transaction) {
		Coupon coupon = transaction.getUsedCoupon();
		if(coupon.getType().equals("total_percentage")) {
			return applyTotalPercentCoupon(transaction);
		}
		if(coupon.getType().equals("total_amount")) {
			return applyTotalAmountCoupon(transaction);
		}
		if(coupon.getType().equals("2x1")) {
			return applyMxNCoupon(transaction, 2, 1);
		}
		if(coupon.getType().equals("3x2")) {
			return applyMxNCoupon(transaction, 3, 2);
		}
		if(coupon.getType().equals("product_percentage")) {
			return applyProductPercentCoupon(transaction);
		}
		if(coupon.getType().equals("product_amount")) {
			return applyProductAmountCoupon(transaction);
		}
		return false;
	}

	public boolean applyCoupon(Transaction transaction) {
		Coupon coupon = transaction.getUsedCoupon();
		if(coupon == null || checkCoupon(/*Change when User is done*//*transaction.getUser()*/null, coupon)) {
			return false;
		}
		if(transaction.getTotalPrice() != transaction.calculateTotalProductPrice()) {
			transaction.setTotalPrice(transaction.calculateTotalProductPrice());
		}
		if(coupon.getMinimum() != null && transaction.getTotalPrice() < coupon.getMinimum()) {
			return false;
		}
		if(!applyCouponByType(transaction)) {
			return false;
		}
		transactionRepository.save(transaction);
		return true;
	}

	public boolean applyCoupon(Long transactionId) {
		Optional<Transaction> transactionOptional= transactionRepository.findById(transactionId);
		if (transactionOptional.isEmpty()) {
			return false;
		}
		return applyCoupon(transactionOptional.get());
	}

}
