package com.example.BookStore.service;

import com.example.BookStore.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO findById(Long id);
    void updateStockQty(ProductDTO product, int qtyPurchased);
    void update(ProductDTO productDTO);
    List<ProductDTO> findFavoriteByUserId(Long userId);
    boolean isFavoritedByUser(Long productId, Long userId);
    void loveProduct(Long productId, Long userId);
    ProductDTO findActiveById(Long productId);
    List<ProductDTO> findActive();
}
