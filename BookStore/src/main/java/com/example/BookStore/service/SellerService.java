package com.example.BookStore.service;

import com.example.BookStore.dto.ProductDTO;
import com.example.BookStore.entity.Product;

import java.util.List;

public interface SellerService {

     List<ProductDTO> findProductByUserId(Long userId);

     List<ProductDTO> findAll();

     void delete(List<ProductDTO> cart);

     ProductDTO addProduct(ProductDTO productDTO);
     void deleteAll();
}
