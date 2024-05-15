package com.example.BookStore.repos;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.BookStore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BookStore.entity.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsernameAndIsDeletedFalse(String username);

	Optional<User> findByEmail(String email);
	User findByRole(String role);

	List<User> findAllByIsDeletedFalse();
	@Transactional
	void deleteByEmailAndIsEnabled(String email, boolean isEnabled);

//    Optional<Object> findByUsername(String username);
}