package com.example.BookStore.service.impl;

import com.example.BookStore.entity.Product;
import com.example.BookStore.repos.ProductRepos;
import com.example.BookStore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepos productRepos;

    @Override
    public List<Product> getsAllProducts() {

        return productRepos.findAll();
    }

    @Override
    public List<Product> searchProducts(String query) {
        // Tìm kiếm người dùng dựa trên từ khóa 'query'
        List<Product> allProducts = productRepos.findAll();
        List<Product> searchResults = allProducts.stream()
                .filter(Product -> Product.getTitle().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
        return searchResults;
    }

    @Override
    public void blockProduct(Long id) {
        Product product = productRepos.findById(id).orElseThrow(() -> new UsernameNotFoundException("Product not found with id: " + id));
        product.setBlocked(true);
        productRepos.save(product);
    }

    @Override
    public void unblockProduct(Long id) {
        Product product = productRepos.findById(id).orElseThrow(() -> new UsernameNotFoundException("Product not found with id: " + id));
        product.setBlocked(false);
        productRepos.save(product);
    }

    @Override
    public void deletedProduct(Long id) {
        Product product = productRepos.findById(id).orElseThrow(() -> new UsernameNotFoundException("Product not found with id: " + id));
        product.setDeleted(true);
        productRepos.save(product);
    }

    @Override
    public void unDeletedProduct(Long id) {
        Product product = productRepos.findById(id).orElseThrow(() -> new UsernameNotFoundException("Product not found with id: " + id));
        product.setDeleted(false);
        productRepos.save(product);
    }
}
