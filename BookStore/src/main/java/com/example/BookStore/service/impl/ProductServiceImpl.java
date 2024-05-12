package com.example.BookStore.service.impl;

import com.example.BookStore.dto.ProductDTO;
import com.example.BookStore.entity.Product;
import com.example.BookStore.entity.User;
import com.example.BookStore.exception.CartItemNotFoundException;
import com.example.BookStore.exception.ProductNotFoundException;
import com.example.BookStore.exception.UserNotFoundException;
import com.example.BookStore.mapper.BookMapper;
import com.example.BookStore.mapper.ProductMapper;
import com.example.BookStore.mapper.UserMapper;
import com.example.BookStore.repos.ProductRepos;
import com.example.BookStore.repos.UserRepos;
import com.example.BookStore.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {
    private final Logger log = LogManager.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepos repos;
    @Autowired
    private UserRepos userRepos;

    @Autowired
    private ProductMapper mapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public ProductDTO findById(Long id) {
        Product product = repos
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Could not find any product with Id=" + id));
        return mapper.toDto(product);
    }

    @Override
    public void updateStockQty(ProductDTO product, int qtyPurchased) {
        product.setStockQty(product.getStockQty() - qtyPurchased);
        System.out.println(product.getStockQty());
        this.update(product);
    }

    @Override
    public void update(ProductDTO productDTO) {
        Product product = mapper.toEntity(productDTO);
        Product theProduct = repos
                .findById(productDTO.getId())
                .orElseThrow(() -> new CartItemNotFoundException("Could not find any product with Id=" + productDTO.getId()));

        theProduct.setItem(product.getItem());
        theProduct.setStockQty(product.getStockQty());
        theProduct.setBlocked(product.isBlocked());
        theProduct.setDeleted(product.isDeleted());
        theProduct.setDiscountPercent(product.getDiscountPercent());
        theProduct.setPrice(product.getPrice());

        try{
            Product updated = repos.save(theProduct);
            mapper.toDto(updated);
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public List<ProductDTO> findFavoriteProductByUserId(Long userId) {
        List<Product> products = repos.findFavoritesByUserId(userId);
        if (products == null || products.isEmpty())
            throw new ProductNotFoundException("Could not find any favorite with userId=" + userId);
        return mapper.toDto(products);
    }

    @Override
    public boolean isFavoritedByUser(Long productId, Long userId) {
        Optional<Product> product = repos
                .isFavoritedByUser(productId, userId);
        return product.isPresent();
    }

    @Override
    public void loveProduct(Long productId, Long userId) {
        Product product = repos.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        User user = userRepos.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        if (product.getFavoriteByUsers().contains(user)) {
            product.getFavoriteByUsers().remove(user);
            user.getFavoriteProducts().remove(product);
        } else {
            product.getFavoriteByUsers().add(user);
            user.getFavoriteProducts().add(product);
        }

        repos.save(product);
        userRepos.save(user);
    }
}
