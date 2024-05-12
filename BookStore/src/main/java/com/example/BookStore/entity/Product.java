package com.example.BookStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Book item;

    @Column
    private BigDecimal price;
    @Column
    private double discountPercent;
    @Column
    private int stockQty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private User seller;

    @Column
    private boolean isBlocked;
    @Column
    private boolean isDeleted;

    @ManyToMany(mappedBy = "favoriteProducts")
    private Set<User> favoriteByUsers = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<CartItem> cartItem;
}
