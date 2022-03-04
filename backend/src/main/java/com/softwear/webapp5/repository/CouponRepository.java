package com.softwear.webapp5.repository;

import java.util.List;

import com.softwear.webapp5.model.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;

import com.softwear.webapp5.model.Coupon;
import org.springframework.data.jpa.repository.Query;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
	
	List<Coupon> findByCode(String code);
	List<Coupon> findByType(String type);
	List<Coupon> findByStartDate(String startDate);
	List<Coupon> findByDateOfExpiry(String dateOfExpiry);
	List<Coupon> findByMinimum(Float minimum);
	List<Coupon> findByDiscount(Float discount);

	@Query("SELECT coupon FROM Coupon coupon, Transaction trans " +
			"WHERE coupon=trans.usedCoupon and trans.user=:user")
	List<Coupon> findCouponsByUser(ShopUser user);
	
}