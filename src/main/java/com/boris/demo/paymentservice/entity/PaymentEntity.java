package com.boris.demo.paymentservice.entity;

import com.boris.demo.paymentservice.enums.PaymentStatusEnum;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table( name="payment" )
public class PaymentEntity extends Auditable {

    @Id
    @GeneratedValue(generator="system-uuid")
    private UUID id;

    @Column( precision=20, scale=2 )
    private BigDecimal amount;

    private Currency currency;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "payee_id", referencedColumnName = "id")
    private UserEntity payee;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "payment_method_id", referencedColumnName = "id")
    private PaymentMethodEntity paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatusEnum paymentStatus;
}
