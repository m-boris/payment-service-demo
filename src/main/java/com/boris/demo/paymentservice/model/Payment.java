package com.boris.demo.paymentservice.model;

import com.boris.demo.paymentservice.enums.PaymentStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

@Data
public class Payment  {

    private UUID id;


    private BigDecimal amount;

    private Currency currency;

    private User user;


    private User payee;


    private PaymentMethod paymentMethod;


    private PaymentStatusEnum paymentStatus;
}
