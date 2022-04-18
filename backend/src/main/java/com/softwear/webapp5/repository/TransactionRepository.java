package com.softwear.webapp5.repository;

import com.softwear.webapp5.model.Product;
import org.springframework.data.domain.Page;  
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import com.softwear.webapp5.data.StaticDTO;
import com.softwear.webapp5.model.Coupon;
import com.softwear.webapp5.model.Transaction;
import com.softwear.webapp5.model.ShopUser;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByType(String type);
    List<Transaction> findByUser(ShopUser user);

    Page<Transaction> findByUser(ShopUser user, Pageable page);

    List<Transaction> findByUsedCoupon(Coupon coupon);
    List<Transaction> findByDate(String date);
    List<Transaction> findByTotalPrice(Double totalPrice);

    @Query("SELECT t FROM Transaction t " +
            "WHERE t.user = :user and t.type = 'CART'")
    Page<Transaction> findCart(ShopUser user, Pageable page);

    
    @Query("SELECT t FROM Transaction t " +
            "WHERE t.user = :user and t.type = 'CART'")
    Optional<Transaction> findCart(ShopUser user);
    
    @Query("SELECT t FROM Transaction t " +
            "WHERE t.user = :user and t.type = 'WISHLIST'")
    Page<Transaction> findWishlist(ShopUser user, Pageable page);
    
    @Query("SELECT t FROM Transaction t " +
            "WHERE t.user = :user and t.type = 'WISHLIST'")
    Optional<Transaction> findWishlist(ShopUser user);


    @Query("SELECT p FROM Product p, Transaction t " +
            "WHERE t.user = :user and t.type = 'WISHLIST' and p MEMBER OF t.products and p.name = :productName")
    Optional<Product> findProductInWishlist(ShopUser user, String productName);

    @Query("SELECT t FROM Transaction t " +
            "WHERE t.user = :user and not (t.type = 'CART' or t.type = 'WISHLIST') " +
            "ORDER BY t.id DESC")
    Page<Transaction> findPurchaseHistory(ShopUser user, Pageable page);
    
    @Query("SELECT t FROM Transaction t " +
            "WHERE t.user = :user and not (t.type = 'CART' or t.type = 'WISHLIST') " +
            "ORDER BY t.id DESC")
    List<Transaction> findPurchaseHistory(ShopUser user);

    @Query("SELECT new com.softwear.webapp5.data.StaticDTO(p.name, SUM(p.price), COUNT(p.name)) "
            + "FROM Transaction t "
            + "LEFT JOIN t.products p "
            + "WHERE NOT (t.type = 'CART' OR t.type = 'WISHLIST') "
            + "GROUP BY p.name")
    public List<StaticDTO> getStatics();
    
    @Query("SELECT t FROM Transaction t " +
            "WHERE t.type = :type")
    Page<Transaction> findByType(String type, Pageable page);

    @Query("SELECT t FROM Transaction t " +
            "WHERE t.user = :user and t.type = :type " +
            "ORDER BY t.id DESC")
    Page<Transaction> findByTypeAndUser(ShopUser user, String type, Pageable page);    

    @Query("SELECT t FROM Transaction t ")
    Page<Transaction> findAll(Pageable page);
}
