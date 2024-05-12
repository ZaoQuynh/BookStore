package com.example.BookStore.service.impl;

import com.example.BookStore.dto.ProductDTO;
import com.example.BookStore.entity.Product;
import com.example.BookStore.exception.CartItemNotFoundException;
import com.example.BookStore.exception.ProductNotFoundException;
import com.example.BookStore.mapper.ProductMapper;
import com.example.BookStore.repos.ProductRepos;
import com.example.BookStore.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final Logger log = LogManager.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepos repos;

    @Autowired
    private ProductMapper mapper;

    @Override
    public ProductDTO findById(Long id) {
        Product product = repos
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Could not find any product with Id=" + id));
        return mapper.toDto(product);
    }

    @Override
    public void updateStockQty(ProductDTO product, int qtyPurchased) {
        int stockQty = product.getStockQty();
        stockQty-=qtyPurchased;
        product.setStockQty(stockQty);
        this.update(product);
    }

    @Override
    public void update(ProductDTO productDTO) {
        Product product = mapper.toEntity(productDTO);
        Product theProduct = repos
                .findById(product.getId())
                .orElseThrow(() -> new CartItemNotFoundException("Could not find any product with Id=" + productDTO.getId()));

        theProduct.setItem(product.getItem());
        theProduct.setStockQty(product.getStockQty());
        theProduct.setBlocked(product.isBlocked());
        theProduct.setDeleted(product.isDeleted());
        theProduct.setDiscountPercent(product.getDiscountPercent());
        theProduct.setPrice(product.getPrice());

        try{
            Product updated = repos.save(theProduct);
            mapper.toDto(updated);
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
