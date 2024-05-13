package com.example.BookStore.repos;

import com.example.BookStore.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepos extends JpaRepository<OrderItem, Long> {
    @Query("SELECT o FROM OrderItem o WHERE o.product.id = ?1")
    List<OrderItem> getOrderItemByProductId(Long productId);
}
