package com.example.BookStore.controller;


import com.example.BookStore.dto.*;
import com.example.BookStore.entity.OrderItem;
import com.example.BookStore.entity.Product;
import com.example.BookStore.entity.User;
import com.example.BookStore.service.OrderItemService;
import com.example.BookStore.service.ProductService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.BookStore.builder.RegisterRequestBuilder;
import com.example.BookStore.service.AuthService;
import com.example.BookStore.service.UserService;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final ProductService productService;
    private final OrderItemService orderItemService;
    private final AuthService authService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "forgot-password";
    }

    @GetMapping("/reset-password")
    public String resetPassword(@RequestParam("email") String email, Model model) {
        model.addAttribute("email", email);
        return "reset-password";
    }

    @PostMapping("/forgot-password")
    @ResponseBody
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        if (authService.forgetPass(email)) {
            return ResponseEntity.ok().body("success");
        } else {
            return ResponseEntity.badRequest().body("not found");
        }

    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<RegisterResponse> register(@RequestParam String fullname,
                                                     @RequestParam String username, @RequestParam String email,
                                                     @RequestParam String password, @RequestParam String gender,
                                                     @RequestParam String role) {
        System.out.println("register post");
        RegisterRequest registerRequest = new RegisterRequest();
        RegisterRequestBuilder registerRequestBuilder = new RegisterRequestBuilder();
        registerRequest = registerRequestBuilder
                .fullname(fullname).username(username).email(email)
                .password(password).gender(gender).role(role).build();
        RegisterResponse registerResponse = authService.register(registerRequest);
        return ResponseEntity.ok(registerResponse);
    }

    @PostMapping("/reset-password")
    @ResponseBody
    public ResponseEntity<String> resetPassword(@RequestParam String password, @RequestParam String email) {
        if (authService.resetPass(password, email)) {
            return ResponseEntity.ok().body("success");
        } else {
            return ResponseEntity.badRequest().body("not found");
        }

    }

    @GetMapping("/notify-check-email")
    public String notifyCheckEmail() {
        return "notify-check-email";
    }

    @GetMapping("/notify-check-email-createNewPass")
    public String notifyCheckEmailPass() {
        return "notify-check-email-createNewPass";
    }

    @GetMapping("/verify")
    public String verifyUser(@RequestParam("code") String code) {
        if (authService.verifyUser(code)) {
            System.out.println("verify get");
            return "success-register";
        } else {
            return "404";
        }
    }

    @PostMapping("/authentication")
    @ResponseBody
    public ResponseEntity<LoginResponse> login(@RequestParam String username, @RequestParam String password) {
        System.out.println("post");
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        LoginResponse loginResponse = authService.login(loginRequest);
        // if login success
        if (loginResponse.getMessage() == null) {
            System.out.println("set cookie");
            HttpCookie cookieAccessToken = ResponseCookie.from("accessToken", loginResponse.getAccessToken())
                    .httpOnly(true)
                    .build();
            HttpCookie cookieRefreshToken = ResponseCookie.from("refreshToken", loginResponse.getRefreshToken())
                    .httpOnly(true)
                    .build();
            HttpCookie userName = ResponseCookie.from("username", username)
                    .httpOnly(true)
                    .build();
            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, cookieAccessToken.toString(), cookieRefreshToken.toString(), userName.toString())
                    .body(loginResponse);

        }
        return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/login")
    public String login() {
        System.out.println("vo");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }

        return "redirect:/";
    }

    @GetMapping("/profile")
    public String profile(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        String username = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                    break;
                }
            }
        }
        if (username != null) {
            Optional<User> userOptional = userService.getUserByUsername(username);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                List<Product> products = productService.getsAllProductsBySeller(user.getId());
                model.addAttribute("username", username);
                model.addAttribute("products", products);
                model.addAttribute("user", user);
                return "profile"; // không cần dấu /
            } else {
                return "login"; // không cần dấu /
            }
        } else {
            return "login"; // không cần dấu /
        }
    }

    @GetMapping("/profile/edit")
    public String editProfile(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        String username = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                    break;
                }
            }
        }
        if (username != null) {
            Optional<User> userOptional = userService.getUserByUsername(username);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                model.addAttribute("username", username);
                model.addAttribute("user", user);
                return "edit-profile"; // không cần dấu /
            } else {
                return "login"; // không cần dấu /
            }
        } else {
            return "login"; // không cần dấu /
        }
    }

    @GetMapping("/profile/statistical")
    public String statisticalProfile(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        String username = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                    break;
                }
            }
        }
        if (username != null) {
            Optional<User> userOptional = userService.getUserByUsername(username);
            List<OrderItem> orderItems =  orderItemService.getsAllOrders();
            BigDecimal totalPrice = orderItems.stream()
                    .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQty())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            model.addAttribute("totalPrice", totalPrice);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                model.addAttribute("username", username);
                model.addAttribute("orderItems", orderItems);
                model.addAttribute("user", user);
                return "statistical"; // không cần dấu /
            } else {
                return "login"; // không cần dấu /
            }
        } else {
            return "login"; // không cần dấu /
        }
    }

    @PostMapping("/profile/edit")
    @ResponseBody
    public ResponseEntity<UpdateResponse> updateProfile(HttpServletRequest request, @RequestParam String fullName, @RequestParam String email) {
        Cookie[] cookies = request.getCookies();
        String username = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                    break;
                }
            }
        }
        userService.updateUser(fullName, email, username);
        UpdateResponse updateResponse = new UpdateResponse();
        updateResponse.setMessage("success");
        return ResponseEntity.ok().body(updateResponse); // trả về "success" khi cập nhật thành công
    }



    @PostMapping("/profile")
    @ResponseBody
    public ResponseEntity<UpdateResponse> deleteProfile(HttpServletResponse response, @RequestParam String username, @RequestParam String password) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        LoginResponse loginResponse = authService.login(loginRequest);
        if (loginResponse.getMessage() == null) {
            userService.deleteUser(username);
            deleteCookies(response, "accessToken", "refreshToken", "username");
            UpdateResponse updateResponse = new UpdateResponse();
            updateResponse.setMessage("success");
            return ResponseEntity.ok().body(updateResponse);
        } else {
            UpdateResponse updateResponse = new UpdateResponse();
            updateResponse.setMessage("error");
            return ResponseEntity.badRequest().body(updateResponse);
        }

    }

    private void deleteCookies(HttpServletResponse response, String... cookieNames) {
        for (String cookieName : cookieNames) {
            Cookie cookie = new Cookie(cookieName, null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
    }

}



