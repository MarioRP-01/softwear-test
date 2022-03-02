package com.softwear.webapp5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

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
		// Uncomment block bellow when TransactionRepository.getCouponByUser is done
		/*if(transactionRepository.getCouponsByUser(user).contains(coupon)) {
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

}
