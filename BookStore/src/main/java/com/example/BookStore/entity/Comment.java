package com.example.BookStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String commentText;
    @Column
    private String feedbackText;

    @OneToOne
    @JoinColumn(name = "commentor_id")
    private User commentor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
