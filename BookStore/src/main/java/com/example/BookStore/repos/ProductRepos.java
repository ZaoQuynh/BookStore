package com.example.BookStore.repos;

import com.example.BookStore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepos extends JpaRepository<Product, Long> {
    @Query("SELECT e FROM Product e WHERE e.seller.id = ?1 AND e.isDeleted = false AND e.isBlocked = false")
    List<Product> findProductByUserId(Long userId);
}
