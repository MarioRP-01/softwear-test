package com.softwear.webapp5.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
class Coupon {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;

    

    Coupon() {}

}