package com.example.BookStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Comment {
    @Column
    private String commentText;
    @Column
    private String feedbackText;
    @Column
    private Date date;
    @Column
    private String commentorName;
}
