package com.boris.demo.paymentservice.model;

import com.boris.demo.paymentservice.enums.PaymentMethodNameEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentMethod {
    private UUID id;
    private PaymentMethodNameEnum paymentMethodName;
    private String paymentData; // account number, credit card number, should be replaced with interface
    private Date creationDate;
}
