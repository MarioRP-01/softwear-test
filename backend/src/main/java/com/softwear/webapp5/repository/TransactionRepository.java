package com.softwear.webapp5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.annotation.Target;
import java.util.List;

import com.softwear.webapp5.model.Coupon;
import com.softwear.webapp5.model.Transaction;
import com.softwear.webapp5.model.ShopUser;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByType(String type);
    List<Transaction> findByUser(ShopUser user);
    List<Transaction> findByUsedCoupon(Coupon coupon);
    List<Transaction> findByDate(String date);
    List<Transaction> findByTotalPrice(Double totalPrice);

}
