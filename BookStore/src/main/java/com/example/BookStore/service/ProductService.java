package com.example.BookStore.service;

import com.example.BookStore.dto.ProductDTO;

public interface ProductService {
    ProductDTO findById(Long id);
    void updateStockQty(ProductDTO product, int qtyPurchased);
    void update(ProductDTO productDTO);
}
