package com.example.BookStore.service.impl;

import com.example.BookStore.dto.*;
import com.example.BookStore.entity.Order;
import com.example.BookStore.entity.Payment;
import com.example.BookStore.exception.InsufficientStockException;
import com.example.BookStore.mapper.OrderMapper;
import com.example.BookStore.repos.OrderRepos;
import com.example.BookStore.service.CartService;
import com.example.BookStore.service.InforDeliveryService;
import com.example.BookStore.service.OrderService;
import jakarta.transaction.Transactional;
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

    @Autowired
    private CartService cartService;

    @Autowired
    private InforDeliveryService inforDeliveryService;

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

    @Override
    @Transactional
    public void payment(Long userId, Long inforDeliveryId, String methodPayment) {
        List<CartItemDTO> cart = cartService.findActiveByCustomerIdForPayment(userId);

        cart.forEach(item -> {
            if(item.getQty()>item.getProduct().getStockQty()){
                throw new InsufficientStockException("Not enough stock for product: " + item.getProduct().getItem().getTitle());
            }
        });

        List<OrderDTO> orderDTOs = this.convertCartToOrder(cart);

        PaymentDTO payment = new PaymentDTO(Payment.EPaymentMethod.valueOf(methodPayment),
                Payment.EPaymentStatus.UNPAID, null);
        InforDeliveryDTO inforDelivery = inforDeliveryService.findById(inforDeliveryId);

        orderDTOs.forEach(item -> {
            item.setPayment(payment);
            item.setInforDelivery(inforDelivery);
            this.add(item);
        });
        cartService.delete(cart);
    }
}
