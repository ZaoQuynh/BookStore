package com.example.BookStore.controller;

import com.example.BookStore.dto.CommentDTO;
import com.example.BookStore.dto.ProductDTO;
import com.example.BookStore.exception.ProductNotFoundException;
import com.example.BookStore.service.OrderItemService;
import com.example.BookStore.service.OrderService;
import com.example.BookStore.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final Logger log = LogManager.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/{productId}")
    public String getProduct(@PathVariable("productId") Long productId, Model model) {
        ProductDTO product = new ProductDTO();
        int amountPurchased = 0;
        List<CommentDTO> comments = Collections.emptyList();
        try{
            product = productService.findById(productId);
            amountPurchased = orderItemService.countQtyProductByProductId(productId);
            comments = orderItemService.findCommentByProductId(productId);
        } catch (ProductNotFoundException e){
            log.error("Error while fetching product with Id: {}", productId, e);
        }
        model.addAttribute("product", product);
        model.addAttribute("amountPurchased", amountPurchased);
        model.addAttribute("comments", comments);
        return "/productDetails";
    }
}
