package com.example.BookStore.dto;

import com.example.BookStore.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDTO {
    private Payment.EPaymentMethod paymentMethod;
    private Payment.EPaymentStatus paymentStatus;
    private Date paymentDate;


}
