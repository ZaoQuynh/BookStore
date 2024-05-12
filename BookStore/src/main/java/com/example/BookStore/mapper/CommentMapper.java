package com.example.BookStore.mapper;

import com.example.BookStore.dto.CommentDTO;
import com.example.BookStore.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper extends BaseMapper<Comment, CommentDTO>{
    @Override
    protected Class<CommentDTO> getDtoClass() {
        return CommentDTO.class;
    }

    @Override
    protected Class<Comment> getEntityClass() {
        return Comment.class;
    }

    @Override
    protected void configuration() {

    }
}
