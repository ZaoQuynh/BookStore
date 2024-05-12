package com.example.BookStore.repos;

import com.example.BookStore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepos extends JpaRepository<Order, Long> {
}
