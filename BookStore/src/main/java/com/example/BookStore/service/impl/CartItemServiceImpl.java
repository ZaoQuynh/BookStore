package com.example.BookStore.service.impl;

import com.example.BookStore.dto.CartItemDTO;
import com.example.BookStore.entity.CartItem;
import com.example.BookStore.exception.CartItemNotFoundException;
import com.example.BookStore.mapper.CartItemMapper;
import com.example.BookStore.mapper.ProductMapper;
import com.example.BookStore.repos.CartItemRepos;
import com.example.BookStore.service.CartItemService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final Logger log = LogManager.getLogger(CartItemServiceImpl.class);

    @Autowired
    private CartItemRepos repos;
    @Autowired
    private CartItemMapper mapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public CartItemDTO update(CartItemDTO cartItemDTO) {
        CartItem cartItem = repos
                .findById(cartItemDTO.getId())
                .orElseThrow(() -> new CartItemNotFoundException("Could not find any cartItem with Id=" + cartItemDTO.getId()));
        cartItem.setQty(cartItemDTO.getQty());
        cartItem.setProduct(productMapper.toEntity(cartItemDTO.getProduct()));

        try{
            CartItem updated = repos.save(cartItem);
            return mapper.toDto(updated);
        } catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean delete(Long id) {
        Long count = repos.countById(id);
        if (count == null || count == 0)
            throw new CartItemNotFoundException("Could not find any cartItem with Id=" + id);
        try {
            repos.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public CartItemDTO findById(Long id) {
        CartItem cartItem = repos
                .findById(id)
                .orElseThrow(() -> new CartItemNotFoundException("Could not find any cartItem with Id=" + id));
        return mapper.toDto(cartItem);
    }

    @Override
    public CartItemDTO fixNewQuantity(CartItemDTO cartItemDTO, String qtyStr) {
        try {
            int intQty = Integer.valueOf(qtyStr) + cartItemDTO.getQty();
            int maxQty = cartItemDTO.getProduct().getStockQty();
            if(intQty > maxQty)
                intQty = maxQty;
            if(intQty <= 0)
                intQty = 0;
            cartItemDTO.setQty(intQty);
        } catch (Exception e){
            log.error(e.getMessage());
        }
        return cartItemDTO;
    }

    @Override
    public void updateQuantity(CartItemDTO cartItemDTO, String qtyStr) {
        try{
            cartItemDTO = this.fixNewQuantity(cartItemDTO, qtyStr);
            if (cartItemDTO.getQty() == 0) {
                this.delete(cartItemDTO.getId());
            } else {
                this.update(cartItemDTO);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
