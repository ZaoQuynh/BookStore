package com.example.BookStore.dto;

import com.example.BookStore.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDTO {
    private Long id;
    private ProductDTO product;
    private int qty;
    private BigDecimal currPrice;
    private CommentDTO comment;
    private Order order;
}
