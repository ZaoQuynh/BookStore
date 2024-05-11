package com.example.BookStore.mapper;

import com.example.BookStore.dto.InforDeliveryDTO;
import com.example.BookStore.entity.InforDelivery;
import org.springframework.stereotype.Component;

@Component
public class InforDeliveryMapper extends BaseMapper<InforDelivery, InforDeliveryDTO>{
    @Override
    protected Class<InforDeliveryDTO> getDtoClass() {
        return InforDeliveryDTO.class;
    }

    @Override
    protected Class<InforDelivery> getEntityClass() {
        return InforDelivery.class;
    }

    @Override
    protected void configuration() {
    }
}