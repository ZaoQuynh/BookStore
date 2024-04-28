package com.example.BookStore.repos;

import com.example.BookStore.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepos extends JpaRepository<Chat, Long> {
}
