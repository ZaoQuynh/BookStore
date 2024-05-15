package com.example.BookStore.service;

import com.example.BookStore.entity.OrderItem;
import com.example.BookStore.entity.Product;

import java.util.List;

public interface OrderItemService {

    List<OrderItem> getsAllOrders();

}
