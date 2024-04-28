package com.example.BookStore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Payment {
    @Column
    private EPaymentMethod paymentMethod;
    @Column
    private EPaymentStatus paymentStatus;
    @Column
    private Date paymentDate;

    public enum EPaymentMethod{
        CASH, MOMO
    }

    public enum EPaymentStatus{
        UNPAID, REFUNDED, PAID
    }
}
