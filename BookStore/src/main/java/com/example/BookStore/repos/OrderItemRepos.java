package com.example.BookStore.repos;

import com.example.BookStore.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepos extends JpaRepository<OrderItem, Long> {
}
