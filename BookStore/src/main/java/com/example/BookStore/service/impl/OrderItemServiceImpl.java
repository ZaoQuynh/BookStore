package com.example.BookStore.service.impl;

import com.example.BookStore.dto.CommentDTO;
import com.example.BookStore.dto.OrderItemDTO;
import com.example.BookStore.entity.OrderItem;
import com.example.BookStore.mapper.OrderItemMapper;
import com.example.BookStore.repos.OrderItemRepos;
import com.example.BookStore.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepos repos;

    @Autowired
    private OrderItemMapper mapper;

    @Override
    public int countQtyProductByProductId(Long productId) {
        List<OrderItem> orderItems = repos.countQtyProductByProductId(productId);
        AtomicInteger count = new AtomicInteger();
        orderItems.forEach(o -> count.addAndGet(o.getQty()));
        return count.get();
    }

    @Override
    public List<CommentDTO> findCommentByProductId(Long productId) {
        List<OrderItem> orderItems = repos.countQtyProductByProductId(productId);
        List<OrderItemDTO> orderItemDTOs = mapper.toDto(orderItems);
        return orderItemDTOs.stream()
                .map(OrderItemDTO::getComment)
                .filter(Objects::nonNull) // Lọc bỏ các comment null
                .filter(comment -> comment.getCommentText() != null)
                .toList();

    }
}
