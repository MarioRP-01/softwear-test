package com.softwear.webapp5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.softwear.webapp5.model.Coupon;
import com.softwear.webapp5.model.Transaction;
import com.softwear.webapp5.model.ShopUser;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    public List<Transaction> findByType(String type);
    public List<Transaction> findByUser(ShopUser user);
    public List<Transaction> findByUsedCoupon(Coupon coupon);
    public List<Transaction> findByDate(String date);

}
