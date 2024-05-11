package com.example.BookStore.service;

import com.example.BookStore.dto.InforDeliveryDTO;

import java.util.List;

public interface InforDeliveryService {
    List<InforDeliveryDTO> findByUserId(Long userId);
    InforDeliveryDTO add(InforDeliveryDTO inforDeliveryDTO);
    String CheckInput(InforDeliveryDTO inforDeliveryDTO);
    InforDeliveryDTO findById(Long id);
}
