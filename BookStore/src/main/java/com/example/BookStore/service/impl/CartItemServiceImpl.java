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
    public CartItemDTO add(CartItemDTO cartItemDTO) {
        CartItem cartItem = mapper.toEntity(cartItemDTO);
        try {
            CartItem added = repos.save(cartItem);
            return mapper.toDto(added);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

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
    public int checkValue(CartItemDTO cartItemDTO, int qty) {
        int maxQty = cartItemDTO.getProduct().getStockQty();
        if(qty > maxQty)
            qty = maxQty;
        if(qty < 0)
            qty = 1;
        return qty;
    }

    @Override
    public CartItemDTO updateQuantity(CartItemDTO cartItemDTO, int newQty, String event) {
        try{
            if(event.equals("button")){
                newQty += cartItemDTO.getQty();
            }
            cartItemDTO.setQty(checkValue(cartItemDTO, newQty));

            if (cartItemDTO.getQty() == 0) {
                this.delete(cartItemDTO.getId());
            } else {
                return this.update(cartItemDTO);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public CartItemDTO findCartItemByProductAndCustomerId(Long productId, Long customerId) {
        CartItem cartItem = repos
                .findCartItemByProductAndCustomerId(productId, customerId);
        return mapper.toDto(cartItem);
    }
}
