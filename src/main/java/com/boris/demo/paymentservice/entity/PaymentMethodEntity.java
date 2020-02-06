package com.boris.demo.paymentservice.entity;

import com.boris.demo.paymentservice.enums.PaymentMethodNameEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table( name="payment_methods" )
public class PaymentMethodEntity extends Auditable{

    @Id
    @GeneratedValue(generator="system-uuid")
    private UUID id;

    @Enumerated(EnumType.STRING)
    private PaymentMethodNameEnum paymentMethodName;

    private String paymentData; // account number, credit card number, should be replaced with interface
}
