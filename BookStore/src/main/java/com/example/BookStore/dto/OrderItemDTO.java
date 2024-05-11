package com.example.BookStore.dto;

import com.example.BookStore.entity.Comment;
import com.example.BookStore.entity.Order;
import com.example.BookStore.entity.Product;
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
    private Comment comment;
    private Order order;
}
