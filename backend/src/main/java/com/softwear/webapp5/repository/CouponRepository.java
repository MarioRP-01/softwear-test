package com.softwear.webapp5.repository;

import java.util.List;
import java.util.Optional;

import com.softwear.webapp5.model.ShopUser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.softwear.webapp5.model.Coupon;
import org.springframework.data.jpa.repository.Query;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
	
	Optional<Coupon> findByCode(String code);
	List<Coupon> findByType(String type);
	List<Coupon> findByStartDate(String startDate);
	List<Coupon> findByDateOfExpiry(String dateOfExpiry);
	List<Coupon> findByMinimum(Float minimum);
	List<Coupon> findByDiscount(Float discount);
	
		
	@Query("SELECT coupon FROM Coupon coupon, Transaction trans " +
			"WHERE coupon=trans.usedCoupon and trans.user=:user and trans.type not in ('CART', 'WISHLIST')")
	List<Coupon> findCouponsByUser(ShopUser user);
	
}