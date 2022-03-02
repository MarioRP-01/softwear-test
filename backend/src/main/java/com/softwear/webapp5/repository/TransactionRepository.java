package com.softwear.webapp5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.annotation.Target;
import java.util.List;

import com.softwear.webapp5.model.Coupon;
import com.softwear.webapp5.model.Transaction;
import com.softwear.webapp5.model.User;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByType(String type);
    List<Transaction> findByUser(User user);
    List<Transaction> findByUsedCoupon(Coupon coupon);
    List<Transaction> findByDate(String date);
    List<Transaction> findByTotalPrice(Double totalPrice);

}
