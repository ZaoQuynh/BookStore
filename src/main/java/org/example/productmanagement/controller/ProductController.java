package org.example.productmanagement.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.productmanagement.dto.ProductDTO;
import org.example.productmanagement.dto.UserDTO;
import org.example.productmanagement.entity.Product;
import org.example.productmanagement.repository.ProductRespository;
import org.example.productmanagement.service.ProductService;
import org.example.productmanagement.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final UserService userService;
    private final ProductRespository productRespository;

    @GetMapping("/seller")
    public String getSellerPage(Model model) {
        int userId = userService.getCurrentUserId();
        List<ProductDTO> productList = productService.findProductByUserId(userId);
        model.addAttribute("productList", productList);
        model.addAttribute("productDTO", new ProductDTO());
        return "seller";
    }

    @GetMapping("/seller/editProduct/{id}")
    public String editProductPage(@PathVariable int id, Model model) {
        ProductDTO productDTO = productService.findProductById(id);
        model.addAttribute("productDTO", productDTO);
        System.out.println(productDTO.getId());
        return "edit";
    }

    @PostMapping("/seller/editProduct/{id}")
    public String updateProduct(@ModelAttribute("productDto") ProductDTO productDTO, BindingResult bindingResult, Model model, @PathVariable int id) {
        productService.update(productDTO, id);
        return "redirect:/seller";
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute("product") ProductDTO productdto, Model model) {
        try {
            int userId = userService.getCurrentUserId();

            productdto.setIdUser(userId);

            ProductDTO productNew = productService.add(productdto);

            if (productNew != null) {
                model.addAttribute("Message", "Thêm sách thành công!");
            } else {
                model.addAttribute("Message", "Thêm sách thất bại!");
            }

            return "redirect:seller";
        } catch (Exception e) {
            model.addAttribute("Message", "Thêm sách thất bại!");

            return "redirect:seller";
        }
    }


    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id) {
        try {
            System.out.println(id);
            productService.deleteProduct(id);
            return "redirect:/seller";
        }
        catch (Exception e) {
            return "redirect:/seller";
        }
    }
}
