package com.example.BookStore.service.impl;

import com.example.BookStore.dto.CartItemDTO;
import com.example.BookStore.entity.CartItem;
import com.example.BookStore.exception.CartItemNotFoundException;
import com.example.BookStore.mapper.CartItemMapper;
import com.example.BookStore.repos.CartItemRepos;
import com.example.BookStore.service.CartItemService;
import com.example.BookStore.service.CartService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private final Logger log = LogManager.getLogger(CartServiceImpl.class);

    @Autowired
    private CartItemRepos cartItemRepos;
    @Autowired
    private CartItemService cartItemService;



    @Autowired
    private CartItemMapper cartItemMapper;

    @Override
    public List<CartItemDTO> findActiveByCustomerId(Long userId) {
        List<CartItem> cartItems = cartItemRepos.findActiveByCustomerId(userId);
        if (cartItems == null || cartItems.isEmpty())
            throw new CartItemNotFoundException("Could not find any active product with userId=" + userId);
        return cartItemMapper.toDto(cartItems);
    }

    @Override
    public List<CartItemDTO> findByCustomerId(Long userId) {
        List<CartItem> cartItems = cartItemRepos.findByCustomer_Id(userId);
        if (cartItems == null || cartItems.isEmpty())
            throw new CartItemNotFoundException("Could not find all product with userId=" + userId);
        return cartItemMapper.toDto(cartItems);
    }

    @Override
    public BigDecimal totalPriceOfCart(List<CartItemDTO> cart) {
        return cart.stream()
                .map(CartItemDTO::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void delete(List<CartItemDTO> cart) {
        cart.forEach(item -> cartItemService.delete(item.getId()));
    }
}
