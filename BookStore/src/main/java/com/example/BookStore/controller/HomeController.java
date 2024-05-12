package com.example.BookStore.controller;

import com.example.BookStore.service.ProductService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    
    private final ProductService productService;

    @GetMapping("/")
    public String products(Model model, HttpServletRequest request){
        model.addAttribute("products", productService.getsAllProducts());
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    String username = cookie.getValue();
                    model.addAttribute("username", username);
                    break;
                }
            }
        }
        return "home";
    }



}