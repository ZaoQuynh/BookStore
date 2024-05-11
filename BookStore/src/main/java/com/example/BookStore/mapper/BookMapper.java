package com.example.BookStore.mapper;

import com.example.BookStore.dto.BookDTO;
import com.example.BookStore.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper extends BaseMapper<Book, BookDTO>{
    @Override
    protected Class<BookDTO> getDtoClass() {
        return BookDTO.class;
    }

    @Override
    protected Class<Book> getEntityClass() {
        return Book.class;
    }

    @Override
    protected void configuration() {
    }
}

