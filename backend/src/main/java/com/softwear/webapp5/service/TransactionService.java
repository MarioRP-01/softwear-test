package com.softwear.webapp5.service;

import java.util.List;
import java.util.Optional;

import com.softwear.webapp5.repository.TransactionRepository;
import com.softwear.webapp5.model.Coupon;
import com.softwear.webapp5.model.ShopUser;
import com.softwear.webapp5.model.Transaction;
import com.softwear.webapp5.model.ShopUser;

public class TransactionService {
    
    private TransactionRepository transactionRepository;

    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id);
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public List<Transaction> findByType(String type) {
        return transactionRepository.findByType(type);
    }

    public List<Transaction> findByUser(ShopUser user) {
        return transactionRepository.findByUser(user);
    }

    public List<Transaction> findByUsedCoupon(Coupon coupon) {
        return transactionRepository.findByUsedCoupon(coupon);
    }

    public List<Transaction> findByDate(String date) {
        return transactionRepository.findByDate(date);
    }

}
