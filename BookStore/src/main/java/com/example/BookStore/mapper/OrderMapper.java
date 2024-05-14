package com.example.BookStore.mapper;

import com.example.BookStore.dto.OrderDTO;
import com.example.BookStore.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper extends BaseMapper<Order, OrderDTO> {

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
        mapper.createTypeMap(OrderDTO.class, Order.class).addMapping(OrderDTO::getOrderStatus, Order::setOrderStatus);
    }
}
