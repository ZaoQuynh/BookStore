package org.example.productmanagement.service.Impl;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.productmanagement.dto.ProductDTO;
import org.example.productmanagement.entity.Product;
import org.example.productmanagement.mapper.ProductMapper;
import org.example.productmanagement.repository.ProductRespository;
import org.example.productmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final Logger log = LogManager.getLogger(UserServiceImpl.class);
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductRespository productRespository;

    @Override
    public List<ProductDTO> findProductByUserId(int userId) {
        List<Product> products = productRespository.findProductByUserId(userId);
        return productMapper.toDto(products);
    }

    @Override
    public ProductDTO findProductById(int productId) {
        Product product = productRespository.findProductById(productId);
        return productMapper.toDto(product);
    }

    @Override
    public ProductDTO add(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        try {
            Product productAdd = productRespository.save(product);
            return productMapper.toDto(productAdd);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }



    @Override
    public ProductDTO update(ProductDTO productDTO, int id) {
        Product product = productRespository.findProductById(id);
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setStockQty(productDTO.getStockQty());
        productRespository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public ProductDTO deleteProduct(int id) {
        Product product = productRespository.findProductById(id);
        product.setDeleted(true);
        productRespository.save(product);
        return productMapper.toDto(product);
    }
}
