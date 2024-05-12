package com.example.BookStore.service.impl;

import com.example.BookStore.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.BookStore.repos.UserRepository;
import com.example.BookStore.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	private final UserRepository userRepository;
	@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				return userRepository.findByUsername(username)
						.orElseThrow(() -> new UsernameNotFoundException("User not found"));
			}
		};
	}

	@Override
	public User checkUserByEmail(String email) {

		return userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

	@Override
	public List<User> gatUsers() {
		return userRepository.findAll();
	}

	@Override
	public void blockUser(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));
		user.setBlocked(true);
		userRepository.save(user);
	}

	@Override
	public void unblockUser(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));
		user.setBlocked(false);
		userRepository.save(user);
	}

	@Override
	public void deletedUser(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));
		user.setDeleted(true);
		userRepository.save(user);
	}

	@Override
	public void unDeletedUser(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));
		user.setDeleted(false);
		userRepository.save(user);
	}

	@Override
	public List<User> searchUsers(String query) {
		// Tìm kiếm người dùng dựa trên từ khóa 'query'
		List<User> allUsers = userRepository.findAll();
		List<User> searchResults = allUsers.stream()
				.filter(user -> user.getUsername().toLowerCase().contains(query.toLowerCase()))
				.collect(Collectors.toList());
		return searchResults;
	}


}
