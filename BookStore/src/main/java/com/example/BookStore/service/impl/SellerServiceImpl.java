package com.example.BookStore.service.impl;

import com.example.BookStore.dto.ProductDTO;
import com.example.BookStore.entity.Product;
import com.example.BookStore.mapper.ProductMapper;
import com.example.BookStore.repos.ProductRepos;
import com.example.BookStore.service.SellerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class SellerServiceImpl implements SellerService {

    private final Logger log = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private ProductRepos productRepos;

    @Autowired
    private ProductMapper mapper;

    @Override
    public List<ProductDTO> findProductByUserId(Long userId) {
        List<Product> products = productRepos.findProductByUserId(userId);
        return mapper.toDto(products);
    }

    @Override
    public  List<ProductDTO> findAll() {
        List<Product> products = productRepos.findAll();
        return mapper.toDto(products);
    }


    @Override
    public void delete(List<ProductDTO> cart) {

    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = mapper.toEntity(productDTO);
        try {
            Product productAdd = productRepos.save(product);
            return mapper.toDto(productAdd);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteAll() {
        productRepos.deleteAll();
    }
}
