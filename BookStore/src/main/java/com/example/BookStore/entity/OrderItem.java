package com.example.BookStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private int qty;
    @Column
    private BigDecimal currPrice;

    @Embedded
    private Comment comment;

    @Column
    private String receiverName;

    @Column
    private String phoneNumber;

    @Column
    private Long sellerId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;
}
