package com.example.BookStore.controller;


import com.example.BookStore.entity.Product;
import com.example.BookStore.entity.User;
import com.example.BookStore.service.AuthService;
import com.example.BookStore.service.ProductService;
import com.example.BookStore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/admin")
    public String dashboard(){
        return "/admin/dashboard";
    }

    @GetMapping("/admin/users")
    public String user(Model model){
        model.addAttribute("users", userService.getUsers());
        return "/admin/users";
    }

    @PutMapping("/admin/users/{userId}/block")
    public ResponseEntity<?> disableUser(@PathVariable Long userId) {
        userService.blockUser(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/admin/users/{userId}/unBlock")
    public ResponseEntity<?> enableUser(@PathVariable Long userId) {
        userService.unblockUser(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/admin/users/{userId}/deleted")
    public ResponseEntity<?> deletedUser(@PathVariable Long userId) {
        userService.deletedUser(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/admin/users/{userId}/undeleted")
    public ResponseEntity<?> unDeletedUser(@PathVariable Long userId) {
        userService.unDeletedUser(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/admin/users/search")
    public String searchUser(@RequestParam("query") String query, Model model) {
        // Xử lý tìm kiếm người dùng dựa trên giá trị truy vấn 'query'
        List<User> searchResults = userService.searchUsers(query);
        model.addAttribute("searchResults", searchResults);
        return "/admin/users";
    }

    @GetMapping("/admin/products")
    public String products(Model model){
        model.addAttribute("products", productService.getsAllProductsAdmin());
        return "/admin/products";
    }

    @GetMapping("/admin/products/search")
    public String searchProduct(@RequestParam("query") String query, Model model) {
        List<Product> searchResults = productService.searchProductsAdmin(query);
        model.addAttribute("searchResults", searchResults);
        return "/admin/products";
    }


    @PutMapping("/admin/products/{id}/block")
    public ResponseEntity<?> disableProduct(@PathVariable Long id) {
        productService.blockProduct(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/admin/products/{id}/unBlock")
    public ResponseEntity<?> enableProduct(@PathVariable Long id) {
        productService.unblockProduct(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/admin/products/{id}/deleted")
    public ResponseEntity<?> deletedProduct(@PathVariable Long id) {
        productService.deletedProduct(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/admin/products/{id}/undeleted")
    public ResponseEntity<?> unDeletedProduct(@PathVariable Long id) {
        productService.unDeletedProduct(id);
        return ResponseEntity.ok().build();
    }

}
