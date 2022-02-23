package com.softwear.webapp5.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softwear.webapp5.model.Coupon;
import com.softwear.webapp5.repository.CouponRepository;

@Service
public class DatabaseInitializer {
	
	@Autowired
	private CouponRepository couponRepository;
	
	@PostConstruct
	public void init() {
		couponRepository.save(new Coupon("ESE10", "total_percentage", "15/02/22", "26/06/22", 10.00f, 0.1f, null));
		couponRepository.save(new Coupon("DAME10", "total_fix_amount", "15/02/22", "26/02/22", 10.00f, 2.5f, null));
		couponRepository.save(new Coupon("2x1SIEMPREENTRA", "2x1", "13/03/22", "26/06/22", null, null, null));
		couponRepository.save(new Coupon("PRUEBAEL3X2", "3x2", "12/02/22", "22/02/22", null, null, null));
		couponRepository.save(new Coupon("PORTODOLOALTO", "total_percentage", "15/02/22", "26/06/22", 0f, 0.5f, null));
	}
	
}
