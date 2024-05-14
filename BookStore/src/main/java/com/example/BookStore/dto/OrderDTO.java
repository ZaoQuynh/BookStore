package com.example.BookStore.dto;

import com.example.BookStore.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
    private Long id;
    private List<OrderItemDTO> orderItems;
    private Date orderDate;
    private Order.EOrderStatus orderStatus;
    private PaymentDTO payment;
    private InforDeliveryDTO inforDelivery;
}
