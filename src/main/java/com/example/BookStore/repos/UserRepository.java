package com.example.BookStore.repos;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BookStore.entity.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);
	User findByRole(String role);
	@Transactional
	void deleteByEmailAndIsEnabled(String email, boolean isEnabled);

//    Optional<Object> findByUsername(String username);
}