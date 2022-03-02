package com.softwear.webapp5.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softwear.webapp5.model.Coupon;
import com.softwear.webapp5.model.Transaction;
import com.softwear.webapp5.repository.CouponRepository;
import com.softwear.webapp5.repository.TransactionRepository;

@Service
public class DatabaseInitializer {
	
	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private TransactionRepository transactionRepository;
	
	@PostConstruct
	public void init() {

		// Coupons

		Coupon coupon = new Coupon("ASTONISHOFFER", "total_percentage", "15/02/22", "26/06/22", 0f, 0.5f, null);

		couponRepository.save(new Coupon("10PER", "total_percentage", "15/02/22", "26/06/22", 10.00f, 0.1f, null));
		couponRepository.save(new Coupon("GIVEME10", "total_fix_amount", "15/02/22", "26/02/22", 10.00f, 2.5f, null));
		couponRepository.save(new Coupon("2X1", "2x1", "13/03/22", "26/06/22", null, null, null));
		couponRepository.save(new Coupon("TAKEALOOK3X2", "3x2", "12/02/22", "22/02/22", null, null, null));
		couponRepository.save(coupon);

		// Transactions

		Transaction transaction = new Transaction("CART", "24/02/22");
		transaction.setUsedCoupon(coupon);
		transactionRepository.save(transaction);
		
	}
	
}
