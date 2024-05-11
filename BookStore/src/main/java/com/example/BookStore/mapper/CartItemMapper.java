package com.example.BookStore.mapper;

import com.example.BookStore.dto.CartItemDTO;
import com.example.BookStore.entity.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper extends BaseMapper<CartItem, CartItemDTO>{
    @Override
    protected Class<CartItemDTO> getDtoClass() {
        return CartItemDTO.class;
    }

    @Override
    protected Class<CartItem> getEntityClass() {
        return CartItem.class;
    }

    @Override
    protected void configuration() {
    }
}
