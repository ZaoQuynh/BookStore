package com.example.BookStore.mapper;

import com.example.BookStore.dto.ProductDTO;
import com.example.BookStore.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper extends BaseMapper<Product, ProductDTO>{

    @Autowired
    private BookMapper bookMapper;

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

    @Override
    public ProductDTO toDto(Product entity) {
        ProductDTO dto = super.toDto(entity);
        if (entity.getItem() != null) {
            dto.setItem(bookMapper.toDto(entity.getItem()));
        }
        return dto;
    }

    @Override
    public Product toEntity(ProductDTO dto) {
        Product entity = super.toEntity(dto);
        if (dto.getItem() != null) {
            entity.setItem(bookMapper.toEntity(dto.getItem()));
        }
        return entity;
    }

}
