package com.example.BookStore.controller;

import com.example.BookStore.entity.Product;
import com.example.BookStore.entity.User;
import com.example.BookStore.repos.ProductRepos;
import com.example.BookStore.service.ProductService;
import com.example.BookStore.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller@RequiredArgsConstructor

public class ProductController {

    private final ProductService productService;

    private final UserService userService;

    @GetMapping("/products")
    public String Products(HttpServletRequest request, Model model) {
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
        model.addAttribute("products", productService.getsAllProducts());
        return "/products";
    }

    @GetMapping("/products/search")
    public String searchProduct(HttpServletRequest request,@RequestParam("query") String query, Model model) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    String username = cookie.getValue();
                    Optional<User> userOptional = userService.getUserByUsername(username);
                    User user = userOptional.get();
                    model.addAttribute("username", username);
                    model.addAttribute("user", user);
                    break;
                }
            }
        }
        List<Product> searchResults = productService.searchProducts(query);
        model.addAttribute("products", searchResults);
        model.addAttribute("query", query);
        return "/search";
    }

}