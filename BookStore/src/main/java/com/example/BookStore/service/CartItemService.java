package com.example.BookStore.service;

import com.example.BookStore.dto.CartItemDTO;

public interface CartItemService {
    CartItemDTO add(CartItemDTO cartItemDTO);
    public CartItemDTO update(CartItemDTO cartItemDTO);
    public boolean delete(Long id);
    public CartItemDTO findById(Long id);
    int checkValue(CartItemDTO cartItemDTO, int qty);

    public CartItemDTO updateQuantity(CartItemDTO cartItemDTO, int newQty, String event);
    CartItemDTO findCartItemByProductAndCustomerId(Long productId, Long customerId);
}
