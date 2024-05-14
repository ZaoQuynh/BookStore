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

import java.util.*;
import java.util.stream.Collectors;

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
        order.getOrderItems().forEach(item -> item.setOrder(order));
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
        Map<UserDTO, List<OrderItemDTO>> ordersMap = cart.stream()
                .collect(Collectors.groupingBy(item -> item.getProduct().getSeller(),
                        Collectors.mapping(item -> {
                            OrderItemDTO orderItem = new OrderItemDTO();
                            orderItem.setQty(item.getQty());
                            orderItem.setCurrPrice(item.getProduct().getCurrentPrice());
                            orderItem.setProduct(item.getProduct());
                            return orderItem;
                        }, Collectors.toList())));

        List<OrderDTO> orders = new ArrayList<>();
        ordersMap.forEach((seller, orderItems) -> {
            OrderDTO order = new OrderDTO();
            order.setOrderStatus(Order.EOrderStatus.PENDING);
            order.setOrderItems(orderItems);
            orders.add(order);
        });
        return orders;
    }
}
