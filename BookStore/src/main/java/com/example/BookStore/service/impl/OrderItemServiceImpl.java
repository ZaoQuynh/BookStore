package com.example.BookStore.service.impl;

import com.example.BookStore.entity.OrderItem;
import com.example.BookStore.entity.Product;
import com.example.BookStore.repos.OrderItemRepos;
import com.example.BookStore.repos.ProductRepos;
import com.example.BookStore.service.OrderItemService;
import com.example.BookStore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepos orderItemRepos;


    @Override
    public List<OrderItem> getsAllOrders() {
        return orderItemRepos.findAll();
    }
}
