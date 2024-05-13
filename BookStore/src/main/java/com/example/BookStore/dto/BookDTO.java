package com.example.BookStore.dto;

import com.example.BookStore.entity.Book;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {
    private String title;
    private String authors;
    private String publisher;
    private int publisherYear;
    private Book.EGenre genre;
    private String description;
}

