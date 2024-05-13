package com.example.BookStore.dto;

import com.example.BookStore.entity.CartItem;
import com.example.BookStore.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private BookDTO item;
    private BigDecimal price;
    private double discountPercent;
    private int stockQty;
    private UserDTO seller;
    private boolean isBlocked;
    private boolean isDeleted;
    private List<User> favoritedByUsers;
    private CartItem cartItem;
    private String imageUrl;
    
}
