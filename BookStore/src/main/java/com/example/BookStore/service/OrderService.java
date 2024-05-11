package com.example.BookStore.service;

import com.example.BookStore.dto.CartItemDTO;
import com.example.BookStore.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO add(OrderDTO orderDTO);
    List<OrderDTO> convertCartToOrder(List<CartItemDTO> cart);
}
