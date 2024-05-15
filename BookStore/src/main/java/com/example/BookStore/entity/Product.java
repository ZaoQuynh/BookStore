package com.example.BookStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

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

    @Column
    private String title;
    @Column
    private String authors;
    @Column
    private int publisherYear;
    @Column
    private String genre;
    @Column
    private String description;

    @Column
    private String img;

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
    private List<User> favoritedByUsers;

    @OneToOne(mappedBy = "product", cascade = CascadeType.REMOVE)
    private CartItem cartItem;
}
