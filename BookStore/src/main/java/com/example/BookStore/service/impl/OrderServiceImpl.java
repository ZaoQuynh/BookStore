package com.example.BookStore.service.impl;

import com.example.BookStore.dto.*;
import com.example.BookStore.entity.Order;
import com.example.BookStore.mapper.OrderMapper;
import com.example.BookStore.repos.OrderRepos;
import com.example.BookStore.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final Logger log = LogManager.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepos repos;

    @Autowired
    private OrderMapper mapper;

    @Override
    public OrderDTO add(OrderDTO orderDTO) {
        Order order = mapper.toEntity(orderDTO);
        try {
            order.setOrderDate(new Date());
            Order added = repos.save(order);
            return mapper.toDto(added);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public List<OrderDTO> convertCartToOrder(List<CartItemDTO> cart) {
        List<OrderDTO> orders = new ArrayList<>(Collections.emptyList());

        List<UserDTO> sellers = cart.stream()
                .map(CartItemDTO::getProduct)
                .map(ProductDTO::getSeller)
                .distinct()
                .toList();

        sellers.forEach(seller -> {
            List<OrderItemDTO> orderItems = cart.stream().filter(item -> item.getProduct().getSeller()==seller)
                    .map(item -> {
                        OrderItemDTO orderItem = new OrderItemDTO();
                        orderItem.setQty(item.getQty());
                        orderItem.setCurrPrice(item.getProduct().getCurrentPrice());
                        orderItem.setProduct(item.getProduct());
                        return orderItem;
                    }).toList();
            OrderDTO order = new OrderDTO();
            order.setOrderItems(orderItems);
            order.setOrderStatus(Order.EOrderStatus.PENDING);
            orders.add(order);
        });

        return orders;
    }
}
