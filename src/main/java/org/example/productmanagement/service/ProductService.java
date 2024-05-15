package org.example.productmanagement.service;

import org.example.productmanagement.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findProductByUserId(int userId);
    ProductDTO findProductById(int productId);
    ProductDTO add(ProductDTO productDTO);
    ProductDTO update(ProductDTO productDTO, int id);
    ProductDTO deleteProduct(int id);
}
