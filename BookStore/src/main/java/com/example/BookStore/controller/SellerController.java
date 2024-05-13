package com.example.BookStore.controller;

import com.example.BookStore.dto.BookDTO;
import com.example.BookStore.dto.ProductDTO;
import com.example.BookStore.dto.UserDTO;
import com.example.BookStore.entity.Book;
import com.example.BookStore.entity.Product;
import com.example.BookStore.mapper.ProductMapper;
import com.example.BookStore.mapper.UserMapper;
import com.example.BookStore.repos.ProductRepos;
import com.example.BookStore.repos.UserRepos;
import com.example.BookStore.service.SellerService;
import com.example.BookStore.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("")

public class SellerController {
    private final Logger log = LogManager.getLogger(SellerController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private UserRepos userRepos;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductRepos productRepos;

    @GetMapping("/seller")
    public String getSellerPage(Model model) {
        List<ProductDTO> seller = Collections.emptyList();;
        Long userId = userService.getCurrentUserId();

        List<UserDTO> users = userService.findAll();

//        UserDTO user = new UserDTO();
//        user.setFullName("Phan Vũ Thắng");
//        user.setEmail("Thang@gmail.com");
//        user.setUsername("Thang");
//        user.setPassword("Thang");

//        userService.save(user);

//        sellerService.deleteAll();
        try {
            seller = sellerService.findProductByUserId(userId);
//            seller = sellerService.findAll();
            System.out.println("Get products successfully");
        } catch (Exception e) {
            log.error("Error", userId, e);
        }

        model.addAttribute("seller", seller);
        return "Seller/seller";
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam String title,
                                           @RequestParam String authors, @RequestParam String publisher,
                                           @RequestParam int publisherYear, @RequestParam BigDecimal price,
                                    @RequestParam String description, Model model) {
        try {
            ProductDTO productDTO = new ProductDTO();

            BookDTO bookDTO = new BookDTO();

            bookDTO.setTitle(title);
            bookDTO.setAuthors(authors);
            bookDTO.setPublisher(publisher);
            bookDTO.setPublisherYear(publisherYear);
            bookDTO.setDescription(description);
            bookDTO.setGenre(Book.EGenre.CHILDREN_BOOK);

            productDTO.setPrice(price);

            Long userId = userService.getCurrentUserId();
            UserDTO currentUser = userService.findById(userId);

            productDTO.setSeller(currentUser);

            ProductDTO productNew = sellerService.addProduct(productDTO);

            if (productNew != null) {
                model.addAttribute("Message", "Thêm sách thành công!");
            } else {
                model.addAttribute("Message", "Thêm sách thất bại!");
            }

            return "Seller/seller";
        } catch (Exception e) {
            model.addAttribute("Message", "Thêm sách thất bại!");

            return "Seller/seller";
        }
    }
}
