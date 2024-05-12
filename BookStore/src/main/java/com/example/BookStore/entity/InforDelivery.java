package com.example.BookStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "infor_deliveries")
public class InforDelivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String receiverName;
    @Column
    private String province;
    @Column
    private String district;
    @Column
    private String ward;
    @Column
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany
    private List<Order> orders;

}
