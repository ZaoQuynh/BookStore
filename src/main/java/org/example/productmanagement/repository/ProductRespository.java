package org.example.productmanagement.repository;


import org.example.productmanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRespository extends JpaRepository<Product, Integer> {
    @Query("SELECT e FROM Product e WHERE e.idUser = ?1 and e.isDeleted = false and e.isBlocked = false ")
    List<Product> findProductByUserId(int userId);

    Product findProductById(int id);

    @Query("UPDATE Product p SET p.isDeleted = true where p.id = ?1")
    Product updateProductIsDeleted(int id);
}
