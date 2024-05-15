package org.example.productmanagement.mapper;

import org.example.productmanagement.dto.ProductDTO;
import org.example.productmanagement.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper extends BaseMapper<Product, ProductDTO> {
    @Override
    protected Class getDtoClass() {
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
