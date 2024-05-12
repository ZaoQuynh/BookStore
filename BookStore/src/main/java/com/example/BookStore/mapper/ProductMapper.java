package com.example.BookStore.mapper;

import com.example.BookStore.dto.ProductDTO;
import com.example.BookStore.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper extends BaseMapper<Product, ProductDTO>{

    @Override
    protected Class<ProductDTO> getDtoClass() {
        return ProductDTO.class;
    }

    @Override
    protected Class<Product> getEntityClass() {
        return Product.class;
    }

    @Override
    protected void configuration() {
    }
}
