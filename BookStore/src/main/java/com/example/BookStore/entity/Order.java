package com.example.BookStore.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    @Column
    private Date orderDate;
    @Column
    private EOrderStatus orderStatus;

    @Embedded
    private Payment payment;

    public enum EOrderStatus{
        PENDING, PROCESSING, SHIPPED, DELIVERED, RETURN, CANCELLED, COMPLETED
    }
}
