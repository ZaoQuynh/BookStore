package com.example.BookStore.repos;

import com.example.BookStore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepos extends JpaRepository<Product, Long> {

    Optional<Product> findByIdAndIsDeletedFalseAndIsBlockedFalse(Long id);

    List<Product> findBySellerIdAndIsDeletedFalseAndIsBlockedFalse(Long id);

    List<Product> findAllByIsDeletedFalseAndIsBlockedFalse();


    Optional<Product> findByIdAndIsDeletedFalse(Long id);

    List<Product> findBySellerIdAndIsDeletedFalse(Long id);

    List<Product> findAllByIsDeletedFalse();
}
