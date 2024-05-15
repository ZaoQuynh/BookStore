package com.example.BookStore.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.BookStore.dto.LoginRequest;
import com.example.BookStore.dto.LoginResponse;
import com.example.BookStore.dto.RegisterRequest;
import com.example.BookStore.dto.RegisterResponse;
import com.example.BookStore.email.EmailSender;
import com.example.BookStore.entity.User;
import com.example.BookStore.repos.UserRepository;
import com.example.BookStore.service.AuthService;
import com.example.BookStore.service.JwtService;
import com.example.BookStore.service.UserService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailSender emailSender;
    private final UserService userService;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication;
        // verify via username password
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken
                            (loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (AuthenticationException e) {
            e.printStackTrace();
            authentication = null;
        }
        LoginResponse loginResponse = new LoginResponse();
        if (authentication != null && authentication.isAuthenticated()) {
            var user = userRepository.findByUsernameAndIsDeletedFalse(loginRequest.getUsername())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Email or password"));


            if (user.isDeleted() && user != null) {
                 loginResponse.setMessage("User is deleted!");
                return loginResponse;
            } else if (user.isDeleted() && user != null) {
                loginResponse.setMessage("User is blocked!");
                return loginResponse;
            }

            var accessToken = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

            loginResponse.setAccessToken(accessToken);
            loginResponse.setRefreshToken(refreshToken);
            loginResponse.setRole(user.getRole());
        } else {
            loginResponse.setMessage("Invalid Email or password");
        }


        return loginResponse;
    }

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        User user = new User();
        RegisterResponse registerResponse = new RegisterResponse();

        Optional<User> usernameExisted = userRepository.findByUsernameAndIsDeletedFalse(registerRequest.getUsername());
        Optional<User> emailExisted = userRepository.findByEmail(registerRequest.getEmail());

        // kiểm tra xem đã tồn tại Username này chưa
        if (usernameExisted.isPresent()) {
            registerResponse.setError("Username existed");
            return registerResponse;
        }
        if (emailExisted.isPresent()) {
            if (emailExisted.get().isEnabled()) {
                registerResponse.setError("Email existed");
                return registerResponse;
            }
        }
        user.setEmail(registerRequest.getEmail());
        user.setUsername(registerRequest.getUsername());
        user.setFullName(registerRequest.getFullname());
        user.setRole(registerRequest.getRole());
        user.setBirthday(registerRequest.getBirthDay());
        user.setGender(registerRequest.getGender());
        user.setEnabled(false);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        User newUser = userRepository.save(user);
        String activateToken = jwtService.generateActivateToken(newUser);

        registerResponse.setToken(activateToken);
        System.out.println(activateToken);
        try {
            emailSender.sendActivationLink(newUser, "http://localhost:8080", activateToken);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return registerResponse;
    }

    @Override
    public boolean verifyUser(String code) {
        String username = jwtService.extractUsername(code);
        if (!username.isEmpty()) {
            UserDetails userDetails = this.userService.userDetailsService().loadUserByUsername(username);
            Optional<User> user = this.userRepository.findByUsernameAndIsDeletedFalse(username);
            if (jwtService.isTokenValid(code, userDetails)) {
                User newUser = user.get();
                newUser.setEnabled(true);
                this.userRepository.save(newUser);
                this.userRepository.deleteByEmailAndIsEnabled(newUser.getEmail(), false);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean forgetPass(String email) {
        User user = userService.checkUserByEmail(email);

        try {
            emailSender.sendLinkResetPass(user, "http://localhost:8080", email);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return (user != null);
    }

    @Override
    public boolean resetPass(String password, String email) {
        User user = userService.checkUserByEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return (user != null);
    }
}

