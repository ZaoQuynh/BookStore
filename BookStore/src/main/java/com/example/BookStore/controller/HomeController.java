package com.example.BookStore.controller;

import com.example.BookStore.dto.ProductDTO;
import com.example.BookStore.exception.ProductNotFoundException;
import com.example.BookStore.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    private final Logger log = LogManager.getLogger(HomeController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/home")
    public String home(Model model) {
        List<ProductDTO> products = Collections.emptyList();

        try {
            products = productService.findActive();
        } catch (ProductNotFoundException e){
            log.error("Error while fetching active product");
        }


        model.addAttribute("products", products);
        return "/home";
    }
}
