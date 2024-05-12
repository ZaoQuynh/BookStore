package com.example.BookStore.dto;

import com.example.BookStore.entity.User;
import com.example.BookStore.utility.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemDTO {
    private Long id;
    private ProductDTO product;
    private int qty;
    private UserDTO customer;

    public String getFormatQty() {
        return "x" + String.valueOf(qty);
    }

    public BigDecimal getTotalPrice() {
        return this.product.getCurrentPrice().multiply(BigDecimal.valueOf(this.qty));
    }

    public String getFormatTotalPrice() {
        return Utils.formatCurrencyVietnamese(this.getTotalPrice());
    }
}
