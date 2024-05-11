package com.example.BookStore.mapper;

import com.example.BookStore.dto.OrderDTO;
import com.example.BookStore.dto.ProductDTO;
import com.example.BookStore.entity.Order;
import com.example.BookStore.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper extends BaseMapper<Order, OrderDTO> {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    protected Class<OrderDTO> getDtoClass() {
        return OrderDTO.class;
    }

    @Override
    protected Class<Order> getEntityClass() {
        return Order.class;
    }

    @Override
    protected void configuration() {

    }
}
