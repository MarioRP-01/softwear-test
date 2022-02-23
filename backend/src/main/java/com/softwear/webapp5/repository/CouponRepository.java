package com.softwear.webapp5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softwear.webapp5.model.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
	
	public List<Coupon> findByCode(String code);
	public List<Coupon> findByType(String type);
	public List<Coupon> findByStartDate(String startDate);
	public List<Coupon> findByDateOfExpiry(String dateOfExpiry);
	public List<Coupon> findByMinimum(Float minimum);
	public List<Coupon> findByDiscount(Float discount);
	
}