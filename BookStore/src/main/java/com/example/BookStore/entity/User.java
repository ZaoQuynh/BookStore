package com.example.BookStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String fullName;
    @Column
    private EGender gender;
    @Column
    private Date birthday;
    @Column
    private String email;
    @Column
    private ERole role;
    @Column
    private boolean isEnabled;
    @Column
    private boolean isBlocked;
    @Column
    private boolean isDeleted;

    @OneToMany(mappedBy = "user")
    private List<InforDelivery> inforDeliveries;

    @OneToMany(mappedBy = "customer")
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "seller")
    private List<Product> products;

    @ManyToMany
    private Set<Product> favoriteProducts = new HashSet<>();

    public enum EGender{
        MALE, FEMALE
    }

    public enum ERole{
        CUSTOMER, SELLER, ADMIN
    }
}
