package com.example.BookStore.service;

import com.example.BookStore.dto.CartItemDTO;

public interface CartItemService {
    public CartItemDTO update(CartItemDTO cartItemDTO);
    public boolean delete(Long id);
    public CartItemDTO findById(Long id);
    public CartItemDTO fixNewQuantity(CartItemDTO cartItemDTO, String qtyStr);
}
