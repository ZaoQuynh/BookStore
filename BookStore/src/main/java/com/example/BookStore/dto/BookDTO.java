package com.example.BookStore.dto;

import com.example.BookStore.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
