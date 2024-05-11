package com.example.BookStore.dto;

import com.example.BookStore.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InforDeliveryDTO {
    private Long id;
    private String receiverName;
    private String province;
    private String district;
    private String ward;
    private String phoneNumber;
    private UserDTO user;
}
