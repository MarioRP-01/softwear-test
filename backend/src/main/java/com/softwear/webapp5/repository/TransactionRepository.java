package com.softwear.webapp5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import com.softwear.webapp5.model.Coupon;
import com.softwear.webapp5.model.Transaction;
import com.softwear.webapp5.model.ShopUser;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByType(String type);
    List<Transaction> findByUser(ShopUser user);
    List<Transaction> findByUsedCoupon(Coupon coupon);
    List<Transaction> findByDate(String date);
    List<Transaction> findByTotalPrice(Double totalPrice);

    @Query("SELECT t FROM Transaction t " +
            "WHERE t.user = :user and t.type = 'CART'")
    Optional<Transaction> findCart(ShopUser user);

    @Query("SELECT t FROM Transaction t " +
            "WHERE t.user = :user and t.type = 'WISHLIST'")
    Optional<Transaction> findWishlist(ShopUser user);

    @Query("SELECT t FROM Transaction t " +
            "WHERE t.user = :user and not (t.type = 'CART' or t.type = 'WISHLIST')")
    List<Transaction> findPurchaseHistory(ShopUser user);

}
