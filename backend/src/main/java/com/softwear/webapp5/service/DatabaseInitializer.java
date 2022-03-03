package com.softwear.webapp5.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softwear.webapp5.model.Coupon;
import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.repository.CouponRepository;
import com.softwear.webapp5.repository.ProductRepository;

@Service
public class DatabaseInitializer {
	
	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@PostConstruct
	public void init() {
		couponRepository.save(new Coupon("ESE10", "total_percentage", "15/02/22", "26/06/22", 10.00f, 0.1f, null));
		couponRepository.save(new Coupon("DAME10", "total_fix_amount", "15/02/22", "26/02/22", 10.00f, 2.5f, null));
		couponRepository.save(new Coupon("2x1SIEMPREENTRA", "2x1", "13/03/22", "26/06/22", null, null, null));
		couponRepository.save(new Coupon("PRUEBAEL3X2", "3x2", "12/02/22", "22/02/22", null, null, null));
		couponRepository.save(new Coupon("PORTODOLOALTO", "total_percentage", "15/02/22", "26/06/22", 0f, 0.5f, null));

		ArrayList<String> lista1 = new ArrayList<>();
		ArrayList<String> lista2 = new ArrayList<>();
		ArrayList<String> lista3 = new ArrayList<>();

		lista1.add("item1.webp");
		lista1.add("item2.webp");
		lista1.add("item3.webp");

		lista2.add("item4.webp");
		lista2.add("item5.webp");
		lista2.add("item6.webp");

		lista3.add("item7.webp");
		lista3.add("item8.webp");

		productRepository.save(new Product("camisa", "es cómoda", lista1, 10, 156, "Correos", "China", "Softwear", "15 de marzo de 2022"));
		productRepository.save(new Product("chaqueta", "está cómoda", lista2, 20, 156, "DMW", "Albacete", "Softwear", "26 de abril de 2022"));
		productRepository.save(new Product("pantalón", "ufff", lista3, 15, 156, "Amazon", "Murcia", "Softwear", "12 de enero de 2022"));
	}
	
}