package com.example.BookStore.service;

import com.example.BookStore.dto.ProductDTO;
import com.example.BookStore.dto.UserDTO;

import java.util.List;

public interface ProductService {
    ProductDTO findById(Long id);
    void updateStockQty(ProductDTO product, int qtyPurchased);
    void update(ProductDTO productDTO);
    List<ProductDTO> findFavoriteProductByUserId(Long userId);
    boolean isFavoritedByUser(Long productId, Long userId);
    void loveProduct(Long productId, Long userId);
}
