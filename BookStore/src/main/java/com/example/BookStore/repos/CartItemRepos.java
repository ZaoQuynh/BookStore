package com.example.BookStore.repos;

import com.example.BookStore.entity.CartItem;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepos extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCustomer_Id(Long userId);
    Long countById(Long id);

    @Query("SELECT e FROM CartItem e WHERE e.customer.id = ?1 " +
            "AND e.product.isDeleted = false " +
            "AND e.product.isBlocked = false " +
            "AND e.product.stockQty>0" +
            "AND e.qty <= e.product.stockQty")
    List<CartItem> findActiveByCustomerId(Long userId);

    @Query("SELECT e FROM CartItem e WHERE e.product.id = ?1 AND e.customer.id =?2")
    CartItem findCartItemByProductAndCustomerId(Long productId, Long customerId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT e FROM CartItem e WHERE e.customer.id = ?1 " +
            "AND e.product.isDeleted = false " +
            "AND e.product.isBlocked = false ")
    List<CartItem> findActiveByCustomerIdForPayment(Long userId);
}
