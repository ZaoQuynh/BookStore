package com.example.BookStore.controller;

import com.example.BookStore.entity.User;
import com.example.BookStore.service.ProductService;
import com.example.BookStore.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HomeController {
    
    private final ProductService productService;

    private final UserService userService;

    @GetMapping("/")
    public String products(Model model, HttpServletRequest request){
        model.addAttribute("products", productService.getsAllProducts());
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    String username = cookie.getValue();
                    Optional<User> userOptional = userService.getUserByUsername(username);
                    User user = userOptional.get();
                    model.addAttribute("user", user);
                    model.addAttribute("username", username);
                    break;
                }
            }
        }
        return "home";
    }



}