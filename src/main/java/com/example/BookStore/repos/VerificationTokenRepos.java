package com.example.BookStore.repos;

import com.example.BookStore.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepos extends JpaRepository<VerificationToken, Long> {
}
