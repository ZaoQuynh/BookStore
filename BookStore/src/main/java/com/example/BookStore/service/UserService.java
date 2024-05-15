package com.example.BookStore.service;

import com.example.BookStore.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService {
	UserDetailsService userDetailsService();
	User checkUserByEmail(String email);

	List<User> getUsers();

	void blockUser(Long userId);
	void unblockUser(Long userId);

	void deletedUser(Long userId);

	void unDeletedUser(Long userId);
	List<User> searchUsers(String query);

	Optional<User> getUserByUsername(String username);
	void updateUser(String fullName, String email, String username);

	void deleteUser(String username);



}
