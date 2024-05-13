package com.example.BookStore.repos;

import com.example.BookStore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepos extends JpaRepository<Product, Long> {

    @Query("SELECT o FROM Product o JOIN o.favoriteByUsers u WHERE u.id = ?1")
    List<Product> findFavoriteByUserId(Long userId);
    @Query("SELECT o FROM Product o JOIN o.favoriteByUsers u WHERE o.id = ?1 AND u.id = ?2")
    Optional<Product> isFavoritedByUser(Long productId, Long userId);

}
