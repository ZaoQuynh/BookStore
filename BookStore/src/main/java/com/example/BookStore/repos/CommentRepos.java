package com.example.BookStore.repos;

import com.example.BookStore.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepos extends JpaRepository<Comment, Long> {
}
