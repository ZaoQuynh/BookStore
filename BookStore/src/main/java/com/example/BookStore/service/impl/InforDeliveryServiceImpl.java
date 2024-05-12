package com.example.BookStore.service.impl;

import com.example.BookStore.dto.InforDeliveryDTO;
import com.example.BookStore.entity.InforDelivery;
import com.example.BookStore.exception.InforDeliveryNotFoundException;
import com.example.BookStore.mapper.InforDeliveryMapper;
import com.example.BookStore.repos.InforDeliveryRepos;
import com.example.BookStore.service.InforDeliveryService;
import com.example.BookStore.utility.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InforDeliveryServiceImpl implements InforDeliveryService {
    private final Logger log = LogManager.getLogger(InforDeliveryServiceImpl.class);

    @Autowired
    private InforDeliveryRepos repos;

    @Autowired
    private InforDeliveryMapper mapper;

    @Override
    public List<InforDeliveryDTO> findByUserId(Long userId) {
        List<InforDelivery> inforDeliveries = repos.findByUser_Id(userId);
        if (inforDeliveries == null || inforDeliveries.isEmpty())
            throw new InforDeliveryNotFoundException("Could not find any information delivery with userId=" + userId);
        return mapper.toDto(inforDeliveries);
    }

    @Override
    public InforDeliveryDTO add(InforDeliveryDTO inforDeliveryDTO) {
        InforDelivery inforDelivery = mapper.toEntity(inforDeliveryDTO);
        try {
            InforDelivery added = repos.save(inforDelivery);
            return mapper.toDto(added);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public String CheckInput(InforDeliveryDTO inforDeliveryDTO) {
        String message = null;
        if (Utils.isNullOrEmpty(inforDeliveryDTO.getReceiverName()))
            message = "Vui lòng nhập tên người nhận";
        if(!Utils.isValidVietnamesePhoneNumber(inforDeliveryDTO.getPhoneNumber()))
            message = "Số điện thoại không hợp lệ!";
        if ((Utils.isNullOrEmpty(inforDeliveryDTO.getProvince())
                || Utils.isNullOrEmpty(inforDeliveryDTO.getDistrict())
                || Utils.isNullOrEmpty(inforDeliveryDTO.getWard())))
            message = "Vui lòng chọn địa chỉ";
        return message;
    }

    @Override
    public InforDeliveryDTO findById(Long id) {
        InforDelivery inforDelivery = repos
                .findById(id)
                .orElseThrow(() -> new InforDeliveryNotFoundException("Could not find any cartItem with Id=" + id));
        return mapper.toDto(inforDelivery);
    }
}
