package com.example.BookStore.service;

import com.example.BookStore.dto.CommentDTO;

import java.util.List;

public interface OrderItemService {
    int countQtyProductByProductId(Long productId);
    List<CommentDTO> findCommentByProductId(Long productId);
}
