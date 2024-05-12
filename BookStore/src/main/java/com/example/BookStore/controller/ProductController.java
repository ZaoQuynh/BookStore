package com.example.BookStore.controller;

import com.example.BookStore.entity.Product;
import com.example.BookStore.entity.User;
import com.example.BookStore.repos.ProductRepos;
import com.example.BookStore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller@RequiredArgsConstructor

public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public String Products(Model model) {
        model.addAttribute("products", productService.getsAllProducts());
        return "/products";
    }

    @GetMapping("/products/search")
    public String searchProduct(@RequestParam("query") String query, Model model) {
        List<Product> searchResults = productService.searchProducts(query);
        model.addAttribute("products", searchResults);
        model.addAttribute("query", query);

        return "/search";
    }

}