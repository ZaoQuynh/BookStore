package com.example.BookStore.controller;

import com.example.BookStore.dto.CommentDTO;
import com.example.BookStore.dto.ProductDTO;
import com.example.BookStore.exception.ProductNotFoundException;
import com.example.BookStore.exception.UserNotFoundException;
import com.example.BookStore.service.OrderItemService;
import com.example.BookStore.service.ProductService;
import com.example.BookStore.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @Autowired
    private UserService userService;

    @GetMapping("/{productId}")
    public String getProduct(@PathVariable("productId") Long productId, Model model) {
        ProductDTO product;
        int amountPurchased;
        List<CommentDTO> comments;
        Long userId = userService.getCurrentUserId();
        boolean isChecked = false;
        try{
            product = productService.findActiveById(productId);
            amountPurchased = orderItemService.countQtyProductByProductId(productId);
            comments = orderItemService.findCommentByProductId(productId);
            if(productService.isFavoritedByUser(productId, userId)){
                isChecked = true;
            }
        } catch (ProductNotFoundException e){
            log.error("Error while fetching product with Id: {}", productId, e);
            return "redirect:/home";
        }
        model.addAttribute("isChecked", isChecked);
        model.addAttribute("product", product);
        model.addAttribute("amountPurchased", amountPurchased);
        model.addAttribute("comments", comments);
        return "/productDetails";
    }

    @GetMapping("/favorites")
    public String getFavorites(Model model){
        Long userId = userService.getCurrentUserId();
        List<ProductDTO> favorites = Collections.emptyList();

        try {
            favorites = productService.findFavoriteByUserId(userId);
        } catch (ProductNotFoundException e){
            log.error("Error while fetching favorites with Id: {}", userId, e);
        }

        model.addAttribute("favorites", favorites);
        return "/favoriteManage";
    }

    @PostMapping("/love")
    public String addFavorites(@RequestParam("productId") Long productId, RedirectAttributes ra){
        try{
            Long userId = userService.getCurrentUserId();
            productService.loveProduct(productId, userId);
        } catch (UserNotFoundException e){
            ra.addFlashAttribute("errorMessage", "Đăng nhập để yêu thích sản phẩm.");
        }
        catch (ProductNotFoundException e){
            log.error("Error while loving product with Id: {}", productId, e);
        }
        return "redirect:/product/" + productId;
    }
}
