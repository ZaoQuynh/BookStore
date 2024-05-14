package com.example.BookStore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
