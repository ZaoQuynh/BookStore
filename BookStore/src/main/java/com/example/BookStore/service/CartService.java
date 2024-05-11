package com.example.BookStore.service;

import com.example.BookStore.dto.CartItemDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {
    List<CartItemDTO> findActiveByCustomerId(Long userId);
    List<CartItemDTO> findByCustomerId(Long userId);
    BigDecimal totalPriceOfCart(List<CartItemDTO> cart);
}
