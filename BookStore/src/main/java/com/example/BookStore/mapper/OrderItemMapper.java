package com.example.BookStore.mapper;

import com.example.BookStore.dto.OrderItemDTO;
import com.example.BookStore.entity.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper extends BaseMapper<OrderItem, OrderItemDTO> {
    @Override
    protected Class<OrderItemDTO> getDtoClass() {
        return OrderItemDTO.class;
    }

    @Override
    protected Class<OrderItem> getEntityClass() {
        return OrderItem.class;
    }

    @Override
    protected void configuration() {
    }
}
