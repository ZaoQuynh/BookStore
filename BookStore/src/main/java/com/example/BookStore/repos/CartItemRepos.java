package com.example.BookStore.repos;

import com.example.BookStore.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepos extends JpaRepository<CartItem, Long> {
}
