package com.example.BookStore.service;

import com.example.BookStore.entity.Product;
import com.example.BookStore.entity.User;

import java.util.List;

public interface ProductService {

    List<Product> getsAllProducts();

    List<Product> searchProducts(String query);
    
    List<Product> searchProductsAdmin(String query);

    void blockProduct(Long id);

    void unblockProduct(Long id);

    void deletedProduct(Long id);

    void unDeletedProduct(Long id);

    List<Product> getsAllProductsBySeller(Long id);

    List<Product> getsAllProductsAdmin();
}
