package com.example.BookStore.repos;

import com.example.BookStore.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepos extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCustomer_Id(Long userId);
    Long countById(Long id);

    @Query("SELECT e FROM CartItem e WHERE e.customer.id = ?1 AND e.product.isDeleted = false AND e.product.isBlocked = false")
    List<CartItem> findActiveByCustomerId(Long userId);
}
